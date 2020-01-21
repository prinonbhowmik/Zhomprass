package com.my.zhomprass_java.Models;

public class DeleteProducts {

    private int position;
    private CartProducts cartProducts;

    public DeleteProducts(int position, CartProducts cartProducts) {
        this.position = position;
        this.cartProducts = cartProducts;
    }

    public int getPosition() {
        return position;
    }

    public CartProducts getCartProducts() {
        return cartProducts;
    }
}
