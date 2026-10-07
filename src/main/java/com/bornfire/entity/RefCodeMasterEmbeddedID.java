package com.bornfire.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class RefCodeMasterEmbeddedID implements Serializable{
	
	private String	ref_rec_type;
	private String	ref_code;
	
	
	public String getRef_rec_type() {
		return ref_rec_type;
	}
	
	public RefCodeMasterEmbeddedID(String ref_rec_type, String ref_code) {
		super();
		this.ref_rec_type = ref_rec_type;
		this.ref_code = ref_code;
	
	}
	public void setRef_rec_type(String ref_rec_type) {
		this.ref_rec_type = ref_rec_type;
	}
	public String getRef_code() {
		return ref_code;
	}
	public void setRef_code(String ref_code) {
		this.ref_code = ref_code;
	}
	public RefCodeMasterEmbeddedID() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ref_code == null) ? 0 : ref_code.hashCode());
		result = prime * result + ((ref_rec_type == null) ? 0 : ref_rec_type.hashCode());
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
		RefCodeMasterEmbeddedID other = (RefCodeMasterEmbeddedID) obj;
		if (ref_code == null) {
			if (other.ref_code != null)
				return false;
		} else if (!ref_code.equals(other.ref_code))
			return false;
		if (ref_rec_type == null) {
			if (other.ref_rec_type != null)
				return false;
		} else if (!ref_rec_type.equals(other.ref_rec_type))
			return false;
		return true;
	}
	
	
	
	

	
}