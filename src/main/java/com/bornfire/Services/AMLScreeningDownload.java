package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@ConfigurationProperties("output")
public class AMLScreeningDownload {
	private static final Logger logger = LoggerFactory.getLogger(AMLScreeningDownload.class);
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;


	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	Environment env;
	
	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt9;

		Query query = null;
		try {
			query = hs.createNativeQuery("select count(*) from BAML_SCR_ALERT_OPER a where a.SCR_DATE=?1 ")
					.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		BigDecimal count = (BigDecimal) query.getSingleResult();

		int value = count.intValue();
		if (value > 0) {
			msg = "success";
		} else {
			msg = "Data Not available for the Report. Please Contact Administrator";
		}

		return msg;

	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException, ParseException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		String path =  env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		//fileName = reportId + "_" + todate;
		
		fileName = reportId + "_" + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));

		zipFileName =   fileName + ".zip";

		try {
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + reportId);
			    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLScreeningReports/screening_oper/ScreeningOper.jasper");

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("REPORT_DATE", todate);

			logger.info("BEFORE GENERATING PDF :" + reportId);
				
				
//				if (filetype.equals("pdf")) {
//					fileName = fileName + ".pdf";
//					path +=  fileName;
//					logger.info("BEFORE GENERATING PDF 1 :" + reportId);
//					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
//					logger.info("BEFORE GENERATING PDF 2 :" + path);
//					JasperExportManager.exportReportToPdfFile(jp, path);
//					logger.info("PDF File exported");
//				} else {
					fileName = fileName + ".xlsx";
					path +=   fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JRXlsxExporter exporter = new JRXlsxExporter();
					exporter.setExporterInput(new SimpleExporterInput(jp));
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
					exporter.exportReport();
					logger.info("Excel File exported");
//				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;

	}

}
