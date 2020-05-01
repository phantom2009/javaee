package javaee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet API提供HttpServlet，但是没有HttpFilter,因为他们认为过滤器虽然重要但是不是用户业务，所以不会很多，所以没提供HttpFilter,要写过滤器可以自己直接手写类实现javax.servlet.Filter.
 * 		我们尝试写一个HttpFilter,他的作用在结构上类似HttpServlet。		
 */
public abstract class HttpFilter implements Filter {
	
	//TODO:该对象保存Filter配置信息的相关内容，需要在init()方法中赋值
	private FilterConfig filterConfig;
	
	//TODO:这里是面向对象封装的思路，init作为父类值调用基本的业务，另外传递一个函数（委托）有子类实现业务，即使调用init();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		//TODO:调用这个方法的同时也就是将init()的部分业务方法由子类去处理，这种设计思路比较重要，刚哥这种延迟处理业务的思路很重要，他实现了子类中初始化的业务在父类中也会初始化
		init();   
	}
	
	//TODO:延迟方法
	public abstract void init();
	
	//TODO:这个方法可以使用protected，他主要是给子类使用上面filterConfig获取系统信息
	public FilterConfig getFilterConfig(){
		return filterConfig;
	}
	
	/**
	 * 原生的 doFilter 方法,这个是过滤器声明周期方法，每次过滤时候执行, 方法内部把 ServletRequest 和 ServletResponse 
	 * 转为了 HttpServletRequest 和 HttpServletResponse, 并调用了 抽象的
	 * doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	 * 
	 * 抽象的doFilter方法由子类重写，因为我们不能在这个父类中直接写子类要过滤的业务，而是在过滤器方法中调用子类的业务，这是刚哥的设计思路，同abstract init()方法
	 * 
	 * 若编写 Filter 的过滤方法不建议直接继承该方法. 而建议继承
	 * doFilter(HttpServletRequest request, HttpServletResponse response, 
	 *		FilterChain filterChain) 方法
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;	
		doFilter(httpServletRequest, httpServletResponse, filterChain);
	}
	
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException;
	
	@Override
	public void destroy() {
		//......
	}	
}
