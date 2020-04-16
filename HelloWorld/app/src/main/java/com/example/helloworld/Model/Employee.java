package com.example.helloworld.Model;

public class Employee {
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

    String getAge() {
        return age;
    }

    String getName() {
        return name;
    }

    String getProfileImage() {
        return profileImage;
    }

    String getSalary() {
        return salary;
    }
}
