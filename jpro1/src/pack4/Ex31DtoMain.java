package pack4;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex31DtoMain {
	ArrayList<Ex31StudDto> list = new ArrayList<Ex31StudDto>(); // list는 위에 설정해야되

//	Ex31StudDto dto = new Ex31StudDto(); // 생성자를 여기에 작성하면 값이 누적이 안되고 마지막 입력값만 나옴

	public void showList() {
		System.out.println("이름 국어 영어 총점 ");
		for (Ex31StudDto sc2 : list) {
			System.out.println(sc2.getIrum() + " " + sc2.getKjumsu() + " " + sc2.getEjumsu() + " "
					+ (sc2.getKjumsu() + sc2.getEjumsu()));

		}
		System.out.println("응시인원 : " + list.size()+"명");

	}

	public void insertList() {
		while (true) { //반복문while 무한루프사용 (값이 정해지면 for문 사용)
			
			Ex31StudDto dto = new Ex31StudDto(); // dto를 여기서 사용하니까 while에 넣어줘
			
			Scanner sc = new Scanner(System.in);
			System.out.print("이름입력 : ");
			dto.setIrum(sc.next()); // 문자만 입력
			System.out.print("국어 점수 : ");
			dto.setKjumsu(sc.nextInt()); // 숫자 입력
			System.out.print("영어 점수 : ");
			dto.setEjumsu(sc.nextInt());
			list.add(dto);

			System.out.println("계속할까요? : y/n");
			if (sc.next().equalsIgnoreCase("n"))
				break;
		}

	}

	public static void main(String[] args) { //main은 출력을 담당함

		Ex31DtoMain dtoTest = new Ex31DtoMain();
		dtoTest.insertList();
		dtoTest.showList();
	}

}
