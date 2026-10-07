package com.bornfire.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.BAMLSearchIntFilter;
import com.bornfire.entity.CRS_TIN;
import com.bornfire.entity.CrsListRepository;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;
import com.bornfire.entity.EntityTable;
import com.bornfire.entity.xml.ConsolidatedList;
import com.bornfire.entity.xml.Entities;
import com.bornfire.entity.xml.Entity;
import com.bornfire.entity.xml.EntityAddress;
import com.bornfire.entity.xml.EntityAlias;
import com.bornfire.jaxb.convertXML.AddressFixType;
import com.bornfire.jaxb.convertXML.CRSOECD;
import com.bornfire.jaxb.convertXML.ObjectFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.monitorjbl.xlsx.StreamingReader;

import au.com.bytecode.opencsv.CSVReader;

@Service
@ConfigurationProperties("output")
@Transactional
public class CRS_List_Services {

	private static final Logger logger = LoggerFactory.getLogger(CRS_List_Services.class);

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DataSource srcdataSource;

	@Autowired
	EMAIL email;

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	@Autowired
	EMAILREP emailRep;

	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	Environment env;

	@Autowired
	private CrsListRepository crsListRepository;

	//private static String[] columns = {"SL", "Customer ID", "Last Name", "First
	//Name","NID","Risk Category","PEP Description","Status","Membership
	//Date","Date of PEP","Date of Resignation","Resignation Reason","Active
	//Product Type"};

	private static List<CRS_TIN> pepList = new ArrayList<CRS_TIN>();

