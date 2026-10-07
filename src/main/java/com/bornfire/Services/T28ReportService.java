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

import com.bornfire.entity.t24.T24Detail;
import com.bornfire.entity.t28.T28Report;
import com.bornfire.entity.t28.T28ReportRep;

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
public class T28ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T28ReportService.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	@Autowired
	T28ReportRep t28ReportRep;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	Environment env;

	// summary starts
	public ModelAndView getT28View(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Object> T28rep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery("select * from T28_AML_CFT_INF ORDER BY LENGTH(srl_client),srl_client ,srl_no");

		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {

			String srl_no = (String) a[0];
			String inf_reqd = (String) a[1];
			String cur_qtr = (String) a[2];
			String remarks_cur = (String) a[3];
			String pre_qtr = (String) a[4];
			String remarks_pre = (String) a[5];
			Character entity_flg = (Character) a[6];
			Character del_flg = (Character) a[7];
			Character modify_flg = (Character) a[8];
			String entry_user = (String) a[9];
			String modify_user = (String) a[10];
			String verify_user = (String) a[11];
			Date entry_time = (Date) a[12];
			Date modify_time = (Date) a[13];
			Date verify_time = (Date) a[14];
			Date report_date = (Date) a[15];
			Date rep_period_from = (Date) a[16];
			Date rep_period_to = (Date) a[17];
			String srl_client = (String) a[18];

			T28Report T28Report = new T28Report(srl_no, inf_reqd, cur_qtr, remarks_cur, pre_qtr, remarks_pre,
					entity_flg, del_flg, modify_flg, entry_user, modify_user, verify_user, entry_time, modify_time,
					verify_time, report_date, rep_period_from, rep_period_to, srl_client);
			T28rep.add(T28Report);
		}
		;

		List<Object> pagedlist;

		if (T28rep.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T28rep.size());
			pagedlist = T28rep.subList(startItem, toIndex);
		}
		logger.info("Converting to Page");
		Page<Object> T28currentrepPage = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				T28rep.size());

		mv.setViewName("ReportT28");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T28rep);
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
		Date dT28;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT28 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs
					.createQuery("select count(*) from T28Report a where a.rep_period_from=?1 and  a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT28).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T28Report a").getSingleResult();
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
	public ModelAndView getT28Rep(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T28rep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery("select * from T28_AML_CFT_INF ORDER BY LENGTH(srl_client),srl_client");

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			String srl_no = (String) a[0];
			String inf_reqd = (String) a[1];
			String cur_qtr = (String) a[2];
			String remarks_cur = (String) a[3];
			String pre_qtr = (String) a[4];
			String remarks_pre = (String) a[5];
			Character entity_flg = (Character) a[6];
			Character del_flg = (Character) a[7];
			Character modify_flg = (Character) a[8];
			String entry_user = (String) a[9];
			String modify_user = (String) a[10];
			String verify_user = (String) a[11];
			Date entry_time = (Date) a[12];
			Date modify_time = (Date) a[13];
			Date verify_time = (Date) a[14];
			Date report_date = (Date) a[15];
			Date rep_period_from = (Date) a[16];
			Date rep_period_to = (Date) a[17];
			String srl_client = (String) a[18];

			T28Report T28Report = new T28Report(srl_no, inf_reqd, cur_qtr, remarks_cur, pre_qtr, remarks_pre,
					entity_flg, del_flg, modify_flg, entry_user, modify_user, verify_user, entry_time, modify_time,
					verify_time, report_date, rep_period_from, rep_period_to, srl_client);

			T28rep.add(T28Report);
		}

		mv.setViewName("ReportT28");
		mv.addObject("reportsummary", T28rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		return mv;

	}

	public ModelAndView getT28Dtl(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T24Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T28_AML_CFT_DET_INF a where report_date = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T28_AML_CFT_DET_INF a where report_date = ?1");
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

		mv.setViewName("ReportT24:: reportcontent");
		mv.addObject("reportdetails", T24Dt1Page);

		mv.addObject("singledetail", new T24Detail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + "31-12_2019";

		zipFileName = fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T28/T28.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T28/T28.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T28/T28.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T28/T28.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				//map.put("REPORT_DATE", todate);

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path += fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {

					System.out.println("EXCEl");
					fileName = fileName + ".xlsx";
					path += fileName;
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

	public String editT28(List<T28Report> t28report,HttpServletRequest req) {
		// TODO Auto-generated method stub
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		String userid = (String) req.getSession().getAttribute("USERID");

		for (int i = 0; i < t28report.size(); i++) {
			/*
			 * System.out.println(t28report.getSrl_no().split(",")[i]+"/"+t28report.
			 * getCur_qtr().split(",")[i]+"/"+t28report.getRemarks_cur().split(",")[i]);
			 */

			Optional<T28Report> t28report1 = t28ReportRep.findById(t28report.get(i).getSrl_no());

			if (t28report1.isPresent()) {
				T28Report t28report2 = t28report1.get();
				
				// t28report1.setInf_reqd(t28report.getInf_reqd().split(",")[i]);
				t28report2.setCur_qtr(t28report.get(i).getCur_qtr());
				t28report2.setRemarks_cur(t28report.get(i).getRemarks_cur());
				t28report2.setEntity_flg('N');
				t28report2.setModify_user(userid);
				t28report2.setModify_flg('Y');
				t28report2.setDel_flg('N');

				
				t28ReportRep.save(t28report2);
			}

		}

		msg = "Record Edited Successfully ";

		return msg;
	}

	public String verifyT28(List<T28Report> t28report,HttpServletRequest req) {
		// TODO Auto-generated method stub
		String msg = "";
		
		Session hs = sessionFactory.getCurrentSession();
		String userid = (String) req.getSession().getAttribute("USERID");
		for (int i = 0; i < t28report.size(); i++) {
			/*
			 * System.out.println(t28report.getSrl_no().split(",")[i]+"/"+t28report.
			 * getCur_qtr().split(",")[i]+"/"+t28report.getRemarks_cur().split(",")[i]);
			 */
			
			Optional<T28Report> t28report1 = t28ReportRep.findById(t28report.get(i).getSrl_no());
			 
			if (t28report1.isPresent()) {
				T28Report t28report2 = t28report1.get();
				
				t28report2.setVerify_user(userid);
				//System.out.println("T28"+t28report2.getVerify_user());
				if(t28report2.getModify_user().equals(t28report2.getVerify_user())) {
					  msg="Same User Cannot Verify !"; }else {
				// t28report1.setInf_reqd(t28report.getInf_reqd().split(",")[i]);
				t28report2.setCur_qtr(t28report.get(i).getCur_qtr());
				t28report2.setRemarks_cur(t28report.get(i).getRemarks_cur());
				t28report2.setEntity_flg('Y');
				t28report2.setModify_flg('N');
				t28report2.setDel_flg('N');
				
				t28ReportRep.save(t28report2);
					  }
				  }
		}
		
		msg = "Record Verify Successfully ";

		return msg;
	}

	public T28Report getSrlNo(String id) {

		T28Report up = t28ReportRep.findByIdcustom(id);

		return up;

	}

}