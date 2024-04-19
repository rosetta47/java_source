package pack4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex33CompanyMain {
	private ArrayList<Ex33Company> list = new ArrayList<Ex33Company>();

	public void insertDatas(String[] datas) {

		for (int i = 0; i < datas.length; i++) {
			StringTokenizer tok = new StringTokenizer(datas[i], ",");
			int sabun = Integer.parseInt(tok.nextToken());
			String name = tok.nextToken();
			int gibon = Integer.parseInt(tok.nextToken());
			int ipsa = Integer.parseInt(tok.nextToken());

			Ex33Company dto = new Ex33Company();
			dto.setSabun(sabun);
			dto.setName(name);
			dto.setGibon(gibon);
			dto.setIpsa(ipsa);
			list.add(dto);
		}
	}

	public void displayData() {

		for (Ex33Company dd : list) {
			int gunsok;
			int year = (2024 - dd.getIpsa());

			if (year < 4) {
				gunsok = 150000;
			} else if (year > 3 && year < 9) {
				gunsok = 450000;
			} else
				gunsok = 1000000;

			double gongje = ((dd.getGibon() + gunsok) * 0.5);

			System.out.println("사번 : " + dd.getSabun() + ", 이름 : " + dd.getName() + ", 기본급 : " + dd.getGibon()
					+ ", 근무년수 : " + year + ", 근속수당 : " + gunsok + ", 공제액 : " + gongje + ", 수령액 : "
					+ (dd.getGibon() + gunsok - gongje));
		}
		System.out.printf("\n처리 건수 %d건", list.size());
		System.out.println();
	}

	public static void main(String[] args) {

		Ex33CompanyMain main = new Ex33CompanyMain();
		Scanner sc = new Scanner(System.in);

		System.out.println("사번, 이름, 기본급, 입사년도 순서대로 입력");

		String[] datas = new String[2];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = sc.next();
		}

		main.insertDatas(datas);
		main.displayData();

	}

}
