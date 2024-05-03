package pack;

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
	private JLabel lblname, lbljumin;
	private JTextField txtname, txtjumin;
	private JTextArea txtResult = new JTextArea();
	private JButton btnCheck;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public DbTest6PrepEx2() {
		layInit();
		accDb();

		setBounds(400, 400, 400, 350);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layInit() {
		JPanel panel1 = new JPanel();
		JLabel lblname = new JLabel("고객명 :");
		txtname = new JTextField("", 5);
		panel1.add(lblname);
		panel1.add(txtname);
		add("North", panel1);

		JPanel panel2 = new JPanel();
		JLabel lbljumin = new JLabel("주민번호 :");
		txtjumin = new JTextField("", 10);
		panel2.add(lbljumin);
		panel2.add(txtjumin);
		add("North", panel2);
		panel2.add(btnCheck);
		btnCheck.addActionListener(this);

		JPanel panel3 = new JPanel();
		panel3.add(txtResult);
		txtResult.setEditable(false);
		add("Center", panel3);

	}

	private void accDb() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			processDb();
		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		}

	}

	private void processDb() {
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");

			String sql = "select * from jikwon";
			pstmt = conn.prepareStatement(sql); // 이걸적어야지 하나하나씩 넘어감
			rs = pstmt.executeQuery();
			rs.next();
			display();
		} catch (Exception e) {
			System.out.println("processDb 오류 :" + e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnCheck)
				rs.first();

		} catch (Exception e2) {
			// TODO: handle exception
		}

		display();

	}

	private void display() {
		try {
			txtname.setText(rs.getString("jikwon_name"));
			txtjumin.setText(rs.getString("jikwon_jumin"));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		new DbTest6PrepEx2();

	}

}
