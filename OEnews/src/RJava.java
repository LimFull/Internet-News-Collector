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
	System.out.println(re.eval("naver_url")); //url 잘 들어갔는지 확인
	re.eval("html <- read_html(naver_url)");
	re.eval("temp <- c(unique(html_nodes(html,\"#main_content .list_body .type06_headline a\")%>%html_attr('href')),unique(html_nodes(html,\"#main_content .list_body .type06 a\")%>%html_attr('href')))");

	re.eval("news_url <-c()");
	re.eval("news_url <- c(news_url,temp)");
	System.out.println(re.eval("news_url")); // news_url 확인
	System.out.println("news_url 완료");
	
	re.eval("news_content <- c()");
	re.eval("for (i in 1:length(news_url)){html <- read_html(news_url[i]) temp <- repair_encoding(html_text(html_nodes(html,'#articleBodyContents')),from = 'utf-8') news_content <- c(news_content,temp)}");
	re.eval("news <- cbind(url=news_url,content=unlist(news_content))");
	re.eval("news <- as.data.frame(news)");
	System.out.println(re.eval("news"));
	
	System.out.println("\nend");
}
}
