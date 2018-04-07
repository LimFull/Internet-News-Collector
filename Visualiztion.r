tdm <- TermDocumentMatrix(doc,control=list(tokenize=words))
tdm2 <- as.matrix(tdm)
tdm3 <- rowSums(tdm2)
tdm4=head(sort(tdm3,decreasing = T),5)
tdm4

#pie(tdm4,col=rainbow(5),radius = 1)
# pie chart




