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

import com.bornfire.entity.t24.T24Detail;
import com.bornfire.entity.t24.T24Report;
import com.bornfire.entity.t24.T24ReportRep;

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
public class T24ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T24ReportService.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	T24ReportRep t24ReportRep;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	Environment env;

	// summary starts
	public ModelAndView getT24View(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Object> T24rep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T24_INT_ADT_AML_CFT_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2  ");
	try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {
			String d1a_int_adt_ho = (String) a[0];
			String d2a_int_adt_bo = (String) a[1];
			String d3a_noa_under_int_adt = (String) a[2];
			String d4a_det_of_areas = (String) a[3];
			String d5a_note = (String) a[4];
			
			BigDecimal c1d_int_adt_ho_num = (BigDecimal) a[5];
			BigDecimal c2d_int_adt_bo_num = (BigDecimal) a[6];
			BigDecimal c3d_numa_unper_int_adt_num = (BigDecimal) a[7];
			BigDecimal c4d_det_of_areas_num = (BigDecimal) a[8];
			BigDecimal c5d_note_num = (BigDecimal) a[9];
			BigDecimal c1e_int_adt_ho_num_grade = (BigDecimal) a[10];
			BigDecimal c2e_int_adt_bo_num_grade = (BigDecimal) a[11];
			BigDecimal c3e_num_gradea_unper_int_adt_num_grade = (BigDecimal) a[12];
			BigDecimal c4e_det_of_areas_num_grade = (BigDecimal) a[13];
			BigDecimal c5e_note_num_grade = (BigDecimal) a[14];
			String report_code = (String) a[15];
			String report_name = (String) a[16];
			Date report_date = (Date) a[17];
			Date report_due_date = (Date) a[18];
			Date rep_submit_date = (Date) a[19];
			Date rep_period_from = (Date) a[20];
			Date rep_period_to = (Date) a[21];
			String rep_freq = (String) a[22];
			String nil_report_flg = (String) a[23];
			String arch_flg = (String) a[24];
			String entity_flg = (String) a[25];
			String modify_flg = (String) a[26];
			String del_flg = (String) a[27];
			String entry_user = (String) a[28];
			String modify_user = (String) a[29];
			String verify_user = (String) a[30];
			Date entry_time = (Date) a[31];
			Date modify_time = (Date) a[32];
			Date verify_time = (Date) a[33];

			T24Report T24Report = new T24Report(d1a_int_adt_ho, d2a_int_adt_bo, d3a_noa_under_int_adt, d4a_det_of_areas,
					d5a_note, 
					c1d_int_adt_ho_num, c2d_int_adt_bo_num, c3d_numa_unper_int_adt_num, c4d_det_of_areas_num,
					c5d_note_num, c1e_int_adt_ho_num_grade, c2e_int_adt_bo_num_grade,
					c3e_num_gradea_unper_int_adt_num_grade, c4e_det_of_areas_num_grade, c5e_note_num_grade, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg, entity_flg, modify_flg, del_flg, entry_user, modify_user,
					verify_user, entry_time, modify_time, verify_time);
			T24rep.add(T24Report);
		}
		;

		List<Object> pagedlist;

		if (T24rep.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T24rep.size());
			pagedlist = T24rep.subList(startItem, toIndex);
		}
		logger.info("Converting to Page");
		Page<Object> T24currentrepPage = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				T24rep.size());

		mv.setViewName("ReportT24");
		//mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T24rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		mv.addObject("displaymode", "summary");

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
					.createQuery("select count(*) from T24Report a where a.rep_period_from=?1 and  a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT24).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T24Report a").getSingleResult();
				if (modcnt > 0) {
					msg = "success";

					/*
					 * msg = "Records Pending for Verification For the Report";
					 */ } else {
					msg = "success";
				}
			} else {
				msg = "Data Not available for the Report. Please Contact Administrator";

				// msg = "success";

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

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T24rep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T24_INT_ADT_AML_CFT_TABLE where report_date = ?1 ");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			String d1a_int_adt_ho = (String) a[0];
			String d2a_int_adt_bo = (String) a[1];
			String d3a_noa_under_int_adt = (String) a[2];
			String d4a_det_of_areas = (String) a[3];
			String d5a_note = (String) a[4];
			
			BigDecimal c1d_int_adt_ho_num = (BigDecimal) a[5];
			BigDecimal c2d_int_adt_bo_num = (BigDecimal) a[6];
			BigDecimal c3d_numa_unper_int_adt_num = (BigDecimal) a[7];
			BigDecimal c4d_det_of_areas_num = (BigDecimal) a[8];
			BigDecimal c5d_note_num = (BigDecimal) a[9];
			BigDecimal c1e_int_adt_ho_num_grade = (BigDecimal) a[10];
			BigDecimal c2e_int_adt_bo_num_grade = (BigDecimal) a[11];
			BigDecimal c3e_num_gradea_unper_int_adt_num_grade = (BigDecimal) a[12];
			BigDecimal c4e_det_of_areas_num_grade = (BigDecimal) a[13];
			BigDecimal c5e_note_num_grade = (BigDecimal) a[14];
			String report_code = (String) a[15];
			String report_name = (String) a[16];
			Date report_date = (Date) a[17];
			Date report_due_date = (Date) a[18];
			Date rep_submit_date = (Date) a[19];
			Date rep_period_from = (Date) a[20];
			Date rep_period_to = (Date) a[21];
			String rep_freq = (String) a[22];
			String nil_report_flg = (String) a[23];
			String arch_flg = (String) a[24];
			String entity_flg = (String) a[25];
			String modify_flg = (String) a[26];
			String del_flg = (String) a[27];
			String entry_user = (String) a[28];
			String modify_user = (String) a[29];
			String verify_user = (String) a[30];
			Date entry_time = (Date) a[31];
			Date modify_time = (Date) a[32];
			Date verify_time = (Date) a[33];

			T24Report T24Report = new T24Report(d1a_int_adt_ho, d2a_int_adt_bo, d3a_noa_under_int_adt, d4a_det_of_areas,
					d5a_note, 
					c1d_int_adt_ho_num, c2d_int_adt_bo_num, c3d_numa_unper_int_adt_num, c4d_det_of_areas_num,
					c5d_note_num, c1e_int_adt_ho_num_grade, c2e_int_adt_bo_num_grade,
					c3e_num_gradea_unper_int_adt_num_grade, c4e_det_of_areas_num_grade, c5e_note_num_grade, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg, entity_flg, modify_flg, del_flg, entry_user, modify_user,
					verify_user, entry_time, modify_time, verify_time);
			T24rep.add(T24Report);
		}

		mv.setViewName("ReportT24");
		mv.addObject("reportsummary", T24rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		return mv;

	}

	public ModelAndView getT24Dtl(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T24Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T24_INT_ADT_AML_CFT_DETAILS a where report_date = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T24_INT_ADT_AML_CFT_DETAILS a where report_date = ?1");
		}
		try {
			qr.setParameter(1, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info("Getting Report Detail for : " + reportId + "," + fromdate + "," + todate + "," + currency);
		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {

			BigDecimal pre_ho_num = (BigDecimal) a[0];
			BigDecimal pre_bo_num = (BigDecimal) a[1];
			BigDecimal pre_noa_int_adt_num = (BigDecimal) a[2];
			String pre_det_area_covered = (String) a[3];
			String pre_note_areas_checks = (String) a[4];
			BigDecimal pre_ho_num_grade = (BigDecimal) a[5];
			BigDecimal pre_bo_num_grade = (BigDecimal) a[6];
			BigDecimal pre_noa_int_adt_num_grade = (BigDecimal) a[7];
			String pre_det_area_covered_grade = (String) a[8];
			String pre_note_areas_checks_grade = (String) a[9];
			BigDecimal cur_ho_num = (BigDecimal) a[10];
			BigDecimal cur_bo_num = (BigDecimal) a[11];
			BigDecimal cur_noa_int_adt_num = (BigDecimal) a[12];
			String cur_det_area_covered = (String) a[13];
			String cur_note_areas_checks = (String) a[14];
			BigDecimal cur_ho_num_grade = (BigDecimal) a[15];
			BigDecimal cur_bo_num_grade = (BigDecimal) a[16];
			BigDecimal cur_noa_int_adt_num_grade = (BigDecimal) a[17];
			String cur_det_area_covered_grade = (String) a[18];
			String cur_note_areas_checks_grade = (String) a[19];
			String qtr_flg = (String) a[20];
			String entity_flg = (String) a[21];
			String del_flg = (String) a[22];
			String modify_flg = (String) a[23];
			Date entry_date = (Date) a[24];
			Date modify_date = (Date) a[25];
			Date verify_date = (Date) a[26];
			String entry_user = (String) a[27];
			String modify_user = (String) a[28];
			String verify_user = (String) a[29];
			String report_code = (String) a[30];
			String report_name = (String) a[31];
			Date report_date = (Date) a[32];
			String arch_flg = (String) a[33];

			T24Detail py = new T24Detail(pre_ho_num, pre_bo_num, pre_noa_int_adt_num, pre_det_area_covered,
					pre_note_areas_checks, pre_ho_num_grade, pre_bo_num_grade, pre_noa_int_adt_num_grade,
					pre_det_area_covered_grade, pre_note_areas_checks_grade, cur_ho_num, cur_bo_num,
					cur_noa_int_adt_num, cur_det_area_covered, cur_note_areas_checks, cur_ho_num_grade,
					cur_bo_num_grade, cur_noa_int_adt_num_grade, cur_det_area_covered_grade,
					cur_note_areas_checks_grade, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date,
					verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg);

			T24Dt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T24Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T24Dt1.size());
			pagedlist = T24Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T24Dt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize), T24Dt1.size());

		mv.setViewName("ReportT24 :: reportcontent");
		mv.addObject("reportdetails", T24Dt1Page);

		mv.addObject("singledetail", new T24Detail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T24Copy/T24.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T24Copy/T24.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T24Copy/T24.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T24Copy/T24.jasper");
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
		
	
	public String editT24(T24Report t24report) {
		// TODO Auto-generated method stub
		String msg = "";
		
		T24Report up = t24report;

		up.setReport_code("T24");
		up.setD1a_int_adt_ho("Internal audit reviews/ checks performed Of Which");
		up.setD2a_int_adt_bo("Head Office");
		up.setD3a_noa_under_int_adt("Branches");
		//up.setD4a_det_of_areas("Number of areas covered under Internal audit reviews/ checks ");
		up.setD5a_note("Details of areas covered");
		up.setModify_flg("Y");
		up.setDel_flg("N");
		up.setEntity_flg("N");
		t24ReportRep.save(up);

		msg = "Record Edited Successfully ";

		return msg;
	}

	public String verifyT24(T24Report t24report) {
		// TODO Auto-generated method stub
		String msg = "";
		T24Report up = t24report;
		
		System.out.println("SERMOD" + up.getModify_user());
		System.out.println("SERVER" + up.getVerify_user());
		 if(up.getModify_user().equals(up.getVerify_user())) {
			  msg="Same User Cannot Verify !"; }else {
		
		up.setD1a_int_adt_ho("Internal audit reviews/ checks performed Of Which");
		up.setD2a_int_adt_bo("Head Office");
		up.setD3a_noa_under_int_adt("Branches");
	//	up.setD4a_det_of_areas("Number of areas covered under Internal audit reviews/ checks ");
		up.setD5a_note("Details of areas covered");
		up.setReport_code("T24");
		up.setEntity_flg("Y");
		up.setModify_flg("N");
		up.setDel_flg("N");

		
		t24ReportRep.save(up);
		msg = "Verified Successfully";
			  }
		return msg;
	}

}