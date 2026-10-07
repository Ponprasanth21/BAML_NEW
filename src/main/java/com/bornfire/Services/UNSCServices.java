package com.bornfire.Services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.bornfire.entity.AMLIndividualRepository;
import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;
import com.bornfire.entity.CMG_MASTER_REPOSITRY;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;
import com.bornfire.entity.EntityTable;
import com.bornfire.entity.EntityTableRepository;
import com.bornfire.entity.IndividualTable;
import com.bornfire.entity.ThirdPartyResponse;
import com.bornfire.entity.xml.ConsolidatedList;
import com.bornfire.entity.xml.Entities;
import com.bornfire.entity.xml.Entity;
import com.bornfire.entity.xml.EntityAddress;
import com.bornfire.entity.xml.EntityAlias;
import com.bornfire.entity.xml.Individual;
import com.bornfire.entity.xml.IndividualAddress;
import com.bornfire.entity.xml.IndividualAlias;
import com.bornfire.entity.xml.IndividualDateOfBirth;
import com.bornfire.entity.xml.IndividualDocument;
import com.bornfire.entity.xml.IndividualPlaceOfBirth;
import com.bornfire.entity.xml.Individuals;
import com.bornfire.entity.xml.LastDayUpdated;
import com.bornfire.entity.xml.ListType;
import com.bornfire.entity.xml.Title;

@Service
@Component
public class UNSCServices {

	private static final Logger logger = LoggerFactory.getLogger(UNSCServices.class);

	@Autowired
	AMLIndividualRepository amlIndividualRepository;

	@Autowired
	EntityTableRepository entityTablelRepository;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	AlertManagementRepository alertrep;

	@Autowired
	EMAILREP emailRep;

	@Autowired
	CMG_MASTER_REPOSITRY cmgMasterRepository;
	

	@Autowired
	Environment env;

	public String addRule(IndividualTable individualTable, String formmode, String dataid) {
		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		return msg;

		/* try { */

	}

	public IndividualTable getSrlNo(String dataid) {
		/* logger.info(id); */
		if (amlIndividualRepository.existsById(dataid)) {
			System.out.println("getsrlno");
			IndividualTable up = amlIndividualRepository.findById(dataid).get();

			return up;
		} else {
			return new IndividualTable();
		}

	};

	public EntityTable getDataId(String dataid) {
		/* logger.info(id); */
		if (entityTablelRepository.existsById(dataid)) {
			System.out.println("getDataId");
			EntityTable up = entityTablelRepository.findById(dataid).get();

			return up;
		} else {
			return new EntityTable();
		}

	};

	public String processUpload(String screenId, MultipartFile file, String userid)
			throws IllegalStateException, IOException {
		return userid;

	}

