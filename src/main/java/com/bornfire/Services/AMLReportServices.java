/*
 * 
 * package com.bornfire.Services;
 * 
 * import java.io.File; import java.io.FileNotFoundException; import
 * java.sql.SQLException; import java.text.DateFormat; import
 * java.text.SimpleDateFormat; import java.util.ArrayList; import
 * java.util.Arrays; import java.util.Collections; import java.util.Date; import
 * java.util.HashMap; import java.util.List;
 * 
 * import javax.sql.DataSource; import javax.validation.constraints.NotNull;
 * 
 * import org.hibernate.Session; import org.hibernate.SessionFactory; import
 * org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.context.properties.ConfigurationProperties; import
 * org.springframework.data.domain.Pageable; import
 * org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional; import
 * org.springframework.util.ResourceUtils; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import net.sf.jasperreports.engine.JRException; import
 * net.sf.jasperreports.engine.JasperFillManager; import
 * net.sf.jasperreports.engine.JasperPrint; import
 * net.sf.jasperreports.engine.JasperReport; import
 * net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter; import
 * net.sf.jasperreports.engine.util.JRLoader; import
 * net.sf.jasperreports.export.SimpleExporterInput; import
 * net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
 * 
 * @Service
 * 
 * @Transactional
 * 
 * @ConfigurationProperties("output") public class AMLReportServices {
 * 
 * private static final Logger logger =
 * LoggerFactory.getLogger(AMLReportServices.class);
 * 
 * @NotNull private String exportpath;
 * 
 * 
 * 
 * @Autowired DataSource srcdataSource;
 * 
 * @Autowired SessionFactory sessionFactory;
 * 
 * 
 * 
 * public String getExportpath() { return exportpath; }
 * 
 * public void setExportpath(String exportpath) { this.exportpath = exportpath;
 * }
 * 
 * public ModelAndView getReportView(String reportId, String reportDate, String
 * fromdate, String todate, String currency, String dtltype, String subreportid,
 * String secid, String reportingTime ,Pageable pageable) {
 * 
 * ModelAndView repsummary = new ModelAndView();
 * 
 * logger.info("Getting View for the Report :" + reportId); switch (reportId) {
 * 
 * 
 * }
 * 
 * 
 * 
 * return repsummary;
 * 
 * }
 * 
 * public ModelAndView getReportSummary(String reportId, String reportDate,
 * String fromdate, String todate, String currency,String dtltype, String
 * subreportid, String secid,String reportingTime,Pageable pageable) {
 * 
 * ModelAndView repsummary = new ModelAndView();
 * logger.info("Getting Summary for the Report :" + reportId); switch (reportId)
 * {
 * 
 * 
 * 
 * }
 * 
 * return repsummary;
 * 
 * }
 * 
 * public ModelAndView getReportDetails(String reportId, String instanceCode,
 * String asondate, String fromdate, String todate, String currency, String
 * reportingTime ,String dtltype, String subreportid, String secid, Pageable
 * pageable) {
 * 
 * ModelAndView repdetail = new ModelAndView();
 * logger.info("Getting Details for the Report :" + reportId); switch (reportId)
 * {
 * 
 * }
 * 
 * return repdetail;
 * 
 * }
 * 
 * public File getDownloadFile(String reportId, String asondate, String
 * fromdate, String todate, String currency, String subreportid, String secid,
 * String dtltype, String reportingTime,String filetype, String instancecode)
 * throws FileNotFoundException, JRException, SQLException {
 * 
 * File repfile = null;
 * 
 * logger.info("Getting Report File for : " + reportId + " in " + filetype +
 * " format");
 * 
 * switch (reportId) { }
 * 
 * return repfile; }
 * 
 * public String saveReport(String reportId, String asondate, String fromdate,
 * String todate, String currency) {
 * 
 * String msg = null;
 * 
 * logger.info("Saving the Report : " + reportId);
 * 
 * try {
 * 
 * 
 * 
 * xbrlProceduresRep.ReportSaveSp(reportId, "0", asondate, fromdate, todate,
 * currency);
 * 
 * logger.info("ReportServices->saveReport()->inside try{}"); msg = "success";
 * 
 * } catch (Exception e) {
 * logger.info("ReportServices->saveReport()->inside catch{}"); msg = "failed";
 * }
 * 
 * return msg; }
 * 
 * public String saveFIM0500Report(String reportId, String asondate, String
 * fromdate, String todate, String currency, String reportingTime) {
 * 
 * String msg = null;
 * 
 * logger.info("Saving the Report : " + reportId);
 * 
 * try {
 * 
 * xbrlProceduresRep.ReportSaveSp(reportId, reportingTime , asondate, fromdate,
 * todate, currency);
 * 
 * logger.info("ReportServices->saveFIM0500Report()->inside try{}"); msg =
 * "success";
 * 
 * } catch (Exception e) {
 * logger.info("ReportServices->saveFIM0500Report()->inside catch{}"); msg =
 * "failed"; }
 * 
 * 
 * 
 * return msg; }
 * 
 * 
 * 
 * public String preCheckReport(String reportid, String asondate, String
 * fromdate, String todate) {
 * 
 * String msg = "";
 * 
 * logger.info("Report precheck : " + reportid);
 * 
 * switch (reportid) {
 * 
 * 
 * default: logger.info("default -> preCheck()"); }
 * 
 * return msg; }
 * 
 * 
 * public class ReportTitle {
 * 
 * String reportName; String reportId; Date report_date; String domain;
 * Character completedFlg; String frequency;
 * 
 * public String getReportName() { return reportName; }
 * 
 * public void setReportName(String reportName) { this.reportName = reportName;
 * }
 * 
 * public String getReportId() { return reportId; }
 * 
 * public void setReportId(String reportId) { this.reportId = reportId; }
 * 
 * public Date getReport_date() { return report_date; }
 * 
 * public void setReport_date(Date report_date) { this.report_date =
 * report_date; }
 * 
 * public String getDomain() { return domain; }
 * 
 * public void setDomain(String domain) { this.domain = domain; }
 * 
 * public Character getCompletedFlg() { return completedFlg; }
 * 
 * public void setCompletedFlg(Character completedFlg) { this.completedFlg =
 * completedFlg; }
 * 
 * public String getFrequency() { return frequency; }
 * 
 * public void setFrequency(String frequency) { this.frequency = frequency; }
 * 
 * public ReportTitle(String reportName, String reportId) { super();
 * this.reportName = reportName; this.reportId = reportId; }
 * 
 * public ReportTitle(String reportName, String reportId, Date reportDate,
 * String domain, Character completedFlg, String frequency) { super();
 * this.reportName = reportName; this.reportId = reportId; this.report_date =
 * reportDate; this.domain = domain; this.completedFlg = completedFlg;
 * this.frequency = frequency; }
 * 
 * }
 * 
 * class FileUpload {
 * 
 * private String dpnd_report_id; private String report_name; private String
 * report_frequency; private String file_count;
 * 
 * public String getDpnd_report_id() { return dpnd_report_id; }
 * 
 * public void setDpnd_report_id(String dpnd_report_id) { this.dpnd_report_id =
 * dpnd_report_id; }
 * 
 * public String getReport_name() { return report_name; }
 * 
 * public void setReport_name(String report_name) { this.report_name =
 * report_name; }
 * 
 * public String getReport_frequency() { return report_frequency; }
 * 
 * public void setReport_frequency(String report_frequency) {
 * this.report_frequency = report_frequency; }
 * 
 * public String getFile_count() { return file_count; }
 * 
 * public void setFile_count(String file_count) { this.file_count = file_count;
 * }
 * 
 * public FileUpload(String dpnd_report_id, String report_name, String
 * report_frequency, String file_count) { this.dpnd_report_id = dpnd_report_id;
 * this.report_name = report_name; this.report_frequency = report_frequency;
 * this.file_count = file_count; }
 * 
 * }
 * 
 * public File getAuditLogFile(Date fromdate, Date todate) {
 * 
 * DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
 * 
 * String path = exportpath; String fileName =
 * "AUDIT_LOGS_"+dateFormat.format(new Date())+".xlsx"; File outputFile;
 * 
 * File jasperFile;
 * 
 * File folders = new File(path); if (!folders.exists()) { folders.mkdirs(); }
 * 
 * try { jasperFile =
 * ResourceUtils.getFile("classpath:static/jasper/AUDIT_LOGS/AuditLogs.jasper");
 * JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
 * HashMap<String, Object> map = new HashMap<String, Object>();
 * 
 * logger.info("Inside File Generation Method");
 * 
 * logger.info("Assigning Parameters for Jasper"); map.put("FromDate",
 * dateFormat.format(fromdate)); map.put("ToDate", dateFormat.format(todate));
 * 
 * 
 * logger.info("Inside Method");
 * 
 * path = path + "/" + fileName; JasperPrint jp =
 * JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
 * JRXlsxExporter exporter = new JRXlsxExporter(); exporter.setExporterInput(new
 * SimpleExporterInput(jp)); exporter.setExporterOutput(new
 * SimpleOutputStreamExporterOutput(path)); exporter.exportReport();
 * logger.info("Excel File exported");
 * 
 * } catch (FileNotFoundException|JRException|SQLException e) {
 * 
 * logger.info(e.getMessage()); logger.info("Inside catch");
 * 
 * e.printStackTrace(); }
 * 
 * 
 * outputFile = new File(path);
 * 
 * 
 * return outputFile;
 * 
 * }
 * 
 * 
 * }
 * 
 */