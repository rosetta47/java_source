package pack3;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// 어댑터 클래스를 이용한 이벤트 처리 연습
// WindowAdapter 추상 클래스는 windowListener 인터페이스를 구현한 클래스임
// 그래서 WindowListener의 추상 메소드가 WindowAdapter에서 일반 메소드로 오버라이드(재정의)됨
public class MyFrame4adapter extends WindowAdapter { //상속
	private Frame frame;
	
	public MyFrame4adapter() {
		frame = new Frame("Adapter 연습"); //포함
		
		frame.setSize(300, 250);
		frame.setLocation(200, 250);
		frame.setVisible(true);

		frame.addWindowListener(this); 
	}// this를 쓰는 이유 : windowadapter를 myframe에서 상속받고 있으니까 내자신을 부르면 되.
	
	@Override
		public void windowClosing(WindowEvent e) {
		// 선택적으로 필요한 메소드를 오버라이딩할 수 있다.
			System.exit(0);
		}
	
	public static void main(String[] args) {
		new MyFrame4adapter();

	}

}
