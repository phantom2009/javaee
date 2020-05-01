package javaee.servlet;

import java.io.IOException;
import java.util.List;
import javaee.entities.CusInfo;
import javaee.entities.UserInfo;
import javaee.service.CusInfoService;
import javaee.service.UserInfoService;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 建立普通类，实现Servlet接口就是Servlet类啦，记得在web.xml中配置访问路径，Servlet3.0以上可以用注解写访问地址，只保留一个这种写法，后面我们都使用继承HttpServlet和注解实现路由；
 * 		一般都实现HttpServlet,该接口间接实现Servlet
 */
public class HomeServlet implements Servlet {

	private ServletConfig servletConfig;
	private UserInfoService userInfoService;
	private CusInfoService cusInfoService;
	
	//TODO:构造函数(1)
	public HomeServlet() {
        super();
        userInfoService=new UserInfoService();
        cusInfoService=new CusInfoService();
    }
	
	//TODO:声明周期方法(2)
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		this.servletConfig=servletConfig;		
	}

	@Override
	public ServletConfig getServletConfig() {
		return this.servletConfig;
	}
	
	//TODO:生命周期方法(3)
	@Override
	public void service(ServletRequest request, ServletResponse response)throws ServletException, IOException {
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		
//		 Cookie[] cookies = ((HttpServletRequest) request).getCookies();
//	     if(cookies !=null) {
//	    	 for(int i=0;i<cookies.length;i++){
//	    		 System.out.println(cookies[i].getName()+":"+cookies[i].getValue());
//	    	 }
//	     }

		
		
		
		List<UserInfo> userInfos=userInfoService.getUserInfos(1,5,null);		
		httpServletRequest.setAttribute("userinfos", userInfos);
		
		List<CusInfo> cusInfos=cusInfoService.getCusInfos(1, 5, null);
		httpServletRequest.setAttribute("cusinfos", cusInfos);
		
		httpServletRequest.getRequestDispatcher("/xViews/index.jsp").forward(httpServletRequest, httpServletResponse);
	}

	@Override
	public String getServletInfo() {
		return "This is entry Servlet!";
	}
	
	//TODO:生命周期方法(4)
	@Override
	public void destroy() {}
}
