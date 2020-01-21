package com.my.zhomprass_java.Models;

public class ThiredCategoryList {
    private int id;
    private int cat_id;
    private int sub_cat_id;
    private String sub_category_name;
    private int position;
    private boolean status;
    private String image;

    public int getId() {
        return id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public int getSub_cat_id() {
        return sub_cat_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
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

    public ThiredCategoryList(int id, int cat_id, int sub_cat_id, String sub_category_name, int position, boolean status, String image) {
        this.id = id;
        this.cat_id = cat_id;
        this.sub_cat_id = sub_cat_id;
        this.sub_category_name = sub_category_name;
        this.position = position;
        this.status = status;
        this.image = image;
    }
}
