package com.my.zhomprass_java.Models;

public class RecentProducts {

    private int id;
    private int cat_id;
    private int sub_cat_id;
    private int third_cat_id;
    private String product_name;
    private String product_description;
    private int price;
    private int point;
    private boolean status;
    private String image;

    public RecentProducts(int id, int cat_id, int sub_cat_id, int third_cat_id, String product_name, String product_description, int price, int point, boolean status, String image) {
        this.id = id;
        this.cat_id = cat_id;
        this.sub_cat_id = sub_cat_id;
        this.third_cat_id = third_cat_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.price = price;
        this.point = point;
        this.status = status;
        this.image = image;
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

    public String getProduct_description() {
        return product_description;
    }

    public int getPrice() {
        return price;
    }

    public int getPoint() {
        return point;
    }

    public boolean isStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
}
