import java.util.ArrayList;
import java.util.List;

// Antarmuka untuk produk (buku)
interface Book {
    String getDetails();
}

// Kelas konkret untuk buku fiksi
class FictionBook implements Book {
    private String title;

    public FictionBook(String title) {
        this.title = title;
    }

    public String getDetails() {
        return "Fiction Book: " + title;
    }
}

// Kelas konkret untuk buku non-fiksi
class NonFictionBook implements Book {
    private String title;

    public NonFictionBook(String title) {
        this.title = title;
    }

    public String getDetails() {
        return "Non-Fiction Book: " + title;
    }
}

// Kelas abstrak Factory
abstract class BookFactory {
    private List<String> createdBooks = new ArrayList<>();

    abstract Book createBook(String title);

    public Book produceBook(String title) {
        Book book = createBook(title);
        createdBooks.add(book.getDetails());
        System.out.println("Created: " + book.getDetails());
        return book;
    }

    public List<String> getCreatedBooks() {
        return createdBooks;
    }
}

public class FactoryMethod {
    public static BookFactory getFictionFactory() {
        return new FictionBookFactory();
    }

    public static BookFactory getNonFictionFactory() {
        return new NonFictionBookFactory();
    }
}

// Factory untuk buku fiksi
class FictionBookFactory extends BookFactory {
    Book createBook(String title) {
        return new FictionBook(title);
    }
}

// Factory untuk buku non-fiksi
class NonFictionBookFactory extends BookFactory {
    Book createBook(String title) {
        return new NonFictionBook(title);
    }
}