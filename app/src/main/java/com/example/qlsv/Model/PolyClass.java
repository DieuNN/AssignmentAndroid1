package com.example.qlsv.Model;

public class PolyClass {
    private String Id;
    private String name;

    public PolyClass(String id, String name) {
        Id = id;
        this.name = name;
    }


    public PolyClass() {
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
}
