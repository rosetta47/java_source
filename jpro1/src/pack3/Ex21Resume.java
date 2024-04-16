package pack3;

public interface Ex21Resume {//이력서 표준 양식 
	String SIZE = "A4"; // public final static String SIZE = "A4";의 의미
	//int kor =90;
	
	void setIrum(String irum); //추상메소드야
	void setPhone(String phone);
	void printData(); //printData메소드에서 출력하도록 한거임
	
	// java 1.8 이후에 가능 default하니까 에러 안생김
	default void display(boolean b) {
		if(b) {
			System.out.println("참");
		}else {
			System.out.println("거짓");
		}
	}
	
	static void play() {
		System.out.println("play 메소드");
	}
	
}
