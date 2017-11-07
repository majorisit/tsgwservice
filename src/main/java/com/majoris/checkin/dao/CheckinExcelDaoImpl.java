package com.majoris.checkin.dao;

import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.majoris.checkin.config.AppConfig;

/**
 * fs
 * @author Arivu
 *
 */
@Service
public class CheckinExcelDaoImpl implements CheckinExcelDao {
	/**
	 * Logger
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public String getMemberById(String memberId) throws Exception {
		String name = null;		
		PreparedStatement stmt = null;		
		try
		{
//			String strQuery = "Select * from MasterMembersList where Id='" + memberId+"'";
		
			String strQuery = "update MasterMembersList set FirstName='SATHIYA' where Id='" + memberId+"'";			
			
			LOG.info("AppConfig.getConnection() >>>>>>>>>>>>>>>>>>>>> " + AppConfig.getConnection());			
//			stmt = AppConfig.getConnection().createStatement();
			
		     stmt =  AppConfig.getConnection().prepareStatement(strQuery);
//		      pstmt.setString(1, "1");
//		      pstmt.setString(2, "somename" );
		      stmt.executeUpdate();
		      stmt.close();			
			
//			stmt.executeUpdate(strQuery);
//			ResultSet results = stmt.executeQuery(strQuery);
//			while (results.next()) {
//				name = results.getString("FirstName");
//				LOG.info(">>>>>>>>>>>>>>>>>>>>> " + name);
//			}			
		}
		finally
		{
			if(stmt != null)
			{
				stmt.close();
			}
		}
		return name;
	}
}
