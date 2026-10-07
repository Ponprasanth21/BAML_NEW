package com.bornfire.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransactionMasterEmbeddedId implements Serializable{
	private String	tran_id;
	private String	part_tran_type;
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public String getPart_tran_type() {
		return part_tran_type; 
	}
	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}
	public TransactionMasterEmbeddedId() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((part_tran_type == null) ? 0 : part_tran_type.hashCode());
		result = prime * result + ((tran_id == null) ? 0 : tran_id.hashCode());
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
		TransactionMasterEmbeddedId other = (TransactionMasterEmbeddedId) obj;
		if (part_tran_type == null) {
			if (other.part_tran_type != null)
				return false;
		} else if (!part_tran_type.equals(other.part_tran_type))
			return false;
		if (tran_id == null) {
			if (other.tran_id != null)
				return false;
		} else if (!tran_id.equals(other.tran_id))
			return false;
		return true;
	}

	
}