	public String processUpload(String screenId, MultipartFile file, String userid)
			throws IllegalStateException, IOException {

		String fileName = file.getOriginalFilename();
		File convertedFile = multipartToFile(file, fileName);

		String fileExt = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		logger.info("fileExt: " + fileExt);

		String Errormsg = "";

		String status = "";

//	logger.info("truncating table: Negative List");

		Session theSession = sessionFactory.getCurrentSession();

//	theSession.createSQLQuery(" truncate table NEGATIVE_LIST ").executeUpdate();

//	logger.info("NEGATIVE_LIST truncated");

		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {
			logger.info("reading values from Excel");

			String cellval = "";

			try (InputStream is = new FileInputStream(convertedFile);

					Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is)) {

				for (Sheet s : workbook) {

					logger.info("inside workbook");

					for (Row r : s) {
						ArrayList<String> resultList = new ArrayList<>();
						if (r.getRowNum() == 0) {
							logger.info("r.getRowNum() == 0");
							continue;

						}
						logger.info("2");
						cellval = "";
						String val = null;
						for (int j = 0; j < 15; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								logger.info("3");
								val = null;
							} else {
								val = cell.getStringCellValue();
								logger.info("4");
							}
							resultList.add(val);

						}
						logger.info("5");
						logger.info(resultList.get(0));
						String acct_no = resultList.get(0);
						String messref = resultList.get(1);
						logger.info(resultList.get(1));
						String docrefid = resultList.get(2);
						logger.info(resultList.get(2));
						String first_name = resultList.get(3);
						logger.info(resultList.get(3));
						String last_name = resultList.get(4);
						logger.info(resultList.get(4));
						String street = resultList.get(5);
						logger.info(resultList.get(5));
						String post_code = resultList.get(6);
						logger.info(resultList.get(6));
						String address_1 = resultList.get(7);
						logger.info(resultList.get(7));
						String address = resultList.get(8);
						logger.info(resultList.get(8));
						String city = resultList.get(9);
						logger.info(resultList.get(9));
						String ctry = resultList.get(10);
						logger.info(resultList.get(10));
						String tin = resultList.get(11);
						logger.info(resultList.get(11));
						String crncy = resultList.get(12);
						logger.info(resultList.get(12));

						// String bal = resultList.get(13);
						// logger.info(resultList.get(13));
						String interest = resultList.get(13);
						logger.info(resultList.get(13));
						// String dob = resultList.get(15);
						// logger.info(resultList.get(15));

						/*
						 * BigDecimal bal1 = null; if (bal != null && !bal.isEmpty()) {
						 * logger.info("available2"); bal1 = new BigDecimal(bal);
						 * logger.info("before save bal");
						 * 
						 * }
						 */
						BigDecimal interest1 = null;
						try {
							if (interest != null && !interest.isEmpty()) {
								logger.info("available");
								interest1 = new BigDecimal(interest);
								if (interest == "-") {
									interest1 = new BigDecimal("0");
								}
								logger.info("before save int");
							}
						} catch (Exception e) {
							// Errormsg = "failed "+"Please fix the Start Date of cif_id -"+ cif_id +"
							// Fromat to (DD/MM/YYYY)";
							continue;

						}

						logger.info("before save");
						CRS_TIN info = new CRS_TIN(acct_no, messref, docrefid, first_name, last_name, street, post_code,
								address_1, address, city, ctry, tin, crncy, interest1);
						logger.info("before save1");

						crsListRepository.save(info);
						status = "successfully uploaded";

					}

					logger.info("inserted values into CRS Fund List");
				}

			} catch (Exception e) {

			}
		} else {
			logger.info("reading values from CSV");

			try {

				CSVReader reader = new CSVReader(new FileReader(convertedFile.getAbsolutePath()), ',');
				String[] nextLine;
				int skipRow = 0;
				while ((nextLine = reader.readNext()) != null) {

					if (skipRow > 0) {

						String acct_no = nextLine[0];
						String messref = nextLine[1];
						String docrefid = nextLine[2];
						String first_name = nextLine[3];
						String last_name = nextLine[4];
						String street = nextLine[5];
						String post_code = nextLine[6];
						String address_1 = nextLine[7];
						String address = nextLine[8];
						String city = nextLine[9];
						String ctry = nextLine[10];
						String tin = nextLine[11];
						String crncy = nextLine[12];
						/* String bal = nextLine[13]; */
						String interest = nextLine[13];
						// String dob = nextLine[15];

						BigDecimal interest1 = null;
						if (interest != null && !interest.isEmpty()) {
							interest1 = new BigDecimal(interest);
						}

						/*
						 * BigDecimal bal1 = null; if (bal != null && !bal.isEmpty()) { bal1 = new
						 * BigDecimal(bal); }
						 */

						/*
						 * Date dob1 = null; try { if (dob != null && !dob.isEmpty()) { dob1 = new
						 * SimpleDateFormat("dd/MM/yy").parse(dob); } } catch (Exception e) {
						 * 
						 * continue;
						 * 
						 * }
						 */
						CRS_TIN info = new CRS_TIN(acct_no, messref, docrefid, first_name, last_name, street, post_code,
								address_1, address, city, ctry, tin, crncy, interest1);

						crsListRepository.save(info);

					}

				}
				skipRow++;

				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into HNWI fund ");

			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}

		}

		if (!Errormsg.isEmpty()) {

			return Errormsg;
		} else {
			return status;
		}

	}

	public static File multipartToFile(MultipartFile multipart, String fileName)
			throws IllegalStateException, IOException {

		Path newFile = Paths.get(multipart.getOriginalFilename());
		try (InputStream is = multipart.getInputStream(); OutputStream os = Files.newOutputStream(newFile)) {
			byte[] buffer = new byte[4096];
			int read = 0;
			while ((read = is.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
		}
		return newFile.toFile();

//			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
//			multipart.transferTo(convFile);
//			return convFile;
	}

	@RequestMapping(value = "marshallCRS")
  //  @ResponseBody
	public String GenerateXML(@RequestParam("file") MultipartFile file)
			throws JAXBException, IOException, JsonProcessingException, IllegalStateException {

		String Status = "";
		Session hs = sessionFactory.getCurrentSession();
		List<CRS_TIN> crsTin = new ArrayList<>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from CRS_TIN_TABLE");
		List<Object[]> results = qr.getResultList();
		
		System.out.println(results);
		try {
			AddressFixType crs = new AddressFixType();
			// logger.info("Before URL Read");
			JAXBContext jaxbContext;
			Marshaller jaxbMarshaller;
			StringWriter sw = null;
			try {
				jaxbContext = JAXBContext.newInstance(AddressFixType.class);
				jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
				jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
				// JaxbCharacterEscapeHandler jaxbCharHandler = new
				// JaxbCharacterEscapeHandler();
				// jaxbMarshaller.setProperty("com.sun.xml.bind.characterEscapeHandler",
				// jaxbCharHandler);

				ObjectFactory obj = new ObjectFactory();
				JAXBElement<AddressFixType> jaxbElement = obj.createAddressTypeAddressFix(crs);
				//jaxbMarshaller.marshal(jaxbElement, new FileOutputStream(“C:\Users\kalid\Desktop\outputxml"));
				sw = new StringWriter();
				jaxbMarshaller.marshal(jaxbElement, sw);
			} catch (Exception e) {
				e.printStackTrace();
			}

		

		
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			Status = "error Occured, Please Contact Administrator" + ex;
		}
		return Status;
	}

}
