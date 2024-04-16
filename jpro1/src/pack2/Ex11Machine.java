package pack2;

import java.util.Scanner;

public class Ex11Machine {
	private int cupCount;
	private Ex11coinIn coinIn = new Ex11coinIn(); //포함관계
	
	public Ex11Machine() {
		
	}
	
	public void showData() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("금액을 입력하세요");
		//coinIn.setCoin(scanner.nextInt());
		coinIn.setCoin(scanner.nextInt());
		
		System.out.println("몇잔을 원하세요");
		cupCount = scanner.nextInt();
		//String result = coinIn.calc(cupCount);
		System.out.println(coinIn.calc(cupCount));
	}
	
	
	
}
