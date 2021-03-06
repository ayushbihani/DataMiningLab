#Explore Data

dataInput <- read.csv('input.csv')
print(dataInput)
print(dim(dataInput))
print(names(dataInput))
print(summary(dataInput))
str(dataInput)
#Get only specific coolumns
newData<-data.frame(dataInput$name,dataInput$Salary,dataInput$Performance)
print(newData)
#get max salary of employee
maxSalary<- max(dataInput$Salary)
print(maxSalary)
details<- subset(dataInput,Salary = maxSalary)
print(details)

#Simple Data Visualization
#histoitgram of Salary
hist(newData$Salary)
hist(newData$Performace)

#PieChart of departments
depts = array(unique(dataInput$Department,incomparables=FALSE))
deptTable = table(dataInput$Department)
piePercent = round((100*deptTable)/sum(deptTable),1)
pie(deptTable,main="Departments",labels = piePercent)
legend("topleft",depts)

#scatter plot
yrange<- dataInput$Salary
xrange<- dataInput$Performance
plot(xrange, yrange,xlab="Performance",ylab="Salary")
title("performace vs salary plot")






