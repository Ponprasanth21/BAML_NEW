package com.bornfire.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.bornfire.Services.AMLAccessRoleService;
import com.bornfire.Services.AccountsInquiry;
import com.bornfire.Services.Case_Management_Services;
import com.bornfire.Services.CustomerMasterService;
import com.bornfire.Services.EMAIL;
import com.bornfire.Services.FinCMGService;
import com.bornfire.Services.KycServices;
import com.bornfire.Services.LoginServices;
import com.bornfire.Services.MonitorParaService;
import com.bornfire.Services.RefCodeService;
import com.bornfire.Services.ReferenceCodeConfigure;
import com.bornfire.Services.T28ReportServices;
import com.bornfire.Services.T8ReportService;
import com.bornfire.Services.ThirdPartyServices;
import com.bornfire.Services.TransactionMasterServices;
import com.bornfire.Services.UNSCServices;
import com.bornfire.Services.UserProfileModService;
import com.bornfire.config.CronJobScheduler;
import com.bornfire.entity.AMLAccessRole;
import com.bornfire.entity.AccountsInquiryEntity;
import com.bornfire.entity.BAML_AUDIT_ENTITY;
import com.bornfire.entity.BAML_Cust_Case_Docs;
import com.bornfire.entity.BAML_Doc_Hist_Table;
import com.bornfire.entity.CMG_MASTER;
import com.bornfire.entity.CMG_MASTER_REPOSITRY;
import com.bornfire.entity.CRS_TIN;
import com.bornfire.entity.ETLMonitorRep;
import com.bornfire.entity.EntityTable;
import com.bornfire.entity.GenRefCodeMast;
import com.bornfire.entity.IndividualTable;
import com.bornfire.entity.Monitoringparameter;
import com.bornfire.entity.MontParameterRepository;
import com.bornfire.entity.RefcodeEntity;
import com.bornfire.entity.ThirdPartyRepository;
import com.bornfire.entity.ThirdPartyResponse;
import com.bornfire.entity.Transaction;
import com.bornfire.entity.t28.T28Reports;
import com.bornfire.entity.t8.T8Report;
import com.bornfire.entity.t8.T8ReportMod;
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
import com.bornfire.entity.xml.Nationality;
import com.bornfire.entity.xml.Title;
import com.bornfire.jaxb.convertXML.AddressFixType;
import com.bornfire.jaxb.convertXML.CRSOECD;
import com.bornfire.jaxb.convertXML.CorrectableOrganisationPartyType;
import com.bornfire.jaxb.convertXML.CrsBodyType;
import com.bornfire.jaxb.convertXML.DocSpecType;
import com.bornfire.jaxb.convertXML.MessageSpecType;
import com.bornfire.jaxb.convertXML.ObjectFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@Transactional
@RequestMapping(value = "AML")
public class AMLRestController {
	@Autowired
	ReferenceCodeConfigure referenceCodeConfigure;

	@Autowired
	AMLAccessRoleService AccessRoleService;

	@Autowired
	UserProfileModService usermodservice;

	@Autowired
	LoginServices loginServices;

	@Autowired
	KycServices kycServices;

	@Autowired
	AccountsInquiry accountsInquirySer;
	@Autowired
	MonitorParaService paraservices;
	@Autowired
	TransactionMasterServices transactionMaServices;
	@Autowired
	FinCMGService CMGService;
	@Autowired
	RefCodeService refCodeService;
	@Autowired
	Case_Management_Services case_management_services;
	@Autowired
	EMAIL email;

	@Autowired
	T28ReportServices t28ReportServices;
	
	@Autowired
	T8ReportService t8reportService;

	@Autowired
	private MontParameterRepository montParameterRepository;
	
	@Autowired
	CMG_MASTER_REPOSITRY cMG_MASTER_REPOSITRY;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	CustomerMasterService customerMasterService;
	
	@Autowired
	ETLMonitorRep eTLMonitorRep;
	
	@Autowired
	UNSCServices unscServices;
	
	@Autowired
	ThirdPartyServices thirdPartyServices;
	
