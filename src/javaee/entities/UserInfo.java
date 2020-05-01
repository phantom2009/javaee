package javaee.entities;

public class UserInfo {

	private Integer id;
	private String userName;
	private String userPassword;	
	public UserInfo() {
		super();
	}
	public UserInfo(Integer id, String userName, String userPassword) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + userName
				+ ", userPassword=" + userPassword + "]";
	}
}
