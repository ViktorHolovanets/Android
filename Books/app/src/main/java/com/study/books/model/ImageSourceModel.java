package com.study.books.model;

public class ImageSourceModel {
    private String imgResource;

    public String getImgResource() {
        return this.imgResource;
    }

    public void setFlagResource(String img) {
        this.imgResource = img;
    }
    public ImageSourceModel(String img){
        this.imgResource=img;
    }
}
