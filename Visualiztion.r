tdm <- TermDocumentMatrix(doc,control=list(tokenize=words))
tdm2 <- as.matrix(tdm)
tdm3 <- rowSums(tdm2)
tdm4=head(sort(tdm3,decreasing = T),5)
tdm4

#pie(tdm4,col=rainbow(5),radius = 1)
# pie chart





if (!requireNamespace("wordcloud")){
  install.packages("wordcloud")
} 
library(wordcloud)

if (!requireNamespace("RColorBrewer")){
  install.packages("RColorBrewer")
} 
library(RColorBrewer)

palete <- brewer.pal(9,"Set1")
x11()
wordcloud(
  names(tdm3),
  freq = tdm4,
  scale=c(5,1),
  rot.per = 0.5,
  min.freq=7,
  random.order=F,
  random.color = T,
  colors=palete
  
)


#as.data.frame(tdm3)
