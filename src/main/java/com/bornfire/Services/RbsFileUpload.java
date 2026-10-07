package com.bornfire.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class RbsFileUpload {
	
	
	@Autowired
	private T8ReportService t8ReportService;
	
	@Autowired
	private T9ReportServices t9reportServices;
	
	@Autowired
	private T10ReportService t10ReportServices;
	
	@Autowired
	private T18ReportService t18ReportService;
	
	@Autowired
	private T12ReportService t12ReportService;
	
	@Autowired
	private T15ReportService t15ReportService;
	
	
	@Autowired
	private Cust_Aband_Fund_List_Service Aband_Fund_List_Service;
	
	public String processUploadFiles(String reportId, String asondate, MultipartFile files, String userid) throws FileNotFoundException, SQLException, IOException, ParseException {
		
		String msg = "";
		System.out.println(reportId);
		switch(reportId) {
		
		case "t8":
			msg = t8ReportService.processUpload(asondate, files, userid);
			break;
		
		
		
		case "t9":
			msg = t9reportServices.processUpload(asondate, files, userid);
			break;
			
		case "t10":
			msg= t10ReportServices.processUpload(asondate, files, userid);
			break;
			
		case "t18":
			msg = t18ReportService.processUpload(asondate, files, userid);
			break;
			
		case "t12_RODRIGUES":
			msg = t12ReportService.processUpload(asondate, files, userid);
			break;
			
		case "t15":
			msg = t15ReportService.processUpload(asondate, files, userid);
			break;
			
		case "t22":
			msg = Aband_Fund_List_Service.processUploadRbs(asondate, files, userid);
			break;
			
		}
		
		
		
		
		
		return msg;

	}
}
