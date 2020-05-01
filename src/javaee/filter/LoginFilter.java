package javaee.filter;

import java.io.IOException;
import javaee.entities.UserInfo;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权过滤器，相关技术点如下
 * 		1> 模拟HttpServletFilter写一个HttpFilter,因为前者jre提供了默认实现，后者默认没有；
 * 		2> 尽管可以注解Filter,但是因为注解不可控制过滤器执行顺序，大家习惯用配置文件实现过滤器的请求过滤路径映射；
 * @author Administrator
 *
 */
public class LoginFilter extends HttpFilter {

	@Override
	public void init() {}

	@Override
	public void doFilter(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {		
		String requestPage = request.getServletPath();
		response.addHeader("Access-Control-Allow-Origin", "*");
		if (requestPage.equals("/login.jsp") || requestPage.indexOf("loginServlet") > 0
				|| requestPage.endsWith(".css") || requestPage.endsWith(".js")
				|| requestPage.endsWith(".jpg") || requestPage.endsWith(".gif")
				|| requestPage.endsWith(".png")) {
			filterChain.doFilter(request, response);
		} else {
			UserInfo user = (UserInfo) request.getSession().getAttribute("user");
			if (user == null || !user.getUserName().equals("admin") 
					|| !user.getUserPassword().equals("123456")) {  //添加判断：如果用户没有登录则跳转到loging.jsp				
				String redirectPathString=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
						+request.getContextPath()+"/login.jsp";			
				response.sendRedirect(redirectPathString); 				
				//下面使用返回html页面中也能解决servlet访问路径问题上面的redirectPathString
				/*
				response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript' type='text/javascript'>");
                out.println("window.top.location.href='" + request.getContextPath() + "/login.jsp'");
                out.println("</script>");
				*/
			} else {
				filterChain.doFilter(request, response);
			}
		}
	}
}
