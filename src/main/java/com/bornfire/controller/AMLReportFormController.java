package com.bornfire.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bornfire.Services.T1CurrentReportService;
import com.bornfire.entity.t1.T1MasterProdDetail;




@Controller
@ConfigurationProperties("default")
@RequestMapping("Reports/{reportid}/Form")
public class AMLReportFormController {

	private static final Logger logger = LoggerFactory.getLogger(AMLReportFormController.class);

	private String pagesize;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	@Autowired
	T1CurrentReportService T1CurrentReportService;

	@RequestMapping(value = "T1Input", method = RequestMethod.POST)
	@ResponseBody
	public String T1Input(@ModelAttribute("reportSummary") T1MasterProdDetail inputform, @PathVariable("reportid") String reportid,
			@RequestParam(value = "asondate", required = false) String asondate,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,
			@RequestParam("currency") String currency, HttpServletRequest rq) {
		
	

		String msg = "";

		String userid = (String) rq.getSession().getAttribute("USERID");

		msg = T1CurrentReportService.saveReportT1(reportid, fromdate, todate, currency, inputform, userid);

		/*
		 * if (reportServices.saveReport(reportid, asondate, fromdate, todate,
		 * currency).equals("success")) { return msg; } else {
		 * 
		 * return "Error Occured. Please contact Administraotr"; }
		 */
		return msg;
	}
	



}
