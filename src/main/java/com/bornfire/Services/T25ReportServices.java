package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.entity.t25.T25Mod;

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
public class T25ReportServices {

	private static final Logger logger = LoggerFactory.getLogger(T25ReportServices.class);

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
	/*
	 * public ModelAndView getT25View(String reportId, String fromdate, String
	 * todate, String currency, String dtltype, Pageable pageable) {
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * Session hs = sessionFactory.getCurrentSession();
	 * 
	 * logger.info("Getting No of records in Master table :" + reportId);
	 * 
	 * List<T25Mod> T25Rep = new ArrayList<T25Mod>();
	 * 
	 * List<T25Mod> T25mod = new ArrayList<T25Mod>();
	 * 
	 * try {
	 * 
	 * Long dtlcnt = (Long)
	 * hs.createQuery("select count(*) from T25Mod a where a.report_date=?1")
	 * .setParameter(1, df.parse(todate)).getSingleResult();
	 * 
	 * 
	 * Long modcnt = (Long)
	 * hs.createQuery("select count(*) from T25Mod a where a.report_date=?1")
	 * 
	 * 
	 * if (dtlcnt > 0) {
	 * 
	 * T25Rep = hs.createQuery("from T25Mod a where a.report_date = ?1 ",
	 * T25Mod.class) .setParameter(1, df.parse(todate)).getResultList();
	 * 
	 * mv.addObject("reportsummary", T25Rep.get(0));
	 * 
	 * if (dtltype.equals("report")) { mv.addObject("editButton", "Y"); }
	 * 
	 * } else if (modcnt > 0) {
	 * 
	 * T25mod = hs.createQuery("from T25Mod a where a.report_date = ?1 ",
	 * T25Mod.class) .setParameter(1, df.parse(reportDate)).getResultList();
	 * 
	 * mv.addObject("reportsummary", T25mod.get(0)); }
	 * 
	 * 
	 * else {
	 * 
	 * mv.addObject("reportsummary", new T25Mod(df.parse(todate)));
	 * mv.addObject("editButton", "N"); }
	 * 
	 * } catch (ParseException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * logger.info("Getting Report Summary for : " + reportId + "," + todate + "," +
	 * fromdate + "," + todate + "," + currency);
	 * 
	 * mv.setViewName("ReportT25"); // mv.addObject("currlist",
	 * refCodeConfig.currList()); mv.addObject("menu", "AMLReports");
	 * mv.addObject("reportsflag", "reportsflag");
	 * logger.info("returning model view"); return mv;
	 * 
	 * }
	 */


	/*
	 * public ModelAndView getT25View1(String reportId, String fromdate, String
	 * todate) {
	 * 
	 * logger.info("T25ReportService -> getT25View()");
	 * 
	 * // declaration of variable ModelAndView mv = new ModelAndView(); Session hs =
	 * sessionFactory.getCurrentSession(); List<Object> t25Rep = new
	 * ArrayList<Object>(); Query<Object[]> qr;
	 * 
	 * qr = hs.
	 * createNativeQuery("select * from T25_AMLCFT_APPL_MOD_TABLE where REPORT_CODE = ?1"
	 * ); try { qr.setParameter(1, df.parse(todate)); } catch (ParseException e) {
	 * e.printStackTrace(); }
	 * 
	 * List<Object[]> results = qr.getResultList();
	 * 
	 * for (Object[] a : results) {
	 * 
	 * String d1a_ho = (String) a[0]; String d2a_bo = (String) a[1]; String
	 * d3a_nbdti = (String) a[2]; String p1b_ho_fully_applied = (String) a[3];
	 * String p2b_bo_fully_applied = (String) a[4]; String p3b_nbdti_not_applicable
	 * = (String) a[5]; String c1b_ho_fully_applied = (String) a[6]; String
	 * c2b_bo_fully_applied = (String) a[7]; String c3b_nbdti_not_applicable =
	 * (String) a[8]; String report_code = (String) a[9]; String report_name =
	 * (String) a[10]; Date report_date = (Date) a[11]; Date report_due_date =
	 * (Date) a[12]; Date rep_submit_date = (Date) a[13]; Date rep_period_from =
	 * (Date) a[14]; Date rep_period_to = (Date) a[15]; String rep_freq = (String)
	 * a[16]; String nil_report_flg = (String) a[17]; String arch_flg = (String)
	 * a[18]; String entity_flg = (String) a[19]; String modify_flg = (String)
	 * a[20]; String del_flg = (String) a[21]; String entry_user = (String) a[22];
	 * String modify_user = (String) a[23]; String verify_user = (String) a[24];
	 * Date entry_time = (Date) a[25]; Date modify_time = (Date) a[26]; Date
	 * verify_time = (Date) a[27]; System.out.println("EntityFlg" + entity_flg);
	 * T25Mod t25Report = new T25Mod(d1a_ho, d2a_bo, d3a_nbdti,
	 * p1b_ho_fully_applied, p2b_bo_fully_applied, p3b_nbdti_not_applicable,
	 * c1b_ho_fully_applied, c2b_bo_fully_applied, c3b_nbdti_not_applicable,
	 * report_code, report_name, report_date, report_due_date, rep_submit_date,
	 * rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg,
	 * entity_flg, modify_flg, del_flg, entry_user, modify_user, verify_user,
	 * entry_time, modify_time, verify_time);
	 * 
	 * t25Rep.add(t25Report); }
	 * 
	 * mv.setViewName("ReportT17"); // mv.addObject("currlist",
	 * refCodeConfig.currList()); mv.addObject("reportsummary", t25Rep);
	 * mv.addObject("reportsflag", "reportsflag"); mv.addObject("menu", reportId);
	 * logger.info("returning model view"); return mv; }
	 */

