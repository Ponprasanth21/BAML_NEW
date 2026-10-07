package com.bornfire.entity.xml;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

@XmlRootElement(name = "INDIVIDUAL")
@XmlType(propOrder = {"dataId", "versionNum", "firstName", "secondName", "thirdName","unListType", 
		"referenceNum", "listedOn","nameOriginalScript", "comments1", "designation", "nationality", "listType", "title","lastDayUpdated", "individualAlias"
		,"individualAddress", "individualDateOfBirth" ,"individualPlaceOfBirth","individualDocument", "sortKey","sortKeyLastMod"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Individual {
	
	
	private String dataId;
	private String versionNum;
	private String firstName;
	private String secondName;
	
	private String thirdName;
	private String unListType;
	private String referenceNum;
	private String listedOn;
	private String nameOriginalScript;
	private String comments1;
	
	private List<Title> title;
	private List<Designation> designation;
	private List<Nationality> nationality;
	private List<ListType> listType;
	private List<LastDayUpdated> lastDayUpdated;
	private List<IndividualAlias> individualAlias;
	private List<IndividualAddress> individualAddress;
	private List<IndividualDateOfBirth> individualDateOfBirth;
	private List<IndividualPlaceOfBirth> individualPlaceOfBirth;
	private List<IndividualDocument> individualDocument;
	private String sortKey;
	private String sortKeyLastMod;
	
	
	
	
	
	public String getNameOriginalScript() {
		return nameOriginalScript;
	}
	@XmlElement(name = "NAME_ORIGINAL_SCRIPT", required = false)
	public void setNameOriginalScript(String nameOriginalScript) {
		this.nameOriginalScript = nameOriginalScript;
	}

	public String getSortKey() {
		return sortKey;
	}

	@XmlElement(name = "SORT_KEY", required = false)
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getSortKeyLastMod() {
		return sortKeyLastMod;
	}

	@XmlElement(name = "SORT_KEY_LAST_MOD", required = false)
	public void setSortKeyLastMod(String sortKeyLastMod) {
		this.sortKeyLastMod = sortKeyLastMod;
	}

	public String getDataId() {
		return dataId;
	}
	
	@XmlElement(name = "DATAID", required = false)
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getVersionNum() {
		return versionNum;
	}
	
	@XmlElement(name = "VERSIONNUM", required = false)
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getFirstName() {
		return firstName;
	}
	
	@XmlElement(name = "FIRST_NAME", required = false)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	
	@XmlElement(name = "SECOND_NAME", required = false)
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getThirdName() {
		return thirdName;
	}
	
	@XmlElement(name = "THIRD_NAME", required = false)
	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}
	public String getUnListType() {
		return unListType;
	}
	
	@XmlElement(name = "UN_LIST_TYPE", required = false)
	public void setUnListType(String unListType) {
		this.unListType = unListType;
	}
	public String getReferenceNum() {
		return referenceNum;
	}
	
	@XmlElement(name = "REFERENCE_NUMBER", required = false)
	public void setReferenceNum(String referenceNum) {
		this.referenceNum = referenceNum;
	}
	public String getListedOn() {
		return listedOn;
	}
	
	@XmlElement(name = "LISTED_ON", required = false)
	public void setListedOn(String listedOn) {
		this.listedOn = listedOn;
	}
	public String getComments1() {
		return comments1;
	}
	
	@XmlElement(name = "COMMENTS1", required = false)
	public void setComments1(String comments1) {
		this.comments1 = comments1;
	}
	public List<Designation> getDesignation() {
		return designation;
	}
	
	
	
	
	public List<Title> getTitle() {
		return title;
	}
	@XmlElement(name = "TITLE", required = false)
	public void setTitle(List<Title> title) {
		this.title = title;
	}

	@XmlElement(name = "DESIGNATION", required = false)
	public void setDesignation(List<Designation> designation) {
		this.designation = designation;
	}
	
	
	
	public List<Nationality> getNationality() {
		return nationality;
	}
	
	@XmlElement(name = "NATIONALITY", required = false)
	public void setNationality(List<Nationality> nationality) {
		this.nationality = nationality;
	}
	public List<ListType> getListType() {
		return listType;
	}
	
	@XmlElement(name = "LIST_TYPE", required = false)
	public void setListType(List<ListType> listType) {
		this.listType = listType;
	}
	
	public List<LastDayUpdated> getLastDayUpdated() {
		return lastDayUpdated;
	}
	public void setLastDayUpdated(List<LastDayUpdated> lastDayUpdated) {
		this.lastDayUpdated = lastDayUpdated;
	}
	public List<IndividualAlias> getIndividualAlias() {
		return individualAlias;
	}
	
	@XmlElement(name = "INDIVIDUAL_ALIAS", required = false)
	public void setIndividualAlias(List<IndividualAlias> individualAlias) {
		this.individualAlias = individualAlias;
	}
	public List<IndividualAddress> getIndividualAddress() {
		return individualAddress;
	}
	
	@XmlElement(name = "INDIVIDUAL_ADDRESS", required = false)
	public void setIndividualAddress(List<IndividualAddress> individualAddress) {
		this.individualAddress = individualAddress;
	}
	public List<IndividualDateOfBirth> getIndividualDateOfBirth() {
		return individualDateOfBirth;
	}
	
	@XmlElement(name = "INDIVIDUAL_DATE_OF_BIRTH", required = false)
	public void setIndividualDateOfBirth(List<IndividualDateOfBirth> individualDateOfBirth) {
		this.individualDateOfBirth = individualDateOfBirth;
	}
	public List<IndividualDocument> getIndividualDocument() {
		return individualDocument;
	}
	
	@XmlElement(name = "INDIVIDUAL_DOCUMENT", required = false)
	public void setIndividualDocument(List<IndividualDocument> individualDocument) {
		this.individualDocument = individualDocument;
	}
	
	
	public Individual() {}

	public List<IndividualPlaceOfBirth> getIndividualPlaceOfBirth() {
		return individualPlaceOfBirth;
	}

	@XmlElement(name = "INDIVIDUAL_PLACE_OF_BIRTH", required = false)
	public void setIndividualPlaceOfBirth(List<IndividualPlaceOfBirth> individualPlaceOfBirth) {
		this.individualPlaceOfBirth = individualPlaceOfBirth;
	}

	public Individual(String dataId, String versionNum, String firstName, String secondName, String thirdName,
			String unListType, String referenceNum,String nameOriginalScript,  String listedOn, String comments1,List<Title> title, List<Designation> designation,
			List<Nationality> nationality, List<ListType> listType, List<LastDayUpdated> lastDayUpdated,
			List<IndividualAlias> individualAlias, List<IndividualAddress> individualAddress,
			List<IndividualDateOfBirth> individualDateOfBirth, List<IndividualPlaceOfBirth> individualPlaceOfBirth,
			List<IndividualDocument> individualDocument, String sortKey, String sortKeyLastMod) {
		this.dataId = dataId;
		this.versionNum = versionNum;
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.unListType = unListType;
		this.referenceNum = referenceNum;
		this.nameOriginalScript= nameOriginalScript;
		this.listedOn = listedOn;
		this.comments1 = comments1;
		this.title = title;
		this.designation = designation;
		this.nationality = nationality;
		this.listType = listType;
		this.lastDayUpdated = lastDayUpdated;
		this.individualAlias = individualAlias;
		this.individualAddress = individualAddress;
		this.individualDateOfBirth = individualDateOfBirth;
		this.individualPlaceOfBirth = individualPlaceOfBirth;
		this.individualDocument = individualDocument;
		this.sortKey = sortKey;
		this.sortKeyLastMod = sortKeyLastMod;
	}
	@Override
	public String toString() {
		return "Individual [dataId=" + dataId + ", versionNum=" + versionNum + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", thirdName=" + thirdName + ", unListType=" + unListType
				+ ", referenceNum=" + referenceNum + ", listedOn=" + listedOn + ", nameOriginalScript="
				+ nameOriginalScript + ", comments1=" + comments1 + ", title=" + title + ", designation=" + designation
				+ ", nationality=" + nationality + ", listType=" + listType + ", lastDayUpdated=" + lastDayUpdated
				+ ", individualAlias=" + individualAlias + ", individualAddress=" + individualAddress
				+ ", individualDateOfBirth=" + individualDateOfBirth + ", individualPlaceOfBirth="
				+ individualPlaceOfBirth + ", individualDocument=" + individualDocument + ", sortKey=" + sortKey
				+ ", sortKeyLastMod=" + sortKeyLastMod + "]";
	}


	
	
	

	
	
	
	
	
	
	
	
	
}