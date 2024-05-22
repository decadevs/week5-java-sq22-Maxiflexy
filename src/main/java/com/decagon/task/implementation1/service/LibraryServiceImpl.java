package com.decagon.task.implementation1.service;

import com.decagon.task.implementation1.model.Book;
import com.decagon.task.implementation1.model.Person;

import java.util.*;

public class LibraryServiceImpl implements LibraryService{
    private final HashMap<String, Queue<Book>> books; ////HashMap that stores queues of books categorized by their titles as strings
    private final HashMap<String , PriorityQueue<Person>> bookRequests; // A HashMap that stores book title as strings and a queue of person that request that book;
    private final HashMap<Book, Person> borrowedBooks; //To keep track of borrowed books

    public LibraryServiceImpl(){
        this.books = new HashMap<>();
        this.bookRequests = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
    }


    @Override
    public void addBook(Book book) {
        books.putIfAbsent(book.getTitle(), new LinkedList<>());  //creates a key if absent and then create a new queue for it
        books.get(book.getTitle()).add(book);  //gets the key as title of the book and then add the book to the queue values of that title
    }


    @Override
    public void addPersonToQueue(Person person) {
        String requestedBookTitle = person.getRequestedBook().getTitle(); // gets the requested Book object of the person and then gets the title of that book
        bookRequests.putIfAbsent(requestedBookTitle, new PriorityQueue<>(new RoleComparator()));
        bookRequests.get(requestedBookTitle).add(person);
//        bookRequests.putIfAbsent(requestedBookTitle, new PriorityQueue<>(new Comparator<Person>() {  // checks if the map contains the title, if it doesn't, then it creates a queue for it and title as key
//            @Override
//            public int compare(Person p1, Person p2) {
//                return getPriority(p1.getRole()) - getPriority(p2.getRole());
//            }
//
//            private int getPriority(Role role) {
//                switch (role) {
//                    case TEACHER:
//                        return 1;
//                    case SENIOR_STUDENT:
//                        return 2;
//                    case JUNIOR_STUDENT:
//                        return 3;
//                    default:
//                        throw new IllegalArgumentException("unknown role : " + role);
//                }
//            }
//        }));
        //bookRequests.get(requestedBookTitle).add(person); // adds the person to the priority queue of the requested book
    }

    @Override
    public void borrowBooks() {
        for(String bookTitle : books.keySet()){ // iterates over all the keys in the books HashMap
            Queue<Book> bookQueue = books.get(bookTitle); // retrieves the corresponding queue of book values to ensure available copies;
            PriorityQueue<Person> requestQueue = bookRequests.get(bookTitle); //retrieves the Priority_queue of people that have requested that book
            while (!bookQueue.isEmpty() && requestQueue != null && !requestQueue.isEmpty()){ // runs as long as there are available books in the bookQueue, and the requestQueue is not null/empty
                Book book = bookQueue.poll();  //retrieves the next available book from the bookQueue assigns it to book
                Person person = requestQueue.poll();  // retrieves and remove the next person from the requestQueue who wants to borrow that book
                borrowedBooks.put(book, person);// adds entry to the borrowedBooks map by associating the book with the person who borrowed it
                assert person != null;
                System.out.println(person.getName() + " has borrowed " + book); // prints a message indicating the person has borrowed the book

                if(requestQueue.isEmpty()) {
                    bookRequests.remove(bookTitle); // if its empty, it removes the entry for the book title from the bookRequest map.
                }
            }
        }
    }

    @Override
    public boolean returnBook(Book book) {// checks whether the book was successfully removed or not

        Person person = borrowedBooks.remove(book);
        if(person == null)
            return false;
        books.get(book.getTitle()).add(book);
        return true;

//        if(borrowedBooks.containsKey(book)){
//            Person person = borrowedBooks.remove(book); // retrieves and removes the person associated with that book
//            books.get(book.getTitle()).add(book); // retrieves the queue of books associated with the title of the returned book and adds the book to the queue
//            System.out.println(person.getName() + " has returned " + book);
//            return true;
//        }else {
//            System.out.println("This book was not borrowed from this library");
//            return false;
//        }
    }


    public Queue<Book> getBooks(){ //returns a queue of all the books available in the library
        Queue<Book> allBooks = new LinkedList<>(); // a new linkedList to store all the books from different queues
        for(Queue<Book> bookQueue : books.values()){ //iterates over each queue of books stored in the books map
            allBooks.addAll(bookQueue); // adds all the books from the current bookQueue to th allBooks queue
        }
        return allBooks; // returns a copy of the book list for testing
    }

    //method to get the book request for testing
    public HashMap<String, PriorityQueue<Person>> getBookRequests(){
        return bookRequests;

//        HashMap<String, PriorityQueue<Person>> copy = new HashMap<>();
//        for(String title : bookRequests.keySet()){
//            copy.put(title, new PriorityQueue<>(bookRequests.get(title)));
//        }
//        return copy;
    }


    public boolean isBookBorrowedBy(Person person, Book book) {  // check if a person currently borrows a book
        return borrowedBooks.get(book) != null && borrowedBooks.get(book).equals(person);
        // Retrieves the person associated with the given book from the borrowedBooks map. Then check if the retrieved person is not null(i.e., the book is borrowed)
        // and if it equals the specified person
    }

}
