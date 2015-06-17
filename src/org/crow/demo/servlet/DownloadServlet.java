/**
 * @author Crow
 * @date 2015年6月13日
 */
package org.crow.demo.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Crow
 * @date 2015年6月13日
 *
 */
public class DownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Constructor of the object.
	 */
	public DownloadServlet() {
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

		// 获得用户要下载的文件名
		String fileName = request.getParameter("fileName");
		String trueFileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
<<<<<<< HEAD
		System.out.println("File name: " + trueFileName);
=======
		System.out.println("文件名：" + trueFileName);
>>>>>>> origin/master
		// 获得文件真是存储位置，判断文件是否存在
//		String filePath = getServletContext().getRealPath("/resources/" + fileName);
		
		// 获得配置文件中的下载父路径
		InputStream isForReadProps = request.getServletContext().getResourceAsStream("/WEB-INF/classes/download.properties");
		String parentPath = getPropertiesParameter(isForReadProps, "DOWNLOAD_PATH");
		
		String filePath = parentPath + fileName; 
<<<<<<< HEAD
		System.out.println("File true path: " + filePath);
		File downloadFile = new File(filePath);
		if(downloadFile.exists()){
			trueFileName = new String(trueFileName.getBytes("UTF-8"), "ISO-8859-1");// 解决中文乱码
			System.out.println("File name after encoding: " + trueFileName);
=======
		System.out.println("文件真实路径：" + filePath);
		File downloadFile = new File(filePath);
		if(downloadFile.exists()){
>>>>>>> origin/master
			// 设置文件MIME类型
			response.setContentType(getServletContext().getMimeType(trueFileName));
			// 设置文件以附件形式下载
			response.setHeader("Content-Disposition", "attachment;filename=" + trueFileName);
			
			InputStream is = new FileInputStream(downloadFile);
			OutputStream os = response.getOutputStream();
			
			byte[] data = new byte[1024];
			int length = 0;
			while((length = is.read(data)) != -1){
				os.write(data, 0, length);
			}
			os.flush();
			os.close();
			is.close();
		}else{
			request.setAttribute("info", "File \"" + fileName + "\" not exist.");
			
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
<<<<<<< HEAD
=======
		
		
		
		
		
		
		
		/*
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
>>>>>>> origin/master
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	
	/**
	 * 获得指定properties配置文件中的指定的参数值
	 * @author Crow
	 * @date 2015年6月13日
	 * @param is
	 * @param key
	 * @return
	 */
	private String getPropertiesParameter(InputStream is, String key){
		String value = null;
		Properties prop = new Properties();
		try {
			prop.load(is);
			value = prop.getProperty("DOWNLOAD_PATH");
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("获取某properties中的参数 " + key + " 失败");
		};
		return value;
	}

}
