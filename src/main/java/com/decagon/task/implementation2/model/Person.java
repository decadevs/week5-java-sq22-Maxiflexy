package com.decagon.task.implementation2.model;

import com.decagon.task.enums.Role;

public class Person {
    private String name;
    private int age;
    private Role role;
    private Book requestedBook;

    public Person(String name, int age, Role role, Book requestedBook) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.requestedBook = requestedBook;
    }

    public Book getRequestedBook() {
        return requestedBook;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age='" + age +
                "', role='" + role + "', requestedBook='" + requestedBook + "}";
    }

}
