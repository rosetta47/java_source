package pack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.HobbyModel;


//@WebServlet("/hobby.do")
//@WebServlet({"/hobby.do","/hobby2.do","*.kor"})
public class HobbyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HobbyModel model;

	public void init(ServletConfig config) throws ServletException {
		model = new HobbyModel(); // 객체 관리는 프로그래머가 하고 있는 것임(나중에 스프링이 다 해줌)
		
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hobby = request.getParameter("hobby");
		
		//model을 통해 결과를 얻음
		ArrayList<String> list = model.getHobby(hobby);
		
		request.setAttribute("datas", list);//datas라는 키에 list를 줄거임
		String viewName = "/WEB-INF/views/show.jsp";
		
		request.getRequestDispatcher(viewName).forward(request, response);
	}

}
