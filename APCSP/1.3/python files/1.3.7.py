def days():
    for day in 'MTWRFSS':
        print (day + 'day')
    for day in range(5, 8):
        print('It is the ' + str(day) + 'th of September')
        
import matplotlib.pyplot as plt
import random

plt.ion()

def picks():
    a = []
    
    a += [random.choice([1, 3, 10])]
    
    for choices in range(5):
        a += [random.choice([1, 3, 10])]
        
    plt.hist(a)
    plt.show()
    
def roll_hundred_pair():
    dice = []
    for i in range(100):
        dice.append(random.randint(1, 6))
    plt.hist(dice)
    plt.show()
    
def dice(n):
    dice = []
    total = 0
    for r in range(n):
        dice.append(random.randint(1, 6))
    for i in range(len(dice)):
        total += dice[i]
    print('Roll was ' + total)
    
def hangman_display(guessed, secret):
    output = ''
    for s in range(len(secret)):
        character = ''
        if (secret[s] == ' '):
            character = ' '
        else:
            for g in range(len(guessed)):
                if (secret[s] == guessed[g]):
                    character = secret[s]
            if (character == ''):
                character = '-'
        output += character
    return output
    
def matches(ticket, winners):
    matches = 0
    if (type(ticket) == list) and (type(winners) == list):
        if (len(ticket) == len(winners)):
            for t in range(len(ticket)):
                for w in range(len(winners)):
                    if (ticket[t] == winners[w]):
                        matches += 1
    if (matches == len(ticket)):
        print('You just won the lottery!')
    else:
        print('There\'s ' + str(matches) + ' number(s) in common')
        
def report(guess, secret):
    inPlace = 0
    colors = []
    if (type(guess) == list) and (type(secret) == list):
        if (len(guess) == len(secret)):
            for i in range(len(guess)):
                if (guess[i] == secret[i]):
                    inPlace += 1
            for s in range(len(secret)):
                for g in range(len(guess)):
                    if (guess[g] == secret[s]):
                        colors.append(guess[g])
            colors = set(colors)
            return (str(inPlace) + ' values in the same place and ' + str(len(colors)) + ' colors in common')