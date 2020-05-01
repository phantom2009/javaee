package javaee.service;

import java.util.List;

import javaee.dao.CusInfoDAO;
import javaee.entities.CusInfo;

public class CusInfoService {

	private CusInfoDAO cusInfoDAO;
	
	public CusInfoService(){
		cusInfoDAO=new CusInfoDAO();
	}
	
	//TODO:添加一条,add
	public boolean add(CusInfo cusInfo){
		return cusInfoDAO.add(cusInfo)>0;
	}
	
	//TODO:查询一组,getCusInfos
	public List<CusInfo> getCusInfos(Integer page,Integer size,CusInfo cusInfo){
		return cusInfoDAO.getCusInfos(page, size, cusInfo);
	}
	
}
