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

import com.bornfire.entity.T16.T16REPORT;
import com.bornfire.entity.t19.T19Detail;
import com.bornfire.entity.t19.T19Report;

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
public class T19ReportService {
	
	private static final Logger logger = LoggerFactory.getLogger(T19Report.class);

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
	
	
	//summary starts
	public ModelAndView getT19currentView(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {
       
		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T19urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;
		
		

		logger.info("Inside archive"+fromdate);
		qr = hs.createNativeQuery(
				"select * from T19_TYPES_HITS_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			
			String d1a_large_cash_trans = (String) a[0];
			String d2a_cash_in_cash_out_trans = (String) a[1];
			String d3a_linked_cash_trans = (String) a[2];
			String d4a_funds_in_funds_out_trans = (String) a[3];
			String d5a_caution_list_santion_cust = (String) a[4];
			String d6a_sanction_jurisdiction = (String) a[5];
			String d7a_trans_non_equi_jurisdiction = (String) a[6];
			String d8a_trans_peps_high_risk_accts = (String) a[7];
			String d9a_trans_new_reactivate_accts = (String) a[8];
			String d10a_trans_minor_accts = (String) a[9];
			String d11a_all_otr_type_trans = (String) a[10];
			String d12a_note = (String) a[11];
			BigDecimal c1j_large_cash_trans_nof_hits = (BigDecimal) a[12];
			BigDecimal c2j_cash_in_cash_out_trans_nof_hits = (BigDecimal) a[13];
			BigDecimal c3j_linked_cash_trans_nof_hits = (BigDecimal) a[14];
			BigDecimal c4j_funds_in_funds_out_trans_nof_hits = (BigDecimal) a[15];
			BigDecimal c5j_caution_list_santion_custs_nof_hits = (BigDecimal) a[16];
			BigDecimal c6j_sanction_jurispiction_nof_hits = (BigDecimal) a[17];
			BigDecimal c7j_trans_non_equi_jurisdiction_nof_hits = (BigDecimal) a[18];
			BigDecimal c8j_trans_peps_high_risk_accts_nof_hits = (BigDecimal) a[19];
			BigDecimal c9j_trans_new_reactivate_accts_nof_hits = (BigDecimal) a[20];
			BigDecimal c10j_trans_minor_accts_nof_hits = (BigDecimal) a[21];
			BigDecimal c11j_all_otr_type_trans_nof_hits = (BigDecimal) a[22];
			String c12j_note_nof_hits = (String) a[23];
			BigDecimal c1k_large_cash_trans_nof_phits = (BigDecimal) a[24];
			BigDecimal c2k_cash_in_cash_out_trans_nof_phits = (BigDecimal) a[25];
			BigDecimal c3k_linked_cash_trans_nof_phits = (BigDecimal) a[26];
			BigDecimal c4k_funds_in_funds_out_trans_nof_phits = (BigDecimal) a[27];
			BigDecimal c5k_caution_list_santion_custs_nof_phits = (BigDecimal) a[28];
			BigDecimal c6k_sanction_jurispiction_nof_phits =(BigDecimal)a[29];
			BigDecimal c7k_trans_non_equi_jurisdiction =(BigDecimal)a[30];
			BigDecimal c8k_trans_peps_high_risk_accts_nof_phits =(BigDecimal)a[31];
			BigDecimal c9k_trans_new_reactivate_accts_nof_phits =(BigDecimal)a[32];
			BigDecimal c10k_trans_minor_accts_nof_phits =(BigDecimal)a[33];
			BigDecimal c11k_all_otr_type_trans_nof_phits =(BigDecimal)a[34];
			String c12k_note_nof_phits =(String)a[35];
			BigDecimal c1l_large_cash_trans_nof_phitsc =(BigDecimal)a[36];
			BigDecimal c2l_cash_in_cash_out_trans_nof_phitsc =(BigDecimal)a[37];
			BigDecimal c3l_linkel_cash_trans_nof_phitsc =(BigDecimal)a[38];
			BigDecimal c4l_funds_in_funds_out_trans_nof_phitsc =(BigDecimal)a[39];
			BigDecimal c5l_caution_list_santion_custs_nof_phitsc =(BigDecimal)a[40];
			BigDecimal c6l_sanction_jurispiction_nof_phitsc =(BigDecimal)a[41];
			BigDecimal c7l_trans_non_equi_jurisdiction_nof_phitsc =(BigDecimal)a[42];
			BigDecimal c8l_trans_peps_high_risk_accts_nof_phitsc =(BigDecimal)a[43];
			BigDecimal c9l_trans_new_reactivate_accts_nof_phitsc =(BigDecimal)a[44];
			BigDecimal c10l_trans_minor_accts_nof_phitsc =(BigDecimal)a[45];
			BigDecimal c11l_all_otr_type_trans_nof_phitsc =(BigDecimal)a[46];
			String c12l_note_nof_phitsc =(String)a[47];
			BigDecimal c1m_largm_cash_trans_nof_str =(BigDecimal)a[48];
			BigDecimal c2m_cash_in_cash_out_trans_nof_str =(BigDecimal)a[49];
			BigDecimal c3m_linked_cash_trans_nof_str =(BigDecimal)a[50];
			BigDecimal c4m_funds_in_funds_out_trans_nof_str =(BigDecimal)a[51];
			BigDecimal c5m_caution_list_santion_custs_nof_str =(BigDecimal)a[52];
			BigDecimal c6m_sanction_jurispiction_nof_str =(BigDecimal)a[53];
			BigDecimal c7m_trans_non_equi_jurisdiction_nof_str =(BigDecimal)a[54];
			BigDecimal c8m_trans_peps_high_risk_accts_nof_str =(BigDecimal)a[55];
			BigDecimal c9m_trans_new_reactivate_accts_nof_str =(BigDecimal)a[56];
			BigDecimal c10m_trans_minor_accts_nof_str =(BigDecimal)a[57];
			BigDecimal c11m_all_otr_type_trans_nof_str =(BigDecimal)a[58];
			String c12m_note_nof_str =(String)a[59];
			BigDecimal c1n_large_cash_trans_nof_alerts =(BigDecimal)a[60];
			BigDecimal c2n_cash_in_cash_out_trans_nof_alerts =(BigDecimal)a[61];
			BigDecimal c3n_linked_cash_trans_nof_alerts =(BigDecimal)a[62];
			BigDecimal c4n_funds_in_funds_out_trans_nof_alerts =(BigDecimal)a[63];
			BigDecimal c5n_caution_list_santion_custs_nof_alerts =(BigDecimal)a[64];
			BigDecimal c6n_sanction_jurispiction_nof_alerts =(BigDecimal)a[65];
			BigDecimal c7n_trans_nof_equi_jurisdiction_nof_alerts =(BigDecimal)a[66];
			BigDecimal c8n_trans_peps_high_risk_accts_nof_alerts =(BigDecimal)a[67];
			BigDecimal c9n_trans_new_reactivate_accts_nof_alerts =(BigDecimal)a[68];
			BigDecimal c10n_trans_minor_accts_nof_alerts =(BigDecimal)a[69];
			BigDecimal c11n_all_otr_type_trans_nof_alerts =(BigDecimal)a[70];
			String c12n_note_nof_alerts =(String)a[71];
			BigDecimal c1o_large_cash_trans_nof_alerts_nostr =(BigDecimal)a[72];
			BigDecimal c2o_cash_in_cash_out_trans_nof_alerts_nostr =(BigDecimal)a[73];
			BigDecimal c3o_linked_cash_trans_nof_alerts_nostr =(BigDecimal)a[74];
			BigDecimal c4o_funds_in_funds_out_trans_nof_alerts_nostr =(BigDecimal)a[75];
			BigDecimal c5o_caution_list_santion_custs_nof_alerts_nostr =(BigDecimal)a[76];
			BigDecimal c6o_sanction_jurispiction_nof_alerts_nostr =(BigDecimal)a[77];
			BigDecimal c7o_trans_non_equi_jurisdiction_nof_alerts_nostr =(BigDecimal)a[78];
			BigDecimal c8o_trans_peps_high_risk_accts_nof_alerts_nostr =(BigDecimal)a[79];
			BigDecimal c9o_trans_new_reactivate_accts_nof_alerts_nostr =(BigDecimal)a[80];
			BigDecimal c10o_trans_minor_accts_nof_alerts_nostr =(BigDecimal)a[81];
			BigDecimal c11o_all_otr_type_trans_nof_alerts_nostr =(BigDecimal)a[82];
			String c12o_note_nof_alerts_nostr =(String)a[83];
			String c1p_large_cash_trans_avg_time =(String)a[84];
			String c2p_casp_in_cash_out_trans_avg_time =(String)a[85];
			String c3p_linked_cash_trans_avg_time =(String)a[86];
			String c4p_funds_in_funds_out_trans_avg_time =(String)a[87];
			String c5p_caution_list_santion_custs_avg_time =(String)a[88];
			String c6p_sanction_jurispiction_avg_time =(String)a[89];
			String c7p_trans_non_equi_jurisdiction_avg_time =(String)a[90];
			String c8p_trans_peps_higp_risk_accts_avg_time =(String)a[91];
			String c9p_trans_new_reactivate_accts_avg_time =(String)a[92];
			String c10p_trans_minor_accts_avg_time =(String)a[93];
			String c11p_all_otr_type_trans_avg_time =(String)a[94];
			String c12p_note_avg_time =(String)a[95];
			BigDecimal c1q_large_cash_trans_nof_alerts_str =(BigDecimal)a[96];
			BigDecimal c2q_cash_in_cash_out_trans_nof_alerts_str =(BigDecimal)a[97];
			BigDecimal c3q_linkeq_cash_trans_nof_alerts_str =(BigDecimal)a[98];
			BigDecimal c4q_funds_in_funds_out_trans_nof_alerts_str =(BigDecimal)a[99];
			BigDecimal c5q_caution_list_santion_custs_nof_alerts_str =(BigDecimal)a[100];
			BigDecimal c6q_sanction_jurispiction_nof_alerts_str =(BigDecimal)a[101];
			BigDecimal c7q_trans_non_equi_jurisdiction_nof_alerts_str =(BigDecimal)a[102];
			BigDecimal c8q_trans_peps_high_risk_accts_nof_alerts_str =(BigDecimal)a[103];
			BigDecimal c9q_trans_new_reactivate_accts_nof_alerts_str =(BigDecimal)a[104];
			BigDecimal c10q_trans_minor_accts_nof_alerts_str =(BigDecimal)a[105];
			BigDecimal c11q_all_otr_type_trans_nof_alerts_str =(BigDecimal)a[106];
			String c12q_note_nof_alerts_str =(String)a[107];
			String report_code =(String)a[108];
			String report_name =(String)a[109];
			Date report_date =(Date)a[110];
			Date report_due_date =(Date)a[111];
			Date rep_submit_date =(Date)a[112];
			Date rep_period_from =(Date)a[113];
			Date rep_period_to =(Date)a[114];
			String rep_freq =(String)a[115];
			String nil_report_flg =(String)a[116];
			Character arch_flg =(Character)a[117];
			String entry_user = (String) a[118];
			String modify_user = (String) a[119];
			String verify_user = (String) a[120];
			Date entry_time = (Date) a[121];
			Date modify_time = (Date) a[122];
			Date verify_time = (Date) a[123];
			Character entity_flg = (Character) a[124];
			Character modify_flg = (Character) a[125];
			Character del_flg = (Character) a[126];


	
			T19Report T19Report = new T19Report(d1a_large_cash_trans, d2a_cash_in_cash_out_trans, 
					d3a_linked_cash_trans, d4a_funds_in_funds_out_trans, d5a_caution_list_santion_cust, 
					d6a_sanction_jurisdiction, d7a_trans_non_equi_jurisdiction, d8a_trans_peps_high_risk_accts, 
					d9a_trans_new_reactivate_accts, d10a_trans_minor_accts, d11a_all_otr_type_trans, d12a_note, 
					 c1j_large_cash_trans_nof_hits, c2j_cash_in_cash_out_trans_nof_hits, c3j_linked_cash_trans_nof_hits, c4j_funds_in_funds_out_trans_nof_hits, c5j_caution_list_santion_custs_nof_hits, c6j_sanction_jurispiction_nof_hits, c7j_trans_non_equi_jurisdiction_nof_hits, c8j_trans_peps_high_risk_accts_nof_hits, c9j_trans_new_reactivate_accts_nof_hits, c10j_trans_minor_accts_nof_hits, c11j_all_otr_type_trans_nof_hits, c12j_note_nof_hits, c1k_large_cash_trans_nof_phits, c2k_cash_in_cash_out_trans_nof_phits, c3k_linked_cash_trans_nof_phits, c4k_funds_in_funds_out_trans_nof_phits, c5k_caution_list_santion_custs_nof_phits, c6k_sanction_jurispiction_nof_phits, c7k_trans_non_equi_jurisdiction, c8k_trans_peps_high_risk_accts_nof_phits, c9k_trans_new_reactivate_accts_nof_phits, c10k_trans_minor_accts_nof_phits, c11k_all_otr_type_trans_nof_phits, c12k_note_nof_phits, c1l_large_cash_trans_nof_phitsc, c2l_cash_in_cash_out_trans_nof_phitsc, c3l_linkel_cash_trans_nof_phitsc, c4l_funds_in_funds_out_trans_nof_phitsc, c5l_caution_list_santion_custs_nof_phitsc, c6l_sanction_jurispiction_nof_phitsc, c7l_trans_non_equi_jurisdiction_nof_phitsc, c8l_trans_peps_high_risk_accts_nof_phitsc, c9l_trans_new_reactivate_accts_nof_phitsc, c10l_trans_minor_accts_nof_phitsc, c11l_all_otr_type_trans_nof_phitsc, c12l_note_nof_phitsc, c1m_largm_cash_trans_nof_str, c2m_cash_in_cash_out_trans_nof_str, c3m_linked_cash_trans_nof_str, c4m_funds_in_funds_out_trans_nof_str, c5m_caution_list_santion_custs_nof_str, c6m_sanction_jurispiction_nof_str, c7m_trans_non_equi_jurisdiction_nof_str, c8m_trans_peps_high_risk_accts_nof_str, c9m_trans_new_reactivate_accts_nof_str, c10m_trans_minor_accts_nof_str, c11m_all_otr_type_trans_nof_str, c12m_note_nof_str, c1n_large_cash_trans_nof_alerts, c2n_cash_in_cash_out_trans_nof_alerts, c3n_linked_cash_trans_nof_alerts, c4n_funds_in_funds_out_trans_nof_alerts, c5n_caution_list_santion_custs_nof_alerts, c6n_sanction_jurispiction_nof_alerts, c7n_trans_nof_equi_jurisdiction_nof_alerts, c8n_trans_peps_high_risk_accts_nof_alerts, c9n_trans_new_reactivate_accts_nof_alerts, c10n_trans_minor_accts_nof_alerts, c11n_all_otr_type_trans_nof_alerts, c12n_note_nof_alerts, c1o_large_cash_trans_nof_alerts_nostr, c2o_cash_in_cash_out_trans_nof_alerts_nostr, c3o_linked_cash_trans_nof_alerts_nostr, c4o_funds_in_funds_out_trans_nof_alerts_nostr, c5o_caution_list_santion_custs_nof_alerts_nostr, c6o_sanction_jurispiction_nof_alerts_nostr, c7o_trans_non_equi_jurisdiction_nof_alerts_nostr, c8o_trans_peps_high_risk_accts_nof_alerts_nostr, c9o_trans_new_reactivate_accts_nof_alerts_nostr, c10o_trans_minor_accts_nof_alerts_nostr, c11o_all_otr_type_trans_nof_alerts_nostr, c12o_note_nof_alerts_nostr, c1p_large_cash_trans_avg_time, c2p_casp_in_cash_out_trans_avg_time, c3p_linked_cash_trans_avg_time, c4p_funds_in_funds_out_trans_avg_time, c5p_caution_list_santion_custs_avg_time, c6p_sanction_jurispiction_avg_time, c7p_trans_non_equi_jurisdiction_avg_time, c8p_trans_peps_higp_risk_accts_avg_time, c9p_trans_new_reactivate_accts_avg_time, c10p_trans_minor_accts_avg_time, c11p_all_otr_type_trans_avg_time, c12p_note_avg_time, c1q_large_cash_trans_nof_alerts_str, c2q_cash_in_cash_out_trans_nof_alerts_str, c3q_linkeq_cash_trans_nof_alerts_str, c4q_funds_in_funds_out_trans_nof_alerts_str, c5q_caution_list_santion_custs_nof_alerts_str, c6q_sanction_jurispiction_nof_alerts_str, c7q_trans_non_equi_jurisdiction_nof_alerts_str, c8q_trans_peps_high_risk_accts_nof_alerts_str, c9q_trans_new_reactivate_accts_nof_alerts_str, c10q_trans_minor_accts_nof_alerts_str, c11q_all_otr_type_trans_nof_alerts_str, c12q_note_nof_alerts_str, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, 
					rep_period_to, rep_freq, nil_report_flg, arch_flg, entry_user,
					modify_user, verify_user, entry_time, modify_time, verify_time,entity_flg,modify_flg,del_flg);
			
					T19urrentrep.add(T19Report);

		}
		;

		/*
		 * List<Object> pagedlist;
		 * 
		 * if (T19urrentrep.size() < startItem) { pagedlist = Collections.emptyList(); }
		 * else { int toIndex = Math.min(startItem + pageSize, T19urrentrep.size());
		 * pagedlist = T19urrentrep.subList(startItem, toIndex); }
		 * logger.info("Converting to Page"); Page<Object> T19urrentrepPage = new
		 * PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
		 * T19urrentrep.size());
		 */

		mv.setViewName("ReportT19");
//		mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T19urrentrep);
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
		Date dT19;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT19 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery(
					"select count(*) from T19Report a where a.rep_period_from=?1 and  a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT19).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T19Report a").getSingleResult();
				if (modcnt > 0) {
					msg = "success";

					/*
					 * msg = "Records Pending for Verification For the Report";
					 */ } else {
					msg = "Data Not available for the Report. Please Contact Administrator";
				}
			} else {
				 msg = "Data Not available for the Report. Please Contact Administrator";

				//msg = "success";

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public ModelAndView getT19currentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T19urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;


		

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T19_TYPES_HITS_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			
			String d1a_large_cash_trans = (String) a[0];
			String d2a_cash_in_cash_out_trans = (String) a[1];
			String d3a_linked_cash_trans = (String) a[2];
			String d4a_funds_in_funds_out_trans = (String) a[3];
			String d5a_caution_list_santion_cust = (String) a[4];
			String d6a_sanction_jurisdiction = (String) a[5];
			String d7a_trans_non_equi_jurisdiction = (String) a[6];
			String d8a_trans_peps_high_risk_accts = (String) a[7];
			String d9a_trans_new_reactivate_accts = (String) a[8];
			String d10a_trans_minor_accts = (String) a[9];
			String d11a_all_otr_type_trans = (String) a[10];
			String d12a_note = (String) a[11];
			BigDecimal c1j_large_cash_trans_nof_hits = (BigDecimal) a[12];
			BigDecimal c2j_cash_in_cash_out_trans_nof_hits = (BigDecimal) a[13];
			BigDecimal c3j_linked_cash_trans_nof_hits = (BigDecimal) a[14];
			BigDecimal c4j_funds_in_funds_out_trans_nof_hits = (BigDecimal) a[15];
			BigDecimal c5j_caution_list_santion_custs_nof_hits = (BigDecimal) a[16];
			BigDecimal c6j_sanction_jurispiction_nof_hits = (BigDecimal) a[17];
			BigDecimal c7j_trans_non_equi_jurisdiction_nof_hits = (BigDecimal) a[18];
			BigDecimal c8j_trans_peps_high_risk_accts_nof_hits = (BigDecimal) a[19];
			BigDecimal c9j_trans_new_reactivate_accts_nof_hits = (BigDecimal) a[20];
			BigDecimal c10j_trans_minor_accts_nof_hits = (BigDecimal) a[21];
			BigDecimal c11j_all_otr_type_trans_nof_hits = (BigDecimal) a[22];
			String c12j_note_nof_hits = (String) a[23];
			BigDecimal c1k_large_cash_trans_nof_phits = (BigDecimal) a[24];
			BigDecimal c2k_cash_in_cash_out_trans_nof_phits = (BigDecimal) a[25];
			BigDecimal c3k_linked_cash_trans_nof_phits = (BigDecimal) a[26];
			BigDecimal c4k_funds_in_funds_out_trans_nof_phits = (BigDecimal) a[27];
			BigDecimal c5k_caution_list_santion_custs_nof_phits = (BigDecimal) a[28];
			BigDecimal c6k_sanction_jurispiction_nof_phits =(BigDecimal)a[29];
			BigDecimal c7k_trans_non_equi_jurisdiction =(BigDecimal)a[30];
			BigDecimal c8k_trans_peps_high_risk_accts_nof_phits =(BigDecimal)a[31];
			BigDecimal c9k_trans_new_reactivate_accts_nof_phits =(BigDecimal)a[32];
			BigDecimal c10k_trans_minor_accts_nof_phits =(BigDecimal)a[33];
			BigDecimal c11k_all_otr_type_trans_nof_phits =(BigDecimal)a[34];
			String c12k_note_nof_phits =(String)a[35];
			BigDecimal c1l_large_cash_trans_nof_phitsc =(BigDecimal)a[36];
			BigDecimal c2l_cash_in_cash_out_trans_nof_phitsc =(BigDecimal)a[37];
			BigDecimal c3l_linkel_cash_trans_nof_phitsc =(BigDecimal)a[38];
			BigDecimal c4l_funds_in_funds_out_trans_nof_phitsc =(BigDecimal)a[39];
			BigDecimal c5l_caution_list_santion_custs_nof_phitsc =(BigDecimal)a[40];
			BigDecimal c6l_sanction_jurispiction_nof_phitsc =(BigDecimal)a[41];
			BigDecimal c7l_trans_non_equi_jurisdiction_nof_phitsc =(BigDecimal)a[42];
			BigDecimal c8l_trans_peps_high_risk_accts_nof_phitsc =(BigDecimal)a[43];
			BigDecimal c9l_trans_new_reactivate_accts_nof_phitsc =(BigDecimal)a[44];
			BigDecimal c10l_trans_minor_accts_nof_phitsc =(BigDecimal)a[45];
			BigDecimal c11l_all_otr_type_trans_nof_phitsc =(BigDecimal)a[46];
			String c12l_note_nof_phitsc =(String)a[47];
			BigDecimal c1m_largm_cash_trans_nof_str =(BigDecimal)a[48];
			BigDecimal c2m_cash_in_cash_out_trans_nof_str =(BigDecimal)a[49];
			BigDecimal c3m_linked_cash_trans_nof_str =(BigDecimal)a[50];
			BigDecimal c4m_funds_in_funds_out_trans_nof_str =(BigDecimal)a[51];
			BigDecimal c5m_caution_list_santion_custs_nof_str =(BigDecimal)a[52];
			BigDecimal c6m_sanction_jurispiction_nof_str =(BigDecimal)a[53];
			BigDecimal c7m_trans_non_equi_jurisdiction_nof_str =(BigDecimal)a[54];
			BigDecimal c8m_trans_peps_high_risk_accts_nof_str =(BigDecimal)a[55];
			BigDecimal c9m_trans_new_reactivate_accts_nof_str =(BigDecimal)a[56];
			BigDecimal c10m_trans_minor_accts_nof_str =(BigDecimal)a[57];
			BigDecimal c11m_all_otr_type_trans_nof_str =(BigDecimal)a[58];
			String c12m_note_nof_str =(String)a[59];
			BigDecimal c1n_large_cash_trans_nof_alerts =(BigDecimal)a[60];
			BigDecimal c2n_cash_in_cash_out_trans_nof_alerts =(BigDecimal)a[61];
			BigDecimal c3n_linked_cash_trans_nof_alerts =(BigDecimal)a[62];
			BigDecimal c4n_funds_in_funds_out_trans_nof_alerts =(BigDecimal)a[63];
			BigDecimal c5n_caution_list_santion_custs_nof_alerts =(BigDecimal)a[64];
			BigDecimal c6n_sanction_jurispiction_nof_alerts =(BigDecimal)a[65];
			BigDecimal c7n_trans_nof_equi_jurisdiction_nof_alerts =(BigDecimal)a[66];
			BigDecimal c8n_trans_peps_high_risk_accts_nof_alerts =(BigDecimal)a[67];
			BigDecimal c9n_trans_new_reactivate_accts_nof_alerts =(BigDecimal)a[68];
			BigDecimal c10n_trans_minor_accts_nof_alerts =(BigDecimal)a[69];
			BigDecimal c11n_all_otr_type_trans_nof_alerts =(BigDecimal)a[70];
			String c12n_note_nof_alerts =(String)a[71];
			BigDecimal c1o_large_cash_trans_nof_alerts_nostr =(BigDecimal)a[72];
			BigDecimal c2o_cash_in_cash_out_trans_nof_alerts_nostr =(BigDecimal)a[73];
			BigDecimal c3o_linked_cash_trans_nof_alerts_nostr =(BigDecimal)a[74];
			BigDecimal c4o_funds_in_funds_out_trans_nof_alerts_nostr =(BigDecimal)a[75];
			BigDecimal c5o_caution_list_santion_custs_nof_alerts_nostr =(BigDecimal)a[76];
			BigDecimal c6o_sanction_jurispiction_nof_alerts_nostr =(BigDecimal)a[77];
			BigDecimal c7o_trans_non_equi_jurisdiction_nof_alerts_nostr =(BigDecimal)a[78];
			BigDecimal c8o_trans_peps_high_risk_accts_nof_alerts_nostr =(BigDecimal)a[79];
			BigDecimal c9o_trans_new_reactivate_accts_nof_alerts_nostr =(BigDecimal)a[80];
			BigDecimal c10o_trans_minor_accts_nof_alerts_nostr =(BigDecimal)a[81];
			BigDecimal c11o_all_otr_type_trans_nof_alerts_nostr =(BigDecimal)a[82];
			String c12o_note_nof_alerts_nostr =(String)a[83];
			String c1p_large_cash_trans_avg_time =(String)a[84];
			String c2p_casp_in_cash_out_trans_avg_time =(String)a[85];
			String c3p_linked_cash_trans_avg_time =(String)a[86];
			String c4p_funds_in_funds_out_trans_avg_time =(String)a[87];
			String c5p_caution_list_santion_custs_avg_time =(String)a[88];
			String c6p_sanction_jurispiction_avg_time =(String)a[89];
			String c7p_trans_non_equi_jurisdiction_avg_time =(String)a[90];
			String c8p_trans_peps_higp_risk_accts_avg_time =(String)a[91];
			String c9p_trans_new_reactivate_accts_avg_time =(String)a[92];
			String c10p_trans_minor_accts_avg_time =(String)a[93];
			String c11p_all_otr_type_trans_avg_time =(String)a[94];
			String c12p_note_avg_time =(String)a[95];
			BigDecimal c1q_large_cash_trans_nof_alerts_str =(BigDecimal)a[96];
			BigDecimal c2q_cash_in_cash_out_trans_nof_alerts_str =(BigDecimal)a[97];
			BigDecimal c3q_linkeq_cash_trans_nof_alerts_str =(BigDecimal)a[98];
			BigDecimal c4q_funds_in_funds_out_trans_nof_alerts_str =(BigDecimal)a[99];
			BigDecimal c5q_caution_list_santion_custs_nof_alerts_str =(BigDecimal)a[100];
			BigDecimal c6q_sanction_jurispiction_nof_alerts_str =(BigDecimal)a[101];
			BigDecimal c7q_trans_non_equi_jurisdiction_nof_alerts_str =(BigDecimal)a[102];
			BigDecimal c8q_trans_peps_high_risk_accts_nof_alerts_str =(BigDecimal)a[103];
			BigDecimal c9q_trans_new_reactivate_accts_nof_alerts_str =(BigDecimal)a[104];
			BigDecimal c10q_trans_minor_accts_nof_alerts_str =(BigDecimal)a[105];
			BigDecimal c11q_all_otr_type_trans_nof_alerts_str =(BigDecimal)a[106];
			String c12q_note_nof_alerts_str =(String)a[107];
			String report_code =(String)a[108];
			String report_name =(String)a[109];
			Date report_date =(Date)a[110];
			Date report_due_date =(Date)a[111];
			Date rep_submit_date =(Date)a[112];
			Date rep_period_from =(Date)a[113];
			Date rep_period_to =(Date)a[114];
			String rep_freq =(String)a[115];
			String nil_report_flg =(String)a[116];
			Character arch_flg =(Character)a[117];
			String entry_user = (String) a[118];
			String modify_user = (String) a[119];
			String verify_user = (String) a[120];
			Date entry_time = (Date) a[121];
			Date modify_time = (Date) a[122];
			Date verify_time = (Date) a[123];
			Character entity_flg = (Character) a[124];
			Character modify_flg = (Character) a[125];
			Character del_flg = (Character) a[126];


	
			T19Report T19Report = new T19Report(d1a_large_cash_trans, d2a_cash_in_cash_out_trans, 
					d3a_linked_cash_trans, d4a_funds_in_funds_out_trans, d5a_caution_list_santion_cust, 
					d6a_sanction_jurisdiction, d7a_trans_non_equi_jurisdiction, d8a_trans_peps_high_risk_accts, 
					d9a_trans_new_reactivate_accts, d10a_trans_minor_accts, d11a_all_otr_type_trans, d12a_note, 
					 c1j_large_cash_trans_nof_hits, c2j_cash_in_cash_out_trans_nof_hits, c3j_linked_cash_trans_nof_hits, c4j_funds_in_funds_out_trans_nof_hits, c5j_caution_list_santion_custs_nof_hits, c6j_sanction_jurispiction_nof_hits, c7j_trans_non_equi_jurisdiction_nof_hits, c8j_trans_peps_high_risk_accts_nof_hits, c9j_trans_new_reactivate_accts_nof_hits, c10j_trans_minor_accts_nof_hits, c11j_all_otr_type_trans_nof_hits, c12j_note_nof_hits, c1k_large_cash_trans_nof_phits, c2k_cash_in_cash_out_trans_nof_phits, c3k_linked_cash_trans_nof_phits, c4k_funds_in_funds_out_trans_nof_phits, c5k_caution_list_santion_custs_nof_phits, c6k_sanction_jurispiction_nof_phits, c7k_trans_non_equi_jurisdiction, c8k_trans_peps_high_risk_accts_nof_phits, c9k_trans_new_reactivate_accts_nof_phits, c10k_trans_minor_accts_nof_phits, c11k_all_otr_type_trans_nof_phits, c12k_note_nof_phits, c1l_large_cash_trans_nof_phitsc, c2l_cash_in_cash_out_trans_nof_phitsc, c3l_linkel_cash_trans_nof_phitsc, c4l_funds_in_funds_out_trans_nof_phitsc, c5l_caution_list_santion_custs_nof_phitsc, c6l_sanction_jurispiction_nof_phitsc, c7l_trans_non_equi_jurisdiction_nof_phitsc, c8l_trans_peps_high_risk_accts_nof_phitsc, c9l_trans_new_reactivate_accts_nof_phitsc, c10l_trans_minor_accts_nof_phitsc, c11l_all_otr_type_trans_nof_phitsc, c12l_note_nof_phitsc, c1m_largm_cash_trans_nof_str, c2m_cash_in_cash_out_trans_nof_str, c3m_linked_cash_trans_nof_str, c4m_funds_in_funds_out_trans_nof_str, c5m_caution_list_santion_custs_nof_str, c6m_sanction_jurispiction_nof_str, c7m_trans_non_equi_jurisdiction_nof_str, c8m_trans_peps_high_risk_accts_nof_str, c9m_trans_new_reactivate_accts_nof_str, c10m_trans_minor_accts_nof_str, c11m_all_otr_type_trans_nof_str, c12m_note_nof_str, c1n_large_cash_trans_nof_alerts, c2n_cash_in_cash_out_trans_nof_alerts, c3n_linked_cash_trans_nof_alerts, c4n_funds_in_funds_out_trans_nof_alerts, c5n_caution_list_santion_custs_nof_alerts, c6n_sanction_jurispiction_nof_alerts, c7n_trans_nof_equi_jurisdiction_nof_alerts, c8n_trans_peps_high_risk_accts_nof_alerts, c9n_trans_new_reactivate_accts_nof_alerts, c10n_trans_minor_accts_nof_alerts, c11n_all_otr_type_trans_nof_alerts, c12n_note_nof_alerts, c1o_large_cash_trans_nof_alerts_nostr, c2o_cash_in_cash_out_trans_nof_alerts_nostr, c3o_linked_cash_trans_nof_alerts_nostr, c4o_funds_in_funds_out_trans_nof_alerts_nostr, c5o_caution_list_santion_custs_nof_alerts_nostr, c6o_sanction_jurispiction_nof_alerts_nostr, c7o_trans_non_equi_jurisdiction_nof_alerts_nostr, c8o_trans_peps_high_risk_accts_nof_alerts_nostr, c9o_trans_new_reactivate_accts_nof_alerts_nostr, c10o_trans_minor_accts_nof_alerts_nostr, c11o_all_otr_type_trans_nof_alerts_nostr, c12o_note_nof_alerts_nostr, c1p_large_cash_trans_avg_time, c2p_casp_in_cash_out_trans_avg_time, c3p_linked_cash_trans_avg_time, c4p_funds_in_funds_out_trans_avg_time, c5p_caution_list_santion_custs_avg_time, c6p_sanction_jurispiction_avg_time, c7p_trans_non_equi_jurisdiction_avg_time, c8p_trans_peps_higp_risk_accts_avg_time, c9p_trans_new_reactivate_accts_avg_time, c10p_trans_minor_accts_avg_time, c11p_all_otr_type_trans_avg_time, c12p_note_avg_time, c1q_large_cash_trans_nof_alerts_str, c2q_cash_in_cash_out_trans_nof_alerts_str, c3q_linkeq_cash_trans_nof_alerts_str, c4q_funds_in_funds_out_trans_nof_alerts_str, c5q_caution_list_santion_custs_nof_alerts_str, c6q_sanction_jurispiction_nof_alerts_str, c7q_trans_non_equi_jurisdiction_nof_alerts_str, c8q_trans_peps_high_risk_accts_nof_alerts_str, c9q_trans_new_reactivate_accts_nof_alerts_str, c10q_trans_minor_accts_nof_alerts_str, c11q_all_otr_type_trans_nof_alerts_str, c12q_note_nof_alerts_str, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, 
					rep_period_to, rep_freq, nil_report_flg, arch_flg, entry_user,
					modify_user, verify_user, entry_time, modify_time, verify_time,entity_flg,modify_flg,del_flg);
			
					T19urrentrep.add(T19Report);			
		
 		
		};

		mv.setViewName("ReportT19");
		mv.addObject("reportsummary", T19urrentrep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		return mv;

	}

	public ModelAndView getT19currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T19urrentDt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T19_TYPES_HITS_DETAILS a where report_date = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T19_TYPES_HITS_DETAILS a where report_date = ?1");
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
			String acct_no = (String) a[2];
			String acct_name = (String) a[3];
			Date tran_date = (Date) a[4];
			String tran_id = (String) a[5];
			BigDecimal part_tran_id = (BigDecimal) a[6];
			String part_tran_type = (String) a[7];
			String tran_crncy = (String) a[8];
			BigDecimal tran_amt = (BigDecimal) a[9];
			String tran_particulars = (String) a[10];
			BigDecimal tran_nof_hits = (BigDecimal) a[11];
			BigDecimal tran_nof_phits = (BigDecimal) a[12];
			BigDecimal tran_nof_phitsc = (BigDecimal) a[13];
			BigDecimal tran_nof_str = (BigDecimal) a[14];
			BigDecimal tran_nof_alerts = (BigDecimal) a[15];
			BigDecimal tran_nof_alerts_nostr = (BigDecimal) a[16];
			Date tran_avg_time = (Date) a[17];
			BigDecimal tran_nof_alerts_str = (BigDecimal) a[18];
			String qtr_flg = (String) a[19];
			String entity_flg = (String) a[20];
			String del_flg = (String) a[21];
			String modify_flg = (String) a[22];
			Date entry_date = (Date) a[23];
			Date modify_date = (Date) a[24];
			Date verify_date = (Date) a[25];
			String entry_user = (String) a[26];
			String modify_user = (String) a[27];
			String verify_user = (String) a[28];
			String report_code = (String) a[29];
			String report_name = (String) a[30];
			Date report_date = (Date) a[31];
			String arch_flg = (String) a[32];



			T19Detail py = new T19Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_nof_hits, tran_nof_phits, tran_nof_phitsc, tran_nof_str, tran_nof_alerts, tran_nof_alerts_nostr, tran_avg_time, tran_nof_alerts_str, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg);

