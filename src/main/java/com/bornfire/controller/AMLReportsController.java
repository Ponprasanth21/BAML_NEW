package com.bornfire.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.Services.AMLAccessRoleService;
import com.bornfire.Services.ReportServices;
import com.bornfire.Services.T11ReportService;
import com.bornfire.Services.T16ReportService;
import com.bornfire.Services.T17ReportService;
import com.bornfire.Services.T19ReportService;
import com.bornfire.Services.T1CurrentReportService;
import com.bornfire.Services.T20ReportService;
import com.bornfire.Services.T23ReportService;
import com.bornfire.Services.T24ReportService;
import com.bornfire.Services.T25ReportServices;
import com.bornfire.Services.T26ReportService;
import com.bornfire.Services.T28ReportServices;
import com.bornfire.Services.T7ReportServices;
import com.bornfire.Services.T8ReportService;
import com.bornfire.entity.CMG_MASTER;
import com.bornfire.entity.CMG_MASTER_REPOSITRY;
import com.bornfire.entity.T16.T16REPORT;
import com.bornfire.entity.T26.T26Report;
import com.bornfire.entity.t1.T1MasterProdDetail;
import com.bornfire.entity.t11.T11DetailRep;
import com.bornfire.entity.t11.T11Details;
import com.bornfire.entity.t17.T17Report;
import com.bornfire.entity.t19.T19Report;
import com.bornfire.entity.t20.T20Report;
import com.bornfire.entity.t23.T23Report;
import com.bornfire.entity.t24.T24Report;
import com.bornfire.entity.t25.T25Mod;
import com.bornfire.entity.t28.T28Reports;
import com.bornfire.entity.t7.T7Report;
import com.bornfire.entity.t8.T8ReportMod;

import net.sf.jasperreports.engine.JRException;

@Controller
@ConfigurationProperties("default")
@RequestMapping(value = "Reports")
public class AMLReportsController {

	private static final Logger logger = LoggerFactory.getLogger(AMLReportsController.class);

	@Autowired
	AMLAccessRoleService AccessRoleService;

	@Autowired
	ReportServices reportServices;

	@Autowired
	T17ReportService t17reportServices;
	
	@Autowired
	T7ReportServices t7reportServices;

	@Autowired
	T19ReportService t19reportServices;

	@Autowired
	T16ReportService t16reportServices;

	@Autowired
	T11DetailRep t11DetailsRep;
	@Autowired
	T11ReportService t11reportServices;

	@Autowired
	CMG_MASTER_REPOSITRY cmgRep;

	@Autowired
	T20ReportService t20reportServices;

	@Autowired
	T25ReportServices t25reportServices;

	@Autowired
	T24ReportService t24reportServices;

	@Autowired
	T23ReportService t23reportServices;

	@Autowired
	T26ReportService t26ReportService;

	@Autowired
	private CMG_MASTER_REPOSITRY cmgMaster;

	@Autowired
	T28ReportServices t28ReportServices;
	
	@Autowired
	T8ReportService t8reportService;
	
	@Autowired
	T1CurrentReportService t1CurrentReportService;

	// @Autowired
	// private T8Repositry t8rep;

