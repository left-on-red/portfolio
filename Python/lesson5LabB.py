message = input("enter message: ")
key = int(input("enter the number of characters to shift (1-26): "))

def en(d, k):
    cyc = "abcdefghijklmnopqrstuvwxyz"
    out = []
    for i in d:
        ii = cyc.find(i.lower())
        if ii != -1:
            ni = ii + k
            if len(cyc) <= ni: ni = ni - len(cyc)
            if i.isupper(): out.append(cyc[ni].upper())
            else: out.append(cyc[ni])
        else: out.append(i)
    return "".join(out)

def de(d, k):
    cyc = "abcdefghijklmnopqrstuvwxyz"
    out = []
    for i in d:
        ii = cyc.find(i.lower())
        if ii != -1:
            ni = ii - k
            if ni < 0: ni = ni + len(cyc)
            if i.isupper(): out.append(cyc[ni].upper())
            else: out.append(cyc[ni])
        else: out.append(i)
    return "".join(out)
    
print(en(message, key))
print(de(en(message, key), key))
