package org.crow.demo.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crow.demo.entity.User;

public class UserLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  
	 */
	List<User> userList = null;

	/**
	 * Constructor of the object.
	 */
	public UserLoginServlet() {
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

		doPut(request, response);
		
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
		// If user data not exist, then initialize.
		if(userList == null){
			String projectPath = request.getServletContext().getRealPath("/");
			userList = getUserInfo(projectPath);
			System.out.println("initialize user data.");
			for(User user : userList){
				System.out.println(user.getUserAccount() + " - " + user.getUserPassword());
			}
			System.out.println("Initialize end.\n");
		}

		
		String userAccount = request.getParameter("userAccount");
		String userPassword = request.getParameter("userPassword");
		
		// Is this user exist?
		boolean userExist = false;
		if(userAccount == null || "".equals(userAccount) || userPassword == null || "".equals(userPassword)){
			response.sendRedirect("error.jsp");
			return;
		}
		
		for(User user : userList){
			if(userAccount.equals(user.getUserAccount()) && userPassword.equals(user.getUserPassword())){
				userExist = true;
			}
		}
		
		if(userExist){
			request.setAttribute("info", userAccount + ", welcome!");
			
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}else{
			request.setAttribute("info", "Login failure.");
			
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
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
		out.close();
		*/
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
	}
	
	/**
	 * Get user date.
	 * @author Crow
	 * @date 2015年6月13日
	 * @return
	 */
	private List<User> getUserInfo(String projectPath) {
		List<User> userList = new ArrayList<User>();
		
		try {
			File userDataFile = getUserDataFile(projectPath);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(userDataFile), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while((line = br.readLine()) != null){
				String[] info = line.split("#");
				userList.add(new User(info[0], info[1]));
			}
			br.close();
			isr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("The target file is not found, use the default user data.");
			userList.add(new User("admin", "admin"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * Get the file which save the user data.
	 * @author Crow
	 * @date 2015年6月13日
	 * @return
	 * @throws FileNotFoundException
	 */
	private File getUserDataFile(String projectPath) throws FileNotFoundException{
		String userDataFilePath = projectPath + "\\res\\data\\users";
		File userDataFile = new File(userDataFilePath);
		if(userDataFile.exists()){
			return userDataFile;
		}
		throw new FileNotFoundException();
	}

}
