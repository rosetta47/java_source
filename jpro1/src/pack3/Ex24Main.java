package pack3;

public class Ex24Main {

	public static void main(String[] args) {
		// 내부클래스 연습용
		Ex24Outer outer = new Ex24Outer();
		outer.aa();
		outer.bb();
		//outer.cc(); err
		
		System.out.println();
	//	Inner inner = new Inner(); // err :내부 클래스는 독립적인 인스턴스 불가 
		
		// 아래의 소스는 수행은 가능하나 현실적으로 사용하지 않는다.
	//	Ex24Outer.Inner inner = outer.new Inner();
	//	inner.cc();
		
		System.out.println("+++++++++");
		outer.aa();
		
		
	}

}
