arr = [
    "11.16.211.2",
    "133.1.1.111",
    "201.22.3.41",
    "17.55.23.123",
    "144.211.32.45"
]

def getType(ip):
    first = ip.split('.')[0]
    toReturn = "A"
    if (int(first) >= 128): toReturn = "B"
    if (int(first) >= 192): toReturn = "C"
    return toReturn

def getHost(ip):
    netType = getType(ip);
    first = ip.split(".")[0]
    toReturn = first
    if (netType != "C" or netType == "A"): toReturn += (".%s" % ip.split(".")[1])
    if (netType == "A"): toReturn += (".%s" % ip.split(".")[2])
    return toReturn

for i in range(len(arr)): print("%s is Class %s Host %s" % (arr[i], getType(arr[i]), getHost(arr[i])))