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

import com.bornfire.entity.t17.T17Report;
import com.bornfire.entity.t7.T7DetRepo;
import com.bornfire.entity.t7.T7Detail;
import com.bornfire.entity.t7.T7DetailId;
import com.bornfire.entity.t7.T7Report;

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
public class T7ReportServices {

	private static final Logger logger = LoggerFactory.getLogger(T7ReportServices.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	T7DetRepo  t7DetRepo;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	Environment env;
	
	
	//summary starts
	public ModelAndView getT7View(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<Object> t7rep = new ArrayList<Object>();
		Query<Object[]> qr;
		
		

		logger.info("Inside archive"+fromdate+todate);
		qr = hs.createNativeQuery(
				"select * from T7CUST_KYC_CDD_REVIEW_RPT_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
	try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));
			logger.info("Inside archive"+df.parse(todate)+todate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			
			String d1a_ind_low = (String) a[0];
			String d2a_ind_med = (String) a[1];
			String d3a_ind_hig = (String) a[2];
			String d4a_cor_low = (String) a[3];
			String d5a_cor_med = (String) a[4];
			String d6a_cor_hig = (String) a[5];
			String d7a_npr_org_low = (String) a[6];
			String d8a_npr_org_med = (String) a[7];
			String d9a_npr_org_hig = (String) a[8];
			String d10a_trust_low = (String) a[9];
			String d11a_trust_med = (String) a[10];
			String d12a_trust_hig = (String) a[11];
			String d13a_all_otr_low = (String) a[12];
			String d14a_all_otr_med = (String) a[13];
			String d15a_all_otr_hig = (String) a[14];
			String d16a_peps_dom_low = (String) a[15];
			String d17a_peps_dom_med = (String) a[16];
			String d18a_peps_dom_hig = (String) a[17];
			String d19a_peps_frn_low = (String) a[18];
			String d20a_peps_frn_med = (String) a[19];
			String d21a_peps_frn_hig = (String) a[20];
			String d22a_tcps_low = (String) a[21];
			String d23a_tcps_med = (String) a[22];
			String d24a_tcps_hig = (String) a[23];
			BigDecimal c1f_ind_low_b30 = (BigDecimal) a[24];
			BigDecimal c2f_ind_med_b30 = (BigDecimal) a[25];
			BigDecimal c3f_ind_hig_b30 = (BigDecimal) a[26];
			BigDecimal c4f_cor_low_b30 = (BigDecimal) a[27];
			BigDecimal c5f_cor_med_b30 = (BigDecimal) a[28];
			BigDecimal c6f_cor_hig_b30 = (BigDecimal) a[29];
			BigDecimal c7f_npr_org_low_b30 = (BigDecimal) a[30];
			BigDecimal c8f_npr_org_med_b30 = (BigDecimal) a[31];
			BigDecimal c9f_npr_org_hig_b30 = (BigDecimal) a[32];
			BigDecimal c10f_trust_low_b30 = (BigDecimal) a[33];
			BigDecimal c11f_trust_med_b30 = (BigDecimal) a[34];
			BigDecimal c12f_trust_hig_b30 = (BigDecimal) a[35];
			BigDecimal c13f_all_otr_low_b30 = (BigDecimal) a[36];
			BigDecimal c14f_all_otr_med_b30 = (BigDecimal) a[37];
			BigDecimal c15f_all_otr_hig_b30 = (BigDecimal) a[38];
			BigDecimal c16f_peps_dom_low_b30 = (BigDecimal) a[39];
			BigDecimal c17f_peps_dom_med_b30 = (BigDecimal) a[40];
			BigDecimal c18f_peps_dom_hig_b30 = (BigDecimal) a[41];
			BigDecimal c19f_peps_frn_low_b30 = (BigDecimal) a[42];
			BigDecimal c20f_peps_frn_med_b30 = (BigDecimal) a[43];
			BigDecimal c21f_peps_frn_hig_b30 = (BigDecimal) a[44];
			BigDecimal c22f_tcps_low_b30 = (BigDecimal) a[45];
			BigDecimal c23f_tcps_med_b30 = (BigDecimal) a[46];
			BigDecimal c24f_tcps_hig_b30 = (BigDecimal) a[47];
			BigDecimal c1g_ind_low_30_60 = (BigDecimal) a[48];
			BigDecimal c2g_ind_med_30_60 = (BigDecimal) a[49];
			BigDecimal c3g_ind_hig_30_60 = (BigDecimal) a[50];
			BigDecimal c4g_cor_low_30_60 = (BigDecimal) a[51];
			BigDecimal c5g_cor_med_30_60 = (BigDecimal) a[52];
			BigDecimal c6g_cor_hig_30_60 = (BigDecimal) a[53];
			BigDecimal c7g_npr_org_low_30_60 = (BigDecimal) a[54];
			BigDecimal c8g_npr_org_med_30_60 = (BigDecimal) a[55];
			BigDecimal c9g_npr_org_hig_30_60 = (BigDecimal) a[56];
			BigDecimal c10g_trust_low_30_60 = (BigDecimal) a[57];
			BigDecimal c11g_trust_med_30_60 = (BigDecimal) a[58];
			BigDecimal c12g_trust_hig_30_60 = (BigDecimal) a[59];
			BigDecimal c13g_all_otr_low_30_60 = (BigDecimal) a[60];
			BigDecimal c14g_all_otr_med_30_60 = (BigDecimal) a[61];
			BigDecimal c15g_all_otr_hig_30_60 = (BigDecimal) a[62];
			BigDecimal c16g_peps_dom_low_30_60 = (BigDecimal) a[63];
			BigDecimal c17g_peps_dom_med_30_60 = (BigDecimal) a[64];
			BigDecimal c18g_peps_dom_hig_30_60 = (BigDecimal) a[65];
			BigDecimal c19g_peps_frn_low_30_60 = (BigDecimal) a[66];
			BigDecimal c20g_peps_frn_med_30_60 = (BigDecimal) a[67];
			BigDecimal c21g_peps_frn_hig_30_60 = (BigDecimal) a[68];
			BigDecimal c22g_tcps_low_30_60 = (BigDecimal) a[69];
			BigDecimal c23g_tcps_med_30_60 = (BigDecimal) a[70];
			BigDecimal c24g_tcps_hig_30_60 = (BigDecimal) a[71];
			BigDecimal c1h_ind_low_60_90 = (BigDecimal) a[72];
			BigDecimal c2h_ind_med_60_90 = (BigDecimal) a[73];
			BigDecimal c3h_ind_hig_60_90 = (BigDecimal) a[74];
			BigDecimal c4h_cor_low_60_90 = (BigDecimal) a[75];
			BigDecimal c5h_cor_med_60_90 = (BigDecimal) a[76];
			BigDecimal c6h_cor_hig_60_90 = (BigDecimal) a[77];
			BigDecimal c7h_npr_org_low_60_90 = (BigDecimal) a[78];
			BigDecimal c8h_npr_org_med_60_90 = (BigDecimal) a[79];
			BigDecimal c9h_npr_org_hig_60_90 = (BigDecimal) a[80];
			BigDecimal c10h_trust_low_60_90 = (BigDecimal) a[81];
			BigDecimal c11h_trust_med_60_90 = (BigDecimal) a[82];
			BigDecimal c12h_trust_hig_60_90 = (BigDecimal) a[83];
			BigDecimal c13h_all_otr_low_60_90 = (BigDecimal) a[84];
			BigDecimal c14h_all_otr_med_60_90 = (BigDecimal) a[85];
			BigDecimal c15h_all_otr_hig_60_90 = (BigDecimal) a[86];
			BigDecimal c16h_peps_dom_low_60_90 = (BigDecimal) a[87];
			BigDecimal c17h_peps_dom_med_60_90 = (BigDecimal) a[88];
			BigDecimal c18h_peps_dom_hig_60_90 = (BigDecimal) a[89];
			BigDecimal c19h_peps_frn_low_60_90 = (BigDecimal) a[90];
			BigDecimal c20h_peps_frn_med_60_90 = (BigDecimal) a[91];
			BigDecimal c21h_peps_frn_hig_60_90 = (BigDecimal) a[92];
			BigDecimal c22h_tcps_low_60_90 = (BigDecimal) a[93];
			BigDecimal c23h_tcps_med_60_90 = (BigDecimal) a[94];
			BigDecimal c24h_tcps_hig_60_90 = (BigDecimal) a[95];
			BigDecimal c1i_ind_low_a90 = (BigDecimal) a[96];
			BigDecimal c2i_ind_med_a90 = (BigDecimal) a[97];
			BigDecimal c3i_ind_hig_a90 = (BigDecimal) a[98];
			BigDecimal c4i_cor_low_a90 = (BigDecimal) a[99];
			BigDecimal c5i_cor_med_a90 = (BigDecimal) a[100];
			BigDecimal c6i_cor_hig_a90 = (BigDecimal) a[101];
			BigDecimal c7i_npr_org_low_a90 = (BigDecimal) a[102];
			BigDecimal c8i_npr_org_med_a90 = (BigDecimal) a[103];
			BigDecimal c9i_npr_org_hig_a90 = (BigDecimal) a[104];
			BigDecimal c10i_trust_low_a90 = (BigDecimal) a[105];
			BigDecimal c11i_trust_med_a90 = (BigDecimal) a[106];
			BigDecimal c12i_trust_hig_a90 = (BigDecimal) a[107];
			BigDecimal c13i_all_otr_low_a90 = (BigDecimal) a[108];
			BigDecimal c14i_all_otr_med_a90 = (BigDecimal) a[109];
			BigDecimal c15i_all_otr_hig_a90 = (BigDecimal) a[110];
			BigDecimal c16i_peps_dom_low_a90 = (BigDecimal) a[111];
			BigDecimal c17i_peps_dom_med_a90 = (BigDecimal) a[112];
			BigDecimal c18i_peps_dom_hig_a90 = (BigDecimal) a[113];
			BigDecimal c19i_peps_frn_low_a90 = (BigDecimal) a[114];
			BigDecimal c20i_peps_frn_med_a90 = (BigDecimal) a[115];
			BigDecimal c21i_peps_frn_hig_a90 = (BigDecimal) a[116];
			BigDecimal c22i_tcps_low_a90 = (BigDecimal) a[117];
			BigDecimal c23i_tcps_med_a90 = (BigDecimal) a[118];
			BigDecimal c24i_tcps_hig_a90 = (BigDecimal) a[119];
			String report_code = (String) a[120];
			String report_name = (String) a[121];
			Date report_date = (Date) a[122];
			Date report_due_date = (Date) a[123];
			Date rep_submit_date = (Date) a[124];
			Date rep_period_from = (Date) a[125];
			Date rep_period_to = (Date) a[126];
			String rep_freq = (String) a[127];
			String nil_report_flg = (String) a[128];
			String arch_flg = (String) a[129];
			String entity_flg = (String) a[130];
			String modify_flg = (String) a[131];
			String verify_flg = (String) a[132];
			String entry_user = (String) a[133];
			String modify_user = (String) a[134];
			String verify_user = (String) a[135];
			Date entry_time = (Date) a[136];
			Date modify_time = (Date) a[137];
			Date verify_time = (Date) a[138];


			T7Report t7report = new T7Report(d1a_ind_low, d2a_ind_med, d3a_ind_hig, d4a_cor_low, d5a_cor_med, d6a_cor_hig, d7a_npr_org_low, d8a_npr_org_med, d9a_npr_org_hig, d10a_trust_low, d11a_trust_med, d12a_trust_hig, d13a_all_otr_low, d14a_all_otr_med, d15a_all_otr_hig, d16a_peps_dom_low, d17a_peps_dom_med, d18a_peps_dom_hig, d19a_peps_frn_low, d20a_peps_frn_med, d21a_peps_frn_hig, d22a_tcps_low, d23a_tcps_med, d24a_tcps_hig,  c1f_ind_low_b30, c2f_ind_med_b30, c3f_ind_hig_b30, c4f_cor_low_b30, c5f_cor_med_b30, c6f_cor_hig_b30, c7f_npr_org_low_b30, c8f_npr_org_med_b30, c9f_npr_org_hig_b30, c10f_trust_low_b30, c11f_trust_med_b30, c12f_trust_hig_b30, c13f_all_otr_low_b30, c14f_all_otr_med_b30, c15f_all_otr_hig_b30, c16f_peps_dom_low_b30, c17f_peps_dom_med_b30, c18f_peps_dom_hig_b30, c19f_peps_frn_low_b30, c20f_peps_frn_med_b30, c21f_peps_frn_hig_b30, c22f_tcps_low_b30, c23f_tcps_med_b30, c24f_tcps_hig_b30, c1g_ind_low_30_60, c2g_ind_med_30_60, c3g_ind_hig_30_60, c4g_cor_low_30_60, c5g_cor_med_30_60, c6g_cor_hig_30_60, c7g_npr_org_low_30_60, c8g_npr_org_med_30_60, c9g_npr_org_hig_30_60, c10g_trust_low_30_60, c11g_trust_med_30_60, c12g_trust_hig_30_60, c13g_all_otr_low_30_60, c14g_all_otr_med_30_60, c15g_all_otr_hig_30_60, c16g_peps_dom_low_30_60, c17g_peps_dom_med_30_60, c18g_peps_dom_hig_30_60, c19g_peps_frn_low_30_60, c20g_peps_frn_med_30_60, c21g_peps_frn_hig_30_60, c22g_tcps_low_30_60, c23g_tcps_med_30_60, c24g_tcps_hig_30_60, c1h_ind_low_60_90, c2h_ind_med_60_90, c3h_ind_hig_60_90, c4h_cor_low_60_90, c5h_cor_med_60_90, c6h_cor_hig_60_90, c7h_npr_org_low_60_90, c8h_npr_org_med_60_90, c9h_npr_org_hig_60_90, c10h_trust_low_60_90, c11h_trust_med_60_90, c12h_trust_hig_60_90, c13h_all_otr_low_60_90, c14h_all_otr_med_60_90, c15h_all_otr_hig_60_90, c16h_peps_dom_low_60_90, c17h_peps_dom_med_60_90, c18h_peps_dom_hig_60_90, c19h_peps_frn_low_60_90, c20h_peps_frn_med_60_90, c21h_peps_frn_hig_60_90, c22h_tcps_low_60_90, c23h_tcps_med_60_90, c24h_tcps_hig_60_90, c1i_ind_low_a90, c2i_ind_med_a90, c3i_ind_hig_a90, c4i_cor_low_a90, c5i_cor_med_a90, c6i_cor_hig_a90, c7i_npr_org_low_a90, c8i_npr_org_med_a90, c9i_npr_org_hig_a90, c10i_trust_low_a90, c11i_trust_med_a90, c12i_trust_hig_a90, c13i_all_otr_low_a90, c14i_all_otr_med_a90, c15i_all_otr_hig_a90, c16i_peps_dom_low_a90, c17i_peps_dom_med_a90, c18i_peps_dom_hig_a90, c19i_peps_frn_low_a90, c20i_peps_frn_med_a90, c21i_peps_frn_hig_a90, c22i_tcps_low_a90, c23i_tcps_med_a90, c24i_tcps_hig_a90, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg
					,  entity_flg,  modify_flg,verify_flg,  entry_user,  modify_user,  verify_user, entry_time,modify_time,verify_time);
              t7rep.add(t7report);
		}
		;

		/*
		 * List<Object> pagedlist;
		 * 
		 * if (t7rep.size() < startItem) { pagedlist = Collections.emptyList(); }
		 * else { int toIndex = Math.min(startItem + pageSize, t7rep.size());
		 * pagedlist = t7rep.subList(startItem, toIndex); }
		 * logger.info("Converting to Page"); Page<Object> t7repPage = new
		 * PageImpl<Object>(pagedlist, PageRequest.of(Page, pageSize),
		 * t7rep.size());
		 */

		mv.setViewName("ReportT7");
	//	mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary1", t7rep);
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
		Date dt7;
		logger.info("Report precheck : " + reportId);

		try {
			//dt1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromdate);
			dt7 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery(
					"select count(*) from T7Report a where a.report_date=?1 ").
					setParameter(1, dt7).getSingleResult();
			System.out.println(dtlcnt);
			if (dtlcnt > 0) {
				msg="success";
			} else {
				 msg = "This Report is not Applicable";
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public ModelAndView getT7Rep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> t7rep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T7CUST_KYC_CDD_REVIEW_RPT_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
	try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			
			String d1a_ind_low = (String) a[0];
			String d2a_ind_med = (String) a[1];
			String d3a_ind_hig = (String) a[2];
			String d4a_cor_low = (String) a[3];
			String d5a_cor_med = (String) a[4];
			String d6a_cor_hig = (String) a[5];
			String d7a_npr_org_low = (String) a[6];
			String d8a_npr_org_med = (String) a[7];
			String d9a_npr_org_hig = (String) a[8];
			String d10a_trust_low = (String) a[9];
			String d11a_trust_med = (String) a[10];
			String d12a_trust_hig = (String) a[11];
			String d13a_all_otr_low = (String) a[12];
			String d14a_all_otr_med = (String) a[13];
			String d15a_all_otr_hig = (String) a[14];
			String d16a_peps_dom_low = (String) a[15];
			String d17a_peps_dom_med = (String) a[16];
			String d18a_peps_dom_hig = (String) a[17];
			String d19a_peps_frn_low = (String) a[18];
			String d20a_peps_frn_med = (String) a[19];
			String d21a_peps_frn_hig = (String) a[20];
			String d22a_tcps_low = (String) a[21];
			String d23a_tcps_med = (String) a[22];
			String d24a_tcps_hig = (String) a[23];
			BigDecimal c1f_ind_low_b30 = (BigDecimal) a[24];
			BigDecimal c2f_ind_med_b30 = (BigDecimal) a[25];
			BigDecimal c3f_ind_hig_b30 = (BigDecimal) a[26];
			BigDecimal c4f_cor_low_b30 = (BigDecimal) a[27];
			BigDecimal c5f_cor_med_b30 = (BigDecimal) a[28];
			BigDecimal c6f_cor_hig_b30 = (BigDecimal) a[29];
			BigDecimal c7f_npr_org_low_b30 = (BigDecimal) a[30];
			BigDecimal c8f_npr_org_med_b30 = (BigDecimal) a[31];
			BigDecimal c9f_npr_org_hig_b30 = (BigDecimal) a[32];
			BigDecimal c10f_trust_low_b30 = (BigDecimal) a[33];
			BigDecimal c11f_trust_med_b30 = (BigDecimal) a[34];
			BigDecimal c12f_trust_hig_b30 = (BigDecimal) a[35];
			BigDecimal c13f_all_otr_low_b30 = (BigDecimal) a[36];
			BigDecimal c14f_all_otr_med_b30 = (BigDecimal) a[37];
			BigDecimal c15f_all_otr_hig_b30 = (BigDecimal) a[38];
			BigDecimal c16f_peps_dom_low_b30 = (BigDecimal) a[39];
			BigDecimal c17f_peps_dom_med_b30 = (BigDecimal) a[40];
			BigDecimal c18f_peps_dom_hig_b30 = (BigDecimal) a[41];
			BigDecimal c19f_peps_frn_low_b30 = (BigDecimal) a[42];
			BigDecimal c20f_peps_frn_med_b30 = (BigDecimal) a[43];
			BigDecimal c21f_peps_frn_hig_b30 = (BigDecimal) a[44];
			BigDecimal c22f_tcps_low_b30 = (BigDecimal) a[45];
			BigDecimal c23f_tcps_med_b30 = (BigDecimal) a[46];
			BigDecimal c24f_tcps_hig_b30 = (BigDecimal) a[47];
			BigDecimal c1g_ind_low_30_60 = (BigDecimal) a[48];
			BigDecimal c2g_ind_med_30_60 = (BigDecimal) a[49];
			BigDecimal c3g_ind_hig_30_60 = (BigDecimal) a[50];
			BigDecimal c4g_cor_low_30_60 = (BigDecimal) a[51];
			BigDecimal c5g_cor_med_30_60 = (BigDecimal) a[52];
			BigDecimal c6g_cor_hig_30_60 = (BigDecimal) a[53];
			BigDecimal c7g_npr_org_low_30_60 = (BigDecimal) a[54];
			BigDecimal c8g_npr_org_med_30_60 = (BigDecimal) a[55];
			BigDecimal c9g_npr_org_hig_30_60 = (BigDecimal) a[56];
			BigDecimal c10g_trust_low_30_60 = (BigDecimal) a[57];
			BigDecimal c11g_trust_med_30_60 = (BigDecimal) a[58];
			BigDecimal c12g_trust_hig_30_60 = (BigDecimal) a[59];
			BigDecimal c13g_all_otr_low_30_60 = (BigDecimal) a[60];
			BigDecimal c14g_all_otr_med_30_60 = (BigDecimal) a[61];
			BigDecimal c15g_all_otr_hig_30_60 = (BigDecimal) a[62];
			BigDecimal c16g_peps_dom_low_30_60 = (BigDecimal) a[63];
			BigDecimal c17g_peps_dom_med_30_60 = (BigDecimal) a[64];
			BigDecimal c18g_peps_dom_hig_30_60 = (BigDecimal) a[65];
			BigDecimal c19g_peps_frn_low_30_60 = (BigDecimal) a[66];
			BigDecimal c20g_peps_frn_med_30_60 = (BigDecimal) a[67];
			BigDecimal c21g_peps_frn_hig_30_60 = (BigDecimal) a[68];
			BigDecimal c22g_tcps_low_30_60 = (BigDecimal) a[69];
			BigDecimal c23g_tcps_med_30_60 = (BigDecimal) a[70];
			BigDecimal c24g_tcps_hig_30_60 = (BigDecimal) a[71];
			BigDecimal c1h_ind_low_60_90 = (BigDecimal) a[72];
			BigDecimal c2h_ind_med_60_90 = (BigDecimal) a[73];
			BigDecimal c3h_ind_hig_60_90 = (BigDecimal) a[74];
			BigDecimal c4h_cor_low_60_90 = (BigDecimal) a[75];
			BigDecimal c5h_cor_med_60_90 = (BigDecimal) a[76];
			BigDecimal c6h_cor_hig_60_90 = (BigDecimal) a[77];
			BigDecimal c7h_npr_org_low_60_90 = (BigDecimal) a[78];
			BigDecimal c8h_npr_org_med_60_90 = (BigDecimal) a[79];
			BigDecimal c9h_npr_org_hig_60_90 = (BigDecimal) a[80];
			BigDecimal c10h_trust_low_60_90 = (BigDecimal) a[81];
			BigDecimal c11h_trust_med_60_90 = (BigDecimal) a[82];
			BigDecimal c12h_trust_hig_60_90 = (BigDecimal) a[83];
			BigDecimal c13h_all_otr_low_60_90 = (BigDecimal) a[84];
			BigDecimal c14h_all_otr_med_60_90 = (BigDecimal) a[85];
			BigDecimal c15h_all_otr_hig_60_90 = (BigDecimal) a[86];
			BigDecimal c16h_peps_dom_low_60_90 = (BigDecimal) a[87];
			BigDecimal c17h_peps_dom_med_60_90 = (BigDecimal) a[88];
			BigDecimal c18h_peps_dom_hig_60_90 = (BigDecimal) a[89];
			BigDecimal c19h_peps_frn_low_60_90 = (BigDecimal) a[90];
			BigDecimal c20h_peps_frn_med_60_90 = (BigDecimal) a[91];
			BigDecimal c21h_peps_frn_hig_60_90 = (BigDecimal) a[92];
			BigDecimal c22h_tcps_low_60_90 = (BigDecimal) a[93];
			BigDecimal c23h_tcps_med_60_90 = (BigDecimal) a[94];
			BigDecimal c24h_tcps_hig_60_90 = (BigDecimal) a[95];
			BigDecimal c1i_ind_low_a90 = (BigDecimal) a[96];
			BigDecimal c2i_ind_med_a90 = (BigDecimal) a[97];
			BigDecimal c3i_ind_hig_a90 = (BigDecimal) a[98];
			BigDecimal c4i_cor_low_a90 = (BigDecimal) a[99];
			BigDecimal c5i_cor_med_a90 = (BigDecimal) a[100];
			BigDecimal c6i_cor_hig_a90 = (BigDecimal) a[101];
			BigDecimal c7i_npr_org_low_a90 = (BigDecimal) a[102];
			BigDecimal c8i_npr_org_med_a90 = (BigDecimal) a[103];
			BigDecimal c9i_npr_org_hig_a90 = (BigDecimal) a[104];
			BigDecimal c10i_trust_low_a90 = (BigDecimal) a[105];
			BigDecimal c11i_trust_med_a90 = (BigDecimal) a[106];
			BigDecimal c12i_trust_hig_a90 = (BigDecimal) a[107];
			BigDecimal c13i_all_otr_low_a90 = (BigDecimal) a[108];
			BigDecimal c14i_all_otr_med_a90 = (BigDecimal) a[109];
			BigDecimal c15i_all_otr_hig_a90 = (BigDecimal) a[110];
			BigDecimal c16i_peps_dom_low_a90 = (BigDecimal) a[111];
			BigDecimal c17i_peps_dom_med_a90 = (BigDecimal) a[112];
			BigDecimal c18i_peps_dom_hig_a90 = (BigDecimal) a[113];
			BigDecimal c19i_peps_frn_low_a90 = (BigDecimal) a[114];
			BigDecimal c20i_peps_frn_med_a90 = (BigDecimal) a[115];
			BigDecimal c21i_peps_frn_hig_a90 = (BigDecimal) a[116];
			BigDecimal c22i_tcps_low_a90 = (BigDecimal) a[117];
			BigDecimal c23i_tcps_med_a90 = (BigDecimal) a[118];
			BigDecimal c24i_tcps_hig_a90 = (BigDecimal) a[119];
			String report_code = (String) a[120];
			String report_name = (String) a[121];
			Date report_date = (Date) a[122];
			Date report_due_date = (Date) a[123];
			Date rep_submit_date = (Date) a[124];
			Date rep_period_from = (Date) a[125];
			Date rep_period_to = (Date) a[126];
			String rep_freq = (String) a[127];
			String nil_report_flg = (String) a[128];
			String arch_flg = (String) a[129];
			String entity_flg = (String) a[130];
			String modify_flg = (String) a[131];
			String verify_flg = (String) a[132];
			String entry_user = (String) a[133];
			String modify_user = (String) a[134];
			String verify_user = (String) a[135];
			Date entry_time = (Date) a[136];
			Date modify_time = (Date) a[137];
			Date verify_time = (Date) a[138];


			T7Report t7report = new T7Report(d1a_ind_low, d2a_ind_med, d3a_ind_hig, d4a_cor_low, d5a_cor_med, d6a_cor_hig, d7a_npr_org_low, d8a_npr_org_med, d9a_npr_org_hig, d10a_trust_low, d11a_trust_med, d12a_trust_hig, d13a_all_otr_low, d14a_all_otr_med, d15a_all_otr_hig, d16a_peps_dom_low, d17a_peps_dom_med, d18a_peps_dom_hig, d19a_peps_frn_low, d20a_peps_frn_med, d21a_peps_frn_hig, d22a_tcps_low, d23a_tcps_med, d24a_tcps_hig,  c1f_ind_low_b30, c2f_ind_med_b30, c3f_ind_hig_b30, c4f_cor_low_b30, c5f_cor_med_b30, c6f_cor_hig_b30, c7f_npr_org_low_b30, c8f_npr_org_med_b30, c9f_npr_org_hig_b30, c10f_trust_low_b30, c11f_trust_med_b30, c12f_trust_hig_b30, c13f_all_otr_low_b30, c14f_all_otr_med_b30, c15f_all_otr_hig_b30, c16f_peps_dom_low_b30, c17f_peps_dom_med_b30, c18f_peps_dom_hig_b30, c19f_peps_frn_low_b30, c20f_peps_frn_med_b30, c21f_peps_frn_hig_b30, c22f_tcps_low_b30, c23f_tcps_med_b30, c24f_tcps_hig_b30, c1g_ind_low_30_60, c2g_ind_med_30_60, c3g_ind_hig_30_60, c4g_cor_low_30_60, c5g_cor_med_30_60, c6g_cor_hig_30_60, c7g_npr_org_low_30_60, c8g_npr_org_med_30_60, c9g_npr_org_hig_30_60, c10g_trust_low_30_60, c11g_trust_med_30_60, c12g_trust_hig_30_60, c13g_all_otr_low_30_60, c14g_all_otr_med_30_60, c15g_all_otr_hig_30_60, c16g_peps_dom_low_30_60, c17g_peps_dom_med_30_60, c18g_peps_dom_hig_30_60, c19g_peps_frn_low_30_60, c20g_peps_frn_med_30_60, c21g_peps_frn_hig_30_60, c22g_tcps_low_30_60, c23g_tcps_med_30_60, c24g_tcps_hig_30_60, c1h_ind_low_60_90, c2h_ind_med_60_90, c3h_ind_hig_60_90, c4h_cor_low_60_90, c5h_cor_med_60_90, c6h_cor_hig_60_90, c7h_npr_org_low_60_90, c8h_npr_org_med_60_90, c9h_npr_org_hig_60_90, c10h_trust_low_60_90, c11h_trust_med_60_90, c12h_trust_hig_60_90, c13h_all_otr_low_60_90, c14h_all_otr_med_60_90, c15h_all_otr_hig_60_90, c16h_peps_dom_low_60_90, c17h_peps_dom_med_60_90, c18h_peps_dom_hig_60_90, c19h_peps_frn_low_60_90, c20h_peps_frn_med_60_90, c21h_peps_frn_hig_60_90, c22h_tcps_low_60_90, c23h_tcps_med_60_90, c24h_tcps_hig_60_90, c1i_ind_low_a90, c2i_ind_med_a90, c3i_ind_hig_a90, c4i_cor_low_a90, c5i_cor_med_a90, c6i_cor_hig_a90, c7i_npr_org_low_a90, c8i_npr_org_med_a90, c9i_npr_org_hig_a90, c10i_trust_low_a90, c11i_trust_med_a90, c12i_trust_hig_a90, c13i_all_otr_low_a90, c14i_all_otr_med_a90, c15i_all_otr_hig_a90, c16i_peps_dom_low_a90, c17i_peps_dom_med_a90, c18i_peps_dom_hig_a90, c19i_peps_frn_low_a90, c20i_peps_frn_med_a90, c21i_peps_frn_hig_a90, c22i_tcps_low_a90, c23i_tcps_med_a90, c24i_tcps_hig_a90, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg
					,  entity_flg,  modify_flg,verify_flg,  entry_user,  modify_user,  verify_user, entry_time,modify_time,verify_time);
t7rep.add(t7report);
		}
		;
		mv.setViewName("ReportT7");
		mv.addObject("reportsummary1", t7rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;

	}

	public ModelAndView getT7Dtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable,String filter) {
		logger.info("Getting Report Detail fo1r : " + reportId + "," + fromdate + "," + todate + "," + currency);

		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> t7Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			if(!filter.equals("null")) {
				logger.info("Conver");
			qr = hs.createNativeQuery("select * from T7CUST_KYC_CDD_REVIEW_MAST_TB a where REPORT_DATE = ?1 and cell_mapping =?2");
			qr.setParameter(2,filter);
			}else {
			qr = hs.createNativeQuery("select * from T7CUST_KYC_CDD_REVIEW_MAST_TB a where report_date = ?1");
		} }else {
			qr = hs.createNativeQuery("select * from T7CUST_KYC_CDD_REVIEW_MAST_TB a where report_date = ?1");
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
			String cust_name = (String) a[1];
			String cust_type = (String) a[2];
			Date cust_kyc_date = (Date) a[3];
			Date cust_kyc_review_date = (Date) a[4];
			String cust_rating = (String) a[5];
			Date cust_rating_date = (Date) a[6];
			Date cust_rating_review_date = (Date) a[7];
			String entity_flg = (String) a[8];
			String del_flg = (String) a[9];
			String modify_flg = (String) a[10];
			Date entry_date = (Date) a[11];
			Date modify_date = (Date) a[12];
			Date verify_date = (Date) a[13];
			String entry_user = (String) a[14];
			String modify_user = (String) a[15];
			String verify_user = (String) a[16];
			String report_code = (String) a[17];
			String report_name = (String) a[18];
			Date report_date = (Date) a[19];
			Character arch_flg = (Character) a[20];
			
			T7DetailId py1 = new T7DetailId(cust_id, report_date);
			T7Detail py = new T7Detail(py1, cust_name, cust_type, cust_kyc_date, 
					cust_kyc_review_date, cust_rating, cust_rating_date,
					cust_rating_review_date, entity_flg, del_flg, 
					modify_flg, entry_date, modify_date, verify_date,
					entry_user, modify_user, verify_user, report_code, report_name, arch_flg);

			t7Dt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (t7Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t7Dt1.size());
			pagedlist = t7Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> t7Dt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(Page, pageSize),
				t7Dt1.size());

		mv.setViewName("ReportT7 :: reportcontent");
		mv.addObject("reportdetails", t7Dt1Page);

		mv.addObject("singledetail", new T7Detail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
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
			fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MM-yyyy").parse(strDate1));

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName =   fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T7/T7.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T7/T7.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T7/T7.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T7/T7.jasper");
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
					fileName = fileName + ".xlsx";
					path +=   fileName;
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
	


	public String editT7(T7Report t7Report) {
		// TODO Auto-generated method stub
		String msg = "";
		Session session = sessionFactory.getCurrentSession();
		/* try { */
		T7Report up = t7Report;
		up.setReport_code("T7");
		up.setEntity_flg("N");
		up.setModify_flg("Y");


		session.saveOrUpdate(up);
		msg = "Record Edited Successfully";

		return msg;
	}

	public String verifyT7(T7Report t7Report) {

		String msg = "";

		Session session = sessionFactory.getCurrentSession();

		T7Report up = t7Report;
		 if(up.getModify_user().equals(up.getVerify_user())) {
			  msg="Same User Cannot Verify !"; }else {
		up.setReport_code("T17");
		up.setEntity_flg("Y");
	

		session.saveOrUpdate(up);
		msg = "Verified Successfully";
			  }
		return msg;
	}

}
