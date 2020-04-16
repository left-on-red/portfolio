intrusions = int(input("Please enter the number of intrusions: "))
days = int(input("Please enter the number of days tracked: "))
average = intrusions / days

def getStatus(num):
    toReturn = "null"
    if (num >= 200):
        toReturn = "critical"
    elif (num >= 100 and num < 200):
        toReturn = "high"
    elif (num >= 20 and num < 100):
        toReturn = "average"
    elif(num < 20):
        toReturn = "low"
    return toReturn;

print("%i %s" % (average, getStatus(average)))