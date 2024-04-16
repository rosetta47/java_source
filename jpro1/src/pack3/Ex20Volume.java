package pack3;

// 추상 메소드와 상수로 구성된 클래스=인터페이스
// 다중상속이 가능하고, 인터페이스는 인스턴스(new)할 수 없다.
// 인터페이스는 자식 클래스에서 implements 키워드로 구현한다.
// 인터페이스 간 상속이 가능하다.

public interface Ex20Volume {
	void volumeUp(int level); // public abstract void volumeUp(int level);를 의미
	void volumeDown(int level);
	
/*	void print() {
		//일반 메소드는 사용할 수 없다.
	} */
	
}
