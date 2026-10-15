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
		BigDecimal Number = (BigDecimal) hs
				.createNativeQuery("SELECT CAST(nextval('\"AML\".\"AML_AUDIT_SEQ\"') AS NUMERIC)")
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

						if ("Active".equals(up.getLogin_status())) {
							up.setUser_locked_flg("N");
						} else {
							up.setUser_locked_flg("Y");
						}

						if ("Active".equals(up.getUser_status())) {
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

					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String acctexpold = us1.getAcc_exp_date() != null ? dateFormat.format(us1.getAcc_exp_date()) : "";
					String acctexpnew = userProfile.getAcc_exp_date() != null ? dateFormat.format(userProfile.getAcc_exp_date()) : "";
					String disableSold = us1.getDisable_start_date() != null ? dateFormat.format(us1.getDisable_start_date()) : "";
					String disableSnew = userProfile.getDisable_start_date() != null ? dateFormat.format(userProfile.getDisable_start_date()) : "";
					String disableEold = us1.getDisable_end_date() != null ? dateFormat.format(us1.getDisable_end_date()) : "";
					String disableEnew = userProfile.getDisable_end_date() != null ? dateFormat.format(userProfile.getDisable_end_date()) : "";
					String passOLD = us1.getPass_exp_date() != null ? dateFormat.format(us1.getPass_exp_date()) : "";
					String passNEW = userProfile.getPass_exp_date() != null ? dateFormat.format(userProfile.getPass_exp_date()) : "";

					if (java.util.Objects.equals(us1.getEmail_id(), userProfile.getEmail_id())
							&& java.util.Objects.equals(us1.getMob_number(), userProfile.getMob_number())
							&& java.util.Objects.equals(us1.getLogin_low(), userProfile.getLogin_low())
							&& java.util.Objects.equals(us1.getLogin_high(), userProfile.getLogin_high())
							&& acctexpold.equals(acctexpnew)
							&& disableSold.equals(disableSnew)
							&& disableEold.equals(disableEnew)
							&& passOLD.equals(passNEW)
							&& java.util.Objects.equals(us1.getUser_status(), userProfile.getUser_status())
							&& java.util.Objects.equals(us1.getLogin_status(), userProfile.getLogin_status())
							&& java.util.Objects.equals(us1.getRemark(), userProfile.getRemark())
							&& java.util.Objects.equals(us1.getRole_id(), userProfile.getRole_id())) {
						msg = "No any Modification done";

					} else {

						userProfile.setPassword(up.get().getPassword());

						if ("Active".equals(userProfile.getLogin_status())) {
							userProfile.setUser_locked_flg("N");
						} else {
							userProfile.setUser_locked_flg("Y");
						}

						if ("Active".equals(userProfile.getUser_status())) {
							userProfile.setDisable_flg("N");
						} else {
							userProfile.setDisable_flg("Y");
						}

						if (userProfile.getBank_code() == null) userProfile.setBank_code(us1.getBank_code());
						if (userProfile.getBank_name() == null) userProfile.setBank_name(us1.getBank_name());
						if (userProfile.getBranch_code() == null) userProfile.setBranch_code(us1.getBranch_code());
						if (userProfile.getBranch_name() == null) userProfile.setBranch_name(us1.getBranch_name());
						if (userProfile.getEmpid() == null) userProfile.setEmpid(us1.getEmpid());
						if (userProfile.getEmp_name() == null) userProfile.setEmp_name(us1.getEmp_name());
						if (userProfile.getUsername() == null) userProfile.setUsername(us1.getUsername());
						if (userProfile.getEntry_user() == null) userProfile.setEntry_user(us1.getEntry_user());
						if (userProfile.getEntry_time() == null) userProfile.setEntry_time(us1.getEntry_time());
						if (userProfile.getNew_user_flg() == null) userProfile.setNew_user_flg("N");
						if (userProfile.getDel_flg() == null) userProfile.setDel_flg("N");
						if (userProfile.getLogin_flg() == null) userProfile.setLogin_flg(us1.getLogin_flg());
						if (userProfile.getPhoto() == null) userProfile.setPhoto(us1.getPhoto());

						userProfile.setNo_of_attmp(0);
						userProfile.setEntity_flg("N");
						userProfile.setModify_user(inputUser);
						userProfile.setModify_time(new Date());

						Session session = sessionFactory.getCurrentSession();
						session.saveOrUpdate(userProfile);

						msg = "User Edited Successfully";
					}
				}
			} else if (formmode.equals("delete")) {
				System.out.println("delete mode");

				Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());

				if (up.isPresent()) {
					UserProfile us1 = up.get();
					userProfile.setPassword(us1.getPassword());

					if ("Active".equals(userProfile.getLogin_status())) {
						userProfile.setUser_locked_flg("N");
					} else {
						userProfile.setUser_locked_flg("Y");
					}

					if ("Active".equals(userProfile.getUser_status())) {
						userProfile.setDisable_flg("N");
					} else {
						userProfile.setDisable_flg("Y");
					}

					if (userProfile.getBank_code() == null) userProfile.setBank_code(us1.getBank_code());
					if (userProfile.getBank_name() == null) userProfile.setBank_name(us1.getBank_name());
					if (userProfile.getBranch_code() == null) userProfile.setBranch_code(us1.getBranch_code());
					if (userProfile.getBranch_name() == null) userProfile.setBranch_name(us1.getBranch_name());
					if (userProfile.getEmpid() == null) userProfile.setEmpid(us1.getEmpid());
					if (userProfile.getEmp_name() == null) userProfile.setEmp_name(us1.getEmp_name());
					if (userProfile.getUsername() == null) userProfile.setUsername(us1.getUsername());
					if (userProfile.getEntry_user() == null) userProfile.setEntry_user(us1.getEntry_user());
					if (userProfile.getEntry_time() == null) userProfile.setEntry_time(us1.getEntry_time());
					if (userProfile.getNew_user_flg() == null) userProfile.setNew_user_flg("N");
					if (userProfile.getDel_flg() == null) userProfile.setDel_flg("Y");
					if (userProfile.getLogin_flg() == null) userProfile.setLogin_flg(us1.getLogin_flg());
					if (userProfile.getPhoto() == null) userProfile.setPhoto(us1.getPhoto());

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
		String targetUserId = (userProfilemoden != null && userProfilemoden.getUserid() != null) ? userProfilemoden.getUserid() : inputUser;
		if (targetUserId != null) {
			Session hs = sessionFactory.getCurrentSession();
			hs.createNativeQuery("DELETE FROM \"AML\".\"BAML_USER_PROFILE_MOD_TABLE\" WHERE \"USER_ID\" = ?1 ")
					.setParameter(1, targetUserId)
					.executeUpdate();
		}
		return inputUser;
	}

	public String cancel(UserProfileModEn userProfilemoden, UserProfile userprofile, String inputUser) {
		String targetUserId = (userProfilemoden != null && userProfilemoden.getUserid() != null) ? userProfilemoden.getUserid()
				: (userprofile != null && userprofile.getUserid() != null ? userprofile.getUserid() : inputUser);
		if (targetUserId != null) {
			Session hs = sessionFactory.getCurrentSession();
			hs.createNativeQuery("DELETE FROM \"AML\".\"BAML_USER_PROFILE_MOD_TABLE\" WHERE \"USER_ID\" = ?1")
					.setParameter(1, targetUserId)
					.executeUpdate();
		}
		return "Last changes are removed ";
	}
}
