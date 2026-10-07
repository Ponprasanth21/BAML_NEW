package com.bornfire.entity.t2p;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "T2P_CFT_CUSTOMER_RATING_MAST_TB")
public class T2PreviousMast implements Serializable{

	private String	customer_id;
	private String	customer_name;
	private String	branch_id;
	private String	branch_name;
	private String	bank_id;
	private String	ownership_type;
	private Date	relationship_date;
	private String	customer_rating;
	private Date	customer_rating_date;
	private Date	customer_next_rating_date;
	private String	mis_face_to_face;
	private String	mis_non_face_to_face;
	private String	mis_internal_rating_grade;
	private String	mis_internal_rating_scale;
	private String	remarks;
	private Character	del_flg;
	private Character	entity_cre_flg;
	@Id
	private Date	entity_cre_date;
	private Character	mod_flg;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	private String	aml_code_1;
	private String	aml_code_2;
	private String	aml_code_3;
	private String	aml_code_4;
	private String	aml_code_5;
	private String	aml_code_6;
	private String	aml_code_7;
	private String	aml_code_8;
	private String	aml_code_9;
	private String	aml_code_10;
	private Date	report_date;

	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getOwnership_type() {
		return ownership_type;
	}
	public void setOwnership_type(String ownership_type) {
		this.ownership_type = ownership_type;
	}
	public Date getRelationship_date() {
		return relationship_date;
	}
	public void setRelationship_date(Date relationship_date) {
		this.relationship_date = relationship_date;
	}
	public String getCustomer_rating() {
		return customer_rating;
	}
	public void setCustomer_rating(String customer_rating) {
		this.customer_rating = customer_rating;
	}
	public Date getCustomer_rating_date() {
		return customer_rating_date;
	}
	public void setCustomer_rating_date(Date customer_rating_date) {
		this.customer_rating_date = customer_rating_date;
	}
	public Date getCustomer_next_rating_date() {
		return customer_next_rating_date;
	}
	public void setCustomer_next_rating_date(Date customer_next_rating_date) {
		this.customer_next_rating_date = customer_next_rating_date;
	}
	public String getMis_face_to_face() {
		return mis_face_to_face;
	}
	public void setMis_face_to_face(String mis_face_to_face) {
		this.mis_face_to_face = mis_face_to_face;
	}
	public String getMis_non_face_to_face() {
		return mis_non_face_to_face;
	}
	public void setMis_non_face_to_face(String mis_non_face_to_face) {
		this.mis_non_face_to_face = mis_non_face_to_face;
	}
	public String getMis_internal_rating_grade() {
		return mis_internal_rating_grade;
	}
	public void setMis_internal_rating_grade(String mis_internal_rating_grade) {
		this.mis_internal_rating_grade = mis_internal_rating_grade;
	}
	public String getMis_internal_rating_scale() {
		return mis_internal_rating_scale;
	}
	public void setMis_internal_rating_scale(String mis_internal_rating_scale) {
		this.mis_internal_rating_scale = mis_internal_rating_scale;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Date getEntity_cre_date() {
		return entity_cre_date;
	}
	public void setEntity_cre_date(Date entity_cre_date) {
		this.entity_cre_date = entity_cre_date;
	}
	
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getAml_code_1() {
		return aml_code_1;
	}
	public void setAml_code_1(String aml_code_1) {
		this.aml_code_1 = aml_code_1;
	}
	public String getAml_code_2() {
		return aml_code_2;
	}
	
	public void setAml_code_2(String aml_code_2) {
		this.aml_code_2 = aml_code_2;
	}
	public String getAml_code_3() {
		return aml_code_3;
	}
	public void setAml_code_3(String aml_code_3) {
		this.aml_code_3 = aml_code_3;
	}
	
	public Character getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}
	public Character getEntity_cre_flg() {
		return entity_cre_flg;
	}
	public void setEntity_cre_flg(Character entity_cre_flg) {
		this.entity_cre_flg = entity_cre_flg;
	}
	public Character getMod_flg() {
		return mod_flg;
	}
	public void setMod_flg(Character mod_flg) {
		this.mod_flg = mod_flg;
	}
	
	public String getAml_code_4() {
		return aml_code_4;
	}
	public void setAml_code_4(String aml_code_4) {
		this.aml_code_4 = aml_code_4;
	}
	public String getAml_code_5() {
		return aml_code_5;
	}
	public void setAml_code_5(String aml_code_5) {
		this.aml_code_5 = aml_code_5;
	}
	public String getAml_code_6() {
		return aml_code_6;
	}
	public void setAml_code_6(String aml_code_6) {
		this.aml_code_6 = aml_code_6;
	}
	public String getAml_code_7() {
		return aml_code_7;
	}
	public void setAml_code_7(String aml_code_7) {
		this.aml_code_7 = aml_code_7;
	}
	public String getAml_code_8() {
		return aml_code_8;
	}
	public void setAml_code_8(String aml_code_8) {
		this.aml_code_8 = aml_code_8;
	}
	public String getAml_code_9() {
		return aml_code_9;
	}
	public void setAml_code_9(String aml_code_9) {
		this.aml_code_9 = aml_code_9;
	}
	public String getAml_code_10() {
		return aml_code_10;
	}
	public void setAml_code_10(String aml_code_10) {
		this.aml_code_10 = aml_code_10;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	
	
	
	public T2PreviousMast() {
		super();
		// TODO Auto-generated constructor stub
	}
	public T2PreviousMast(String customer_id, String customer_name, String branch_id, String branch_name, String bank_id,
			String ownership_type, Date relationship_date, String customer_rating, Date customer_rating_date,
			Date customer_next_rating_date, String mis_face_to_face, String mis_non_face_to_face,
			String mis_internal_rating_grade, String mis_internal_rating_scale, String remarks, Character del_flg,
			Character entity_cre_flg, Date entity_cre_date, Character mod_flg, String entry_user, String modify_user,
			String auth_user, Date entry_time, Date modify_time, Date auth_time, String aml_code_1, String aml_code_2,
			String aml_code_3, String aml_code_4, String aml_code_5, String aml_code_6, String aml_code_7,
			String aml_code_8, String aml_code_9, String aml_code_10, Date report_date) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.branch_id = branch_id;
		this.branch_name = branch_name;
		this.bank_id = bank_id;
		this.ownership_type = ownership_type;
		this.relationship_date = relationship_date;
		this.customer_rating = customer_rating;
		this.customer_rating_date = customer_rating_date;
		this.customer_next_rating_date = customer_next_rating_date;
		this.mis_face_to_face = mis_face_to_face;
		this.mis_non_face_to_face = mis_non_face_to_face;
		this.mis_internal_rating_grade = mis_internal_rating_grade;
		this.mis_internal_rating_scale = mis_internal_rating_scale;
		this.remarks = remarks;
		this.del_flg = del_flg;
		this.entity_cre_flg = entity_cre_flg;
		this.entity_cre_date = entity_cre_date;
		this.mod_flg = mod_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.aml_code_1 = aml_code_1;
		this.aml_code_2 = aml_code_2;
		this.aml_code_3 = aml_code_3;
		this.aml_code_4 = aml_code_4;
		this.aml_code_5 = aml_code_5;
		this.aml_code_6 = aml_code_6;
		this.aml_code_7 = aml_code_7;
		this.aml_code_8 = aml_code_8;
		this.aml_code_9 = aml_code_9;
		this.aml_code_10 = aml_code_10;
		this.report_date = report_date;
	}
	
	
	
}
