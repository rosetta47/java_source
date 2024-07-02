package kr.mvc.model;

import java.util.ArrayList;

import kr.mvc.controller.UserForm;

// 여러 개의 DAO 클래스 관리가 목적임
public class UserManager {
	private static UserManager manager = new UserManager();
	public static UserManager instance() { 
		return manager; //싱글톤 만듬
	}
	
	private UserDaoModel getUserDaoModel() {//getUserDaoModel메소드 만듬
		return new UserDaoModel();
	}
	
	//로그인 (테이블의 칼럼명과 다르게 해서 비교하려고 _넣음)
	public boolean login(String user_id, String user_password) {
		UserDto dto = getUserDaoModel().findUser(user_id);
		if(dto == null) return false;
		
		if(dto.getPassword().equals(user_password)) {
			return true;
		}else {
			return false;
		}
	}
	
	//전체 자료 읽기
	public ArrayList<UserDto> getUserAll(){ //원래는 이름이 같은게 좋아
		return getUserDaoModel().getUserDataAll();
	}
	
	//유저매니저 insert
	public int insert(UserForm userForm) {
		return getUserDaoModel().insertData(userForm);
	}
	
	//view를 위한 하나를 넘김
	public UserDto findUser(String userid) { //반환값이 UserDto
		return getUserDaoModel().findUser(userid);
		
	}
}
