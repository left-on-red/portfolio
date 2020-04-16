import pygame

class snake:
    size = 5
    head = [5, 5]
    trail = []
    facing = ""
    color = (255, 255, 255)
    kill_color = (200, 200, 200)
    moving = False
    dead = False

    field = False

    def __init__(self, pos):
        self.head = pos

    def check_collision(self):
        movable = True
        for t in self.trail:
            if (self.head[0] == t[0] and self.head[1] == t[1]): movable = False
        
        if (self.head[0] >= self.field.gridWidth or self.head[0] <- -1 or self.head[1] >= self.field.gridHeight or self.head[1] <= -1): movable = False

        if (not movable): self.kill()

        if (self.head[0] == self.field.food[0] and self.head[1] == self.field.food[1]):
            movable = False
            self.expand()
            self.field.create_food()
        
        return movable

    def kill(self):
        #pygame.draw.rect(self.field.window, (200, 200, 200), pygame.Rect(self.head[0] * self.field.density, self.head[1] * self.field.density, self.field.density, self.field.density))
        self.facing = ""
        self.trail = []
        self.head = [20, 20]
        self.moving = False
        self.dead = True
        self.field.die()

    def expand(self):
        before = self.head
        if (self.facing == "n"): self.head = [self.head[0], self.head[1] - 1]
        if (self.facing == "e"): self.head = [self.head[0] + 1, self.head[1]]
        if (self.facing == "s"): self.head = [self.head[0], self.head[1] + 1]
        if (self.facing == "w"): self.head = [self.head[0] - 1, self.head[1]]

        self.trail.insert(0, before)

        self.check_collision()
        self.field.update()

    def bind_field(self, field): self.field = field

    def set_direction(self, direction):
        if self.moving:
            changable = True
            if (self.facing == "n" and direction == "s"): changable = False
            elif (self.facing == "e" and direction == "w"): changable = False
            elif (self.facing == "s" and direction == "n"): changable = False
            elif (self.facing == "w" and direction == "e"): changable = False

            if (changable): self.facing = direction
        else:
            self.facing = direction
            self.moving = True

    def update(self):
        if (not self.dead):
            if (self.facing != ""):
                if (len(self.trail) < self.size): self.expand()
                else:
                    if (self.check_collision()):
                        updated = []
                        before = [self.head[0], self.head[1]]
                        if (self.facing == "n"): self.head = [self.head[0], self.head[1] - 1]
                        elif (self.facing == "e"): self.head = [self.head[0] + 1, self.head[1]]
                        elif (self.facing == "s"): self.head = [self.head[0], self.head[1] + 1]
                        elif (self.facing == "w"): self.head = [self.head[0] - 1, self.head[1]]

                        for t in range(len(self.trail)):
                            if (t == 0): updated.append(before)
                            else: updated.append(self.trail[t-1])
                        
                        self.trail = updated
                        self.field.update()