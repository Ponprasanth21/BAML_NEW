
package com.bornfire.Services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JRException;

@Service
@Transactional
@ConfigurationProperties("output")
public class ReportServices {

	private static final Logger logger = LoggerFactory.getLogger(ReportServices.class);

	@Autowired
	RBSReportGeneration rbsReportGeneration;

	@Autowired
	T2PreviousReportServices t2previousreportservices;

	@Autowired
	T2CurrentReportServices t2currentreportservices;
	@Autowired
	T4ReportServices t4reportservice;

	@Autowired
	T5ReportService t5ReportService;

	@Autowired
	T6ReportService t6reportservice;

	@Autowired
	T7ReportServices t7ReportService;

	@Autowired
	T8ReportService t8ReportService;

	@Autowired
	T9ReportServices t9ReportService;

	@Autowired
	T10ReportService t10ReportService;

	@Autowired
	T11ReportService t11reportservice;

	@Autowired
	T12ReportService t12ReportService;

	@Autowired
	T13ReportServices t13reportservice;

	@Autowired
	T14ReportService T14ReportService;

	@Autowired
	T15ReportService T15ReportService;

	@Autowired
	T17ReportService t17ReportService;

	@Autowired
	T18ReportService t18ReportService;

	@Autowired
	T19ReportService t19reportservice;

	@Autowired
	T20ReportService t20ReportService;

	@Autowired
	T21ReportService t21reportservice;

	@Autowired
	T22ReportService t22reportservice;

	@Autowired
	T24ReportService T24ReportService;

	@Autowired
	T25ReportServices t25ReportService;

	@Autowired
	T26ReportService T26ReportService;

	@Autowired
	T27CurrentReportServices T27CurrentReportService;

	@Autowired
	T27PreviousReportServices T27PreviousReportService;

	@Autowired
	Cust_Black_List_Ind_Service cust_Black_list_Ind_Service;

	@Autowired
	Cust_Black_List_Corp_Service cust_Black_list_Corp_Service;

	@Autowired
	NegativeListService negativeListService;

	@Autowired
	Cust_White_ListService whiteListService;

	@Autowired
	Cust_Hnwi_List_Service hnwiListService;

	@Autowired
	Cust_Pep_List_Service pepListService;

	@Autowired
	Cust_Aband_Fund_List_Service abandfundListService;

	@Autowired
	BAML_Daily_Loan_Blacklist_RT_List_Service baml_daily_loan_ListService;

	@Autowired
	BAML_RISK_CATEGORY_RT_Service baml_risk_category_ListService;

	@Autowired
	AMLMonitoringDownload amlMonitoringDownload;

	@Autowired
	AMLScreeningDownload amlScreeningDownload;

	@Autowired
	T1CurrentReportService T1CurrentReportService;

	@Autowired
	T23ReportService t23ReportService;

	@Autowired
	T3AReportService t3aReportService;

	@Autowired
	T16ReportService T16ReportService;

	@Autowired
	RiskCustService RiskCustService;

	@Autowired
	T28ReportServices t28ReportServices;
	
	@Autowired
	JASPERDOWNLOAD  JASPERDOWNLOAD;

