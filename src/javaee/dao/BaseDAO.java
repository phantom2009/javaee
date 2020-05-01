package javaee.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 感觉这个作用没有那么大，因为需要在DAO层控制事务，这个可有可无。
 */
@SuppressWarnings("all")
public class BaseDAO<T> {
	
	protected QueryRunner queryRunner = null;
	
	private Class<T> clazz;
		
	public BaseDAO() {
		
		//TODO:如果传递数据源，后面的方法都不需要带Connection对象啦
		queryRunner = new QueryRunner();
		
		//TODO:先获取子类的clazz类型，然后据此获取父类的泛型类型Type，不是Class类型，由于父类带泛型参数所以直接转换为ParameterizedType
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		
		//TODO:使用父类的ParameterizedType类型获取父类的泛型参数列表
		Type[] types=type.getActualTypeArguments();
		
		//TODO:获取方向类表总Type类型的第一个转换为Class类型
		clazz=(Class<T>)types[0];	
	}
	
	public int modify(Connection conn, String sql, Object ... args) throws SQLException {
		return queryRunner.update(conn, sql, args);
	}
	
	public void batch(Connection conn, String sql, Object[] ... args) throws SQLException {
		queryRunner.batch(conn, sql, args);
	}

	public <E> E get4Scalar(Connection conn, String sql, Object ... args) throws SQLException {
		return (E) queryRunner.query(conn, sql, new ScalarHandler(), args);
	}

	public List<T> getModels(Connection conn, String sql, Object ... args) throws SQLException {
		return queryRunner.query(conn, sql,new BeanListHandler<T>(clazz), args);
	}

	public T get(Connection conn, String sql, Object... args) throws SQLException { 
		return queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
	}
}
