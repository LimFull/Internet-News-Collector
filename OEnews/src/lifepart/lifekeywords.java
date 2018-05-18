package lifepart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class lifekeywords {
	List<String> ret = new ArrayList<String>(); //키워드를 저장할 문자열 리스트
	
	 public lifekeywords(){
	BufferedReader br = null;
	try{
		br = Files.newBufferedReader(Paths.get("Rdata/ITkeywords.csv")); //csv파일을 읽음  
		Charset.forName("UTF-8");  //UTF-8 형식
		String line = "";
		
		while((line = br.readLine()) != null){
			ret.add(line);	// 한 줄씩 ret에 추가
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
	 
	 String getwords(int i){ //i번째 단어를 가져오는 메소드
		  return ret.get(i);
	 }
}
