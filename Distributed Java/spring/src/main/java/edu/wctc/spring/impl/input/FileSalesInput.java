package edu.wctc.spring.impl.input;

import edu.wctc.spring.Sale;
import edu.wctc.spring.iface.SalesInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSalesInput implements SalesInput {

    @Override
    public List<Sale> getSales() {
        List<Sale> list = new ArrayList<>();

        try {
            File file = new File("sales.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] commas =  line.split(",");

                String name = commas[0];
                String country = commas[1];

                try {
                    double amount = Double.parseDouble(commas[2]);
                    double tax = Double.parseDouble(commas[3]);

                    list.add(new Sale(name, country, amount, tax));
                }

                catch (NumberFormatException e) { e.printStackTrace(); }
            }
        }

        catch (FileNotFoundException e) { e.printStackTrace(); }

        return list;
    }
}
