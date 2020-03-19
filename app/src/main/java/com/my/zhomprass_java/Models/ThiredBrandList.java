package com.my.zhomprass_java.Models;

public class ThiredBrandList {
    private int id;
    private int cat_id;
    private int sub_cat_id;
    private int third_cat_id;
    private String brand_name;
    private int position;
    private boolean status;
    private String image;
    private int total_product;

    public ThiredBrandList(int id, int cat_id, int sub_cat_id, int third_cat_id, String brand_name, int position, boolean status, String image, int total_product) {
        this.id = id;
        this.cat_id = cat_id;
        this.sub_cat_id = sub_cat_id;
        this.third_cat_id = third_cat_id;
        this.brand_name = brand_name;
        this.position = position;
        this.status = status;
        this.image = image;
        this.total_product = total_product;
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

    public String getBrand_name() {
        return brand_name;
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
