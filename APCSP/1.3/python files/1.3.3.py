from __future__ import print_function # use Python 3.0 printing

def age_limit_output(age):
    '''Step 6a if-else example'''
    AGE_LIMIT = 13 #convention: use CAPS for constants
    if age < AGE_LIMIT:
        print(age, 'is below the age limit.')
    else:
        print(age, 'is old enough.')
    print(' Minimum age is ', AGE_LIMIT)
    
def vowel(letter):
    if (len(letter) == 1):
        vowels = 'aeiouAEIOU'
        if letter in vowels:
            return True
        else:
            return False
    else:
        print('You can only input one letter at a time!')

def inRectangle(x, y):
    return x >= 40 and x <= 130 and y >= 100 and y <= 120
    
def report_grade(percent):
    if (type(percent) is int):
        if (percent <= 100 and percent >= 0):
            if (percent < 85):
                print('A grade of ' + str(percent) + ' does not indicate mastery.')
                print('Seek extra practice or help.')
            else:
                print('A grade of ' + str(percent) + ' percent indicates mastery.')
                print('Keep up the good work!')
        else:
            print('Enter a number from 0-100')
    else:
        print('Enter a number')
        
def hint(color, secret):
    if (type(secret) is list):
        if (type(color) is str):
            if color in secret:
                print('The color ' + color + ' IS in the secret sequence of colors.')
                return True
            else:
                print('The color' + color + ' IS NOT in the secret sequence of colors.')
                return False
        else:
            print('The color has to be a string')
    else:
        print('The secret has to be an array')