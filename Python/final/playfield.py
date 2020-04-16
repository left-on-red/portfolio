import random
import pygame
import snake

class playfield:
    width = 600
    height = 600

    window = False

    density = 5

    gridWidth = width / density
    gridHeight = height / density

    color = (0, 0, 0)
    food_color = (255, 255, 0)

    player = snake.snake([20, 20])
    food = []

    dead = False
    paused = False

    def __init__(self):
        self.window = pygame.display.set_mode((self.width, self.height))
        self.player.bind_field(self)

    def create_food(self):
        x = random.randint(0, self.gridWidth)
        y = random.randint(0, self.gridHeight)

        collides = False
        if (self.player.head[0] == x and self.player.head[1] == y): collides = True
        for t in self.player.trail:
            if (t[0] == x and t[1] == y): collides = True

        if (collides): self.create_food()
        else: self.food = [x, y]

    def update(self):
        self.window.fill(self.color)

        pygame.draw.rect(self.window, self.food_color, pygame.Rect(self.food[0] * self.density, self.food[1] * self.density, self.density, self.density))
        pygame.draw.rect(self.window, self.player.color, pygame.Rect(self.player.head[0] * self.density, self.player.head[1] * self.density, self.density, self.density))

        for t in self.player.trail:
            pygame.draw.rect(self.window, self.player.color, pygame.Rect(t[0] * self.density, t[1] * self.density, self.density, self.density))
        pygame.display.update()

    def die(self):
        self.dead = True
        self.create_food()

    def start(self):
        running = True
        self.create_food()
        clock = pygame.time.Clock()
        while running:

            pygame.time.delay(1)
            clock.tick(10)

            for event in pygame.event.get():
                if (event.type == pygame.QUIT): running = False
            
            keys = pygame.key.get_pressed()
            if (keys[pygame.K_w]): self.player.set_direction("n")
            if (keys[pygame.K_d]): self.player.set_direction("e")
            if (keys[pygame.K_s]): self.player.set_direction("s")
            if (keys[pygame.K_a]): self.player.set_direction("w")
            if (keys[pygame.K_SPACE]):
                pygame.time.delay(200)
                if (not self.dead and self.paused and not self.player.moving): self.paused = False
                elif (not self.dead and not self.paused and not self.player.moving):
                    self.paused = True
                    pygame.time.delay(500)
                elif (self.dead):
                    self.dead = False
                # if (self.dead): self.dead = False
                # if (not self.dead): self.paused = True
                # if (self.paused): self.paused = False
            
            if (not self.paused and not self.dead):
                self.player.dead = False
                self.player.update()
        pygame.quit()
        