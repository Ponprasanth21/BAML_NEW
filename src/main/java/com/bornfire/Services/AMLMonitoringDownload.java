package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@Transactional
@ConfigurationProperties("output")
public class AMLMonitoringDownload {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;


	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");



	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt9;

		try {
			// dt1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromdate);
		//	dt9 = new SimpleDateFormat("dd-MM-yyyy").parse(todate);

			/*
			 * Long dtlcnt = (Long) hs.createQuery(
			 * "select count(*) from T9Report a where a.rep_period_from=?1 and  a.rep_period_to=?2"
			 * ) .setParameter(1, dt1).setParameter(2, dt9).getSingleResult();
			 */

			msg = "success";

		} catch (Exception e) {

			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		System.out.println(todate);
		String path = "";
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date ConDate = dateFormat1.parse(todate);
	System.out.println(ConDate);
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");  
	String strDate1 = formatter1.format(ConDate);
			fileName = reportId + "_" + strDate1;
		} catch (ParseException e1) {

			
			e1.printStackTrace();
		}

		zipFileName =  fileName + ".zip";

		// String filetype="detailexcel";
		// dtltype="report";
		try {
			InputStream jasperFile;
			HashMap<String, Object> map = new HashMap<String, Object>();

			jasperFile = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/rule_main.jasper");
			
			InputStream subrep1 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule0001/Rule1.jasper");
		InputStream subrep2 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule0002/Rule2.jasper");
			InputStream subrep3 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule0003/Rule3.jasper");
			InputStream subrep4 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule0004/Rule4.jasper");
			InputStream subrep5 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule5/Rule5.jasper");
			
			InputStream subrep6 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule6/Rule6.jasper");
			InputStream subrep7 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule7/Rule7.jasper");
			InputStream subrep8 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule8/Rule8.jasper");
			InputStream subrep9 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule9/Rule9.jasper");
			InputStream subrep10 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule10/Rule10.jasper");
			
			InputStream subrep11 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule11/Rule11.jasper");
			InputStream subrep12 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule12/Rule12.jasper");
			InputStream subrep13 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule13/Rule13.jasper");
			InputStream subrep14 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule14/Rule14.jasper");
			
			InputStream subrep16 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule16/Rule16.jasper");
			InputStream subrep18 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule18/Rule18.jasper");
			
			InputStream subrep22 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule22/Rule22.jasper");
			InputStream subrep23 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule23/Rule23.jasper");
			InputStream subrep24 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule24/Rule24.jasper");
			InputStream subrep25 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule25/Rule25.jasper");
			InputStream subrep26 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule26/Rule26.jasper");
			InputStream subrep27 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule27/Rule27.jasper");
			InputStream subrep28 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule28/Rule28.jasper");
			InputStream subrep29 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule29/Rule29.jasper");
			InputStream subrep30 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule30/Rule30.jasper");
			InputStream subrep31 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule31/Rule31.jasper");
			InputStream subrep32 = this.getClass().getResourceAsStream("/static/jasper/AMLMonitoringReports/Rule32/Rule32.jasper");
	
		//	JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);

		
			try {
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
				 Date ConDate = dateFormat1.parse(todate);
		System.out.println(ConDate);
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		String strDate1 = formatter1.format(ConDate);
				
				String today = dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
				map.put("ALERT_DATE", strDate1);
			} catch (ParseException e1) {

			
				e1.printStackTrace();
			}
		//	map.put("REPORT_DATE", todate);
			
			map.put("RULE1", subrep1);
			map.put("RULE2", subrep2);
			map.put("RULE3", subrep3);
			map.put("RULE4", subrep4);
			map.put("RULE5", subrep5);
			map.put("RULE6", subrep6);
			map.put("RULE7", subrep7);
			map.put("RULE8", subrep8);
			map.put("RULE9", subrep9);
			map.put("RULE10", subrep10);
			map.put("RULE11", subrep11);
			map.put("RULE12", subrep12);
			map.put("RULE13", subrep13);
			map.put("RULE14", subrep14);
			map.put("RULE16", subrep16);
			map.put("RULE18", subrep18);
			map.put("RULE22", subrep22);
			map.put("RULE23", subrep23);
			map.put("RULE24", subrep24);
			map.put("RULE25", subrep25);
			map.put("RULE26", subrep26);
			map.put("RULE27", subrep27);
			map.put("RULE28", subrep28);
			map.put("RULE29", subrep29);
			map.put("RULE30", subrep30);
			map.put("RULE31", subrep31);
			map.put("RULE32", subrep32);
		//	map.put("ALERT_DATE", todate);
			
			
		System.out.println("afetr sub repoty");
		JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
			fileName = fileName + ".xlsx";
			path =  fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			exporter.exportReport();

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;

	}

}
