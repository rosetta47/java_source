package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Thread와 Socket를 사용해 간단한 웹 서버 작성

public class Net7SimpleHttpServer {
	private ServerSocket serverSocket;
	private final int PORT;
	
	public Net7SimpleHttpServer(int port) {
		this.PORT = port;
	}
	
	public void gogo() throws IOException {
		serverSocket = new ServerSocket(PORT); // 8080의 서버소켓을 만듬
		System.out.println("HTML Server started on post : " + PORT);
		
		while(true) {
			try {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Received request from " + 
				clientSocket.getRemoteSocketAddress());
				
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				new Thread(clientHandler).start(); // 웹 프로그램은 스레드 많이 써
			} catch (Exception e) {
				System.out.println("gogo err : " + e.getMessage());
				break; // 무한루프에서 빠져나옴
			}
		}
	}
	
	// 내부 클래스
	private static class ClientHandler implements Runnable {
		private Socket clientSocket;
		
		public ClientHandler(Socket socket) {
			clientSocket = socket;
		}
		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				
				String requestLine = in.readLine();
				System.out.println("Request :" + requestLine);
				
				// HTTP에 요청에 대한 응답 전송
				out.println("HTTP/1.1 200 OK");
				out.println("Content-Type:text/html;charset=UTF-8");
				out.println("");
				out.println("<html><head><title>연습</title></head>");
				out.println("<body>");
				
				out.println("<h1>홈 페이지</h1>");
				out.println("<a href='https://www.daum.net'>다음으로</a>출발<br>");//<br>은 라인스킵
				out.println("<a href='https://www.naver.com'>네이버로</a>출발<br>");
				out.println("</body>");
				out.println("</html>");
				
				out.close();
				in.close();
				clientSocket.close();
				
			} catch (Exception e) {
				System.out.println("error client request :" + e.getMessage());
			}
			
		}
	}
	
	public static void main(String[] args) {
		int port = 8080; // Default port : 연습용 포트 넘머 8080
		
		try {
			Net7SimpleHttpServer httpServer = new Net7SimpleHttpServer(port);
			httpServer.gogo();
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
		

	}

}