	@Autowired
	ThirdPartyRepository thirdPartyRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UNSCServices.class); 
	
	@RequestMapping(value = "AMLRefCodeConfig/{refCodeView}", method = RequestMethod.GET)
	public List<GenRefCodeMast> refcode(@PathVariable("refCodeView") String refCodeView, Model md) {

		return referenceCodeConfigure.genRefCodeView(refCodeView);
	}

	
	@RequestMapping(value = "etlRun", method = {  RequestMethod.POST })
	public String report_date1(@RequestParam("module_name") String module_name, Model md) throws ParseException {

		return CMGService.runprocedure(module_name);
	}
	@RequestMapping(value = "deleteRuleType/{ruleid}", method = { RequestMethod.GET, RequestMethod.POST })
	public String RuleType(@PathVariable("ruleid") String ruleid, Model md) {

		System.out.println("Ajax");
		return AccessRoleService.deleteRole(ruleid);
	}
	/*
	 * @RequestMapping(value = "CANCELINUSER/{ruleid}", method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String
	 * CANCELINUSER(@PathVariable("ruleid") String ruleid, Model md) {
	 * 
	 * System.out.println("Ajax"); return usermodservice.cancel(ruleid); }
	 */

	@RequestMapping(value = "/getRoleDetails/{roleid}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AMLAccessRole> gettingpatvisitdetail(@PathVariable(value = "roleid", required = true) String roleid) {

		List<AMLAccessRole> roleiddetails = AccessRoleService.gettingaccessDetails(roleid);

		return roleiddetails;
	}

	@RequestMapping(value = "AMLRefCodeConfig/refEdit", method = RequestMethod.POST)
	public int refEdit(@RequestParam String refCode, @RequestParam String refType, @RequestParam String oldSourceCode,
			@RequestParam String newSourceCode, Model md) {

		return referenceCodeConfigure.refEdit(refCode, refType, oldSourceCode, newSourceCode);
	}

	@RequestMapping(value = "AMLRefCodeConfig/refAdd", method = RequestMethod.POST)
	public int refAdd(@RequestParam String refCode, @RequestParam String refType, @RequestParam String SourceCode,
			Model md) {

		return referenceCodeConfigure.refAdd(refCode, refType, SourceCode);
	}

	@RequestMapping(value = "AMLRefCodeConfig/refDelete", method = RequestMethod.POST)
	public int refDelete(@RequestParam String refCode, @RequestParam String refType, @RequestParam String SourceCode,
			Model md) {

		return referenceCodeConfigure.refDelete(refCode, refType, SourceCode);
	}

	/**************************************************
	 * AML ACCOUNT INQUIRY START
	 *******************************************************/
	@RequestMapping(value = "/getACCcustomerId/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AccountsInquiryEntity> getACCcustomerId(@PathVariable(required = true) String CustId) {

		List<AccountsInquiryEntity> userlistunique = accountsInquirySer.getcustomerId(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getACCNumber/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AccountsInquiryEntity> getACCNumber(@PathVariable(required = true) String CustId) {

		List<AccountsInquiryEntity> userlistunique = accountsInquirySer.getACCNumber(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getAccname/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AccountsInquiryEntity> getAccname(@PathVariable(required = true) String CustId) {

		List<AccountsInquiryEntity> userlistunique = accountsInquirySer.getAccname(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getSchcode/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AccountsInquiryEntity> getSchcode(@PathVariable(required = true) String CustId) {

		List<AccountsInquiryEntity> userlistunique = accountsInquirySer.getSchcode(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getCurcode/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AccountsInquiryEntity> getCurcode(@PathVariable(required = true) String CustId) {

		List<AccountsInquiryEntity> userlistunique = accountsInquirySer.getCurcode(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getAccbal/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AccountsInquiryEntity> getAccbal(@PathVariable(required = true) String CustId) {

		List<AccountsInquiryEntity> userlistunique = accountsInquirySer.getAccbal(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	/**************************************************
	 * AML ACCOUNT INQUIRY END
	 *******************************************************/

	/**************************************************
	 * AML Transaction Search Start
	 *******************************************************/
	@RequestMapping(value = "/getTranrefNo/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Transaction> getTranrefNo(@PathVariable(required = true) String CustId) {

		List<Transaction> userlistunique = transactionMaServices.getTranrefNo(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getTrandate/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Transaction> getTrandate(@PathVariable(required = true) String CustId) {

		List<Transaction> userlistunique = transactionMaServices.getTrandate(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getTranId/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Transaction> getTranId(@PathVariable(required = true) String CustId) {

		List<Transaction> userlistunique = transactionMaServices.getTranId(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getPtranId/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Transaction> getPtranId(@PathVariable(required = true) String CustId) {

		List<Transaction> userlistunique = transactionMaServices.getPtranId(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getPtrantype/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Transaction> getPtrantype(@PathVariable(required = true) String CustId) {

		List<Transaction> userlistunique = transactionMaServices.getPtrantype(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getTranamt/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Transaction> getTranamt(@PathVariable(required = true) String CustId) {

		List<Transaction> userlistunique = transactionMaServices.getTranamt(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	@RequestMapping(value = "/getTranstatus/{CustId}", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Transaction> getTranstatus(@PathVariable(required = true) String CustId) {

		List<Transaction> userlistunique = transactionMaServices.getTranstatus(CustId);
		System.out.println(userlistunique);

		return userlistunique;
	}

	/**************************************************
	 * AML Transaction Search Start
	 *******************************************************/

	/*
	 * @RequestMapping(value="userlogList", method = RequestMethod.GET) public
	 * List<XBRLSession> userLogList(@RequestParam String fromdate, @RequestParam
	 * String todate){
	 * 
	 * Date fromdate2=null; Date todate2=null;
	 * 
	 * try { fromdate2 = new SimpleDateFormat("dd-MM-yyyy").parse(fromdate); todate2
	 * = new SimpleDateFormat("dd-MM-yyyy").parse(todate);
	 * 
	 * }catch(Exception e) { e.printStackTrace(); } return
	 * loginServices.getUserLog(fromdate2, todate2);
	 * 
	 * }
	 */

	@RequestMapping(value = "getRuleType/{rulecode}/{rulesubcode}", method = RequestMethod.GET)
	public String RuleType(@PathVariable("rulecode") String rulecode, @PathVariable("rulesubcode") String rulesubcode,
			Model md) {

		return paraservices.getruletypedesc(rulecode, rulesubcode);
	}
	
	@RequestMapping(value = "getCityBirth/{nid1}/{city_code}", method = RequestMethod.GET)
	public CMG_MASTER getCityBirth(@PathVariable("nid1") String nid1, @PathVariable("city_code") String city_code,
			Model md) {

		return cMG_MASTER_REPOSITRY.getCity(nid1, city_code);
	}
	
	@RequestMapping(value = "getRuleCode/{rulecode}", method = RequestMethod.GET)
	public String RuleCode(@PathVariable("rulecode") String rulecode, Model md) {

		return paraservices.getrulecodedesc(rulecode);
	}

	@RequestMapping(value = "getReportCode/{repcode}", method = RequestMethod.GET)
	public String ReportCode(@PathVariable("repcode") String repcode, Model md) {

		return refCodeService.getReportCodedesc(repcode);
	}

	@RequestMapping(value = "getRecordtypeDESC/{repcode}", method = RequestMethod.GET)
	public String RecordTypeDesc(@PathVariable("repcode") String repcode, Model md) {

		return refCodeService.getRecordTypedesc(repcode);
	}

	@RequestMapping(value = "getReferenceCodedescribition/{rectype}/{refcode}", method = RequestMethod.GET)
	public String ReferenceCodeDesc(@PathVariable("rectype") String rectype, @PathVariable("refcode") String refcode,
			Model md) {

		return refCodeService.getReferenceCodedesc(rectype, refcode);
	}

	@RequestMapping(value = "getScriptDesc/{rectype}", method = RequestMethod.GET)
	public String ScriptDesc(@PathVariable("rectype") String rectype, Model md) {

		return paraservices.getScriptdesc(rectype);

	}

	@RequestMapping(value = "getRuleTypeselect/{rulecode}", method = RequestMethod.GET)
	public List<Monitoringparameter> RuleCodeselect(@PathVariable("rulecode") String rulecode, Model md) {

		return paraservices.GetRuleTypeselect(rulecode);
	}

	@RequestMapping(value = "getReferenceCode/{rulecode}", method = RequestMethod.GET)
	public List<RefcodeEntity> getReferenceCode(@PathVariable("rulecode") String rulecode, Model md) {

		return refCodeService.getReferenceCodeselect(rulecode);
	}

	@RequestMapping(value = "getReferenceType/{rectype}/{refcode}", method = RequestMethod.GET)
	public String getReferenceType(@PathVariable("rectype") String rectype, @PathVariable("refcode") String refcode,
			Model md) {

		return refCodeService.getReferenceType(rectype, refcode);
	}

	@RequestMapping(value = "getRuleCodedescribition/{rulecode}", method = RequestMethod.GET)
	public String RuleCodedescselect(@PathVariable("rulecode") String rulecode, Model md) {

		return paraservices.RuleCodedescselect(rulecode);
	}

	@RequestMapping(value = "getBlobImage/{custid}", method = RequestMethod.GET)
	@ResponseBody
	public String BlobImage(@PathVariable("custid") String custid, Model md) {
		BAML_Doc_Hist_Table kycHistory = kycServices.BlobImage(custid);
		System.out.println(kycHistory.getDocimage());
		return Base64.getEncoder().encodeToString(kycHistory.getDocimage());
	}

	@RequestMapping(value = "getBlobImageCase/{custid}", method = RequestMethod.GET)
	@ResponseBody
	public String BlobImage1(@PathVariable("custid") String custid, Model md) {
		BAML_Cust_Case_Docs baml_Cust_Case_Docs = case_management_services.BlobImage(custid);
		System.out.println(baml_Cust_Case_Docs.getDoc_image());
		return Base64.getEncoder().encodeToString(baml_Cust_Case_Docs.getDoc_image());
	}

	public static File multipartToFile(MultipartFile multipart, String fileName)
			throws IllegalStateException, IOException {
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipart.transferTo(convFile);
		return convFile;
	}

	/*
	 * // @RequestMapping(value="marshall2")
	 * 
	 * @PostMapping(path = "/ws/marshallupload")
	 * 
	 * @ResponseBody public String unmarshallindividualRev(@RequestParam("file")
	 * MultipartFile file) throws JAXBException, IOException,
	 * JsonProcessingException, IllegalStateException { String Status = "";
	 * 
	 * try { String url =
	 * "https://scsanctions.un.org/resources/xml/en/consolidated.xml";
	 * DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder b = f.newDocumentBuilder(); Document doc = b.parse(url);
	 * 
	 * String data = getDOCFormat(doc); ConsolidatedList artist =
	 * getUNSCDetails(data);
	 * 
	 * System.out.println(artist.getEnt().getEntity().get(0).getComments1());
	 * 
	 * String fileName = file.getOriginalFilename(); System.out.println("Multi.." +
	 * file.getOriginalFilename());
	 * 
	 * // File xmlFile = ResourceUtils.getFile("classpath:static/xmlfile/inti.xml");
	 * // File xmlFile = ResourceUtils.getFile("xml;charset=UTF-8"); JAXBContext
	 * context = JAXBContext.newInstance(ConsolidatedList.class); Unmarshaller um =
	 * context.createUnmarshaller(); ConsolidatedList ent1 = (ConsolidatedList)
	 * um.unmarshal(file.getInputStream());
	 * 
	 * Entities ent = ent1.getEnt();
	 * 
	 * System.out.println("Size of ind List--->" + ent.getEntity().size()); Session
	 * hs = sessionFactory.getCurrentSession(); Query query =
	 * hs.createQuery("delete EntityTable");
	 * System.out.println("Deleted Sucessfully");
	 * 
	 * for (int i = 0; i < ent.getEntity().size(); i++) {
	 * System.out.println("begin"); System.out.println("i->" + i); Session
	 * theSession = sessionFactory.getCurrentSession();
	 * 
	 * final Integer iCount = new Integer(i);
	 * 
	 * String dataid =
	 * Optional.ofNullable(ent).map(Entities::getEntity).filter(indList ->
	 * !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getDataId).orElse("");
	 * System.out.println("dataid" + dataid); String versionnum =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getVersionNum).orElse(""); System.out.println("versionnum" +
	 * versionnum); String first_name =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getFirstName).orElse(""); System.out.println("first_name" +
	 * first_name);
	 * 
	 * String un_list_type = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getUnListType).orElse(""); System.out.println("un_list_type" +
	 * un_list_type); String reference_number =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getReferenceNum).orElse("");
	 * System.out.println("reference_number" + reference_number); Date listed_on =
	 * Optional.ofNullable(ent).map(Entities::getEntity).filter(indList ->
	 * !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getListedOn).orElse(null);
	 * System.out.println("listed_on" + listed_on); String name_original_script =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getNameOriginalScript).orElse("");
	 * System.out.println("nameOriginalScript" + name_original_script); String
	 * comments1 = Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList
	 * -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getComments1).orElse(""); System.out.println("comments1" +
	 * comments1);
	 * 
	 * String list_type_value = null;
	 * 
	 * // String nationality_value=String.join("", //
	 * ent.getIndividual().get(i).getNationality().get(0).getValue()); String
	 * last_day_updated_date = null; //
	 * System.out.println(ent.getEntity().get(i).getEntityAlias().get(0).getQuality(
	 * )); String entity_alias_quality =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
	 * .map(indAliasList ->
	 * indAliasList.get(0)).map(EntityAlias::getQuality).orElse("");
	 * System.out.println("entity_alias_quality" + entity_alias_quality); String
	 * entity_alias_name = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
	 * .map(indAliasList ->
	 * indAliasList.get(0)).map(EntityAlias::getAliasname).orElse("");
	 * System.out.println("entity_alias_alias_name" + entity_alias_name); String
	 * entity_alias_note = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
	 * .map(indAliasList ->
	 * indAliasList.get(0)).map(EntityAlias::getNote).orElse("");
	 * System.out.println("entity_alias_note" + entity_alias_note); String
	 * entity_address_street = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getStreet).orElse("");
	 * System.out.println("entity_address_street" + entity_address_street); String
	 * entity_address_city = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getCity).orElse("");
	 * System.out.println("entity_address_city" + entity_address_city); String
	 * entity_address_state_province =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getZipcode).orElse("");
	 * System.out.println("entity_address_state_province" +
	 * entity_address_state_province); String entity_address_country =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getCountry).orElse("");
	 * System.out.println("entity_address_country" + entity_address_country); String
	 * entity_address_note = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getNote).orElse("");
	 * System.out.println("entity_address_note" + entity_address_note); String
	 * sort_key = Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList
	 * -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getSortKey).orElse(""); System.out.println("sort_key" +
	 * sort_key); String sort_key_last_mod =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getSortKeyLastMod).orElse("");
	 * System.out.println("sort_key_last_mod" + sort_key_last_mod);
	 * 
	 * EntityTable entity = new EntityTable(dataid, versionnum, first_name,
	 * un_list_type, reference_number, listed_on, name_original_script, comments1,
	 * list_type_value, last_day_updated_date, entity_alias_quality,
	 * entity_alias_name, entity_alias_note, entity_address_street,
	 * entity_address_city, entity_address_state_province, entity_address_country,
	 * entity_address_note, sort_key, sort_key_last_mod);
	 * 
	 * // System.out.println("Old Entity Table removed");
	 * theSession.saveOrUpdate(entity); theSession.flush(); theSession.clear();
	 * System.out.println("saved into database");
	 * 
	 * Status = "Uploaded Successfully";
	 * 
	 * } } catch (Exception ex) { ex.printStackTrace(); Status =
	 * "error Occured, Please Contact Administrator"; } return Status; }
	 * 
	 * private ConsolidatedList getUNSCDetails(String unscDate) { InputStream stream
	 * = null; JAXBContext jaxBContext; JAXBElement<ConsolidatedList> jaxbElement =
	 * null; try { stream = new ByteArrayInputStream(unscDate.getBytes("UTF-8"));
	 * jaxBContext = JAXBContext.newInstance(ConsolidatedList.class); Unmarshaller
	 * unMarshaller = jaxBContext.createUnmarshaller(); XMLInputFactory factory =
	 * XMLInputFactory.newInstance(); XMLEventReader xmlEventReader =
	 * factory.createXMLEventReader(stream); jaxbElement =
	 * unMarshaller.unmarshal(xmlEventReader, ConsolidatedList.class); } catch
	 * (JAXBException e) { e.printStackTrace(); } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); } catch
	 * (XMLStreamException e) { e.printStackTrace(); } ConsolidatedList document =
	 * jaxbElement.getValue();
	 * 
	 * return document; }
	 * 
	 * private String getDOCFormat(Document doc) { try { StringWriter sw = new
	 * StringWriter(); TransformerFactory tf = TransformerFactory.newInstance();
	 * Transformer transformer = tf.newTransformer();
	 * transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	 * transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	 * transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	 * transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	 * 
	 * transformer.transform(new DOMSource(doc), new StreamResult(sw)); return
	 * sw.toString(); } catch (Exception ex) { throw new
	 * RuntimeException("Error converting to String", ex); } }
	 * 
	 * @PostMapping(path = "/ws/marshallappend")
	 * 
	 * @ResponseBody public String entitiesAppend(@RequestParam("file")
	 * MultipartFile file) throws JAXBException, IOException,
	 * JsonProcessingException, IllegalStateException { String Status = "";
	 * 
	 * String fileName = file.getOriginalFilename(); System.out.println("Multi.." +
	 * file.getOriginalFilename());
	 * 
	 * // File xmlFile = ResourceUtils.getFile("classpath:static/xmlfile/inti.xml");
	 * // File xmlFile = ResourceUtils.getFile("xml;charset=UTF-8"); JAXBContext
	 * context = JAXBContext.newInstance(ConsolidatedList.class); Unmarshaller um =
	 * context.createUnmarshaller(); ConsolidatedList ent1 = (ConsolidatedList)
	 * um.unmarshal(file.getInputStream());
	 * 
	 * Entities ent = ent1.getEnt();
	 * 
	 * System.out.println("Size of ind List--->" + ent.getEntity().size());
	 * 
	 * for (int i = 0; i < ent.getEntity().size(); i++) {
	 * System.out.println("begin"); System.out.println("i->" + i); Session
	 * theSession = sessionFactory.getCurrentSession();
	 * 
	 * final Integer iCount = new Integer(i);
	 * 
	 * String dataid =
	 * Optional.ofNullable(ent).map(Entities::getEntity).filter(indList ->
	 * !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getDataId).orElse("");
	 * System.out.println("dataid" + dataid); String versionnum =
	 * Optional.ofNullable(ent).map(Entities::getEntity).filter(indList ->
	 * !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getVersionNum).orElse("");
	 * System.out.println("versionnum" + versionnum); String first_name =
	 * Optional.ofNullable(ent).map(Entities::getEntity).filter(indList ->
	 * !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getFirstName).orElse("");
	 * System.out.println("first_name" + first_name);
	 * 
	 * String un_list_type = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getUnListType).orElse(""); System.out.println("un_list_type" +
	 * un_list_type); String reference_number =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getReferenceNum).orElse("");
	 * System.out.println("reference_number" + reference_number); Date listed_on =
	 * Optional.ofNullable(ent).map(Entities::getEntity).filter(indList ->
	 * !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getListedOn).orElse(null);
	 * System.out.println("listed_on" + listed_on); String name_original_script =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getNameOriginalScript).orElse("");
	 * System.out.println("nameOriginalScript" + name_original_script); String
	 * comments1 = Optional.ofNullable(ent).map(Entities::getEntity).filter(indList
	 * -> !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getComments1).orElse("");
	 * System.out.println("comments1" + comments1);
	 * 
	 * String list_type_value = null;
	 * 
	 * // String nationality_value=String.join("", //
	 * ent.getIndividual().get(i).getNationality().get(0).getValue()); String
	 * last_day_updated_date = null; //
	 * System.out.println(ent.getEntity().get(i).getEntityAlias().get(0).getQuality(
	 * )); String entity_alias_quality =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
	 * .map(indAliasList ->
	 * indAliasList.get(0)).map(EntityAlias::getQuality).orElse("");
	 * System.out.println("entity_alias_quality" + entity_alias_quality); String
	 * entity_alias_name = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
	 * .map(indAliasList ->
	 * indAliasList.get(0)).map(EntityAlias::getAliasname).orElse("");
	 * System.out.println("entity_alias_alias_name" + entity_alias_name); String
	 * entity_alias_note = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAlias).filter(indAliasList -> !indAliasList.isEmpty())
	 * .map(indAliasList ->
	 * indAliasList.get(0)).map(EntityAlias::getNote).orElse("");
	 * System.out.println("entity_alias_note" + entity_alias_note); String
	 * entity_address_street = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getStreet).orElse("");
	 * System.out.println("entity_address_street" + entity_address_street); String
	 * entity_address_city = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getCity).orElse("");
	 * System.out.println("entity_address_city" + entity_address_city); String
	 * entity_address_state_province =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getZipcode).orElse("");
	 * System.out.println("entity_address_state_province" +
	 * entity_address_state_province); String entity_address_country =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getCountry).orElse("");
	 * System.out.println("entity_address_country" + entity_address_country); String
	 * entity_address_note = Optional.ofNullable(ent).map(Entities::getEntity)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getEntityAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(EntityAddress::getNote).orElse("");
	 * System.out.println("entity_address_note" + entity_address_note); String
	 * sort_key = Optional.ofNullable(ent).map(Entities::getEntity).filter(indList
	 * -> !indList.isEmpty()) .map(indList ->
	 * indList.get(iCount)).map(Entity::getSortKey).orElse("");
	 * System.out.println("sort_key" + sort_key); String sort_key_last_mod =
	 * Optional.ofNullable(ent).map(Entities::getEntity) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Entity::getSortKeyLastMod).orElse("");
	 * System.out.println("sort_key_last_mod" + sort_key_last_mod);
	 * 
	 * EntityTable entity = new EntityTable(dataid, versionnum, first_name,
	 * un_list_type, reference_number, listed_on, name_original_script, comments1,
	 * list_type_value, last_day_updated_date, entity_alias_quality,
	 * entity_alias_name, entity_alias_note, entity_address_street,
	 * entity_address_city, entity_address_state_province, entity_address_country,
	 * entity_address_note, sort_key, sort_key_last_mod);
	 * 
	 * // System.out.println("Old Entity Table removed");
	 * theSession.saveOrUpdate(entity); theSession.flush(); theSession.clear();
	 * System.out.println("saved into database"); System.out.println("end"); Status
	 * = "Appended Successfully";
	 * 
	 * }
	 * 
	 * return Status; }
	 * 
	 * @PostMapping(path = "/ws/marshallindupload")
	 * 
	 * @ResponseBody public String unmarshallindividual(@RequestParam("file")
	 * MultipartFile file) throws JAXBException, IOException,
	 * JsonProcessingException, IllegalStateException {
	 * 
	 * String Status = "";
	 * 
	 * String fileName = file.getOriginalFilename(); System.out.println("Multi" +
	 * file.getOriginalFilename());
	 * 
	 * // File xmlFile = ResourceUtils.getFile("classpath:static/xmlfile/inti.xml");
	 * // File xmlFile = ResourceUtils.getFile("xml;charset=UTF-8"); JAXBContext
	 * context = JAXBContext.newInstance(ConsolidatedList.class); Unmarshaller um =
	 * context.createUnmarshaller(); // ConsolidatedList ent = (Individuals)
	 * um.unmarshal(file.getInputStream()); ConsolidatedList ent1 =
	 * (ConsolidatedList) um.unmarshal(file.getInputStream());
	 * 
	 * Individuals ent = ent1.getInd();
	 * 
	 * System.out.println("Size of ind List--->" + ent.getIndividual().size());
	 * Session hs = sessionFactory.getCurrentSession(); Query query =
	 * hs.createQuery("delete IndividualTable");
	 * System.out.println("Deleted Sucessfully");
	 * 
	 * for (int i = 0; i < ent.getIndividual().size(); i++) {
	 * System.out.println("begin"); System.out.println("i->" + i); //
	 * System.out.println(ent.getIndividual().get(0)); Session theSession =
	 * sessionFactory.getCurrentSession();
	 * 
	 * final Integer iCount = new Integer(i);
	 * 
	 * String dataid = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getDataId).orElse(""); System.out.println("dataid" +
	 * dataid); String versionnum =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getVersionNum).orElse(""); System.out.println("versionnum" +
	 * versionnum); String first_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getFirstName).orElse(""); System.out.println("first_name" +
	 * first_name); String second_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getSecondName).orElse(""); System.out.println("second_name"
	 * + second_name); String third_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getThirdName).orElse(""); System.out.println("third_name" +
	 * third_name);
	 * 
	 * String un_list_type =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getUnListType).orElse(""); System.out.println("un_list_type"
	 * + un_list_type); String reference_number =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getReferenceNum).orElse("");
	 * System.out.println("reference_number" + reference_number); Date listed_on =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getListedOn).orElse(null); System.out.println("listed_on" +
	 * listed_on);
	 * 
	 * String name_original_script =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getNameOriginalScript).orElse("");
	 * System.out.println("nameOriginalScript" + name_original_script); String
	 * comments1 = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getComments1).orElse(""); System.out.println("comments1" +
	 * comments1);
	 * 
	 * String designation_value = null;
	 * 
	 * System.out.println("designation_value" + designation_value); List<String>
	 * title = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList ->
	 * indList.get(iCount)).map(Individual::getTitle) .filter(indNationalList ->
	 * !indNationalList.isEmpty()) .map(indNationalList ->
	 * indNationalList.get(0)).map(Title::getValue).orElse(null); String title_value
	 * = ""; System.out.println("title" + title);
	 * 
	 * List<String> nationality =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getNationality).filter(indNationalList ->
	 * !indNationalList.isEmpty()) .map(indNationalList ->
	 * indNationalList.get(0)).map(Nationality::getValue).orElse(null);
	 * 
	 * String nationality_value = "";
	 * 
	 * System.out.println("nationality_value" + nationality_value); List<String>
	 * listType = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getListType).filter(indNationalList ->
	 * !indNationalList.isEmpty()) .map(indNationalList ->
	 * indNationalList.get(0)).map(ListType::getValue).orElse(null);
	 * 
	 * String list_type_value = "";
	 * 
	 * // String nationality_value=String.join("", //
	 * ent.getIndividual().get(i).getNationality().get(0).getValue()); Date
	 * last_day_updated_date = (Date)
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getLastDayUpdated).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(LastDayUpdated::getValue).orElse(null);
	 * 
	 * String individual_alias_quality =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAlias).filter(indAliasList ->
	 * !indAliasList.isEmpty()) .map(indAliasList ->
	 * indAliasList.get(0)).map(IndividualAlias::getAliasName).orElse("");
	 * System.out.println("individual_alias_quality" + individual_alias_quality);
	 * String individual_alias_alias_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAlias).filter(indAliasList ->
	 * !indAliasList.isEmpty()) .map(indAliasList ->
	 * indAliasList.get(0)).map(IndividualAlias::getAliasName).orElse("");
	 * System.out.println("individual_alias_alias_name" +
	 * individual_alias_alias_name); String individual_alias_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAlias).filter(indAliasList ->
	 * !indAliasList.isEmpty()) .map(indAliasList ->
	 * indAliasList.get(0)).map(IndividualAlias::getNote).orElse("");
	 * System.out.println("individual_alias_note" + individual_alias_note); String
	 * individual_address_street =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getStreet).orElse("");
	 * System.out.println("individual_address_street" + individual_address_street);
	 * String individual_address_state =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getState).orElse("");
	 * System.out.println("individual_address_state" + individual_address_state);
	 * String individual_address_city =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getCity).orElse("");
	 * System.out.println("individual_address_city" + individual_address_city);
	 * String individual_address_state_province =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getStateProvince).orElse("");
	 * System.out.println("individual_address_state_province" +
	 * individual_address_state_province); String individual_address_country =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getCountry).orElse("");
	 * System.out.println("individual_address_country" +
	 * individual_address_country); String individual_address_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getNote).orElse("");
	 * System.out.println("individual_address_note" + individual_address_note);
	 * String individual_date_of_birth_type_of_date =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getTypeOfDate).orElse("");
	 * System.out.println("individual_date_of_birth_type_of_date" +
	 * individual_date_of_birth_type_of_date); String individual_date_of_birth_year
	 * = Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getYear).orElse("");
	 * System.out.println("individual_date_of_birth_year" +
	 * individual_date_of_birth_year); String individual_date_of_birth_from_year =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getFromYear).orElse("");
	 * System.out.println("individual_date_of_birth_from_year" +
	 * individual_date_of_birth_from_year); String individual_date_of_birth_to_year
	 * = Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getToYear).orElse("");
	 * System.out.println("individual_date_of_birth_to_year" +
	 * individual_date_of_birth_to_year);// String //
	 * individual_date_of_birth_to_year=ent.getIndividual().get(i).
	 * getIndividualDateOfBirth().get(0).getToYear();
	 * 
	 * Date individual_date_of_birth_date =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getDate).orElse(null);
	 * System.out.println("individual_date_of_birth_date" +
	 * individual_date_of_birth_date); String individual_place_of_birth_city =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getCity).orElse("");
	 * System.out.println("individual_place_of_birth_city" +
	 * individual_place_of_birth_city); String
	 * individual_place_of_birth_state_province =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getStateProvince).orElse("");
	 * System.out.println("individual_place_of_birth_state_province" +
	 * individual_place_of_birth_state_province); String
	 * individual_place_of_birth_country =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getCountry).orElse("");
	 * System.out.println("individual_place_of_birth_country" +
	 * individual_place_of_birth_country); String individual_place_of_birth_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getNote).orElse("");
	 * System.out.println("individual_place_of_birth_note" +
	 * individual_place_of_birth_note); String individual_document_type_of_document1
	 * = Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getTypeOfDocument).orElse("");
	 * System.out.println("individual_document_type_of_document1" +
	 * individual_document_type_of_document1); String
	 * individual_document_type_of_document2 =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getTypeOfDocument2).orElse("");
	 * System.out.println("individual_document_type_of_document2" +
	 * individual_document_type_of_document2); // Date //
	 * individual_document_date_of_issue=ent.getIndividual().get(i).
	 * getIndividualDocument().get(0).getDateOfIssue(); // Date //
	 * individual_document_date_of_issue=ent.getIndividual().get(i).
	 * getIndividualDocument().get(0).getDateOfIssue(); Date
	 * individual_document_date_of_issue =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getDateOfIssue).orElse(null);
	 * System.out.println("individual_document_date_of_issue" +
	 * individual_document_date_of_issue); String individual_document_num =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getNum).orElse("");
	 * System.out.println("individual_document_num" + individual_document_num);
	 * String individual_document_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getNote).orElse("");
	 * System.out.println("individual_document_note" + individual_document_note);
	 * String individual_document_issuing_country =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getIssuingCountry).orElse("");
	 * System.out.println("individual_document_issuing_country" +
	 * individual_document_issuing_country); String
	 * individual_document_country_of_issue =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getCountryOfIssue).orElse("");
	 * System.out.println("individual_document_country_of_issue" +
	 * individual_document_country_of_issue); String
	 * individual_document_city_of_issue =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getCityOfIssue).orElse("");
	 * System.out.println("individual_document_city_of_issue" +
	 * individual_document_city_of_issue); String sort_key =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getSortKey).orElse(""); System.out.println("sort_key" +
	 * sort_key); String sort_key_last_mod =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getSortKeyLastMod).orElse("");
	 * System.out.println("sort_key_last_mod" + sort_key_last_mod);
	 * 
	 * IndividualTable individual = new IndividualTable(dataid, versionnum,
	 * first_name, second_name, third_name, un_list_type, reference_number,
	 * listed_on, name_original_script, comments1, designation_value, title_value,
	 * nationality_value, list_type_value, last_day_updated_date,
	 * individual_alias_quality, individual_alias_alias_name, individual_alias_note,
	 * individual_address_street, individual_address_state, individual_address_city,
	 * individual_address_state_province, individual_address_country,
	 * individual_address_note, individual_date_of_birth_type_of_date,
	 * individual_date_of_birth_year, individual_date_of_birth_from_year,
	 * individual_date_of_birth_to_year, individual_date_of_birth_date,
	 * individual_place_of_birth_city, individual_place_of_birth_state_province,
	 * individual_place_of_birth_country, individual_place_of_birth_note,
	 * individual_document_type_of_document1, individual_document_type_of_document2,
	 * individual_document_date_of_issue, individual_document_num,
	 * individual_document_note, individual_document_issuing_country,
	 * individual_document_country_of_issue, individual_document_city_of_issue,
	 * sort_key, sort_key_last_mod);
	 * 
	 * // System.out.println("Old Individual Table removed");
	 * theSession.saveOrUpdate(individual); theSession.flush(); theSession.clear();
	 * System.out.println("saved into database"); System.out.println("end"); Status
	 * = "Uploaded Successfully"; // return Status; }
	 * 
	 * return Status; }
	 * 
	 * @PostMapping(path = "/ws/marshallindappend")
	 * 
	 * @ResponseBody public String unmarshallindividualappend(@RequestParam("file")
	 * MultipartFile file) throws JAXBException, IOException,
	 * JsonProcessingException, IllegalStateException { String Status = "";
	 * 
	 * String fileName = file.getOriginalFilename(); System.out.println("Multi" +
	 * file.getOriginalFilename());
	 * 
	 * // File xmlFile = ResourceUtils.getFile("classpath:static/xmlfile/inti.xml");
	 * // File xmlFile = ResourceUtils.getFile("xml;charset=UTF-8"); JAXBContext
	 * context = JAXBContext.newInstance(ConsolidatedList.class); Unmarshaller um =
	 * context.createUnmarshaller(); // ConsolidatedList ent = (Individuals)
	 * um.unmarshal(file.getInputStream()); ConsolidatedList ent1 =
	 * (ConsolidatedList) um.unmarshal(file.getInputStream());
	 * 
	 * Individuals ent = ent1.getInd();
	 * 
	 * System.out.println("Size of ind List--->" + ent.getIndividual().size());
	 * 
	 * for (int i = 0; i < ent.getIndividual().size(); i++) {
	 * System.out.println("begin"); System.out.println("i->" + i); //
	 * System.out.println(ent.getIndividual().get(0)); Session theSession =
	 * sessionFactory.getCurrentSession();
	 * 
	 * final Integer iCount = new Integer(i);
	 * 
	 * String dataid = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getDataId).orElse(""); System.out.println("dataid" +
	 * dataid); String versionnum =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getVersionNum).orElse(""); System.out.println("versionnum" +
	 * versionnum); String first_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getFirstName).orElse(""); System.out.println("first_name" +
	 * first_name); String second_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getSecondName).orElse(""); System.out.println("second_name"
	 * + second_name); String third_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getThirdName).orElse(""); System.out.println("third_name" +
	 * third_name);
	 * 
	 * String un_list_type =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getUnListType).orElse(""); System.out.println("un_list_type"
	 * + un_list_type); String reference_number =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getReferenceNum).orElse("");
	 * System.out.println("reference_number" + reference_number); Date listed_on =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getListedOn).orElse(null); System.out.println("listed_on" +
	 * listed_on);
	 * 
	 * String name_original_script =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getNameOriginalScript).orElse("");
	 * System.out.println("nameOriginalScript" + name_original_script); String
	 * comments1 = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getComments1).orElse(""); System.out.println("comments1" +
	 * comments1);
	 * 
	 * String designation_value = null;
	 * 
	 * System.out.println("designation_value" + designation_value); List<String>
	 * title = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList ->
	 * indList.get(iCount)).map(Individual::getTitle) .filter(indNationalList ->
	 * !indNationalList.isEmpty()) .map(indNationalList ->
	 * indNationalList.get(0)).map(Title::getValue).orElse(null); String title_value
	 * = ""; System.out.println("title" + title);
	 * 
	 * List<String> nationality =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getNationality).filter(indNationalList ->
	 * !indNationalList.isEmpty()) .map(indNationalList ->
	 * indNationalList.get(0)).map(Nationality::getValue).orElse(null);
	 * 
	 * String nationality_value = "";
	 * 
	 * System.out.println("nationality_value" + nationality_value); List<String>
	 * listType = Optional.ofNullable(ent).map(Individuals::getIndividual)
	 * .filter(indList -> !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getListType).filter(indNationalList ->
	 * !indNationalList.isEmpty()) .map(indNationalList ->
	 * indNationalList.get(0)).map(ListType::getValue).orElse(null);
	 * 
	 * String list_type_value = "";
	 * 
	 * // String nationality_value=String.join("", //
	 * ent.getIndividual().get(i).getNationality().get(0).getValue()); Date
	 * last_day_updated_date = (Date)
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getLastDayUpdated).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(LastDayUpdated::getValue).orElse(null);
	 * 
	 * String individual_alias_quality =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAlias).filter(indAliasList ->
	 * !indAliasList.isEmpty()) .map(indAliasList ->
	 * indAliasList.get(0)).map(IndividualAlias::getAliasName).orElse("");
	 * System.out.println("individual_alias_quality" + individual_alias_quality);
	 * String individual_alias_alias_name =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAlias).filter(indAliasList ->
	 * !indAliasList.isEmpty()) .map(indAliasList ->
	 * indAliasList.get(0)).map(IndividualAlias::getAliasName).orElse("");
	 * System.out.println("individual_alias_alias_name" +
	 * individual_alias_alias_name); String individual_alias_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAlias).filter(indAliasList ->
	 * !indAliasList.isEmpty()) .map(indAliasList ->
	 * indAliasList.get(0)).map(IndividualAlias::getNote).orElse("");
	 * System.out.println("individual_alias_note" + individual_alias_note); String
	 * individual_address_street =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getStreet).orElse("");
	 * System.out.println("individual_address_street" + individual_address_street);
	 * String individual_address_state =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getState).orElse("");
	 * System.out.println("individual_address_state" + individual_address_state);
	 * String individual_address_city =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getCity).orElse("");
	 * System.out.println("individual_address_city" + individual_address_city);
	 * String individual_address_state_province =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getStateProvince).orElse("");
	 * System.out.println("individual_address_state_province" +
	 * individual_address_state_province); String individual_address_country =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getCountry).orElse("");
	 * System.out.println("individual_address_country" +
	 * individual_address_country); String individual_address_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualAddress).filter(indAddressList ->
	 * !indAddressList.isEmpty()) .map(indAddressList ->
	 * indAddressList.get(0)).map(IndividualAddress::getNote).orElse("");
	 * System.out.println("individual_address_note" + individual_address_note);
	 * String individual_date_of_birth_type_of_date =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getTypeOfDate).orElse("");
	 * System.out.println("individual_date_of_birth_type_of_date" +
	 * individual_date_of_birth_type_of_date); String individual_date_of_birth_year
	 * = Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getYear).orElse("");
	 * System.out.println("individual_date_of_birth_year" +
	 * individual_date_of_birth_year); String individual_date_of_birth_from_year =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getFromYear).orElse("");
	 * System.out.println("individual_date_of_birth_from_year" +
	 * individual_date_of_birth_from_year); String individual_date_of_birth_to_year
	 * = Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getToYear).orElse("");
	 * System.out.println("individual_date_of_birth_to_year" +
	 * individual_date_of_birth_to_year);// String //
	 * individual_date_of_birth_to_year=ent.getIndividual().get(i).
	 * getIndividualDateOfBirth().get(0).getToYear();
	 * 
	 * Date individual_date_of_birth_date =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDateOfBirth).filter(indDobList ->
	 * !indDobList.isEmpty()) .map(indDobList ->
	 * indDobList.get(0)).map(IndividualDateOfBirth::getDate).orElse(null);
	 * System.out.println("individual_date_of_birth_date" +
	 * individual_date_of_birth_date); String individual_place_of_birth_city =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getCity).orElse("");
	 * System.out.println("individual_place_of_birth_city" +
	 * individual_place_of_birth_city); String
	 * individual_place_of_birth_state_province =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getStateProvince).orElse("");
	 * System.out.println("individual_place_of_birth_state_province" +
	 * individual_place_of_birth_state_province); String
	 * individual_place_of_birth_country =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getCountry).orElse("");
	 * System.out.println("individual_place_of_birth_country" +
	 * individual_place_of_birth_country); String individual_place_of_birth_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualPlaceOfBirth).filter(indPobList ->
	 * !indPobList.isEmpty()) .map(indPobList ->
	 * indPobList.get(0)).map(IndividualPlaceOfBirth::getNote).orElse("");
	 * System.out.println("individual_place_of_birth_note" +
	 * individual_place_of_birth_note); String individual_document_type_of_document1
	 * = Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getTypeOfDocument).orElse("");
	 * System.out.println("individual_document_type_of_document1" +
	 * individual_document_type_of_document1); String
	 * individual_document_type_of_document2 =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getTypeOfDocument2).orElse("");
	 * System.out.println("individual_document_type_of_document2" +
	 * individual_document_type_of_document2); // Date //
	 * individual_document_date_of_issue=ent.getIndividual().get(i).
	 * getIndividualDocument().get(0).getDateOfIssue(); // Date //
	 * individual_document_date_of_issue=ent.getIndividual().get(i).
	 * getIndividualDocument().get(0).getDateOfIssue(); Date
	 * individual_document_date_of_issue =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getDateOfIssue).orElse(null);
	 * System.out.println("individual_document_date_of_issue" +
	 * individual_document_date_of_issue); String individual_document_num =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getNum).orElse("");
	 * System.out.println("individual_document_num" + individual_document_num);
	 * String individual_document_note =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getNote).orElse("");
	 * System.out.println("individual_document_note" + individual_document_note);
	 * String individual_document_issuing_country =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getIssuingCountry).orElse("");
	 * System.out.println("individual_document_issuing_country" +
	 * individual_document_issuing_country); String
	 * individual_document_country_of_issue =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getCountryOfIssue).orElse("");
	 * System.out.println("individual_document_country_of_issue" +
	 * individual_document_country_of_issue); String
	 * individual_document_city_of_issue =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getIndividualDocument).filter(indDocList ->
	 * !indDocList.isEmpty()) .map(indDocList ->
	 * indDocList.get(0)).map(IndividualDocument::getCityOfIssue).orElse("");
	 * System.out.println("individual_document_city_of_issue" +
	 * individual_document_city_of_issue); String sort_key =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getSortKey).orElse(""); System.out.println("sort_key" +
	 * sort_key); String sort_key_last_mod =
	 * Optional.ofNullable(ent).map(Individuals::getIndividual) .filter(indList ->
	 * !indList.isEmpty()).map(indList -> indList.get(iCount))
	 * .map(Individual::getSortKeyLastMod).orElse("");
	 * System.out.println("sort_key_last_mod" + sort_key_last_mod);
	 * 
	 * IndividualTable individual = new IndividualTable(dataid, versionnum,
	 * first_name, second_name, third_name, un_list_type, reference_number,
	 * listed_on, name_original_script, comments1, designation_value, title_value,
	 * nationality_value, list_type_value, last_day_updated_date,
	 * individual_alias_quality, individual_alias_alias_name, individual_alias_note,
	 * individual_address_street, individual_address_state, individual_address_city,
	 * individual_address_state_province, individual_address_country,
	 * individual_address_note, individual_date_of_birth_type_of_date,
	 * individual_date_of_birth_year, individual_date_of_birth_from_year,
	 * individual_date_of_birth_to_year, individual_date_of_birth_date,
	 * individual_place_of_birth_city, individual_place_of_birth_state_province,
	 * individual_place_of_birth_country, individual_place_of_birth_note,
	 * individual_document_type_of_document1, individual_document_type_of_document2,
	 * individual_document_date_of_issue, individual_document_num,
	 * individual_document_note, individual_document_issuing_country,
	 * individual_document_country_of_issue, individual_document_city_of_issue,
	 * sort_key, sort_key_last_mod);
	 * 
	 * // System.out.println("Old Individual Table removed");
	 * theSession.saveOrUpdate(individual); theSession.flush(); theSession.clear();
	 * System.out.println("saved into database"); System.out.println("end"); Status
	 * = "Uploaded Successfully"; // return Status; }
	 * 
	 * return Status; }
	 */

	@RequestMapping(value = "auditList", method = RequestMethod.GET)
	public List<BAML_AUDIT_ENTITY> auditList(@RequestParam String acct_num) {

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return paraservices.getAuditLog(acct_num);

	}

	@RequestMapping(value = "T28Edit", method = RequestMethod.POST)
	@ResponseBody
	public String T28Edit(@ModelAttribute T28Reports t28reports, Model md, HttpServletRequest rq) {
		// System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");
		System.out.println(t28reports);
		String msg = t28ReportServices.editT28(t28reports);

		return msg;

	}

	@RequestMapping(value = "T28Verify", method = RequestMethod.POST)
	@ResponseBody
	public String T28Verify(@ModelAttribute T28Reports t28reports, Model md, HttpServletRequest rq) {
		// System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");
		System.out.println(t28reports);
		String msg = t28ReportServices.verifyT28(t28reports);

		return msg;

	}
	/*
	 * @RequestMapping(value = "T28Verify", method = RequestMethod.POST,consumes =
	 * "application/json")
	 * 
	 * @ResponseBody public String t28Verify(@RequestBody List<T28Report> t28report)
	 * { System.out.println(t28report.get(0).getSrl_no()); String msg =
	 * t28reportServices.verifyT28(t28report);
	 * 
	 * return msg;
	 * 
	 * }
	 */

	@RequestMapping(value = "getCustAudit/{cifid}", method = RequestMethod.GET)
	public List<CMG_MASTER> getCustAudit(@PathVariable("cifid") String cifid, Model md) {

		return customerMasterService.getCustomerByName(cifid);
	}

	@RequestMapping(value = "T8Verify", method = RequestMethod.POST)
	@ResponseBody
	public String t8Verify(@ModelAttribute T8Report t8report, @ModelAttribute T8ReportMod t8ReportMod,
			HttpServletRequest rq) {
		//System.out.println("createUser21321");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t8reportService.verifyT8(t8report, t8ReportMod);

		return msg;

	}
	
	
	@PostMapping(path = "ws/marshallindupload")
	@ResponseBody
	public String UNSC() {

		logger.info("Time to Refresh the UNSC Individual Data");
		String status=unscServices.uploadUNSCInt();
		
		logger.info(" STATUS FOR UNSC "+status);

		
		
		
		return status;

	}
	
	@RequestMapping(value = "getNIDRecord/{nid}", method = RequestMethod.GET)
	public List<Object> NidFetch(@PathVariable("nid") String nid,
			Model md) {

		return thirdPartyServices.getNidFetchDet(nid);
	}
	
	
	
	
	@RequestMapping(value = "getblackList/{nid2}", method = RequestMethod.GET)
	public List<Object> getblackList(@PathVariable("nid2") String nid2,
			Model md) {
		
		
		return thirdPartyServices.getBlackListDet(nid2);
		
		
	}
	
	@RequestMapping(value = "getblackListBCM/{nid2}", method = RequestMethod.GET)
	public int getblackListBCM(@PathVariable("nid2") String nid2,
			Model md) {
		
		
		return cMG_MASTER_REPOSITRY.getblackListBCM(nid2);
		
		
	}
	
	
	@RequestMapping(value = "getblackListByName/{firstname}", method = RequestMethod.GET)
	public List<Object> getblackListByName(@PathVariable("firstname") String firstname,
			Model md) {
		
		return thirdPartyServices.getBlackListDetByName(firstname);
		
	}
	
	
	
	

	
	@RequestMapping(value = "getPOBDesc/{POB}", method = RequestMethod.GET)
	public String getPOBDesc( @PathVariable("POB") String POB,
			Model md) {

		return cMG_MASTER_REPOSITRY.getPOBDesc(POB);
	}
	
	
	
	
	
	@RequestMapping(value = "getPEPCheck/{Name}", method = RequestMethod.GET)
	public int getPEPCheck(@PathVariable("Name") String Name,
			Model md) {

		return thirdPartyRepository.findNamePEPListrest(Name);
	}
	
	
	@RequestMapping(value = "getUNSCListByName/{firstname}", method = RequestMethod.GET)
	public int getUNSCListByName(@PathVariable("firstname") String firstname,
			Model md) {

		return thirdPartyRepository.findNameUNSCListrest(firstname);
	}

	
	
	@RequestMapping(value = "getBlackListFin/{nid2}", method = RequestMethod.GET)
	public int getBlackListFin(@PathVariable("nid2") String nid2,
			Model md) {

		return thirdPartyRepository.findNIDfinrest(nid2);
	}

	
	
	
	
	@RequestMapping(value = "getPEPListFin/{NAME}", method = RequestMethod.GET)
	public int getPEPListFin(@PathVariable("NAME") String NAME,
			Model md) {

		return thirdPartyRepository.findNamefinrest(NAME);
	}
	
	@RequestMapping(value = "getUNSCListFin/{NAME}/{NAME2}", method = RequestMethod.GET)
	public int getUNSCListFin(@PathVariable("NAME") String NAME,@PathVariable("NAME2") String NAME2,
			Model md) {

		return thirdPartyRepository.findNameUNSCfinrest(NAME,NAME2);
	}
	
	/*
	 * @RequestMapping(value = "marshallCRS") // @ResponseBody public String
	 * GenerateXML(@RequestParam("file") MultipartFile file) throws JAXBException,
	 * IOException, JsonProcessingException, IllegalStateException {
	 * 
	 * String Status = ""; Session hs = sessionFactory.getCurrentSession();
	 * List<CRS_TIN> crsTin = new ArrayList<>(); Query qr;
	 * 
	 * qr = hs.createNativeQuery("select * from CRS_TIN_TABLE"); List<Object[]>
	 * results = qr.getResultList();
	 * 
	 * System.out.println(results); CRSOECD ov=new CRSOECD();
	 * 
	 * MessageSpecType msgSpec=new MessageSpecType(); msgSpec.setContact("");
	 * msgSpec.setMessageRefId(""); msgSpec.setSendingCompanyIN("");
	 * 
	 * 
	 * ov.setMessageSpec(msgSpec);
	 * 
	 * CrsBodyType cooc=new CrsBodyType();
	 * 
	 * CorrectableOrganisationPartyType ccd=new CorrectableOrganisationPartyType();
	 * DocSpecType bb=new DocSpecType(); bb.setCorrDocRefId(""); ccd.setDocSpec(bb);
	 * cooc.setReportingFI(ccd); List<CrsBodyType> crsBody=new
	 * ArrayList<CrsBodyType>(); crsBody.add(cooc); ov.setCrsBody(crsBody);
	 * 
	 * JAXBContext jaxbContext; Marshaller jaxbMarshaller; StringWriter sw = null;
	 * try { jaxbContext = JAXBContext.newInstance(CRSOECD.class); jaxbMarshaller =
	 * jaxbContext.createMarshaller();
	 * jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	 * jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	 * jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
	 * //JaxbCharacterEscapeHandler jaxbCharHandler = new
	 * JaxbCharacterEscapeHandler();
	 * //jaxbMarshaller.setProperty("com.sun.xml.bind.characterEscapeHandler",
	 * jaxbCharHandler);
	 * 
	 * ObjectFactory obj = new ObjectFactory(); JAXBElement<CRSOECD> jaxbElement =
	 * new JAXBElement( new QName(CRSOECD.class.getSimpleName()), CRSOECD.class,
	 * ov);
	 * 
	 * //JAXBElement<CRSOECD> jaxbElement = obj.createDocument(ov); sw = new
	 * StringWriter(); jaxbMarshaller.marshal(jaxbElement, sw);
	 * 
	 * 
	 * 
	 * //Write XML to StringWriter // jaxbMarshaller.marshal(ov, sw);
	 * 
	 * //Verify XML Content String xmlContent = sw.toString(); System.out.println(
	 * xmlContent ); } catch (Exception e) { e.printStackTrace(); } try {
	 * AddressFixType crs = new AddressFixType(); // logger.info("Before URL Read");
	 * JAXBContext jaxbContext; Marshaller jaxbMarshaller; StringWriter sw = null;
	 * 
	 * 
	 * 
	 * try {
	 * 
	 * jaxbContext = JAXBContext.newInstance(AddressFixType.class); jaxbMarshaller =
	 * jaxbContext.createMarshaller();
	 * jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	 * jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	 * jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //
	 * JaxbCharacterEscapeHandler jaxbCharHandler = new //
	 * JaxbCharacterEscapeHandler(); //
	 * jaxbMarshaller.setProperty("com.sun.xml.bind.characterEscapeHandler", //
	 * jaxbCharHandler);
	 * 
	 * ObjectFactory obj = new ObjectFactory(); JAXBElement<AddressFixType>
	 * jaxbElement = obj.createAddressTypeAddressFix(crs);
	 * //jaxbMarshaller.marshal(jaxbElement, new
	 * FileOutputStream(“C:\Users\kalid\Desktop\outputxml")); sw = new
	 * StringWriter(); jaxbMarshaller.marshal(jaxbElement, sw); } catch (Exception
	 * e) { e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); System.out.println(ex); Status
	 * = "error Occured, Please Contact Administrator" + ex; } return Status; }
	 */

}
