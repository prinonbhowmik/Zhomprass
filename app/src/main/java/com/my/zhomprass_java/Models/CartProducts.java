package com.my.zhomprass_java.Models;

public class CartProducts {

    private int id;
    private int cat_id;
    private int sub_cat_id;
    private int third_cat_id;
    private String product_name;
    private int price;
    private int point;
    private int quantity;
    private String image;

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public int getSub_cat_id() {
        return sub_cat_id;
    }

    public int getThird_cat_id() {
        return third_cat_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getPrice() {
        return price;
    }

    public int getPoint() {
        return point;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartProducts(int id, int cat_id, int sub_cat_id, int third_cat_id, String product_name, int price, int point, int quantity, String image) {
        this.id = id;
        this.cat_id = cat_id;
        this.sub_cat_id = sub_cat_id;
        this.third_cat_id = third_cat_id;
        this.product_name = product_name;
        this.price = price;
        this.point = point;
        this.quantity = quantity;
        this.image = image;
    }
}
