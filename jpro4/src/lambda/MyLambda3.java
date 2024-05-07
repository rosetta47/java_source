package lambda;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MyLambda3 {
	
	public MyLambda3() {
		// 람다 표현식 사용
		test1(); // List 콜렉션을 이용
		System.out.println("------------------");
		test2(); // Thread를 사용
		System.out.println("------------------");
		test3(); // FileFilter를 사용한 파일 필터링
	}

	
	private void test1() {
		List<String> list = Arrays.asList("Apple", "Banana", "Cherry");

		// 전통적인 방법으로 출력
		for (String i : list) {
			System.out.println(i);
		}
		System.out.println();
		// 위 전통방식을 foreach를 써서 람다식으로 표현
		// foreach는 Consumer 함수형 인터페이스의 인스턴스를 받아, 각 요소에 대한 작업을 정의한다.
		// Consumer : 반환 값이 없으며, 1개의 매개변수를 받아들이고 반환값이 없는 accept()를 정의한다.
		list.forEach(i -> System.out.println(i)); // consumer가 있음
	}
	
	//내부클래스----------
	class ThreadTest {
		public void sendData(String friend) {
			System.out.println(friend + "에게 문자 전송");
		}
		
		public ThreadTest() {
			// TODO Auto-generated constructor stub
		}
		
		void m1() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			}).start();
		}
		void m2() {
			Runnable runnable = () -> sendData("Brian");
			runnable.run();
		}
		void m3() {
			Thread thread = new Thread(() -> sendData("Linda"));
			thread.start();
		}
		void m4() {
			new Thread(() -> sendData("Betty")).start();
		}
	}

	//--------------
	private void test2() {
		ThreadTest threadTest = new ThreadTest();
		threadTest.m1();
		threadTest.m2();
		threadTest.m3();
		threadTest.m4();
	}
	
	private void test3() {
		// 특정 디렉토리(폴더)에 있는 파일 목록 걸러 보기
		File direc = new File("c:/work");
		
		// FileFilter 함수형 인터페이스로 람다식 처리, 확장자 txt만 필터링해보기
		File[] files = direc.listFiles((File file) ->
		file.isFile() && file.getName().endsWith(".txt")); //isFile파일을 읽고
		
		if(files != null ) {
			for(File f:files) {
				System.out.println(f.getName());
			}
		}
		
	}
	

	public static void main(String[] args) {
		new MyLambda3();
	}

}
