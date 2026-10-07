package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.PasswordEncryption;
import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.UserProfile;
import com.bornfire.entity.UserProfileModEn;
import com.bornfire.entity.UserProfileModRep;
import com.bornfire.entity.UserProfileRep;

@Service
@ConfigurationProperties("output")
@Transactional
public class UserProfileModService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);

	@Autowired
	UserProfileModRep userProfileModRep;

	@Autowired
	UserProfileRep userProfileRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;



	@Value("${default.password}")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String addUser(UserProfileModEn userProfile, String formmode, String inputUser, String user) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

		String count = userProfileRep.getusercount(user);
		System.out.println(count + "insode lioop" + user);
		try {

			if (formmode.equals("add")) {

				if (count.equals("0")) {

					UserProfileModEn up = userProfile;
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
						up.setModify_time(new Date());
						up.setModify_user(inputUser);
						up.setNew_user_flg("Y");

						up.setLogin_flg("N");
						up.setNo_of_attmp(0);
						up.setPassword(encryptedPassword);

						audit.setAudit_date(new Date());
						audit.setEntry_time(new Date());
						audit.setEntry_user(inputUser);
						audit.setFunc_code("USER CREATED");
						audit.setRemarks("ADDED");
						audit.setAudit_table("BAML_USER_PROFILE_TABLE");
						audit.setAudit_screen("USER PROFILE MAINTANANCE");
						audit.setEvent_id(up.getUserid());
						audit.setEvent_name(up.getUsername());
						audit.setModi_details("RECORD CREATED");
						audit.setAudit_ref_no(Number.toString());
						auditLocal.save(audit);

					} catch (Exception e) {
						e.printStackTrace();
					}

					userProfileModRep.save(up);

					msg = "User Created Successfully";
				} else {
					msg = "User Already existing";
				}
			}
			// When the user data modifed and submitted.
			else if (formmode.equals("edit")) {
				System.out.println("edit mode");

				Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());

				if (up.isPresent()) {
					UserProfile us1 = up.get();

					DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
					String acctexpold = dateFormat.format(us1.getAcc_exp_date());
					String acctexpnew = dateFormat.format(userProfile.getAcc_exp_date());
					String disableSold = dateFormat.format(us1.getDisable_start_date());
					String disableSnew = dateFormat.format(userProfile.getDisable_start_date());
					String disableEold = dateFormat.format(us1.getDisable_end_date());
					String disableEnew = dateFormat.format(userProfile.getDisable_end_date());
					String passOLD = dateFormat.format(us1.getPass_exp_date());
					String passNEW = dateFormat.format(userProfile.getPass_exp_date());

					if ((us1.getEmail_id().equals(userProfile.getEmail_id()))
							&& (us1.getMob_number().equals(userProfile.getMob_number()))
							&& (us1.getLogin_low().equals(userProfile.getLogin_low()))
							&& (us1.getLogin_high().equals(userProfile.getLogin_high()))
							&& (acctexpold.equals(acctexpnew))
							&& (disableSold.equals(disableSnew))
							&& (disableEold.equals(disableEnew))
							&& (passOLD.equals(passNEW))
							&& (us1.getUser_status().equals(userProfile.getUser_status())) && (us1.getLogin_status().equals(userProfile.getLogin_status()))
							&& (us1.getRemark().equals(userProfile.getRemark())) && (us1.getRole_id().equals(userProfile.getRole_id()))
) {
						msg = "No any Modification done";

					} else {
						

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

						Session session = sessionFactory.getCurrentSession();
						session.saveOrUpdate(userProfile);
						
						// userProfileModRep.save(userProfile)

						msg = "User Edited Successfully";
					}
				}
			} else if (formmode.equals("delete")) {
				System.out.println("delete mode");

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

					Session session = sessionFactory.getCurrentSession();
					session.saveOrUpdate(userProfile);
					// userProfileModRep.save(userProfile);
				}

				msg = "User Deleted Successfully";

			}
		} catch (Exception e) {
			msg = "Error Occured. Please contact Administrator";
			e.printStackTrace();
			logger.info(e.getMessage());
		}

		return msg;
	}

	public Iterable<UserProfileModEn> getUsersList() {

		Iterable<UserProfileModEn> users = userProfileModRep.findAll();

		return users;

	}

	public UserProfileModEn getUser(String id) {
		logger.info(id);
		if (userProfileModRep.existsById(id)) {
			UserProfileModEn up = userProfileModRep.findById(id).get();
			logger.info(up.getEntity_flg());
			return up;
		} else {
			return new UserProfileModEn();
		}

	};

	public String deleteUser(UserProfileModEn userProfilemoden, String inputUser) {
		String msg = "";

		Session hs = sessionFactory.getCurrentSession();
		NativeQuery q1 = hs.createNativeQuery("delete from BAML_USER_PROFILE_MOD_TABLE where user_id=?1 ")
				.setParameter(1, inputUser);
		System.out.println(inputUser);

		return inputUser;
	}

	public String cancel(UserProfileModEn userProfilemoden, UserProfile userprofile, String inputUser) {
		String msg = "";

		UserProfileModEn up = userProfilemoden;
		Session session = sessionFactory.getCurrentSession();
		// UserProfile ups = userprofile;
		// session.saveOrUpdate(user);
		// ups.setEntity_flg("Y");
		// session.update(ups);
		session.remove(up);
		Session hs = sessionFactory.getCurrentSession();
		 NativeQuery q1 = hs.createNativeQuery("delete from AML_USER_PROFILE_MOD_TABLE where user_id=?1").setParameter(1,inputUser);
		/*NativeQuery q2 = session
				.createNativeQuery("update BAML_USER_PROFILE_TABLE set ENTITY_FLG ='Y' where user_id = ?1")
				.setParameter(1, inputUser);
		*/// System.out.println(inputUser);

		msg = "Last changes are removed ";
		return msg;
	}
}
