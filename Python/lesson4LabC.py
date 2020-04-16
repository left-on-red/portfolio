toSave = float(input("Amount to Save up for: "))
payment = float(input("Monthly payment: "))
interest = float(input("Annual Interest: "))

saved = 0
counter = 0
while (toSave > saved):
    toAdd = float(saved * (interest / 100 / 12))
    toAdd += payment
    saved += toAdd
    counter += 1
    print("Month %i has $%s saved." % (counter, "{:,.2f}".format(round(saved, 2))))
print("%i months to save $%s" % (counter, '{:,.2f}'.format(round(saved, 2))))