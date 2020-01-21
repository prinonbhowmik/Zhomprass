package com.my.zhomprass_java.Models;

public class Thana {

    private int id;
    private int district_id;
    private String name;

    public Thana(int id, int district_id, String name) {
        this.id = id;
        this.district_id = district_id;
        this.name = name;
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
}
