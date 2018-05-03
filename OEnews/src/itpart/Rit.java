package itpart; //import org.rosuda.JTI.* 빼놓음

import java.io.InputStreamReader;
import java.io.BufferedReader;
 

public class Rit {
	public Rit(){
		try{
			Process p = Runtime.getRuntime().exec("make IT");
			p.waitFor();
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
