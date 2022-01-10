package com.example.aerodromeApp.dataModel;

public class data_model_gridView {

    int image,price;
    String header,description;

    public data_model_gridView(int image, String header, String description, int price) {
        this.image = image;
        this.header = header;
        this.description = description;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
