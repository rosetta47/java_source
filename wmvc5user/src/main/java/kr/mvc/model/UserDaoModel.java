package kr.mvc.model; 

import java.util.ArrayList; 
import java.util.List; 

import org.apache.ibatis.session.SqlSession; 
import org.apache.ibatis.session.SqlSessionFactory; 

import kr.mvc.controller.UserForm; 

// Controller 클래스의 요청을 받아 DB 연동 처리를 담당함
public class UserDaoModel {
    // SqlSessionFactory 객체 생성, SQL 세션을 생성하는데 사용
    private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
    
    // 기본 생성자
    public UserDaoModel() {
        // TODO Auto-generated constructor stub
    }
    
    // 특정 사용자 정보를 조회하는 메소드
    public UserDto findUser(String userid) {
        UserDto dto = null; // UserDto 객체를 null로 초기화
        
        SqlSession session = factory.openSession(); // SQL 세션을 엶
        
        try {
            // SQL 매퍼의 "findUser" 쿼리를 실행하여 사용자 정보를 조회
            dto = session.selectOne("findUser", userid); // DataMapper.xml의 id가 "findUser"인 select문 실행
        } catch (Exception e) {
            System.out.println("findUser err : " + e); // 예외 발생 시 에러 메시지 출력
        } finally {
            session.close(); // 세션 닫기
        }
        return dto; // 조회된 UserDto 객체 반환
    }
    
    // 모든 사용자 정보를 조회하는 메소드
    public ArrayList<UserDto> getUserDataAll() {
        List<UserDto> list = null; // UserDto 리스트를 null로 초기화
        SqlSession session = factory.openSession(); // SQL 세션을 엶
        
        try {
            // SQL 매퍼의 "selectDataAll" 쿼리를 실행하여 모든 사용자 정보를 조회
            list = session.selectList("selectDataAll");
        } catch (Exception e) {
            System.out.println("getUserAll err : " + e); // 예외 발생 시 에러 메시지 출력
        } finally {
            session.close(); // 세션 닫기
        }
        
        return (ArrayList<UserDto>) list; // 조회된 리스트를 ArrayList로 변환하여 반환
    }
    
    // 사용자 정보를 삽입하는 메소드
    public int insertData(UserForm userForm) {
        int result = 0; // 결과를 저장할 변수 초기화
        SqlSession session = factory.openSession(); // SQL 세션을 엶
        
        try {
            // SQL 매퍼의 "insertData" 쿼리를 실행하여 사용자 정보를 삽입
            result = session.insert("insertData", userForm);
            session.commit(); // 삽입 성공 시 커밋
        } catch (Exception e) {
            System.out.println("insertData err : " + e); // 예외 발생 시 에러 메시지 출력
            session.rollback(); // 예외 발생 시 롤백
        } finally {
            session.close(); // 세션 닫기
        }
        return result; // 삽입 결과 반환
    }
}
