c = input("Name to convert to Acronym: ")
acr = ""
for i in range(len(c.split(" "))): acr += c.split(" ")[i][0].upper()
print(acr)