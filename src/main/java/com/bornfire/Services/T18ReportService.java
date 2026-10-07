package com.bornfire.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.entity.TRAN_MASTER_DETAIL_RBS;
import com.bornfire.entity.t1.T1CurProdDetail;
import com.bornfire.entity.t10.T10Detail;
import com.bornfire.entity.t10.T10Report;
import com.bornfire.entity.t12.T12Detail;
import com.bornfire.entity.t18.T18Detail;
import com.bornfire.entity.t18.T18Report;
import com.monitorjbl.xlsx.StreamingReader;

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
public class T18ReportService {

private static final Logger logger = LoggerFactory.getLogger(T12ReportService.class);

public static File multipartToFile(MultipartFile multipart, String fileName)
		throws IllegalStateException, IOException {


Path newFile = Paths.get(multipart.getOriginalFilename());
  try(InputStream is = multipart.getInputStream();
     OutputStream os = Files.newOutputStream(newFile)) {
     byte[] buffer = new byte[4096];
     int read = 0;
     while((read = is.read(buffer)) > 0) {
       os.write(buffer,0,read);
     }
  }
  return newFile.toFile();  


//	File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
//	multipart.transferTo(convFile);
//	return convFile;
}
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
	
	public ModelAndView getT18View(String reportId, String fromdate, String todate) {

		logger.info("T18ReportService -> getT18View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T18DIST_CHANNELS_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			
			String d1a_agents = (String) a[0];
			String d2a_cash = (String) a[1];
			String d3a_elecronic_transfer = (String) a[2];
			String d4a_credit_cards = (String) a[3];
			String d5a_all_other_channels = (String) a[4];
			String d6a_atm = (String) a[5];
			String d7a_counter_trans = (String) a[6];
			String d8a_clearing_trans = (String) a[7];
			String d9a_00001_trans = (String) a[8];
			String d10a_0002_trans = (String) a[9];
			String d11a_total = (String) a[10];
			String d12a_validation = (String) a[11];
			BigDecimal c1e_agents_nof_cust = (BigDecimal) a[12];
			BigDecimal c2e_cash_nof_cust = (BigDecimal) a[13];
			BigDecimal c3e_elecronic_transfer_nof_cust = (BigDecimal) a[14];
			BigDecimal c4e_credit_carcs_nof_cust = (BigDecimal) a[15];
			BigDecimal c5e_all_other_channels_nof_cust = (BigDecimal) a[16];
			BigDecimal c6e_atm_nof_cust = (BigDecimal) a[17];
			BigDecimal c7e_counter_trans_nof_cust = (BigDecimal) a[18];
			BigDecimal c8e_clearing_trans_nof_cust = (BigDecimal) a[19];
			BigDecimal c9e_00001_trans_nof_cust = (BigDecimal) a[20];
			BigDecimal c10e_0002_trans_nof_cust = (BigDecimal) a[21];
			BigDecimal c11e_total_nof_cust = (BigDecimal) a[22];
			BigDecimal c12e_validation_nof_cust = (BigDecimal) a[23];
			BigDecimal c1f_agents_nof_trans = (BigDecimal) a[24];
			BigDecimal c2f_cash_nof_trans = (BigDecimal) a[25];
			BigDecimal c3f_elecronic_transfer_nof_trans = (BigDecimal) a[26];
			BigDecimal c4f_credit_cards_nof_trans = (BigDecimal) a[27];
			BigDecimal c5f_all_other_channels_nof_trans = (BigDecimal) a[28];
			BigDecimal c6f_atm_nof_trans = (BigDecimal) a[29];
			BigDecimal c7f_counter_trans_nof_trans = (BigDecimal) a[30];
			BigDecimal c8f_clearing_trans_nof_trans = (BigDecimal) a[31];
			BigDecimal c9f_00001_trans_nof_trans = (BigDecimal) a[32];
			BigDecimal c10f_0002_trans_nof_trans = (BigDecimal) a[33];
			BigDecimal c11f_total_nof_trans = (BigDecimal) a[34];
			BigDecimal c12f_validation_nof_trans = (BigDecimal) a[35];
			BigDecimal c1g_agents_val_trans = (BigDecimal) a[36];
			BigDecimal c2g_cash_val_trans = (BigDecimal) a[37];
			BigDecimal c3g_elecronic_transfer_val_trans = (BigDecimal) a[38];
			BigDecimal c4g_credit_cards_val_trans = (BigDecimal) a[39];
			BigDecimal c5g_all_other_channels_val_trans = (BigDecimal) a[40];
			BigDecimal c6g_atm_val_trans = (BigDecimal) a[41];
			BigDecimal c7g_counter_trans_val_trans = (BigDecimal) a[42];
			BigDecimal c8g_clearing_trans_val_trans = (BigDecimal) a[43];
			BigDecimal c9g_00001_trans_val_trans = (BigDecimal) a[44];
			BigDecimal c10g_0002_trans_val_trans = (BigDecimal) a[45];
			BigDecimal c11g_total_val_trans = (BigDecimal) a[46];
			BigDecimal c12g_validation_val_trans = (BigDecimal) a[47];
			String report_code = (String) a[48];
			String report_name = (String) a[49];
			Date report_date = (Date) a[50];
			Date report_due_date = (Date) a[51];
			Date rep_submit_date = (Date) a[52];
			Date rep_period_from = (Date) a[53];
			Date rep_period_to = (Date) a[54];
			String rep_freq = (String) a[55];
			Character nil_report_flg = (Character) a[56];
			Character arch_flg = (Character) a[57];

			
			T18Report t18Report = new T18Report(d1a_agents, d2a_cash, d3a_elecronic_transfer, d4a_credit_cards, d5a_all_other_channels, 
					d6a_atm, d7a_counter_trans, d8a_clearing_trans, d9a_00001_trans, d10a_0002_trans, d11a_total, d12a_validation, c1e_agents_nof_cust, 
					c2e_cash_nof_cust, c3e_elecronic_transfer_nof_cust, c4e_credit_carcs_nof_cust, c5e_all_other_channels_nof_cust, c6e_atm_nof_cust, 
					c7e_counter_trans_nof_cust, c8e_clearing_trans_nof_cust, c9e_00001_trans_nof_cust, c10e_0002_trans_nof_cust, c11e_total_nof_cust, 
					c12e_validation_nof_cust, c1f_agents_nof_trans, c2f_cash_nof_trans, c3f_elecronic_transfer_nof_trans, c4f_credit_cards_nof_trans, 
					c5f_all_other_channels_nof_trans, c6f_atm_nof_trans, c7f_counter_trans_nof_trans, c8f_clearing_trans_nof_trans, c9f_00001_trans_nof_trans,
					c10f_0002_trans_nof_trans, c11f_total_nof_trans, c12f_validation_nof_trans, c1g_agents_val_trans, c2g_cash_val_trans, 
					c3g_elecronic_transfer_val_trans, c4g_credit_cards_val_trans, c5g_all_other_channels_val_trans, c6g_atm_val_trans, 
					c7g_counter_trans_val_trans, c8g_clearing_trans_val_trans, c9g_00001_trans_val_trans, c10g_0002_trans_val_trans, c11g_total_val_trans,
					c12g_validation_val_trans, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, 
					rep_freq, nil_report_flg, arch_flg);
					
			t17Rep.add(t18Report);

			
		}

		mv.setViewName("ReportT18");
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
		Date dt2;

		Query query = null;

		query = hs.createNativeQuery("select count(*) from T18DIST_CHANNELS_DETAILS where report_date = ?1 ");
				try {
					query.setParameter(1, df.parse(todate));
				} catch (ParseException e) {
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
	
	public ModelAndView getT18Rep(String reportId, String fromdate, String todate) {

		logger.info("T18ReportService -> getT18Rep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T18DIST_CHANNELS_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			
			String d1a_agents = (String) a[0];
			String d2a_cash = (String) a[1];
			String d3a_elecronic_transfer = (String) a[2];
			String d4a_credit_cards = (String) a[3];
			String d5a_all_other_channels = (String) a[4];
			String d6a_atm = (String) a[5];
			String d7a_counter_trans = (String) a[6];
			String d8a_clearing_trans = (String) a[7];
			String d9a_00001_trans = (String) a[8];
			String d10a_0002_trans = (String) a[9];
			String d11a_total = (String) a[10];
			String d12a_validation = (String) a[11];
			BigDecimal c1e_agents_nof_cust = (BigDecimal) a[12];
			BigDecimal c2e_cash_nof_cust = (BigDecimal) a[13];
			BigDecimal c3e_elecronic_transfer_nof_cust = (BigDecimal) a[14];
			BigDecimal c4e_credit_carcs_nof_cust = (BigDecimal) a[15];
			BigDecimal c5e_all_other_channels_nof_cust = (BigDecimal) a[16];
			BigDecimal c6e_atm_nof_cust = (BigDecimal) a[17];
			BigDecimal c7e_counter_trans_nof_cust = (BigDecimal) a[18];
			BigDecimal c8e_clearing_trans_nof_cust = (BigDecimal) a[19];
			BigDecimal c9e_00001_trans_nof_cust = (BigDecimal) a[20];
			BigDecimal c10e_0002_trans_nof_cust = (BigDecimal) a[21];
			BigDecimal c11e_total_nof_cust = (BigDecimal) a[22];
			BigDecimal c12e_validation_nof_cust = (BigDecimal) a[23];
			BigDecimal c1f_agents_nof_trans = (BigDecimal) a[24];
			BigDecimal c2f_cash_nof_trans = (BigDecimal) a[25];
			BigDecimal c3f_elecronic_transfer_nof_trans = (BigDecimal) a[26];
			BigDecimal c4f_credit_cards_nof_trans = (BigDecimal) a[27];
			BigDecimal c5f_all_other_channels_nof_trans = (BigDecimal) a[28];
			BigDecimal c6f_atm_nof_trans = (BigDecimal) a[29];
			BigDecimal c7f_counter_trans_nof_trans = (BigDecimal) a[30];
			BigDecimal c8f_clearing_trans_nof_trans = (BigDecimal) a[31];
			BigDecimal c9f_00001_trans_nof_trans = (BigDecimal) a[32];
			BigDecimal c10f_0002_trans_nof_trans = (BigDecimal) a[33];
			BigDecimal c11f_total_nof_trans = (BigDecimal) a[34];
			BigDecimal c12f_validation_nof_trans = (BigDecimal) a[35];
			BigDecimal c1g_agents_val_trans = (BigDecimal) a[36];
			BigDecimal c2g_cash_val_trans = (BigDecimal) a[37];
			BigDecimal c3g_elecronic_transfer_val_trans = (BigDecimal) a[38];
			BigDecimal c4g_credit_cards_val_trans = (BigDecimal) a[39];
			BigDecimal c5g_all_other_channels_val_trans = (BigDecimal) a[40];
			BigDecimal c6g_atm_val_trans = (BigDecimal) a[41];
			BigDecimal c7g_counter_trans_val_trans = (BigDecimal) a[42];
			BigDecimal c8g_clearing_trans_val_trans = (BigDecimal) a[43];
			BigDecimal c9g_00001_trans_val_trans = (BigDecimal) a[44];
			BigDecimal c10g_0002_trans_val_trans = (BigDecimal) a[45];
			BigDecimal c11g_total_val_trans = (BigDecimal) a[46];
			BigDecimal c12g_validation_val_trans = (BigDecimal) a[47];
			String report_code = (String) a[48];
			String report_name = (String) a[49];
			Date report_date = (Date) a[50];
			Date report_due_date = (Date) a[51];
			Date rep_submit_date = (Date) a[52];
			Date rep_period_from = (Date) a[53];
			Date rep_period_to = (Date) a[54];
			String rep_freq = (String) a[55];
			Character nil_report_flg = (Character) a[56];
			Character arch_flg = (Character) a[57];

			
			T18Report t18Report = new T18Report(d1a_agents, d2a_cash, d3a_elecronic_transfer, d4a_credit_cards, d5a_all_other_channels, 
					d6a_atm, d7a_counter_trans, d8a_clearing_trans, d9a_00001_trans, d10a_0002_trans, d11a_total, d12a_validation, c1e_agents_nof_cust, 
					c2e_cash_nof_cust, c3e_elecronic_transfer_nof_cust, c4e_credit_carcs_nof_cust, c5e_all_other_channels_nof_cust, c6e_atm_nof_cust, 
					c7e_counter_trans_nof_cust, c8e_clearing_trans_nof_cust, c9e_00001_trans_nof_cust, c10e_0002_trans_nof_cust, c11e_total_nof_cust, 
					c12e_validation_nof_cust, c1f_agents_nof_trans, c2f_cash_nof_trans, c3f_elecronic_transfer_nof_trans, c4f_credit_cards_nof_trans, 
					c5f_all_other_channels_nof_trans, c6f_atm_nof_trans, c7f_counter_trans_nof_trans, c8f_clearing_trans_nof_trans, c9f_00001_trans_nof_trans,
					c10f_0002_trans_nof_trans, c11f_total_nof_trans, c12f_validation_nof_trans, c1g_agents_val_trans, c2g_cash_val_trans, 
					c3g_elecronic_transfer_val_trans, c4g_credit_cards_val_trans, c5g_all_other_channels_val_trans, c6g_atm_val_trans, 
					c7g_counter_trans_val_trans, c8g_clearing_trans_val_trans, c9g_00001_trans_val_trans, c10g_0002_trans_val_trans, c11g_total_val_trans,
					c12g_validation_val_trans, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, 
					rep_freq, nil_report_flg, arch_flg);
			t17Rep.add(t18Report);
		
		
		}

		mv.setViewName("ReportT18");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t17Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		logger.info("returning model view");
		return mv;
	}
	
	public ModelAndView getT18Dtl(String reportId, String fromdate, String todate, String currency,
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
						"select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T18_REPORT =?2");
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
		List<T18Report> T1Master = new ArrayList<T18Report>();

		try {
			T1Master = hs.createQuery("from T18Report a where a.report_date = ?1 ", T18Report.class)
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

		mv.setViewName("ReportT18 :: reportcontent");
		// mv.setViewName("ReportT1");
		mv.addObject("reportdetails", T1Dt1Page);
		mv.addObject("reportmaster", T1Master);
		mv.addObject("singledetail", new TRAN_MASTER_DETAIL_RBS());
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
			fileName = "t"+reportId + "_" + strDate1;
			System.out.println("filename"+fileName);
		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName =  fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile = null;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile =this.getClass().getResourceAsStream("/static/jasper/Details/NEW_AML_DETAILS/T18Detail.jasper");
					} 

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile =this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T18/T18.jasper");
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
			map.put("REPORT_DATE", strDate1);
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
	
	public String processUpload(String asondate, MultipartFile files, String userid)
			throws SQLException, FileNotFoundException, IOException {

		String result = "";
		
		String status = "";

				
				
				MultipartFile uploadedFile = files;
				
				
				
				result = T18Upload(uploadedFile, asondate, userid);
				
				
		return result;
	}
	
	public String T18Upload(MultipartFile file, String asondate, String userid)
			throws SQLException, FileNotFoundException, IOException {

		
		String fileName = file.getOriginalFilename();
		File convertedFile = multipartToFile(file, fileName);

		String fileExt = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		logger.info("file extension : " + fileExt);

		String Errormsg = "";

		String status = "";

		

		Session theSession = sessionFactory.getCurrentSession();
		

		

		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {
			
			logger.info("reading values from Excel");

			String cellval = "";

			try (InputStream is = new FileInputStream(convertedFile);

					Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is)) {
				
				
				
				for (Sheet s : workbook) {
					
					int sheetNumber = workbook.getSheetIndex(s);
					if(sheetNumber == 0 ) {
			
					logger.info("inside workbook");
					
					for (Row r : s) {
									
						ArrayList<String> resultList = new ArrayList<>();
						if (r.getRowNum() == 0) {
							continue;
						}

						cellval = "";
						String val = null;
						for (int j = 0; j < 13; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);
						}
						
						String	cust_id = resultList.get(0);
						String	cust_name= resultList.get(1);
						String	acct_no= resultList.get(2);
						String	acct_name= resultList.get(3);
						
						String	date=resultList.get(4);
						Date tran_date=new SimpleDateFormat("dd-MM-yyyy").parse(date);  
						
						
						
						String	tran_id= resultList.get(5);
						
						
						String	part_tran_id= resultList.get(6);
						BigDecimal bigdecimalpart_tran_id = new BigDecimal(part_tran_id);
						
						
						String	part_tran_type= resultList.get(7);
						String	tran_crncy= resultList.get(8);
						
						String	tran_amt= resultList.get(9);
						BigDecimal bigdecimaltran_amt = new BigDecimal(tran_amt);
						
						
						String	tran_particulars= resultList.get(10);
						String	tran_channel= resultList.get(11);
						String	tran_category= resultList.get(12);
						Character qtr_flg = null;
						Character entity_flg = null;
						Character del_flg = null;
						Character modify_flg = null;
						Date entry_date = null;
						Date modify_date = null;
						Date verify_date = null;
						String entry_user = null;
						String modify_user = userid;
						String verify_user = null;
						String report_code = null;
						String report_name = "T8";
						String date2 = resultList.get(25);
						Date report_date = new SimpleDateFormat("dd-MM-yyyy").parse(date2);
						Character arch_flg = null;
						 if(cust_id == null) {
							 
							 break;
						 }
						 String	cell_mapping= null;
						 String	process_owner= null;
						 String	bank_id= null;
						 String	cust_type= null;
						 String	cust_rating= null;
						 Date	cust_rating_date= null;
						 String	tran_type = null;
						 String	cntry_res = null;
						 String	cnty_incorp = null;
						 String	cntry_oper = null;
						 String	aml_code_1 = null;
						 String	aml_code_2 = null;
						 String	aml_code_3 = null;
						 String	aml_code_4 = null;
						 String	aml_code_5 = null;
						 String	aml_code_6 = null;
						 String	aml_code_7 = null;
						 String	aml_code_8 = null;
						 String	aml_code_9 = null;
						 String	aml_code_10 = null;
						 Date	relationship_date = null;
						 String	mis_face_to_face = null;
						 String	mis_non_face_to_face = null;
						 String	mis_internal_rating_grade = null;
						 String	mis_internal_rating_scale = null;
						 String	tran_sub_type = null;

						
						T18Detail t18Detail = new T18Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, bigdecimalpart_tran_id, part_tran_type, 
								tran_crncy, bigdecimaltran_amt, tran_particulars, tran_channel, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date,
								modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg
								,cell_mapping,process_owner,bank_id,cust_type,cust_rating,cust_rating_date,tran_type,  cntry_res,
								 cnty_incorp,  cntry_oper,  aml_code_1,  aml_code_2,  aml_code_3,
								 aml_code_4,  aml_code_5,  aml_code_6,  aml_code_7,  aml_code_8,
								 aml_code_9,  aml_code_10,  relationship_date,  mis_face_to_face,
								 mis_non_face_to_face,  mis_internal_rating_grade,  mis_internal_rating_scale, tran_sub_type);
						
						


						 
						 logger.info("saving values:");
						theSession.save(t18Detail);
						theSession.flush();
						theSession.clear();
					}

					
				}
					StoredProcedureQuery query1 = theSession.createStoredProcedureQuery("t18dist_channels_sp")
							.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
					query1.setParameter("REPORT_DATE", asondate);
					query1.execute();

				status = "File Successfully Uploaded";
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}
		}

		

		return status;

	}
	
	
	
