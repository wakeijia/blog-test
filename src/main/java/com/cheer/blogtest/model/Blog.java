package com.cheer.blogtest.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Blog implements Serializable {
    private int id;
    private String bUsername;
    private String bTheme;
    private String message;
    private String time;

    public Blog(){}

    public Blog(String bUsername,String bTheme,String message){
        this.bUsername = bUsername;
        this.bTheme = bTheme;
        this.message = message;
    }
}
