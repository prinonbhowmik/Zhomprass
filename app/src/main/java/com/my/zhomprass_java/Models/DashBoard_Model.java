package com.my.zhomprass_java.Models;

public class DashBoard_Model {

    private String user_name;
    private String full_name;
    private String mobile_no;
    private String zpl_level;
    private int position;
    private int rank;
    private double total_earn;
    private String total_convert;
    private String total_withdraw;
    private double available;
    private double refer;
    private double generation;
    private double zpl;
    private double total_point;
    private double ranks;
    private double weekly;
    private double daily;
    private double monthly;
    private double dealer_spot;
    private double dealer_refer;
    private double dealer_royality;

    public DashBoard_Model(String user_name, String full_name, String mobile_no, String zpl_level, int position, int rank, double total_earn, String total_convert, String total_withdraw, double available, double refer, double generation, double zpl, double total_point, double ranks, double weekly, double daily, double monthly, double dealer_spot, double dealer_refer, double dealer_royality) {
        this.user_name = user_name;
        this.full_name = full_name;
        this.mobile_no = mobile_no;
        this.zpl_level = zpl_level;
        this.position = position;
        this.rank = rank;
        this.total_earn = total_earn;
        this.total_convert = total_convert;
        this.total_withdraw = total_withdraw;
        this.available = available;
        this.refer = refer;
        this.generation = generation;
        this.zpl = zpl;
        this.total_point = total_point;
        this.ranks = ranks;
        this.weekly = weekly;
        this.daily = daily;
        this.monthly = monthly;
        this.dealer_spot = dealer_spot;
        this.dealer_refer = dealer_refer;
        this.dealer_royality = dealer_royality;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getZpl_level() {
        return zpl_level;
    }

    public int getPosition() {
        return position;
    }

    public int getRank() {
        return rank;
    }

    public double getTotal_earn() {
        return total_earn;
    }

    public String getTotal_convert() {
        return total_convert;
    }

    public String getTotal_withdraw() {
        return total_withdraw;
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

    public double getZpl() {
        return zpl;
    }

    public double getTotal_point() {
        return total_point;
    }

    public double getRanks() {
        return ranks;
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

    public double getDealer_refer() {
        return dealer_refer;
    }

    public double getDealer_royality() {
        return dealer_royality;
    }
}
