package com.my.zhomprass_java.Models;

public class District {
    private int id;
    private int division_id;
    private String name;

    public District(int id, int division_id, String name) {
        this.id = id;
        this.division_id = division_id;
        this.name = name;
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
}