	public String preCheckReport(String reportId, String fromdate, String todate) {

		String msg = "";

		logger.info("precheck : " + reportId);

		switch (reportId) {

		case "1":
			msg = T1CurrentReportService.preCheck(reportId, fromdate, todate);
			break;

		case "2p":
			msg = t2previousreportservices.preCheck(reportId, fromdate, todate);
			break;

		case "2":
			msg = t2currentreportservices.preCheck(reportId, fromdate, todate);
			break;

		case "3.A":
			msg = t3aReportService.preCheck(reportId, fromdate, todate);
			break;
		case "3.B":
			msg = t3aReportService.preCheckB(reportId, fromdate, todate);
			break;
		case "4":
			msg = t4reportservice.preCheck(reportId, fromdate, todate);
			break;

		case "5":
			msg = t5ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "6":
			msg = t6reportservice.preCheck(reportId, fromdate, todate);
			break;

		case "7":
			msg = t7ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "8":
			msg = t8ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "9":
			msg = t9ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "10":
			msg = t10ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "11":
			msg = t11reportservice.preCheck(reportId, fromdate, todate);
			break;

		case "12":
			msg = t12ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "13":
			msg = t13reportservice.preCheck(reportId, fromdate, todate);
			break;

		case "14":
			msg = T14ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "15":
			msg = T15ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "16":
			msg = T16ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "17":
			msg = t17ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "18":
			msg = t18ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "19":
			msg = t19reportservice.preCheck(reportId, fromdate, todate);
			break;

		case "20":
			msg = t20ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "21":
			msg = t21reportservice.preCheck(reportId, fromdate, todate);
			break;

		case "22":
			msg = t22reportservice.preCheck(reportId, fromdate, todate);
			break;

		case "23":
			msg = t23ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "24":
			msg = T24ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "25":
			msg = t25ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "26":
			msg = T26ReportService.preCheck(reportId, fromdate, todate);
			break;

		case "27":
			msg = T27CurrentReportService.preCheck(reportId, fromdate, todate);
			break;

		/*
		 * case "28": msg = t28ReportService.preCheck(reportId, fromdate, todate);
		 * break;
		 */

		case "28":
			msg = t28ReportServices.preCheck(reportId, fromdate, todate);
			break;

		case "generation":
			msg = rbsReportGeneration.preCheck(reportId, fromdate, todate);
			break;

		case "monitoring":
			msg = amlMonitoringDownload.preCheck(reportId, fromdate, todate);
			break;
		case "RiskCustService":
			msg = RiskCustService.preCheck(reportId, fromdate, todate);
			break;

		/*
		 * default: msg = T16ReportService.preCheck(reportId, fromdate, todate);
		 * 
		 * logger.info("default -> preCheck()");
		 */
		}

		return msg;
	}

