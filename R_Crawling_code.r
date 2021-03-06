# 네이버뉴스 IT카테고리 첫 페이지 크롤링 예시
# rvest 패키지 필요 : 
if (!requireNamespace("rvest")){
  install.packages("rvest")
} 
library(rvest)


# dplyr 패키지 필요 : 
if (!requireNamespace("dplyr")){
  install.packages("dplyr")
} 
library(dplyr)

# 리눅스에서 rvest 패키지 설치 오류 발생할 경우 터미널에 다음 명령 입력 -> sudo apt-get install libcurl4-openssl-dev libssl-dev
# rvest install 중 xml2 error -> sudo apt-get install libxml2 libxml2-dev
# rvest install jvm error -> sudo apt-get purge openjdk-8-jre-headless
			     sudo apt-get install openjdk-8-jre-headless
			     sudo apt-get install openjdk-8-jre
 naver_url <- 'http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=105'
 html <- read_html(naver_url)
 temp <- c(unique(html_nodes(html,"#main_content .list_body .type06_headline a")%>%
                html_attr('href')),unique(html_nodes(html,"#main_content .list_body .type06 a")%>%
                                             html_attr('href')))
 temp
 

 news_url <-c()
 news_url <- c(news_url,temp)
 news_url 
 

 news_content <- c()

 for (i in 1:length(news_url)){
     html <- read_html(news_url[i])
     temp <- repair_encoding(html_text(html_nodes(html,'#articleBodyContents')),from = 'utf-8')
     news_content <- c(news_content,temp)
 }
  news <- cbind(url=news_url,content=unlist(news_content))
 news <- as.data.frame(news)
 news
 dir.create("./Rdata",showWarnings = F) 
# Create Rdata Folder
 write.csv(news,file=paste0("./Rdata/news",".csv"),row.names = F)
# Save as csv file
