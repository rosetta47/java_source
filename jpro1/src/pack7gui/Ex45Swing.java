package pack7gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Swing
/*
AWT 기술을 기반으로 순수 자바 언어로 만들어진 라이브러리
모든 AWT 기능 + 추가된 풍부하고 화려한 고급 컴포넌트
AWT 컴포넌트에 J자가 덧붙여진 이름의 클래스
그 외 J 자로 시작하는 클래스
javax.swing 패키지
Swing 컴포넌트는 경량 컴포넌트(Light weight components)
native(peer) 운영체제에 의존하지 않음 */

public class Ex45Swing extends JFrame implements ActionListener {
	JLabel lblshow;
	int count = 0;
	
	
	public Ex45Swing() {
		setTitle("스윙 연습");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); 
		// top, left, bottom, right 여백을 줌(setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)
		
		
		
		JButton button = new JButton("클릭(C)");
		button.setMnemonic(KeyEvent.VK_C);
	// 단축키 숏컷키 = setMnemonic (스윙에만 있음) : alt+c눌러도 먹힘
		button.addActionListener(this);
		panel.add(button);
		
		lblshow = new JLabel("버튼 클릭 수 : 0");
		panel.add(lblshow);
		
		add(panel, BorderLayout.CENTER);
		// add("Center", panel); : 위랑 결과가 동일함
		
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		// addWindowListener(new WindowAdapter() ....);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 원도우 종료를 아래와 같이 적을 수 있다.
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		count += 1; // count 하나씩 늘어나게 함
		lblshow.setText("버튼 클릭 수 : " + count); // setText
		
		
		
	}
	
	public static void main(String[] args) {
		new Ex45Swing();

	}

}
