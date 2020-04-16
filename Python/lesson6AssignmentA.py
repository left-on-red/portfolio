arr = []
while (True):
    inp = input("please enter an item to purchase (NA to end): ")
    if (inp.upper() == "NA"): break
    arr.append(inp)
arr.reverse()
print("\n".join(arr))