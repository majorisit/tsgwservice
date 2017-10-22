package com.majoris.checkin;

import java.io.Serializable;

/**
 * Memeber Details CSV data
 * 
 * @author Arivu
 *
 */
public class CheckedOutMembersCsv implements Serializable {
	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	private String memeberId;
	private String firstName;
	private String lastName;
	private String wristbands;
	private String addressChanged;
	private String thendralMullai;

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

	public String getWristbands() {
		return wristbands;
	}

	public void setWristbands(String wristbands) {
		this.wristbands = wristbands;
	}

	public String getAddressChanged() {
		return addressChanged;
	}

	public void setAddressChanged(String addressChanged) {
		this.addressChanged = addressChanged;
	}

	public String getThendralMullai() {
		return thendralMullai;
	}

	public void setThendralMullai(String thendralMullai) {
		this.thendralMullai = thendralMullai;
	}

}
