package com.task.blogs;

public class posts {

    private String title;
    private String body;

    public posts(String title, String body){
        this.title =title;
        this.body = body;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }
}
