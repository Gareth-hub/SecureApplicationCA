package com.example.staff;

public class Staff {
    private int id;
    private String name;
    private String email;
    private int age;
    private int salary;

    public Staff(int id, String name, String email, int age, int salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

}