	private String pagesize;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// To show the required report at the first stage
	@RequestMapping(value = "{reportid}", method = RequestMethod.POST)
	public ModelAndView reportView(@PathVariable("reportid") String reportId,
			@RequestParam(value = "function", required = false) String function,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) throws ParseException {
		System.out.println("fr" + fromdate);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		String currency = null;
		String userid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// if(dtltype.equals("report")) {
		md.addAttribute("menu", "AMLReports");

		/*
		 * loginServices.SessionLogging("REPORTS"+reportid, "M8",
		 * req.getSession().getId(), userid, req.getRemoteAddr(), "ACTIVE"); }else {
		 * md.addAttribute("menu", "XBRLArchives");
		 * loginServices.SessionLogging("ARCHREPORTS"+reportid, "M9",
		 * req.getSession().getId(), userid, req.getRemoteAddr(), "ACTIVE"); }
		 */

		logger.info("Get Report :" + reportId);
		try {

			fromdate = dateFormat.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromdate));
			todate = dateFormat.format(new SimpleDateFormat("dd/MM/yyyy").parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		logger.info("Assigning Model Attributes :" + reportId);
		// Assigning required Modal Attributes
		System.out.println("fromdate = " + fromdate);
		md.addAttribute("reportId", reportId);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("type", type);
		md.addAttribute("reportingTime", reportingTime);
		// md.addAttribute("reportTitle", reportServices.getReportName(reportid));
		logger.info("type1:" + type);
		logger.info("Getting ModelandView :" + reportId);
		ModelAndView mv = new ModelAndView();

		mv = reportServices.getReportView(reportId, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize));

		// System.out.println("----------------------");

		// Page<Object> sup0700RepPage = (Page<Object>)
		// mv.getModelMap().get("reportsummary");

		// sup0700RepPage.getContent().forEach((a)-> System.out.println(a.toString()));

