package pack;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

// 멀티 스레드로 멀티 테스킹 : 복수 개의 문서 읽기
public class Net3Thread implements Runnable{
	private String url;
	private String title;
	
	public Net3Thread(String url, String title) {
		this.url = url;
		this.title = title;
	}
	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(url).get(); // 네트워크를 통해 다른 컴에서 접속 후 자료 읽기
			String text = doc.text();
			
			System.out.println("-------------");
			System.out.println("문서 제목 : " + title);
			
			printKoreanText(text);
		} catch (Exception e) {
			System.out.println("read err : " + e);
		}
		
	}
	
	public static void printKoreanText(String text) {
		// 정규 표현식 사용(프로그래밍에서 문자열을 다룰 때 문자열의 일정한 패턴을 말한다.)
		// 한글과 공백만 얻기 정규 표현식을 사용해서
		Pattern pattern = Pattern.compile("[가-힣\\s]+");
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			String line = matcher.group().trim(); // .trim(): 앞뒤의 공백을 자름
			if(!line.isEmpty() && line.length() >1) { 
				//빈 줄은 제외 = !line 표현 , 1글자 제외 = line.length() >1
				System.out.println(line);
				
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		String[] titles = {
			"백설공주"	,
			"인어공주"	
		};
		String[] urls = {"https://ko.wikipedia.org/wiki/"
		+URLEncoder.encode(titles[0],"UTF-8"),"https://ko.wikipedia.org/wiki/"
				+URLEncoder.encode(titles[1],"UTF-8")
		};
		for (int i = 0; i < urls.length; i++) {
			Thread thread = new Thread(new Net3Thread(urls[i], titles[i]));
			thread.start();
		}
		System.out.println("프로그램 종료");
	}
	
}
