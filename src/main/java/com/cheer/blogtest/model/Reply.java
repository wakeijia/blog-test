package com.cheer.blogtest.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Reply implements Serializable {
    private int id;
    private int bid;
    private int floor;
    private String rUsername;
    private String rTheme;
    private String replay;
    private String time;

    public Reply(){}

    public Reply(int bid,int floor,String rUsername,String rTheme,String replay){
        this.bid = bid;
        this.floor = floor;
        this.rUsername = rUsername;
        this.rTheme = rTheme;
        this.replay = replay;
    }
}
