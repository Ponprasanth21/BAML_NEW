package com.bornfire.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.Services.AMLAccessRoleService;
import com.bornfire.Services.AMLKYCParameterServices;
import com.bornfire.Services.AlertManagementServices;
import com.bornfire.Services.Aml_Scr_Parm_Cust_Type_Service;
import com.bornfire.Services.Aml_Scr_Parm_Schm_Type_Service;
import com.bornfire.Services.BAMLCustomerChecksService;
import com.bornfire.Services.BAMLTranAlertsMasterService;
import com.bornfire.Services.BAML_SCR_Alert_Operations_Service;
import com.bornfire.Services.BAML_STR_SERVICE;
import com.bornfire.Services.BankandBranchServices;
import com.bornfire.Services.BatchJobServices;
import com.bornfire.Services.BlacklistServices;
import com.bornfire.Services.Case_Management_Services;
import com.bornfire.Services.Cust_Aband_Fund_List_Service;
import com.bornfire.Services.Cust_Black_List_Corp_Service;
import com.bornfire.Services.Cust_Black_List_Ind_Service;
import com.bornfire.Services.Cust_Hnwi_List_Service;
import com.bornfire.Services.Cust_Pep_List_Service;
import com.bornfire.Services.Cust_White_ListService;
import com.bornfire.Services.CustomerDao;
import com.bornfire.Services.CustomerMasterService;
import com.bornfire.Services.EMAIL;
import com.bornfire.Services.InputReportRejectServices;
import com.bornfire.Services.KycServices;
import com.bornfire.Services.LoginServices;
import com.bornfire.Services.MonitorParaService;
import com.bornfire.Services.NegativeListService;
import com.bornfire.Services.RBSDataMaintenanceServices;
import com.bornfire.Services.RefCodeService;
import com.bornfire.Services.ReferenceCodeConfigure;
import com.bornfire.Services.T10ReportService;
import com.bornfire.Services.T12ReportService;
import com.bornfire.Services.T14ReportService;
import com.bornfire.Services.T15ReportService;
import com.bornfire.Services.T18ReportService;
import com.bornfire.Services.T1CurrentReportService;
import com.bornfire.Services.T2CurrentReportServices;
import com.bornfire.Services.T3AReportService;
import com.bornfire.Services.T5ReportService;
import com.bornfire.Services.T8ReportService;
import com.bornfire.Services.T9ReportServices;
import com.bornfire.Services.ThirdPartyServices;
import com.bornfire.Services.TransMonitoringServices;
import com.bornfire.Services.TransactionMasterServices;
import com.bornfire.Services.UNSCServices;
import com.bornfire.Services.UserProfileDao;
import com.bornfire.Services.UserProfileModService;
import com.bornfire.entity.*;
import com.bornfire.entity.riskcust.RISKREVIEWDETAILREP;
import com.bornfire.entity.riskcust.RISKREVIEWREP;
import com.bornfire.entity.t1.T1CurProdDetail;
import com.bornfire.entity.t1.T1CurProdDetailRepo;
import com.bornfire.entity.t1.T1CurProdServicesRepo;
import com.bornfire.entity.t1.T1DataMaintenance;
import com.bornfire.entity.t10.T10DataMaintenance;
import com.bornfire.entity.t10.T10DataMaintenanceRep;
import com.bornfire.entity.t10.T10Detail;
import com.bornfire.entity.t10.T10DetailRepo;
import com.bornfire.entity.t11.T11DetailRep;
import com.bornfire.entity.t11.T11Details;
import com.bornfire.entity.t11.T11ReportsRep;
import com.bornfire.entity.t12.T12DataMaintenance;
import com.bornfire.entity.t12.T12DataMaintenanceRep;
import com.bornfire.entity.t12.T12Detail;
import com.bornfire.entity.t12.T12DetailRepo;
import com.bornfire.entity.t13.T13DetailRepo;
import com.bornfire.entity.t14.T14DataMaintenanceRep;
import com.bornfire.entity.t14.T14Detail;
import com.bornfire.entity.t14.T14DetailMaintenance;
import com.bornfire.entity.t14.T14DetailRepo;
import com.bornfire.entity.t15.T15DataMaintenance;
import com.bornfire.entity.t15.T15DataMaintenanceRep;
import com.bornfire.entity.t15.T15Detail;
import com.bornfire.entity.t15.T15DetailRepo;
import com.bornfire.entity.t18.T18DataMaintenance;
import com.bornfire.entity.t18.T18DataMaintenanceRep;
import com.bornfire.entity.t18.T18Detail;
import com.bornfire.entity.t18.T18DetailRepo;
import com.bornfire.entity.t21.T21DetailRepo;
import com.bornfire.entity.t27.T27CDetRepo;
import com.bornfire.entity.t3a.T3ADataMaintenance;
import com.bornfire.entity.t3a.T3ADataMaintenanceRep;
import com.bornfire.entity.t3a.T3ADetailRepo;
import com.bornfire.entity.t4.T4DetailRepo;
import com.bornfire.entity.t5.T5Detail;
import com.bornfire.entity.t5.T5DetailRepo;
import com.bornfire.entity.t8.T8DataMaintenance;
import com.bornfire.entity.t8.T8DataMaintenanceRep;
import com.bornfire.entity.t8.T8Detail;
import com.bornfire.entity.t8.T8DetailRepo;
import com.bornfire.entity.t9.T9DataMaintenance;
import com.bornfire.entity.t9.T9DataMaintenanceRep;
import com.bornfire.entity.t9.T9Detail;
import com.bornfire.entity.t9.T9DetailRepo;

//import com.bornfire.entity.t8.T8Repositry;

import net.sf.jasperreports.engine.JRException;

@Controller
@ConfigurationProperties("default")

@Transactional
public class NavigationController {

	@Autowired
	Cust_White_ListService whiteListservices;

	@Autowired
	private Cust_White_List_Repository whiteListRepository;

	@Autowired
	UserProfileDao userProfileDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	private CMGrepository CMGrepository;

	@Autowired
	private TransRepository transRepository;

	@Autowired
	private BlackListRepository blackListRepository;

	@Autowired
	private NegativeListRepository negativeListRepository;

	@Autowired
	EMAIL email;

	@Autowired
	private Cust_Black_List_Ind_Repository cust_black_List_Ind_Repository;

	@Autowired
	private Cust_Black_List_Corp_Repository cust_black_List_Corp_Repository;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private Cust_Pep_List_Repository cust_pep_list_Repository;

	@Autowired
	private Cust_Aband_Fund_List_Repository cust_abond_fund_list_Repository;


	@Autowired
	private Cust_Hnwi_List_Repository cust_hnwi_list_Repository;

	@Autowired
	CustomerMasterService custmasterservices;

	@Autowired
	AMLAccessRoleService AccessRoleService;

	@Autowired
	private AccessandRolesRepository accessandrolesrepository;

	@Autowired
	LoginServices loginServices;

	@Autowired
	UserProfileModService userProfileModSer;

	@Autowired
	TransMonitoringServices transMonitoringServices;

	@Autowired
	TransactionMasterServices transactionmasterServices;

	@Autowired
	private TransactionMasterRepository transactionMasterRepository;

	@Autowired
	private RuleEngineRepository ruleenginerepository;

	@Autowired
	private TransMonitoringRepository transMonitoringRepository;

	@Autowired
	BlacklistServices blacklistservices;

	@Autowired
	private MONITOREP montRepository;

	@Autowired
	private AlertManagementRepository alertmanagementrepository;

	@Autowired
	ReferenceCodeConfigure referenceCodeConfigure;

	@Autowired
	AlertManagementServices alertservices;

	@Autowired
	private RecordTypeRepository recordTypeRepository;

	@Autowired
	private MontParameterRepository montParameterRepository;

	@Autowired
	MonitorParaService paraservices;

	@Autowired
	NegativeListService negativeListservices;

	@Autowired
	Cust_Pep_List_Service pepListservices;

	@Autowired
	Cust_Aband_Fund_List_Service abond_fund_Listservices;

	@Autowired
	Cust_Hnwi_List_Service hnwiListservices;

	@Autowired
	Cust_Black_List_Ind_Service cust_black_list_Ind_Services;

	@Autowired
	Aml_Scr_Parm_Cust_Type_Repository aml_scr_parm_cust_type_repository;

	@Autowired
	Aml_Scr_Parm_Cust_Type_Service aml_scr_parm_cust_type_Services;

	@Autowired
	Aml_Scr_Parm_Schm_Type_Repository aml_scr_parm_Schm_type_repository;

	@Autowired
	Aml_Scr_Parm_Schm_Type_Service aml_scr_parm_Schm_type_Services;

	@Autowired
	Cust_Black_List_Corp_Service cust_black_list_Corp_Services;

	@Autowired
	private FinUserProfileRep finuserrep;

	@Autowired
	private RefCodeRepository refCodeRepository;

	@Autowired
	private CMG_MASTER_REPOSITRY cmgMaster;

	@Autowired
	private BAML_Daily_Loan_Blacklist_RT_Repository baml_daily_loan_blacklist_rt_repository;

	@Autowired
	private BAML_Daily_Cash_Blacklist_RT_Repository baml_daily_cash_blacklist_rt_repository;

	@Autowired
	private BAML_Cust_Blacklist_RPT_Repository baml_cust_blacklist_rpt_repository;

	@Autowired
	private BAML_Cust_HNWI_RPT_Repository baml_cust_hnwi_rpt_repository;

	@Autowired
	private BAML_Cust_PEP_RPT_Repository baml_cust_pep_rpt_repository;

	@Autowired
	private ACCT_MASTER_REPOSITRY ACCTMaster;

	@Autowired
	private TRAN_MASTER_REPOSITRY TRANMaster;

	@Autowired
	RefCodeService refCodeService;

	@Autowired
	AMLIndividualRepository amlIndividualRepository;

	@Autowired
	private KYCRep kycRep;

	@Autowired
	KycServices kycServices;

	@Autowired
	BlacklistServices blacklistservice;

	@Autowired
	BAML_STR_SERVICE baml_STR_SERVICE;

	@Autowired
	STR_REPOSITRY str_rep;

	@Autowired
	BAMLTranAlertsMasterRepository bamlTranAlertsMasterRepository;

	@Autowired
	BAMLTranAlertsMasterService bamlTranAlertsMasterService;

	@Autowired
	BAML_SCR_Alert_Operations_Service bamlAlert_OperationsService;

	@Autowired
	UNSCServices unscServices;

	@Autowired
	EntityTableRepository entityTableRepository;

	@Autowired
	BAML_AUDIT_REPOSITRY auditRep;

	@Autowired
	AML_KYC_Parameter_Repository kycParameterrep;

	@Autowired
	AMLKYCParameterServices kycParameterServices;

	@Autowired
	AML_AUDIT_LOCAL_REP audit_local;

	@Autowired
	BAML_Kyc_Rep baml_kyc_rep;

	@Autowired
	BAML_Doc_Hist_Rep baml_doc_hist_rep;

	@Autowired
	BAML_Kyc_His_Rep baml_kyc_his_rep;

	@Autowired
	BAML_Risk_History_Rep baml_risk_hist_rep;

	@Autowired
	BAML_STR_Internal_Rep bAML_STR_Internal;

	@Autowired
	AML_Case_List_Rep aml_Case_List_Rep;

	@Autowired
	Case_Management_Services CMgmt;

	@Autowired
	BAML_Case_Sheet_Rep baml_case_Sheet_Rep;

	@Autowired
	BAML_Case_Docs_Rep baml_Case_Docs_Rep;

	@Autowired
	BAML_SCR_Alert_Oper_Repository baml_SCR_Alert_Oper_Repository;

	@Autowired
	BAML_Risk_Category_RPT_Repository baml_Risk_Category_RPT_Repository;

	@Autowired
	BAMLCustomerChecksRepo bamlCustomerChecksRepo;

	@Autowired
	BAMLCustomerChecksService bamlCustomerChecksService;

	@Autowired
	RBSReportRepo rbsReportlist;

	@Autowired
	T4ReportsRep t4ReportsRep;

	@Autowired
	T4AccReportsRep t4AccReportsRep;

	@Autowired
	InputReportRejectServices inputReportRejectServices;

	@Autowired
	T11DetailRep t11DetailRep;

	@Autowired
	T11ReportsRep t11ReportsRep;

	@Autowired
	BAMLSolRepository bamlSolRepository;

	@Autowired
	BankandBranchServices bankandBranchServices;

	@Autowired
	BAMLBatchJobSchedularRepository bamlBatchJobSchedular;

	@Autowired
	BatchJobServices batchJobServices;

	@Autowired
	AMLUserAlertReposirtory amlUserAlertReposirtory;

	@Autowired
	RISKREVIEWREP rISKREVIEWREP;

	@Autowired
	RISKREVIEWDETAILREP rISKREVIEWDETAILREP;

	@Autowired
	T1CurProdServicesRepo t1CurProdServicesRepo;

	@Autowired
	ETLMonitorRep etlMonitorrep;

	@Autowired
	ETLErrorRep rTLErrorRep;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	@Autowired
	ThirdPartyServices thirdPartyServices;

	@Autowired
	com.bornfire.Services.RBSValidationservices rbsValidationservices;

	@Autowired
	ReportValidationsRepo reportValidationsRepo;

	@Autowired
	T27CDetRepo t27CDetRepo;

	@Autowired
	T3ADetailRepo t3ADetailRepo;

	@Autowired
	T4DetailRepo t4DetailRepo;

	@Autowired
	T5DetailRepo t5DetailRepo;

	@Autowired
	T8DetailRepo t8DetailRepo;

	@Autowired
	T9DetailRepo t9DetailRepo;

	@Autowired
	T10DetailRepo t10DetailRepo;

	@Autowired
	T12DetailRepo t12DetailRepo;

	@Autowired
	T13DetailRepo t13DetailRepo;

	@Autowired
	T14DetailRepo t14DetailRepo;

	@Autowired
	T15DetailRepo t15DetailRepo;

	@Autowired
	T18DetailRepo t18DetailRepo;

	@Autowired
	T21DetailRepo t21DetailRepo;

	@Autowired
	RPT_View_Repo rpt_View_Repo;

	@Autowired
	T1CurrentReportService t1CurrentReportService;

	@Autowired
	T3AReportService t3AReportService;

	@Autowired
	T8ReportService t8ReportService;

	@Autowired
	T9ReportServices t9ReportService;

	@Autowired
	T10ReportService t10ReportService;

	@Autowired
	T12ReportService t12ReportService;

	@Autowired
	T14ReportService t14ReportService;

	@Autowired
	T15ReportService t15ReportService;

	@Autowired
	T18ReportService t18ReportService;

	@Autowired
	RBSDataMaintenanceServices rbsDataMaintenanceServices;

	@Autowired
	T1CurProdDetailRepo t1CurProdDetailRepo;

	@Autowired
	T2CurrentReportServices t2CurrentReportServices;

	@Autowired
	T2CurrentDetailRepo t2CurrentDetailRepo;

	@Autowired
	T5ReportService t5ReportService;

	@Autowired
	BAML_AUDITTRAIL_REP bAML_AUDITTRAIL_REP;

	@Autowired
	T12DataMaintenanceRep t12DataMaintenanceRep;

	@Autowired
	T9DataMaintenanceRep t9DataMaintenanceRep;

	@Autowired
	T8DataMaintenanceRep t8DataMaintenanceRep;

	@Autowired
	T10DataMaintenanceRep t10DataMaintenanceRep;

	@Autowired
	T14DataMaintenanceRep t14DataMaintenanceRep;

	@Autowired
	T15DataMaintenanceRep t15DataMaintenanceRep;

	@Autowired
	T18DataMaintenanceRep t18DataMaintenanceRep;

	@Autowired
	T3ADataMaintenanceRep t3ADataMaintenanceRep;

	@Autowired
	CrsListRepository crsListRepository;

	private String pagesize;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	@RequestMapping("/logout")
	public String index8() {
		return "AMLStart.html";
	}

	private static final Logger logger = LoggerFactory.getLogger(NavigationController.class);

	/*************************************
	 * Dashboard Starts
	 ****************************************/
	@RequestMapping(value = "Dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public String dashboard(@RequestParam(required = false) String CustId,
			@RequestParam(required = false) String CustCount, @RequestParam(required = false) String AcctCount,
			@RequestParam(required = false) String TranCount, Model md, HttpServletRequest req) {

		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		String userid = (String) req.getSession().getAttribute("USERID");
		String roleId = (String) req.getSession().getAttribute("ROLEID");

		md.addAttribute("alert", amlUserAlertReposirtory.getUsersubList());
		md.addAttribute("alert11", amlUserAlertReposirtory.getUsersubIList());
		md.addAttribute("alertCount", amlUserAlertReposirtory.getAlertCount());
		md.addAttribute("alertCount1", amlUserAlertReposirtory.getAlertCount1());
		md.addAttribute("changepassword", userProfileDao.checkPasswordChangeReq(userid));
		md.addAttribute("checkpassExpiry", userProfileDao.checkpassexpirty(userid));
		md.addAttribute("checkAcctExpiry", userProfileDao.checkAcctexpirty(userid));
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("CustCount", cmgMaster.findCustomcount());
		md.addAttribute("AcctCount", cmgMaster.findAcccount());
		md.addAttribute("TranCount", cmgMaster.findTranCount());

		int completed = 0;
		int uncompleted = 0;

		/* List<ReportTitle> ls = reportServices.getDashBoardRepList(domainid); */

		// md.addAttribute("reportList", ls);
		md.addAttribute("completed", completed);
		md.addAttribute("uncompleted", uncompleted);
		md.addAttribute("menu", "Dashboard");

		return "AMLDashboard";
	}

	/*************************************
	 * Dashboard ends
	 ****************************************/

	/*************************************
	 * Dashboard Starts
	 * 
	 * @throws ParseException
	 ****************************************/
	@RequestMapping(value = "userAlert", method = { RequestMethod.GET, RequestMethod.POST })
	public String userAlert(@RequestParam(value = "alertDate", required = false) String date,
			@RequestParam(value = "alertDate1", required = false) String date1,
			@RequestParam(required = false) String CustCount, @RequestParam(required = false) String AcctCount,
			@RequestParam(required = false) String TranCount, Model md, HttpServletRequest req) throws ParseException {

		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		String userid = (String) req.getSession().getAttribute("USERID");
		String roleId = (String) req.getSession().getAttribute("ROLEID");

		String alertDate = "";
		String alertDate1 = "";
		if (date == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
			alertDate1 = dateFormat.format(new Date());
			alertDate = dateFormat1.format(new Date());

		} else {
			alertDate = date;

		}

		md.addAttribute("alert1", alertservices.getAlertAList(date));
		md.addAttribute("alert2", alertservices.getAlertIList(date));
		md.addAttribute("alert", amlUserAlertReposirtory.getUsersubList());
		md.addAttribute("alert11", amlUserAlertReposirtory.getUsersubIList());
		md.addAttribute("alertCount", amlUserAlertReposirtory.getAlertCount());
		md.addAttribute("alertCount1", amlUserAlertReposirtory.getAlertCount1());
		md.addAttribute("alertDate", alertDate1);
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("alert", amlUserAlertReposirtory.getUsersubList());
		md.addAttribute("alertCount", amlUserAlertReposirtory.getAlertCount());
		md.addAttribute("tranMonitorType", "alertDate");
		md.addAttribute("menu", "userAlert");

		return "AMLUserAlertScreen";
	}

	/*************************************
	 * Dashboard ends
	 ****************************************/

