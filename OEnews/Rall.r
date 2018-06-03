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

#Rit.r
 naver_url <- 'http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=105'
 html <- read_html(naver_url)
 temp <- c(unique(html_nodes(html,"#main_content .list_body .type06_headline a")%>%
                html_attr('href')),unique(html_nodes(html,"#main_content .list_body .type06 a")%>%
                                             html_attr('href')),repair_encoding(html_text(html_nodes(html,'.writing')),from = 'utf-8'))
 speech<-temp[21:40] #언론사명 저장 

 news_url <-c()
 news_url <- c(news_url,temp[1:20])
 

 news_content <- c()
 news_title <- c()
 for (i in 1:length(news_url)){
     html <- read_html(news_url[i])
     temp <- repair_encoding(html_text(html_nodes(html,'#articleBodyContents')),from = 'utf-8')
     news_content <- c(news_content,temp)
     temp <- repair_encoding(html_text(html_nodes(html,'h3#articleTitle')),from = 'utf-8')
     news_title <- c(news_title, temp)
 }
  news <- cbind(url=news_url,content=unlist(news_content))
 news <- as.data.frame(news)




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
 news_content<-gsub("etnews\\.com","",news_content)

 doc <- Corpus(VectorSource(news_content))
 doc <- TermDocumentMatrix(doc,control=list(tokenize=words,removeNumbers=T,removePunctuation=T,wordLengths=c(3,Inf),
                                            stopwords=c("며","월","전","개","오는","밝혔다","따라","하는","했다","만","대한","위한","hankyung","이번","할","에","그는","것이","것을","디지털데일리","또","중","그","많","한다","때","있","은","는","이","가","의","위해","것","고","com","있다","및","을","를","수","일","등을","등","▶","디지털타임스","지디넷코리아","통해","바로가기","것으로","년","있는","지난","말했다","이를","한","flash")))

 doc <- as.matrix(doc)

 #많이 나온 단어의 링크 추출
 wordurl <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 url을 담을 행렬 생성   
 wordspeech <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 언론사명을 담을 행렬 생성   
 wordtitle <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 제목을 담을 행렬 생성   
 sortedword <- doc[rev(order(rowSums(doc))),]           #총 등장 횟수(행의 합)를 기준으로 정렬한 sortedword 벡터 생성
 for (i in 1:5){                       # i : 몇 개의 키워드의 url을 뽑을 것인지 
 k <- 0                                # url과 url 사이에 공백이 끼어들지 않도록 k를 이용해 url을 빽빽히 쌓는다. 새로운 행에 대한 루프를 돌 때마다 0으로 초기화 해준다.
 for (j in 1:20){                      # j : 몇 개의 기사를 검사할 것인지
if (sortedword[i,j] != 0) {            # i번째 순위 키워드가 j번째 기사에 포함될 경우 (0이 아닌 경우)
k <- k+1
wordurl[i,k] <- c(news_url[j])        # wordurl[i]에 j번째 기사의 url을 넣는다.
wordspeech[i,k] <- c(speech[j])       # wordurl[i]에 j번째 기사의 언론사명을 넣는다.
wordtitle[i,k] <- c(news_title[j])}}}      # wordurl[i]에 j번째 기사의 제목을 넣는다.


 doc <- rowSums(doc) 
 doc <- doc[order(doc,decreasing=T)] 
 doc <- as.data.frame(doc[1:30])
  

#워드클라우드 생성
 pdf.options(family = "Korea1deb") #pdf 한글 옵션
 png(filename="cloudit.png",width=500,height=500) #png 이미지 저장
 wordcloud(words = rownames(doc),freq = doc$doc, min.freq=1, max.words=200, random.order=FALSE,rot.per=0.3,colors=brewer.pal(5,"Dark2"), scale=c(7,3))

#wordurl 확인
wordurl

#키워드를 따로 저장하기 위해 doc의 행 이름들을 keywords에 넣음
 keywords <- rownames(doc)
#Rdata 폴더 생성
 dir.create("./Rdata",showWarnings = F) 
#wordurl, keywords를 csv파일로 저장
 write.csv(wordurl,file=paste0("./Rdata/ITwordurl",".csv"),row.names = F)
 write.csv(wordspeech,file=paste0("./Rdata/ITwordspeech",".csv"),row.names = F)
 write.csv(wordtitle,file=paste0("./Rdata/ITwordtitle",".csv"),row.names = F)

 write.table(keywords,file=paste0("./Rdata/ITkeywords",".csv"),row.names = F,col.names = F)

