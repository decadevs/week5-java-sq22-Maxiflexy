package com.decagon.task.implementation1.model;

import com.decagon.task.enums.Role;

public class Person {                                                                           //implements Comparable<Person>{
    private final String name;
    private final Role role;
    private final Book requestedBook;

    public Person(String name, Role role, Book requestedBook) {
        this.name = name;
        this.role = role;
        this.requestedBook = requestedBook;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Book getRequestedBook() {
        return requestedBook;
    }

    @Override
    public String toString() {
        return "Person{name='" + name +
                "', role='" + role + "', requestedBook='" + requestedBook + "}";
    }

    //@Override
//    public int compareTo(Person otherPerson) {   // this method uses the natural ordering of enum values to compare the roles directly.
//        return this.role.compareTo(otherPerson.role);
//    }

//    private int getRolePriority(Role role){   //returns an integer representation of the role passes in.
//        switch(role){
//            case TEACHER :
//                return 3;
//            case SENIOR_STUDENT:
//                return 2;
//            case JUNIOR_STUDENT:
//                return 1;
//            default:
//                return 0;
//        }
////        return switch (role) {
////            case TEACHER -> 3;
////            case SENIOR_STUDENT -> 2;
////            case JUNIOR_STUDENT -> 1;
////        };
//    }

    //@Override
//    public int compareTo(Person otherPerson){  // compares the current person and another person
//        int currentPersonPriority = getRolePriority(this.role);
//        int otherPersonPriority = getRolePriority(otherPerson.role);
//
//        return Integer.compare(otherPersonPriority, currentPersonPriority); // returns positive if the current person is greater than other person,
//        // returns negative if the current person is less than the other person
//        // returns zero if they are both the same.
//    }


}
