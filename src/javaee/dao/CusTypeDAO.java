package javaee.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javaee.entities.CusType;
import javaee.utils.C3p0ConnHelper;

/**
 * 这种继承自父类没有太大意义，还需要单独kong
 * @author Administrator
 *
 */
public class CusTypeDAO{
	
	private Connection conn;
	private QueryRunner qr;
	
	//[start] + add(CusType) 
	public int add(CusType cusType){
		int ra=0;
		String sql="insert into tbl_custype(typename,inputer,inputdate) values(?,?,?)";	
		conn=C3p0ConnHelper.getConnection();
		qr=new QueryRunner();
		try {
			conn.setAutoCommit(false);
			ra=qr.update(conn, sql, cusType.getTypeName(),cusType.getInputer(),new Date());
			conn.commit();
		} catch (SQLException e) {		
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
		}
		return ra;
	}
	//[end]
	
	//[start] + del(Integer id) 
	public int del(Integer id){
		int ra=0;
		String sql="delete from tbl_custype where id=?";
		conn=C3p0ConnHelper.getConnection();
		qr=new QueryRunner();
		try {
			conn.setAutoCommit(false);
			ra=qr.update(conn, sql,id);
			conn.commit();
		} catch (SQLException e) {			
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
		}		
		return ra;
	}
	//[end]
	
	//[start] + update(Custype cusType)
	
	//[end]
	
	//[start] + get(Integer id) 
	public CusType get(Integer id){
		CusType cusType=null;		
		String sql="select * from tbl_custype where id=?";
		qr=new QueryRunner();		
		try {
			cusType=qr.query(conn, sql,new BeanHandler<CusType>(CusType.class),id);
		} catch (SQLException e) {			
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
		}	
		return cusType;
	}
	//[end]
	
	//[start] + getCusTypeTotal 以后要带条件
	public int getCusTypeTotal(){
		long total=0;
		String sql="select count(id) from tbl_custype";
		qr=new QueryRunner();
		try {
			total=qr.query(conn, sql, new ScalarHandler<Long>());
		} catch (Exception e) {

		}
		return (int)total;
	}
	//[end]
	
	//[start] getCusTypes(Integer page,Integer size,CusType cusType)
	public List<CusType> getCusTypes(Integer page,Integer size,CusType cusType){
		List<CusType> list=null;
		String sql="select * from tbl_custype limit ?,?";
		qr=new QueryRunner();		
		try {
			list=qr.query(conn, sql,new BeanListHandler<>(CusType.class),(page-1)*size,size);
		} catch (SQLException e) {			
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
		}	
		return list;
	}
	//[end]
}
