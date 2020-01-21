package com.my.zhomprass_java.Models;

public class SubCategoryList {

    private int id;
    private int cat_id;
    private String sub_category_name;
    private String position;
    private boolean status;
    private String image;

    public SubCategoryList(int id, int cat_id, String sub_category_name, String position, boolean status, String image) {
        this.id = id;
        this.cat_id = cat_id;
        this.sub_category_name = sub_category_name;
        this.position = position;
        this.status = status;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public String getPosition() {
        return position;
    }

    public boolean isStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
}
