package com.example.skincareapp;

public class SkinCareInfo {
    String name, description, goodFor, treating;

    //state of the item
    boolean expanded;

    public SkinCareInfo() {}

    public SkinCareInfo(String name, String description, String goodFor, String treating) {
        this.name = name;
        this.description = description;
        this.goodFor = goodFor;
        this.treating = treating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isExpanded(){
        return expanded;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoodFor() {
        return goodFor;
    }

    public void setGoodFor(String goodFor) {
        this.goodFor = goodFor;
    }

    public String getTreating() {
        return treating;
    }

    public void setTreating(String treating) {
        this.treating = treating;
    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }
}
