package javaee.servlet.filedownload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StandardDownloadServlet
 */
@WebServlet(description = "标准的文件下载", urlPatterns = { "/standardDownloadServlet" })
public class StandardDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StandardDownloadServlet() {
        super();  
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 通知客户端浏览器: 这是一个需要下载的文件, 不能再按普通的 html 的方式打开.
		//		即设置一个响应的类型: application/x-msdownload
		//		response.setContentType("application/x-msdownload"); 
	
		//2. 通知客户端浏览器: 不再有浏览器来处理该文件, 而是交由用户自行处理
		//		设置用户处理的方式: 响应头: Content-Disposition
		//		response.setHeader("Content-Disposition", "attachment;filename=abc.txt");
		
		String fileName=request.getParameter("filename");
		String type=request.getParameter("type");
		String fullName="";
		switch (type) {
			case "zip":
				fullName=request.getServletContext().getRealPath("/xResources/zips/"+fileName+".zip");	
				response.setContentType("application/x-msdownload");
				response.setHeader("content-disposition", "attachment;filename="+fileName+".zip");
				break;
			case "csv":
				fullName=request.getServletContext().getRealPath("/xResources/csvs/"+fileName+".csv");
				//response.setContentType("application/x-msdownload");   
				//这句不写通过Chrome控制台看响应头就是没有写，那也就是说要么默认值，要么用不到，不影响功能
				response.setHeader("content-disposition", "attachment;filename="+fileName+".csv");
				break;
			default:
				break;
		}
		//以下8句话是固定套路，与上传类似
		InputStream inputStream=new FileInputStream(fullName);   //直接传入String构造File对象再构造FileInputStream对象，构造函数重载。
		OutputStream outputStream=response.getOutputStream();    //getOutputStream()本身返回的是ServletOutputStream,他是抽象类OutputStream的子类，我用OutputStream声明是为了容易记忆
		byte[] buffer=new byte[1024];
		int len=0;
		while ((len=inputStream.read(buffer))!=-1) {
			outputStream.write(buffer, 0, len);			
		}	
		outputStream.flush();
		inputStream.close();
		outputStream.close();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName=request.getParameter("filename");
		String type=request.getParameter("type");
		String fullName="";
		switch (type) {
			case "zip":
				fullName=request.getServletContext().getRealPath("/xResources/zips/"+fileName+".zip");				
				break;
			case "csv":
				fullName=request.getServletContext().getRealPath("/xResources/csvs/"+fileName+".csv");					
				break;
			default:
				break;
		}
		//这里我们写作octet-stream，实际上现代浏览器对于返回流可能会自动判别，这句话不用写，我这里写上防止一些低版本浏览器
		//	又或者一些水平差的人乱写
		//	application/octet-streamMIME类型比较符合我们常规的理解，文件类型不需要知道，都当二进制流里即可
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//response.setHeader("content-disposition", "attachment;filename="+fileName+".zip");
		
		//以下8句话是固定套路，与上传类似
		InputStream inputStream=new FileInputStream(fullName);
		OutputStream outputStream=response.getOutputStream();
		byte[] buffer=new byte[1024];
		int len=0;
		while ((len=inputStream.read(buffer))!=-1) {
			outputStream.write(buffer, 0, len);			
		}
		outputStream.flush();
		inputStream.close();
		outputStream.close();
	}

}
