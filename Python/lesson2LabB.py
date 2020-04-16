import math

total = float(input("please enter your money amount: "))

twenties = math.floor(total / 20)
if (twenties != 0):
    total = total - (twenties * 20)
    print("%s twenty dollar bills" % twenties)

tens = math.floor(total / 10)
if (tens != 0):
    total = total - (tens * 10)
    print("%s ten dollar bills" % tens)

fives = math.floor(total / 5)
if (fives != 0):
    total = total - (fives * 5)
    print("%s five dollar bills" % fives)

singles = math.floor(total)
if (singles != 0):
    total = total - singles
    print("%s single dollar bills" % singles)

quarters = math.floor(total / 0.25)
if (quarters != 0):
    total = total - (quarters * 0.25)
    print("%s quarters" % quarters)

dimes = math.floor(total / 0.10)
if (dimes != 0):
    total = total - (dimes * 0.10)
    print("%s dimes" % dimes)

pennies = math.floor(total / 0.01)
if (pennies != 0):
    total = total - (pennies * 0.01)
    print("%s pennies" % pennies)