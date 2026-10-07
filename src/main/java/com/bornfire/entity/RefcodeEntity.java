package com.bornfire.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "BAML_REFERENCE_CODE_TABLE")
public class RefcodeEntity {
	

	
	

	public RefcodeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	@EmbeddedId
	RefCodeMasterEmbeddedID refCodeId;
	
	public RefCodeMasterEmbeddedID getRefCodeId() {
		return refCodeId;
	}
	public void setRefCodeId(RefCodeMasterEmbeddedID refCodeId) {
		this.refCodeId = refCodeId;
	}
	public String getRpt_code() {
		return rpt_code;
	}
	public void setRpt_code(String rpt_code) {
		this.rpt_code = rpt_code;
	}

	private String	rpt_code;
	private String	del_flg;
	private String	ref_desc;
	private String	long_ref_code;
	private String	ref_rec_desc;
	private String	ref_type;
	private String	module;

	
	private String	rpt_desc;
	private String	entity_flag;
	private String	modify_flag;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	verify_time;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	

	
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getRef_desc() {
		return ref_desc;
	}
	public void setRef_desc(String ref_desc) {
		this.ref_desc = ref_desc;
	}
	public String getLong_ref_code() {
		return long_ref_code;
	}
	public void setLong_ref_code(String long_ref_code) {
		this.long_ref_code = long_ref_code;
	}
	public String getRef_rec_desc() {
		return ref_rec_desc;
	}
	public void setRef_rec_desc(String ref_rec_desc) {
		this.ref_rec_desc = ref_rec_desc;
	}
	public String getRef_type() {
		return ref_type;
	}
	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

	public String getRpt_desc() {
		return rpt_desc;
	}
	public void setRpt_desc(String rpt_desc) {
		this.rpt_desc = rpt_desc;
	}
	public String getEntity_flag() {
		return entity_flag;
	}
	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}
	public String getModify_flag() {
		return modify_flag;
	}
	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
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
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
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
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public void setRefCodeId(String rpt_code) {
		
		
	}
	@Override
	public String toString() {
		return "RefcodeEntity [refCodeId=" + refCodeId + ", del_flg=" + del_flg + ", ref_desc=" + ref_desc
				+ ", long_ref_code=" + long_ref_code + ", ref_rec_desc=" + ref_rec_desc + ", ref_type=" + ref_type
				+ ", module=" + module + ", rpt_desc=" + rpt_desc + ", entity_flag=" + entity_flag + ", modify_flag="
				+ modify_flag + ", entry_time=" + entry_time + ", modify_time=" + modify_time + ", verify_time="
				+ verify_time + ", entry_user=" + entry_user + ", modify_user=" + modify_user + ", verify_user="
				+ verify_user + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((del_flg == null) ? 0 : del_flg.hashCode());
		result = prime * result + ((entity_flag == null) ? 0 : entity_flag.hashCode());
		result = prime * result + ((entry_time == null) ? 0 : entry_time.hashCode());
		result = prime * result + ((entry_user == null) ? 0 : entry_user.hashCode());
		result = prime * result + ((long_ref_code == null) ? 0 : long_ref_code.hashCode());
		result = prime * result + ((modify_flag == null) ? 0 : modify_flag.hashCode());
		result = prime * result + ((modify_time == null) ? 0 : modify_time.hashCode());
		result = prime * result + ((modify_user == null) ? 0 : modify_user.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((refCodeId == null) ? 0 : refCodeId.hashCode());
		result = prime * result + ((ref_desc == null) ? 0 : ref_desc.hashCode());
		result = prime * result + ((ref_rec_desc == null) ? 0 : ref_rec_desc.hashCode());
		result = prime * result + ((ref_type == null) ? 0 : ref_type.hashCode());
		result = prime * result + ((rpt_code == null) ? 0 : rpt_code.hashCode());
		result = prime * result + ((rpt_desc == null) ? 0 : rpt_desc.hashCode());
		result = prime * result + ((verify_time == null) ? 0 : verify_time.hashCode());
		result = prime * result + ((verify_user == null) ? 0 : verify_user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RefcodeEntity other = (RefcodeEntity) obj;
		if (del_flg == null) {
			if (other.del_flg != null)
				return false;
		} else if (!del_flg.equals(other.del_flg))
			return false;
		if (entity_flag == null) {
			if (other.entity_flag != null)
				return false;
		} else if (!entity_flag.equals(other.entity_flag))
			return false;
		if (entry_time == null) {
			if (other.entry_time != null)
				return false;
		} else if (!entry_time.equals(other.entry_time))
			return false;
		if (entry_user == null) {
			if (other.entry_user != null)
				return false;
		} else if (!entry_user.equals(other.entry_user))
			return false;
		if (long_ref_code == null) {
			if (other.long_ref_code != null)
				return false;
		} else if (!long_ref_code.equals(other.long_ref_code))
			return false;
		if (modify_flag == null) {
			if (other.modify_flag != null)
				return false;
		} else if (!modify_flag.equals(other.modify_flag))
			return false;
		if (modify_time == null) {
			if (other.modify_time != null)
				return false;
		} else if (!modify_time.equals(other.modify_time))
			return false;
		if (modify_user == null) {
			if (other.modify_user != null)
				return false;
		} else if (!modify_user.equals(other.modify_user))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (refCodeId == null) {
			if (other.refCodeId != null)
				return false;
		} else if (!refCodeId.equals(other.refCodeId))
			return false;
		if (ref_desc == null) {
			if (other.ref_desc != null)
				return false;
		} else if (!ref_desc.equals(other.ref_desc))
			return false;
		if (ref_rec_desc == null) {
			if (other.ref_rec_desc != null)
				return false;
		} else if (!ref_rec_desc.equals(other.ref_rec_desc))
			return false;
		if (ref_type == null) {
			if (other.ref_type != null)
				return false;
		} else if (!ref_type.equals(other.ref_type))
			return false;
		if (rpt_code == null) {
			if (other.rpt_code != null)
				return false;
		} else if (!rpt_code.equals(other.rpt_code))
			return false;
		if (rpt_desc == null) {
			if (other.rpt_desc != null)
				return false;
		} else if (!rpt_desc.equals(other.rpt_desc))
			return false;
		if (verify_time == null) {
			if (other.verify_time != null)
				return false;
		} else if (!verify_time.equals(other.verify_time))
			return false;
		if (verify_user == null) {
			if (other.verify_user != null)
				return false;
		} else if (!verify_user.equals(other.verify_user))
			return false;
		return true;
	}


	
	
	
	
	

}