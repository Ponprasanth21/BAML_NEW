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

import com.bornfire.entity.t12.T12Detail;
import com.bornfire.entity.t20.T20Detail;
import com.bornfire.entity.t20.T20Report;
import com.bornfire.entity.t20.T20ReportRep;
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
public class T20ReportService {
	
private static final Logger logger = LoggerFactory.getLogger(T20ReportService.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;
	@Autowired
	T20ReportRep t20ReportRep;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	Environment env;
	

	public ModelAndView getT20View(String reportId, String fromdate, String todate) {

		logger.info("T20ReportService -> getT20View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T20_STR_TABLE where REPORT_DATE = ?1");
		
	try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			
			String d1a_str_mlro = (String) a[0];
			String d2a_avg_time__raising_str = (String) a[1];
			String d3a_str_closed_mlro = (String) a[2];
			String d4a_avg_time_cls_str = (String) a[3];
			String d5a_nof_str_by_mlro = (String) a[4];
			String d6a_avg_time_filing_str = (String) a[5];
			String c1c_nof_str_filed_mlro = (String) a[6];
			String c2c_avg_time__raising_str = (String) a[7];
			String c3c_nof_str_closed_mlro = (String) a[8];
			String c4c_avg_time_closed_mlro = (String) a[9];
			String c5c_nof_str_filed_mlro = (String) a[10];
			String c6c_avg_time_filed_str = (String) a[11];
			String report_code = (String) a[12];
			String report_name = (String) a[13];
			Date report_date = (Date) a[14];
			Date report_due_date = (Date) a[15];
			Date rep_submit_date = (Date) a[16];
			Date rep_period_from = (Date) a[17];
			Date rep_period_to = (Date) a[18];
			String rep_freq = (String) a[19];
			String nil_report_flg = (String) a[20];
			String arch_flg = (String) a[21];
			String entity_flg =(String) a[22];
			String del_flg = (String) a[23];
			String modify_flg = (String) a[24];
			String entry_user = (String) a[25];
			String modify_user = (String) a[26];
			String Verify_user = (String) a[27];
			Date entry_time = (Date) a[28];
			Date modify_time = (Date) a[29];
			Date verify_time = (Date) a[30];
			String qtr_flg = (String) a[31];

					T20Report t20Report = new T20Report(d1a_str_mlro, d2a_avg_time__raising_str,d3a_str_closed_mlro, d4a_avg_time_cls_str, d5a_nof_str_by_mlro, d6a_avg_time_filing_str,c1c_nof_str_filed_mlro, c2c_avg_time__raising_str,c3c_nof_str_closed_mlro, c4c_avg_time_closed_mlro, c5c_nof_str_filed_mlro, c6c_avg_time_filed_str,report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,rep_freq, nil_report_flg, arch_flg,entity_flg,del_flg,modify_flg,entry_user,modify_user,Verify_user,entry_time,modify_time,verify_time,qtr_flg);

			t17Rep.add(t20Report);

			
		}

		mv.setViewName("ReportT20");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t17Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

	public String preCheck(String reportid, String fromdate, String todate) {
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dT19;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT19 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

		Query query = null;

		query = hs.createNativeQuery("select count(*) from T20_STR_TABLE where report_date = ?1 ");
				
		try {
			query.setParameter(1, df.parse(todate));
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
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();

		}
		return msg;

	}
	
	public ModelAndView getT20Rep(String reportId, String fromdate, String todate) {

		logger.info("T20ReportService -> getT20Rep()");
		Date dt1;
		Date dT19 = null;

		
			try {
//				dt1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromdate);
				dT19 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T20_STR_TABLE where REPORT_DATE = ?1");
		qr.setParameter(1, dT19);
		/*try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			
			String d1a_str_mlro = (String) a[0];
			String d2a_avg_time__raising_str = (String) a[1];
			String d3a_str_closed_mlro = (String) a[2];
			String d4a_avg_time_cls_str = (String) a[3];
			String d5a_nof_str_by_mlro = (String) a[4];
			String d6a_avg_time_filing_str = (String) a[5];
			
			String c1c_nof_str_filed_mlro = (String) a[6];
			String c2c_avg_time__raising_str = (String) a[7];
			String c3c_nof_str_closed_mlro = (String) a[8];
			String c4c_avg_time_closed_mlro = (String) a[9];
			String c5c_nof_str_filed_mlro = (String) a[10];
			String c6c_avg_time_filed_str = (String) a[11];
			String report_code = (String) a[12];
			String report_name = (String) a[13];
			Date report_date = (Date) a[14];
			Date report_due_date = (Date) a[15];
			Date rep_submit_date = (Date) a[16];
			Date rep_period_from = (Date) a[17];
			Date rep_period_to = (Date) a[18];
			String rep_freq = (String) a[19];
			String nil_report_flg = (String) a[20];
			String arch_flg = (String) a[21];
			String entity_flg =(String) a[22];
			String del_flg = (String) a[23];
			String modify_flg = (String) a[24];
			String entry_user = (String) a[25];
			String modify_user = (String) a[26];
			String Verify_user = (String) a[27];
			Date entry_time = (Date) a[28];
			Date modify_time = (Date) a[29];
			Date verify_time = (Date) a[30];
			String qtr_flg = (String) a[31];
			
			T20Report t20Report = new T20Report(d1a_str_mlro, d2a_avg_time__raising_str,d3a_str_closed_mlro, d4a_avg_time_cls_str, d5a_nof_str_by_mlro, d6a_avg_time_filing_str, c1c_nof_str_filed_mlro, c2c_avg_time__raising_str,c3c_nof_str_closed_mlro, c4c_avg_time_closed_mlro, c5c_nof_str_filed_mlro, c6c_avg_time_filed_str,report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,rep_freq, nil_report_flg, arch_flg,entity_flg,del_flg,modify_flg,entry_user,modify_user,Verify_user,entry_time,modify_time,verify_time,qtr_flg);

			t17Rep.add(t20Report);
		
		
		}

		mv.setViewName("ReportT20");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t17Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		logger.info("returning model view");
		return mv;
	}
	
	public ModelAndView getT20Dtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> t12Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T20_STR_DETAILS a where REPORT_DATE = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T20_STR_DETAILS a where REPORT_DATE = ?1");
		}
		qr.setParameter(1, todate);

	/*	try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {
			
			String cust_id = (String) a[0];
			String cust_name = (String) a[1];
			String acct_no = (String) a[2];
			String acct_name = (String) a[3];
			Date tran_date = (Date) a[4];
			String tran_id = (String) a[5];
			BigDecimal part_tran_id = (BigDecimal) a[6];
			String part_tran_type = (String) a[7];
			String tran_crncy = (String) a[8];
			BigDecimal tran_amt = (BigDecimal) a[9];
			String tran_particulars = (String) a[10];
			BigDecimal tran_nof_str_filed_mlro = (BigDecimal) a[11];
			BigDecimal tran_avg_time__raising_str = (BigDecimal) a[12];
			BigDecimal tran_nof_str_closed_mlro = (BigDecimal) a[13];
			BigDecimal tran_avg_time_closed_mlro = (BigDecimal) a[14];
			BigDecimal tran_nof_str_filed_mlro_alert = (BigDecimal) a[15];
			BigDecimal tran_avg_time_filed_str = (BigDecimal) a[16];
			String qtr_flg = (String) a[17];
			Date entity_flg = (Date) a[18];
			Date del_flg = (Date) a[19];
			Date modify_flg = (Date) a[20];
			Date entry_date = (Date) a[21];
			Date modify_date = (Date) a[22];
			String verify_date = (String) a[23];
			String entry_user = (String) a[24];
			String modify_user = (String) a[25];
			String verify_user = (String) a[26];
			Date report_code = (Date) a[27];
			Date report_name = (Date) a[28];
			Date report_date = (Date) a[29];
			String arch_flg = (String) a[30];
			
			T20Detail t20Detail = new T20Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_nof_str_filed_mlro, tran_avg_time__raising_str, tran_nof_str_closed_mlro, tran_avg_time_closed_mlro, tran_nof_str_filed_mlro_alert, tran_avg_time_filed_str, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg);
			
			t12Dt1.add(t20Detail);
			
			
		}
		;

		List<Object> pagedlist;

		if (t12Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t12Dt1.size());
			pagedlist = t12Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> t12Dt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize), t12Dt1.size());

		mv.setViewName("ReportT20 :: reportcontent");
		mv.addObject("reportdetails", t12Dt1Page);
		mv.addObject("singledetail", new T12Detail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}
	
	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate1 = null ;
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
	 strDate1 = formatter1.format(ConDate);
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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T20Copy/T20.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T20Copy/T20.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T20Copy/T20.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T20Copy/T20.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("REPORT_DATE", strDate1);


				

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
		
	
	public String editT20(T20Report t20Report) {
		// TODO Auto-generated method stub
		String msg = "";
		/* try { */
	//	Session session = sessionFactory.getCurrentSession();
		T20Report up = t20Report;
		up.setReport_code("T20");
		up.setEntity_flg("N");
		up.setModify_flg("Y");
		up.setDel_flg("N");
	
		t20ReportRep.save(up);
		//session.saveOrUpdate(up);
		
		msg = "Record Edited Successfully";
		
	
		return msg;
	}
	public String verifyT20(T20Report t20Report) {
		// TODO Auto-generated method stub
		String msg = "";
		/* try { */
	//	Session session = sessionFactory.getCurrentSession();
		T20Report up = t20Report;
		System.out.println("SERMOD" + up.getModify_user());
		System.out.println("SERVER" + up.getVerify_user());
		
		 if(up.getModify_user().equals(up.getVerify_user())) {
			  msg="Same User Cannot Verify !"; }else {
		up.setReport_code("T20");
		up.setEntity_flg("Y");
		up.setModify_flg("N");
		up.setDel_flg("N");
	
		t20ReportRep.save(up);

		msg = "Verified Successfully";
			  }
		return msg;
	}

}
