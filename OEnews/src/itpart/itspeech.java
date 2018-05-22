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

public class itspeech {

	List<List<String>> ret = new ArrayList<List<String>>(); //키워드를 저장할 문자열 리스트
	
	 public itspeech(){
	BufferedReader br = null;
	try{
		br = Files.newBufferedReader(Paths.get("Rdata/ITwordspeech.csv")); //csv파일을 읽음  
		Charset.forName("UTF-8");  //UTF-8 형식
		String line = "";
		
		while((line = br.readLine()) != null){
			List<String> tmplist = new ArrayList<String>();
			String array[] = line.split(",");
			tmplist = Arrays.asList(array);
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

	List<String> lstr1 = new ArrayList<String>(); List<String> lstr2 = new ArrayList<String>();
	List<String> lstr3 = new ArrayList<String>(); List<String> lstr4 = new ArrayList<String>();
	List<String> lstr5 = new ArrayList<String>();
	lstr1 = ret.get(1); lstr2 = ret.get(2); lstr3 = ret.get(3);
	lstr4 = ret.get(4); lstr5 = ret.get(5);
	
	int slength[] = new int[5];
	 for (int i = 1; i<6; i++){
		 List<String> lstr = new ArrayList<String>();
		 lstr = ret.get(i);
		 int k = 0;
		 while (!lstr.get(k).equals("NA")){
			 k++;
		 }
		 slength[i-1] = k;
	 }
 
	 }
}

