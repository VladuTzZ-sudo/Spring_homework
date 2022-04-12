package com.example.cursul1_advanced;

import java.util.Random;
import java.util.UUID;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private static Integer incrementalId = 0;

    public static User createUser() {
        Random random = new Random();
        User x = new User();

        x.id = incrementalId++;
        x.name = String.valueOf(new UUID(30, 0));
        x.age = random.nextInt(30);
        return x;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
