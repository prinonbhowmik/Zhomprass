package com.my.zhomprass_java.Models;

public class UserInfo {
    private int login_success;
    private int customer_id;

    public UserInfo(int login_success, int customer_id) {
        this.login_success = login_success;
        this.customer_id = customer_id;
    }

    public int getLogin_success() {
        return login_success;
    }

    public int getCustomer_id() {
        return customer_id;
    }
}
