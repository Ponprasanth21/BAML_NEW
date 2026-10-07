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

import com.bornfire.entity.TRAN_MASTER_DETAIL_RBS;
import com.bornfire.entity.t1.T1CurProdDetail;
import com.bornfire.entity.t1.T1CurProdServices;
import com.bornfire.entity.t1.T1CurProdServicesRepo;
import com.bornfire.entity.t1.T1MasterProdDetail;

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
public class T1CurrentReportService {

	private static final Logger logger = LoggerFactory.getLogger(T1CurrentReportService.class);

	@Autowired
	T1CurProdServicesRepo t1CurProdServiceRepo;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	T1CurProdServicesRepo t1CurProdServicesRepo;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	Environment env;

	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt9;
		logger.info("Report precheck : " + reportId);

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dt9 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);
			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery("select count(*) from T1CurProdServices a where a.report_date=?1")
					.setParameter(1, dt9).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T1CurProdServices a").getSingleResult();
				if (modcnt > 0) {
					msg = "success";
				}
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

	public ModelAndView getT1View(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<T1CurProdServices> T1rep = new ArrayList<T1CurProdServices>();
		// Query<Object[]> qr;

		List<T1MasterProdDetail> T1Master = new ArrayList<T1MasterProdDetail>();

		logger.info("Inside archive");

		try {
			Date d1 = df.parse(todate);
			T1rep = t1CurProdServiceRepo.getT1CurProdServices(d1);

			T1Master = hs.createQuery("from T1MasterProdDetail a where a.report_date = ?1 ", T1MasterProdDetail.class)
					.setParameter(1, df.parse(todate)).getResultList();

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// T1rep = t1CurProdServiceRepo.getT1CurProdServices(d1);

		mv.setViewName("ReportT1");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T1rep);
		mv.addObject("reportmaster", T1Master);
		mv.addObject("displaymode", "summary");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + mv.getViewName());

		return mv;

	}

	public ModelAndView getT1currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable, String filter) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T1Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			if (!filter.equals("null")) {
				qr = hs.createNativeQuery(
						"select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T1_REPORT =?2");
				qr.setParameter(2, filter);
			} else {
				qr = hs.createNativeQuery("select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1");
			}
		} else {
			qr = hs.createNativeQuery("select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1");
		}
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<T1MasterProdDetail> T1Master = new ArrayList<T1MasterProdDetail>();

		try {
			T1Master = hs.createQuery("from T1MasterProdDetail a where a.report_date = ?1 ", T1MasterProdDetail.class)
					.setParameter(1, df.parse(todate)).getResultList();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Getting Report Detail for : " + reportId + "," + fromdate + "," + todate + "," + currency);
		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {

			String cust_id = (String) a[0];
			String cust_name = (String) a[1];
			String cust_type = (String) a[2];
			String cust_rating = (String) a[3];
			String acct_no = (String) a[4];
			String acct_name = (String) a[5];
			String tran_type = (String) a[6];
			String tran_sub_type = (String) a[7];
			Date tran_date = (Date) a[8];
			String tran_id = (String) a[9];
			BigDecimal part_tran_id = (BigDecimal) a[10];
			String part_tran_type = (String) a[11];
			String tran_crncy = (String) a[12];
			BigDecimal tran_amt = (BigDecimal) a[13];
			BigDecimal tran_amt_orgin = (BigDecimal) a[14];
			String tran_category = (String) a[15];
			Character qtr_flg = (Character) a[16];
			Character entity_flg = (Character) a[17];
			Character del_flg = (Character) a[18];
			Character modify_flg = (Character) a[19];
			Date entry_date = (Date) a[20];
			Date modify_date = (Date) a[21];
			Date verify_date = (Date) a[22];
			String entry_user = (String) a[23];
			String modify_user = (String) a[24];
			String verify_user = (String) a[25];
			String report_code = (String) a[26];
			String report_name = (String) a[27];
			Date report_date = (Date) a[28];
			Character arch_flg = (Character) a[29];
			String cell_mapping = (String) a[30];
			String process_owner = (String) a[31];
			String bank_id = (String) a[32];
			Date cust_rating_date = (Date) a[33];
			String tran_particulars = (String) a[34];
			String tran_channel = (String) a[35];
			String cntry_res = (String) a[36];
			String cnty_incorp = (String) a[37];
			String cntry_oper = (String) a[38];
			String aml_code_1 = (String) a[39];
			String aml_code_2 = (String) a[40];
			String aml_code_3 = (String) a[41];
			String aml_code_4 = (String) a[42];
			String aml_code_5 = (String) a[43];
			String aml_code_6 = (String) a[44];
			String aml_code_7 = (String) a[45];
			String aml_code_8 = (String) a[46];
			String aml_code_9 = (String) a[47];
			String aml_code_10 = (String) a[48];
			String t1_report = (String) a[49];
			String t2_report = (String) a[50];
			String t3_report = (String) a[51];
			String t4_report = (String) a[52];
			String t5_report = (String) a[53];
			String t6_report = (String) a[54];
			String t7_report = (String) a[55];
			String t8_report = (String) a[56];
			String t9_report = (String) a[57];
			String t10_report = (String) a[58];
			String t11_report = (String) a[59];
			String t12_report = (String) a[60];
			String t13_report = (String) a[61];
			String t14_report = (String) a[62];
			String t15_report = (String) a[63];
			String t16_report = (String) a[64];
			String t17_report = (String) a[65];
			String t18_report = (String) a[66];
			String t19_report = (String) a[67];
			String t20_report = (String) a[68];
			String t21_report = (String) a[69];
			String t22_report = (String) a[70];
			String t23_report = (String) a[71];
			String t24_report = (String) a[72];
			String t25_report = (String) a[73];
			String t26_report = (String) a[74];
			String t27_report = (String) a[75];
			String t28_report = (String) a[76];
			String t29_report = (String) a[77];
			BigDecimal srl_num = (BigDecimal) a[78];


			TRAN_MASTER_DETAIL_RBS py = new TRAN_MASTER_DETAIL_RBS(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name,
					tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt,tran_amt_orgin,
					tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date,
					entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping,
					process_owner, bank_id, cust_rating_date, tran_particulars, tran_channel, cntry_res, cnty_incorp,
					cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7,
					aml_code_8, aml_code_9, aml_code_10, t1_report, t2_report, t3_report,t4_report,t5_report,t6_report,t7_report,t8_report,t9_report,t10_report,
					 t11_report, t12_report, t13_report,t14_report,t15_report,t16_report,t17_report,t18_report,t19_report,t20_report, 
					 t21_report, t22_report, t23_report,t24_report,t25_report,t26_report,t27_report,t28_report,t29_report,srl_num);

			T1Dt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T1Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T1Dt1.size());
			pagedlist = T1Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T1Dt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize), T1Dt1.size());

		mv.setViewName("ReportT1 :: reportcontent");
		// mv.setViewName("ReportT1");
		mv.addObject("reportdetails", T1Dt1Page);
		mv.addObject("reportmaster", T1Master);
		mv.addObject("singledetail", new TRAN_MASTER_DETAIL_RBS());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public ModelAndView getT1Input(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) throws ParseException {
		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<T1MasterProdDetail> T1rep = new ArrayList<T1MasterProdDetail>();
		Query<Object[]> qr;
		logger.info("Inside input");

		Long dtlcnt = (Long) hs.createQuery("select count(*) from T1MasterProdDetail a where a.report_date=?1")
				.setParameter(1, df.parse(todate)).getSingleResult();

		if (dtlcnt > 0) {

			T1rep = hs.createQuery("from T1MasterProdDetail a where a.report_date = ?1 ", T1MasterProdDetail.class)
					.setParameter(1, df.parse(todate)).getResultList();

			mv.addObject("reportsummary", T1rep.get(0));
			logger.info("DFdx");

		} else {

			// mv.addObject("reportsummary", new T1MasterProdDetail(todate));
		}

		/*
		 * catch (ParseException e) {
		 * 
		 * e.printStackTrace(); }
		 */

		mv.setViewName("ReportT1 :: reportcontent");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T1rep);
		mv.addObject("displaymode", "input");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + mv.getViewName());

		return mv;
	}

	public ModelAndView getT1InputEdit(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {
		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<T1MasterProdDetail> T1rep = new ArrayList<T1MasterProdDetail>();
		Query<Object[]> qr;
		logger.info("Inside input edit");

		/* try { */

		Long dtlcnt = (Long) hs.createQuery("select count(*) from T1MasterProdDetail a where a.report_date=?1")
				.setParameter(1, todate).getSingleResult();

		if (dtlcnt > 0) {

			T1rep = hs.createQuery("from T1MasterProdDetail a where a.report_date = ?1 ", T1MasterProdDetail.class)
					.setParameter(1, todate).getResultList();

			mv.addObject("reportsummary", T1rep.get(0));
			logger.info("DFdx");

		} else {

			// mv.addObject("reportsummary", new T1MasterProdDetail(todate));
		}
		/*
		 * } catch (ParseException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		mv.setViewName("ReportT1 :: reportcontent");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T1rep);
		mv.addObject("displaymode", "edit");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + mv.getViewName());

		return mv;
	}

	public String saveReportT1(String reportId, String fromdate, String todate, String currency,
			T1MasterProdDetail inputform, String userid) {

		String msg = "";

		Session hs = sessionFactory.getCurrentSession();

		try {
			T1MasterProdDetail up = inputform;

			/* form.setDel_flg('A'); */
			up.setReport_date(df.parse(todate));
			// mod.setEntry_user(userid);
			up.setModify_user(userid);
			hs.saveOrUpdate(up);
			msg = "Report Input Saved Successfully";

		} catch (Exception e) {
			e.printStackTrace();
			msg = "Error Occured. Please contact Administrator";

		}

		return msg;
	}

	public ModelAndView getT1CurrentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<T1CurProdServices> T1rep = new ArrayList<T1CurProdServices>();
		// Query<Object[]> qr;

		logger.info("Inside archive");

		try {
			Date d1 = df.parse(todate);
			T1rep = t1CurProdServiceRepo.getT1CurProdServices(d1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<T1MasterProdDetail> T1Master = new ArrayList<T1MasterProdDetail>();

		try {
			T1Master = hs.createQuery("from T1MasterProdDetail a where a.report_date = ?1 ", T1MasterProdDetail.class)
					.setParameter(1, df.parse(todate)).getResultList();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mv.setViewName("ReportT1");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T1rep);
		mv.addObject("reportmaster", T1Master);
		mv.addObject("displaymode", "summary");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + mv.getViewName());

		return mv;

	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

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
			fileName = "t" + reportId + "_" + strDate1;
			System.out.println(fileName + "hi manoj");

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {

						jasperFile = this.getClass()
								.getResourceAsStream("/static/jasper/Details/NEW_AML_DETAILS/T1Detail.jasper");
					} else {
						jasperFile = this.getClass()
								.getResourceAsStream("/static/jasper/AmlJasper/T1Curr/T1Curr.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass()
								.getResourceAsStream("/static/jasper/AmlJasper/T1Curr/T1Curr.jasper");
					} else {
						jasperFile = this.getClass()
								.getResourceAsStream("/static/jasper/AmlJasper/T1Curr/T1Curr.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				try {
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
					Date ConDate = dateFormat1.parse(todate);
					SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
					String strDate1 = formatter1.format(ConDate);

					String today = dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
					map.put("REPORT_DATE", strDate1);
				} catch (ParseException e1) {

					logger.info(e1.getMessage());
					e1.printStackTrace();
				}

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path += fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {

					System.out.println("EXCEEEEEll");
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

	public String editT1(T1MasterProdDetail t1Report) {
		// TODO Auto-generated method stub
		String msg = "";
		Session session = sessionFactory.getCurrentSession();
		T1MasterProdDetail up = t1Report;
		up.setD1a_dep_tkg_services("Deposit-taking services");
		up.setD2a_lend_fund_based("Lending - Fund Based");
		up.setD3a_fb_finance_lease("Finance lease");
		up.setD4a_fb_oper_lease("Operating lease");
		up.setD5a_fb_loans("Loans");
		up.setD6a_fb_factoring("Factoring");
		up.setD7a_fb_others("Others");
		up.setD8a_lending_non_fund_based("Lending -  Non-Fund Based");
		up.setD9a_credit_cards("Credit card");
		up.setD10a_merchang_pos("Merchant point of sales");
		up.setD11a_other_prod_services("Other products and services, please specify");
		up.setD12a_retirement_saving_schemes("Retirement Savings Scheme (RSS)");
		up.setReport_code("T1");

		session.saveOrUpdate(up);
		msg = "Record Edited Successfully";
		T1CurProdServices T1rep = t1CurProdServiceRepo.getT1CurProdServicesmast(up.getReport_date());
		T1rep.setD1a_dep_tkg_services(up.getD1a_dep_tkg_services());
		T1rep.setD2a_lend_fund_based(up.getD2a_lend_fund_based());
		T1rep.setD3a_fb_finance_lease(up.getD3a_fb_finance_lease());
		T1rep.setD4a_fb_oper_lease(up.getD4a_fb_oper_lease());
		T1rep.setD5a_fb_loans(up.getD5a_fb_loans());
		T1rep.setD6a_fb_factoring(up.getD6a_fb_factoring());
		T1rep.setD7a_fb_others(up.getD7a_fb_others());
		T1rep.setD8a_lending_non_fund_based(up.getD8a_lending_non_fund_based());
		T1rep.setD9a_credit_cards(up.getD9a_credit_cards());
		T1rep.setD10a_merchang_pos(up.getD10a_merchang_pos());
		T1rep.setD11a_other_prod_services(up.getD11a_other_prod_services());
		T1rep.setD12a_retirement_saving_schemes(up.getD12a_retirement_saving_schemes());
		T1rep.setD1b_dep_tkg_services(up.getD1b_dep_tkg_services());
		T1rep.setD2b_lend_fund_based(up.getD2b_lend_fund_based());
		T1rep.setD3b_fb_finance_lease(up.getD3b_fb_finance_lease());
		T1rep.setD4b_fb_oper_lease(up.getD4b_fb_oper_lease());
		T1rep.setD5b_fb_loans(up.getD5b_fb_loans());
		T1rep.setD6b_fb_factoring(up.getD6b_fb_factoring());
		T1rep.setD7b_fb_others(up.getD7b_fb_others());
		T1rep.setD8b_lending_non_fund_based(up.getD8b_lending_non_fund_based());
		T1rep.setD9b_credit_cards(up.getD9b_credit_cards());
		T1rep.setD10b_merchang_pos(up.getD10b_merchang_pos());
		T1rep.setD11b_other_prod_services(up.getD11b_other_prod_services());
		T1rep.setD12b_retirement_saving_schemes(up.getD12b_retirement_saving_schemes());
		T1rep.setC1c_dep_tkg_services_nbtdi(up.getC1c_dep_tkg_services_nbtdi());
		T1rep.setC2c_lend_fund_based_nbtdi(up.getC2c_lend_fund_based_nbtdi());
		T1rep.setC3c_fb_finance_lease_nbtdi(up.getC3c_fb_finance_lease_nbtdi());
		T1rep.setC4c_fb_oper_lease_nbtdi(up.getC4c_fb_oper_lease_nbtdi());
		T1rep.setC5c_fb_loans_nbtdi(up.getC5c_fb_loans_nbtdi());
		T1rep.setC6c_fb_factoring_nbtdi(up.getC6c_fb_factoring_nbtdi());
		T1rep.setC7c_fb_others_nbtdi(up.getC7c_fb_others_nbtdi());
		T1rep.setC8c_lending_non_fund_based_nbtdi(up.getC8c_lending_non_fund_based_nbtdi());
		T1rep.setC9c_credit_cards_nbtdi(up.getC9c_credit_cards_nbtdi());
		T1rep.setC10c_merchang_pos_nbtdi(up.getC10c_merchang_pos_nbtdi());
		T1rep.setC11c_other_prod_services_nbtdi(up.getC11c_other_prod_services_nbtdi());
		T1rep.setC12c_retirement_saving_schemes_nbtdi(up.getC12c_retirement_saving_schemes_nbtdi());
		T1rep.setC1d_dep_tkg_services_offer_ftf(up.getC1d_dep_tkg_services_offer_ftf());
		T1rep.setC2d_lend_fund_based_offer_ftf(up.getC2d_lend_fund_based_offer_ftf());
		T1rep.setC3d_fb_finance_lease_offer_ftf(up.getC3d_fb_finance_lease_offer_ftf());
		T1rep.setC4d_fb_oper_lease_offer_ftf(up.getC4d_fb_oper_lease_offer_ftf());
		T1rep.setC5d_fb_loans_offer_ftf(up.getC5d_fb_loans_offer_ftf());
		T1rep.setC6d_fb_factoring_offer_ftf(up.getC6d_fb_factoring_offer_ftf());
		T1rep.setC7d_fb_others_offer_ftf(up.getC7d_fb_others_offer_ftf());
		T1rep.setC8d_lending_non_fund_based_offer_ftf(up.getC8d_lending_non_fund_based_offer_ftf());
		T1rep.setC9d_credit_cards_offer_ftf(up.getC9d_credit_cards_offer_ftf());
		T1rep.setC10d_merchang_pos_offer_ftf(up.getC10d_merchang_pos_offer_ftf());
		T1rep.setC11d_other_prod_services_offer_ftf(up.getC11d_other_prod_services_offer_ftf());
		T1rep.setC12d_retirement_saving_schemes_offer_ftf(up.getC12d_retirement_saving_schemes_offer_ftf());
		T1rep.setC1e_dep_tkg_services_offer_nftf(up.getC1e_dep_tkg_services_offer_nftf());
		T1rep.setC2e_lend_fund_based_offer_nftf(up.getC2e_lend_fund_based_offer_nftf());
		T1rep.setC3e_fb_finance_lease_offer_nftf(up.getC3e_fb_finance_lease_offer_nftf());
		T1rep.setC4e_fb_oper_lease_offer_nftf(up.getC4e_fb_oper_lease_offer_nftf());
		T1rep.setC5e_fb_loans_offer_nftf(up.getC5e_fb_loans_offer_nftf());
		T1rep.setC6e_fb_factoring_offer_nftf(up.getC6e_fb_factoring_offer_nftf());
		T1rep.setC7e_fb_others_offer_nftf(up.getC7e_fb_others_offer_nftf());
		T1rep.setC8e_lending_non_fund_based_offer_nftf(up.getC8e_lending_non_fund_based_offer_nftf());
		T1rep.setC9e_credit_cards_offer_nftf(up.getC9e_credit_cards_offer_nftf());
		T1rep.setC10e_merchang_pos_offer_nftf(up.getC10e_merchang_pos_offer_nftf());
		T1rep.setC11e_other_prod_services_offer_nftf(up.getC11e_other_prod_services_offer_nftf());
		T1rep.setC12e_retirement_saving_schemes_offer_nftf(up.getC12e_retirement_saving_schemes_offer_nftf());
		T1rep.setC1f_dep_tkg_services_offer_tp(up.getC1f_dep_tkg_services_offer_tp());
		T1rep.setC2f_lend_fund_based_offer_tp(up.getC2f_lend_fund_based_offer_tp());
		T1rep.setC3f_fb_finance_lease_offer_tp(up.getC3f_fb_finance_lease_offer_tp());
		T1rep.setC4f_fb_oper_lease_offer_tp(up.getC4f_fb_oper_lease_offer_tp());
		T1rep.setC5f_fb_loans_offer_tp(up.getC5f_fb_loans_offer_tp());
		T1rep.setC6f_fb_factoring_offer_tp(up.getC6f_fb_factoring_offer_tp());
		T1rep.setC7f_fb_others_offer_tp(up.getC7f_fb_others_offer_tp());
		T1rep.setC8f_lending_non_fund_based_offer_tp(up.getC8f_lending_non_fund_based_offer_tp());
		T1rep.setC9f_credit_cards_offer_tp(up.getC9f_credit_cards_offer_tp());
		T1rep.setC10f_merchang_pos_offer_tp(up.getC10f_merchang_pos_offer_tp());
		T1rep.setC11f_other_prod_services_offer_tp(up.getC11f_other_prod_services_offer_tp());
		T1rep.setC12f_retirement_saving_schemes_offer_tp(up.getC12f_retirement_saving_schemes_offer_tp());
		session.saveOrUpdate(T1rep);

		/*
		 * StoredProcedureQuery query2 =
		 * session.createStoredProcedureQuery("T1_CUR_PROD_SERVICES_SP")
		 * .registerStoredProcedureParameter("REPORT_DATE", String.class,
		 * ParameterMode.IN); query2.setParameter("REPORT_DATE", up.getReport_date());
		 * query2.execute();
		 */

		return msg;
	}

	public Page<T1CurProdDetail> parameterlistwithdecode(String rpt_date, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T1CurProdDetail> t9Dt1 = new ArrayList<T1CurProdDetail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T1_CUR_PROD_SERVICES_DET_TABLE where report_date=?1");
		qr.setParameter(1, rpt_date);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String cust_name = (String) a[1];
				String cust_type = (String) a[2];
				String cust_rating = (String) a[3];
				String acct_no = (String) a[4];
				String acct_name = (String) a[5];
				String tran_type = (String) a[6];
				String tran_sub_type = (String) a[7];
				Date tran_date = (Date) a[8];
				String tran_id = (String) a[9];
				BigDecimal part_tran_id = (BigDecimal) a[10];
				Character part_tran_type = (Character) a[11];
				String tran_crncy = (String) a[12];
				BigDecimal tran_amt = (BigDecimal) a[13];
				String tran_category = (String) a[14];
				Character qtr_flg = (Character) a[15];
				Character entity_flg = (Character) a[16];
				Character del_flg = (Character) a[17];
				Character modify_flg = (Character) a[18];
				Date entry_date = (Date) a[19];
				Date modify_date = (Date) a[20];
				Date verify_date = (Date) a[21];
				String entry_user = (String) a[22];
				String modify_user = (String) a[23];
				String verify_user = (String) a[24];
				String report_code = (String) a[25];
				String report_name = (String) a[26];
				Date report_date = (Date) a[27];
				Character arch_flg = (Character) a[28];
				String cell_mapping = (String) a[29];
				String process_owner = (String) a[30];
				String bank_id = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_particular = (String) a[33];
				String tran_channel = (String) a[34];
				String cntry_res = (String) a[35];
				String cnty_incorp = (String) a[36];
				String cntry_oper = (String) a[37];
				String aml_code_1 = (String) a[38];
				String aml_code_2 = (String) a[39];
				String aml_code_3 = (String) a[40];
				String aml_code_4 = (String) a[41];
				String aml_code_5 = (String) a[42];
				String aml_code_6 = (String) a[43];
				String aml_code_7 = (String) a[44];
				String aml_code_8 = (String) a[45];
				String aml_code_9 = (String) a[46];
				String aml_code_10 = (String) a[47];
				Date relationship_date = (Date) a[48];
				String mis_face_to_face = (String) a[49];
				String mis_non_face_to_face = (String) a[50];
				String mis_internal_rating_grade = (String) a[51];
				String mis_internal_rating_scale = (String) a[52];

				T1CurProdDetail py = new T1CurProdDetail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name,
						tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy,
						tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date,
						verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date,
						arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particular, tran_channel,
						cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5,
						aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date,
						mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T1CurProdDetail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T1CurProdDetail> t9Dt1Page = new PageImpl<T1CurProdDetail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}

	public Page<T1CurProdDetail> searchT1Both(String rpt_date, String tran_date1, String P_O, String tran_id1,BigDecimal part_tran_id1, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T1CurProdDetail> t9Dt1 = new ArrayList<T1CurProdDetail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T1_CUR_PROD_SERVICES_DET_TABLE where report_date=?1 and tran_date=?2 and process_owner=?3 and trim(tran_id)=?4 and trim(part_tran_id)=?5");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		qr.setParameter(3, P_O);
		qr.setParameter(4, tran_id1);
		qr.setParameter(5, part_tran_id1);
		

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String cust_name = (String) a[1];
				String cust_type = (String) a[2];
				String cust_rating = (String) a[3];
				String acct_no = (String) a[4];
				String acct_name = (String) a[5];
				String tran_type = (String) a[6];
				String tran_sub_type = (String) a[7];
				Date tran_date = (Date) a[8];
				String tran_id = (String) a[9];
				BigDecimal part_tran_id = (BigDecimal) a[10];
				Character part_tran_type = (Character) a[11];
				String tran_crncy = (String) a[12];
				BigDecimal tran_amt = (BigDecimal) a[13];
				String tran_category = (String) a[14];
				Character qtr_flg = (Character) a[15];
				Character entity_flg = (Character) a[16];
				Character del_flg = (Character) a[17];
				Character modify_flg = (Character) a[18];
				Date entry_date = (Date) a[19];
				Date modify_date = (Date) a[20];
				Date verify_date = (Date) a[21];
				String entry_user = (String) a[22];
				String modify_user = (String) a[23];
				String verify_user = (String) a[24];
				String report_code = (String) a[25];
				String report_name = (String) a[26];
				Date report_date = (Date) a[27];
				Character arch_flg = (Character) a[28];
				String cell_mapping = (String) a[29];
				String process_owner = (String) a[30];
				String bank_id = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_particular = (String) a[33];
				String tran_channel = (String) a[34];
				String cntry_res = (String) a[35];
				String cnty_incorp = (String) a[36];
				String cntry_oper = (String) a[37];
				String aml_code_1 = (String) a[38];
				String aml_code_2 = (String) a[39];
				String aml_code_3 = (String) a[40];
				String aml_code_4 = (String) a[41];
				String aml_code_5 = (String) a[42];
				String aml_code_6 = (String) a[43];
				String aml_code_7 = (String) a[44];
				String aml_code_8 = (String) a[45];
				String aml_code_9 = (String) a[46];
				String aml_code_10 = (String) a[47];
				Date relationship_date = (Date) a[48];
				String mis_face_to_face = (String) a[49];
				String mis_non_face_to_face = (String) a[50];
				String mis_internal_rating_grade = (String) a[51];
				String mis_internal_rating_scale = (String) a[52];

				T1CurProdDetail py = new T1CurProdDetail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name,
						tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy,
						tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date,
						verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date,
						arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particular, tran_channel,
						cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5,
						aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date,
						mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T1CurProdDetail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T1CurProdDetail> t9Dt1Page = new PageImpl<T1CurProdDetail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}

	public Page<T1CurProdDetail> searchT1PO(String rpt_date, String P_O, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T1CurProdDetail> t9Dt1 = new ArrayList<T1CurProdDetail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T1_CUR_PROD_SERVICES_DET_TABLE where report_date=?1 and process_owner=?2");
		qr.setParameter(1, rpt_date);

		qr.setParameter(2, P_O);

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String cust_name = (String) a[1];
				String cust_type = (String) a[2];
				String cust_rating = (String) a[3];
				String acct_no = (String) a[4];
				String acct_name = (String) a[5];
				String tran_type = (String) a[6];
				String tran_sub_type = (String) a[7];
				Date tran_date = (Date) a[8];
				String tran_id = (String) a[9];
				BigDecimal part_tran_id = (BigDecimal) a[10];
				Character part_tran_type = (Character) a[11];
				String tran_crncy = (String) a[12];
				BigDecimal tran_amt = (BigDecimal) a[13];
				String tran_category = (String) a[14];
				Character qtr_flg = (Character) a[15];
				Character entity_flg = (Character) a[16];
				Character del_flg = (Character) a[17];
				Character modify_flg = (Character) a[18];
				Date entry_date = (Date) a[19];
				Date modify_date = (Date) a[20];
				Date verify_date = (Date) a[21];
				String entry_user = (String) a[22];
				String modify_user = (String) a[23];
				String verify_user = (String) a[24];
				String report_code = (String) a[25];
				String report_name = (String) a[26];
				Date report_date = (Date) a[27];
				Character arch_flg = (Character) a[28];
				String cell_mapping = (String) a[29];
				String process_owner = (String) a[30];
				String bank_id = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_particular = (String) a[33];
				String tran_channel = (String) a[34];
				String cntry_res = (String) a[35];
				String cnty_incorp = (String) a[36];
				String cntry_oper = (String) a[37];
				String aml_code_1 = (String) a[38];
				String aml_code_2 = (String) a[39];
				String aml_code_3 = (String) a[40];
				String aml_code_4 = (String) a[41];
				String aml_code_5 = (String) a[42];
				String aml_code_6 = (String) a[43];
				String aml_code_7 = (String) a[44];
				String aml_code_8 = (String) a[45];
				String aml_code_9 = (String) a[46];
				String aml_code_10 = (String) a[47];
				Date relationship_date = (Date) a[48];
				String mis_face_to_face = (String) a[49];
				String mis_non_face_to_face = (String) a[50];
				String mis_internal_rating_grade = (String) a[51];
				String mis_internal_rating_scale = (String) a[52];

				T1CurProdDetail py = new T1CurProdDetail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name,
						tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy,
						tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date,
						verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date,
						arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particular, tran_channel,
						cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5,
						aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date,
						mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T1CurProdDetail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T1CurProdDetail> t9Dt1Page = new PageImpl<T1CurProdDetail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	public Page<T1CurProdDetail> searchT1Date(String rpt_date, String tran_date1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T1CurProdDetail> t9Dt1 = new ArrayList<T1CurProdDetail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T1_CUR_PROD_SERVICES_DET_TABLE where report_date=?1 and tran_date=?2 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String cust_name = (String) a[1];
				String cust_type = (String) a[2];
				String cust_rating = (String) a[3];
				String acct_no = (String) a[4];
				String acct_name = (String) a[5];
				String tran_type = (String) a[6];
				String tran_sub_type = (String) a[7];
				Date tran_date = (Date) a[8];
				String tran_id = (String) a[9];
				BigDecimal part_tran_id = (BigDecimal) a[10];
				Character part_tran_type = (Character) a[11];
				String tran_crncy = (String) a[12];
				BigDecimal tran_amt = (BigDecimal) a[13];
				String tran_category = (String) a[14];
				Character qtr_flg = (Character) a[15];
				Character entity_flg = (Character) a[16];
				Character del_flg = (Character) a[17];
				Character modify_flg = (Character) a[18];
				Date entry_date = (Date) a[19];
				Date modify_date = (Date) a[20];
				Date verify_date = (Date) a[21];
				String entry_user = (String) a[22];
				String modify_user = (String) a[23];
				String verify_user = (String) a[24];
				String report_code = (String) a[25];
				String report_name = (String) a[26];
				Date report_date = (Date) a[27];
				Character arch_flg = (Character) a[28];
				String cell_mapping = (String) a[29];
				String process_owner = (String) a[30];
				String bank_id = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_particular = (String) a[33];
				String tran_channel = (String) a[34];
				String cntry_res = (String) a[35];
				String cnty_incorp = (String) a[36];
				String cntry_oper = (String) a[37];
				String aml_code_1 = (String) a[38];
				String aml_code_2 = (String) a[39];
				String aml_code_3 = (String) a[40];
				String aml_code_4 = (String) a[41];
				String aml_code_5 = (String) a[42];
				String aml_code_6 = (String) a[43];
				String aml_code_7 = (String) a[44];
				String aml_code_8 = (String) a[45];
				String aml_code_9 = (String) a[46];
				String aml_code_10 = (String) a[47];
				Date relationship_date = (Date) a[48];
				String mis_face_to_face = (String) a[49];
				String mis_non_face_to_face = (String) a[50];
				String mis_internal_rating_grade = (String) a[51];
				String mis_internal_rating_scale = (String) a[52];

				T1CurProdDetail py = new T1CurProdDetail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name,
						tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy,
						tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date,
						verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date,
						arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particular, tran_channel,
						cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5,
						aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date,
						mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T1CurProdDetail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T1CurProdDetail> t9Dt1Page = new PageImpl<T1CurProdDetail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	public Page<T1CurProdDetail> searchT1SingleTran(String rpt_date, String tran_date1,String tran_id1, BigDecimal part_tran_id1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T1CurProdDetail> t9Dt1 = new ArrayList<T1CurProdDetail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T1_CUR_PROD_SERVICES_DET_TABLE where report_date=?1 and tran_date=?2 and trim(tran_id)=?3 and trim(part_tran_id)=?4 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		qr.setParameter(3, tran_id1);
		qr.setParameter(4, part_tran_id1);
		

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String cust_name = (String) a[1];
				String cust_type = (String) a[2];
				String cust_rating = (String) a[3];
				String acct_no = (String) a[4];
				String acct_name = (String) a[5];
				String tran_type = (String) a[6];
				String tran_sub_type = (String) a[7];
				Date tran_date = (Date) a[8];
				String tran_id = (String) a[9];
				BigDecimal part_tran_id = (BigDecimal) a[10];
				Character part_tran_type = (Character) a[11];
				String tran_crncy = (String) a[12];
				BigDecimal tran_amt = (BigDecimal) a[13];
				String tran_category = (String) a[14];
				Character qtr_flg = (Character) a[15];
				Character entity_flg = (Character) a[16];
				Character del_flg = (Character) a[17];
				Character modify_flg = (Character) a[18];
				Date entry_date = (Date) a[19];
				Date modify_date = (Date) a[20];
				Date verify_date = (Date) a[21];
				String entry_user = (String) a[22];
				String modify_user = (String) a[23];
				String verify_user = (String) a[24];
				String report_code = (String) a[25];
				String report_name = (String) a[26];
				Date report_date = (Date) a[27];
				Character arch_flg = (Character) a[28];
				String cell_mapping = (String) a[29];
				String process_owner = (String) a[30];
				String bank_id = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_particular = (String) a[33];
				String tran_channel = (String) a[34];
				String cntry_res = (String) a[35];
				String cnty_incorp = (String) a[36];
				String cntry_oper = (String) a[37];
				String aml_code_1 = (String) a[38];
				String aml_code_2 = (String) a[39];
				String aml_code_3 = (String) a[40];
				String aml_code_4 = (String) a[41];
				String aml_code_5 = (String) a[42];
				String aml_code_6 = (String) a[43];
				String aml_code_7 = (String) a[44];
				String aml_code_8 = (String) a[45];
				String aml_code_9 = (String) a[46];
				String aml_code_10 = (String) a[47];
				Date relationship_date = (Date) a[48];
				String mis_face_to_face = (String) a[49];
				String mis_non_face_to_face = (String) a[50];
				String mis_internal_rating_grade = (String) a[51];
				String mis_internal_rating_scale = (String) a[52];

				T1CurProdDetail py = new T1CurProdDetail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name,
						tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy,
						tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date,
						verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date,
						arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particular, tran_channel,
						cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5,
						aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date,
						mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T1CurProdDetail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T1CurProdDetail> t9Dt1Page = new PageImpl<T1CurProdDetail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	public File getFileTest(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {
		Session hs = sessionFactory.getCurrentSession();
		String path = this.env.getProperty("output.exportpath1");
		File outputFile;
		logger.info("Getting Output file :" + reportId);
		outputFile = new File(path);
		return outputFile;

	}

}
