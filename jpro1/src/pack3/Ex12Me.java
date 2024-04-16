package pack3;

public class Ex12Me extends Ex12Father{
	final int score = 90; // 멤버 필드에 final하면 값 수정 불가
	
	public Ex12Me() {
		//super(); // 생략가능
		System.out.println("내 생성자");
	//	score = 80;  에러
	}
	
	public void biking() {
		System.out.println("자전거로 전국일주~~~");
		
//	public String getHouse() { //메소드에 final하면 자식 클래스에서 오버라이딩 불가
//			return "집";
//		}	
		
	}
	
}
