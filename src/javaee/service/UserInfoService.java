package javaee.service;

import java.util.List;



import javaee.dao.UserInfoDAO;
import javaee.entities.UserInfo;

public class UserInfoService {

	private UserInfoDAO userInfoDAO;
	
	public UserInfoService(){
		userInfoDAO=new UserInfoDAO();
	}
	
	//[start] + getUserInfoCountNumber
	public int getUserInfoTotal(){
		return userInfoDAO.getUserInfoTotal();
	}
	//[end]
	
	//[start] + get 查询一条
	public UserInfo get(Integer id){
		return userInfoDAO.getUserInfo(id);
	}
	//[end]
	
	//[start] + 分组查询
	public List<UserInfo> getUserInfos(Integer page,Integer size,UserInfo userInfo){
		return userInfoDAO.getUserInfos(page, size, userInfo);
	}
	//[end]
}
