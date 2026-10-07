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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
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
public class RBSReportGeneration {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	Environment env;	
	
	private static final Logger logger = LoggerFactory.getLogger(RBSReportGeneration.class);

	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt9;
System.out.println("prechecktodate"+todate);
		try {
			// dt1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromdate);
			dt9 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

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

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		logger.info("GET GENERATION");
		System.out.println("generation"+todate);
		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date ConDate = dateFormat1.parse(todate);
	System.out.println(ConDate);
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
	String strDate1 = formatter1.format(ConDate);
			fileName = "t"+reportId + "_" + strDate1;
			logger.info("GET GENERATION"+fileName);
		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName = fileName + ".zip";

		// String filetype="detailexcel";
		// dtltype="report";
		try {
			InputStream jasperFile;
			HashMap<String, Object> map = new HashMap<String, Object>();

			jasperFile = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/Report_main.jasper");
			
		
			InputStream subrep1 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T1/T1Curr.jasper");
			InputStream subrep2 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T2C/T2C.jasper");
			InputStream subrep3 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T3A/T3A.jasper");
			InputStream subrep4 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T3B/T3B.jasper");
			InputStream subrep5 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T4/T4.jasper");
			InputStream subrep6 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T5/T5.jasper");
			InputStream subrep7 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T6/T6.jasper");
			InputStream subrep8 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T7/T7.jasper");
			InputStream subrep9 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T8Copy/T8.jasper");
			InputStream subrep10 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T9jas/T9.jasper");
			InputStream subrep11 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T10Copy/T10.jasper");
			InputStream subrep12 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T11jas/T11.jasper");
			InputStream subrep13 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T12/T12.jasper");
			InputStream subrep14 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T13/T13.jasper");
			InputStream subrep15 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T14/T14.jasper");
			InputStream subrep16 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T15/T15.jasper");
			InputStream subrep17 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T16/T16.jasper");
			InputStream subrep18 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T17Copy/T17.jasper");
			InputStream subrep19 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T18/T18.jasper");
			InputStream subrep20 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T19/T19.jasper");
			InputStream subrep21 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T20Copy/T20.jasper");
			InputStream subrep22 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T21Copy/T21.jasper");
			InputStream subrep23 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T22/T22.jasper");
			InputStream subrep24 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T23/T23.jasper");
			InputStream subrep25 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T24Copy/T24.jasper");
			InputStream subrep26 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T25Copy/T25.jasper");
			InputStream subrep27 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T26/T26.jasper");
			InputStream subrep28 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/T27C/T27C.jasper");
			InputStream subrep29 = this.getClass().getResourceAsStream("/static/jasper/T28OUT/T28.jasper");
			InputStream subrep30 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/INDEX/RBSINDEX.jasper");
			InputStream subrep31 = this.getClass().getResourceAsStream("/static/jasper/ColorAmlJasper(consol)/INSTRUCTION/RBSINST.jasper");

			
			
			
			
			map.put("INST", subrep31);
			map.put("INDEX",subrep30);
			map.put("T1C", subrep1);
			map.put("T2C", subrep2);
			map.put("T3A", subrep3);
			map.put("T3B", subrep4);
			map.put("T4", subrep5);
			map.put("T5", subrep6);
			map.put("T6", subrep7);
			map.put("T7", subrep8);
			map.put("T8", subrep9);
			map.put("T9", subrep10);
			map.put("T10", subrep11);
			map.put("T11", subrep12);
			map.put("T12", subrep13);
			map.put("T13", subrep14);
			map.put("T14", subrep15);
			map.put("T15", subrep16);
			map.put("T16", subrep17);
			map.put("T17", subrep18);
			map.put("T18", subrep19);
			map.put("T19", subrep20);
			map.put("T20", subrep21);
			map.put("T21", subrep22);
			map.put("T22", subrep23);
			map.put("T23", subrep24);
			map.put("T24", subrep25);
			map.put("T25", subrep26);
			map.put("T26", subrep27);
			map.put("T27C", subrep28);
			map.put("T28", subrep29);
			
			
			

			
			
			logger.info("GET GENERATION ASSIGNING PARAMETER");

			JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
			try {
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
				 Date ConDate = dateFormat1.parse(todate);
		System.out.println(ConDate);
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		String strDate1 = formatter1.format(ConDate);
				
				String today = dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
				map.put("REPORT_DATE", strDate1);
				logger.info("GET GENERATION"+strDate1);

			} catch (ParseException e1) {

				logger.info(e1.getMessage());
				e1.printStackTrace();
			}
			fileName = fileName + ".xlsx";
			path +=  fileName;
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
