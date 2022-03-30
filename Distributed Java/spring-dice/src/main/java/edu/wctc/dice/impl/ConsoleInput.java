package edu.wctc.dice.impl;

import edu.wctc.dice.iface.GameInput;
import org.springframework.stereotype.Component;

import java.util.Scanner;


public class ConsoleInput implements GameInput {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
