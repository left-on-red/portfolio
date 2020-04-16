import random

def roll_two_dice():
    dice = []
    dice.append(random.randint(1, 6))
    dice.append(random.randint(1, 6))
    return dice[0] + dice[1]

def guess_letter():
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    return random.choice(alphabet)