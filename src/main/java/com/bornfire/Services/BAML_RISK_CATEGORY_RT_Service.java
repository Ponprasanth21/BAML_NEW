package com.bornfire.Services;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.BAML_Cust_HNWI_RPT_Entity;
import com.bornfire.entity.BAML_Risk_Category_RPT_Entity;
import com.bornfire.entity.BAML_Risk_Category_RPT_Repository;
import com.bornfire.entity.Cust_Aband_Fund_List_Entity;

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
@ConfigurationProperties("output")
@Transactional
public class BAML_RISK_CATEGORY_RT_Service {

	@Autowired
	BAML_Risk_Category_RPT_Repository baml_risk_category_Repository;
	
	private static final Logger logger = LoggerFactory.getLogger(BAML_RISK_CATEGORY_RT_Service.class);

	@Autowired
	DataSource srcdataSource;
	
	
	
	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	Environment env;
	
	@Autowired
	BAML_Risk_Category_RPT_Repository baml_Risk_Category_RPT_Repository;
	
	

	  private static String[] columns = {"SL", "Customer ID", "Name", "NID","Risk Category","Account No","POB","Membership Date","Department","Type Of Loan","Account Open Date","Amount Applied","Amount Disbursed","Status(N/R"};
	   
	  private static List<BAML_Risk_Category_RPT_Entity> risk_loan_List =  new ArrayList<BAML_Risk_Category_RPT_Entity>();
	  
	  
	  private static String[] columns_RSS = {"SL", "Customer ID", "Name", "NID","Risk Category","Account No","POB","Beneficiary","Department","Account Open Date","Effective Date","Contribution Amount"};
	   
	  private static List<BAML_Risk_Category_RPT_Entity> risk_rss_List =  new ArrayList<BAML_Risk_Category_RPT_Entity>();
	  

	  private static String[] columns_DEP = {"SL", "Customer ID", "Name", "NID","Risk Category","Account No","POB","Occupation","Monthly Income","Customer ID","NAME","NID","Risk Category","A/C No","POB","Occupation","Monthly Income","Application Date","Deposited Amount","New/Re-investment","Payment Mode"};
	   
