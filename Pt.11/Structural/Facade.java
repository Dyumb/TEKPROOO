class BorrowingSystem {
    public void borrowBook(String bookTitle) {
        System.out.println("Borrowed : " + bookTitle);
    }
}

class ReturningSystem {
    public void returnBook(String bookTitle) {
        System.out.println("Returned : " + bookTitle);
    }
}

class LibraryFacade {
    private BorrowingSystem borrowingSystem;
    private ReturningSystem returningSystem;

    public LibraryFacade() {
        this.borrowingSystem = new BorrowingSystem();
        this.returningSystem = new ReturningSystem();
    }

    public void returnBook(String bookTitle) {
        returningSystem.returnBook(bookTitle);
    }
}

public class Facade {
    public static void main(String[] args) {
        LibraryFacade library = new LibraryFacade();
        library.borrow("Harry Potter");
        library.returnBook("Harry Potter");
    }
}