package com.bornfire.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServices {
	@Autowired
	NegativeListService neg_list_service;
	
	@Autowired
	Cust_Aband_Fund_List_Service aband_Fund_list_service;
	
	@Autowired
	Cust_Hnwi_List_Service cust_hnwi_list_service;
	
	@Autowired
	Cust_Pep_List_Service cust_pep_list_service;
	
	@Autowired
	Cust_Black_List_Ind_Service cust_black_list_ind_service;
	
	@Autowired
	Cust_Black_List_Corp_Service cust_black_list_corp_service;
	
	@Autowired
	CRS_List_Services crs_list_service;

	public String uploadPreCheck(String screenId, String reportDate) {
		
		String msg = "";
		
		switch (screenId) {
		case "Neg_List":
//			msg = neg_list_service.uploadPreCheck(screenId, reportDate);
			break;
			
		
			
			
			default:
				System.out.println("default -> no report matched in switch");
			
		}
		
		return msg;
	}
	
	
	public String processUploadFiles(String screenId, MultipartFile file, String userid) throws FileNotFoundException, SQLException, IOException {
		
		String msg = "";
		
		switch(screenId) {
		case "Neg_List":
			msg = neg_list_service.processUpload(screenId, file, userid);
			break;
			
		case "Aband_Fund_List":
			msg = aband_Fund_list_service.processUpload(screenId, file, userid);
			break;
			
		case "HNWI_List":
			msg = cust_hnwi_list_service.processUpload(screenId, file, userid);
			break;
			
		case "PEP_List":
			msg = cust_pep_list_service.processUpload(screenId, file, userid);
			break;	
			
        case "CRS_List":
			msg = crs_list_service.processUpload(screenId, file, userid);
			break;	
			
			
		case "Ind_Black_List":
			msg = cust_black_list_ind_service.processUpload(screenId, file, userid);
			break;
			
		case "Corp_Black_List":
			msg = cust_black_list_corp_service.processUpload(screenId, file, userid);
			break;
			
			
		default:
			System.out.println("default -> no screen matched in switch");
		
		}
		
		
		return msg;

	}
	
	

}
