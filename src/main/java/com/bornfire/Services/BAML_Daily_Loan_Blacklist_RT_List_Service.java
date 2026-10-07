package com.bornfire.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bornfire.entity.BAML_Daily_Cash_Blacklist_RT_Entity;
import com.bornfire.entity.BAML_Daily_Cash_Blacklist_RT_Repository;
import com.bornfire.entity.BAML_Daily_Loan_Blacklist_RT_Entity;
import com.bornfire.entity.BAML_Daily_Loan_Blacklist_RT_Repository;

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
public class BAML_Daily_Loan_Blacklist_RT_List_Service {

	@Autowired
	BAML_Daily_Loan_Blacklist_RT_Repository baml_daily_loan_Repository;
	
	@Autowired
	BAML_Daily_Cash_Blacklist_RT_Repository baml_daily_cash_Repository;

	private static final Logger logger = LoggerFactory.getLogger(BAML_Daily_Loan_Blacklist_RT_List_Service.class);

	@Autowired
	DataSource srcdataSource;

	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Environment env;

	private static String[] columns_Loan = {"SL", "Customer ID", "Name","NID","Risk Category","Occupation","A/c Closed Date","HNWI","BlackList_Ind Name","BlackList_Ind/NID","BlackList_CORP/Name","BlackList_CORP/NID","PEP/Name","PEP/Occupation","UNSC/Name","UNSC/Country"};
	private static String[] columns_Rss = {"SL", "Customer ID", "Name","NID","Risk Category","Occupation","HNWI","BlackList_Ind Name","BlackList_Ind/NID","BlackList_CORP/Name","BlackList_CORP/NID","PEP/Name","PEP/Occupation","UNSC/Name","UNSC/Country"};
	private static String[] columns_Deposit = {"SL", "Customer ID", "Name","NID","Risk Category","Occupation","HNWI","BlackList_Ind Name","BlackList_Ind/NID","BlackList_CORP/Name","BlackList_CORP/NID","PEP/Name","PEP/Occupation","UNSC/Name","UNSC/Country"};
	private static String[] columns_Cash = {"SL", "Customer ID", "Name","NID","Risk Category","Amount","Date","HNWI","BlackList_Ind Name","BlackList_Ind/NID","BlackList_CORP/Name","BlackList_CORP/NID","PEP/Name","PEP/Occupation","UNSC/Name","UNSC/Country"};

