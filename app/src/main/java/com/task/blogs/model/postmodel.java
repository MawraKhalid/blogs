package com.task.blogs.model;

public class postmodel {

    String pImage, PTitle , PDescription;


    public postmodel(){

    }
    public postmodel(String pImage, String PTitle, String PDescription) {
        this.pImage = pImage;
        this.PTitle = PTitle;
        this.PDescription = PDescription;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getPTitle() {
        return PTitle;
    }

    public void setPTitle(String PTitle) {
        this.PTitle = PTitle;
    }

    public String getPDescription() {
        return PDescription;
    }

    public void setPDescription(String PDescription) {
        this.PDescription = PDescription;
    }
}
