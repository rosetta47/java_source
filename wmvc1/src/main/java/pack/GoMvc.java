package pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GoMvc")
public class GoMvc extends HttpServlet {// Controller 역할함(비지니스 로직 아님)
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String result="", viewName="/WEB-INF/";
		String name;
		
		try {
			name = request.getParameter("name");
		} catch (Exception e) {
			name = null;
		}
		
		// 클라이언트의 요청을 받아 분석(판단) : 모델과 뷰를 선택하는 기능 기술
		if(name == null || name.equals("")) {
			result = "환영합니다"; //Model(Business Logic)을 수행한 결과라 가정한다.
			viewName += "views/view1.jsp"; // views 폴더에 view1 
		}else if(name.equals("korea")) {
			result = "한국인이군요"; 
			viewName += "views/view2.jsp";
		}else {
			result = name+ "님 반가워요"; 
			viewName += "views/view3.jsp";
		}
		
		request.setAttribute("result", result); //jsp이니까 이렇게 부를 수 있어
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
		dispatcher.forward(request, response); // 41,42,43을 다 해야지 자료가 넘어감
	}

}
