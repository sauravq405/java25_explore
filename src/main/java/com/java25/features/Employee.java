package com.java25.features;

public class Employee extends Person{
    Employee(String name, int age) {
        if(age > 18){
            System.out.println("Eligible for job");
        }
        super(name, age);
        System.out.println("Employee constructor called");
    }
}
