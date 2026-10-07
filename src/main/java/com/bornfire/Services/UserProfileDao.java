package com.bornfire.Services;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.PasswordEncryption;
import com.bornfire.entity.AMLSession;
import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;
import com.bornfire.entity.FinUserProfileRep;
import com.bornfire.entity.UserProfile;
import com.bornfire.entity.UserProfileModEn;
import com.bornfire.entity.UserProfileRep;

import net.sf.jasperreports.engine.JRException;
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
public class UserProfileDao {

	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);

	@Autowired
	UserProfileRep userProfileRep;

	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Autowired
	FinUserProfileRep finUserProfileRep;
	
	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;


	@Autowired
	AlertManagementRepository alertrep;
	
	@Autowired
	EMAILREP emailRep;

	@Value("${default.password}")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/*
	 * Getting 3 inputs -
	 * 
	 * UserProfile Object, Formmode - Valid values : add, edit, inputuser - user who
	 * edited the data
	 * 
	 * if formmode is add - Get password from application.properties create the user
	 * 
	 * if formmode is edit - Get password from database for that user and use other
	 * fields came from front end.
	 * 
	 * 
	 */
	
	public String cancelUserentity(UserProfile userProfile,String inputUser) {
		String msg = "";
		Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());
		if (up.isPresent()) {
			userProfile.setPassword(up.get().getPassword());

			if (userProfile.getLogin_status().equals("Active")) {
				userProfile.setUser_locked_flg("N");
			} else {
				userProfile.setUser_locked_flg("Y");
			}

			if (userProfile.getUser_status().equals("Active")) {
				userProfile.setDisable_flg("N");
			} else {
				userProfile.setDisable_flg("Y");
			}
			
			UserProfile user = up.get();
			System.out.println("+++++++++++++++++++++++" +user);

			user.setNo_of_attmp(0);
			user.setEntity_flg("Y");
			user.setModify_user(inputUser);
			user.setModify_time(new Date());
			user.setLogin_flg("Y");
			

			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(user);
		}
		return msg;
	}

	public String addUserentity(UserProfile userProfile, UserProfileModEn USERMOD,String formmode,String inputUser,String user1) throws ParseException {
		String msg = "";
		String count=userProfileRep.getusercount(user1);
		Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());
		

	
		
		if(count.equals("1")) {
			UserProfile up1 = up.get();
			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			String acctexpold = dateFormat.format(up1.getAcc_exp_date());
			String acctexpnew = dateFormat.format(USERMOD.getAcc_exp_date());
			String disableSold = dateFormat.format(up1.getDisable_start_date());
			String disableSnew = dateFormat.format(USERMOD.getDisable_start_date());
			String disableEold = dateFormat.format(up1.getDisable_end_date());
			String disableEnew = dateFormat.format(USERMOD.getDisable_end_date());
			String passOLD = dateFormat.format(up1.getPass_exp_date());
			String passNEW = dateFormat.format(USERMOD.getPass_exp_date());

			if((up1.getEmail_id().equals(USERMOD.getEmail_id())) && (up1.getMob_number().equals(USERMOD.getMob_number()))
					&& (up1.getLogin_low().equals(USERMOD.getLogin_low())) && (up1.getLogin_high().equals(USERMOD.getLogin_high()))
					&& (acctexpold.equals(acctexpnew))
					&& (disableSold.equals(disableSnew))
					&& (disableEold.equals(disableEnew))
					&& (passOLD.equals(passNEW))
					&& (up1.getUser_status().equals(USERMOD.getUser_status())) && (up1.getLogin_status().equals(USERMOD.getLogin_status()))
					&& (up1.getRemark().equals(USERMOD.getRemark())) && (up1.getRole_id().equals(USERMOD.getRole_id()))) {
				
			}else {
		if (up.isPresent()) {
			userProfile.setPassword(up.get().getPassword());

			if (userProfile.getLogin_status().equals("Active")) {
				userProfile.setUser_locked_flg("N");
			} else {
				userProfile.setUser_locked_flg("Y");
			}

			if (userProfile.getUser_status().equals("Active")) {
				userProfile.setDisable_flg("N");
			} else {
				userProfile.setDisable_flg("Y");
			}
			
			UserProfile user = up.get();
			System.out.println("+++++++++++++++++++++++" +user);

			user.setNo_of_attmp(0);
			user.setEntity_flg("N");
			user.setModify_user(inputUser);
			user.setModify_time(new Date());
			user.setLogin_flg("Y");
			

			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(user);
			
			String modi = "";

			BigDecimal Number = (BigDecimal) session.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			BigDecimal EMAIL = (BigDecimal) session.createNativeQuery("SELECT EMAILSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();

			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			
			if (!up1.getEmail_id().equals(USERMOD.getEmail_id())) {
				modi = modi + ("EMAIL ID  + " + up1.getEmail_id() + '+' + USERMOD.getEmail_id() + '+');
				
			} else if (!up1.getMob_number().equals(USERMOD.getMob_number())) {
				modi = modi + ("MOBILE NUMBER  + " + up1.getMob_number() + '+' + USERMOD.getMob_number() + '+');	
			}else if (!up1.getLogin_low().equals(USERMOD.getLogin_low())) {
				modi = modi + ("LOGIN_LOW  + " + up1.getLogin_low() + '+' + USERMOD.getLogin_low() + '+');	
			}else if (!up1.getLogin_high().equals(USERMOD.getLogin_high())) {
				modi = modi + ("LOGIN_HIGH  + " + up1.getLogin_high() + '+' + USERMOD.getLogin_high() + '+');	
			}else if (up1.getRemark() != null && USERMOD.getRemark() !=null && !up1.getRemark().equals(USERMOD.getRemark())) {
				modi = modi + ("REMARKS  + " + up1.getRemark() + '+' + USERMOD.getRemark() + '+');	
			}else if (!up1.getRole_id().equals(USERMOD.getRole_id())) {
				modi = modi + ("ROLE_ID  + " + up1.getRole_id() + '+' + USERMOD.getRole_id() + '+');	
			}else if (!up1.getUser_status().equals(USERMOD.getUser_status())) {
				modi = modi + ("USER_STATUS  + " + up1.getUser_status() + '+' + USERMOD.getUser_status() + '+');	
			}else if (!up1.getLogin_status().equals(USERMOD.getLogin_status())) {
				modi = modi + ("USER_STATUS  + " + up1.getLogin_status() + '+' + USERMOD.getLogin_status() + '+');	
			}else if(!acctexpold.equals(acctexpnew)) {
				modi = modi + ("ACCT_EXP_DATE  + " + acctexpold + '+' + acctexpnew + '+');	
			}else if(!passOLD.equals(passNEW)) {
				modi = modi + ("PASSWORD_EXP_DATE  + " + passOLD + '+' + passNEW + '+');	
			}else if(!disableSold.equals(disableSnew)) {
				modi = modi + ("DISABLE_START_DATE  + " + disableSold + '+' + disableSnew + '+');	
			}else if(!disableEold.equals(disableEnew)) {
				modi = modi + ("USER_STATUS  + " + disableEold + '+' + disableEnew + '+');	
			}
			System.out.println("number"+Number);
			audit.setAudit_ref_no(Number.toString());

			audit.setAudit_date(new Date());
			audit.setAudit_table("BAML_USER_PROFILE_TABLE");
			audit.setAudit_screen("USER PROFILE MAINTENANCE");
			audit.setEntry_user(inputUser);
			audit.setEntry_time(new Date());
			audit.setFunc_code("USER MODIFIED");
			audit.setRemarks("MODIFIED");
			audit.setEvent_id(userProfile.getUserid());
			audit.setEvent_name("MODIFIED");
			audit.setModi_details(modi);
			System.out.println(modi);
			auditLocal.save(audit);
			EmailAlert EA = new EmailAlert();
			String alertcode = "USER-MODIFY";
			AlertManagementEntity  AM= alertrep.getalertdetail(alertcode);
			if(AM!=null && AM.getEmail_flg()!=null && AM.getEmail_flg().equals("Y")) {
				EA.setEmail_id(AM.getEmail_1());
				EA.setEmail_id_cc1(AM.getEmail_2());
				EA.setEmail_id_cc2(AM.getEmail_3());
				EA.setEmail_sub(AM.getParam_1());
				EA.setEmail_body("USER MODIFIED SUCCESSFULLY...., USER_ID :"+up1.getUserid()+", USER_NAME :"+up1.getUsername()+". CHANGED PARAMETERS ARE "+modi);
				EA.setEmail_date(new Date());
				EA.setEmail_srl_no(EMAIL);
				EA.setSend_flg("N");
				emailRep.save(EA);
			}
		}
		}}
		return msg;
	}
	public String addUser(UserProfile userProfile, String formmode, String inputUser) {

		String msg = "";

		try {

			if (formmode.equals("add")) {

				UserProfile up = userProfile;
				try {
					String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);

					if (up.getLogin_status().equals("Active")) {
						up.setUser_locked_flg("N");
					} else {
						up.setUser_locked_flg("Y");
					}

					if (up.getUser_status().equals("Active")) {
						up.setDisable_flg("N");
					} else {
						up.setDisable_flg("Y");
					}

					up.setEntity_flg("N");
					up.setEntry_time(new Date());
					up.setEntry_user(inputUser);
					
					up.setLogin_flg("N");//To prompt the user for changing the password at first login
					up.setNo_of_attmp(0);
					up.setPassword(encryptedPassword);

				} catch (Exception e) {
					e.printStackTrace();
				}

				
				userProfileRep.save(up);

				msg = "User Created Successfully";

			}
			//When the user data modifed and submitted.
			else {

				Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());

				if (up.isPresent()) {

					userProfile.setPassword(up.get().getPassword());

					if (userProfile.getLogin_status().equals("Active")) {
						userProfile.setUser_locked_flg("N");
					} else {
						userProfile.setUser_locked_flg("Y");
					}

					if (userProfile.getUser_status().equals("Active")) {
						userProfile.setDisable_flg("N");
					} else {
						userProfile.setDisable_flg("Y");
					}

					userProfile.setNo_of_attmp(0);
					userProfile.setEntity_flg("N");
					userProfile.setModify_user(inputUser);
					userProfile.setModify_time(new Date());

					userProfileRep.save(userProfile);
				}

				msg = "User Edited Successfully";

			}
		} catch (Exception e) {
			msg = "Error Occured. Please contact Administrator";
			e.printStackTrace();
			logger.info(e.getMessage());
		}

		return msg;
	}
	
	public List<UserProfile> getUsersListfshg(){
		Query q1;
		Session hs = sessionFactory.getCurrentSession();
		q1 = hs.createNativeQuery(
				"select * from BAML_USER_PROFILE_TABLE union all SELECT * FROM Baml_user_profile_mod_table ");
		List<UserProfile> result1 = q1.getResultList();
		return result1;
	}

	public List<Object> getUsersList() {
		
		Session hs = sessionFactory.getCurrentSession();
		
		Query<Object[]> q1;
		
		Query<Object[]> q2;
		
		List<Object> userProfileList = new ArrayList<Object>();
		
		q1 = hs.createNativeQuery("select * from BAML_USER_PROFILE_TABLE where entity_flg ='Y' and del_flg ='N' order by USER_ID");
		
		q2 = hs.createNativeQuery("select * from BAML_USER_PROFILE_MOD_TABLE WHERE entity_flg ='N'  ");
		
		List<Object[]> result1 = q1.getResultList();
		List<Object[]> result2 = q2.getResultList();
		
		for (Object[] a : result1) {
			String bank_code = (String) a[0];
			String bank_name = (String) a[1];
			String branch_code = (String) a[2];
			String branch_name = (String) a[3];
			String emp_id = (String) a[4];
			String emp_name = (String) a[5];
			String user_id = (String) a[6];
			String user_name = (String) a[7];
			String inactive_time = (String) a[8];
			Date acc_exp_date = (Date) a[9];
			String login_low = (String) a[10];
			String login_high = (String) a[11];
			Date disable_start_date = (Date) a[12];
			Date disable_end_date = (Date) a[13];
			String password = (String) a[14];
			Date pass_exp_date = (Date) a[15];
			String user_status = (String) a[16];
			String login_status = (String) a[17];
			
			
			Character virtual_flg = (Character) a[18];
			String virtualFlg = String.valueOf(virtual_flg);
			
			
			
			String work_class = (String) a[19];
			BigDecimal mob_number = (BigDecimal) a[20];
			String mobNum =mob_number.toString();
			
			
			String email_id = (String) a[21];
			String role_id = (String) a[22];
			String role_desc = (String) a[23];
			String permissions = (String) a[24];
			String per_effctive_date = (String) a[25];
			String admin = (String) a[26];
			String xbrl_configuration = (String) a[27];
			String xbrl_report = (String) a[28];
			String scheduler = (String) a[29];
			String execution = (String) a[30];
			String mis_reports = (String) a[31];
			String xml_reports = (String) a[32];
			String archivel = (String) a[33];
			String general_inq = (String) a[34];
			String audit_inq = (String) a[35];
			String channel = (String) a[36];
			String entry_user = (String) a[37];
			Date entry_time = (Date) a[38];
			String auth_user = (String) a[39];
			Date auth_time = (Date) a[40];
			String modify_user = (String) a[41];
			Date modify_time = (Date) a[42];
			Character entity_flg = (Character) a[43];
			String entityFlag = String.valueOf(entity_flg);
			
			String auth_flg = (String) a[44];
			Character modify_flg = (Character) a[45];
			String modifyFlg = String.valueOf(modify_flg);
			
			
			Character del_flg = (Character) a[46];
			String delFlg = String.valueOf(del_flg);
			
			
			String session_id = (String) a[47];
			
			
			Character login_flg = (Character) a[48];
			String loginFlag = String.valueOf(login_flg);
			
			
			Character user_locked_flg = (Character) a[49];
			String userLockedFlag = String.valueOf(user_locked_flg);
			
			
			BigDecimal no_of_attmp = (BigDecimal) a[50];
			Integer noOfAttmp = no_of_attmp.intValue();
			
			
			Character disable_flg = (Character) a[51];
			String disableFlag = String.valueOf(disable_flg);
			
			
			String domain_id = (String) a[53];
			
			Character new_user_flg = (Character) a[54];
			String newUserFlag = String.valueOf(new_user_flg);
			
			String remark = (String) a[55];
			
			UserProfile userProfile = new UserProfile(bank_code, bank_name, branch_code, branch_name, emp_id, emp_name, 
					user_id, user_name, inactive_time, acc_exp_date, login_low, login_high, disable_start_date, disable_end_date, 
					password, pass_exp_date, user_status, login_status, virtualFlg, work_class, mobNum, email_id, role_id,
					role_desc, permissions, per_effctive_date, admin, xbrl_configuration, xbrl_report, scheduler, execution,
					mis_reports, xml_reports, archivel, general_inq, audit_inq, channel, entry_user, entry_time, auth_user, 
					auth_time, modify_user, modify_time, entityFlag, auth_flg, modifyFlg, delFlg, session_id, loginFlag, 
					userLockedFlag, noOfAttmp, disableFlag, domain_id, newUserFlag,remark);
			
			

			userProfileList.add(userProfile);
			
			
		}
		
		
		for (Object[] a : result2) {
			String bank_code = (String) a[0];
			String bank_name = (String) a[1];
			String branch_code = (String) a[2];
			String branch_name = (String) a[3];
			String emp_id = (String) a[4];
			String emp_name = (String) a[5];
			String user_id = (String) a[6];
			String user_name = (String) a[7];
			String inactive_time = (String) a[8];
			Date acc_exp_date = (Date) a[9];
			String login_low = (String) a[10];
			String login_high = (String) a[11];
			Date disable_start_date = (Date) a[12];
			Date disable_end_date = (Date) a[13];
			String password = (String) a[14];
			Date pass_exp_date = (Date) a[15];
			String user_status = (String) a[16];
			String login_status = (String) a[17];
			
			Character virtual_flg = (Character) a[18];
			String virtualFlg = String.valueOf(virtual_flg);
			
			String work_class = (String) a[19];
			BigDecimal mob_number = (BigDecimal) a[20];
			String mobNum =mob_number.toString();
			
			
			String email_id = (String) a[21];
			String role_id = (String) a[22];
			String role_desc = (String) a[23];
			String permissions = (String) a[24];
			String per_effctive_date = (String) a[25];
			String admin = (String) a[26];
			String xbrl_configuration = (String) a[27];
			String xbrl_report = (String) a[28];
			String scheduler = (String) a[29];
			String execution = (String) a[30];
			String mis_reports = (String) a[31];
			String xml_reports = (String) a[32];
			String archivel = (String) a[33];
			String general_inq = (String) a[34];
			String audit_inq = (String) a[35];
			String channel = (String) a[36];
			String entry_user = (String) a[37];
			Date entry_time = (Date) a[38];
			String auth_user = (String) a[39];
			Date auth_time = (Date) a[40];
			String modify_user = (String) a[41];
			Date modify_time = (Date) a[42];
			Character entity_flg = (Character) a[43];
			
			String entityFlag = String.valueOf(entity_flg);
			
			String auth_flg = (String) a[44];
			Character modify_flg = (Character) a[45];
			String modifyFlg = String.valueOf(modify_flg);
			
			
			Character del_flg = (Character) a[46];
			String delFlg = String.valueOf(del_flg);
			String session_id = (String) a[47];
			Character login_flg = (Character) a[48];
			String loginFlag = String.valueOf(login_flg);
			
			Character user_locked_flg = (Character) a[49];
			String userLockedFlag = String.valueOf(user_locked_flg);
			
			BigDecimal no_of_attmp = (BigDecimal) a[50];
			Integer noOfAttmp = no_of_attmp.intValue();
			
			
			
			Character disable_flg = (Character) a[51];
			String disableFlag = String.valueOf(disable_flg);
			
			String domain_id = (String) a[53];
			
			Character new_user_flg = (Character) a[54];
			String newUserFlag = String.valueOf(new_user_flg);
			
			String remark = (String) a[55];
			UserProfileModEn userProfileModEn = new UserProfileModEn(bank_code, bank_name, branch_code, branch_name, emp_id, emp_name,
					user_id, user_name, inactive_time, acc_exp_date, login_low, login_high, disable_start_date, disable_end_date,
					password, pass_exp_date, user_status, login_status, virtualFlg, work_class, mobNum, email_id, role_id, 
					role_desc, permissions, per_effctive_date, admin, xbrl_configuration, xbrl_report, scheduler, execution, 
					mis_reports, xml_reports, archivel, general_inq, audit_inq, channel, entry_user, entry_time, auth_user, 
					auth_time, modify_user, modify_time, entityFlag, auth_flg, modifyFlg, delFlg, session_id, loginFlag, 
					userLockedFlag, noOfAttmp, disableFlag, newUserFlag, domain_id,remark);
			
		
			
			userProfileList.add(userProfileModEn);

			
		}
		


		Iterable<UserProfile> users = userProfileRep.findAll();
		
		return userProfileList;

	}

	
