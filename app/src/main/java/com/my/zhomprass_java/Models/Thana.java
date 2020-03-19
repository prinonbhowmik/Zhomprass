package com.my.zhomprass_java.Models;

public class Thana {

    private int id;
    private int district_id;
    private String name;
    private int total_shop;

    public Thana(int id, int district_id, String name, int total_shop) {
        this.id = id;
        this.district_id = district_id;
        this.name = name;
        this.total_shop = total_shop;
    }

    public int getId() {
        return id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_shop() {
        return total_shop;
    }
}
