intrusions = int(input("Please enter the number of intrusions: "))
days = int(input("Please enter the number of days tracked: "))
average = intrusions / days
status = "null"
if (average >= 200):
    status = "critical"
elif (average >= 100 and average < 200):
    status = "high"
elif (average >= 20 and average < 100):
    status = "average"
elif(average < 20):
    status = "low"

print("%i %s" % (average, status))