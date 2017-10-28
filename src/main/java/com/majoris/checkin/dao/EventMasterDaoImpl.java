package com.majoris.checkin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.majoris.checkin.dto.EventMaster;

@Repository
public class EventMasterDaoImpl implements EventMasterDao {
	/**
	 * Logger
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private final String SELECT_EVENT_MASTER_BY_ID = "select * from event_master where event_id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public EventMaster findEventMasterById(String id) {
		try
		{
			return jdbcTemplate.queryForObject(SELECT_EVENT_MASTER_BY_ID, new Object[] { id }, new EventMasterMapper());
		}
		catch(EmptyResultDataAccessException e)
		{
			LOG.info(">>>>>>>>>>>>> Could not find a EventMaster for EventId {}",id);
		}
		return null;
	}
}

class EventMasterMapper implements RowMapper<EventMaster> {

	@Override
	public EventMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventMaster event = new EventMaster();
		return event;
	}

}

