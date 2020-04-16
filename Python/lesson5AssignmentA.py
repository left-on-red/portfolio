i = input("enter word: ")
r = ""
for c in range(len(i)):
    if (c % 2 != 0):
        r += i[c]
print(r)