	public ModelAndView getT25Rep(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		logger.info("Getting No of records in Master table :" + reportId);

		List<T25Mod> T25Rep = new ArrayList<T25Mod>();
		/*
		 * List<T25Mod> T25Mod = new ArrayList<T25Mod>();
		 */

		try {

			Long dtlcnt = (Long) hs.createQuery("select count(*) from T25Mod a where a.report_date=?1")
					.setParameter(1, df.parse(todate)).getSingleResult();

			/*
			 * Long modcnt = (Long)
			 * hs.createQuery("select count(*) from T25Mod a where a.report_date=?1")
			 * .setParameter(1, df.parse(reportDate)).getSingleResult();
			 * 
			 */if (dtlcnt > 0) {

				T25Rep = hs.createQuery("from T25Mod a where a.report_date = ?1 ", T25Mod.class)
						.setParameter(1, df.parse(todate)).getResultList();

				mv.addObject("reportsummary", T25Rep.get(0));
				mv.addObject("editButton", "Y");

			} /*
				 * else if (modcnt > 0) {
				 * 
				 * T25Mod = hs.createQuery("from T25Mod a where a.report_date = ?1 ",
				 * T25Mod.class) .setParameter(1, df.parse(reportDate)).getResultList();
				 * 
				 * mv.addObject("reportsummary", T25Mod.get(0)); mv.addObject("editButton",
				 * "Y"); }
				 */
			else {

				mv.addObject("reportsummary", new T25Mod(df.parse(todate)));
				mv.addObject("editButton", "N");
			}

		} catch (ParseException e) {

			e.printStackTrace();
		}

		logger.info("Getting Report Summary for : " + reportId + "," + todate + "," + fromdate + "," + todate + ","
				+ currency);

		/*
		 * mv.setViewName(reportId + "/" + subreportid + ":: reportcontent");
		 * mv.addObject("subreportid", subreportid); mv.addObject("secid", secid);
		 */
		return mv;

	}

	/*
	 * public ModelAndView getModData(String reportid, String reportDate, String
	 * fromdate, String todate, String currency, Pageable pageable) { ModelAndView
	 * mv = new ModelAndView();
	 * 
	 * Session hs = sessionFactory.getCurrentSession();
	 * 
	 * logger.info("Getting No of records in Master table :" + reportid);
	 * 
	 * List<T25Mod> T25Rep = new ArrayList<T25Mod>();
	 * 
	 * try {
	 * 
	 * Long dtlcnt = (Long)
	 * hs.createQuery("select count(*) from T25Mod a where a.report_date=?1")
	 * .setParameter(1, df.parse(reportDate)).getSingleResult();
	 * 
	 * if (dtlcnt > 0) {
	 * 
	 * T25Rep = hs.createQuery("from T25Mod a where a.report_date = ?1 ",
	 * T25Mod.class) .setParameter(1, df.parse(reportDate)).getResultList();
	 * 
	 * 
	 * mv.addObject("reportsummary", T25Rep.get(0));
	 * 
	 * } else {
	 * 
	 * mv.addObject("T25Mod", new T25Mod(df.parse(reportDate))); }
	 * 
	 * } catch (ParseException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * 
	 * logger.info("Getting Report Summary for : " + reportid + "," + reportDate +
	 * "," + fromdate + "," + todate + "," + currency);
	 * 
	 * mv.setViewName(reportid + "/" + reportid + "Verify");
	 * 
	 * return mv; }
	 */

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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T25Copy/T25.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T25Copy/T25.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T25Copy/T25.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T25Copy/T25.jasper");
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
		

	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dT6;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT6 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			
			
			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs
					.createQuery("select count(*) from T25Mod a where a.rep_period_from=?1 and a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT6).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T25Mod a").getSingleResult();
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

