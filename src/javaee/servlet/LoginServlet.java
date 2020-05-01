package javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javaee.entities.UserInfo;
import javaee.service.LoginService;
import javaee.service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
@SuppressWarnings("all")
public class LoginServlet extends HttpServlet {
	
	private LoginService loginService;
	private HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        loginService=new LoginService();
    }

	//TODO:转发到post执行
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doPost(request, response);
	}

	//TODO:根据请求方法，做简单处理然后调用对应的处理方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String methodName=request.getParameter("method");
		if(null==methodName){
			//在servlet中用如下两种路径跳转都是可以的，因为servlet的访问路径总是在request.getContentPath()下
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
			response.sendRedirect("login.jsp"); 
		}else{
			try {
				Method method=this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);		
				method.invoke(this,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 该方法必须public否则上面的反射取不到，当然我们简化处理后也可以不用反射，直接依据参数调用不同的方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String username=request.getParameter("username");
		String userpassword=request.getParameter("userpassword");
		if(username.equals("") || userpassword.equals("")){		
			request.setAttribute("error","账号或者密码不可为空");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		UserInfo userInfo=loginService.login(username, userpassword);	
		if(null==userInfo){		
			request.setAttribute("error","账号或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);		
		}else{
			session=request.getSession();
			session.setAttribute("user", userInfo);	
			response.sendRedirect("homeServlet");
		}
	}
	
	/**
	 * 注销
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		session=request.getSession();
		session.removeAttribute("user");
		response.sendRedirect("loginServlet");
	}
}
