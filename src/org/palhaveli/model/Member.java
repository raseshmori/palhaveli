package org.palhaveli.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private String memberId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String flatApt;
	private String apartment;
	private String landmark;
	private String road;
	private String region;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String mobile;
	private String home;
	private String email;
	private String caste;
	private String homeTown;
	private String profession;
	private String familyMembers;
	private boolean bramsambandh;
	private boolean thakorji;
	private boolean isDuplicate;
	private List<String> groupIds;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFlatApt() {
		return flatApt;
	}
	public void setFlatApt(String flatApt) {
		this.flatApt = flatApt;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getFamilyMembers() {
		return familyMembers;
	}
	public void setFamilyMembers(String familyMembers) {
		this.familyMembers = familyMembers;
	}
	public boolean isBramsambandh() {
		return bramsambandh;
	}
	public void setBramsambandh(boolean bramsambandh) {
		this.bramsambandh = bramsambandh;
	}
	public boolean isThakorji() {
		return thakorji;
	}
	public void setThakorji(boolean thakorji) {
		this.thakorji = thakorji;
	}
	public boolean isDuplicate() {
		return isDuplicate;
	}
	public void setDuplicate(boolean isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public List<String> getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	
	public void addGroupId(String groupId){
		if(groupIds==null)
			groupIds=new ArrayList<String>();
		
		groupIds.add(groupId);
	}
}
