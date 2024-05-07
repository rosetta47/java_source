package lambda;

// lambda Expression : 이름이 없는 익명 함수(메소드)
// 람다 표현식은 함수형 인터페이스의 구현으로만 가용할 수 있다.
// 하나의 추상 메소드를 가져야 하며, 람다 표현식은 해당 메소드의 구현으로 동작한다.

@FunctionalInterface // 함수형 인터페이스임을 명시적으로 알림 / 있으면 가독성이 좋아
interface HelloInter{ // lambda Expression 사용할 때 인터 페이스
	// 추상 메소드는반드시 1개여야 함. 이것을 함수형 인터페이스라고 함.
	int calcData(int a, int b); // a,b 두개의 파라미터가 있다.
//	int calcData2(int a, int b); // 2개면 인터페이스가 안됨
}

public class MyLambda1 implements HelloInter {

	@Override
	public int calcData(int a, int b) {
		// 인터페이스의 추상 메소드를 오버라이딩 : 전통적 방법
		return a + b;	
	}
	
	/*@Override
	public int calcData2(int a, int b) {
		
		return 0;
	} */
	
	public static void main(String[] args) {
		MyLambda1 my = new MyLambda1();
		System.out.println(my.calcData(3, 4)); // 전통적인 방식
		
		System.out.println();
		// 인터페이스 변수 = 람다식 으로 표현 : (parameter ,,,) -> { body }
		HelloInter inter = (x, y) -> x + y;
		System.out.println(inter.calcData(4, 5));
		
		HelloInter inter2 = (x, y) -> x * y;
		System.out.println(inter2.calcData(4, 5));
	}

}
