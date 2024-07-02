package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class ViewController implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//상세보기 처리를 하는 곳
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		
		//모델과 통신
		UserDto dto = UserManager.instance().findUser(userid);//db에서 자료를 가지고 온거임
		request.setAttribute("user", dto); //dto를 view.jsp에 넣기 위한 forward
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view.jsp");//서버에서 서버를 부름 = forward
		modelAndView.setRedirect(false);//forward으로 갈꺼니까 false로 해
		
		return modelAndView;
	}
}
