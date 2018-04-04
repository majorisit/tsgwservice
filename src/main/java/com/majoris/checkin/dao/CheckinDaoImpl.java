package com.majoris.checkin.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.majoris.checkin.MemberCsv;
import com.majoris.checkin.config.AppConfig;

/**
 * 
 * @author Arivu
 *
 */
@Service
public class CheckinDaoImpl implements CheckinDao {
	/**
	 * Logger
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public MemberCsv getMemberById(String memberId) throws Exception {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		MemberCsv member = null;
		try {
			String sql = "select * from MasterMembers where Id='" + memberId + "'";
			LOG.info("getMemberById() -> " + sql);
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs != null && rs.next()) {
					member = new MemberCsv();
					member.setMemeberId(rs.getString("Id"));
					member.setFirstName(rs.getString("FirstName"));
					member.setLastName(rs.getString("LastName"));
					member.setEmail1(rs.getString("Email1"));
					member.setEmail2(rs.getString("Email2"));
					member.setPhone1(rs.getString("Phone1"));
					member.setPhone2(rs.getString("Phone1"));
					member.setAddress1(rs.getString("Address1"));
					member.setAddress2(rs.getString("Address2"));
					member.setCity(rs.getString("City"));
					member.setState(rs.getString("State"));
					member.setZipCode(rs.getString("ZipCode"));
					member.setCountry(rs.getString("Country"));
					member.setType(rs.getString("Type"));
					member.setYear(rs.getString("Since"));
					member.setCheckedOut(rs.getString("CheckedOut"));
					member.setSpouseFirstName(rs.getString("SpouseFirstName"));
					member.setSpouseLastName(rs.getString("SpouseLastName"));
					member.setKids(rs.getString("Childrens"));
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return member;
	}

	@Override
	public void updateMemberById(String memberId, String checkedOut) throws Exception {
		String sql = "update MasterMembers set CheckedOut='" + checkedOut + "' where Id='" + memberId + "'";
		LOG.info("updateMemberById() -> " + sql);
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	public void updateFoodIssuedById(String memberId, String breakfastIssued, String lunchVegIssued,
			String lunchNonVegIssued, String dinnerVegIssued, String dinnerNonVegIssued, String checkinFlag)
			throws Exception {
		String sql = "update FoodTransaction set CheckedOut='" + checkinFlag + "',BreakfastIssued='" + breakfastIssued
				+ "',LunchVegIssued='" + lunchVegIssued + "',LunchNonVegIssued='" + lunchNonVegIssued
				+ "',DinnerVegIssued='" + dinnerVegIssued + "',DinnerNonVegIssued='" + dinnerNonVegIssued
				+ "' where MemberId='" + memberId + "'";
		LOG.info("updateFoodIssuedById() -> " + sql);
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	public MemberCsv getFoodTransactionById(String memberId) throws Exception {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		MemberCsv member = null;
		try {
			String sql = "select * from FoodTransaction where MemberID='" + memberId + "'";
//			LOG.info("getNonMemberByID() -> " + sql);
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs != null && rs.next()) {
					member = new MemberCsv();
					member.setMemeberId(rs.getString("MemberID"));
					member.setFirstName(rs.getString("PayerFirstName"));
					member.setLastName(rs.getString("PayerLastName"));
					member.setEmail1(rs.getString("PayerEmail"));
					member.setBreakfastTotal(rs.getString("Breakfast"));
					member.setBreakfastIssued(rs.getString("BreakfastIssued"));
					member.setLunchVegTotal(rs.getString("LunchVeg"));
					member.setLunchVegIssued(rs.getString("LunchVegIssued"));
					member.setLunchNonVegTotal(rs.getString("LunchNonVeg"));
					member.setLunchNonVegIssued(rs.getString("LunchNonVegIssued"));
					member.setDinnerVegTotal(rs.getString("DinnerVeg"));
					member.setDinnerVegIssued(rs.getString("DinnerVegIssued"));
					member.setDinnerNonVegTotal(rs.getString("DinnerNonVeg"));
					member.setDinnerNonVegIssued(rs.getString("DinnerNonVegIssued"));
					member.setCheckedOut(rs.getString("CheckedOut"));
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return member;
	}

	@Override
	public List<MemberCsv> getAllMembersWithFoodTransactions() throws Exception {
		List<MemberCsv> members = new ArrayList<MemberCsv>();
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		MemberCsv member = null;
		try {
			String sql = "select * from MasterMembers";
//			LOG.info("getAllMembersWithFoodTransactions() -> " + sql);
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs != null && rs.next()) {
					member = new MemberCsv();
					member.setMemeberId(rs.getString("Id"));
					member.setFirstName(rs.getString("FirstName"));
					member.setLastName(rs.getString("LastName"));
					member.setEmail1(rs.getString("Email1"));
					member.setEmail2(rs.getString("Email2"));
					member.setPhone1(rs.getString("Phone1"));
					member.setPhone2(rs.getString("Phone2"));
					member.setAddress1(rs.getString("Address1"));
					member.setAddress2(rs.getString("Address2"));
					member.setCity(rs.getString("City"));
					member.setState(rs.getString("State"));
					member.setZipCode(rs.getString("ZipCode"));
					member.setCountry(rs.getString("Country"));
					member.setType(rs.getString("Type"));
					member.setYear(rs.getString("Since"));
					member.setCheckedOut(rs.getString("CheckedOut"));
					
					
					MemberCsv food = getFoodTransactionById(member.getMemeberId());
					if(food != null)
					{
						member.setBreakfastTotal(food.getBreakfastTotal());
						member.setLunchVegTotal(food.getLunchVegTotal());
						member.setLunchNonVegTotal(food.getLunchNonVegTotal());
						member.setDinnerVegTotal(food.getDinnerVegTotal());
						member.setDinnerNonVegTotal(food.getDinnerNonVegTotal());

						member.setBreakfastIssued(food.getBreakfastIssued());
						member.setLunchVegIssued(food.getLunchVegIssued());
						member.setLunchNonVegIssued(food.getLunchNonVegIssued());
						member.setDinnerVegIssued(food.getDinnerVegIssued());
						member.setDinnerNonVegIssued(food.getDinnerNonVegIssued());
					}
					members.add(member);
				}
			}
		} finally {

			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return members;
	}
	
	@Override
	public List<MemberCsv> getAllNonMemberFoodTransactions() throws Exception {
		List<MemberCsv> members = new ArrayList<MemberCsv>();		
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		MemberCsv member = null;
		try {
//			String sql = "select * from FoodTransaction where MemberID like 'T6%'";
			String sql = "select * from FoodTransaction";
//			LOG.info("getAllNonMemberFoodTransactions() -> " + sql);
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs != null && rs.next()) {
					member = new MemberCsv();
					member.setMemeberId(rs.getString("MemberID"));
					member.setFirstName(rs.getString("PayerFirstName"));
					member.setLastName(rs.getString("PayerLastName"));
					member.setEmail1(rs.getString("PayerEmail"));
					member.setBreakfastTotal(rs.getString("Breakfast"));
					member.setBreakfastIssued(rs.getString("BreakfastIssued"));
					member.setLunchVegTotal(rs.getString("LunchVeg"));
					member.setLunchVegIssued(rs.getString("LunchVegIssued"));
					member.setLunchNonVegTotal(rs.getString("LunchNonVeg"));
					member.setLunchNonVegIssued(rs.getString("LunchNonVegIssued"));
					member.setDinnerVegTotal(rs.getString("DinnerVeg"));
					member.setDinnerVegIssued(rs.getString("DinnerVegIssued"));
					member.setDinnerNonVegTotal(rs.getString("DinnerNonVeg"));
					member.setDinnerNonVegIssued(rs.getString("DinnerNonVegIssued"));
					member.setCheckedOut(rs.getString("CheckedOut"));
					members.add(member);
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return members;
	}	
	
	@Override
	public List<MemberCsv> getAllMembersForRenewal() throws Exception {
		List<MemberCsv> members = new ArrayList<MemberCsv>();		
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		MemberCsv member = null;
		try {
//			String sql = "select * from NonRenewedMembers where MemberID = '670'";
			String sql = "select * from NonRenewedMembers";
//			LOG.info("getAllNonMemberFoodTransactions() -> " + sql);
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs != null && rs.next()) {
					member = new MemberCsv();
					member.setMemeberId(rs.getString("MemberID"));
					member.setFirstName(rs.getString("FirstName"));
					member.setLastName(rs.getString("LastName"));
					member.setEmail1(rs.getString("Email"));
					member.setEmail2(rs.getString("Email2"));
					member.setEmail3(rs.getString("Email3"));
					members.add(member);
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return members;
	}		
	
	@Override
	public String getEmailMemberId(String memberId) throws Exception {
		String id = null;
		String sql = "select MemberId from EmailSent where MemberId='" + memberId + "'";
		LOG.info("selectEmailMemberId() -> " + sql);
		Statement stmt = null;
		ResultSet rs = null;		
		Connection conn = null;
		try {
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					id = rs.getString("MemberID");
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return id;
	}	
	
	@Override	
	public boolean updateEmailSent(String memberId) throws Exception
	{
		int num = 0;
		String sql = "insert into EmailSent values('" + memberId + "')";
		LOG.info("updateEmailSent() -> " + sql);
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppConfig.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				num = stmt.executeUpdate(sql);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return num >= 0;
	}
}
