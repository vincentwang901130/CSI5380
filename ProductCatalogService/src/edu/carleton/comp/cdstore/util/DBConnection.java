package edu.carleton.comp.cdstore.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection connection=null;
	private PreparedStatement preparedStatement=null;
	public DBConnection(){
		this.connection=ConnectionPool.getInstance().getConnection();
	}
	
	public boolean executeStatement(String sql){
		try{
			Statement statement=this.connection.createStatement();
			statement.execute(sql);
			statement.close();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public int executeUpdateandGetKey(String sql) {
		int key=-1;
		Statement statement;
		try {
			statement = this.connection.createStatement();
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=statement.getGeneratedKeys();
			if(rs.next()){
				key=rs.getInt(1);
			}
			statement.close();
			return key;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  return key;
		}
		
	}

	public int executeUpdate(String sql){
		try{
			Statement statement=this.connection.createStatement();
			int rowsEffected=statement.executeUpdate(sql);
			statement.close();
			return rowsEffected;
			}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public PreparedStatement prepareStatement(String sql)throws Exception{
		try{
			this.preparedStatement=this.connection.prepareStatement(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return this.preparedStatement;
	}
	
	public void commit()throws SQLException{
		this.connection.commit();
	}
	
	public boolean returnConnection(){
		return ConnectionPool.getInstance().returnConnection(this.connection);
	}
	
	public String getURL(){
		try{
			return this.connection.getMetaData().getURL();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	
 }