	private static List<BAML_Daily_Loan_Blacklist_RT_Entity> dailyList = new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>();
	private static List<BAML_Daily_Loan_Blacklist_RT_Entity> daily_RSSList = new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>();
	private static List<BAML_Daily_Loan_Blacklist_RT_Entity> daily_DepositList = new ArrayList<BAML_Daily_Loan_Blacklist_RT_Entity>();
	private static List<BAML_Daily_Cash_Blacklist_RT_Entity> daily_CashList = new ArrayList<BAML_Daily_Cash_Blacklist_RT_Entity>();

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		Date ConDateFromdate = null;
		try {
			ConDateFromdate = dateFormat1.parse(fromdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(ConDateFromdate);

		String strDate2 = formatter1.format(ConDateFromdate);
		try {
			fromdate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Date ConToDate = null;
		try {
			ConToDate = dateFormat1.parse(todate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(ConToDate);

		String strDate1 = formatter1.format(ConToDate);
		try {
			todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String path = env.getProperty("output.exportpath");
		String fileName = "";

		File outputFile;

		logger.info("Getting Output file :" + reportId);

		String msg = "";

		try {

			InputStream fileStream = null;

			if (reportId.equals("Daily_Loan")) {
				fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
				logger.info("Getting Jasper file :" + reportId);

				fileStream = this.getClass()
						.getResourceAsStream("/static/jasper/AMLReports/DailyReports/DailyLoan.jasper");
			} else if (reportId.equals("Daily_Rss")) {
				fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("xlsx")) {
					fileStream = this.getClass()
							.getResourceAsStream("/static/jasper/AMLReports/DailyReports/DailyRss.jasper");
				} else {
					fileStream = this.getClass()
							.getResourceAsStream("/static/jasper/AMLReports/DailyReports/DailyRss.jasper");
				}
			} else if (reportId.equals("Daily_Deposit")) {
				fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("xlsx")) {
					fileStream = this.getClass()
							.getResourceAsStream("/static/jasper/AMLReports/DailyReports/DailyDeposit.jasper");
				} else {
					fileStream = this.getClass()
							.getResourceAsStream("/static/jasper/AMLReports/DailyReports/DailyDeposit.jasper");
				}
			} else if (reportId.equals("Daily_cash_tran")) {
				fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(todate));
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("xlsx")) {
					fileStream = this.getClass()
							.getResourceAsStream("/static/jasper/AMLReports/DailyReports/DailyCashTran.jasper");
				} else {
					fileStream = this.getClass()
							.getResourceAsStream("/static/jasper/AMLReports/DailyReports/DailyCashTran.jasper");
				}
			}

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("TO_DATE", todate);
			map.put("FROM_DATE", fromdate);

			logger.info("BEFORE GENERATING PDF :" + reportId);
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;
				logger.info("BEFORE GENERATING PDF 1 :" + reportId);
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				logger.info("BEFORE GENERATING PDF 2 :" + path);
				JasperExportManager.exportReportToPdfFile(jp, path);
				logger.info("PDF File exported");
			} else {
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

		outputFile = new File(path);

		return outputFile;

	}

	public ByteArrayInputStream getFileLoanExcel(String userid, String reportId, String fromdate, String todate,
			String dtltype, String filetype) throws FileNotFoundException, JRException, SQLException, ParseException {

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

		String path = env.getProperty("output.exportpath");
		String fileName = "";

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		try {
			InputStream fileStream = null;

			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("LOAN_LIST");

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
			sheet.addMergedRegion(CellRangeAddress.valueOf("A1:P2"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellTitle.setCellStyle(headerCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));

			Row ReportNameRow = sheet.createRow(2);
			Cell cellReporName = ReportNameRow.createCell((short) 0);
			cellReporName.setCellValue("Loan Daily BlackList and Caution List/UNSC/PEP Report");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A3:P3"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporName.setCellStyle(headerCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);

			CellStyle reportDateCellStyle = workbook.createCellStyle();
			reportDateCellStyle.setFont(headerFont);
			reportDateCellStyle.setBorderTop(BorderStyle.MEDIUM);
			reportDateCellStyle.setBorderBottom(BorderStyle.MEDIUM);
			reportDateCellStyle.setBorderLeft(BorderStyle.MEDIUM);
			reportDateCellStyle.setBorderRight(BorderStyle.MEDIUM);

			Row Report_Date_Row = sheet.createRow(3);
			Cell cellReporDate = Report_Date_Row.createCell((short) 0);
			cellReporDate.setCellValue("Start Date- " + fromDAte );
			sheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//			reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
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
			cellReporDate2.setCellValue("End Date- " + toDAte);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A5:C5"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//			reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporDate2.setCellStyle(reportDateCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);

			
			
			Row headerRow = sheet.createRow(6);
			for (int i = 0; i < columns_Loan.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns_Loan[i]);
				cell.setCellStyle(headerCellStyle);
			}

			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			DataFormat fmt = workbook.createDataFormat();
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(fmt.getFormat("@"));

			int rowNum = 6;
			int sn = 1;

			dailyList = baml_daily_loan_Repository.findAllCustIdfordailyLoanReportExcel(fromDAte, toDAte);
			for (BAML_Daily_Loan_Blacklist_RT_Entity daily_List : dailyList) {
				Row row = sheet.createRow(++rowNum);
				writeBook_Loan(daily_List, row, dateCellStyle, sn, cellStyle);
				sn++;
			}

			for (int i = 0; i < columns_Loan.length; i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(out);

			out.close();

			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());

	}

	private void writeBook_Loan(BAML_Daily_Loan_Blacklist_RT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
			CellStyle dateCell) {

		Cell cell = row.createCell(0);
		cell.setCellValue(sn);
		cell.setCellStyle(dateCell);

		Cell cif = row.createCell(1);
		cif.setCellValue(aBook.getCif());
		cif.setCellStyle(dateCell);

		Cell lastname = row.createCell(2);
		lastname.setCellValue(aBook.getName());
		lastname.setCellStyle(dateCell);

		Cell firstname = row.createCell(3);
		firstname.setCellValue(aBook.getNid());
		firstname.setCellStyle(dateCell);

		Cell nid = row.createCell(4);
		nid.setCellValue(aBook.getRisk_category());
		nid.setCellStyle(dateCell);

		Cell risk_cat = row.createCell(5);
		risk_cat.setCellValue(aBook.getOccupation());
		risk_cat.setCellStyle(dateCell);

		Cell pep_desc = row.createCell(6);
		pep_desc.setCellValue(aBook.getTran_date());
		pep_desc.setCellStyle(dateCellStyle);

		Cell cust_pos = row.createCell(7);
		cust_pos.setCellValue(aBook.getHnwi_name());
		cust_pos.setCellStyle(dateCell);

		Cell membershipDate = row.createCell(8);
		membershipDate.setCellValue(aBook.getBlack_list_ind_name());
		membershipDate.setCellStyle(dateCellStyle);

		Cell dateofpep = row.createCell(9);
		dateofpep.setCellValue(aBook.getBlack_list_ind_nid());
		dateofpep.setCellStyle(dateCellStyle);

		Cell dateofresgn = row.createCell(10);
		dateofresgn.setCellValue(aBook.getBlack_list_corp_name());
		dateofresgn.setCellStyle(dateCellStyle);

		cell = row.createCell(11);
		cell.setCellValue(aBook.getBlack_list_corp_nid());

		cell = row.createCell(12);
		cell.setCellValue(aBook.getPep_name());

		cell = row.createCell(13);
		cell.setCellValue(aBook.getPep_occupation());

		cell = row.createCell(14);
		cell.setCellValue(aBook.getUnsc_name());

		cell = row.createCell(15);
		cell.setCellValue(aBook.getUnsc_country());
	}

//**************************************RSS EXCEL
	public ByteArrayInputStream getFileRSSExcel(String userid, String reportId, String fromdate, String todate,
			String dtltype, String filetype) throws FileNotFoundException, JRException, SQLException, ParseException {

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

		String fileName = "";

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		try {
			InputStream fileStream = null;

			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("RSS_LIST");

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
			sheet.addMergedRegion(CellRangeAddress.valueOf("A1:P2"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//		headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellTitle.setCellStyle(headerCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A1:P2"), sheet);
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));

			Row ReportNameRow = sheet.createRow(2);
			Cell cellReporName = ReportNameRow.createCell((short) 0);
			cellReporName.setCellValue("RSS Daily BlackList and Caution List/UNSC/PEP Report");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A3:P3"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//		headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporName.setCellStyle(headerCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A3:P3"), sheet);

			CellStyle reportDateCellStyle = workbook.createCellStyle();
			reportDateCellStyle.setFont(headerFont);
			reportDateCellStyle.setBorderTop(BorderStyle.MEDIUM);
			reportDateCellStyle.setBorderBottom(BorderStyle.MEDIUM);
			reportDateCellStyle.setBorderLeft(BorderStyle.MEDIUM);
			reportDateCellStyle.setBorderRight(BorderStyle.MEDIUM);

			Row Report_Date_Row = sheet.createRow(3);
			Cell cellReporDate = Report_Date_Row.createCell((short) 0);
			cellReporDate.setCellValue("Start Date- " + fromDAte);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//		reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
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
			cellReporDate2.setCellValue("End Date- " + toDAte);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A5:C5"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//		reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporDate2.setCellStyle(reportDateCellStyle);
			RegionUtil.setBorderTop(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderRight(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);
			RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
		            CellRangeAddress.valueOf("A5:C5"), sheet);

			Row headerRow = sheet.createRow(6);
			for (int i = 0; i < columns_Rss.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns_Rss[i]);
				cell.setCellStyle(headerCellStyle);
			}

			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			DataFormat fmt = workbook.createDataFormat();
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(fmt.getFormat("@"));

			int rowNum = 6;
			int sn = 1;

			daily_RSSList = baml_daily_loan_Repository.findAllCustIdfordailyRssReport(fromDAte, toDAte);
			for (BAML_Daily_Loan_Blacklist_RT_Entity daily_List : daily_RSSList) {
				Row row = sheet.createRow(++rowNum);
				writeBook_RSS(daily_List, row, dateCellStyle, sn, cellStyle);
				sn++;
			}

			for (int i = 0; i < columns_Rss.length; i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(out);

			out.close();

			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());

	}

	private void writeBook_RSS(BAML_Daily_Loan_Blacklist_RT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
			CellStyle dateCell) {

		Cell cell = row.createCell(0);
		cell.setCellValue(sn);
		cell.setCellStyle(dateCell);

		Cell cif = row.createCell(1);
		cif.setCellValue(aBook.getCif());
		cif.setCellStyle(dateCell);

		Cell lastname = row.createCell(2);
		lastname.setCellValue(aBook.getName());
		lastname.setCellStyle(dateCell);

		Cell firstname = row.createCell(3);
		firstname.setCellValue(aBook.getNid());
		firstname.setCellStyle(dateCell);

		Cell nid = row.createCell(4);
		nid.setCellValue(aBook.getRisk_category());
		nid.setCellStyle(dateCell);

		Cell risk_cat = row.createCell(5);
		risk_cat.setCellValue(aBook.getOccupation());
		risk_cat.setCellStyle(dateCell);

//Cell pep_desc = row.createCell(6);
//pep_desc.setCellValue(aBook.getTran_date());
//pep_desc.setCellStyle(dateCellStyle);

		Cell cust_pos = row.createCell(6);
		cust_pos.setCellValue(aBook.getHnwi_name());
		cust_pos.setCellStyle(dateCell);

		Cell membershipDate = row.createCell(7);
		membershipDate.setCellValue(aBook.getBlack_list_ind_name());
		membershipDate.setCellStyle(dateCell);

		Cell dateofpep = row.createCell(8);
		dateofpep.setCellValue(aBook.getBlack_list_ind_nid());
		dateofpep.setCellStyle(dateCell);

		Cell dateofresgn = row.createCell(9);
		dateofresgn.setCellValue(aBook.getBlack_list_corp_name());
		dateofresgn.setCellStyle(dateCell);

		cell = row.createCell(10);
		cell.setCellValue(aBook.getBlack_list_corp_nid());

		cell = row.createCell(11);
		cell.setCellValue(aBook.getPep_name());

		cell = row.createCell(12);
		cell.setCellValue(aBook.getPep_occupation());

		cell = row.createCell(13);
		cell.setCellValue(aBook.getUnsc_name());

		cell = row.createCell(14);
		cell.setCellValue(aBook.getUnsc_country());
	}
	
	//**************************************deposit EXCEL
		public ByteArrayInputStream getFileDepositExcel(String userid, String reportId, String fromdate, String todate,
				String dtltype, String filetype) throws FileNotFoundException, JRException, SQLException, ParseException {

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

			String fileName = "";

			logger.info("Getting Output file :" + reportId);

			fileName = reportId + "_" + dateFormat.format(new Date());

			try {
				InputStream fileStream = null;

				Workbook workbook = new XSSFWorkbook();
				CreationHelper createHelper = workbook.getCreationHelper();
				Sheet sheet = workbook.createSheet("DEPOSIT_LIST");

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
				sheet.addMergedRegion(CellRangeAddress.valueOf("A1:P2"));
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				cellTitle.setCellStyle(headerCellStyle);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:P2"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:P2"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:P2"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A1:P2"), sheet);
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));

				Row ReportNameRow = sheet.createRow(2);
				Cell cellReporName = ReportNameRow.createCell((short) 0);
				cellReporName.setCellValue("Deposit Daily BlackList and Caution List/UNSC/PEP Report");
				sheet.addMergedRegion(CellRangeAddress.valueOf("A3:P3"));
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				cellReporName.setCellStyle(headerCellStyle);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:P3"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:P3"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:P3"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A3:P3"), sheet);

				CellStyle reportDateCellStyle = workbook.createCellStyle();
				reportDateCellStyle.setFont(headerFont);
				reportDateCellStyle.setBorderTop(BorderStyle.MEDIUM);
				reportDateCellStyle.setBorderBottom(BorderStyle.MEDIUM);
				reportDateCellStyle.setBorderLeft(BorderStyle.MEDIUM);
				reportDateCellStyle.setBorderRight(BorderStyle.MEDIUM);

				Row Report_Date_Row = sheet.createRow(3);
				Cell cellReporDate = Report_Date_Row.createCell((short) 0);
				cellReporDate.setCellValue("Start Date- " + fromDAte);
				sheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));
				reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//			reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
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
				cellReporDate2.setCellValue("End Date- " + toDAte);
				sheet.addMergedRegion(CellRangeAddress.valueOf("A5:C5"));
				reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//				reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				cellReporDate2.setCellStyle(reportDateCellStyle);
				RegionUtil.setBorderTop(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);
				RegionUtil.setBorderRight(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);
				RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);
				RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
			            CellRangeAddress.valueOf("A5:C5"), sheet);

				
				
				Row headerRow = sheet.createRow(6);
				for (int i = 0; i < columns_Deposit.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columns_Deposit[i]);
					cell.setCellStyle(headerCellStyle);
				}

				CellStyle dateCellStyle = workbook.createCellStyle();
				dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

				DataFormat fmt = workbook.createDataFormat();
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setDataFormat(fmt.getFormat("@"));

				int rowNum = 6;
				int sn = 1;

				daily_DepositList = baml_daily_loan_Repository.findAllCustIdfordailyDepositReport(fromDAte, toDAte);
				for (BAML_Daily_Loan_Blacklist_RT_Entity daily_List : daily_DepositList) {
					Row row = sheet.createRow(++rowNum);
					writeBook_Deposit(daily_List, row, dateCellStyle, sn, cellStyle);
					sn++;
				}

				for (int i = 0; i < columns_Deposit.length; i++) {
					sheet.autoSizeColumn(i);
				}

				workbook.write(out);

				out.close();

				workbook.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ByteArrayInputStream(out.toByteArray());

		}

		private void writeBook_Deposit(BAML_Daily_Loan_Blacklist_RT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
				CellStyle dateCell) {

			Cell cell = row.createCell(0);
			cell.setCellValue(sn);
			cell.setCellStyle(dateCell);

			Cell cif = row.createCell(1);
			cif.setCellValue(aBook.getCif());
			cif.setCellStyle(dateCell);

			Cell lastname = row.createCell(2);
			lastname.setCellValue(aBook.getName());
			lastname.setCellStyle(dateCell);

			Cell firstname = row.createCell(3);
			firstname.setCellValue(aBook.getNid());
			firstname.setCellStyle(dateCell);

			Cell nid = row.createCell(4);
			nid.setCellValue(aBook.getRisk_category());
			nid.setCellStyle(dateCell);

			Cell risk_cat = row.createCell(5);
			risk_cat.setCellValue(aBook.getOccupation());
			risk_cat.setCellStyle(dateCell);

	//Cell pep_desc = row.createCell(6);
	//pep_desc.setCellValue(aBook.getTran_date());
	//pep_desc.setCellStyle(dateCellStyle);

			Cell cust_pos = row.createCell(6);
			cust_pos.setCellValue(aBook.getHnwi_name());
			cust_pos.setCellStyle(dateCell);

			Cell membershipDate = row.createCell(7);
			membershipDate.setCellValue(aBook.getBlack_list_ind_name());
			membershipDate.setCellStyle(dateCell);

			Cell dateofpep = row.createCell(8);
			dateofpep.setCellValue(aBook.getBlack_list_ind_nid());
			dateofpep.setCellStyle(dateCell);

			Cell dateofresgn = row.createCell(9);
			dateofresgn.setCellValue(aBook.getBlack_list_corp_name());
			dateofresgn.setCellStyle(dateCell);

			cell = row.createCell(10);
			cell.setCellValue(aBook.getBlack_list_corp_nid());

			cell = row.createCell(11);
			cell.setCellValue(aBook.getPep_name());

			cell = row.createCell(12);
			cell.setCellValue(aBook.getPep_occupation());

			cell = row.createCell(13);
			cell.setCellValue(aBook.getUnsc_name());

			cell = row.createCell(14);
			cell.setCellValue(aBook.getUnsc_country());
		}
		
		
		//**************************************deposit EXCEL
				public ByteArrayInputStream getFileCashExcel(String userid, String reportId, String fromdate, String todate,
						String dtltype, String filetype) throws FileNotFoundException, JRException, SQLException, ParseException {

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

					String fileName = "";

					logger.info("Getting Output file :" + reportId);

					fileName = reportId + "_" + dateFormat.format(new Date());

					try {
						InputStream fileStream = null;

						Workbook workbook = new XSSFWorkbook();
						CreationHelper createHelper = workbook.getCreationHelper();
						Sheet sheet = workbook.createSheet("CASH_LIST");

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
						sheet.addMergedRegion(CellRangeAddress.valueOf("A1:P2"));
						headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//					headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
						cellTitle.setCellStyle(headerCellStyle);
						RegionUtil.setBorderTop(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A1:P2"), sheet);
						RegionUtil.setBorderRight(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A1:P2"), sheet);
						RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A1:P2"), sheet);
						RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A1:P2"), sheet);
//					sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));

						Row ReportNameRow = sheet.createRow(2);
						Cell cellReporName = ReportNameRow.createCell((short) 0);
						cellReporName.setCellValue("Cash Daily BlackList and Caution List/UNSC/PEP Report");
						sheet.addMergedRegion(CellRangeAddress.valueOf("A3:P3"));
						headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//					headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
						cellReporName.setCellStyle(headerCellStyle);
						RegionUtil.setBorderTop(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A3:P3"), sheet);
						RegionUtil.setBorderRight(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A3:P3"), sheet);
						RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A3:P3"), sheet);
						RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A3:P3"), sheet);

						CellStyle reportDateCellStyle = workbook.createCellStyle();
						reportDateCellStyle.setFont(headerFont);
						reportDateCellStyle.setBorderTop(BorderStyle.MEDIUM);
						reportDateCellStyle.setBorderBottom(BorderStyle.MEDIUM);
						reportDateCellStyle.setBorderLeft(BorderStyle.MEDIUM);
						reportDateCellStyle.setBorderRight(BorderStyle.MEDIUM);

						Row Report_Date_Row = sheet.createRow(3);
						Cell cellReporDate = Report_Date_Row.createCell((short) 0);
						cellReporDate.setCellValue("Start Date- " + fromDAte);
						sheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));
						reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//					reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
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
						cellReporDate2.setCellValue("End Date- " + toDAte);
						sheet.addMergedRegion(CellRangeAddress.valueOf("A5:C5"));
						reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//						reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
						cellReporDate2.setCellStyle(reportDateCellStyle);
						RegionUtil.setBorderTop(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A5:C5"), sheet);
						RegionUtil.setBorderRight(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A5:C5"), sheet);
						RegionUtil.setBorderLeft(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A5:C5"), sheet);
						RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
					            CellRangeAddress.valueOf("A5:C5"), sheet);

						
						
						Row headerRow = sheet.createRow(6);
						for (int i = 0; i < columns_Cash.length; i++) {
							Cell cell = headerRow.createCell(i);
							cell.setCellValue(columns_Cash[i]);
							cell.setCellStyle(headerCellStyle);
						}

						CellStyle dateCellStyle = workbook.createCellStyle();
						dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

						DataFormat fmt = workbook.createDataFormat();
						CellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setDataFormat(fmt.getFormat("@"));

						DataFormat fmt1 = workbook.createDataFormat();
						CellStyle numStyle = workbook.createCellStyle();
						numStyle.setDataFormat(fmt1.getFormat("#,##0.000"));
						
						
						int rowNum = 6;
						int sn = 1;

						daily_CashList = baml_daily_cash_Repository.findAllCustIdfordailyCASHReport(fromDAte, toDAte);
						for (BAML_Daily_Cash_Blacklist_RT_Entity daily_List : daily_CashList) {
							Row row = sheet.createRow(++rowNum);
							getFileCashExcel(daily_List, row, dateCellStyle, sn, cellStyle,numStyle);
							sn++;
						}

						for (int i = 0; i < columns_Cash.length; i++) {
							sheet.autoSizeColumn(i);
						}

						workbook.write(out);

						out.close();

						workbook.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

					return new ByteArrayInputStream(out.toByteArray());

				}

				private void getFileCashExcel(BAML_Daily_Cash_Blacklist_RT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
						CellStyle dateCell, CellStyle numStyle) {

					Cell cell = row.createCell(0);
					cell.setCellValue(sn);
					cell.setCellStyle(dateCell);

					Cell cif = row.createCell(1);
					cif.setCellValue(aBook.getCif());
					cif.setCellStyle(dateCell);

					Cell lastname = row.createCell(2);
					lastname.setCellValue(aBook.getName());
					lastname.setCellStyle(dateCell);

					Cell firstname = row.createCell(3);
					firstname.setCellValue(aBook.getNid());
					firstname.setCellStyle(dateCell);

					Cell nid = row.createCell(4);
					nid.setCellValue(aBook.getRisk_category());
					nid.setCellStyle(dateCell);


					
					Cell risk_cat = row.createCell(5);
					risk_cat.setCellValue(String.format("%,.2f", aBook.getTran_amount()));
					risk_cat.setCellStyle(dateCell);

			Cell pep_desc = row.createCell(6);
			pep_desc.setCellValue(aBook.getTran_date());
			pep_desc.setCellStyle(dateCellStyle);

					Cell cust_pos = row.createCell(7);
					cust_pos.setCellValue(aBook.getHnwi_name());
					cust_pos.setCellStyle(dateCell);

					Cell membershipDate = row.createCell(8);
					membershipDate.setCellValue(aBook.getBlack_list_ind_name());
					membershipDate.setCellStyle(dateCell);

					Cell dateofpep = row.createCell(9);
					dateofpep.setCellValue(aBook.getBlack_list_ind_nid());
					dateofpep.setCellStyle(dateCell);

					Cell dateofresgn = row.createCell(10);
					dateofresgn.setCellValue(aBook.getBlack_list_corp_name());
					dateofresgn.setCellStyle(dateCell);

					cell = row.createCell(11);
					cell.setCellValue(aBook.getBlack_list_corp_nid());

					cell = row.createCell(12);
					cell.setCellValue(aBook.getPep_name());

					cell = row.createCell(13);
					cell.setCellValue(aBook.getPep_occupation());

					cell = row.createCell(14);
					cell.setCellValue(aBook.getUnsc_name());

					cell = row.createCell(15);
					cell.setCellValue(aBook.getUnsc_country());
				}
}