	public ModelAndView getReportView(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) throws ParseException {

		ModelAndView repsummary = new ModelAndView();

		logger.info("Getting View for the Report :" + reportId);
		switch (reportId) {

		case "1":
			repsummary = T1CurrentReportService.getT1View(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "2p":
			repsummary = t2previousreportservices.getT2previousView(reportId, fromdate, todate, currency, dtltype,
					pageable);
			// msg = t2currentreportservices.preCheck(repgetT2currentViewortId,fromdate,
			// todate);
			break;

		case "2":
			repsummary = t2currentreportservices.getT2currentView(reportId, fromdate, todate, currency, dtltype,
					pageable);
			// msg = t2currentreportservices.preCheck(repgetT2currentViewortId,fromdate,
			// todate);
			break;

		case "3.A":
			repsummary = t3aReportService.getT3aView(reportId, fromdate, todate);
			break;
		case "3.B":
			repsummary = t3aReportService.getT3aViewB(reportId, fromdate, todate);
			break;
		case "4":
			repsummary = t4reportservice.getT4currentView(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "5":
			repsummary = t5ReportService.getT2View(reportId, fromdate, todate);
			break;

		case "6":
			repsummary = t6reportservice.getT6currentView(reportId, fromdate, todate, currency, dtltype, pageable);

			break;

		case "7":
			repsummary = t7ReportService.getT7View(reportId, fromdate, todate, currency, dtltype, pageable);

			break;

		case "8":
			repsummary = t8ReportService.getT8View(reportId, fromdate, todate);
			break;
	
		case "9":
			repsummary = t9ReportService.getT9View(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "10":
			repsummary = t10ReportService.getT10View(reportId, fromdate, todate);
			break;

		case "11":
			repsummary = t11reportservice.getT11currentView(reportId, fromdate, todate, currency, dtltype, pageable);
			// msg = t2currentreportservices.preCheck(reportId,fromdate, todate);
			break;

		case "12":
			repsummary = t12ReportService.getT12View(reportId, fromdate, todate);
			break;

		case "13":
			repsummary = t13reportservice.getT13View(reportId, fromdate, todate, currency, dtltype, pageable);

			break;

		case "14":
			repsummary = T14ReportService.getT14View(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "15":
			repsummary = T15ReportService.getT15View(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "16":
			repsummary = T16ReportService.getT16Rep(reportId, fromdate, todate);
			break;

		case "17":
			repsummary = t17ReportService.getT17Rep(reportId, fromdate, todate);
			break;

		case "18":
			repsummary = t18ReportService.getT18Rep(reportId, fromdate, todate);
			break;

		case "19":
			repsummary = t19reportservice.getT19currentView(reportId, fromdate, todate, currency, dtltype, pageable);
			// msg = t2currentreportservices.preCheck(reportId,fromdate, todate);

			break;

		case "20":
			repsummary = t20ReportService.getT20View(reportId, fromdate, todate);
			break;

		case "21":
			repsummary = t21reportservice.getT21View(reportId, fromdate, todate, currency, dtltype, pageable);
			// msg = t2currentreportservices.preCheck(reportId,fromdate, todate);

			break;

		case "22":
			repsummary = t22reportservice.getT22currentView(reportId, fromdate, todate, currency, dtltype, pageable);
			// msg = t2currentreportservices.preCheck(reportId,fromdate, todate);

			break;

		case "23":
			repsummary = t23ReportService.getT23currentView(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "24":
			repsummary = T24ReportService.getT24View(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "25":
			repsummary = t25ReportService.getT25View(reportId, fromdate, todate);
			// msg = t2currentreportservices.preCheck(reportId,fromdate, todate);

			break;

		case "26":
			repsummary = T26ReportService.getT24View(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "27":
			repsummary = T27CurrentReportService.getT27currentView(reportId, fromdate, todate, currency, dtltype,
					pageable);
			break;

		/*
		 * case "28": repsummary = t28ReportService.getT28View(reportId, fromdate,
		 * todate, currency, dtltype, pageable); break;
		 */

		case "28":
			repsummary = t28ReportServices.getT28currentView(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "RiskCustService":
			repsummary = RiskCustService.getRiskCustView(reportId, fromdate, todate);
			break;

		}
		return repsummary;

	}

	public ModelAndView getReportSummary(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable,String type) {

		ModelAndView repsummary = new ModelAndView();
		logger.info("Getting Summary for the Report :" + reportId);
		switch (reportId) {

		case "1":
			repsummary = T1CurrentReportService.getT1CurrentRep(reportId, fromdate, todate, currency, dtltype,
					pageable);
			break;

		case "t2previous":
			repsummary = t2previousreportservices.getT2previousRep(reportId, fromdate, todate, currency, dtltype,
					pageable);
			break;

		case "2":
			repsummary = t2currentreportservices.getT2currentRep(reportId, fromdate, todate, currency, dtltype,
					pageable);
			break;

		case "3.A":
			repsummary = t3aReportService.getT13Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;
		case "3.B":
			repsummary = t3aReportService.getT13RepB(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "4":
			repsummary = t4reportservice.getT4currentView(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "5":
			repsummary = t5ReportService.getT2Rep(reportId, fromdate, todate);
			break;

		case "6":
			repsummary = t6reportservice.getT6currentRep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "7":
			repsummary = t7ReportService.getT7Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "8":
			repsummary = t8ReportService.getT8Rep(reportId, fromdate, todate);
			break;

		case "9":
			repsummary = t9ReportService.getT9Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "10":
			repsummary = t10ReportService.getT10Rep(reportId, fromdate, todate);
			break;

		case "11":
			repsummary = t11reportservice.getT11currentRep(reportId, fromdate, todate, currency, dtltype, pageable);

			break;

		case "12":
			repsummary = t12ReportService.getT12Rep(reportId, fromdate, todate);
			break;

		case "13":
			repsummary = t13reportservice.getT13Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "14":
			repsummary = T14ReportService.getT14Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "15":
			repsummary = T15ReportService.getT15Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "16":
			repsummary = T16ReportService.getT16Rep(reportId, fromdate, todate);
			break;
		case "17":
			repsummary = t17ReportService.getT17Rep(reportId, fromdate, todate);
			break;

		case "18":
			repsummary = t18ReportService.getT18Rep(reportId, fromdate, todate);
			break;

		case "19":
			repsummary = t19reportservice.getT19currentRep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "20":
			repsummary = t20ReportService.getT20Rep(reportId, fromdate, todate);
			break;

		case "21":
			repsummary = t21reportservice.getT21Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "22":
			repsummary = t22reportservice.getT22currentRep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "23":
			repsummary = t23ReportService.getT23currentRep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "24":
			repsummary = T24ReportService.getT24Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "25":
			repsummary = t25ReportService.getT25Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			// msg = t2currentreportservices.preCheck(reportId,fromdate, todate);

			break;

		case "26":
			repsummary = T26ReportService.getT24Rep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "27":
			repsummary = T27CurrentReportService.getT27currentView(reportId, fromdate, todate, currency, dtltype,
					pageable);
			break;

		case "28":
			repsummary = t28ReportServices.getT28currentRep(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "RiskCustService":
			repsummary = RiskCustService.getRiskCustRep(reportId, fromdate, todate);
			break;

		}

		return repsummary;

	}
	
	
	public ModelAndView getReportDetails(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable, String ratvalue, String tablecol, String filter) {

		ModelAndView repdetail = new ModelAndView();
		logger.info("Getting Details for the Report :" + reportId);
		switch (reportId) {

		case "1":
			repdetail = T1CurrentReportService.getT1currentDtl(reportId, fromdate, todate, currency, dtltype, pageable,
					filter);
			break;

		case "t2previous":
			repdetail = t2previousreportservices.getT2previousDtl(reportId, fromdate, todate, currency, dtltype,
					pageable, ratvalue, tablecol);
			break;

		case "2":
			repdetail = t2currentreportservices.getT2currentDtl(reportId, fromdate, todate, currency, dtltype, pageable,filter);
			break;

		case "3.A":
			repdetail = t3aReportService.getT13Dtl(reportId, fromdate, todate, currency, dtltype, pageable,filter);
			break;

		case "4":
			repdetail = t4reportservice.getT4Dtl(reportId, fromdate, todate, currency, dtltype, pageable, filter);
			break;

		case "5":
			repdetail = t5ReportService.getT2currentDtl(reportId, fromdate, todate, currency, dtltype, pageable,
					filter);
			break;

		case "6":
			repdetail = t6reportservice.getT6currentDtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "7":
			repdetail = t7ReportService.getT7Dtl(reportId, fromdate, todate, currency, dtltype, pageable, filter);
			break;

		case "8":
			repdetail = t8ReportService.getT2currentDtl(reportId, fromdate, todate, currency, dtltype, pageable,
					filter);
			break;

		case "9":
			repdetail = t9ReportService.getT9Dtl(reportId, fromdate, todate, currency, dtltype, pageable, filter);
			break;

		case "10":
			repdetail = t10ReportService.getT10currentDtl(reportId, fromdate, todate, currency, dtltype, pageable,
					filter);
			break;
		case "11":
			repdetail = t11reportservice.getT11currentDtl(reportId, fromdate, todate, currency, dtltype, pageable);

			break;

		case "12":
			repdetail = t12ReportService.getT12Dtl(reportId, fromdate, todate, currency, dtltype, pageable,
					filter);
			break;

		case "13":
			repdetail = t13reportservice.getT13Dtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "14":
			repdetail = T14ReportService.getT14Dtl(reportId, fromdate, todate, currency, dtltype, pageable, filter);
			break;

		case "15":
			repdetail = T15ReportService.getT15Dtl(reportId, fromdate, todate, currency, dtltype, pageable, filter);
			break;
		case "16":
			repdetail = T16ReportService.getT16currentDtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;
		case "17":
			repdetail = t17ReportService.getT17currentDtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;
		case "18":
			repdetail = t18ReportService.getT18Dtl(reportId, fromdate, todate, currency, dtltype, pageable,
					filter);
			break;

		case "19":
			repdetail = t19reportservice.getT19currentDtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;
		case "20":
			repdetail = t20ReportService.getT20Dtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "21":
			repdetail = t21reportservice.getT21Dtl(reportId, fromdate, todate, currency, dtltype, pageable, filter);
			break;

		case "22":
			repdetail = t22reportservice.getT22currentDtl(reportId, fromdate, todate, currency, dtltype, pageable,filter);
			break;

		case "23":
			repdetail = t23ReportService.getT23currentDtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "24":
			repdetail = T24ReportService.getT24Dtl(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		case "27":
			repdetail = T27CurrentReportService.getT27currentDtl(reportId, fromdate, todate, currency, dtltype,
					pageable);
			break;

		case "28":
			repdetail = T27PreviousReportService.getT27previousDtl(reportId, fromdate, todate, currency, dtltype,
					pageable);
			break;
		case "RiskCustService":
			repdetail = RiskCustService.getRiskCustcurrentDtl(reportId, fromdate, todate, currency, dtltype, pageable,
					filter);
			break;

		}

		return repdetail;

	}

	public File getDownloadFile(String userid, String reportId, String fromdate, String todate, String currency,
			String dtltype, String filetype) throws FileNotFoundException, JRException, SQLException {

		File repfile = null;

		logger.info("Getting Report File for : " + reportId + " in " + filetype + " format");

		switch (reportId) {

		case "1":
			repfile = T1CurrentReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "t2previous":
			repfile = t2previousreportservices.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "2":
			repfile = t2currentreportservices.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "3.A":
			repfile = t3aReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;
		case "3.B":
			repfile = t3aReportService.getFile3(reportId, fromdate, todate, currency, dtltype, filetype);
			break;
		case "4":
			repfile = t4reportservice.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "5":
			repfile = t5ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "6":
			repfile = t6reportservice.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "7":
			repfile = t7ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "8":
			repfile = t8ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "9":
			repfile = t9ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "10":
			repfile = t10ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "11":
			repfile = t11reportservice.getFile(reportId, fromdate, todate, currency, dtltype, filetype);

			break;

		case "12":
			repfile = t12ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "13":
			repfile = t13reportservice.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "14":
			repfile = T14ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "15":
			repfile = T15ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "16":
			repfile = T16ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;
		case "17":
			repfile = t17ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "18":
			repfile = t18ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "19":
			repfile = t19reportservice.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "20":
			repfile = t20ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "21":
			repfile = t21reportservice.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "22":
			repfile = t22reportservice.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "23":
			repfile = t23ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "24":
			repfile = T24ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "25":
			repfile = t25ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "26":
			repfile = T26ReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "27":
			repfile = T27CurrentReportService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		/*
		 * case "28": repfile = t28ReportService.getFile(reportId, fromdate, todate,
		 * currency, dtltype, filetype); break;
		 */

		case "28":
			repfile = t28ReportServices.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "Black_List_Ind":
			repfile = cust_Black_list_Ind_Service.getFile(userid, reportId, fromdate, todate, currency, dtltype,
					filetype);
			break;

		case "Black_List_Corp":
			repfile = cust_Black_list_Corp_Service.getFile(userid, reportId, fromdate, todate, currency, dtltype,
					filetype);
			break;

		case "Neg_List":
			repfile = negativeListService.getFile(userid, reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "WHite_List":
			repfile = whiteListService.getFile(userid, reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "HNWI_List":
			repfile = hnwiListService.getFile(userid, reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "PEP_List":
			repfile = pepListService.getFile(userid, reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "ABAND_List":
			repfile = abandfundListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;
		case "UNSC_List":
			repfile = abandfundListService.getUNSCFile(userid, reportId, fromdate, todate, currency, dtltype, filetype);
			break;
		case "Daily_Loan":
			repfile = baml_daily_loan_ListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "Daily_Rss":
			repfile = baml_daily_loan_ListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "Daily_Deposit":
			repfile = baml_daily_loan_ListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "Daily_cash_tran":
			repfile = baml_daily_loan_ListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "generation":
			repfile = rbsReportGeneration.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "monitoring":
			repfile = amlMonitoringDownload.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;
		case "AML_Risk_Cust_Reports_Summary":
			repfile = RiskCustService.getFile(userid, reportId, fromdate, todate, currency, dtltype, filetype);
			break;
		case "AML_Risk_Cust_Reports_Detail":
			repfile = RiskCustService.getFiledetail(userid, reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		}

		return repfile;
	}
	public ByteArrayInputStream getDownloadFileExcel(String userid, String reportId, String fromdate, String todate, 
			String dtltype, String filetype, String catgeory ) throws FileNotFoundException, JRException, SQLException, ParseException {

		ByteArrayInputStream repfile = null;
		switch (reportId) {
		
			case "PEP_List":
				repfile = pepListService.getFileExcel(userid, reportId, fromdate, todate, dtltype, filetype);
				break;
			
			case "Daily_Loan":
				repfile = baml_daily_loan_ListService.getFileLoanExcel(userid,reportId, fromdate, todate,  dtltype, filetype);
				break;

			case "Daily_Rss":
				repfile = baml_daily_loan_ListService.getFileRSSExcel(userid,reportId, fromdate, todate,  dtltype, filetype);
				break;

			case "Daily_Deposit":
				repfile = baml_daily_loan_ListService.getFileDepositExcel(userid,reportId, fromdate, todate, dtltype, filetype);
				break;

			case "Daily_cash_tran":
				repfile = baml_daily_loan_ListService.getFileCashExcel(userid,reportId, fromdate, todate, dtltype, filetype);
				break;
				
			case "Black_List_Ind":
				repfile = cust_Black_list_Ind_Service.getFile_Ind_Excel(userid, reportId, fromdate, todate,  dtltype,
						filetype);
				break;

			case "Black_List_Corp":
				repfile = cust_Black_list_Corp_Service.getFile_Corp_Excel(userid, reportId, fromdate, todate,  dtltype,
						filetype);
				break;

			case "HNWI_List":
				repfile = hnwiListService.getFile_HNWI_Excel(userid, reportId, fromdate, todate,  dtltype, filetype);
				break;

			case "ABAND_List":
				repfile = abandfundListService.getFile_Aband_fund_Excel(userid,reportId, fromdate, todate, dtltype, filetype);
				break;
				
			case "UNSC_List":
				repfile = abandfundListService.getFile_UNSC_Excel(userid, reportId, fromdate, todate,  dtltype, filetype);
				break;
				

			case "RISK_LOAN":
				repfile = baml_risk_category_ListService.getFile_RISK_LOAN_Excel(userid,reportId, fromdate, todate,  dtltype, filetype,catgeory);
				break;
			case "RISK_RSS":
				repfile = baml_risk_category_ListService.getFile_RISK_RSS_Excel(userid,reportId, fromdate, todate,  dtltype, filetype,catgeory);
				break;
			case "RISK_DEP":
				repfile = baml_risk_category_ListService.getFile_RISK_DEP_Excel(userid,reportId, fromdate, todate,  dtltype, filetype,
						catgeory);
				break;
				
		}
				return repfile;
	
	}
	
	

	public ModelAndView getReportDetailsInq(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable, String ratvalue, String tablecol, String instancecode, String filter) {

		ModelAndView repdetail = new ModelAndView();
		logger.info("Getting Details for the Report :" + reportId);
		switch (reportId) {

		case "t7":
			repdetail = t7ReportService.getT7Dtl(reportId, fromdate, todate, currency, dtltype, pageable, filter);
			System.out.println("report Details");
			break;

		}

		return repdetail;
	}

	public ModelAndView getReportAdd(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable, String ratvalue, String tablecol) {

		logger.info("ReportServices -> getReportAdd()");

		ModelAndView repAdd = new ModelAndView();

		switch (reportId) {

		case "t12":
			repAdd = t12ReportService.addDetailRecord(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		}

		return repAdd;

	}

	public ModelAndView getReportInputEdit(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView repinput = new ModelAndView();
		logger.info("Getting Input Edit for the Report :" + reportId);
		switch (reportId) {

		case "t1current":
			repinput = T1CurrentReportService.getT1InputEdit(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		}

		return repinput;

	}

	public ModelAndView getReportInput(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) throws ParseException {

		ModelAndView repinput = new ModelAndView();
		logger.info("Getting Input for the Report :" + reportId);
		switch (reportId) {

		case "1":
			repinput = T1CurrentReportService.getT1Input(reportId, fromdate, todate, currency, dtltype, pageable);
			break;

		}

		return repinput;

	}

	public String preCheckReportScreening(String reportId, String fromdate, String todate) {

		String msg = "";

		logger.info("precheck : " + reportId);

		switch (reportId) {

		case "screening":
			msg = amlScreeningDownload.preCheck(reportId, fromdate, todate);
			break;

		default:
			logger.info("default -> preCheck()");
		}

		return msg;
	}

	public File getDownloadFileScr(String userid, String reportId, String fromdate, String todate, String currency,
			String dtltype, String filetype, String catgeory) throws FileNotFoundException, JRException, SQLException, ParseException {

		File repfile = null;

		logger.info("Getting Report File for : " + reportId + " in " + "pdf" + " format");

		switch (reportId) {

		case "screening":
			repfile = amlScreeningDownload.getFile(reportId, fromdate, todate, currency, dtltype, filetype);
			break;

		case "RISK_LOAN":
			repfile = baml_risk_category_ListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype,
					catgeory);
			break;
		case "RISK_RSS":
			repfile = baml_risk_category_ListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype,
					catgeory);
			break;
		case "RISK_DEP":
			repfile = baml_risk_category_ListService.getFile(reportId, fromdate, todate, currency, dtltype, filetype,
					catgeory);
			break;

		}

		return repfile;
	}

	

	
	
}
