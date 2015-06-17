/**
 * @author Crow
 * @date 2015年6月13日
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

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * 管理文件上传请求
 * 使用了commons-fileupload和commons-io两个jar包
 * @author Crow
 * @date 2015年6月13日
 *
 */
public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
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
		
		boolean isUploadSuccess = false;
		String fileName = "";
		
		if(ServletFileUpload.isMultipartContent(request)){
			// 获得文件上传的父目录
			InputStream isForParentPath = request.getServletContext().getResourceAsStream("/WEB-INF/classes/upload.properties");
			HashMap<String, String> values = getPropertiesParameter(isForParentPath, new String[]{"UPLOAD_PATH", "UPLOAD_FILE_MAX_SIZE", "UPLOAD_MAX_SIZE", "UPLOAD_THRESHOLD"});
			String uploadPath = (String) values.get("UPLOAD_PATH");
			long singleUploadFileMaxSize = Long.valueOf(values.get("UPLOAD_FILE_MAX_SIZE"));
			long uploadFileMaxSize = Long.valueOf(values.get("UPLOAD_MAX_SIZE"));
			int uploadThreshold = Integer.valueOf(values.get("UPLOAD_THRESHOLD"));
			
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			dfif.setRepository(new File(uploadPath));// 上传文件的临时目录
			dfif.setSizeThreshold(uploadThreshold);// 文件在内存中可缓存的最大值，单位：byte。超过该值，将使用硬盘空间缓存(JVM内存有限，避免OutOfMemory)。
			
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			sfu.setHeaderEncoding("UTF-8");// 解决乱码
			sfu.setFileSizeMax(singleUploadFileMaxSize);// 单个文件上传最大值，单位：byte，最大10MB
			sfu.setSizeMax(uploadFileMaxSize);// 一次上传多个文件总最大值，单位：byte，最大100MB
			
			try {
				// 解析request，获得FileItemIterator集合
				FileItemIterator fileItems = sfu.getItemIterator(request);
				while(fileItems.hasNext()){
					// 获得一个上传的文件的文件流
					FileItemStream fis = fileItems.next();
					// 过滤掉表单中的非文件域
					if(!fis.isFormField() && fis.getName().length() > 0){
						fileName = fis.getName();
						System.out.println("处理前的文件名：" + fileName);
						int separaterIndex = fileName.lastIndexOf("/");
						if(separaterIndex > 0){
							fileName = fileName.substring(separaterIndex);
							System.out.println("处理后的文件名：" + fileName);
						}
						
						// 在此编写上传文件的命名规则
						
						// 创建文件输入输出流，复制文件
						BufferedInputStream bis = new BufferedInputStream(fis.openStream());
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(uploadPath + "img", fileName)));
						Streams.copy(bis, bos, true);
						System.out.println("上传文件 " + fileName + " 成功");
					}
				}
				isUploadSuccess = true;
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		
		if(isUploadSuccess){
			// 跳转
//			request.setAttribute("info", "File upload success.");
//			request.getRequestDispatcher("success.jsp").forward(request, response);
			
			JSONObject jo = new JSONObject();
			jo.put("state", "success");
			jo.put("info", "文件" + fileName + "上传成功");
			
			// 返回数据
			PrintWriter out = response.getWriter();
			out.write(jo.toString());
			out.flush();
			out.close();
		}else{
//			request.setAttribute("info", "File upload failure.");
//			request.getRequestDispatcher("error.jsp").forward(request, response);
			
			PrintWriter out = response.getWriter();
			out.write("{\"state\": \"failure\", \"info\": \"上传文件 " + fileName + " 失败\"}");
			out.flush();
			out.close();
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
