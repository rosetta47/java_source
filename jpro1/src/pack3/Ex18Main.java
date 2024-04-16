package pack3;

public class Ex18Main {

	public static void main(String[] args) {
		Ex18Cow cow = new Ex18Cow();
		System.out.println(cow.name());
		System.out.println(cow.eat());
		System.out.println(cow.action());
		cow.animalPrint();
		
		System.out.println();
		Ex18Lion lion = new Ex18Lion();
		System.out.println(lion.name());
		System.out.println(lion.eat());
		System.out.println(lion.action());
		cow.animalPrint();
		lion.eatOther(); // 사자 고유 메소드
		
		System.out.println();
		Ex18Animal animal = null;
		animal = cow;
		System.out.println(animal.name());
		
		animal = lion;
		System.out.println(animal.name());
		
		System.out.println("별도의 클래스 사용해보기");
		Ex18FindUtil.find(cow); //static이니까 new안하고 바로 출력
		System.out.println();
		Ex18FindUtil.find(lion);
		
	}

}
