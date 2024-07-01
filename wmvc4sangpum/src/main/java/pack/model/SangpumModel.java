package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SangpumModel {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List<SangpumDto> selectDataAll(){ //메소드 이름 : selectDataAll
		List<SangpumDto> list = null;
		
		SqlSession session = factory.openSession(); //세션 열기
		list = session.selectList("selectDataAll");  //id이름 : selectDataAll
		session.close(); //세션 닫기
		
		return list;
	}
}
