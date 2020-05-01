package javaee.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javaee.entities.UserInfo;
import javaee.utils.C3p0ConnHelper;


public class UserInfoDAO{
	
	private Connection conn;
	private QueryRunner qr;
	
	//[start] + add 添加用户
	public Integer add(UserInfo userInfo) throws Exception{
		Integer ra=0;
		conn=C3p0ConnHelper.getConnection();     
		qr=new QueryRunner();                       //因为要手动控制事务所以conn对象在执行时候传递
		String sql = "insert into tbl_userinfo(username,userpassword) values(?,?)";				
		try {
			conn.setAutoCommit(false);
			ra=qr.update(conn, sql, userInfo.getUserName(),userInfo.getUserPassword());
			conn.commit();
		} catch (SQLException e) {			
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);  //回滚并且无异常关闭连接
		}
		return ra;
	}
	//[end]
	
	//[start] + del 删除用户
	public Integer del(UserInfo userInfo) throws Exception{
		Integer ra=0;
		conn=C3p0ConnHelper.getConnection();     
		qr=new QueryRunner();
		String sql = "delete from tbl_userinfo where id=?";
		try {
			conn.setAutoCommit(false);
			ra=qr.update(conn, sql, userInfo.getId());
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return ra;
	}
	//[end]

	//[start] + update 更新用户信息
	
	//[end]

	//[start] + getUserInfo
	public UserInfo getUserInfo(Integer id){
		UserInfo userInfo=null;
		conn=C3p0ConnHelper.getConnection();
		String sql="select * from tbl_userinfo where id=?";
		qr=new QueryRunner();	
		try {
			//TODO:execute()与Query()的差别还是直接看源代码吧，手册上有时候版本不同跟不上，其实查询就用query()方法就够啦。
			//		以下两种写法都可以
			//userInfo=(UserInfo) qr.execute(conn, sql, new BeanHandler<>(UserInfo.class), id);			
			System.out.println(id+"*******");		
			userInfo = qr.query(conn, sql, new BeanHandler<UserInfo>(UserInfo.class), id);
			
			System.out.println(userInfo);
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally{
			//DbUtils.closeQuietly(conn);
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return userInfo;
	}
	//[end]

	//[start] + getUserInfoCountNumber
	public int getUserInfoTotal(){
		long count=0;
		conn = C3p0ConnHelper.getConnection();
		qr = new QueryRunner();
		String sql = "select count(id) from tbl_userinfo";
		
		try {
			count=qr.query(conn, sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//qr.close(conn);         //1.7版本没有close(conn)方法
			DbUtils.closeQuietly(conn);
		}
		return (int)count;
	}
	//[end]

	//[start] + getUserInfos分组查询
	public List<UserInfo> getUserInfos(Integer page,Integer size,UserInfo userInfo){
		List<UserInfo> list = null;
		String sql="select * from tbl_userinfo ORDER BY id limit ?,?";
		conn=C3p0ConnHelper.getConnection();
		qr=new QueryRunner();
		
		try {
			list=qr.query(conn, sql, new BeanListHandler<UserInfo>(UserInfo.class), (page-1)*size,size);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return list;
	}
	//[end]
}
