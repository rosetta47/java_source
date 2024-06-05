package pack;

public class Gugudan {
	private static Gugudan gugudan = new Gugudan();
	public static Gugudan getInstance() { //싱글톤 패턴 만듬 : 인스턴스 하나생성
		return gugudan;
	}
	
	// 구구단 계산 비즈니스 로직 구현 클래스
	public Gugudan() {
		// TODO Auto-generated constructor stub
	}
	
	public int[] computeDudu(int dan) {
		int gu[] = new int[9];
		for (int i = 1; i < 10; i++) {
			gu[i - 1] = dan * i;
		}
		return gu;
	} 
}
