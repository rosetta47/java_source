package pack1;

public class Ex2Car {
	private int speed;
	public String irum;
	private double weight; //= private double weight = 0.0
	
	public Ex2Car() {
		irum = "홍길동";
		speed = 10;
	}
	
	public void showData() {
		System.out.println("이름은" + irum + ", 속도는" + speed);
	}
	//public void abcd(int s, int password) 
	public void setSpeed(int s) { //speed에 집중, setter 메소드
		speed= s; 
		//return;
	}
	
	public int getSpeed() { //참조할 꺼야 getter 메소드
		return speed;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
		//멤버필드 weight에 지역변수 weight값을 치환한다.
	}
	


	
}