#Rlife.r
 naver_url <- 'http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=103'
 html <- read_html(naver_url)
 temp <- c(unique(html_nodes(html,"#main_content .list_body .type06_headline a")%>%
                html_attr('href')),unique(html_nodes(html,"#main_content .list_body .type06 a")%>%
                                             html_attr('href')),repair_encoding(html_text(html_nodes(html,'.writing')),from = 'utf-8'))
 speech<-temp[21:40] #언론사명 저장 

 news_url <-c()
 news_url <- c(news_url,temp[1:20])
 

 news_content <- c()
 news_title <- c()
 for (i in 1:length(news_url)){
     html <- read_html(news_url[i])
     temp <- repair_encoding(html_text(html_nodes(html,'#articleBodyContents')),from = 'utf-8')
     news_content <- c(news_content,temp)
     temp <- repair_encoding(html_text(html_nodes(html,'h3#articleTitle')),from = 'utf-8')
     news_title <- c(news_title, temp)
 }
  news <- cbind(url=news_url,content=unlist(news_content))
 news <- as.data.frame(news)




# news_content에 뉴스 내용이 들어있어야 함



# 문장을 쪼개기 위한 함수 정의
 words <- function(doc){
 doc<- as.character(doc)
 doc2<-paste(SimplePos22(doc))
 doc3<-str_match(doc2,"([가-힣]+)/NC")
 doc4<-doc3[,2]
 doc4[!is.na(doc4)]
 }

# 불필요한 글자 제거
 news_content<-gsub("【서울=뉴시스】","",news_content)
 news_content<-gsub("photo@yna\\.co\\.kr","",news_content)
 news_content<-gsub("뉴스1photo@news1\\.kr","",news_content)
 news_content<-gsub("flash 오류를 우회하기 위한 함수 추가\nfunction _flash_removeCallback()","",news_content)
 news_content<-gsub("\t","",news_content)
 news_content<-gsub("\n","",news_content)
 news_content<-gsub("\\{\\}","",news_content)
 news_content<-gsub("\\(\\)","",news_content)
 news_content<-gsub("사진=","",news_content)
 news_content<-gsub("사진\\]","",news_content)
 news_content<-gsub("기자]","",news_content)
 news_content<-gsub("기자)","",news_content)
 news_content<-gsub("기자\\(","",news_content)
 news_content<-gsub("기자=","",news_content)
 news_content<-gsub("기자 =","",news_content)
 news_content<-gsub("기자 [a-z]+","",news_content)
 news_content<-gsub("\\[한겨레\\]","",news_content)
 news_content<-gsub("”","",news_content)
 news_content<-gsub("\"","",news_content)
 news_content<-gsub("▶.+","",news_content)
 news_content<-gsub("♥.+","",news_content)
 news_content<-gsub("☎","",news_content)
 news_content<-gsub("newsis\\.com","",news_content)
 news_content<-gsub("@yna","",news_content)
 news_content<-gsub("\\[사진 영상 제보받습니다\\] 공감언론 뉴시스가 독자 여러분의 소중한 제보를 기다립니다\\. 뉴스 가치나 화제성이 있다고 판단되는 사진 또는 영상을 뉴시스 사진영상부","",news_content)
 news_content<-gsub("로 보내주시면 적극 반영하겠습니다","",news_content)
 news_content<-gsub("\\(서울","",news_content)
 news_content<-gsub("연합뉴스\\)","",news_content)
 news_content<-gsub("\\[연합뉴스","",news_content)
 news_content<-gsub("연합뉴스\\]","",news_content)
 news_content<-gsub("\\[뉴스","",news_content)
 news_content<-gsub("뉴스1\\]","",news_content)
 news_content<-gsub("copyright@munhwa","",news_content)
 news_content<-gsub("\\[ 문화닷컴 바로가기 \\| 문화일보가 직접 편집한 뉴스 채널 \\| 모바일 웹 \\]","",news_content)
 news_content<-gsub("'대한민국 오후를 여는 유일석간 문화일보' 무단 전재 및 재배포 금지","",news_content)
 news_content<-gsub("[a-z]*@","",news_content)
 news_content<-gsub("【서울","",news_content)
 news_content<-gsub("◇","",news_content)
 news_content<-gsub("\\[ MBN 문화스포츠부 \\]","",news_content)
 news_content<-gsub("MBN\\(www\\.mbn\\.co\\.kr\\) 무단전재 및 재배포 금지","",news_content)
 news_content<-gsub("ⓒ","",news_content)
 news_content<-gsub("오마이뉴스는 개인의 일상을 소재로 한 생활글도 뉴스로 채택하고 있습니다\\. 개인의 경험을 통해 뉴스를 좀더 생생하고 구체적으로 파악할 수 있습니다\\. 당신의 이야기가 오마이뉴스에 오면 뉴스가 됩니다\\. 당신의 이야기를 들려주세요","",news_content)
 news_content<-gsub("\\[오마이뉴스","",news_content)

 doc <- Corpus(VectorSource(news_content))
 doc <- TermDocumentMatrix(doc,control=list(tokenize=words,removeNumbers=T,removePunctuation=T,wordLengths=c(3,Inf),
                                            stopwords=c("있도록","하지만","대한","전","또는","밝혔다","등의","오는","이번","에서","중","당시","그런데","매우","없었다","있었다","또","∼","▲","월","위한","도","했다","게","오전","오후","쓴","옮김","제공","그가","그의","그","그는","거의","것이","것이다","더","자신의","할","로","많","한다","때","있","은","는","이","가","의","위해","것","고","com","있다","및","을","를","수","일","등을","등","▶","통해","바로가기","것으로","년","있는","지난","말했다","이를","한","flash")))

 doc <- as.matrix(doc)

 #많이 나온 단어의 링크 추출
 wordurl <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 url을 담을 벡터 생성
 wordspeech <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 언론사명을 담을 행렬 생성
 wordtitle <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 제목을 담을 행렬 생성
 sortedword <- doc[rev(order(rowSums(doc))),]           #총 등장 횟수(행의 합)를 기준으로 정렬한 sortedword 벡터 생성
 for (i in 1:5){                       # i : 몇 개의 키워드의 url을 뽑을 것인지 
 k <- 0
 for (j in 1:20){                      # j : 몇 개의 기사를 검사할 것인지
if (sortedword[i,j] != 0) {            # i번째 순위 키워드가 j번째 기사에 포함될 경우 (0이 아닌 경우)
k <- k+1
wordurl[i,k] <- c(news_url[j])        # wordurl[i]에 j번째 기사의 url을 넣는다.
wordspeech[i,k] <- c(speech[j])       # wordurl[i]에 j번째 기사의 언론사명을 넣는다
wordtitle[i,k] <- c(news_title[j])}}}    # wordurl[i]에 j번째 기사의 제목을 넣는다.

 doc <- rowSums(doc) 
 doc <- doc[order(doc,decreasing=T)] 
 doc <- as.data.frame(doc[1:30])

