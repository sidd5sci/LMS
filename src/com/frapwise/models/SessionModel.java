package com.frapwise.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.frapwise.dao.Queries;
import com.frapwise.dao.SessionDao;
import com.frapwise.db.DB;
import com.frapwise.entities.Session;

public class SessionModel implements SessionDao,Queries{
	
	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

	

	
	public SessionModel(){
		DB db = new DB();
		this.conn = db.getConnection();
	}
	@Override
	public Object add(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object update(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getSession(String ssid) {
		// TODO Auto-generated method stub
		Session session = new Session();
		try {
			this.prep = this.conn.prepareStatement(GET_SESSION_BY_SSID);
			this.prep.setString(1, ssid);
			this.result = this.prep.executeQuery();
			
			while(this.result.next()) {
				session.setSsid(this.result.getString("id"));
				session.setUid(this.result.getInt("uid"));
				session.setIpAddress(this.result.getString("ip_address"));
				session.setUserAgent(this.result.getString("user_agent"));
				session.setPayload(this.result.getString("payload"));
				session.setLastActivity(this.result.getString("last_activity"));
			}
		}catch(Exception  e) {
			e.printStackTrace();
		}
		
		return session;
	}

	@Override
	public int setSession(Session session) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			this.prep = this.conn.prepareStatement(ADD_SESSION);
			this.prep.setString(1, session.getSsid());
			this.prep.setInt(2, session.getUid());
			this.prep.setString(3, session.getIpAddress());
			this.prep.setString(4, session.getUserAgent());
			this.prep.setString(5, session.getPayload());
			flag = this.prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
