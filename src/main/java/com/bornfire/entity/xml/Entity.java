package com.bornfire.entity.xml;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

@XmlRootElement(name = "ENTITY")
@XmlType(propOrder = { "dataId", "versionNum", "firstName", "unListType", "referenceNum", "listedOn","nameOriginalScript", "comments1" ,"listType"
		                 ,"lastDayUpdated","entityAlias", "entityAddress","sortKey","sortKeyLastMod"})


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entity {

	private String dataId;
	private String versionNum;
	private String firstName;
	
	private String unListType;
	private String referenceNum;
	
	private String listedOn;
	private String nameOriginalScript;


	private String comments1;

	private List<ListType> listType;
	private List<LastDayUpdated> lastDayUpdated;
	private List<EntityAlias> entityAlias;
	private List<EntityAddress> entityAddress;
	private String sortKey;
	private String sortKeyLastMod;

	public String getDataId() {
		return dataId;
	}

	@XmlElement(name = "DATAID")
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getVersionNum() {
		return versionNum;
	}

	@XmlElement(name = "VERSIONNUM")
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getFirstName() {
		return firstName;
	}

	@XmlElement(name = "FIRST_NAME")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUnListType() {
		return unListType;
	}

	@XmlElement(name = "UN_LIST_TYPE")
	public void setUnListType(String unListType) {
		this.unListType = unListType;
	}

	public String getReferenceNum() {
		return referenceNum;
	}

	@XmlElement(name = "REFERENCE_NUMBER")

	public void setReferenceNum(String referenceNum) {
		this.referenceNum = referenceNum;
	}

	
	
	
	
	public String getNameOriginalScript() {
		return nameOriginalScript;
	}
	@XmlElement(name = "NAME_ORIGINAL_SCRIPT")
        public void setNameOriginalScript(String nameOriginalScript) {
		this.nameOriginalScript = nameOriginalScript;
	}

	public String getListedOn() {
		return listedOn;
	}

	@XmlElement(name = "LISTED_ON")

	public void setListedOn(String listedOn) {
		this.listedOn = listedOn;
	}

	public String getComments1() {
		return comments1;
	}

	@XmlElement(name = "COMMENTS1")

	public void setComments1(String comments1) {
		this.comments1 = comments1;
	}

	public List<ListType> getListType() {
		return listType;
	}

	@XmlElement(name = "LIST_TYPE")

	public void setListType(List<ListType> listType) {
		this.listType = listType;
	}

	public List<LastDayUpdated> getLastDayUpdated() {
		return lastDayUpdated;
	}

	@XmlElement(name = "LAST_DAY_UPDATED")

	public void setLastDayUpdated(List<LastDayUpdated> lastDayUpdated) {
		this.lastDayUpdated = lastDayUpdated;
	}

	public List<EntityAlias> getEntityAlias() {
		return entityAlias;
	}

	@XmlElement(name = "ENTITY_ALIAS")

	public void setEntityAlias(List<EntityAlias> entityAlias) {
		this.entityAlias = entityAlias;
	}

	public List<EntityAddress> getEntityAddress() {
		return entityAddress;
	}

	@XmlElement(name = "ENTITY_ADDRESS")
	public void setEntityAddress(List<EntityAddress> entityAddress) {
		this.entityAddress = entityAddress;
	}

	public String getSortKey() {
		return sortKey;
	}

	@XmlElement(name = "SORT_KEY")
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getSortKeyLastMod() {
		return sortKeyLastMod;
	}

	@XmlElement(name = "SORT_KEY_LAST_MOD")
	public void setSortKeyLastMod(String sortKeyLastMod) {
		this.sortKeyLastMod = sortKeyLastMod;
	}

	public Entity(String dataId, String versionNum, String firstName, String unListType, String referenceNum,
			String listedOn,String nameOriginalScript, String comments1, List<ListType> listType, List<LastDayUpdated> lastDayUpdated,
			List<EntityAlias> entityAlias, List<EntityAddress> entityAddress, String sortKey, String sortKeyLastMod) {
		super();
		this.dataId = dataId;
		this.versionNum = versionNum;
		this.firstName = firstName;
		this.unListType = unListType;
		this.referenceNum = referenceNum;
		this.listedOn = listedOn;
		this.nameOriginalScript = nameOriginalScript;
		this.comments1 = comments1;
		this.listType = listType;
		this.lastDayUpdated = lastDayUpdated;
		this.entityAlias = entityAlias;
		this.entityAddress = entityAddress;
		this.sortKey = sortKey;
		this.sortKeyLastMod = sortKeyLastMod;
	}

	
	
	@Override
	public String toString() {
		return "Entity [dataId=" + dataId + ", versionNum=" + versionNum + ", firstName=" + firstName + ", unListType="
				+ unListType + ", referenceNum=" + referenceNum + ", listedOn=" + listedOn + ", nameOriginalScript="
				+ nameOriginalScript + ", comments1=" + comments1 + ", listType=" + listType + ", lastDayUpdated="
				+ lastDayUpdated + ", entityAlias=" + entityAlias + ", entityAddress=" + entityAddress + ", sortKey="
				+ sortKey + ", sortKeyLastMod=" + sortKeyLastMod + "]";
	}

	public Entity() {
	
	
	}

}