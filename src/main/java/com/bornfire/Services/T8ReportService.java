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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Id;
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
import com.bornfire.entity.t20.T20Report;
import com.bornfire.entity.t3a.T3AReport;
import com.bornfire.entity.t5.T5Detail;
import com.bornfire.entity.t8.T8Detail;
import com.bornfire.entity.t8.T8ModDetail;
import com.bornfire.entity.t8.T8ModRep;
import com.bornfire.entity.t8.T8Report;
import com.bornfire.entity.t8.T8ReportMod;
import com.bornfire.entity.t8.T8Repositry;
import com.bornfire.entity.t8.T8SumRep;

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
public class T8ReportService {

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
	
	
//		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
//		multipart.transferTo(convFile);
//		return convFile;
	}

	private static final Logger logger = LoggerFactory.getLogger(T8ReportService.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	T8Repositry t8Repository;

	@Autowired
	T8ModRep t8ModRep;

	@Autowired
	T8SumRep t8SumRep;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public ModelAndView getT8View(String reportId, String fromdate, String todate) {

		logger.info("T8ReportService -> getT8View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t8Rep = new ArrayList<Object>();
		List<Object> t8RepMod = new ArrayList<Object>();
		Query<Object[]> qr;
		Query<Object[]> qr2;

		qr = hs.createNativeQuery("select * from T8_TRAN_CUST_TYPE_TABLE where REPORT_DATE = ?1");
		qr2 = hs.createNativeQuery("select * from T8_TRAN_CUST_TYPE_MOD_TABLE WHERE REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
			qr2.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();
		List<Object[]> results2 = qr2.getResultList();

		for (Object[] a : results) {
			String d1a_cash_dep = (String) a[0];
			String d2a_cash_wdl = (String) a[1];
			String d3a_dom_inw_rem = (String) a[2];
			String d4a_dom_out_rem = (String) a[3];
			String d5a_chq_inw_tran = (String) a[4];
			String d6a_chq_out_tran = (String) a[5];
			String d7a_aotr_dr_tran = (String) a[6];
			String d8a_aotr_cr_tran = (String) a[7];
			String d9a_aotr_inw_tran = (String) a[8];
			String d10a_aotr_out_tran = (String) a[9];
			String d11a_tot_tran = (String) a[10];
			String d12a_val = (String) a[11];
			BigDecimal p1b_cash_dep_not_low = (BigDecimal) a[12];
			BigDecimal p2b_cash_wdl_not_low = (BigDecimal) a[13];
			BigDecimal p3b_bom_inw_rem_not_low = (BigDecimal) a[14];
			BigDecimal p4b_bom_out_rem_not_low = (BigDecimal) a[15];
			BigDecimal p5b_chq_inw_tran_not_low = (BigDecimal) a[16];
			BigDecimal p6b_chq_out_tran_not_low = (BigDecimal) a[17];
			BigDecimal p7b_aotr_dr_tran_not_low = (BigDecimal) a[18];
			BigDecimal p8b_aotr_cr_tran_not_low = (BigDecimal) a[19];
			BigDecimal p9b_aotr_inw_tran_not_low = (BigDecimal) a[20];
			BigDecimal p10b_aotr_out_tran_not_low = (BigDecimal) a[21];
			BigDecimal p11b_tot_tran_not_low = (BigDecimal) a[22];
			BigDecimal p12b_validation_not_low = (BigDecimal) a[23];
			BigDecimal p1c_cash_dep_tamt_low = (BigDecimal) a[24];
			BigDecimal p2c_cash_wdl_tamt_low = (BigDecimal) a[25];
			BigDecimal p3c_com_inw_rem_tamt_low = (BigDecimal) a[26];
			BigDecimal p4c_com_out_rem_tamt_low = (BigDecimal) a[27];
			BigDecimal p5c_chq_inw_tran_tamt_low = (BigDecimal) a[28];
			BigDecimal p6c_chq_out_tran_tamt_low = (BigDecimal) a[29];
			BigDecimal p7c_aotr_dr_tran_tamt_low = (BigDecimal) a[30];
			BigDecimal p8c_aotr_cr_tran_tamt_low = (BigDecimal) a[31];
			BigDecimal p9c_aotr_inw_tran_tamt_low = (BigDecimal) a[32];
			BigDecimal p10c_aotr_out_tran_tamt_low = (BigDecimal) a[33];
			BigDecimal p11c_tot_tran_tamt_low = (BigDecimal) a[34];
			BigDecimal p12c_validation_tamt_low = (BigDecimal) a[35];
			BigDecimal p1d_cash_dep_not_med = (BigDecimal) a[36];
			BigDecimal p2d_cash_wdl_not_med = (BigDecimal) a[37];
			BigDecimal p3d_bom_inw_rem_not_med = (BigDecimal) a[38];
			BigDecimal p4d_bom_out_rem_not_med = (BigDecimal) a[39];
			BigDecimal p5d_chq_inw_tran_not_med = (BigDecimal) a[40];
			BigDecimal p6d_chq_out_tran_not_med = (BigDecimal) a[41];
			BigDecimal p7d_aotr_dr_tran_not_med = (BigDecimal) a[42];
			BigDecimal p8d_aotr_cr_tran_not_med = (BigDecimal) a[43];
			BigDecimal p9d_aotr_inw_tran_not_med = (BigDecimal) a[44];
			BigDecimal p10d_aotr_out_tran_not_med = (BigDecimal) a[45];
			BigDecimal p11d_tot_tran_not_med = (BigDecimal) a[46];
			BigDecimal p12d_validation_not_med = (BigDecimal) a[47];
			BigDecimal p1e_cash_dep_tamt_med = (BigDecimal) a[48];
			BigDecimal p2e_cash_wdl_tamt_med = (BigDecimal) a[49];
			BigDecimal p3e_com_inw_rem_tamt_med = (BigDecimal) a[50];
			BigDecimal p4e_com_out_rem_tamt_med = (BigDecimal) a[51];
			BigDecimal p5e_chq_inw_tran_tamt_med = (BigDecimal) a[52];
			BigDecimal p6e_chq_out_tran_tamt_med = (BigDecimal) a[53];
			BigDecimal p7e_aotr_dr_tran_tamt_med = (BigDecimal) a[54];
			BigDecimal p8e_aotr_cr_tran_tamt_med = (BigDecimal) a[55];
			BigDecimal p9e_aotr_inw_tran_tamt_med = (BigDecimal) a[56];
			BigDecimal p10e_aotr_out_tran_tamt_med = (BigDecimal) a[57];
			BigDecimal p11e_tot_tran_tamt_med = (BigDecimal) a[58];
			BigDecimal p12e_validation_tamt_med = (BigDecimal) a[59];
			BigDecimal p1f_cash_dep_not_hig = (BigDecimal) a[60];
			BigDecimal p2f_cash_wdl_not_hig = (BigDecimal) a[61];
			BigDecimal p3f_bom_inw_rem_not_hig = (BigDecimal) a[62];
			BigDecimal p4f_bom_out_rem_not_hig = (BigDecimal) a[63];
			BigDecimal p5f_chq_inw_tran_not_hig = (BigDecimal) a[64];
			BigDecimal p6f_chq_out_tran_not_hig = (BigDecimal) a[65];
			BigDecimal p7f_aotr_dr_tran_not_hig = (BigDecimal) a[66];
			BigDecimal p8f_aotr_cr_tran_not_hig = (BigDecimal) a[67];
			BigDecimal p9f_aotr_inw_tran_not_hig = (BigDecimal) a[68];
			BigDecimal p10f_aotr_out_tran_not_hig = (BigDecimal) a[69];
			BigDecimal p11f_tot_tran_not_hig = (BigDecimal) a[70];
			BigDecimal p12f_validation_not_hig = (BigDecimal) a[71];
			BigDecimal p1g_cash_dep_tamt_hig = (BigDecimal) a[72];
			BigDecimal p2g_cash_wdl_tamt_hig = (BigDecimal) a[73];
			BigDecimal p3g_com_inw_rem_tamt_hig = (BigDecimal) a[74];
			BigDecimal p4g_com_out_rem_tamt_hig = (BigDecimal) a[75];
			BigDecimal p5g_chq_inw_tran_tamt_hig = (BigDecimal) a[76];
			BigDecimal p6g_chq_out_tran_tamt_hig = (BigDecimal) a[77];
			BigDecimal p7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[78];
			BigDecimal p8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[79];
			BigDecimal p9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[80];
			BigDecimal p10g_aotr_out_tran_tamt_hig = (BigDecimal) a[81];
			BigDecimal p11g_tot_tran_tamt_hig = (BigDecimal) a[82];
			BigDecimal p12g_validation_tamt_hig = (BigDecimal) a[83];
			BigDecimal c1b_cash_dep_not_low = (BigDecimal) a[84];
			BigDecimal c2b_cash_wdl_not_low = (BigDecimal) a[85];
			BigDecimal c3b_bom_inw_rem_not_low = (BigDecimal) a[86];
			BigDecimal c4b_bom_out_rem_not_low = (BigDecimal) a[87];
			BigDecimal c5b_chq_inw_tran_not_low = (BigDecimal) a[88];
			BigDecimal c6b_chq_out_tran_not_low = (BigDecimal) a[89];
			BigDecimal c7b_aotr_dr_tran_not_low = (BigDecimal) a[90];
			BigDecimal c8b_aotr_cr_tran_not_low = (BigDecimal) a[91];
			BigDecimal c9b_aotr_inw_tran_not_low = (BigDecimal) a[92];
			BigDecimal c10b_aotr_out_tran_not_low = (BigDecimal) a[93];
			BigDecimal c11b_tot_tran_not_low = (BigDecimal) a[94];
			BigDecimal c12b_validation_not_low = (BigDecimal) a[95];
			BigDecimal c1c_cash_dep_tamt_low = (BigDecimal) a[96];
			BigDecimal c2c_cash_wdl_tamt_low = (BigDecimal) a[97];
			BigDecimal c3c_com_inw_rem_tamt_low = (BigDecimal) a[98];
			BigDecimal c4c_com_out_rem_tamt_low = (BigDecimal) a[99];
			BigDecimal c5c_chq_inw_tran_tamt_low = (BigDecimal) a[100];
			BigDecimal c6c_chq_out_tran_tamt_low = (BigDecimal) a[101];
			BigDecimal c7c_aotr_dr_tran_tamt_low = (BigDecimal) a[102];
			BigDecimal c8c_aotr_cr_tran_tamt_low = (BigDecimal) a[103];
			BigDecimal c9c_aotr_inw_tran_tamt_low = (BigDecimal) a[104];
			BigDecimal c10c_aotr_out_tran_tamt_low = (BigDecimal) a[105];
			BigDecimal c11c_tot_tran_tamt_low = (BigDecimal) a[106];
			BigDecimal c12c_validation_tamt_low = (BigDecimal) a[107];
			BigDecimal c1d_cash_dep_not_med = (BigDecimal) a[108];
			BigDecimal c2d_cash_wdl_not_med = (BigDecimal) a[109];
			BigDecimal c3d_bom_inw_rem_not_med = (BigDecimal) a[110];
			BigDecimal c4d_bom_out_rem_not_med = (BigDecimal) a[111];
			BigDecimal c5d_chq_inw_tran_not_med = (BigDecimal) a[112];
			BigDecimal c6d_chq_out_tran_not_med = (BigDecimal) a[113];
			BigDecimal c7d_aotr_dr_tran_not_med = (BigDecimal) a[114];
			BigDecimal c8d_aotr_cr_tran_not_med = (BigDecimal) a[115];
			BigDecimal c9d_aotr_inw_tran_not_med = (BigDecimal) a[116];
			BigDecimal c10d_aotr_out_tran_not_med = (BigDecimal) a[117];
			BigDecimal c11d_tot_tran_not_med = (BigDecimal) a[118];
			BigDecimal c12d_validation_not_med = (BigDecimal) a[119];
			BigDecimal c1e_cash_dep_tamt_med = (BigDecimal) a[120];
			BigDecimal c2e_cash_wdl_tamt_med = (BigDecimal) a[121];
			BigDecimal c3e_com_inw_rem_tamt_med = (BigDecimal) a[122];
			BigDecimal c4e_com_out_rem_tamt_med = (BigDecimal) a[123];
			BigDecimal c5e_chq_inw_tran_tamt_med = (BigDecimal) a[124];
			BigDecimal c6e_chq_out_tran_tamt_med = (BigDecimal) a[125];
			BigDecimal c7e_aotr_dr_tran_tamt_med = (BigDecimal) a[126];
			BigDecimal c8e_aotr_cr_tran_tamt_med = (BigDecimal) a[127];
			BigDecimal c9e_aotr_inw_tran_tamt_med = (BigDecimal) a[128];
			BigDecimal c10e_aotr_out_tran_tamt_med = (BigDecimal) a[129];
			BigDecimal c11e_tot_tran_tamt_med = (BigDecimal) a[130];
			BigDecimal c12e_validation_tamt_med = (BigDecimal) a[131];
			BigDecimal c1f_cash_dep_not_hig = (BigDecimal) a[132];
			BigDecimal c2f_cash_wdl_not_hig = (BigDecimal) a[133];
			BigDecimal c3f_bom_inw_rem_not_hig = (BigDecimal) a[134];
			BigDecimal c4f_bom_out_rem_not_hig = (BigDecimal) a[135];
			BigDecimal c5f_chq_inw_tran_not_hig = (BigDecimal) a[136];
			BigDecimal c6f_chq_out_tran_not_hig = (BigDecimal) a[137];
			BigDecimal c7f_aotr_dr_tran_not_hig = (BigDecimal) a[138];
			BigDecimal c8f_aotr_cr_tran_not_hig = (BigDecimal) a[139];
			BigDecimal c9f_aotr_inw_tran_not_hig = (BigDecimal) a[140];
			BigDecimal c10f_aotr_out_tran_not_hig = (BigDecimal) a[141];
			BigDecimal c11f_tot_tran_not_hig = (BigDecimal) a[142];
			BigDecimal c12f_validation_not_hig = (BigDecimal) a[143];
			BigDecimal c1g_cash_dep_tamt_hig = (BigDecimal) a[144];
			BigDecimal c2g_cash_wdl_tamt_hig = (BigDecimal) a[145];
			BigDecimal c3g_com_inw_rem_tamt_hig = (BigDecimal) a[146];
			BigDecimal c4g_com_out_rem_tamt_hig = (BigDecimal) a[147];
			BigDecimal c5g_chq_inw_tran_tamt_hig = (BigDecimal) a[148];
			BigDecimal c6g_chq_out_tran_tamt_hig = (BigDecimal) a[149];
			BigDecimal c7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[150];
			BigDecimal c8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[151];
			BigDecimal c9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[152];
			BigDecimal c10g_aotr_out_tran_tamt_hig = (BigDecimal) a[153];
			BigDecimal c11g_tot_tran_tamt_hig = (BigDecimal) a[154];
			BigDecimal c12g_validation_tamt_hig = (BigDecimal) a[155];
			String report_code = (String) a[156];
			String report_name = (String) a[157];
			Date report_date = (Date) a[158];
			Date report_due_date = (Date) a[159];
			Date rep_submit_date = (Date) a[160];
			Date rep_period_from = (Date) a[161];
			Date rep_period_to = (Date) a[162];
			String rep_freq = (String) a[163];
			String nil_report_flg = (String) a[164];
			String arch_flg = (String) a[165];

			T8Report t8Report = new T8Report(d1a_cash_dep, d2a_cash_wdl, d3a_dom_inw_rem, d4a_dom_out_rem,
					d5a_chq_inw_tran, d6a_chq_out_tran, d7a_aotr_dr_tran, d8a_aotr_cr_tran, d9a_aotr_inw_tran,
					d10a_aotr_out_tran, d11a_tot_tran, d12a_val, p1b_cash_dep_not_low, p2b_cash_wdl_not_low,
					p3b_bom_inw_rem_not_low, p4b_bom_out_rem_not_low, p5b_chq_inw_tran_not_low,
					p6b_chq_out_tran_not_low, p7b_aotr_dr_tran_not_low, p8b_aotr_cr_tran_not_low,
					p9b_aotr_inw_tran_not_low, p10b_aotr_out_tran_not_low, p11b_tot_tran_not_low,
					p12b_validation_not_low, p1c_cash_dep_tamt_low, p2c_cash_wdl_tamt_low, p3c_com_inw_rem_tamt_low,
					p4c_com_out_rem_tamt_low, p5c_chq_inw_tran_tamt_low, p6c_chq_out_tran_tamt_low,
					p7c_aotr_dr_tran_tamt_low, p8c_aotr_cr_tran_tamt_low, p9c_aotr_inw_tran_tamt_low,
					p10c_aotr_out_tran_tamt_low, p11c_tot_tran_tamt_low, p12c_validation_tamt_low, p1d_cash_dep_not_med,
					p2d_cash_wdl_not_med, p3d_bom_inw_rem_not_med, p4d_bom_out_rem_not_med, p5d_chq_inw_tran_not_med,
					p6d_chq_out_tran_not_med, p7d_aotr_dr_tran_not_med, p8d_aotr_cr_tran_not_med,
					p9d_aotr_inw_tran_not_med, p10d_aotr_out_tran_not_med, p11d_tot_tran_not_med,
					p12d_validation_not_med, p1e_cash_dep_tamt_med, p2e_cash_wdl_tamt_med, p3e_com_inw_rem_tamt_med,
					p4e_com_out_rem_tamt_med, p5e_chq_inw_tran_tamt_med, p6e_chq_out_tran_tamt_med,
					p7e_aotr_dr_tran_tamt_med, p8e_aotr_cr_tran_tamt_med, p9e_aotr_inw_tran_tamt_med,
					p10e_aotr_out_tran_tamt_med, p11e_tot_tran_tamt_med, p12e_validation_tamt_med, p1f_cash_dep_not_hig,
					p2f_cash_wdl_not_hig, p3f_bom_inw_rem_not_hig, p4f_bom_out_rem_not_hig, p5f_chq_inw_tran_not_hig,
					p6f_chq_out_tran_not_hig, p7f_aotr_dr_tran_not_hig, p8f_aotr_cr_tran_not_hig,
					p9f_aotr_inw_tran_not_hig, p10f_aotr_out_tran_not_hig, p11f_tot_tran_not_hig,
					p12f_validation_not_hig, p1g_cash_dep_tamt_hig, p2g_cash_wdl_tamt_hig, p3g_com_inw_rem_tamt_hig,
					p4g_com_out_rem_tamt_hig, p5g_chq_inw_tran_tamt_hig, p6g_chq_out_tran_tamt_hig,
					p7g_aotr_dr_tran_tamt_hig, p8g_aotr_cr_tran_tamt_hig, p9g_aotr_inw_tran_tamt_hig,
					p10g_aotr_out_tran_tamt_hig, p11g_tot_tran_tamt_hig, p12g_validation_tamt_hig, c1b_cash_dep_not_low,
					c2b_cash_wdl_not_low, c3b_bom_inw_rem_not_low, c4b_bom_out_rem_not_low, c5b_chq_inw_tran_not_low,
					c6b_chq_out_tran_not_low, c7b_aotr_dr_tran_not_low, c8b_aotr_cr_tran_not_low,
					c9b_aotr_inw_tran_not_low, c10b_aotr_out_tran_not_low, c11b_tot_tran_not_low,
					c12b_validation_not_low, c1c_cash_dep_tamt_low, c2c_cash_wdl_tamt_low, c3c_com_inw_rem_tamt_low,
					c4c_com_out_rem_tamt_low, c5c_chq_inw_tran_tamt_low, c6c_chq_out_tran_tamt_low,
					c7c_aotr_dr_tran_tamt_low, c8c_aotr_cr_tran_tamt_low, c9c_aotr_inw_tran_tamt_low,
					c10c_aotr_out_tran_tamt_low, c11c_tot_tran_tamt_low, c12c_validation_tamt_low, c1d_cash_dep_not_med,
					c2d_cash_wdl_not_med, c3d_bom_inw_rem_not_med, c4d_bom_out_rem_not_med, c5d_chq_inw_tran_not_med,
					c6d_chq_out_tran_not_med, c7d_aotr_dr_tran_not_med, c8d_aotr_cr_tran_not_med,
					c9d_aotr_inw_tran_not_med, c10d_aotr_out_tran_not_med, c11d_tot_tran_not_med,
					c12d_validation_not_med, c1e_cash_dep_tamt_med, c2e_cash_wdl_tamt_med, c3e_com_inw_rem_tamt_med,
					c4e_com_out_rem_tamt_med, c5e_chq_inw_tran_tamt_med, c6e_chq_out_tran_tamt_med,
					c7e_aotr_dr_tran_tamt_med, c8e_aotr_cr_tran_tamt_med, c9e_aotr_inw_tran_tamt_med,
					c10e_aotr_out_tran_tamt_med, c11e_tot_tran_tamt_med, c12e_validation_tamt_med, c1f_cash_dep_not_hig,
					c2f_cash_wdl_not_hig, c3f_bom_inw_rem_not_hig, c4f_bom_out_rem_not_hig, c5f_chq_inw_tran_not_hig,
					c6f_chq_out_tran_not_hig, c7f_aotr_dr_tran_not_hig, c8f_aotr_cr_tran_not_hig,
					c9f_aotr_inw_tran_not_hig, c10f_aotr_out_tran_not_hig, c11f_tot_tran_not_hig,
					c12f_validation_not_hig, c1g_cash_dep_tamt_hig, c2g_cash_wdl_tamt_hig, c3g_com_inw_rem_tamt_hig,
					c4g_com_out_rem_tamt_hig, c5g_chq_inw_tran_tamt_hig, c6g_chq_out_tran_tamt_hig,
					c7g_aotr_dr_tran_tamt_hig, c8g_aotr_cr_tran_tamt_hig, c9g_aotr_inw_tran_tamt_hig,
					c10g_aotr_out_tran_tamt_hig, c11g_tot_tran_tamt_hig, c12g_validation_tamt_hig, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg);

			t8Rep.add(t8Report);
		}
		for (Object[] a : results2) {
			String d1a_cash_dep = (String) a[0];
			String d2a_cash_wdl = (String) a[1];
			String d3a_dom_inw_rem = (String) a[2];
			String d4a_dom_out_rem = (String) a[3];
			String d5a_chq_inw_tran = (String) a[4];
			String d6a_chq_out_tran = (String) a[5];
			String d7a_aotr_dr_tran = (String) a[6];
			String d8a_aotr_cr_tran = (String) a[7];
			String d9a_aotr_inw_tran = (String) a[8];
			String d10a_aotr_out_tran = (String) a[9];
			String d11a_tot_tran = (String) a[10];
			String d12a_val = (String) a[11];
			BigDecimal p1b_cash_dep_not_low = (BigDecimal) a[12];
			BigDecimal p2b_cash_wdl_not_low = (BigDecimal) a[13];
			BigDecimal p3b_bom_inw_rem_not_low = (BigDecimal) a[14];
			BigDecimal p4b_bom_out_rem_not_low = (BigDecimal) a[15];
			BigDecimal p5b_chq_inw_tran_not_low = (BigDecimal) a[16];
			BigDecimal p6b_chq_out_tran_not_low = (BigDecimal) a[17];
			BigDecimal p7b_aotr_dr_tran_not_low = (BigDecimal) a[18];
			BigDecimal p8b_aotr_cr_tran_not_low = (BigDecimal) a[19];
			BigDecimal p9b_aotr_inw_tran_not_low = (BigDecimal) a[20];
			BigDecimal p10b_aotr_out_tran_not_low = (BigDecimal) a[21];
			BigDecimal p11b_tot_tran_not_low = (BigDecimal) a[22];
			BigDecimal p12b_validation_not_low = (BigDecimal) a[23];
			BigDecimal p1c_cash_dep_tamt_low = (BigDecimal) a[24];
			BigDecimal p2c_cash_wdl_tamt_low = (BigDecimal) a[25];
			BigDecimal p3c_com_inw_rem_tamt_low = (BigDecimal) a[26];
			BigDecimal p4c_com_out_rem_tamt_low = (BigDecimal) a[27];
			BigDecimal p5c_chq_inw_tran_tamt_low = (BigDecimal) a[28];
			BigDecimal p6c_chq_out_tran_tamt_low = (BigDecimal) a[29];
			BigDecimal p7c_aotr_dr_tran_tamt_low = (BigDecimal) a[30];
			BigDecimal p8c_aotr_cr_tran_tamt_low = (BigDecimal) a[31];
			BigDecimal p9c_aotr_inw_tran_tamt_low = (BigDecimal) a[32];
			BigDecimal p10c_aotr_out_tran_tamt_low = (BigDecimal) a[33];
			BigDecimal p11c_tot_tran_tamt_low = (BigDecimal) a[34];
			BigDecimal p12c_validation_tamt_low = (BigDecimal) a[35];
			BigDecimal p1d_cash_dep_not_med = (BigDecimal) a[36];
			BigDecimal p2d_cash_wdl_not_med = (BigDecimal) a[37];
			BigDecimal p3d_bom_inw_rem_not_med = (BigDecimal) a[38];
			BigDecimal p4d_bom_out_rem_not_med = (BigDecimal) a[39];
			BigDecimal p5d_chq_inw_tran_not_med = (BigDecimal) a[40];
			BigDecimal p6d_chq_out_tran_not_med = (BigDecimal) a[41];
			BigDecimal p7d_aotr_dr_tran_not_med = (BigDecimal) a[42];
			BigDecimal p8d_aotr_cr_tran_not_med = (BigDecimal) a[43];
			BigDecimal p9d_aotr_inw_tran_not_med = (BigDecimal) a[44];
			BigDecimal p10d_aotr_out_tran_not_med = (BigDecimal) a[45];
			BigDecimal p11d_tot_tran_not_med = (BigDecimal) a[46];
			BigDecimal p12d_validation_not_med = (BigDecimal) a[47];
			BigDecimal p1e_cash_dep_tamt_med = (BigDecimal) a[48];
			BigDecimal p2e_cash_wdl_tamt_med = (BigDecimal) a[49];
			BigDecimal p3e_com_inw_rem_tamt_med = (BigDecimal) a[50];
			BigDecimal p4e_com_out_rem_tamt_med = (BigDecimal) a[51];
			BigDecimal p5e_chq_inw_tran_tamt_med = (BigDecimal) a[52];
			BigDecimal p6e_chq_out_tran_tamt_med = (BigDecimal) a[53];
			BigDecimal p7e_aotr_dr_tran_tamt_med = (BigDecimal) a[54];
			BigDecimal p8e_aotr_cr_tran_tamt_med = (BigDecimal) a[55];
			BigDecimal p9e_aotr_inw_tran_tamt_med = (BigDecimal) a[56];
			BigDecimal p10e_aotr_out_tran_tamt_med = (BigDecimal) a[57];
			BigDecimal p11e_tot_tran_tamt_med = (BigDecimal) a[58];
			BigDecimal p12e_validation_tamt_med = (BigDecimal) a[59];
			BigDecimal p1f_cash_dep_not_hig = (BigDecimal) a[60];
			BigDecimal p2f_cash_wdl_not_hig = (BigDecimal) a[61];
			BigDecimal p3f_bom_inw_rem_not_hig = (BigDecimal) a[62];
			BigDecimal p4f_bom_out_rem_not_hig = (BigDecimal) a[63];
			BigDecimal p5f_chq_inw_tran_not_hig = (BigDecimal) a[64];
			BigDecimal p6f_chq_out_tran_not_hig = (BigDecimal) a[65];
			BigDecimal p7f_aotr_dr_tran_not_hig = (BigDecimal) a[66];
			BigDecimal p8f_aotr_cr_tran_not_hig = (BigDecimal) a[67];
			BigDecimal p9f_aotr_inw_tran_not_hig = (BigDecimal) a[68];
			BigDecimal p10f_aotr_out_tran_not_hig = (BigDecimal) a[69];
			BigDecimal p11f_tot_tran_not_hig = (BigDecimal) a[70];
			BigDecimal p12f_validation_not_hig = (BigDecimal) a[71];
			BigDecimal p1g_cash_dep_tamt_hig = (BigDecimal) a[72];
			BigDecimal p2g_cash_wdl_tamt_hig = (BigDecimal) a[73];
			BigDecimal p3g_com_inw_rem_tamt_hig = (BigDecimal) a[74];
			BigDecimal p4g_com_out_rem_tamt_hig = (BigDecimal) a[75];
			BigDecimal p5g_chq_inw_tran_tamt_hig = (BigDecimal) a[76];
			BigDecimal p6g_chq_out_tran_tamt_hig = (BigDecimal) a[77];
			BigDecimal p7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[78];
			BigDecimal p8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[79];
			BigDecimal p9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[80];
			BigDecimal p10g_aotr_out_tran_tamt_hig = (BigDecimal) a[81];
			BigDecimal p11g_tot_tran_tamt_hig = (BigDecimal) a[82];
			BigDecimal p12g_validation_tamt_hig = (BigDecimal) a[83];
			BigDecimal c1b_cash_dep_not_low = (BigDecimal) a[84];
			BigDecimal c2b_cash_wdl_not_low = (BigDecimal) a[85];
			BigDecimal c3b_bom_inw_rem_not_low = (BigDecimal) a[86];
			BigDecimal c4b_bom_out_rem_not_low = (BigDecimal) a[87];
			BigDecimal c5b_chq_inw_tran_not_low = (BigDecimal) a[88];
			BigDecimal c6b_chq_out_tran_not_low = (BigDecimal) a[89];
			BigDecimal c7b_aotr_dr_tran_not_low = (BigDecimal) a[90];
			BigDecimal c8b_aotr_cr_tran_not_low = (BigDecimal) a[91];
			BigDecimal c9b_aotr_inw_tran_not_low = (BigDecimal) a[92];
			BigDecimal c10b_aotr_out_tran_not_low = (BigDecimal) a[93];
			BigDecimal c11b_tot_tran_not_low = (BigDecimal) a[94];
			BigDecimal c12b_validation_not_low = (BigDecimal) a[95];
			BigDecimal c1c_cash_dep_tamt_low = (BigDecimal) a[96];
			BigDecimal c2c_cash_wdl_tamt_low = (BigDecimal) a[97];
			BigDecimal c3c_com_inw_rem_tamt_low = (BigDecimal) a[98];
			BigDecimal c4c_com_out_rem_tamt_low = (BigDecimal) a[99];
			BigDecimal c5c_chq_inw_tran_tamt_low = (BigDecimal) a[100];
			BigDecimal c6c_chq_out_tran_tamt_low = (BigDecimal) a[101];
			BigDecimal c7c_aotr_dr_tran_tamt_low = (BigDecimal) a[102];
			BigDecimal c8c_aotr_cr_tran_tamt_low = (BigDecimal) a[103];
			BigDecimal c9c_aotr_inw_tran_tamt_low = (BigDecimal) a[104];
			BigDecimal c10c_aotr_out_tran_tamt_low = (BigDecimal) a[105];
			BigDecimal c11c_tot_tran_tamt_low = (BigDecimal) a[106];
			BigDecimal c12c_validation_tamt_low = (BigDecimal) a[107];
			BigDecimal c1d_cash_dep_not_med = (BigDecimal) a[108];
			BigDecimal c2d_cash_wdl_not_med = (BigDecimal) a[109];
			BigDecimal c3d_bom_inw_rem_not_med = (BigDecimal) a[110];
			BigDecimal c4d_bom_out_rem_not_med = (BigDecimal) a[111];
			BigDecimal c5d_chq_inw_tran_not_med = (BigDecimal) a[112];
			BigDecimal c6d_chq_out_tran_not_med = (BigDecimal) a[113];
			BigDecimal c7d_aotr_dr_tran_not_med = (BigDecimal) a[114];
			BigDecimal c8d_aotr_cr_tran_not_med = (BigDecimal) a[115];
			BigDecimal c9d_aotr_inw_tran_not_med = (BigDecimal) a[116];
			BigDecimal c10d_aotr_out_tran_not_med = (BigDecimal) a[117];
			BigDecimal c11d_tot_tran_not_med = (BigDecimal) a[118];
			BigDecimal c12d_validation_not_med = (BigDecimal) a[119];
			BigDecimal c1e_cash_dep_tamt_med = (BigDecimal) a[120];
			BigDecimal c2e_cash_wdl_tamt_med = (BigDecimal) a[121];
			BigDecimal c3e_com_inw_rem_tamt_med = (BigDecimal) a[122];
			BigDecimal c4e_com_out_rem_tamt_med = (BigDecimal) a[123];
			BigDecimal c5e_chq_inw_tran_tamt_med = (BigDecimal) a[124];
			BigDecimal c6e_chq_out_tran_tamt_med = (BigDecimal) a[125];
			BigDecimal c7e_aotr_dr_tran_tamt_med = (BigDecimal) a[126];
			BigDecimal c8e_aotr_cr_tran_tamt_med = (BigDecimal) a[127];
			BigDecimal c9e_aotr_inw_tran_tamt_med = (BigDecimal) a[128];
			BigDecimal c10e_aotr_out_tran_tamt_med = (BigDecimal) a[129];
			BigDecimal c11e_tot_tran_tamt_med = (BigDecimal) a[130];
			BigDecimal c12e_validation_tamt_med = (BigDecimal) a[131];
			BigDecimal c1f_cash_dep_not_hig = (BigDecimal) a[132];
			BigDecimal c2f_cash_wdl_not_hig = (BigDecimal) a[133];
			BigDecimal c3f_bom_inw_rem_not_hig = (BigDecimal) a[134];
			BigDecimal c4f_bom_out_rem_not_hig = (BigDecimal) a[135];
			BigDecimal c5f_chq_inw_tran_not_hig = (BigDecimal) a[136];
			BigDecimal c6f_chq_out_tran_not_hig = (BigDecimal) a[137];
			BigDecimal c7f_aotr_dr_tran_not_hig = (BigDecimal) a[138];
			BigDecimal c8f_aotr_cr_tran_not_hig = (BigDecimal) a[139];
			BigDecimal c9f_aotr_inw_tran_not_hig = (BigDecimal) a[140];
			BigDecimal c10f_aotr_out_tran_not_hig = (BigDecimal) a[141];
			BigDecimal c11f_tot_tran_not_hig = (BigDecimal) a[142];
			BigDecimal c12f_validation_not_hig = (BigDecimal) a[143];
			BigDecimal c1g_cash_dep_tamt_hig = (BigDecimal) a[144];
			BigDecimal c2g_cash_wdl_tamt_hig = (BigDecimal) a[145];
			BigDecimal c3g_com_inw_rem_tamt_hig = (BigDecimal) a[146];
			BigDecimal c4g_com_out_rem_tamt_hig = (BigDecimal) a[147];
			BigDecimal c5g_chq_inw_tran_tamt_hig = (BigDecimal) a[148];
			BigDecimal c6g_chq_out_tran_tamt_hig = (BigDecimal) a[149];
			BigDecimal c7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[150];
			BigDecimal c8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[151];
			BigDecimal c9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[152];
			BigDecimal c10g_aotr_out_tran_tamt_hig = (BigDecimal) a[153];
			BigDecimal c11g_tot_tran_tamt_hig = (BigDecimal) a[154];
			BigDecimal c12g_validation_tamt_hig = (BigDecimal) a[155];
			String report_code = (String) a[156];
			String report_name = (String) a[157];
			Date report_date = (Date) a[158];
			Date report_due_date = (Date) a[159];
			Date rep_submit_date = (Date) a[160];
			Date rep_period_from = (Date) a[161];
			Date rep_period_to = (Date) a[162];
			String rep_freq = (String) a[163];
			String nil_report_flg = (String) a[164];
			String arch_flg = (String) a[165];
			Character entity_flg = (Character) a[166];
			Character modify_flg = (Character) a[167];
			Character del_flg = (Character) a[168];
			String entry_user = (String) a[169];
			String modify_user = (String) a[170];
			String verify_user = (String) a[171];
			Date entry_time = (Date) a[172];
			Date modify_time = (Date) a[173];
			Date verify_time = (Date) a[174];

			T8ReportMod t8ReportMod = new T8ReportMod(d1a_cash_dep, d2a_cash_wdl, d3a_dom_inw_rem, d4a_dom_out_rem,
					d5a_chq_inw_tran, d6a_chq_out_tran, d7a_aotr_dr_tran, d8a_aotr_cr_tran, d9a_aotr_inw_tran,
					d10a_aotr_out_tran, d11a_tot_tran, d12a_val, p1b_cash_dep_not_low, p2b_cash_wdl_not_low,
					p3b_bom_inw_rem_not_low, p4b_bom_out_rem_not_low, p5b_chq_inw_tran_not_low,
					p6b_chq_out_tran_not_low, p7b_aotr_dr_tran_not_low, p8b_aotr_cr_tran_not_low,
					p9b_aotr_inw_tran_not_low, p10b_aotr_out_tran_not_low, p11b_tot_tran_not_low,
					p12b_validation_not_low, p1c_cash_dep_tamt_low, p2c_cash_wdl_tamt_low, p3c_com_inw_rem_tamt_low,
					p4c_com_out_rem_tamt_low, p5c_chq_inw_tran_tamt_low, p6c_chq_out_tran_tamt_low,
					p7c_aotr_dr_tran_tamt_low, p8c_aotr_cr_tran_tamt_low, p9c_aotr_inw_tran_tamt_low,
					p10c_aotr_out_tran_tamt_low, p11c_tot_tran_tamt_low, p12c_validation_tamt_low, p1d_cash_dep_not_med,
					p2d_cash_wdl_not_med, p3d_bom_inw_rem_not_med, p4d_bom_out_rem_not_med, p5d_chq_inw_tran_not_med,
					p6d_chq_out_tran_not_med, p7d_aotr_dr_tran_not_med, p8d_aotr_cr_tran_not_med,
					p9d_aotr_inw_tran_not_med, p10d_aotr_out_tran_not_med, p11d_tot_tran_not_med,
					p12d_validation_not_med, p1e_cash_dep_tamt_med, p2e_cash_wdl_tamt_med, p3e_com_inw_rem_tamt_med,
					p4e_com_out_rem_tamt_med, p5e_chq_inw_tran_tamt_med, p6e_chq_out_tran_tamt_med,
					p7e_aotr_dr_tran_tamt_med, p8e_aotr_cr_tran_tamt_med, p9e_aotr_inw_tran_tamt_med,
					p10e_aotr_out_tran_tamt_med, p11e_tot_tran_tamt_med, p12e_validation_tamt_med, p1f_cash_dep_not_hig,
					p2f_cash_wdl_not_hig, p3f_bom_inw_rem_not_hig, p4f_bom_out_rem_not_hig, p5f_chq_inw_tran_not_hig,
					p6f_chq_out_tran_not_hig, p7f_aotr_dr_tran_not_hig, p8f_aotr_cr_tran_not_hig,
					p9f_aotr_inw_tran_not_hig, p10f_aotr_out_tran_not_hig, p11f_tot_tran_not_hig,
					p12f_validation_not_hig, p1g_cash_dep_tamt_hig, p2g_cash_wdl_tamt_hig, p3g_com_inw_rem_tamt_hig,
					p4g_com_out_rem_tamt_hig, p5g_chq_inw_tran_tamt_hig, p6g_chq_out_tran_tamt_hig,
					p7g_aotr_dr_tran_tamt_hig, p8g_aotr_cr_tran_tamt_hig, p9g_aotr_inw_tran_tamt_hig,
					p10g_aotr_out_tran_tamt_hig, p11g_tot_tran_tamt_hig, p12g_validation_tamt_hig, c1b_cash_dep_not_low,
					c2b_cash_wdl_not_low, c3b_bom_inw_rem_not_low, c4b_bom_out_rem_not_low, c5b_chq_inw_tran_not_low,
					c6b_chq_out_tran_not_low, c7b_aotr_dr_tran_not_low, c8b_aotr_cr_tran_not_low,
					c9b_aotr_inw_tran_not_low, c10b_aotr_out_tran_not_low, c11b_tot_tran_not_low,
					c12b_validation_not_low, c1c_cash_dep_tamt_low, c2c_cash_wdl_tamt_low, c3c_com_inw_rem_tamt_low,
					c4c_com_out_rem_tamt_low, c5c_chq_inw_tran_tamt_low, c6c_chq_out_tran_tamt_low,
					c7c_aotr_dr_tran_tamt_low, c8c_aotr_cr_tran_tamt_low, c9c_aotr_inw_tran_tamt_low,
					c10c_aotr_out_tran_tamt_low, c11c_tot_tran_tamt_low, c12c_validation_tamt_low, c1d_cash_dep_not_med,
					c2d_cash_wdl_not_med, c3d_bom_inw_rem_not_med, c4d_bom_out_rem_not_med, c5d_chq_inw_tran_not_med,
					c6d_chq_out_tran_not_med, c7d_aotr_dr_tran_not_med, c8d_aotr_cr_tran_not_med,
					c9d_aotr_inw_tran_not_med, c10d_aotr_out_tran_not_med, c11d_tot_tran_not_med,
					c12d_validation_not_med, c1e_cash_dep_tamt_med, c2e_cash_wdl_tamt_med, c3e_com_inw_rem_tamt_med,
					c4e_com_out_rem_tamt_med, c5e_chq_inw_tran_tamt_med, c6e_chq_out_tran_tamt_med,
					c7e_aotr_dr_tran_tamt_med, c8e_aotr_cr_tran_tamt_med, c9e_aotr_inw_tran_tamt_med,
					c10e_aotr_out_tran_tamt_med, c11e_tot_tran_tamt_med, c12e_validation_tamt_med, c1f_cash_dep_not_hig,
					c2f_cash_wdl_not_hig, c3f_bom_inw_rem_not_hig, c4f_bom_out_rem_not_hig, c5f_chq_inw_tran_not_hig,
					c6f_chq_out_tran_not_hig, c7f_aotr_dr_tran_not_hig, c8f_aotr_cr_tran_not_hig,
					c9f_aotr_inw_tran_not_hig, c10f_aotr_out_tran_not_hig, c11f_tot_tran_not_hig,
					c12f_validation_not_hig, c1g_cash_dep_tamt_hig, c2g_cash_wdl_tamt_hig, c3g_com_inw_rem_tamt_hig,
					c4g_com_out_rem_tamt_hig, c5g_chq_inw_tran_tamt_hig, c6g_chq_out_tran_tamt_hig,
					c7g_aotr_dr_tran_tamt_hig, c8g_aotr_cr_tran_tamt_hig, c9g_aotr_inw_tran_tamt_hig,
					c10g_aotr_out_tran_tamt_hig, c11g_tot_tran_tamt_hig, c12g_validation_tamt_hig, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg, entity_flg, modify_flg, del_flg, entry_user, modify_user,
					verify_user, entry_time, modify_time, verify_time);

			t8RepMod.add(t8ReportMod);

		}
		mv.setViewName("ReportT8");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t8Rep);
		mv.addObject("modsummary", t8RepMod);
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

		query = hs.createNativeQuery("select count(*) from T8_TRAN_CUST_TYPE_DETAIL where report_date = ?1 ");
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

	public ModelAndView getT8Rep(String reportId, String fromdate, String todate) {

		logger.info("T8ReportService -> getT8Rep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t8Rep = new ArrayList<Object>();
		List<Object> t8RepMod = new ArrayList<Object>();
		Query<Object[]> qr;
		Query<Object[]> qr2;

		qr = hs.createNativeQuery("select * from T8_TRAN_CUST_TYPE_TABLE where REPORT_DATE = ?1");
		qr2 = hs.createNativeQuery("select * from T8_TRAN_CUST_TYPE_MOD_TABLE WHERE REPORT_DATE =?1");
		try {
			qr.setParameter(1, df.parse(todate));
			qr2.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();
		List<Object[]> results2 = qr2.getResultList();

		for (Object[] a : results) {
			String d1a_cash_dep = (String) a[0];
			String d2a_cash_wdl = (String) a[1];
			String d3a_dom_inw_rem = (String) a[2];
			String d4a_dom_out_rem = (String) a[3];
			String d5a_chq_inw_tran = (String) a[4];
			String d6a_chq_out_tran = (String) a[5];
			String d7a_aotr_dr_tran = (String) a[6];
			String d8a_aotr_cr_tran = (String) a[7];
			String d9a_aotr_inw_tran = (String) a[8];
			String d10a_aotr_out_tran = (String) a[9];
			String d11a_tot_tran = (String) a[10];
			String d12a_val = (String) a[11];
			BigDecimal p1b_cash_dep_not_low = (BigDecimal) a[12];
			BigDecimal p2b_cash_wdl_not_low = (BigDecimal) a[13];
			BigDecimal p3b_bom_inw_rem_not_low = (BigDecimal) a[14];
			BigDecimal p4b_bom_out_rem_not_low = (BigDecimal) a[15];
			BigDecimal p5b_chq_inw_tran_not_low = (BigDecimal) a[16];
			BigDecimal p6b_chq_out_tran_not_low = (BigDecimal) a[17];
			BigDecimal p7b_aotr_dr_tran_not_low = (BigDecimal) a[18];
			BigDecimal p8b_aotr_cr_tran_not_low = (BigDecimal) a[19];
			BigDecimal p9b_aotr_inw_tran_not_low = (BigDecimal) a[20];
			BigDecimal p10b_aotr_out_tran_not_low = (BigDecimal) a[21];
			BigDecimal p11b_tot_tran_not_low = (BigDecimal) a[22];
			BigDecimal p12b_validation_not_low = (BigDecimal) a[23];
			BigDecimal p1c_cash_dep_tamt_low = (BigDecimal) a[24];
			BigDecimal p2c_cash_wdl_tamt_low = (BigDecimal) a[25];
			BigDecimal p3c_com_inw_rem_tamt_low = (BigDecimal) a[26];
			BigDecimal p4c_com_out_rem_tamt_low = (BigDecimal) a[27];
			BigDecimal p5c_chq_inw_tran_tamt_low = (BigDecimal) a[28];
			BigDecimal p6c_chq_out_tran_tamt_low = (BigDecimal) a[29];
			BigDecimal p7c_aotr_dr_tran_tamt_low = (BigDecimal) a[30];
			BigDecimal p8c_aotr_cr_tran_tamt_low = (BigDecimal) a[31];
			BigDecimal p9c_aotr_inw_tran_tamt_low = (BigDecimal) a[32];
			BigDecimal p10c_aotr_out_tran_tamt_low = (BigDecimal) a[33];
			BigDecimal p11c_tot_tran_tamt_low = (BigDecimal) a[34];
			BigDecimal p12c_validation_tamt_low = (BigDecimal) a[35];
			BigDecimal p1d_cash_dep_not_med = (BigDecimal) a[36];
			BigDecimal p2d_cash_wdl_not_med = (BigDecimal) a[37];
			BigDecimal p3d_bom_inw_rem_not_med = (BigDecimal) a[38];
			BigDecimal p4d_bom_out_rem_not_med = (BigDecimal) a[39];
			BigDecimal p5d_chq_inw_tran_not_med = (BigDecimal) a[40];
			BigDecimal p6d_chq_out_tran_not_med = (BigDecimal) a[41];
			BigDecimal p7d_aotr_dr_tran_not_med = (BigDecimal) a[42];
			BigDecimal p8d_aotr_cr_tran_not_med = (BigDecimal) a[43];
			BigDecimal p9d_aotr_inw_tran_not_med = (BigDecimal) a[44];
			BigDecimal p10d_aotr_out_tran_not_med = (BigDecimal) a[45];
			BigDecimal p11d_tot_tran_not_med = (BigDecimal) a[46];
			BigDecimal p12d_validation_not_med = (BigDecimal) a[47];
			BigDecimal p1e_cash_dep_tamt_med = (BigDecimal) a[48];
			BigDecimal p2e_cash_wdl_tamt_med = (BigDecimal) a[49];
			BigDecimal p3e_com_inw_rem_tamt_med = (BigDecimal) a[50];
			BigDecimal p4e_com_out_rem_tamt_med = (BigDecimal) a[51];
			BigDecimal p5e_chq_inw_tran_tamt_med = (BigDecimal) a[52];
			BigDecimal p6e_chq_out_tran_tamt_med = (BigDecimal) a[53];
			BigDecimal p7e_aotr_dr_tran_tamt_med = (BigDecimal) a[54];
			BigDecimal p8e_aotr_cr_tran_tamt_med = (BigDecimal) a[55];
			BigDecimal p9e_aotr_inw_tran_tamt_med = (BigDecimal) a[56];
			BigDecimal p10e_aotr_out_tran_tamt_med = (BigDecimal) a[57];
			BigDecimal p11e_tot_tran_tamt_med = (BigDecimal) a[58];
			BigDecimal p12e_validation_tamt_med = (BigDecimal) a[59];
			BigDecimal p1f_cash_dep_not_hig = (BigDecimal) a[60];
			BigDecimal p2f_cash_wdl_not_hig = (BigDecimal) a[61];
			BigDecimal p3f_bom_inw_rem_not_hig = (BigDecimal) a[62];
			BigDecimal p4f_bom_out_rem_not_hig = (BigDecimal) a[63];
			BigDecimal p5f_chq_inw_tran_not_hig = (BigDecimal) a[64];
			BigDecimal p6f_chq_out_tran_not_hig = (BigDecimal) a[65];
			BigDecimal p7f_aotr_dr_tran_not_hig = (BigDecimal) a[66];
			BigDecimal p8f_aotr_cr_tran_not_hig = (BigDecimal) a[67];
			BigDecimal p9f_aotr_inw_tran_not_hig = (BigDecimal) a[68];
			BigDecimal p10f_aotr_out_tran_not_hig = (BigDecimal) a[69];
			BigDecimal p11f_tot_tran_not_hig = (BigDecimal) a[70];
			BigDecimal p12f_validation_not_hig = (BigDecimal) a[71];
			BigDecimal p1g_cash_dep_tamt_hig = (BigDecimal) a[72];
			BigDecimal p2g_cash_wdl_tamt_hig = (BigDecimal) a[73];
			BigDecimal p3g_com_inw_rem_tamt_hig = (BigDecimal) a[74];
			BigDecimal p4g_com_out_rem_tamt_hig = (BigDecimal) a[75];
			BigDecimal p5g_chq_inw_tran_tamt_hig = (BigDecimal) a[76];
			BigDecimal p6g_chq_out_tran_tamt_hig = (BigDecimal) a[77];
			BigDecimal p7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[78];
			BigDecimal p8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[79];
			BigDecimal p9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[80];
			BigDecimal p10g_aotr_out_tran_tamt_hig = (BigDecimal) a[81];
			BigDecimal p11g_tot_tran_tamt_hig = (BigDecimal) a[82];
			BigDecimal p12g_validation_tamt_hig = (BigDecimal) a[83];
			BigDecimal c1b_cash_dep_not_low = (BigDecimal) a[84];
			BigDecimal c2b_cash_wdl_not_low = (BigDecimal) a[85];
			BigDecimal c3b_bom_inw_rem_not_low = (BigDecimal) a[86];
			BigDecimal c4b_bom_out_rem_not_low = (BigDecimal) a[87];
			BigDecimal c5b_chq_inw_tran_not_low = (BigDecimal) a[88];
			BigDecimal c6b_chq_out_tran_not_low = (BigDecimal) a[89];
			BigDecimal c7b_aotr_dr_tran_not_low = (BigDecimal) a[90];
			BigDecimal c8b_aotr_cr_tran_not_low = (BigDecimal) a[91];
			BigDecimal c9b_aotr_inw_tran_not_low = (BigDecimal) a[92];
			BigDecimal c10b_aotr_out_tran_not_low = (BigDecimal) a[93];
			BigDecimal c11b_tot_tran_not_low = (BigDecimal) a[94];
			BigDecimal c12b_validation_not_low = (BigDecimal) a[95];
			BigDecimal c1c_cash_dep_tamt_low = (BigDecimal) a[96];
			BigDecimal c2c_cash_wdl_tamt_low = (BigDecimal) a[97];
			BigDecimal c3c_com_inw_rem_tamt_low = (BigDecimal) a[98];
			BigDecimal c4c_com_out_rem_tamt_low = (BigDecimal) a[99];
			BigDecimal c5c_chq_inw_tran_tamt_low = (BigDecimal) a[100];
			BigDecimal c6c_chq_out_tran_tamt_low = (BigDecimal) a[101];
			BigDecimal c7c_aotr_dr_tran_tamt_low = (BigDecimal) a[102];
			BigDecimal c8c_aotr_cr_tran_tamt_low = (BigDecimal) a[103];
			BigDecimal c9c_aotr_inw_tran_tamt_low = (BigDecimal) a[104];
			BigDecimal c10c_aotr_out_tran_tamt_low = (BigDecimal) a[105];
			BigDecimal c11c_tot_tran_tamt_low = (BigDecimal) a[106];
			BigDecimal c12c_validation_tamt_low = (BigDecimal) a[107];
			BigDecimal c1d_cash_dep_not_med = (BigDecimal) a[108];
			BigDecimal c2d_cash_wdl_not_med = (BigDecimal) a[109];
			BigDecimal c3d_bom_inw_rem_not_med = (BigDecimal) a[110];
			BigDecimal c4d_bom_out_rem_not_med = (BigDecimal) a[111];
			BigDecimal c5d_chq_inw_tran_not_med = (BigDecimal) a[112];
			BigDecimal c6d_chq_out_tran_not_med = (BigDecimal) a[113];
			BigDecimal c7d_aotr_dr_tran_not_med = (BigDecimal) a[114];
			BigDecimal c8d_aotr_cr_tran_not_med = (BigDecimal) a[115];
			BigDecimal c9d_aotr_inw_tran_not_med = (BigDecimal) a[116];
			BigDecimal c10d_aotr_out_tran_not_med = (BigDecimal) a[117];
			BigDecimal c11d_tot_tran_not_med = (BigDecimal) a[118];
			BigDecimal c12d_validation_not_med = (BigDecimal) a[119];
			BigDecimal c1e_cash_dep_tamt_med = (BigDecimal) a[120];
			BigDecimal c2e_cash_wdl_tamt_med = (BigDecimal) a[121];
			BigDecimal c3e_com_inw_rem_tamt_med = (BigDecimal) a[122];
			BigDecimal c4e_com_out_rem_tamt_med = (BigDecimal) a[123];
			BigDecimal c5e_chq_inw_tran_tamt_med = (BigDecimal) a[124];
			BigDecimal c6e_chq_out_tran_tamt_med = (BigDecimal) a[125];
			BigDecimal c7e_aotr_dr_tran_tamt_med = (BigDecimal) a[126];
			BigDecimal c8e_aotr_cr_tran_tamt_med = (BigDecimal) a[127];
			BigDecimal c9e_aotr_inw_tran_tamt_med = (BigDecimal) a[128];
			BigDecimal c10e_aotr_out_tran_tamt_med = (BigDecimal) a[129];
			BigDecimal c11e_tot_tran_tamt_med = (BigDecimal) a[130];
			BigDecimal c12e_validation_tamt_med = (BigDecimal) a[131];
			BigDecimal c1f_cash_dep_not_hig = (BigDecimal) a[132];
			BigDecimal c2f_cash_wdl_not_hig = (BigDecimal) a[133];
			BigDecimal c3f_bom_inw_rem_not_hig = (BigDecimal) a[134];
			BigDecimal c4f_bom_out_rem_not_hig = (BigDecimal) a[135];
			BigDecimal c5f_chq_inw_tran_not_hig = (BigDecimal) a[136];
			BigDecimal c6f_chq_out_tran_not_hig = (BigDecimal) a[137];
			BigDecimal c7f_aotr_dr_tran_not_hig = (BigDecimal) a[138];
			BigDecimal c8f_aotr_cr_tran_not_hig = (BigDecimal) a[139];
			BigDecimal c9f_aotr_inw_tran_not_hig = (BigDecimal) a[140];
			BigDecimal c10f_aotr_out_tran_not_hig = (BigDecimal) a[141];
			BigDecimal c11f_tot_tran_not_hig = (BigDecimal) a[142];
			BigDecimal c12f_validation_not_hig = (BigDecimal) a[143];
			BigDecimal c1g_cash_dep_tamt_hig = (BigDecimal) a[144];
			BigDecimal c2g_cash_wdl_tamt_hig = (BigDecimal) a[145];
			BigDecimal c3g_com_inw_rem_tamt_hig = (BigDecimal) a[146];
			BigDecimal c4g_com_out_rem_tamt_hig = (BigDecimal) a[147];
			BigDecimal c5g_chq_inw_tran_tamt_hig = (BigDecimal) a[148];
			BigDecimal c6g_chq_out_tran_tamt_hig = (BigDecimal) a[149];
			BigDecimal c7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[150];
			BigDecimal c8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[151];
			BigDecimal c9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[152];
			BigDecimal c10g_aotr_out_tran_tamt_hig = (BigDecimal) a[153];
			BigDecimal c11g_tot_tran_tamt_hig = (BigDecimal) a[154];
			BigDecimal c12g_validation_tamt_hig = (BigDecimal) a[155];
			String report_code = (String) a[156];
			String report_name = (String) a[157];
			Date report_date = (Date) a[158];
			Date report_due_date = (Date) a[159];
			Date rep_submit_date = (Date) a[160];
			Date rep_period_from = (Date) a[161];
			Date rep_period_to = (Date) a[162];
			String rep_freq = (String) a[163];
			String nil_report_flg = (String) a[164];
			String arch_flg = (String) a[165];

			T8Report t8Report = new T8Report(d1a_cash_dep, d2a_cash_wdl, d3a_dom_inw_rem, d4a_dom_out_rem,
					d5a_chq_inw_tran, d6a_chq_out_tran, d7a_aotr_dr_tran, d8a_aotr_cr_tran, d9a_aotr_inw_tran,
					d10a_aotr_out_tran, d11a_tot_tran, d12a_val, p1b_cash_dep_not_low, p2b_cash_wdl_not_low,
					p3b_bom_inw_rem_not_low, p4b_bom_out_rem_not_low, p5b_chq_inw_tran_not_low,
					p6b_chq_out_tran_not_low, p7b_aotr_dr_tran_not_low, p8b_aotr_cr_tran_not_low,
					p9b_aotr_inw_tran_not_low, p10b_aotr_out_tran_not_low, p11b_tot_tran_not_low,
					p12b_validation_not_low, p1c_cash_dep_tamt_low, p2c_cash_wdl_tamt_low, p3c_com_inw_rem_tamt_low,
					p4c_com_out_rem_tamt_low, p5c_chq_inw_tran_tamt_low, p6c_chq_out_tran_tamt_low,
					p7c_aotr_dr_tran_tamt_low, p8c_aotr_cr_tran_tamt_low, p9c_aotr_inw_tran_tamt_low,
					p10c_aotr_out_tran_tamt_low, p11c_tot_tran_tamt_low, p12c_validation_tamt_low, p1d_cash_dep_not_med,
					p2d_cash_wdl_not_med, p3d_bom_inw_rem_not_med, p4d_bom_out_rem_not_med, p5d_chq_inw_tran_not_med,
					p6d_chq_out_tran_not_med, p7d_aotr_dr_tran_not_med, p8d_aotr_cr_tran_not_med,
					p9d_aotr_inw_tran_not_med, p10d_aotr_out_tran_not_med, p11d_tot_tran_not_med,
					p12d_validation_not_med, p1e_cash_dep_tamt_med, p2e_cash_wdl_tamt_med, p3e_com_inw_rem_tamt_med,
					p4e_com_out_rem_tamt_med, p5e_chq_inw_tran_tamt_med, p6e_chq_out_tran_tamt_med,
					p7e_aotr_dr_tran_tamt_med, p8e_aotr_cr_tran_tamt_med, p9e_aotr_inw_tran_tamt_med,
					p10e_aotr_out_tran_tamt_med, p11e_tot_tran_tamt_med, p12e_validation_tamt_med, p1f_cash_dep_not_hig,
					p2f_cash_wdl_not_hig, p3f_bom_inw_rem_not_hig, p4f_bom_out_rem_not_hig, p5f_chq_inw_tran_not_hig,
					p6f_chq_out_tran_not_hig, p7f_aotr_dr_tran_not_hig, p8f_aotr_cr_tran_not_hig,
					p9f_aotr_inw_tran_not_hig, p10f_aotr_out_tran_not_hig, p11f_tot_tran_not_hig,
					p12f_validation_not_hig, p1g_cash_dep_tamt_hig, p2g_cash_wdl_tamt_hig, p3g_com_inw_rem_tamt_hig,
					p4g_com_out_rem_tamt_hig, p5g_chq_inw_tran_tamt_hig, p6g_chq_out_tran_tamt_hig,
					p7g_aotr_dr_tran_tamt_hig, p8g_aotr_cr_tran_tamt_hig, p9g_aotr_inw_tran_tamt_hig,
					p10g_aotr_out_tran_tamt_hig, p11g_tot_tran_tamt_hig, p12g_validation_tamt_hig, c1b_cash_dep_not_low,
					c2b_cash_wdl_not_low, c3b_bom_inw_rem_not_low, c4b_bom_out_rem_not_low, c5b_chq_inw_tran_not_low,
					c6b_chq_out_tran_not_low, c7b_aotr_dr_tran_not_low, c8b_aotr_cr_tran_not_low,
					c9b_aotr_inw_tran_not_low, c10b_aotr_out_tran_not_low, c11b_tot_tran_not_low,
					c12b_validation_not_low, c1c_cash_dep_tamt_low, c2c_cash_wdl_tamt_low, c3c_com_inw_rem_tamt_low,
					c4c_com_out_rem_tamt_low, c5c_chq_inw_tran_tamt_low, c6c_chq_out_tran_tamt_low,
					c7c_aotr_dr_tran_tamt_low, c8c_aotr_cr_tran_tamt_low, c9c_aotr_inw_tran_tamt_low,
					c10c_aotr_out_tran_tamt_low, c11c_tot_tran_tamt_low, c12c_validation_tamt_low, c1d_cash_dep_not_med,
					c2d_cash_wdl_not_med, c3d_bom_inw_rem_not_med, c4d_bom_out_rem_not_med, c5d_chq_inw_tran_not_med,
					c6d_chq_out_tran_not_med, c7d_aotr_dr_tran_not_med, c8d_aotr_cr_tran_not_med,
					c9d_aotr_inw_tran_not_med, c10d_aotr_out_tran_not_med, c11d_tot_tran_not_med,
					c12d_validation_not_med, c1e_cash_dep_tamt_med, c2e_cash_wdl_tamt_med, c3e_com_inw_rem_tamt_med,
					c4e_com_out_rem_tamt_med, c5e_chq_inw_tran_tamt_med, c6e_chq_out_tran_tamt_med,
					c7e_aotr_dr_tran_tamt_med, c8e_aotr_cr_tran_tamt_med, c9e_aotr_inw_tran_tamt_med,
					c10e_aotr_out_tran_tamt_med, c11e_tot_tran_tamt_med, c12e_validation_tamt_med, c1f_cash_dep_not_hig,
					c2f_cash_wdl_not_hig, c3f_bom_inw_rem_not_hig, c4f_bom_out_rem_not_hig, c5f_chq_inw_tran_not_hig,
					c6f_chq_out_tran_not_hig, c7f_aotr_dr_tran_not_hig, c8f_aotr_cr_tran_not_hig,
					c9f_aotr_inw_tran_not_hig, c10f_aotr_out_tran_not_hig, c11f_tot_tran_not_hig,
					c12f_validation_not_hig, c1g_cash_dep_tamt_hig, c2g_cash_wdl_tamt_hig, c3g_com_inw_rem_tamt_hig,
					c4g_com_out_rem_tamt_hig, c5g_chq_inw_tran_tamt_hig, c6g_chq_out_tran_tamt_hig,
					c7g_aotr_dr_tran_tamt_hig, c8g_aotr_cr_tran_tamt_hig, c9g_aotr_inw_tran_tamt_hig,
					c10g_aotr_out_tran_tamt_hig, c11g_tot_tran_tamt_hig, c12g_validation_tamt_hig, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg);

			t8Rep.add(t8Report);
		}
		for (Object[] a : results2) {
			String d1a_cash_dep = (String) a[0];
			String d2a_cash_wdl = (String) a[1];
			String d3a_dom_inw_rem = (String) a[2];
			String d4a_dom_out_rem = (String) a[3];
			String d5a_chq_inw_tran = (String) a[4];
			String d6a_chq_out_tran = (String) a[5];
			String d7a_aotr_dr_tran = (String) a[6];
			String d8a_aotr_cr_tran = (String) a[7];
			String d9a_aotr_inw_tran = (String) a[8];
			String d10a_aotr_out_tran = (String) a[9];
			String d11a_tot_tran = (String) a[10];
			String d12a_val = (String) a[11];
			BigDecimal p1b_cash_dep_not_low = (BigDecimal) a[12];
			BigDecimal p2b_cash_wdl_not_low = (BigDecimal) a[13];
			BigDecimal p3b_bom_inw_rem_not_low = (BigDecimal) a[14];
			BigDecimal p4b_bom_out_rem_not_low = (BigDecimal) a[15];
			BigDecimal p5b_chq_inw_tran_not_low = (BigDecimal) a[16];
			BigDecimal p6b_chq_out_tran_not_low = (BigDecimal) a[17];
			BigDecimal p7b_aotr_dr_tran_not_low = (BigDecimal) a[18];
			BigDecimal p8b_aotr_cr_tran_not_low = (BigDecimal) a[19];
			BigDecimal p9b_aotr_inw_tran_not_low = (BigDecimal) a[20];
			BigDecimal p10b_aotr_out_tran_not_low = (BigDecimal) a[21];
			BigDecimal p11b_tot_tran_not_low = (BigDecimal) a[22];
			BigDecimal p12b_validation_not_low = (BigDecimal) a[23];
			BigDecimal p1c_cash_dep_tamt_low = (BigDecimal) a[24];
			BigDecimal p2c_cash_wdl_tamt_low = (BigDecimal) a[25];
			BigDecimal p3c_com_inw_rem_tamt_low = (BigDecimal) a[26];
			BigDecimal p4c_com_out_rem_tamt_low = (BigDecimal) a[27];
			BigDecimal p5c_chq_inw_tran_tamt_low = (BigDecimal) a[28];
			BigDecimal p6c_chq_out_tran_tamt_low = (BigDecimal) a[29];
			BigDecimal p7c_aotr_dr_tran_tamt_low = (BigDecimal) a[30];
			BigDecimal p8c_aotr_cr_tran_tamt_low = (BigDecimal) a[31];
			BigDecimal p9c_aotr_inw_tran_tamt_low = (BigDecimal) a[32];
			BigDecimal p10c_aotr_out_tran_tamt_low = (BigDecimal) a[33];
			BigDecimal p11c_tot_tran_tamt_low = (BigDecimal) a[34];
			BigDecimal p12c_validation_tamt_low = (BigDecimal) a[35];
			BigDecimal p1d_cash_dep_not_med = (BigDecimal) a[36];
			BigDecimal p2d_cash_wdl_not_med = (BigDecimal) a[37];
			BigDecimal p3d_bom_inw_rem_not_med = (BigDecimal) a[38];
			BigDecimal p4d_bom_out_rem_not_med = (BigDecimal) a[39];
			BigDecimal p5d_chq_inw_tran_not_med = (BigDecimal) a[40];
			BigDecimal p6d_chq_out_tran_not_med = (BigDecimal) a[41];
			BigDecimal p7d_aotr_dr_tran_not_med = (BigDecimal) a[42];
			BigDecimal p8d_aotr_cr_tran_not_med = (BigDecimal) a[43];
			BigDecimal p9d_aotr_inw_tran_not_med = (BigDecimal) a[44];
			BigDecimal p10d_aotr_out_tran_not_med = (BigDecimal) a[45];
			BigDecimal p11d_tot_tran_not_med = (BigDecimal) a[46];
			BigDecimal p12d_validation_not_med = (BigDecimal) a[47];
			BigDecimal p1e_cash_dep_tamt_med = (BigDecimal) a[48];
			BigDecimal p2e_cash_wdl_tamt_med = (BigDecimal) a[49];
			BigDecimal p3e_com_inw_rem_tamt_med = (BigDecimal) a[50];
			BigDecimal p4e_com_out_rem_tamt_med = (BigDecimal) a[51];
			BigDecimal p5e_chq_inw_tran_tamt_med = (BigDecimal) a[52];
			BigDecimal p6e_chq_out_tran_tamt_med = (BigDecimal) a[53];
			BigDecimal p7e_aotr_dr_tran_tamt_med = (BigDecimal) a[54];
			BigDecimal p8e_aotr_cr_tran_tamt_med = (BigDecimal) a[55];
			BigDecimal p9e_aotr_inw_tran_tamt_med = (BigDecimal) a[56];
			BigDecimal p10e_aotr_out_tran_tamt_med = (BigDecimal) a[57];
			BigDecimal p11e_tot_tran_tamt_med = (BigDecimal) a[58];
			BigDecimal p12e_validation_tamt_med = (BigDecimal) a[59];
			BigDecimal p1f_cash_dep_not_hig = (BigDecimal) a[60];
			BigDecimal p2f_cash_wdl_not_hig = (BigDecimal) a[61];
			BigDecimal p3f_bom_inw_rem_not_hig = (BigDecimal) a[62];
			BigDecimal p4f_bom_out_rem_not_hig = (BigDecimal) a[63];
			BigDecimal p5f_chq_inw_tran_not_hig = (BigDecimal) a[64];
			BigDecimal p6f_chq_out_tran_not_hig = (BigDecimal) a[65];
			BigDecimal p7f_aotr_dr_tran_not_hig = (BigDecimal) a[66];
			BigDecimal p8f_aotr_cr_tran_not_hig = (BigDecimal) a[67];
			BigDecimal p9f_aotr_inw_tran_not_hig = (BigDecimal) a[68];
			BigDecimal p10f_aotr_out_tran_not_hig = (BigDecimal) a[69];
			BigDecimal p11f_tot_tran_not_hig = (BigDecimal) a[70];
			BigDecimal p12f_validation_not_hig = (BigDecimal) a[71];
			BigDecimal p1g_cash_dep_tamt_hig = (BigDecimal) a[72];
			BigDecimal p2g_cash_wdl_tamt_hig = (BigDecimal) a[73];
			BigDecimal p3g_com_inw_rem_tamt_hig = (BigDecimal) a[74];
			BigDecimal p4g_com_out_rem_tamt_hig = (BigDecimal) a[75];
			BigDecimal p5g_chq_inw_tran_tamt_hig = (BigDecimal) a[76];
			BigDecimal p6g_chq_out_tran_tamt_hig = (BigDecimal) a[77];
			BigDecimal p7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[78];
			BigDecimal p8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[79];
			BigDecimal p9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[80];
			BigDecimal p10g_aotr_out_tran_tamt_hig = (BigDecimal) a[81];
			BigDecimal p11g_tot_tran_tamt_hig = (BigDecimal) a[82];
			BigDecimal p12g_validation_tamt_hig = (BigDecimal) a[83];
			BigDecimal c1b_cash_dep_not_low = (BigDecimal) a[84];
			BigDecimal c2b_cash_wdl_not_low = (BigDecimal) a[85];
			BigDecimal c3b_bom_inw_rem_not_low = (BigDecimal) a[86];
			BigDecimal c4b_bom_out_rem_not_low = (BigDecimal) a[87];
			BigDecimal c5b_chq_inw_tran_not_low = (BigDecimal) a[88];
			BigDecimal c6b_chq_out_tran_not_low = (BigDecimal) a[89];
			BigDecimal c7b_aotr_dr_tran_not_low = (BigDecimal) a[90];
			BigDecimal c8b_aotr_cr_tran_not_low = (BigDecimal) a[91];
			BigDecimal c9b_aotr_inw_tran_not_low = (BigDecimal) a[92];
			BigDecimal c10b_aotr_out_tran_not_low = (BigDecimal) a[93];
			BigDecimal c11b_tot_tran_not_low = (BigDecimal) a[94];
			BigDecimal c12b_validation_not_low = (BigDecimal) a[95];
			BigDecimal c1c_cash_dep_tamt_low = (BigDecimal) a[96];
			BigDecimal c2c_cash_wdl_tamt_low = (BigDecimal) a[97];
			BigDecimal c3c_com_inw_rem_tamt_low = (BigDecimal) a[98];
			BigDecimal c4c_com_out_rem_tamt_low = (BigDecimal) a[99];
			BigDecimal c5c_chq_inw_tran_tamt_low = (BigDecimal) a[100];
			BigDecimal c6c_chq_out_tran_tamt_low = (BigDecimal) a[101];
			BigDecimal c7c_aotr_dr_tran_tamt_low = (BigDecimal) a[102];
			BigDecimal c8c_aotr_cr_tran_tamt_low = (BigDecimal) a[103];
			BigDecimal c9c_aotr_inw_tran_tamt_low = (BigDecimal) a[104];
			BigDecimal c10c_aotr_out_tran_tamt_low = (BigDecimal) a[105];
			BigDecimal c11c_tot_tran_tamt_low = (BigDecimal) a[106];
			BigDecimal c12c_validation_tamt_low = (BigDecimal) a[107];
			BigDecimal c1d_cash_dep_not_med = (BigDecimal) a[108];
			BigDecimal c2d_cash_wdl_not_med = (BigDecimal) a[109];
			BigDecimal c3d_bom_inw_rem_not_med = (BigDecimal) a[110];
			BigDecimal c4d_bom_out_rem_not_med = (BigDecimal) a[111];
			BigDecimal c5d_chq_inw_tran_not_med = (BigDecimal) a[112];
			BigDecimal c6d_chq_out_tran_not_med = (BigDecimal) a[113];
			BigDecimal c7d_aotr_dr_tran_not_med = (BigDecimal) a[114];
			BigDecimal c8d_aotr_cr_tran_not_med = (BigDecimal) a[115];
			BigDecimal c9d_aotr_inw_tran_not_med = (BigDecimal) a[116];
			BigDecimal c10d_aotr_out_tran_not_med = (BigDecimal) a[117];
			BigDecimal c11d_tot_tran_not_med = (BigDecimal) a[118];
			BigDecimal c12d_validation_not_med = (BigDecimal) a[119];
			BigDecimal c1e_cash_dep_tamt_med = (BigDecimal) a[120];
			BigDecimal c2e_cash_wdl_tamt_med = (BigDecimal) a[121];
			BigDecimal c3e_com_inw_rem_tamt_med = (BigDecimal) a[122];
			BigDecimal c4e_com_out_rem_tamt_med = (BigDecimal) a[123];
			BigDecimal c5e_chq_inw_tran_tamt_med = (BigDecimal) a[124];
			BigDecimal c6e_chq_out_tran_tamt_med = (BigDecimal) a[125];
			BigDecimal c7e_aotr_dr_tran_tamt_med = (BigDecimal) a[126];
			BigDecimal c8e_aotr_cr_tran_tamt_med = (BigDecimal) a[127];
			BigDecimal c9e_aotr_inw_tran_tamt_med = (BigDecimal) a[128];
			BigDecimal c10e_aotr_out_tran_tamt_med = (BigDecimal) a[129];
			BigDecimal c11e_tot_tran_tamt_med = (BigDecimal) a[130];
			BigDecimal c12e_validation_tamt_med = (BigDecimal) a[131];
			BigDecimal c1f_cash_dep_not_hig = (BigDecimal) a[132];
			BigDecimal c2f_cash_wdl_not_hig = (BigDecimal) a[133];
			BigDecimal c3f_bom_inw_rem_not_hig = (BigDecimal) a[134];
			BigDecimal c4f_bom_out_rem_not_hig = (BigDecimal) a[135];
			BigDecimal c5f_chq_inw_tran_not_hig = (BigDecimal) a[136];
			BigDecimal c6f_chq_out_tran_not_hig = (BigDecimal) a[137];
			BigDecimal c7f_aotr_dr_tran_not_hig = (BigDecimal) a[138];
			BigDecimal c8f_aotr_cr_tran_not_hig = (BigDecimal) a[139];
			BigDecimal c9f_aotr_inw_tran_not_hig = (BigDecimal) a[140];
			BigDecimal c10f_aotr_out_tran_not_hig = (BigDecimal) a[141];
			BigDecimal c11f_tot_tran_not_hig = (BigDecimal) a[142];
			BigDecimal c12f_validation_not_hig = (BigDecimal) a[143];
			BigDecimal c1g_cash_dep_tamt_hig = (BigDecimal) a[144];
			BigDecimal c2g_cash_wdl_tamt_hig = (BigDecimal) a[145];
			BigDecimal c3g_com_inw_rem_tamt_hig = (BigDecimal) a[146];
			BigDecimal c4g_com_out_rem_tamt_hig = (BigDecimal) a[147];
			BigDecimal c5g_chq_inw_tran_tamt_hig = (BigDecimal) a[148];
			BigDecimal c6g_chq_out_tran_tamt_hig = (BigDecimal) a[149];
			BigDecimal c7g_aotr_dr_tran_tamt_hig = (BigDecimal) a[150];
			BigDecimal c8g_aotr_cr_tran_tamt_hig = (BigDecimal) a[151];
			BigDecimal c9g_aotr_inw_tran_tamt_hig = (BigDecimal) a[152];
			BigDecimal c10g_aotr_out_tran_tamt_hig = (BigDecimal) a[153];
			BigDecimal c11g_tot_tran_tamt_hig = (BigDecimal) a[154];
			BigDecimal c12g_validation_tamt_hig = (BigDecimal) a[155];
			String report_code = (String) a[156];
			String report_name = (String) a[157];
			Date report_date = (Date) a[158];
			Date report_due_date = (Date) a[159];
			Date rep_submit_date = (Date) a[160];
			Date rep_period_from = (Date) a[161];
			Date rep_period_to = (Date) a[162];
			String rep_freq = (String) a[163];
			String nil_report_flg = (String) a[164];
			String arch_flg = (String) a[165];
			Character entity_flg = (Character) a[166];
			Character modify_flg = (Character) a[167];
			Character del_flg = (Character) a[168];

			String entry_user = (String) a[169];
			String modify_user = (String) a[170];
			String verify_user = (String) a[171];
			Date entry_time = (Date) a[172];
			Date modify_time = (Date) a[173];
			Date verify_time = (Date) a[174];

			T8ReportMod t8ReportMod = new T8ReportMod(d1a_cash_dep, d2a_cash_wdl, d3a_dom_inw_rem, d4a_dom_out_rem,
					d5a_chq_inw_tran, d6a_chq_out_tran, d7a_aotr_dr_tran, d8a_aotr_cr_tran, d9a_aotr_inw_tran,
					d10a_aotr_out_tran, d11a_tot_tran, d12a_val, p1b_cash_dep_not_low, p2b_cash_wdl_not_low,
					p3b_bom_inw_rem_not_low, p4b_bom_out_rem_not_low, p5b_chq_inw_tran_not_low,
					p6b_chq_out_tran_not_low, p7b_aotr_dr_tran_not_low, p8b_aotr_cr_tran_not_low,
					p9b_aotr_inw_tran_not_low, p10b_aotr_out_tran_not_low, p11b_tot_tran_not_low,
					p12b_validation_not_low, p1c_cash_dep_tamt_low, p2c_cash_wdl_tamt_low, p3c_com_inw_rem_tamt_low,
					p4c_com_out_rem_tamt_low, p5c_chq_inw_tran_tamt_low, p6c_chq_out_tran_tamt_low,
					p7c_aotr_dr_tran_tamt_low, p8c_aotr_cr_tran_tamt_low, p9c_aotr_inw_tran_tamt_low,
					p10c_aotr_out_tran_tamt_low, p11c_tot_tran_tamt_low, p12c_validation_tamt_low, p1d_cash_dep_not_med,
					p2d_cash_wdl_not_med, p3d_bom_inw_rem_not_med, p4d_bom_out_rem_not_med, p5d_chq_inw_tran_not_med,
					p6d_chq_out_tran_not_med, p7d_aotr_dr_tran_not_med, p8d_aotr_cr_tran_not_med,
					p9d_aotr_inw_tran_not_med, p10d_aotr_out_tran_not_med, p11d_tot_tran_not_med,
					p12d_validation_not_med, p1e_cash_dep_tamt_med, p2e_cash_wdl_tamt_med, p3e_com_inw_rem_tamt_med,
					p4e_com_out_rem_tamt_med, p5e_chq_inw_tran_tamt_med, p6e_chq_out_tran_tamt_med,
					p7e_aotr_dr_tran_tamt_med, p8e_aotr_cr_tran_tamt_med, p9e_aotr_inw_tran_tamt_med,
					p10e_aotr_out_tran_tamt_med, p11e_tot_tran_tamt_med, p12e_validation_tamt_med, p1f_cash_dep_not_hig,
					p2f_cash_wdl_not_hig, p3f_bom_inw_rem_not_hig, p4f_bom_out_rem_not_hig, p5f_chq_inw_tran_not_hig,
					p6f_chq_out_tran_not_hig, p7f_aotr_dr_tran_not_hig, p8f_aotr_cr_tran_not_hig,
					p9f_aotr_inw_tran_not_hig, p10f_aotr_out_tran_not_hig, p11f_tot_tran_not_hig,
					p12f_validation_not_hig, p1g_cash_dep_tamt_hig, p2g_cash_wdl_tamt_hig, p3g_com_inw_rem_tamt_hig,
					p4g_com_out_rem_tamt_hig, p5g_chq_inw_tran_tamt_hig, p6g_chq_out_tran_tamt_hig,
					p7g_aotr_dr_tran_tamt_hig, p8g_aotr_cr_tran_tamt_hig, p9g_aotr_inw_tran_tamt_hig,
					p10g_aotr_out_tran_tamt_hig, p11g_tot_tran_tamt_hig, p12g_validation_tamt_hig, c1b_cash_dep_not_low,
					c2b_cash_wdl_not_low, c3b_bom_inw_rem_not_low, c4b_bom_out_rem_not_low, c5b_chq_inw_tran_not_low,
					c6b_chq_out_tran_not_low, c7b_aotr_dr_tran_not_low, c8b_aotr_cr_tran_not_low,
					c9b_aotr_inw_tran_not_low, c10b_aotr_out_tran_not_low, c11b_tot_tran_not_low,
					c12b_validation_not_low, c1c_cash_dep_tamt_low, c2c_cash_wdl_tamt_low, c3c_com_inw_rem_tamt_low,
					c4c_com_out_rem_tamt_low, c5c_chq_inw_tran_tamt_low, c6c_chq_out_tran_tamt_low,
					c7c_aotr_dr_tran_tamt_low, c8c_aotr_cr_tran_tamt_low, c9c_aotr_inw_tran_tamt_low,
					c10c_aotr_out_tran_tamt_low, c11c_tot_tran_tamt_low, c12c_validation_tamt_low, c1d_cash_dep_not_med,
					c2d_cash_wdl_not_med, c3d_bom_inw_rem_not_med, c4d_bom_out_rem_not_med, c5d_chq_inw_tran_not_med,
					c6d_chq_out_tran_not_med, c7d_aotr_dr_tran_not_med, c8d_aotr_cr_tran_not_med,
					c9d_aotr_inw_tran_not_med, c10d_aotr_out_tran_not_med, c11d_tot_tran_not_med,
					c12d_validation_not_med, c1e_cash_dep_tamt_med, c2e_cash_wdl_tamt_med, c3e_com_inw_rem_tamt_med,
					c4e_com_out_rem_tamt_med, c5e_chq_inw_tran_tamt_med, c6e_chq_out_tran_tamt_med,
					c7e_aotr_dr_tran_tamt_med, c8e_aotr_cr_tran_tamt_med, c9e_aotr_inw_tran_tamt_med,
					c10e_aotr_out_tran_tamt_med, c11e_tot_tran_tamt_med, c12e_validation_tamt_med, c1f_cash_dep_not_hig,
					c2f_cash_wdl_not_hig, c3f_bom_inw_rem_not_hig, c4f_bom_out_rem_not_hig, c5f_chq_inw_tran_not_hig,
					c6f_chq_out_tran_not_hig, c7f_aotr_dr_tran_not_hig, c8f_aotr_cr_tran_not_hig,
					c9f_aotr_inw_tran_not_hig, c10f_aotr_out_tran_not_hig, c11f_tot_tran_not_hig,
					c12f_validation_not_hig, c1g_cash_dep_tamt_hig, c2g_cash_wdl_tamt_hig, c3g_com_inw_rem_tamt_hig,
					c4g_com_out_rem_tamt_hig, c5g_chq_inw_tran_tamt_hig, c6g_chq_out_tran_tamt_hig,
					c7g_aotr_dr_tran_tamt_hig, c8g_aotr_cr_tran_tamt_hig, c9g_aotr_inw_tran_tamt_hig,
					c10g_aotr_out_tran_tamt_hig, c11g_tot_tran_tamt_hig, c12g_validation_tamt_hig, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg, entity_flg, modify_flg, del_flg, entry_user, modify_user,
					verify_user, entry_time, modify_time, verify_time);

			t8RepMod.add(t8ReportMod);

		}
		mv.setViewName("ReportT8");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t8Rep);
		mv.addObject("modsummary", t8RepMod);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

			public ModelAndView getT2currentDtl(String reportId, String fromdate, String todate, String currency,
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
								"select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T8_REPORT =?2");
						qr.setParameter(2, filter);
					} else {
						qr = hs.createNativeQuery("select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T8_REPORT  is not null");
					}
				} else {
					qr = hs.createNativeQuery("select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T8_REPORT is not null");
				}
				try {
					qr.setParameter(1, df.parse(todate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<T8Report> T1Master = new ArrayList<T8Report>();

				try {
					T1Master = hs.createQuery("from T8Report a where a.report_date = ?1 ", T8Report.class)
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

				mv.setViewName("ReportT8 :: reportcontent");
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

		String path = "";
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

		zipFileName = fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile = null;
				logger.info("Getting Jasper file :" + reportId + dtltype + filetype);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass()
								.getResourceAsStream("/static/jasper/Details/NEW_AML_DETAILS/T8Detail.jasper");
						
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass()
								.getResourceAsStream("/static/jasper/AmlJasper/T8Copy/T8.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");

			

					InputStream subrep1 = this.getClass().getResourceAsStream("/static/jasper/Details/T8Detail/T8DetailCASH.jasper");
					InputStream subrep2 = this.getClass().getResourceAsStream("/static/jasper/Details/T8Detail/T8DetailLOCALIN.jasper");
					InputStream subrep3 = this.getClass().getResourceAsStream("/static/jasper/Details/T8Detail/T8DetailLOCALOUT.jasper");
					InputStream subrep4 = this.getClass().getResourceAsStream("/static/jasper/Details/T8Detail/T8DetailCHQIN.jasper");
					InputStream subrep5 = this.getClass().getResourceAsStream("/static/jasper/Details/T8Detail/T8DetailCHQOUT.jasper");
					InputStream subrep6 = this.getClass().getResourceAsStream("/static/jasper/Details/T8Detail/T8DetailPOS.jasper");
					InputStream subrep7 = this.getClass().getResourceAsStream("/static/jasper/Details/T8Detail/T8DetailWITHDRAW.jasper");
				
					map.put("T8CASH", subrep1);
					map.put("T8LOCALIN", subrep2);
					map.put("T8LOCALOUT", subrep3);
					map.put("T8CHEQUEIN", subrep4);
					map.put("T8CHEQUEOUT", subrep5);
					map.put("T8POS", subrep6);
					map.put("T8WITHDRAW", subrep7);
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
					path +=  fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					fileName = fileName + ".xlsx";
					path = fileName;
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

	public String editT8(T8ReportMod t8ReportMod) {
		// TODO Auto-generated method stub
		String msg = "";
		/* try { */
		Session session = sessionFactory.getCurrentSession();
		T8ReportMod up = t8ReportMod;
		up.setReport_code("T8");
		// up.setD1a_cash_dep("Cash deposits");
		// up.setD2a_cash_wdl("Cash withdrawals");
		// up.setD3a_dom_inw_rem("Local Bank Transfer(Inward)");
		// up.setD4a_dom_out_rem("Local Bank Transfer(Outward)");
		// up.setD5a_chq_inw_tran("Cheques inward transactions");
		// up.setD6a_chq_out_tran("Cheques outward transactions");
		// up.setD7a_aotr_dr_tran("All other \"transfer\" transactions within the NBDTI
		// (include the debit values) ");
		// up.setD8a_aotr_cr_tran("All other \"transfer\" transactions within the NBDTI
		// (include the credit values)");
		// up.setD9a_aotr_inw_tran("All other inward transactions");
		// up.setD10a_aotr_out_tran("All other outward transactions");
		// up.setD11a_tot_tran("Total Transactions");
		// up.setD12a_val("Validation (Total Transactions) ");
		up.setEntity_flg('N');
		up.setModify_flg('Y');
		up.setDel_flg('N');

		// t8ModRep.save(up);
		session.saveOrUpdate(up);

		msg = "Record Edited Successfully";

		return msg;
	}

	public String verifyT8(T8Report t8Report, T8ReportMod t8ReportMod) {
		// TODO Auto-generated method stub
		String msg = "";
		/* try { */
		Session session = sessionFactory.getCurrentSession();
		T8Report up1 = t8Report;
		T8ReportMod up = t8ReportMod;
		//System.out.println("Modi:" + up.getModify_user());
		//System.out.println("Veri:" + up.getVerify_user());

		if (up.getModify_user().equals(up.getVerify_user())) {
			msg = "Same User Cannot Verify !";
		} else {
			up.setReport_code("T8");
			up1.setReport_code("T8");
			up.setEntity_flg('Y');
			up.setModify_flg('N');
			up.setDel_flg('N');
			session.saveOrUpdate(up1);
			session.saveOrUpdate(up);
			// t8ModRep.save(up);
			// t8SumRep.save(up1);

			msg = "Verified Successfully";
		}
		return msg;
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

		
		theSession.createSQLQuery(" truncate table T8_TRAN_CUST_TYPE_MOD_DETAIL ").executeUpdate();

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

							String cust_id = resultList.get(0);
							String cust_name = resultList.get(1);
							String cust_type = resultList.get(2);
							String cust_rating = resultList.get(3);
							String acct_no = resultList.get(4);
							String acct_name = resultList.get(5);
							String tran_type = resultList.get(6);
							String tran_sub_type = resultList.get(7);

							String date = resultList.get(8);
							Date tran_date = new SimpleDateFormat("dd-MM-yyyy").parse(date);

							String tran_id = resultList.get(9);

							String part_tran_id = resultList.get(10);
							BigDecimal bigDecimalpart_tran_id = new BigDecimal(part_tran_id);

							String part_tran_type = resultList.get(11);
							String tran_crncy = resultList.get(12);

							String tran_amt = resultList.get(13);
							BigDecimal bigDecimaltran_amt = new BigDecimal(part_tran_id);

							String tran_category = resultList.get(14);
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
							String date2 = resultList.get(27);
							Date report_date = new SimpleDateFormat("dd-MM-yyyy").parse(date2);
							Character arch_flg = null;
							if (cust_id == null) {
								break;
							}

							T8ModDetail t8ModDetail = new T8ModDetail(cust_id, cust_name, cust_type, cust_rating, acct_no,
									acct_name, tran_type, tran_sub_type, tran_date, tran_id, bigDecimalpart_tran_id,
									part_tran_type, tran_crncy, bigDecimaltran_amt, tran_category, qtr_flg, entity_flg,
									del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user,
									verify_user, report_code, report_name, report_date, arch_flg);

							logger.info("saving values:");
							theSession.save(t8ModDetail);
							theSession.flush();
							theSession.clear();
						}

					}

					
				}
				
				StoredProcedureQuery query = theSession.createStoredProcedureQuery("T8_MOD_TO_DETAIL_SP");
				query.execute();
				StoredProcedureQuery query1 = theSession.createStoredProcedureQuery("t8_tran_cust_rpt")
						.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
				query1.setParameter("REPORT_DATE", asondate);
				query1.execute();
				status = "File Successfully Uploaded";
			}
			
			
			
			catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}
		}

		return status;

	}
	
	
	
	
	public Page<T8Detail> parameterlistwithdecode(String rpt_date,Pageable pageable) throws ParseException {
		System.out.println("rpt_date"+rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T8Detail> t9Dt1 = new ArrayList<T8Detail>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1");
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



				
				T8Detail py = new T8Detail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particulars, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						
						
						
						
						
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<T8Detail> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<T8Detail> t9Dt1Page = new PageImpl<T8Detail>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}
	
	
	public Page<T8Detail> searchT8Both(String rpt_date, String tran_date1, String P_O, String tran_id1,BigDecimal part_tran_id1, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T8Detail> t9Dt1 = new ArrayList<T8Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1 and tran_date=?2 and process_owner=?3 and trim(tran_id)=?4 and trim(part_tran_id)=?5");
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



				
				T8Detail py = new T8Detail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particulars, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						
						

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T8Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T8Detail> t9Dt1Page = new PageImpl<T8Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Page<T8Detail> searchT8PO(String rpt_date, String P_O, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T8Detail> t9Dt1 = new ArrayList<T8Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1 and process_owner=?2");
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



				
				T8Detail py = new T8Detail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particulars, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						

				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T8Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T8Detail> t9Dt1Page = new PageImpl<T8Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	public Page<T8Detail> searchT8Date(String rpt_date, String tran_date1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T8Detail> t9Dt1 = new ArrayList<T8Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1 and tran_date=?2 ");
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



				
				T8Detail py = new T8Detail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particulars, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						


				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T8Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T8Detail> t9Dt1Page = new PageImpl<T8Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	public Page<T8Detail> searchT8SingleTran(String rpt_date, String tran_date1,String tran_id1, BigDecimal part_tran_id1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T8Detail> t9Dt1 = new ArrayList<T8Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1 and tran_date=?2 and trim(tran_id)=?3 and trim(part_tran_id)=?4 ");
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



				
				T8Detail py = new T8Detail(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping, process_owner, bank_id, cust_rating_date, tran_particulars, tran_channel, cntry_res, cnty_incorp, cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);
						
						


				t9Dt1.add(py);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T8Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T8Detail> t9Dt1Page = new PageImpl<T8Detail>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}

}
