package com.example.mobileda.englishcenter.model;

import java.util.Date;

public class Student {
    private String name;
    private Date birthday;
    private String literacy;
    private String address;

    public Student() {}
    public Student(String name, Date birthday, String literacy, String address) {
        this.name = name;
        this.birthday = birthday;
        this.literacy = literacy;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLiteracy() {
        return literacy;
    }

    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
