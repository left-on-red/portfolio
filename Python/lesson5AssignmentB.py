in1 = input("describe an item of clothing that is a knitted top that is half way between black and white: ").lower()
in2 = input("how would you Describe paying for it using a bank note? ").lower()

isBritish = False;
isAmerican = False;
if (in1.find("sweater") != -1 or in1.find("gray") != -1 or in2.find("check") != -1): isAmerican = True
if (in1.find("pullover") != -1 or in1.find("grey") != -1 or in2.find("cheque") != -1): isBritish = True

classification = "awful at answering questions"
if (isBritish and not isAmerican): classification = "a Brit"
if (isAmerican and not isBritish): classification = "an American"

print("you are %s" % classification)