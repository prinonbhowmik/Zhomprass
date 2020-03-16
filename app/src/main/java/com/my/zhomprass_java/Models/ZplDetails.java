package com.my.zhomprass_java.Models;

public class ZplDetails {
    private int id;
    private String date;
    private String level;
    private String balance;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLevel() {
        return level;
    }

    public String getBalance() {
        return balance;
    }

    public ZplDetails(int id, String date, String level, String balance) {
        this.id = id;
        this.date = date;
        this.level = level;
        this.balance = balance;
    }


}
