package com.decagon.task.implementation2.service;

import com.decagon.task.implementation2.model.Book;
import com.decagon.task.implementation2.model.Person;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LibraryServiceImpl implements LibraryService{

    private HashMap<String, Queue<Book>> books; //HashMap that stores queues of books categorized by their titles as strings
    private Queue<Person> bookRequests; // A queue of that has the list of persons that are requesting for a book
    private HashMap<Book, Person> borrowedBooks; //HashMap that tracks which person has borrowed which book

    public LibraryServiceImpl() {
        this.books = new HashMap<>();
        this.bookRequests = new LinkedList<>();
        this.borrowedBooks = new HashMap<>();
    }

    @Override
    public void addBook(Book book) {
        books.putIfAbsent(book.getTitle(), new LinkedList<>()); //creates a key if absent and then create a new queue for it
        books.get(book.getTitle()).add(book); // gets the key as title of the book and then add the book to the queue values of that title
    }

    @Override
    public void addPersonToQueue(Person person) {
        bookRequests.add(person); // adds person to queue called bookRequest
    }

    @Override
    public void borrowBooks() {
        while (!bookRequests.isEmpty()){    //this ensures that there are people in the bookRequest Queue that needs a book
            Person person = bookRequests.poll();   // removes the F-I element in the queue and assigns it to person
            String requestBookTitle = person.getRequestedBook().getTitle();   // the title of the book requested by the person is assigned as requestBookTitle
            Queue<Book> bookQueue = books.get(requestBookTitle);   // retrieves the queue of books associated with the given title from the HashMap and assigns it to bookQueue

            if(bookQueue != null && !bookQueue.isEmpty()){
                Book book = bookQueue.poll();   // removes th F-I element in the book queue and assigns it to book
                borrowedBooks.put(book, person); // records the person that has borrowed a book
                System.out.println(person + " has borrowed " + book);
            }else {
                System.out.println("No copies of " + requestBookTitle + " are available for " + person);
            }
        }
    }

    @Override
    public boolean returnBook(Book book) {  // checks whether the book was successfully removed or not
        if(borrowedBooks.containsKey(book)){
            Person person = borrowedBooks.remove(book); // retrieves and removes the person associated with that book
            books.get(book.getTitle()).add(book); // retrieves the queue of books associated with the title of the returned book and adds the book to the queue
            System.out.println(person + " has returned " + book);
            return true;
        }else{
            System.out.println("This book was not borrowed from this library");
            return false;
        }
    }

    public Queue<Book> getBooks(){ //returns a queue of all the books available in the library
        Queue<Book> allBooks = new LinkedList<>(); // a new linkedList to store all the books from different queues
        for(Queue<Book> bookQueue : books.values()){ //iterates over each queue of books stored in the books map
            allBooks.addAll(bookQueue); // adds all the books from the current bookQueue to th allBooks queue
        }
        return allBooks;  // returns a copy of the book list for testing
    }

    public Queue<Person> getBookRequests(){
        return new LinkedList<>(bookRequests); // creates a LinkedList containing all the elements from the bookRequest queue
    }

    public boolean isBookBorrowedBy(Person person, Book book){ // check if a person currently borrows a book
        return borrowedBooks.get(book) != null && borrowedBooks.get(book).equals(person);
        //Retrieves the person associated with the given book from the borrowedBooks map. Then check if the retrieved person is not null(i.e., the book is borrowed)
        // and if it equals the specified person
    }

}
