package models;

import java.util.UUID;

public class User {
    private String name;
    private int age;
    private String id;

    // Constructors

    public User(){
        this.name = "";
        this.age = 0;
        this.id = "";
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = generateUniqueID();
    }

    // Id Generator
    private static String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Show User infos
    @Override
    public String toString() {
        return "---------------------------------\n" +
                "id          : " + id + "\n" +
                "name        : " + name + "\n" +
                "age         : " + age;
    }
}