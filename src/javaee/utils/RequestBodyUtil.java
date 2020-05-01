package javaee.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class RequestBodyUtil {

	/**
	 * 用字符串形式获取请求体,对于json字符串可以在获取后由java json工具传化成java对象
	 * @param request
	 * @return
	 */
	public static String readAsChars(HttpServletRequest request){
		BufferedReader br=null;
		StringBuilder sb=new StringBuilder("");
		try {
			br=request.getReader();
			String str;
			while ((str=br.readLine())!=null) {
				sb.append(str);				
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * 用流形式读取request中的内容，返回成字符串
	 * @param request
	 * @return
	 */
	public static String readAsStream(HttpServletRequest request){
		StringBuilder sb=new StringBuilder("");
		InputStream is = null;
        try{
            is = request.getInputStream();
            sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1;){
                sb.append(new String(b, 0, n));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        finally{
            if (null != is){
                try{
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
		return sb.toString();
	}
	
	/**
	 * 读到字节数组中
	 * @param request
	 * @return
	 */
	public static byte[] readAsBytes(HttpServletRequest request){
		int len = request.getContentLength();
		byte[] buffer = new byte[len];
		ServletInputStream in = null;
		try {
			in = request.getInputStream();
			in.read(buffer, 0, len);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer;
	}
}
