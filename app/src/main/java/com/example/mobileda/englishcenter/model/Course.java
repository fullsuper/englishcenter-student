package com.example.mobileda.englishcenter.model;

import com.google.firebase.firestore.DocumentReference;

/**
 * Created by fullsuper on 5/19/2018.
 */

public class Course {
    private int imgCourse;
    private String time;
    private String state;
    private String room;
    private String subject;
    private DocumentReference teacher;

    public Course(int imgCourse, String subject,String time,String room,String state,DocumentReference teacher){
        this.imgCourse = imgCourse;
        this.time = time ;
        this.state = state;
        this.room = room;
        this.subject = subject;
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getImgCourse() {
        return imgCourse;
    }

    public void setImgCourse(int imgCourse) {
        this.imgCourse = imgCourse;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        state = state;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public DocumentReference getTeacher() {
        return teacher;
    }

    public void setTeacher(DocumentReference teacher) {
        this.teacher = teacher;
    }
}
