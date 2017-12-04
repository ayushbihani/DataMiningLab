#party - install this package 
#load the , if package does not install you're f**ked
str(iris)
set.seed(1234)
#index to separate 70% as train and 30% test
ind <- sample(2, nrow(iris),replace = TRUE, prob = c(0.7,0.3))
trainData <- iris[ind ==1,]
testData <- iris[ind ==2,]

#Building the tree
library(party)
myFormula <- Species ~ Sepal.Length + Sepal.Width + Petal.Length + Petal.Width 
iris_ctree <-ctree(myFormula, data = trainData)

#check the prediction for 1.training set 2.plotting tree 3.test set 
table(predict(iris_ctree), trainData$Species)
plot(iris_ctree)
plot(iris_ctree, type = "simple")
testPred <- predict(iris_ctree, newdata = testData)
table(testPred, testData$Species)
#printing the rules
print(iris_ctree)