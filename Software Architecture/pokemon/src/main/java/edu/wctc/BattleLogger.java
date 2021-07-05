package edu.wctc;

import java.io.*;
import java.util.Scanner;

public class BattleLogger extends FileWriter {
    private static BattleLogger logger;
    private static final Scanner in = new Scanner(System.in);

    private BattleLogger() throws IOException { super("pokemon.log"); }

    // this was a required method that needed to be implemented from FileWriter
    // but I don't really have a use for it with the way I've designed things
    @Override
    public void write(String str) {
        try {
            super.write(str);
            super.flush();
        }

        catch(IOException e) {}
    }

    /**
     * prints to the console and writes to the log file
     * @param str
     */
    public static void print(String str) {
        checkLogger();
        logger.write(str);
        System.out.print(str);
    }

    /**
     * prints to the console and writes to the log file with a linebreak at the end
     * @param str
     */
    public static void println(String str) { print(str + "\n"); }

    /**
     * prints a string and then reads the next line of user input and returns it
     * @param str
     * @return
     */
    public static String readln(String str) {
        print(str);
        String next = in.nextLine();
        logger.write(next + "\n");
        return next;
    }

    /**
     * reads the next line of user input and returns it
     * @return user input
     */
    public static String readln() { return readln(""); }

    /**
     * validates that the logger singleton has been instantiated. if not, it instantiates it
     */
    private static void checkLogger() {
        try { if (logger == null) { logger = new BattleLogger(); } }
        catch (IOException e) {}
    }
}
