package com.bornfire.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.CMGrepository;
import com.bornfire.entity.TransMonitoringRepository;
import com.bornfire.entity.UserProfile;
import com.bornfire.entity.UserProfileRep;



@Configuration
@EnableWebSecurity

public class AMLWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	UserProfileRep userProfileRep;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	CMGrepository cmgrepository;

	@Autowired
	TransMonitoringRepository transmonitoringrepository;
	

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	
	private static final Logger logger = LoggerFactory.getLogger(AMLWebSecurity.class);
	
	 public final Integer SESSION_TIMEOUT_IN_SECONDS = 600;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/webjars/**", "/images/**", "/login*", "/freezeColumn/**","favicon.ico").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.failureHandler(amlAuthFailHandle()).successHandler(amlAuthSuccessHandle())
				.usernameParameter("userid").and().logout().permitAll().and()
				.logout().logoutSuccessHandler(amlLogoutSuccessHandler()).permitAll()
				.and().sessionManagement().maximumSessions(1)
				.maxSessionsPreventsLogin(false);
				

		http.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider ap = new DaoAuthenticationProvider() {

			@Override
			@Transactional
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String userid = authentication.getName();
				String password = authentication.getCredentials().toString();
				
				Optional<UserProfile> up = userProfileRep.findById(userid);
			
				try {

					if (up.isPresent()) {
						UserProfile usr = up.get();

						if (!usr.isAccountNonExpired()) {
							System.out.println("Account Expired");
							/*Session hs = sessionFactory.getCurrentSession();
							BigDecimal Number1 = (BigDecimal) hs.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
									.getSingleResult();
			                 AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
							
							audit.setAudit_date(new Date());
							audit.setEntry_time(new Date());
							audit.setEntry_user(usr.getUserid());
							audit.setFunc_code("LOGIN FAILED");
							audit.setRemarks("LOGIN");
							audit.setAudit_table("AML_USER_PROFILE_TABLE");
							audit.setAudit_screen("AML LOGIN");
							audit.setEvent_id(up.get().getUserid());
							audit.setEvent_name(up.get().getUsername());
							audit.setModi_details("Account Expired");
							audit.setAudit_ref_no(Number1.toString());
							auditLocal.save(audit);*/
						throw new AccountExpiredException("Account Expired");
							
							
			

						} else if (!usr.isCredentialsNonExpired()) {

							throw new CredentialsExpiredException("Credentials Expired");

						} else if (!usr.isAccountNonLocked()) {

							throw new LockedException("Account Locked");

						} else if (!usr.isEnabled()) {

							throw new DisabledException("Account Disabled");

						} else if (!usr.isLoginAllowed()) {

							throw new LockedException("Login Not Allowed");

						} else if (!PasswordEncryption.validatePassword(password, usr.getPassword())) {
							logger.info("Passing Userid :" + userid);

				 			Session hs = sessionFactory.getCurrentSession();
							Transaction tr = hs.getTransaction();
							hs.createQuery(
									"update UserProfile a set a.no_of_attmp=nvl(a.no_of_attmp,0)+1, a.user_locked_flg=decode(nvl(a.no_of_attmp,0)+1,'3','Y','N'), a.login_status=decode(nvl(a.no_of_attmp,0)+1,'3','Inactive','Active') where userid=?1")
									.setParameter(1, userid).executeUpdate();
							tr.commit();
							hs.close();
							throw new BadCredentialsException("Authentication failed");

						} else {
							
							return new UsernamePasswordAuthenticationToken(userid, password, Collections.emptyList());

						}

					} else {

						throw new UsernameNotFoundException("Invalid User Name");
					}

				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					e.printStackTrace();
					authentication.setAuthenticated(false);
				}
				System.out.println(authentication.getAuthorities()+"useer");

				return authentication;

			}

			@Override
			public boolean supports(Class<?> aClass) {
				return aClass.equals(UsernamePasswordAuthenticationToken.class);
			}

		};

		ap.setHideUserNotFoundExceptions(false);
		ap.setUserDetailsService(userDetailsService());

		return ap;
	}
	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

				Optional<UserProfile> up = userProfileRep.findByusername(username);

				if (up.isPresent()) {
					return up.get();
				} else {
					return new UserProfile();
				}

			}

		};
	}

	@Bean
	public AuthenticationFailureHandler amlAuthFailHandle( ) {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {

				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.sendRedirect("login?error=" + exception.getMessage());
;
			
		
/*				Session hs = sessionFactory.openSession();
				BigDecimal Number1 = (BigDecimal) hs.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
						.getSingleResult();
AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
				
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user("");
				audit.setFunc_code(exception.getMessage());
				audit.setRemarks("LOGIN");
				audit.setAudit_table("AML_USER_PROFILE_TABLE");
				audit.setAudit_screen("AML LOGIN");
				audit.setEvent_id("");
				audit.setEvent_name("");
				audit.setModi_details(exception.getMessage());
				audit.setAudit_ref_no(Number1.toString());
				auditLocal.save(audit);
*/
			}

		};

	}

	
	@Bean
	public AuthenticationFailureHandler xbrlAuthFailHandle() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {

				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				logger.info(exception.getMessage());
				response.sendRedirect("login?error=" + exception.getMessage());

			}

		};

	}

	@Bean
	public AuthenticationSuccessHandler amlAuthSuccessHandle() {
		return new AuthenticationSuccessHandler() {
		Session hs1 = sessionFactory.openSession();

			BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				
				
				Optional<UserProfile> up = userProfileRep.findById(authentication.getName());
				UserProfile user = up.get();
				user.setNo_of_attmp(0);
				user.setUser_locked_flg("N");
		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
				
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(user.getUserid());
				audit.setFunc_code("LOGIN SUCCESS");
				audit.setRemarks("LOGIN");
				audit.setAudit_table("BAML_USER_PROFILE_TABLE");
				audit.setAudit_screen("AML LOGIN");
				audit.setEvent_id(user.getUserid());
				audit.setEvent_name(user.getUsername());
				audit.setModi_details("USER LOGIN ");
				audit.setAudit_ref_no(Number1.toString());
				auditLocal.save(audit);

				userProfileRep.save(user);
hs1.close();
				/*
				 * long custcount =cmgrepository.findCustomcount(); long trancount =
				 * transmonitoringrepository.findtrancount(); long transucescount =
				 * transmonitoringrepository.findtransucescount(); long tranfailurecount =
				 * transmonitoringrepository.findtranfailurecount();
				 */
				//loginServices.SessionLogging("LOGIN","M1",request.getSession().getId(),user.getUserid(),request.getRemoteAddr(),
				//		"ACTIVE");
				//used t set the session time out for the application 
				 request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
				 
				request.getSession().setAttribute("USERID", user.getUserid());
				request.getSession().setAttribute("USERNAME", user.getUsername());
				request.getSession().setAttribute("ROLEID", user.getRole_id());
				request.getSession().setAttribute("DOMAINID", user.getDomain_id()); 
				request.getSession().setAttribute("PERMISSIONS", user.getPermissions());
				request.getSession().setAttribute("WORKCLASS", user.getWork_class());
//				request.getSession().setAttribute("CustomerCount",custcount);
//				request.getSession().setAttribute("trancount",trancount);
//				request.getSession().setAttribute("transucescount",transucescount);
//				request.getSession().setAttribute("tranfailurecount",tranfailurecount);
				
				
				response.sendRedirect("Dashboard");
			}
  
		};

	}
	
	@Bean
	public LogoutSuccessHandler amlLogoutSuccessHandler() {
		
		return new LogoutSuccessHandler() {

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				
				 HttpSession session = request.getSession(false);
				    if (session != null) {
				        session.invalidate();
				    }
				    Optional<UserProfile> up = userProfileRep.findById(authentication.getName());
					UserProfile user = up.get();
					
				
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
				Session hs1 = sessionFactory.openSession();

				BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
						.getSingleResult();

				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(user.getUserid());
				audit.setFunc_code("LOGOUT SUCCESS");
				audit.setRemarks("LOGOUT");
				audit.setAudit_table("BAML_USER_PROFILE_TABLE");
				audit.setAudit_screen("AML LOGOUT");
				audit.setEvent_id(user.getUserid());
				audit.setEvent_name(user.getUsername());
				audit.setModi_details("USER LOGOUT ");
				audit.setAudit_ref_no(Number1.toString());
				auditLocal.save(audit);
				
				response.sendRedirect("login?logout");
				hs1.close();

			
			}
		
		
	};
}

	
}