	public String uploadUNSC() {
		String Status = "";
		try {
			logger.info("Before URL Read");

			String url =  env.getProperty("UNSC.url");
		//	String url = "https://scsanctions.un.org/resources/xml/en/consolidated.xml";

			/*
			 * URL oracle = new URL(url); BufferedReader in = new BufferedReader(new
			 * InputStreamReader(oracle.openStream()));
			 * 
			 * String inputLine; while ((inputLine = in.readLine()) != null)
			 * logger.info(inputLine); in.close();
			 */

			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document doc = b.parse(url);
			logger.info(doc + "URL Read Successfully ");
			String data = getDOCFormat(doc);
			ConsolidatedList ent1 = getUNSCDetails(data);

			logger.info(ent1.getEnt().getEntity().get(0).getComments1());

			Entities ent = ent1.getEnt();

			logger.info("Size of ind List--->" + ent.getEntity().size());

			logger.info("Old Data Removed In BAML_UNSC_ENTITY_TABLE");

			for (int i = 0; i < ent.getEntity().size(); i++) {

				final Integer iCount = new Integer(i);

				String dataid = Optional.ofNullable(ent).map(Entities::getEntity).filter(indList -> !indList.isEmpty())
						.map(indList -> indList.get(iCount)).map(Entity::getDataId).orElse("");
				String versionnum = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getVersionNum).orElse("");
				String first_name = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getFirstName).orElse("");

				String un_list_type = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getUnListType).orElse("");
				String reference_number = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getReferenceNum).orElse("");

				Entity indAll = ent.getEntity().get(i);
				String listed_onstr = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getListedOn).orElse("");

				 logger.info("listed_onstr"+listed_onstr);

				String listed_on1 = String.valueOf(indAll.getListedOn());
				Date listed_on = null;
				if (!listed_onstr.equals("")) {
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
					listed_on = sdf.parse(listed_onstr);
				}
				// sdf.parse(listed_on);

				 System.out.println("loisterdON" + listed_on1);
				// logger.info("Ent_Listed_on:" + listed_on1);
				// logger.info("Ent_Listed_on_Data:" + indAll.toString());
				/*
				 * Date listed_on =
				 * Optional.ofNullable(ent).map(Entities::getEntity).filter(indList ->
				 * !indList.isEmpty()) .map(indList ->
				 * indList.get(iCount)).map(Entity::getListedOn).orElse(null);
				 */
				String name_original_script = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getNameOriginalScript).orElse("");
				String comments1 = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getComments1).orElse("");

				String list_type_value = "";

				if (indAll.getListType() != null) {
					if (indAll.getListType().size() > 0) {
						if (indAll.getListType().get(0).getValue() != null) {
							List<String> list_type_value1 = indAll.getListType().get(0).getValue();

							if (list_type_value1.size() > 0) {
								list_type_value = list_type_value1.get(0);
							}
						}
					}
				}

				String last_day_updated_date = "";

				/*
				 * if(indAll.getLastDayUpdated()!=null) {
				 * if(indAll.getLastDayUpdated().size()>0) {
				 * if(indAll.getLastDayUpdated().get(0).getValue()!=null) { List<Date>
				 * last_day_updated_date1=indAll.getLastDayUpdated().get(0).getValue();
				 * 
				 * if(last_day_updated_date1.size()>0) {
				 * last_day_updated_date=last_day_updated_date1.get(0).toString(); } } } }
				 */

				String entity_alias_quality = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
						.map(indAliasList -> indAliasList.get(0)).map(EntityAlias::getQuality).orElse("");
				String entity_alias_name = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
						.map(indAliasList -> indAliasList.get(0)).map(EntityAlias::getAliasname).orElse("");
				StringBuilder entity_alias_good_name = new StringBuilder();
				StringBuilder entity_alias_low_name = new StringBuilder();

				if (indAll.getEntityAlias() != null) {
					if (indAll.getEntityAlias().size() > 0) {
						List<EntityAlias> IAlias = indAll.getEntityAlias();
						for (int j = 0; j < IAlias.size(); j++) {
							String entity_alias_quality1 = IAlias.get(j).getQuality();

							String entity_alias_name1 = IAlias.get(j).getAliasname();
							if (entity_alias_quality1.equals("a.k.a.")) {

								entity_alias_good_name.append(entity_alias_name1);
								if (j != IAlias.size() - 1) {
									entity_alias_good_name.append(",");
								}
							} else if (entity_alias_quality1.equals("f.k.a.")) {

								entity_alias_low_name.append(entity_alias_name1);
								if (j != IAlias.size() - 1) {
									entity_alias_low_name.append(",");
								}
							}
						}

					}
				}
				String entity_alias_note = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
						.map(indAliasList -> indAliasList.get(0)).map(EntityAlias::getNote).orElse("");
				String entity_address_street = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(EntityAddress::getStreet).orElse("");
				String entity_address_city = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(EntityAddress::getCity).orElse("");
				String entity_address_state_province = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(EntityAddress::getZipcode).orElse("");
				String entity_address_country = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(EntityAddress::getCountry).orElse("");
				String entity_address_note = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getEntityAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(EntityAddress::getNote).orElse("");
				String sort_key = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getSortKey).orElse("");
				String sort_key_last_mod = Optional.ofNullable(ent).map(Entities::getEntity)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Entity::getSortKeyLastMod).orElse("");
				
				Date entry_time= new Date();

				EntityTable entity = new EntityTable(dataid, versionnum, first_name, un_list_type, reference_number,
						listed_on, name_original_script, comments1, list_type_value, last_day_updated_date,
						entity_alias_quality, entity_alias_name, entity_alias_note, entity_address_street,
						entity_address_city, entity_address_state_province, entity_address_country, entity_address_note,
						sort_key, sort_key_last_mod, entity_alias_low_name.toString(),
						entity_alias_good_name.toString(),entry_time);
				entity.setEntity_flg("Y");
				entity.setModify_flg("N");
				entity.setDel_flg("N");
				entityTablelRepository.save(entity);

				Status = "Uploaded Successfully";

			}

			logger.info("Data Refreshed In BAML_UNSC_ENTITY_TABLE");

			BigDecimal email = emailRep.getsequence();
			EmailAlert EA = new EmailAlert();
			String alertcode = "UNSC-UPL";
			AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
			if (AM.getEmail_flg().equals("Y")) {
				EA.setEmail_id(AM.getEmail_1());
				EA.setEmail_id_cc1(AM.getEmail_2());
				EA.setEmail_id_cc2(AM.getEmail_3());
				EA.setEmail_sub(AM.getParam_1());
				EA.setEmail_body("UNSC-Entity Data Refreshed ....");
				EA.setEmail_date(new Date());
				EA.setEmail_srl_no(email);
				EA.setSend_flg("N");
				emailRep.save(EA);
				logger.info("Mail Delivered Sucessfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			Status = "error Occured, Please Contact Administrator" + ex;
			logger.info("ERROR STATUS FOR UNSC " + Status);

		}
		return Status;
	}

	private ConsolidatedList getUNSCDetails(String unscDate) {
		InputStream stream = null;
		JAXBContext jaxBContext;
		JAXBElement<ConsolidatedList> jaxbElement = null;
		try {
			stream = new ByteArrayInputStream(unscDate.getBytes("UTF-8"));
			jaxBContext = JAXBContext.newInstance(ConsolidatedList.class);
			Unmarshaller unMarshaller = jaxBContext.createUnmarshaller();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader xmlEventReader = factory.createXMLEventReader(stream);
			jaxbElement = unMarshaller.unmarshal(xmlEventReader, ConsolidatedList.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		ConsolidatedList document = jaxbElement.getValue();

		return document;
	}

	private String getDOCFormat(Document doc) {
		try {
			StringWriter sw = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			return sw.toString();
		} catch (Exception ex) {
			throw new RuntimeException("Error converting to String", ex);
		}
	}

	public String uploadUNSCInt() {
		String Status = "";
		try {
			//String url = "https://scsanctions.un.org/resources/xml/en/consolidated.xml";
			String url =  env.getProperty("UNSC.url");
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document doc = b.parse(url);

			String data = getDOCFormat(doc);
			ConsolidatedList ent1 = getUNSCDetails(data);

			Individuals ent = ent1.getInd();

			logger.info("Old Data Removed In BAML_UNSC_Individual_Table");
			for (int i = 0; i < ent.getIndividual().size(); i++) {

				final Integer iCount = new Integer(i);

				String dataid = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getDataId).orElse("");

				System.out.println(dataid);
				String versionnum = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getVersionNum).orElse("");
				String first_name = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getFirstName).orElse("");
				String second_name = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getSecondName).orElse("");
				String third_name = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getThirdName).orElse("");
				String un_list_type = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getUnListType).orElse("");
				String reference_number = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getReferenceNum).orElse("");

				Individual indAll = ent.getIndividual().get(i);

				String listed_onstr = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getListedOn).orElse("");

				// logger.info("listed_onstr"+listed_onstr);

				String listed_on1 = String.valueOf(indAll.getListedOn());

				Date listed_on = null;
				if (!listed_onstr.isEmpty()) {

					System.out.println("listed_onstr:" + listed_onstr);
					Date dt1;
					dt1 = new SimpleDateFormat("yyyy-MM-dd").parse(listed_onstr);
					System.out.println("dt1" + dt1);
					listed_on = dt1;
					// DateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-dd");
					// Date ConDate = dateFormat1.parse(listed_onstr);

					// System.out.println(ConDate);
					// DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
					// Date ConDate = formatter1.parse(listed_onstr);
					// String strDate1 = formatter1.format(listed_onstr);
					// listed_on = formatter1.parse(strDate1);
					// System.out.println("ConDate"+ConDate);
					// listed_on = ConDate;
					// System.out.println("HI"+ConDate);
					// System.out.println("listed on"+listed_on);
				}

				// String listed_on1 = String.valueOf(indAll.getListedOn());
				// SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				// sdf.parse(listed_on);

				/// System.out.println("loisterdON" + listed_on1);
				// logger.info("Ind_Listed_on:" + listed_on1);

				/*
				 * Date listed_on = Optional.ofNullable(ent).map(Individuals::getIndividual)
				 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
				 * .map(Individual::getListedOn).orElse(null);
				 */

				String name_original_script = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getNameOriginalScript).orElse("");
				String comments1 = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getComments1).orElse("");

				String designation_value = null;

				if (indAll.getDesignation() != null) {
					if (indAll.getDesignation().size() > 0) {
						List<String> designation_valueValue = indAll.getDesignation().get(0).getValue();
						if (designation_valueValue != null) {
							designation_value = designation_valueValue.get(0);
						}
					}
				}

				/*
				 * List<String> title = Optional.ofNullable(ent).map(Individuals::getIndividual)
				 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
				 * .map(Individual::getTitle).filter(indNationalList ->
				 * !indNationalList.isEmpty()) .map(indNationalList ->
				 * indNationalList.get(0)).map(Title::getValue).orElse(null);
				 */

				String title_value = "";

				if (indAll.getTitle() != null) {
					if (indAll.getTitle().size() > 0) {
						Title titlevalue_Value = indAll.getTitle().get(0);
						if (titlevalue_Value != null) {
							List<String> titlevalue_ValueStr = titlevalue_Value.getValue();
							if (titlevalue_ValueStr != null) {
								if (titlevalue_ValueStr.size() > 0) {
									title_value = titlevalue_ValueStr.get(0);
								}
							}
						}
					}

				}

				/*
				 * List<String> nationality =
				 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
				 * !indList.isEmpty()).map(indList -> indList.get(iCount))
				 * .map(Individual::getNationality).filter(indNationalList ->
				 * !indNationalList.isEmpty()) .map(indNationalList ->
				 * indNationalList.get(0)).map(Nationality::getValue).orElse(null);
				 */

				String nationality_value = "";

				if (indAll.getNationality() != null) {
					if (indAll.getNationality().size() > 0) {
						if (indAll.getNationality().get(0).getValue() != null) {
							List<String> nationality_valueStr = indAll.getNationality().get(0).getValue();

							if (nationality_valueStr.size() > 0) {
								nationality_value = nationality_valueStr.get(0);
							}
						}
					}
				}

				/*
				 * List<String> listType =
				 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
				 * !indList.isEmpty()).map(indList -> indList.get(iCount))
				 * .map(Individual::getListType).filter(indNationalList ->
				 * !indNationalList.isEmpty()) .map(indNationalList ->
				 * indNationalList.get(0)).map(ListType::getValue).orElse(null);
				 */

				String list_type_value = "";

				if (indAll.getListType() != null) {

					if (indAll.getListType().size() > 0) {
						ListType listTypeType = indAll.getListType().get(0);
						if (listTypeType.getValue() != null) {
							if (listTypeType.getValue().size() > 0) {
								List<String> listTypeStr = listTypeType.getValue();
								if (listTypeStr.size() > 0) {
									list_type_value = listTypeStr.get(0);
								}
							}
						}
					}
				}

				Date last_day_updated_date = (Date) Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getLastDayUpdated).filter(indDobList -> !indDobList.isEmpty())
						.map(indDobList -> indDobList.get(0)).map(LastDayUpdated::getValue).orElse(null);

				if (indAll.getLastDayUpdated() != null) {
					if (indAll.getLastDayUpdated().size() > 0) {
						LastDayUpdated lastDateUpdate = indAll.getLastDayUpdated().get(0);
						if (lastDateUpdate.getValue() != null) {
							List<Date> lastDateUpdateStr = lastDateUpdate.getValue();
							if (lastDateUpdateStr.size() > 0) {
								last_day_updated_date = lastDateUpdateStr.get(0);
							}
						}
					}
				}

				String individual_alias_quality = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAlias).filter(indAliasList -> !indAliasList.isEmpty())
						.map(indAliasList -> indAliasList.get(0)).map(IndividualAlias::getAliasName).orElse("");
				String individual_alias_alias_name = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAlias).filter(indAliasList -> !indAliasList.isEmpty())
						.map(indAliasList -> indAliasList.get(0)).map(IndividualAlias::getAliasName).orElse("");
				StringBuilder individual_alias_good_name = new StringBuilder();
				StringBuilder individual_alias_low_name = new StringBuilder();

				if (indAll.getIndividualAlias() != null) {
					if (indAll.getIndividualAlias().size() > 0) {
						List<IndividualAlias> IAlias = indAll.getIndividualAlias();
						for (int j = 0; j < IAlias.size(); j++) {
							String individual_alias_quality1 = IAlias.get(j).getQuality();

							String individual_alias_alias_name1 = IAlias.get(j).getAliasName();
							if (individual_alias_quality1.equals("Good")) {

								individual_alias_good_name.append(individual_alias_alias_name1);
								if (j != IAlias.size() - 1) {
									individual_alias_good_name.append(",");
								}
							} else if (individual_alias_quality1.equals("Low")) {

								individual_alias_low_name.append(individual_alias_alias_name1);
								if (j != IAlias.size() - 1) {
									individual_alias_low_name.append(",");
								}
							}
						}

					}
				}

				String individual_alias_note = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAlias).filter(indAliasList -> !indAliasList.isEmpty())
						.map(indAliasList -> indAliasList.get(0)).map(IndividualAlias::getNote).orElse("");
				String individual_address_street = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(IndividualAddress::getStreet).orElse("");
				String individual_address_state = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(IndividualAddress::getState).orElse("");
				String individual_address_city = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(IndividualAddress::getCity).orElse("");
				String individual_address_state_province = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(IndividualAddress::getStateProvince)
						.orElse("");
				String individual_address_country = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(IndividualAddress::getCountry).orElse("");
				String individual_address_note = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualAddress).filter(indAddressList -> !indAddressList.isEmpty())
						.map(indAddressList -> indAddressList.get(0)).map(IndividualAddress::getNote).orElse("");
				String individual_date_of_birth_type_of_date = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDateOfBirth).filter(indDobList -> !indDobList.isEmpty())
						.map(indDobList -> indDobList.get(0)).map(IndividualDateOfBirth::getTypeOfDate).orElse("");
				String individual_date_of_birth_year = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDateOfBirth).filter(indDobList -> !indDobList.isEmpty())
						.map(indDobList -> indDobList.get(0)).map(IndividualDateOfBirth::getYear).orElse("");
				String individual_date_of_birth_from_year = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDateOfBirth).filter(indDobList -> !indDobList.isEmpty())
						.map(indDobList -> indDobList.get(0)).map(IndividualDateOfBirth::getFromYear).orElse("");
				String individual_date_of_birth_to_year = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDateOfBirth).filter(indDobList -> !indDobList.isEmpty())
						.map(indDobList -> indDobList.get(0)).map(IndividualDateOfBirth::getToYear).orElse("");

				/*
				 * Date individual_date_of_birth_date =
				 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
				 * !indList.isEmpty()).map(indList -> indList.get(iCount))
				 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
				 * !indDobList.isEmpty()) .map(indDobList ->
				 * indDobList.get(0)).map(IndividualDateOfBirth::getDate).orElse(null);
				 */
				//logger.info("individual_date_of_birth_date" + individual_date_of_birth_date);
				
				String dateofbirth = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDateOfBirth).filter(indDobList -> !indDobList.isEmpty())
						.map(indDobList -> indDobList.get(0)).map(IndividualDateOfBirth::getDate).orElse("");

				// logger.info("listed_onstr"+listed_onstr);

				

				Date individual_date_of_birth_date = null;
				if (!dateofbirth.isEmpty()) {

					System.out.println("dateofbirth:" + dateofbirth);
					Date dt1;
					dt1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateofbirth);
					logger.info("dt1" + dt1);
					individual_date_of_birth_date = dt1;
					// DateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-dd");
					// Date ConDate = dateFormat1.parse(listed_onstr);

					// System.out.println(ConDate);
					// DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
					// Date ConDate = formatter1.parse(listed_onstr);
					// String strDate1 = formatter1.format(listed_onstr);
					// listed_on = formatter1.parse(strDate1);
					// System.out.println("ConDate"+ConDate);
					// listed_on = ConDate;
					// System.out.println("HI"+ConDate);
					// System.out.println("listed on"+listed_on);
				}
				String individual_place_of_birth_city = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualPlaceOfBirth).filter(indPobList -> !indPobList.isEmpty())
						.map(indPobList -> indPobList.get(0)).map(IndividualPlaceOfBirth::getCity).orElse("");

				String individual_place_of_birth_state_province = Optional.ofNullable(ent)
						.map(Individuals::getIndividual).filter(indList -> !indList.isEmpty())
						.map(indList -> indList.get(iCount)).map(Individual::getIndividualPlaceOfBirth)
						.filter(indPobList -> !indPobList.isEmpty()).map(indPobList -> indPobList.get(0))
						.map(IndividualPlaceOfBirth::getStateProvince).orElse("");
				String individual_place_of_birth_country = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualPlaceOfBirth).filter(indPobList -> !indPobList.isEmpty())
						.map(indPobList -> indPobList.get(0)).map(IndividualPlaceOfBirth::getCountry).orElse("");
				String individual_place_of_birth_note = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualPlaceOfBirth).filter(indPobList -> !indPobList.isEmpty())
						.map(indPobList -> indPobList.get(0)).map(IndividualPlaceOfBirth::getNote).orElse("");
				String individual_document_type_of_document1 = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getTypeOfDocument).orElse("");
				String individual_document_type_of_document2 = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getTypeOfDocument2).orElse("");
				Date individual_document_date_of_issue = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getDateOfIssue).orElse(null);
				String individual_document_num = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getNum).orElse("");
				String individual_document_note = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getNote).orElse("");
				String individual_document_issuing_country = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getIssuingCountry).orElse("");
				String individual_document_country_of_issue = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getCountryOfIssue).orElse("");
				String individual_document_city_of_issue = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getIndividualDocument).filter(indDocList -> !indDocList.isEmpty())
						.map(indDocList -> indDocList.get(0)).map(IndividualDocument::getCityOfIssue).orElse("");
				String sort_key = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getSortKey).orElse("");
				String sort_key_last_mod = Optional.ofNullable(ent).map(Individuals::getIndividual)
						.filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
						.map(Individual::getSortKeyLastMod).orElse("");
				String full_name = "";
				
				Date entry_time = new Date();
				IndividualTable individual = new IndividualTable(dataid, versionnum, first_name, second_name,
						third_name, un_list_type, reference_number, listed_on, name_original_script, comments1,
						designation_value, title_value, nationality_value, list_type_value, last_day_updated_date,
						individual_alias_quality, individual_alias_alias_name, individual_alias_note,
						individual_address_street, individual_address_state, individual_address_city,
						individual_address_state_province, individual_address_country, individual_address_note,
						individual_date_of_birth_type_of_date, individual_date_of_birth_year,
						individual_date_of_birth_from_year, individual_date_of_birth_to_year,
						individual_date_of_birth_date, individual_place_of_birth_city,
						individual_place_of_birth_state_province, individual_place_of_birth_country,
						individual_place_of_birth_note, individual_document_type_of_document1,
						individual_document_type_of_document2, individual_document_date_of_issue,
						individual_document_num, individual_document_note, individual_document_issuing_country,
						individual_document_country_of_issue, individual_document_city_of_issue, sort_key,
						sort_key_last_mod, individual_alias_low_name.toString(), individual_alias_good_name.toString(),
						full_name,entry_time);
				individual.setEntity_flg("Y");
				individual.setModify_flg("N");
				individual.setDel_flg("N");
				amlIndividualRepository.save(individual);
				Status = "Uploaded Successfully";

			}
			logger.info("Data Refreshed In BAML_UNSC_Individual_Table");
			BigDecimal email = emailRep.getsequence();
			EmailAlert EA = new EmailAlert();
			String alertcode = "UNSC-UPL";
			AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
			if (AM.getEmail_flg().equals("Y")) {
				EA.setEmail_id(AM.getEmail_1());
				EA.setEmail_id_cc1(AM.getEmail_2());
				EA.setEmail_id_cc2(AM.getEmail_3());
				EA.setEmail_sub(AM.getParam_1());
				EA.setEmail_body("UNSC-Individual Data Refreshed ....");
				EA.setEmail_date(new Date());
				EA.setEmail_srl_no(email);
				EA.setSend_flg("N");
				emailRep.save(EA);
				logger.info("Mail Delivered Sucessfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			Status = "error Occured, Please Contact Administrator";
		}
		return Status;
	}

	public ThirdPartyResponse addUnsc(IndividualTable individualTable, String formmode) {

		ThirdPartyResponse msg = new ThirdPartyResponse();
		if (formmode.equals("addindividual")) {
			System.out.println(formmode);
			IndividualTable up = individualTable;
			// String alertcode = up.getReference_number();
			String first_name = up.getFirst_name();
			String second_name = up.getSecond_name();
			String third_name = up.getThird_name();
			String full_name = up.getFull_name();
			System.out.println(first_name);
			System.out.println(second_name);
			System.out.println(third_name);

			// String AM = amlIndividualRepository.findAlldataID(alertcode);
			String firstname = cmgMasterRepository.findByFirstName(first_name, second_name);
			// String secondname= cmgMasterRepository.findBySecondName(second_name);
			// String thirdname = cmgMasterRepository.findByThirdName(third_name);
			System.out.println(firstname);
			// System.out.println(secondname);
			// System.out.println(thirdname);
			/*
			 * if((up.getReference_number()).equals(AM)) { msg = "Already Existing"; }else {
			 */

			if ((up.getThird_name().equals(firstname)) || (up.getFull_name().equals(full_name))) {

				msg.setStatus("Name found in Customer list");
				msg.setTranID("0");
				return msg;
			} else {
				up.setEntity_flg("N");
				up.setDel_flg("N");
				up.setModify_flg("N");
				amlIndividualRepository.save(up);
				msg.setStatus("Added Successfully");

				return msg;
			}
		}else if (formmode.equals("proceed")) {
			IndividualTable up = individualTable;

			up.setEntity_flg("N");
			up.setDel_flg("N");
			up.setModify_flg("N");
			amlIndividualRepository.save(up);
			msg.setStatus("Added Successfully");

			return msg;
		}else if (formmode.equals("editindividual")) {
			IndividualTable up = individualTable;

			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			amlIndividualRepository.save(up);
			msg.setStatus("Modified Successfully");

			return msg;
		} else if (formmode.equals("verifyindividual")) {
			IndividualTable up = individualTable;
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			up.setDel_flg("N");
			amlIndividualRepository.save(up);
			msg.setStatus("Verified Successfully");

			return msg;
		}
		return msg;
	}

	public String addUnscEnt(EntityTable entityTable, String formmode) {

		String msg = "";
		if (formmode.equals("addentity")) {
			EntityTable up = entityTable;
			// Session hs = sessionFactory.getCurrentSession();
			String alertcode = up.getReference_number();
			System.out.println(alertcode);
			String AM = entityTablelRepository.findAlldataID(alertcode);

			System.out.println("AM" + AM);
			if ((up.getReference_number()).equals(AM)) {
				msg = "Already Existing";
			} else {
				up.setEntity_flg("N");
				up.setDel_flg("N");
				up.setModify_flg("N");
				entityTablelRepository.save(up);

				msg = "Added Successfully";
			}

		} else if (formmode.equals("editentity")) {
			EntityTable up = entityTable;

			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			entityTablelRepository.save(up);
			msg = "Edited Successfully";
		} else if (formmode.equals("verifyentity")) {
			EntityTable up = entityTable;
            up.setEntity_flg("Y");
			up.setModify_flg("N");
			up.setDel_flg("N");
			entityTablelRepository.save(up);
			msg = "Verified Successfully";
		}
		return msg;
	}

	public String getSrlNoIndValue() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("v");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT UNSCIND.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "IND" + numformate.format(billNumber);
		System.out.println("billno" + serialno);
		return serialno;
	}

	public String getSrlNoEntValue() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("000");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT UNSCENT.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "ENT" + numformate.format(billNumber);
		System.out.println("billno" + serialno);
		return serialno;
	}

}
