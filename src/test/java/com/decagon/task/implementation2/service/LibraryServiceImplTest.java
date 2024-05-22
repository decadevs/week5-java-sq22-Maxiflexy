package com.decagon.task.implementation2.service;

import com.decagon.task.enums.Role;
import com.decagon.task.implementation2.model.Book;
import com.decagon.task.implementation2.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {
    private LibraryService libraryService;

    @BeforeEach
    void setUp(){
        libraryService = new LibraryServiceImpl();
    }

    @Test
    void testAddBook() {
        Book book = new Book("Intro to Testing with JUnit5", "Uzoma Ibezim");
        libraryService.addBook(book);

        Queue<Book> books = ((LibraryServiceImpl)libraryService).getBooks();// Verify if the book was added correctly
        assertEquals(1, books.size());
        assertEquals(book, books.peek());
    }

    @Test
    void testAddPersonToQueue() {
        Book book = new Book("Intro to JAVA", "James Gosling");
        Person person = new Person("Ejemba Onyekachi", 26, Role.SENIOR_STUDENT, book);
        libraryService.addPersonToQueue(person);

        //Verify the person was added to the correct queue
        Queue<Person> bookRequests = ((LibraryServiceImpl)libraryService).getBookRequests();
        assertEquals(person, bookRequests.peek());
    }

    @Test
    void testBorrowBooks() {
        Book book1 = new Book("Intro to JAVA", "James Gosling");
        Book book2 = new Book("Intro to Testing with JUnit5", "Uzoma Ibezim");

        libraryService.addBook(book1);
        libraryService.addBook(book2);

        Person student1 = new Person("Okereke Ifeoma", 28, Role.JUNIOR_STUDENT, book1);
        Person teacher = new Person("Segun Osiki", 35, Role.TEACHER, book1);
        Person student2 = new Person("Ejemba Onyekachi", 26, Role.SENIOR_STUDENT, book2);
        Person student3 = new Person("Agne John", 18, Role.SENIOR_STUDENT, book2);

        libraryService.addPersonToQueue(student1);
        libraryService.addPersonToQueue(teacher);
        libraryService.addPersonToQueue(student2);
        libraryService.addPersonToQueue(student3);

        libraryService.borrowBooks();

        //verify the borrowing process
        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(student1, book1));
        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher, book1));
        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(student2, book2));
     }

    @Test
    void testReturnBook() {
        Book book = new Book("Data Science", "Decagon");
        libraryService.addBook(book);
        Person teacher = new Person("Segun Osiki", 35, Role.TEACHER, book);
        libraryService.addPersonToQueue(teacher);
        libraryService.borrowBooks();

        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher, book)); // verify that book is borrowed
        assertTrue(libraryService.returnBook(book)); // Return book and verify it is no longer borrowed
        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher, book));

        Book nonBorrowedBook = new Book("Intro to Information Technology", "Gadibia Daro");
        assertFalse(libraryService.returnBook(nonBorrowedBook));
    }
}