	  private static List<BAML_Risk_Category_RPT_Entity> risk_dep_List =  new ArrayList<BAML_Risk_Category_RPT_Entity>();
	
	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype,String category) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		String path =  env.getProperty("output.exportpath");
		String fileName = "";
		
		File outputFile;

		logger.info("Getting Output file :" + reportId);

		String msg="";


	
		 if (!filetype.equals("xbrl")) {

			try {
				
				
				InputStream fileStream = null;
				
				 if(reportId.equals("RISK_LOAN")) {
					 fileName = reportId + "_" + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
						logger.info("Getting Jasper file :" + reportId);
						if (filetype.equals("xlsx")) {
						    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLRISKREPORTS/Loan.jasper");
						}else {
						    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLRISKREPORTS/Loan.jasper");
						}
				 }else if(reportId.equals("RISK_RSS")) {
					 fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
						logger.info("Getting Jasper file :" + reportId);
						if (filetype.equals("xlsx")) {
						    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLRISKREPORTS/RSS.jasper");
						}else {
						    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLRISKREPORTS/RSS.jasper");
						}
				 }else if(reportId.equals("RISK_DEP")) {
					 fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
						logger.info("Getting Jasper file :" + reportId);
						if (filetype.equals("xlsx")) {
						    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLRISKREPORTS/Deposit.jasper");
						}else {
						    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLRISKREPORTS/Deposit.jasper");
						}
				 }
			
				 JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
					HashMap<String, Object> map = new HashMap<String, Object>();

					logger.info("Assigning Parameters for Jasper");
					map.put("TODATE", todate);
					map.put("FROMDATE", fromdate);
					map.put("CATEGORY", category);

					logger.info("BEFORE GENERATING PDF :" + reportId);
					if (filetype.equals("pdf")) {
						fileName = fileName + ".pdf";
						path =  fileName;
						logger.info("BEFORE GENERATING PDF 1 :" + reportId);
						JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
						logger.info("BEFORE GENERATING PDF 2 :" + path);
						JasperExportManager.exportReportToPdfFile(jp, path);
						logger.info("PDF File exported");
					} else {
						fileName = fileName + ".xlsx";
						path =   fileName;
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
	public ByteArrayInputStream getFile_RISK_LOAN_Excel(String userid,String reportId, String fromdate, String todate, String dtltype,
			String filetype,String risk_category) throws FileNotFoundException, JRException, SQLException, ParseException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Session hs = sessionFactory.getCurrentSession();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		String fromDAte = null;
		String toDAte = null;

		if (fromdate != null && !fromdate.isEmpty()) {
			Date ConDateFromdate = dateFormat1.parse(fromdate);
			String strDate2 = formatter1.format(ConDateFromdate);
			fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

			Date ConToDate = dateFormat1.parse(todate);
			String strDate1 = formatter1.format(ConToDate);
			toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		}

		logger.info("Getting Output file :" + reportId);

		try {

			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("RISK_LOAN_REPORT_LIST");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLACK.getIndex());

			
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
			headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
			headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
			headerCellStyle.setBorderRight(BorderStyle.MEDIUM);

			Row TitleRow = sheet.createRow(0);
			Cell cellTitle = TitleRow.createCell((short) 0);
			cellTitle.setCellValue("THE MAURITIUS CIVIL SERVICE MUTUAL AID ASSOCIATION LTD");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A1:N2"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellTitle.setCellStyle(headerCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:N2"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:N2"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:N2"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:N2"), sheet);
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
			
			Row ReportNameRow = sheet.createRow(2);
			Cell cellReporName = ReportNameRow.createCell((short) 0);
			cellReporName.setCellValue("Loan Monitoring Report Per Risk Category");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A3:N3"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporName.setCellStyle(headerCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:N3"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:N3"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:N3"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:N3"), sheet);
			
			CellStyle reportDateCellStyle = workbook.createCellStyle();
			reportDateCellStyle.setFont(headerFont);
			
			Row Report_Date_Row = sheet.createRow(3);
			Cell cellReporDate = Report_Date_Row.createCell((short) 0);
			cellReporDate.setCellValue("Start Date- "+fromDAte);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
			cellReporDate.setCellStyle(reportDateCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A4:C4"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A4:C4"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A4:C4"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A4:C4"), sheet);
			
			
			Row Report_Date_Row2 = sheet.createRow(4);
			Cell cellReporDate2 = Report_Date_Row2.createCell((short) 0);
			cellReporDate2.setCellValue("End Date- "+toDAte);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A5:C5"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
			cellReporDate2.setCellStyle(reportDateCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			
			Row Report_Date_Row3 = sheet.createRow(5);
			Cell cellReporDate3 = Report_Date_Row3.createCell((short) 0);
			cellReporDate3.setCellValue("Risk Category- "+risk_category);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A6:C6"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
			cellReporDate3.setCellStyle(reportDateCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A6:C6"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A6:C6"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A6:C6"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A6:C6"), sheet);
			
			Row headerRow = sheet.createRow(7);
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
			
			
			DataFormat fmt = workbook.createDataFormat();
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(fmt.getFormat("@"));
//			cellStyle.setBorderTop(BorderStyle.MEDIUM);
//			cellStyle.setBorderBottom(BorderStyle.MEDIUM);
//			cellStyle.setBorderLeft(BorderStyle.MEDIUM);
//			cellStyle.setBorderRight(BorderStyle.MEDIUM);
			
			DataFormat fmt1 = workbook.createDataFormat();
			CellStyle numStyle = workbook.createCellStyle();
			numStyle.setDataFormat(fmt1.getFormat("###,0.00"));
//			numStyle.setBorderTop(BorderStyle.MEDIUM);
//			numStyle.setBorderBottom(BorderStyle.MEDIUM);
//			numStyle.setBorderLeft(BorderStyle.MEDIUM);
//			numStyle.setBorderRight(BorderStyle.MEDIUM);
			
			
			int rowNum = 7;
			int sn = 1;

			risk_loan_List = baml_Risk_Category_RPT_Repository.getLoanData(fromDAte,toDAte, risk_category);
			for (BAML_Risk_Category_RPT_Entity pep_List : risk_loan_List) {
				Row row = sheet.createRow(++rowNum);
				writeBook(pep_List, row, dateCellStyle,sn,cellStyle,numStyle);
				sn++;
			}

			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			
			workbook.write(out);
			out.close();

			// Closing the workbook
			workbook.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());

	}

		private void writeBook(BAML_Risk_Category_RPT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
				CellStyle dateCell, CellStyle numStyle) {

			Cell cell = row.createCell(0);
			cell.setCellValue(sn);
			cell.setCellStyle(dateCell);

			Cell cif = row.createCell(1);
			cif.setCellValue(aBook.getCIF());
			cif.setCellStyle(dateCell);

			Cell lastname = row.createCell(2);
			lastname.setCellValue(aBook.getNAME());
			lastname.setCellStyle(dateCell);

			Cell firstname = row.createCell(3);
			firstname.setCellValue(aBook.getNID());
			firstname.setCellStyle(dateCell);

			Cell nid = row.createCell(4);
			nid.setCellValue(aBook.getRISK_CATEGORY());
			nid.setCellStyle(dateCell);

			Cell riskcat = row.createCell(5);
			riskcat.setCellValue(aBook.getFORACID());
			riskcat.setCellStyle(dateCell);
			
			Cell type = row.createCell(6);
			type.setCellValue(aBook.getPOB());
			type.setCellStyle(dateCell);
			
			Cell mem_date = row.createCell(7);
			mem_date.setCellValue(aBook.getMEMBERSHIP_DATE());
			mem_date.setCellStyle(dateCellStyle);
			
			Cell dep = row.createCell(8);
			dep.setCellValue(aBook.getDEPARTMENT());
			dep.setCellStyle(dateCell);
			
			Cell type_of_loan = row.createCell(9);
			type_of_loan.setCellValue(aBook.getTYPE_OF_LOAN());
			type_of_loan.setCellStyle(dateCell);
			
			
			Cell acc_opn_dta = row.createCell(10);
			acc_opn_dta.setCellValue(aBook.getACCT_OPN_DATE());
			acc_opn_dta.setCellStyle(dateCellStyle);
			
			
			Cell amtapplied = row.createCell(11);
			if(aBook.getAMT_APPL()!=null) {
				amtapplied.setCellValue(String.format("%,.2f", aBook.getAMT_APPL()));
			}else {
				amtapplied.setCellValue("");
			}
			amtapplied.setCellStyle(dateCell);
			
			Cell amtdisbursed = row.createCell(12);
			if(aBook.getAMT_DIS()!=null) {
				amtdisbursed.setCellValue(String.format("%,.2f", aBook.getAMT_DIS()));
			}else {
				amtdisbursed.setCellValue("");
			}
			amtdisbursed.setCellStyle(dateCell);
			
			Cell cust_pos = row.createCell(13);
			if (aBook.getSTATUS_NEW_REIN()!=null) {
				cust_pos.setCellValue("R-Renewal");
			} else{
				cust_pos.setCellValue("N-New");
			}
			cust_pos.setCellStyle(dateCell);
		}

