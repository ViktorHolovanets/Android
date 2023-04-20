package com.study.books.model;

public class ImageSourseModel {
    private String imgResource;

    public String getImgResource() {
        return this.imgResource;
    }

    public void setFlagResource(String img) {
        this.imgResource = img;
    }
    public ImageSourseModel(String img){
        this.imgResource=img;
    }
}
