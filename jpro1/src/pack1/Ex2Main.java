package pack1;

public class Ex2Main {

	public static void main(String[] args) {
		// getter, setter 연습
		System.out.println("프로그램을 실행하다가... ");
		
		// Ex2Car 객체를 만들기
		System.out.println();
		Ex2Car tom = new Ex2Car();
		tom.showData();
		System.out.println("이름은 " + tom.irum);
		tom.irum = "미스터 톰"; // public이니까 접근 가능(private아니라서) 
		System.out.println("이름은 " + tom.irum);
		
		System.out.println("겟처, 셋터 확인");
		//tom.speed = 80; //speed는 private이라서 접근 불가능
		//tom.abcd(80, 123); //private를 출력하기 위해 class에서 public라는 행위를만듦 
		tom.setSpeed(80); //speed에만 집중 
		tom.showData(); 
		//System.out.println("속도는" + tom.speed); //오류문장
		System.out.println("속도는" + tom.getSpeed());
		int result = tom.getSpeed();
		System.out.println("속도는 : " + result);
		
		
		
		
	}

}
