package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.mvc.model.UserManager;

public class LoginController implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("userid");
		String pwd = request.getParameter("password");
		
		// 모델과 통신을 해야되(controller)
		UserManager manager = UserManager.instance();
		boolean b = manager.login(id, pwd);
		
		ModelAndView modelAndView = new ModelAndView();
		if(b) {//자료가 있어 로그인성공 세션만들어주면 되
			//로그인에 성공 자격을 갖춤
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", id);//userid가 세션에 담김
			modelAndView.setViewName("list.m2");//servlet을 만나기 위해 목록보기를 하기 위해 list.m2만듬//리다이렉트
			
		}else {
			modelAndView.setViewName("fail.html");//리다이렉트
		}
		
		modelAndView.setRedirect(true);
		return modelAndView;
	}
}