	/*
	 * public String preCheck(String reportid, String fromdate, String todate) {
	 * 
	 * String msg = ""; Session hs = sessionFactory.getCurrentSession(); Date dt;
	 * 
	 * try { dt = new SimpleDateFormat("dd-MM-yyyy").parse(todate);
	 * 
	 * logger.info("Getting No of records in Mod table :" + reportid); Long modcnt1
	 * = (Long)
	 * hs.createQuery("select count(*) from T25Detail a").getSingleResult();
	 * 
	 * if (modcnt1 > 0) { msg = "Records Pending for Verification For the Report"; }
	 * else { msg = "success"; }
	 * 
	 * } catch (Exception e) { logger.info(e.getMessage()); msg = "Error";
	 * e.printStackTrace();
	 * 
	 * } return msg;
	 * 
	 * }
	 */
	/*
	 * public String saveReportT25(String reportId, String asondate, String
	 * fromdate, String todate, String currency, T25Detail detail, String userid) {
	 * 
	 * String msg = "";
	 * 
	 * Session hs = sessionFactory.getCurrentSession();
	 * 
	 * try {
	 * 
	 * T25Mod mod = new T25Mod(detail);
	 * 
	 * mod.setDel_flg('A'); mod.setRcre_time(new Date());
	 * mod.setRcre_user_id(userid); mod.setLchg_user_id(userid);
	 * mod.setLchg_time(new Date()); mod.setReport_date(df.parse(asondate));
	 * hs.saveOrUpdate(mod); msg = "Report Saved Successfully";
	 * 
	 * } catch (Exception e) { e.printStackTrace(); msg =
	 * "Error Occured. Please contact Administrator";
	 * 
	 * }
	 * 
	 * return msg; }
	 */
	public String editT25(T25Mod t25report) {
		// TODO Auto-generated method stub
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();

		// System.out.println("Hello"+up.getEntity_flg());
		// System.out.println(t25Report);
		// System.out.println(up);

		T25Mod up = t25report;

		up.setReport_code("T25");
		up.setD1a_ho("Head Office");
		up.setD2a_bo("Reports");
		up.setD3a_nbdti("NBDTI's (outsourced) service providers");

		up.setModify_flg("Y");
		up.setDel_flg("N");
		up.setEntity_flg("N");
		hs.saveOrUpdate(up);

		msg = "Record Edited Successfully ";

		return msg;
	}

	public String verifyT25(T25Mod t25report) {
		// TODO Auto-generated method stub
		String msg = "";
		Session session = sessionFactory.getCurrentSession();

		T25Mod up = t25report;
		
		  if(up.getModify_user().equals(up.getVerify_user())) {
		  msg="Same User Cannot Verify !"; }else {
		 
	up.setReport_code("T25");
	up.setD1a_ho("Head Office");
	up.setD2a_bo("Reports");
	up.setD3a_nbdti("NBDTI's (outsourced) service providers");

	up.setEntity_flg("Y");
	up.setModify_flg("N");
	up.setDel_flg("N");

	session.saveOrUpdate(up);

	msg = "Verified Successfully";

	 } 
		
		return msg;
	}
	public ModelAndView getT25View(String reportId, String fromdate, String todate) {

		logger.info("T25ReportService -> getT25View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t25Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T25_AMLCFT_APPL_MOD_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {

			String d1a_ho = (String) a[0];
			String d2a_bo = (String) a[1];
			String d3a_nbdti = (String) a[2];
			
			String c1b_ho_fully_applied = (String) a[3];
			String c2b_bo_fully_applied = (String) a[4];
			String c3b_nbdti_not_applicable = (String) a[5];
			String report_code = (String) a[6];
			String report_name = (String) a[7];
			Date report_date = (Date) a[8];
			Date report_due_date = (Date) a[9];
			Date rep_submit_date = (Date) a[10];
			Date rep_period_from = (Date) a[11];
			Date rep_period_to = (Date) a[12];
			String rep_freq = (String) a[13];
			String nil_report_flg = (String) a[14];
			String arch_flg = (String) a[15];
			String entity_flg = (String) a[16];
			String modify_flg = (String) a[17];
			String del_flg = (String) a[18];
			String entry_user = (String) a[19];
			String modify_user = (String) a[20];
			String verify_user = (String) a[21];
			Date entry_time = (Date) a[22];
			Date modify_time = (Date) a[23];
			Date verify_time = (Date) a[24];
			System.out.println("EntityFlg" + entity_flg);
			T25Mod t25Report = new T25Mod(d1a_ho, d2a_bo, d3a_nbdti, c1b_ho_fully_applied, c2b_bo_fully_applied, c3b_nbdti_not_applicable, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg, entity_flg, modify_flg, del_flg, entry_user, modify_user, verify_user, entry_time, modify_time, verify_time);
			t25Rep.add(t25Report);
		}

		mv.setViewName("ReportT25");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t25Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}
}
