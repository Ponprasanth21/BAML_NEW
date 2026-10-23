package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.bornfire.entity.BAMLTranAlertsMaster;
import com.bornfire.entity.BAMLTranAlertsMasterRepository;

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
public class BAMLTranAlertsMasterService {
	
	@NotNull
	private String exportpath;
	
	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	BAMLTranAlertsMasterRepository bamlTranAlertsMasterRepository;
	
	public BAMLTranAlertsMaster getSrlNo(String id,String trandate,String parttran) {

			System.out.println("getting tran id");
			BAMLTranAlertsMaster up = bamlTranAlertsMasterRepository.findByIdcustom(id);

			return up;
		

	}
	
	public String addPARAMETER(BAMLTranAlertsMaster alertparam, String formmode,String USERID,String Tranid,String Trandate,String ParttranId) throws ParseException {
		// TODO Auto-generated method stub
		
		if (Trandate != null) System.out.println(Trandate.trim());
		if (Tranid != null) System.out.println(Tranid.trim());
		if (ParttranId != null) System.out.println(ParttranId.trim());
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormat1=new SimpleDateFormat("dd-MMM-yyyy");
		Date date1 = null;
		if (Trandate != null && !Trandate.trim().isEmpty()) {
			try {
				date1 = dateFormat.parse(Trandate.trim());
			} catch (Exception e1) {
				try {
					date1 = dateFormat1.parse(Trandate.trim());
				} catch (Exception e2) {
					try {
						date1 = new SimpleDateFormat("dd-MM-yyyy").parse(Trandate.trim());
					} catch (Exception ignored) {}
				}
			}
		}
		
		Optional<BAMLTranAlertsMaster> reg = (Tranid != null && !Tranid.trim().isEmpty()) ? bamlTranAlertsMasterRepository.findByIdcustomResult(Tranid.trim()) : Optional.empty();

		String msg = "";
		if (formmode.equals("edit")) {
			BAMLTranAlertsMaster up = alertparam;
			up.setEntity_flag("N");
			up.setDel_flg("N");
			if (reg.isPresent()) {
				up.setPart_tran_type(reg.get().getPart_tran_type());
				up.setTran_id(reg.get().getTran_id());
				up.setPart_tran_srl_num(reg.get().getPart_tran_srl_num());
				up.setTran_sub_type(reg.get().getTran_sub_type());
				up.setAlert_code(reg.get().getAlert_code());
				up.setValue_date(reg.get().getValue_date());
				up.setTran_type(reg.get().getTran_type());
				up.setTran_crncy_code(reg.get().getTran_crncy_code());
				up.setTran_amt(reg.get().getTran_amt());
			}
			if (date1 != null) {
				up.setTran_date(date1);
			}
			up.setEntry_user_id(USERID);
			up.setEntry_date(new Date());
			bamlTranAlertsMasterRepository.save(up);
			msg = "Edited Successfully";
		} 
		  
		if (formmode.equals("verify")) {
			BAMLTranAlertsMaster up = alertparam;
			if (up.getEntry_user_id() == null || !up.getEntry_user_id().equals(USERID)) {
				up.setEntity_flag("Y");
				up.setDel_flg("N");
				up.setVfd_user_id(USERID);
				up.setVfd_date(new Date());
				if (reg.isPresent()) {
					up.setTran_id(reg.get().getTran_id());
					up.setPart_tran_type(reg.get().getPart_tran_type());
					up.setPart_tran_srl_num(reg.get().getPart_tran_srl_num());
					up.setTran_sub_type(reg.get().getTran_sub_type());
					up.setTran_type(reg.get().getTran_type());
					up.setTran_crncy_code(reg.get().getTran_crncy_code());
					up.setTran_amt(reg.get().getTran_amt());
					up.setAlert_code(reg.get().getAlert_code());
					up.setValue_date(reg.get().getValue_date());
				}
				if (date1 != null) {
					up.setTran_date(date1);
				}
				bamlTranAlertsMasterRepository.save(up);
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

		String path = exportpath;
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
