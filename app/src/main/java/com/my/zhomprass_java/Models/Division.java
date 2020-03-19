package com.my.zhomprass_java.Models;

public class Division {
    private int id;
    private String name;
    private int total_shop;

    public Division(int id, String name, int total_shop) {
        this.id = id;
        this.name = name;
        this.total_shop = total_shop;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_shop() {
        return total_shop;
    }
}
