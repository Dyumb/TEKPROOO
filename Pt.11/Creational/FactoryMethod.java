import java.util.ArrayList;
import java.util.List;

interface Book {
    String getDetails();
}

class FictionBook implements Book {
    private String title;

    public FictionBook(String title) {
        this.title = title;
    }

    public String getDetails() {
        return "Fiction Book : " + title;
    }
}

class NonFictionBook implements Book {
    private String title;

    public NonFictionBook(String title) {
        this.title = title;
    }

    public String getDetails() {
        return "Non-Fiction Book : " + title;
    }
}

abstract class BookFactory {
    private List<String> createdBooks = new ArrayList<>();
    
    abstract Book creatBook(String title) {
        Book book = createdBook(title);
        createdBooks.add(book.getDetails());
        System.out.println("Created : " + book.getDetails());
        return book;
    }

    public List<String> getCreatedBooks() {
        return createdBooks;
    }
}

class FictionBookFactory extends BooksFactory {
    Book createBook(String title) {
        return new FictionBook(title);
    }
}

class NonFictionBookFactory extends BooksFactory {
    Book createBook(String title) {
        return new NonFictionBook(title);
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        BookFactory fictionFactory = new FictionBookFactory();
        BookFactory nonFictionFactory = new NonFictionBookFactory();

        fictionFactory.produceBook("The Hobbit");
        fictionFactory.produceBook("Dune");

        nonFictionFactory.produceBook("Sapiens");
        nonFictionFactory.produceBook("Educated");

        System.out.println("Fiction books created: " + fictionFactory.getCreatedBooks());
        System.out.println("Non-fiction books created: " + nonFictionFactory.getCreatedBooks());
    }
}