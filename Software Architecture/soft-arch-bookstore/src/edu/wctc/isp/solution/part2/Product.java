package edu.wctc.isp.solution.part2;

import java.time.LocalDate;

public interface Product {

    double getCost();

    String getFormattedReleaseDate();

    int getQuantityOnHand();

    LocalDate getReleaseDate();

    String getTitle();

    void order(int quantity);

}
