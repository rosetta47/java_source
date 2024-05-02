package pack;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		// properties 파일 읽기
		// 자바프로그램에서 프로그램 설정정보를 정적으로 정해놓는 대신(하드코딩) 외부 .properties확장자 파일로 저장하여 시스템 설정 변경에
		// 유연하게 대처하게 위해 사용
		// Java.util.Properties클래스를 이용하면, 프로그램의 설정정보를 쉽게 개발코드에 불러오거나 또는 설정정보에 새로운 정보를
		// 추가, 저장 할 수 있음
		// 시스템 설정 변경시 시스탬이 재컴파일 되는 것을 막음
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(
					"C:\\work\\jsou\\jpro2\\src\\pack\\ex.properties"));
			System.out.println(prop.getProperty("mes1"));
			System.out.println(prop.getProperty("mes2"));
		} catch (Exception e) {
			System.out.println("err : " + e);
		}

	}

}
