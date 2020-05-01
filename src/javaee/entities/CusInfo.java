package javaee.entities;

import java.sql.Timestamp;


public class CusInfo {

	private Integer id;
	private String cusName;
	private String cusSex;
	private Integer cusAge;
	
	private Integer cusTypeId;
	
	private Timestamp createDate;        //数据库也用这个同名类型，他与java.util.Date不同在于他会将时间转为标准时间，并且字节只有4个字节，数据库的Datetime是8个字节
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusSex() {
		return cusSex;
	}
	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}
	public Integer getCusAge() {
		return cusAge;
	}
	public void setCusAge(Integer cusAge) {
		this.cusAge = cusAge;
	}
	public Integer getCusTypeId() {
		return cusTypeId;
	}
	public void setCusTypeId(Integer cusTypeId) {
		this.cusTypeId = cusTypeId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
	
	
}
