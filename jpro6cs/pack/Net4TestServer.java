package pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Net4TestServer {

	public static void main(String[] args) {
		// 단순 서버(1회용)
		ServerSocket ss = null;
		
		// 내 컴퓨터가 사용 중인 port number 확인
	/*	for (int i = 0; i < 65536; i++) {
			try {
				ss = new ServerSocket(i);
				ss.close();
			} catch (Exception e) {
				System.out.println(i + "번 port는 사용 중");
			}
		}
		System.out.println("확인 종료"); */
		
		Socket socket = null; // TCP (전화기로 생각해) 기반의 통신용 클래스(파일)
		try {
			ss = new ServerSocket(9999); // 서버 소켓
			System.out.println("server start...");
			socket = ss.accept(); 
			// 서버 소켓으로 부터 클라이언트과 컴과 통신하기 위한 개별 소켓을 만듬(accept)
			// 무한루프에 빠짐 -> listen 이 있음 => 클라이언트가 들어와야지 나옴
			
			BufferedReader reader = new BufferedReader( // 클라이언트가 정보를 주면 받고 땡할꺼임
					new InputStreamReader(socket.getInputStream()));
			String data = reader.readLine(); // 메세지만 일방적으로 받음
			System.out.println("receive data : "  + data);
			
			reader.close();
			socket.close();
			ss.close();
		} catch (Exception e) {
			System.out.println("server err:" + e);
		}
	}

}
