package com.bornfire.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.taskapi.LiftAPI;

@RestController
@RequestMapping("/api")
public class TaskAPIController {
	
	


	@RequestMapping(method = RequestMethod.POST, value="/lift", produces = "application/json", consumes = "application/json")
		public ResponseEntity<String> liftDet(
				
				@RequestBody String request) throws JSONException  {

		
		JSONArray detArray = null;
		
		JSONArray buildingData = new JSONArray(request);
		List<LiftAPI> liftdetails = new ArrayList<>();

		System.out.println(liftdetails.toString());
						return new ResponseEntity<>(liftdetails.toString(), HttpStatus.OK);

		}

}
