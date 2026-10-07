package com.bornfire.controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.Services.UploadServices;

@RestController
public class FileUploaderController {
	 //Save the uploaded file to this folder
//    private static String UPLOADED_FOLDER = "F://temp//";
	 private final Logger logger = LoggerFactory.getLogger(FileUploaderController.class);

    @Autowired
    UploadServices uploadServices;
    
    

    @GetMapping("/")
    public String index() {
        return "upload";
    }
    

    
    @PostMapping(path = "/ws/fileUpload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("screenid") String screenId, HttpServletRequest rq) throws  SQLException, IOException {

    	 logger.debug("Single file upload!");
    	 
    	 System.out.println("screenId "+screenId);
    	
    	String msg="";
    	String userid = (String) rq.getSession().getAttribute("USERID");
		msg = uploadServices.processUploadFiles(screenId,  file, userid);
    	

        return msg;
    }
  

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
