package com.example.androidnetworking_lab.Lab4;

import retrofit2.Call;

public class SvrResponseInsert {
    private Products product;
    private String message;

    public SvrResponseInsert(Products product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

}
