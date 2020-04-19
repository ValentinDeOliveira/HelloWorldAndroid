package com.example.hello_world.Model.Structures;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private String salary;
    private String age ;
    private String profileImage;

    public Employee(String name, String salary, String age, String profileImage){
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profileImage = profileImage;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getSalary() {
        return salary;
    }
}
