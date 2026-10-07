package com.bornfire.config;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component
public class SMS {
	
	
	public static String retval = "";
	//@Scheduled(cron = "0 * * ? * *")
	public String sms() throws IOException {
		String Msg ="hiii thala";
		String MobNum = "9176950810"	;

		try {

			String data = URLEncoder.encode("user", "UTF-8") + "="
					+ URLEncoder.encode("siddhaiyan@bornfire.in", "UTF-8");
			data += "&" + URLEncoder.encode("password", "UTF-8") + "="
					+ URLEncoder.encode("Bornfire2017", "UTF-8");
			data += "&" + URLEncoder.encode("msisdn", "UTF-8") + "="
					+ URLEncoder.encode(MobNum.trim(), "UTF-8");
			data += "&" + URLEncoder.encode("msg", "UTF-8") + "="
					+ URLEncoder.encode(Msg.trim(), "UTF-8");
			data += "&" + URLEncoder.encode("sid", "UTF-8") + "="
					+ URLEncoder.encode("BFIACC", "UTF-8");
			data += "&" + URLEncoder.encode("fl", "UTF-8") + "="
					+ URLEncoder.encode("0", "UTF-8");
			data += "&" + URLEncoder.encode("gwid", "UTF-8") + "="
					+ URLEncoder.encode("2", "UTF-8");
			
			URL url = new URL("http://180.150.251.49/vendorsms/pushsms.aspx");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
			conn.getOutputStream());
			wr.write(data);
			wr.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				retval += line;
			}
			wr.close();
			rd.close();
			String rsp = retval;
			System.out.println("Message Sent to "+MobNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}

}