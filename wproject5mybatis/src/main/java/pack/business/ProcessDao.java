package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.business.DataDto;
import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	//여러개 읽기
	public List<DataDto> selectDataAll() throws SQLException{
		SqlSession sqlSession = factory.openSession(); // 세션 열기
		List list = sqlSession.selectList("selectDataAll"); //DataMapper.xml에서 id 읽어와
		sqlSession.close();
		return list;
	}
	
	//하나만 리턴
	public DataDto selectPart(String para) throws SQLException{
		SqlSession sqlSession = factory.openSession(); // 세션 열기
		DataDto dto = sqlSession.selectOne("selectDataById", para);
		sqlSession.close();// 세션 닫아
		return dto;
	}
	
	public void insData(DataForm form) throws SQLException{
		SqlSession sqlSession = factory.openSession(); // transaction 수동 처리
		sqlSession.insert("insertData", form);
		sqlSession.commit(); //수동이라서 써야되 //커밋해줘야되  
		sqlSession.close();
	}
	
	public void upData(DataForm form) throws SQLException{
		SqlSession sqlSession = factory.openSession(true);//transaction 자동 처리(true)
		sqlSession.update("updateData", form);
		sqlSession.close();
	}
	
	public boolean delData(int para) {
		boolean result = false;
		SqlSession sqlSession = factory.openSession(); //수동
		
		try {
			int cou = sqlSession.delete("deleteData", para);
			if(cou > 0)result = true; //하나라도 삭제가 된다면
			
			sqlSession.commit(); // 커밋해
		} catch (Exception e) {
			System.out.println("delData err :" + e);
			sqlSession.rollback();//오류나면 롤백해
		} finally {
			if(sqlSession !=null) sqlSession.close();
		}
		return result;
	}
}
