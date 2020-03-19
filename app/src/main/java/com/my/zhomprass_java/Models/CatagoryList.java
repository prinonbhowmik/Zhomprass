package com.my.zhomprass_java.Models;

public class CatagoryList {
    private int id;
    private String category_name;
    private int position;
    private boolean status;
    private String image;
    private int total_product;

    public CatagoryList(int id, String category_name, int position, boolean status, String image, int total_product) {
        this.id = id;
        this.category_name = category_name;
        this.position = position;
        this.status = status;
        this.image = image;
        this.total_product = total_product;
    }

    public int getId() {
        return id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public int getTotal_product() {
        return total_product;
    }
}
