package com.majoris.checkin.dao;

import java.util.List;

import com.majoris.checkin.MemberCsv;

/**
 * 
 * @author Arivu
 *
 */
public interface CheckinDao {
	MemberCsv getMemberById(String memberId) throws Exception;

	MemberCsv getFoodTransactionById(String memberId) throws Exception;

	void updateMemberById(String memberId, String checkinFlag) throws Exception;

	void updateFoodIssuedById(String memberId, String breakfastIssued, String lunchVegIssued, String lunchNonVegIssued,
			String dinnerVegIssued, String dinnerNonVegIssued, String checkinFlag) throws Exception;
	
	List<MemberCsv> getAllMembersWithFoodTransactions() throws Exception;
	
	List<MemberCsv> getAllNonMemberFoodTransactions() throws Exception;
	
	List<MemberCsv> getAllMembersForRenewal() throws Exception;	
	
	String getEmailMemberId(String memberId) throws Exception;	
	
	boolean updateEmailSent(String memberId) throws Exception;		
}
