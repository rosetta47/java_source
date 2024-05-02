package pack7gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Ex49Memojang extends JFrame implements ActionListener {
	private JTextArea txtMeno = new JTextArea("", 10, 30); // 내용은 비어있고
	private String copyText; // 복사한 문자열 잠시 보관용

	private JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit;
	private JMenuItem mnuCopy, mnuPaste, mnuCut, mnuDel, mnuFontSize;
	private JMenuItem mnuAbout, mnuEtc1, mnuEtc2;

	private	JPopupMenu popup; // 팝업 메뉴 아이콘이야
	private	JMenuItem m_white, m_blue, m_yellow;
	
	public Ex49Memojang() {
		super("메모장");

		// 레이아웃 만들기
		initLayout();
		menuLayout();

		setBounds(200, 200, 400, 350);
		setVisible(true); // 화면 보이는 것
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 화면 종료
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(Ex49Memojang.this, "정말 종료할까요?", "종료 확인",
						JOptionPane.YES_NO_OPTION); // 무명클래스라서 Ex49Memojang.this 이렇게 써야함
				if (result == JOptionPane.YES_OPTION)
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				else
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			}
		});
	}

	private void initLayout() {
		JScrollPane scrollPane = new JScrollPane(txtMeno); // 스크롤 생성을 txtMemo에 다가
		txtMeno.setFont(new Font("돋음", Font.PLAIN, 16));
		add("Center", scrollPane);

	}

	private void menuLayout() { // 메뉴 디자인 : menuLayout()
		JMenuBar menuBar = new JMenuBar(); // 메뉴 바 객체 생성
		// 파일
		JMenu mnuFile = new JMenu("파일"); // 주메뉴 생성
		mnuNew = new JMenuItem("새문서"); // 부메뉴 생성
		mnuOpen = new JMenuItem("열기..."); // 열기... 뒤에 ...이 있으면 대화창 뜬다
		mnuSave = new JMenuItem("저장...");
		mnuExit = new JMenuItem("종료");
		mnuFile.add(mnuNew);
		mnuFile.add(mnuOpen);
		mnuFile.add(mnuSave);
		mnuFile.addSeparator(); // 구분선 : mnuFile.addSeparator();
		mnuFile.add(mnuExit); // 메뉴에 부메뉴 등록

		// 편집
		JMenu mnuEdit = new JMenu("편집"); // 주메뉴
		mnuCopy = new JMenuItem("복사");
		mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK)); // 단축키 숏컷키임 (Ctrl + C)
		mnuPaste = new JMenuItem("붙여넣기");
		mnuCut = new JMenuItem("잘라내기");
		mnuDel = new JMenuItem("삭제");
		mnuFontSize = new JMenuItem("글꼴 크기...");
		mnuEdit.add(mnuCopy);
		mnuEdit.add(mnuPaste);
		mnuEdit.add(mnuCut);
		mnuEdit.add(mnuDel);
		mnuEdit.addSeparator();
		mnuEdit.add(mnuFontSize);

		// 보기
		JMenu mnuShow = new JMenu("보기"); // 주메뉴
		mnuAbout = new JMenuItem("메모장이란...");
		mnuEtc1 = new JMenuItem("계산기");
		mnuEtc2 = new JMenuItem("카페");
		mnuShow.add(mnuAbout);
		mnuShow.addSeparator();
		mnuShow.add(mnuEtc1);
		mnuShow.add(mnuEtc2);

		menuBar.add(mnuFile); // 메뉴바에 주메뉴 등록
		menuBar.add(mnuEdit);
		menuBar.add(mnuShow);

		setJMenuBar(menuBar); // j프레임에 메뉴바를 등록

		// 메뉴에 리스너 달기
		mnuNew.addActionListener(this);
		mnuOpen.addActionListener(this);
		mnuSave.addActionListener(this);
		mnuExit.addActionListener(this);
		mnuCopy.addActionListener(this);
		mnuPaste.addActionListener(this);
		mnuCut.addActionListener(this);
		mnuDel.addActionListener(this);
		mnuFontSize.addActionListener(this);
		mnuAbout.addActionListener(this);
		mnuEtc1.addActionListener(this);
		mnuEtc2.addActionListener(this); // 이걸 설정하면 actionPerformed(ActionEvent e) 로 넘어감

		// 팝업메뉴 오른쪽 버튼 누르면 나오는 거
		popup = new JPopupMenu();
		JMenu menuColor = new JMenu("색 선택");
		m_white = new JMenuItem("하양");
		m_blue = new JMenuItem("파랑");
		m_yellow = new JMenuItem("노랑");
		menuColor.add(m_white);
		menuColor.add(m_blue);
		menuColor.add(m_yellow);
		m_white.addActionListener(this);
		m_blue.addActionListener(this);
		m_yellow.addActionListener(this);
		popup.add(menuColor);
		txtMeno.add(popup); // 	txtMeno에다가 (popup) 을 추가
		
		txtMeno.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 마우스 오른쪽 버튼 클릭시 해당 지점에 팝엄 띄우기 (옛버전)
				/*if(e.getModifiers() == MouseEvent.BUTTON3_MASK) {
					popup.show(txtMeno, e.getX(), e.getY());
				} */
				// 마우스 오른쪽 버튼 클릭시 해당 지점에 팝엄 띄우기 (새로운버전)
				if((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == MouseEvent.BUTTON3_DOWN_MASK) {
					popup.show(txtMeno, e.getX(), e.getY());
				}
			} // BUTTON3_DOWN_MASK : 마우스 오른쪽 버튼이 눌린 상태를 나타내는 상수
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(e.getActionCommand());
		// System.out.println(e.getSource());
		if (e.getSource() == mnuNew) { // 새문서
			txtMeno.setText(""); // settext ""비움
			setTitle("제목 없음");
		} else if (e.getSource() == mnuOpen) { // 열기
			// 파일 열기를 위한 경로명과 파일명 얻기 OS지원 창을 사용
			FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
			dialog.setDirectory("."); // "." : 현재 디럭토리임 (여기서는 jpro1)
			dialog.setVisible(true);
			if (dialog.getFile() == null)
				return;
			String dfName = dialog.getDirectory() + dialog.getFile();

			try {
				BufferedReader reader = new BufferedReader(new FileReader(dfName));

				txtMeno.setText("");
				String line = ""; // line 이 있어야지 글을 한줄한줄 갖고오나봐
				while ((line = reader.readLine()) != null) {
					txtMeno.append(line + "\n");
				}
				reader.close();

				setTitle(dialog.getFile() + " - 메모장");

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
				JOptionPane.showMessageDialog(this, e2.getMessage(), "에러", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getSource() == mnuSave) { // 저장
			// 파일 저장을 위한 경로명과 파일명 얻기 OS지원 창을 사용
			FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
			dialog.setDirectory("."); // "." : 현재 디럭토리임 (여기서는 jpro1)
			dialog.setVisible(true);
			if (dialog.getFile() == null)
				return;
			String dfName = dialog.getDirectory() + dialog.getFile();
			// System.out.println("dfName :" + dfName);

			try { // 파일이기 때문에 try catch 사용함
					// BufferedWriter writer = new BufferedWriter(
					// new FileWriter("c:/work/a.txt")); //c:\\work\\a.txt ->윈도우 타입
				BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));

				writer.write(txtMeno.getText());
				writer.close();
				setTitle(dialog.getFile() + " - 메모장");
			} catch (Exception e2) {
				// 개발자가 보고 싶으면 syso
				System.out.println(e2.getMessage());
				JOptionPane.showMessageDialog(this, e2.getMessage(), "에러", JOptionPane.WARNING_MESSAGE); // 사용자에게 보여주려면
																											// 이렇게 씀
			}
		} else if (e.getSource() == mnuExit) { // 종료

			int result = JOptionPane.showConfirmDialog(this, "정말 종료할까요?", "종료 확인", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION)
				System.exit(0);
			else
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		} else if (e.getSource() == mnuCopy) { // 복사

			// System.out.println(txtMeno.getSelectedText());
			copyText = txtMeno.getSelectedText();

		} else if (e.getSource() == mnuPaste) { // 붙여넣기

			// txtMeno.setText(copyText); //복사한거만 남고 다 날라감. why txtMemo에 하고 있어서
			int position = txtMeno.getCaretPosition();
			txtMeno.insert(copyText, position);

		} else if (e.getSource() == mnuCut) { // 잘라내기
			// copyText에 저장된 부분은 지워줘야 됨 txtMemo 에서 시작과 끝을 만들어야됨.
			copyText = txtMeno.getSelectedText();
			int start = txtMeno.getSelectionStart();
			int end = txtMeno.getSelectionEnd();
			txtMeno.replaceRange("", start, end); // "" 커서 드래그 만큼

		} else if (e.getSource() == mnuDel) { // 삭제
			int start = txtMeno.getSelectionStart();
			int end = txtMeno.getSelectionEnd();
			txtMeno.replaceRange("", start, end);

		} else if (e.getSource() == mnuFontSize) { // 글꼴 크기
			String fontSize = JOptionPane.showInputDialog(this, "글꼴 크기: ", "16");
			if (fontSize != null) {
				try {
					int fSize = Integer.parseInt(fontSize);

					txtMeno.setFont(new Font(txtMeno.getFont().getName(), txtMeno.getFont().getStyle(), fSize));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "글자 크기를 정확히 입력하세요");
				}
			}

		} else if (e.getSource() == mnuAbout) { // 메모장이란 : 모달사용해서 함
			new Ex49MemoAbout(this);
			System.out.println("About 호출 후 상황"); // modal, modaless 구분하려고 씀

		} else if (e.getSource() == mnuEtc1) { // 기타1 : 계산기
			// exe (실행파일) 실행하기
			try {
				Runtime runtime = Runtime.getRuntime();
				runtime.exec("calc.exe");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2);
			}

		} else if (e.getSource() == mnuEtc2) { // 기타2 : 브라우저 화면에 띄우기
			// 브라우저 실행하기
			try {
				// Desktop 클래스는 Java 응용 프로그램 URI 나 파일을 처리하기 위해 기본 등록된 관련 응용 프로그램을 실행 할 수 있음.
				// 1. 기본 브라우저를 통해서 URL 전시 2. 메일 클라이언트 실행
				// 3. 기본 응용프로그램을 통해서 파일을 열거나 편집

				String url = "https://cafe.daum.net/flowlife";
				// DeskTop 지원 확인
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.BROWSE)) {
						// URI는 문자열, 특정 리소스를 의미함.
						desktop.browse(new URI(url));
					}
				} else {
					JOptionPane.showMessageDialog(this, "브라우저 지원 안함");
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage());
			}
		}else if (e.getSource() == m_white) { // 팝업 메뉴용
			txtMeno.setBackground(Color.WHITE);
		}else if (e.getSource() == m_blue) { // 팝업 메뉴용
			txtMeno.setBackground(Color.BLUE);
		}else if (e.getSource() == m_yellow) { // 팝업 메뉴용
			txtMeno.setBackground(Color.YELLOW);
		}

	}

	public static void main(String[] args) {
		// 간단한 메모장 만들기
		new Ex49Memojang();

	}

}
