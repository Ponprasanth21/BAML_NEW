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

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@ConfigurationProperties("output")
@Transactional
public class JASPERDOWNLOAD {

	@Autowired
	DataSource srcdataSource;
	
	private static final Logger logger = LoggerFactory.getLogger(JASPERDOWNLOAD.class);

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		 
		 
		 Date ConDateFromdate = null;
		try {
			ConDateFromdate = dateFormat1.parse(fromdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 System.out.println(ConDateFromdate);
		
		 String strDate2 = formatter1.format(ConDateFromdate);
		 try {
			fromdate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 Date ConToDate = null;
		try {
			ConToDate = dateFormat1.parse(todate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 System.out.println(ConToDate);
		   
		 String strDate1 = formatter1.format(ConToDate);
		 try {
			todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		String path = "";
		String fileName = "";
		
		File outputFile;

		logger.info("Getting Output file :" + reportId);

		String msg="";


	
		 if (!filetype.equals("xbrl")) {

			try {
				
				
				InputStream fileStream = null;
				 fileName = reportId + "_" + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
				 fileStream = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T10COLOR/T10.jasper");
				 
			
				 JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
					HashMap<String, Object> map = new HashMap<String, Object>();

					logger.info("Assigning Parameters for Jasper");
					map.put("REPORT_DATE", todate);
					//map.put("FROM_DATE", fromdate);

					logger.info("BEFORE GENERATING PDF :" + reportId);
					if (filetype.equals("pdf")) {
						fileName = fileName + ".pdf";
						path =  fileName;
						logger.info("BEFORE GENERATING PDF 1 :" + reportId);
						JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
						logger.info("BEFORE GENERATING PDF 2 :" + path);
						JasperExportManager.exportReportToPdfFile(jp, path);
						logger.info("PDF File exported");
					} else {
						fileName = fileName + ".xlsx";
						path =   fileName;
						JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
						JRXlsxExporter exporter = new JRXlsxExporter();
						exporter.setExporterInput(new SimpleExporterInput(jp));
						exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
						exporter.exportReport();
						logger.info("Excel File exported");
					}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		outputFile = new File(path);

		return outputFile;

	}

}