#워드클라우드 생성
 pdf.options(family = "Korea1deb") #pdf 한글 옵션
 png(filename="cloudlife.png",width=500,height=500) #png 이미지 저장
 wordcloud(words = rownames(doc),freq = doc$doc, min.freq=1, max.words=200, random.order=FALSE,rot.per=0.3,colors=brewer.pal(5,"Dark2"), scale=c(7,3))

#wordurl 확인
wordurl

#키워드를 따로 저장하기 위해 doc의 행 이름들을 keywords에 넣음
 keywords <- rownames(doc)
#Rdata 폴더 생성
 dir.create("./Rdata",showWarnings = F) 
#wordurl, keywords를 csv파일로 저장
 write.csv(wordurl,file=paste0("./Rdata/LIFEwordurl",".csv"),row.names = F)
 write.csv(wordspeech,file=paste0("./Rdata/LIFEwordspeech",".csv"),row.names = F)
 write.csv(wordtitle,file=paste0("./Rdata/LIFEwordtitle",".csv"),row.names = F)
 write.table(keywords,file=paste0("./Rdata/LIFEkeywords",".csv"),row.names = F,col.names = F)

#Rsociety.r
 naver_url <- 'http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=102'
 html <- read_html(naver_url)
 temp <- c(unique(html_nodes(html,"#main_content .list_body .type06_headline a")%>%
                html_attr('href')),unique(html_nodes(html,"#main_content .list_body .type06 a")%>%
                                             html_attr('href')),repair_encoding(html_text(html_nodes(html,'.writing')),from = 'utf-8'))
 temp
 speech<-temp[21:40] #언론사명 저장 

 news_url <-c()
 news_url <- c(news_url,temp[1:20])
 

 news_content <- c()
 news_title <- c()
 for (i in 1:length(news_url)){
     html <- read_html(news_url[i])
     temp <- repair_encoding(html_text(html_nodes(html,'#articleBodyContents')),from = 'utf-8')
     news_content <- c(news_content,temp)
     temp <- repair_encoding(html_text(html_nodes(html,'h3#articleTitle')),from = 'utf-8')
     news_title <- c(news_title, temp)
 }
  news <- cbind(url=news_url,content=unlist(news_content))
 news <- as.data.frame(news)




