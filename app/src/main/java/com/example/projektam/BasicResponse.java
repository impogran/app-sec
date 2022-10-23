package com.example.projektam;

import java.util.List;

public class BasicResponse {
    public String message;
    public List<String> allEndpoints;

    @Override
    public String toString() {
        return "User{" +
                "message='" + message + '\'' +
                ", all endpoints=" + allEndpoints +
                '}';
    }
}
