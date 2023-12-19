package com.example.demo;

public class Cat {

    private int id;
    private int age;
    private String name;

    Cat(int age, String name) {
        this.age = age;
        this.name = name;
    }

    protected Cat() {}

    public Object getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
