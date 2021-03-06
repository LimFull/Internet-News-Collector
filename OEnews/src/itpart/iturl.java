package itpart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class iturl {
	List<List<String>> ret = new ArrayList<List<String>>(); // url을 저장할 문자열 리스트
	java.awt.Desktop desktop = java.awt.Desktop.getDesktop(); // 해당 OS 지원 브라우저 연결
public iturl(){
	BufferedReader br = null;
	try{
		br = Files.newBufferedReader(Paths.get("Rdata/ITwordurl.csv")); //csv파일을 읽음  
		Charset.forName("UTF-8");  //UTF-8 형식
		String line = "";
		
		while((line = br.readLine()) != null){
			List<String> tmplist = new ArrayList<String>(); //tmplist 객체 생성
			String array[] = line.split(",");  // 콤마(,) 기준 분리
			tmplist = Arrays.asList(array); // tmplist에 저장
			ret.add(tmplist);	// 한 줄씩 ret에 추가
		}
	
	}catch(FileNotFoundException e){
		e.printStackTrace();
	}catch(IOException e){
		e.printStackTrace();
	}finally{
		try{
			if(br != null){
				br.close();  //파일 읽기 종료
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
public void openurl(int word, int title, int[] titlenumber){
	List<String> lstr = new ArrayList<String>(); //lstr 객체 생성
	lstr = ret.get(word+1); // 열이름 제외한 url 값 저장
	try{
		java.net.URI uri = new java.net.URI(lstr.get(titlenumber[title]).replace("\"", "")); // URL 연결할 객체 생성
		desktop.browse(uri); // 브라우저 실행하여 
	}
	catch(IOException _e){
		System.err.println(_e.getMessage());
	}
	catch(URISyntaxException _e){
		System.err.println(_e.getMessage());
	}
}
}
