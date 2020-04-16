arr = []
running = True

while (running):
    num = int(input("Number of Steps(-1): "))
    if (num != -1): arr.append(num)
    else: running = False

print("Total steps %i number of days %i average steps %f." % (sum(arr), len(arr), sum(arr) / len(arr)))
print("The most steps in one day is %i and the lowest is %i." % (max(arr), min(arr)))