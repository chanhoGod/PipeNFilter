
/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.IOException;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description 입력값으로 들어온 byte들을 읽어 CS 값만 출력값 파일에 기록하는 클래스.
 */
public class SinkFilter extends GeneralFilter {
	private String filePath;

	public SinkFilter(String outputFilePath) {
		this.filePath = outputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		int databyte = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		List<Character> list = new ArrayList<Character>();

		try {
			FileWriter fw = new FileWriter(this.filePath);
			while (true) {
				while (databyte != '\n' && databyte != -1) {
					databyte = in.read();
					if (databyte != -1) {
						list.add((char)databyte);
					}

				}

				for(Character c : list) {
					fw.write(c);
				}
				if (databyte == -1) {
					fw.close();
					System.out.print("::Filtering is finished; Output file is created.");
					return;
				}
				idx = 0;
				databyte = '\0';
				list.clear();
			}
			
			

		} catch (Exception e) {
			closePorts();
			e.printStackTrace();

		}
	}

}