	public Page<T18Detail> parameterlistwithdecode(String rpt_date,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T18Detail> t9Dt1 = new ArrayList<T18Detail>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T18DIST_CHANNELS_DETAILS where report_date=?1");
			qr.setParameter(1,rpt_date);
			List<Object[]> result = qr.getResultList();
			
			try {
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
				String tran_channel = (String) a[11];
				String tran_category = (String) a[12];
				Character qtr_flg = (Character) a[13];
				Character entity_flg = (Character) a[14];
				Character del_flg = (Character) a[15];
				Character modify_flg = (Character) a[16];
				Date entry_date = (Date) a[17];
				Date modify_date = (Date) a[18];
				Date verify_date = (Date) a[19];
				String entry_user = (String) a[20];
				String modify_user = (String) a[21];
				String verify_user = (String) a[22];
				String report_code = (String) a[23];
				String report_name = (String) a[24];
				Date report_date = (Date) a[25];
				Character arch_flg = (Character) a[26];
				String cell_mapping = (String) a[27];
				String process_owner = (String) a[28];
				String bank_id = (String) a[29];
				String cust_type = (String) a[30];
				String cust_rating = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_type = (String) a[33];
				String cntry_res = (String) a[34];
				String cnty_incorp = (String) a[35];
				String cntry_oper = (String) a[36];
				String aml_code_1 = (String) a[37];
				String aml_code_2 = (String) a[38];
				String aml_code_3 = (String) a[39];
				String aml_code_4 = (String) a[40];
				String aml_code_5 = (String) a[41];
				String aml_code_6 = (String) a[42];
				String aml_code_7 = (String) a[43];
				String aml_code_8 = (String) a[44];
				String aml_code_9 = (String) a[45];
				String aml_code_10 = (String) a[46];
				Date relationship_date = (Date) a[47];
				String mis_face_to_face = (String) a[48];
				String mis_non_face_to_face = (String) a[49];
				String mis_internal_rating_grade = (String) a[50];
				String mis_internal_rating_scale = (String) a[51];
				String tran_sub_type = (String) a[52];



				
				T18Detail py = new T18Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_channel, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_type, cust_rating, cust_rating_date, tran_type, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, tran_sub_type);
						
						
						
						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T18Detail> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T18Detail> t9Dt1Page = new PageImpl<T18Detail>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}
	
	
	
