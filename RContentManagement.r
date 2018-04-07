# KoNLP 패키지 필요
if (!requireNamespace("KoNLP")){
  install.packages("KoNLP")
} 
library(KoNLP)


# tm 패키지 필요
if (!requireNamespace("tm")){
  install.packages("tm")
} 
library(tm)

# wordcloud 패키지 필요
if (!requireNamespace("wordcloud")){
  install.packages("wordcloud")
} 
library(wordcloud)

# news_content에 뉴스 내용이 들어있어야 함 (crawling code 참조)

library(stringr)

# 문장의 형태소 분석을 위해 세종사전 불러옴
 useSejongDic()

# 불필요한 문장 제거
 news_content<-gsub("\n\t\n\t\n\n\n\n// flash 오류를 우회하기 위한 함수 추가\nfunction _flash_removeCallback()","",news_content)
 news_content<-gsub("\t","",news_content)
 news_content<-gsub("\n","",news_content)
 news_content<-gsub("\\{\\}","",news_content)
 news_content<-gsub("\\(\\)","",news_content)
 news_content<-gsub("사진=","",news_content)
 news_content<-gsub("기자]","",news_content)
 news_content<-gsub("기자)","",news_content)
 news_content<-gsub("지디넷코리아)","",news_content)
 news_content<-gsub("\\(지디넷코리아","",news_content)
 news_content<-gsub("▶.+","",news_content)
 news_content<-gsub("♥.+","",news_content)

 
 words <- function(doc){
 doc<- as.character(doc)
 doc2<-paste(SimplePos22(doc))
 doc3<-str_match(doc2,"([가-힣]+)/NC")
 doc4<-doc3[,2]
 doc4[!is.na(doc4)]
 }


 doc <- Corpus(VectorSource(news_content))
 doc <- TermDocumentMatrix(doc,control=list(tokenize=words,removeNumbers=T,removePunctuation=T,wordLengths=c(3,Inf),
                                            stopwords=c("은","는","이","가","com","있다","및","을","를","수","일","등을","등","▶")))

 doc <- as.matrix(doc)
 doc <- rowSums(doc) 
 doc <- doc[order(doc,decreasing=T)] 
 doc <- as.data.frame(doc[1:30])
 doc

#워드클라우드 생성
 wordcloud(words = rownames(doc),freq = doc$doc, min.freq=1, max.words=200, random.order=FALSE,rot.per=0.3,colors=brewer.pal(5,"Dark2"), scale=c(3,1))



#------------------result---------------------
             doc[1:30]
무궁화          18
호              18
멜론            13
모든            10
년              10
위성            10
추천            10
그는             9
기자             9
위해             9
통해             9
월               9
위성을           9
vip              9
금지             8
재배포           8
abs에            8
블록체인         7
이더리움         7
이번             7
넷마블은         7
말했다           7
콘텐츠를         7
같은             6
다양한           6
대한             6
밝혔다           6
글로벌           6
kt와             6
내렸다           6