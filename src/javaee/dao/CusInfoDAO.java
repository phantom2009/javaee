package javaee.dao;

import java.sql.Connection;

import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javaee.entities.CusInfo;
import javaee.utils.C3p0ConnHelper;


public class CusInfoDAO extends BaseDAO<CusInfo>{
	
	private Connection conn;
	private QueryRunner qr;
	
	//[start] + add(CusInfo) 
	public int add(CusInfo cusInfo){
		int ra=0;
		String sql="insert into tbl_cusinfo(cusname,cussex) values(?,?)";
		conn=C3p0ConnHelper.getConnection();
		qr=new QueryRunner();
		try {
			conn.setAutoCommit(false);
			ra=qr.update(conn, sql, cusInfo.getCusName(),cusInfo.getCusSex());
			conn.commit();
		} catch (Exception e) {
			DbUtils.rollbackAndCloseQuietly(conn);
		}
		return ra;
	}
	//[end]
	
	//[start] + getCusInfos 查询一组
	public List<CusInfo> getCusInfos(Integer page,Integer size,CusInfo cusInfo){
		List<CusInfo> list=null;
		String sql="select * from tbl_cusinfo ORDER BY id desc limit ?,?";
		conn=C3p0ConnHelper.getConnection();
		qr=new QueryRunner();
		//查询不使用事务
		try {
			list=qr.query(conn, sql, new BeanListHandler<CusInfo>(CusInfo.class), (page-1)*size,size);
		} catch (Exception e) {
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
		}
		return list;
	}
	//[end]
}
