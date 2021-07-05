package edu.wctc.isp.solution.part2;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VinylRecord implements Album {

    private final int SECONDS_IN_MINUTE = 60;
    private final int SECONDS_IN_HOUR = SECONDS_IN_MINUTE * 60;

    private String title;
    private double cost;
    private int quantityOnHand;
    private LocalDate releaseDate;
    private String artist;
    private Duration playingTime;

    public VinylRecord(String title, double cost, int quantityOnHand, LocalDate releaseDate, String artist, Duration playingTime) {
        this.title = title;
        this.cost = cost;
        this.quantityOnHand = quantityOnHand;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.playingTime = playingTime;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public double getCost() {
        return cost;
    }

    private String getFormattedPlayingTime() {
        long seconds = playingTime.getSeconds();
        return String.format(
                "%d:%02d",
                seconds / SECONDS_IN_HOUR,
                (seconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE);
    }

    @Override
    public String getFormattedReleaseDate() {
        return releaseDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public Duration getPlayingTime() {
        return playingTime;
    }

    @Override
    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void order(int quantity) {
        if (quantity > quantityOnHand) {
            throw new IllegalArgumentException("Cannot order more than are on hand");
        }
        quantityOnHand -= quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vinyl Record");
        sb.append("\n Title: ").append(title);
        sb.append("\n Artist: ").append(artist);
        sb.append("\n Cost: $").append(cost);
        sb.append("\n Playing time: ").append(getFormattedPlayingTime());
        sb.append("\n Release date: ").append(getFormattedReleaseDate());
        sb.append("\n Quantity on hand: ").append(quantityOnHand);
        return sb.toString();
    }
}
