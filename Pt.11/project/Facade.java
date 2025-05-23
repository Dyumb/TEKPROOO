class BorrowingSystem {
    public void borrowBook(String bookTitle) {
        System.out.println("Borrowed: " + bookTitle);
    }
}

class ReturningSystem {
    public void returnBook(String bookTitle) {
        System.out.println("Returned: " + bookTitle);
    }
}

public class Facade {
    private BorrowingSystem borrowingSystem;
    private ReturningSystem returningSystem;

    public Facade() {
        this.borrowingSystem = new BorrowingSystem();
        this.returningSystem = new ReturningSystem();
    }

    public void borrow(String bookTitle) {
        borrowingSystem.borrowBook(bookTitle);
    }

    public void returnBook(String bookTitle) {
        returningSystem.returnBook(bookTitle);
    }
}