package pack7gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pack7gui.Ex44Event.EventTest;

public class Ex48 extends JFrame implements ActionListener {
	JTextField txtNum1, txtNum2; // 앞에 private 안쓰aus  default로 설정됨
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdoA, rdoS, rdoM, rdoD;
	JLabel lblResult;
	JButton btnC, btnD, btnE; // 여기 전역변수에 쓰는 이유는 전체 클래스에서 사용하려고
	JTextArea area = new JTextArea(" ", 10, 5);

	public Ex48() { // ex48 생성자 
		setTitle("미니 계산기");

		layoutInit();
		addLayout(); // 버튼 관련

		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layoutInit() {
		setLayout(new GridLayout(5, 1));

		// 1행
		JLabel lbl1 = new JLabel("숫자1 : ");
		txtNum1 = new JTextField("", 20);
		JPanel panel1 = new JPanel();
		panel1.add(lbl1);
		panel1.add(txtNum1);
		add(panel1); // Frame에 등록

		// 2행
		JLabel lbl2 = new JLabel("숫자2 : ");
		txtNum2 = new JTextField("", 20);
		JPanel panel2 = new JPanel();
		panel2.add(lbl2);
		panel2.add(txtNum2);
		add(panel2);

		// 3행
		rdoA = new JRadioButton("+", true);
		rdoS = new JRadioButton("-", false);
		rdoM = new JRadioButton("*", false);
		rdoD = new JRadioButton("/", false);

		buttonGroup.add(rdoA);
		buttonGroup.add(rdoS);
		buttonGroup.add(rdoM);
		buttonGroup.add(rdoD);

		JPanel panel3 = new JPanel();
		panel3.add(rdoA);
		panel3.add(rdoS);
		panel3.add(rdoM);
		panel3.add(rdoD);
		add(panel3);
		

		// 5행 : 결과보기
		lblResult = new JLabel("결과 : ", JLabel.LEFT); // 가운데 정렬 추가함
		add(lblResult);

		txtNum1.requestFocus(); // requestFocus() : 해당 객체로 cursor 이동
	}

	private void addLayout() {
		btnC = new JButton("계산");
		btnD = new JButton("초기화");
		btnE = new JButton("종료");
		btnC.addActionListener(this);
		btnD.addActionListener(this);
		btnE.addActionListener(this);

		JPanel panel7 = new JPanel();
		panel7.add(btnC);
		panel7.add(btnD);
		panel7.add(btnE);

		add("South", panel7); // Frame은 BorderLayout이므로
		// btnC.addActionListener(new Calculate());

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼 눌러서 실행
		if (txtNum1.getText().equals("")) {
			lblResult.setText("숫자1 입력!");
			txtNum1.requestFocus(); // 커서는 하나만 존재해.
			return;
		}

		if (txtNum2.getText().equals("")) {
			lblResult.setText("숫자2 입력!");
			txtNum2.requestFocus(); // 이걸 안쓰면 커서의 위치가 넘어가지 않음
			return;
		}
		// 나이는 아라비아 숫자형태여야 한다.그래서 숫자형태인지 확인방법
		int n1, n2, calc = 0;
		if (rdoA.isSelected()) {
			n1 = Integer.parseInt(txtNum1.getText()); // 문자를 정수로 교환하는 거임 : Integer.parseInt
			n2 = Integer.parseInt(txtNum2.getText());
			calc = n1 + n2;
		} else if (rdoS.isSelected()) {
			n1 = Integer.parseInt(txtNum1.getText()); // 문자를 정수로 교환하는 거임 : Integer.parseInt
			n2 = Integer.parseInt(txtNum2.getText());
			calc = n1 - n2;
		} else if (rdoM.isSelected()) {
			n1 = Integer.parseInt(txtNum1.getText()); // 문자를 정수로 교환하는 거임 : Integer.parseInt
			n2 = Integer.parseInt(txtNum2.getText());
			calc = n1 * n2;
		} else if (rdoD.isSelected()) {
			n1 = Integer.parseInt(txtNum1.getText()); // 문자를 정수로 교환하는 거임 : Integer.parseInt
			n2 = Integer.parseInt(txtNum2.getText());
			calc = n1 / n2;
		}

		try {
			n1 = Integer.parseInt(txtNum1.getText()); // 문자를 정수로 교환하는 거임 : Integer.parseInt
			n2 = Integer.parseInt(txtNum2.getText());
		} catch (Exception e2) {
			lblResult.setText("숫자만 가능");
			txtNum1.requestFocus();
			return;
		}

		if (e.getSource() == btnE) { // 실행하면 위에 작업이 끝나야지 종료됨. 위에 Ex48() 생성자에게 쓰면 더 좋아
			int result = JOptionPane.showConfirmDialog(this, 
					"정말 종료할까요?", "종료", JOptionPane.YES_NO_OPTION);

			switch (result) { // 프레임에 결과창을 보이게 하는 법
			case JOptionPane.YES_OPTION:
				area.append("예 선택\n");
				System.exit(0); // 각각 break를 걸어줘야됨
			case JOptionPane.NO_OPTION:
				area.append("아니오 선택\n");
				break;
			}
		} else if(e.getSource() == btnD) { // 초기화
			txtNum1.setText("");
			txtNum2.setText("");
			rdoA.setSelected(true);
			rdoS.setSelected(true);
			rdoM.setSelected(true);
			rdoD.setSelected(true);
			txtNum1.requestFocus();
			lblResult.setText("결과: ");
		}
		

		String message = "결과:" + n1 + " , " + n2 + ", 계산은 " + calc;
		lblResult.setText(message);

	}

	public static void main(String[] args) {
		new Ex48();

	}

}