		return mv;

	}

	// To check report data availability and Pending verification before
	@RequestMapping(value = "{reportid}/Precheck", method = RequestMethod.GET)
	@ResponseBody
	public String reportPreCheck(@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate) throws ParseException {

		logger.info("Precheck for Report :" + reportid);

		return reportServices.preCheckReport(reportid, fromdate, todate);

	}

	@RequestMapping(value = "{reportid}/Summary", method = RequestMethod.GET)
	public ModelAndView reportSummay(@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam("currency") String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) {

		logger.info("Getting Report Summary :" + reportid);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		logger.info("Assigning Model Attributes :" + reportid);
		md.addAttribute("menu", "XBRLReports");
		md.addAttribute("reportid", reportid);
		md.addAttribute("asondate", asondate);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("type", type);
		System.out.println("type" + type);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("displaymode", "summary");
		md.addAttribute("filter", filter);
		logger.info("type2:" + type);
		logger.info("Getting ModelandView :" + reportid);
		ModelAndView mv = reportServices.getReportSummary(reportid, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize), type);

		return mv;

	}
	


	@RequestMapping(value = "{reportid}/Details", method = RequestMethod.GET)
	public ModelAndView reportDetail(@PathVariable("reportid") String reportid,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "ratvalue", required = false) String ratvalue,
			@RequestParam(value = "tablecol", required = false) String tablecol,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam("currency") String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) {
		logger.info("Getting Report Details :" + reportid);
		logger.info("Assigning Model Attributes :" + reportid);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menu", "XBRLReports");
		md.addAttribute("reportid", reportid);
		md.addAttribute("asondate", asondate);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("type", type);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("instancecode", instancecode);
		md.addAttribute("filter", filter);
		md.addAttribute("displaymode", "detail");
		logger.info("type3:" + type);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		logger.info("Getting ModelandView :" + reportid + filter);
		ModelAndView mv = reportServices.getReportDetails(reportid, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize), ratvalue, tablecol, filter);

		return mv;
	}

	@RequestMapping(value = "{reportid}/Add", method = RequestMethod.GET)
	public ModelAndView reportAddForm(@PathVariable("reportid") String reportid,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "ratvalue", required = false) String ratvalue,
			@RequestParam(value = "tablecol", required = false) String tablecol,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) {

		logger.info("AMLReportsController -> reportAddForm()");

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		md.addAttribute("menu", "XBRLReports");
		md.addAttribute("reportid", reportid);
		md.addAttribute("asondate", asondate);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("type", type);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("displaymode", "add");
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));
		logger.info("type4:" + type);
		ModelAndView mv = reportServices.getReportAdd(reportid, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize), ratvalue, tablecol);

		System.out.println();

		return mv;
	}

	@RequestMapping(value = "{reportid}/Download", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource AMLDownload(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "reportingTime", required = false) String reportingTime,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");
		String currency = null;
		InputStreamResource resource = null;
		try {
			logger.info(
					"Getting download File :" + reportid + ", FileType :" + filetype + ", SubreportId :" + subreportid);
		 System.out.println(todate);
			File repfile = reportServices.getDownloadFile(null, reportid, fromdate, todate, currency, dtltype,
					filetype);
			logger.info("inside controller: 1 ");

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
			logger.info("inside controller:2 ");
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}
	@RequestMapping(value = "{reportid}/AMLReportDownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource AMLReportDownload1(HttpServletResponse response,
			@RequestParam(value = "reportId", required = false) String reportId,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "dtltype", required = false) String dtltype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			logger.info("Getting download File :" + reportId + ", FileType :" + filetype);
			// System.out.println(asondate);getDownloadFile

			File repfile = reportServices.getDownloadFile(userid, reportId, fromdate, todate, null, dtltype, filetype);
			logger.info("inside controller: 1 ");
			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
			
			logger.info("inside controller: 2 ",resource);

		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}


	@RequestMapping(value = "{reportid}/DetailsInq", method = RequestMethod.GET)
	public ModelAndView DetailInquiery(@PathVariable("reportid") String reportid,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "ratvalue", required = false) String ratvalue,
			@RequestParam(value = "tablecol", required = false) String tablecol,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam("currency") String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,

			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) {

		logger.info("Getting Report Details :" + reportid);
		logger.info("Assigning Model Attributes :" + reportid);

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		md.addAttribute("menu", "XBRLReports");
		md.addAttribute("reportid", reportid);
		md.addAttribute("asondate", asondate);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("type", type);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("instancecode", instancecode);
		md.addAttribute("displaymode", "detail");
		logger.info("type5:" + type);
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		logger.info("Getting ModelandView :" + reportid);
		ModelAndView mv = reportServices.getReportDetailsInq(reportid, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize), ratvalue, tablecol, instancecode, filter);

		return mv;
	}

	@RequestMapping(value = "/AMLReportDownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource AMLReportDownload(HttpServletResponse response,
			@RequestParam(value = "reportId", required = false) String reportId,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "dtltype", required = false) String dtltype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			logger.info("Getting download File :" + reportId + ", FileType :" + filetype);
			// System.out.println(asondate);getDownloadFile

			File repfile = reportServices.getDownloadFile(userid, reportId, fromdate, todate, null, dtltype, filetype);
			logger.info("inside controller: 1 ");
			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

			logger.info("inside controller: 2 ");

		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	/*
	 * @RequestMapping(value = "getBlobImage/{custid}", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String BlobImage(@PathVariable("custid") String custid,
	 * Model md) { KycHistory kycHistory=kycServices.BlobImage(custid);
	 * System.out.println(kycHistory.getDoc_image()); return
	 * Base64.getEncoder().encodeToString(kycHistory.getDoc_image()); }
	 */

	@RequestMapping(value = "{reportid}/Precheck", method = RequestMethod.POST)
	public String cust(@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate, Model md,
			HttpServletRequest req) throws ParseException {

		List<CMG_MASTER> custMaster_List = new ArrayList<>();

		List<Object[]> list_Objects = cmgRep.findAllCustom();

		for (Object[] obj : list_Objects) {
			CMG_MASTER info = new CMG_MASTER();
			info.setCust_id(String.valueOf(obj[0]));
			info.setCust_name(String.valueOf(obj[1]));
			info.setRiskrating(String.valueOf(obj[2]));

			custMaster_List.add(info);
		}

		md.addAttribute("CUSTOMERLIST", custMaster_List);
		return todate;

	}

	@RequestMapping(value = "{reportid}/Input", method = RequestMethod.GET)
	public ModelAndView reportInput(@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam("currency") String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) throws ParseException {
		logger.info("Get Report Input Screen" + reportid);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		logger.info("Assigning Model Attributes :" + reportid);
		md.addAttribute("menu", "XBRLReports");
		md.addAttribute("reportid", reportid);
		md.addAttribute("asondate", asondate);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("type", type);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("displaymode", "input");
		logger.info("type6:" + type);
		logger.info("Getting ModelandView :" + reportid);
		ModelAndView mv = reportServices.getReportInput(reportid, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize));
		return mv;
	}

	@RequestMapping(value = "{reportid}/InputEdit", method = RequestMethod.GET)
	public ModelAndView reportInputEdit(@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam("currency") String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) {
		logger.info("Get Report Input Screen" + reportid);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		logger.info("Assigning Model Attributes :" + reportid);
		md.addAttribute("menu", "XBRLReports");
		md.addAttribute("reportid", reportid);
		md.addAttribute("asondate", asondate);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("type", type);
		md.addAttribute("reportingTime", reportingTime);
		md.addAttribute("displaymode", "edit");
		logger.info("type7:" + type);
		logger.info("Getting ModelandView :" + reportid);
		ModelAndView mv = reportServices.getReportInputEdit(reportid, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize));
		return mv;
	}

	@RequestMapping(value = "createT11Add", method = RequestMethod.POST)
	@ResponseBody
	public String createuser(@RequestParam("custid") String custid, @ModelAttribute T11Details t11details, Model md,
			HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t11reportServices.addt11(t11details, custid);

		return msg;

	}

	@RequestMapping(value = "T20Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t20Modify(@ModelAttribute T20Report t20report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t20reportServices.editT20(t20report);

		return msg;

	}

	@RequestMapping(value = "T20Verify", method = RequestMethod.POST)
	@ResponseBody
	public String t20Verify(@ModelAttribute T20Report t20report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t20reportServices.verifyT20(t20report);

		return msg;

	}
	

	@RequestMapping(value = "T7Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t7Modify(@ModelAttribute T7Report t7report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t7reportServices.editT7(t7report);

		return msg;

	}

	@RequestMapping(value = "T7Verify", method = RequestMethod.POST)
	@ResponseBody
	public String T7Verify(@ModelAttribute T7Report t7report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t7reportServices.verifyT7(t7report);

		return msg;

	}

	@RequestMapping(value = "T17Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t17Modify(@ModelAttribute T17Report t17report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t17reportServices.editT17(t17report);

		return msg;

	}

	@RequestMapping(value = "T17Verify", method = RequestMethod.POST)
	@ResponseBody
	public String T17Verify(@ModelAttribute T17Report t17report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t17reportServices.verifyT17(t17report);

		return msg;

	}

	@RequestMapping(value = "T19Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t19Modify(@ModelAttribute T19Report t17report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t19reportServices.editT19(t17report);

		return msg;

	}

	@RequestMapping(value = "T19Verify", method = RequestMethod.POST)
	@ResponseBody
	public String T19Verify(@ModelAttribute T19Report t17report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t19reportServices.verifyT19(t17report);

		return msg;

	}

	@RequestMapping(value = "T16Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t16Modify(@ModelAttribute T16REPORT t16report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t16reportServices.editT16(t16report);

		return msg;

	}

	@RequestMapping(value = "T16Verify", method = RequestMethod.POST)
	@ResponseBody
	public String T16Verify(@ModelAttribute T16REPORT t16report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t16reportServices.verifyT16(t16report);

		return msg;

	}

	@RequestMapping(value = "T25Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t25Modify(@ModelAttribute T25Mod t25report, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		System.out.println("Edit iuiiu---->" + t25report.getEntity_flg());
		String msg = t25reportServices.editT25(t25report);

		return msg;

	}

	@RequestMapping(value = "T25Verify", method = RequestMethod.POST)
	@ResponseBody
	public String t25Verify(@ModelAttribute T25Mod t25report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		System.out.println("Verify iuiiu---->" + t25report.getModify_flg());

		String msg = t25reportServices.verifyT25(t25report);

		return msg;

	}

	@RequestMapping(value = "T24Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t24Modify(@ModelAttribute T24Report t24report, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t24reportServices.editT24(t24report);

		return msg;

	}

	@RequestMapping(value = "T24Verify", method = RequestMethod.POST)
	@ResponseBody
	public String t24Verify(@ModelAttribute T24Report t24report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t24reportServices.verifyT24(t24report);

		return msg;

	}

	@RequestMapping(value = "getCaseCustT11", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCaseCust(@RequestParam(required = false) String dtltype,
			@RequestParam(required = false) String cust_id, @RequestParam(required = false) String custName2,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));
		if (dtltype == null || dtltype.equals("report1")) {
			System.out.println(dtltype);
			md.addAttribute("dtltype", "report1");
			md.addAttribute("Cust", cmgMaster.getcustId(cust_id));
			md.addAttribute("Cust", cmgMaster.getcustomerName(custName2));
			System.out.println("custID" + cmgMaster.getcustId(cust_id));
		}

		// md.addAttribute("inquiryflag", "inquiryflag");

		return "ReportT11";
	}

	@RequestMapping(value = "{reportid}/PrecheckScr", method = RequestMethod.GET)
	@ResponseBody
	public String reportPreCheckForscreeing(@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate) throws ParseException {

		logger.info("Precheck for Report :" + reportid);

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		Date ConDateFromdate = dateFormat1.parse(todate);

		String strDate2 = formatter1.format(ConDateFromdate);
		todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

		return reportServices.preCheckReportScreening(reportid, fromdate, todate);

	}

	@RequestMapping(value = "{reportid}/DownloadScr", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource AMLDownloadScr(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "reportingTime", required = false) String reportingTime,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filetype", required = false) String filetype)
			throws IOException, SQLException, ParseException {
		response.setContentType("application/octet-stream");

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		Date ConDateFromdate = dateFormat1.parse(todate);

		String strDate2 = formatter1.format(ConDateFromdate);
		todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

		InputStreamResource resource = null;
		try {
			logger.info(
					"Getting download File :" + reportid + ", FileType :" + filetype + ", SubreportId :" + subreportid);
			// System.out.println(asondate);getDownloadFile
			File repfile = reportServices.getDownloadFileScr(null, reportid, fromdate, todate, currency, dtltype,
					filetype, null);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "{reportid}/PrecheckFromrpt", method = RequestMethod.GET)
	@ResponseBody
	public String reportPrecheckFromrpt(@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate) throws ParseException {

		logger.info("Precheck for Report :" + reportid);

		return reportServices.preCheckReport(reportid, fromdate, todate);

	}

	@RequestMapping(value = "{reportid}/DownloadFormRptScr", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public InputStreamResource AMLDownloadFormRptScr(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "reportingTime", required = false) String reportingTime,
			@RequestParam(value = "instancecode", required = false) String instancecode,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {
		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			logger.info(
					"Getting download File :" + reportid + ", FileType :" + filetype + ", SubreportId :" + subreportid);
			// System.out.println(asondate);getDownloadFile
			File repfile = reportServices.getDownloadFile(null, reportid, fromdate, todate, currency, dtltype,
					filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@RequestMapping(value = "{reportid}/view", method = RequestMethod.POST)
	public ModelAndView reportformView(@PathVariable("reportid") String reportId,
			@RequestParam(value = "function", required = false) String function,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam("currency") String currency,
			@RequestParam(value = "subreportid", required = false) String subreportid,
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "reportingTime", required = false) String reportingTime, Model md,
			HttpServletRequest req) throws ParseException {

		String roleId = (String) req.getSession().getAttribute("ROLEID");
		md.addAttribute("AMLRoleMenu", AccessRoleService.getRoleMenu(roleId));

		String userid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// if(dtltype.equals("report")) {
		md.addAttribute("menu", "AMLFormReports");

		/*
		 * loginServices.SessionLogging("REPORTS"+reportid, "M8",
		 * req.getSession().getId(), userid, req.getRemoteAddr(), "ACTIVE"); }else {
		 * md.addAttribute("menu", "XBRLArchives");
		 * loginServices.SessionLogging("ARCHREPORTS"+reportid, "M9",
		 * req.getSession().getId(), userid, req.getRemoteAddr(), "ACTIVE"); }
		 */

		logger.info("Get Report :" + reportId);
		try {

			fromdate = dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(fromdate));
			todate = dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		logger.info("Assigning Model Attributes :" + reportId);
		// Assigning required Modal Attributes
		System.out.println("fromdate = " + fromdate);
		md.addAttribute("reportId", reportId);
		md.addAttribute("fromdate", fromdate);
		md.addAttribute("todate", todate);
		md.addAttribute("currency", currency);
		md.addAttribute("dtltype", dtltype);
		md.addAttribute("type", type);
		md.addAttribute("reportingTime", reportingTime);
		// md.addAttribute("reportTitle", reportServices.getReportName(reportid));

		logger.info("Getting ModelandView :" + reportId);
		ModelAndView mv = new ModelAndView();

		mv = reportServices.getReportView(reportId, fromdate, todate, currency, dtltype,
				PageRequest.of(currentPage, pageSize));

		// System.out.println("----------------------");

		// Page<Object> sup0700RepPage = (Page<Object>)
		// mv.getModelMap().get("reportsummary");

		// sup0700RepPage.getContent().forEach((a)-> System.out.println(a.toString()));

		return mv;

	}

	@RequestMapping(value = "/AMLReportDownloadFormattedDate", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource AMLReportDownloadFormattedDate(HttpServletResponse response,
			@RequestParam(value = "reportId", required = false) String reportId,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "category", required = false) String category)
			throws IOException, SQLException, ParseException {
		response.setContentType("application/octet-stream");

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		Date ConDateFromdate = dateFormat1.parse(fromdate);
		System.out.println(ConDateFromdate);

		String strDate2 = formatter1.format(ConDateFromdate);
		fromdate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

		Date ConToDate = dateFormat1.parse(todate);
		System.out.println(ConToDate);

		String strDate1 = formatter1.format(ConToDate);
		todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));

		InputStreamResource resource = null;
		try {
			logger.info("Getting download File :" + reportId + ", FileType :" + filetype);
			// System.out.println(asondate);getDownloadFile
			File repfile = reportServices.getDownloadFileScr(userid, reportId, fromdate, todate, null, null, filetype,
					category);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return resource;
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
	 * reportingTime, Model md, HttpServletRequest req) {
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
	 * size.orElse(Integer.parseInt(pagesize)); logger.info("Getting ModelandView :"
	 * + reportid); md.addAttribute("reportdetails", t8rep.gett8details(todate,
	 * PageRequest.of(currentPage, pageSize)));
	 * 
	 * //ModelAndView mv = reportServices.getReportDetails(reportid, fromdate,
	 * todate, currency, dtltype, //PageRequest.of(currentPage, pageSize), ratvalue,
	 * tablecol); return "ReportT8"; }
	 */

	@RequestMapping(value = "T23Edit", method = RequestMethod.POST)
	@ResponseBody
	public String T23Edit(@ModelAttribute T23Report t23report, Model md, HttpServletRequest rq) {
		// System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t23reportServices.editT23(t23report);

		return msg;

	}

	@RequestMapping(value = "T23Verify", method = RequestMethod.POST)
	@ResponseBody
	public String T23Verify(@ModelAttribute T23Report t23report, Model md, HttpServletRequest rq) {
		// System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t23reportServices.verifyT23(t23report);

		return msg;

	}

	@RequestMapping(value = "T26Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t26Modify(@ModelAttribute T26Report t26report, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		System.out.println("t26 edit");
		String msg = t26ReportService.editT26(t26report);

		return msg;

	}

	@RequestMapping(value = "T26Verify", method = RequestMethod.POST)
	@ResponseBody
	public String t26Verify(@ModelAttribute T26Report t26report, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t26ReportService.verifyT26(t26report);

		return msg;

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
		System.out.println();
		String msg = t28ReportServices.verifyT28(t28reports);

		return msg;

	}
	
	
	@RequestMapping(value = "T8Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t8Modify(@ModelAttribute T8ReportMod t8ReportMod, Model md, HttpServletRequest rq) {
		System.out.println("createUser");
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t8reportService.editT8(t8ReportMod);

		return msg;

	}
	
	@RequestMapping(value = "T1Edit", method = RequestMethod.POST)
	@ResponseBody
	public String t1Modify(@ModelAttribute T1MasterProdDetail t1report, Model md, HttpServletRequest rq) {
		
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = t1CurrentReportService.editT1(t1report);

		return msg;

	}
	
	@GetMapping("/AMLReportDownloadXLSX")
	public ResponseEntity<InputStreamResource> AMLDownloadExcel(HttpServletResponse response,
			@RequestParam(value = "reportId", required = false) String reportId,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "dtltype", required = false) String dtltype,
			@RequestParam(value = "category", required = false) String category) throws IOException, SQLException, JRException, ParseException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		String toDAte = null;

		if (fromdate != null && !fromdate.isEmpty()) {

			if(todate.equals("undefined")) {
				toDAte="";
			}else {
				Date ConToDate = dateFormat1.parse(todate);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}
			
		}else {
			toDAte="";
		}
		
		HttpHeaders headers = new HttpHeaders();
		switch (reportId) {
			case "PEP_List":
					headers.add("Content-Disposition", "attachment; filename=PEP_List_"+toDAte+".xlsx");
			break;
			
			case "Daily_Loan":
				headers.add("Content-Disposition", "attachment; filename=Daily_LOAN_"+toDAte+".xlsx");
				break;
				
			case "Daily_Rss":
				headers.add("Content-Disposition", "attachment; filename=Daily_RSS_"+toDAte+".xlsx");
				break;
				
			case "Daily_Deposit":
				headers.add("Content-Disposition", "attachment; filename=Daily_Deposit_"+toDAte+".xlsx");
				break;
				
			case "Daily_cash_tran":
				headers.add("Content-Disposition", "attachment; filename=Daily_Cash_"+toDAte+".xlsx");
				break;
				
			case "ABAND_List":
				headers.add("Content-Disposition", "attachment; filename=ABAND_List_"+dateFormat.format(new Date())+".xlsx");
				break;
				
			case "Black_List_Ind":
				headers.add("Content-Disposition", "attachment; filename=Black_List_IND_"+toDAte+".xlsx");
				break;

			case "Black_List_Corp":
				headers.add("Content-Disposition", "attachment; filename=Black_List_Corp_"+toDAte+".xlsx");
				break;
				
			case "HNWI_List":
				headers.add("Content-Disposition", "attachment; filename=HNWI_List_"+toDAte+".xlsx");
				break;
				
			case "UNSC_List":
				headers.add("Content-Disposition", "attachment; filename=UNSC_List_"+toDAte+".xlsx");
				break;
				
			case "screening":
				headers.add("Content-Disposition", "attachment; filename=screening_"+toDAte+".xlsx");
				break;

			case "RISK_LOAN":
				headers.add("Content-Disposition", "attachment; filename=RISK_LOAN_"+toDAte+".xlsx");
				break;
			case "RISK_RSS":
				headers.add("Content-Disposition", "attachment; filename=RISK_RSS_"+toDAte+".xlsx");
				break;
			case "RISK_DEP":
				headers.add("Content-Disposition", "attachment; filename=RISK_DEP_"+toDAte+".xlsx");
				break;
				
		}
		
		return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(reportServices.getDownloadFileExcel(null, reportId, fromdate, todate, dtltype,
    					filetype,category)));

	
	}
	

}
