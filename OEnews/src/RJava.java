import org.rosuda.JRI.*;

public class RJava {
public static void main (String[] args){
	System.out.println("Creating Rengine (with arguments)");
	String[] Rargs = {"--vanilla"};
	Rengine re = new Rengine(Rargs, false, null);
	System.out.println("Rengine created, waiting For R");
	
	if(!re.waitForR()){
		System.out.println("Loading R engine was failed");
		return;
	}
	//REXP a = re.eval("a <-10",true);
	//System.out.println(a.asDouble());
	
	//테스트 시작
	re.eval("a<-10");
	//System.out.println(re.eval("a<-10").asDouble());
	re.eval("if(!requireNamespace(\"rvest\")){install.packages(\"rvest\")}");
	re.eval("library(rvest)");
	re.eval("if(!requireNamespace(\"dplyr\")){install.packages(\"dplyr\")}");
	re.eval("library(dplyr)");
	re.eval("naver_url <- 'http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=105'");
	System.out.print(re.eval("naver_url"));
	re.eval("");
	re.eval("");
	
	System.out.println("\nend");
}
}
