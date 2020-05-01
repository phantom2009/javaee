package javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CharCodeServlet
 */
@WebServlet("/charcodeServlet")
public class CharCodeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public CharCodeServlet() {
        super();
    
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("get"); 
//		String name=request.getParameter("name");
//		PrintWriter writer = response.getWriter();
//		writer.write(name);
//		System.out.println(name); 
		
		request.setCharacterEncoding("utf-8");                // 请求
		response.setCharacterEncoding("utf-8");               // 响应
		response.setContentType("text/html;charset=utf-8");   // 响应     
		String name=request.getParameter("name");            //保存的是iso-8859-1格式数据
		PrintWriter writer = response.getWriter();
		writer.write(name);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("post"); 
		//String name=request.getParameter("name");
		//System.out.println(name); 
		
		
		request.setCharacterEncoding("utf-8");                // 请求
		response.setCharacterEncoding("utf-8");               // 响应
		response.setContentType("text/html;charset=utf-8");   // 响应     
		String name=request.getParameter("name");            //保存的是iso-8859-1格式数据
		PrintWriter writer = response.getWriter();
		writer.write(name);

	}

}
