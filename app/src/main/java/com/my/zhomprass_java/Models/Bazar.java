package com.my.zhomprass_java.Models;

public class Bazar {
    private int id;
    private int thana_id;
    private String name;

    public int getId() {
        return id;
    }

    public int getThana_id() {
        return thana_id;
    }

    public String getName() {
        return name;
    }

    public Bazar(int id, int thana_id, String name) {
        this.id = id;
        this.thana_id = thana_id;
        this.name = name;
    }
}
