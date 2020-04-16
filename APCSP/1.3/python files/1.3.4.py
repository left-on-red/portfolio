from __future__ import print_function
import random

def guess_once():
    secret = random.randint(1, 4)
    print('I have a number between 1 and 4.')
    guess = int(raw_input('Guess: '))
    if guess != secret:
        if (guess > secret):
            print('Too high, my number was ', str(secret), '.', sep='')
        elif (guess < secret):
            print('Too low - my number was ', str(secret), '!', sep='')
    else:
        print('Right on! I was number ', str(guess), end='!\n')

def quiz_decimal(low, high):
    response = raw_input('Type a number between ' + str(low) + ' and ' + str(high) + ': ')
    if (float(response) > high or float(response < low)):
        if (float(response) > high):
            print('No, ' + response + ' is greater than ' + str(high))
        if (float(response) < low):
            print('No, ' + response + ' is less than ' + str(low))
    else:
        print('Good! ' + str(low) + ' < ' + response + ' < ' + str(high))

def food_id(food):
    ''' Returns categorization of food
    
    food is a string
    returns a string for categories
    '''
    # The data
    fruits = ['apple', 'banana', 'orange']
    citrus = ['orange']
    starchy = ['banana', 'potato']
    
    # Check the category and report
    if food in fruits:
        if food in citrus:
            return 'Citrus, Fruit'
        else:
            return 'NOT Citrus, Fruit'
    else:
        if food in starchy:
            return 'Starchy, NOT Fruit'
        else:
            return 'NOT Starchy, NOT Fruit'
            
def food_id_test():
    ''' Unit test for food_id
    returns True if good, returns False and prints error if not
    good
    '''
    works = True
    if food_id('orange') != 'Citrus, Fruit':
        works = False
        print('orange bug in food_id()')
    if food_id('banana') != 'NOT Citrus, Fruit':
        works = False
        print('banana bug in food_id()')
    # Add tests so that all likes of code are visited during test
    
    if works:
        print('food_id() passed all test')
        return works
        
def f(x):
    if (type(x) is int):
        if (x % 2 == 0):
            if (x % 3 == 0):
                return str(x) + ' is a multiple of 6'
            else:
                return str(x) + ' is an even number'
        else:
            return str(x) + ' is an odd number'
    else:
        return('please specify an integer')