package javaee.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0ConnHelper {

	//TODO:获取数据源
	public static DataSource getDataSource(){
		return new ComboPooledDataSource("mysql");
	}
	
	//TODO:获取数据库连接
	public static Connection getConnection(){
		Connection conn=null;
		ComboPooledDataSource dataSource=new ComboPooledDataSource("mysql");
		try {
			conn=dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//TODO:释放数据库操作的相关资源
	public static void release(ResultSet rs,PreparedStatement pstm,Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn!= null) {
			try { 
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