# news_content에 뉴스 내용이 들어있어야 함



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
 news_content<-gsub("▶.+","",news_content)
 news_content<-gsub("♥.+","",news_content)
 news_content<-gsub("▲","",news_content)
 news_content<-gsub("◆","",news_content)
 news_content<-gsub("◇","",news_content)
 news_content<-gsub("cbs노컷뉴스","",news_content)
 news_content<-gsub("photo@","",news_content)
 news_content<-gsub("제공=","",news_content)
 news_content<-gsub("제공\\)","",news_content)
 news_content<-gsub("\\(www","",news_content)
 news_content<-gsub("뉴스 가치나 화제성이 있다고 판단되는 사진 또는 영상을 뉴시스 사진영상부\\(n-photo@newsis\\.com, 02-721-7470\\)로 보내주시면 적극 반영하겠습니다","",news_content)
 news_content<-gsub("photo@newsis\\.com\\[사진 영상 제보받습니다\\] 공감언론 뉴시스가 독자 여러분의 소중한 제보를 기다립니다","",news_content)
 news_content<-gsub("\\(서울=연합뉴스\\)","",news_content)

 doc <- Corpus(VectorSource(news_content))
 doc <- TermDocumentMatrix(doc,control=list(tokenize=words,removeNumbers=T,removePunctuation=T,wordLengths=c(3,Inf),
                                            stopwords=c("가장","이어","하고","최","있도록","씨는","그래서","그런","news","밝혔다","이번","그리고","서울경제","세계일보","며","하는","위한","이라고","yna","명이","중","김","이날","명은","뉴스","있습니다","시","오후","오전","했다","명","개","조","강","씨","측","월","전","시간","newsis","또","그","많","한다","때","있","은","는","이","가","의","위해","것","고","com","있다","및","을","를","수","일","등을","등","▶","통해","바로가기","것으로","년","있는","지난","말했다","이를","한","flash")))

 doc <- as.matrix(doc)

 #많이 나온 단어의 링크 추출
 wordurl <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 url을 담을 벡터 생성
 wordspeech <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 언론사명을 담을 행렬 생성 
 wordtitle <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 제목을 담을 행렬 생성 
 sortedword <- doc[rev(order(rowSums(doc))),]           #총 등장 횟수(행의 합)를 기준으로 정렬한 sortedword 벡터 생성
 for (i in 1:5){                       # i : 몇 개의 키워드의 url을 뽑을 것인지 
 k <- 0
 for (j in 1:20){                      # j : 몇 개의 기사를 검사할 것인지
if (sortedword[i,j] != 0) {            # i번째 순위 키워드가 j번째 기사에 포함될 경우 (0이 아닌 경우)
k <- k+1
wordurl[i,k] <- c(news_url[j])        # wordurl[i]에 j번째 기사의 url을 넣는다.
wordspeech[i,k] <- c(speech[j])       # wordurl[i]에 j번째 기사의 언론사명을 넣는다.
wordtitle[i,k] <- c(news_title[j])}}}    

 doc <- rowSums(doc) 
 doc <- doc[order(doc,decreasing=T)] 
 doc <- as.data.frame(doc[1:30])

#워드클라우드 생성
 pdf.options(family = "Korea1deb") #pdf 한글 옵션
 png(filename="cloudsociety.png",width=500,height=500) #png 이미지 저장
 wordcloud(words = rownames(doc),freq = doc$doc, min.freq=1, max.words=200, random.order=FALSE,rot.per=0.3,colors=brewer.pal(5,"Dark2"), scale=c(7,3))

#wordurl 확인
wordurl

#키워드를 따로 저장하기 위해 doc의 행 이름들을 keywords에 넣음
 keywords <- rownames(doc)
#Rdata 폴더 생성
 dir.create("./Rdata",showWarnings = F) 
#wordurl, keywords를 csv파일로 저장
 write.csv(wordurl,file=paste0("./Rdata/SOCIETYwordurl",".csv"),row.names = F)
 write.csv(wordspeech,file=paste0("./Rdata/SOCIETYwordspeech",".csv"),row.names = F)
 write.csv(wordtitle,file=paste0("./Rdata/SOCIETYwordtitle",".csv"),row.names = F)
 write.table(keywords,file=paste0("./Rdata/SOCIETYkeywords",".csv"),row.names = F,col.names = F)

