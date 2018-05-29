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

import javax.swing.JButton;

public class itspeech {

	List<List<String>> ret = new ArrayList<List<String>>(); //키워드를 저장할 문자열 리스트
	int slength[] = new int[5];
	int sclength[] = new int[5];
	int sd[][] = new int[5][20];  //같은 언론사명임을 숫자로써 구분할 배열
	int sc[][] = new int[5][20];  //sd에 구분된 번호의 언론사가 몇번씩 중복돼있는지 알게 해주는 배열
	
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

	
	 
	 for (int i = 1; i<6; i++){
		 List<String> lstr = new ArrayList<String>();
		 lstr = ret.get(i);
		 int k = 0;
		 while (!lstr.get(k).equals("NA")){
			 k++;
		 }
		 slength[i-1] = k;
	 }
 
	
	  
	 for (int i = 0; i<5; i++){
		 sclength[i]=slength[i];
	 }
	 
	  for (int k = 0; k<5; k++){  //5개 단어
		  List<String> lstr = new ArrayList<String>();
			lstr = ret.get(k+1);
		  int num = 0; 
		  for (int i = 0; i<slength[k]; i++){  //길이만큼 확인
			  if (sd[k][i]==0){
				  num++;
				  sd[k][i]=num;
				  sc[k][num-1]=sc[k][num-1]+1;
				  if (i < slength[k]-1)  //끝이 아닐 경우에만 
				  for(int j = i+1; j<slength[k]; j++){  //i번째 다음 순서부터 끝까지
					  if (lstr.get(i).equals(lstr.get(j))){  //i번째와 j번째 내용이 같으면
					  sd[k][j]=sd[k][i];  //같은 num값 부여
					  sc[k][num-1]=sc[k][num-1]+1;
					  sclength[k]=sclength[k]-1;  //중복된만큼 총 길이를 줄임
					  }
				  }
			  }
		  }
	  }
	   
	  
	 }
	 String getname(int word, int speech){
		 int find=0;
		 List<String> lstr1 = new ArrayList<String>();
		 lstr1 = ret.get(word+1);
		 for (int i = 0; i<slength[word]; i++){
			 if (sd[word][i]==speech+1) {
				 find=i;
				 break;
			 }
		 }
		 return lstr1.get(find).concat(" "+sc[word][speech]); 
	 }
	 int[] gettitlenumber(int word,int speech){
		 int find[] = new int[20];
		 int k = 0;
		  for (int i = 0; i<slength[word]; i++){
			  if (sd[word][i]==speech+1){
				  find[k]=i;
				   k++;
			  }
		  }
		 return find;
	 }
}

