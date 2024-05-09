package pack;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Net4TestClient {

	public static void main(String[] args) {
		try {
		//	InetAddress ia = InetAddress.getByName("127.0.0.1");
	//		System.out.println(ia);// 결과값 : /127.0.0.1
		//	Socket socket = new Socket(ia, 9999); // 클라이언트에서 9999찾아감
			
			Socket socket = new Socket("127.0.0.1", 9999); // 서버와 접속(Net4TestServer)
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println("Hi I'am kelly" + "\n"); // 서버로 자료 전송
			
			writer.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("client err : " + e);
		}
		// 특정 컴의 접속 후 메세지 전달용
		

	}

}
