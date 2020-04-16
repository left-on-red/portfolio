f = open("log.txt", "r").read()
lines = f.split("\n")

for i in range(len(lines) - 4):
    if lines[i+4] != "":
        segments = lines[i+4].split(" ")
        if segments[4] == "GET" and segments[5] == "/index.html":
            date = segments[0]
            ip1 = segments[3]
            ip2 = segments[9]
            print(date + "|index.html|" + ip1 + "|" + ip2)