package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.RuleEngineEntity;
import com.bornfire.entity.RuleEngineRepository;

@Service
@ConfigurationProperties("output")
@Transactional

public class LoginServices {

	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);
	@Autowired
	RuleEngineRepository ruleenginerepository;
	@Autowired
	SessionFactory sessionFactory;

	public String addRule(RuleEngineEntity ruleengine, String formmode, String inputSrlNo) {
		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
  
		/* try { */

		if (formmode.equals("add")) {

			RuleEngineEntity up = ruleengine;
			/*
			 * try { String encryptedPassword =
			 * PasswordEncryption.getEncryptedPassword(this.password);
			 * 
			 * if (up.getLogin_status().equals("Active")) { up.setUser_locked_flg("N"); }
			 * else { up.setUser_locked_flg("Y"); }
			 * 
			 * if (up.getUser_status().equals("Active")) { up.setDisable_flg("N"); } else {
			 * up.setDisable_flg("Y"); }
			 * 
			 * up.setEntity_flg("N"); up.setEntry_time(new Date());
			 * up.setEntry_user(inputUser);
			 * 
			 * up.setLogin_flg("N");//To prompt the user for changing the password at first
			 * login up.setNo_of_attmp(0); up.setPassword(encryptedPassword);
			 * 
			 * } catch (Exception e) { e.printStackTrace(); }
			 */
			up.setDel_flg("N");
			ruleenginerepository.save(up);

			msg = "User Created Successfully";

		}
		// When the user data modifed and submitted.
		
		  else if (formmode.equals("edit")) {
				RuleEngineEntity up = ruleengine;
			/*
			 * Optional<RuleEngineEntity> up
			 * =RuleEngineEntity.findById(RuleEngineEntity.getSrl_no());
			 */
		  
			/* if (up.isPresent()) { */
		  
		  
		 /* userProfile.setPassword(up.get().getPassword());
		  
		  if (userProfile.getLogin_status().equals("Active")) {
		 
		 userProfile.setUser_locked_flg("N"); } else {
		 userProfile.setUser_locked_flg("Y"); }
		  
		  if (userProfile.getUser_status().equals("Active")) {
		  userProfile.setDisable_flg("N"); } else { userProfile.setDisable_flg("Y"); }
		  
		  userProfile.setNo_of_attmp(0); userProfile.setEntity_flg("N");
		  userProfile.setModify_user(inputUser); userProfile.setModify_time(new
		  Date());*/
		  
		    up.setDel_flg("N");
			ruleenginerepository.save(up);/* } */
		  
		  msg = "User Edited Successfully";
		  
		  }
		
		  else if (formmode.equals("delete")) {
			 
		  
		  msg = "User Deleted Successfully";
		  
		  }
		 

		return msg;
	}
	
	public String getSrlNoValue() {
		 Session hs =sessionFactory.getCurrentSession(); 
		
				 DecimalFormat numformate = new  DecimalFormat("000");
		 BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
		String serialno="RLE"+numformate.format(billNumber);
		System.out.println("billno" + serialno);
		 return serialno;
		}
	
	public RuleEngineEntity getSrlNo(String id) {
		/* logger.info(id); */
		if (ruleenginerepository.existsById(id)) {
			System.out.println("getsrlno");
			RuleEngineEntity up = ruleenginerepository.findById(id).get();
			/* logger.info(up.getEntity_flg()); */
			/*
			 * DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			 * System.out.println(up.getActivated_date().SimpleDateFormat("dd-MM-yyyy"));
			 */
			
			/* up.setRule_date(sdf.parse(up.getRule_date())).toString(); */
		
			
			/*
			 * 
			 * sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.CHINESE);
			 */
			return up;
		} else {
			return new RuleEngineEntity();
		}

	};
	
	
	public String deleterule(String inputSrlNo) {
		String msg = "";
		 Optional<RuleEngineEntity> user= ruleenginerepository.findById(inputSrlNo);
			RuleEngineEntity reg = user.get();       
			reg.setDel_flg("Y");
			ruleenginerepository.save(reg);
			  msg = "User Deleted Successfully";
		return msg;
	}

}
/* } */