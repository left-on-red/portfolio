package edu.wctc.isp.solution.part1;

import java.time.Duration;
import java.time.LocalDate;

public interface Album {

    String getArtist();

    double getCost();

    String getFormattedReleaseDate();

    Duration getPlayingTime();

    int getQuantityOnHand();

    LocalDate getReleaseDate();

    String getTitle();

    void order(int quantity);

}
