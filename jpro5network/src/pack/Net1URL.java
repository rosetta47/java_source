package pack;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.print.DocFlavor.INPUT_STREAM;

// URL 클래스로 특정 웹 서버 컴의 문서 읽기
// 인터넷이 가능한 서버들의 자원에 접근하여 주소 및 기타 정보를 다루는 클래스
public class Net1URL {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://www.daum.net");
			// "https://www.daum.net:80/index.html 거의 도메인을 이렇게 되어있음
			// http 보안용 서버 ==> https. tcp 프로토콜 기반으로 응용프로그램 계층에서 사용
			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		//	System.out.println(br.read());
			// 읽은 문서 파일로 저장
			PrintWriter pw = new PrintWriter(System.out, true);
			PrintWriter fw = new PrintWriter(new FileOutputStream("c:/work/ok.html"));
			
			String line = "";
			while((line =br.readLine()) != null) {
				pw.println(line); //console로 출력
				fw.println(line); // file로 저장
				fw.flush();// buffer를 비우려고 씀
			}
			br.close();
			is.close();
			pw.close();
			fw.close();
			
		} catch (Exception e) {
			System.out.println("err :" + e);
		}

	}

}
