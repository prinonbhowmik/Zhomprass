package com.my.zhomprass_java.Models;

public class ShopList {
    private int shop_id;
    private String bazar_id;
    private int owner_mobile;
    private String shop_name;

    public int getShop_id() {
        return shop_id;
    }

    public String getBazar_id() {
        return bazar_id;
    }

    public int getOwner_mobile() {
        return owner_mobile;
    }

    public String getShop_name() {
        return shop_name;
    }

    public ShopList(int shop_id, String bazar_id, int owner_mobile, String shop_name) {
        this.shop_id = shop_id;
        this.bazar_id = bazar_id;
        this.owner_mobile = owner_mobile;
        this.shop_name = shop_name;
    }
}
