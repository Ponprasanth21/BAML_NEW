package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.BAML_RBS_PROCEDURE_REP;
import com.bornfire.entity.BAML_RBS_REPORT_PROCEDURE;
import com.bornfire.entity.T2CurrentMast;
import com.bornfire.entity.t1.T1DataMaintenance;
import com.bornfire.entity.t10.T10DataMaintenance;
import com.bornfire.entity.t12.T12DataMaintenance;
import com.bornfire.entity.t14.T14DetailMaintenance;
import com.bornfire.entity.t15.T15DataMaintenance;
import com.bornfire.entity.t18.T18DataMaintenance;
import com.bornfire.entity.t3a.T3ADataMaintenance;
import com.bornfire.entity.t5.T5Detail;
import com.bornfire.entity.t8.T8DataMaintenance;
import com.bornfire.entity.t9.T9DataMaintenance;

@Service
@Transactional
@ConfigurationProperties("output")
public class RBSDataMaintenanceServices {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	BAML_RBS_PROCEDURE_REP bAML_RBS_PROCEDURE_REP;
	
	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	private static final Logger logger = LoggerFactory.getLogger(RBSDataMaintenanceServices.class);

	public String dataMaintenanceT5(String formmode, String rpt_code, String rpt_date, T2CurrentMast t2CurrentMast,
			T5Detail t5Detail) throws ParseException {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("addT5")) {

			T5Detail up2 = t5Detail;
			up2.setEntity_cre_flg("N");
			up2.setMod_flg("Y");
			up2.setDel_flg("N");
			hs.save(up2);
			msg = "Added Successfully";

		} else if (formmode.equals("addT2")) {

			T2CurrentMast up1 = t2CurrentMast;
			up1.setEntity_cre_flg('Y');
			up1.setMod_flg('Y');
			up1.setDel_flg('N');
			hs.save(up1);
			msg = "Added Successfully";

		} else if (formmode.equals("editT2")) {

			T2CurrentMast up1 = t2CurrentMast;
			up1.setEntity_cre_flg('N');
			up1.setDel_flg('N');
			up1.setMod_flg('N');
			hs.saveOrUpdate(up1);
			msg = "Edited Successfully";

		} else if (formmode.equals("editT5")) {

			T5Detail up2 = t5Detail;

			up2.setEntity_cre_flg("N");
			up2.setDel_flg("N");
			up2.setMod_flg("N");
			hs.saveOrUpdate(up2);
		
			msg = "Edited Successfully";

		} else if (formmode.equals("verifyT2")) {

			T2CurrentMast up1 = t2CurrentMast;
			up1.setEntity_cre_flg('Y');
			up1.setDel_flg('N');
			hs.saveOrUpdate(up1);
			Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T2");
			BAML_RBS_REPORT_PROCEDURE up = account.get();
			up.setReport_flag('Y');
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			up.setReport_date(dt1);
			bAML_RBS_PROCEDURE_REP.save(up);
			msg = "Verified Successfully";

		} else if (formmode.equals("verifyT5")) {

			T5Detail up2 = t5Detail;
			up2.setEntity_cre_flg("Y");
			up2.setDel_flg("N");
			hs.saveOrUpdate(up2);
			Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T5");
			BAML_RBS_REPORT_PROCEDURE up1 = account.get();
			up1.setReport_flag('Y');
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			up1.setReport_date(dt1);
			bAML_RBS_PROCEDURE_REP.save(up1);
			msg = "Verified Successfully";

		} else if (formmode.equals("deleteT2")) {

			T2CurrentMast up1 = t2CurrentMast;
			// up1.setReport_date(rpt_date);
			hs.delete(up1);
			Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T2");
			BAML_RBS_REPORT_PROCEDURE up = account.get();
			up.setReport_flag('Y');
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			up.setReport_date(dt1);
			bAML_RBS_PROCEDURE_REP.save(up);
			msg = "Deleted Successfully";

		} else if (formmode.equals("deleteT5")) {

			T5Detail up2 = t5Detail;
			hs.delete(up2);
			Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T5");
			BAML_RBS_REPORT_PROCEDURE up1 = account.get();
			up1.setReport_flag('Y');
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			up1.setReport_date(dt1);
			bAML_RBS_PROCEDURE_REP.save(up1);
			msg = "Deleted Successfully";

		}
		return msg;
	}

	public String dataMaintenanceT3A(String formmode, String rpt_date, String str,
			T3ADataMaintenance t3ADataMaintenance) throws ParseException {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("addT3A")) {

			T3ADataMaintenance up = t3ADataMaintenance;
		
			/*
			 * Date dt1; dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(str);
			 * System.out.println("dt1" + dt1); SimpleDateFormat formatter1 = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); String strDate1 =
			 * formatter1.format(dt1); up.setTran_date(strDate1);
			 */
			hs.save(up);
			msg = "Added Successfully";
		} else if (formmode.equals("editT3A")) {

			T3ADataMaintenance up = t3ADataMaintenance;
			hs.saveOrUpdate(up);
			String sql = "Update BAML_RBS_REPORT_MAINTENANCE set REPORT_FLAG = 'Y' AND REPORT_DATE ="+rpt_date + " WHERE REPORT_CODE ='T1';";
		    System.out.println(sql);
		    Query query = hs.createSQLQuery(sql);
		    query.executeUpdate();
			msg = "Edited Successfully";

		} else if (formmode.equals("verifyT3A")) {
			T3ADataMaintenance up = t3ADataMaintenance;
			hs.saveOrUpdate(up);
			Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T3");
			BAML_RBS_REPORT_PROCEDURE up1 = account.get();
			up1.setReport_flag('Y');
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			up1.setReport_date(dt1);
			bAML_RBS_PROCEDURE_REP.save(up1);
			msg = "Verified Successfully";

		} else if (formmode.equals("deleteT3A")) {
			T3ADataMaintenance up = t3ADataMaintenance;
			hs.delete(up);
			Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T3");
			BAML_RBS_REPORT_PROCEDURE up1 = account.get();
			up1.setReport_flag('Y');
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
			up.setReport_date(dt1);
			bAML_RBS_PROCEDURE_REP.save(up1);
			msg = "Deleted Successfully";

		}
		return msg;
	}

	public String dataMaintenance(String formmode, String rpt_code, String rpt_date, String tran_date,
			T1DataMaintenance t1CurProdDetail, T12DataMaintenance t12DataMaintenance,
			T8DataMaintenance t8DataMaintenance, T9DataMaintenance t9DataMaintenance,
			T10DataMaintenance t10DataMaintenance, T14DetailMaintenance t14DataMaintenance,
			T15DataMaintenance t15DataMaintenance, T18DataMaintenance t18DataMaintenance) throws ParseException {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("add")) {

			if (rpt_code.equals("T1")) {

				T1DataMaintenance up1 = t1CurProdDetail;
				up1.setEntity_flg('N');
				up1.setModify_flg('N');
				up1.setDel_flg('N');
				hs.save(up1);
				
				msg = "Added Successfully";

			} else if (rpt_code.equals("T8")) {

				T8DataMaintenance up2 = t8DataMaintenance;
				up2.setEntity_flg('N');
				up2.setModify_flg('N');
				up2.setDel_flg('N');
				hs.save(up2);
				msg = "Added Successfully";

			} else if (rpt_code.equals("T9")) {

				T9DataMaintenance up3 = t9DataMaintenance;
				up3.setEntity_flg('N');
				up3.setModify_flg('N');
				up3.setDel_flg('N');
				hs.save(up3);
				msg = "Added Successfully";

			} else if (rpt_code.equals("T10")) {

				T10DataMaintenance up4 = t10DataMaintenance;
				up4.setEntity_flg('N');
				up4.setModify_flg('N');
				up4.setDel_flg('N');
				hs.save(up4);
				msg = "Added Successfully";

			} else if (rpt_code.equals("T12")) {

				T12DataMaintenance up5 = t12DataMaintenance;
				up5.setEntity_flg('N');
				up5.setModify_flg('N');
				up5.setDel_flg('N');
				hs.save(up5);
				msg = "Added Successfully";

			} else if (rpt_code.equals("T14")) {

				T14DetailMaintenance up6 = t14DataMaintenance;
				up6.setEntity_flg('N');
				up6.setModify_flg('N');
				up6.setDel_flg('N');
				hs.save(up6);
				msg = "Added Successfully";

			} else if (rpt_code.equals("T15")) {

				T15DataMaintenance up7 = t15DataMaintenance;
				up7.setEntity_flg('N');
				up7.setModify_flg('N');
				up7.setDel_flg('N');
				hs.save(up7);
				msg = "Added Successfully";

			} else if (rpt_code.equals("T18")) {

				T18DataMaintenance up8 = t18DataMaintenance;
				up8.setEntity_flg('N');
				up8.setModify_flg('N');
				up8.setDel_flg('N');
				hs.save(up8);
				
				msg = "Added Successfully";

			}
		} else if (formmode.equals("edit")) {

			if (rpt_code.equals("T1")) {

				T1DataMaintenance up1 = t1CurProdDetail;
				up1.setEntity_flg('N');
				up1.setModify_flg('Y');
				up1.setDel_flg('N');
				// t1CurProdServicesRepo.save(up1);
				hs.saveOrUpdate(up1);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T1");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);

				AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL(); 
				BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
						.getSingleResult();
				String modi = "RECORD EDITED :";
				modi = modi+"TABLE-1 : TRANSACTION ID : "+up1.getTran_id()+", PART TRAN ID : "+up1.getPart_tran_id()+" are Modified";
				audit.setAudit_date(new Date());
				audit.setEntry_user(up1.getEntry_user());
				audit.setFunc_code("RECORD MODIFIED");
				audit.setRemarks("MODIFIED");
				audit.setAudit_table("T1_");
				audit.setAudit_screen("RBS REPORTS");
				audit.setEvent_id(up1.getEntry_user());
				audit.setEvent_name("TABLE-1 : TRANSACTION ID : "+up1.getTran_id());
				audit.setModi_details(modi);
				audit.setEntry_user(up1.getEntry_user());
				audit.setEntry_time(new Date());
				audit.setAudit_ref_no(Number.toString());
				auditLocal.save(audit);

				msg = "Edited Successfully";
			} else if (rpt_code.equals("T8")) {

				T8DataMaintenance up2 = t8DataMaintenance;
				// T8DetailRepo
				up2.setEntity_flg('N');
				up2.setModify_flg('Y');
				up2.setDel_flg('N');
				hs.saveOrUpdate(up2);
				msg = "Edited Successfully";

			} else if (rpt_code.equals("T9")) {

				T9DataMaintenance up3 = t9DataMaintenance;
				up3.setEntity_flg('N');
				up3.setModify_flg('Y');
				up3.setDel_flg('N');
				hs.saveOrUpdate(up3);
				msg = "Edited Successfully";

			} else if (rpt_code.equals("T10")) {

				T10DataMaintenance up4 = t10DataMaintenance;
				up4.setEntity_flg('N');
				up4.setModify_flg('Y');
				up4.setDel_flg('N');
				hs.saveOrUpdate(up4);
				msg = "Edited Successfully";

			} else if (rpt_code.equals("T12")) {

				T12DataMaintenance up5 = t12DataMaintenance;
				up5.setEntity_flg('N');
				up5.setModify_flg('Y');
				up5.setDel_flg('N');
				hs.saveOrUpdate(up5);
				msg = "Edited Successfully";

			} else if (rpt_code.equals("T14")) {

				T14DetailMaintenance up6 = t14DataMaintenance;
				up6.setEntity_flg('N');
				up6.setModify_flg('Y');
				up6.setDel_flg('N');
				hs.saveOrUpdate(up6);
				msg = "Edited Successfully";

			} else if (rpt_code.equals("T15")) {

				T15DataMaintenance up7 = t15DataMaintenance;
				up7.setEntity_flg('N');
				up7.setModify_flg('Y');
				up7.setDel_flg('N');
				hs.saveOrUpdate(up7);
				msg = "Edited Successfully";

			} else if (rpt_code.equals("T18")) {

				T18DataMaintenance up7 = t18DataMaintenance;
				up7.setEntity_flg('N');
				up7.setModify_flg('Y');
				up7.setDel_flg('N');
				hs.saveOrUpdate(up7);
				msg = "Edited Successfully";

			}

		} else if (formmode.equals("verify")) {

			if (rpt_code.equals("T1")) {

				T1DataMaintenance up1 = t1CurProdDetail;
				up1.setEntity_flg('Y');
				up1.setModify_flg('Y');
				up1.setDel_flg('N');
				// t1CurProdServicesRepo.save(up1);
				hs.saveOrUpdate(up1);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T1");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";
			} else if (rpt_code.equals("T8")) {

				T8DataMaintenance up2 = t8DataMaintenance;
				up2.setEntity_flg('Y');
				up2.setModify_flg('Y');
				up2.setDel_flg('N');
				// T8DetailRepo
				hs.saveOrUpdate(up2);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T8");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";

			} else if (rpt_code.equals("T9")) {

				T9DataMaintenance up3 = t9DataMaintenance;
				up3.setEntity_flg('Y');
				up3.setModify_flg('Y');
				up3.setDel_flg('N');
				hs.saveOrUpdate(up3);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T9");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";

			} else if (rpt_code.equals("T10")) {

				T10DataMaintenance up4 = t10DataMaintenance;
				up4.setEntity_flg('Y');
				up4.setModify_flg('Y');
				up4.setDel_flg('N');
				hs.saveOrUpdate(up4);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T10");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";

			} else if (rpt_code.equals("T12")) {

				T12DataMaintenance up5 = t12DataMaintenance;
				up5.setEntity_flg('Y');
				up5.setModify_flg('Y');
				up5.setDel_flg('N');
				hs.saveOrUpdate(up5);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T12");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";

			} else if (rpt_code.equals("T14")) {

				T14DetailMaintenance up6 = t14DataMaintenance;
				up6.setEntity_flg('Y');
				up6.setModify_flg('Y');
				up6.setDel_flg('N');
				hs.saveOrUpdate(up6);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T14");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";

			} else if (rpt_code.equals("T15")) {

				T15DataMaintenance up7 = t15DataMaintenance;
				up7.setEntity_flg('Y');
				up7.setModify_flg('Y');
				up7.setDel_flg('N');
				hs.saveOrUpdate(up7);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T15");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";

			} else if (rpt_code.equals("T18")) {
				

				T18DataMaintenance up7 = t18DataMaintenance;
				up7.setEntity_flg('Y');
				up7.setModify_flg('Y');
				up7.setDel_flg('N');
				hs.saveOrUpdate(up7);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T18");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Verified Successfully";

			}

		} else if (formmode.equals("delete")) {

			if (rpt_code.equals("T1")) {

				T1DataMaintenance up1 = t1CurProdDetail;
				// t1CurProdServicesRepo.save(up1);
				hs.delete(up1);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T1");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Delete Successfully";
			} else if (rpt_code.equals("T8")) {

				T8DataMaintenance up2 = t8DataMaintenance;
				// T8DetailRepo
				hs.delete(up2);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T8");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Deleted Successfully";

			} else if (rpt_code.equals("T9")) {

				T9DataMaintenance up3 = t9DataMaintenance;
				hs.delete(up3);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T9");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Deleted Successfully";

			} else if (rpt_code.equals("T10")) {

				T10DataMaintenance up4 = t10DataMaintenance;
				hs.delete(up4);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T10");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Deleted Successfully";

			} else if (rpt_code.equals("T12")) {

				T12DataMaintenance up5 = t12DataMaintenance;
				hs.delete(up5);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T12");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Deleted Successfully";

			} else if (rpt_code.equals("T14")) {

				T14DetailMaintenance up6 = t14DataMaintenance;
				hs.delete(up6);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T14");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Deleted Successfully";

			} else if (rpt_code.equals("T15")) {
				
                
				T15DataMaintenance up7 = t15DataMaintenance;
				hs.delete(up7);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T15");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				
				msg = "Deleted Successfully";

			} else if (rpt_code.equals("T18")) {

				T18DataMaintenance up7 = t18DataMaintenance;
				hs.delete(up7);
				Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T18");
				BAML_RBS_REPORT_PROCEDURE up = account.get();
				up.setReport_flag('Y');
				Date dt1;
				dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(rpt_date);
				up.setReport_date(dt1);
				bAML_RBS_PROCEDURE_REP.save(up);
				msg = "Deleted Successfully";

			}

		}
		return msg;
	}

}
