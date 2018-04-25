//import org.rosuda.JRI.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class RJava {
public static void main (String[] args){
	
try{
	Process p = Runtime.getRuntime().exec("make IT");
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
