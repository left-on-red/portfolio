import math

seconds = int(input("enter the number of seconds: "))

days = math.floor(seconds / 86400)
seconds = seconds - (days * 86400)

hours = math.floor(seconds / 3600)
seconds = seconds - (hours * 3600)

minutes = math.floor(seconds / 60)
seconds = seconds - (minutes * 60)

print("%s days %s hours %s minutes %s seconds" % (days, hours, minutes, seconds))