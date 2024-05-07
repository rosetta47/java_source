package pack;

import java.awt.GridLayout;
import java.awt.Label;
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
// 내가 풀어본것
public class DbTest6PrepEx1 extends JFrame implements ActionListener {
	private JLabel lblcode, lblsang, lblsu, lbldan;
	private JTextField tcode, tsang, tsu, tdan, tpay;
	private JTextArea txtResult = new JTextArea();
	private JButton btnAdd;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	

	public DbTest6PrepEx1() {
		super("상품 처리");

		layInit();
		accDb();

		setBounds(500, 500, 700, 350);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layInit() {

	//	setLayout(new GridLayout(5, 3));

		JPanel panel1 = new JPanel();

		JLabel lblcode = new JLabel("코드 :");
		tcode = new JTextField("", 5);
		panel1.add(lblcode);
		panel1.add(tcode);

		JLabel lblsang = new JLabel("품명 :");
		tsang = new JTextField("", 5);
		panel1.add(lblsang);
		panel1.add(tsang);

		JLabel lblsu = new JLabel("수량 :");
		tsu = new JTextField("", 5);
		panel1.add(lblsu);
		panel1.add(tsu);

		JLabel lbldan = new JLabel("단가 :");
		tdan = new JTextField("", 7);
		panel1.add(lbldan);
		panel1.add(tdan);

		JButton btnAdd = new JButton("추가");
		panel1.add(btnAdd);
		btnAdd.addActionListener(this);
		add("North", panel1);

		JPanel panel2 = new JPanel();
		panel2.add(txtResult);
		add("Center", panel2);

		txtResult.setEditable(false); // 편집안되고 보여지기만 가능
		JScrollPane pane = new JScrollPane(txtResult);
		add("Center", pane);
	}

	private void accDb() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");

			String sql = "";

			sql = "select * from sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();


		} catch (Exception e) {
			System.out.println("accDb 오류 : " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");

			String sql = "select code, sang, su, dan from sangdata";

			if (e.getSource() == btnAdd) {
				// insert문
			} 
			txtResult.setText("코드\t상품명\t수량\t단가\t금액\n");
			while (rs.next()) { String imsi = rs.getString("code") + "\t" +
					  rs.getString("sang") + "\t" + rs.getString("su") + "\t" + rs.getString("dan")
					  + "\n";
				
			txtResult.append(imsi);
			}
			
			String co = tcode.getText();
			String na = tsang.getText();
			String cn = tsu.getText();
			String pr = tdan.getText();

			sql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, co);
			pstmt.setString(2, na);
			pstmt.setString(3, cn);
			pstmt.setString(4, pr);
			rs = pstmt.executeQuery();
			
			display();

		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	private void display() {
		try {
			tcode.setText(rs.getString("code"));
			tsang.setText(rs.getString("sang"));
			tsu.setText(rs.getString("su"));
			tdan.setText(rs.getString("dan"));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		new DbTest6PrepEx1();

	}

}
