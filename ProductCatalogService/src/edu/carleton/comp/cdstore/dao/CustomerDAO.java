package edu.carleton.comp.cdstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import edu.carleton.comp.cdstore.models.Customer;

public class CustomerDAO extends DAO {
		
		public CustomerDAO(){
			super.init();
			this.dao=new DAOHelper();
		}
		public CustomerDAO(DAOHelper daohelper){
			super.init();
			this.dao=daohelper;
		}
		
		public List<Object> findall(){
			String sql=this.sqlcode.getProperty("customer.findall");
			return super.processResultSet(this.dao.executeLookup(sql, "customer.findall"));
		}
		
		@Override
		public Object findByPrimaryKey(String email) {
			String sql=this.sqlcode.getProperty("customer.searchuserbyemail");
			String sqlstring=MessageFormat.format(sql, new Object[]{email});
			return getDataObject(this.dao.executeLookup(sqlstring,"customer.searchuserbyemail"),true);
		}
		@Override
		protected Object getDataObject(ResultSet rs, boolean close) {
			Customer customer=null;
			try{
				if(rs.next()){
					customer=new Customer(Integer.valueOf(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				if(close){
					close(rs);
				}
			}
			return customer;
		}
		
	@Override
	public boolean create(Object o) {
		Customer cus=(Customer) o;
		String sql1=this.sqlcode.getProperty("customer.searchuserbyemail");
		String sqlstring=MessageFormat.format(sql1, new Object[]{cus.getEmail()});
		ResultSet exits=this.dao.executeLookup(sqlstring,"customer.searchuserbyemail");
		String sql=this.sqlcode.getProperty("customer.create");
		boolean flag=true;
		
		try {
			if (exits.next()==false){
			String sqlString = MessageFormat.format(sql, new Object[]{
					cus.getUserid(),
					cus.getPassword(),
					cus.getFname(),
					cus.getIname(),
					cus.getEmail(),
					cus.getSex()});
			flag=this.dao.executeUpdate(sqlString);
			
			}else{
				flag=false;			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}

	@Override
	public boolean update(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
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
	public Object findByPrimaryKey(int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object find(Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Integer getInteger(ResultSet paramResultSet, boolean paramBoolean) {
		// TODO Auto-generated method stub
		return null;
	}
}
