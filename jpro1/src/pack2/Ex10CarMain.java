package pack2;

public class Ex10CarMain {

	public static void main(String[] args) {
		// 클래스의 포함관계 연습
		Ex10pohamCar tom = new Ex10pohamCar("미스터 톰");
		tom.playcarTurnHandle(30); //30은 양수
		System.out.println(tom.ownerName + "의 회전량은 "+ tom.turnMessageShow
				+ " " + tom.handle.quantity);
		
		tom.playcarTurnHandle(-20); //30은 양수
		System.out.println(tom.ownerName + "의 회전량은 "+ tom.turnMessageShow
				+ " " + tom.handle.quantity);
		
		System.out.println();
		Ex10pohamCar james = new Ex10pohamCar("제임스 삼촌");
		james.playcarTurnHandle(0); 
		System.out.println(james.ownerName + "의 회전량은 "+ james.turnMessageShow
				+ " " + james.handle.quantity);
	}

}
