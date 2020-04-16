print("Gaming Tool")
a = int(input("\nhow many A's did you earn last Semester? "))
b = int(input("\nhow many B's did you earn last Semester? "))
c = int(input("\nhow many grades below a C did you earn last Semester? "))
homework = int(input("\nhow many hours of homework? "))
housework = int(input("\nhow many hours of housework? "))
sports = int(input("\nhow many hours of sports? "))
print("---------------------------------------------")

money = 0
money += (a * 150)
money += (b * 75)
money -= (c * 100)

hours = 0
hours += (homework / 2)
hours += (housework / 3)
hours += (sports / 4)

if (money >= 500):
    print("you may buy a gaming system")
else:
    print("you may not buy a gaming system")

print("you have earned %i hours of play time" % hours)