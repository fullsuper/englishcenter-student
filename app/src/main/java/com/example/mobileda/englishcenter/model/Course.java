package com.example.mobileda.englishcenter.model;

/**
 * Created by fullsuper on 5/19/2018.
 */

public class Course {
    private int imgCourse;
    private String classId;
    private String subject;

    public Course(int imgCourse,String classID, String subject){
        this.imgCourse =imgCourse;
        this.classId =classID;
        this.subject = subject;
    }


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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
}
