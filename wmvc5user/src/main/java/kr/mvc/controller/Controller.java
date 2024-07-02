package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller { //execute을 만들어서 할꺼임
	ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
}
