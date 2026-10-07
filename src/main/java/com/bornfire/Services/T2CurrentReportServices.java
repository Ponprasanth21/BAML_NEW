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

import com.bornfire.entity.T2CurrentId;
import com.bornfire.entity.T2CurrentMast;
import com.bornfire.entity.T2CurrentRpt;
import com.bornfire.entity.T2CurrentRptRepo;
import com.bornfire.entity.t1.T1CurProdDetail;

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
public class T2CurrentReportServices {

	private static final Logger logger = LoggerFactory.getLogger(T2CurrentReportServices.class);

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


	// summary starts
	public ModelAndView getT2currentView(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<Object> t2currentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T2_CFT_CUSTOMER_RATING_RPT_TB a where REPORT_DATE = ?1 ");
		try {
			qr.setParameter(1, df.parse(todate));
		
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

			String customer_rating_1a = (String) a[0];
			BigDecimal internal_rating_face_to_face_1b = (BigDecimal) a[1];
			BigDecimal internal_rating_non_face_to_face_1c = (BigDecimal) a[2];
			String customer_rating_1d_1 = (String) a[3];
			BigDecimal previous_internal_rating_grade_1e = (BigDecimal) a[4];
			BigDecimal previous_internal_rating_scale_legend_1f = (BigDecimal) a[5];
			String customer_rating_2a = (String) a[6];
			BigDecimal internal_rating_face_to_face_2b = (BigDecimal) a[7];
			BigDecimal internal_rating_non_face_to_face_2c = (BigDecimal) a[8];
			String customer_rating_2d_1 = (String) a[9];
			BigDecimal previous_internal_rating_grade_2e = (BigDecimal) a[10];
			BigDecimal previous_internal_rating_scale_legend_2f = (BigDecimal) a[11];
			String customer_rating_3a = (String) a[12];
			BigDecimal internal_rating_face_to_face_3b = (BigDecimal) a[13];
			BigDecimal internal_rating_non_face_to_face_3c = (BigDecimal) a[14];
			String customer_rating_3d_1 = (String) a[15];
			BigDecimal previous_internal_rating_grade_3e = (BigDecimal) a[16];
			BigDecimal previous_internal_rating_scale_legend_3f = (BigDecimal) a[17];
			Date report_submit_date = (Date) a[18];
			Date report_generate_date = (Date) a[19];
			Date report_due_date = (Date) a[20];
			Character nil_report_flg = (Character) a[21];
			Date report_from_date = (Date) a[22];
			Date report_to_date = (Date) a[23];
			String frequency = (String) a[24];
			Date report_date = (Date) a[25];

			T2CurrentId t2currentreportId = new T2CurrentId(report_from_date, report_to_date,report_date);
			T2CurrentRpt t2currentreport = new T2CurrentRpt(t2currentreportId, customer_rating_1a,
					internal_rating_face_to_face_1b, internal_rating_non_face_to_face_1c, customer_rating_1d_1,
					previous_internal_rating_grade_1e, previous_internal_rating_scale_legend_1f, customer_rating_2a,
					internal_rating_face_to_face_2b, internal_rating_non_face_to_face_2c, customer_rating_2d_1,
					previous_internal_rating_grade_2e, previous_internal_rating_scale_legend_2f, customer_rating_3a,
					internal_rating_face_to_face_3b, internal_rating_non_face_to_face_3c, customer_rating_3d_1,
					previous_internal_rating_grade_3e, previous_internal_rating_scale_legend_3f, report_submit_date,
					report_generate_date, report_due_date, nil_report_flg, frequency);

			t2currentrep.add(t2currentreport);

		}
		;

		/*
		 * List<Object> pagedlist;
		 * 
		 * if (t2currentrep.size() < startItem) { pagedlist = Collections.emptyList(); }
		 * else { int toIndex = Math.min(startItem + pageSize, t2currentrep.size());
		 * pagedlist = t2currentrep.subList(startItem, toIndex); }
		 * logger.info("Converting to Page"); Page<Object> t2currentrepPage = new
		 * PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
		 * t2currentrep.size());
		 */

		mv.setViewName("ReportT2Current");
//		mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t2currentrep);

		mv.addObject("displaymode", "summary");

		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + reportId);
		return mv;

	}

	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt2;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);
			
			System.out.println("todate->" + todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery("select count(*) from T2CurrentMast a where a.report_date=?1 ")
									.setParameter(1, df.parse(todate)).getSingleResult();
			
			System.out.println(dtlcnt);

			if (dtlcnt > 0) {
				
					msg = "success";

					msg = "success";
				
			} else {
				//msg = "Data Not available for the Report. Please Contact Administrator";

				 msg = "success";

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public ModelAndView getT2currentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> t2currentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T2_CFT_CUSTOMER_RATING_RPT_TB a where REPORT_DATE = ?1 ");
	try {
			qr.setParameter(1, df.parse(todate));
			

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

			String customer_rating_1a = (String) a[0];
			BigDecimal internal_rating_face_to_face_1b = (BigDecimal) a[1];
			BigDecimal internal_rating_non_face_to_face_1c = (BigDecimal) a[2];
			String customer_rating_1d_1 = (String) a[3];
			BigDecimal previous_internal_rating_grade_1e = (BigDecimal) a[4];
			BigDecimal previous_internal_rating_scale_legend_1f = (BigDecimal) a[5];
			String customer_rating_2a = (String) a[6];
			BigDecimal internal_rating_face_to_face_2b = (BigDecimal) a[7];
			BigDecimal internal_rating_non_face_to_face_2c = (BigDecimal) a[8];
			String customer_rating_2d_1 = (String) a[9];
			BigDecimal previous_internal_rating_grade_2e = (BigDecimal) a[10];
			BigDecimal previous_internal_rating_scale_legend_2f = (BigDecimal) a[11];
			String customer_rating_3a = (String) a[12];
			BigDecimal internal_rating_face_to_face_3b = (BigDecimal) a[13];
			BigDecimal internal_rating_non_face_to_face_3c = (BigDecimal) a[14];
			String customer_rating_3d_1 = (String) a[15];
			BigDecimal previous_internal_rating_grade_3e = (BigDecimal) a[16];
			BigDecimal previous_internal_rating_scale_legend_3f = (BigDecimal) a[17];
			Date report_submit_date = (Date) a[18];
			Date report_generate_date = (Date) a[19];
			Date report_due_date = (Date) a[20];
			Character nil_report_flg = (Character) a[21];
			Date report_from_date = (Date) a[22];
			Date report_to_date = (Date) a[23];
			String frequency = (String) a[24];
			Date report_date = (Date) a[25];

			T2CurrentId t2currentreportId = new T2CurrentId(report_from_date, report_to_date,report_date);
			T2CurrentRpt t2currentreport = new T2CurrentRpt(t2currentreportId, customer_rating_1a,
					internal_rating_face_to_face_1b, internal_rating_non_face_to_face_1c, customer_rating_1d_1,
					previous_internal_rating_grade_1e, previous_internal_rating_scale_legend_1f, customer_rating_2a,
					internal_rating_face_to_face_2b, internal_rating_non_face_to_face_2c, customer_rating_2d_1,
					previous_internal_rating_grade_2e, previous_internal_rating_scale_legend_2f, customer_rating_3a,
					internal_rating_face_to_face_3b, internal_rating_non_face_to_face_3c, customer_rating_3d_1,
					previous_internal_rating_grade_3e, previous_internal_rating_scale_legend_3f, report_submit_date,
					report_generate_date, report_due_date, nil_report_flg, frequency);

			t2currentrep.add(t2currentreport);

		}

		mv.setViewName("ReportT2Current");
		mv.addObject("reportsummary", t2currentrep);
		mv.addObject("menu", "reportId");
		mv.addObject("reportsflag", "reportsflag");

		return mv;

	}

	public ModelAndView getT2currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable, String filter) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> t2currentDt1 = new ArrayList<Object>();
		Query<Object[]> qr;
