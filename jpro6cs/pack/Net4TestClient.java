package pack;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Net4TestClient {

	public static void main(String[] args) {
		try {
//	InetAddress ia = InetAddress.getByName("127.0.0.1");
//		System.out.println(ia);// 결과값 : /127.0.0.1
//	Socket socket = new Socket(ia, 9999); // 클라이언트에서 9999찾아감
			
			Socket socket = new Socket("192.168.0.25", 9999); // 서버와 접속(Net4TestServer)
			
			PrintWriter writer = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), 
							StandardCharsets.UTF_8), true);
			//PrintWriter:데이터 출력
			writer.println("안녕 난 켈리야" + "\n"); // 서버로 자료 전송
			
			writer.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("client err : " + e);
		}
		// 특정 컴의 접속 후 메세지 전달용
		

	}

}
