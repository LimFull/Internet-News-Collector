package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rall {
	public Rall(){
		try{
			Process p = Runtime.getRuntime().exec("make ALL");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
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