/*
		if (dtltype.equals("report")) {
			if (ratvalue == null) {
				qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where report_date = ?1");
			} else if (ratvalue.equals("high") && (tablecol.equals("ff"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_FACE_TO_FACE='HIGH' and report_date = ?1");
			} else if (ratvalue.equals("high") && (tablecol.equals("nff"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_NON_FACE_TO_FACE='HIGH' and report_date = ?1");
			} else if (ratvalue.equals("high") && (tablecol.equals("irg"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_INTERNAL_RATING_GRADE='HIGH' and report_date = ?1");
			} else if (ratvalue.equals("high") && (tablecol.equals("irs"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_INTERNAL_RATING_SCALE='HIGH' and report_date = ?1");
			} else if (ratvalue.equals("medium") && (tablecol.equals("ff"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_FACE_TO_FACE='MEDIUM' and report_date = ?1");
			} else if (ratvalue.equals("medium") && (tablecol.equals("nff"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_NON_FACE_TO_FACE='MEDIUM' and report_date = ?1");
			} else if (ratvalue.equals("medium") && (tablecol.equals("irg"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_INTERNAL_RATING_GRADE='MEDIUM' and report_date = ?1");
			} else if (ratvalue.equals("medium") && (tablecol.equals("irs"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_INTERNAL_RATING_SCALE='MEDIUM' and report_date = ?1");
			}

			else if (ratvalue.equals("low") && (tablecol.equals("ff"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_FACE_TO_FACE='LOW' and report_date = ?1");
			} else if (ratvalue.equals("low") && (tablecol.equals("nff"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_NON_FACE_TO_FACE='LOW' and report_date = ?1");
			} else if (ratvalue.equals("low") && (tablecol.equals("irg"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_INTERNAL_RATING_GRADE='LOW' and report_date = ?1");
			} else if (ratvalue.equals("low") && (tablecol.equals("irs"))) {
				qr = hs.createNativeQuery(
						"select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where MIS_INTERNAL_RATING_SCALE='LOW' and report_date = ?1");
			}

			else {
				qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where report_date = ?1");
			}
		} else {
			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where report_date = ?1");
		}
		try {
			qr.setParameter(1, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
*/
		
		if (dtltype.equals("report")) {
			if(!filter.equals("null")) {
				qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where report_date = ?1 and CUST_RATING =?2");
				qr.setParameter(2,filter);
			}else {
				qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where report_date = ?1");
			}
		} else {
			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB a where report_date = ?1");
		}

		try {
			qr.setParameter(1, df.parse(todate));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		logger.info("Getting Report Detail for : " + reportId + "," + fromdate + "," + todate + "," + currency);
		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {

			String cust_id = (String) a[0];
			String customer_name = (String) a[1];
			String branch_id = (String) a[2];
			String branch_name = (String) a[3];
			String bank_id = (String) a[4];
			String ownership_type = (String) a[5];
			Date relationship_date = (Date) a[6];
			String cust_rating = (String) a[7];
			Date customer_rating_date = (Date) a[8];
			Date customer_next_rating_date = (Date) a[9];
			String mis_face_to_face = (String) a[10];
			String mis_non_face_to_face = (String) a[11];
			String mis_internal_rating_grade = (String) a[12];
			String mis_internal_rating_scale = (String) a[13];
			String remarks = (String) a[14];
			Character del_flg = (Character) a[15];
			Character entity_cre_flg = (Character) a[16];
			Date entity_cre_date = (Date) a[17];
			Character mod_flg = (Character) a[18];
			String entry_user = (String) a[19];
			String modify_user = (String) a[20];
			String auth_user = (String) a[21];
			Date entry_time = (Date) a[22];
			Date modify_time = (Date) a[23];
			Date auth_time = (Date) a[24];
			String aml_code_1 = (String) a[25];
			String aml_code_2 = (String) a[26];
			String aml_code_3 = (String) a[27];
			String aml_code_4 = (String) a[28];
			String aml_code_5 = (String) a[29];
			String aml_code_6 = (String) a[30];
			String aml_code_7 = (String) a[31];
			String aml_code_8 = (String) a[32];
			String aml_code_9 = (String) a[33];
			String aml_code_10 = (String) a[34];
			Date report_date = (Date) a[35];
			String process_owner = (String) a[36];
			String qtr_flg = (String) a[37];
			Date verify_date = (Date) a[38];
			String verify_user = (String) a[39];
			String arch_flg = (String) a[40];
			String cell_mapping = (String) a[41];
			String tran_channel = (String) a[42];
			String acct_num = (String) a[43];
			String act_name = (String) a[44];
			String tran_type = (String) a[45];
			String tran_sub_type = (String) a[46];
			Date tran_date = (Date) a[47];
			String tran_id = (String) a[48];
			String part_tran_id = (String) a[49];
			String part_tran_type = (String) a[50];
			String tran_crncy = (String) a[51];
			BigDecimal tran_amt = (BigDecimal) a[52];
			String tran_category = (String) a[53];
			Date customer_due_rating_date = (Date) a[54];
			String report_quarter = (String) a[55];

 

			T2CurrentMast py = new T2CurrentMast(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, relationship_date, cust_rating, customer_rating_date, customer_next_rating_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, process_owner, qtr_flg, verify_date, verify_user, arch_flg, cell_mapping, tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, customer_due_rating_date, report_quarter);

			t2currentDt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (t2currentDt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t2currentDt1.size());
			pagedlist = t2currentDt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> t2currentDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				t2currentDt1.size());

		mv.setViewName("ReportT2Current :: reportcontent");
		mv.addObject("reportdetails", t2currentDt1Page);

		mv.addObject("singledetail", new T2CurrentMast());
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

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date ConDate = dateFormat1.parse(todate);
	System.out.println(ConDate);
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");  
	String strDate1 = formatter1.format(ConDate);
			fileName ="t"+reportId + "_" + strDate1;
			System.out.println(fileName +"hi manoj");

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName =  fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile=null;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						logger.info("Getting Jasper file details:" + reportId);

						jasperFile = this.getClass().getResourceAsStream("/static/jasper/Details/T2Detail/T2Detail.jasper");
					} 

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T2C/T2C.jasper");
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
					System.out.println("todya"+today);
					map.put("REPORT_DATE", strDate1);
				} catch (ParseException e1) {

					logger.info(e1.getMessage());
					e1.printStackTrace();
				}

				

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path +=   fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					fileName = fileName + ".xlsx";
					path +=    fileName;
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
	
	
	
	
	public Page<T2CurrentMast> parameterlistwithdecode(String rpt_date,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T2CurrentMast> t9Dt1 = new ArrayList<T2CurrentMast>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1");
			qr.setParameter(1,rpt_date);
			List<Object[]> result = qr.getResultList();
			
			try {
			for (Object[] a : result) {

				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				Date relationship_date = (Date) a[6];
				String cust_rating = (String) a[7];
				Date customer_rating_date = (Date) a[8];
				Date customer_next_rating_date = (Date) a[9];
				String mis_face_to_face = (String) a[10];
				String mis_non_face_to_face = (String) a[11];
				String mis_internal_rating_grade = (String) a[12];
				String mis_internal_rating_scale = (String) a[13];
				String remarks = (String) a[14];
				Character del_flg = (Character) a[15];
				Character entity_cre_flg = (Character) a[16];
				Date entity_cre_date = (Date) a[17];
				Character mod_flg = (Character) a[18];
				String entry_user = (String) a[19];
				String modify_user = (String) a[20];
				String auth_user = (String) a[21];
				Date entry_time = (Date) a[22];
				Date modify_time = (Date) a[23];
				Date auth_time = (Date) a[24];
				String aml_code_1 = (String) a[25];
				String aml_code_2 = (String) a[26];
				String aml_code_3 = (String) a[27];
				String aml_code_4 = (String) a[28];
				String aml_code_5 = (String) a[29];
				String aml_code_6 = (String) a[30];
				String aml_code_7 = (String) a[31];
				String aml_code_8 = (String) a[32];
				String aml_code_9 = (String) a[33];
				String aml_code_10 = (String) a[34];
				Date report_date = (Date) a[35];
				String process_owner = (String) a[36];
				String qtr_flg = (String) a[37];
				Date verify_date = (Date) a[38];
				String verify_user = (String) a[39];
				String arch_flg = (String) a[40];
				String cell_mapping = (String) a[41];
				String tran_channel = (String) a[42];
				String acct_num = (String) a[43];
				String act_name = (String) a[44];
				String tran_type = (String) a[45];
				String tran_sub_type = (String) a[46];
				Date tran_date = (Date) a[47];
				String tran_id = (String) a[48];
				String part_tran_id = (String) a[49];
				String part_tran_type = (String) a[50];
				String tran_crncy = (String) a[51];
				BigDecimal tran_amt = (BigDecimal) a[52];
				String tran_category = (String) a[53];
				Date customer_due_rating_date = (Date) a[54];
				String report_quarter = (String) a[55];

	 

				T2CurrentMast py = new T2CurrentMast(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, relationship_date, cust_rating, customer_rating_date, customer_next_rating_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, process_owner, qtr_flg, verify_date, verify_user, arch_flg, cell_mapping, tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, customer_due_rating_date, report_quarter);
						

						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T2CurrentMast> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T2CurrentMast> t9Dt1Page = new PageImpl<T2CurrentMast>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}

	
	
	
	
	
	
	
	
	
	
	public Page<T2CurrentMast> searchAll(String rpt_date,String Cust_ID,String P_O, String Cust_name,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T2CurrentMast> t9Dt1 = new ArrayList<T2CurrentMast>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1 and cust_id=?2 and process_owner= ?3 and UPPER(customer_name) like UPPER(?4)");
			qr.setParameter(1,rpt_date);
			qr.setParameter(2,Cust_ID);
			qr.setParameter(3,P_O);
			qr.setParameter(4,Cust_name);
			List<Object[]> result = qr.getResultList();
			
			try {
			for (Object[] a : result) {

				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				Date relationship_date = (Date) a[6];
				String cust_rating = (String) a[7];
				Date customer_rating_date = (Date) a[8];
				Date customer_next_rating_date = (Date) a[9];
				String mis_face_to_face = (String) a[10];
				String mis_non_face_to_face = (String) a[11];
				String mis_internal_rating_grade = (String) a[12];
				String mis_internal_rating_scale = (String) a[13];
				String remarks = (String) a[14];
				Character del_flg = (Character) a[15];
				Character entity_cre_flg = (Character) a[16];
				Date entity_cre_date = (Date) a[17];
				Character mod_flg = (Character) a[18];
				String entry_user = (String) a[19];
				String modify_user = (String) a[20];
				String auth_user = (String) a[21];
				Date entry_time = (Date) a[22];
				Date modify_time = (Date) a[23];
				Date auth_time = (Date) a[24];
				String aml_code_1 = (String) a[25];
				String aml_code_2 = (String) a[26];
				String aml_code_3 = (String) a[27];
				String aml_code_4 = (String) a[28];
				String aml_code_5 = (String) a[29];
				String aml_code_6 = (String) a[30];
				String aml_code_7 = (String) a[31];
				String aml_code_8 = (String) a[32];
				String aml_code_9 = (String) a[33];
				String aml_code_10 = (String) a[34];
				Date report_date = (Date) a[35];
				String process_owner = (String) a[36];
				String qtr_flg = (String) a[37];
				Date verify_date = (Date) a[38];
				String verify_user = (String) a[39];
				String arch_flg = (String) a[40];
				String cell_mapping = (String) a[41];
				String tran_channel = (String) a[42];
				String acct_num = (String) a[43];
				String act_name = (String) a[44];
				String tran_type = (String) a[45];
				String tran_sub_type = (String) a[46];
				Date tran_date = (Date) a[47];
				String tran_id = (String) a[48];
				String part_tran_id = (String) a[49];
				String part_tran_type = (String) a[50];
				String tran_crncy = (String) a[51];
				BigDecimal tran_amt = (BigDecimal) a[52];
				String tran_category = (String) a[53];
				Date customer_due_rating_date = (Date) a[54];
				String report_quarter = (String) a[55];

	 

				T2CurrentMast py = new T2CurrentMast(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, relationship_date, cust_rating, customer_rating_date, customer_next_rating_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, process_owner, qtr_flg, verify_date, verify_user, arch_flg, cell_mapping, tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, customer_due_rating_date, report_quarter);
						

						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T2CurrentMast> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T2CurrentMast> t9Dt1Page = new PageImpl<T2CurrentMast>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}
	
	
	
	
	
	
	
	
	public Page<T2CurrentMast> searchbycust(String rpt_date,String Cust_ID, String Cust_name,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T2CurrentMast> t9Dt1 = new ArrayList<T2CurrentMast>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1 and cust_id=?2 and UPPER(customer_name) like UPPER(?3)");
			qr.setParameter(1,rpt_date);
			qr.setParameter(2,Cust_ID);
			
			qr.setParameter(3,Cust_name);
			List<Object[]> result = qr.getResultList();
			
			try {
			for (Object[] a : result) {

				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				Date relationship_date = (Date) a[6];
				String cust_rating = (String) a[7];
				Date customer_rating_date = (Date) a[8];
				Date customer_next_rating_date = (Date) a[9];
				String mis_face_to_face = (String) a[10];
				String mis_non_face_to_face = (String) a[11];
				String mis_internal_rating_grade = (String) a[12];
				String mis_internal_rating_scale = (String) a[13];
				String remarks = (String) a[14];
				Character del_flg = (Character) a[15];
				Character entity_cre_flg = (Character) a[16];
				Date entity_cre_date = (Date) a[17];
				Character mod_flg = (Character) a[18];
				String entry_user = (String) a[19];
				String modify_user = (String) a[20];
				String auth_user = (String) a[21];
				Date entry_time = (Date) a[22];
				Date modify_time = (Date) a[23];
				Date auth_time = (Date) a[24];
				String aml_code_1 = (String) a[25];
				String aml_code_2 = (String) a[26];
				String aml_code_3 = (String) a[27];
				String aml_code_4 = (String) a[28];
				String aml_code_5 = (String) a[29];
				String aml_code_6 = (String) a[30];
				String aml_code_7 = (String) a[31];
				String aml_code_8 = (String) a[32];
				String aml_code_9 = (String) a[33];
				String aml_code_10 = (String) a[34];
				Date report_date = (Date) a[35];
				String process_owner = (String) a[36];
				String qtr_flg = (String) a[37];
				Date verify_date = (Date) a[38];
				String verify_user = (String) a[39];
				String arch_flg = (String) a[40];
				String cell_mapping = (String) a[41];
				String tran_channel = (String) a[42];
				String acct_num = (String) a[43];
				String act_name = (String) a[44];
				String tran_type = (String) a[45];
				String tran_sub_type = (String) a[46];
				Date tran_date = (Date) a[47];
				String tran_id = (String) a[48];
				String part_tran_id = (String) a[49];
				String part_tran_type = (String) a[50];
				String tran_crncy = (String) a[51];
				BigDecimal tran_amt = (BigDecimal) a[52];
				String tran_category = (String) a[53];
				Date customer_due_rating_date = (Date) a[54];
				String report_quarter = (String) a[55];

	 

				T2CurrentMast py = new T2CurrentMast(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, relationship_date, cust_rating, customer_rating_date, customer_next_rating_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, process_owner, qtr_flg, verify_date, verify_user, arch_flg, cell_mapping, tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, customer_due_rating_date, report_quarter);
						

						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T2CurrentMast> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T2CurrentMast> t9Dt1Page = new PageImpl<T2CurrentMast>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}
	
	
	
	
	
	
	public Page<T2CurrentMast> searchbycustID(String rpt_date,String Cust_ID,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T2CurrentMast> t9Dt1 = new ArrayList<T2CurrentMast>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1 and cust_id=?2 ");
			qr.setParameter(1,rpt_date);
			qr.setParameter(2,Cust_ID);
			List<Object[]> result = qr.getResultList();
			
			try {
			for (Object[] a : result) {

				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				Date relationship_date = (Date) a[6];
				String cust_rating = (String) a[7];
				Date customer_rating_date = (Date) a[8];
				Date customer_next_rating_date = (Date) a[9];
				String mis_face_to_face = (String) a[10];
				String mis_non_face_to_face = (String) a[11];
				String mis_internal_rating_grade = (String) a[12];
				String mis_internal_rating_scale = (String) a[13];
				String remarks = (String) a[14];
				Character del_flg = (Character) a[15];
				Character entity_cre_flg = (Character) a[16];
				Date entity_cre_date = (Date) a[17];
				Character mod_flg = (Character) a[18];
				String entry_user = (String) a[19];
				String modify_user = (String) a[20];
				String auth_user = (String) a[21];
				Date entry_time = (Date) a[22];
				Date modify_time = (Date) a[23];
				Date auth_time = (Date) a[24];
				String aml_code_1 = (String) a[25];
				String aml_code_2 = (String) a[26];
				String aml_code_3 = (String) a[27];
				String aml_code_4 = (String) a[28];
				String aml_code_5 = (String) a[29];
				String aml_code_6 = (String) a[30];
				String aml_code_7 = (String) a[31];
				String aml_code_8 = (String) a[32];
				String aml_code_9 = (String) a[33];
				String aml_code_10 = (String) a[34];
				Date report_date = (Date) a[35];
				String process_owner = (String) a[36];
				String qtr_flg = (String) a[37];
				Date verify_date = (Date) a[38];
				String verify_user = (String) a[39];
				String arch_flg = (String) a[40];
				String cell_mapping = (String) a[41];
				String tran_channel = (String) a[42];
				String acct_num = (String) a[43];
				String act_name = (String) a[44];
				String tran_type = (String) a[45];
				String tran_sub_type = (String) a[46];
				Date tran_date = (Date) a[47];
				String tran_id = (String) a[48];
				String part_tran_id = (String) a[49];
				String part_tran_type = (String) a[50];
				String tran_crncy = (String) a[51];
				BigDecimal tran_amt = (BigDecimal) a[52];
				String tran_category = (String) a[53];
				Date customer_due_rating_date = (Date) a[54];
				String report_quarter = (String) a[55];

	 

				T2CurrentMast py = new T2CurrentMast(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, relationship_date, cust_rating, customer_rating_date, customer_next_rating_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, process_owner, qtr_flg, verify_date, verify_user, arch_flg, cell_mapping, tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, customer_due_rating_date, report_quarter);
						

						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T2CurrentMast> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T2CurrentMast> t9Dt1Page = new PageImpl<T2CurrentMast>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}
	
	
	
	
	
	public Page<T2CurrentMast> searchbyPO(String rpt_date,String P_O,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T2CurrentMast> t9Dt1 = new ArrayList<T2CurrentMast>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1 and process_owner=?2 ");
			qr.setParameter(1,rpt_date);
			qr.setParameter(2,P_O);
			List<Object[]> result = qr.getResultList();
			
			try {
			for (Object[] a : result) {

				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				Date relationship_date = (Date) a[6];
				String cust_rating = (String) a[7];
				Date customer_rating_date = (Date) a[8];
				Date customer_next_rating_date = (Date) a[9];
				String mis_face_to_face = (String) a[10];
				String mis_non_face_to_face = (String) a[11];
				String mis_internal_rating_grade = (String) a[12];
				String mis_internal_rating_scale = (String) a[13];
				String remarks = (String) a[14];
				Character del_flg = (Character) a[15];
				Character entity_cre_flg = (Character) a[16];
				Date entity_cre_date = (Date) a[17];
				Character mod_flg = (Character) a[18];
				String entry_user = (String) a[19];
				String modify_user = (String) a[20];
				String auth_user = (String) a[21];
				Date entry_time = (Date) a[22];
				Date modify_time = (Date) a[23];
				Date auth_time = (Date) a[24];
				String aml_code_1 = (String) a[25];
				String aml_code_2 = (String) a[26];
				String aml_code_3 = (String) a[27];
				String aml_code_4 = (String) a[28];
				String aml_code_5 = (String) a[29];
				String aml_code_6 = (String) a[30];
				String aml_code_7 = (String) a[31];
				String aml_code_8 = (String) a[32];
				String aml_code_9 = (String) a[33];
				String aml_code_10 = (String) a[34];
				Date report_date = (Date) a[35];
				String process_owner = (String) a[36];
				String qtr_flg = (String) a[37];
				Date verify_date = (Date) a[38];
				String verify_user = (String) a[39];
				String arch_flg = (String) a[40];
				String cell_mapping = (String) a[41];
				String tran_channel = (String) a[42];
				String acct_num = (String) a[43];
				String act_name = (String) a[44];
				String tran_type = (String) a[45];
				String tran_sub_type = (String) a[46];
				Date tran_date = (Date) a[47];
				String tran_id = (String) a[48];
				String part_tran_id = (String) a[49];
				String part_tran_type = (String) a[50];
				String tran_crncy = (String) a[51];
				BigDecimal tran_amt = (BigDecimal) a[52];
				String tran_category = (String) a[53];
				Date customer_due_rating_date = (Date) a[54];
				String report_quarter = (String) a[55];

	 

				T2CurrentMast py = new T2CurrentMast(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, relationship_date, cust_rating, customer_rating_date, customer_next_rating_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, process_owner, qtr_flg, verify_date, verify_user, arch_flg, cell_mapping, tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, customer_due_rating_date, report_quarter);
						

						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T2CurrentMast> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T2CurrentMast> t9Dt1Page = new PageImpl<T2CurrentMast>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}
	
	
	
	public Page<T2CurrentMast> searchbyName(String rpt_date,String Cust_Name,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T2CurrentMast> t9Dt1 = new ArrayList<T2CurrentMast>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1 and UPPER(customer_name) like UPPER(?2)");
			qr.setParameter(1,rpt_date);
			qr.setParameter(2,Cust_Name);
			List<Object[]> result = qr.getResultList();
			
			try {
			for (Object[] a : result) {

				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				Date relationship_date = (Date) a[6];
				String cust_rating = (String) a[7];
				Date customer_rating_date = (Date) a[8];
				Date customer_next_rating_date = (Date) a[9];
				String mis_face_to_face = (String) a[10];
				String mis_non_face_to_face = (String) a[11];
				String mis_internal_rating_grade = (String) a[12];
				String mis_internal_rating_scale = (String) a[13];
				String remarks = (String) a[14];
				Character del_flg = (Character) a[15];
				Character entity_cre_flg = (Character) a[16];
				Date entity_cre_date = (Date) a[17];
				Character mod_flg = (Character) a[18];
				String entry_user = (String) a[19];
				String modify_user = (String) a[20];
				String auth_user = (String) a[21];
				Date entry_time = (Date) a[22];
				Date modify_time = (Date) a[23];
				Date auth_time = (Date) a[24];
				String aml_code_1 = (String) a[25];
				String aml_code_2 = (String) a[26];
				String aml_code_3 = (String) a[27];
				String aml_code_4 = (String) a[28];
				String aml_code_5 = (String) a[29];
				String aml_code_6 = (String) a[30];
				String aml_code_7 = (String) a[31];
				String aml_code_8 = (String) a[32];
				String aml_code_9 = (String) a[33];
				String aml_code_10 = (String) a[34];
				Date report_date = (Date) a[35];
				String process_owner = (String) a[36];
				String qtr_flg = (String) a[37];
				Date verify_date = (Date) a[38];
				String verify_user = (String) a[39];
				String arch_flg = (String) a[40];
				String cell_mapping = (String) a[41];
				String tran_channel = (String) a[42];
				String acct_num = (String) a[43];
				String act_name = (String) a[44];
				String tran_type = (String) a[45];
				String tran_sub_type = (String) a[46];
				Date tran_date = (Date) a[47];
				String tran_id = (String) a[48];
				String part_tran_id = (String) a[49];
				String part_tran_type = (String) a[50];
				String tran_crncy = (String) a[51];
				BigDecimal tran_amt = (BigDecimal) a[52];
				String tran_category = (String) a[53];
				Date customer_due_rating_date = (Date) a[54];
				String report_quarter = (String) a[55];

	 

				T2CurrentMast py = new T2CurrentMast(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, relationship_date, cust_rating, customer_rating_date, customer_next_rating_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, process_owner, qtr_flg, verify_date, verify_user, arch_flg, cell_mapping, tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, customer_due_rating_date, report_quarter);
						

						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T2CurrentMast> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T2CurrentMast> t9Dt1Page = new PageImpl<T2CurrentMast>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}
	
}
