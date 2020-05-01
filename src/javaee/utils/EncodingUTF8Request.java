package javaee.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 使用装饰模式改造HttpServletRequest的getParameter(String)方法，如果需要还可以改造其他方法
 * @author Administrator
 *
 */
public class EncodingUTF8Request extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	private String characterCode;
	
	public EncodingUTF8Request(HttpServletRequest request) {
		super(request);		
	}
	
	/**
	 * 重载一个构造函数，仅仅为了使用方便
	 * @param request
	 * @param characterCode
	 */
	public EncodingUTF8Request(HttpServletRequest request,String characterCode) {
		super(request);
		this.request=request;
		this.characterCode = characterCode;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getCharacterCode() {
		return characterCode;
	}

	public void setCharacterCode(String characterCode) {
		this.characterCode = characterCode;
	}
	
	/**
	 * 重写我们常用的这个方法，如果要重写其他方法请在此之后继续扩展
	 */
	@Override
	public String getParameter(String name){
		String method=request.getMethod();
		String val=null;                               //是否可以设置为空
			try{
				request.setCharacterEncoding("utf-8"); //对post应该起作用，get起作用还要下面的代码
				val=request.getParameter(name);
				if("get".equalsIgnoreCase(method) && val!=null){
					val=new String(val.getBytes("ISO-8859-1"),"utf-8");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		return val;
	}
}
