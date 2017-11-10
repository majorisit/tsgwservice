package com.majoris.checkin.dto;

import java.io.Serializable;

/**
 * Memeber Details CSV data
 * 
 * @author Arivu
 *
 */
public class EmailedMembersCsv implements Serializable {
	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	private String memeberId;

	public String getMemeberId() {
		return memeberId;
	}

	public void setMemeberId(String memeberId) {
		this.memeberId = memeberId;
	}
}
