package pack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbTest6PrepEx2 extends JFrame implements ActionListener {
	private JTextField name2, jumin;
	int num1; // 주민번호
	String name; // 고객명
	String gjumin;

	JButton btnC = new JButton("확인");

	JTextArea txtResult = new JTextArea();

	String sql = "";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs, rs2;

	public DbTest6PrepEx2() {
		layInit();
		accDB();

		setBounds(300, 400, 400, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void layInit() { // 디자인

		setLayout(new GridLayout(5, 1));

		JPanel panel1 = new JPanel();
		name2 = new JTextField("", 5);
		panel1.add(new JLabel("고객명 :"));
		panel1.add(name2);
		add("North", panel1);

		JPanel panel2 = new JPanel();
		jumin = new JTextField("", 15);
		panel2.add(new JLabel("주민번호 :"));
		panel2.add(jumin);
		add("North", panel2);
		panel2.add(btnC);

		JPanel panel3 = new JPanel();
		panel3.add(txtResult);
		add("Center", panel3);

		txtResult.setEditable(false);
		JScrollPane pane = new JScrollPane(txtResult);
		add(pane);

		btnC.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnC) {

				try {
					name = name2.getText();
					gjumin = jumin.getText();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				txtResult.setText(null);
				// 부분자료 읽기
				try {

					sql = "SELECT jikwon_no, jikwon_name, buser_name, buser_tel, jikwon_jik from jikwon inner JOIN buser ON jikwon.buser_num = buser.buser_no inner JOIN gogek ON jikwon.jikwon_no = gogek.gogek_damsano WHERE gogek_name=? AND gogek_jumin=?";

					pstmt = conn.prepareStatement(sql); 
					pstmt.setString(1, name); //
					pstmt.setString(2, gjumin);
					rs = pstmt.executeQuery();

					if (rs.next()) {
						display();
					}
				} catch (Exception e2) {
					System.out.println("try err : " + e2);
				}

			}
		} catch (Exception e2) {
			System.out.println("acper err : " + e2);
		}

	}

	private void accDB() { // 자료추가 마리아db연결
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			processDB();
		} catch (Exception e) {
			System.out.println("accDB err : " + e); // 연결에 실패할 수 있으니 오류를 뿌려준다.
		}
	}

	private void processDB() { // 마리아db에 있는 자료 불려오기
		try {
			String url = "jdbc:mariadb://localhost:3306/test"; // 3306(포트번호)
			conn = DriverManager.getConnection(url, "root", "123");

			pstmt = conn.prepareStatement(sql);
		
		} catch (Exception e) {
			System.out.println("processDB err : " + e);
		}
	}

	private void display() { // 문제 풀기
		try {
			sql = "SELECT jikwon_no, jikwon_name, buser_name, buser_tel, jikwon_jik from jikwon inner JOIN buser ON jikwon.buser_num = buser.buser_no inner JOIN gogek ON jikwon.jikwon_no = gogek.gogek_damsano"; //여기에 고객명과 주민번호 입력하면 담당직원번호 떠야되
			pstmt = conn.prepareStatement(sql);
			rs2 = pstmt.executeQuery();

			txtResult.setText("사번\t이름\t부서명\t부서번호\t직책\n");
			while (rs2.next()) {
				String imsi = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t " + rs2.getString(3) + "\t " + rs.getString(4)
						+ "\t " + rs2.getString(5) + "\n";
				txtResult.append(imsi);
			}
		} catch (Exception e) {
			System.out.println("display err:" + e);
		}

	}

	public static void main(String[] args) {
		new DbTest6PrepEx2();

	}

}
