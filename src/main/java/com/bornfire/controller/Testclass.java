package com.bornfire.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;  
public class Testclass {  
public static void main(String[] args) throws ParseException {  
	String message1 = "<i>Please find below the alert particulars,</i><br>";
	message1 +="<b>Card daily limit reached for the following customer:</b><br>";
	//if(cmnVal.getEmail_sub().equals("Evidence Source of Fund Alert")) {
	
String[] dt1="Customer ID: fsdfasda:/Cust Name :Ram".split("/");
	for (String info : dt1) {

		message1 += "<table border=1> <tr><td><b>" + info + "</b></td></tr></table><br>";

	}
	
	System.out.println("message1"+message1);
}
}  