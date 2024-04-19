package pack5io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.imageio.stream.FileImageInputStream;

public class Ex35IoTest3 {
	// 2byte 단위로 데이터 입출력 : 문자열 데이터 처리가 가능, 한글 자료에 효과적이다
	public void write_file(File file, ArrayList<String> strdatas) {
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(file));

			for (String munja : strdatas) {
				writer.write(munja, 0, munja.length());
				writer.newLine(); // line skip
			}
			if (writer != null)
				writer.close();
		} catch (Exception e) {
			System.out.println("write_file errr :" + e);
		}

	}

	public void read_file(File file) {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(file));
			
			String oneline;
		//	String strs = null;
			// 문자열 더하기가 많은 경우 StringBuffer, StringBuilder 중 하나 추천
			StringBuffer buffer = new StringBuffer();
	//		StringBuilder builder = new StringBuilder();
			
			while((oneline = reader.readLine()) != null) {
			//	strs += oneline + "\n";
				buffer.append(oneline + "\n"); //이렇게 쓰는 것을 권장함
			}
			if (reader != null) reader.close();
			
			System.out.println(buffer.toString());
		} catch (Exception e) {
			System.out.println("read_file errr :" + e);
		} finally {
			
		}
	}

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("하하");
		list.add("호호");
		list.add("히히히"); // 이클립스에서 메모장에 입력된다.

		File file = new File("c:/work/iotest2.txt");

		Ex35IoTest3 test2 = new Ex35IoTest3();
		test2.write_file(file, list);
		test2.read_file(file);

	}

}
