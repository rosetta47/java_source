package kr.mvc.controller;

public class ModelAndView {// 호출 방식과 view 파일명 기억하는 클래스로 만들꺼야
	private boolean isRedirect = false; //스프링은 false가 기본임
	private String viewName = "";
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	
	
	
}
