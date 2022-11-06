package com.example.sampleapp;

public class ModelClass {
    String id,name,img;

    public ModelClass(String id, String name, String img){
        this.id=id;
        this.name=name;
        this.img=img;
    }
    public ModelClass(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

