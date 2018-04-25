package societypart;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rsociety {
public static void main (String[] args){
		
		try{
			Process p = Runtime.getRuntime().exec("make SOCIETY");
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
