package pack7gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

// JDialog : 맞춤형 대화 상자 작성. 커스터마이징이 가능하다.
// Frame에서 자식창으로 호출 가능
public class Ex49MemoAbout extends JDialog implements ActionListener{
	public Ex49MemoAbout(JFrame frame) {
		super(frame);
	//	super(frame, "메모장이란", true); // 이렇게 modal 설정할 수 있어
		
		setTitle("메모장이란");
		setModal(true); // 모달임 true
		//setModal(false); //modaless
		
		initLayoutAbout();
		
		setBackground(Color.LIGHT_GRAY);
		setBounds(350, 350, 200, 200);
		setVisible(true);
		
	}
	private void initLayoutAbout() {
		JLabel lblMes = new JLabel("미니메모장 ver 0.9");
		JButton btnConfirm = new JButton("확인");
		btnConfirm.addActionListener(this);
		add("Center", lblMes);
		add("South", btnConfirm);
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose(); // Jdialog 닫기
		
	}
}
