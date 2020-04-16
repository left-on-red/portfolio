f = open("prideAndPrejudice.txt", "r").read()
lines = f.split("\n")
counts = {}
for l in lines:
    if (l != ""):
        splits = l.split(" ")
        for s in splits:
            if s.lower() not in counts: counts[s.lower()] = 1
            else: counts[s.lower()] += 1

highestC = -1
highestW = ""
for k, v in counts.items():
    if v > highestC:
        highestC = v
        highestW = k

print(highestW + " " + str(highestC))