//***********************************************RSS
		public ByteArrayInputStream getFile_RISK_RSS_Excel(String userid,String reportId, String fromdate, String todate, String dtltype,
				String filetype,String risk_category) throws FileNotFoundException, JRException, SQLException, ParseException {

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			Session hs = sessionFactory.getCurrentSession();
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

			String fromDAte = null;
			String toDAte = null;

			if (fromdate != null && !fromdate.isEmpty()) {
				Date ConDateFromdate = dateFormat1.parse(fromdate);
				String strDate2 = formatter1.format(ConDateFromdate);
				fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

				Date ConToDate = dateFormat1.parse(todate);
				String strDate1 = formatter1.format(ConToDate);
				toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
			}

			logger.info("Getting Output file :" + reportId);

			try {

				Workbook workbook = new XSSFWorkbook();
				CreationHelper createHelper = workbook.getCreationHelper();
				Sheet sheet = workbook.createSheet("RISK_LOAN_REPORT_LIST");

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 14);
				headerFont.setColor(IndexedColors.BLACK.getIndex());

				
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);
				headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
				headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
				headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
				headerCellStyle.setBorderRight(BorderStyle.MEDIUM);

				Row TitleRow = sheet.createRow(0);
				Cell cellTitle = TitleRow.createCell((short) 0);
				cellTitle.setCellValue("THE MAURITIUS CIVIL SERVICE MUTUAL AID ASSOCIATION LTD");
				sheet.addMergedRegion(CellRangeAddress.valueOf("A1:L2"));
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				cellTitle.setCellStyle(headerCellStyle);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:L2"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:L2"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:L2"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:L2"), sheet);
				
				Row ReportNameRow = sheet.createRow(2);
				Cell cellReporName = ReportNameRow.createCell((short) 0);
				cellReporName.setCellValue("Retirement Saving Scheme Report Per Risk Category");
				sheet.addMergedRegion(CellRangeAddress.valueOf("A3:L3"));
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:L3"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:L3"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:L3"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:L3"), sheet);
//				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				cellReporName.setCellStyle(headerCellStyle);
				
				CellStyle reportDateCellStyle = workbook.createCellStyle();
				reportDateCellStyle.setFont(headerFont);
				
				Row Report_Date_Row = sheet.createRow(3);
				Cell cellReporDate = Report_Date_Row.createCell((short) 0);
				cellReporDate.setCellValue("Start Date- "+fromDAte);
				sheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));
				reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
				cellReporDate.setCellStyle(reportDateCellStyle);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A4:C4"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A4:C4"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A4:C4"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A4:C4"), sheet);
				
				
				Row Report_Date_Row2 = sheet.createRow(4);
				Cell cellReporDate2 = Report_Date_Row2.createCell((short) 0);
				cellReporDate2.setCellValue("End Date- "+toDAte);
				sheet.addMergedRegion(CellRangeAddress.valueOf("A5:C5"));
				reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
				cellReporDate2.setCellStyle(reportDateCellStyle);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);
				
				Row Report_Date_Row3 = sheet.createRow(5);
				Cell cellReporDate3 = Report_Date_Row3.createCell((short) 0);
				cellReporDate3.setCellValue("Risk Category- "+risk_category);
				sheet.addMergedRegion(CellRangeAddress.valueOf("A6:C6"));
				reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
				cellReporDate3.setCellStyle(reportDateCellStyle);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A6:C6"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A6:C6"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A6:C6"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A6:C6"), sheet);
				
				Row headerRow = sheet.createRow(7);
				for (int i = 0; i < columns_RSS.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columns_RSS[i]);
					cell.setCellStyle(headerCellStyle);
				}

				CellStyle dateCellStyle = workbook.createCellStyle();
				dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
				
				DataFormat fmt = workbook.createDataFormat();
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setDataFormat(fmt.getFormat("@"));
//				cellStyle.setBorderTop(BorderStyle.MEDIUM);
//				cellStyle.setBorderBottom(BorderStyle.MEDIUM);
//				cellStyle.setBorderLeft(BorderStyle.MEDIUM);
//				cellStyle.setBorderRight(BorderStyle.MEDIUM);
				
				DataFormat fmt1 = workbook.createDataFormat();
				CellStyle numStyle = workbook.createCellStyle();
				numStyle.setDataFormat(fmt1.getFormat("###,0.00"));
