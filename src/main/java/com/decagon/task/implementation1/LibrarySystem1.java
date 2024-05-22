package com.decagon.task.implementation1;

import com.decagon.task.implementation1.service.LibraryService;
import com.decagon.task.implementation1.service.LibraryServiceImpl;
import com.decagon.task.enums.Role;
import com.decagon.task.implementation1.model.Book;
import com.decagon.task.implementation1.model.Person;

public class LibrarySystem1 {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryServiceImpl();

        Book book1 = new Book("Intro to JAVA", "James Gosling");
        Book book2 = new Book("Intro to Testing with JUnit5", "Uzoma Ibezim");
        Book book3 = new Book("Data Science", "Decagon");

        libraryService.addBook(book1);
        libraryService.addBook(book2);
        libraryService.addBook(book3);

        Person teacher = new Person("Segun Osiki",  Role.TEACHER, book1);
        Person student1 = new Person("Ejemba Onyekachi", Role.SENIOR_STUDENT, book1);
        Person student2 = new Person("Okereke Ifeoma",  Role.JUNIOR_STUDENT, book1);

        Person student3 = new Person("Agne John", Role.SENIOR_STUDENT, book3);
        Person teacher2 = new Person("Uzoma Ibezim", Role.TEACHER, book3);


        libraryService.addPersonToQueue(teacher);
        libraryService.addPersonToQueue(student1);
        libraryService.addPersonToQueue(student3);
        libraryService.addPersonToQueue(student2);
        libraryService.addPersonToQueue(teacher2);

        //libraryService.borrowBooks();



    }
}