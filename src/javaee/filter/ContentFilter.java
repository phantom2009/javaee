package javaee.filter;

import java.io.IOException;
import javaee.utils.EncodingUTF8Request;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//String content=request.getParameter("content");
		
		//我们可以判定content中是否包含非法字符，问题是判断后无法设定回request.getParameter()方法
		
		//刚哥的解决办法是装饰HttpServletRequest对象，改变其getParameter()方法
		
		//实际是通过这里的方式改变的
		HttpServletRequest temp=new EncodingUTF8Request(request);
						
		filterChain.doFilter(temp, response);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
