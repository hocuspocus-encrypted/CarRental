package com.carRental.service;

import java.io.FileNotFoundException;

public class Car {

    private int stockID;
    private String name;
    private String model;
    private String manufacturer;
    private String color;
    private int year;
    private boolean availability;
    private double pricePerDay;

    public Car(int stockID, String name, String model, String manufacturer, String color, int year, boolean availability, double pricePerDay) {
        this.stockID = stockID;
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.color = color;
        this.year = year;
        this.availability = availability;
        this.pricePerDay = pricePerDay;
    }

    public void book()
    {
        availability = false;
    }


    // Getter and Setter methods for stockID
    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for model
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // Getter and Setter methods for manufacturer
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Getter and Setter methods for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Getter and Setter methods for year
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Getter and Setter methods for availability
    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    // Getter and Setter methods for pricePerDay
    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(stockID).append(",");
        sb.append(name).append(",");
        sb.append(model).append(",");
        sb.append(manufacturer).append(",");
        sb.append(color).append(",");
        sb.append(year).append(",");
        sb.append(availability).append(",");
        sb.append(pricePerDay);
        return sb.toString();
    }
}
