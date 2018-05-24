package com.example.mobileda.englishcenter.model;

/**
 * Created by fullsuper on 5/19/2018.
 */

public class Message {
    private int imageTitle;
    private String title;
    private String content;

    public Message(int imageTitle, String title, String content) {
        this.imageTitle = imageTitle;
        this.title = title;
        this.content = content;
    }

    public int getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(int imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