public List<Object> getUsersListsearch( String Userid) {
		
		Session hs = sessionFactory.getCurrentSession();
		
		Query<Object[]> q1;
		
		Query<Object[]> q2;
		
		List<Object> userProfileList = new ArrayList<Object>();
		
		q1 = hs.createNativeQuery("select * from BAML_USER_PROFILE_TABLE where entity_flg ='Y' and del_flg ='N' order by USER_ID");
		
		q2 = hs.createNativeQuery("select * from BAML_USER_PROFILE_MOD_TABLE WHERE entity_flg ='N'  ");
		
		List<Object[]> result1 = q1.getResultList();
		List<Object[]> result2 = q2.getResultList();
		
		for (Object[] a : result1) {
			String bank_code = (String) a[0];
			String bank_name = (String) a[1];
			String branch_code = (String) a[2];
			String branch_name = (String) a[3];
			String emp_id = (String) a[4];
			String emp_name = (String) a[5];
			String user_id = (String) a[6];
			String user_name = (String) a[7];
			String inactive_time = (String) a[8];
			Date acc_exp_date = (Date) a[9];
			String login_low = (String) a[10];
			String login_high = (String) a[11];
			Date disable_start_date = (Date) a[12];
			Date disable_end_date = (Date) a[13];
			String password = (String) a[14];
			Date pass_exp_date = (Date) a[15];
			String user_status = (String) a[16];
			String login_status = (String) a[17];
			
			
			Character virtual_flg = (Character) a[18];
			String virtualFlg = String.valueOf(virtual_flg);
			
			
			
			String work_class = (String) a[19];
			BigDecimal mob_number = (BigDecimal) a[20];
			String mobNum =mob_number.toString();
			
			
			String email_id = (String) a[21];
			String role_id = (String) a[22];
			String role_desc = (String) a[23];
			String permissions = (String) a[24];
			String per_effctive_date = (String) a[25];
			String admin = (String) a[26];
			String xbrl_configuration = (String) a[27];
			String xbrl_report = (String) a[28];
			String scheduler = (String) a[29];
			String execution = (String) a[30];
			String mis_reports = (String) a[31];
			String xml_reports = (String) a[32];
			String archivel = (String) a[33];
			String general_inq = (String) a[34];
			String audit_inq = (String) a[35];
			String channel = (String) a[36];
			String entry_user = (String) a[37];
			Date entry_time = (Date) a[38];
			String auth_user = (String) a[39];
			Date auth_time = (Date) a[40];
			String modify_user = (String) a[41];
			Date modify_time = (Date) a[42];
			Character entity_flg = (Character) a[43];
			String entityFlag = String.valueOf(entity_flg);
			
			String auth_flg = (String) a[44];
			Character modify_flg = (Character) a[45];
			String modifyFlg = String.valueOf(modify_flg);
			
			
			Character del_flg = (Character) a[46];
			String delFlg = String.valueOf(del_flg);
			
			
			String session_id = (String) a[47];
			
			
			Character login_flg = (Character) a[48];
			String loginFlag = String.valueOf(login_flg);
			
			
			Character user_locked_flg = (Character) a[49];
			String userLockedFlag = String.valueOf(user_locked_flg);
			
			
			BigDecimal no_of_attmp = (BigDecimal) a[50];
			Integer noOfAttmp = no_of_attmp.intValue();
			
			
			Character disable_flg = (Character) a[51];
			String disableFlag = String.valueOf(disable_flg);
			
			
			String domain_id = (String) a[53];
			
			Character new_user_flg = (Character) a[54];
			String newUserFlag = String.valueOf(new_user_flg);
			
			String remark = (String) a[55];
			
			UserProfile userProfile = new UserProfile(bank_code, bank_name, branch_code, branch_name, emp_id, emp_name, 
					user_id, user_name, inactive_time, acc_exp_date, login_low, login_high, disable_start_date, disable_end_date, 
					password, pass_exp_date, user_status, login_status, virtualFlg, work_class, mobNum, email_id, role_id,
					role_desc, permissions, per_effctive_date, admin, xbrl_configuration, xbrl_report, scheduler, execution,
					mis_reports, xml_reports, archivel, general_inq, audit_inq, channel, entry_user, entry_time, auth_user, 
					auth_time, modify_user, modify_time, entityFlag, auth_flg, modifyFlg, delFlg, session_id, loginFlag, 
					userLockedFlag, noOfAttmp, disableFlag, domain_id, newUserFlag,remark);
			
			

			userProfileList.add(userProfile);
			
			
		}
		
		
		for (Object[] a : result2) {
			String bank_code = (String) a[0];
			String bank_name = (String) a[1];
			String branch_code = (String) a[2];
			String branch_name = (String) a[3];
			String emp_id = (String) a[4];
			String emp_name = (String) a[5];
			String user_id = (String) a[6];
			String user_name = (String) a[7];
			String inactive_time = (String) a[8];
			Date acc_exp_date = (Date) a[9];
			String login_low = (String) a[10];
			String login_high = (String) a[11];
			Date disable_start_date = (Date) a[12];
			Date disable_end_date = (Date) a[13];
			String password = (String) a[14];
			Date pass_exp_date = (Date) a[15];
			String user_status = (String) a[16];
			String login_status = (String) a[17];
			
			Character virtual_flg = (Character) a[18];
			String virtualFlg = String.valueOf(virtual_flg);
			
			String work_class = (String) a[19];
			BigDecimal mob_number = (BigDecimal) a[20];
			String mobNum =mob_number.toString();
			
			
			String email_id = (String) a[21];
			String role_id = (String) a[22];
			String role_desc = (String) a[23];
			String permissions = (String) a[24];
			String per_effctive_date = (String) a[25];
			String admin = (String) a[26];
			String xbrl_configuration = (String) a[27];
			String xbrl_report = (String) a[28];
			String scheduler = (String) a[29];
			String execution = (String) a[30];
			String mis_reports = (String) a[31];
			String xml_reports = (String) a[32];
			String archivel = (String) a[33];
			String general_inq = (String) a[34];
			String audit_inq = (String) a[35];
			String channel = (String) a[36];
			String entry_user = (String) a[37];
			Date entry_time = (Date) a[38];
			String auth_user = (String) a[39];
			Date auth_time = (Date) a[40];
			String modify_user = (String) a[41];
			Date modify_time = (Date) a[42];
			Character entity_flg = (Character) a[43];
			
			String entityFlag = String.valueOf(entity_flg);
			
			String auth_flg = (String) a[44];
			Character modify_flg = (Character) a[45];
			String modifyFlg = String.valueOf(modify_flg);
			
			
			Character del_flg = (Character) a[46];
			String delFlg = String.valueOf(del_flg);
			String session_id = (String) a[47];
			Character login_flg = (Character) a[48];
			String loginFlag = String.valueOf(login_flg);
			
			Character user_locked_flg = (Character) a[49];
			String userLockedFlag = String.valueOf(user_locked_flg);
			
			BigDecimal no_of_attmp = (BigDecimal) a[50];
			Integer noOfAttmp = no_of_attmp.intValue();
			
			
			
			Character disable_flg = (Character) a[51];
			String disableFlag = String.valueOf(disable_flg);
			
			String domain_id = (String) a[53];
			
			Character new_user_flg = (Character) a[54];
			String newUserFlag = String.valueOf(new_user_flg);
			
			String remark = (String) a[55];
			UserProfileModEn userProfileModEn = new UserProfileModEn(bank_code, bank_name, branch_code, branch_name, emp_id, emp_name,
					user_id, user_name, inactive_time, acc_exp_date, login_low, login_high, disable_start_date, disable_end_date,
					password, pass_exp_date, user_status, login_status, virtualFlg, work_class, mobNum, email_id, role_id, 
					role_desc, permissions, per_effctive_date, admin, xbrl_configuration, xbrl_report, scheduler, execution, 
					mis_reports, xml_reports, archivel, general_inq, audit_inq, channel, entry_user, entry_time, auth_user, 
					auth_time, modify_user, modify_time, entityFlag, auth_flg, modifyFlg, delFlg, session_id, loginFlag, 
					userLockedFlag, noOfAttmp, disableFlag, newUserFlag, domain_id,remark);
			
		
			
			userProfileList.add(userProfileModEn);

			
		}
		


		Iterable<UserProfile> users = userProfileRep.findAll();
		
		return userProfileList;

	}
	public UserProfile getUser(String id) {
		logger.info(id);
		if (userProfileRep.existsById(id)) {
			UserProfile up = userProfileRep.findById(id).get();
			logger.info(up.getEntity_flg());
			return up;
		} else {
			return new UserProfile();
		}

	};
	public String DeleteUser(UserProfile userProfile, String inputUser) {
		String msg = "";

		Session hs = sessionFactory.getCurrentSession();
		BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
		/*
		 * Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());
		 */
		UserProfile up = userProfile;
		try {
			String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);

			if (up.getLogin_status().equals("Active")) {
				up.setUser_locked_flg("N");
			} else {
				up.setUser_locked_flg("Y");
			}

			if (up.getUser_status().equals("Active")) {
				up.setDisable_flg("N");
			} else {
				up.setDisable_flg("Y");
			}

			up.setEntity_flg("N");
			up.setDel_flg("Y");
			up.setModify_time(new Date());
			up.setModify_user(inputUser);
			
			up.setLogin_flg("N");//To prompt the user for changing the password at first login
			up.setNo_of_attmp(0);
			up.setPassword(encryptedPassword);
			
			System.out.println("flag " + userProfile.getNew_user_flg());
			
			audit.setAudit_date(new Date());
			audit.setAudit_table("USER PROFILE MAINTENANCE");
			audit.setAudit_screen("USER PROFILE - DELETE");
			audit.setEntry_user(inputUser);
			audit.setEvent_name(inputUser);
			audit.setEntry_time(new Date());
			audit.setFunc_code("USER DELETED");
			audit.setRemarks(up.getUserid());
			audit.setEvent_id(up.getUserid());
			audit.setModi_details("Del_flg + N + Y +");
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);

				
		
		
			
			
			//UserProfile user = new UserProfile(up);
			//System.out.println("=-=-=-=-=-"+user.toString());
			
			Session session = sessionFactory.getCurrentSession();
			
			session.saveOrUpdate(up);
			//session.remove(up);
			msg = "User Deleted Successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		return msg;
	}


	
	public String verifyUser(UserProfileModEn up, String inputUser) {
		String msg = "";
		Session session = sessionFactory.getCurrentSession();
		
		//used to update the password in userprofile mod object 
		 Optional<UserProfile> user1 = userProfileRep.findById(up.getUserid());
		  
		AML_AUDIT_LOCAL audit = auditLocal.getAuditVerifyUser(up.getUserid());
		
		try {

			if (up.getLogin_status().equals("Active")) {
				up.setUser_locked_flg("N");
				up.setNo_of_attmp(0);
			} else {
				up.setUser_locked_flg("Y");
			}

			if (up.getUser_status().equals("Active")) {
				up.setDisable_flg("N");
			} else {
				up.setDisable_flg("Y");
			}

			up.setEntity_flg("Y");
			up.setAuth_time(new Date());
			up.setAuth_user(inputUser);
			up.setDel_flg("N");
			up.setLogin_flg("Y");//To prompt the user for changing the password at first login
			up.setNo_of_attmp(0);
			if(user1.isPresent()) {
				up.setPassword(user1.get().getPassword());
			}else {
				String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);
				up.setPassword(encryptedPassword);
			}
			
			
			UserProfile user = new UserProfile(up);
			user.setEntity_flg("Y");
			
			session.clear();
			session.saveOrUpdate(user);
			session.remove(up);

			BigDecimal EMAIL = (BigDecimal) session.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
		
			if (audit !=null && audit.getRemarks().equals("ADDED")) {
				audit.setAudit_date(new Date());
				audit.setAudit_table("USER PROFILE MAINTENANCE");
				audit.setAudit_screen("USER PROFILE - CREATION");
				audit.setFunc_code("USER VERIFIED");
				audit.setRemarks("VERIFIED");
				audit.setEvent_id(up.getUserid());
				audit.setEvent_name(up.getUsername());
				audit.setModi_details(audit.getModi_details());
				audit.setAuth_user(inputUser);
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
				EmailAlert EA = new EmailAlert();
				String alertcode = "USER-ADD";
				AlertManagementEntity  AM= alertrep.getalertdetail(alertcode);
				if(AM.getEmail_flg().equals("Y")) {
					EA.setEmail_id(AM.getEmail_1());
					EA.setEmail_id_cc1(AM.getEmail_2());
					EA.setEmail_id_cc2(AM.getEmail_3());
					EA.setEmail_sub(AM.getParam_1());
					EA.setEmail_body("USER CREATED SUCCESSFULLY...., USER_ID :"+up.getUserid()+", USER_NAME :"+up.getUsername());
					EA.setEmail_date(new Date());
					EA.setEmail_srl_no(EMAIL);
					EA.setSend_flg("N");
					emailRep.save(EA);
				}
			}else if (audit!=null && audit.getRemarks().equals("MODIFIED")) {
				audit.setAudit_date(new Date());
				audit.setAudit_table("USER PROFILE MAINTENANCE");
				audit.setAudit_screen("USER PROFILE - MODIFIED");
				audit.setFunc_code("USER MODIFIED");
				audit.setRemarks("VERIFIED");
				audit.setEvent_id(up.getUserid());
				audit.setEvent_name(up.getUsername());
				audit.setModi_details(audit.getModi_details());
				audit.setAuth_user(inputUser);
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
			}
			if(audit!=null) {
			session.saveOrUpdate(audit);
			}
			msg = "User Verified Successfully";

		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}

		
		
		return msg;
	}

	public String passwordReset(UserProfile userprofile, String userid,String Useronchange) {

		Session session = sessionFactory.getCurrentSession();
		String msg = "";
		BigDecimal EMAIL = (BigDecimal) session.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
System.out.println("passwordReset"+userid);
		try {
			String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);

			Optional<UserProfile> up = userProfileRep.findById(userid);

			if (up.isPresent()) {
				UserProfile user = up.get();
				user.setPassword(encryptedPassword);
				user.setNo_of_attmp(0);
				user.setLogin_flg("N");
				user.setUser_locked_flg("N");
				Integer passlife = Integer.parseInt(user.getRemark());
				System.out.println("PASSWORDLIFESPAN"+passlife);
				LocalDateTime localDateTime = user.getPass_exp_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				user.setPass_exp_date(Date.from(localDateTime.plusDays(passlife).atZone(ZoneId.systemDefault()).toInstant()));
				user.setNew_user_flg("Y");
				userProfileRep.save(user);
				
	
					audit.setAudit_date(new Date());
					audit.setAudit_table("USER PROFILE MAINTENANCE");
					audit.setAudit_screen("USER PROFILE - CREATION");
					audit.setFunc_code("USER PASSWORD RESET");
					audit.setRemarks("PASSWORD RESET ");
					audit.setEvent_id(userid);
					audit.setEvent_name(user.getUsername());
					audit.setModi_details("PASSWORD RESET "+userid);
					audit.setAuth_user(Useronchange);
					audit.setEntry_user(Useronchange);
					audit.setEntry_time(new Date());
					audit.setAuth_time(new Date());
					audit.setAudit_ref_no(EMAIL.toString());
					auditLocal.save(audit);
					
			}

			msg = "Password Resetted Successfully";

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

			e.printStackTrace();

			msg = "Error Occured. Please contact Administrator";
		}

		return msg;
	}

