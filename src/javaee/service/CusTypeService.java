package javaee.service;


import java.util.List;
import javaee.dao.CusTypeDAO;
import javaee.entities.CusType;

public class CusTypeService {

	CusTypeDAO cusTypeDAO;
	
	
	public CusTypeService(){
		cusTypeDAO=new CusTypeDAO();
	}
	
	//TODO:新增一条
	public boolean add(CusType cusType){	
		return cusTypeDAO.add(cusType)>0;
	}
	
	
	public boolean del(Integer id){
		int ra=cusTypeDAO.del(id);
		return ra>0;
	}
	
	public CusType getCusType(Integer id){
		return cusTypeDAO.get(id);
	}
	
	public int getCusTypeTotal(){
		return cusTypeDAO.getCusTypeTotal();
	}
	
	public List<CusType> getCusTypes(Integer page,Integer size,CusType cusType){
		return cusTypeDAO.getCusTypes(page, size, cusType);
	}
}
