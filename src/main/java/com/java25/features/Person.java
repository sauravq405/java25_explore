package com.java25.features;

public class Person {
    private String name;
    private int age;

    // Parameterized public constructor //deleted 'public' access specifier
     Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters (optional but common for POJOs)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Optional: toString() for easy printing
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}

