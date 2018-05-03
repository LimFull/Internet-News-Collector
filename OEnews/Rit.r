#!/usr/bin/Rscript --vanilla --slave 

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
# KoNLP 패키지 필요
if (!requireNamespace("KoNLP")){
  install.packages("KoNLP")
} 
library(KoNLP)
# rJava 패키지가 필요하다. 
# rJava 패키지 설치 중 ERROR: configuration failed for package ‘rJava’ -> $ sudo R CMD javaconf -> 그래도 안되면 $ sudo apt-get install r-cran-rjava


# tm 패키지 필요
if (!requireNamespace("tm")){
  install.packages("tm")
} 
library(tm)
# tm 패키지 설치 중 dependency 'slam' is not available 오류 
# -> install.packages('devtools')
# -> library(devtools)
# -> slam_url <- "https://cran.r-project.org/src/contrib/Archive/slam/slam_0.1-37.tar.gz"
# -> install_url(slam_url)

# wordcloud 패키지 필요
if (!requireNamespace("wordcloud")){
  install.packages("wordcloud")
} 
library(wordcloud)

library(stringr)

#-----------패키지 로딩---------------

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




# news_content에 뉴스 내용이 들어있어야 함


# 문장의 형태소 분석을 위해 세종사전 불러옴
 useSejongDic()

# 문장을 쪼개기 위한 함수 정의
 words <- function(doc){
 doc<- as.character(doc)
 doc2<-paste(SimplePos22(doc))
 doc3<-str_match(doc2,"([가-힣]+)/NC")
 doc4<-doc3[,2]
 doc4[!is.na(doc4)]
 }

# 불필요한 글자 제거
 news_content<-gsub("flash 오류를 우회하기 위한 함수 추가\nfunction _flash_removeCallback()","",news_content)
 news_content<-gsub("\t","",news_content)
 news_content<-gsub("\n","",news_content)
 news_content<-gsub("\\{\\}","",news_content)
 news_content<-gsub("\\(\\)","",news_content)
 news_content<-gsub("사진=","",news_content)
 news_content<-gsub("기자]","",news_content)
 news_content<-gsub("기자)","",news_content)
 news_content<-gsub("기자\\(","",news_content)
 news_content<-gsub("기자=","",news_content)
 news_content<-gsub("기자 =","",news_content)
 news_content<-gsub("기자 [a-z]+","",news_content)
 news_content<-gsub("▶.+","",news_content)
 news_content<-gsub("♥.+","",news_content)
 news_content<-gsub("무단전재 및 재배포 금지","",news_content)

 doc <- Corpus(VectorSource(news_content))
 doc <- TermDocumentMatrix(doc,control=list(tokenize=words,removeNumbers=T,removePunctuation=T,wordLengths=c(3,Inf),
                                            stopwords=c("하는","했다","만","대한","위한","hankyung","이번","할","에","그는","것이","것을","디지털데일리","또","중","그","많","한다","때","있","은","는","이","가","의","위해","것","고","com","있다","및","을","를","수","일","등을","등","▶","디지털타임스","지디넷코리아","통해","바로가기","것으로","년","있는","지난","말했다","이를","한","flash")))

 doc <- as.matrix(doc)

 #많이 나온 단어의 링크 추출
 wordurl <- c()              #핫 키워드가 있는 기사의 url을 담을 벡터 생성
 sortedword <- doc[rev(order(rowSums(doc))),]           #총 등장 횟수(행의 합)를 기준으로 정렬한 sortedword 벡터 생성
 for (i in 1:3){                       # i : 몇 개의 키워드의 url을 뽑을 것인지 
 for (j in 1:20){                      # j : 몇 개의 기사를 검사할 것인지
if (sortedword[i,j] != 0) {            # i번째 순위 키워드가 j번째 기사에 포함될 경우 (0이 아닌 경우)
wordurl[i] <- c(news_url[j])}}}        # wordurl[i]에 j번째 기사의 url을 넣는다.

 doc <- rowSums(doc) 
 doc <- doc[order(doc,decreasing=T)] 
 doc <- as.data.frame(doc[1:30])
 doc

#워드클라우드 생성
 pdf.options(family = "Korea1deb") #pdf 한글 옵션
 png(filename="cloudit.png",width=500,height=500) #png 이미지 저장
 wordcloud(words = rownames(doc),freq = doc$doc, min.freq=1, max.words=200, random.order=FALSE,rot.per=0.3,colors=brewer.pal(5,"Dark2"), scale=c(7,3))

#wordurl 확인
wordurl
