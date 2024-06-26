package pack7gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
1) AWT(Abstract Window Toolkit)란?
- GUI프로그래밍(윈도우 프로그래밍)을 위한 도구
- Java로 구현하지 않고 OS의 컴포넌트를 그대로 사용(OS 종속적)

2) Swing
- AWT를 확장한 GUI프로그래밍 도구
- AWT보다 더 많은 종류의 컴포넌트 제공
- OS의 컴포넌트를 사용하지 않고 Java로 구현

3) AWT의 구성
- AWT관련 패키지는 모두 java.awt로 시작
- AWT컴포넌트의 최상위 조상은 java.awt.Component 클래스(메뉴 관련 컴포넌트 제외)

4) Container
- 다른 컴포넌트를 포함할 수 있는 컴포넌트
- 독립적인 컨테이너: 독립 사용 가능, 다른 컴포넌트나 종속적 컨테이너를 포함 가능
- 종속적인 컨테이너: 독립 사용 불가(다른 컨테이너에 포함)
- add()를 사용해서 추가
- 컨테이너에 담기는 컴포넌트는 컨테이너의 설정을 따름(변경 가능) */

// https://m.blog.naver.com/so_yuns/222172786073 : layoutmanager 참조
// Frame : BorderLayout 기본레이어
// Panel : FlowLayout이 기본

public class Ex43FrameLayout extends Frame implements ActionListener { // Frame도 컨테이너임 (가장 큰 그릇임)
	Panel pn1 = new Panel(); // 컨테이너(visual object을 담는 그릇)의 일종
	Panel pn2 = new Panel();
	Panel pn3 = new Panel();
	Panel pn4 = new Panel();
	Panel pn5 = new Panel();
	Panel pn6 = new Panel();
	
	TextField txtBun, txtIrum; // 멤버변수
	Button btnGo;
	CardLayout card = new CardLayout(); 
	
	public Ex43FrameLayout() {
		// 배치 관리자로 화면 디자인 연습
		setLayout(new GridLayout(2, 1)); // Frame의 layout을 변경 (2행 1열)
		
		// 첫번째 행 디자인 
		Label lbl1 = new Label("bunho:"); //메세지 컴포넌트
		txtBun = new TextField("10", 20 ); // 키보드로 자료 입력 가능함
		pn1.add(lbl1); // panel에 label 객체를 담음
		pn1.add(txtBun); //panel에 textfield 객체 담기
		pn1.setBackground(Color.YELLOW); // panel 배경색 변경
	//	super.add(pn1); // Frame에 panel을 담음 이걸 주석처리하면 그냥 프레임만 보임 우리가 설정한 pn1이 안보임
		
		Label lbl2 = new Label("irum:"); //메세지 컴포넌트
		txtIrum = new TextField("이기자", 30 ); // 키보드로 자료 입력 가능함
		pn2.add(lbl2); // panel에 label 객체를 담음
		pn2.add(txtIrum); //panel에 textIrum객체 담기
		pn2.setBackground(Color.CYAN); 
		
		pn3.setLayout(card);   // flowlayout(기본)를 cardlayout으로 바꿈 = pn3
		pn3.add("first", pn1); // pn3에 "first"라는 이름으로 pn1을 담음
		pn3.add("second", pn2);
		btnGo = new Button("ok");
		btnGo.addActionListener(this); // 버튼 이벤트를 발생하는 것을 인식하기 위해서 씀
		
		pn4.add(pn3); // pn4를 flowlayout
		pn4.add(btnGo);
		
		super.add(pn4); // frame 에 pn4를 담기
		
		// 두번째 행 디자인 
		pn5.setLayout(new BorderLayout()); //  flowlayout(기본)를 BorderLayout으로 바꿈
		pn5.setBackground(new Color(123, 123, 123));
		pn5.add("East", new Label("kbs"));
		pn5.add("West", new Label("mbc"));
		pn5.add("South", new Label("cbs"));
		pn5.add("North", new Label("ytn"));
		pn5.add("Center", new Label("tvn"));
		
		super.add(pn5);
		
		super.setTitle("레이아웃 연습");
		super.setBounds(200,200,400,300);
		super.setVisible(true);
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}
	
	@Override
		public void actionPerformed(ActionEvent e) {
			// 버튼 이벤트 처리 메소드
			// 버튼을 클릭하면 pn1, pn2를 교체를 할꺼임
			// System.out.println("good");
			// System.out.println(e.getActionCommand());
		if(e.getActionCommand().equalsIgnoreCase("ok")) {
			btnGo.setLabel("go");
			card.show(pn3, "second");  //pn3에 담긴 pn2을 보여줌
		} else {
			btnGo.setLabel("ok");
			card.show(pn3, "first"); //pn3에 담긴 pn1을 보여줌
		}
	}
	
	
	public static void main(String[] args) {
		new Ex43FrameLayout();

	}

}
