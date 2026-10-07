package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.entity.T26.T26Report;
import com.bornfire.entity.T26.T26ReportRepo;
import com.bornfire.entity.t24.T24Detail;
import com.bornfire.entity.t24.T24Report;

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
public class T26ReportService {
	
	private static final Logger logger = LoggerFactory.getLogger(T26ReportService.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;



	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;
	
	@Autowired
	T26ReportRepo t26ReportRepo;
	
	@Autowired
	Environment env;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public ModelAndView getT24View(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) throws ParseException {

		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		
		List<T26Report> T26rep = new ArrayList<T26Report>();
		
		Date d1 = df.parse(todate);
		
		T26rep = t26ReportRepo.getT26ReportSummary(d1);
		

		
	
		mv.setViewName("ReportT26");
		//mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T26rep);
		mv.addObject("displaymode", "summary");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + mv.getViewName());

		return mv;

	}
	
	
	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dT24;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT24 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs
					.createQuery("select count(*) from T26Report a where a.report_from_date=?1 and  a.report_to_date=?2")
					.setParameter(1, dt1).setParameter(2, dT24).getSingleResult();

			if (dtlcnt > 0) {
				
					msg = "success";
				
			} else {
				msg = "Data Not available for the Report. Please Contact Administrator";

				

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}
	
	
	public ModelAndView getT24Rep(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		
		List<T26Report> T26rep = new ArrayList<T26Report>();
		
		Date d1 = null;
		try {
			d1 = df.parse(todate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		T26rep = t26ReportRepo.getT26ReportSummary(d1);
		

		logger.info("Inside archive");
	
		mv.setViewName("ReportT26");
		//mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T26rep);
		mv.addObject("displaymode", "summary");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + mv.getViewName());

		return mv;

	}
	
	
	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		logger.info("Getting Output file :" + reportId);

		

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date ConDate = dateFormat1.parse(todate);
	System.out.println(ConDate);
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
	String strDate1 = formatter1.format(ConDate);
			fileName = "t"+reportId + "_" + strDate1;
		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName =  fileName + ".zip";

		if (!filetype.equals("xbrl")) {

				
			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T26/T26.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T26/T26.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T26/T26.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T26/T26.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				try {
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
					 Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
			String strDate1 = formatter1.format(ConDate);
					
					String today = dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
					map.put("REPORT_DATE", today);
				} catch (ParseException e1) {

					logger.info(e1.getMessage());
					e1.printStackTrace();
				}


				

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path +=  fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					
					System.out.println("EXCEEEEEll");
					fileName = fileName + ".xlsx";
					path +=  fileName;
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
	
	public String editT26(T26Report t26report) {
		
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		T26Report up = t26report;

		up.getReport_date();
		up.setA_1_desc("By the BOM");
		up.setA_2_desc("No. of violations for");
		up.setA_3_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_4_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_5_desc("No. of breaches relating to legislative requirements:");
		up.setA_6_desc("CDD");
		up.setA_7_desc("PEP");
		up.setA_8_desc("Internal control rules");
		up.setA_9_desc("STRs");
		up.setA_10_desc("Other types of violations");
		up.setA_11_desc("No. of warnings issued to");
		up.setA_12_desc("No. of show cause issued to");
		up.setA_13_desc("No. of penalties imposed on");
		up.setA_14_desc("Total amount of penalties imposed on");
		up.setA_15_desc("No. of corrective or remedial actions required or taken");
		up.setA_16_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_17_desc("No of convictions");
		up.setA_18_desc("By the FSC");
		up.setA_19_desc("No. of violations for ");
		up.setA_20_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_21_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_22_desc("No. of breaches relating to legislative requirements:");
		up.setA_23_desc("CDD");
		up.setA_24_desc("PEP");
		up.setA_25_desc("Internal control rules");
		up.setA_26_desc("STRs");
		up.setA_27_desc("Other types of violations");
		up.setA_28_desc("No. of penalties imposed on");
		up.setA_29_desc("Total amount of penalties imposed on");
		up.setA_30_desc("No. of corrective or remedial actions required or taken");
		up.setA_31_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_32_desc("No of convictions");
		up.setA_33_desc("By the FIU and ICAC");
		up.setA_34_desc("No. of violations for ");
		up.setA_35_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_36_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_37_desc("No. of breaches relating to legislative requirements:");
		up.setA_38_desc("CDD");
		up.setA_39_desc("PEP");
		up.setA_40_desc("Internal control rules");
		up.setA_41_desc("STRs");
		up.setA_42_desc("Other types of violations");
		up.setA_43_desc("No. of penalties imposed on");
		up.setA_44_desc("Total amount of penalties imposed on");
		up.setA_45_desc("No. of corrective or remedial actions required or taken");
		up.setA_46_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_47_desc("No. of convictions");
		up.setA_48_desc("Intelligence reports received from FIU on NBDTIs customers");
		up.setA_49_desc("No of convictions");
		up.setA_50_desc("By foreign supervisors");
		up.setA_51_desc("No. of violations for ");
		up.setA_52_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_53_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_54_desc("No. of breaches relating to legislative requirements:");
		up.setA_55_desc("CDD");
		up.setA_56_desc("PEP");
		up.setA_57_desc("Internal control rules");
		up.setA_58_desc("STRs");
		up.setA_59_desc("Other types of violations");
		up.setA_60_desc("No. of penalties imposed on");
		up.setA_61_desc("Total amount of penalties imposed on");
		up.setA_62_desc("No. of corrective or remedial actions required or taken");
		up.setA_63_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_64_desc("No. of convictions");
		up.setA_65_desc("Intelligence reports received from FIU on NBDTIs customers");
		
		up.setModify_flag("Y");
		up.setDel_flag("N");
		up.setEntity_flag("N");
		hs.saveOrUpdate(up);

		msg = "Record Edited Successfully ";

		return msg;
	}

	public String verifyT26(T26Report t26report) {
		
		String msg = "";
		Session session = sessionFactory.getCurrentSession();

		T26Report up = t26report;
		up.setA_1_desc("By the BOM");
		up.setA_2_desc("No. of violations for");
		up.setA_3_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_4_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_5_desc("No. of breaches relating to legislative requirements:");
		up.setA_6_desc("CDD");
		up.setA_7_desc("PEP");
		up.setA_8_desc("Internal control rules");
		up.setA_9_desc("STRs");
		up.setA_10_desc("Other types of violations");
		up.setA_11_desc("No. of warnings issued to");
		up.setA_12_desc("No. of show cause issued to");
		up.setA_13_desc("No. of penalties imposed on");
		up.setA_14_desc("Total amount of penalties imposed on");
		up.setA_15_desc("No. of corrective or remedial actions required or taken");
		up.setA_16_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_17_desc("No of convictions");
		up.setA_18_desc("By the FSC");
		up.setA_19_desc("No. of violations for ");
		up.setA_20_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_21_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_22_desc("No. of breaches relating to legislative requirements:");
		up.setA_23_desc("CDD");
		up.setA_24_desc("PEP");
		up.setA_25_desc("Internal control rules");
		up.setA_26_desc("STRs");
		up.setA_27_desc("Other types of violations");
		up.setA_28_desc("No. of penalties imposed on");
		up.setA_29_desc("Total amount of penalties imposed on");
		up.setA_30_desc("No. of corrective or remedial actions required or taken");
		up.setA_31_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_32_desc("No of convictions");
		up.setA_33_desc("By the FIU and ICAC");
		up.setA_34_desc("No. of violations for ");
		up.setA_35_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_36_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_37_desc("No. of breaches relating to legislative requirements:");
		up.setA_38_desc("CDD");
		up.setA_39_desc("PEP");
		up.setA_40_desc("Internal control rules");
		up.setA_41_desc("STRs");
		up.setA_42_desc("Other types of violations");
		up.setA_43_desc("No. of penalties imposed on");
		up.setA_44_desc("Total amount of penalties imposed on");
		up.setA_45_desc("No. of corrective or remedial actions required or taken");
		up.setA_46_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_47_desc("No. of convictions");
		up.setA_48_desc("Intelligence reports received from FIU on NBDTIs customers");
		up.setA_49_desc("No of convictions");
		up.setA_50_desc("By foreign supervisors");
		up.setA_51_desc("No. of violations for ");
		up.setA_52_desc("Not meeting enhanced CDD requirements in high risk situations");
		up.setA_53_desc("Establishing business relationships with a client without fully meeting CDD requirements");
		up.setA_54_desc("No. of breaches relating to legislative requirements:");
		up.setA_55_desc("CDD");
		up.setA_56_desc("PEP");
		up.setA_57_desc("Internal control rules");
		up.setA_58_desc("STRs");
		up.setA_59_desc("Other types of violations");
		up.setA_60_desc("No. of penalties imposed on");
		up.setA_61_desc("Total amount of penalties imposed on");
		up.setA_62_desc("No. of corrective or remedial actions required or taken");
		up.setA_63_desc("No. of court proceedings / prosecutions initiated against");
		up.setA_64_desc("No. of convictions");
		up.setA_65_desc("Intelligence reports received from FIU on NBDTIs customers");
		
		up.setEntity_flag("Y");
		up.setModify_flag("N");
		up.setDel_flag("N");

		session.saveOrUpdate(up);

		msg = "Verified Successfully";

		return msg;
	}

}
