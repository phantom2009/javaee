package javaee.entities;

import java.util.Date;

public class CusType {

	private Integer id;
	private String typeName;
	private String inputer;
	private Date inputDate;               //java类型用java.util.date,数据库类型用datetime,插入默认值使用new java.util.date()         
	
	public CusType() {
		super();
	}

	public CusType(Integer id, String typeName, String inputer, Date inputDate) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.inputer = inputer;
		this.inputDate = inputDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getInputer() {
		return inputer;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Override
	public String toString() {
		return "CusType [id=" + id + ", typeName=" + typeName + ", inputer="
				+ inputer + ", inputDate=" + inputDate + "]";
	}
	
}
