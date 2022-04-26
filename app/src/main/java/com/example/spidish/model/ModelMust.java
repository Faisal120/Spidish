package com.example.spidish.model;

public class ModelMust {

    String name;
    String description;
    String price;
    String quantity;
    String unit;
    int imgurl;

    public ModelMust(String name, String description, String price, String quantity, String unit, int imgurl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getImgurl() {
        return imgurl;
    }

    public void setImgurl(Integer imgurl) {
        this.imgurl = imgurl;
    }
}
