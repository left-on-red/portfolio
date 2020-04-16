for i in range(5):
    email = input("enter an email address: ")
    if (email.count("@") == 1):
        domain = email.split("@")[1]
        split = domain.split(".")
        print(split[len(split) - 2])
    else: print("that's an invalid email address")