package com.queasy.model.quiz;

public class Picture {
    private int id;
    private String picture;

    public Picture(int id, String picture) {
        this.id = id;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
