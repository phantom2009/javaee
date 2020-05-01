package javaee.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javaee.entities.PageInfo;
import javaee.entities.UserInfo;
import javaee.service.UserInfoService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

@WebServlet("/userInfoServlet")
public class UserInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7408654964525958614L;
	
	private UserInfoService userInfoService;

	public UserInfoServlet() {
        super();
        userInfoService=new UserInfoService();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	//[start] + doGet 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	//[end]	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//这是一种约定，前台需要传递参数用来表明自己要调用的方法，因为Servlet只有一个，请求地址是一样的
		String methodName=request.getParameter("method");		
		//因为是反射调用，这里可能出现各种异常，如果不想用反射可以使用switch多条件字符串判断，然后调用指定方法
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}
	
	//[start] + save新增 
	public void save(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		System.out.println("名称是："+name+",密码是："+password);
	}
	//[end]
	
	//TODO:http://localhost:8084/07javaee/userInfoServlet?method=get&id=1002&callback=getData
	//		getData({"id":1002,"userName":"admin","userPassword":"123456"})
	/*
	public void get(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setHeader("Content-Type","text/json;charset=utf-8");
		String id=request.getParameter("id");
		String callback=request.getParameter("callback");
		if(null==id){
			id="1005";
		}
		UserInfo userInfo=new UserInfo();
		userInfo.setId(Integer.parseInt(id));
		userInfo.setUserName("admin");
		userInfo.setUserPassword("123456");
		String json=JSON.toJSONString(userInfo);
		String result=callback+"("+json+")";		
		response.getWriter().write(result);		
	}
	*/
	
	//TODO:上面的代码，与jsonp协作没有任何问题，但是对返回的数据使用callback+data部分拼接，那种写法在h5时代已经放弃，原则的java服务端跨域写法如下
	public void get(HttpServletRequest request,HttpServletResponse response) throws IOException{
				
		String strId=request.getParameter("id");
		
		response.setHeader("Content-Type","text/json;charset=utf-8");	
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");       
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
		
        Integer id=Integer.parseInt(strId);
        if(id==null){
        	id=3;
        }

		UserInfo userInfo=userInfoService.get(id);		
		String json=JSON.toJSONString(userInfo);			
		response.getWriter().write(json);
	}
	
	//[start] + list 用户列表
	public void list(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pageIndex=request.getParameter("page");
		String pageSize=request.getParameter("size");
		Integer page;
		Integer size;
		page=pageIndex==null?1:Integer.parseInt(pageIndex);
		size=pageSize==null?5:Integer.parseInt(pageSize);
		
		List<UserInfo> userInfos=userInfoService.getUserInfos(page, size, null);
		
		Integer total=userInfoService.getUserInfoTotal();
		
		PageInfo pageInfo=new PageInfo();
		pageInfo.setCurrentPage(page);
		pageInfo.setSize(size);
		pageInfo.setTotal(total);
		pageInfo.setTotalPage((int)Math.ceil(total/(size*1.0)));   //Math.ceil()与Math.floor()返回的是向上取整的结果，但是却是double类型，这与我们一般理解还有点不一样

		request.setAttribute("userinfos", userInfos);		
		request.setAttribute("page", pageInfo);
		
		request.getRequestDispatcher("/xViews/userinfo/list.jsp").forward(request, response);		
	}
	//[end]
	
	
}