//				numStyle.setBorderTop(BorderStyle.MEDIUM);
//				numStyle.setBorderBottom(BorderStyle.MEDIUM);
//				numStyle.setBorderLeft(BorderStyle.MEDIUM);
//				numStyle.setBorderRight(BorderStyle.MEDIUM);

				int rowNum = 7;
				int sn = 1;

				risk_rss_List = baml_Risk_Category_RPT_Repository.getRSSData(fromDAte,toDAte, risk_category);
				for (BAML_Risk_Category_RPT_Entity pep_List : risk_rss_List) {
					Row row = sheet.createRow(++rowNum);
					writeBook_RSS(pep_List, row, dateCellStyle,sn,cellStyle,numStyle);
					sn++;
				}

				for (int i = 0; i < columns_RSS.length; i++) {
					sheet.autoSizeColumn(i);
				}

				
				workbook.write(out);
				out.close();

				// Closing the workbook
				workbook.close();


			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ByteArrayInputStream(out.toByteArray());

		}

			private void writeBook_RSS(BAML_Risk_Category_RPT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
					CellStyle dateCell, CellStyle numStyle) {

				Cell cell = row.createCell(0);
				cell.setCellValue(sn);
				cell.setCellStyle(dateCell);

				Cell cif = row.createCell(1);
				cif.setCellValue(aBook.getCIF());
				cif.setCellStyle(dateCell);

				Cell lastname = row.createCell(2);
				lastname.setCellValue(aBook.getNAME());
				lastname.setCellStyle(dateCell);

				Cell firstname = row.createCell(3);
				firstname.setCellValue(aBook.getNID());
				firstname.setCellStyle(dateCell);

				Cell nid = row.createCell(4);
				nid.setCellValue(aBook.getRISK_CATEGORY());
				nid.setCellStyle(dateCell);

				Cell riskcat = row.createCell(5);
				riskcat.setCellValue(aBook.getFORACID());
				riskcat.setCellStyle(dateCell);
				
				Cell type = row.createCell(6);
				type.setCellValue(aBook.getPOB());
				type.setCellStyle(dateCell);
				
				Cell mem_date = row.createCell(7);
				mem_date.setCellValue(aBook.getBENEFICIARY());
				mem_date.setCellStyle(dateCellStyle);
				
				Cell dep = row.createCell(8);
				dep.setCellValue(aBook.getDEPARTMENT());
				dep.setCellStyle(dateCell);
				
				
				
				Cell acc_opn_dta = row.createCell(9);
				acc_opn_dta.setCellValue(aBook.getACCT_OPN_DATE());
				acc_opn_dta.setCellStyle(dateCellStyle);
				
				Cell effDate = row.createCell(10);
				effDate.setCellValue(aBook.getEFF_DATE());
				effDate.setCellStyle(dateCellStyle);
				
				Cell amtapplied = row.createCell(11);
				if(aBook.getCONT_AMT()!=null) {
					amtapplied.setCellValue(String.format("%,.2f", aBook.getCONT_AMT()));
				}else {
					amtapplied.setCellValue("");
				}
				amtapplied.setCellStyle(dateCell);
				
			}
			//***********************************************DEP
			public ByteArrayInputStream getFile_RISK_DEP_Excel(String userid,String reportId, String fromdate, String todate, String dtltype,
					String filetype,String risk_category) throws FileNotFoundException, JRException, SQLException, ParseException {

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				Session hs = sessionFactory.getCurrentSession();
				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

				String fromDAte = null;
				String toDAte = null;

				if (fromdate != null && !fromdate.isEmpty()) {
					Date ConDateFromdate = dateFormat1.parse(fromdate);
					String strDate2 = formatter1.format(ConDateFromdate);
					fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

					Date ConToDate = dateFormat1.parse(todate);
					String strDate1 = formatter1.format(ConToDate);
					toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
				}

				logger.info("Getting Output file :" + reportId);

				try {

					
					
					Workbook workbook = new XSSFWorkbook();
					CreationHelper createHelper = workbook.getCreationHelper();
					Sheet sheet = workbook.createSheet("RISK_DEP_REPORT_LIST");

					Font headerFont = workbook.createFont();
					headerFont.setBold(true);
					headerFont.setFontHeightInPoints((short) 14);
					headerFont.setColor(IndexedColors.BLACK.getIndex());
					
					CellStyle headerCellStyle = workbook.createCellStyle();
					headerCellStyle.setFont(headerFont);
					headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
					headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
					headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
					headerCellStyle.setBorderRight(BorderStyle.MEDIUM);
					
					Row TitleRow = sheet.createRow(0);
					Cell cellTitle = TitleRow.createCell((short) 0);
					cellTitle.setCellValue("THE MAURITIUS CIVIL SERVICE MUTUAL AID ASSOCIATION LTD");
					sheet.addMergedRegion(CellRangeAddress.valueOf("A1:T2"));
					headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//					headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
					cellTitle.setCellStyle(headerCellStyle);
					RegionUtil.setBorderTop(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A1:T2"), sheet);
					RegionUtil.setBorderRight(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A1:T2"), sheet);
					RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A1:T2"), sheet);
					RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A1:T2"), sheet);
//					sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
					
					
					
					Row ReportNameRow = sheet.createRow(2);
					Cell cellReporName = ReportNameRow.createCell((short) 0);
					cellReporName.setCellValue("Deposits Report Per Risk Category");
					sheet.addMergedRegion(CellRangeAddress.valueOf("A3:T3"));
					headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//					headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
					cellReporName.setCellStyle(headerCellStyle);
					RegionUtil.setBorderTop(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A3:T3"), sheet);
					RegionUtil.setBorderRight(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A3:T3"), sheet);
					RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A3:T3"), sheet);
					RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A3:T3"), sheet);
					
					CellStyle reportDateCellStyle = workbook.createCellStyle();
					reportDateCellStyle.setFont(headerFont);
					reportDateCellStyle.setBorderTop(BorderStyle.MEDIUM);
					reportDateCellStyle.setBorderBottom(BorderStyle.MEDIUM);
					reportDateCellStyle.setBorderLeft(BorderStyle.MEDIUM);
					reportDateCellStyle.setBorderRight(BorderStyle.MEDIUM);
					
					CellStyle reportDateCellStyle1 = workbook.createCellStyle();
					reportDateCellStyle1.setFont(headerFont);
					reportDateCellStyle1.setBorderTop(BorderStyle.MEDIUM);
					reportDateCellStyle1.setBorderBottom(BorderStyle.MEDIUM);
					reportDateCellStyle1.setBorderLeft(BorderStyle.MEDIUM);
					reportDateCellStyle1.setBorderRight(BorderStyle.MEDIUM);
					
					Row Report_Date_Row = sheet.createRow(3);
					Cell cellReporDate = Report_Date_Row.createCell((short) 0);
					cellReporDate.setCellValue("Start Date- "+fromDAte);
					sheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));
					reportDateCellStyle1.setAlignment(HorizontalAlignment.LEFT);
					cellReporDate.setCellStyle(reportDateCellStyle1);
					RegionUtil.setBorderTop(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A4:C4"), sheet);
					RegionUtil.setBorderRight(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A4:C4"), sheet);
					RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A4:C4"), sheet);
					RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A4:C4"), sheet);
					
					
					Row Report_Date_Row2 = sheet.createRow(4);
					Cell cellReporDate2 = Report_Date_Row2.createCell((short) 0);
					cellReporDate2.setCellValue("End Date- "+toDAte);
					sheet.addMergedRegion(CellRangeAddress.valueOf("A5:C5"));
					reportDateCellStyle1.setAlignment(HorizontalAlignment.LEFT);
					cellReporDate2.setCellStyle(reportDateCellStyle1);
					RegionUtil.setBorderTop(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A5:C5"), sheet);
					RegionUtil.setBorderRight(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A5:C5"), sheet);
					RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A5:C5"), sheet);
					RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A5:C5"), sheet);
					
					Row Report_Date_Row3 = sheet.createRow(5);
					Cell cellReporDate3 = Report_Date_Row3.createCell((short) 0);
					cellReporDate3.setCellValue("Risk Category- "+risk_category);
					sheet.addMergedRegion(CellRangeAddress.valueOf("A6:C6"));
					reportDateCellStyle1.setAlignment(HorizontalAlignment.LEFT);
					cellReporDate3.setCellStyle(reportDateCellStyle1);
					RegionUtil.setBorderTop(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A6:C6"), sheet);
					RegionUtil.setBorderRight(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A6:C6"), sheet);
					RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A6:C6"), sheet);
					RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A6:C6"), sheet);
					
					Row headerRow1 = sheet.createRow(7);
					Cell cellheaderRow11 = headerRow1.createCell((short) 0);
					cellheaderRow11.setCellValue("First Applicant");
					sheet.addMergedRegion(CellRangeAddress.valueOf("A8:I8"));
					reportDateCellStyle.setAlignment(HorizontalAlignment.CENTER);
					cellheaderRow11.setCellStyle(headerCellStyle);
					RegionUtil.setBorderTop(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A8:I8"), sheet);
					RegionUtil.setBorderRight(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A8:I8"), sheet);
					RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A8:I8"), sheet);
					RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("A8:I8"), sheet);
					
					
					Cell cellSecond = headerRow1.createCell((short) 9);
					cellSecond.setCellValue("Second Applicant");
					sheet.addMergedRegion(CellRangeAddress.valueOf("J8:Q8"));
					reportDateCellStyle.setAlignment(HorizontalAlignment.CENTER);
					cellSecond.setCellStyle(headerCellStyle);
					RegionUtil.setBorderTop(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("J8:Q8"), sheet);
					RegionUtil.setBorderRight(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("J8:Q8"), sheet);
					RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("J8:Q8"), sheet);
					RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
				            CellRangeAddress.valueOf("J8:Q8"), sheet);
					
					
					DataFormat fmt = workbook.createDataFormat();
					CellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setDataFormat(fmt.getFormat("@"));
//					cellStyle.setBorderTop(BorderStyle.MEDIUM);
//					cellStyle.setBorderBottom(BorderStyle.MEDIUM);
//					cellStyle.setBorderLeft(BorderStyle.MEDIUM);
//					cellStyle.setBorderRight(BorderStyle.MEDIUM);
					
					
				
					Row headerRow = sheet.createRow(8);
					for (int i = 0; i < columns_DEP.length; i++) {
						Cell cell = headerRow.createCell(i);
						cell.setCellValue(columns_DEP[i]);
						cell.setCellStyle(headerCellStyle);
					}

					CellStyle dateCellStyle = workbook.createCellStyle();
					dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
					
					
					DataFormat fmt1 = workbook.createDataFormat();
					CellStyle numStyle = workbook.createCellStyle();
					numStyle.setDataFormat(fmt1.getFormat("###,0.00"));
//					numStyle.setBorderTop(BorderStyle.MEDIUM);
//					numStyle.setBorderBottom(BorderStyle.MEDIUM);
//					numStyle.setBorderLeft(BorderStyle.MEDIUM);
//					numStyle.setBorderRight(BorderStyle.MEDIUM);

					int rowNum = 8;
					int sn = 1;

					risk_rss_List = baml_Risk_Category_RPT_Repository.getDepositData(fromDAte,toDAte, risk_category);
					for (BAML_Risk_Category_RPT_Entity pep_List : risk_rss_List) {
						Row row = sheet.createRow(++rowNum);
						writeBook_DEP(pep_List, row, dateCellStyle,sn,cellStyle,numStyle);
						sn++;
					}

					for (int i = 0; i < columns_DEP.length; i++) {
						sheet.autoSizeColumn(i);
					}

					
					workbook.write(out);
					out.close();

					// Closing the workbook
					workbook.close();


				} catch (Exception e) {
					e.printStackTrace();
				}

				return new ByteArrayInputStream(out.toByteArray());

			}
				private void writeBook_DEP(BAML_Risk_Category_RPT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
						CellStyle dateCell, CellStyle numStyle) {

					Cell cell = row.createCell(0);
					cell.setCellValue(sn);
					cell.setCellStyle(dateCell);

					Cell cif = row.createCell(1);
					cif.setCellValue(aBook.getCIF());
					cif.setCellStyle(dateCell);

					Cell lastname = row.createCell(2);
					lastname.setCellValue(aBook.getNAME());
					lastname.setCellStyle(dateCell);

					Cell firstname = row.createCell(3);
					firstname.setCellValue(aBook.getNID());
					firstname.setCellStyle(dateCell);

					Cell nid = row.createCell(4);
					nid.setCellValue(aBook.getRISK_CATEGORY());
					nid.setCellStyle(dateCell);

					Cell riskcat = row.createCell(5);
					riskcat.setCellValue(aBook.getFORACID());
					riskcat.setCellStyle(dateCell);
					
					Cell type = row.createCell(6);
					type.setCellValue(aBook.getPOB());
					type.setCellStyle(dateCell);
					
					Cell mem_date = row.createCell(7);
					mem_date.setCellValue(aBook.getOCCUPATION());
					mem_date.setCellStyle(dateCell);
					
					Cell dep = row.createCell(8);
					if(aBook.getMLY_INCOME()!=null) {
						dep.setCellValue(String.format("%,.2f", aBook.getMLY_INCOME()));
					}else {
						dep.setCellValue("");
					}
					dep.setCellStyle(dateCell);
					
					
					
					Cell acc_opn_dta = row.createCell(9);
					acc_opn_dta.setCellValue(aBook.getCIF_2());
					acc_opn_dta.setCellStyle(dateCell);
					
					Cell effDate = row.createCell(10);
					effDate.setCellValue(aBook.getNAME_2());
					effDate.setCellStyle(dateCell);
					
					Cell NID2 = row.createCell(11);
					NID2.setCellValue(aBook.getNID_2());
					NID2.setCellStyle(dateCell);
					
					
					Cell RISK2 = row.createCell(12);
					RISK2.setCellValue(aBook.getRISK_CATEGORY_2());
					RISK2.setCellStyle(dateCell);
					
					Cell AC2 = row.createCell(13);
					AC2.setCellValue(aBook.getFORACID_2());
					AC2.setCellStyle(dateCell);
					
					Cell POB2 = row.createCell(14);
					POB2.setCellValue(aBook.getPOB_2());
					POB2.setCellStyle(dateCell);
					
					Cell getOCCUPATION_2 = row.createCell(15);
					getOCCUPATION_2.setCellValue(aBook.getOCCUPATION_2());
					getOCCUPATION_2.setCellStyle(dateCell);
					
					Cell amtapplied = row.createCell(16);
					if(aBook.getMLY_INCOME_2()!=null) {
						amtapplied.setCellValue(String.format("%,.2f", aBook.getMLY_INCOME_2()));
					}else {
						amtapplied.setCellValue("");
					}
					amtapplied.setCellStyle(dateCell);
					
					Cell getAPPL_DATE = row.createCell(17);
					getAPPL_DATE.setCellValue(aBook.getAPPL_DATE());
					getAPPL_DATE.setCellStyle(dateCellStyle);
					
					Cell getAMT_DIS = row.createCell(18);
					if(aBook.getAMT_DIS()!=null) {
						getAMT_DIS.setCellValue(String.format("%,.2f", aBook.getAMT_DIS()));
					}else {
						getAMT_DIS.setCellValue("");
					}
					getAMT_DIS.setCellStyle(dateCell);
					
					Cell cust_pos = row.createCell(19);
					if (aBook.getSTATUS_NEW_REIN()!=null) {
						if(aBook.getSTATUS_NEW_REIN().equals("A")) {
							cust_pos.setCellValue("Re-Investment");
						}else if(aBook.getSTATUS_NEW_REIN().equals("R")) {
							cust_pos.setCellValue("Re-Investment");
						}else {
							cust_pos.setCellValue("New");

						}
					} else{
						cust_pos.setCellValue("New");
					}
					
					
					cust_pos.setCellStyle(dateCell);
					
					Cell payment_mode = row.createCell(20);
					payment_mode.setCellValue(aBook.getBENEFICIARY());
					payment_mode.setCellStyle(dateCell);
					
				}

}