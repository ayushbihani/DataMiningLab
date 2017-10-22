data <- read.csv('input.csv')
print(data)
print(ncol(data))
print(nrow(data))

# Get all people working in IT 
ITpeople <- subset( data, Department == "IT")
print(ITpeople)

#export
write.csv(ITpeople,'output.csv')
outputdata <- read.csv('output.csv')
print(outputdata)





