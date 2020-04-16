import random

print("welcome to guess the number!")
upper = int(input("\nenter the upper limit: "))

rand = random.randint(0, upper)
limited = input("\ndo you want to have limited guesses? (y/n)").lower().startswith("y")

limit = -1
if (limited):
    limit = int(input("\nhow many guess do you want to be limited to? "))

tries = 0

found = False
while (found == False and limit != 0):
    guess = int(input("\nguess the secret number: "))
    if (guess == rand):
        found = True
        tries += 1
        print("\ncorrect")
        print("\ntook %i tries" % tries)
    else:
        limit -= 1
        tries += 1

        if (limit != 0):
            if (guess > rand):
                print("\nlower")
            else:
                print("\nhigher")
        
        if (limited):
            if (limit == 0):
                print("no more guesses; the number was %i" % rand)
            else:
                print("\nguesses left: %i" % limit)