#REconomy.r
naver_url <- 'http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=101'
html <- read_html(naver_url)
 temp <- c(unique(html_nodes(html,"#main_content .list_body .type06_headline a")%>%
                html_attr('href')),unique(html_nodes(html,"#main_content .list_body .type06 a")%>%
                                             html_attr('href')),repair_encoding(html_text(html_nodes(html,'.writing')),from = 'utf-8'))
 speech<-temp[21:40] #언론사명 저장 

 news_url <-c()
 news_url <- c(news_url,temp[1:20])


news_content <- c()
news_title <- c()
for (i in 1:length(news_url)){
  html <- read_html(news_url[i])
  temp <- repair_encoding(html_text(html_nodes(html,'#articleBodyContents')),from = 'utf-8')
  news_content <- c(news_content,temp)
  temp <- repair_encoding(html_text(html_nodes(html,'h3#articleTitle')),from = 'utf-8')
  news_title <- c(news_title, temp)
}
news <- cbind(url=news_url,content=unlist(news_content))
news <- as.data.frame(news)




# news_content에 뉴스 내용이 들어있어야 함



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
news_content<-gsub("㎡+","",news_content)


doc <- Corpus(VectorSource(news_content))
doc <- TermDocumentMatrix(doc,control=list(tokenize=words,removeNumbers=T,removePunctuation=T,wordLengths=c(3,Inf),
                                           stopwords=c("따라","이에","◆","【서울","newsis","총","이번","현재","시","월","에","높은","기록했다","기록하며","위를","위치했다","밝혔다","등의","중","최근","로","많","한다","때","있","은","는","이","가","의","위해","것","고","com","있다","및","을","를","수","일","등을","등","▶","디지털타임스","지디넷코리아","통해","바로가기","것으로","년","있는","지난","말했다","이를","한","flash")))

doc <- as.matrix(doc)

 #많이 나온 단어의 링크 추출
 wordurl <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 url을 담을 벡터 생성
 wordspeech <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 언론사명을 담을 행렬 생성   
 wordtitle <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 제목을 담을 행렬 생성   
 sortedword <- doc[rev(order(rowSums(doc))),]           #총 등장 횟수(행의 합)를 기준으로 정렬한 sortedword 벡터 생성
 for (i in 1:5){                       # i : 몇 개의 키워드의 url을 뽑을 것인지 
 k <- 0
 for (j in 1:20){                      # j : 몇 개의 기사를 검사할 것인지
if (sortedword[i,j] != 0) {            # i번째 순위 키워드가 j번째 기사에 포함될 경우 (0이 아닌 경우)
k <- k+1
wordurl[i,k] <- c(news_url[j])        # wordurl[i]에 j번째 기사의 url을 넣는다.
wordspeech[i,k] <- c(speech[j])       # wordurl[i]에 j번째 기사의 언론사명을 넣는다.
wordtitle[i,k] <- c(news_title[j])}}}    # wordurl[i]에 j번째 기사의 제목을 넣는다.

doc <- rowSums(doc) 
doc <- doc[order(doc,decreasing=T)] 
doc <- as.data.frame(doc[1:30])

#워드클라우드 생성 
pdf.options(family = "Korea1deb") #pdf 한글 옵션 
png(filename="cloudeconomy.png",width=500,height=500) #png 이미지 저장 
wordcloud(words = rownames(doc),freq = doc$doc, min.freq=1, max.words=200, random.order=FALSE,rot.per=0.3,colors=brewer.pal(5,"Dark2"), scale=c(7,3))

#wordurl 확인
wordurl

#키워드를 따로 저장하기 위해 doc의 행 이름들을 keywords에 넣음
 keywords <- rownames(doc)
#Rdata 폴더 생성
 dir.create("./Rdata",showWarnings = F) 
#wordurl, keywords를 csv파일로 저장
 write.csv(wordurl,file=paste0("./Rdata/ECONOMYwordurl",".csv"),row.names = F)
 write.csv(wordspeech,file=paste0("./Rdata/ECONOMYwordspeech",".csv"),row.names = F)
 write.csv(wordtitle,file=paste0("./Rdata/ECONOMYwordtitle",".csv"),row.names = F)
 write.table(keywords,file=paste0("./Rdata/ECONOMYkeywords",".csv"),row.names = F,col.names = F)

