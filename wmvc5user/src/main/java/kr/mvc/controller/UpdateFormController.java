package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class UpdateFormController implements Controller{
 @Override
 	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	//수정 화면 띄우기
	 	request.setCharacterEncoding("utf-8");
	 	String userid = request.getParameter("userid");
	 	
	 	UserDto dto = UserManager.instance().findUser(userid);
	 	request.setAttribute("user", dto);//모델과 통신함
	 	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("update.jsp");//서버에서 서버를 부름 = forward((객체로 전달할꺼니까))
		modelAndView.setRedirect(false);//forward으로 갈꺼니까 false로 해
		
		return modelAndView;
 	}
}
