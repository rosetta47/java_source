package pack.business;

import java.util.List;

public interface ProcessInter { //프로젝트가 커지면 interface를 만들어서 사용하는게 효율적이야 파생클래스를가지고 로직을 짜는게 좋아
	List<DataDto> selectDataAll();
	DataDto selectPart(String para);
	boolean insertData(DataFormBean form);
	boolean updateData(DataFormBean form);
	boolean deleteData(String id);
}
