package com.bornfire.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.BAML_RBS_PROCEDURE_REP;
import com.bornfire.entity.BAML_RBS_REPORT_PROCEDURE;
import com.bornfire.entity.CMG_MASTER;
import com.bornfire.entity.CMG_MASTER_REPOSITRY;
import com.bornfire.entity.Cust_Aband_Fund_List_Entity;
import com.bornfire.entity.Cust_Aband_Fund_List_Repository;
import com.bornfire.entity.TRAN_MASTER_DETAIL_RBS;
import com.bornfire.entity.t1.T1CurProdDetail;
import com.bornfire.entity.t10.T10Detail;
import com.bornfire.entity.t10.T10Report;
import com.bornfire.entity.t12.T12Detail;
import com.bornfire.entity.t12.T12RODRIGUES;
import com.bornfire.entity.t12.T12RODRIGUESID;
import com.bornfire.entity.t12.T12RODRIGUES_REP;
import com.bornfire.entity.t12.T12Report;
import com.bornfire.entity.t18.T18Detail;
import com.bornfire.entity.t8.T8ModDetail;
import com.monitorjbl.xlsx.StreamingReader;

import au.com.bytecode.opencsv.CSVReader;
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
public class T12ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T12ReportService.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;


	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;
	
	@Autowired
	CMG_MASTER_REPOSITRY cmg_Repository;
	
	@Autowired
	T12RODRIGUES_REP t12rod_rep;
	
	@Autowired
	BAML_RBS_PROCEDURE_REP bAML_RBS_PROCEDURE_REP;
	
	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	Environment env;
	
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

	}
	public ModelAndView getT12View(String reportId, String fromdate, String todate) {

		logger.info("T12ReportService -> getT12View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t12Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T12_IND_CASH_DEP_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			/*try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {

			String d1a_cur_bmur100k = (String) a[0];
			String d2a_cur_mur100_200k = (String) a[1];
			String d3a_cur_mur200_350k = (String) a[2];
			String d4a_cur_mur350_500k = (String) a[3];
			String d5a_cur_mur500_750k = (String) a[4];
			String d6a_cur_mur750_1000k = (String) a[5];
			String d7a_cur_amur1000k = (String) a[6];
			String d8a_cur_total = (String) a[7];
			BigDecimal c1b_cur_bmur100k_nof_dep_tran_hig = (BigDecimal) a[8];
			BigDecimal c2b_cur_mur100_200k_nof_dep_tran_hig = (BigDecimal) a[9];
			BigDecimal c3b_cur_mur200_350k_nof_dep_tran_hig = (BigDecimal) a[10];
			BigDecimal c4b_cur_mur350_500k_nof_dep_tran_hig = (BigDecimal) a[11];
			BigDecimal c5b_cur_mur500_750k_nof_dep_tran_hig = (BigDecimal) a[12];
			BigDecimal c6b_cur_mur750_1000k_nof_dep_tran_hig = (BigDecimal) a[13];
			BigDecimal c7b_cur_amur1000k_nof_dep_tran_hig = (BigDecimal) a[14];
			BigDecimal c8b_cur_total_nof_dep_tran_hig = (BigDecimal) a[15];
			BigDecimal c1c_cur_bmur100k_to_val_tran_hig = (BigDecimal) a[16];
			BigDecimal c2c_cur_mur100_200k_to_val_tran_hig = (BigDecimal) a[17];
			BigDecimal c3c_cur_mur200_350k_to_val_tran_hig = (BigDecimal) a[18];
			BigDecimal c4c_cur_mur350_500k_to_val_tran_hig = (BigDecimal) a[19];
			BigDecimal c5c_cur_mur500_750k_to_val_tran_hig = (BigDecimal) a[20];
			BigDecimal c6c_cur_mur750_1000k_to_val_tran_hig = (BigDecimal) a[21];
			BigDecimal c7c_cur_amur1000k_to_val_tran_hig = (BigDecimal) a[22];
			BigDecimal c8c_cur_total_to_val_tran_hig = (BigDecimal) a[23];
			BigDecimal c1d_cur_bmur100k_nof_dep_tran_med = (BigDecimal) a[24];
			BigDecimal c2d_cur_mur100_200k_nof_dep_tran_med = (BigDecimal) a[25];
			BigDecimal c3d_cur_mur200_350k_nof_dep_tran_med = (BigDecimal) a[26];
			BigDecimal c4d_cur_mur350_500k_nof_dep_tran_med = (BigDecimal) a[27];
			BigDecimal c5d_cur_mur500_750k_nof_dep_tran_med = (BigDecimal) a[28];
			BigDecimal c6d_cur_mur750_1000k_nof_dep_tran_med = (BigDecimal) a[29];
			BigDecimal c7d_cur_amur1000k_nof_dep_tran_med = (BigDecimal) a[30];
			BigDecimal c8d_cur_total_nof_dep_tran_med = (BigDecimal) a[31];
			BigDecimal c1e_cur_bmur100k_to_val_tran_med = (BigDecimal) a[32];
			BigDecimal c2e_cur_mur100_200k_to_val_tran_med = (BigDecimal) a[33];
			BigDecimal c3e_cur_mur200_350k_to_val_tran_med = (BigDecimal) a[34];
			BigDecimal c4e_cur_mur350_500k_to_val_tran_med = (BigDecimal) a[35];
			BigDecimal c5e_cur_mur500_750k_to_val_tran_med = (BigDecimal) a[36];
			BigDecimal c6e_cur_mur750_1000k_to_val_tran_med = (BigDecimal) a[37];
			BigDecimal c7e_cur_amur1000k_to_val_tran_med = (BigDecimal) a[38];
			BigDecimal c8e_cur_total_to_val_tran_med = (BigDecimal) a[39];
			BigDecimal c1f_cur_bmur100k_nof_dep_tran_low = (BigDecimal) a[40];
			BigDecimal c2f_cur_mur100_200k_nof_dep_tran_low = (BigDecimal) a[41];
			BigDecimal c3f_cur_mur200_350k_nof_dep_tran_low = (BigDecimal) a[42];
			BigDecimal c4f_cur_mur350_500k_nof_dep_tran_low = (BigDecimal) a[43];
			BigDecimal c5f_cur_mur500_750k_nof_dep_tran_low = (BigDecimal) a[44];
			BigDecimal c6f_cur_mur750_1000k_nof_dep_tran_low = (BigDecimal) a[45];
			BigDecimal c7f_cur_amur1000k_nof_dep_tran_low = (BigDecimal) a[46];
			BigDecimal c8f_cur_total_nof_dep_tran_low = (BigDecimal) a[47];
			BigDecimal c1g_cur_bmur100k_to_val_tran_low = (BigDecimal) a[48];
			BigDecimal c2g_cur_mur100_200k_to_val_tran_low = (BigDecimal) a[49];
			BigDecimal c3g_cur_mur200_350k_to_val_tran_low = (BigDecimal) a[50];
			BigDecimal c4g_cur_mur350_500k_to_val_tran_low = (BigDecimal) a[51];
			BigDecimal c5g_cur_mur500_750k_to_val_tran_low = (BigDecimal) a[52];
			BigDecimal c6g_cur_mur750_1000k_to_val_tran_low = (BigDecimal) a[53];
			BigDecimal c7g_cur_amur1000k_to_val_tran_low = (BigDecimal) a[54];
			BigDecimal c8g_cur_total_to_val_tran_low = (BigDecimal) a[55];
			String report_code = (String) a[56];
			String report_name = (String) a[57];
			Date report_date = (Date) a[58];
			Date report_due_date = (Date) a[59];
			Date rep_submit_date = (Date) a[60];
			Date rep_period_from = (Date) a[61];
			Date rep_period_to = (Date) a[62];
			String rep_freq = (String) a[63];
			Character nil_report_flg = (Character) a[64];
			Character arch_flg = (Character) a[65];


			T12Report t12Report = new T12Report(d1a_cur_bmur100k, d2a_cur_mur100_200k, d3a_cur_mur200_350k, d4a_cur_mur350_500k, d5a_cur_mur500_750k, 
					d6a_cur_mur750_1000k, d7a_cur_amur1000k, d8a_cur_total, c1b_cur_bmur100k_nof_dep_tran_hig, c2b_cur_mur100_200k_nof_dep_tran_hig, 
					c3b_cur_mur200_350k_nof_dep_tran_hig, c4b_cur_mur350_500k_nof_dep_tran_hig, c5b_cur_mur500_750k_nof_dep_tran_hig, 
					c6b_cur_mur750_1000k_nof_dep_tran_hig, c7b_cur_amur1000k_nof_dep_tran_hig, c8b_cur_total_nof_dep_tran_hig, 
					c1c_cur_bmur100k_to_val_tran_hig, c2c_cur_mur100_200k_to_val_tran_hig, c3c_cur_mur200_350k_to_val_tran_hig, 
					c4c_cur_mur350_500k_to_val_tran_hig, c5c_cur_mur500_750k_to_val_tran_hig, c6c_cur_mur750_1000k_to_val_tran_hig, 
					c7c_cur_amur1000k_to_val_tran_hig, c8c_cur_total_to_val_tran_hig, c1d_cur_bmur100k_nof_dep_tran_med, 
					c2d_cur_mur100_200k_nof_dep_tran_med, c3d_cur_mur200_350k_nof_dep_tran_med, c4d_cur_mur350_500k_nof_dep_tran_med, 
					c5d_cur_mur500_750k_nof_dep_tran_med, c6d_cur_mur750_1000k_nof_dep_tran_med, c7d_cur_amur1000k_nof_dep_tran_med, 
					c8d_cur_total_nof_dep_tran_med, c1e_cur_bmur100k_to_val_tran_med, c2e_cur_mur100_200k_to_val_tran_med, c3e_cur_mur200_350k_to_val_tran_med, 
					c4e_cur_mur350_500k_to_val_tran_med, c5e_cur_mur500_750k_to_val_tran_med, c6e_cur_mur750_1000k_to_val_tran_med, 
					c7e_cur_amur1000k_to_val_tran_med, c8e_cur_total_to_val_tran_med, c1f_cur_bmur100k_nof_dep_tran_low, c2f_cur_mur100_200k_nof_dep_tran_low, 
					c3f_cur_mur200_350k_nof_dep_tran_low, c4f_cur_mur350_500k_nof_dep_tran_low, c5f_cur_mur500_750k_nof_dep_tran_low, 
					c6f_cur_mur750_1000k_nof_dep_tran_low, c7f_cur_amur1000k_nof_dep_tran_low, c8f_cur_total_nof_dep_tran_low, 
					c1g_cur_bmur100k_to_val_tran_low, c2g_cur_mur100_200k_to_val_tran_low, c3g_cur_mur200_350k_to_val_tran_low,
					c4g_cur_mur350_500k_to_val_tran_low, c5g_cur_mur500_750k_to_val_tran_low, c6g_cur_mur750_1000k_to_val_tran_low, 
					c7g_cur_amur1000k_to_val_tran_low, c8g_cur_total_to_val_tran_low, report_code, report_name, report_date, report_due_date,
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);

			t12Rep.add(t12Report);
		}

		mv.setViewName("ReportT12");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t12Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

	public String preCheck(String reportid, String fromdate, String todate) {
		logger.info("t12 report service -> precheck()");
		logger.info("todate -> " + todate);
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			query = hs.createNativeQuery("select count(*) from T12_IND_CASH_DEP_DETILS where report_date = ?1 ")
					.setParameter(1, df1.parse(todate));
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

		return msg;

	}

	public ModelAndView getT12Rep(String reportId, String fromdate, String todate) {

		logger.info("T12ReportService -> getT12Rep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t12Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T12_IND_CASH_DEP_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			String d1a_cur_bmur100k = (String) a[0];
			String d2a_cur_mur100_200k = (String) a[1];
			String d3a_cur_mur200_350k = (String) a[2];
			String d4a_cur_mur350_500k = (String) a[3];
			String d5a_cur_mur500_750k = (String) a[4];
			String d6a_cur_mur750_1000k = (String) a[5];
			String d7a_cur_amur1000k = (String) a[6];
			String d8a_cur_total = (String) a[7];
			BigDecimal c1b_cur_bmur100k_nof_dep_tran_hig = (BigDecimal) a[8];
			BigDecimal c2b_cur_mur100_200k_nof_dep_tran_hig = (BigDecimal) a[9];
			BigDecimal c3b_cur_mur200_350k_nof_dep_tran_hig = (BigDecimal) a[10];
			BigDecimal c4b_cur_mur350_500k_nof_dep_tran_hig = (BigDecimal) a[11];
			BigDecimal c5b_cur_mur500_750k_nof_dep_tran_hig = (BigDecimal) a[12];
			BigDecimal c6b_cur_mur750_1000k_nof_dep_tran_hig = (BigDecimal) a[13];
			BigDecimal c7b_cur_amur1000k_nof_dep_tran_hig = (BigDecimal) a[14];
			BigDecimal c8b_cur_total_nof_dep_tran_hig = (BigDecimal) a[15];
			BigDecimal c1c_cur_bmur100k_to_val_tran_hig = (BigDecimal) a[16];
			BigDecimal c2c_cur_mur100_200k_to_val_tran_hig = (BigDecimal) a[17];
			BigDecimal c3c_cur_mur200_350k_to_val_tran_hig = (BigDecimal) a[18];
			BigDecimal c4c_cur_mur350_500k_to_val_tran_hig = (BigDecimal) a[19];
			BigDecimal c5c_cur_mur500_750k_to_val_tran_hig = (BigDecimal) a[20];
			BigDecimal c6c_cur_mur750_1000k_to_val_tran_hig = (BigDecimal) a[21];
			BigDecimal c7c_cur_amur1000k_to_val_tran_hig = (BigDecimal) a[22];
			BigDecimal c8c_cur_total_to_val_tran_hig = (BigDecimal) a[23];
			BigDecimal c1d_cur_bmur100k_nof_dep_tran_med = (BigDecimal) a[24];
			BigDecimal c2d_cur_mur100_200k_nof_dep_tran_med = (BigDecimal) a[25];
			BigDecimal c3d_cur_mur200_350k_nof_dep_tran_med = (BigDecimal) a[26];
			BigDecimal c4d_cur_mur350_500k_nof_dep_tran_med = (BigDecimal) a[27];
			BigDecimal c5d_cur_mur500_750k_nof_dep_tran_med = (BigDecimal) a[28];
			BigDecimal c6d_cur_mur750_1000k_nof_dep_tran_med = (BigDecimal) a[29];
			BigDecimal c7d_cur_amur1000k_nof_dep_tran_med = (BigDecimal) a[30];
			BigDecimal c8d_cur_total_nof_dep_tran_med = (BigDecimal) a[31];
			BigDecimal c1e_cur_bmur100k_to_val_tran_med = (BigDecimal) a[32];
			BigDecimal c2e_cur_mur100_200k_to_val_tran_med = (BigDecimal) a[33];
			BigDecimal c3e_cur_mur200_350k_to_val_tran_med = (BigDecimal) a[34];
			BigDecimal c4e_cur_mur350_500k_to_val_tran_med = (BigDecimal) a[35];
			BigDecimal c5e_cur_mur500_750k_to_val_tran_med = (BigDecimal) a[36];
			BigDecimal c6e_cur_mur750_1000k_to_val_tran_med = (BigDecimal) a[37];
			BigDecimal c7e_cur_amur1000k_to_val_tran_med = (BigDecimal) a[38];
			BigDecimal c8e_cur_total_to_val_tran_med = (BigDecimal) a[39];
			BigDecimal c1f_cur_bmur100k_nof_dep_tran_low = (BigDecimal) a[40];
			BigDecimal c2f_cur_mur100_200k_nof_dep_tran_low = (BigDecimal) a[41];
			BigDecimal c3f_cur_mur200_350k_nof_dep_tran_low = (BigDecimal) a[42];
			BigDecimal c4f_cur_mur350_500k_nof_dep_tran_low = (BigDecimal) a[43];
			BigDecimal c5f_cur_mur500_750k_nof_dep_tran_low = (BigDecimal) a[44];
			BigDecimal c6f_cur_mur750_1000k_nof_dep_tran_low = (BigDecimal) a[45];
			BigDecimal c7f_cur_amur1000k_nof_dep_tran_low = (BigDecimal) a[46];
			BigDecimal c8f_cur_total_nof_dep_tran_low = (BigDecimal) a[47];
			BigDecimal c1g_cur_bmur100k_to_val_tran_low = (BigDecimal) a[48];
			BigDecimal c2g_cur_mur100_200k_to_val_tran_low = (BigDecimal) a[49];
			BigDecimal c3g_cur_mur200_350k_to_val_tran_low = (BigDecimal) a[50];
			BigDecimal c4g_cur_mur350_500k_to_val_tran_low = (BigDecimal) a[51];
			BigDecimal c5g_cur_mur500_750k_to_val_tran_low = (BigDecimal) a[52];
			BigDecimal c6g_cur_mur750_1000k_to_val_tran_low = (BigDecimal) a[53];
			BigDecimal c7g_cur_amur1000k_to_val_tran_low = (BigDecimal) a[54];
			BigDecimal c8g_cur_total_to_val_tran_low = (BigDecimal) a[55];
			String report_code = (String) a[56];
			String report_name = (String) a[57];
			Date report_date = (Date) a[58];
			Date report_due_date = (Date) a[59];
			Date rep_submit_date = (Date) a[60];
			Date rep_period_from = (Date) a[61];
			Date rep_period_to = (Date) a[62];
			String rep_freq = (String) a[63];
			Character nil_report_flg = (Character) a[64];
			Character arch_flg = (Character) a[65];


			T12Report t12Report = new T12Report(d1a_cur_bmur100k, d2a_cur_mur100_200k, d3a_cur_mur200_350k, d4a_cur_mur350_500k, d5a_cur_mur500_750k, 
					d6a_cur_mur750_1000k, d7a_cur_amur1000k, d8a_cur_total, c1b_cur_bmur100k_nof_dep_tran_hig, c2b_cur_mur100_200k_nof_dep_tran_hig, 
					c3b_cur_mur200_350k_nof_dep_tran_hig, c4b_cur_mur350_500k_nof_dep_tran_hig, c5b_cur_mur500_750k_nof_dep_tran_hig, 
					c6b_cur_mur750_1000k_nof_dep_tran_hig, c7b_cur_amur1000k_nof_dep_tran_hig, c8b_cur_total_nof_dep_tran_hig, 
					c1c_cur_bmur100k_to_val_tran_hig, c2c_cur_mur100_200k_to_val_tran_hig, c3c_cur_mur200_350k_to_val_tran_hig, 
					c4c_cur_mur350_500k_to_val_tran_hig, c5c_cur_mur500_750k_to_val_tran_hig, c6c_cur_mur750_1000k_to_val_tran_hig, 
					c7c_cur_amur1000k_to_val_tran_hig, c8c_cur_total_to_val_tran_hig, c1d_cur_bmur100k_nof_dep_tran_med, 
					c2d_cur_mur100_200k_nof_dep_tran_med, c3d_cur_mur200_350k_nof_dep_tran_med, c4d_cur_mur350_500k_nof_dep_tran_med, 
					c5d_cur_mur500_750k_nof_dep_tran_med, c6d_cur_mur750_1000k_nof_dep_tran_med, c7d_cur_amur1000k_nof_dep_tran_med, 
					c8d_cur_total_nof_dep_tran_med, c1e_cur_bmur100k_to_val_tran_med, c2e_cur_mur100_200k_to_val_tran_med, c3e_cur_mur200_350k_to_val_tran_med, 
					c4e_cur_mur350_500k_to_val_tran_med, c5e_cur_mur500_750k_to_val_tran_med, c6e_cur_mur750_1000k_to_val_tran_med, 
					c7e_cur_amur1000k_to_val_tran_med, c8e_cur_total_to_val_tran_med, c1f_cur_bmur100k_nof_dep_tran_low, c2f_cur_mur100_200k_nof_dep_tran_low, 
					c3f_cur_mur200_350k_nof_dep_tran_low, c4f_cur_mur350_500k_nof_dep_tran_low, c5f_cur_mur500_750k_nof_dep_tran_low, 
					c6f_cur_mur750_1000k_nof_dep_tran_low, c7f_cur_amur1000k_nof_dep_tran_low, c8f_cur_total_nof_dep_tran_low, 
					c1g_cur_bmur100k_to_val_tran_low, c2g_cur_mur100_200k_to_val_tran_low, c3g_cur_mur200_350k_to_val_tran_low,
					c4g_cur_mur350_500k_to_val_tran_low, c5g_cur_mur500_750k_to_val_tran_low, c6g_cur_mur750_1000k_to_val_tran_low, 
					c7g_cur_amur1000k_to_val_tran_low, c8g_cur_total_to_val_tran_low, report_code, report_name, report_date, report_due_date,
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);

			t12Rep.add(t12Report);
		}

		mv.setViewName("ReportT12");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t12Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		logger.info("returning model view");
		return mv;
	}

	public ModelAndView getT12Dtl(String reportId, String fromdate, String todate, String currency,
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
						"select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T12_REPORT =?2");
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
		List<T12Report> T1Master = new ArrayList<T12Report>();

		try {
			T1Master = hs.createQuery("from T12Report a where a.report_date = ?1 ", T12Report.class)
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

		mv.setViewName("ReportT12 :: reportcontent");
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
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
	String strDate1 = formatter1.format(ConDate);
			fileName = "T"+reportId + "_" +strDate1;

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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/Details/NEW_AML_DETAILS/T12Detail.jasper");
					} 

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T12/T12.jasper");
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
/*				File folders = new File(path);
				if (!folders.exists()) {
					folders.mkdirs();
				}
*/
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

	public ModelAndView addDetailRecord(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {
		
		logger.info("T12ReportService -> addDetailRecord()");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ReportT12:: reportcontent");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		mv.addObject("displaymode", "add");
		
		return mv;

	}
	public String processUpload(String asondate, MultipartFile files, String userid)
			throws SQLException, FileNotFoundException, IOException {

		String result = "";

		String status = "";

		MultipartFile uploadedFile = files;

		result = T8Upload(uploadedFile, asondate, userid);

		return result;
	}

	public String T8Upload(MultipartFile file, String asondate, String userid)
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
		
		
		
		logger.info("truncating table: T8-MOD-TABLE");

		

		logger.info("T8-MOD-TABLE truncated");

		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			logger.info("reading values from Excel");

			String cellval = "";

			try (InputStream is = new FileInputStream(convertedFile);

					Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is)) {

				for (Sheet s : workbook) {

					int sheetNumber = workbook.getSheetIndex(s);
					if (sheetNumber == 0) {

						logger.info("inside workbook");

						for (Row r : s) {

							ArrayList<String> resultList = new ArrayList<>();
							if (r.getRowNum() == 0) {
								continue;
							}

							cellval = "";
							String val = null;
							for (int j = 0; j < 29; j++) {
								Cell cell = r.getCell(j);
								if (cell == null || cell.getStringCellValue().length() == 0) {
									val = null;
								} else {
									val = cell.getStringCellValue();
								}
								resultList.add(val);
							}
							

								String sn = resultList.get(0);
								String tran_date = resultList.get(1);
								String cust_name = resultList.get(2);
								String cif = resultList.get(3);
								String nid = resultList.get(4);
								String purpose_of_tran = resultList.get(5);
								String tran_amount = resultList.get(6);
								String risk_category = resultList.get(7);
								String type_of_tran = resultList.get(8);
								
								String report_date = resultList.get(9);
								String report_name ="RODRIGUES";
								logger.info("inside workbook tran_date" + tran_date );
								
								Date report_date1 = null;
								try {
									if (asondate != null && !asondate.isEmpty()) {
										report_date1 = new SimpleDateFormat("dd/MM/yyyy").parse(asondate);
										logger.info("inside workbook report_date11" + report_date1 );

									}
								} catch (Exception e) {
									logger.info("inside workbook3" + e.getMessage());
									try {
										if (asondate != null && !asondate.isEmpty()) {
											report_date1 = new SimpleDateFormat("dd-MM-yyyy")
													.parse(report_date);
										}
									} catch (Exception e1) {
										try {
											if (asondate != null && !asondate.isEmpty()) {
												report_date1 = new SimpleDateFormat("dd-MMM-yyyy")
														.parse(report_date);
											}
										} catch (Exception e111) {
											
											Errormsg = "failed " + "Please fix the REPORT_DATE of cif_id -" + cif
													+ " Fromat to  (dd/MM/yy)";
											continue;

										}

									}

								}
								logger.info("inside workbook report_date1" + report_date1 );

								Date tran_date1 = null;
								try {
									if (report_date != null && !tran_date.isEmpty()) {
										tran_date1 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
										logger.info("inside workbook tran_date11" + tran_date1 );

									}
								} catch (Exception e) {
									logger.info("inside workbook3" + e.getMessage());
									try {
										if (report_date != null && !tran_date.isEmpty()) {
											tran_date1 = new SimpleDateFormat("dd-MM-yyyy")
													.parse(tran_date);
										}
									} catch (Exception e1) {
										try {
											if (report_date != null && !tran_date.isEmpty()) {
												tran_date1 = new SimpleDateFormat("dd-MMM-yyyy")
														.parse(tran_date);
											}
										} catch (Exception e111) {
											
											Errormsg = "failed " + "Please fix the TRAN_DATE of cif_id -" + cif
													+ " Fromat to  (dd/MM/yyyy)";
											continue;

										}

									}

								}
	logger.info("inside workbook tran_date12" + tran_date1 );
							    
								BigDecimal sn1= null;
								if(sn!=null && !sn.isEmpty()) {
									sn1 = new BigDecimal(sn.replaceAll("[a-zA-Z,]", "").trim());
								}
								  
								BigDecimal tran_amount1= null;
								if(tran_amount!=null && !tran_amount.isEmpty()) {
									System.out.println(tran_amount+"tran_amount");
									tran_amount1 = new BigDecimal(tran_amount.replaceAll("[a-zA-Z,]", "").trim());
								}
							   
								String risk_rating = null;
								//T12RODRIGUESID t12id = new T12RODRIGUESID(sn1,report_date1);
								DecimalFormat numformate = new DecimalFormat("0");
								BigDecimal billNumber = (BigDecimal) theSession.createNativeQuery("SELECT RODRIGUES_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
							
								T12RODRIGUES sup1000ManualS1 = new T12RODRIGUES(billNumber,report_date1,tran_date1,cust_name, cif,purpose_of_tran,tran_amount1,risk_category,
										 type_of_tran,  nid,risk_rating,report_name);
		CMG_MASTER CMG = cmg_Repository.findByIdnic(nid);

								if(cmg_Repository.findByIdnic(nid) != null) {
									sup1000ManualS1.setRisk_rating(CMG.getLocaletext());
								
									if(sup1000ManualS1.getCif() ==null) {
										sup1000ManualS1.setCif(CMG.getCif_id());
								}
								} else {
									sup1000ManualS1.setRisk_rating(risk_category);
									sup1000ManualS1.setCif(cif);
								}
									logger.info("inside cmg repositry");
									
									theSession.save(sup1000ManualS1);
									
									 AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL(); 
										BigDecimal Number = (BigDecimal) theSession.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
												.getSingleResult();
										String modi = "RECORD UPLOADED";
										audit.setAudit_date(new Date());
										audit.setEntry_user(userid);
										audit.setFunc_code("RECORD UPLAODED");
										audit.setRemarks("ADDED");
										audit.setAudit_table("TABLE-12 RODRIGUES");
										audit.setAudit_screen("RBS FILE UPLOAD");
										audit.setEvent_id(sup1000ManualS1.getSn().toString());
										audit.setEvent_name(sup1000ManualS1.getCif()+"-"+"ADDED");
										audit.setModi_details(modi);
										audit.setEntry_user(userid);
										audit.setEntry_time(new Date());
										audit.setAudit_ref_no(Number.toString());
										auditLocal.save(audit);
										
						//	theSession.flush();
						//	theSession.clear();
						}

					}

					
				}
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T12RD");
					BAML_RBS_REPORT_PROCEDURE up = account.get();
					up.setReport_flag('Y');
					Date dt1;
					dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(asondate);
					up.setReport_date(dt1);
					bAML_RBS_PROCEDURE_REP.save(up);
				
				status = "File Successfully Uploaded";
			}
			
			
			
			catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}
		}

		return status;

	}

	
	public String processUploadTEST(String reportdateas,MultipartFile file, String userid)
			throws SQLException, FileNotFoundException, IOException {
		Session hs = sessionFactory.getCurrentSession();
		String fileName = file.getOriginalFilename();
		File convertedFile = multipartToFile(file, fileName);

		String fileExt = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		logger.info("fileExt: " + fileExt);

		String Errormsg = "";

		String status = "";

//		logger.info("truncating table: Negative List");

		Session theSession = sessionFactory.getCurrentSession();
		
//		theSession.createSQLQuery(" truncate table NEGATIVE_LIST ").executeUpdate();

//		logger.info("NEGATIVE_LIST truncated");

		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {
			logger.info("reading values from Excel");

			String cellval = "";

			try (InputStream is = new FileInputStream(convertedFile);

					Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is)) {

				for (Sheet s : workbook) {

					logger.info("inside workbook");

					for (Row r : s) {
						ArrayList<String> resultList = new ArrayList<>();
						if (r.getRowNum() == 0) {

							continue;

						}

						cellval = "";
						String val = null;
						for (int j = 0; j < 28; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);

						}
					

						String sn = resultList.get(0);
						String tran_date = resultList.get(1);
						String cust_name = resultList.get(2);
						String cif = resultList.get(3);
						String nid = resultList.get(4);
						String purpose_of_tran = resultList.get(5);
						String tran_amount = resultList.get(6);
						String risk_category = resultList.get(7);
						String type_of_tran = resultList.get(8);
						
						String report_date = resultList.get(9);
						String report_name ="RODRIGUES";
						logger.info("inside workbook tran_date" + tran_date );
						
						Date report_date1 = null;
						try {
							if (reportdateas != null && !reportdateas.isEmpty()) {
								report_date1 = new SimpleDateFormat("dd/MM/yyyy").parse(report_date);
								logger.info("inside workbook report_date11" + report_date1 );

							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (reportdateas != null && !reportdateas.isEmpty()) {
									report_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(report_date);
								}
							} catch (Exception e1) {
								try {
									if (reportdateas != null && !reportdateas.isEmpty()) {
										report_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(report_date);
									}
								} catch (Exception e111) {
									
									Errormsg = "failed " + "Please fix the REPORT_DATE of cif_id -" + cif
											+ " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}
						logger.info("inside workbook report_date1" + report_date1 );

						Date tran_date1 = null;
						try {
							if (report_date != null && !tran_date.isEmpty()) {
								tran_date1 = new SimpleDateFormat("dd/MM/yyyy").parse(tran_date);
								logger.info("inside workbook tran_date11" + tran_date1 );

							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (report_date != null && !tran_date.isEmpty()) {
									tran_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(tran_date);
								}
							} catch (Exception e1) {
								try {
									if (report_date != null && !tran_date.isEmpty()) {
										tran_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(tran_date);
									}
								} catch (Exception e111) {
									
									Errormsg = "failed " + "Please fix the TRAN_DATE of cif_id -" + cif
											+ " Fromat to  (dd/MM/yyyy)";
									continue;

								}

							}

						}
						
					
						
						logger.info("inside workbook tran_date12" + tran_date1 );
					    
						BigDecimal sn1= null;
						if(sn!=null && !sn.isEmpty()) {
							sn1 = new BigDecimal(sn.replaceAll("[a-zA-Z,]", "").trim());
						}
						  
						BigDecimal tran_amount1= null;
						if(tran_amount!=null && !tran_amount.isEmpty()) {
							System.out.println(tran_amount+"tran_amount");
							tran_amount1 = new BigDecimal(tran_amount.replaceAll("[a-zA-Z,]", "").trim());
						}
					   
						String risk_rating = null;
						DecimalFormat numformate = new DecimalFormat("0");
						BigDecimal SrlNumber = (BigDecimal) theSession.createNativeQuery("SELECT RODRIGUES_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
						
						//T12RODRIGUESID t12id = new T12RODRIGUESID(sn1,report_date1);
						
						CMG_MASTER CMG = cmg_Repository.findByIdnic(nid);

						if(cmg_Repository.findByIdnic(nid) != null) {
							risk_rating = CMG.getLocaletext();
						
							if(cif ==null) {
								cif = CMG.getCif_id();
							}
						} else {
							risk_rating = risk_category;
						
							
						}logger.info("inside cmg repositry");
							

						T12RODRIGUES sup1000ManualS1 = new T12RODRIGUES(SrlNumber,report_date1,tran_date1,cust_name, cif,purpose_of_tran,tran_amount1,risk_category,
								 type_of_tran,  nid,risk_rating,report_name);	
							
						t12rod_rep.save(sup1000ManualS1);
							logger.info("outside cmg repositry");
							
							 AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL(); 
							BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
									.getSingleResult();
							String modi = "RECORD ADDED";
							audit.setAudit_date(new Date());
							audit.setEntry_user(userid);
							audit.setFunc_code("RECORD CREATED");
							audit.setRemarks("ADDED");
							audit.setAudit_table("CUST_ABAND_FUND_LIST");
							audit.setAudit_screen("ABAND_FUND LISTING MASTER");
							audit.setEvent_id(userid);
							audit.setEvent_name(sup1000ManualS1.getCif()+"-"+"ADDED");
							audit.setModi_details(modi);
							audit.setEntry_user(userid);
							audit.setEntry_time(new Date());
							audit.setAudit_ref_no(Number.toString());
							auditLocal.save(audit);
							
							logger.info("audit cmg repositry");
							
							status = "successfully uploaded";
//							theSession.flush();
//							theSession.clear();
							StoredProcedureQuery query1 = theSession.createStoredProcedureQuery("t12_rodrigues_detail_upload_sp")
									.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
							query1.setParameter("REPORT_DATE", reportdateas);
							query1.execute();
							System.out.println(reportdateas);
							logger.info("report cmg repositry"+reportdateas);
							/*	StoredProcedureQuery query2 = theSession.createStoredProcedureQuery("T1_CUR_PROD_SERVICES_SP")
									.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
							query2.setParameter("REPORT_DATE", reportdateas);
							query2.execute();
							
							StoredProcedureQuery query3 = theSession.createStoredProcedureQuery("T3_A_FACE_FACE_MASTER")
									.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
							query3.setParameter("REPORT_DATE", reportdateas);
							query3.execute();
							
							StoredProcedureQuery query4 = theSession.createStoredProcedureQuery("T8_TRAN_CUST_RPT")
									.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
							query4.setParameter("REPORT_DATE", reportdateas);
							query4.execute();
							
							StoredProcedureQuery query5 = theSession.createStoredProcedureQuery("T12_IND_CASH_DEP_SP")
									.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
							query5.setParameter("REPORT_DATE", reportdateas);
							query5.execute();
*/				
							//}
					}

					logger.info("inserted values into Abond Fund List");
				}

				
			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}
		}  

		if(!Errormsg.isEmpty()) {
			Errormsg="Successfully uploaded Except for Customer id "+Errormsg;
			return Errormsg;
		}else {
			return status;
		}
		
		
		
	}
	
	public Page<T12Detail> parameterlistwithdecode(String rpt_date,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T12Detail> t9Dt1 = new ArrayList<T12Detail>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T12_IND_CASH_DEP_DETILS where report_date=?1");
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
				Character part_tran_type = (Character) a[7];
				String tran_crncy = (String) a[8];
				BigDecimal tran_amt = (BigDecimal) a[9];
				String tran_particulars = (String) a[10];
				String tran_type = (String) a[11];
				String tran_sub_type = (String) a[12];
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
				String cust_rating = (String) a[27];
				String cell_mapping = (String) a[28];
				String process_owner = (String) a[29];
				String bank_id = (String) a[30];
				String cust_type = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_category = (String) a[33];
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




				
				T12Detail py = new T12Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_type, tran_sub_type, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cust_rating, cell_mapping, process_owner, bank_id, cust_type, cust_rating_date, tran_category, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						
						
						
						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T12Detail> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T12Detail> t9Dt1Page = new PageImpl<T12Detail>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}

	
	public Page<T12Detail> searchT12Both(String rpt_date, String tran_date1, String P_O, String tran_id1,BigDecimal part_tran_id1, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T12Detail> t9Dt1 = new ArrayList<T12Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T12_IND_CASH_DEP_DETILS where report_date=?1 and tran_date=?2 and process_owner=?3 and trim(tran_id)=?4 and trim(part_tran_id)=?5");
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
				Character part_tran_type = (Character) a[7];
				String tran_crncy = (String) a[8];
				BigDecimal tran_amt = (BigDecimal) a[9];
				String tran_particulars = (String) a[10];
				String tran_type = (String) a[11];
				String tran_sub_type = (String) a[12];
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
				String cust_rating = (String) a[27];
				String cell_mapping = (String) a[28];
				String process_owner = (String) a[29];
				String bank_id = (String) a[30];
				String cust_type = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_category = (String) a[33];
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




				
				T12Detail py = new T12Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_type, tran_sub_type, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cust_rating, cell_mapping, process_owner, bank_id, cust_type, cust_rating_date, tran_category, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						
						
						
						
				
						
						

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T12Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T12Detail> t9Dt1Page = new PageImpl<T12Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	public Page<T12Detail> searchT12PO(String rpt_date, String P_O, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T12Detail> t9Dt1 = new ArrayList<T12Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T12_IND_CASH_DEP_DETILS where report_date=?1 and process_owner=?2");
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
				Character part_tran_type = (Character) a[7];
				String tran_crncy = (String) a[8];
				BigDecimal tran_amt = (BigDecimal) a[9];
				String tran_particulars = (String) a[10];
				String tran_type = (String) a[11];
				String tran_sub_type = (String) a[12];
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
				String cust_rating = (String) a[27];
				String cell_mapping = (String) a[28];
				String process_owner = (String) a[29];
				String bank_id = (String) a[30];
				String cust_type = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_category = (String) a[33];
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




				
				T12Detail py = new T12Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_type, tran_sub_type, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cust_rating, cell_mapping, process_owner, bank_id, cust_type, cust_rating_date, tran_category, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						
						

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T12Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T12Detail> t9Dt1Page = new PageImpl<T12Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	public Page<T12Detail> searchT12Date(String rpt_date, String tran_date1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T12Detail> t9Dt1 = new ArrayList<T12Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T12_IND_CASH_DEP_DETILS where report_date=?1 and tran_date=?2 ");
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
				Character part_tran_type = (Character) a[7];
				String tran_crncy = (String) a[8];
				BigDecimal tran_amt = (BigDecimal) a[9];
				String tran_particulars = (String) a[10];
				String tran_type = (String) a[11];
				String tran_sub_type = (String) a[12];
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
				String cust_rating = (String) a[27];
				String cell_mapping = (String) a[28];
				String process_owner = (String) a[29];
				String bank_id = (String) a[30];
				String cust_type = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_category = (String) a[33];
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




				
				T12Detail py = new T12Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_type, tran_sub_type, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cust_rating, cell_mapping, process_owner, bank_id, cust_type, cust_rating_date, tran_category, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						
						
				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T12Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T12Detail> t9Dt1Page = new PageImpl<T12Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	public Page<T12Detail> searchT12SingleTran(String rpt_date, String tran_date1,String tran_id1, BigDecimal part_tran_id1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T12Detail> t9Dt1 = new ArrayList<T12Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T12_IND_CASH_DEP_DETILS where report_date=?1 and tran_date=?2 and trim(tran_id)=?3 and trim(part_tran_id)=?4 ");
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
				Character part_tran_type = (Character) a[7];
				String tran_crncy = (String) a[8];
				BigDecimal tran_amt = (BigDecimal) a[9];
				String tran_particulars = (String) a[10];
				String tran_type = (String) a[11];
				String tran_sub_type = (String) a[12];
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
				String cust_rating = (String) a[27];
				String cell_mapping = (String) a[28];
				String process_owner = (String) a[29];
				String bank_id = (String) a[30];
				String cust_type = (String) a[31];
				Date cust_rating_date = (Date) a[32];
				String tran_category = (String) a[33];
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




				
				T12Detail py = new T12Detail(cust_id, cust_name, acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, tran_type, tran_sub_type, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cust_rating, cell_mapping, process_owner, bank_id, cust_type, cust_rating_date, tran_category, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						
						

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T12Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T12Detail> t9Dt1Page = new PageImpl<T12Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}

}
