package com.bornfire.entity.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ENTITY_ALIAS")
public class EntityAlias {
	
	private String aliasname;
	private String quality;
	private String note;
	
	
	public EntityAlias() {}
	
	public String getAliasname() {
		return aliasname;
	}
	@XmlElement(name = "ALIAS_NAME")
    public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	
	
	public String getQuality() {
		return quality;
	}
	@XmlElement(name = "QUALITY")
    public void setQuality(String quality) {
		this.quality = quality;
	}
	
	
	public String getNote() {
		return note;
	}
	@XmlElement(name = "NOTE")
    public void setNote(String note) {
		this.note = note;
	}
	
	public EntityAlias(String aliasname, String quality, String note) {
		super();
		this.aliasname = aliasname;
		this.quality = quality;
		this.note = note;
	}

	@Override
	public String toString() {
		return "EntityAlias [aliasname=" + aliasname + ", quality=" + quality + ", note=" + note + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
