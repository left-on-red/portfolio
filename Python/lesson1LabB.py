age = int(input("Please enter your Age: "))
key = int(input("Please enter your favorite number: "))
encrypted = age ^ key
print("Encrypted number: %s" % encrypted)
unencrypted = encrypted ^ key
print("Unencrypted age: %s" % unencrypted)
