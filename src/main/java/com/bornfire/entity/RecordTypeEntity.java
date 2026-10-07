package com.bornfire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RECORD_TYPE_TABLE")
public class RecordTypeEntity {
	
	@Id
	private String	ref_rec_type;
	private String	rec_desc;
	public String getRef_rec_type() {
		return ref_rec_type;
	}
	public void setRef_rec_type(String ref_rec_type) {
		this.ref_rec_type = ref_rec_type;
	}
	public String getRec_desc() {
		return rec_desc;
	}
	public void setRec_desc(String rec_desc) {
		this.rec_desc = rec_desc;
	}
	public RecordTypeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}