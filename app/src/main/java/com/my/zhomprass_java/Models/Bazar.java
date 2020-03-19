package com.my.zhomprass_java.Models;

public class Bazar {
    private int id;
    private int thana_id;
    private String name;
    private int total_shop;

    public Bazar(int id, int thana_id, String name, int total_shop) {
        this.id = id;
        this.thana_id = thana_id;
        this.name = name;
        this.total_shop = total_shop;
    }

    public int getId() {
        return id;
    }

    public int getThana_id() {
        return thana_id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_shop() {
        return total_shop;
    }
}
