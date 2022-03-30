package edu.wctc.spring;

public class Sale {
    private String name;
    private String country;
    private double amount;
    private double tax;

    private double shipping = 0.0;

    public Sale(String name, String country, double amount, double tax) {
        this.name = name;
        this.country = country;
        this.amount = amount;
        this.tax = tax;
    }

    public String getName() { return this.name; }
    public String getCountry() { return this.country; }
    public double getAmount() { return this.amount; }
    public double getTax() { return this.tax; }

    public double getShipping() { return this.shipping; }
    public void setShipping(double shipping) { this.shipping = shipping; }
}
