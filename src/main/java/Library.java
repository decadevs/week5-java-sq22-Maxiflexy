import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Library {
    // Define the Book class
    class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return title + " by " + author;
        }
    }

    // HashMap to store books
    private HashMap<String, Queue<Book>> books = new HashMap<>();

    // Method to add a book to the library
    public void addBook(Book book) {
        books.putIfAbsent(book.getTitle(), new LinkedList<>());
        books.get(book.getTitle()).add(book);
    }

    // Main method for testing
    public static void main(String[] args) {
        Library library = new Library();

        // Create some book instances
        Book book1 = library.new Book("Dune", "Frank Herbert");
        Book book2 = library.new Book("Dune", "Frank Herbert");
        Book book3 = library.new Book("Neuromancer", "William Gibson");

        // Add books to the library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Display the books in the library
        for (String title : library.books.keySet()) {
            System.out.println("Books with title '" + title + "': " + library.books.get(title));
        }
    }
}
