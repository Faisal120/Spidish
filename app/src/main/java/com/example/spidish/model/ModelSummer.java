package com.example.spidish.model;

public class ModelSummer {
    String name;
    String quantity;
    String price;
    int img;

    public ModelSummer(String name, String quantity, String price, int img) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
