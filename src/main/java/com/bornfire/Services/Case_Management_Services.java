package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.AML_Case_List_Rep;
import com.bornfire.entity.AML_Cust_Case_Mgmt;
import com.bornfire.entity.BAML_Case_Sheet_Rep;
import com.bornfire.entity.BAML_Cust_Case_Docs;
import com.bornfire.entity.BAML_Cust_Case_Sheet;

@Service
@Transactional
@ConfigurationProperties("output")
public class Case_Management_Services {
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	AML_Case_List_Rep aml_Case_List_Rep;

	@Autowired
	BAML_Case_Sheet_Rep baml_Case_Sheet_Rep;
	private static final Logger logger = LoggerFactory.getLogger(Case_Management_Services.class);

	public String addCases(AML_Cust_Case_Mgmt aml_Cust_Case_Mgmt, BAML_Cust_Case_Sheet baml_Cust_Case_Sheet,
			BAML_Cust_Case_Docs baml_Cust_Case_Docs, String formmode) {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("edit")) {
			AML_Cust_Case_Mgmt up = aml_Cust_Case_Mgmt;
			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			aml_Case_List_Rep.save(up);
			msg = "Case Details Modified Sucessfully";
		} else if (formmode.equals("verify")) {
			AML_Cust_Case_Mgmt up = aml_Cust_Case_Mgmt;
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			up.setDel_flg("N");
			aml_Case_List_Rep.save(up);
			msg = "Case Details Verified  Sucessfully";
		}  else if (formmode.equals("addcomments")) {
			BAML_Cust_Case_Sheet up = baml_Cust_Case_Sheet;
			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			baml_Case_Sheet_Rep.save(up);
			msg = "Case Sheet Comments Added Sucessfully";
		}else if (formmode.equals("editcomments")) {
			BAML_Cust_Case_Sheet up = baml_Cust_Case_Sheet;
			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			baml_Case_Sheet_Rep.save(up);
			msg = "Case Sheet Comments Modified Sucessfully";
		} else if (formmode.equals("verifycomments")) {
			BAML_Cust_Case_Sheet up = baml_Cust_Case_Sheet;
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			up.setDel_flg("N");
			baml_Case_Sheet_Rep.save(up);
			msg = "Case Sheet Comments Verified  Sucessfully";
		} else if (formmode.equals("submit")) {
			System.out.println(formmode);

			BAML_Cust_Case_Docs up = baml_Cust_Case_Docs;

			hs.saveOrUpdate(up);

			msg = "Case Document Added Successfully";

		} else if (formmode.equals("caselist")) {
			AML_Cust_Case_Mgmt up = aml_Cust_Case_Mgmt;

			up.setDel_flg("N");
			aml_Case_List_Rep.save(up);
			msg = "Case Added";
		}
		return msg;
	}
	public String getSrlNoValue2() {
		 Session hs =sessionFactory.getCurrentSession(); 
		
				 DecimalFormat numformate = new  DecimalFormat("0");
		 BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT CASESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
		String serialno="CASE"+numformate.format(billNumber);
		System.out.println("billno" + serialno);
		 return serialno;
		}
	public String getSrlNoValue() {
		 Session hs =sessionFactory.getCurrentSession(); 
		
				 DecimalFormat numformate = new  DecimalFormat("00");
		 BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT CASESHEETSEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
		String serialno="CASESHEET"+numformate.format(billNumber);
		System.out.println("billno" + serialno);
		 return serialno;
		}
	public String getSrlNoValue1() {
		 Session hs =sessionFactory.getCurrentSession(); 
		
				 DecimalFormat numformate = new  DecimalFormat("00");
		 BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT CASEDOCSEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
		String serialno="CASEDOC"+numformate.format(billNumber);
		System.out.println("billno" + serialno);
		 return serialno;
		}
	public BAML_Cust_Case_Docs BlobImage(String custid) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(custid);
		@SuppressWarnings("unchecked")
		List<BAML_Cust_Case_Docs> query = (List<BAML_Cust_Case_Docs>) session
				.createQuery("from BAML_Cust_Case_Docs where cust_id=?1").setParameter(1, custid).getResultList();
		return query.get(0);

	};

	public AML_Cust_Case_Mgmt getCustId(String id) {

		if (aml_Case_List_Rep.existsById(id)) {
			System.out.println("getsrlno");
			AML_Cust_Case_Mgmt up = aml_Case_List_Rep.findByIdCustom(id);

			return up;
		} else {
			return new AML_Cust_Case_Mgmt();
		}

	};

}
