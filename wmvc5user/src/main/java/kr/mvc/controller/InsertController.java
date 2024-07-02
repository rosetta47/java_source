package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class InsertController implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		UserForm userForm = new UserForm();
		userForm.setUserid(request.getParameter("userid"));
		userForm.setPassword(request.getParameter("password"));
		userForm.setName(request.getParameter("name"));
		userForm.setEmail(request.getParameter("email"));
		
		// 모델과 통신할꺼임
		int result = UserManager.instance().insert(userForm);
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(result >0) {
			// insert 후 목록보기
			modelAndView.setViewName("list.m2");//클라이언트를 통해서 불려야되 = redirect
		}else {
			modelAndView.setViewName("fail.html");
		}
		
		modelAndView.setRedirect(true);
		
		return modelAndView;
	}
	
}