	/*************************************
	 * Admin ---> UserProfile ---> User creation starts
	 ****************************************/
	@RequestMapping(value = "UserProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String userprofile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
		// loginuserid, req.getRemoteAddr(),
		// "ACTIVE");
		md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());
		md.addAttribute("menu", "UserProfile"); // To highlight the menu
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("userProfiles", userProfileDao.getUsersList());
			// md.addAttribute("userProfiles", userProfileModSer.getUsersList());
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfile", userProfileDao.getUser(userid));
			md.addAttribute("userProfilecheck", userProfileDao.getUser(userid));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfile", userProfileModSer.getUser(userid));
			md.addAttribute("userProfilecheck", userProfileModSer.getUser(userid));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfile", userProfileDao.getUser(userid));
			md.addAttribute("userProfilecheck", userProfileDao.getUser(userid));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else if (formmode.equals("cancel")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfile", userProfileModSer.getUser(userid));
			md.addAttribute("userProfilecheck", userProfileModSer.getUser(userid));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfile", userProfileDao.getUser(userid));
			md.addAttribute("userProfilecheck", userProfileDao.getUser(userid));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else if (formmode.equals("viewnew")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfile", userProfileModSer.getUser(userid));
			md.addAttribute("userProfilecheck", userProfileModSer.getUser(userid));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else if (formmode.equals("search")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfiles", userProfileDao.getUsersListsearch(userid));
			//md.addAttribute("userProfile", userProfileModSer.getUser(userid));
			md.addAttribute("userProfilecheck", userProfileModSer.getUser(userid));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());

		} else {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", reportServices.getDomainList());
			md.addAttribute("FinUserProfiles", finuserrep.getfin_user_details());
			md.addAttribute("userProfile", userProfileDao.getUser(""));
			md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());
		}
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return "AMLUserprofile";
	}

	@RequestMapping(value = "Finuserdata", method = RequestMethod.GET)
	public ModelAndView Finuserdata(@RequestParam String userid) {
		ModelAndView mv = new ModelAndView("AMLUserprofile :: finuserapply");
		mv.addObject("formmode", "add");
		// mv.addObject("userProfile", userProfileDao.getFinUser(userid));
		mv.addObject("RuleIDType", accessandrolesrepository.roleidtype());
		return mv;

	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	@ResponseBody
	public String createuser(@RequestParam("formmode") String formmode, @RequestParam("user") String user,
			@ModelAttribute UserProfileModEn userform, @ModelAttribute UserProfile userprofile, Model md,
			HttpServletRequest rq) throws ParseException {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = userProfileModSer.addUser(userform, formmode, userid, user);
		md.addAttribute("flagchange", userProfileDao.addUserentity(userprofile, userform, formmode, userid, user));
		md.addAttribute("menu", "UserProfile"); // To highlight the menu
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return msg;

	}

	@RequestMapping(value = "DeleteUser", method = RequestMethod.POST)
	@ResponseBody
	public String DeleteUser(@RequestParam("formmode") String formmode, @ModelAttribute UserProfileModEn userform,
			@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		// String msg = "";

		String msg = userProfileDao.DeleteUser(userprofile, userid);

		md.addAttribute("menu", "UserProfile"); // To highlight the menu
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return msg;

	}

	@RequestMapping(value = "verifyUser", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUser(@ModelAttribute UserProfile userprofile, UserProfileModEn userProfilemoden, Model md,
			HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = userProfileDao.verifyUser(userProfilemoden, userid);
		md.addAttribute("modtable", userProfileModSer.deleteUser(userProfilemoden, userid));
		md.addAttribute("menu", "UserProfile - VERIFY"); // To highlight the menu
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return msg;

	}

	@RequestMapping(value = "CancelUser", method = RequestMethod.POST)
	@ResponseBody
	public String cancel(@ModelAttribute UserProfile userprofile, @RequestParam("userid") String userid,
			UserProfileModEn userProfilemoden, Model md, HttpServletRequest rq) {
		// String userid = (String) rq.getSession().getAttribute("USERID");

		md.addAttribute("flagchange", userProfileDao.cancelUserentity(userprofile, userid));
		String msg = userProfileModSer.cancel(userProfilemoden, userprofile, userid);
		md.addAttribute("menu", "UserProfile - CANCEL"); // To highlight the menu
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return msg;

	}

	@RequestMapping(value = "passwordReset", method = RequestMethod.POST)
	@ResponseBody
	public String passwordReset(@RequestParam(value = "userid", required = false) String userid,
			@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String Userid = (String) rq.getSession().getAttribute("USERID");
		String msg = userProfileDao.passwordReset(userprofile, userid, Userid);
		md.addAttribute("menu", "UserProfile"); // To highlight the menu
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return msg;

	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
			Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = userProfileDao.changePassword(oldpass, newpass, userid);

		return msg;

	}

	/*************************************
	 * Admin ---> UserProfile ---> User creation ends
	 ****************************************/
	/*************************************
	 * Admin ---> UserProfile ---> Access and Roles Starts
	 ****************************************/

	@RequestMapping(value = "AMLAccessandRoles", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAccessandRoles(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLAccessandRoles");
			md.addAttribute("menuname", "Access and Roles");
			md.addAttribute("formmode", "list");
			md.addAttribute("AccessandRoles", accessandrolesrepository.rulelist(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("add")) {
			md.addAttribute("menuname", "Access and Roles - ADD");
			md.addAttribute("AMLAccessRole", new AMLAccessRole());
			md.addAttribute("formmode", formmode);
		} else if (formmode.equals("edit")) {
			md.addAttribute("menuname", "Access and Roles - EDIT");
			md.addAttribute("formmode", formmode);
			md.addAttribute("AMLAccessRole", AccessRoleService.getRoleId(userid));

		} else if (formmode.equals("view")) {
			md.addAttribute("menuname", "Access and Roles - INQUIRY");
			md.addAttribute("formmode", formmode);
			md.addAttribute("AMLAccessRole", AccessRoleService.getRoleId(userid));

		} else if (formmode.equals("verify")) {
			md.addAttribute("menuname", "Access and Roles - VERIFY");
			md.addAttribute("formmode", formmode);
			md.addAttribute("AMLAccessRole", AccessRoleService.getRoleId(userid));

		} else if (formmode.equals("delete")) {

			/* String delete_msg = AccessRoleService.deleteRole(userid); */
			md.addAttribute("deletemsg", AccessRoleService.deleteRole(userid));
			md.addAttribute("formmode", "list");
			md.addAttribute("AccessandRoles", accessandrolesrepository.rulelist(PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return "AMLAccessandRoles";
	}

	@RequestMapping(value = "createAccessRole", method = RequestMethod.POST)
	@ResponseBody
	public String createAccessRoleEn(@RequestParam("formmode") String formmode,
			@RequestParam(value = "adminValue", required = false) String adminValue,
			@RequestParam(value = "inquiryValue", required = false) String inquiryValue,
			@RequestParam(value = "monitoringValue", required = false) String monitoringValue,
			@RequestParam(value = "listValue", required = false) String listValue,
			@RequestParam(value = "interfaceValue", required = false) String interfaceValue,
			@RequestParam(value = "screenValue", required = false) String screenValue,
			@RequestParam(value = "riskValue", required = false) String riskValue,
			@RequestParam(value = "caseValue", required = false) String caseValue,
			@RequestParam(value = "thirdpartyValue", required = false) String thirdpartyValue,
			@RequestParam(value = "amlReportValue", required = false) String amlReportValue,
			@RequestParam(value = "archivalValue", required = false) String archivalValue,
			@RequestParam(value = "strreportValue", required = false) String strreportValue,
			@RequestParam(value = "AuditlogValue", required = false) String AuditlogValue,
			@RequestParam(value = "reportValue", required = false) String reportValue,
			@RequestParam(value = "finalString", required = false) String finalString,

			@ModelAttribute AMLAccessRole alertparam, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
System.out.println("AuditlogValue "+AuditlogValue);
		String msg = AccessRoleService.addPARAMETER(alertparam, formmode, adminValue, inquiryValue, monitoringValue,
				listValue, interfaceValue, screenValue, riskValue, reportValue, caseValue, thirdpartyValue,
				amlReportValue, archivalValue, strreportValue, AuditlogValue, finalString, userid);

		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "monitoringparameter");

		return msg;

	}

	@RequestMapping(value = "deleteAccessRole", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAccessRoleEn(@RequestParam(value = "userid", required = false) String userid, Model md,
			HttpServletRequest rq) {

		String msg = AccessRoleService.deleteRole(userid);

		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "monitoringparameter");

		return msg;

	}

	/*************************************
	 * Admin ---> UserProfile ---> Access and Roles ends
	 ****************************************/

	/*************************************
	 * Admin ---> AlertManagement ---> AlertParameters Starts
	 ****************************************/
	@RequestMapping(value = "AMLAlertParameters", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAlertManagement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menu", "AMLAlertParameters");
			md.addAttribute("menuname", "Alert Parameters");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			/*
			 * md.addAttribute("RuleLists",
			 * ruleenginerepository.findAll(PageRequest.of(currentPage, pageSize)));
			 */
			md.addAttribute("AlertParameterList",
					alertmanagementrepository.alertlist(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("AlertParameter", alertservices.getSrlNo(srlno));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("AlertParameter", alertservices.getSrlNo(srlno));
			
		}else if(formmode.equals("exec")){
			md.addAttribute("menu", "AMLAlertParameters");
			md.addAttribute("menuname", "Alert Parameters");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("AlertParameterList",alertmanagementrepository.alertlist(PageRequest.of(currentPage, pageSize)));
			try {
				abond_fund_Listservices.executeListManagement();
			} catch (JRException | SQLException | IOException e) {
				e.printStackTrace();
				return "AMLAlertParameters";
			}
		} else if (formmode.equals("delete")) {
		
			// int status=ruleenginerepository.findByfgdg1(srlno);
			// String msg = alertservices.deletealert(srlno);
			md.addAttribute("AlertParameter", alertservices.getSrlNo(srlno));
			// md.addAttribute("RuleLists", ruleenginerepository.findById1(srlno));
			/* String msg = ruleenginerepository.findById1(srlno); */
			md.addAttribute("formmode", "delete"); // to set which form - valid values are "edit" , "add" & "list"
			// md.addAttribute("AlertParameterList",
			// alertmanagementrepository.alertlist(PageRequest.of(currentPage, pageSize)));

		}
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("parameterflag", "parameterflag");

		return "AMLAlertParameters";
	}

	@RequestMapping(value = "createAlert", method = RequestMethod.POST)
	@ResponseBody
	public String createRule(@RequestParam("formmode") String formmode,
			@ModelAttribute AlertManagementEntity alertparam, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = alertservices.addAlert(alertparam, formmode, userid);

		return msg;

	}

	/*************************************
	 * Admin ---> AlertManagement---> AlertParameters ends
	 ****************************************/

	/*************************************
	 * Admin ---> RuleEngine --->RuleEngine Starts
	 ****************************************/

	@RequestMapping(value = "AMLRuleEngine2", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLRuleEngine2(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menu", "AMLRuleEngine2");
			md.addAttribute("menuname", "Rule Engine");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			/*
			 * md.addAttribute("RuleLists",
			 * ruleenginerepository.findAll(PageRequest.of(currentPage, pageSize)));
			 */
			md.addAttribute("RuleLists", ruleenginerepository.rulelist(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("add")) {
			md.addAttribute("RULESrlNo", loginServices.getSrlNoValue());
			md.addAttribute("formmode", formmode);
		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("RuleEngine", loginServices.getSrlNo(srlno));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("RuleEngine", loginServices.getSrlNo(srlno));
		} else if (formmode.equals("delete")) {
			// int status=ruleenginerepository.findByfgdg1(srlno);
			String msg = loginServices.deleterule(srlno);

			// md.addAttribute("RuleLists", ruleenginerepository.findById1(srlno));
			/* String msg = ruleenginerepository.findById1(srlno); */
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("RuleLists", ruleenginerepository.rulelist(PageRequest.of(currentPage, pageSize)));

		}
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("ruleflag", "alertflag");

		return "AMLRuleEngine2";
	}

	@RequestMapping(value = "createRule", method = RequestMethod.POST)
	@ResponseBody
	public String createRule(@RequestParam("formmode") String formmode, @ModelAttribute RuleEngineEntity ruleengine,
			Model md, HttpServletRequest rq) {
		/* String userid = (String) rq.getSession().getAttribute("USERID"); */
		String srlno = "1";
		String msg = loginServices.addRule(ruleengine, formmode, srlno);
		md.addAttribute("menu", "createRule");
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("ruleflag", "alertflag");

		return msg;

	}

	/*************************************
	 * Admin ---> RuleEngine --->RuleEngine ends
	 ****************************************/

	/*************************************
	 * Admin ---> REFERENCE CODE MASTER starts
	 ****************************************/

	@RequestMapping(value = "ReferenceCodeMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String ReferenceCodeMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String refcode, @RequestParam(required = false) String reportcode, @RequestParam(required = false) String master,
			@RequestParam(required = false) String recordtype, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "REFERENCE CODE MASTER - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			List<RecordTypeEntity> custMaster_List = new ArrayList<>();

			List<Object[]> lst_Objects = recordTypeRepository.findAllCustom();

			for (Object[] obj : lst_Objects) {
				RecordTypeEntity info = new RecordTypeEntity();
				info.setRef_rec_type(String.valueOf(obj[0]));
				info.setRec_desc(String.valueOf(obj[1]));

				custMaster_List.add(info);
			}

			md.addAttribute("RECORDTYPELIST", custMaster_List);
			md.addAttribute("RefCodemasterList", refCodeRepository.refcodelist(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("search")) {

			md.addAttribute("menuname", "REFERENCE CODE MASTER - List ");
			md.addAttribute("formmode", "search"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("RefCodemasterList",
					refCodeRepository.codelistsearch('%' + recordtype + '%'));
			md.addAttribute("master", "master");
		}
		else if (formmode.equals("RecTypesearch")) {

			md.addAttribute("menuname", "REFERENCE CODE MASTER - List ");
			md.addAttribute("formmode", "search"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("RefCodemasterList",
					refCodeRepository.rectypelistsearch('%' + recordtype + '%'));
			md.addAttribute("master", "master");
		}
		else if (formmode.equals("RecDescsearch")) {

			md.addAttribute("menuname", "REFERENCE CODE MASTER - List ");
			md.addAttribute("formmode", "search"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("master", "master"); 
			md.addAttribute("RefCodemasterList",
					refCodeRepository.recdesclistsearch('%' + recordtype + '%'));
			
			 

		}
		else if (formmode.equals("RefCodesearch")) {

			md.addAttribute("menuname", "REFERENCE CODE MASTER - List ");
			md.addAttribute("formmode", "search"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("RefCodemasterList",
					refCodeRepository.refcodelistsearch('%' + recordtype + '%'));
			md.addAttribute("master", "master");
		}else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);

			List<RecordTypeEntity> custMaster_List = new ArrayList<>();

			List<Object[]> lst_Objects = recordTypeRepository.findAllCustom();

			for (Object[] obj : lst_Objects) {
				RecordTypeEntity info = new RecordTypeEntity();
				info.setRef_rec_type(String.valueOf(obj[0]));
				info.setRec_desc(String.valueOf(obj[1]));

				custMaster_List.add(info);
			}

			md.addAttribute("RECORDTYPELIST", custMaster_List);
			req.getSession().getAttribute("USERID");
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Add");
			md.addAttribute("RefCodeMaster1", new RefcodeEntity());
			md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());

		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", formmode);
			List<RecordTypeEntity> custMaster_List = new ArrayList<>();

			List<Object[]> lst_Objects = recordTypeRepository.findAllCustom();

			for (Object[] obj : lst_Objects) {
				RecordTypeEntity info = new RecordTypeEntity();
				info.setRef_rec_type(String.valueOf(obj[0]));
				info.setRec_desc(String.valueOf(obj[1]));

				custMaster_List.add(info);
			}

			md.addAttribute("RECORDTYPELIST", custMaster_List);
			/* md.addAttribute("userProfile", userProfileDao.getUser(userid)); */
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Modify");
			md.addAttribute("RefCodeMaster1", new RefcodeEntity());
			md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());
			md.addAttribute("RefCodeMaster1", refCodeService.getRefcode(refcode, recordtype));

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("RefCodeMaster1", new RefcodeEntity());
			md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());
			md.addAttribute("RefCodeMaster1", refCodeService.getRefcode(refcode, recordtype));
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Verify");

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("RefCodeMaster1", new RefcodeEntity());
			md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Inquiry");
			md.addAttribute("RefCodeMaster1", refCodeService.getRefcode(refcode, recordtype));

		} else if (formmode.equals("delete")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Delete");
			String msg = refCodeService.deleteParameter(refcode, recordtype, reportcode);
			md.addAttribute("RefCodemasterList", refCodeRepository.refcodelist(PageRequest.of(currentPage, pageSize)));
			/* md.addAttribute("RefCodeMaster", refCodeService.getRefcode(refcode)); */

		}
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "ReferenceCodeMaster");

		return "AMLReferenceCodeMaster";
	}

	@RequestMapping(value = "createReferenceCodeMaster", method = RequestMethod.POST)
	@ResponseBody
	public String createCodeMaster(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String refcode, @RequestParam(required = false) String reportcode,
			@RequestParam(required = false) String recordtype, @ModelAttribute RefcodeEntity alertparam, Model md,
			HttpServletRequest rq) {

		String msg = refCodeService.addPARAMETER(alertparam, formmode, refcode, recordtype, reportcode);
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "ReferenceCodeMaster");

		return msg;

	}

	@RequestMapping(value = "DeleteReferenceCodeMaster", method = RequestMethod.POST)
	@ResponseBody
	public String DeleteReferenceCodeMaster(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String refcode, @RequestParam(required = false) String reportcode,
			@RequestParam(required = false) String recordtype, @ModelAttribute RefcodeEntity alertparam, Model md,
			HttpServletRequest rq) {
		String msg = refCodeService.deleteParameter(refcode, recordtype, reportcode);
		// String msg = refCodeService.addPARAMETER(alertparam, formmode, refcode,
		// recordtype, reportcode);
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "ReferenceCodeMaster");

		return msg;

	}

	/***************************************************************************************************/
	/*************************************
	 * Admin ---> REFERENCE CODE MASTER ends
	 ****************************************/

	/*********************************************
	 * MONITORING PARAMETER STARTS
	 **********************************************/

	@RequestMapping(value = "monitoringparameter", method = { RequestMethod.GET, RequestMethod.POST })
	public String Monitoringparameter(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "Rule Engine - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("MonitoringParameterList",
					montParameterRepository.parameterlist(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("RULECODELIST", refCodeRepository.findAllCustomList());

			md.addAttribute("menuname1", "RULE ENGINE - Add");
			md.addAttribute("MonitorParameter", new Monitoringparameter());
			md.addAttribute("Customertype", montParameterRepository.Customertype());
			md.addAttribute("Accounttype", montParameterRepository.Accounttype());
			md.addAttribute("Executiontype", montParameterRepository.Executiontype());
			md.addAttribute("Frequencytype", montParameterRepository.Frequencytype());
			md.addAttribute("ScriptType", montParameterRepository.Scripttype());
			md.addAttribute("MonitoringParameter", montParameterRepository.ruletype());
			md.addAttribute("RuleSubType", montParameterRepository.rulesubtype());
			md.addAttribute("rulecodetype", montParameterRepository.ruleCode());

		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", formmode);

			/* md.addAttribute("userProfile", userProfileDao.getUser(userid)); */
			md.addAttribute("menuname1", "Rule Engine- Modify");
			md.addAttribute("RULECODELIST", refCodeRepository.findAllCustomList());
			md.addAttribute("MonitorParameter", paraservices.getSrlNo(srlno));
			md.addAttribute("Customertype", montParameterRepository.Customertype());
			md.addAttribute("Accounttype", montParameterRepository.Accounttype());
			md.addAttribute("Executiontype", montParameterRepository.Executiontype());
			md.addAttribute("Frequencytype", montParameterRepository.Frequencytype());
			md.addAttribute("ScriptType", montParameterRepository.Scripttype());
			md.addAttribute("MonitoringParameter", montParameterRepository.ruletype());
			md.addAttribute("RuleSubType", montParameterRepository.rulesubtype());
			md.addAttribute("rulecodetype", montParameterRepository.ruleCode());

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("MonitorParameter", paraservices.getSrlNo(srlno));
			md.addAttribute("menuname1", "Rule Engine - Verify");

			md.addAttribute("MonitoringParameter", montParameterRepository.ruletype());
			md.addAttribute("RuleSubType", montParameterRepository.rulesubtype());
			md.addAttribute("rulecodetype", montParameterRepository.ruleCode());

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Rule Engine - Inquiry");
			md.addAttribute("MonitorParameter", paraservices.getSrlNo(srlno));

			md.addAttribute("MonitoringParameter", montParameterRepository.ruletype());
			md.addAttribute("RuleSubType", montParameterRepository.rulesubtype());
			md.addAttribute("rulecodetype", montParameterRepository.ruleCode());
		} else if (formmode.equals("delete")) {
			md.addAttribute("formmode", formmode);

			md.addAttribute("menuname1", "Rule Engine - Delete");
			md.addAttribute("MonitorParameter", paraservices.getSrlNo(srlno));

			md.addAttribute("MonitoringParameter", montParameterRepository.ruletype());
			md.addAttribute("RuleSubType", montParameterRepository.rulesubtype());
			md.addAttribute("rulecodetype", montParameterRepository.ruleCode());

		}
		md.addAttribute("adminflag", "adminflag");

		md.addAttribute("parameterflag", "parameterflag");

		md.addAttribute("menu", "monitoringparameter");

		return "AMLMonitoringparameter";
	}


	@RequestMapping(value = "createMonitoringParameter", method = RequestMethod.POST)
	@ResponseBody
	public String createMonitoringParameter(@RequestParam("formmode") String formmode,
			 @ModelAttribute Monitoringparameter monitoringparameter, Model md,
			HttpServletRequest rq) throws ParseException {
		logger.info("rbsValidationsChk:  Controller");
		
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = paraservices.addPARAMETER(monitoringparameter,formmode,userid);
		md.addAttribute("adminflag", "adminflag");

		return msg;

	}
	/*********************************************
	 * MONITORING PARAMETER ENDS
	 **********************************************/

	/********************
	 * ***************** Inquiry -----> CustomerInquiry starts
	 ****************************************/
	@RequestMapping(value = "CustomerInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String customerInquiry(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "CustomerInquiry");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("customerInquiry", cmgMaster.findAllCustom(PageRequest.of(currentPage, pageSize)));
			md.addAttribute("COUNT", "10");

		}
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCustProfile";
	}

	/***************************************************************
	 * CUSTOMER SEARCH
	 **********************************************************************/

	@RequestMapping(value = "getcustomerId", method = { RequestMethod.GET, RequestMethod.POST })
	public String getcustomerId(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custid2, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustomerInquiry");
		// md.addAttribute("formmode", "list"); // to set which form - valid values are
		// "edit" , "add" & "list"
		md.addAttribute("customerInquiry", cmgMaster.getcustomerId(custid2));
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCustProfile";
	}

	@RequestMapping(value = "getcustomerName", method = { RequestMethod.GET, RequestMethod.POST })
	public String getcustomerName(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custName2, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustomerInquiry");
		// md.addAttribute("formmode", "list"); // to set which form - valid values are
		// "edit" , "add" & "list"
		md.addAttribute("customerInquiry", cmgMaster.getcustomerName("%" + custName2 + "%"));
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCustProfile";
	}

	@RequestMapping(value = "getcustomerRESIDINGCOUNTRY", method = { RequestMethod.GET, RequestMethod.POST })
	public String getcustomerDOB(@RequestParam(required = false) String formmode,

			@RequestParam(required = false) String custName2, @RequestParam(required = false) Optional<Integer> page,

			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		// Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(custName2);

		md.addAttribute("menu", "CustomerInquiry");
		// md.addAttribute("formmode", "list"); // to set which form - valid values are
		// "edit" , "add" & "list"
		md.addAttribute("customerInquiry", cmgMaster.getcustomerDOB(custName2 + "%"));
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCustProfile";
	}

	@RequestMapping(value = "getcustomerNUMBER", method = { RequestMethod.GET, RequestMethod.POST })
	public String getcustomerNUMBER(@RequestParam(required = false) String formmode,

			@RequestParam(required = false) String custName2, @RequestParam(required = false) Optional<Integer> page,

			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustomerInquiry");
		// md.addAttribute("formmode", "list"); // to set which form - valid values are
		// "edit" , "add" & "list"
		md.addAttribute("customerInquiry", cmgMaster.getcustomerNUMBER(custName2));
		md.addAttribute("COUNT", cmgMaster.getcustomerNUMBERcount(custName2));
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCustProfile";
	}

	@RequestMapping(value = "getcustomerEMAIL", method = { RequestMethod.GET, RequestMethod.POST })
	public String getcustomerEMAIL(@RequestParam(required = false) String formmode,

			@RequestParam(required = false) String custName2, @RequestParam(required = false) Optional<Integer> page,

			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustomerInquiry");
		// md.addAttribute("formmode", "list"); // to set which form - valid values are
		// "edit" , "add" & "list"
		md.addAttribute("customerInquiry", cmgMaster.getcustomerEMAIL(custName2));
		md.addAttribute("COUNT", cmgMaster.getcustomerEMAILcount(custName2));
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCustProfile";
	}

	@RequestMapping(value = "getcustomerNatID", method = { RequestMethod.GET, RequestMethod.POST })
	public String getcustomerNatID(@RequestParam(required = false) String formmode,

			@RequestParam(required = false) String custName2, @RequestParam(required = false) Optional<Integer> page,

			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustomerInquiry");
		// md.addAttribute("formmode", "list"); // to set which form - valid values are
		// "edit" , "add" & "list"
		md.addAttribute("customerInquiry", cmgMaster.getcustomerNatID(custName2));
		md.addAttribute("COUNT", cmgMaster.getcustomerNatIDcount(custName2));
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCustProfile";
	}

	/*************************************
	 * Inquiry -----> CustomerInquiry ends
	 ****************************************/

	/*************************************
	 * Inquiry -----> AccountsInquiry starts
	 ****************************************/
	@RequestMapping(value = "AMLAccountsInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAccountsInquiry(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLAccountsInquiry");
			md.addAttribute("menuparent", "inquiry");
			md.addAttribute("menuname", "Accounts Inquiry");
			md.addAttribute("formmode", "list");
			md.addAttribute("AccountList", ACCTMaster.findAllAcctIdCustom(PageRequest.of(currentPage, pageSize)));

		}
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLAccountsInquiry";
	}

	@RequestMapping(value = "getACCcustomerId", method = { RequestMethod.GET, RequestMethod.POST })
	public String getACCcustomerId(@RequestParam(required = false) String search,
			@RequestParam(required = false) String custName2, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (search == null || search.equals("custID")) {
			md.addAttribute("menu", "AMLAccountsInquiry");
			md.addAttribute("menuparent", "inquiry");
			md.addAttribute("menuname", "Accounts Inquiry");
			md.addAttribute("formmode", "add");
			md.addAttribute("AccountList", ACCTMaster.getcustomerId(custName2));
			md.addAttribute("count", ACCTMaster.getcustomerIdcount(custName2));

		} else if (search.equals("custACC")) {
			md.addAttribute("menu", "AMLAccountsInquiry");
			md.addAttribute("menuparent", "inquiry");
			md.addAttribute("menuname", "Accounts Inquiry");
			md.addAttribute("formmode", "add");
			md.addAttribute("AccountList", ACCTMaster.getACCNumber(custName2));
			md.addAttribute("count", ACCTMaster.getACCNumbercount(custName2));

		} else if (search.equals("custNAME")) {
			md.addAttribute("menu", "AMLAccountsInquiry");
			md.addAttribute("menuparent", "inquiry");
			md.addAttribute("menuname", "Accounts Inquiry");
			md.addAttribute("formmode", "add");
			md.addAttribute("AccountList", ACCTMaster.getACCName(custName2));
			md.addAttribute("count", ACCTMaster.getACCNamecount(custName2));

		} else if (search.equals("custSCH")) {
			md.addAttribute("menu", "AMLAccountsInquiry");
			md.addAttribute("menuparent", "inquiry");
			md.addAttribute("menuname", "Accounts Inquiry");
			md.addAttribute("formmode", "add");
			md.addAttribute("AccountList", ACCTMaster.getACCSchCode(custName2));
			md.addAttribute("count", ACCTMaster.getACCSchCodecount(custName2));

		} else if (search.equals("custCUR")) {
			md.addAttribute("menu", "AMLAccountsInquiry");
			md.addAttribute("menuparent", "inquiry");
			md.addAttribute("menuname", "Accounts Inquiry");
			md.addAttribute("formmode", "add");
			md.addAttribute("AccountList", ACCTMaster.getACCCurCode(custName2));
			md.addAttribute("count", ACCTMaster.getACCCurCodecount(custName2));

		} else if (search.equals("custBAL")) {
			md.addAttribute("menu", "AMLAccountsInquiry");
			md.addAttribute("menuparent", "inquiry");
			md.addAttribute("menuname", "Accounts Inquiry");
			md.addAttribute("formmode", "add");
			md.addAttribute("AccountList", ACCTMaster.getACCBalance(custName2));
			md.addAttribute("count", ACCTMaster.getACCBalancecount(custName2));

		}
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLAccountsInquiry";
	}

	/*************************************
	 * Inquiry -----> AccountsInquiry ends
	 ****************************************/

	/*************************************
	 * Inquiry -----> Transaction Inquiry starts
	 * 
	 * @throws ParseException
	 ****************************************/
	@RequestMapping(value = "TransactionInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String transactionInquiry(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String fromdate, @RequestParam(required = false) String todate,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		/// Get Yesterday date
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();

		cal1.add(Calendar.DATE, -2);
		cal2.add(Calendar.DATE, -1);
		String date = dateFormat.format(cal1.getTime());
		String date1 = dateFormat.format(cal2.getTime());

		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "TransactionInquiry");
			md.addAttribute("menuname", "Transaction Inquiry");
			md.addAttribute("formmode", "list");
			md.addAttribute("fromdate", date);
			md.addAttribute("todate", date1);
			md.addAttribute("datatype", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("transactionInquiry",
					TRANMaster.findAllCustom(fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
			md.addAttribute("count", TRANMaster.findAlldatecount(fromdate1, todate1));

		}
		md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLTransaction";
	}

	@RequestMapping(value = "GetTransactionSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String GetTransactionSearch(@RequestParam(required = false) String search,
			@RequestParam(required = false) String custName2, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (search == null || search.equals("TRANREF")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANREF(custName2));
			md.addAttribute("count", TRANMaster.getTRANREFcount(custName2));
		} else if (search.equals("TRANDATE")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANDATE(custName2));
			md.addAttribute("count", TRANMaster.getTRANDATEcount(custName2));

		} else if (search.equals("TRANID")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANID(custName2));
			md.addAttribute("count", TRANMaster.getTRANIDcount(custName2));

		} else if (search.equals("PARTTRANID")) {
			md.addAttribute("transactionInquiry", TRANMaster.getPARTTRANID(custName2));
			md.addAttribute("count", TRANMaster.getPARTTRANIDcount(custName2));

		} else if (search.equals("PARTTRANTYPE")) {
			md.addAttribute("transactionInquiry", TRANMaster.getPARTTRANTYPE(custName2));
			md.addAttribute("count", TRANMaster.getPARTTRANTYPEcount(custName2));

		} else if (search.equals("TRANAMT")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANAMT(custName2));
			md.addAttribute("count", TRANMaster.getTRANAMTcount(custName2));

		} else if (search.equals("TRANSTATUS")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANSTATUS(custName2));
			md.addAttribute("count", TRANMaster.getTRANSTATUScount(custName2));

		} else if (search.equals("TRANACCT")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANACCT(custName2));
			md.addAttribute("count", TRANMaster.getTRANACCTcount(custName2));

		} else if (search.equals("TRANCUST")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANCUST(custName2));
			md.addAttribute("count", TRANMaster.getTRANCUSTcount(custName2));

		} else if (search.equals("CUSTID")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANCUSTID(custName2));
			md.addAttribute("count", TRANMaster.getTRANCUSTIDcount(custName2));

		} else if (search.equals("TRANCRNCY")) {
			md.addAttribute("transactionInquiry", TRANMaster.getTRANCRNCY(custName2));
			md.addAttribute("count", TRANMaster.getTRANCRNCYcount(custName2));

		}
		md.addAttribute("menu", "TransactionInquiry");
		md.addAttribute("menuname", "Transaction Inquiry");
		md.addAttribute("formmode", "SEARCH");
		// md.addAttribute("datatype", "list");
		md.addAttribute("inquiryflag", "inquiryflag");

		return "NewFile";
	}

	@RequestMapping(value = "TransactionInquirydate", method = { RequestMethod.GET, RequestMethod.POST })
	public String TransactionInquirydate(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String fromdate,
			@RequestParam(required = false) String todate, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

		md.addAttribute("menu", "TransactionInquiry");
		md.addAttribute("menuname", "Transaction Inquiry");
		md.addAttribute("formmode", "list");
		md.addAttribute("select", "datefield");
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);// to set which form - valid values are "edit" , "add" & "list"
		md.addAttribute("transactionInquiry",
				TRANMaster.findAlldate(fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("count", TRANMaster.findAlldatecount(fromdate1, todate1));

		md.addAttribute("inquiryflag", "inquiryflag");

		return "NewFile";
	}

	/*************************************
	 * Inquiry -----> Transaction Inquiry ends
	 ****************************************/

	/*************************************
	 * Monitoring -----> Transaction Monitoring starts
	 * 
	 * @throws ParseException
	 ****************************************/
	@RequestMapping(value = "AMLTransMonitoring", method = { RequestMethod.GET, RequestMethod.POST })
	public String transactionMonitoring(@RequestParam(required = false) String formmode,
			@RequestParam(value = "rulecode", required = false) String rulecode,
			@RequestParam(value = "tranamount", required = false) String tranamount,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "TRANTYPE", required = false) String TRANTYPE,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "noofdays", required = false) String noofdays,
			@RequestParam(value = "rolecodedesc", required = false) String rolecodedesc,
			@RequestParam(value = "threshold", required = false) BigDecimal threshold,
			@RequestParam(value = "lowvalue", required = false) BigDecimal lowvalue,
			@RequestParam(value = "highvalue", required = false) BigDecimal highvalue,

			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLTransMonitoring");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("transactionMonitoring", montParameterRepository.findAllCustom());
			md.addAttribute("rulecodetype", montParameterRepository.ruleCode());
			if (rulecode != null) {
				if (TRANTYPE == null) {
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					md.addAttribute("TransactionMaster", transactionmasterServices
							.getTransactionDetails(PageRequest.of(currentPage, pageSize), rulecode, today1));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("HVCDP")) {
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomList(lowvalue, highvalue, today1,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);

					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("HVCWL")) {
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListHVCWL(lowvalue, highvalue, today1,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("HVNCD")) {

					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListHVNCD(lowvalue, highvalue, today1,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("HVNCW")) {
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListHVNCW(lowvalue, highvalue, today1,
							PageRequest.of(currentPage, pageSize)));

					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVCDP")) {
					int currentPage = page.orElse(0);
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVCDP(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVCWL")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVCWL(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVNCD")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVNCD(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVNCW")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVNCW(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVCDP1")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVCDP1(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVCWL1")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVCWL1(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVNCD1")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVNCD1(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CVNCW1")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCVNCW1(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("DTCDT")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);

					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListDTCDT(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("TSCTP")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListTSCTP(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("TSCTP1")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListTSCTP1(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CTACP")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);

					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListCTACP(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("MODE", "CTACP");
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("STVID")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);

					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListSTVID(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("MODE", "CTACP");
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CTVID")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCTVID(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("STVET")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);

					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListSTVET(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("NTVET")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListNTVET(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("TATLS")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);

					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListTATLS(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("NATLS")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListNATLS(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("RTBLP")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListRTBLP(fromdate1, today1, lowvalue,
							highvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CTBTL")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListCTBTL(today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("NCTBT")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListNCTBT(today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("RTBTL")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListRTBTL(today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("CUTMA")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListCUTMA(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("ATBTL")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListATBTL(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("MTOOR")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListMTOOR(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("OTOMR")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListOTOMR(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("TINCP")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					md.addAttribute("TransactionMaster",
							TRANMaster.findAllCustomListTINCP(today1, lowvalue, PageRequest.of(currentPage, pageSize)));
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("today", today);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("VINCP")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListVINCP(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("FIFOE")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListFIFOE(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("MRTSB")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListMRTSB(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("RRTSB")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListRRTSB(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("FINWC")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListFINWC(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("FOUTC")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListFOUTC(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("SODWE")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListSODWE(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("RSINO")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListRSINO(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				} else if (TRANTYPE.equals("LVDFS")) {
					int currentPage = page.orElse(0);
					int pageSize = size.orElse(Integer.parseInt(pagesize));
					Date today1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
					md.addAttribute("TransactionMaster", TRANMaster.findAllCustomListLVDFS(fromdate1, today1, threshold,
							PageRequest.of(currentPage, pageSize)));
					md.addAttribute("today", today);
					md.addAttribute("ruledescription", rolecodedesc);
					md.addAttribute("lowvalue", lowvalue);
					md.addAttribute("highvalue", highvalue);
					md.addAttribute("rulecode", rulecode);
					md.addAttribute("formmode", "tranlist");
				}

			}
		}
		md.addAttribute("threshold", threshold);
		md.addAttribute("noofdays", noofdays);
		md.addAttribute("monitoringflag", "monitoringflag");

		return "AMLTransMonitoring";
	}

	/*************************************
	 * Monitoring -----> Transaction Monitoring ends
	 ****************************************/
	/*************************************
	 * Monitoring -----> Transaction Master Starts
	 ****************************************/
	@RequestMapping(value = "AMLTransactionMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAccountsInquiry1(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String tranid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "Transaction Master");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("TransactionMaster",
					transactionMasterRepository.findAll(PageRequest.of(currentPage, pageSize)));

		}
		md.addAttribute("monitoringflag", "monitoringflag");
		md.addAttribute("menu", "AMLTransMonitoring");

		return "AMLTransactionMaster";
	}

	/*************************************
	 * Monitoring -----> Transaction Master ends
	 *************************************/

	/*************************************
	 * List Management -----> Customized List ------>Black list starts
	 ****************************************/

	@RequestMapping(value = "AMLBlacklist", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLblacklistInd(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal srlno, @RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "nid", required = false) String nid,
			@RequestParam(value = "risk", required = false) String risk,
			@RequestParam(value = "masterdataid", required = false) BigDecimal masterdataid,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "datefreezed", required = false) String datefreezed,
			@RequestParam(value = "datedefreezed", required = false) String datedefreezed,
			@RequestParam(value = "prodtype", required = false) String prodtype,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("Ind_list")) {
			md.addAttribute("menuname", "Individual  Black List and Caution List  - List ");
			md.addAttribute("formmode", "Ind_list"); // to set which form - valid values are "edit" , "add" & "list"

			try {
				if (cif != null && !cif.isEmpty()) {

					md.addAttribute("custIndblackList",
							cust_black_List_Ind_Repository.getIndlistBycif(PageRequest.of(currentPage, pageSize), cif));

				} else if (lastname != null && !lastname.isEmpty()) {

					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistBylastname(PageRequest.of(currentPage, pageSize), lastname + '%'));

				} else if (firstname != null && !firstname.isEmpty()) {

					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistByfirstname(PageRequest.of(currentPage, pageSize), firstname + '%'));

				} else if (nid != null && !nid.isEmpty()) {

					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistBynid(PageRequest.of(currentPage, pageSize), nid + '%'));

				} else if (risk != null && !risk.isEmpty()) {

					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistByrisk(PageRequest.of(currentPage, pageSize), risk));

				} else if (masterdataid != null) {

					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistBymasterdataid(PageRequest.of(currentPage, pageSize), masterdataid));

				} else if (status != null) {
					if (status.equals("O")) {
						md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
								.getIndlistBystatusOpenedANDEmpty(PageRequest.of(currentPage, pageSize), status));
					} else {
						md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
								.getIndlistBystatus(PageRequest.of(currentPage, pageSize), status));
					}
				} else if (datefreezed != null && !datefreezed.isEmpty()) {
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(datefreezed);
					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistByDATE_FREEZED(PageRequest.of(currentPage, pageSize), fromdate1));

				} else if (datedefreezed != null && !datedefreezed.isEmpty()) {
					Date fromdate2 = new SimpleDateFormat("dd/MM/yyyy").parse(datedefreezed);
					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistByDATE_DeFREEZED(PageRequest.of(currentPage, pageSize), fromdate2));

				} else if (prodtype != null && !prodtype.isEmpty()) {
					md.addAttribute("custIndblackList", cust_black_List_Ind_Repository
							.getIndlistByProdType(PageRequest.of(currentPage, pageSize), prodtype));

				} else {
					md.addAttribute("custIndblackList",
							cust_black_List_Ind_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("custIndblackList",
						cust_black_List_Ind_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("Ind_add")) {

			md.addAttribute("formmode", "Ind_add");
			md.addAttribute("menuname1", "Individual Black List and Caution List - Add");
			md.addAttribute("Ind_ListParameter", new Cust_Black_List_Ind_Entity());
//			md.addAttribute("RefNo", cust_black_list_Ind_Services.getNextRefValue());

		} else if (formmode.equals("Ind_edit")) {

			md.addAttribute("formmode", "Ind_edit");
			md.addAttribute("menuname1", "Individual Black List and Caution List - Modify");
			md.addAttribute("Ind_ListParameter", cust_black_list_Ind_Services.getSrlNo(srlno));

		} else if (formmode.equals("Ind_verify")) {

			md.addAttribute("formmode", "Ind_verify");
			md.addAttribute("Ind_ListParameter", cust_black_list_Ind_Services.getSrlNo(srlno));
			md.addAttribute("menuname1", "Individual Black List and Caution List - Verify");

		} else if (formmode.equals("Ind_view")) {

			md.addAttribute("formmode", "Ind_view");
			md.addAttribute("menuname1", "Individual Black List and Caution List - Inquiry");
			md.addAttribute("Ind_ListParameter", cust_black_list_Ind_Services.getSrlNo(srlno));

		} else if (formmode.equals("Ind_delete")) {

			md.addAttribute("formmode", "Ind_delete");
			md.addAttribute("menuname1", "Individual Black List and Caution List - Delete");
			md.addAttribute("Ind_ListParameter", cust_black_list_Ind_Services.getSrlNo(srlno));

		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return "AMLBlacklist";
	}

	@RequestMapping(value = "AMLBlacklistCorp", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLblacklist(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal srlno, @RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "nid", required = false) String nid,
			@RequestParam(value = "risk", required = false) String risk,
			@RequestParam(value = "masterdataid", required = false) BigDecimal masterdataid,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "datefreezed", required = false) String datefreezed,
			@RequestParam(value = "datedefreezed", required = false) String datedefreezed,
			@RequestParam(value = "prodtype", required = false) String prodtype,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("Corp_list")) {
			md.addAttribute("menuname_corp", "Corporate Black List and Caution List - List ");
			md.addAttribute("formmode", "Corp_list"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (cif != null && !cif.isEmpty()) {
					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getCorplistBycif(PageRequest.of(currentPage, pageSize), cif));
				} else if (lastname != null && !lastname.isEmpty()) {
					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getCorplistBylastname(PageRequest.of(currentPage, pageSize), lastname + '%'));
				} else if (firstname != null && !firstname.isEmpty()) {
					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getCorplistByfirstname(PageRequest.of(currentPage, pageSize), firstname + '%'));
				} else if (nid != null && !nid.isEmpty()) {
					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getCorplistBynid(PageRequest.of(currentPage, pageSize), nid + '%'));
				} else if (risk != null && !risk.isEmpty()) {

					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getIndlistByrisk(PageRequest.of(currentPage, pageSize), risk));

				} else if (masterdataid != null) {

					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getIndlistBymasterdataid(PageRequest.of(currentPage, pageSize), masterdataid));

				} else if (status != null && !status.isEmpty()) {
					if (status.equals("O")) {
						md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
								.getIndlistBystatusOpenedANDEmpty(PageRequest.of(currentPage, pageSize), status));
					} else {
						md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
								.getIndlistBystatus(PageRequest.of(currentPage, pageSize), status));
					}
				} else if (datefreezed != null && !datefreezed.isEmpty()) {
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(datefreezed);
					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getIndlistByDATE_FREEZED(PageRequest.of(currentPage, pageSize), fromdate1));

				} else if (datedefreezed != null && !datedefreezed.isEmpty()) {
					Date fromdate2 = new SimpleDateFormat("dd/MM/yyyy").parse(datedefreezed);
					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getIndlistByDATE_DeFREEZED(PageRequest.of(currentPage, pageSize), fromdate2));

				} else if (prodtype != null && !prodtype.isEmpty()) {
					md.addAttribute("custCorpblackList", cust_black_List_Corp_Repository
							.getIndlistByProdType(PageRequest.of(currentPage, pageSize), prodtype));

				} else {
					md.addAttribute("custCorpblackList",
							cust_black_List_Corp_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("custCorpblackList",
						cust_black_List_Corp_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("Corp_add")) {
			md.addAttribute("formmode", "Corp_add");
			md.addAttribute("menuname_corp", "Corporate Black List and Caution List - Add");
			md.addAttribute("Corp_ListParameter", new Cust_Black_List_Ind_Entity());
//			md.addAttribute("CorpRefNo", cust_black_list_Corp_Services.getNextRefValue());
		} else if (formmode.equals("Corp_edit")) {

			md.addAttribute("formmode", "Corp_edit");
			md.addAttribute("menuname_corp", "Corporate Black List and Caution List - Modify");
			md.addAttribute("Corp_ListParameter", cust_black_list_Corp_Services.getSrlNo(srlno));

		} else if (formmode.equals("Corp_verify")) {

			md.addAttribute("formmode", "Corp_verify");
			md.addAttribute("menuname_corp", "Corporate Black List and Caution List - Verify");
			md.addAttribute("Corp_ListParameter", cust_black_list_Corp_Services.getSrlNo(srlno));

		} else if (formmode.equals("Corp_view")) {

			md.addAttribute("formmode", "Corp_view");
			md.addAttribute("menuname_corp", "Corporate Black List and Caution List - View");
			md.addAttribute("Corp_ListParameter", cust_black_list_Corp_Services.getSrlNo(srlno));

		} else if (formmode.equals("Corp_delete")) {

			md.addAttribute("formmode", "Corp_delete");
			md.addAttribute("menuname_corp", "Corporate Black List and Caution List - Delete");
			md.addAttribute("Corp_ListParameter", cust_black_list_Corp_Services.getSrlNo(srlno));

		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return "AMLBlacklist";
	}

	// individual black list
	@RequestMapping(value = "createBlackListIND", method = RequestMethod.POST)
	@ResponseBody
	public String createCust_BlackList_Ind(@RequestParam("formmode") String formmode,
			@ModelAttribute Cust_Black_List_Ind_Entity alertparam, Model md, HttpServletRequest rq) {

		String msg = cust_black_list_Ind_Services.addBlack_IND_List(alertparam, formmode);

//		if (msg.equals("Black List Created Successfully")) {
//			//after saving the new  record increementing the value by 1 for next record to insert
//			cust_black_list_Ind_Services.updateBlack_list_Ind_Num();
//		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return msg;

	}

	// corporate black list
	@RequestMapping(value = "createBlackListCorp", method = RequestMethod.POST)
	@ResponseBody
	public String createCust_BlackList_Corp(@RequestParam("formmode") String formmode,
			@ModelAttribute Cust_Black_List_Corp_Entity alertparam, Model md, HttpServletRequest rq) {

		String msg = cust_black_list_Corp_Services.addBlack_IND_List(alertparam, formmode);

//		if (msg.equals("Black List Created Successfully")) {
//			//after saving the new  record increementing the value by 1 for next record to insert
//			cust_black_list_Corp_Services.updateBlack_list_Corp_Num();
//		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return msg;

	}

	/*************************************
	 * List Management -----> Customized List ------>Black list ends
	 ****************************************/
	@RequestMapping(value = "AMLwhitelist", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLwhitelist(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) BigDecimal srlno, @RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "nid", required = false) String nid,
			@RequestParam(value = "risk", required = false) String risk,
			@RequestParam(value = "masterdataid", required = false) BigDecimal masterdataid,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "datefreezed", required = false) String datefreezed,
			@RequestParam(value = "datedefreezed", required = false) String datedefreezed,
			@RequestParam(value = "prodtype", required = false) String prodtype,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLwhitelist");
			md.addAttribute("menuname", "White List");

			md.addAttribute("menuname", "White List - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			try {
				if (cif != null && !cif.isEmpty()) {

					md.addAttribute("singledetail", new BlackListEntity());
					md.addAttribute("NegativeList",
							whiteListRepository.getNeglistBycif(PageRequest.of(currentPage, pageSize), cif));

				} else if (lastname != null && !lastname.isEmpty()) {

					md.addAttribute("NegativeList", whiteListRepository
							.getNeglistBylastname(PageRequest.of(currentPage, pageSize), lastname + '%'));

				} else if (firstname != null && !firstname.isEmpty()) {

					md.addAttribute("NegativeList", whiteListRepository
							.getNeglistByfirstname(PageRequest.of(currentPage, pageSize), firstname + '%'));

				} else if (nid != null && !nid.isEmpty()) {

					md.addAttribute("NegativeList",
							whiteListRepository.getNeglistBynid(PageRequest.of(currentPage, pageSize), nid + '%'));

				} else if (risk != null && !risk.isEmpty()) {

					md.addAttribute("NegativeList",
							whiteListRepository.getIndlistByrisk(PageRequest.of(currentPage, pageSize), risk));

				} else if (masterdataid != null) {

					md.addAttribute("NegativeList", whiteListRepository
							.getIndlistBymasterdataid(PageRequest.of(currentPage, pageSize), masterdataid));

				} else if (status != null) {
					if (status.equals("O")) {
						md.addAttribute("NegativeList", whiteListRepository
								.getIndlistBystatusOpenedANDEmpty(PageRequest.of(currentPage, pageSize), status));
					} else {
						md.addAttribute("NegativeList",
								whiteListRepository.getIndlistBystatus(PageRequest.of(currentPage, pageSize), status));
					}
				} else if (datefreezed != null && !datefreezed.isEmpty()) {
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(datefreezed);
					md.addAttribute("NegativeList", whiteListRepository
							.getIndlistByDATE_FREEZED(PageRequest.of(currentPage, pageSize), fromdate1));

				} else if (datedefreezed != null && !datedefreezed.isEmpty()) {
					Date fromdate2 = new SimpleDateFormat("dd/MM/yyyy").parse(datedefreezed);
					md.addAttribute("NegativeList", whiteListRepository
							.getIndlistByDATE_DeFREEZED(PageRequest.of(currentPage, pageSize), fromdate2));

				} else if (prodtype != null && !prodtype.isEmpty()) {
					md.addAttribute("NegativeList",
							whiteListRepository.getIndlistByProdType(PageRequest.of(currentPage, pageSize), prodtype));

				} else {

					md.addAttribute("NegativeList",
							whiteListRepository.parameterlist(PageRequest.of(currentPage, pageSize)));

				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						whiteListRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "White List - Add");
			md.addAttribute("NegativeParameter", new NegativeListEntity());
//			md.addAttribute("RefNo", negativeListservices.getNextRefValue());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "White List - Modify");
			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);

			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));
			md.addAttribute("menuname1", "White List - Verify");

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "White List - Inquiry");
			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "White List - Delete");
			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));
		}
		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return "AMLWhiteList";
	}

	@RequestMapping(value = "createWhiteList", method = RequestMethod.POST)
	@ResponseBody
	public String createWhiteList(@RequestParam("formmode") String formmode,
			@ModelAttribute Cust_White_List_Entity alertparam, Model md, HttpServletRequest rq) {

		String msg = whiteListservices.addNegativeList(alertparam, formmode);

//		if (msg.equals("Negative List Created Successfully")) {
//			//after saving the new  record increementing the value by 1 for next record to insert
//			negativeListservices.updateNegative_list_Num();
//		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return msg;

	}

	@RequestMapping(value = "AMLPEPList", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLPEPList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "nid", required = false) String nid,
			@RequestParam(value = "risk", required = false) String risk, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "PEP Listing - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (cif != null && !cif.isEmpty()) {

					md.addAttribute("PepList",
							cust_pep_list_Repository.getpeplistBycif(cif, PageRequest.of(currentPage, pageSize)));

				} else if (lastname != null && !lastname.isEmpty()) {

					md.addAttribute("PepList", cust_pep_list_Repository.getpeplistBylastname(lastname + '%',
							PageRequest.of(currentPage, pageSize)));

				} else if (firstname != null && !firstname.isEmpty()) {

					md.addAttribute("PepList", cust_pep_list_Repository.getpeplistByfirstname(firstname + '%',
							PageRequest.of(currentPage, pageSize)));

				} else if (nid != null && !nid.isEmpty()) {

					md.addAttribute("PepList",
							cust_pep_list_Repository.getpeplistBynid(nid + '%', PageRequest.of(currentPage, pageSize)));

				} else if (risk != null && !risk.isEmpty()) {
					md.addAttribute("PepList",
							cust_pep_list_Repository.getIndlistByrisk(risk, PageRequest.of(currentPage, pageSize)));

				} else {
					md.addAttribute("PepList",
							cust_pep_list_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("PepList",
						cust_pep_list_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "PEP List - Add");
			md.addAttribute("PepParameter", new Cust_Pep_List_Entity());
//			md.addAttribute("RefNo", pepListservices.getNextRefValue());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "PEP List - Modify");
			md.addAttribute("PepParameter", pepListservices.getSrlNo(srlno));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);

			md.addAttribute("PepParameter", pepListservices.getSrlNo(srlno));
			md.addAttribute("menuname1", "PEP List - Verify");

		}

		else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "PEP List - Inquiry");
			md.addAttribute("PepParameter", pepListservices.getSrlNo(srlno));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "PEP List - Delete");
			md.addAttribute("PepParameter", pepListservices.getSrlNo(srlno));
		}
		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return "AMLPepList";
	}

	@RequestMapping(value = "createPepList", method = RequestMethod.POST)
	@ResponseBody
	public String createPepList(@RequestParam("formmode") String formmode,
			@ModelAttribute Cust_Pep_List_Entity alertparam, Model md, HttpServletRequest rq) {

		String msg = pepListservices.addPepList(alertparam, formmode);

//		if (msg.equals("Pep List Created Successfully")) {
//			//after saving the new  record increementing the value by 1 for next record to insert
//			negativeListservices.updateNegative_list_Num();
//		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return msg;

	}
	// End of pep list master
	// **********************************************************************

	// Start of HNWI list master
	// *****************************************************************************

	@RequestMapping(value = "AMLHNWIList", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLHNWIList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "risk", required = false) String risk,
			@RequestParam(value = "prodtype", required = false) String prodtype,
			@RequestParam(value = "nid", required = false) String nid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "HNWI Listing - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (cif != null && !cif.isEmpty()) {

					md.addAttribute("HnwiList",
							cust_hnwi_list_Repository.gethnwilistBycif(PageRequest.of(currentPage, pageSize), cif));

				} else if (lastname != null && !lastname.isEmpty()) {

					md.addAttribute("HnwiList", cust_hnwi_list_Repository
							.gethnwilistBylastname(PageRequest.of(currentPage, pageSize), lastname + '%'));

				} else if (firstname != null && !firstname.isEmpty()) {

					md.addAttribute("HnwiList", cust_hnwi_list_Repository
							.gethnwilistByfirstname(PageRequest.of(currentPage, pageSize), firstname + '%'));

				} else if (nid != null && !nid.isEmpty()) {

					md.addAttribute("HnwiList", cust_hnwi_list_Repository
							.gethnwilistBynid(PageRequest.of(currentPage, pageSize), nid + '%'));

				} else if (risk != null && !risk.isEmpty()) {

					md.addAttribute("HnwiList",
							cust_hnwi_list_Repository.gethnwilistByrisk(PageRequest.of(currentPage, pageSize), risk));

				} else if (prodtype != null && !prodtype.isEmpty()) {

					md.addAttribute("HnwiList", cust_hnwi_list_Repository
							.gethnwilistByprodtupe(PageRequest.of(currentPage, pageSize), prodtype));

				} else {
					md.addAttribute("HnwiList",
							cust_hnwi_list_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("HnwiList",
						cust_hnwi_list_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "HNWI List - Add");
			md.addAttribute("HnwiParameter", new Cust_Hnwi_List_Entity());
//				md.addAttribute("RefNo", pepListservices.getNextRefValue());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "HNWI List - Modify");
			md.addAttribute("HnwiParameter", hnwiListservices.getSrlNo(srlno));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("HnwiParameter", hnwiListservices.getSrlNo(srlno));
			md.addAttribute("menuname1", "HNWI List - Verify");

		}

		else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "HNWI List - Inquiry");
			md.addAttribute("HnwiParameter", hnwiListservices.getSrlNo(srlno));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "HNWI List - Delete");
			md.addAttribute("HnwiParameter", hnwiListservices.getSrlNo(srlno));
		}
		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return "AMLHNWIList";
	}

	@RequestMapping(value = "createHnwiList", method = RequestMethod.POST)
	@ResponseBody
	public String createHNWIList(@RequestParam("formmode") String formmode,
			@ModelAttribute Cust_Hnwi_List_Entity alertparam, Model md, HttpServletRequest rq) {

		String msg = hnwiListservices.addHnwiList(alertparam, formmode);

//			if (msg.equals("Pep List Created Successfully")) {
//				//after saving the new  record increementing the value by 1 for next record to insert
//				negativeListservices.updateNegative_list_Num();
//			}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return msg;

	}
	// End of HNWI list master
	// **********************************************************************

	// Start of ABOND_LIST list master
	// *****************************************************************************

	@RequestMapping(value = "AMLABAND_FUNDList", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLABAND_FUNDList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "Abandoned Fund Listing");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (cif != null && !cif.isEmpty()) {
					md.addAttribute("AbondList", cust_abond_fund_list_Repository
							.getabandlistBycif(PageRequest.of(currentPage, pageSize), cif));
				} else if (lastname != null && !lastname.isEmpty()) {
					md.addAttribute("AbondList", cust_abond_fund_list_Repository
							.getabandlistBylastname(PageRequest.of(currentPage, pageSize), lastname + '%'));
				} else {
					md.addAttribute("AbondList",
							cust_abond_fund_list_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("AbondList",
						cust_abond_fund_list_Repository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Abandoned Fund Listing - Add");
			md.addAttribute("AbondParameter", new Cust_Aband_Fund_List_Entity());
//				md.addAttribute("RefNo", pepListservices.getNextRefValue());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Abandoned Fund Listing - Modify");
			md.addAttribute("AbondParameter", abond_fund_Listservices.getSrlNo(srlno));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("AbondParameter", abond_fund_Listservices.getSrlNo(srlno));
			md.addAttribute("menuname1", "Abandoned Fund Listing - Verify");

		}

		else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Abandoned Fund Listing - Inquiry");
			md.addAttribute("AbondParameter", abond_fund_Listservices.getSrlNo(srlno));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Abandoned Fund Listing - Delete");
			md.addAttribute("AbondParameter", abond_fund_Listservices.getSrlNo(srlno));
		}
		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return "AMLABAND_FUNDList";
	}

	@RequestMapping(value = "createAMLABAND_FUNDList", method = RequestMethod.POST)
	@ResponseBody
	public String createAMLABAND_FUNDList(@RequestParam("formmode") String formmode,
			@ModelAttribute Cust_Aband_Fund_List_Entity alertparam, Model md, HttpServletRequest rq) {

		String msg = abond_fund_Listservices.addABondList(alertparam, formmode);

//			if (msg.equals("Pep List Created Successfully")) {
//				//after saving the new  record increementing the value by 1 for next record to insert
//				negativeListservices.updateNegative_list_Num();
//			}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return msg;

	}
	// End of ABOND_LIST list master
	// **********************************************************************

	@RequestMapping(value = "/blacklistadd", method = RequestMethod.POST)
	@ResponseBody
	public String bankmasterAdd(@ModelAttribute("singledetail") BlackListEntity detail, HttpServletRequest hs) {

		return blacklistservices.detailChanges(detail, 'A');

	}

	@RequestMapping(value = "blacklistdelete", method = RequestMethod.POST)
	@ResponseBody
	public String bankmasterDelete(@ModelAttribute("singledetail") BlackListEntity detail, HttpServletRequest hs) {
		String userid = (String) hs.getSession().getAttribute("USERID");
		return blacklistservices.detailChanges(detail, 'D');

	}

	/*************************************
	 * List Management -----> Customized List ------>Black list ends
	 ****************************************/

	/*************************************
	 * List Management -----> Customized List ------>Negative List Starts
	 ****************************************/

	@RequestMapping(value = "AMLnegativelist", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLnegativelist(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal srlno, @RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "nid", required = false) String nid,
			@RequestParam(value = "risk", required = false) String risk,
			@RequestParam(value = "masterdataid", required = false) BigDecimal masterdataid,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "datefreezed", required = false) String datefreezed,
			@RequestParam(value = "datedefreezed", required = false) String datedefreezed,
			@RequestParam(value = "prodtype", required = false) String prodtype,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "Negative List - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (cif != null && !cif.isEmpty()) {

					md.addAttribute("NegativeList",
							negativeListRepository.getNeglistBycif(PageRequest.of(currentPage, pageSize), cif));

				} else if (lastname != null && !lastname.isEmpty()) {

					md.addAttribute("NegativeList", negativeListRepository
							.getNeglistBylastname(PageRequest.of(currentPage, pageSize), lastname + '%'));

				} else if (firstname != null && !firstname.isEmpty()) {

					md.addAttribute("NegativeList", negativeListRepository
							.getNeglistByfirstname(PageRequest.of(currentPage, pageSize), firstname + '%'));

				} else if (nid != null && !nid.isEmpty()) {

					md.addAttribute("NegativeList",
							negativeListRepository.getNeglistBynid(PageRequest.of(currentPage, pageSize), nid + '%'));

				} else if (risk != null && !risk.isEmpty()) {

					md.addAttribute("NegativeList",
							negativeListRepository.getIndlistByrisk(PageRequest.of(currentPage, pageSize), risk));

				} else if (masterdataid != null) {

					md.addAttribute("NegativeList", negativeListRepository
							.getIndlistBymasterdataid(PageRequest.of(currentPage, pageSize), masterdataid));

				} else if (status != null) {
					if (status.equals("O")) {
						md.addAttribute("NegativeList", negativeListRepository
								.getIndlistBystatusOpenedANDEmpty(PageRequest.of(currentPage, pageSize), status));
					} else {
						md.addAttribute("NegativeList", negativeListRepository
								.getIndlistBystatus(PageRequest.of(currentPage, pageSize), status));
					}
				} else if (datefreezed != null && !datefreezed.isEmpty()) {
					Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(datefreezed);
					md.addAttribute("NegativeList", negativeListRepository
							.getIndlistByDATE_FREEZED(PageRequest.of(currentPage, pageSize), fromdate1));

				} else if (datedefreezed != null && !datedefreezed.isEmpty()) {
					Date fromdate2 = new SimpleDateFormat("dd/MM/yyyy").parse(datedefreezed);
					md.addAttribute("NegativeList", negativeListRepository
							.getIndlistByDATE_DeFREEZED(PageRequest.of(currentPage, pageSize), fromdate2));

				} else if (prodtype != null && !prodtype.isEmpty()) {
					md.addAttribute("NegativeList", negativeListRepository
							.getIndlistByProdType(PageRequest.of(currentPage, pageSize), prodtype));

				} else {

					md.addAttribute("NegativeList",
							negativeListRepository.parameterlist(PageRequest.of(currentPage, pageSize)));

				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						negativeListRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Negative List - Add");
			md.addAttribute("NegativeParameter", new NegativeListEntity());
//			md.addAttribute("RefNo", negativeListservices.getNextRefValue());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Negative List - Modify");
			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);

			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));
			md.addAttribute("menuname1", "Negative List - Verify");

		}

		else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Negative List - Inquiry");
			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Negative List - Delete");
			md.addAttribute("NegativeParameter", negativeListservices.getSrlNo(srlno));
		}
		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return "AMLNegativelist";
	}

	@RequestMapping(value = "createNegativeList", method = RequestMethod.POST)
	@ResponseBody
	public String createNegativeList(@RequestParam("formmode") String formmode,
			@ModelAttribute NegativeListEntity alertparam, Model md, HttpServletRequest rq) {

		String msg = negativeListservices.addNegativeList(alertparam, formmode);

//		if (msg.equals("Negative List Created Successfully")) {
//			//after saving the new  record increementing the value by 1 for next record to insert
//			negativeListservices.updateNegative_list_Num();
//		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return msg;

	}

	/*************************************
	 * List Management -----> Customized List ------>Negative List ends
	 ****************************************/

	/*************************************
	 * List Management -----> Customized List ------>Duplication list Starts
	 ****************************************/

	@RequestMapping(value = "AMLduplication", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLduplication(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLduplication");
			md.addAttribute("menuname", "Black List");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("singledetail", new BlackListEntity());

			md.addAttribute("Blacklist", blackListRepository.findAllRandom(PageRequest.of(currentPage, pageSize)));

		}
		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("custlistflag", "custlistflag");

		return "AMLDuplication";
	}

	/*************************************
	 * List Management -----> Customized List ------>Duplication list ends
	 ****************************************/
	/*************************************
	 * Risk Management -----> Risk Categorization------>Customer KYC Starts
	 ****************************************/

	@RequestMapping(value = "AMLCustomerKYC", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCustomerKYC(@RequestParam(required = false) String formmode,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(value = "docId", required = false) String docId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@ModelAttribute BAML_Doc_Hist_Table bAML_Doc_Hist_Table,
			@ModelAttribute BAML_Kyc_His_Table bAML_Kyc_His_Table,
			@ModelAttribute BAML_Risk_History_Table bAML_Risk_History_Table, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLCustomerKYC");
			md.addAttribute("menuname", "Customer KYC");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			// md.addAttribute("CustomerKYC",
			// baml_kyc_rep.findAllCustom(PageRequest.of(currentPage, pageSize)));
			try {
				if (name != null && !name.isEmpty()) {

					md.addAttribute("CustomerKYC",
							baml_kyc_rep.getlistByFIRSTname(PageRequest.of(currentPage, pageSize), name + '%'));
				} else {
					md.addAttribute("CustomerKYC", baml_kyc_rep.findAllCustom(PageRequest.of(currentPage, pageSize)));

				}
			} catch (Exception e) {
				md.addAttribute("CustomerKYC", baml_kyc_rep.findAllCustom(PageRequest.of(currentPage, pageSize)));

			}

		} else if (formmode.equals("maintain")) {

			md.addAttribute("formmode", "maintain");
			md.addAttribute("formmode", formmode);

			md.addAttribute("menu", "AMLCustomerKYC");
			md.addAttribute("menuname", "Customer KYC Maintenance");
			md.addAttribute("CustomerName", CustName);
			md.addAttribute("CustID", CUSTID);
			md.addAttribute("CustomerKYC1", baml_doc_hist_rep.findByCustId(CUSTID));
			md.addAttribute("CustomerKYC", baml_kyc_rep.getCustomer(CUSTID));

		} else if (formmode.equals("submit")) {

			md.addAttribute("formmode", "maintain");
			md.addAttribute("formmode", formmode);

			md.addAttribute("menu", "AMLCustomerKYC");
			md.addAttribute("menuname", "Customer KYC Maintenance");
			md.addAttribute("CustomerName", CustName);
			md.addAttribute("CustID", CUSTID);
			md.addAttribute("CustomerKYC1", baml_doc_hist_rep.findByCustId(CUSTID));
			md.addAttribute("CustomerKYC", baml_kyc_rep.getCustomer(CUSTID));

			// md.addAttribute("KycMaintenance", new KycHistory());
			String msg = kycServices.getDetails(bAML_Doc_Hist_Table, bAML_Kyc_His_Table, bAML_Risk_History_Table,
					formmode);

			md.addAttribute("KycMaintenance", msg);
			return msg;

			// md.addAttribute("RefNo", negativeListservices.getNextRefValue());
			// md.addAttribute("CustomerKYC",
			// cmgMaster.findAllCustom(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("History")) {
			md.addAttribute("menu", "AMLCustomerKYC");
			md.addAttribute("menuname", "Customer KYC History");
			md.addAttribute("formmode", "History"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("CustomerName", CustName);
			md.addAttribute("CustID", CUSTID);
			// md.addAttribute("docId", docId);
			md.addAttribute("History", baml_kyc_his_rep.findByCustomId(CUSTID));

		} else if (formmode.equals("risk")) {

			md.addAttribute("formmode", "maintain");
			md.addAttribute("formmode", formmode);

			md.addAttribute("menu", "AMLCustomerKYC");
			md.addAttribute("menuname", "Risk Profiling History");
			md.addAttribute("CustomerName", CustName);
			md.addAttribute("CustID", CUSTID);
			md.addAttribute("Risk", baml_risk_hist_rep.findByCustomId(CUSTID));

		}

		md.addAttribute("riskmgntflag", "riskmgntflag");
		md.addAttribute("riskcatflag", "riskcatflag");

		return "AMLCustomerKYC";
	}

	@RequestMapping(value = "AMLCustomerKYCAdd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String AMLCustomerKYCAdd(@RequestParam(required = false) String formmode,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(value = "SRLNO", required = false) String SRLNO,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@ModelAttribute BAML_Doc_Hist_Table bAML_Doc_Hist_Table,
			@ModelAttribute BAML_Kyc_His_Table bAML_Kyc_His_Table,
			@ModelAttribute BAML_Risk_History_Table bAML_Risk_History_Table, Model md, HttpServletRequest req,
			@RequestParam MultipartFile file) throws IOException {

		byte[] byteArr = file.getBytes();
		// kycHistory.setDoc_image(byteArr);
		bAML_Doc_Hist_Table.setDocimage(byteArr);
		// md.addAttribute("AMLRoleMenu", kycServices.getDoc_image(SRLNO));
		// kycHistory.getDoc_image(byteArr);
		// 1 means first image
		String msg = kycServices.getDetails(bAML_Doc_Hist_Table, bAML_Kyc_His_Table, bAML_Risk_History_Table, formmode);

		md.addAttribute("KycMaintenance", msg);
		return msg;

	}

	@RequestMapping(value = "Risk001", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String AMLCustomerKY(@RequestParam(required = false) String formmode,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(value = "SRLNO", required = false) String SRLNO,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@ModelAttribute BAML_Doc_Hist_Table bAML_Doc_Hist_Table,
			@ModelAttribute BAML_Kyc_His_Table bAML_Kyc_His_Table,
			@ModelAttribute BAML_Risk_History_Table bAML_Risk_History_Table, Model md, HttpServletRequest req)
			throws IOException {

		String msg = kycServices.getDetails(bAML_Doc_Hist_Table, bAML_Kyc_His_Table, bAML_Risk_History_Table, formmode);

		md.addAttribute("KycMaintenance", msg);
		return msg;

	}

	/*************************************
	 * Risk Management -----> Risk Categorization------>Customer KYC Ends
	 ****************************************/
	/*************************************
	 * Risk Management -----> Risk Categorization------>Customer Risk Profiling
	 * Starts
	 ****************************************/

	@RequestMapping(value = "AMLCustomerRiskProfiling", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCustomerRiskProfiling(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLCustomerRiskProfiling");
			md.addAttribute("menuname", "Customer Risk Profiling");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("CustomerProfiling", cmgMaster.findAll(PageRequest.of(currentPage, pageSize)));
		} else {

			md.addAttribute("formmode", formmode);

		}
		md.addAttribute("riskmgntflag", "riskmgntflag");
		md.addAttribute("riskcatflag", "riskcatflag");

		return "AMLCustomerRiskProfiling";
	}

	@RequestMapping(value = "AMLCustomerAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCustomerAccount(@RequestParam(required = false) String formmode,
			@RequestParam(value = "custId", required = false) String custId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "AMLCustomerAccount");
		md.addAttribute("menuname", "Customer Accounts");
		md.addAttribute("accSummeryList",
				ACCTMaster.findAllCustIdCustom(custId, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("riskmgntflag", "riskmgntflag");
		md.addAttribute("riskcatflag", "riskcatflag");

		return "AMLCustomerAccount";
	}

	/*************************************
	 * Risk Management -----> Risk Categorization------>Customer Risk Profiling Ends
	 ****************************************/

	/*************************************
	 * Risk Management -----> Risk Categorization------>Customer Risk Rating Starts
	 ****************************************/
	@RequestMapping(value = "AMLRiskRating", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLRiskRating(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLRiskRating");
			md.addAttribute("menuname", "Customer Risk Rating");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("CustomerProfiling", cmgMaster.findAll(PageRequest.of(currentPage, pageSize)));
		} else {

			md.addAttribute("formmode", formmode);

		}
		md.addAttribute("riskmgntflag", "riskmgntflag");
		md.addAttribute("riskcatflag", "riskcatflag");

		return "AMLRiskRating";
	}

	@RequestMapping(value = "AMLRiskRating1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLRiskRating1(@RequestParam(required = false) String formmode,
			@RequestParam(value = "custId", required = false) String custId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "AMLRiskRating1");
		md.addAttribute("menuname", "Customer Accounts");
		md.addAttribute("Riskrating", cmgMaster.findAllCustIdCustom(custId, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("riskmgntflag", "riskmgntflag");
		md.addAttribute("riskcatflag", "riskcatflag");

		return "AMLRiskRating1";
	}

	/*************************************
	 * Risk Management -----> Risk Categorization------>Customer Risk Rating Ends
	 ****************************************/
	@RequestMapping(value = "AMLCustmaster1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCustmaster1(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menuname", "Customer Master");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("Custmaster1", cmgMaster.findAll(PageRequest.of(currentPage, pageSize)));
		} else {

			md.addAttribute("formmode", formmode);

		}
		md.addAttribute("monitoringflag", "monitoringflag");
		md.addAttribute("menu", "AMLTransMonitoring");

		return "AMLCustmaster1";
	}

	@RequestMapping(value = "AMLCustmaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCustmaster(@RequestParam(required = false) String formmode,
			@RequestParam(value = "custId", required = false) String custId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("Custmaster", custmasterservices.getCustId(custId));
		md.addAttribute("monitoringflag", "monitoringflag");
		md.addAttribute("menu", "AMLTransMonitoring");

		return "AMLCustmaster";
	}

	/************************ T2 Current *********************/

	@RequestMapping("/t2current")
	public String ResetPassWord1() {

		return "ReportT2Current.html";

	}

	@RequestMapping(value = "AMLReports", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLrep(@RequestParam(value = "reportid") String reportid, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		String reportvalue = null;

		/********* date calculation ********/
		Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
		int quarter = (calendar.get(Calendar.MONTH) / 3) + 1;

		int year = calendar.get(Calendar.YEAR);

		switch (quarter) {

		case 1:

			md.addAttribute("fromDate", "01-OCT-" + (year - 1));
			md.addAttribute("toDate", "31-DEC-" + (year - 1));
			break;

		case 2:

			md.addAttribute("fromDate", "01-JAN-" + (year));
			md.addAttribute("toDate", "31-MAR-" + (year));
			break;

		case 3:

			md.addAttribute("fromDate", "01-APR-" + (year));
			md.addAttribute("toDate", "30-JUN-" + (year));
			break;

		case 4:

			md.addAttribute("fromDate", "01-JUL-" + (year));
			md.addAttribute("toDate", "30-SEP-" + (year));
			break;

		default:
		}

		/********* date calculation ********/

		switch (reportid) {

		case "t1previous":
			reportvalue = "T1 Previous - Products, Services & Delivery Channels";
			break;
		case "t4":
			reportvalue = "	PROFILE OF Profile of NEW customers onboarded and applications rejected";
			break;

		case "t1current":
			reportvalue = "T1 Current - Products, Services & Delivery Channels";
			break;

		case "t2previous":
			reportvalue = "T2 Previous - NBDTI's internal AML/CFT customer rating ";
			break;

		case "t2current":
			reportvalue = "T2 Current - NBDTI's internal AML/CFT customer rating ";
			break;

		case "t5":
			reportvalue = "Customer risk migration ";
			break;

		case "t6":
			reportvalue = "T6 Current - Policy for frequency of KYC and CDD reviews of existing customers";
			break;

		case "t7":
			reportvalue = "Customer KYC- CDD Review";
			break;

		case "t8":
			reportvalue = "Transaction details by customer type";
			break;

		case "t9":
			reportvalue = "Domestic outward remittances by customer risk categories";
			break;

		case "t10":
			reportvalue = "Domestic inward remittances by customer risk categories";
			break;

		case "t11":
			reportvalue = "T11 - Transactions terminated or not processed due to concerns about CDD";
			break;

		case "t12":
			reportvalue = "Individual cash deposit transactions during the quarter -Total";
			break;

		case "t13":
			reportvalue = "Individual cash withdrawal transactions during the quarter -Total";
			break;

		case "t14":
			reportvalue = "Cheque inward transactions during the quarter ";
			break;

		case "t15":
			reportvalue = "Cheque outward transactions during the quarter ";
			break;

		case "t17":
			reportvalue = "Transaction Monitoring Systems (pick from appropriate drop down options (blue cells) where available)";
			break;

		case "t18":
			reportvalue = "Distribution channels";
			break;

		case "t19":
			reportvalue = "Table 19:  Types of Hits (pre-transaction) and Alerts (post-transaction)";
			break;

		case "t20":
			reportvalue = "Suspicion Reports";
			break;

		case "t21":
			reportvalue = "Report 21: Inactive and dormant accounts";
			break;

		case "t22":
			reportvalue = "Table 22:  Abandoned fund accounts";
			break;

		case "t24":
			reportvalue = "Internal audit AML-CFT reviews/checks";
			break;

		case "t25":
			reportvalue = " Application of NBDTI's AML-CFT risk management framework to";
			break;

		case "t27previous":
			reportvalue = " Previous Quarter: Transactions from Non-Resident Customers ";
			break;

		case "t27current":
			reportvalue = " Current Quarter: Transactions from Non-Resident Customers ";
			break;

		}
		md.addAttribute("reportvalue", reportvalue);
		md.addAttribute("reportid", reportid);
		md.addAttribute("menu", reportid);
		String domainid = (String) req.getSession().getAttribute("DOMAINID");

		md.addAttribute("reportsflag", "reportsflag");

		return "AMLReports";
	}

	@RequestMapping(value = "AMLAccountMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAccountMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AccountMaster");
			md.addAttribute("menuname", "Account Master");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("AccountMaster", ACCTMaster.findAll(PageRequest.of(currentPage, pageSize)));
			/*
			 * md.addAttribute("CustomerProfiling",
			 * CMGrepository.findAll(PageRequest.of(currentPage, pageSize)));
			 */ } else {

			md.addAttribute("formmode", formmode);

		}
		return "AMLAccountMaster";
	}

	@RequestMapping(value = "AMLAccountMaster1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAccountMaster1(@RequestParam(required = false) String formmode,
			@RequestParam(value = "acid", required = false) String acid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("Gam", custmasterservices.getgamAcid(acid));

		return "AMLAccountMaster1";
	}

	@RequestMapping(value = "AMLAccountLedger1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAccountLedger1(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("monitoringflag", "monitoringflag");
		md.addAttribute("menu", "AMLTransMonitoring");

		md.addAttribute("formmode", "list");
		md.addAttribute("ledger", custmasterservices.gethtdAcid(acid));
		return "AMLAccountLedger1";
	}

	/*************************************
	 * Inquiry -----> AMLRemittanceTransaction starts
	 ****************************************/
	@RequestMapping(value = "AMLRemittanceTransaction", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLRemittanceTransaction(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "AMLRemittanceTransaction");
			md.addAttribute("menuname", "Transaction Master");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("TransactionMaster", ACCTMaster.findAll(PageRequest.of(currentPage, pageSize)));

		}
		md.addAttribute("monitoringflag", "monitoringflag");

		return "AMLRemittanceTransaction";
	}

	/*************************************
	 * Inquiry -----> AMLRemittanceTransaction ends
	 ****************************************/

	@RequestMapping(value = "AMLTran1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLTran1(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLTran1";
	}

	/*************************************
	 * Inquiry -----> AMLRemittanceTransaction ends
	 ****************************************/

	@RequestMapping(value = "AMLCaseManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCM(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("casemanagementflag", "casemanagementflag");

		return "AMLCaseManagement";
	}

	@RequestMapping(value = "AMLCMCustomers", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCMCUST(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("casemanagementflag", "casemanagementflag");

		return "AMLCMCustomers";
	}

	@RequestMapping(value = "AMLCMAccounts", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCMAccounts(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMAccounts";
	}

	@RequestMapping(value = "AMLCMDocuments", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLDoc(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMDocuments";
	}

	@RequestMapping(value = "AMLCMAddress", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAddress(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMAddress";
	}

	@RequestMapping(value = "AMLCMKyc", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLKyc(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMKyc";
	}

	@RequestMapping(value = "AMLCMRating", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLRating(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMRating";
	}

	@RequestMapping(value = "AMLCMHistory", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLHistory(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMHistory";
	}

	@RequestMapping(value = "AMLCMSourceIncome", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLSrcIncome(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMSourceIncome";
	}

	@RequestMapping(value = "AMLCMEmployment", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLEMPT(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMEmployment";
	}

	@RequestMapping(value = "AMLCMTransactions", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLTransactions(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMTransactions";
	}

	@RequestMapping(value = "AMLCMAccHistory", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCMAccHistory(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMAccHistory";
	}

	@RequestMapping(value = "AMLCMLedger", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCMLedger(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMLedger";
	}

	@RequestMapping(value = "AMLCMTurnover", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCMTurnover(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMTurnover";
	}

	@RequestMapping(value = "AMLCMAccountTransaction", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCMAccountTransaction(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMAccountTransaction";
	}

	@RequestMapping(value = "AMLCMHome", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCMHome(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranId", required = false) String tranId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "list");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");
		md.addAttribute("transaction",
				transactionMasterRepository.findAlltranIdCustom(tranId, PageRequest.of(currentPage, pageSize)));

		return "AMLCMHome";
	}

	/********************
	 * ***************** AML monitoring reports -----> AMLCustBlackListingReport
	 * starts
	 * 
	 * @throws ParseException
	 ****************************************/
	@RequestMapping(value = "AMLCustBlackListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custBlackListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String formmode_corp,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "Individual Black List Report");
		md.addAttribute("formmode", "formmode_Ind"); // to set which form - valid values are "edit" , "add" & "list"

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		String fromDAte = null;
		String toDAte = null;

		if (from_Date != null && !from_Date.isEmpty()) {
			Date ConDateFromdate = dateFormat1.parse(from_Date);
			String strDate2 = formatter1.format(ConDateFromdate);
			fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

			Date ConToDate = dateFormat1.parse(to_date);
			String strDate1 = formatter1.format(ConToDate);
			toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		}
		md.addAttribute("individualBlackList", baml_cust_blacklist_rpt_repository
				.findAllCustBlackListIndReport(fromDAte, toDAte, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustBlackListingReport";
	}

	@RequestMapping(value = "AMLCustCorpBlackListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custCorpBlackListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");

		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menu1", "Corporate Black List Report");
		md.addAttribute("formmode", "formmode_Corp"); // to set which form - valid values are "edit" , "add" & "list"

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		String fromDAte = null;
		String toDAte = null;

		if (from_Date != null && !from_Date.isEmpty()) {
			Date ConDateFromdate = dateFormat1.parse(from_Date);
			String strDate2 = formatter1.format(ConDateFromdate);
			fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

			Date ConToDate = dateFormat1.parse(to_date);
			String strDate1 = formatter1.format(ConToDate);
			toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		}
		md.addAttribute("corporateBlackList", baml_cust_blacklist_rpt_repository
				.findAllCustBlackListCorpReport(fromDAte, toDAte, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustBlackListingReport";
	}

	/*************************************
	 * AML monitoring reports -----> AMLCustBlackListingReport ends
	 ****************************************/

	/********************
	 * ***************** AML monitoring reports -----> AMLCustNegativeListingReport
	 * starts
	 * 
	 * @throws ParseException
	 ****************************************/
	@RequestMapping(value = "AMLCustNegativeListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custNegativeListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustNegativeListingReport");
		md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		String fromDAte = null;
		String toDAte = null;

		if (from_Date != null && !from_Date.isEmpty()) {
			Date ConDateFromdate = dateFormat1.parse(from_Date);
			String strDate2 = formatter1.format(ConDateFromdate);
			fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

			Date ConToDate = dateFormat1.parse(to_date);
			String strDate1 = formatter1.format(ConToDate);
			toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		}

		md.addAttribute("customerBlackList", baml_cust_blacklist_rpt_repository.findAllCustNegativelistReport(fromDAte,
				toDAte, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

//			md.addAttribute("customerBlackList", negativeListRepository.paramlistforReport(PageRequest.of(currentPage, pageSize)));

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustNegativeListingReport";
	}

	/*************************************
	 * AML monitoring reports -----> AMLCustNegativeListingReport ends
	 ****************************************/

	/*************************************
	 * AML monitoring reports -----> AMLCustWhiteListingReport ends
	 ****************************************/

	/********************
	 * *****************AML monitoring reports -----> AMLCustPepListingReport starts
	 * 
	 * @throws ParseException
	 ****************************************/
	@RequestMapping(value = "AMLCustPepListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custpepListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "CustPep ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

			String fromDAte = null;
			String toDAte = null;

			if (from_Date != null && !from_Date.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(from_Date);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(to_date);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}

			md.addAttribute("customerBlackList", baml_cust_pep_rpt_repository.findAllCustPEPListReport(fromDAte, toDAte,
					PageRequest.of(currentPage, pageSize)));

			md.addAttribute("fromdate", from_Date);
			md.addAttribute("todate", to_date);

		}

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustPepListingReport";
	}

	/*************************************
	 * AML monitoring reports -----> AMLCustPepListingReport ends
	 ****************************************/

	/********************
	 * ***************** AML monitoring reports-----> AMLCustPepListingReport starts
	 * 
	 * @throws ParseException
	 ****************************************/
	@RequestMapping(value = "AMLCustHNWIListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custhnwiListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "Custhnwi ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

			String fromDAte = null;
			String toDAte = null;

			if (from_Date != null && !from_Date.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(from_Date);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(to_date);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}
			md.addAttribute("customerBlackList", baml_cust_hnwi_rpt_repository.findAllCustHNWIListReport(fromDAte,
					toDAte, PageRequest.of(currentPage, pageSize)));
		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustHNWIListingReport";
	}

	/*************************************
	 * AML monitoring reports -----> AMLCustPepListingReport ends
	 ****************************************/

	/****************************************************************
	 * AML monitoring reports -----> AMLCustUNSCReport ends
	 * 
	 * @throws ParseException
	 ****************************************************************/

	@RequestMapping(value = "AMLCustUNSCReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custUNSCReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "UNSC ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

			String fromDAte = null;
			String toDAte = null;

			if (from_Date != null && !from_Date.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(from_Date);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(to_date);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}

			md.addAttribute("customerBlackList", baml_cust_hnwi_rpt_repository.findAllCustUNSClistReport(fromDAte,
					toDAte, PageRequest.of(currentPage, pageSize)));
//			md.addAttribute("customerBlackList", cmgMaster.findAll(PageRequest.of(currentPage, pageSize)));
		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustUNSCReport";
	}

	/*************************************
	 * AML monitoring reports -----> AMLCustUNSCReport ends
	 ****************************************/

	/****************************************************************
	 * AML Daily reports -----> AMLCustUNSCReport ends
	 * 
	 * @throws ParseException
	 ****************************************************************/

	@RequestMapping(value = "AMLDaily_Report_Loan", method = { RequestMethod.GET, RequestMethod.POST })
	public String daily_Report_Loan(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menu", "Cust Loan ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

			String fromDAte = null;
			String toDAte = null;

			if (from_Date != null && !from_Date.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(from_Date);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(to_date);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}
			Session hs1 = sessionFactory.getCurrentSession();
			hs1.flush();
			String query1 = "delete from BAML_DAILY_LOAN_BLACKLIST_RPT_TEMP_TABLE";
			hs1.createNativeQuery(query1);
			hs1.clear();
			hs1.flush();

			md.addAttribute("customerBlackList", baml_daily_loan_blacklist_rt_repository
					.findAllCustIdfordailyLoanReport(fromDAte, toDAte, PageRequest.of(currentPage, pageSize)));

			if (from_Date != null && !from_Date.isEmpty()) {
				Session hs = sessionFactory.getCurrentSession();

				String query = "select distinct * from BAML_DAILY_LOAN_BLACKLIST_RPT_TEMP_TABLE";
				List<BAML_Daily_Loan_Blacklist_RT_Entity> lst = new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>();
				Query<Object[]> qr;
				qr = hs.createNativeQuery(query);
				List<Object[]> result = qr.getResultList();

				hs.clear();
				for (Object[] a : result) {
					BAML_Daily_Loan_Blacklist_RT_Entity info = new BAML_Daily_Loan_Blacklist_RT_Entity();
//					BigDecimal sl = (BigDecimal) a[0];

					String cust_id = (String) a[0];
					info.setCust_id(cust_id);

					String cif_id = (String) a[1];
					info.setCif(cif_id);

					String name = (String) a[2];
					info.setName(name);

					String nid = (String) a[3];
					info.setNid(nid);

					String risk_category = (String) a[4];
					info.setRisk_category(risk_category);

					String occupation = (String) a[5];
					info.setRisk_category(occupation);

					Date tran_date = (Date) a[6];
					info.setTran_date(tran_date);

//					BigDecimal tran_amount = (BigDecimal) a[7];
//					info.setTran_amount(tran_amount);

					String black_list_ind_name = (String) a[8];
					info.setBlack_list_ind_name(black_list_ind_name);

					String black_list_ind_Nid = (String) a[9];
					info.setBlack_list_ind_nid(black_list_ind_Nid);

					String corp_name = (String) a[10];
					info.setBlack_list_corp_name(corp_name);

					String corp_nid = (String) a[11];
					info.setBlack_list_corp_nid(corp_nid);

					String pep_name = (String) a[12];
					info.setPep_name(pep_name);

					String pep_occupation = (String) a[13];
					info.setPep_occupation(pep_occupation);

					String unsc_name = (String) a[14];
					info.setUnsc_name(unsc_name);

					String unsc_country = (String) a[15];
					info.setUnsc_country(unsc_country);

					String hniw_name = (String) a[16];
					info.setHnwi_name(hniw_name);
					lst.add(info);

				}
				md.addAttribute("customerBlackListAlert", lst);

			} else {
				md.addAttribute("customerBlackListAlert", new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>());
			}
		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("amldailyreport", "amldailyreport");

		return "AMLDaily_Report_Loan";
	}

	/*************************************
	 * AML monitoring reports -----> AMLDaily_Report_Loan ends
	 ****************************************/

	/****************************************************************
	 * AML Daily reports -----> AML rss ends
	 * 
	 * @throws ParseException
	 ****************************************************************/

	@RequestMapping(value = "AMLDaily_Report_RSS", method = { RequestMethod.GET, RequestMethod.POST })
	public String daily_Report_RSS(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "Custhnwi ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String fromDAte = null;
			String toDAte = null;

			if (from_Date != null && !from_Date.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(from_Date);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(to_date);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}
			Session hs1 = sessionFactory.getCurrentSession();
			hs1.flush();
			String query1 = "delete from BAML_DAILY_LOAN_BLACKLIST_RPT_TEMP_TABLE";
			hs1.createNativeQuery(query1);
			hs1.clear();
			hs1.flush();

			md.addAttribute("customerBlackList", baml_daily_loan_blacklist_rt_repository
					.findAllCustIdfordailyRssReport(fromDAte, toDAte, PageRequest.of(currentPage, pageSize)));

			if (from_Date != null && !from_Date.isEmpty()) {
				Session hs = sessionFactory.getCurrentSession();

				String query = "select distinct * from BAML_DAILY_LOAN_BLACKLIST_RPT_TEMP_TABLE";
				List<BAML_Daily_Loan_Blacklist_RT_Entity> lst = new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>();
				Query<Object[]> qr;
				qr = hs.createNativeQuery(query);
				List<Object[]> result = qr.getResultList();

				hs.clear();
				for (Object[] a : result) {
					BAML_Daily_Loan_Blacklist_RT_Entity info = new BAML_Daily_Loan_Blacklist_RT_Entity();

					String cust_id = (String) a[0];
					info.setCust_id(cust_id);

					String cif_id = (String) a[1];
					info.setCif(cif_id);

					String name = (String) a[2];
					info.setName(name);

					String nid = (String) a[3];
					info.setNid(nid);

					String risk_category = (String) a[4];
					info.setRisk_category(risk_category);

					String occupation = (String) a[5];
					info.setRisk_category(occupation);

					Date tran_date = (Date) a[6];
					info.setTran_date(tran_date);

//					BigDecimal tran_amount = (BigDecimal) a[7];
//					info.setTran_amount(tran_amount);

					String black_list_ind_name = (String) a[8];
					info.setBlack_list_ind_name(black_list_ind_name);

					String black_list_ind_Nid = (String) a[9];
					info.setBlack_list_ind_nid(black_list_ind_Nid);

					String corp_name = (String) a[10];
					info.setBlack_list_corp_name(corp_name);

					String corp_nid = (String) a[11];
					info.setBlack_list_corp_nid(corp_nid);

					String pep_name = (String) a[12];
					info.setPep_name(pep_name);

					String pep_occupation = (String) a[13];
					info.setPep_occupation(pep_occupation);

					String unsc_name = (String) a[14];
					info.setUnsc_name(unsc_name);

					String unsc_country = (String) a[15];
					info.setUnsc_country(unsc_country);

					String hniw_name = (String) a[16];
					info.setHnwi_name(hniw_name);
					lst.add(info);

				}
				md.addAttribute("customerBlackListAlert", lst);

			} else {
				md.addAttribute("customerBlackListAlert", new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>());
			}
		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("amldailyreport", "amldailyreport");

		return "AMLDaily_Report_RSS";
	}

	/*************************************
	 * AML monitoring reports -----> AMLDaily_Report_RSS ends
	 ****************************************/

	/****************************************************************
	 * AML Daily reports -----> AMLDaily_Report_Deposiit_Daily
	 * 
	 * @throws ParseException
	 ****************************************************************/

	@RequestMapping(value = "AMLDaily_Report_Deposit_Daily", method = { RequestMethod.GET, RequestMethod.POST })
	public String daily_Report_Deposit_Daily(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "Custhnwi ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String fromDAte = null;
			String toDAte = null;

			if (from_Date != null && !from_Date.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(from_Date);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(to_date);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}
			Session hs1 = sessionFactory.getCurrentSession();
			hs1.flush();
			String query1 = "delete from BAML_DAILY_LOAN_BLACKLIST_RPT_TEMP_TABLE";
			hs1.createNativeQuery(query1);
			hs1.clear();
			hs1.flush();

			md.addAttribute("customerBlackList", baml_daily_loan_blacklist_rt_repository
					.findAllCustIdfordailyDepositReport(fromDAte, toDAte, PageRequest.of(currentPage, pageSize)));

			if (from_Date != null && !from_Date.isEmpty()) {
				Session hs = sessionFactory.getCurrentSession();

				String query = "select distinct * from BAML_DAILY_LOAN_BLACKLIST_RPT_TEMP_TABLE";
				List<BAML_Daily_Loan_Blacklist_RT_Entity> lst = new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>();
				Query<Object[]> qr;
				qr = hs.createNativeQuery(query);
				List<Object[]> result = qr.getResultList();

				hs.clear();
				for (Object[] a : result) {
					BAML_Daily_Loan_Blacklist_RT_Entity info = new BAML_Daily_Loan_Blacklist_RT_Entity();

					String cust_id = (String) a[0];
					info.setCust_id(cust_id);

					String cif_id = (String) a[1];
					info.setCif(cif_id);

					String name = (String) a[2];
					info.setName(name);

					String nid = (String) a[3];
					info.setNid(nid);

					String risk_category = (String) a[4];
					info.setRisk_category(risk_category);

					String occupation = (String) a[5];
					info.setRisk_category(occupation);

					Date tran_date = (Date) a[6];
					info.setTran_date(tran_date);

//					BigDecimal tran_amount = (BigDecimal) a[7];
//					info.setTran_amount(tran_amount);

					String black_list_ind_name = (String) a[8];
					info.setBlack_list_ind_name(black_list_ind_name);

					String black_list_ind_Nid = (String) a[9];
					info.setBlack_list_ind_nid(black_list_ind_Nid);

					String corp_name = (String) a[10];
					info.setBlack_list_corp_name(corp_name);

					String corp_nid = (String) a[11];
					info.setBlack_list_corp_nid(corp_nid);

					String pep_name = (String) a[12];
					info.setPep_name(pep_name);

					String pep_occupation = (String) a[13];
					info.setPep_occupation(pep_occupation);

					String unsc_name = (String) a[14];
					info.setUnsc_name(unsc_name);

					String unsc_country = (String) a[15];
					info.setUnsc_country(unsc_country);

					String hniw_name = (String) a[16];
					info.setHnwi_name(hniw_name);
					lst.add(info);

				}
				md.addAttribute("customerBlackListAlert", lst);

			} else {
				md.addAttribute("customerBlackListAlert", new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>());
			}

		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("amldailyreport", "amldailyreport");

		return "AMLDaily_Report_Deposit_Daily";
	}

	/*************************************
	 * AML monitoring reports -----> AMLDaily_Report_Deposiit_Daily
	 ****************************************/

	/****************************************************************
	 * AML Daily reports -----> AMLDaily_Report_Deposiit_Daily
	 * 
	 * @throws ParseException
	 ****************************************************************/

	@RequestMapping(value = "AMLDaily_Report_Cash_Tran", method = { RequestMethod.GET, RequestMethod.POST })
	public String daily_Report_Cash_Tran(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(value = "Mode", required = false) String Mode, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menu", "Custhnwi ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String fromDAte = null;
			String toDAte = null;

			if (from_Date != null && !from_Date.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(from_Date);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(to_date);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}
//			Session hs1 = sessionFactory.getCurrentSession();
//			hs1.flush();
//			String query1 = "delete from BAML_DAILY_LOAN_BLACKLIST_RPT_TEMP_TABLE";
//			hs1.createNativeQuery(query1);
//			hs1.clear();
//			hs1.flush();

			if (Mode != null) {
				md.addAttribute("customerBlackList", baml_daily_cash_blacklist_rt_repository
						.findAllCustIdfordailyCASHReportPage(fromDAte, toDAte, PageRequest.of(currentPage, pageSize)));
			} else {
				md.addAttribute("customerBlackList", baml_daily_cash_blacklist_rt_repository
						.findAllCustIdfordailyCASHReport(fromDAte, toDAte, PageRequest.of(currentPage, pageSize)));
			}

//			if (from_Date != null && !from_Date.isEmpty()) {
//				Session hs = sessionFactory.getCurrentSession();
//
//				String query = "select distinct * from BAML_DAILY_CASH_BLACKLIST_RPT_TEMP_TABLE";
//				List<BAML_Daily_Loan_Blacklist_RT_Entity> lst = new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>();
//				Query<Object[]> qr;
//				qr = hs.createNativeQuery(query);
//				List<Object[]> result = qr.getResultList();
//
//				hs.clear();
//				for (Object[] a : result) {
//					BAML_Daily_Loan_Blacklist_RT_Entity info = new BAML_Daily_Loan_Blacklist_RT_Entity();
//
//					String cust_id = (String) a[0];
//					info.setCust_id(cust_id);
//
//					String cif_id = (String) a[1];
//					info.setCif(cif_id);
//
//					String name = (String) a[2];
//					info.setName(name);
//
//					String nid = (String) a[3];
//					info.setNid(nid);
//
//					String risk_category = (String) a[4];
//					info.setRisk_category(risk_category);
//
//					String occupation = (String) a[5];
//					info.setRisk_category(occupation);
//
//					Date tran_date = (Date) a[6];
//					info.setTran_date(tran_date);
//
////					BigDecimal tran_amount = (BigDecimal) a[7];
////					info.setTran_amount(tran_amount);
//
//					String black_list_ind_name = (String) a[8];
//					info.setBlack_list_ind_name(black_list_ind_name);
//
//					String black_list_ind_Nid = (String) a[9];
//					info.setBlack_list_ind_nid(black_list_ind_Nid);
//
//					String corp_name = (String) a[10];
//					info.setBlack_list_corp_name(corp_name);
//
//					String corp_nid = (String) a[11];
//					info.setBlack_list_corp_nid(corp_nid);
//
//					String pep_name = (String) a[12];
//					info.setPep_name(pep_name);
//
//					String pep_occupation = (String) a[13];
//					info.setPep_occupation(pep_occupation);
//
//					String unsc_name = (String) a[14];
//					info.setUnsc_name(unsc_name);
//
//					String unsc_country = (String) a[15];
//					info.setUnsc_country(unsc_country);
//
//					String hniw_name = (String) a[16];
//					info.setHnwi_name(hniw_name);
//					lst.add(info);
//
//				}
//				md.addAttribute("customerBlackListAlert", lst);
//
//			} else {
//				md.addAttribute("customerBlackListAlert", new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>());
//			}

		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("amldailyreport", "amldailyreport");

		return "AMLDaily_Report_Daily_Cash_Tran";
	}

	/*************************************
	 * AML monitoring reports -----> AMLDaily_Report_Deposiit_Daily
	 ****************************************/

	/*************************************
	 * Inquiry -----> AMLCustInquiry - hyperlink to case management starts
	 ****************************************/
	@RequestMapping(value = "AMLCustInquiry1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCustInquiry1(@RequestParam(required = false) String formmode,
			@RequestParam(value = "custId", required = false) String custId,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "general");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Customer Master");

		md.addAttribute("Occupation", cmgMaster.getOccupation(custId));
		md.addAttribute("City", cmgMaster.getcitycode(custId));
		md.addAttribute("State", cmgMaster.getstatecode(custId));
		md.addAttribute("Country", cmgMaster.getcountrycode(custId));
		md.addAttribute("CountryResidence", cmgMaster.getcountryresidence(custId));
		md.addAttribute("Placeofbirth", cmgMaster.getplaceofbirth(custId));
		md.addAttribute("CustomerType", cmgMaster.getcustomertype(custId));
		md.addAttribute("Custmaster", cmgMaster.getCustomer(custId));
		md.addAttribute("DEPARTMENT", cmgMaster.getDepartment(custId));
		md.addAttribute("PAYSITE", cmgMaster.getPaysiteCode(custId));
		md.addAttribute("BANKBRANCH", cmgMaster.getBankBranch(custId));
		md.addAttribute("BANKNAME", cmgMaster.getBankName(custId));
		md.addAttribute("CUSTOMERSTATUS", cmgMaster.getCustomerStatus(custId));
		Date from = cmgMaster.getcustopendate(custId);

		Calendar firstCal = GregorianCalendar.getInstance();
		Calendar secondCal = GregorianCalendar.getInstance();
		Date dats = new Date();
		if (from == null) {
			firstCal.setTime(dats);
			secondCal.setTime(dats);

			secondCal.add(Calendar.DAY_OF_YEAR, 1 - firstCal.get(Calendar.DAY_OF_YEAR));
			int yearofvalue = secondCal.get(Calendar.YEAR) - firstCal.get(Calendar.YEAR);
			if (yearofvalue < '1') {
				md.addAttribute("YEAROFVALUE", yearofvalue + " Years");
			} else {
				md.addAttribute("YEAROFVALUE", yearofvalue + " Year");
			}

		} else {
			firstCal.setTime(from);
			secondCal.setTime(dats);

			secondCal.add(Calendar.DAY_OF_YEAR, 1 - firstCal.get(Calendar.DAY_OF_YEAR));
			int yearofvalue = secondCal.get(Calendar.YEAR) - firstCal.get(Calendar.YEAR);
			if (yearofvalue < '1') {
				md.addAttribute("YEAROFVALUE", yearofvalue + " Years");
			} else {
				md.addAttribute("YEAROFVALUE", yearofvalue + " Year");
			}

		}
		md.addAttribute("inquiryflag", "inquiryflag");

		return "CustomerGeneral";
	}

	/*************************************
	 * Inquiry -----> AMLCustInquiry - hyperlink to case management ends
	 ****************************************/

	/*************************************
	 * Inquiry -----> AMLAccountInquiry - hyperlink to case management starts
	 ****************************************/

	/*************************************
	 * Inquiry -----> AMLCustInquiry - hyperlink to case management ends
	 * 
	 * @throws ParseException
	 ****************************************/

	@RequestMapping(value = "CustAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustAccount(@RequestParam(required = false) String formmode,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "CustAccount");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("CustomerName", CustName);
		md.addAttribute("CustID", CUSTID);
		md.addAttribute("NumOfDeposit", ACCTMaster.getCountOfDeposit(CUSTID));
		md.addAttribute("Acctcount", ACCTMaster.countCustom(CUSTID));

		List<ACCT_MASTER> custMaster_List = new ArrayList<>();
		List<Object[]> lst_Objects = ACCTMaster.findAllCustIdCustom1(CUSTID);

		for (Object[] obj : lst_Objects) {
			ACCT_MASTER info = new ACCT_MASTER();

			info.setGl_sub_head_code(String.valueOf(obj[0]));
			info.setSchm_code(String.valueOf(obj[1]));
			info.setAcct_name(String.valueOf(obj[2]));
			info.setForacid(String.valueOf(obj[3]));
			if (String.valueOf(obj[4]) != "null") {
				info.setAcct_opn_date(new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(obj[4])));

			}
			if (String.valueOf(obj[5]) != "null") {
				info.setAcct_cls_date(new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(obj[5])));

			}
			info.setAcct_status(String.valueOf(obj[6]));
			if (String.valueOf(obj[7]) != "null") {
				info.setAcct_status_date(new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(obj[7])));

			}
			info.setMode_of_oper_code(String.valueOf(obj[8]));
			info.setClr_bal_amt(String.valueOf(obj[9]));
			if (String.valueOf(obj[10]) != "null") {
				info.setLast_tran_date(new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(obj[10])));
			}
			info.setAcid(String.valueOf(obj[11]));
			// info.setClr_bal_amt(String.valueOf(obj[0]));
			// info.setLast_frez_date(new
			// SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[1])));

			custMaster_List.add(info);
			md.addAttribute("AcctMaster", custMaster_List);
		}
		md.addAttribute("inquiryflag", "inquiryflag");
		return "CustomerGeneral";

	}

	@RequestMapping(value = "CustTransaction", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustTransaction(@RequestParam(required = false) String formmode,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		final Calendar cal = Calendar.getInstance();
		/* cal.add(Calendar.DATE, -1); */
		String date = dateFormat.format(cal.getTime());
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "CustTransaction");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("CustomerName", CustName);
		md.addAttribute("CustID", CUSTID);
		md.addAttribute("fromdate", date);
		md.addAttribute("todate", date);
		md.addAttribute("TranCount", TRANMaster.TranCount(CUSTID));
		md.addAttribute("AcctMastertran", TRANMaster.getAccountDetailsCust(CUSTID));
		md.addAttribute("inquiryflag", "inquiryflag");
		/*
		 * List<TRANTEMP> custMaster_List=new ArrayList<>();
		 * 
		 * List<Object[]> lst_Objects= TRANMaster.getAccountDetailsCust(CUSTID);
		 * 
		 * for(Object[] obj:lst_Objects) { TRANTEMP info=new TRANTEMP();
		 * info.setTran_date(new
		 * SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[0])));
		 * info.setForacid(String.valueOf(obj[1]));
		 * info.setAcct_name(String.valueOf(obj[2]));
		 * info.setPart_tran_type(String.valueOf(obj[3]));
		 * info.setTran_id((String.valueOf(obj[4])));
		 * info.setPart_tran_srl_num(String.valueOf(obj[5]));
		 * info.setCust_id(String.valueOf(obj[6]));
		 * info.setTran_crncy_code(String.valueOf(obj[7]));
		 * info.setTran_amt(String.valueOf(obj[8]));
		 * info.setTran_particular(String.valueOf(obj[9]));
		 * info.setModule_id(String.valueOf(obj[10]));
		 * info.setAcid(String.valueOf(obj[11]));
		 * 
		 * custMaster_List.add(info);
		 * 
		 * } md.addAttribute("AcctMastertran", custMaster_List);
		 * 
		 */
		return "CustomerGeneral";
	}

	@RequestMapping(value = "CustTransactiondate", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustTransactiondate(@RequestParam(required = false) String formmode,
			@RequestParam(value = "customer", required = false) String customer,
			@RequestParam(value = "custname", required = false) String custname,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "acid", required = false) String acid,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

		md.addAttribute("formmode", "CustTransaction");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("CustomerName", custname);
		md.addAttribute("CustID", customer);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);

		md.addAttribute("TranCount", TRANMaster.TranCount(fromdate1, todate1, customer));
		md.addAttribute("AcctMastertran", TRANMaster.getAccountDetailsCustdate(fromdate1, todate1, customer));
		md.addAttribute("inquiryflag", "inquiryflag");

		return "CustomerGeneral";
	}

	@RequestMapping(value = "AMLAccountInquiry1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAccountInquiry1(@RequestParam(required = false) String formmode,
			@RequestParam(value = "acctnum", required = false) String acctnum,
			@RequestParam(value = "acid", required = false) String acid,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "AccountGeneral");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("ACCNUM", acctnum);
		md.addAttribute("CustID", CUSTID);
		md.addAttribute("acid", acid);
		md.addAttribute("GLSUBHEAD", ACCTMaster.getglsubheadcode(CUSTID, acctnum));
		md.addAttribute("SCHMCODE", ACCTMaster.getschmcode(CUSTID, acctnum));
		// md.addAttribute("MATURITYDATE", ACCTMaster.getMaturityDate(acid));
		// md.addAttribute("LOANPERIOD", ACCTMaster.getLOANPERIOD(acid));
		md.addAttribute("DESTINATIONOFFUND", ACCTMaster.getDESTINATIONOFFUND(acid));

		md.addAttribute("AcctMastertran", cmgMaster.getAccountDetailsCust(CUSTID));
		md.addAttribute("AcctMaster", ACCTMaster.getAccountDetails(acid));

		List<ACCT_MASTER> custMaster_List = new ArrayList<>();

		List<Object[]> lst_Objects = ACCTMaster.getAccountDetails1(acid);

		for (Object[] obj : lst_Objects) {
			ACCT_MASTER info = new ACCT_MASTER();

			/*
			 * info.setGl_sub_head_code(String.valueOf(obj[0]));
			 * info.setSchm_code(String.valueOf(obj[1]));
			 * info.setAcct_name(String.valueOf(obj[2])); info.setAcct_opn_date(new
			 * SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[3])));
			 * info.setAcct_cls_date(new
			 * SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[4])));
			 * info.setAcct_status(String.valueOf(obj[5])); info.setAcct_status_date(new
			 * SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[6])));
			 * info.setMode_of_oper_code(String.valueOf(obj[7]));
			 */
			// info.setClr_bal_amt(String.valueOf(obj[0]));
			// info.setLast_frez_date(new
			// SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[1])));

			custMaster_List.add(info);
			// md.addAttribute("AcctMaster1", custMaster_List);
			md.addAttribute("clr_bal_amt", String.valueOf(obj[0]));
			md.addAttribute("bal_date", String.valueOf(obj[1]));
			md.addAttribute("inquiryflag", "inquiryflag");
		}

		return "AccountGeneral";
	}

	@RequestMapping(value = "AccountTransaction", method = { RequestMethod.GET, RequestMethod.POST })
	public String AccountTransaction(@RequestParam(required = false) String formmode,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "AcctNumber", required = false) String AcctNumber,
			@RequestParam(value = "acid", required = false) String acid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		final Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "AccountTransactionGeneral");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("CustomerName", CustName);
		md.addAttribute("CustID", AcctNumber);
		md.addAttribute("CustID1", CUSTID);
		md.addAttribute("ACID", acid);
		md.addAttribute("fromdate", date);
		md.addAttribute("todate", date);
		md.addAttribute("AccName", ACCTMaster.getacctdetailsfronameacid(acid));
		md.addAttribute("count", transRepository.getTransactioncount(acid));
		md.addAttribute("AcctTransaction", transRepository.getTransaction(acid, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("inquiryflag", "inquiryflag");
		return "AccountGeneral";
	}

	@RequestMapping(value = "AcctTransactiondate", method = { RequestMethod.GET, RequestMethod.POST })
	public String AcctTransactiondate(@RequestParam(required = false) String formmode,
			@RequestParam(value = "customer", required = false) String customer,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "cust", required = false) String cust,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "acid", required = false) String acid,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		// final Calendar cal = Calendar.getInstance();
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

		// String fromdate1 = dateFormat.format(fromdate);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "AccountTransactionGeneral");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("CustomerName", CustName);
		md.addAttribute("CustID", customer);
		md.addAttribute("ACID", acid);
		md.addAttribute("CustID1", cust);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("AccName", ACCTMaster.getacctdetailsfronameacid(acid));
		md.addAttribute("count", TRANMaster.TranCount(fromdate1, todate1, cust));
		md.addAttribute("AcctTransaction", TRANMaster.getAccountDetailsCustdate(fromdate1, todate1, cust));

		md.addAttribute("inquiryflag", "inquiryflag");
		return "AccountGeneral";
	}

	@RequestMapping(value = "AMLTranInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLTranInquiry(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranid", required = false) String tranid,
			@RequestParam(value = "tranrefno", required = false) String tranrefno,
			@RequestParam(value = "parttranid", required = false) String parttranid,
			@RequestParam(value = "acid", required = false) String acid,
			@RequestParam(value = "trandate", required = true) String trandate,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "TransactionGeneral");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("tranid", tranid);
		md.addAttribute("AccName", ACCTMaster.getacctdetailsfronameacid(acid));
		md.addAttribute("inquiryflag", "inquiryflag");

		// md.addAttribute("AcctMaster",
		// transRepository.getTransactionDetails(tranid,parttranid,PageRequest.of(currentPage,
		// pageSize)));

		List<TRANTEMP> custMaster_List = new ArrayList<>();

		List<Object[]> lst_Objects = transRepository.getTransactionDetails(tranid, trandate, parttranid);

		for (Object[] obj : lst_Objects) {
			TRANTEMP info = new TRANTEMP();
			info.setTran_date(new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[0])));
			info.setForacid(String.valueOf(obj[1]));
			info.setAcct_name(String.valueOf(obj[2]));
			info.setPart_tran_type(String.valueOf(obj[3]));
			info.setTran_id((String.valueOf(obj[4])));
			info.setPart_tran_srl_num(String.valueOf(obj[5]));
			info.setCust_id(String.valueOf(obj[6]));
			info.setTran_crncy_code(String.valueOf(obj[7]));
			info.setTran_amt(String.valueOf(obj[8]));
			info.setTran_particular(String.valueOf(obj[9]));
			info.setModule_id(String.valueOf(obj[10]));
			info.setAcid(String.valueOf(obj[11]));
			info.setCif_id(String.valueOf(obj[12]));

			custMaster_List.add(info);

		}
		md.addAttribute("AcctMaster", custMaster_List);

		return "TransactionGeneral";
	}

	@RequestMapping(value = "AMLTranInqDate", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLTranInqDate(@RequestParam(required = false) String formmode,
			@RequestParam(value = "tranid", required = false) String tranid,
			@RequestParam(value = "tranDate", required = false) String tranDate,
			@RequestParam(value = "parttranid", required = false) String parttranid,
			@RequestParam(value = "acid", required = false) String acid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		md.addAttribute("formmode", "TransactionGeneral");
		md.addAttribute("menu", "Monitoring");
		md.addAttribute("menuname", "Account Master");
		md.addAttribute("tranid", tranid);
		md.addAttribute("AccName", ACCTMaster.getacctdetailsfronameacid(acid));
		md.addAttribute("inquiryflag", "inquiryflag");

		// md.addAttribute("AcctMaster",
		// transRepository.getTransactionDetails(tranid,parttranid,PageRequest.of(currentPage,
		// pageSize)));

		List<TRANTEMP> custMaster_List = new ArrayList<>();

		List<Object[]> lst_Objects = transRepository.getTransactionDate(tranid, tranDate);

		for (Object[] obj : lst_Objects) {
			TRANTEMP info = new TRANTEMP();
			info.setTran_date(new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[0])));
			info.setForacid(String.valueOf(obj[1]));
			info.setAcct_name(String.valueOf(obj[2]));
			info.setPart_tran_type(String.valueOf(obj[3]));
			info.setTran_id((String.valueOf(obj[4])));
			info.setPart_tran_srl_num(String.valueOf(obj[5]));
			info.setCust_id(String.valueOf(obj[6]));
			info.setTran_crncy_code(String.valueOf(obj[7]));
			info.setTran_amt(String.valueOf(obj[8]));
			info.setTran_particular(String.valueOf(obj[9]));
			info.setModule_id(String.valueOf(obj[10]));
			info.setAcid(String.valueOf(obj[11]));
			info.setCif_id(String.valueOf(obj[12]));

			custMaster_List.add(info);

		}
		md.addAttribute("AcctMaster", custMaster_List);

		return "TransactionGeneral";
	}

	@RequestMapping(value = "AMLCustAband_fundListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custabandfundListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "Customer Aband_fund Listing Report");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("customerBlackList",
					cust_abond_fund_list_Repository.paramlistforReport(PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustAband_fund_ListingReport";
	}

	@RequestMapping(value = "AML_STR_REPORT", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_STR_REPORT(@RequestParam(required = false) String formmode,

			@RequestParam(value = "report_id", required = false) String report_id,
			@RequestParam(value = "foracid", required = false) String foracid,
			@RequestParam(value = "tran_id", required = false) String tran_id,
			@RequestParam(value = "tran_date", required = false) String tran_date,
			@RequestParam(value = "part_tran_srl_num", required = false) String part_tran_srl_num,
			@RequestParam(value = "tran_type", required = false) String tran_type,
			@RequestParam(value = "part_tran_type", required = false) String part_tran_type,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws FileNotFoundException, JRException, SQLException, ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		md.addAttribute("aml_str_rep", new AML_STR_ENTITY());
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("STR_REPORT", str_rep.reportlist(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("add")) {
			// Date date_tran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			// md.addAttribute("tran_id", tran_id);
			// md.addAttribute("tran_date", tran_date);
			// md.addAttribute("part_tran_srl_num", part_tran_srl_num);
			// md.addAttribute("tran_type", tran_type);
			// md.addAttribute("part_tran_type", part_tran_type);
			md.addAttribute("formmode", "add");
			md.addAttribute("aml_str_rep", new AML_STR_ENTITY());

			// md.addAttribute("SEARCHFROMTRAN",
			// TRANMaster.reportdet(tran_id, part_tran_srl_num, tran_type, part_tran_type));
		} else if (formmode.equals("custname")) {
			Date date_tran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

			md.addAttribute("formmode", "custname");
			md.addAttribute("tran_id", tran_id);
			md.addAttribute("tran_date", tran_date);
			md.addAttribute("part_tran_srl_num", part_tran_srl_num);

			md.addAttribute("SelectTran", TRANMaster.getSTRTranDet(tran_id,
					new SimpleDateFormat("dd-MMM-yyyy").format(date_tran), part_tran_srl_num));
		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("reportid", report_id);
			md.addAttribute("aml_str_rep", baml_STR_SERVICE.getSrlNo(report_id));
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("aml_str_rep", baml_STR_SERVICE.getSrlNo(report_id));
		} else if (formmode.equals("downlaod")) {
			md.addAttribute("formmode", "download");
			md.addAttribute("output_path", baml_STR_SERVICE.getFile(report_id));
		}
		md.addAttribute("interfaceflag", "interfaceflag");
		return "AML_STR";
	}

	@RequestMapping(value = "AML_STR_REPORT_ADD", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_STR_REPORT_ADD(@RequestParam(required = false) String formmode,

			@RequestParam(value = "report_id", required = false) String report_id,
			@RequestParam(value = "foracid", required = false) String foracid,
			@RequestParam(value = "tran_id", required = false) String tran_id,
			@RequestParam(value = "tran_date", required = false) String tran_date,
			@RequestParam(value = "part_tran_srl_num", required = false) String part_tran_srl_num,
			@RequestParam(value = "tran_type", required = false) String tran_type,
			@RequestParam(value = "part_tran_type", required = false) String part_tran_type,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws FileNotFoundException, JRException, SQLException, ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		md.addAttribute("aml_str_rep", new AML_STR_ENTITY());
		md.addAttribute("formmode", "add");
		md.addAttribute("interfaceflag", "interfaceflag");
		return "AML_STR";
	}

	@RequestMapping(value = "STRReportSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String STRReportSearch(@RequestParam(required = false) String formmode,

			@RequestParam(value = "report_id", required = false) String report_id,
			@RequestParam(value = "foracid", required = false) String foracid,
			@RequestParam(value = "tran_id", required = false) String tran_id,
			@RequestParam(value = "tran_date", required = false) String tran_date,
			@RequestParam(value = "part_tran_srl_num", required = false) String part_tran_srl_num,
			@RequestParam(value = "tran_type", required = false) String tran_type,
			@RequestParam(value = "part_tran_type", required = false) String part_tran_type,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws FileNotFoundException, JRException, SQLException, ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		Date date_tran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

		md.addAttribute("tran_id", tran_id);
		md.addAttribute("tran_date", tran_date);
		md.addAttribute("part_tran_srl_num", part_tran_srl_num);

		md.addAttribute("SelectTran", baml_STR_SERVICE.getBAMLSearchFilter(tran_id.trim(),
				new SimpleDateFormat("dd-MMM-yyyy").format(date_tran), part_tran_srl_num.trim()));

		md.addAttribute("interfaceflag", "interfaceflag");
		return "STRReportSearch";
	}

	@RequestMapping(value = "AML_STR_REPORTDOWNLOAD", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource AML_STR_REPORTDOWNLOAD(HttpServletResponse response,
			@RequestParam("report_id") String report_id,

			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "reportingTime", required = false) String reportingTime,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			// logger.info("Getting download File :"+report_id+", FileType :"+filetype+"");
			File repfile = baml_STR_SERVICE.getFile(report_id);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "createSTR", method = RequestMethod.POST)
	@ResponseBody
	public String createSTR(@RequestParam("formmode") String formmode, @ModelAttribute AML_STR_ENTITY strform, Model md,
			HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = baml_STR_SERVICE.addPARAMETER(strform, formmode, userid);

		md.addAttribute("menu", "UserProfile"); // To highlight the menu
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return msg;

	}

	@RequestMapping(value = "createSTR1", method = RequestMethod.POST)
	@ResponseBody
	public String createSTR1(@RequestParam("formmode") String formmode,
			@RequestParam(value = "reportid", required = false) String reportid, @ModelAttribute AML_STR_ENTITY strform,
			Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = baml_STR_SERVICE.addPARAMETER1(strform, formmode, userid, reportid);

		md.addAttribute("menu", "UserProfile"); // To highlight the menu
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("userprofileflag", "userprofileflag");

		return msg;

	}

	/********************
	 * *****************SCREENING ---> AML_SCR_PepListing starts
	 ****************************************/
	@RequestMapping(value = "AML_SCR_PepListing", method = { RequestMethod.GET, RequestMethod.POST })
	public String aml_SCR_PepListing(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "CustPep ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("customerBlackList", baml_cust_pep_rpt_repository.findAllCustPEPListReport(from_Date,
					to_date, PageRequest.of(currentPage, pageSize)));
			md.addAttribute("fromdate", from_Date);
			md.addAttribute("todate", to_date);
		}
		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("watchlistflag1", "watchlistflag1");

		return "AML_SCR_PepListing";
	}

	/*************************************
	 * AML SCREENING ---> AML_SCR_PepListing ends
	 ****************************************/

	/********************
	 * *****************SCREENING ---> AML_SCR_UNSCListing starts
	 ****************************************/
	@RequestMapping(value = "AML_SCR_UNSCListing", method = { RequestMethod.GET, RequestMethod.POST })
	public String aml_SCR_UNSCListing(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "UNSC ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("customerBlackList", baml_cust_hnwi_rpt_repository.findAllCustUNSClistReport(from_Date,
					to_date, PageRequest.of(currentPage, pageSize)));
//			md.addAttribute("customerBlackList", cmgMaster.findAll(PageRequest.of(currentPage, pageSize)));
		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);
		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("watchlistflag1", "watchlistflag1");

		return "AML_SCR_UNSCListing";
	}

	/*************************************
	 * AML SCREENING ---> AML_SCR_UNSCListing ends
	 ****************************************/

	/********************
	 * ***************** AML Screening -----> AMLSCCRBlackListingReport starts
	 ****************************************/
	@RequestMapping(value = "AML_SCR_BlackListing", method = { RequestMethod.GET, RequestMethod.POST })
	public String aml_scr_BlackListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String formmode_corp,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "Individual Black List ");
		md.addAttribute("formmode", "formmode_Ind"); // to set which form - valid values are "edit" , "add" & "list"
		md.addAttribute("individualBlackList", baml_cust_blacklist_rpt_repository
				.findAllCustBlackListIndReport(from_Date, to_date, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);
		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("scrCustlistflag1", "scrCustlistflag1");

		return "AML_SCR_BlackListing";
	}

	@RequestMapping(value = "AML_SCR_CorpBlackListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String aml_SCR_CorpBlackListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");

		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menu1", "Corporate Black List");
		md.addAttribute("formmode", "formmode_Corp"); // to set which form - valid values are "edit" , "add" & "list"
		md.addAttribute("corporateBlackList", baml_cust_blacklist_rpt_repository
				.findAllCustBlackListCorpReport(from_Date, to_date, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("fromdate1", from_Date);
		md.addAttribute("todate1", to_date);

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("scrCustlistflag1", "scrCustlistflag1");

		return "AML_SCR_BlackListing";
	}

	/*************************************
	 * AML SCREENING -----> SCREENING ends
	 ****************************************/

	/********************
	 * ***************** AML SCREENING -----> AML_SCR_NegativeListing starts
	 ****************************************/
	@RequestMapping(value = "AML_SCR_NegativeListing", method = { RequestMethod.GET, RequestMethod.POST })
	public String aml_scr_NegativeListing(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustNegativeListingReport");
		md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

		md.addAttribute("customerBlackList", baml_cust_blacklist_rpt_repository.findAllCustNegativelistReport(from_Date,
				to_date, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("scrCustlistflag1", "scrCustlistflag1");

		return "AML_SCR_NegativeListing";
	}

	/*************************************
	 * AML SCREENING -----> AML_SCR_NegativeListing ends
	 ****************************************/

	/********************
	 * ***************** AML SCREENING -----> AML_SCR_HNWIList starts
	 ****************************************/
	@RequestMapping(value = "AML_SCR_HNWIListing", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_SCR_HNWIList(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "Custhnwi ListingReport");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("customerBlackList", baml_cust_hnwi_rpt_repository.findAllCustHNWIListReport(from_Date,
					to_date, PageRequest.of(currentPage, pageSize)));
		}
		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("scrCustlistflag1", "scrCustlistflag1");

		return "AML_SCR_HNWIListing";
	}

	/********************
	 * ***************** AML SCREENING -----> AML_SCR_HNWIList emnds
	 ****************************************/

	/********************
	 * ***************** AML SCREENING -----> AML_SCR_ABAND_FUND List starts
	 ****************************************/
	@RequestMapping(value = "AML_SCR_ABAND_FUNDListing", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_SCR_ABAND_FUNDList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menu", "Customer Aband_fund Listing ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("customerBlackList",
					cust_abond_fund_list_Repository.paramlistforReport(PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("scrCustlistflag1", "scrCustlistflag1");

		return "AML_SCR_Aband_fund_Listing";
	}

	/********************
	 * ***************** AML SCREENING -----> AML_SCR_ABAND_FUNDList emnds
	 ****************************************/

	/********************
	 * ***************** PARAMETER ------- AML SCREENING PARAMETER -----emnds
	 ****************************************/

	@RequestMapping(value = "ScreeningParameter_Cust", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLScreeningParameter(@RequestParam(required = false) String formmode, @RequestParam(required = false) String Search,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) String userid,@RequestParam(required = false) String Values,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("Ind_list")) {
			md.addAttribute("menuname", "Customer Type List ");
			md.addAttribute("formmode", "Ind_list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("custIndblackList",	aml_scr_parm_cust_type_repository.parameterlistwithdecode(PageRequest.of(currentPage, pageSize)));

			if(Search == null || Search.equals("null")){
				System.out.println("custttypetesting");
			//	md.addAttribute("custIndblackList",	aml_scr_parm_cust_type_repository.parameterlistwithdecode1(PageRequest.of(currentPage, pageSize)));
			
			}else if (Search.equals("loan")) {
				String master =Values.toUpperCase();
				System.out.println("MASTER "+master);
				md.addAttribute("custIndblackList",
						aml_scr_parm_cust_type_repository.parameterlistwithdecodeloan(master,PageRequest.of(currentPage, pageSize)));
			
			}else if (Search.equals("custtype")) {
				String master =Values.toUpperCase();
				System.out.println("MASTER "+master);
				md.addAttribute("custIndblackList",
						aml_scr_parm_cust_type_repository.parameterlistwithdecodecusttype(master,PageRequest.of(currentPage, pageSize)));
			
			}else if (Search.equals("custsub")) {
				String master =Values.toUpperCase();
				System.out.println("MASTER "+master);
				md.addAttribute("custIndblackList",
						aml_scr_parm_cust_type_repository.parameterlistwithdecodecustsub(master,PageRequest.of(currentPage, pageSize)));
			
			}
			else if (Search.equals("schm")) {
				String master =Values.toUpperCase();
				System.out.println("MASTER "+master);
				md.addAttribute("custIndblackList",
						aml_scr_parm_cust_type_repository.parameterlistwithdecodeschm(master,PageRequest.of(currentPage, pageSize)));
			
			}
			else if (Search.equals("glsub")) {
				String master =Values.toUpperCase();
				System.out.println("MASTER "+master);
				md.addAttribute("custIndblackList",
						aml_scr_parm_cust_type_repository.parameterlistwithdecodegl(master,PageRequest.of(currentPage, pageSize)));
			
			}else if(Search.equals("refno")) {
				String master =Values.toUpperCase();
				System.out.println("MASTER "+master);
				md.addAttribute("custIndblackList",
						aml_scr_parm_cust_type_repository.parameterlistwithdecoderefno(master,PageRequest.of(currentPage, pageSize)));
				
			}
				
		//	md.addAttribute("custIndblackList",aml_scr_parm_cust_type_repository.parameterlistwithdecode(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("Ind_add")) {

			md.addAttribute("formmode", "Ind_add");
			md.addAttribute("menuname1", "Customer Type List - Add");
			md.addAttribute("Ind_ListParameter", new Aml_Scr_Parm_Cust_Type_Entity());
//			md.addAttribute("RefNo", aml_scr_parm_cust_type_Services.getNextRefValue());

		} else if (formmode.equals("Ind_edit")) {

			md.addAttribute("formmode", "Ind_edit");
			md.addAttribute("menuname1", "Customer Type List - Modify");
			md.addAttribute("Ind_ListParameter", aml_scr_parm_cust_type_Services.getSrlNo(srlno));

		} else if (formmode.equals("Ind_verify")) {

			md.addAttribute("formmode", "Ind_verify");
			md.addAttribute("Ind_ListParameter", aml_scr_parm_cust_type_Services.getSrlNo(srlno));
			md.addAttribute("menuname1", "Customer Type List - Verify");

		} else if (formmode.equals("Ind_view")) {

			md.addAttribute("formmode", "Ind_view");
			md.addAttribute("menuname1", "Customer Type List - Inquiry");
			md.addAttribute("Ind_ListParameter", aml_scr_parm_cust_type_Services.getSrlNo(srlno));

		} else if (formmode.equals("Ind_delete")) {

			md.addAttribute("formmode", "Ind_delete");
			md.addAttribute("menuname1", "Customer Type List - Delete");
			md.addAttribute("Ind_ListParameter", aml_scr_parm_cust_type_Services.getSrlNo(srlno));

		}
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("parameterflag", "parameterflag");

		return "AML_SCR_PARMETER";
	}

	// Customer TYPE list
	@RequestMapping(value = "createSCR_Cust_List", method = RequestMethod.POST)
	@ResponseBody
	public String createSCR_Cust_List(@RequestParam("formmode") String formmode,
			@ModelAttribute Aml_Scr_Parm_Cust_Type_Entity alertparam, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = aml_scr_parm_cust_type_Services.addBlack_IND_List(alertparam, formmode, userid);

//		if (msg.equals("Customer Type Created Successfully")) {
//				//after saving the new  record increementing the value by 1 for next record to insert
//			aml_scr_parm_cust_type_Services.updateBlack_list_Ind_Num();
//		}

		md.addAttribute("parameterflag", "parameterflag");

		return msg;

	}

	@RequestMapping(value = "ScreeningParameter_Schm", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLscreen_parameter_Schm(@RequestParam(required = false) String formmode,@RequestParam(required = false) String Values,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) String userid,@RequestParam(required = false) String Search,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("Corp_list")) {
			md.addAttribute("menuname_corp", "Loan Schemes  ");
			md.addAttribute("formmode", "Corp_list"); // to set which form - valid values are "edit" , "add" & "list"
			if(Search == null) {
				md.addAttribute("custCorpblackList",
						aml_scr_parm_Schm_type_Services.parameterlistwithdecode(PageRequest.of(currentPage, pageSize)));
			}else if(Search.equals("refno")) {
				String master = Values.toUpperCase();
				md.addAttribute("custCorpblackList",
						aml_scr_parm_Schm_type_repository.parameterlistwithdecoderefno(master,PageRequest.of(currentPage, pageSize)));
			}else if(Search.equals("loan")) {
				String master = Values.toUpperCase();
				md.addAttribute("custCorpblackList",
						aml_scr_parm_Schm_type_repository.parameterlistwithdecodeloan(master,PageRequest.of(currentPage, pageSize)));
			}else if(Search.equals("loanschm")) {
				String master = Values.toUpperCase();
				md.addAttribute("custCorpblackList",
						aml_scr_parm_Schm_type_repository.parameterlistwithdecodeloanschm(master,PageRequest.of(currentPage, pageSize)));
			}
			else if(Search.equals("schm")) {
				String master = Values.toUpperCase();
				md.addAttribute("custCorpblackList",
						aml_scr_parm_Schm_type_repository.parameterlistwithdecodeschm(master,PageRequest.of(currentPage, pageSize)));
			}else if(Search.equals("glsub")) {
				String master = Values.toUpperCase();
				md.addAttribute("custCorpblackList",
						aml_scr_parm_Schm_type_repository.parameterlistwithdecodeschm(master,PageRequest.of(currentPage, pageSize)));
			}
			

		} else if (formmode.equals("Corp_add")) {

			md.addAttribute("formmode", "Corp_add");
			md.addAttribute("menuname_corp", "Loan Schemes - Add");
			md.addAttribute("Corp_ListParameter", new Aml_Scr_Parm_Schm_Type_Entity());
//			md.addAttribute("CorpRefNo", aml_scr_parm_Schm_type_Services.getNextRefValue());

		} else if (formmode.equals("Corp_edit")) {

			md.addAttribute("formmode", "Corp_edit");
			md.addAttribute("menuname_corp", "Loan Schemes - Modify");
			md.addAttribute("Corp_ListParameter", aml_scr_parm_Schm_type_Services.getSrlNo(srlno));

		} else if (formmode.equals("Corp_verify")) {

			md.addAttribute("formmode", "Corp_verify");
			md.addAttribute("menuname_corp", "Loan Schemes - Verify");
			md.addAttribute("Corp_ListParameter", aml_scr_parm_Schm_type_Services.getSrlNo(srlno));

		} else if (formmode.equals("Corp_view")) {

			md.addAttribute("formmode", "Corp_view");
			md.addAttribute("menuname_corp", "Loan Schemes - View");
			md.addAttribute("Corp_ListParameter", aml_scr_parm_Schm_type_Services.getSrlNo(srlno));

		} else if (formmode.equals("Corp_delete")) {

			md.addAttribute("formmode", "Corp_delete");
			md.addAttribute("menuname_corp", "Loan Schemes - Delete");
			md.addAttribute("Corp_ListParameter", aml_scr_parm_Schm_type_Services.getSrlNo(srlno));

		}

		md.addAttribute("parameterflag", "parameterflag");

		return "AML_SCR_PARMETER";
	}

	@RequestMapping(value = "createSCR_Schm_List", method = RequestMethod.POST)
	@ResponseBody
	public String createcreateSCR_Schm_List(@RequestParam("formmode") String formmode,
			@ModelAttribute Aml_Scr_Parm_Schm_Type_Entity alertparam, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = aml_scr_parm_Schm_type_Services.addSchm_Type_List(alertparam, formmode, userid);
//		if (msg.equals("Loan Scheme Created Successfully")) {
//				//after saving the new  record increementing the value by 1 for next record to insert
//			aml_scr_parm_Schm_type_Services.updateSCR_SCHM_list_Num();
//		}

		md.addAttribute("parameterflag", "parameterflag");

		return msg;

	}

	/********************
	 * ***************** AML SCREENING PARAMETER emnds
	 ****************************************/

	/***************
	 * AML MONITORING REPORTS STARTS
	 * 
	 * @throws ParseException
	 ******************/
	@RequestMapping(value = "AMLMonitoringReports", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLMonitoringReports(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String tranid, @RequestParam(required = false) String Fromdate,
			@RequestParam(required = false) String trandate, @RequestParam(required = false) String parttran,
			@RequestParam(required = false) String Todate, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String acid, @RequestParam(required = false) String alertcode,
			@RequestParam(required = false) String freeText, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");

			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			String date = dateFormat.format(cal.getTime());
			String date1 = dateFormat1.format(cal.getTime());
			md.addAttribute("Fromdate", date);
			md.addAttribute("Todate", date);
			md.addAttribute("menuname", "AML ALERT TRANSACTIONS");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("MonitoringParameterList",
					bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("datefilter")) {

			if ((Todate.isEmpty() || Fromdate.isEmpty()) && !freeText.isEmpty()) {

				md.addAttribute("formmode", formmode);
				md.addAttribute("menuname", "AML ALERT TRANSACTIONS");
				md.addAttribute("MonitoringParameterList", bamlTranAlertsMasterRepository
						.parameterlistwithoudate(freeText, PageRequest.of(currentPage, pageSize)));
			} else if (!Todate.isEmpty() && !Fromdate.isEmpty()) {
				Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
				Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
				md.addAttribute("formmode", formmode);
				md.addAttribute("menuname", "AML ALERT TRANSACTIONS");
				md.addAttribute("MonitoringParameterList", bamlTranAlertsMasterRepository
						.parameterlistwithdate(fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("Fromdate", Fromdate);
				md.addAttribute("Todate", Todate);

			}

			else {
				Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
				Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
				md.addAttribute("formmode", formmode);
				md.addAttribute("menuname", "AML ALERT TRANSACTIONS");
				md.addAttribute("MonitoringParameterList", bamlTranAlertsMasterRepository.parameterlistdate(fromdate1,
						todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("Fromdate", Fromdate);
				md.addAttribute("Todate", Todate);
			}
		} else if (formmode.equals("Search")) {
			md.addAttribute("formmode", formmode);
			Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
			Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
			md.addAttribute("menuname", "AML ALERT TRANSACTIONS");
			md.addAttribute("Fromdate", Fromdate);
			md.addAttribute("Todate", Todate);
			md.addAttribute("freeText", freeText);
			md.addAttribute("MonitoringParameterList", bamlTranAlertsMasterRepository.parameterlistSearch(fromdate1,
					todate1, freeText, PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("rulefilter")) {
			md.addAttribute("monitoringalertflg", "Y");
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "AML ALERT TRAN MASTER - INQUIRY");
			md.addAttribute("Fromdate", Fromdate);
			md.addAttribute("Todate", Todate);
			md.addAttribute("acid", acid);
			md.addAttribute("AlertCode", alertcode);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
			Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
			Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			String date = dateFormat.format(cal.getTime());
			String date1 = dateFormat1.format(cal.getTime());

			if (alertcode.equals("CVNCW1")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVNCW1(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("CVCDP")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVCDP(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("CVNCD")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVNCD(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("CVNCW")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVNCW(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("CVCWL")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVCWL(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("CVCDP1")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVCDP1(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("CVCWL1")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVCWL1(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("CVNCD1")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getCVNCD1(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else if (alertcode.equals("TSCTP")) {
				md.addAttribute("TransactionMaster",
						TRANMaster.getTSCTP(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date1, PageRequest.of(currentPage, pageSize)));

			} else {
				md.addAttribute("TransactionMaster",
						TRANMaster.getDTCDT(acid, fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("MonitoringParameterList",
						bamlTranAlertsMasterRepository.parameterlist(date, PageRequest.of(currentPage, pageSize)));

			}
			// md.addAttribute("MonitorParameter",
			// bamlTranAlertsMasterService.getSrlNo(tranid, trandate, parttran));

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "AML ALERT TRAN MASTER - INQUIRY");
			md.addAttribute("MonitorParameter", bamlTranAlertsMasterService.getSrlNo(tranid, trandate, parttran));

		}

		else if (formmode.equals("edit")) {
			md.addAttribute("formmode", formmode);

			/* md.addAttribute("userProfile", userProfileDao.getUser(userid)); */
			md.addAttribute("menuname1", "AML ALERT TRAN MASTER - MODIFY");
			md.addAttribute("MonitorParameter", bamlTranAlertsMasterService.getSrlNo(tranid, trandate, parttran));

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("MonitorParameter", bamlTranAlertsMasterService.getSrlNo(tranid, trandate, parttran));
			md.addAttribute("menuname1", "AML ALERT TRAN MASTER - VERIFY");

		}

		// md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "AMLMonitoringReports");
		md.addAttribute("monitoringflag", "monitoringflag");

		return "AMLMonitoringReports";

	}

	@RequestMapping(value = "creatingAMLMonitoringReports", method = RequestMethod.POST)
	@ResponseBody
	public String createAMLMonitoringReports(@RequestParam("formmode") String formmode,
			@RequestParam(value = "parttranid", required = false) String parttranid,
			@RequestParam(value = "tranid", required = false) String tranid,
			@RequestParam(value = "trandate", required = true) String trandate,

			@ModelAttribute BAMLTranAlertsMaster bamlTranAlertsMaster, Model md, HttpServletRequest rq)
			throws ParseException {
		String userid = (String) rq.getSession().getAttribute("USERID");

		// String msg = paraservices.addPARAMETER(bamlTranAlertsMaster, formmode);
		String msg = bamlTranAlertsMasterService.addPARAMETER(bamlTranAlertsMaster, formmode, userid, tranid, trandate,
				parttranid);

		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "monitoringparameter");

		return msg;

	}

	@RequestMapping(value = "MonitoringParameter", method = { RequestMethod.GET, RequestMethod.POST })
	public String MonitoringParameter(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "Operations Parameter");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("MonitoringParameterList", montRepository.parameter(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("modify")) {
			md.addAttribute("menuname", "Operations Parameter");
			md.addAttribute("formmode", "modify"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("MONITORINGPARAMETER", paraservices.getSrlNo1(srlno));
			req.getSession().getAttribute("USERID");

		} else if (formmode.equals("view")) {
			md.addAttribute("menuname", "Operations Parameter");
			md.addAttribute("formmode", "view"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("MONITORINGPARAMETER", paraservices.getSrlNo1(srlno));

		} else if (formmode.equals("verify")) {
			md.addAttribute("menuname", "Operations Parameter");
			md.addAttribute("formmode", "verify"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("MONITORINGPARAMETER", paraservices.getSrlNo1(srlno));

		} else if (formmode.equals("add")) {
			md.addAttribute("menuname", "Operations Parameter");
			md.addAttribute("formmode", "add"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("MONITORINGPARAMETER", new MONITORINGPARAMETERENTIRY());

		}
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "Operations Parameter");
		md.addAttribute("parameterflag", "parameterflag");
		return "MONITORINGPARAMETER";
	}

	@RequestMapping(value = "createMONITORINGPARAMETER", method = RequestMethod.POST)
	@ResponseBody
	public String MONITORINGPARAMETER(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String user, @ModelAttribute MONITORINGPARAMETERENTIRY alertparam, Model md,
			HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = paraservices.EDITPARAMETER(alertparam, formmode, userid, user);
		/* email.sendEmail(user, alertparam); */

		md.addAttribute("adminflag", "adminflag");

		return msg;

	}
	/*
	 * @RequestMapping(value = "createMONITORINGPARAMETER", method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public String MAILMONITORINGPARAMETER(@RequestParam("formmode")
	 * String formmode,
	 * 
	 * @RequestParam(required = false) String user, @ModelAttribute
	 * MONITORINGPARAMETERENTIRY alertparam, Model md, HttpServletRequest rq) {
	 * 
	 * //String msg = paraservices.EDITPARAMETER(alertparam, formmode); String msg =
	 * email.sendEmail(user, alertparam);
	 * 
	 * md.addAttribute("adminflag", "adminflag");
	 * 
	 * return msg;
	 * 
	 * }
	 */

	@RequestMapping(value = "AMLUNSCList", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLUNSCList(@RequestParam(required = false) String formmode,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "entityname", required = false) String entityname,
			@RequestParam(value = "dataid", required = false) String dataid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("listindividual")) {

			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Individual Listing ");

			md.addAttribute("formmode", "listindividual"); // to set which form - valid values are "edit" , "add" &
															// "list"

			try {
				if (name != null && !name.isEmpty()) {
					md.addAttribute("NegativeList", amlIndividualRepository
							.getlistByFIRSTname(PageRequest.of(currentPage, pageSize), name + '%'));
				} else {
					md.addAttribute("NegativeList",
							amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("listentity")) {

			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Entity Listing");

			md.addAttribute("formmode", "listentity"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (entityname != null && !entityname.isEmpty()) {
					md.addAttribute("NegativeList", entityTableRepository
							.getlistByFIRSTname(PageRequest.of(currentPage, pageSize), entityname + '%'));
				} else {
					md.addAttribute("NegativeList",
							entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("addindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual Add");
			md.addAttribute("INDSRL", unscServices.getSrlNoIndValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("addentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Add");
			md.addAttribute("ENTSRL", unscServices.getSrlNoEntValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("editindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual - Edit");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("editentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Edit ");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("verifyindividual")) {
			md.addAttribute("formmode", "verifyindividual");
			md.addAttribute("menuname1", "UNSC Individual-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("verifyentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("viewentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return "AMLUNSCList";
	}

	@RequestMapping(value = "AMLUNSCListDataID", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLUNSCListDataID(@RequestParam(required = false) String formmode,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "DataId", required = false) String DataId,
			@RequestParam(value = "entityname", required = false) String entityname,
			@RequestParam(value = "dataid", required = false) String dataid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		
		
		
		if (formmode == null || formmode.equals("listindividual")) {
			System.out.println("DataId:::" + DataId);
			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Individual Listing ");

			md.addAttribute("formmode", "listindividual"); // to set which form - valid values are "edit" , "add" &
															// "list"

			try {
				if (DataId != null && !DataId.isEmpty()) {
					md.addAttribute("NegativeList",
							amlIndividualRepository.getlistByDataId(PageRequest.of(currentPage, pageSize), DataId));
				} else {
					md.addAttribute("NegativeList",
							amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("listentity")) {

			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Entity Listing");

			md.addAttribute("formmode", "listentity"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (entityname != null && !entityname.isEmpty()) {
					md.addAttribute("NegativeList", entityTableRepository
							.getlistByFIRSTname(PageRequest.of(currentPage, pageSize), entityname + '%'));
				} else {
					md.addAttribute("NegativeList",
							entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("addindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual Add");
			md.addAttribute("INDSRL", unscServices.getSrlNoIndValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("addentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Add");
			md.addAttribute("ENTSRL", unscServices.getSrlNoEntValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("editindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual - Edit");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("editentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Edit ");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("verifyindividual")) {
			md.addAttribute("formmode", "verifyindividual");
			md.addAttribute("menuname1", "UNSC Individual-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("verifyentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("viewentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return "AMLUNSCList";
	}

	@RequestMapping(value = "AMLUNSCListDataID1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLUNSCListDataID1(@RequestParam(required = false) String formmode,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "DataId", required = false) String DataId,
			@RequestParam(value = "entityname", required = false) String entityname,
			@RequestParam(value = "dataid", required = false) String dataid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("listindividual")) {
			System.out.println("DataId:::" + DataId);
			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Individual Listing ");

			md.addAttribute("formmode", "listindividual"); // to set which form - valid values are "edit" , "add" &
															// "list"

			try {
				if (DataId != null && !DataId.isEmpty()) {
					md.addAttribute("NegativeList",
							amlIndividualRepository.getlistByDataId(PageRequest.of(currentPage, pageSize), DataId));
				} else {
					md.addAttribute("NegativeList",
							amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("listentity")) {
			System.out.println("DataIdEntity:::::" + DataId);
			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Entity Listing");

			md.addAttribute("formmode", "listentity"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (DataId != null && !DataId.isEmpty()) {
					md.addAttribute("NegativeList",
							entityTableRepository.getlistByDataId(PageRequest.of(currentPage, pageSize), DataId));
				} else {
					md.addAttribute("NegativeList",
							entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("addindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual Add");
			md.addAttribute("INDSRL", unscServices.getSrlNoIndValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("addentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Add");
			md.addAttribute("ENTSRL", unscServices.getSrlNoEntValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("editindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual - Edit");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("editentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Edit ");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("verifyindividual")) {
			md.addAttribute("formmode", "verifyindividual");
			md.addAttribute("menuname1", "UNSC Individual-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("verifyentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("viewentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return "AMLUNSCList";
	}

	@RequestMapping(value = "AMLUNSCListRef", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLUNSCListRef(@RequestParam(required = false) String formmode,
			@RequestParam(value = "custRefNum", required = false) String custRefNum,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "entityname", required = false) String entityname,
			@RequestParam(value = "dataid", required = false) String dataid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("listindividual")) {

			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Individual Listing ");

			md.addAttribute("formmode", "listindividual"); // to set which form - valid values are "edit" , "add" &
															// "list"

			try {
				if (custRefNum != null && !custRefNum.isEmpty()) {
					md.addAttribute("NegativeList",
							amlIndividualRepository.getlistByRefnum(PageRequest.of(currentPage, pageSize), custRefNum));
				} else {
					md.addAttribute("NegativeList",
							amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("listentity")) {

			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Entity Listing");

			md.addAttribute("formmode", "listentity"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (entityname != null && !entityname.isEmpty()) {
					md.addAttribute("NegativeList", entityTableRepository
							.getlistByFIRSTname(PageRequest.of(currentPage, pageSize), entityname + '%'));
				} else {
					md.addAttribute("NegativeList",
							entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("addindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual Add");
			md.addAttribute("INDSRL", unscServices.getSrlNoIndValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("addentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Add");
			md.addAttribute("ENTSRL", unscServices.getSrlNoEntValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("editindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual - Edit");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("editentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Edit ");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("verifyindividual")) {
			md.addAttribute("formmode", "verifyindividual");
			md.addAttribute("menuname1", "UNSC Individual-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("verifyentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("viewentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return "AMLUNSCList";
	}

	@RequestMapping(value = "AMLUNSCListRef1", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLUNSCListRef1(@RequestParam(required = false) String formmode,
			@RequestParam(value = "custRefNum", required = false) String custRefNum,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "entityname", required = false) String entityname,
			@RequestParam(value = "dataid", required = false) String dataid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("listindividual")) {

			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Individual Listing ");

			md.addAttribute("formmode", "listindividual"); // to set which form - valid values are "edit" , "add" &
															// "list"

			try {
				if (custRefNum != null && !custRefNum.isEmpty()) {
					md.addAttribute("NegativeList",
							amlIndividualRepository.getlistByRefnum(PageRequest.of(currentPage, pageSize), custRefNum));
				} else {
					md.addAttribute("NegativeList",
							amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						amlIndividualRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}
		} else if (formmode.equals("listentity")) {
			System.out.println("custRefNumEntity:::::" + custRefNum);
			md.addAttribute("menu", "UNSC");
			md.addAttribute("menuname", "UNSC Entity Listing");

			md.addAttribute("formmode", "listentity"); // to set which form - valid values are "edit" , "add" & "list"
			try {
				if (custRefNum != null && !custRefNum.isEmpty()) {
					md.addAttribute("NegativeList",
							entityTableRepository.getlistByRefNum(PageRequest.of(currentPage, pageSize), custRefNum));
				} else {
					md.addAttribute("NegativeList",
							entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
				}
			} catch (Exception e) {
				md.addAttribute("NegativeList",
						entityTableRepository.parameterlist(PageRequest.of(currentPage, pageSize)));
			}

		} else if (formmode.equals("addindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual Add");
			md.addAttribute("INDSRL", unscServices.getSrlNoIndValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("addentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Add");
			md.addAttribute("ENTSRL", unscServices.getSrlNoEntValue());
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));

		} else if (formmode.equals("editindividual")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual - Edit");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("editentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity - Edit ");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("verifyindividual")) {
			md.addAttribute("formmode", "verifyindividual");
			md.addAttribute("menuname1", "UNSC Individual-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("verifyentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Verify");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Individual-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("KYC", unscServices.getSrlNo(dataid));

		} else if (formmode.equals("viewentity")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "UNSC Entity-Inquiry");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("Entity", unscServices.getDataId(dataid));

		}

		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return "AMLUNSCList";
	}

	/*************************************
	 * AML monitoring reports -----> AMLCustUNSCReport ends
	 ****************************************/

	/*************** AML MONITORING download STARTS ******************/
	@RequestMapping(value = "AMLRuleDownload", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLRuleDownload(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String tranid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "Monitoring Report");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

		}

		// md.addAttribute("menuname", "Monitoring Report");
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "monitoringparameter");
		return "AMLRuleDownload";

	}

	@RequestMapping(value = "Download/Monitoring", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource AMLDownload(HttpServletResponse response, @RequestParam("filetype") String filetype)
			throws IOException, SQLException, ParseException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			// File repfile = reportServices.getDownloadFile(reportid, asondate, fromdate,
			// todate, currency,subreportid, secid,
			// dtltype, reportingTime,filetype,instancecode);
			File repfile = bamlTranAlertsMasterService.getFile(filetype);
			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	/*************** AML MONITORING download ends ******************/

	@RequestMapping(value = "CustomerAudit", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustomerAudit(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("Main")) {
			md.addAttribute("formmode", "Main");
			md.addAttribute("menuname", "Customer Audit Log");
		} else if (formmode.equals("list1")) {

			md.addAttribute("menuname", "Customer Audit Log");
			md.addAttribute("auditflag", "auditflag");
		}
		md.addAttribute("auditflag", "auditflag");
		return "AMLCustomerAudit";
	}

	@RequestMapping(value = "AccountAudit", method = { RequestMethod.GET, RequestMethod.POST })
	public String AccountAudit(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("Main")) {
			md.addAttribute("formmode", "Main");
			md.addAttribute("menuname", "Account Audit Log");
		} else if (formmode.equals("list1")) {

			md.addAttribute("menuname", "Account Audit Log");
			md.addAttribute("auditflag", "auditflag");
			md.addAttribute("formmode", "list1");
		}
		return "AMLAccountAudit";

	}

	/** RBS Report Generation starts ***/
	@RequestMapping(value = "AMLReportsGeneration", method = { RequestMethod.GET, RequestMethod.POST })
	public String RBSReportGeneration(Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("reportvalue", "RBS Report Generation");
		md.addAttribute("reportid", "rbsReportGeneration");
		md.addAttribute("menu", "rbsReportGeneration");
		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		md.addAttribute("reportsflag", "reportsflag");

		return "AMLReportsGeneration";

	}

	@RequestMapping(value = "AMLMonitoringReportsDownload", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLMonitoringReportsDownload(Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("reportvalue", "Monitoring Report Download");
		md.addAttribute("reportid", "AMLMonitoringReportsDownload");
		md.addAttribute("menu", "AMLMonitoringReportsDownload");
		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		md.addAttribute("monitoringflag", "monitoringflag");

		return "AMLMonitoringDownload";

	}

	/** RBS Report Generation ends ***/

	@RequestMapping(value = "BAMLAuditAccountInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditAccount(@RequestParam(required = false) String foracid,
			@RequestParam(required = false) String formmode, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("foracid", foracid);
		md.addAttribute("formmode", "list1");
		md.addAttribute("AccountAuditList", ACCTMaster.getAuditAccount(foracid));
		md.addAttribute("AuditList", auditRep.getauditlist(foracid, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Account Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLAccountAudit";
	}

	@RequestMapping(value = "BAMLAuditCustAccountInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditCustAccountInquiry(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("foracid", cust_id);
		md.addAttribute("formmode", "custname");
		md.addAttribute("AccountAuditList", ACCTMaster.getAuditCustomer(cust_id));
		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLAccountAudit";
	}

	@RequestMapping(value = "BAMLAuditShortNameAccountInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditShortNameAccountInquiry(@RequestParam(required = false) String acct_short_name,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("foracid", acct_short_name);
		md.addAttribute("formmode", "custname");
		md.addAttribute("AccountAuditList", ACCTMaster.getAuditShortAcctName(acct_short_name));

		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLAccountAudit";
	}

	@RequestMapping(value = "BAMLAuditNameAccountInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditNameAccountInquiry(@RequestParam(required = false) String acct_name,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("foracid", acct_name);
		md.addAttribute("formmode", "custname");
		md.addAttribute("AccountAuditList", ACCTMaster.getAuditAcctName(acct_name));

		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLAccountAudit";
	}

	@RequestMapping(value = "BAMLAuditAccNIDInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditAccNIDInquiry(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("foracid", national_id_card_num);
		md.addAttribute("formmode", "custname");
		md.addAttribute("AccountAuditList", ACCTMaster.getAuditNatId(national_id_card_num));

		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLAccountAudit";
	}

	@RequestMapping(value = "BAMLAuditAccMobInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditAccMobInquiry(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String acct_len,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("foracid", mob_no);
		md.addAttribute("formmode", "custname");
		md.addAttribute("AccountAuditList", ACCTMaster.getAuditMobNo(mob_no));

		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLAccountAudit";
	}

	@RequestMapping(value = "BAMLAuditAccMailInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditAccMailInquiry(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String mail_id, @RequestParam(required = false) String acct_len,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("foracid", mail_id);
		md.addAttribute("formmode", "custname");
		md.addAttribute("AccountAuditList", ACCTMaster.getAuditMailId(mail_id));

		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLAccountAudit";
	}

	@RequestMapping(value = "BAMLAuditCustomerInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditCustomer(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("CUST_ID", cust_id);
		md.addAttribute("formmode", "list1");
		md.addAttribute("CustomerAuditList", cmgMaster.getAuditCustomer(cust_id));
		String cust = baml_STR_SERVICE.getcustAudit(cust_id);
		int leng = baml_STR_SERVICE.getcustAuditlen(cust_id);
		md.addAttribute("AuditList", auditRep.getCustAuditlist(cust, leng, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLCustomerAudit";
	}

	@RequestMapping(value = "BAMLAuditCustomerMobInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditCustomerMobInquiry(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String acct_len,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("CUST_ID", cust_id);
		md.addAttribute("formmode", "list1");
		md.addAttribute("CustomerAuditList", cmgMaster.getByMob(mob_no));
		String cust = baml_STR_SERVICE.getMobAudit(mob_no);
		int leng = baml_STR_SERVICE.getMobAuditlen(mob_no);
		md.addAttribute("AuditList", auditRep.getCustAuditlist(cust, leng, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLCustomerAudit";
	}

	@RequestMapping(value = "BAMLAuditCustomerNIDInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditCustomerNIDInquiry(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("CUST_ID", cust_id);
		md.addAttribute("formmode", "list1");
		md.addAttribute("CustomerAuditList", cmgMaster.getByNID(national_id_card_num));
		String cust = baml_STR_SERVICE.getNIDAudit(national_id_card_num);
		int leng = baml_STR_SERVICE.getNIDAuditlen(national_id_card_num);
		md.addAttribute("AuditList", auditRep.getCustAuditlist(cust, leng, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLCustomerAudit";
	}

	@RequestMapping(value = "BAMLAuditCustomerMailInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditCustomerMailInquiry(@RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String formmode, @RequestParam(required = false) String mail_id,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("CUST_ID", cust_id);
		md.addAttribute("formmode", "list1");
		md.addAttribute("CustomerAuditList", cmgMaster.getByMail(mail_id));
		String cust = baml_STR_SERVICE.getMAILAudit(mail_id);
		int leng = baml_STR_SERVICE.getMAILAuditlen(mail_id);
		md.addAttribute("AuditList", auditRep.getCustAuditlist(cust, leng, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLCustomerAudit";
	}

	@RequestMapping(value = "BAMLAuditCustomerSNameInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditCustomerSNameInquiry(@RequestParam(required = false) String cust_short_name,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("CUST_ID", cust_short_name);
		md.addAttribute("formmode", "custname");
		md.addAttribute("CustomerAuditList", cmgMaster.getAuditCustomerSName(cust_short_name));

		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLCustomerAudit";
	}

	@RequestMapping(value = "BAMLAuditCustomerName", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditCustomerName(@RequestParam(required = false) String cust_name,
			@RequestParam(required = false) String acct_len, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("CUST_ID", cust_name);
		md.addAttribute("formmode", "custname");
		md.addAttribute("CustomerAuditList", cmgMaster.getAuditCustomerName(cust_name));

		md.addAttribute("menuname", "Customer Audit Log");
		md.addAttribute("auditflag", "auditflag");

		return "AMLCustomerAudit";
	}

	/*********************************************
	 * KYC PARAMETER STARTS
	 **********************************************/
	@RequestMapping(value = "kycparameter", method = { RequestMethod.GET, RequestMethod.POST })
	public String Kycparameter(@RequestParam(required = false) String formmode,
			@RequestParam(value = "srlno", required = false) String srlno,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			AML_KYC_Parameter list = kycParameterrep.parameterlist();
			md.addAttribute("menuname", "KYC PARAMETER ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			// md.addAttribute("srlno", list.getSrl_no());

			md.addAttribute("KYCParameterList", list);

		} else if (formmode.equals("Editlist")) {
			AML_KYC_Parameter list = kycParameterrep.parameterlist();
			md.addAttribute("srlno", list.getSrl_no());
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "KYC PARAMETER -Modify ");
			md.addAttribute("kycParameter", kycParameterrep.parameterDropdown());
			md.addAttribute("formmode", "Editlist"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("srlno", srlno);

			md.addAttribute("KYCParameterList", kycParameterrep.parameterlist());

		} else if (formmode.equals("verify")) {
			AML_KYC_Parameter list = kycParameterrep.parameterlist();
			md.addAttribute("srlno", list.getSrl_no());
			md.addAttribute("formmode", formmode);
			md.addAttribute("KYCParameterList", kycParameterrep.parameterlist());

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "KYC PARAMETER  - Verify");

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "KYC PARAMETER - Inquiry");
			md.addAttribute("KycParameter", kycParameterServices.getreportcode(srlno));

		}
		md.addAttribute("riskmgntflag", "riskmgntflag");
		md.addAttribute("riskcatflag", "riskcatflag");

		return "AMLKYCParameter";
	}

	@RequestMapping(value = "createKycParameter", method = RequestMethod.POST)
	@ResponseBody
	public String createKyc(@RequestParam("formmode") String formmode,
			@RequestParam(value = "srlno", required = false) String srlno, @ModelAttribute AML_KYC_Parameter alertparam,
			Model md, HttpServletRequest rq) {
		String msg = kycParameterServices.addPARAMETER(alertparam, srlno, formmode);
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "kycparameter");

		return msg;

	}

	@RequestMapping(value = "USERACTIVITIES", method = { RequestMethod.GET, RequestMethod.POST })
	public String USERACTIVITIES(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,

			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");

		final Calendar cal = Calendar.getInstance();
		/* cal.add(Calendar.DATE, -1); */
		String date = dateFormat.format(cal.getTime());
		String date1 = dateFormat1.format(cal.getTime());

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname", "User activities Audit Log");
		md.addAttribute("Fromdate", date);
		md.addAttribute("Todate", date);
		md.addAttribute("AuditList", audit_local.getauditListLocal1(date1, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("auditflag", "auditflag");
		md.addAttribute("formmode", "list1");

		return "BAML_AUDIT_LOCAL";
	}

	@RequestMapping(value = "BAMLAuditInquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditInquiry(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		md.addAttribute("AuditList",
				audit_local.getauditListLocal(fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "User activities Audit Log");
		md.addAttribute("auditflag", "auditflag");
		md.addAttribute("formmode", "list1");

		return "BAML_AUDIT_LOCAL";
	}

	@RequestMapping(value = "OperationLog", method = { RequestMethod.GET, RequestMethod.POST })
	public String OperationLog(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");

		final Calendar cal = Calendar.getInstance();
		/* cal.add(Calendar.DATE, -1); */
		String date = dateFormat.format(cal.getTime());
		String date1 = dateFormat1.format(cal.getTime());
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname", "Operational Audit Log");
		md.addAttribute("auditflag", "auditflag");
		md.addAttribute("formmode", "list1");
		md.addAttribute("AuditList",
				audit_local.getauditListOpeartionsingle(todate1, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("Fromdate", date);
		md.addAttribute("Todate", date);
		return "BAML_OPERATION_AUDIT";
	}

	@RequestMapping(value = "BAMLAuditOperation", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLAuditOperation(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		if(Fromdate.equals(Todate)) {
			System.out.println("hiii manivannan");
		}
		md.addAttribute("AuditList",
				audit_local.getauditListOpeartion(fromdate1, todate1, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Operational Audit Log");
		md.addAttribute("auditflag", "auditflag");
		md.addAttribute("formmode", "list1");

		return "BAML_OPERATION_AUDIT";
	}

	/*********************************************
	 * KYC PARAMETER ENDS
	 **********************************************/

	@RequestMapping(value = "MonitoringOperation", method = { RequestMethod.GET, RequestMethod.POST })
	public String MonitoringOperation(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("MonitoringParameter", montRepository.parameter(PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Monitoring Operation");
		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("formmode", "list");

		return "MonitoringOperation";
	}

	@RequestMapping(value = "BAMLMONITORINGOPERATION", method = { RequestMethod.GET, RequestMethod.POST })
	public String BAMLMONITORINGOPERATION(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		md.addAttribute("MonitoringParameter", montRepository.parameter(PageRequest.of(currentPage, pageSize)));
		md.addAttribute("AuditList",
				auditRep.getMonitoringList1(Fromdate, Todate, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Monitoring Operation");
		md.addAttribute("monitoringflag", "monitoringflag");
		md.addAttribute("formmode", "list1");

		return "MonitoringOperation";
	}

	/*************** BAML STR STARTS ******************/

	@RequestMapping(value = "BAMLSTRInternal", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLSTRInternal(@RequestParam(required = false) String formmode,
			@RequestParam(value = "dataId", required = false) String dataid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "STR Reports");
		md.addAttribute("menuname", "REGISTER OF DISCLOSURES - Internal");
		// md.addAttribute("STR_REF_NO", baml_STR_SERVICE.getSrlNoValue());
		md.addAttribute("STRInternal", bAML_STR_Internal.InternalList(PageRequest.of(currentPage, pageSize)));
		md.addAttribute("strreport", "strreport");
		return "BAMLSTRInternal";
	}

	@RequestMapping(value = "BAMLSTRExternal", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLSTRExternal(@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "report_id", required = false) String report_id,
			@RequestParam(value = "formmode", required = false) String formmode,

			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws FileNotFoundException, JRException, SQLException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("aml_str_rep", new AML_STR_ENTITY());
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("STR_REPORT", str_rep.reportlist(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("downlaod")) {
			md.addAttribute("formmode", "download");
			md.addAttribute("output_path", baml_STR_SERVICE.getFile(report_id));
		}

		md.addAttribute("menu", "STR Reports");
		md.addAttribute("menuname", "REGISTER OF EXTERNAL DISCLOSURES - External");
		md.addAttribute("strreport", "strreport");
		return "BAMLSTRExternal";

	}

	@RequestMapping(value = "BAMLSTR", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLSTR(@RequestParam(required = false) String formmode, @RequestParam(required = false) String tranid,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("STR_REF_NO", baml_STR_SERVICE.getSrlNoValue());
		md.addAttribute("menu", "STR Reports");
		md.addAttribute("menuname", "INTERNAL REPORT OF SUSPICIOUS TRANSACTIONS ");
		md.addAttribute("strreport", "strreport");
		return "BAMLSTR";

	}

	@RequestMapping(value = "InternalReportOfSuspiciousTransactions", method = RequestMethod.POST)
	@ResponseBody
	public String InternalReportOfSuspiciousTransactions(@RequestParam(required = false) String user,
			@ModelAttribute BAML_STR bAML_STR, @ModelAttribute BAML_STR_Internal bAML_STR_Internal, Model md,
			HttpServletRequest rq) {

		String msg = baml_STR_SERVICE.addReport(bAML_STR, bAML_STR_Internal);
		md.addAttribute("strreport", "strreport");

		return msg;

	}

	@RequestMapping(value = "STR_REPORTDOWNLOAD", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource STR_REPORTDOWNLOAD(HttpServletResponse response,
			@RequestParam("str_ref_no") String str_ref_no,

			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "reportingTime", required = false) String reportingTime,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			// logger.info("Getting download File :"+report_id+", FileType :"+filetype+"");
			File repfile = baml_STR_SERVICE.getFile1(str_ref_no);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "STR_REPORTREGISTER", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource STR_REPORTRegister(HttpServletResponse response,
			@RequestParam("str_ref_no") String str_ref_no,

			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "reportingTime", required = false) String reportingTime,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			// logger.info("Getting download File :"+report_id+", FileType :"+filetype+"");
			File repfile = baml_STR_SERVICE.getFile2(str_ref_no);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	/*********************************************
	 * CASE MANAGEMENT LIST STARTS
	 **********************************************/

	@RequestMapping(value = "CaseList", method = { RequestMethod.GET, RequestMethod.POST })
	public String CaseList(@RequestParam(required = false) String formmode, @RequestParam(required = false) String Id,
			@RequestParam(required = false) String cust_id, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "AMLCaseManagement - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("CaseList", aml_Case_List_Rep.caselist(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("caselist")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("menuname1", "AML Case Management- Modify");
			md.addAttribute("Modi", aml_Case_List_Rep.findByIdCustom(cust_id));

		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("menuname1", "AML Case Management- Modify");
			md.addAttribute("Modi", aml_Case_List_Rep.findByIdCustom(cust_id));

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("Modi", aml_Case_List_Rep.findByIdCustom(cust_id));
			md.addAttribute("menuname1", "AML Case Management- Verify");

		} else if (formmode.equals("casestudy")) {
			md.addAttribute("formmode", "casestudy");
			md.addAttribute("menuname1", "Case Management Sheet");
			md.addAttribute("Modi", aml_Case_List_Rep.findByIdCustom(cust_id));
			md.addAttribute("comments", baml_case_Sheet_Rep.findByIdCustom(cust_id));

		} else if (formmode.equals("addcomments")) {
			md.addAttribute("CASESRL_REF_NO", CMgmt.getSrlNoValue());
			md.addAttribute("formmode", "addcomments");
			md.addAttribute("menuname1", "Case Management Sheet - Add");
			md.addAttribute("Modi", aml_Case_List_Rep.findByIdCustom(cust_id));
			md.addAttribute("comments", baml_case_Sheet_Rep.findByIdCustom(cust_id));

		} else if (formmode.equals("editcomments")) {
			md.addAttribute("formmode", "editcomments");
			md.addAttribute("menuname1", "Case Management Sheet - Modify");
			md.addAttribute("comments", baml_case_Sheet_Rep.findByIdCustom(cust_id));

		} else if (formmode.equals("verifycomments")) {
			md.addAttribute("formmode", "verifycomments");
			md.addAttribute("menuname1", "Case Management Sheet - Verify");
			md.addAttribute("comments", baml_case_Sheet_Rep.findByIdCustom(cust_id));
		} else if (formmode.equals("docadd")) {
			md.addAttribute("CASEDOC_REF_NO", CMgmt.getSrlNoValue1());
			md.addAttribute("formmode", "docadd");
			md.addAttribute("menuname1", "Case Document - Add");
			md.addAttribute("Modi", aml_Case_List_Rep.findByIdCustom(cust_id));
		} else if (formmode.equals("docview")) {
			md.addAttribute("formmode", "docview");
			md.addAttribute("menuname1", "Case Document - View");
			md.addAttribute("comments", baml_Case_Docs_Rep.findByIdCustom(cust_id));
		}

		return "AMLCaseManagementList";
	}

	@RequestMapping(value = "createCaseMgmt", method = RequestMethod.POST)
	@ResponseBody
	public String CreateCaseList(@RequestParam("formmode") String formmode,
			@ModelAttribute AML_Cust_Case_Mgmt aml_Cust_Case_Mgmt,
			@ModelAttribute BAML_Cust_Case_Docs baml_Cust_Case_Docs,
			@ModelAttribute BAML_Cust_Case_Sheet baml_Cust_Case_Sheet, Model md, HttpServletRequest rq) {

		String msg = CMgmt.addCases(aml_Cust_Case_Mgmt, baml_Cust_Case_Sheet, baml_Cust_Case_Docs, formmode);

		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "monitoringparameter");

		return msg;

	}

	@RequestMapping(value = "CaseDocAdd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String CaseDocAdd(@RequestParam(required = false) String formmode,
			@RequestParam(value = "CUSTID", required = false) String CUSTID,
			@RequestParam(value = "SRLNO", required = false) String SRLNO,
			@RequestParam(value = "CustName", required = false) String CustName,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@ModelAttribute BAML_Cust_Case_Docs baml_Cust_Case_Docs,
			@ModelAttribute AML_Cust_Case_Mgmt aml_Cust_Case_Mgmt,
			@ModelAttribute BAML_Cust_Case_Sheet baml_Cust_Case_Sheet, Model md, HttpServletRequest req,
			@RequestParam MultipartFile file) throws IOException {

		byte[] byteArr = file.getBytes();
		baml_Cust_Case_Docs.setDoc_image(byteArr);
		String msg = CMgmt.addCases(aml_Cust_Case_Mgmt, baml_Cust_Case_Sheet, baml_Cust_Case_Docs, formmode);
		md.addAttribute("Adddoc", msg);
		return msg;

	}

	@RequestMapping(value = "getCaseCust", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCaseCust(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String Cust, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String mail_id, @RequestParam(required = false) String cust_short_name,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("caselist")) {
			md.addAttribute("formmode", "caselist");
			md.addAttribute("CASESRLNO", CMgmt.getSrlNoValue2());
			md.addAttribute("Cust", cmgMaster.getcustId(custid));

		}
		// md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCaseManagementList";
	}

	@RequestMapping(value = "getCaseCust1", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCaseCustName(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String custname, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String mail_id, @RequestParam(required = false) String cust_short_name,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("SearchList")) {
			md.addAttribute("formmode", "SearchList");
			// md.addAttribute("CASESRLNO", CMgmt.getSrlNoValue2());
			md.addAttribute("Cust", cmgMaster.getcustname(custname));

		}
		// md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCaseManagementList";
	}

	@RequestMapping(value = "getCaseCust2", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCaseCust2(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String custname, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String mail_id, @RequestParam(required = false) String cust_short_name,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("SearchList")) {
			md.addAttribute("formmode", "SearchList");
			// md.addAttribute("CASESRLNO", CMgmt.getSrlNoValue2());
			md.addAttribute("Cust", cmgMaster.getmob_no(mob_no));

		}
		// md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCaseManagementList";
	}

	@RequestMapping(value = "getCaseCust3", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCaseCust3(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String custname, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String mail_id, @RequestParam(required = false) String cust_short_name,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("caselist")) {
			md.addAttribute("formmode", "caselist");
			md.addAttribute("CASESRLNO", CMgmt.getSrlNoValue2());
			md.addAttribute("Cust", cmgMaster.getnational_id_card_num(national_id_card_num));
		}

		return "AMLCaseManagementList";
	}

	@RequestMapping(value = "getCaseCust4", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCaseCust4(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String custname, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String mail_id, @RequestParam(required = false) String cust_short_name,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("caselist")) {
			md.addAttribute("formmode", "caselist");
			md.addAttribute("CASESRLNO", CMgmt.getSrlNoValue2());
			md.addAttribute("Cust", cmgMaster.getmail_id(mail_id));

		}
		// md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCaseManagementList";
	}

	@RequestMapping(value = "getCaseCust5", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCaseCust5(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String custid, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String custname, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String mob_no, @RequestParam(required = false) String national_id_card_num,
			@RequestParam(required = false) String mail_id, @RequestParam(required = false) String cust_short_name,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("SearchList")) {
			md.addAttribute("formmode", "SearchList");
			// md.addAttribute("CASESRLNO", CMgmt.getSrlNoValue2());
			md.addAttribute("Cust", cmgMaster.getcust_short_name(cust_short_name));

		}
		// md.addAttribute("inquiryflag", "inquiryflag");

		return "AMLCaseManagementList";
	}

	@RequestMapping(value = "AML_SCR_OPERATION_Details", method = { RequestMethod.GET, RequestMethod.POST })
	public String Screening_Operation_Details(@RequestParam(value = "formmode", required = false) String formmode,
			@RequestParam(value = "cust_id", required = false) String cif,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		Date date1 = null;

		List<BAML_SCR_Alert_Oper_Entity> custMaster_List = new ArrayList<BAML_SCR_Alert_Oper_Entity>();

		List<Object[]> lst_Objects = baml_SCR_Alert_Oper_Repository.findAllCustIdforLoansCustType_by_custId(cif);

		for (Object[] obj : lst_Objects) {
			BAML_SCR_Alert_Oper_Entity info = new BAML_SCR_Alert_Oper_Entity();
			info.setCUST_ID(String.valueOf(obj[0]));
			info.setACID(String.valueOf(obj[2]));
			info.setFORACID(String.valueOf(obj[3]));
			info.setACCT_NAME(String.valueOf(obj[4]));
			info.setACCT_OPN_DATE((Date) obj[5]);
			info.setSANCT_LIM((BigDecimal) obj[6]);

			info.setDIS_AMT(info.getSANCT_LIM());
			info.setTRAN_AMT((BigDecimal) obj[6]);
			info.setDATE_DIS((Date) obj[5]);
			info.setREMARKS(String.valueOf(obj[7]));
//			BigDecimal obj1 = new BigDecimal(loanlimit);
			custMaster_List.add(info);
		}
		if (custMaster_List.size() > 0) {

		} else {
			md.addAttribute("loanCount", "0");
		}

		md.addAttribute("scrList", custMaster_List);

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("screeningoperation", "screeningoperation");

		return "AML_SCR_Alert_Operations_Details";

	}

	/*************************************
	 * Monitoring -----> Transaction Monitoring starts
	 ****************************************/
	@RequestMapping(value = "AML_SCR_OPERATION", method = { RequestMethod.GET, RequestMethod.POST })
	public String Screening_Operation(@RequestParam(value = "formmode", required = false) String formmode,
			@RequestParam(value = "rulecode", required = false) String rulecode,
			@RequestParam(value = "tranamount", required = false) String tranamount,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "loanday", required = false) String loanday,
			@RequestParam(value = "TRANTYPE", required = false) String TRANTYPE,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "gl_sub_head_code", required = false) String gl_sub_head_code,

			@RequestParam(value = "cust_othercondition", required = false) String cust_othercondition,
			@RequestParam(value = "cust_remarks", required = false) String cust_remarks,

			@RequestParam(value = "cust_subType", required = false) String cust_subType,
			@RequestParam(value = "schme_code", required = false) String schme_code,
			@RequestParam(value = "loan_limit", required = false) BigDecimal loanlimit,
			@RequestParam(value = "cust_type", required = false) String cust_type,
			@RequestParam(value = "loan_scheme", required = false) String loan_scheme,
			@RequestParam(value = "conditions", required = false) String conditions,
			@RequestParam(value = "ruletype", required = false) String ruletype,
			@RequestParam(value = "rulefield", required = false) String rulefield,

			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		Date date1 = null;
		if (formmode == null || formmode.equals("Cust_list")) {
			md.addAttribute("menu", "AMLTransMonitoring");
			md.addAttribute("formmode", "Cust_list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menuname", "Customer Type And Limits Operations");
			md.addAttribute("transactionMonitoring", aml_scr_parm_cust_type_repository.findAllCustom());

			if (rulecode != null) {

				if (today == null) {
					Date date = new Date();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String strDate = dateFormat.format(date);
					today = strDate;
				}

				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(today);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<BAML_SCR_Alert_Oper_Entity> custMaster_List = new ArrayList<BAML_SCR_Alert_Oper_Entity>();
				List<BAML_SCR_Alert_Oper_Entity> custMaster_finalList = new ArrayList<BAML_SCR_Alert_Oper_Entity>();
				List<BAML_AUDIT_TRAIL_OUT> Audit_trail = new ArrayList<BAML_AUDIT_TRAIL_OUT>();
				String up_occ =cust_type.toUpperCase();
				String occ_code = cmgMaster.getOccupationcode(up_occ);
				System.out.println("OCCUPATION - "+occ_code);
				List<Object[]> lst_Objects = baml_SCR_Alert_Oper_Repository.findAllCustIdforLoansCustType(schme_code,
						gl_sub_head_code, date1,occ_code);

				for (Object[] obj : lst_Objects) {
					BAML_SCR_Alert_Oper_Entity info = new BAML_SCR_Alert_Oper_Entity();
//							try {
//								info.setTran_date(new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[0])));
//							} catch (ParseException e) {
//							}
					info.setCUST_ID(String.valueOf(obj[0]));
					info.setACID(String.valueOf(obj[2]));
					info.setFORACID(String.valueOf(obj[3]));
					info.setACCT_NAME(String.valueOf(obj[4]));
					info.setACCT_OPN_DATE((Date) obj[5]);
					info.setSANCT_LIM((BigDecimal) obj[6]);
					info.setGL_SUB_HEAD_CODE(gl_sub_head_code);
					info.setSCHM_CODE(schme_code);
					info.setDIS_AMT(info.getSANCT_LIM());
					info.setTRAN_AMT((BigDecimal) obj[6]);
					info.setDATE_DIS((Date) obj[5]);
//					BigDecimal obj1 = new BigDecimal(loanlimit);
					info.setCEILING(loanlimit);
					custMaster_List.add(info);
				}

				for (BAML_SCR_Alert_Oper_Entity info1 : custMaster_List) {
					List<Object[]> lst_Ob = baml_SCR_Alert_Oper_Repository
							.findAllCustIdforLoansLimit(info1.getCUST_ID(), info1.getCEILING());

					if (lst_Ob != null && !lst_Ob.isEmpty()) {
						Object[] ob = lst_Ob.get(0);
//						//using this field to display the total sanct limt
						info1.setDIS_AMT((BigDecimal) ob[0]);
						custMaster_finalList.add(info1);
					}
				}
				if (custMaster_finalList.size() > 0) {

				} else {
					md.addAttribute("loanCount", "0");
				}

				md.addAttribute("TransactionMaster", custMaster_finalList);
//						md.addAttribute("TransactionMaster",baml_SCR_Alert_Oper_Repository.getTransactionDetails(PageRequest.of(currentPage,pageSize), rulecode));
				md.addAttribute("today", today);
				md.addAttribute("rulecode", rulecode);
				md.addAttribute("cust_type", cust_type);
				md.addAttribute("cust_subType", cust_subType);
				md.addAttribute("loanlimit", loanlimit);
				md.addAttribute("schme_code", schme_code);
				md.addAttribute("gl_sub_head_code", gl_sub_head_code);
				if (cust_othercondition != null && cust_othercondition.equals("null")) {
					cust_othercondition = "";
				}
				if (cust_remarks != null && cust_remarks.equals("null")) {
					cust_remarks = "";
				}
				if (cust_othercondition == null) {
					cust_othercondition = "";
				}
				if (cust_remarks == null) {
					cust_remarks = "";
				}
				md.addAttribute("cust_othercondition", cust_othercondition);
				md.addAttribute("cust_remarks", cust_remarks);

				md.addAttribute("formmode_cust", "tranlist");
			}

		} else if (formmode.equals("Loan_list")) {
			md.addAttribute("menu", "AMLTransMonitoring");
			md.addAttribute("formmode", "Loan_list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menuname", "Loan Schemes And Limits Operations");
			md.addAttribute("transactionMonitoringschm", aml_scr_parm_Schm_type_repository.findAllCustom());

			if (rulecode != null) {

				if (loanday == null) {
					Date date = new Date();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String strDate = dateFormat.format(date);
					loanday = strDate;
				}
				Date date2 = null;
				try {
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(loanday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<BAML_SCR_Alert_Oper_Entity> custMaster_List = new ArrayList<BAML_SCR_Alert_Oper_Entity>();
				List<BAML_SCR_Alert_Oper_Entity> custMaster_finalList = new ArrayList<BAML_SCR_Alert_Oper_Entity>();

				List<Object[]> lst_Objects = baml_SCR_Alert_Oper_Repository.findAllCustIdforLoans(schme_code,
						gl_sub_head_code, date2);

				for (Object[] obj : lst_Objects) {
					BAML_SCR_Alert_Oper_Entity info = new BAML_SCR_Alert_Oper_Entity();
//						try {
//							info.setTran_date(new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[0])));
//						} catch (ParseException e) {
//						}
					info.setCUST_ID(String.valueOf(obj[0]));
					info.setACID(String.valueOf(obj[2]));
					info.setFORACID(String.valueOf(obj[3]));
					info.setACCT_NAME(String.valueOf(obj[4]));
					info.setACCT_OPN_DATE((Date) obj[5]);
					info.setSANCT_LIM((BigDecimal) obj[6]);
					info.setGL_SUB_HEAD_CODE(gl_sub_head_code);
					info.setSCHM_CODE(schme_code);
					info.setDIS_AMT(info.getSANCT_LIM());
					info.setTRAN_AMT((BigDecimal) obj[6]);
					info.setDATE_DIS((Date) obj[5]);
//					BigDecimal obj1 = new BigDecimal(loanlimit);
					info.setCEILING(loanlimit);
					custMaster_List.add(info);
				}

				for (BAML_SCR_Alert_Oper_Entity info1 : custMaster_List) {
					List<Object[]> lst_Ob = baml_SCR_Alert_Oper_Repository
							.findAllCustIdforLoansLimit(info1.getCUST_ID(), info1.getCEILING());

					if (lst_Ob != null && !lst_Ob.isEmpty()) {
//						Object[] ob = lst_Ob.get(0);
//						info1.setDIS_AMT((BigDecimal) ob[0]);
						custMaster_finalList.add(info1);
					}
				}
				if (custMaster_finalList.size() > 0) {

				} else {
					md.addAttribute("loanCount", "0");
				}

				md.addAttribute("TransactionMaster", custMaster_finalList);
//						md.addAttribute("TransactionMaster",baml_SCR_Alert_Oper_Repository.getTransactionDetails(PageRequest.of(currentPage,pageSize), rulecode));
				md.addAttribute("loanday", loanday);
				md.addAttribute("rulecode1", rulecode);
				md.addAttribute("loanlimit1", loanlimit);
				md.addAttribute("schme_code1", schme_code);
				md.addAttribute("loan_scheme", loan_scheme);
				md.addAttribute("conditions", conditions);
				md.addAttribute("gl_sub_head_code1", gl_sub_head_code);

				md.addAttribute("formmode_loan", "tranlistloan");
			}

		} else if (formmode.equals("Opr_list")) {
			md.addAttribute("formmode", "Opr_list");
			md.addAttribute("menuname", "Operations Parameter");
			md.addAttribute("ruletype", ruletype);
			md.addAttribute("ruletype", ruletype);
			md.addAttribute("rulefield", rulefield);
			md.addAttribute("MonitoringParameter", montRepository.parameter(PageRequest.of(currentPage, pageSize)));
			if (Fromdate != null) {
				Date date11 = null;
				Date date21 = null;
				try {
					date11 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
					date21 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
				} catch (ParseException e) {

				}
				/*
				 * List<BAML_AUDIT_TRAIL_OUT> Audit_trail = new
				 * ArrayList<BAML_AUDIT_TRAIL_OUT>();
				 * 
				 * Page<BAML_AUDIT_TRAIL_OUT> lst_Ob =
				 * bAML_AUDITTRAIL_REP.getMonitoringListaudit(date11, date21, ruletype,
				 * PageRequest.of(currentPage, pageSize)); for (Object[] obj : lst_Ob) {
				 * BAML_AUDIT_TRAIL_OUT info = new BAML_AUDIT_TRAIL_OUT();
				 * 
				 * info.setTitle(String.valueOf(obj[0]));
				 * info.setAuditedid(String.valueOf(obj[1]));
				 * info.setOrgkey(String.valueOf(obj[2]));
				 * info.setCust_name(String.valueOf(obj[3]));
				 * info.setEntry_user(String.valueOf(obj[4]));
				 * info.setVerify_user(String.valueOf(obj[5])); Date Entry_date = (Date) obj[6];
				 * info.setEntry_date(Entry_date); Date Verify_date = (Date) obj[7];
				 * info.setVerify_date(Verify_date); info.setNew_value(String.valueOf(obj[8]));
				 * info.setOld_value(String.valueOf(obj[9]));
				 * 
				 * Audit_trail.add(info); }
				 * 
				 */

				md.addAttribute("AuditList", bAML_AUDITTRAIL_REP.getMonitoringListaudit(date11, date21, ruletype,
						PageRequest.of(currentPage, pageSize)));

			}
			if (Fromdate != null) {
				Date date111 = null;
				Date date211 = null;
				try {
					date111 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
					date211 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				md.addAttribute("Fromdate", Fromdate);
				md.addAttribute("Todate", Todate);
				md.addAttribute("ruletype", ruletype);
				md.addAttribute("rulefield", rulefield);
				md.addAttribute("schmcode", "master");
				md.addAttribute("menuname", "Operations Parameter");
				md.addAttribute("formmode_opr", "tranlistopr");

			}

		}

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("screeningoperation", "screeningoperation");

		return "AML_SCR_Operations";
	}

	/*************************************
	 * Screening -----> alert operations starts
	 ****************************************/

	@RequestMapping(value = "AML_SCR_RPT_Download", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_SCR_RPT_Download(Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("reportvalue", "Screening Report Download");
		md.addAttribute("reportid", "AML_SCR_RPT_Download");
		md.addAttribute("menu", "Screening Report Download");
		String domainid = (String) req.getSession().getAttribute("DOMAINID");

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("screeningoperation", "screeningoperation");

		return "AML_SCR_RPT_Download";

	}

	/* AML CUSTOMER CHECKS */
	@RequestMapping(value = "AMLCustomerChecks", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLCustomerChecks(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String refno, @RequestParam(required = false) String Fromdate,
			@RequestParam(required = false) String Todate, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "Customer Checks");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("MonitoringParameterList",
					bamlCustomerChecksRepo.customerchecklist(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("view")) {
			BigDecimal ref = new BigDecimal(refno);
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Customer Checks- INQUIRY");
			md.addAttribute("MonitorParameter", bamlCustomerChecksService.getRefNo(ref));

		}

		md.addAttribute("menu", "AML Customer Checks");
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustomerChecks";

	}

	@RequestMapping(value = "AML_SCR_Cust_Values", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_SCR_Values(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String AcctNumber,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menuname1", "LIST OF CUSTOMERS WHERE VALUES ARE MISSING");
		md.addAttribute("ScreenCustomer", cmgMaster.findAllCustom1(cust_id));

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("screeningoperation", "screeningoperation");

		return "AML_SCR_Values";

	}

	@RequestMapping(value = "AML_SCR_Acct_Values", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_SCR_Acct_Values(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String AcctNumber,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname1", "LIST OF ACCOUNTS WHERE VALUES ARE MISSING");
		md.addAttribute("ScreenAccount", ACCTMaster.findAllCustom1());

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("screeningoperation", "screeningoperation");

		return "AML_SCR_Values_acct";

	}

	@RequestMapping(value = "AML_Risk_Cust_Reports", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_Risk_Cust_Reports(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String AcctNumber,
			@RequestParam(value = "reportid", required = false) String reportid,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "ratvalue", required = false) String ratvalue,
			@RequestParam(value = "tablecol", required = false) String tablecol,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname1", "LIST OF ACCOUNTS WHERE VALUES ARE MISSING");
		md.addAttribute("ScreenAccount", ACCTMaster.findAllCustom1());
		md.addAttribute("reportid", "RiskCustService");
		md.addAttribute("fromdate", "01/10/2019");
		md.addAttribute("todate", "31/12/2019");
		md.addAttribute("displaymode", "date");
		// md.addAttribute("reportsummary",
		// rISKREVIEWREP.RISKREVIEW("01/10/2019","31/12/2019"));
		// md.addAttribute("reportsummary", rISKREVIEWREP.RISKREVIEW(fromdate,todate));
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "ReportRiskCust";

	}

	@RequestMapping(value = "AML_Risk_Cust_Reports_Summary", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_Risk_Cust_Reports_summary(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String AcctNumber,
			@RequestParam(value = "reportid", required = false) String reportid,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "ratvalue", required = false) String ratvalue,
			@RequestParam(value = "tablecol", required = false) String tablecol,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname1", "LIST OF ACCOUNTS WHERE VALUES ARE MISSING");
		md.addAttribute("ScreenAccount", ACCTMaster.findAllCustom1());
		md.addAttribute("reportid", "RiskCustService");
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("displaymode", "Summary");
		md.addAttribute("reportsummary", rISKREVIEWREP.RISKREVIEW(fromdate, todate));
		// md.addAttribute("reportsummary", rISKREVIEWREP.RISKREVIEW(fromdate,todate));
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "ReportRiskCust";

	}

	@RequestMapping(value = "AML_Risk_Cust_Reports_Detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_Risk_Cust_Reports_Detail(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String AcctNumber,
			@RequestParam(value = "reportid", required = false) String reportid,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "ratvalue", required = false) String ratvalue,
			@RequestParam(value = "tablecol", required = false) String tablecol,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname1", "LIST OF ACCOUNTS WHERE VALUES ARE MISSING");
		md.addAttribute("ScreenAccount", ACCTMaster.findAllCustom1());
		md.addAttribute("reportid", "RiskCustService");
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("displaymode", "Detail");
		md.addAttribute("reportdetail",
				rISKREVIEWDETAILREP.RISKREVIEWDetail(fromdate, todate, PageRequest.of(currentPage, pageSize)));
		// md.addAttribute("reportsummary", rISKREVIEWREP.RISKREVIEW(fromdate,todate));
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "ReportRiskCust";

	}

	@RequestMapping(value = "AML_Risk_Cust_Reports_Detail_Page", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_Risk_Cust_Reports_Detail_Page(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String AcctNumber,
			@RequestParam(value = "reportid", required = false) String reportid,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "ratvalue", required = false) String ratvalue,
			@RequestParam(value = "tablecol", required = false) String tablecol,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname1", "LIST OF ACCOUNTS WHERE VALUES ARE MISSING");
		md.addAttribute("ScreenAccount", ACCTMaster.findAllCustom1());
		md.addAttribute("reportid", "RiskCustService");
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("displaymode", "Detail");
		md.addAttribute("reportdetail",
				rISKREVIEWDETAILREP.RISKREVIEWDetailpage(fromdate, todate, PageRequest.of(currentPage, pageSize)));
		// md.addAttribute("reportsummary", rISKREVIEWREP.RISKREVIEW(fromdate,todate));
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "ReportRiskCust";

	}

	@RequestMapping(value = "AMLFormReports", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLFormrep(@RequestParam(value = "reportid") String reportid, Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		String reportvalue = null;

		/********* date calculation ********/
		Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
		int quarter = (calendar.get(Calendar.MONTH) / 3) + 1;

		int year = calendar.get(Calendar.YEAR);

		switch (quarter) {

		case 1:

			md.addAttribute("fromDate", "01-OCT-" + (year - 1));
			md.addAttribute("toDate", "31-DEC-" + (year - 1));
			break;

		case 2:

			md.addAttribute("fromDate", "01-JAN-" + (year));
			md.addAttribute("toDate", "31-MAR-" + (year));
			break;

		case 3:

			md.addAttribute("fromDate", "01-APR-" + (year));
			md.addAttribute("toDate", "30-JUN-" + (year));
			break;

		case 4:

			md.addAttribute("fromDate", "01-JUL-" + (year));
			md.addAttribute("toDate", "30-SEP-" + (year));
			break;

		default:
		}

		/********* date calculation ********/

		switch (reportid) {

		case "t11":
			reportvalue = "T11 - Transactions terminated or not processed due to concerns about CDD";
			break;

		case "t17":
			reportvalue = "Transaction Monitoring Systems (pick from appropriate drop down options (blue cells) where available)";
			break;

		case "t20":
			reportvalue = "Suspicion Reports";
			break;

		case "t24":
			reportvalue = "Internal audit AML-CFT reviews/checks";
			break;

		case "t25":
			reportvalue = " Application of NBDTI's AML-CFT risk management framework to";
			break;

		}
		md.addAttribute("reportvalue", reportvalue);
		md.addAttribute("reportid", reportid);
		md.addAttribute("menu", reportid);
		String domainid = (String) req.getSession().getAttribute("DOMAINID");

		md.addAttribute("reportsflag", "reportsflag");

		return "AMLFormReports";
	}

	@RequestMapping(value = "AML_Risk_Reports", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_Risk_Reports(@RequestParam(value = "formmode", required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "riskcategory", required = false) String riskcategory,

			@RequestParam(value = "fromdaterss", required = false) String fromdaterss,
			@RequestParam(value = "todaterss", required = false) String todaterss,
			@RequestParam(value = "riskcategoryrss", required = false) String riskcategoryrss,

			@RequestParam(value = "fromdatedep", required = false) String fromdatedep,
			@RequestParam(value = "todatedep", required = false) String todatedep,
			@RequestParam(value = "riskcategorydep", required = false) String riskcategorydep,

			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		List<String> s = new ArrayList<String>();
		s.add("HIGH");
		s.add("MEDIUM");
		s.add("LOW");
		if (formmode == null || formmode.equals("Cust_list")) {
			md.addAttribute("menu", "AMLTransMonitoring");
			md.addAttribute("formmode", "Cust_list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menuname", "Loan Monitoring Report Per Risk Category");
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

			String fromDAte = null;
			String toDAte = null;

			if (fromdate != null && !fromdate.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(fromdate);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(todate);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}
			if (toDAte != null && !toDAte.isEmpty()) {
				md.addAttribute("TransactionMaster", baml_Risk_Category_RPT_Repository.getLoanData(fromDAte, toDAte,
						riskcategory, PageRequest.of(currentPage, pageSize)));
			} else {
				md.addAttribute("TransactionMaster", baml_Risk_Category_RPT_Repository
						.getLoanData(PageRequest.of(currentPage, pageSize), riskcategory));
			}
//			if(todate!=null && !todate.isEmpty() ) {
//				Date fromDateL=new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
//				Date todateL=new SimpleDateFormat("dd/MM/yyyy").parse(todate);
//				
//				md.addAttribute("TransactionMaster", baml_Risk_Category_RPT_Repository
//						.getLoanData(PageRequest.of(currentPage, pageSize), fromDateL, todateL, riskcategory));
//			}else {
//				
//				md.addAttribute("TransactionMaster", baml_Risk_Category_RPT_Repository
//						.getLoanData(PageRequest.of(currentPage, pageSize), riskcategory));
//				
//			}
			md.addAttribute("fromdateloan", fromdate);
			md.addAttribute("todateloan", todate);

			md.addAttribute("riskcategory", s);
			md.addAttribute("riskcategory1", riskcategory);
			md.addAttribute("formmode_cust", "tranlist");

		} else if (formmode.equals("Loan_list")) {
			md.addAttribute("menu", "AMLTransMonitoring");
			md.addAttribute("formmode", "Loan_list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menuname", "Retirement Saving Scheme Report Per Risk Category");

			if (todaterss != null && !todaterss.isEmpty()) {
				Date fromDateR = new SimpleDateFormat("dd/MM/yyyy").parse(fromdaterss);
				Date todateR = new SimpleDateFormat("dd/MM/yyyy").parse(todaterss);
				md.addAttribute("rsslist", baml_Risk_Category_RPT_Repository
						.getRSSData(PageRequest.of(currentPage, pageSize), fromDateR, todateR, riskcategoryrss));

			} else {
				md.addAttribute("rsslist", baml_Risk_Category_RPT_Repository
						.getRSSData(PageRequest.of(currentPage, pageSize), riskcategoryrss));

			}
			md.addAttribute("fromdaterss", fromdaterss);
			md.addAttribute("todaterss", todaterss);
			md.addAttribute("riskcategory", s);
			md.addAttribute("riskcategory1", riskcategoryrss);
//				md.addAttribute("riskcategoryrss", riskcategoryrss);

			md.addAttribute("formmode_loan", "tranlistloan");

		} else if (formmode.equals("Opr_list")) {
			md.addAttribute("formmode", "Opr_list");
			md.addAttribute("menuname", "Deposits Report Per Risk Category");

			if (todatedep != null && !todatedep.isEmpty()) {
				Date fromDateD = new SimpleDateFormat("dd/MM/yyyy").parse(fromdatedep);
				Date todateD = new SimpleDateFormat("dd/MM/yyyy").parse(todatedep);
				md.addAttribute("depositlist", baml_Risk_Category_RPT_Repository
						.getDepositData(PageRequest.of(currentPage, pageSize), fromDateD, todateD, riskcategorydep));
			} else {
				md.addAttribute("depositlist", baml_Risk_Category_RPT_Repository
						.getDepositData(PageRequest.of(currentPage, pageSize), riskcategorydep));

			}

			md.addAttribute("fromdatedep", fromdatedep);
			md.addAttribute("todatedep", todatedep);
			md.addAttribute("riskcategory", s);
			md.addAttribute("riskcategory1", riskcategorydep);
//				md.addAttribute("riskcategorydep", riskcategorydep);

			md.addAttribute("formmode_opr", "tranlistopr");

		}

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AML_Risk_Reports";
	}

//	@RequestMapping(value = "AML_SCR_Alert_Operations", method = { RequestMethod.GET, RequestMethod.POST })
//	public String AML_SCR_Alert_Operations(@RequestParam(value = "formmode", required = false) String formmode,
//			@RequestParam(value = "rulecode", required = false) String rulecode,
//			@RequestParam(value = "tranamount", required = false) String tranamount,
//			@RequestParam(value = "today", required = false) String today,
//			@RequestParam(value = "loanday", required = false) String loanday,
//			@RequestParam(value = "TRANTYPE", required = false) String TRANTYPE,
//			@RequestParam(value = "fromdate", required = false) String fromdate,
//			@RequestParam(value = "gl_sub_head_code", required = false) String gl_sub_head_code,
//			@RequestParam(value = "cust_subType", required = false) String cust_subType,
//			@RequestParam(value = "schme_code", required = false) String schme_code,
//			@RequestParam(value = "loan_limit", required = false) String loanlimit,
//			@RequestParam(value = "cust_type", required = false) String cust_type,
//			@RequestParam(value = "loan_scheme", required = false) String loan_scheme,
//			@RequestParam(value = "conditions", required = false) String conditions,
//			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
//			@RequestParam(required = false) Optional<Integer> page,
//			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
//		String roleId = (String) req.getSession().getAttribute("ROLEID");
//		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
//		int currentPage = page.orElse(0);
//		int pageSize = size.orElse(Integer.parseInt(pagesize));
//
//		if (formmode == null || formmode.equals("Cust_list")) {
//			md.addAttribute("menu", "AMLTransMonitoring");
//			md.addAttribute("formmode", "Cust_list"); // to set which form - valid values are "edit" , "add" & "list"
//			md.addAttribute("menuname", "Customer Type And Limites Operations");
//			md.addAttribute("transactionMonitoring", aml_scr_parm_cust_type_repository.findAllCustom());
//
//			if (rulecode != null) {
//				if (today == null) {
//					Date date = new Date();
//					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//					String strDate = dateFormat.format(date);
//					today = strDate;
//				}
//						md.addAttribute("TransactionMaster",baml_SCR_Alert_Oper_Repository.getTransactionDetails(PageRequest.of(currentPage,pageSize), rulecode,today));
//				md.addAttribute("today", today);
//				md.addAttribute("rulecode", rulecode);
//				md.addAttribute("cust_type", cust_type);
//				md.addAttribute("cust_subType", cust_subType);
//				md.addAttribute("loanlimit", loanlimit);
//				md.addAttribute("schme_code", schme_code);
//				md.addAttribute("gl_sub_head_code", gl_sub_head_code);
//				md.addAttribute("formmode_cust", "tranlist");
//			}
//
//		} else if (formmode.equals("Loan_list")) {
//			md.addAttribute("menu", "AMLTransMonitoring");
//			md.addAttribute("formmode", "Loan_list"); // to set which form - valid values are "edit" , "add" & "list"
//			md.addAttribute("menuname", "Loan Schemes And Limites Operations");
//			md.addAttribute("transactionMonitoringschm", aml_scr_parm_Schm_type_repository.findAllCustom());
//
//			if (rulecode != null) {
//				if (loanday == null) {
//					Date date = new Date();
//					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//					String strDate = dateFormat.format(date);
//					loanday = strDate;
//				}
//						md.addAttribute("TransactionMaster",baml_SCR_Alert_Oper_Repository.getTransactionDetails(PageRequest.of(currentPage,pageSize), rulecode,loanday));
//				md.addAttribute("loanday", loanday);
//				md.addAttribute("rulecode1", rulecode);
//				md.addAttribute("loanlimit1", loanlimit);
//				md.addAttribute("schme_code1", schme_code);
//				md.addAttribute("loan_scheme", loan_scheme);
//				md.addAttribute("conditions", conditions);
//				md.addAttribute("gl_sub_head_code1", gl_sub_head_code);
//
//				md.addAttribute("formmode_loan", "tranlistloan");
//			}
//
//		} else if (formmode.equals("Opr_list")) {
//			md.addAttribute("formmode", "Opr_list");
//			md.addAttribute("menuname", "Operations Parameter");
//			md.addAttribute("MonitoringParameter", montRepository.parameter(PageRequest.of(currentPage, pageSize)));
//			if (Fromdate != null) {
//				md.addAttribute("Fromdate", Fromdate);
//				md.addAttribute("Todate", Todate);
//
//				md.addAttribute("AuditList",
//						auditRep.getMonitoringList1(Fromdate, Todate, PageRequest.of(currentPage, pageSize)));
//				md.addAttribute("menuname", "Monitoring Operation");
//
//				md.addAttribute("formmode_opr", "tranlistopr");
//
//			}
//
//		}
//
//		md.addAttribute("screeningflag1", "screeningflag1");
//		md.addAttribute("screeningoperation", "screeningoperation");
//
//		return "AML_SCR_Alert_Operations";
//	}

	@RequestMapping(value = "AML_SCR_Alert_Operations", method = { RequestMethod.GET, RequestMethod.POST })
	public String AML_SCR_Alert_OperationsCust(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal tranid, @RequestParam(required = false) String Fromdate,
			@RequestParam(required = false) String trandate, @RequestParam(required = false) String parttran,
			@RequestParam(required = false) String Todate, @RequestParam(required = false) String userid,
			@RequestParam(value = "fromdatecust", required = false) String fromdatecust,
			@RequestParam(value = "todatecust", required = false) String todatecust,
			@RequestParam(value = "fromdateloan", required = false) String fromdateloan,
			@RequestParam(value = "todateloan", required = false) String todateloan,
			@RequestParam(value = "ruletype", required = false) String ruletype,
			@RequestParam(value = "rulefield", required = false) String rulefield,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("Cust_list")) {
			md.addAttribute("formmode", "Cust_list");
			md.addAttribute("menuname", "Customer Type And Limits Operations");
			md.addAttribute("MonitoringParameterList",
					baml_SCR_Alert_Oper_Repository.parameterlist("C", PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("Loan_list")) {

			md.addAttribute("formmode", "Loan_list");
			md.addAttribute("menuname", "Loan Schemes And Limits Operations");
			md.addAttribute("MonitoringParameterList",
					baml_SCR_Alert_Oper_Repository.parameterlist("S", PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("datefilterCust")) {
			Date date1 = null;
			Date date2 = null;
			if (fromdatecust != null && !fromdatecust.isEmpty()) {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdatecust);
			}
			if (todatecust != null && !todatecust.isEmpty()) {
				date2 = new SimpleDateFormat("dd/MM/yyyy").parse(todatecust);
			}
			if (fromdatecust != null && !fromdatecust.isEmpty() && todatecust != null && !todatecust.isEmpty()) {
				md.addAttribute("MonitoringParameterList", baml_SCR_Alert_Oper_Repository.parameterlistdate(date1,
						date2, "C", PageRequest.of(currentPage, pageSize)));
			} else {
				md.addAttribute("MonitoringParameterList",
						baml_SCR_Alert_Oper_Repository.parameterlist("C", PageRequest.of(currentPage, pageSize)));
			}

			md.addAttribute("formmode", "Cust_list");
			md.addAttribute("menuname1", "Customer Type And Limits Operations");

			md.addAttribute("FromdateCust", fromdatecust);
			md.addAttribute("TodateCust", todatecust);
		} else if (formmode.equals("datefilterLoan")) {
			Date date1 = null;
			Date date2 = null;
			if (fromdatecust != null && !fromdatecust.isEmpty()) {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdateloan);
			}
			if (todatecust != null && !todatecust.isEmpty()) {
				date2 = new SimpleDateFormat("dd/MM/yyyy").parse(todateloan);
			}
			if (fromdatecust != null && !fromdatecust.isEmpty() && todatecust != null && !todatecust.isEmpty()) {
				md.addAttribute("MonitoringParameterList", baml_SCR_Alert_Oper_Repository.parameterlistdate(date1,
						date2, "S", PageRequest.of(currentPage, pageSize)));
			} else {
				md.addAttribute("MonitoringParameterList",
						baml_SCR_Alert_Oper_Repository.parameterlist("S", PageRequest.of(currentPage, pageSize)));
			}

			md.addAttribute("formmode", "Loan_list");
			md.addAttribute("menuname1", "Loan Schemes And Limits Operations");

			md.addAttribute("FromdateLoan", fromdateloan);
			md.addAttribute("TodateLoan", todateloan);
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Screening Operations - INQUIRY");
			md.addAttribute("MonitorParameter", bamlAlert_OperationsService.getSrlNo(tranid));

		}

		else if (formmode.equals("edit")) {
			md.addAttribute("formmode", formmode);

			/* md.addAttribute("userProfile", userProfileDao.getUser(userid)); */
			md.addAttribute("menuname1", "Screening Operations - MODIFY");
			md.addAttribute("MonitorParameter", bamlAlert_OperationsService.getSrlNo(tranid));

		} else if (formmode.equals("verify")) {
			md.addAttribute("menuname1", "Customer Type And Limits Operations - VERIFY");
			md.addAttribute("formmode", formmode);
			md.addAttribute("MonitorParameter", bamlAlert_OperationsService.getSrlNo(tranid));
			md.addAttribute("menuname1", "AML ALERT TRAN MASTER - VERIFY");

		} else if (formmode.equals("Opr_list")) {
			md.addAttribute("formmode", "Opr_list");
			md.addAttribute("menuname", "Operations Parameter");
			md.addAttribute("ruletype", ruletype);
			md.addAttribute("MonitoringParameter", montRepository.parameter(PageRequest.of(currentPage, pageSize)));
			if (Fromdate != null) {

				Date date11 = null;
				Date date21 = null;
				try {
					date11 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
					date21 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				md.addAttribute("Fromdate", date11);
				md.addAttribute("Todate", date21);
				md.addAttribute("ruletype", auditRep.getruledata(ruletype));
				md.addAttribute("rulefield", rulefield);
				md.addAttribute("schmcode", "master");
				md.addAttribute("AuditList",
						auditRep.getMonitoringList(date11, date21, ruletype, PageRequest.of(currentPage, pageSize)));
				md.addAttribute("menuname", "Operations Parameter");
				md.addAttribute("formmode_opr", "tranlistopr");

			}

		}

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("screeningoperation", "screeningoperation");

		return "AML_SCR_Alert_Operations";

	}

	@RequestMapping(value = "creatingAML_SCR_Alert_Operations", method = RequestMethod.POST)
	@ResponseBody
	public String creatingAML_SCR_Alert_Operations(@RequestParam("formmode") String formmode,
			@RequestParam(value = "tranid", required = false) BigDecimal tranid,
			@ModelAttribute BAML_SCR_Alert_Oper_Entity bamlTranAlertsMaster, Model md, HttpServletRequest rq)
			throws ParseException {
		String userid = (String) rq.getSession().getAttribute("USERID");

		// String msg = paraservices.addPARAMETER(bamlTranAlertsMaster, formmode);
		String msg = bamlAlert_OperationsService.addPARAMETER(bamlTranAlertsMaster, formmode, userid, tranid);

		md.addAttribute("screeningflag1", "screeningflag1");
		md.addAttribute("menu", "monitoringparameter");

		return msg;

	}

	@RequestMapping(value = "rbsreports", method = { RequestMethod.GET, RequestMethod.POST })
	public String RbsReports(Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		// md.addAttribute("reportvalue", "RBS Reports");
		// md.addAttribute("reportid", "RBSReports");

		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("menu", "RBS Report");

		/********* date calculation ********/
		Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
		int quarter = (calendar.get(Calendar.MONTH) / 3) + 1;

		int year = calendar.get(Calendar.YEAR);
		md.addAttribute("fromDate", "01/10/2019");
		md.addAttribute("toDate", "31/12/2019");
		/*
		 * switch (quarter) {
		 * 
		 * case 1: md.addAttribute("fromDate", "01-OCT-" + (year - 1));
		 * md.addAttribute("toDate", "31-DEC-" + (year - 1)); break;
		 * 
		 * case 2: md.addAttribute("fromDate", "01-JAN-" + (year));
		 * md.addAttribute("toDate", "31-MAR-" + (year)); break;
		 * 
		 * case 3: md.addAttribute("fromDate", "01-APR-" + (year));
		 * md.addAttribute("toDate", "30-JUN-" + (year)); break;
		 * 
		 * case 4: md.addAttribute("fromDate", "01-JUL-" + (year));
		 * md.addAttribute("toDate", "30-SEP-" + (year)); break;
		 * 
		 * default: }
		 */

		/********* date calculation ********/

		md.addAttribute("reportlist", rbsReportlist.getReportList());
		// md.addAttribute("inputreportlist", rbsReportlist.getInputReportList());

		return "RBSReports";
	}

	@RequestMapping(value = "rbsarchival", method = { RequestMethod.GET, RequestMethod.POST })
	public String Rbsarchival(Model md, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("menu", "RBS Report");
		md.addAttribute("reportDATE", t1CurProdServicesRepo.getReportList());
		md.addAttribute("reportlist", rbsReportlist.getReportList());

		return "RBSARCHIVAL";
	}

	@RequestMapping(value = "rbsarchivalform", method = { RequestMethod.GET, RequestMethod.POST })
	public String Rbsarchivalfrom(Model md, @RequestParam(value = "reportid", required = false) String reportid,
			@RequestParam(value = "repdesc", required = false) String repdesc, HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("menu", "RBS Report");
		md.addAttribute("datemodal", "datefilter");
		md.addAttribute("reportid", reportid);
		md.addAttribute("repdesc", repdesc);
		md.addAttribute("reportmodal", "Y");
		md.addAttribute("reportDATE", t1CurProdServicesRepo.getReportList());
		md.addAttribute("reportlist", rbsReportlist.getReportList());

		return "RBSARCHIVAL";
	}

	/*
	 * @RequestMapping(value = "{reportid}/Details1", method = RequestMethod.GET)
	 * public String reportDetail1(@PathVariable("reportid") String reportid,
	 * 
	 * @RequestParam(value = "instancecode", required = false) String instancecode,
	 * 
	 * @RequestParam(value = "ratvalue", required = false) String ratvalue,
	 * 
	 * @RequestParam(value = "tablecol", required = false) String tablecol,
	 * 
	 * @RequestParam(value = "asondate", required = false) String asondate,
	 * 
	 * @RequestParam("fromdate") String fromdate, @RequestParam("todate") String
	 * todate,
	 * 
	 * @RequestParam("currency") String currency,
	 * 
	 * @RequestParam(value = "subreportid", required = false) String subreportid,
	 * 
	 * @RequestParam(value = "secid", required = false) String secid,
	 * 
	 * @RequestParam(value = "dtltype", required = false) String dtltype,
	 * 
	 * @RequestParam(value = "page", required = false) Optional<Integer> page,
	 * 
	 * @RequestParam(value = "size", required = false) Optional<Integer> size,
	 * 
	 * @RequestParam(value = "reportingTime", required = false) String
	 * reportingTime, Model md, HttpServletRequest req) throws ParseException {
	 * logger.info("Getting Report Details :" + reportid);
	 * logger.info("Assigning Model Attributes :" + reportid); String roleId =
	 * (String) req.getSession().getAttribute("ROLEID");
	 * md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
	 * md.addAttribute("menu", "XBRLReports"); md.addAttribute("reportid",
	 * reportid); md.addAttribute("asondate", asondate); md.addAttribute("fromdate",
	 * fromdate); md.addAttribute("todate", todate); md.addAttribute("currency",
	 * currency); md.addAttribute("dtltype", dtltype);
	 * md.addAttribute("reportingTime", reportingTime);
	 * md.addAttribute("instancecode", Integer.parseInt(instancecode));
	 * md.addAttribute("displaymode", "detail"); md.addAttribute("displaymode",
	 * "detail"); int currentPage = page.orElse(0); int pageSize =
	 * size.orElse(Integer.parseInt(pagesize));
	 * System.out.println("Getting ModelandView :" + reportid); SimpleDateFormat
	 * dateFormat1 = new SimpleDateFormat("dd/MM/yyyy"); Date ConDate =
	 * dateFormat1.parse(todate); System.out.println(ConDate); SimpleDateFormat
	 * formatter1 = new SimpleDateFormat("dd-MMM-yyyy"); String strDate1 =
	 * formatter1.format(ConDate); md.addAttribute("reportdetails",
	 * t8rep.gett8details(strDate1, PageRequest.of(currentPage, pageSize)));
	 * ((ModelAndView) md).setViewName("ReportT8:: reportcontent");
	 * md.addAttribute("singledetail", new T5Detail());
	 * md.addAttribute("reportsflag", "reportsflag"); md.addAttribute("menu",
	 * reportid);
	 * 
	 * //ModelAndView mv = reportServices.getReportDetails(reportid, fromdate,
	 * todate, currency, dtltype, //PageRequest.of(currentPage, pageSize), ratvalue,
	 * tablecol); return "ReportT8"; }
	 * 
	 */
	@RequestMapping(value = "RejectReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String RejectedReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(required = false) String custName2, @RequestParam(required = false) String cust_id,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		System.out.println("Inside");
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("Customer")) {
			md.addAttribute("menuname", "Table 4: New Customer Applications Rejected");
			md.addAttribute("formmode", "Customer");
			md.addAttribute("reportsummary", t4ReportsRep.rejectList(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("CustomerAdd")) {
			// md.addAttribute("REJECTCUSTSLNO", inputReportRejectServices.getSrlNoValue());
			md.addAttribute("menuname1", "Table 4: New Customer Applications Rejected - Add");
			md.addAttribute("custType", t4ReportsRep.parameterDropdown());
			md.addAttribute("repDate", t4ReportsRep.date());
			md.addAttribute("CUSTREJ", inputReportRejectServices.getT4SrlNo());
			md.addAttribute("formmode", "CustomerAdd");

		} else if (formmode.equals("Account")) {

			md.addAttribute("formmode", "Account");

			md.addAttribute("menuname", "Table 4: New Account Applications Rejected");
			md.addAttribute("reportsummary", t4AccReportsRep.rejAcctList(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("AccountAdd")) {

			md.addAttribute("formmode", "AccountAdd");
			md.addAttribute("ACCREJ", inputReportRejectServices.getAccSrlNo());
			md.addAttribute("menuname1", "Table 4: New Account Applications Rejected - Add");

		} else if (formmode.equals("Transaction")) {

			md.addAttribute("formmode", "Transaction");
			md.addAttribute("menuname", "Table- 11 : Transactions terminated or not processed due to concerns\n"
					+ "										about CDD");
			// md.addAttribute("KYC", amlIndividualRepository.getDataId(dataid));
			md.addAttribute("reportdetails", t11DetailRep.rejectList(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("TransactionAdd")) {
			md.addAttribute("formmode", "TransactionAdd");
			md.addAttribute("CURDATE", t4ReportsRep.curqtrDate());
			md.addAttribute("tranCat", t4ReportsRep.dropDown1());
			md.addAttribute("TRANREJ", inputReportRejectServices.getTranSrlNo());
			md.addAttribute("menuname1", "Transactions terminated or not processed due to concerns about CDD -Add");
			// md.addAttribute("Cust", cmgMaster.getcustId(cust_id));
			md.addAttribute("Cust", cmgMaster.getcustomerName(custName2));

		} else if (formmode.equals("TransactionAdd1")) {
			System.out.println("formmode::" + formmode);
			md.addAttribute("formmode", "TransactionAdd1");
			md.addAttribute("CURDATE", t4ReportsRep.curqtrDate());
			md.addAttribute("tranCat", t4ReportsRep.dropDown1());
			md.addAttribute("TRANREJ", inputReportRejectServices.getTranSrlNo());
			md.addAttribute("menuname1", "Transactions terminated or not processed due to concerns about CDD -Add");

			md.addAttribute("CustName", cmgMaster.getcustName(cust_id));

			md.addAttribute("CustType", cmgMaster.getcustType(cust_id));

			md.addAttribute("reportsflag", "reportsflag");
		}
		return "InputRejectionReports";

	}

	@RequestMapping(value = "RejectAdd", method = RequestMethod.POST)
	@ResponseBody
	public String CustomerRejectAdd(@RequestParam("formmode") String formmode, @ModelAttribute T4Report t4Report,
			@ModelAttribute T4AccReport t4AccReport, @ModelAttribute T11Details t11Details, Model md,
			HttpServletRequest rq) throws ParseException {
		String userid = (String) rq.getSession().getAttribute("USERID");

		// String msg = paraservices.addPARAMETER(bamlTranAlertsMaster, formmode);
		String msg = inputReportRejectServices.addlist(t4Report, t4AccReport, t11Details, formmode);

		md.addAttribute("reportsflag", "reportsflag");
		return msg;

	}

	@RequestMapping(value = "T4ReportDownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource T4ReportDownload(HttpServletResponse response,
			@RequestParam("report_code") String report_code,

			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "reportingTime", required = false) String reportingTime,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			// logger.info("Getting download File :"+report_id+", FileType :"+filetype+"");
			File repfile = inputReportRejectServices.getCustFile(report_code);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "DownloadRej", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource DownloadRej(HttpServletRequest request, HttpServletResponse response,

			@RequestParam(value = "formmode", required = false) String formmode,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "reportid", required = false) String reportid) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			File repfile = inputReportRejectServices.getFile(formmode, filetype, reportid);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "TranDownloadRej", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource TranDownloadRej(HttpServletRequest request, HttpServletResponse response,

			@RequestParam(value = "formmode", required = false) String formmode,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "reportid", required = false) String reportid) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			File repfile = inputReportRejectServices.getTranFile(formmode, filetype, reportid);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "AccDownloadRej", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource AccDownloadRej(HttpServletRequest request, HttpServletResponse response,

			@RequestParam(value = "formmode", required = false) String formmode,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "reportid", required = false) String reportid) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			File repfile = inputReportRejectServices.getAccFile(formmode, filetype, reportid);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "AMLCustWhiteListingReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String custWhiteListingReport(@RequestParam(required = false) String formmode,
			@RequestParam(value = "fromdate", required = false) String from_Date,
			@RequestParam(value = "todate", required = false) String to_date,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "CustNegativeListingReport");
		md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		String fromDAte = null;
		String toDAte = null;

		if (from_Date != null && !from_Date.isEmpty()) {
			Date ConDateFromdate = dateFormat1.parse(from_Date);
			String strDate2 = formatter1.format(ConDateFromdate);
			fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

			Date ConToDate = dateFormat1.parse(to_date);
			String strDate1 = formatter1.format(ConToDate);
			toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		}

		md.addAttribute("customerBlackList", baml_cust_blacklist_rpt_repository.findAllCustWhitelistReport(fromDAte,
				toDAte, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("fromdate", from_Date);
		md.addAttribute("todate", to_date);

//			md.addAttribute("customerBlackList", negativeListRepository.paramlistforReport(PageRequest.of(currentPage, pageSize)));

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustWhiteListingReport";
	}

	/*************************************
	 * Admin ---> Branch --->Bank&Branch Starts
	 ****************************************/

	@RequestMapping(value = "BankBranchMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String BankBranchMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String solId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menuname", "Bank and Branch Details");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("BankandBranchList",
					bamlSolRepository.BankandBranchList(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("menuname1", "Bank and Branch Details-Modify");
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("BankandBranch", bankandBranchServices.getSolID(solId));
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("menuname1", "Bank and Branch Details-Inquiry");
			md.addAttribute("BankandBranch", bankandBranchServices.getSolID(solId));
		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", "verify");
			md.addAttribute("menuname1", "Bank and Branch Details-Verify");
			md.addAttribute("BankandBranch", bankandBranchServices.getSolID(solId));

		}
		md.addAttribute("adminflag", "adminflag");

		return "AMLBankandBranchMaster";
	}

	@RequestMapping(value = "ModBankBranchMaster", method = RequestMethod.POST)
	@ResponseBody
	public String ModBankBranchMaster(@RequestParam("formmode") String formmode,
			@ModelAttribute BAMLSolEntity bamlSolEntity, Model md, HttpServletRequest rq) {

		String msg = bankandBranchServices.modDetails(bamlSolEntity, formmode);
		md.addAttribute("adminflag", "adminflag");

		return msg;

	}

	/*************************************
	 * Admin ---> Branch --->Bank&Branch ends
	 ****************************************/

	/*************************************
	 * Admin ---> BatchJob Starts
	 ****************************************/

	@RequestMapping(value = "BatchJobSchedular", method = { RequestMethod.GET, RequestMethod.POST })
	public String BatchJobSchedular(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String jobId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("menuname", "Batch Job Schedule");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("BatchJobSchedular",
					bamlBatchJobSchedular.getBAMLBatchJobSchedular(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("menuname1", "Batch Job Schedule - Add");
			md.addAttribute("BATCHJOB", batchJobServices.getSrlNoValue());

		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("menuname1", "Batch Job Schedule - Modify");
			md.addAttribute("batchJob", batchJobServices.getJobID(jobId));
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("menuname1", "Batch Job Schedule - Inquiry");
			md.addAttribute("batchJob", batchJobServices.getJobID(jobId));
		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", "verify");
			md.addAttribute("menuname1", "Batch Job Schedule - Verify");
			md.addAttribute("batchJob", batchJobServices.getJobID(jobId));

		} else if (formmode.equals("delete")) {
			md.addAttribute("formmode", "delete");
			md.addAttribute("menuname1", "Batch Job Schedule - Delete");
			md.addAttribute("batchJob", batchJobServices.getJobID(jobId));

		}
		md.addAttribute("adminflag", "adminflag");

		return "BAMLBatchJob";
	}

	@RequestMapping(value = "CreateBatchJobSchedular", method = RequestMethod.POST)
	@ResponseBody
	public String CreateBatchJobSchedular(@RequestParam("formmode") String formmode,
			@ModelAttribute BAMLBatchJobSchedular bamlBatchJobSchedular, Model md, HttpServletRequest rq) {

		String msg = batchJobServices.createJob(bamlBatchJobSchedular, formmode);
		md.addAttribute("adminflag", "adminflag");

		return msg;

	}

	/*************************************
	 * Admin ---> BatchJob ends
	 ****************************************/

	@RequestMapping(value = "STRReportInternalSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String STRReportInternalSearch(@RequestParam(required = false) String formmode,
			@RequestParam(value = "report_id", required = false) String report_id,

			@RequestParam(value = "tran_id", required = false) String tran_id,
			@RequestParam(value = "tran_date", required = false) String tran_date,
			@RequestParam(value = "part_tran_srl_num", required = false) String part_tran_srl_num,
			@RequestParam(value = "tran_type", required = false) String tran_type,
			@RequestParam(value = "part_tran_type", required = false) String part_tran_type,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws FileNotFoundException, JRException, SQLException, ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		Date date_tran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

		md.addAttribute("tran_id", tran_id);
		md.addAttribute("tran_date", tran_date);
		md.addAttribute("part_tran_srl_num", part_tran_srl_num);
		md.addAttribute("SelectTran", baml_STR_SERVICE.getBAMLSearchFilterInt(tran_id.trim(),
				new SimpleDateFormat("dd-MMM-yyyy").format(date_tran), part_tran_srl_num.trim()));

		// md.addAttribute("SelectTran",
		// baml_STR_SERVICE.getBAMLSearchFilter(tran_id.trim(),
		// new SimpleDateFormat("dd-MMM-yyyy").format(date_tran),
		// part_tran_srl_num.trim()));

		md.addAttribute("interfaceflag", "interfaceflag");
		return "STRReportSearch";
	}

	@RequestMapping(value = "EtlMonitor", method = RequestMethod.GET)
	public String etlMonitor(Model md, HttpServletRequest req) {
		// Logging Navigation
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		String userid = (String) req.getSession().getAttribute("USERID");

		// loginServices.SessionLogging("", "M3", req.getSession().getId(), userid,
		// req.getRemoteAddr(), "ACTIVE");

		md.addAttribute("EtlError", rTLErrorRep.getEtlError());
		md.addAttribute("EtlStatus", etlMonitorrep.getEtlStatus());
		md.addAttribute("menu", "EtlMonitor");

		return "BAMLETLMonitor";
	}

	@RequestMapping(value = "ThirdPartyTranAdd", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranAdd(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlNo, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (formmode == null || formmode.equals("add")) {
			md.addAttribute("ThirdPartySRL", thirdPartyServices.getThirdPartySrlNo());
			md.addAttribute("menuname", "THIRD PARTY TRANSACTION - Add");
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("edit")) {
			md.addAttribute("menuname", "THIRD PARTY TRANSACTION  - Modify");
			md.addAttribute("thirdParty", thirdPartyServices.getJobID(srlNo));
			md.addAttribute("formmode", "edit");
		} else if (formmode.equals("verify")) {
			md.addAttribute("menuname", "THIRD PARTY TRANSACTION  - Verify");
			md.addAttribute("thirdParty", thirdPartyServices.getJobID(srlNo));
			md.addAttribute("formmode", "verify");
		} else if (formmode.equals("view")) {
			md.addAttribute("menuname", "THIRD PARTY TRANSACTION  - View");
			md.addAttribute("thirdParty", thirdPartyServices.getJobID(srlNo));
			md.addAttribute("formmode", "view");
		}
		md.addAttribute("casemanagementflag", "casemanagementflag");

		return "InputThirdPartyTransaction";
	}

	@RequestMapping(value = "ThirdPartyTran", method = { RequestMethod.GET, RequestMethod.POST })
	public String thirdParty(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String custId, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");

		final Calendar cal = Calendar.getInstance();
		/* cal.add(Calendar.DATE, -1); */
		String date = dateFormat.format(cal.getTime());
		String date1 = dateFormat1.format(cal.getTime());

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname", "THIRD PARTY TRANSACTION PAYMENT");
		md.addAttribute("Fromdate", date);
		md.addAttribute("Todate", date);

		md.addAttribute("ThirdParty",
				thirdPartyRepository.getThirdPartylist(date1, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("casemanagementflag", "casemanagementflag");
		md.addAttribute("formmode", "list");
		// md.addAttribute("formmode", "list1");

		return "InputThirdPartyTransaction";
	}

	@RequestMapping(value = "ThirdPartyTranList", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String custId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (custId != null && !custId.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository
						.getlistBycustID(PageRequest.of(currentPage, pageSize), custId + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("formmode", "list");

		return "InputThirdPartyTransaction";
	}

	@RequestMapping(value = "ThirdPartyTranListName", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranListName(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String custfullName, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (custfullName != null && !custfullName.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository.getlistBycustName(
						PageRequest.of(currentPage, pageSize), custfullName + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("formmode", "list");

		return "InputThirdPartyTransaction";
	}

	@RequestMapping(value = "ThirdPartyTranListNID", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranListNID(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String NID, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (NID != null && !NID.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository
						.getlistBycustNID(PageRequest.of(currentPage, pageSize), NID + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("formmode", "list");

		return "InputThirdPartyTransaction";
	}

	@RequestMapping(value = "ThirdPartyTranListRisk", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranListRisk(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String RiskCategory, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (RiskCategory != null && !RiskCategory.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository.getlistBycustRisk(
						PageRequest.of(currentPage, pageSize), RiskCategory + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilter(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("formmode", "list");

		return "InputThirdPartyTransaction";
	}

	@RequestMapping(value = "createThirdPartyList", method = RequestMethod.POST)
	@ResponseBody
	public ThirdPartyResponse createThirdPartyList(@RequestParam("formmode") String formmode,
			@RequestParam("nid") String nid, @RequestParam("paydate") String paydate_str,
			@RequestParam("paymode") String paymode, @RequestParam("custId") String custId,
			@ModelAttribute BAMLThirdPartyTran bamlThirdPartyTran, Model md, HttpServletRequest rq)
			throws ParseException {

		// String todate1 = new
		// SimpleDateFormat("yyyy-mm-dd").parse(paydate).toString();
		// DateFormat outputFormat = new SimpleDateFormat("MM/yyyy", Locale.US);
		DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date date = inputFormat.parse(paydate_str);

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String paydate = new SimpleDateFormat("dd-MMM-yyyy").format(date);

		Date payment = new SimpleDateFormat("dd-MMM-yyyy").parse(paydate);

		// String prevPayDate = new
		// SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime());
		String PartyTranAmt = thirdPartyRepository.findAmt(nid, paydate, paymode).toString();
		ThirdPartyResponse msg = thirdPartyServices.createThirdPartyPayment(bamlThirdPartyTran, formmode, PartyTranAmt,
				payment, nid, paymode, custId);
		md.addAttribute("adminflag", "adminflag");

		return msg;

	}

	@RequestMapping(value = "DownloadThirdPartyPayment", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource DownloadThirdPartyPayment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "FROM_DATE", required = false) String FROM_DATE,
			@RequestParam(value = "TO_DATE", required = false) String TO_DATE,
			@RequestParam(value = "formmode", required = false) String formmode,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "reportid", required = false) String reportid) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			File repfile = thirdPartyServices.getFile(formmode, filetype, FROM_DATE, TO_DATE);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "thirdPartySearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String thirdPartySearch(@RequestParam(required = false) String formmode,

			@RequestParam(value = "report_id", required = false) String report_id,
			@RequestParam(value = "foracid", required = false) String foracid,
			@RequestParam(value = "tran_id", required = false) String tran_id,
			@RequestParam(value = "cust_id", required = false) String cust_id,
			@RequestParam(value = "tran_date", required = false) String tran_date,
			@RequestParam(value = "part_tran_srl_num", required = false) String part_tran_srl_num,
			@RequestParam(value = "tran_type", required = false) String tran_type,
			@RequestParam(value = "part_tran_type", required = false) String part_tran_type,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws FileNotFoundException, JRException, SQLException, ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		Date tran_date1 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String trandate = format.format(tran_date1);
		String Sysdate = format.format(new Date());
		String mode = null;
		if (trandate.equals(Sysdate)) {
			mode = "Current";
			md.addAttribute("SelectTran1", baml_STR_SERVICE.getBAMLThirdSearchFilter(mode, cust_id,
					new SimpleDateFormat("dd-MMM-yyyy").format(tran_date1)));
			System.out.println(trandate + " YES " + Sysdate);
		} else {
			mode = "History";
			md.addAttribute("SelectTran1", baml_STR_SERVICE.getBAMLThirdSearchFilter(mode, cust_id,
					new SimpleDateFormat("dd-MMM-yyyy").format(tran_date1)));
			System.out.println(trandate + " NO " + Sysdate);
		}

		md.addAttribute("reportsflag", "reportsflag");
		return "ThirdPartyReportSearch";
	}

	@RequestMapping(value = "createUNSCInd", method = RequestMethod.POST)
	@ResponseBody
	public ThirdPartyResponse createUNSCInd(@RequestParam("formmode") String formmode,
			@ModelAttribute IndividualTable individualTable, Model md, HttpServletRequest rq) {
		System.out.println("controller");
		ThirdPartyResponse msg = unscServices.addUnsc(individualTable, formmode);
		// md.addAttribute("adminflag", "adminflag");

		return msg;

	}

	@RequestMapping(value = "createUNSCEnt", method = RequestMethod.POST)
	@ResponseBody
	public String createUNSCEnt(@RequestParam("formmode") String formmode, @ModelAttribute EntityTable entityTable,
			Model md, HttpServletRequest rq) {

		String msg = unscServices.addUnscEnt(entityTable, formmode);
		md.addAttribute("adminflag", "adminflag");

		return msg;

	}

	@RequestMapping(value = "rbsFileUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public String rbsFileUpload(Model md, HttpServletRequest req) {

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("menu", "RBS Report");
		md.addAttribute("reportvalue", "File Upload");

		return "AMLRbsFileUpload";
	}

	@RequestMapping(value = "ThirdPartyTranRep", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranRep(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String custId, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");

		final Calendar cal = Calendar.getInstance();
		/* cal.add(Calendar.DATE, -1); */
		String date = dateFormat.format(cal.getTime());
		String date1 = dateFormat1.format(cal.getTime());

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menuname", "THIRD PARTY TRANSACTION REPORT");
		md.addAttribute("Fromdate", date);
		md.addAttribute("Todate", date);

		md.addAttribute("ThirdParty",
				thirdPartyRepository.getThirdPartylistRep(date1, PageRequest.of(currentPage, pageSize)));

		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");
		md.addAttribute("formmode", "list");
		// md.addAttribute("formmode", "list1");

		return "ThirdPartyTransactionReport";
	}

	@RequestMapping(value = "ThirdPartyTranRepList", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranRepList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String custId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (custId != null && !custId.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository
						.getlistBycustIDRep(PageRequest.of(currentPage, pageSize), custId + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");
		md.addAttribute("formmode", "list");

		return "ThirdPartyTransactionReport";
	}

	@RequestMapping(value = "ThirdPartyTranRepListName", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranRepListName(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String custfullName, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (custfullName != null && !custfullName.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository.getlistBycustNameRep(
						PageRequest.of(currentPage, pageSize), custfullName + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");
		md.addAttribute("formmode", "list");

		return "ThirdPartyTransactionReport";
	}

	@RequestMapping(value = "ThirdPartyTranRepListNID", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyTranRepListNID(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String NID, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (NID != null && !NID.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository
						.getlistBycustNIDRep(PageRequest.of(currentPage, pageSize), NID + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");
		md.addAttribute("formmode", "list");

		return "ThirdPartyTransactionReport";
	}

	@RequestMapping(value = "ThirdPartyRepTranListRisk", method = { RequestMethod.GET, RequestMethod.POST })
	public String ThirdPartyRepTranListRisk(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Fromdate, @RequestParam(required = false) String Todate,
			@RequestParam(required = false) String RiskCategory, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		Date todate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Todate);
		Date fromdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(Fromdate);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("Fromdate", Fromdate);
		md.addAttribute("Todate", Todate);
		try {
			if (RiskCategory != null && !RiskCategory.isEmpty()) {
				md.addAttribute("ThirdParty", thirdPartyRepository.getlistBycustRiskRep(
						PageRequest.of(currentPage, pageSize), RiskCategory + '%', fromdate1, todate1));
			} else {
				md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
						PageRequest.of(currentPage, pageSize)));
			}
		} catch (Exception e) {
			md.addAttribute("ThirdParty", thirdPartyRepository.getThirdPartylistFilterRep(fromdate1, todate1,
					PageRequest.of(currentPage, pageSize)));
		}

		md.addAttribute("menuname", "Third Party Transactions Report");
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("formmode", "list");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "ThirdPartyTransactionReport";
	}

	@PostMapping(path = "marshallindupload")
	@ResponseBody
	public String UNSC() {

		logger.info("Time to Refresh the UNSC Individual Data");
		String status = unscServices.uploadUNSCInt();

		logger.info(" STATUS FOR UNSC " + status);

		return status;

	}

	@RequestMapping(value = "rbsValidations", method = { RequestMethod.GET, RequestMethod.POST })
	public String rbsValidations(@RequestParam(value = "reportDate", required = false) String reportDate, Model md,
			HttpServletRequest req) {

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (reportDate == null) {
			md.addAttribute("reportvalue", "RBS Report Generation");
			md.addAttribute("reportid", "rbsReportGeneration");
			reportDate = reportDate;
			md.addAttribute("reportDate1", reportDate);
			// md.addAttribute("reportDate1", reportValidationsRepo.getCurrentQtr(new
			// SimpleDateFormat("dd/MM/yyyy")));
			// reportDate = dateFormat.format(new Date());
		} else {
			reportDate = reportDate;
			md.addAttribute("reportDate1", reportDate);
			md.addAttribute("reportvalue", "RBS Report Generation");
			md.addAttribute("reportid", "rbsReportGeneration");
		}
		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		md.addAttribute("reportsflag", "reportsflag");
		md.addAttribute("menu", "RBS Validation Report");
		md.addAttribute("testDate", reportValidationsRepo.getCurrentQtr(new SimpleDateFormat("dd/MM/yyyy")));
		md.addAttribute("reportvalue", "RBS Report Generation");
		md.addAttribute("reportid", "rbsReportGeneration");

		md.addAttribute("RepValid", reportValidationsRepo.getValidationList());

		// md.addAttribute("reportvalue", "File Upload");

		return "RBSValidation";
	}

	@RequestMapping(value = "rbsValidationsChk", method = RequestMethod.POST)
	@ResponseBody
	public ValidationResponse rbsValidationsChk(@RequestParam("srl_no") String srl_no,
			@RequestParam("report_date") String report_date, @ModelAttribute ReportValidations reportValidations,
			Model md, HttpServletRequest rq) {
		logger.info("rbsValidationsChk:  Controller");
		ValidationResponse msg = rbsValidationservices.chkRBSValidations(reportValidations, srl_no, report_date);
		md.addAttribute("reportsflag", "reportsflag");

		return msg;

	}

	@RequestMapping(value = "createDataMaintenance", method = RequestMethod.POST)
	@ResponseBody
	public String createDataMaintenance(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String str, @ModelAttribute T1DataMaintenance t1CurProdDetail,
			@ModelAttribute T12DataMaintenance t12DataMaintenance, @ModelAttribute T8DataMaintenance t8DataMaintenance,
			@ModelAttribute T9DataMaintenance t9DataMaintenance, @ModelAttribute T10DataMaintenance t10DataMaintenance,
			@ModelAttribute T14DetailMaintenance t14DataMaintenance,
			@ModelAttribute T15DataMaintenance t15DataMaintenance,
			@ModelAttribute T18DataMaintenance t18DataMaintenance, Model md, HttpServletRequest rq)
			throws ParseException {
		logger.info("rbsValidationsChk:  Controller");
		System.out.println("rpt_code" + rpt_date);

		String msg = rbsDataMaintenanceServices.dataMaintenance(formmode, rpt_code, rpt_date, str, t1CurProdDetail,
				t12DataMaintenance, t8DataMaintenance, t9DataMaintenance, t10DataMaintenance, t14DataMaintenance,
				t15DataMaintenance, t18DataMaintenance);
		md.addAttribute("reportsflag", "reportsflag");

		return msg;

	}

	@RequestMapping(value = "createDataMaintenanceT5", method = RequestMethod.POST)
	@ResponseBody
	public String createDataMaintenanceT5(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,

			@ModelAttribute T2CurrentMast t2CurrentMast, @ModelAttribute T5Detail t5Detail, Model md,
			HttpServletRequest rq) throws ParseException {
		logger.info("rbsValidationsChk:  Controller");
		System.out.println("rpt_code" + rpt_date);
		// Date dt1;
		/// dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
		// System.out.println("dt1" + dt1);
		// SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		// String strDate1 = formatter1.format(dt1);
		String msg = rbsDataMaintenanceServices.dataMaintenanceT5(formmode, rpt_code, rpt_date, t2CurrentMast,
				t5Detail);
		md.addAttribute("reportsflag", "reportsflag");

		return msg;

	}

	@RequestMapping(value = "createDataMaintenanceT3A", method = RequestMethod.POST)
	@ResponseBody
	public String createDataMaintenanceT3A(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String str, @ModelAttribute T3ADataMaintenance t3ADataMaintenance, Model md,
			HttpServletRequest rq) throws ParseException {
		logger.info("rbsValidationsChk:  Controller");
		System.out.println("rpt_code" + rpt_date);

		String msg = rbsDataMaintenanceServices.dataMaintenanceT3A(formmode, rpt_date, str, t3ADataMaintenance);
		md.addAttribute("reportsflag", "reportsflag");

		return msg;

	}

	@RequestMapping(value = "rbsdataMaintenanceT2", method = { RequestMethod.GET, RequestMethod.POST })
	public String rbsdataMaintenanceT2(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String CIF_ID, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) String rpt_description,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest rq)
			throws ParseException {

		String roleId = (String) rq.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode == null || formmode.equals("list")) {
			// md.addAttribute("ThirdPartySRL", thirdPartyServices.getThirdPartySrlNo());
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			System.out.println(rpt_View_Repo.getValidationList());

			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "list");
		} else if (formmode.equals("CustomerlistT2")) {
			Date dt1;
			dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT2");

			md.addAttribute("Details",
					t2CurrentReportServices.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

		}

		else if (formmode.equals("addT2")) {

			md.addAttribute("menuname1", "Report Data Maintenance - Add");

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + rpt_date);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			// md.addAttribute("thirdParty", thirdPartyServices.getJobID(srlNo));
			md.addAttribute("formmode", "addT2");

		} else if (formmode.equals("editT2")) {
			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());

			md.addAttribute("menuname", "Report Data Maintenance - Modify");
			md.addAttribute("formmode", "editT2");

			md.addAttribute("DetailsView", t2CurrentDetailRepo.getview(rpt_date, CIF_ID));
		} else if (formmode.equals("deleteT2")) {
			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());

			md.addAttribute("menuname", "Report Data Maintenance - Delete");
			md.addAttribute("formmode", "deleteT2");

			md.addAttribute("DetailsView", t2CurrentDetailRepo.getview(rpt_date, CIF_ID));
		} else if (formmode.equals("verifyT2")) {
			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());

			md.addAttribute("menuname", "Report Data Maintenance - Verify");
			md.addAttribute("formmode", "verifyT2");

			md.addAttribute("DetailsView", t2CurrentDetailRepo.getview(rpt_date, CIF_ID));
		} else if (formmode.equals("viewT2")) {
			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());

			md.addAttribute("menuname", "Report Data Maintenance - View");
			md.addAttribute("formmode", "viewT2");

			md.addAttribute("DetailsView", t2CurrentDetailRepo.getview(rpt_date, CIF_ID));
		}
		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "rbsdataMaintenanceT5", method = { RequestMethod.GET, RequestMethod.POST })
	public String rbsdataMaintenanceT5(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String CIF_ID, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_description,

			@RequestParam(required = false) String rpt_date, Model md, HttpServletRequest req) throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		System.out.println(rpt_date + "24354");
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode == null || formmode.equals("list")) {
			// md.addAttribute("ThirdPartySRL", thirdPartyServices.getThirdPartySrlNo());
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			System.out.println(rpt_View_Repo.getValidationList());

			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "list");
		} else if (formmode.equals("CustomerlistT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT5");

			md.addAttribute("Details",
					t5ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("addT5")) {

			md.addAttribute("menuname1", "Report Data Maintenance - Add");

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + rpt_date);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			// md.addAttribute("thirdParty", thirdPartyServices.getJobID(srlNo));
			md.addAttribute("formmode", "addT5");

		} else if (formmode.equals("editT5")) {
			md.addAttribute("rpt_code", rpt_code);
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());

			md.addAttribute("menuname", "Report Data Maintenance - Modify");
			md.addAttribute("formmode", "editT5");

			md.addAttribute("DetailsView", t5DetailRepo.getview(strDate1, CIF_ID));

		} else if (formmode.equals("verifyT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			// md.addAttribute("menuname", "Report Data Maintenance - Inquiry");

			md.addAttribute("DetailsView", t5DetailRepo.getview(strDate1, CIF_ID));

			md.addAttribute("menuname", "Report Data Maintenance - Verify");
			md.addAttribute("formmode", "verifyT5");

		} else if (formmode.equals("deleteT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			// md.addAttribute("menuname", "Report Data Maintenance - Inquiry");

			md.addAttribute("menuname", "Report Data Maintenance - Delete");
			md.addAttribute("formmode", "deleteT5");

			md.addAttribute("DetailsView", t5DetailRepo.getview(strDate1, CIF_ID));

		} else if (formmode.equals("viewT5")) {

			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);
			md.addAttribute("rpt_code", rpt_code);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance - Inquiry");
			md.addAttribute("formmode", "viewT5");

			md.addAttribute("DetailsView", t5DetailRepo.getview(strDate1, CIF_ID));

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "rbsdataMaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String rbsdataMaintenance(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String CIF_ID, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) BigDecimal part_tran_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_description,

			@RequestParam(required = false) String rpt_date, Model md, HttpServletRequest req) throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode == null || formmode.equals("list")) {
			// md.addAttribute("ThirdPartySRL", thirdPartyServices.getThirdPartySrlNo());
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			System.out.println(rpt_View_Repo.getValidationList());

			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "list");
		} else if (formmode.equals("Transactionlist")) {
			Date dt1;
			dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "Transactionlist");

			if (rpt_code.equals("T1")) {

				md.addAttribute("Details", t1CurrentReportService.parameterlistwithdecode(strDate1,
						PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("Details",
						t8ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("Details",
						t9ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("Details",
						t10ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("Details",
						t12ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("Details",
						t14ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("Details",
						t15ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("Details",
						t18ReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

			}

		} else if (formmode.equals("add")) {

			md.addAttribute("menuname1", "Report Data Maintenance - Add");

			md.addAttribute("rpt_code", rpt_code);

			System.out.println("rpt_description" + rpt_date);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			// md.addAttribute("thirdParty", thirdPartyServices.getJobID(srlNo));
			md.addAttribute("formmode", "add");

		} else if (formmode.equals("edit")) {

			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			System.out.println("Transaction:" + rpt_date);

			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			System.out.println("tran_date" + tran_date);
			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);

			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());

			md.addAttribute("menuname", "Report Data Maintenance  - Modify");

			md.addAttribute("formmode", "edit");

			if (rpt_code.equals("T1")) {

				md.addAttribute("DetailsView",
						t1CurProdDetailRepo.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("DetailsView",
						t8DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("DetailsView",
						t9DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("DetailsView",
						t10DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("DetailsView",
						t12DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("DetailsView",
						t14DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("DetailsView",
						t15DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("DetailsView",
						t18DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			}

		} else if (formmode.equals("verify")) {
			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			// md.addAttribute("menuname", "Report Data Maintenance - Inquiry");

			md.addAttribute("menuname", "Report Data Maintenance - Verify");
			md.addAttribute("formmode", "verify");

			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);
			if (rpt_code.equals("T1")) {

				md.addAttribute("DetailsView",
						t1CurProdDetailRepo.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("DetailsView",
						t8DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("DetailsView",
						t9DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("DetailsView",
						t10DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("DetailsView",
						t12DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("DetailsView",
						t14DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("DetailsView",
						t15DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("DetailsView",
						t18DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			}

		} else if (formmode.equals("delete")) {

			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			// md.addAttribute("menuname", "Report Data Maintenance - Inquiry");

			md.addAttribute("menuname", "Report Data Maintenance - Delete");
			md.addAttribute("formmode", "delete");

			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);
			if (rpt_code.equals("T1")) {

				md.addAttribute("DetailsView",
						t1CurProdDetailRepo.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("DetailsView",
						t8DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("DetailsView",
						t9DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("DetailsView",
						t10DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("DetailsView",
						t12DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("DetailsView",
						t14DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("DetailsView",
						t15DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("DetailsView",
						t18DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			}

		} else if (formmode.equals("view")) {

			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance - Inquiry");
			md.addAttribute("formmode", "view");

			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);

			if (rpt_code.equals("T1")) {

				md.addAttribute("DetailsView",
						t1CurProdDetailRepo.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("DetailsView",
						t8DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("DetailsView",
						t9DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("DetailsView",
						t10DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("DetailsView",
						t12DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("DetailsView",
						t14DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("DetailsView",
						t15DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("DetailsView",
						t18DataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			}

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "rbsdataMaintenanceT3A", method = { RequestMethod.GET, RequestMethod.POST })
	public String rbsdataMaintenanceT3A(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String CIF_ID, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_description,

			@RequestParam(required = false) String rpt_date, Model md, HttpServletRequest req) throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		System.out.println(rpt_date + "24354");
		int currentPage = page.orElse(0);

		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode == null || formmode.equals("list")) {
			// md.addAttribute("ThirdPartySRL", thirdPartyServices.getThirdPartySrlNo());
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			System.out.println(rpt_View_Repo.getValidationList());

			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "list");
		} else if (formmode.equals("TransactionCustomer")) {
			Date dt1;
			dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "TransactionCustomer");

			md.addAttribute("Details",
					t3AReportService.parameterlistwithdecode(strDate1, PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("addT3A")) {

			md.addAttribute("menuname1", "Report Data Maintenance - Add");

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + rpt_date);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			// md.addAttribute("RepMaster", rbsReportlist.getReportList());
			// md.addAttribute("thirdParty", thirdPartyServices.getJobID(srlNo));
			md.addAttribute("formmode", "addT3A");

		} else if (formmode.equals("editT3A")) {
			md.addAttribute("rpt_code", rpt_code);
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());

			md.addAttribute("menuname", "Report Data Maintenance - Modify");
			md.addAttribute("formmode", "editT3A");

			md.addAttribute("DetailsView", t3ADataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

		} else if (formmode.equals("verifyT3A")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);
			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			// md.addAttribute("menuname", "Report Data Maintenance - Inquiry");

			md.addAttribute("DetailsView", t3ADataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

			md.addAttribute("menuname", "Report Data Maintenance - Verify");
			md.addAttribute("formmode", "verifyT3A");

		} else if (formmode.equals("deleteT3A")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);
			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);

			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			// md.addAttribute("menuname", "Report Data Maintenance - Inquiry");

			md.addAttribute("menuname", "Report Data Maintenance - Delete");
			md.addAttribute("formmode", "deleteT3A");

			md.addAttribute("DetailsView", t3ADataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

		} else if (formmode.equals("viewT3A")) {

			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			Date dttran;
			dttran = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);

			SimpleDateFormat formattertran = new SimpleDateFormat("dd-MMM-yyyy");
			String strDatetran = formattertran.format(dttran);
			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);
			md.addAttribute("rpt_code", rpt_code);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", rpt_date);
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance - Inquiry");
			md.addAttribute("formmode", "viewT3A");

			md.addAttribute("DetailsView", t3ADataMaintenanceRep.getview(strDate1, tran_id, strDatetran, part_tran_id));

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "CustomerCheckGeneration", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustomerCheckGeneration(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String refno, @RequestParam(required = false) String Fromdate,
			@RequestParam(required = false) String Todate, @RequestParam(required = false) String userid,
			@RequestParam String searchDate, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(searchDate);

		md.addAttribute("menuname", "Customer Checks");
		String msg = bamlCustomerChecksService.Generation(date1);
		md.addAttribute("MonitoringParameterList",
				bamlCustomerChecksRepo.customerCheckListWithDateFilter(date1, PageRequest.of(currentPage, pageSize)));
		md.addAttribute("menuname", "Customer Checks");
		md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

		md.addAttribute("menu", "AML Customer Checks");
		md.addAttribute("amlreport", "amlreport");
		md.addAttribute("AmlmonitoringReportflag", "AmlmonitoringReportflag");

		return "AMLCustomerChecks";

	}

	@RequestMapping(value = "AmlCustomerCheckSearch", method = { RequestMethod.GET, RequestMethod.POST })

	@ResponseBody
	public String CustomerCheckPreCheck(@RequestParam String searchDate) throws ParseException {

		logger.info("CustomerCheckPreCheck()");
		String msg = "";
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(searchDate);

		msg = bamlCustomerChecksService.preCheck(date1);

		logger.info("returning CustomerCheckPreCheck()");
		return msg;
	}

	@RequestMapping(value = "AmlCustomerCheckWithSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustomerCheckWithSearch(@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req,
			@RequestParam String searchDate) throws ParseException {
		logger.info("CustomerCheckWithSearch()");
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(searchDate);

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menuname", "Customer Checks");
		md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
		md.addAttribute("MonitoringParameterList",
				bamlCustomerChecksRepo.customerCheckListWithDateFilter(date1, (PageRequest.of(currentPage, pageSize))));

		logger.info("returning AMLCustomerChecks.html view");
		return "AMLCustomerChecks";
	}

	@RequestMapping(value = "dmTransactionSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String dmTransactionSearch(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String CIF_ID,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) BigDecimal part_tran_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_description,

			@RequestParam(required = false) String rpt_date, Model md, HttpServletRequest req) throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("Transactionlist")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dt2;
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate3 = formatter3.format(dt2);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "Transactionlist");

			if (rpt_code.equals("T1")) {

				md.addAttribute("Details", t1CurrentReportService.searchT1Both(strDate1, strDate3, P_O, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("Details", t8ReportService.searchT8Both(strDate1, strDate3, P_O, tran_id, part_tran_id,
						PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("Details", t9ReportService.searchT9Both(strDate1, strDate3, P_O, tran_id, part_tran_id,
						PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("Details", t10ReportService.searchT10Both(strDate1, strDate3, P_O, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("Details", t12ReportService.searchT12Both(strDate1, strDate3, P_O, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("Details", t14ReportService.searchT14Both(strDate1, strDate3, P_O, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("Details", t15ReportService.searchT15Both(strDate1, strDate3, P_O, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("Details", t18ReportService.searchT18Both(strDate1, strDate3, P_O, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			}

		} else if (formmode.equals("TransactionCustomer")) {

			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dt2;
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate3 = formatter3.format(dt2);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "TransactionCustomer");
			md.addAttribute("Details", t3AReportService.searchT3ABoth(strDate1, strDate3, P_O, tran_id, part_tran_id,
					PageRequest.of(currentPage, pageSize)));

		}
		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "TransactionSearchGetInd", method = { RequestMethod.GET, RequestMethod.POST })
	public String TransactionSearchGetInd(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String CIF_ID,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) BigDecimal part_tran_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_description,

			@RequestParam(required = false) String rpt_date, Model md, HttpServletRequest req) throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("Transactionlist")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dt2;
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate3 = formatter3.format(dt2);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "Transactionlist");

			if (rpt_code.equals("T1")) {

				md.addAttribute("Details", t1CurrentReportService.searchT1SingleTran(strDate1, strDate3, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("Details", t8ReportService.searchT8SingleTran(strDate1, strDate3, tran_id, part_tran_id,
						PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("Details", t9ReportService.searchT9SingleTran(strDate1, strDate3, tran_id, part_tran_id,
						PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("Details", t10ReportService.searchT10SingleTran(strDate1, strDate3, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("Details", t12ReportService.searchT12SingleTran(strDate1, strDate3, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("Details", t14ReportService.searchT14SingleTran(strDate1, strDate3, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("Details", t15ReportService.searchT15SingleTran(strDate1, strDate3, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("Details", t18ReportService.searchT18SingleTran(strDate1, strDate3, tran_id,
						part_tran_id, PageRequest.of(currentPage, pageSize)));

			}

		} else if (formmode.equals("TransactionCustomer")) {

			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dt2;
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate3 = formatter3.format(dt2);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "TransactionCustomer");
			md.addAttribute("Details", t3AReportService.searchT3ASingleTran(strDate1, strDate3, tran_id, part_tran_id,
					PageRequest.of(currentPage, pageSize)));

		}
		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "TransactionSearchByPO", method = { RequestMethod.GET, RequestMethod.POST })
	public String TransactionSearchByPO(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String CIF_ID,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) BigDecimal part_tran_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_description,

			@RequestParam(required = false) String rpt_date, Model md, HttpServletRequest req) throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("Transactionlist")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "Transactionlist");

			if (rpt_code.equals("T1")) {

				md.addAttribute("Details",
						t1CurrentReportService.searchT1PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("Details",
						t8ReportService.searchT8PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));	

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("Details",
						t9ReportService.searchT9PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("Details",
						t10ReportService.searchT10PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("Details",
						t12ReportService.searchT12PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("Details",
						t14ReportService.searchT14PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("Details",
						t15ReportService.searchT15PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("Details",
						t18ReportService.searchT18PO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

			}

		} else if (formmode.equals("TransactionCustomer")) {

			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dt2;
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate3 = formatter3.format(dt2);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "TransactionCustomer");
			md.addAttribute("Details",
					t3AReportService.searchT3APO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

		}
		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "TransactionSearchByTran_date", method = { RequestMethod.GET, RequestMethod.POST })
	public String TransactionSearchByTran_date(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String CIF_ID,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) BigDecimal part_tran_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_description,

			@RequestParam(required = false) String rpt_date, Model md, HttpServletRequest req) throws ParseException {
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("Transactionlist")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dt2;
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate3 = formatter3.format(dt2);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "Transactionlist");

			if (rpt_code.equals("T1")) {

				md.addAttribute("Details",
						t1CurrentReportService.searchT1Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T8")) {

				md.addAttribute("Details",
						t8ReportService.searchT8Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T9")) {

				md.addAttribute("Details",
						t9ReportService.searchT9Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T10")) {

				md.addAttribute("Details",
						t10ReportService.searchT10Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T12")) {

				md.addAttribute("Details",
						t12ReportService.searchT12Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T14")) {

				md.addAttribute("Details",
						t14ReportService.searchT14Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T15")) {

				md.addAttribute("Details",
						t15ReportService.searchT15Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			} else if (rpt_code.equals("T18")) {

				md.addAttribute("Details",
						t18ReportService.searchT18Date(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

			}

		} else if (formmode.equals("TransactionCustomer")) {

			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			Date dt2;
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
			SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate3 = formatter3.format(dt2);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "TransactionCustomer");
			md.addAttribute("Details",
					t3AReportService.searchT3ADate(strDate1, strDate3, PageRequest.of(currentPage, pageSize)));

		}
		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "dmCustomerSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String dmCustomerSearch(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String Cust_ID, @RequestParam(required = false) String Cust_Name,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) String rpt_description,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest rq)
			throws ParseException {

		String roleId = (String) rq.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("CustomerlistT2")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT2");

			md.addAttribute("Details", t2CurrentReportServices.searchAll(strDate1, Cust_ID, P_O, Cust_Name,
					PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("CustomerlistT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT5");

			md.addAttribute("Details", t5ReportService.searchAll(strDate1, Cust_ID, P_O, Cust_Name,
					PageRequest.of(currentPage, pageSize)));

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "CustomerSearchCustDet", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustomerSearchCustDet(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String Cust_ID, @RequestParam(required = false) String Cust_Name,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) String rpt_description,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest rq)
			throws ParseException {

		String roleId = (String) rq.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("CustomerlistT2")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT2");

			md.addAttribute("Details", t2CurrentReportServices.searchbycust(strDate1, Cust_ID, Cust_Name,
					PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("CustomerlistT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT5");

			md.addAttribute("Details",
					t5ReportService.searchbycust(strDate1, Cust_ID, Cust_Name, PageRequest.of(currentPage, pageSize)));

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "CustomerSearchCustID", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustomerSearchCustID(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String Cust_ID, @RequestParam(required = false) String Cust_Name,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) String rpt_description,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest rq)
			throws ParseException {

		String roleId = (String) rq.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("CustomerlistT2")) {
			Date dt1;
			dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT2");

			md.addAttribute("Details",
					t2CurrentReportServices.searchbycustID(strDate1, Cust_ID, PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("CustomerlistT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT5");

			md.addAttribute("Details",
					t5ReportService.searchbycustID(strDate1, Cust_ID, PageRequest.of(currentPage, pageSize)));

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "dmCustomerSearchPO", method = { RequestMethod.GET, RequestMethod.POST })
	public String dmCustomerSearchPO(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String Cust_ID, @RequestParam(required = false) String Cust_Name,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) String rpt_description,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest rq)
			throws ParseException {

		String roleId = (String) rq.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("CustomerlistT2")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT2");

			md.addAttribute("Details",
					t2CurrentReportServices.searchbyPO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("CustomerlistT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT5");

			md.addAttribute("Details",
					t5ReportService.searchbyPO(strDate1, P_O, PageRequest.of(currentPage, pageSize)));

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "CustomerSearchCustName", method = { RequestMethod.GET, RequestMethod.POST })
	public String CustomerSearchCustName(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String rpt_code, @RequestParam(required = false) String rpt_date,
			@RequestParam(required = false) String Cust_ID, @RequestParam(required = false) String Cust_Name,
			@RequestParam(required = false) String P_O, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String tran_date, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) String rpt_description,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest rq)
			throws ParseException {

		String roleId = (String) rq.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		if (formmode.equals("CustomerlistT2")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT2");

			md.addAttribute("Details",
					t2CurrentReportServices.searchbyName(strDate1, Cust_Name, PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("CustomerlistT5")) {
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			System.out.println("dt1" + dt1);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(dt1);

			SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			String strDate2 = formatter2.format(dt1);

			md.addAttribute("rpt_code", rpt_code);
			System.out.println("rpt_description" + strDate1);
			md.addAttribute("rpt_description", rpt_description);
			md.addAttribute("rpt_date", strDate2);
			md.addAttribute("RepMaster", rpt_View_Repo.getValidationList());
			md.addAttribute("RepMaster1", rbsReportlist.getReportList1());
			md.addAttribute("menuname", "Report Data Maintenance");
			md.addAttribute("formmode", "CustomerlistT5");

			md.addAttribute("Details",
					t5ReportService.searchbyName(strDate1, Cust_Name, PageRequest.of(currentPage, pageSize)));

		}

		return "RBSDataMaintenance";
	}

	@RequestMapping(value = "XMLGen", method = { RequestMethod.GET, RequestMethod.POST })
	public String XMLGen(@RequestParam(required = false) String formmode, @RequestParam(required = false) String srlno,
			@RequestParam(value = "cif", required = false) String cif,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "nid", required = false) String nid,
			@RequestParam(value = "risk", required = false) String risk, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "CRS TIN - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			md.addAttribute("CRSList", crsListRepository.getpeplistBycif(PageRequest.of(currentPage, pageSize)));

		}
		md.addAttribute("listmgntflag", "listmgntflag");
		md.addAttribute("reglistflag", "reglistflag");

		return "BAMLXMLGen";
	}
}