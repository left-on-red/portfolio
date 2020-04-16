arr = []
num = int(input("Please Enter the number of Squares: "))
for n in range(num):
    grains = 1
    if (len(arr) == 0): arr.append(grains)
    else:
        grains = arr[n-1] * 2
        arr.append(grains)
    print("Square %i %i grains." % (n+1, grains))