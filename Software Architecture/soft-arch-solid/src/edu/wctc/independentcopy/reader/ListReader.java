package edu.wctc.independentcopy.reader;

import java.util.ArrayList;
import java.util.Scanner;

public class ListReader implements Reader {
    @Override
    public String readln() {
        System.out.println("Enter a list separated by line breaks; (empty line break to submit):");
        String list = "";
        Scanner input = new Scanner(System.in);
        String line;
        do {
            line = input.nextLine();
            list += line + "\n";
        }

        while(!line.equals(""));
        input.close();

        return list;
    }
}
