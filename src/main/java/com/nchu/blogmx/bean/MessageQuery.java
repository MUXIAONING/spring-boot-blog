package com.nchu.blogmx.bean;

public class MessageQuery {
    private String touristName;

    public MessageQuery() {
    }

    public MessageQuery(String touristName) {
        this.touristName = touristName;
    }

    public String gettouristName() {
        return touristName;
    }

    public void settouristName(String touristName) {
        this.touristName = touristName;
    }
}
