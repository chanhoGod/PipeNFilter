
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description 입력값으로 받은 byte들을 아무 기능도 하지 않고 그대로 전송하는 기능을 한다.
 */
public class MiddleFilter extends GeneralFilter {

	@Override
	public void specificComputationForFilter() throws IOException {
        byte databyte = 0;
		List<String> list = new ArrayList<String>();
		StringBuilder checkGrade = new StringBuilder();
		StringBuilder sentence = new StringBuilder();
		boolean isCS = false;
		boolean is13notCS = false;

		while (true) {
			while (databyte != '\n' && databyte != -1 && databyte != '\r') {
				databyte = (byte) in.read();
				if(databyte == '\r' || databyte == '\n')
					break;
				
				if (databyte != ' ') {
					checkGrade.append((char)databyte);
				} else if(databyte == ' '){
					list.add(checkGrade.toString());
					checkGrade.setLength(0);
				}
				if(databyte != '\n' && databyte != '\r')
				sentence.append((char) databyte);
				
				
				
				
			}
//			System.out.println(sentence);
//			if(sentence.indexOf("CS") != -1) {
//				isCS = true;
//			}
//			
//			if (isCS == true) {
//				sentence = addSubject(sentence, "12345");
//				sentence = addSubject(sentence, "23456"); // 추가... 처음에한게안되서 다시 다지우고 만듬
//				sentence = addSubject(sentence, "17651");
//				System.out.println(sentence);
//				saveSubject(sentence);
//				isCS = false;
//			}
			
			if(sentence.indexOf("2013") != -1 && sentence.indexOf("CS") == -1) {
				is13notCS = true;
			}

			if (is13notCS == true) {
				sentence = deleteSubject(sentence, "17651");
				sentence = deleteSubject(sentence, "17652");
				saveSubject(sentence);
				is13notCS = false;
			}

			if (databyte == -1)
				return;
			sentence.setLength(0);
			databyte = '\0';
		}

	}

	private StringBuilder deleteSubject(StringBuilder sentence, String sub) {

		if (sentence.indexOf(sub) != -1) {
			System.out.println(sentence.replace(sentence.indexOf(sub) -1, sentence.indexOf(sub) + 5, ""));
			String str = sentence.toString().replace("\r\n", "");
			return new StringBuilder(str).append('\r').append('\n');
		} else {
			String str = sentence.toString().replace("\r\n", "");
			return new StringBuilder(str).append('\r').append('\n');
		}

	}

	public StringBuilder addSubject(StringBuilder sentence, String sub) {
		
		if (sentence.indexOf(sub) != -1) {
			String str = sentence.toString().replace("\r\n", "");
			return new StringBuilder(str).append('\r').append('\n');
		} else {
			String str = sentence.toString().replace("\r\n", "");
			return new StringBuilder(str).append(' ').append(sub).append('\r').append('\n');
		}
	}

	public void saveSubject(StringBuilder sentence) {
		try {
			char[] ch = sentence.toString().toCharArray();
			for (int i = 0; i < ch.length; i++) {
				out.write(ch[i]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
