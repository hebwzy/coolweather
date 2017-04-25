package com.zgmsy.dierhangdaimademo2.bean;

/**
 * class description here
 *
 * @author wzy
 * @version 1.0.0
 * @since 2017-04-25
 */
public class User {
    private String username;
    private String password;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}