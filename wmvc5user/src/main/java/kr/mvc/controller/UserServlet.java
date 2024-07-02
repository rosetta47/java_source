package kr.mvc.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.m2")//확장자 m2
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// UserServlet공간
	private ModelAndView modelAndView = null;//멤버필드로 선언
	private Controller controller = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//한글깨짐 방지

		try {
			// 파일명을 요청 명으로 사용
			String ss = request.getRequestURI();
			int idx = ss.lastIndexOf('/'); // 문자열을 짜름 .을 기준으로
			StringTokenizer st = new StringTokenizer(ss.substring(idx + 1), ".");
			ss = st.nextToken();// 배열 이동
			//System.out.println("ss : " + ss); //ss : login 결과값이 나옴(login.m2) //list.m2는 list나옴//view가 나옴
			String command = ss; 
			
			controller = getController(command);
			modelAndView = controller.execute(request, response);
			
			// 파일 호출 방식 선택
			if(modelAndView.isRedirect()) {//getisRedirect()임 
				response.sendRedirect(modelAndView.getViewName());
			}else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("/WEB-INF/views/" + modelAndView.getViewName());
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("service err:" + e);
		}
	}
	
	public Controller getController(String command) throws Exception{
		if(command.equals("login")) { //login글자를 만나면
			controller = new LoginController();
		}else if(command.equals("list")) {//list글자를 만나면
			controller = new ListController(); 
		}else if(command.equals("insert")) {//insert글자를 만나면
			controller = new InsertController();  
		}else if(command.equals("view")) {//view글자를 만나면
			controller = new ViewController();  
		}else if(command.equals("logout")) {//logout글자를 만나면
			controller = new LogoutController();//주소를 넘기는 거임
		}else if(command.equals("updateform")) {//updateform글자를 만나면
			controller = new UpdateFormController();//주소를 넘기는 거임
		}else if(command.equals("update")) {//update 요청을 만나면
			controller = new UpdateController();//주소를 넘기는 거임
		}else if(command.equals("delete")) {//delete 요청을 만나면
			controller = new DeleteController();//주소를 넘기는 거임
		}
		
		return controller;
	}
	
}
