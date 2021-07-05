package edu.wctc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Player player = new Player();
	    Maze maze = new Maze(player);

        Scanner scanner = new Scanner(System.in);

        while (!maze.isFinished()) {
            System.out.printf("\u001B[33m%s\u001B[0m\n", maze.getCurrentRoomDescription());
            System.out.printf("\u001B[36m%s\u001B[0m\n", maze.getCurrentRoomExits());
            System.out.println("commands:\n- \u001B[34mn\u001B[0morth\n- \u001B[34ms\u001B[0mouth\n- \u001B[34me\u001B[0mast\n- \u001B[34mw\u001B[0mest\n- \u001B[34mu\u001B[0mp\n- \u001B[34md\u001B[0mown\n- \u001B[34mi\u001B[0mnteract\n- \u001B[34ml\u001B[0moot\n- e\u001B[34mx\u001B[0mit\n- in\u001B[34mv\u001B[0mentory\n(a successful exit means that the maze is finished.)");
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() != 1) { System.out.printf("\u001B[31m%s\u001B[0m\n", "invalid command..."); }
            else {
                char command = input.toCharArray()[0];

                // direction
                if (command == 'n' || command == 's' || command == 'e' || command == 'w' || command == 'u' || command == 'd') {
                    String direction = Room.charToStringDirection(command);
                    if (maze.move(command)) { System.out.printf("you headed %s\n", direction); }
                    else { System.out.printf("this room doesn't have a %s exit\n", direction); }
                }

                // interact
                else if (command == 'i') { System.out.println(maze.interactWithCurrentRoom()); }

                // loot
                else if (command == 'l') { System.out.println(maze.lootCurrentRoom()); }

                // exit
                else if (command == 'x') { System.out.println(maze.exitCurrentRoom()); }

                // inventory
                else if (command == 'v') { System.out.println(maze.getPlayerInventory()); }

                else { System.out.printf("\u001B[31m%s\u001B[0m\n", "invalid command..."); }
            }
        }
    }
}
