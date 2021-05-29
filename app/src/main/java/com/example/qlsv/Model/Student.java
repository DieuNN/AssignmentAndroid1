package com.example.qlsv.Model;

public class Student {
    private String Id;
    private String name;
    private String className;
    private String DOB;

    public Student(String id, String name, String className, String DOB) {
        Id = id;
        this.name = name;
        this.className = className;
        this.DOB = DOB;
    }

    public Student() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
