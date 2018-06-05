package com.example.mobileda.englishcenter.model;

import com.google.firebase.firestore.DocumentReference;

/**
 * Created by fullsuper on 5/19/2018.
 */

public class Message {
    private String title;
    private String content;
    private DocumentReference teacher;

    public Message() {
    }
    public Message(String title, String content,DocumentReference teacher) {
        this.title = title;
        this.content = content;
        this.teacher = teacher;
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

    public DocumentReference getTeacher() {
        return teacher;
    }

    public void setTeacher(DocumentReference teacher) {
        this.teacher = teacher;
    }
}
