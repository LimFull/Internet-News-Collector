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
 
	 int sd[][] = new int[5][20];  //같은 언론사명임을 숫자로써 구분할 배열
	 
	  for (int k = 0; k<5; k++){  //5개 단어
		  List<String> lstr = new ArrayList<String>();
			lstr = ret.get(k+1);
		  int num = 0; 
		  for (int i = 0; i<slength[k]; i++){  //길이만큼 확인
			  if (sd[k][i]==0){
				  num++;
				  sd[k][i]=num;
				  if (i < slength[k]-1)  //끝이 아닐 경우에만 
				  for(int j = i+1; j<slength[k]-i; j++){  //i번째 다음 순서부터 끝까지
					  if (lstr.get(i).equals(lstr.get(j)))  //i번째와 j번째 내용이 같으면
					  sd[k][j]=sd[k][i];  //같은 num값 부여
				  }
			  }
		  }
	  }
	  
	  //확인 (같은 글자의 배열 위치에는 같은 숫자가 나와야 함)
	  System.out.println(sd[0][0]);
	  System.out.println(sd[0][1]);
	  System.out.println(sd[0][2]);
	  System.out.println(sd[0][3]);
	  System.out.println(sd[0][4]);
	  System.out.println(sd[0][5]);
	  System.out.println(sd[0][6]);
	  System.out.println(sd[0][7]);
	  List<String> lstr1 = new ArrayList<String>();
	  lstr1 = ret.get(1);
	  System.out.println(lstr1);
	 
	 }
}
