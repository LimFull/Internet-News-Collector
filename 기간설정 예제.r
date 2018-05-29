#!/usr/bin/Rscript --vanilla --slave 
# forkonlp's N2H4 사용 WITH MIT LISENCE
source('https://install-github.me/forkonlp/N2H4')
library(N2H4)

# 메인 카테고리 id 가져옵니다.
cate<-getMainCategory()
# 예시를 위해 하나만 선택합니다.
# 여기는 100(정치)를 선택했습니다.
tcate<-cate$sid1[1]
# 정치의 세부 카테고리를 가져옵니다.
subCate<-cbind(sid1=tcate,getSubCategory(sid1=tcate))
# 그중에 1번째, 2번째 세부 카테고리를 정했습니다.
tscate<-subCate$sid2[1:2]

# 목표 날짜를 지정합니다.
strDate<-"20160101"
endDate<-"20160102"

# 파일 이름을 지정하면 한 파일에 계속 저장합니다.
# "" 로 두면 페이지 별로 이름을 자동으로 생성하여 저장합니다.
save_path <- "./polrecord/pol3.csv"

strTime<-Sys.time()
midTime<-Sys.time()

for (date in strDate:endDate){
  for (sid1 in tcate){
    for (sid2 in tscate){
      
      print(paste0(date," / ",sid1," / ",sid2 ," / start Time: ", strTime," / spent Time: ", Sys.time()-midTime," / spent Time at first: ", Sys.time()-strTime))
      midTime<-Sys.time()
      
      # 뉴스 리스트 페이지의 url을 sid1, sid2, date로 생성합니다.
      pageUrl<-paste0("http://news.naver.com/main/list.nhn?sid2=",sid2,"&sid1=",sid1,"&mid=shm&mode=LS2D&date=",date)
      # 리스트 페이지의 마지막 페이지수를 가져옵니다.
      max<-getMaxPageNum(pageUrl)
      
      for (pageNum in 1:max){
        print(paste0(date," / ",sid1," / ",sid2," / ",pageNum, " / start Time: ", strTime," / spent Time: ", Sys.time()-midTime," / spent Time at first: ", Sys.time()-strTime))
        midTime<-Sys.time()
        # 페이지넘버를 포함한 뉴스 리스트 페이지의 url을 생성합니다.
        pageUrl<-paste0("http://news.naver.com/main/list.nhn?sid2=",sid2,"&sid1=",sid1,"&mid=shm&mode=LS2D&date=",date,"&page=",pageNum)
        # 뉴스 리스트 페이지 내의 개별 네이버 뉴스 url들을 가져옵니다.
        newsList<-getUrlListByCategory(pageUrl)
        newsData<-c()
        
        # 가져온 url들의 정보를 가져옵니다.
        for (newslink in newsList$links){
          ## 불러오기에 성공할 때 까지 반복합니다.
          # 성공할때 까지 반복하면 못나오는 문제가 있어서 5회로 제한합니다.
          tryi<-0
          tem<-try(getContent(newslink), silent = TRUE)
          while(tryi<=5&&class(tem)=="try-error"){
            tem<-try(getContent(newslink), silent = TRUE)
            tryi<-tryi+1
            print(paste0("try again: ",newslink))
          }
          if(class(tem$datetime)[1]=="POSIXct"){
            newsData<-rbind(newsData, tem)
          }
        }
        
        dir.create("./polrecord",showWarnings=F)
        if(save_path==""){
          # 가져온 뉴스들(보통 한 페이지에 20개의 뉴스가 있습니다.)을 csv 형태로 저장합니다.
          write.csv(newsData, file=paste0("./polrecord/news",sid1,"_",sid2,"_",date,"_",pageNum,".csv"),row.names = F)
        } else {
          if (!file.exists(save_path)){
            write.csv(newsData, file=save_path, row.names = F)
          }
          write.table(newsData, file=save_path, append=T, row.names = F, col.names = F, sep=",")
        }
      } 
    }
  }
}

politicsrecord <- read.csv('polrecord/pol3.csv',header = FALSE, stringsAsFactors = TRUE) # 저장한 기사의 내용들을 저장

precordbody<- data.frame(politicsrecord$V6) # 데이터 프레임 형태로 변환

write.table(precordbody, './polrecord/polrecordbody.txt') # txt 파일로 변환하여 저장



if (!requireNamespace("KoNLP")){ # 세종사전 사용하기 위한 라이브러리
  install.packages("KoNLP")
} 
library(KoNLP)





if (!requireNamespace("wordcloud")){ # 워드클라우드 사용하기 위한 라이브러리
  install.packages("wordcloud")
} 
library(wordcloud)

library(stringr) # 불필요 단어 필링 하기 위한 라이브러리



library(RColorBrewer) # 워드클라우드 관련 다양한 색상 사용 위한 라이브러리







useSejongDic()
txt<-readLines('./polrecord/polrecordbody.txt') # txt 파일화된 내용 불러들임
data2<-sapply(txt,extractNoun,USE.NAMES = F) # 단어 형태소 별로 추출
head(unlist(txt),30) # 추출된 단어들의 30 페이지 분량 배열 작성
data3<-unlist(data2) 
data3<-Filter(function(x){nchar(x)>=2},data3) # 필터링
data3<-gsub("\\d+","",data3)
data3<-str_replace_all(data3,"[^[:alpha:]]","")
data3<-gsub(" ","",data3)
data3<-gsub("-","",data3)
data3<-gsub("kr","",data3)
data3<-gsub("뉴스","",data3)

write(unlist(data3),"./polrecord/polextraction.txt") # 추출된 내용들 txt파일로 저장
rev<-read.table("./polrecord/polextraction.txt") # 테이블 형태로 불러들임
nrow(rev)
wordcount<-table(rev) # 워드 카운트에 단어 수 세서 저장
aa<-head(sort(wordcount,decreasing = T),20)  # 상위 20개 단어 추출하여 내림차순 저장
write.csv(aa,"./polrecord/polextraction_word.csv") # csv파일로 추출된 단어 저장
polkeywords1<-as.data.frame(aa[1:20]) 
polkeywords1

polkeywords<-polkeywords1[,"rev"] # 단어의 열만 추출하여 저장


write.table(polkeywords,file = paste0("./polrecord/polkeywords",".csv"),row.names = F,col.names = F) # 테이블 형태의 csv파일로 행열, 이름 제외후 



# 워드클라우드 생성
palete <- brewer.pal(9,"Set1")
wordcloud(names(aa),freq = aa, min.freq=7, max.words=20, random.order=FALSE,rot.per=0.5,colors=brewer.pal(5,"Dark2"), scale=c(6,2))
savePlot("./polrecord/polwordcloud.png",type = "png")  # png



