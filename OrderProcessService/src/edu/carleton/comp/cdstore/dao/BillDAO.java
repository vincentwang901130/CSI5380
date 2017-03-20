package edu.carleton.comp.cdstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import edu.carleton.comp.cdstore.models.Bill;

public class BillDAO extends DAO{
	public BillDAO(){
		super.init();
		this.dao=new DAOHelper();
	}
	public BillDAO(DAOHelper daohelper){
		super.init();
		this.dao=daohelper;
	}
	@Override
	protected Object getDataObject(ResultSet rs, boolean close) {
		Bill bill=null;
		try{
			if(rs.next())
				bill=new Bill(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9));	
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(close){
				close(rs);
			}
		}
		return bill;
	}
	
	public int getbillid(int userid){
		String sql=this.sqlcode.getProperty("bill.getbillid");
		String sqlstring=MessageFormat.format(sql, new Object[]{userid});
		return getInteger(this.dao.executeLookup(sqlstring, "bill.getbillid"), true);
	}
	
	@Override
	public Object findByPrimaryKey(int billid) {
		String sql=this.sqlcode.getProperty("bill.searchbyid");
		String sqlstring=MessageFormat.format(sql, new Object[]{billid});
		return getDataObject(this.dao.executeLookup(sqlstring,"bill.searchbyid"),true);
	}
	@Override
	public boolean create(Object o) {
			Bill bill=(Bill) o;
			String sql=this.sqlcode.getProperty("bill.create");
			String sqlString=MessageFormat.format(sql, new Object[]{
					bill.getFullname(),
					bill.getAddrline1(),
					bill.getAddrline2(),
					bill.getCity(),
					bill.getProvince(),
					bill.getCountry(),
					bill.getZipcode(),
					bill.getUserid()
			});
			return this.dao.execute(sqlString);
	}
	
	public int createandgetkey(Object o) {
			Bill bill=(Bill) o;
			String sql=this.sqlcode.getProperty("bill.create");
			String sqlString=MessageFormat.format(sql, new Object[]{
					bill.getFullname(),
					bill.getAddrline1(),
					bill.getAddrline2(),
					bill.getCity(),
					bill.getProvince(),
					bill.getCountry(),
					bill.getZipcode(),
					bill.getUserid()
			});
			return this.dao.executeandgetkey(sqlString);
	}

	public boolean update(Object o, int userid) {
		Bill bill=(Bill) o;
		String sql=this.sqlcode.getProperty("bill.update");
		String sqlString=MessageFormat.format(sql, new Object[]{
				bill.getAddrline1(),
				bill.getAddrline2(),
				bill.getCity(),
				bill.getProvince(),
				bill.getCountry(),
				bill.getZipcode(),
				userid				
				});
				
		return this.dao.execute(sqlString);
	}
	
	
	
	@Override
	public boolean delete(int paramInt) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(String paramString) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Object findByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object find(Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected Integer getInteger(ResultSet rs, boolean close) {
		Integer addrid=0;
		try{
			if(rs.next()){
				addrid=rs.getInt(1);
			}
		}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(close){
					close(rs);
				}
			}
		return addrid;
	}
	}
	
