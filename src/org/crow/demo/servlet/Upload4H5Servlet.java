/**
 * @author Crow
 * @date 2015年6月20日
 * @version v0.1
 */
package org.crow.demo.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * 
 * @author Crow
 * @date 2015年6月20日
 * @version v0.1
 *
 */
public class Upload4H5Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Upload4H5Servlet() {
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
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 获得文件上传的父目录
		InputStream isForParentPath = request.getServletContext().getResourceAsStream("/WEB-INF/classes/upload.properties");
		HashMap<String, String> values = getPropertiesParameter(isForParentPath, new String[]{"UPLOAD_PATH", "UPLOAD_FILE_MAX_SIZE", "UPLOAD_MAX_SIZE", "UPLOAD_THRESHOLD"});
		String uploadPath = (String) values.get("UPLOAD_PATH");
//		long singleUploadFileMaxSize = Long.valueOf(values.get("UPLOAD_FILE_MAX_SIZE"));
//		long uploadFileMaxSize = Long.valueOf(values.get("UPLOAD_MAX_SIZE"));
//		int uploadThreshold = Integer.valueOf(values.get("UPLOAD_THRESHOLD"));
		
		String fileName = request.getParameter("file");
		System.out.println("处理前的文件名：" + fileName);
		int separaterIndex = fileName.lastIndexOf("/");
		if(separaterIndex > 0){
			fileName = fileName.substring(separaterIndex);
			System.out.println("处理后的文件名：" + fileName);
		}
		
		// 在此编写上传文件的命名规则
		
		// 创建文件输入输出流，复制文件
		BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(uploadPath + "img", fileName)));
		byte[] data = new byte[1024];
		int length = -1;
		while((length = bis.read(data)) != -1){
			bos.write(data, 0, length);
		}
		bos.flush();
		bos.close();
		bis.close();
		System.out.println("上传文件 " + fileName + " 成功");
		
		JSONObject jo = new JSONObject();
		jo.put("state", "success");
		jo.put("info", "文件 " + fileName + " 上传成功");
		
		// 返回数据
		PrintWriter out = response.getWriter();
		out.write(jo.toString());
		out.flush();
		out.close();
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
	 * 获得某properties中某参数的值
	 * @author Crow
	 * @date 2015年6月13日
	 * @param is
	 * @param key
	 * @return
	 */
	private HashMap<String, String> getPropertiesParameter(InputStream is, String[] keys){
		HashMap<String, String> values = new HashMap<String, String>();
		Properties prop = new Properties();
		try {
			prop.load(is);
			for(int i = 0, l = keys.length; i < l; i++){
				values.put(keys[i], prop.getProperty(keys[i]));
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}

}
