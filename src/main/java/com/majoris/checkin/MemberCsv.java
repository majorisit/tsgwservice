package com.majoris.checkin;

import java.io.Serializable;

import org.springframework.util.StringUtils;

/**
 * Memeber Details CSV data
 * 
 * @author Arivu
 *
 */
public class MemberCsv implements Serializable {
	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	private String memeberId;
	private String firstName;
	private String lastName;
	private String spouseFirstName;
	private String spouseLastName;
	private String email1;
	private String email2;
	private String email3;	
	private String phone1;
	private String phone2;
	private String phone3;	
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String type;
	private String year;
	private String veg;
	private String nonVeg;
	private String vegAmount;
	private String nonVegAmount;	
	private String kids;
	private String checkedOut;
	
	private String addressChanged;
	private String thendralMullai;
	private String numberOfWristbands;
	private String breakfastTotal;
	private String breakfastIssued;
	private String lunchVegTotal;
	private String lunchVegIssued;
	private String lunchNonVegTotal;
	private String lunchNonVegIssued;
	private String dinnerVegTotal;
	private String dinnerVegIssued;
	private String dinnerNonVegTotal;
	private String dinnerNonVegIssued;	

	public String getMemeberId() {
		return memeberId;
	}

	public void setMemeberId(String memeberId) {
		this.memeberId = memeberId;
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

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getVeg() {
		return StringUtils.isEmpty(veg) ? "0" : veg;
	}

	public void setVeg(String veg) {
		this.veg = veg;
	}

	public String getNonVeg() {
		return StringUtils.isEmpty(nonVeg) ? "0" : nonVeg;
	}

	public void setNonVeg(String nonVeg) {
		this.nonVeg = nonVeg;
	}

	public String getSpouseFirstName() {
		return spouseFirstName;
	}

	public void setSpouseFirstName(String spouseFirstName) {
		this.spouseFirstName = spouseFirstName;
	}

	public String getSpouseLastName() {
		return spouseLastName;
	}

	public void setSpouseLastName(String spouseLastName) {
		this.spouseLastName = spouseLastName;
	}

	public String getKids() {
		return kids;
	}

	public void setKids(String kids) {
		this.kids = kids;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getVegAmount() {
		return vegAmount;
	}

	public void setVegAmount(String vegAmount) {
		this.vegAmount = vegAmount;
	}

	public String getNonVegAmount() {
		return nonVegAmount;
	}

	public void setNonVegAmount(String nonVegAmount) {
		this.nonVegAmount = nonVegAmount;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getThendralMullai() {
		return thendralMullai;
	}

	public void setThendralMullai(String thendralMullai) {
		this.thendralMullai = thendralMullai;
	}

	public String getNumberOfWristbands() {
		return numberOfWristbands;
	}

	public void setNumberOfWristbands(String numberOfWristbands) {
		this.numberOfWristbands = numberOfWristbands;
	}

	public String getBreakfastTotal() {
		return breakfastTotal;
	}

	public void setBreakfastTotal(String breakfastTotal) {
		this.breakfastTotal = breakfastTotal;
	}

	public String getBreakfastIssued() {
		return breakfastIssued;
	}

	public void setBreakfastIssued(String breakfastIssued) {
		this.breakfastIssued = breakfastIssued;
	}

	public String getLunchVegTotal() {
		return lunchVegTotal;
	}

	public void setLunchVegTotal(String lunchVegTotal) {
		this.lunchVegTotal = lunchVegTotal;
	}

	public String getLunchVegIssued() {
		return lunchVegIssued;
	}

	public void setLunchVegIssued(String lunchVegIssued) {
		this.lunchVegIssued = lunchVegIssued;
	}

	public String getLunchNonVegTotal() {
		return lunchNonVegTotal;
	}

	public void setLunchNonVegTotal(String lunchNonVegTotal) {
		this.lunchNonVegTotal = lunchNonVegTotal;
	}

	public String getLunchNonVegIssued() {
		return lunchNonVegIssued;
	}

	public void setLunchNonVegIssued(String lunchNonVegIssued) {
		this.lunchNonVegIssued = lunchNonVegIssued;
	}

	public String getDinnerVegTotal() {
		return dinnerVegTotal;
	}

	public void setDinnerVegTotal(String dinnerVegTotal) {
		this.dinnerVegTotal = dinnerVegTotal;
	}

	public String getDinnerVegIssued() {
		return dinnerVegIssued;
	}

	public void setDinnerVegIssued(String dinnerVegIssued) {
		this.dinnerVegIssued = dinnerVegIssued;
	}

	public String getDinnerNonVegTotal() {
		return dinnerNonVegTotal;
	}

	public void setDinnerNonVegTotal(String dinnerNonVegTotal) {
		this.dinnerNonVegTotal = dinnerNonVegTotal;
	}

	public String getDinnerNonVegIssued() {
		return dinnerNonVegIssued;
	}

	public void setDinnerNonVegIssued(String dinnerNonVegIssued) {
		this.dinnerNonVegIssued = dinnerNonVegIssued;
	}

	public String getAddressChanged() {
		return addressChanged;
	}

	public void setAddressChanged(String addressChanged) {
		this.addressChanged = addressChanged;
	}

	public String getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(String checkedOut) {
		this.checkedOut = checkedOut;
	}

}
