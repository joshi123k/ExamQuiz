package com.exam.model;

public class JWTresponse {

    private String token;

    public JWTresponse(String token) {
        this.token = token;
    }

    public JWTresponse() {
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
