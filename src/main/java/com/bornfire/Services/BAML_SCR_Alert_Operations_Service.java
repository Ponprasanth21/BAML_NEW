package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.bornfire.entity.BAML_SCR_Alert_Oper_Entity;
import com.bornfire.entity.BAML_SCR_Alert_Oper_Repository;

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
public class BAML_SCR_Alert_Operations_Service {
	
	
	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	BAML_SCR_Alert_Oper_Repository bamlSCR_Alert_OperRepository;
	
	public BAML_SCR_Alert_Oper_Entity getSrlNo(BigDecimal id) {

			System.out.println("getting tran id");
			BAML_SCR_Alert_Oper_Entity up = bamlSCR_Alert_OperRepository.findByIdcustom(id);

			return up;
		

	}
	
	public String addPARAMETER(BAML_SCR_Alert_Oper_Entity alertparam, String formmode,String UserId,BigDecimal id) throws ParseException {
		// TODO Auto-generated method stub
		
		
//		Optional<BAML_SCR_Alert_Oper_Entity> reg = bamlSCR_Alert_OperRepository.findByIdcustomResult(id);

		String msg = "";
		/* try { */
		if (formmode.equals("edit")) {
			System.out.println("edit");

			BAML_SCR_Alert_Oper_Entity up = alertparam;
			up.setENTITY_FLAG("N");
			up.setDEL_FLG("N");
			
			System.out.println("aaaaaaaaa"+up.getAML_TRAN_REF_NO());
			// reg.get().getAlerttransaction();
			// System.out.println(up.getAlerttransaction().toString());

			up.setENTRY_USER_ID(UserId);

			bamlSCR_Alert_OperRepository.save(up);
			msg = "Edited Successfully";
		} 
		  
		if (formmode.equals("verify")) {

			BAML_SCR_Alert_Oper_Entity up = alertparam;
			if (up.getENTRY_USER_ID() !=null && !(up.getENTRY_USER_ID()).equals(UserId)) {
				up.setENTITY_FLAG("N");
				up.setDEL_FLG("N");
				up.setVFD_USER_ID(UserId);

				bamlSCR_Alert_OperRepository.save(up);
				msg = "Parameter Verified Successfully";
			} else {
				msg = "Same User cannot Verify!";
			}
		}	  
		return msg;
	}
	
	public File getFile(String filetype)
			throws FileNotFoundException, JRException, SQLException, ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		

		fileName = "MonitoringReport";


			try {
				File jasperFile;
				jasperFile = ResourceUtils.getFile("classpath:static/jasper/DCG0100/DCG0100_DTL.jasper");

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				File folders = new File(path);
				if (!folders.exists()) {
					folders.mkdirs();
				}

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path = path + "/" + fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					
				} else {
					fileName = fileName + ".xlsx";
					path = path + "/" + fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JRXlsxExporter exporter = new JRXlsxExporter();
					exporter.setExporterInput(new SimpleExporterInput(jp));
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
					exporter.exportReport();
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		 
			outputFile = new File(path);
	

		return outputFile;

	}
	
	

}
