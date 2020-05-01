package javaee.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javaee.utils.EncodingUTF8Request;

/**
 * 字符串编码过滤器，tomcat至少在8以下都默认iso8859-1传递，这个过滤器要解决这个问题
 * 		1> 直接继承自javax.sevlet.Filter,其原理同另外一个AuthorizeFilter继承自Filter;
 * @author Administrator
 */
public class CharacterCodeFilter implements Filter {
	
	/**
	 * 其名称就是其含义，过滤器配置，这个配置既有可能是web.xml中的全局配置，也有可能是web.xml某个filter的局部参数配置
	 */
	private FilterConfig filterConfig;
	
	/**
	 * 过滤器本身也是一个类，web.xml启动的时候只要配置了过滤器，该过滤器就会启动，使用该过滤器的默认无参数构造函数创建该过滤器实例；
	 * 		实例创建后，由于有了实例就可以调用实例的init()方法，用户做一些初始化的操作；
	 * 		该方法只会执行一次，只有foFilter会过滤每次请求而一直执行
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;	
	}
			
	/**
	 * 类似Servlet的Service()方法，每次请求都会执行
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		
		//filterConfig.getInitParameter(name)
		
		String charCode=filterConfig.getServletContext().getInitParameter("charCode");
		
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		
		//TODO:用Wrapper,装饰器重新构造一个HttpServletRequest
		EncodingUTF8Request encodingUTF8Request=new EncodingUTF8Request(httpServletRequest,charCode);
		
		httpServletResponse.setCharacterEncoding("utf-8");  	
		/**
		 * 这里设置输出格式个缓冲区编码为utf-8,在具体的Servlet中还要使用reponse.setHeader("Content-Type","text/html;charset=utf-8");  
		 * 		常用的text/html,text/json,text/plain,text/stream
		 * 
		 *  response.setContentType("text/html;charset=UTF-8");这一句话本身抵得上上面两句的合并，有的人写这么一句，问题是我额servlet返回内容不是一成不变的
		 *  	text/html,因此最好只写setCharacterEncoding("utf-8")，而setHeader("Content-Type","text/html;charset=utf-8")有具体servlet控制
		 */
		
		//TODO:用装饰过的Request往下传递
		filterChain.doFilter(encodingUTF8Request, httpServletResponse);
	}
	
	@Override
	public void destroy() {
		// ......
	}
}
