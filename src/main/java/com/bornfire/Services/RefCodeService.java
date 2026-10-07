package com.bornfire.Services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.BAML_AUDIT_REPOSITRY;
import com.bornfire.entity.RefCodeMasterEmbeddedID;
import com.bornfire.entity.RefCodeRepository;
import com.bornfire.entity.RefcodeEntity;

@Service
@ConfigurationProperties("output")
@Transactional
public class RefCodeService {

	@Autowired
	private RefCodeRepository refCodeRepository;

	@Autowired
	private BAML_AUDIT_REPOSITRY auditrep;

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	@Autowired
	SessionFactory sessionFactory;

	public String addPARAMETER(RefcodeEntity alertparam, String formmode, String refcode, String recordtype,
			String reportcode) {
		// TODO Auto-generated method stub
		System.out.println("alertparam ->" + alertparam);
		String msg = "";
		/*
		 * Optional<RefcodeEntity> ref= refCodeRepository.getValue(refcode,recordtype);
		 * ref.isPresent(); System.out.println(ref.isPresent()); RefcodeEntity reg =
		 * ref.get();
		 */Session hs = sessionFactory.getCurrentSession();
		BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
		RefcodeEntity ref = alertparam;
		/* try { */
		if (formmode.equals("add")) {
			// Optional<RefcodeEntity> reg = refCodeRepository.findById(new
			// RefCodeMasterEmbeddedID(recordtype,refcode,reportcode));

			// RefcodeEntity reg1= ref.get();
			System.out.println("+_+_+" + alertparam.getRefCodeId() + "+_+_+");
			ref.setRefCodeId(alertparam.getRefCodeId());
			ref.setDel_flg("N");
			ref.setEntity_flag("N");
			refCodeRepository.save(ref);

			audit.setAudit_date(new Date());
			audit.setEntry_user(ref.getEntry_user());
			audit.setFunc_code("RECORD CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("BAML_REFERENCE_CODE_TABLE");
			audit.setAudit_screen("REFERENCE CODE MASTER");
			audit.setEvent_id(ref.getRef_type());
			audit.setModi_details("RECORD CREATED");
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);
			msg = "Parameter Created Successfully";
		} else if (formmode.equals("edit")) {

			Optional<RefcodeEntity> reg = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype, refcode));
			if (reg.isPresent()) {

				RefcodeEntity reg1 = reg.get();
				System.out.println("old  =" + reg1.getModule() +" " +alertparam.getModule());
				if ((reg1.getModule().equals(alertparam.getModule()))
						&& (reg1.getRef_type().equals(alertparam.getRef_type()))
						&& (reg1.getRpt_code().equals(alertparam.getRpt_code()))
						&& (reg1.getRefCodeId().equals(alertparam.getRefCodeId()))
						&& (reg1.getRef_desc().equals(alertparam.getRef_desc()))) {
					msg = "No any Modification done";

				} else {
					String modi = "";

					
				
					if (!reg1.getModule().equals(alertparam.getModule())) {
						modi = modi + ("MODULE + " + reg1.getModule() + '+' + alertparam.getModule() + '+');
						
					} else if (!reg1.getRef_type().equals(alertparam.getRef_type())) {
						modi = modi +("REF_TYPE + " + reg1.getRef_type() + '+' + alertparam.getRef_type() + '+');
					} else if (!reg1.getRpt_code().equals(alertparam.getRpt_code())) {
						modi = modi +("REPORT_CODE + " + reg1.getRpt_code() + '+' + alertparam.getRpt_code() + '+');
					} else if (!reg1.getRef_desc().equals(alertparam.getRef_desc())) {
						modi = modi +("REF_TYPE + " + reg1.getRef_desc() + '+' + alertparam.getRef_desc() + '+');
					}
					System.out.println("number"+Number);
					audit.setAudit_ref_no(Number.toString());

					audit.setAudit_date(new Date());
					audit.setAudit_table("BAML_REFERENCE_CODE_TABLE");
					audit.setAudit_screen("REFERENCE CODE MASTER");
					audit.setEntry_user(ref.getModify_user());
					audit.setFunc_code("RECORD MODIFIED");
					audit.setRemarks("MODIFIED");
					audit.setEvent_id(ref.getRef_type());
					audit.setModi_details(modi);
					System.out.println(modi);
					auditLocal.save(audit);
					alertparam.setDel_flg("N");
					alertparam.setModify_flag("Y");
					alertparam.setEntity_flag("N");
					/*reg1.setModule(alertparam.getModule());
					reg1.setRef_type(alertparam.getRef_type());
					reg1.setRpt_code(alertparam.getRpt_code());
					reg1.setRefCodeId(alertparam.getRefCodeId());
					reg1.setRpt_desc(alertparam.getRpt_desc());
					reg1.setModify_user(alertparam.getModify_user());
					reg1.setModify_time(alertparam.getModify_time());*/
					refCodeRepository.save(alertparam);
					msg = "Parameter Edited Successfully";
				}
			}

		} else if (formmode.equals("delete")) {

			Optional<RefcodeEntity> reg = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype, refcode));
			if (reg.isPresent()) {
				RefcodeEntity reg1 = reg.get();
				reg1.setDel_flg("Y");
				reg1.setEntity_flag("N");
				refCodeRepository.save(reg1);
				audit.setAudit_date(new Date());
				audit.setAudit_table("BAML_REFERENCE_CODE_TABLE");
				audit.setAudit_screen("REFERENCE CODE MASTER");
				audit.setEntry_user(ref.getModify_user());
				audit.setFunc_code("RECORD DELETED");
				audit.setRemarks("DELETED");
				audit.setEvent_id(ref.getRef_type());
				audit.setModi_details("Del_flg + N + Y +");
				audit.setAudit_ref_no(Number.toString());
				auditLocal.save(audit);

			}
			msg = "Parameter Deleted Successfully";
		} else if (formmode.equals("verify")) {
			Optional<RefcodeEntity> reg = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype, refcode));
			RefcodeEntity reg1 = reg.get();
			reg1.setDel_flg("N");
			reg1.setEntity_flag("Y");
			reg1.setVerify_user(alertparam.getVerify_user());
			reg1.setVerify_time(alertparam.getVerify_time());
			refCodeRepository.save(reg1);
			audit.setAudit_date(new Date());
			audit.setAudit_table("BAML_REFERENCE_CODE_TABLE");
			audit.setAudit_screen("REFERENCE CODE MASTER");
			audit.setEntry_user(ref.getModify_user());
			audit.setFunc_code("RECORD VERIFIED");
			audit.setRemarks("VERIFIED");
			audit.setEvent_id(ref.getRef_type());
			audit.setModi_details("ENTITY_FLAG + N + Y +");
			audit.setAuth_user(alertparam.getVerify_user());
			audit.setAuth_time(new Date());
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);

			msg = "Parameter Verified Successfully";

		}
		return msg;
	}

	public RefcodeEntity getRefcode(String id, String rectype) {

		System.out.println("getrefcode" + id + "" + rectype + "");
		Session session = sessionFactory.getCurrentSession();
		Query<RefcodeEntity> query = session.createQuery(
				" from RefcodeEntity where ref_code=?1 and ref_rec_type=?2  ", RefcodeEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, rectype);

		List<RefcodeEntity> result = query.getResultList();

		if (!result.isEmpty()) {

			return result.get(0);
		} else {

			return new RefcodeEntity();
		}

	};

	public String deleteParameter(String refcode, String recordtype, String reportcode) {
		System.out.println("hhhhhh");
		String msg = "";
		Optional<RefcodeEntity> user = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype, refcode));

		RefcodeEntity reg = user.get();
		reg.setDel_flg("Y");

		msg = "Reference Code Deleted Successfully";
		return msg;
	}

	public String verifysrlno(String refcode, String recordtype, String reportcode) {
		System.out.println("hhhhhh");
		String msg = "";
		Optional<RefcodeEntity> user = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype, refcode));

		RefcodeEntity reg = user.get();
		reg.setDel_flg("N");
		/* refCodeRepository.save(reg); */
		msg = "User Deleted Successfully";
		return msg;
	}

	@SuppressWarnings("unchecked")
	public String getReportCodedesc(String repcode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(
				"select distinct(rule_code_desc) from BAML_REFERENCE_CODE_TABLE where rule_code=?1  ");
		query.setParameter(1, repcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getRecordTypedesc(String repcode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select distinct(rec_desc) from RECORD_TYPE_TABLE where ref_rec_type=?1  ");
		query.setParameter(1, repcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getReferenceCodedesc(String rectype, String refcode) {
		System.out.println(rectype);
		System.out.println(refcode);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(
				"select distinct(ref_desc) from BAML_REFERENCE_CODE_TABLE where ref_rec_type=?1 and ref_code=?2 ");
		query.setParameter(1, rectype);
		query.setParameter(2, refcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getReferenceType(String rectype, String refcode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(
				"select distinct ref_type from BAML_REFERENCE_CODE_TABLE where ref_rec_type=?1 and ref_code=?2");
		query.setParameter(1, rectype);
		query.setParameter(2, refcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RefcodeEntity> getReferenceCodeselect(String rulecode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RefcodeEntity where ref_rec_type=?1 ", RefcodeEntity.class);
		query.setParameter(1, rulecode);

		List<RefcodeEntity> result = query.getResultList();

		System.out.println(result);
		return result;
	}
	


}