			T19urrentDt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T19urrentDt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T19urrentDt1.size());
			pagedlist = T19urrentDt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T19urrentDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				T19urrentDt1.size());

		mv.setViewName("ReportT19 :: reportcontent");
		mv.addObject("reportdetails", T19urrentDt1Page);

		mv.addObject("singledetail", new T19Detail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");

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
			fileName = reportId + "_" +strDate1;
		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName = fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T19/T19.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T19/T19.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T19/T19.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T19/T19.jasper");
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
	public String editT19(T19Report t19Report) {
		// TODO Auto-generated method stub
		String msg = "";
		Session session = sessionFactory.getCurrentSession();
		/* try { */
		T19Report up = t19Report;
		up.setReport_code("T19");
		 up.setEntity_flg('N');
		 up.setModify_flg('Y');
		 up.setDel_flg('N');

		session.saveOrUpdate(up);
		msg = "Record Edited Successfully";

		return msg;
	}

	public String verifyT19(T19Report t19Report) {

		String msg = "";

		Session session = sessionFactory.getCurrentSession();

		T19Report up = t19Report;
		if(up.getModify_user().equals(up.getVerify_user())) {
			msg = "Same User Cannot Verify! ";
		}else {
		up.setReport_code("T19");
		 up.setEntity_flg('Y');
		 up.setDel_flg('N');

		session.saveOrUpdate(up);
		msg = "Verified Successfully";
		}
		return msg;
	}

}