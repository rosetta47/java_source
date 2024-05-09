package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// stream 인터페이스 : 컬렉션, 배열 등의 저장 요소를 하나씩 참조하여 
// 인터페이스(람다식)를 적용하며, 반복 처리가 가능하게 한다. 반복자 역할
// 정렬, 집계, 빅데이터 처리 등도 가능하다.
// 1회용. 내부 반복으로 작업 처리. 원본 데이터를 변경하지 않음

public class MyStream {

	public MyStream() {
		test1(); // Stream 생성
		test2(); // 컬렉션에 스트림 적용
		test3(); // VO 클래스 사용
	}
	
	private void test1() {
		// 1) Collection의 스크림 생성
		List<String> list = Arrays.asList("a", "b","c");
		Stream<String> listStream = list.stream();
		
		// 2) 배열의 스크림 생성
		Stream<String> stream1 = Stream.of("a", "b","c");
		Stream<String> stream2 = Stream.of(new String[]{"a", "b","c"});
		Stream<String> stream3 = Arrays.stream(new String[]{"a", "b","c"});
		Stream<String> stream4 = Arrays.stream(new String[]{"a", "b","c"}, 0, 3);//0이상 3이하
	
		// 3) 원시 데이터가지고(기본형데이터) 스크림 생성
		IntStream istream = IntStream.range(5, 10);// 5이상 10미만을 갖는 스트림이 만들어짐
	//	DoubleStream ...
		istream.forEach(para ->System.out.println(para + ""));
		
		System.out.println();
	}
	
	private void test2() {
		List<String> list = Arrays.asList("레밍스", "팩맨", "마리오");
	//	list.add("소닉"); //err : Exception in thread "main" java.lang.UnsupportedOperationException
		// 위에 오류이유는 새로운 요소 추가x, 기존 요소 삭제x 때문에
		Iterator<String> iter = list.iterator(); // 외부 반복자 사용, 전통적
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		System.out.println();
		for(String str:list) { // 향상된 for문 사용, 전통적
			System.out.println(str);
		}
		for(String str:list) { // 향상된 for문 사용, 전통적
			System.out.println(str);
		}
		
		
		System.out.println();
		Stream<String> stream = list.stream();//스트림 객체를 얻음 컬렉션으로 부터
		stream.forEach(str -> System.out.println(str));// 내부 반복으로 작업 처리
		//stream.forEach(str -> System.out.println(str)); // 1회용이라서 err : 위에서 사용함
		
		
		list.stream().forEach(str -> System.out.println(str)); // 스트림 객체를 다시 만들고 하면 실행됨
		list.stream().forEach(System.out::println); //위에 말과 같은 말	(::) 스트림을 쓰고 있구나
	
		System.out.println();
		// 스트림을 사용하여 체이닝 작업 : 모든 필요한 작업으 단일 스트림 파이프라인(일련의 처리 단계)에서 처리 가능
		// 어떤 스트림의 요소들의 합을 구하는 과정에서 요소 값을 먼저 출력하고 싶은 경우 사용
		int sum = IntStream.of(1,3,5,7).peek(System.out::println).sum();// sum자리에 count, max..등 다 쓸수 있어
		System.out.println("합은" + sum);
		
		list.stream().peek(System.out::println).forEach(System.out::println); // 내부 반복이 일어남
		// 결과값이 레밍스레밍스팩맨팩맨마리오마리오 
		
		System.out.println("\n병렬 처리");
		Stream<String> streamPar = list.parallelStream(); //.parallelStream();병렬스트림 객체 생성
		streamPar.forEach(str -> System.out.println(str)); // 순서는 랜덤하게 나옴
	
		System.out.println("\n정렬 처리");
		Stream<String> streamSort = list.stream().sorted(); // ascending오름차순 순으로 나옴
		Stream<String> streamSort2 = list.stream().sorted(Comparator.reverseOrder()); 
		streamSort.forEach(System.out::println); // 순서 정렬 : 레밍스 마리오 팩맨
		streamSort2.forEach(System.out::println); 
		
		Stream<String> streamSort3 = list.stream().distinct().sorted(); // 중복제외 distinct
		
	}
	
	private void test3() {
		System.out.println("\n\nVO 클래스로 컬렉션 작성");
		List<Student> slist = Arrays.asList(
				new Student("레밍스", "남", 22), 
				new Student("팩맨", "남", 25),
				new Student("마리오", "남", 28),
				new Student("피치", "여", 20),
				new Student("마라라", "여", 22));
		
		Stream<Student> stream = slist.stream();
		stream.forEach(p -> {
			System.out.println(p.getName()+" "+p.getGender()+" "+p.getAge());
		});
		
		System.out.println("컬렉션 자료에 대한 중간 처리, 최종 처리가 가능함");
		// 각 개인의 나이를 출력하고, 최종 나이의 평균 출력
		// 방법1 
		double avg = slist.stream() // stream 객체 생성
				.mapToInt(Student :: getAge) // student 객체를 age 값으로 매핑해 age로 새 스트림 생성함.
				.average() // 내부적으로 age 요소의 평균값을 OptionalDouble에 저장. 값이 없을 수도 있다는 가정하고 함.
				.getAsDouble(); // OptionalDouble에 저장
		System.out.println("나이 전체 평균은" + avg);
		
		// 방법2
		Double avg2 = slist.stream().collect(Collectors.averagingInt(Student :: getAge));
		System.out.println("나이 전체 평균 : " + avg2);
		
		// 방법3
		OptionalDouble result = slist.stream().mapToDouble(Student :: getAge).average();
		result.ifPresent(res -> System.out.println("age 전체 평균" + res));
		
		// 필터 처리 
		// 남자 나이 평균
		double avgM = slist.stream().filter(m -> m.getGender().equals("남"))
				.mapToInt(Student :: getAge)
				.average().getAsDouble(); // 옵션에 더블값을 읽은거임
		
		System.out.println("남자 나이 평균" + avgM);
		
		// '마'성을 가진 사람의 자료 출력
		slist.stream().filter(ir -> ir.getName().startsWith("마")).
		forEach(irum -> System.out.println(irum.getName()));
		
	}
	
	// 내부 클래스
	class Student{
		private String name;
		private String gender;
		private int age;
		
		public Student(String name, String gender, int age) {
			this.name=name;
			this.gender=gender;
			this.age=age;
		}
		
		
		public String getName() {
			return name;
		}
		public String getGender() {
			return gender;
		}
		public int getAge() {
			return age;
		}
	}
	
	public static void main(String[] args) {
		new MyStream();

	}

}
