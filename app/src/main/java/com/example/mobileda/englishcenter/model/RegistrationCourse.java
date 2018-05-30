package com.example.mobileda.englishcenter.model;

import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class RegistrationCourse  {
    private String course;
    private String room;
    private String time;
    private String state;
    private String cost;
    private DocumentReference teacher;

    private RegistrationCourse() {
    }

    public RegistrationCourse(String course, String time, String state, String room, String cost, DocumentReference teacher) {
        this.course = course;
        this.room = room;
        this.time = time;
        this.state = state;
        this.cost = cost;
        this.teacher = teacher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
        this.state = state;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public DocumentReference getTeacher() {
        return teacher;
    }

    public void setTeacher(DocumentReference teacher) {
        this.teacher = teacher;
    }

}
