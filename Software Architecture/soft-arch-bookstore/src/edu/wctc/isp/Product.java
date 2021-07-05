package edu.wctc.isp;

import java.time.Duration;
import java.time.LocalDate;

public interface Product {

    String getArtist();

    String getAuthor();

    double getCost();

    String getFormattedReleaseDate();

    String getISBN();

    int getPages();

    Duration getPlayingTime();

    int getQuantityOnHand();

    LocalDate getReleaseDate();

    String getTitle();

    void order(int quantity);

}
