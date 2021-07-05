package edu.wctc.isp.solution.part2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HardcoverBook implements Book {

    private String title;
    private String isbn;
    private double cost;
    private int quantityOnHand;
    private LocalDate releaseDate;
    private String author;
    private int pageCount;

    public HardcoverBook(String title, String isbn, double cost, int quantityOnHand, LocalDate releaseDate, String author, int pageCount) {
        this.title = title;
        this.isbn = isbn;
        this.cost = cost;
        this.quantityOnHand = quantityOnHand;
        this.releaseDate = releaseDate;
        this.author = author;
        this.pageCount = pageCount;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String getFormattedReleaseDate() {
        return releaseDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String getISBN() {
        return isbn;
    }

    @Override
    public int getPages() {
        return pageCount;
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
        StringBuilder sb = new StringBuilder("Hardcover Book: ");
        sb.append("\n Title: ").append(title);
        sb.append("\n Author: ").append(author);
        sb.append("\n Cost: $").append(cost);
        sb.append("\n ISBN: ").append(isbn);
        sb.append("\n Pages: ").append(pageCount);
        sb.append("\n Release date: ").append(getFormattedReleaseDate());
        sb.append("\n Quantity on hand: ").append(quantityOnHand);
        return sb.toString();
    }

}
