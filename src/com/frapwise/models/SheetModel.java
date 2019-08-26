package com.frapwise.models;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.frapwise.dao.Queries;
import com.frapwise.db.DB;
import com.frapwise.entities.Sheet;

public class SheetModel implements Queries{
	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

	
	
	public SheetModel(){
		DB db = new DB();
		this.conn = db.getConnection();
	}
	
	public int add(Sheet sheet) {
		int flag = 0;
		try {
			this.prep = this.conn.prepareStatement("INSERT INTO sheets(id,sheet) VALUES(null,?)");
			this.prep.setBlob(1, (Blob) sheet.getSheet());
			flag = this.prep.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	
}
