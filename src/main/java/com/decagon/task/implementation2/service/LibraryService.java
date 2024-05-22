package com.decagon.task.implementation2.service;

import com.decagon.task.implementation2.model.Book;
import com.decagon.task.implementation2.model.Person;

public interface LibraryService {
    void addBook(Book book);
    void addPersonToQueue(Person person);
    void borrowBooks();
    boolean returnBook(Book book);

}
