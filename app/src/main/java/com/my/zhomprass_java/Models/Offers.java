package com.my.zhomprass_java.Models;

public class Offers {
    private int id;
    private int cat_id;
    private int sub_cat_id;
    private int third_cat_id;
    private int brand_id;
    private int market_id;
    private int shop_id;
    private String product_name;
    private String product_description;
    private double price;
    private double offer;
    private int offer_type;
    private int point;
    private Boolean status;
    private String image;

    public Offers(int id, int cat_id, int sub_cat_id, int third_cat_id, int brand_id, int market_id, int shop_id, String product_name, String product_description, double price, double offer, int offer_type, int point, Boolean status, String image) {
        this.id = id;
        this.cat_id = cat_id;
        this.sub_cat_id = sub_cat_id;
        this.third_cat_id = third_cat_id;
        this.brand_id = brand_id;
        this.market_id = market_id;
        this.shop_id = shop_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.price = price;
        this.offer = offer;
        this.offer_type = offer_type;
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

    public int getBrand_id() {
        return brand_id;
    }

    public int getMarket_id() {
        return market_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public double getPrice() {
        return price;
    }

    public double getOffer() {
        return offer;
    }

    public int getOffer_type() {
        return offer_type;
    }

    public int getPoint() {
        return point;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
}
