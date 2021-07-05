package edu.wctc.isp.solution.part1;

import java.time.LocalDate;

public interface Book {

    String getAuthor();

    double getCost();

    String getFormattedReleaseDate();

    String getISBN();

    int getPages();

    int getQuantityOnHand();

    LocalDate getReleaseDate();

    String getTitle();

    void order(int quantity);

}
