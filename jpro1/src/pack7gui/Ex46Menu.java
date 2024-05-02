package pack7gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ex46Menu extends JFrame implements ActionListener{
	JButton btnR, btnG, btnB;
	JTextArea txtArea = new JTextArea(" ", 10, 5); // 키보드로 여러 행 입력 가능함
	JMenuBar mBar;
	JMenuItem mnuMes, mnuOk, mnuInput;
	
	public Ex46Menu() {
		setTitle("연습");
		
		addLayout(); //버튼 관련
		addMenuLayout(); // 메뉴 관련
		
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addLayout() {
		btnR = new JButton("빨강");
		btnG = new JButton("초록");
		btnB = new JButton("파랑");
		btnR.addActionListener(this);
		btnG.addActionListener(this);
		btnB.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(btnR);
		panel.add(btnG);
		panel.add(btnB);
		
		add("South", panel); // Frame은 BorderLayout이므로
		add("Center", txtArea);
	}
	
	private void addMenuLayout() {
		mBar = new JMenuBar();
		
		JMenu menu = new JMenu("대화상자");
		mnuMes = new JMenuItem("메시지 창");
		mnuOk = new JMenuItem("확인 창");
		mnuInput = new JMenuItem("입력 창");
		menu.add(mnuMes); // 메뉴에 메뉴 아이템 등록하는 법 add 사용
		menu.add(mnuOk);
		menu.add(mnuInput);
		
		JMenu menu2 = new JMenu("난 메뉴야");
		
		mBar.add(menu); // 메뉴바에 메뉴를 등록 add 사용
		mBar.add(menu2); // 메뉴바에 메뉴2를 등록
		
		setJMenuBar(mBar); // J프레임에 넣어야지 화면에 나와 꼭 써야되(메뉴바 등록)
		
		
		// 메뉴에 리스너를 장착해 -> actionPerformed(ActionEvent e)로 연결
		mnuMes.addActionListener(this);
		mnuOk.addActionListener(this);
		mnuInput.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) { // 버튼 누르면 실행(actionPerformed)
	//	System.out.println(e.getActionCommand());
		if(e.getSource() == btnR) { // 배경색 바꿈 if조건을 걸어서 
			txtArea.setBackground(Color.RED);
		} else if(e.getSource() == btnG) {
			txtArea.setBackground(new Color(0, 255, 0));
		}else if(e.getSource() == btnB) {
			txtArea.setBackground(new Color(0, 0, 255));
		}else if(e.getSource() == mnuMes) { // 메뉴
			// 내장된 다이얼로그 박스 호출하기 
			JOptionPane.showMessageDialog(this,
					"메세지", "알림", JOptionPane.INFORMATION_MESSAGE); // 일반 사용자에게 보여줄때
			// this = 현재 창에 띄움. 자동으로 [확인]버튼 생김
			System.out.println("계속"); // 위에 창이 닫혀야지 진행되서 콘솔창에 "계속"이 입력됨 (Modal dialogbox) 
		}else if(e.getSource() == mnuOk) { // 메뉴 , 리턴값이 필요해서 result를 만듬
		int result = JOptionPane.showConfirmDialog(this, 
					"버튼을 고르시오", "골라", JOptionPane.YES_NO_CANCEL_OPTION);
			System.out.println(result); //.showConfirmDialog는 리턴값이 필요함. 콘솔창에 0,1,2 나옴
	
			//	if(result ==0 ...) // if도 쓸수있지만 switch가 깔끔해서 씀
			switch (result) { // 프레임에 결과창을 보이게 하는 법
			case  JOptionPane.YES_OPTION: 
				txtArea.append("예 선택\n");
				break; // 각각 break를 걸어줘야됨
			case  JOptionPane.NO_OPTION:
				txtArea.append("아니오 선택\n");
				break;
			case  JOptionPane.CANCEL_OPTION:
				txtArea.append("취소 선택\n");	
				break;
			}
	
		}else if(e.getSource() == mnuInput) { // 메뉴
			String str = JOptionPane.showInputDialog(this, "자료 입력");
			txtArea.append(str + "\n"); // dialog는 그 창이 닫혀야지 뒤에 창이 실행
		}
	}

	public static void main(String[] args) {
		// 메뉴 작성, 메시지 박스 ...
		new Ex46Menu();
	}

}
