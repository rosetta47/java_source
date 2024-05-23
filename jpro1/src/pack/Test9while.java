package pack;

import java.util.Iterator;
import java.util.Scanner;

public class Test9while {

	public static void main(String[] args) {
		// 반복문 while
		// while(조건) {실행문들..}
		int w = 1; // w은 1이라고 변수생각
		while (w <= 5) {
			System.out.print("w = " + w + " ");
			w++; // w++;은 밖에 써 while일때
		}
		System.out.println("\nwhile 수행 후 w:" + w);
		// w=1 w=2 w=3 w=4 w=5 while 수행 후 w :6 으로 출력

		System.out.println(); // 라인 스킵
		int count = 0;
		while (count < 5) {
			count++;
			System.out.println("count : " + count);
			if (count == 3) {
				System.out.println("쉬어가기");
			}
			if (count == 5) {
				System.out.println("count가 5라서 종료:" + count);
			} // count : 5 count가 5라서 종료:5 출력
		}

		// 반복문에 continue, break 는 while에서 쓸수 있어
		System.out.println();
		w = 0; // 위에 적인 w를 초기화하는 거야. 만약 int w = 0;라고 쓰면 오류임(위에 값이 설정되어있어서)
		while (true) { // 무한 루프는 주로 while문을 사용해
			w++;
			if (w == 5)
				break; // 반복문에 무조건 탈출이야
			if (w == 3)
				continue;
			System.out.println("w는 " + w); // w는 1 w는 2 w는 4 출력
		}

		System.out.println();
		// 팩토리얼(계승) n! = 1부터 n까지의 자연수를 모두 곱하는 것을 의미한다.
		// ex, 5! = 5*4*3*2*1
		// Scanner scanner = new Scanner(System.in); //ctrl space로 간단하게 해
		// System.out.print("정수 입력 :" );
		// int number = scanner.nextInt(); // API를 가져다 적용
		int number = 5;
		// factorial 계산을 위한 초기값 설정
		int factorial = 1;
		int i = 1; // 반복을 위한 counter 변수
		while (i <= number) {
			// System.out.println(i); ////number는 5 factorial은 1이다 출력
			factorial *= i; // number는 5 factorial은 120이다 출력(*가 있어서!로 되는거야)
			i++;
		}

		System.out.printf("number는 %d factorial은 %d이다", number, factorial);

		System.out.println();
		// 키보드로 입력 받은 숫자에 대해 1부터 시작해 그 숫자까지 모든수에
		// 나누기를 시도하고 나누어 떨어지는 경우(약수) 그 수를 출력한다.
		// 0이나 음수를 입력하면 프로그램 종료.

		/*
		 * Scanner scanner = new Scanner(System.in); while(true) {
		 * System.out.print("정수 입력(0이나 음수면 종료) :" ); int num = scanner.nextInt(); if(num
		 * <= 0) { System.out.println("프로그램 종료"); break; } System.out.println(num +
		 * "의 약수는"); int divisor = 1; //약수를 찾기 위해 1부터 시작한다 while(divisor <= num) {
		 * if(num % divisor == 0) { System.out.println(divisor); //약수를 출력 } divisor++;
		 * // 다음 수로 이동 } }
		 */

		System.out.println();
		// do{반복 수행문 ... }while(조건);
		int k = 1;
		do { // 최소 1회는 수행
			System.out.println("k : " + k);
			k = k + 1;
		} while (k <= 5);

		System.out.println();
		// 문1) 1~100 사이의 정수 중 3의 배수이지만 2의 배수가
		// 아닌 수를 출력하고, 합과 갯수도 출력하라

		// 문2) -1 , 3, -5, 7, -9, 11 ... 99 까지의 합 출력

		// 문3) 1~100 사이의 정수중에서 소수와 그 갯수를 출력
		// 소수 : 1보다 크며 1과 그 수 자체 이외의 다은 수로는
		// 나누어 떨어지지 않는 수

		// 문1)

		// int su = 1;
		// while(su<=100) {
		// System.out.println(su);
		// su++;

		// int hap = 0;
		// int gaesu = 0;

		// if(su % 3 == 0 && su 2 ! ==0) {
		// gaesu += 1;
		// hap += su;
		// su++;
		// }
		// System.out.println("합" + hap + "갯수" + gaesu);

		// }

		// 문1  1~100 사이의 정수 중 3의 배수이지만 2의 배수가
		// 아닌 수를 출력하고, 합과 갯수도 출력하라
		int m = 1, tot = 0, su = 0;
		while (m <= 100) {
			if (m % 3 == 0 && m % 2 != 0) {
				System.out.print(m + " "); //3의배수이지만 2의 배수가 아닌수 출력
				tot += m;
				su += 1;
			}
			m++;
		}
		System.out.println("합은 " + tot + ", 객수" + su);
		//결과값 : 3 9 15 21 27 33 39 45 51 57 63 69 75 81 87 93 99 합은 867, 객수17

		// 문2 -1 , 3, -5, 7, -9, 11 ... 99 까지의 합 출력
		int n = 1, cnt = 1, hap = 0; // 변수명 설정
		while (n < 100) {
			if (cnt % 2 == 0) {
				hap += n;// n을 누적 13579
				System.out.println("짝수" + n);
			} else {
				int imsi = n * -1;
				hap += imsi; // 부호 변경후 누적해야되
				System.out.println("홀수" + imsi);
			}
			// System.out.println(n);
			n += 2; // n은 2씩 늘어난다.
			cnt += 1;
		}
		System.out.println("합은 " + hap + "입니다");

		// for로 출력
		/*
		 * int hap2 = 0, cnt2 = 1; for(int c=1; c <100; c += 2) { if(cnt2 % 2 == 0 ) {
		 * hap2 += c;//양수누적 else { hap2 += (-1) * c; //음수누적 } cnt2 ++; }
		 * System.out.println("for 합은 " + hap2 + "입니다");
		 */

		// 문3 
		int aa = 2;
		int count2 = 0;
		
		System.out.println("1부터 100까지의 소수: ");
		while (aa <= 100) {
			boolean imsi = false;

			int bb = 2;
			while (bb <= aa - 1) {
				if (aa % bb == 0) {
					imsi = true;
				}
				bb++;
			}
			if (imsi == false) {
				System.out.println(aa + " ");
				count2++;
			}
			aa++;
		}
		System.out.println("\n" + count2);
		

		System.out.println("2부터 그 숫자의 제곱근까지의 모든 수로 나누어 떨어지는지 확인");
		// 소수를 찾는 이유로 제곱근가지만 검사하는 것은 어떤 수의 약수는 그 수의 제곱근을 넘지 않기 대문
		int num = 2; // 1은 소수가 아니므로 2부터 출발
		int count3 = 0; // 건수
		System.out.println("1부터 100까지의 소수: ");
		boolean isPrime = true; // 현재 숫자가 소수인지 판별

		while (num <= 100) {
			int divisor = 2; // 나누는 수는 2부터 출발
			while (divisor <= Math.sqrt(num)) {
				if (num % divisor == 0) {
					isPrime = false;
					break; // 나누어 떨어지지 않으면 더 이상의 검사는 필요없다.
				}
				divisor++;
			}
			if (isPrime == true) {
				// if(isPrime)써도 상관없어
				count3++; // 소수의 갯수 증가
				System.out.print(num + " ");
			}
			num++;
		}
		System.out.println("\n건수 : " + count2);
		
		

	}

}
