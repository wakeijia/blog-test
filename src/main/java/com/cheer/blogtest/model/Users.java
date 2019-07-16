package com.cheer.blogtest.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Users implements Serializable {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private String avatar;
    private String time;

    public Users(){
    }

    public Users(String username,String password,String email,String phoneNum,String avatar){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.avatar = avatar;
    }
}
