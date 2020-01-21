package com.my.zhomprass_java.Models;

public class UserShortInfo {
    private String zpl;
    private int position;
    private int rank;
    private double available_balance;

    public UserShortInfo(String zpl, int position, int rank, double available_balance) {
        this.zpl = zpl;
        this.position = position;
        this.rank = rank;
        this.available_balance = available_balance;
    }

    public String getZpl() {
        return zpl;
    }

    public int getPosition() {
        return position;
    }

    public int getRank() {
        return rank;
    }

    public double getAvailable_balance() {
        return available_balance;
    }
}
