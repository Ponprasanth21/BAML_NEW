package com.bornfire.entity.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "INDIVIDUAL_ALIAS")
public class IndividualAlias {

	private String quality;
	private String aliasName;
	private String note;

	public String getNote() {
		return note;
	}

	@XmlElement(name = "NOTE", required = false)
	public void setNote(String note) {
		this.note = note;
	}

	public String getQuality() {
		return quality;
	}

	@XmlElement(name = "QUALITY", required = false)
	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getAliasName() {
		return aliasName;
	}

	@XmlElement(name = "ALIAS_NAME", required = false)
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}


	public IndividualAlias(String quality, String aliasName, String note) {
		this.quality = quality;
		this.aliasName = aliasName;
		this.note = note;
	}

	public IndividualAlias() {
	}

	@Override
	public String toString() {
		return "IndividualAlias [quality=" + quality + ", aliasName=" + aliasName + ", note=" + note + "]";
	}

	
}
