package pack3;

public class Ex12Father extends Ex12GrandFa { // 단일 상속만 가능
	//Ex12GrandFa fa = new Ex12GrandFa(); //포함 관계
	
	public String gabo = "꽃병"; // 은닉화
	private int nai = 55;
	private int house = 1;
	
	public Ex12Father() {
		super(); // 매개변수가 없는 부모 생성자 호출
		System.out.println("아버지 생성자");
	}
	
	public Ex12Father(int n) {
		System.out.println("아버지 생성자라고 해");
	}
	
	@Override //@Override은 지워도 상관없어
	public int getNai() {
		// TODO Auto-generated method 오버라이딩이 되는거임
		return nai;
	}
	
	public String say() {
		return "아버지 말씀 : 에러 찾는 연습을 하거라";
	}
	
	final public String getHouse() { //메소드에 final하면 자식 클래스에서 오버라이딩 불가
		return "집 : "+ house +"채"; //getHouse는 me 클래스에 상속 불가해
	}
	
	public void showData() {
		System.out.println("아버지 나이"+nai);
		System.out.println("아버지 나이"+this.nai);
		System.out.println("아버지 나이"+getNai());
		System.out.println("아버지 나이"+this.getNai());
	// System.out.println("할아버지 나이"+super.nai()); //에러
		System.out.println("할아버지 나이"+super.getNai());
		
		System.out.println();
		String gabo = "물병";
		System.out.println("가보 :"+ gabo); //물병
		System.out.println("가보 :"+ this.gabo); //꽃병
		System.out.println("가보 :"+ super.gabo); // 상감청자
	}
}
