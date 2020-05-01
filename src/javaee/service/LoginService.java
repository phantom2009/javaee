package javaee.service;

import javaee.entities.UserInfo;

/**
 * 模拟的登陆 service,暂时写一个固定的测试账号，还没有到dao层去查询账号
 * @author Administrator
 *
 */
public class LoginService {
	
	public UserInfo login(String username,String userpassword){
		UserInfo userInfo=null;		
		if("admin".equals(username) && userpassword.equals("123456")){
			userInfo=new UserInfo();
			userInfo.setUserName(username);
			userInfo.setUserPassword(userpassword);
		}		
		return userInfo;
	}
}
