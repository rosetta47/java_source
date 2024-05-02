package pack7gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ex51Gui extends JFrame implements ActionListener, KeyListener {
	Image image;

	private JTextField name, kor, eng, math;
	int num1, num2, num3;
	private JLabel lblSum,lblAvg, lblJumsu;
	private JButton btnCheck, btnReset;

	public Ex51Gui() {
		super("성적 출력");

		calcLayout();

		setBounds(200, 200, 300, 400);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addKeyListener(this); // 이미지 관련 리스너
		
	}

	private void calcLayout() {
		setLayout(new GridLayout(5, 3));

		// 1행 이름
		JLabel lbl1 = new JLabel("이름: ");
		name = new JTextField("", 20); // 전체적으로 쓰면 위에 전역변수 설정하고 이런식
		JPanel panel1 = new JPanel();
		panel1.add(lbl1);
		panel1.add(name);
		add(panel1);

		// 2행 :국어
		JLabel lbl2 = new JLabel("국어 : ");
		kor = new JTextField("", 20);
		JPanel panel2 = new JPanel();
		panel2.add(lbl2);
		panel2.add(kor);
		add(panel2);

		// 3행 : 영어
		JLabel lbl3 = new JLabel("영어 : ");
		eng = new JTextField("", 20);
		JPanel panel3 = new JPanel();
		panel3.add(lbl3);
		panel3.add(eng);
		add(panel3);

		// 4행 : 수학
		JLabel lbl4 = new JLabel("수학 : ");
		math = new JTextField("", 20);
		JPanel panel4 = new JPanel();
		panel4.add(lbl4);
		panel4.add(math);
		add(panel4);

		// 총점
		
//		lblResult = new JLabel("결과: ", JLabel.CENTER);
//		add(lblResult);

		lblSum = new JLabel("총점: ", JLabel.CENTER);
		add(lblSum);
		
		lblAvg = new JLabel("평균: ", JLabel.CENTER);
		add(lblAvg);
		
		lblJumsu = new JLabel("평가: ", JLabel.CENTER);
		add(lblJumsu);
		
		
		//  (버튼관련)
		btnCheck = new JButton("확인");
		btnReset = new JButton("초기화");
		btnCheck.addActionListener(this);
		btnReset.addActionListener(this);

		JPanel panel5 = new JPanel();
		panel5.add(btnCheck);
		panel5.add(btnReset);
		add(panel5);
		
		// 이미지 관련 
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel lblResult = new JLabel();
		
		if(name.getText().equals("")) {
			lblResult.setText("이름써");
			name.requestFocus(); // 커서는 하나만 존재해.
			return;
		} // 국어
		if (kor.getText().equals("")) {
			lblResult.setText("숫자1 입력해");
			kor.requestFocus();
			return;
		}
		try {
			num1 = Integer.parseInt(kor.getText());
		} catch (Exception e2) {
			lblResult.setText("국어 점수 입력!!");
			kor.requestFocus();
			return;
		} //영어
		if (eng.getText().equals("")) {
			lblResult.setText("숫자1 입력해");
			eng.requestFocus();
			return;
		}
		try {
			num2 = Integer.parseInt(kor.getText());
		} catch (Exception e2) {
			lblResult.setText("국어 점수 입력!!");
			eng.requestFocus();
			return;
		} // 수학
		if (math.getText().equals("")) {
			lblResult.setText("숫자1 입력해");
			math.requestFocus();
			return;
		}
		try {
			num3 = Integer.parseInt(kor.getText());
		} catch (Exception e2) {
			lblResult.setText("국어 점수 입력!!");
			math.requestFocus();
			return;
		}
		


	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new Ex51Gui();

	}

	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) { }
}
