/**
 * @author Crow
 * @date 2015年6月20日
 * @version v0.1
 */
package org.crow.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Crow
 * @date 2015年6月20日
 * @version v0.1
 *
 */
public class CheckUserExistServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckUserExistServlet() {
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

		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Content-type", "text/xml;charset=UTF-8");
		
		String account = request.getParameter("account");
		StringBuilder sb = new StringBuilder();
		sb.append("<info>");
		
		if(account != null){
			if("admin".equals(account)){
				sb.append("user \"" + account + "\" exist");
			}else{
				sb.append("user \"" + account + "\" non-exist");
			}
		}else{
			sb.append("some errors occurred");
		}
		
		sb.append("</info>");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
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
		
		String account = request.getParameter("account");
		String result = "";
		if(account != null){
			if("admin".equals(account)){
				result = "user \"" + account + "\" exist";
			}else{
				result = "user \"" + account + "\" non-exist";
			}
		}else{
			result = "some errors occurred";
		}
		
		PrintWriter pw = response.getWriter();
		pw.println(result);
		pw.flush();
		pw.close();
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
