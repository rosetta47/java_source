package pack;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

// 제3삼자가 제공하는 라이브러리 이용 = jsoup.jar를 사용해 웹 스크래핑
// HTML 문서 데이터 구문 분석, 자료 추출 및 조작용인 오픈 소스
public class Net2 {

	public static void main(String[] args) {
		// 위키백과 사이트에서 검색 결과를 읽어오기
		//https://ko.wikipedia.org/wiki/%EB%B0%B1%EC%84%A4_%EA%B3%B5%EC%A3%BC

		try {
		//	System.out.println(URLEncoder.encode("백설공주","UTF-8")); 
			//위에꺼 결과값 : %EB%B0%B1%EC%84%A4%EA%B3%B5%EC%A3%BC = 백설공주
			
			String url = "https://ko.wikipedia.org/wiki/"+URLEncoder.encode("백설공주","UTF-8");
			//Document : 웹 페이지 문서 자체를 의미함.
			Document doc = Jsoup.connect(url).get();
			String text = doc.text();// 웹문서의 테스트를 모두 읽음
		//	System.out.println(text);
			
			printKoreanText(text); // 불러온 자료에서 한글만 추출하려고 함
		} catch (Exception e) {
			System.out.println("err : "+e.getMessage());
		}
	}

	
	// 순수하게 한글만 추출하기
	public static void printKoreanText(String text) {
		// 정규 표현식 사용(프로그래밍에서 문자열을 다룰 때 문자열의 일정한 패턴을 말한다.)
		// 한글과 공백만 얻기 정규 표현식을 사용해서
		Pattern pattern = Pattern.compile("[가-힣\\s]+");
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			String line = matcher.group().trim(); // .trim(): 앞뒤의 공백을 자름
			if(!line.isEmpty()) { //빈 줄은 제외 = !line 표현
				System.out.println(line);
				
			}
		}
	}
}
