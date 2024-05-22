package com.decagon.task.implementation1.service;

import com.decagon.task.enums.Role;
import com.decagon.task.implementation1.model.Book;
import com.decagon.task.implementation1.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService libraryService;

    @BeforeEach
    void setUp(){
        libraryService = new LibraryServiceImpl();
    }

    @Test
    void testAddBook() {
        Book book = new Book("Intro to JAVA", "James Gosling");
        libraryService.addBook(book);
        Queue<Book> books = ((LibraryServiceImpl)libraryService).getBooks();// verifies the book was added successfully
        assertEquals(1, books.size());
        assertEquals(book, books.peek());
    }

    @Test
    void testAddPersonToQueue() {
        Book book =new Book("Intro to Testing with JUnit5", "Uzoma Ibezim");
        Person person1 = new Person("Ejemba Onyekachi",  Role.SENIOR_STUDENT, book);
        Person person2 = new Person("John Agne", Role.SENIOR_STUDENT, book);

        libraryService.addPersonToQueue(person1);

        HashMap<String, PriorityQueue<Person>> bookRequests = ((LibraryServiceImpl) libraryService).getBookRequests(); // verifies the person was added to the correct queue
        assertEquals(1, bookRequests.get(book.getTitle()).size());
        assertEquals(person1, bookRequests.get(book.getTitle()).peek());
        assertNotEquals(person2, bookRequests.get(book.getTitle()).peek());

    }

    @Test
    void testBorrowBooks() {
        Book book1 = new Book("Intro to JAVA", "James Gosling");
        Book book2 = new Book("Intro to Testing with JUnit5", "Uzoma Ibezim");
        libraryService.addBook(book1);
        libraryService.addBook(book2);

        Person teacher = new Person("Segun Osiki",  Role.TEACHER, book1);
        Person seniorStudent = new Person("Ejemba Onyekachi", Role.SENIOR_STUDENT, book1);

        Person juniorStudent = new Person("Okereke Ifeoma",  Role.JUNIOR_STUDENT, book2);
        Person seniorStudent2 = new Person("Agne John", Role.SENIOR_STUDENT, book2);

        libraryService.addPersonToQueue(seniorStudent);
        libraryService.addPersonToQueue(teacher);

        libraryService.addPersonToQueue(juniorStudent);
        libraryService.addPersonToQueue(seniorStudent2);

        libraryService.borrowBooks();
        assertTrue(((LibraryServiceImpl) libraryService).isBookBorrowedBy(teacher, book1));
        assertFalse(((LibraryServiceImpl) libraryService).isBookBorrowedBy(seniorStudent, book1));

        assertFalse(((LibraryServiceImpl) libraryService).isBookBorrowedBy(juniorStudent, book2));
        assertTrue(((LibraryServiceImpl) libraryService).isBookBorrowedBy(seniorStudent2, book2));
    }

    @Test
    void testReturnedBook(){
        Book book = new Book("Data Science", "Decagon");
        libraryService.addBook(book);

        Person teacher = new Person("Segun Osiki",  Role.TEACHER, book);
        libraryService.addPersonToQueue(teacher);
        libraryService.borrowBooks();

        assertTrue(((LibraryServiceImpl) libraryService).isBookBorrowedBy(teacher, book)); // verify book is borrowed

        assertTrue(libraryService.returnBook(book)); // return the book and verify it is no longer borrowed
        assertFalse(((LibraryServiceImpl) libraryService).isBookBorrowedBy(teacher, book));

        Book nonBorrowedBook = new Book("Intro to Information Technology", "Gadibia Daro"); // Try to return a book that was not borrowed from the library
        assertFalse(libraryService.returnBook(nonBorrowedBook));


    }
}