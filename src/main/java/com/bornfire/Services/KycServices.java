package com.bornfire.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entity.BAML_Doc_Hist_Rep;
import com.bornfire.entity.BAML_Doc_Hist_Table;
import com.bornfire.entity.BAML_Kyc_His_Rep;
import com.bornfire.entity.BAML_Kyc_His_Table;
import com.bornfire.entity.BAML_Risk_History_Rep;
import com.bornfire.entity.BAML_Risk_History_Table;
import com.monitorjbl.xlsx.StreamingReader;

import au.com.bytecode.opencsv.CSVReader;

@Service
@ConfigurationProperties("output")
@Transactional
public class KycServices {

	@Autowired
	BAML_Doc_Hist_Rep bAML_Doc_Hist_Rep;
	
	@Autowired
	BAML_Kyc_His_Rep bAML_kyc_His_Rep;

	@Autowired
	BAML_Risk_History_Rep bAML_Risk_History_Rep;

	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);

	@Autowired
	SessionFactory sessionFactory;

	public String getDetails(BAML_Doc_Hist_Table bAML_Doc_Hist_Table, BAML_Kyc_His_Table bAML_Kyc_His_Table,
			BAML_Risk_History_Table bAML_Risk_History_Table, String formmode) {
		Session hs = sessionFactory.getCurrentSession();

		/*
		 * Optional<KycHistory> up = cmgRepository.findById(kychistory.getCustId());
		 */ // TODO Auto-generated method stub
		String msg = "";

		if (formmode.equals("submit")) {
			System.out.println(formmode);

			BAML_Doc_Hist_Table up = bAML_Doc_Hist_Table;
			BAML_Kyc_His_Table up1 = bAML_Kyc_His_Table;

			hs.saveOrUpdate(up);
			hs.saveOrUpdate(up1);
			// bAML_Doc_Hist_Rep.save(up);
			// bAML_kyc_His_Rep.save(up1);
			msg = "KYC Document Added Successfully";

		} else if (formmode.equals("submitrisk")) {
			System.out.println(formmode);

			BAML_Risk_History_Table up = bAML_Risk_History_Table;

			hs.saveOrUpdate(up);
			bAML_Risk_History_Rep.save(up);
			// bAML_Doc_Hist_Rep.save(up);
			// bAML_kyc_His_Rep.save(up1);
			msg = "Risk Modified Successfully";

		}
		return msg;
	}

	/*
	 * public KycHistory BlobImage(String custid) { Session session =
	 * sessionFactory.getCurrentSession(); System.out.println(custid);
	 * 
	 * @SuppressWarnings("unchecked") List<KycHistory> query = (List<KycHistory>)
	 * session.createQuery("from KycHistory where cust_id=?1") .setParameter(1,
	 * custid).getResultList(); return query.get(0);
	 * 
	 * };
	 */

	public BAML_Doc_Hist_Table BlobImage(String custid) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(custid);
		@SuppressWarnings("unchecked")
		List<BAML_Doc_Hist_Table> query = (List<BAML_Doc_Hist_Table>) session
				.createQuery("from BAML_Doc_Hist_Table where cust_id=?1").setParameter(1, custid).getResultList();
		return query.get(0);

	};

	public static File multipartToFile(MultipartFile multipart, String fileName)
			throws IllegalStateException, IOException {
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipart.transferTo(convFile);
		return convFile;
	}

	public String processUpload(String screenId, MultipartFile file, String userid)
			throws SQLException, FileNotFoundException, IOException {

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

		logger.info("truncating table: SUP1000_S1_MANUAL_TABLE");

		Session theSession = sessionFactory.getCurrentSession();
		theSession.createSQLQuery(" truncate table SUP1000_S1_MANUAL_TABLE ").executeUpdate();

		logger.info("SUP1000_S1_MANUAL_TABLE truncated");

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

							continue;

						}

						cellval = "";
						String val = null;
						for (int j = 0; j < 11; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);

						}

						String complete_flg = resultList.get(0);
						String unique_id = resultList.get(1);
						String acct_number = resultList.get(2);
						String acct_name = resultList.get(3);
						String address = resultList.get(4);
						String last_tran_date = resultList.get(5);
						String acct_currency = resultList.get(6);
						String out_balance = resultList.get(7);
						String date_of_transfer = resultList.get(8);
						String remarks = resultList.get(9);
						String report_date = resultList.get(10);

//					NegativeListEntity sup1000ManualS1 = new NegativeListEntity(complete_flg, unique_id, acct_number,
//							acct_name, address, last_tran_date, acct_currency, out_balance, date_of_transfer,
//							remarks, report_date, "N", userid, new Date());

//					theSession.saveOrUpdate(sup1000ManualS1);
						theSession.flush();
						theSession.clear();
					}

					logger.info("inserted values into Negative List");
				}
//logger.info("inserted values into Negative List");
				status = "success";
			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}
		}

		else {
			logger.info("reading values from CSV");

			try {

				CSVReader reader = new CSVReader(new FileReader(convertedFile.getAbsolutePath()), ',');
				String[] nextLine;
				int skipRow = 0;
				while ((nextLine = reader.readNext()) != null) {

					if (skipRow > 0) {

						String complete_flg = nextLine[0];
						String unique_id = nextLine[1];
						String acct_number = nextLine[2];
						String acct_name = nextLine[3];
						String address = nextLine[4];
						String last_tran_date = nextLine[5];
						String acct_currency = nextLine[6];
						String out_balance = nextLine[7];
						String date_of_transfer = nextLine[8];
						String remarks = nextLine[9];
						String report_date = nextLine[10];

//			NegativeListEntity sup1000ManualS1 = new NegativeListEntity(complete_flg, unique_id, acct_number,
//							acct_name, address, last_tran_date, acct_currency, out_balance, date_of_transfer,
//							remarks, report_date, "N", userid, new Date());

//					theSession.save(sup1000ManualS1);
						theSession.flush();
						theSession.clear();
					}
					skipRow++;
				}
				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into SUP1000_S1_MANUAL_TABLE");
				status = "success";
			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}

		}

		return status;

	}

	public Object addDoc(String alertparam, String formmode) {
		// TODO Auto-generated method stub
		return null;
	}
}