package org.crow.demo.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SolveChineseUnknowableCodeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SolveChineseUnknowableCodeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		// 确定文件名，客户端传递参数给后台钱，进行了编码，后台进行解码，以此解决/避免从客户端传入的参数包含中文乱码
		String fileName = URLDecoder.decode(request.getParameter("fileName"), "UTF-8");
		if (fileName == null || fileName.equals("")) {
			fileName = "未命名";
		}
		fileName += ".txt";
		
		// 解决文件名乱码
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		response.setContentType("text/html");
		response.setContentType(getServletContext().getMimeType(fileName));
		
		// 设置文件以附件形式下载
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 文件内容，确定正确编码，避免乱码
//		String fileContent = "This is just a plain file.\r\nVery simple.";
		String fileContent = "这是一个纯文本文件.\r\n非常简单.";
		InputStream is = new ByteArrayInputStream(fileContent.getBytes("UTF-8"));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			byte[] data = new byte[1024];
			int length = 0;
			while((length = is.read(data)) != -1){
				os.write(data, 0, length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
