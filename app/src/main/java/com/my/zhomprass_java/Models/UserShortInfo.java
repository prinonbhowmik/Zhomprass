package com.my.zhomprass_java.Models;

public class UserShortInfo {

    private String user_name;
    private String full_name;
    private String mobile_no;
    private double total_earn;
    private String total_withdraw;
    private String total_convert;
    private double available;
    private double refer;
    private double generation;
    private double zpl_balance;
    private int total_point;
    private double rank_balance;
    private double weekly;
    private double daily;
    private double monthly;
    private double dealer_spot;
    private double dealer_referal;
    private double dealer_royalty;
    private String zpl;
    private int position;
    private int rank;
    private double available_balance;


    public String getUser_name() {
        return user_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public double getTotal_earn() {
        return total_earn;
    }

    public String getTotal_withdraw() {
        return total_withdraw;
    }

    public String getTotal_convert() {
        return total_convert;
    }

    public double getAvailable() {
        return available;
    }

    public double getRefer() {
        return refer;
    }

    public double getGeneration() {
        return generation;
    }

    public double getZpl_balance() {
        return zpl_balance;
    }

    public int getTotal_point() {
        return total_point;
    }

    public double getRank_balance() {
        return rank_balance;
    }

    public double getWeekly() {
        return weekly;
    }

    public double getDaily() {
        return daily;
    }

    public double getMonthly() {
        return monthly;
    }

    public double getDealer_spot() {
        return dealer_spot;
    }

    public double getDealer_referal() {
        return dealer_referal;
    }

    public double getDealer_royalty() {
        return dealer_royalty;
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

    public UserShortInfo(String user_name, String full_name, String mobile_no, double total_earn, String total_withdraw, String total_convert, double available, double refer, double generation, double zpl_balance, int total_point, double rank_balance, double weekly, double daily, double monthly, double dealer_spot, double dealer_referal, double dealer_royalty, String zpl, int position, int rank, double available_balance) {
        this.user_name = user_name;
        this.full_name = full_name;
        this.mobile_no = mobile_no;
        this.total_earn = total_earn;
        this.total_withdraw = total_withdraw;
        this.total_convert = total_convert;
        this.available = available;
        this.refer = refer;
        this.generation = generation;
        this.zpl_balance = zpl_balance;
        this.total_point = total_point;
        this.rank_balance = rank_balance;
        this.weekly = weekly;
        this.daily = daily;
        this.monthly = monthly;
        this.dealer_spot = dealer_spot;
        this.dealer_referal = dealer_referal;
        this.dealer_royalty = dealer_royalty;
        this.zpl = zpl;
        this.position = position;
        this.rank = rank;
        this.available_balance = available_balance;
    }

    public UserShortInfo() {
    }
}
