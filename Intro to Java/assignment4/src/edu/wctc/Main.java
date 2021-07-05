package edu.wctc;

import java.awt.*;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;

public class Main {
    private static Scanner keyboard = new Scanner(System.in);
    private static PaintCalculator paintCalculator = new PaintCalculator();

    private static void printMenu() {
        String[] options = {
          "add room",
          "view rooms",
          "read rooms from file",
          "write rooms to file",
          "exit program"
        };

        for (int i = 0; i < options.length; i++) {  System.out.printf("\033[0;34m%d.\033[0m %s\n", i+1, options[i]); }
    }

    private static double promptForDimension(String dimensionName) {
        boolean running = true;
        double converted = -1;
        while (running) {
            System.out.printf("\033[0;35m%s\033[0m > ", dimensionName);
            String input = keyboard.nextLine();
            try {
                converted = Double.parseDouble(input);
                if (converted > 0) { running = false; }
            }

            catch(NumberFormatException ignored) {}

            if (running) {  System.out.printf("\033[1;31minvalid %s was provided\033[0m\n", dimensionName); }
        }

        return converted;
    }

    // not sure if this is the "correct" way to do this, but since Scanner blocks execution, I might as well do things like this
    private static void createRoom() { paintCalculator.addRoom(promptForDimension("length"), promptForDimension("height"), promptForDimension("width")); }

    private static void readFile() {
        try {
            Scanner fileReader = new Scanner(new File("data.csv"));
            while (fileReader.hasNext()) {
                String room = fileReader.nextLine();
                if (room == "[empty]") { break; }

                String[] dimensions = room.split(Pattern.quote(","));

                try {
                    double x = Double.parseDouble(dimensions[0]);
                    double y = Double.parseDouble(dimensions[1]);
                    double z = Double.parseDouble(dimensions[2]);
                    paintCalculator.addRoom(x, y, z);
                }

                // this would just trigger the outer catch block and print out the error text rather than just recycling the same code up here
                catch (NumberFormatException e) { throw new FileNotFoundException(); }
            }

            System.out.println("\033[0;32mdata has successfully been read from data.csv\033[0m");
        }

        catch (FileNotFoundException e) { System.out.println("\033[0;31ma data.csv file does not exist to read from...\033[0m"); }
    }

    private static void writeFile() {

        try {
            PrintWriter printWriter = new PrintWriter("data.csv");
            printWriter.print(paintCalculator.toString());
            printWriter.flush();
            printWriter.close();

            System.out.println("\033[0;32mdata has successfully been written to data.csv\033[0m");
        }

        catch (IOException e) { System.out.println("\033[0;31mthere was an error writing to data.csv...\033[0m"); }
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("> ");

            String input = keyboard.nextLine();

            // add room
            if (input.equals("1")) { createRoom(); }

            // view rooms
            else if (input.equals("2")) { System.out.println(paintCalculator.toString()); }

            // read rooms from file
            else if (input.equals("3")) { readFile(); }

            // write rooms to file
            else if (input.equals("4")) { writeFile(); }

            // exit program
            else if (input.equals("5")) { break; }
        }
    }
}