	public Page<T18Detail> searchT18Both(String rpt_date, String tran_date1, String P_O, String tran_id1,BigDecimal part_tran_id1, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T18Detail> t9Dt1 = new ArrayList<T18Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T18DIST_CHANNELS_DETAILS where report_date=?1 and tran_date=?2 and process_owner=?3 and trim(tran_id)=?4 and trim(part_tran_id)=?5");
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
				String acct_no = (String) a[2];
				String acct_name = (String) a[3];
				Date tran_date = (Date) a[4];
				String tran_id = (String) a[5];
				BigDecimal part_tran_id = (BigDecimal) a[6];
				String part_tran_type = (String) a[7];
				String tran_crncy = (String) a[8];
				BigDecimal tran_amt = (BigDecimal) a[9];
				String tran_particulars = (String) a[10];
				String tran_channel = (String) a[11];
				String tran_category = (String) a[12];
				Character qtr_flg = (Character) a[13];
				Character entity_flg = (Character) a[14];
				Character del_flg = (Character) a[15];
				Character modify_flg = (Character) a[16];
				Date entry_date = (Date) a[17];
				Date modify_date = (Date) a[18];
				Date verify_date = (Date) a[19];
				String entry_user = (String) a[20];
				String modify_user = (String) a[21];
				String verify_user = (String) a[22];
				String report_code = (String) a[23];
				String report_name = (String) a[24];
				Date report_date = (Date) a[25];
				Character arch_flg = (Character) a[26];
				String cell_mapping = (String) a[27];
				String process_owner = (String) a[28];
				String bank_id = (String) a[29];
				String cust_type = (String) a[30];
				String cust_rating = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_type = (String) a[33];
				String cntry_res = (String) a[34];
				String cnty_incorp = (String) a[35];
				String cntry_oper = (String) a[36];
				String aml_code_1 = (String) a[37];
				String aml_code_2 = (String) a[38];
				String aml_code_3 = (String) a[39];
				String aml_code_4 = (String) a[40];
				String aml_code_5 = (String) a[41];
				String aml_code_6 = (String) a[42];
				String aml_code_7 = (String) a[43];
				String aml_code_8 = (String) a[44];
				String aml_code_9 = (String) a[45];
				String aml_code_10 = (String) a[46];
				Date relationship_date = (Date) a[47];
				String mis_face_to_face = (String) a[48];
				String mis_non_face_to_face = (String) a[49];
				String mis_internal_rating_grade = (String) a[50];
				String mis_internal_rating_scale = (String) a[51];
				String tran_sub_type = (String) a[52];



				
				T18Detail py = new T18Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_channel, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_type, cust_rating, cust_rating_date, tran_type, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, tran_sub_type);
						
						
						
						
				
						
				
						
						

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T18Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T18Detail> t9Dt1Page = new PageImpl<T18Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Page<T18Detail> searchT18PO(String rpt_date, String P_O, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T18Detail> t9Dt1 = new ArrayList<T18Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T18DIST_CHANNELS_DETAILS where report_date=?1 and process_owner=?2");
		qr.setParameter(1, rpt_date);

		qr.setParameter(2, P_O);

		List<Object[]> result = qr.getResultList();

		try {
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
				String tran_channel = (String) a[11];
				String tran_category = (String) a[12];
				Character qtr_flg = (Character) a[13];
				Character entity_flg = (Character) a[14];
				Character del_flg = (Character) a[15];
				Character modify_flg = (Character) a[16];
				Date entry_date = (Date) a[17];
				Date modify_date = (Date) a[18];
				Date verify_date = (Date) a[19];
				String entry_user = (String) a[20];
				String modify_user = (String) a[21];
				String verify_user = (String) a[22];
				String report_code = (String) a[23];
				String report_name = (String) a[24];
				Date report_date = (Date) a[25];
				Character arch_flg = (Character) a[26];
				String cell_mapping = (String) a[27];
				String process_owner = (String) a[28];
				String bank_id = (String) a[29];
				String cust_type = (String) a[30];
				String cust_rating = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_type = (String) a[33];
				String cntry_res = (String) a[34];
				String cnty_incorp = (String) a[35];
				String cntry_oper = (String) a[36];
				String aml_code_1 = (String) a[37];
				String aml_code_2 = (String) a[38];
				String aml_code_3 = (String) a[39];
				String aml_code_4 = (String) a[40];
				String aml_code_5 = (String) a[41];
				String aml_code_6 = (String) a[42];
				String aml_code_7 = (String) a[43];
				String aml_code_8 = (String) a[44];
				String aml_code_9 = (String) a[45];
				String aml_code_10 = (String) a[46];
				Date relationship_date = (Date) a[47];
				String mis_face_to_face = (String) a[48];
				String mis_non_face_to_face = (String) a[49];
				String mis_internal_rating_grade = (String) a[50];
				String mis_internal_rating_scale = (String) a[51];
				String tran_sub_type = (String) a[52];



				
				T18Detail py = new T18Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_channel, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_type, cust_rating, cust_rating_date, tran_type, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, tran_sub_type);
						
						
						
						
				
						
				

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T18Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T18Detail> t9Dt1Page = new PageImpl<T18Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	public Page<T18Detail> searchT18Date(String rpt_date, String tran_date1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T18Detail> t9Dt1 = new ArrayList<T18Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T18DIST_CHANNELS_DETAILS where report_date=?1 and tran_date=?2 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		

		List<Object[]> result = qr.getResultList();

		try {
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
				String tran_channel = (String) a[11];
				String tran_category = (String) a[12];
				Character qtr_flg = (Character) a[13];
				Character entity_flg = (Character) a[14];
				Character del_flg = (Character) a[15];
				Character modify_flg = (Character) a[16];
				Date entry_date = (Date) a[17];
				Date modify_date = (Date) a[18];
				Date verify_date = (Date) a[19];
				String entry_user = (String) a[20];
				String modify_user = (String) a[21];
				String verify_user = (String) a[22];
				String report_code = (String) a[23];
				String report_name = (String) a[24];
				Date report_date = (Date) a[25];
				Character arch_flg = (Character) a[26];
				String cell_mapping = (String) a[27];
				String process_owner = (String) a[28];
				String bank_id = (String) a[29];
				String cust_type = (String) a[30];
				String cust_rating = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_type = (String) a[33];
				String cntry_res = (String) a[34];
				String cnty_incorp = (String) a[35];
				String cntry_oper = (String) a[36];
				String aml_code_1 = (String) a[37];
				String aml_code_2 = (String) a[38];
				String aml_code_3 = (String) a[39];
				String aml_code_4 = (String) a[40];
				String aml_code_5 = (String) a[41];
				String aml_code_6 = (String) a[42];
				String aml_code_7 = (String) a[43];
				String aml_code_8 = (String) a[44];
				String aml_code_9 = (String) a[45];
				String aml_code_10 = (String) a[46];
				Date relationship_date = (Date) a[47];
				String mis_face_to_face = (String) a[48];
				String mis_non_face_to_face = (String) a[49];
				String mis_internal_rating_grade = (String) a[50];
				String mis_internal_rating_scale = (String) a[51];
				String tran_sub_type = (String) a[52];



				
				T18Detail py = new T18Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_channel, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_type, cust_rating, cust_rating_date, tran_type, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, tran_sub_type);
						
						
						
						
				
						
				

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T18Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T18Detail> t9Dt1Page = new PageImpl<T18Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	public Page<T18Detail> searchT18SingleTran(String rpt_date, String tran_date1,String tran_id1, BigDecimal part_tran_id1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T18Detail> t9Dt1 = new ArrayList<T18Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T18DIST_CHANNELS_DETAILS where report_date=?1 and tran_date=?2 and trim(tran_id)=?3 and trim(part_tran_id)=?4 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		qr.setParameter(3, tran_id1);
		qr.setParameter(4, part_tran_id1);
		

		List<Object[]> result = qr.getResultList();

		try {
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
				String tran_channel = (String) a[11];
				String tran_category = (String) a[12];
				Character qtr_flg = (Character) a[13];
				Character entity_flg = (Character) a[14];
				Character del_flg = (Character) a[15];
				Character modify_flg = (Character) a[16];
				Date entry_date = (Date) a[17];
				Date modify_date = (Date) a[18];
				Date verify_date = (Date) a[19];
				String entry_user = (String) a[20];
				String modify_user = (String) a[21];
				String verify_user = (String) a[22];
				String report_code = (String) a[23];
				String report_name = (String) a[24];
				Date report_date = (Date) a[25];
				Character arch_flg = (Character) a[26];
				String cell_mapping = (String) a[27];
				String process_owner = (String) a[28];
				String bank_id = (String) a[29];
				String cust_type = (String) a[30];
				String cust_rating = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_type = (String) a[33];
				String cntry_res = (String) a[34];
				String cnty_incorp = (String) a[35];
				String cntry_oper = (String) a[36];
				String aml_code_1 = (String) a[37];
				String aml_code_2 = (String) a[38];
				String aml_code_3 = (String) a[39];
				String aml_code_4 = (String) a[40];
				String aml_code_5 = (String) a[41];
				String aml_code_6 = (String) a[42];
				String aml_code_7 = (String) a[43];
				String aml_code_8 = (String) a[44];
				String aml_code_9 = (String) a[45];
				String aml_code_10 = (String) a[46];
				Date relationship_date = (Date) a[47];
				String mis_face_to_face = (String) a[48];
				String mis_non_face_to_face = (String) a[49];
				String mis_internal_rating_grade = (String) a[50];
				String mis_internal_rating_scale = (String) a[51];
				String tran_sub_type = (String) a[52];



				
				T18Detail py = new T18Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_channel, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_type, cust_rating, cust_rating_date, tran_type, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale, tran_sub_type);
						
						
						
						
				
						
				
				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T18Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T18Detail> t9Dt1Page = new PageImpl<T18Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}

}
