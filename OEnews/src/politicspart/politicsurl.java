package politicspart;

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

public class politicsurl {
	List<List<String>> ret = new ArrayList<List<String>>(); // url을 저장할 문자열 리스트
	java.awt.Desktop desktop = java.awt.Desktop.getDesktop(); // 기본 브라우저를 OS 설정에 맞게 실
public politicsurl(){
	BufferedReader br = null; 
	try{
		br = Files.newBufferedReader(Paths.get("Rdata/POLITICSwordurl.csv")); //csv파일을 읽음  
		Charset.forName("UTF-8");  //UTF-8 형식
		String line = "";
		
		while((line = br.readLine()) != null){
			List<String> tmplist = new ArrayList<String>(); // tmplist 객체 생성
			String array[] = line.split(","); // 콤마(,) 단위로 분리
			tmplist = Arrays.asList(array); // tmplist에 리스트 형식으로 저
			ret.add(tmplist);	// 한 줄씩 ret에 추가
		}
	
	}catch(FileNotFoundException e){ // 
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
	List<String> lstr = new ArrayList<String>(); // lstr 리스트 객체 생성
	lstr = ret.get(word+1); // csv 파일 내 열이름 제외한 url 값 불러들이기
	try{
		java.net.URI uri = new java.net.URI(lstr.get(titlenumber[title]).replace("\"", "")); // uri 객체 생성 후 url 저
		desktop.browse(uri);
	}
	catch(IOException _e){
		System.err.println(_e.getMessage());
	}
	catch(URISyntaxException _e){
		System.err.println(_e.getMessage());
	}
}
}