/*public List<FinUserProfileEntity> getFinUsersList() {
		
	 List<FinUserProfileEntity> lst=new ArrayList<FinUserProfileEntity>();
	 
	 List<Object[]> lst_Objects=finUserProfileRep.getfin_user_details();
	 
	 for(Object[] obj:lst_Objects) {
		 FinUserProfileEntity info=new FinUserProfileEntity();
		 info.setUser_id(String.valueOf(obj[2]));
		 info.setEmp_id(String.valueOf(obj[1]));
		 info.setSol_id(String.valueOf(obj[0]));
		 
		 lst.add(info);
	 }
*/	
//		Session hs = sessionFactory.getCurrentSession();
//		
//		return hs.createQuery("from FinUserProfileEntity ", FinUserProfileEntity.class).getResultList();
	//	return lst;

	//}
	public String checkPasswordChangeReq(String userid) {

		Optional<UserProfile> up = userProfileRep.findById(userid);
		String loginflg = up.get().getNew_user_flg();

		return loginflg;
	}

	public int checkAcctexpirty(String userid) {

		Optional<UserProfile> up = userProfileRep.findById(userid);
		Date expDate = up.get().getAcc_exp_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		logger.info("Account Expired in:" + remaindays);
		return remaindays;
	}

	public int checkpassexpirty(String userid) {

		Optional<UserProfile> up = userProfileRep.findById(userid);
		Date expDate = up.get().getPass_exp_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		logger.info("Password Expired in:" + remaindays);
		return remaindays;
	}

	public String changePassword(String oldpass, String newpass, String userid) {
		String msg = "";

		Optional<UserProfile> up = userProfileRep.findById(userid);

		try {
			if (up.isPresent()) {
				UserProfile user = up.get();
				if (PasswordEncryption.validatePassword(oldpass, user.getPassword())) {
					
					if (!PasswordEncryption.validatePassword(newpass, user.getPassword())) {
						
						String encryptedPassword = PasswordEncryption.getEncryptedPassword(newpass);
						user.setPassword(encryptedPassword);
						user.setLogin_flg("Y");
						user.setNew_user_flg("N");
						
						LocalDateTime localDateTime = user.getPass_exp_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						user.setPass_exp_date(Date.from(localDateTime.plusDays(365).atZone(ZoneId.systemDefault()).toInstant()));
						
						userProfileRep.save(user);
						msg = "Password Changed Successfully";
						
					}else {
						
						msg = "New password cannot be Same as Old password";
					}
					
					
				} else {
					msg = "Incorrect Old Password!";
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "Error Occured. Please contact Administrator";
		}
		logger.info(msg);
		return msg;
	};

	
/*	public UserProfile getFinUser(String id) {
//if u modify this code --- compulsorily need to check in weblogic server for the issues
		if (finUserProfileRep.existsById(id)) {

			// FinUserProfileEntity fup = finUserProfileRep.findById(id).get();

//			using array of list to handle the manually written data
			List<Object[]> lst_Objects1_finUser = finUserProfileRep.getfin_user_details(id);

			Object[] lst_Objects_USER = lst_Objects1_finUser.get(0);

			FinUserProfileEntity info = new FinUserProfileEntity();

			info.setUser_id(String.valueOf(lst_Objects_USER[0]));
			info.setSol_id(String.valueOf(lst_Objects_USER[1]));
			//info.setBranch_code(String.valueOf(lst_Objects_USER[1]));
			//info.setEmp_id(String.valueOf(lst_Objects_USER[2]));
//getting the emp details seperate query is written to avoid the weblogic server crash
			//if (info.getEmp_id() != null) {
				//List<Object[]> lst_Objects_gen = finUserProfileRep.getfin_gen_details(info.getEmp_id());
				//Object[] lst_Objects_GEN = lst_Objects_gen.get(0);

				//info.setEmp_id(String.valueOf(lst_Objects_GEN[0]));
				//info.setEmp_name(String.valueOf(lst_Objects_GEN[1]));
				//info.setEmp_email(String.valueOf(lst_Objects_GEN[2]));
			//}
			//getting the sol details- seperate query is written to avoid the weblogic server crash
			if (info.getSol_id() != null) {
				List<Object[]> lst_Objects1_sol = finUserProfileRep.getfin_sol_details(info.getSol_id());
				Object[] lst_Objects_SOL = lst_Objects1_sol.get(0);

				info.setBranch_name(String.valueOf(lst_Objects_SOL[1]));
				info.setBank_code(String.valueOf(lst_Objects_SOL[2]));
				info.setBank_name(String.valueOf(lst_Objects_SOL[3]));
			}

			//assignig values from finuserprofile dto to user profile dto			
			UserProfile up = new UserProfile();
			up.setUserid(info.getUser_id());
			up.setBranch_code(info.getSol_id());
			up.setEmpid(info.getEmp_id());
			up.setEmp_name(info.getEmp_name());
			up.setUsername(info.getEmp_name());
			up.setBranch_code(info.getSol_id());
			up.setBranch_name(info.getBranch_name());
			up.setBank_code(info.getBank_code());
			up.setBank_name(info.getBank_name());
			up.setEmail_id(info.getEmp_email());


			return up;

		} else {

			return new UserProfile();
		}

	}
*/	
	public void SessionLogging(String menuname, String menuid, String sessionid, String userid, String ip,
			String status) {
		Session hs = sessionFactory.getCurrentSession();

		try {

			if (menuname.equals("LOGOUT")) {

				hs.createQuery("update XBRLSession set status='IN-ACTIVE' where session_id = ?1")
						.setParameter(1, sessionid).executeUpdate();

			} else {
				
				hs.save(new AMLSession(menuname, menuid, sessionid, userid, ip, new Date(), status));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public File getUserLogFile(Date fromdate, Date todate) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		String path = "";
		String fileName = "USER_LOGS_"+dateFormat.format(new Date())+".xlsx";
		File outputFile;

		InputStream jasperFile;
		
		
		
		try {
			jasperFile = this.getClass().getResourceAsStream("/static/jasper/USER_LOGS/UserLogs.jasper");
			JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("FromDate", dateFormat.format(fromdate));
			map.put("ToDate", dateFormat.format(todate));
			
			
			
			path =   fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			exporter.exportReport();
			logger.info("Excel File exported");
			
		} catch (JRException|SQLException e) {
			
			e.printStackTrace();
		}


		outputFile = new File(path);	

		
	return outputFile;
	}

	public List<AMLSession> getUserLog(Date fromdate, Date todate) {
		
		
		Session hs = sessionFactory.getCurrentSession();
		
		List<AMLSession> ls = hs.createQuery("from AMLSession where trunc(entry_time,'DD') between ?1 and ?2 and menu in ('LOGIN','LOGOUT') order by entry_time desc ", AMLSession.class)
		.setParameter(1, fromdate)
		.setParameter(2, todate)
		.getResultList();
		
		
		return ls;
	}
	
	

}
