package kr.mvc.model; 

import java.util.ArrayList; 

import kr.mvc.controller.UserForm; 

// 여러 개의 DAO 클래스 관리가 목적임
public class UserManager { // UserManager 클래스 선언, 여러 개의 DAO 클래스를 관리하기 위한 목적

    // UserManager 클래스의 단일 인스턴스를 생성
	private static UserManager manager = new UserManager();
    
    // UserManager 클래스의 인스턴스를 반환하는 메소드, 싱글톤 패턴을 구현
	public static UserManager instance() { 
		return manager; // 싱글톤 패턴으로 유일한 인스턴스를 반환
	}
	
    // UserDaoModel 객체를 반환하는 메소드, UserDaoModel 클래스의 새 인스턴스를 생성하여 반환
	private UserDaoModel getUserDaoModel() {
		return new UserDaoModel();
	}
	
    // 로그인 메소드, 사용자 ID와 비밀번호를 받아 로그인 여부를 확인
	// 로그인 (테이블의 칼럼명과 다르게 해서 비교하려고 _넣음)
	public boolean login(String user_id, String user_password) {
        // UserDaoModel을 통해 사용자를 찾음
		UserDto dto = getUserDaoModel().findUser(user_id);
        
        // 사용자를 찾지 못한 경우 false 반환
		if(dto == null) return false;
		
        // 사용자를 찾았고, 비밀번호가 일치하는 경우 true 반환
		if(dto.getPassword().equals(user_password)) {
			return true;
		} else {
            // 비밀번호가 일치하지 않는 경우 false 반환
			return false;
		}
	}
	
    // 전체 사용자 데이터를 읽어오는 메소드
	// 전체 자료 읽기
	public ArrayList<UserDto> getUserAll() { // 원래는 이름이 같은게 좋아
        // UserDaoModel을 통해 전체 사용자 데이터를 가져옴
		return getUserDaoModel().getUserDataAll();
	}
	
    // 사용자 데이터를 삽입하는 메소드
	// 유저매니저 insert
	public int insert(UserForm userForm) {
        // UserDaoModel을 통해 사용자 데이터를 삽입
		return getUserDaoModel().insertData(userForm);
	}
	
    // 사용자 데이터를 찾는 메소드
	// view를 위한 하나를 넘김
	public UserDto findUser(String userid) { // 반환값이 UserDto
        // UserDaoModel을 통해 특정 사용자 데이터를 찾음
		return getUserDaoModel().findUser(userid);
	}
}
