main = [
    "add a new task",
    "mark task as completed",
    "tasks yet to be done",
    "exit"
]

todo = []
done = []

print("todo list")
running = True

while(running):
    for i in range(len(main)): print("%i) %s" % (i+1, main[i]))
    action = input("which do you want to do ==> ")
    if (action == "1"):
        toAdd = input("new task ==> ")
        todo.append(toAdd)
    elif (action == "2"):
        for i in range(len(todo)): print("%i) %s" % (i+1, todo[i]))
        #for i in range(len(todo)): print(i + ") " + todo[i])
        toMark = input("which one did you complete ==> ")
        toMark = int(toMark)-1
        if (toMark > len(todo)): print("that item doesn't exist")
        else:
            popped = todo.pop(toMark)
            done.append(popped)
    elif (action == "3"):
        for i in range(len(todo)): print("%i) %s" % (i+1, todo[i]))
    elif (action == "4"): running = False
    else: print("invalid input")
print("thanks for using todo list™")