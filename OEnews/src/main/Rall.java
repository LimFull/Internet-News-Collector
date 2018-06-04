package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rall {
	public Rall(){
		try{
			Process p = Runtime.getRuntime().exec("make ALL"); // 터미널 명령을 사용할 프로세스 객체 p 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())); // 프로세스로 부터 문자열 한번에 입력바등ㄹ 객체 br 생성
			String line = null;			
			while((line = br.readLine()) != null){ 
				System.out.println(line);
			}
		} catch(Exception e){
			System.err.println(e);

		}
		System.out.print("\nend");
	}
}