#Rpolitics.r
 naver_url <- 'http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=100'
 html <- read_html(naver_url)
 temp <- c(unique(html_nodes(html,"#main_content .list_body .type06_headline a")%>%
                html_attr('href')),unique(html_nodes(html,"#main_content .list_body .type06 a")%>%
                                             html_attr('href')),repair_encoding(html_text(html_nodes(html,'.writing')),from = 'utf-8'))
 speech<-temp[21:40] #언론사명 저장 

 news_url <-c()
 news_url <- c(news_url,temp[1:20])
 

 news_content <- c()
 news_title <- c()
 for (i in 1:length(news_url)){
     html <- read_html(news_url[i])
     temp <- repair_encoding(html_text(html_nodes(html,'#articleBodyContents')),from = 'utf-8')
     news_content <- c(news_content,temp)
     temp <- repair_encoding(html_text(html_nodes(html,'h3#articleTitle')),from = 'utf-8')
     news_title <- c(news_title, temp)
 }
  news <- cbind(url=news_url,content=unlist(news_content))
 news <- as.data.frame(news)




# news_content에 뉴스 내용이 들어있어야 함



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
 news_content<-gsub("될+","",news_content)
 news_content<-gsub("+","",news_content)
 news_content<-gsub("","",news_content)




 doc <- Corpus(VectorSource(news_content))
 doc <- TermDocumentMatrix(doc,control=list(tokenize=words,removeNumbers=T,removePunctuation=T,wordLengths=c(3,Inf),
stopwords=c("오전","오후","","며","월","많","당","전","이날","대해","한다","때","있","은","는","이","가","의","위해","것",">고","com","있다","photo","및","을","▲","를","수","일","등을","등","newsis","뉴시
스","사진","영상","뉴스","▶","디지털타임스","지디넷코리아","통해","바로가기","것
으로","년","있는","지난","말했다","이를","한","기자","또","고","", "flash","【")))

 doc <- as.matrix(doc)

 #많이 나온 단어의 링크 추출
 wordurl <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 url을 담을 벡터 생성
 wordspeech <- matrix(nrow=5, ncol=20)     #핫 키워드가 있는 기사의 언론사명을 담을 행렬 생성
 wordtitle <- matrix(nrow=5, ncol=20)      #핫 키워드가 있는 기사의 제목 담을 행렬 생성
 sortedword <- doc[rev(order(rowSums(doc))),]           #총 등장 횟수(행의 합)를 기준으로 정렬한 sortedword 벡터 생성
 for (i in 1:5){                       # i : 몇 개의 키워드의 url을 뽑을 것인지 
 k <- 0
 for (j in 1:20){                      # j : 몇 개의 기사를 검사할 것인지
if (sortedword[i,j] != 0) {            # i번째 순위 키워드가 j번째 기사에 포함될 경우 (0이 아닌 경우)
k <- k+1
wordurl[i,k] <- c(news_url[j])        # wordurl[i]에 j번째 기사의 url을 넣는다.
wordspeech[i,k] <- c(speech[j])       # wordurl[i]에 j번째 기사의 언론사명을 넣는다.
wordtitle[i,k] <- c(news_title[j])}}} # wordurl[i]에 j번째 기사의 제목을 넣는다.

 doc <- rowSums(doc) 
 doc <- doc[order(doc,decreasing=T)] 
 doc <- as.data.frame(doc[1:30])

#워드클라우드 생성
 pdf.options(family = "Korea1deb") #pdf 한글 옵션
 png(filename="cloudpolitics.png",width=500,height=500) #png 이미지 저장
 wordcloud(words = rownames(doc),freq = doc$doc, min.freq=1, max.words=200, random.order=FALSE,rot.per=0.3,colors=brewer.pal(5,"Dark2"), scale=c(7,3))

#wordurl 확인
wordurl

#키워드를 따로 저장하기 위해 doc의 행 이름들을 keywords에 넣음
 keywords <- rownames(doc)
#Rdata 폴더 생성
 dir.create("./Rdata",showWarnings = F) 
#wordurl, keywords를 csv파일로 저장
 write.csv(wordurl,file=paste0("./Rdata/POLITICSwordurl",".csv"),row.names = F)
 write.csv(wordspeech,file=paste0("./Rdata/POLITICSwordspeech",".csv"),row.names = F)
 write.table(keywords,file=paste0("./Rdata/POLITICSkeywords",".csv"),row.names = F,col.names = F)
 write.csv(wordtitle,file=paste0("./Rdata/POLITICSwordtitle",".csv"),row.names = F)
