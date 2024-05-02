package pack7gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ex47 extends JFrame implements ActionListener{
	JTextField txtName, txtAge;
	ButtonGroup buttonGroup = new ButtonGroup(); // 그룹1, 그룹2 .. 각 그룹화
	JRadioButton rdoM, rdoF;
	JLabel lblResult; // 멤버필드로 설정함
	
	public Ex47() {
		super("이벤트 연습");
		
		layoutInit();
		
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layoutInit() {
		setLayout(new GridLayout(5, 1));
		
		// 1행
		JLabel lbl1 = new JLabel("이름 : ");
		txtName = new JTextField("", 20);
		JPanel panel1 = new JPanel();
		panel1.add(lbl1);
		panel1.add(txtName);
		add(panel1); // Frame에 등록
		
		// 2행
		JLabel lbl2 = new JLabel("나이 : ");
		txtAge = new JTextField("", 20);
		JPanel panel2 = new JPanel();
		panel2.add(lbl2);
		panel2.add(txtAge);
		add(panel2);
		
		// 3행
		rdoM = new JRadioButton("남자", true); //RadioButton 꼭하나 선택해야되 그리고 두개는 선택안됨.
		rdoF = new JRadioButton("여자", false);
		buttonGroup.add(rdoM); // JRadioButton은 그룹화
		buttonGroup.add(rdoF);
		JPanel panel3 = new JPanel();
		panel3.add(rdoM);
		panel3.add(rdoF);
		add(panel3);
		
		// 4행
		JButton btnOk = new JButton("확인");
		btnOk.addActionListener(this);
		JPanel panel4 = new JPanel();
		panel4.add(btnOk);
		add(panel4);
		
		// 5행 : 결과보기
		lblResult = new JLabel("결과 : " , JLabel.CENTER); // 가운데 정렬 추가함
		add(lblResult);
		
		txtName.requestFocus(); // requestFocus() : 해당 객체로 cursor 이동
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 입력자료 오류검사 : 입력안되면 오류야 .getText().equals("") : 띄어쓰기 하면 안돼
		if(txtName.getText().equals("")) {
			lblResult.setText("이름 입력!");
			txtName.requestFocus(); // 커서는 하나만 존재해.
			return;
		}
		
		if(txtAge.getText().equals("")) {
			lblResult.setText("나이 입력!");
			txtAge.requestFocus(); // 이걸 안쓰면 커서의 위치가 넘어가지 않음
			return;
		}
		// 나이는 아라비아 숫자형태여야 한다.그래서 숫자형태인지 확인방법
		int nai = 0;
		try {
			nai = Integer.parseInt(txtAge.getText()); // 문자를 정수로 교환하는 거임 : Integer.parseInt
		} catch (Exception e2) {
			lblResult.setText("나이는 정수만 가능");
			txtAge.requestFocus(); 
			return;
		}
		// System.out.println(nai); // 콘솔 nai가 잘 나오지는 확인함.
		// 라디오 번튼 선택 여부 확인함 isSelected()
		//System.out.println(rdoM.isSelected() + " " + rdoF.isSelected());
		String gender = "";
		if(rdoM.isSelected()) {
			gender = "남";
		}else {
			gender = "여";
		}
		
		String message = "결과:" + txtName.getText() + "님의 나이는 " +
		nai + ", 성별은 " + gender;
		lblResult.setText(message);
	}
	
	public static void main(String[] args) {
		new Ex47();

	}

}
