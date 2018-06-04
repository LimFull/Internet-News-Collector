package economypart;

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

public class economyurl {
	List<List<String>> ret = new ArrayList<List<String>>(); // url을 저장할 문자열 리스트
	java.awt.Desktop desktop = java.awt.Desktop.getDesktop(); // 해당 OS 지원 브라우저 설정
public economyurl(){
	BufferedReader br = null;
	try{
		br = Files.newBufferedReader(Paths.get("Rdata/ECONOMYwordurl.csv")); //csv파일을 읽음  
		Charset.forName("UTF-8");  //UTF-8 형식
		String line = "";
		
		while((line = br.readLine()) != null){
			List<String> tmplist = new ArrayList<String>(); // tmplist 객체 생성
			String array[] = line.split(","); // 콤마(,)를 기준으로 나눔
			tmplist = Arrays.asList(array); //tmplist에 저장
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
	List<String> lstr = new ArrayList<String>(); // lstr 리스트 생성
	lstr = ret.get(word+1); // 열 이름 제외한 값 받아옴
	try{
		java.net.URI uri = new java.net.URI(lstr.get(titlenumber[title]).replace("\"", "")); // 해당 url 연결할 객체 생성
		desktop.browse(uri); // url을 브라우저에 
	}
	catch(IOException _e){
		System.err.println(_e.getMessage());
	}
	catch(URISyntaxException _e){
		System.err.println(_e.getMessage());
	}
}
}
