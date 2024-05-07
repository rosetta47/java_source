package lambda;

@FunctionalInterface
interface myInter{
	void aaa();
}

interface myInterArg{ //반환값 없음: void
	void bbb(int a, int b);
}

interface myInterArgReturn{ //반환값 int 있음
	int ccc(int a, int b);
}
public class Mylambda2 {

	public static void main(String[] args) {
		// 1. 아구먼트(인자)가 없는 추상 메소드 처리 : 전통적인 방식
		myInter inter = new myInter() {
			
			@Override
			public void aaa() {
				System.out.println("익명 클래스의 aaa 메소드 오버라이딩");
				
			}
		};
		inter.aaa();
		
		// 람다식으로 표현 1
		myInter inter2 = () -> {
			System.out.println("익명 클래스의 aaa 메소드 오버라이딩 :람다 사용");
		};
		inter2.aaa();
		
		myInter inter3 = () -> {
			int imsi = 10;
			System.out.println("람다식으로");
			System.out.println("복수의 며령문 처리");
			System.out.println("imsi :" + imsi);
		};
		inter3.aaa();
		
		// 2. 아구먼트(인자)가 있는 추상 메소드 처리 : 전통적인 방식
		myInterArg interArg = new myInterArg() {
			
			@Override
			public void bbb(int a, int b) {
				System.out.println("두 수의 합은" + (a+b));
				
			}
		};
		System.out.println("-------");
		interArg.bbb(3,4);
		// 람다식으로 표현2 : arg 1개인 경우 (a) -> 또는 a -> 로 적음
		myInterArg interArg2 = (a,b) -> 
		System.out.println("람다로 두 수의 합은" + (a+b)); //ab는 지역변수니까 다른 영어써도 되
		interArg2.bbb(3,4);
		
		System.out.println("------");
		//3. 인자가 있고 반환값도 있는 추상 메소드 처리
		myInterArgReturn argReturn = new myInterArgReturn() {
			
			@Override
			public int ccc(int a, int b) {
				System.out.println("ccc 처리"); //ab는 지역변수니까 다른 영어써도 되
				return a+b;
			}
		};
		int result = argReturn.ccc(5, 6);
		System.out.println("두 수를 더한 결과 : " + result);
		
		// 람다식으로 표현3
		myInterArgReturn argReturn2 = (m,n) -> (m+n); // 수행값이 하나이면 이렇게 간단하게 쓸수 있어
	
		myInterArgReturn argReturn3 = (m,n) -> {
			System.out.println("ccc 수행"); 
			return m+n;
		};
		int result2 = argReturn3.ccc(5, 6);
		System.out.println("람다식으로 두 수를 더한 결과 : " + result2);
	}

}
