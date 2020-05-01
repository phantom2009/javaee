package javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


import javaee.entities.CusType;
import javaee.service.CusTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class CusTypeServlet
 */
@WebServlet("/cusTypeServlet")
public class CusTypeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	CusTypeService cusTypeService;
	
    public CusTypeServlet() {
        super();     
        cusTypeService=new CusTypeService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 		控制器层只接收参数、返回结果
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO:获取服务端传递的方法名参数
		String methodName=request.getParameter("method");
		
		if(null==methodName || ""==methodName){
			PrintWriter  out = response.getWriter(); 
			out.write("{\"result\":\"method arguments errot!\"}");
			return ;
		}
		
		//TODO:利用反射的方式+指定参数获取指定方法名的方法，传递当前参数和执行
		try{
			Method method=this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,request,response);
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	/**
	 * 添加客户类别
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void add(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String name=request.getParameter("cusTypeName");		
		PrintWriter  out = response.getWriter(); 
		if(null==name || ""==name){
			out.write("{\"result\":\"no\"}");
		}else{
			CusType cusType=new CusType(null, name, "admin", null);
			boolean b=cusTypeService.add(cusType);
			if(b){
				out.write("{\"result\":\"ok\"}");
			}else{
				out.write("{\"result\":\"no\"}");
			}
			
		}
	}
	
	//TODO:分组查询，返回json数据
	public void list(HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*
		String strPage=request.getParameter("page");
		String strSize=request.getParameter("size");
		
		Integer page=Integer.parseInt(strPage);
		Integer size=Integer.parseInt(strSize);
		
		List<CusType> list=cusTypeService.list((page-1)*size, size);
		long total=cusTypeService.getRecordCount();
		
		Map<String, Object> map=new Hashtable<String,Object>();
		
		map.put("total", total);
		map.put("rows",list);
		
		//System.out.println(list);
		String json=JSON.toJSONString(map);
		
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out=response.getWriter(); 
		
		out.write(json);
		*/
	}
}
