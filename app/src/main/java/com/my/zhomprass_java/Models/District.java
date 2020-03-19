package com.my.zhomprass_java.Models;

public class District {
    private int id;
    private int division_id;
    private String name;
    private int total_shop;

    public District(int id, int division_id, String name, int total_shop) {
        this.id = id;
        this.division_id = division_id;
        this.name = name;
        this.total_shop = total_shop;
    }

    public int getId() {
        return id;
    }

    public int getDivision_id() {
        return division_id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_shop() {
        return total_shop;
    }
}
