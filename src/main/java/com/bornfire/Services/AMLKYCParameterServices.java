package com.bornfire.Services;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.AML_KYC_Parameter;
import com.bornfire.entity.AML_KYC_Parameter_Repository;
import com.bornfire.entity.t6.T6Report;
import com.bornfire.entity.t6.T6ReportsRep;


@Service
@Transactional
@ConfigurationProperties("output")
public class AMLKYCParameterServices {
	@Autowired
	AML_KYC_Parameter_Repository kycParameterrep;
	
	@Autowired
	T6ReportsRep t6ReportsRep;
    @Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public String addPARAMETER(AML_KYC_Parameter alertparam,String srlno, String formmode) {
		// TODO Auto-generated method stub
		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		/* try { */
		 if (formmode.equals("Editlist")) {
			AML_KYC_Parameter up = alertparam;
			up.setSrl_no("01");
			up.setDel_flg("N");
			up.setModify_flg("Y");
			up.setEntity_flg("N");
			kycParameterrep.save(up);
			msg = "Parameter Edited Successfully";
		}  else if (formmode.equals("verify")) {

			T6Report qs =  (T6Report) sessionFactory.getCurrentSession().createQuery("from T6Report where report_date in (select max(report_date) from T6Report) ").getSingleResult();
			AML_KYC_Parameter up = alertparam;
			T6Report t6 = new T6Report(up);
			t6.setReport_date(qs.getReport_date());
			t6.setRep_period_from(qs.getRep_period_from());
			t6.setRep_period_to(qs.getRep_period_to());

			up.setSrl_no("01");
			up.setEntity_flg("Y");
			up.setDel_flg("N");
			kycParameterrep.save(up);
						
			t6ReportsRep.save(t6);
			//hs.saveOrUpdate(up);
			//hs.saveOrUpdate(t6);
			msg = "Parameter Verified Successfully and Updated To Summary Table";
		}
		return msg;
	}

	public AML_KYC_Parameter getreportcode(String srlno) {

		if (kycParameterrep.existsById(srlno)) {
			System.out.println(srlno);
			AML_KYC_Parameter up = kycParameterrep.findByIdCustom(srlno);

			return up;
		} else {
			return new AML_KYC_Parameter();
		}

	}

	/*public AML_KYC_Parameter getSrlNo1(String id) {

		if (kycParameterrep.existsById(id)) {
			System.out.println("getsrlno");
			MONITORINGPARAMETERENTIRY up = kycParameterrep.findByIdCustom(id);

			return up;
		} else {
			return new AML_KYC_Parameter();
		}
*/
	}