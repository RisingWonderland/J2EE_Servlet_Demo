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
public class GetCityServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCityServlet() {
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
		
		PrintWriter out = response.getWriter();
		String id = request.getParameter("cityId");
		if("1".equals(id))
		{
			out.println("[{\"id\":\"1\", \"name\":\"东城区\"}, {\"id\":\"2\", \"name\":\"西城区\"}, {\"id\":\"3\", \"name\":\"宣武区\"}, {\"id\":\"2\", \"name\":\"崇文区\"}, " +
						 "{\"id\":\"2\", \"name\":\"丰台区\"}, {\"id\":\"2\", \"name\":\"海淀区\"}, {\"id\":\"2\", \"name\":\"朝阳区\"}, {\"id\":\"2\", \"name\":\"石景山区\"}, " +
						 "{\"id\":\"2\", \"name\":\"房山区\"}, {\"id\":\"2\", \"name\":\"平谷区\"}, {\"id\":\"2\", \"name\":\"密云区\"}, {\"id\":\"2\", \"name\":\"怀柔区\"}, " +
						 "{\"id\":\"2\", \"name\":\"延庆区\"}, {\"id\":\"2\", \"name\":\"大兴区\"}, {\"id\":\"2\", \"name\":\"门头沟区\"}, {\"id\":\"2\", \"name\":\"顺义区\"}, " +
						 "{\"id\":\"2\", \"name\":\"昌平区\"}]");
		}else if("2".equals(id))
		{
			out.println("[{\"id\":\"1\", \"name\":\"和平区\"}, {\"id\":\"2\", \"name\":\"河东区\"}, {\"id\":\"3\", \"name\":\"河西区\"}, {\"id\":\"2\", \"name\":\"南开区\"}, " +
						 "{\"id\":\"2\", \"name\":\"河北区\"}, {\"id\":\"2\", \"name\":\"红桥区\"}, {\"id\":\"2\", \"name\":\"塘沽区\"}, {\"id\":\"2\", \"name\":\"汉沽区\"}, " +
						 "{\"id\":\"2\", \"name\":\"大港区\"}, {\"id\":\"2\", \"name\":\"东丽区\"}, {\"id\":\"2\", \"name\":\"西青区\"}, {\"id\":\"2\", \"name\":\"津南区\"}, " +
						 "{\"id\":\"2\", \"name\":\"北辰区\"}, {\"id\":\"2\", \"name\":\"武清区\"}, {\"id\":\"2\", \"name\":\"宝坻区\"}]");
		}else if("3".equals(id))
		{
			out.println("[{\"id\":\"1\", \"name\":\"石家庄\"}, {\"id\":\"2\", \"name\":\"唐山市\"}, {\"id\":\"3\", \"name\":\"秦皇岛\"}, {\"id\":\"2\", \"name\":\"邯郸市\"}, " +
						 "{\"id\":\"2\", \"name\":\"邢台市\"}, {\"id\":\"2\", \"name\":\"保定市\"}, {\"id\":\"2\", \"name\":\"张家口\"}, {\"id\":\"2\", \"name\":\"承德市\"}, " +
						 "{\"id\":\"2\", \"name\":\"沧州市\"}, {\"id\":\"2\", \"name\":\"廊坊市\"}, {\"id\":\"2\", \"name\":\"渠水市\"}]");
		}
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

}
