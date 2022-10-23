package com.example.projektam;

import java.util.List;

public class User {
    public String login;
    public List<String> roles;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", roles=" + roles +
                '}';
    }
}
