arr = [
    '11.16.211.2',
    '133.1.1.111',
    '201.22.3.41',
    '17.55.23.123',
    '144.211.32.45'
]

def getType(str):
    first = str.split('.')[0]
    toReturn = 'A'
    if (int(first) >= 128): toReturn = 'B'
    if (int(first) >= 192): toReturn = 'C'
    return toReturn

for i in range(len(arr)): print("%s is Class %s" % (arr[i], getType(arr[i])))