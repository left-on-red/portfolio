package edu.wctc.spring.impl.input;

import edu.wctc.spring.Sale;
import edu.wctc.spring.iface.SalesInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CliSalesInput implements SalesInput {
    @Override
    public List<Sale> getSales() {
        List<Sale> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int count = 0;
        boolean running = true;

        while (running) {
            count += 1;

            System.out.println("Sale #" + count + ":");

            System.out.print("Customer > ");
            String name = scanner.nextLine();

            System.out.print("Country > ");
            String country = scanner.nextLine();

            // scanner.nextDouble() was causing issues for scanner.nextLine()
            // when I was trying to receive the "another" input
            System.out.print("Amount > ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Tax > ");
            double tax = Double.parseDouble(scanner.nextLine());

            Sale sale = new Sale(name, country, amount, tax);
            list.add(sale);

            // n = no; anything else = yes
            System.out.print("\nAnother? (y/n) > ");
            String another = scanner.nextLine();

            if (another.equalsIgnoreCase("n")) { running = false; }
            else { System.out.print("\n"); }
        }

        scanner.close();

        return list;
    }
}
