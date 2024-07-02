package pack.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class ControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//방법1 : parameter 사용
		//String command = request.getParameter("command");
		
		//방법2 : 파일명을 요청으로 사용
		String ss = request.getRequestURI();
		
		int idx = ss.lastIndexOf('/'); //문자열을 짜름 .을 기준으로
		StringTokenizer st = new StringTokenizer(ss.substring(idx + 1),".");
		ss = st.nextToken();//배열 이동 // ss : sang 파일명만 나옴
		
	//	System.out.println("ss : " + ss);//ss : /wmvc4sangpum/sang.do
		
		
		String command = ss;
		CommandInter inter = null;
		String viewName = "/WEB-INF/views/";
		
		try {
			if(command.equals("sang")) {
				inter = new SangpunImpl();
				viewName += inter.showData(request, response);
				request.getRequestDispatcher(viewName).forward(request, response);
			}else if(command.equals("jikwon")) {
				//...
			}else {
				viewName = "error.html";
				response.sendRedirect(viewName);
			}
		} catch (Exception e) {
			System.out.println("service err :" + e);
		}
	}
	

	

}
