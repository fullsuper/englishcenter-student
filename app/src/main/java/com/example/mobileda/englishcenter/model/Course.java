package com.example.mobileda.englishcenter.model;

import com.google.firebase.firestore.DocumentReference;

/**
 * Created by fullsuper on 5/19/2018.
 */

public class Course {
   private String course;
   private DocumentReference teacher;

   public Course(){

    }
    public Course(String course,DocumentReference teacher){
        this.course = course;
        this.teacher = teacher;
    }
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public DocumentReference getTeacher() {
        return teacher;
    }

    public void setTeacher(DocumentReference teacher) {
        this.teacher = teacher;
    }
}
