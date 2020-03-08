package com.my.zhomprass_java.Models;

public class DashboardDetails {
    private int id;
    private String date;
    private String from_user_name;
    private String balance;

    public DashboardDetails(int id, String date, String from_user_name, String balance) {
        this.id = id;
        this.date = date;
        this.from_user_name = from_user_name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getFrom_user_name() {
        return from_user_name;
    }

    public String getBalance() {
        return balance;
    }
}
