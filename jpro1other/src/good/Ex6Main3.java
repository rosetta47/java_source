package good;

import pack1.Ex6Bank;

public class Ex6Main3 {

	public static void main(String[] args) {
		// 프로젝트가 다른곳에서 Ex6BanK를 사용하려면
		Ex6Bank kildong = new Ex6Bank();
		kildong.dePosit(5000);
		kildong.withDraw(2000);
		System.out.println("kildong 현재예금액 :" + kildong.getMoney());

		//여기부터 자습
		String a = "Happy";
		System.out.println(a.hashCode());//happy 주소랑
		
		a = a+"Day" ;	
		System.out.println(a.hashCode());//HappyDay은 주소가 달라
		
		
	}

}
