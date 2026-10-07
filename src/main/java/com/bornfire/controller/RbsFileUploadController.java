package com.bornfire.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.Services.RbsFileUpload;

@Controller
@RequestMapping(value = "upload")
public class RbsFileUploadController {
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private RbsFileUpload rbsFileUpload;
	
	@PostMapping("{reportid}")
	@ResponseBody
	public String FileUpload(@PathVariable(name="reportid") String reportid, @RequestParam("asondate") String asondate,
			@RequestParam("files") MultipartFile files, HttpServletRequest rq) throws IOException, SQLException, ParseException {
		
	

		String msg = "";
		
		try {
			asondate = dateFormat.format(new SimpleDateFormat("dd/MM/yyyy").parse(asondate));
			System.out.println("UPLOAD"+asondate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String userid = (String) rq.getSession().getAttribute("USERID");
		//msg = uploadServices.processUploadFiles(reportid, asondate, files, userid);
		msg = rbsFileUpload.processUploadFiles(reportid, asondate, files, userid);
		
		

		return msg;
	}

}
