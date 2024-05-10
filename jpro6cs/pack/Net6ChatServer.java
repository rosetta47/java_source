package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 멀티 채팅 서버 : Thread + Socket 사용해서 
public class Net6ChatServer {
	private static final int PORT = 5000;
//	private static List<Socket> clients = new ArrayList<Socket>(); // 스레드를 사용하려면 아래꺼가 더 좋아
	// CopyOnWriteArrayList : 컨텐츠를 읽어 어딘가에 전달할 때 컨텐츠를 복사해서 전달함. 스레드에서 안심하고 처리가 가능함.
	private static List<Socket> clients = new CopyOnWriteArrayList<Socket>();

	// ExecutorService를 이용하면 Thread pool를 생성해 병렬처리를 할 수 있다.
	private static ExecutorService pool = Executors.newFixedThreadPool(4);// pool의 크기를 주는 거임 여기선 4개를 줌

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("채팅 서버 서비스 시작 ...");

		try {
			while (true) { //무한루프
				Socket clientSocket = serverSocket.accept();
				System.out.println("새 접속자와 연결:" + clientSocket.getInetAddress());
				clients.add(clientSocket); // arraylist 등록함
				
				//스레드 풀 객체가 스레드를 실행
				pool.execute(new ClientHandler(clientSocket));

			}
		} finally {
			serverSocket.close();
		}
	}

	static class ClientHandler implements Runnable { // 내부클래스
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		
		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
		}

		@Override
		public void run() {
			try {// 접속자 이름을 먼저 받고 
				String name = in.readLine(); // 접속자명 받기
				if(name == null) {
					throw new IOException("클라이언트 연결이 끊어졌습니다");
				}
				System.out.println(name + "님이 접속했습니다");
				broadcastMesssage("접속^^;" + "님이 입장 ^^");
				// 메세지를 수신
				String message;
				while((message = in.readLine()) != null) { // 메세지를 수신
					broadcastMesssage(name + "님 메세지:" + message);
				}
				
			} catch (Exception e) {
				System.out.println("접속자 연결 오류 : " + e.getMessage());
			} finally {
				try {
					if(socket != null) {
						socket.close();
						clients.remove(socket); //ArrayList<Socket>있던 소켓 제거
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

		}
		
		private void broadcastMesssage(String message) {
			for(Socket client:clients) { // 소켓이 있는 접속자명, 메세지 나옴
				try {
					if(!client.isClosed()) {// !client.isClosed() = client가 안닫혔다(!)
					// 클라이언트로 송신할 데이터를 위한 PrintWriter를 생성해 송신 후 자동으로 flush()함= true의 의미
						PrintWriter clientOut = new PrintWriter(client.getOutputStream(), true);
						clientOut.println(message);
					}
				} catch (Exception e) {
					System.out.println("broadcastMesssage err : " + e.getMessage());
				}
			}
		}
	}
}
