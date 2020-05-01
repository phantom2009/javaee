package javaee.servlet.filedownload;

import java.io.File;
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
 * 使用Servlet返回图片，其原理同img src属性
 */
@WebServlet("/imageOutputServlet")
public class ImageOutputServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ImageOutputServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		//TODO:模拟图片来源，测试相关值都写死		
		String fullName=request.getServletContext().getRealPath("/xResources/imgs/Hydrangeas.jpg");
		File file=new File(fullName);
		//8.设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
        //9.设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        
        InputStream inputStream=new FileInputStream(file);
        OutputStream outputStream=response.getOutputStream();  //没有指定目标文件位置，其实是以流的形式返回到浏览器中，由浏览器决定 
        byte[] bytes=new byte[(int) file.length()];
        inputStream.read(bytes);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
