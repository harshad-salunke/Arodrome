package com.example.aerodromeApp.food_and_beverages_activities;

public class custom_arrayList_food_and_beverages_page {

    int image,price;
    String header;
    String description;
    String size;
    String inStock,nutrients;

    public custom_arrayList_food_and_beverages_page(int image, int price, String header, String description, String size, String inStock, String nutrients) {
        this.image = image;
        this.price = price;
        this.header = header;
        this.description = description;
        this.size = size;
        this.inStock = inStock;
        this.nutrients = nutrients;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public String getNutrients() {
        return nutrients;
    }

    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }
}
