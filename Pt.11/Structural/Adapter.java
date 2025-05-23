interface NewLibrarySystem {
    void borrowBook();
}

class OldLibrarySystem {
    public void checkoutBook() {
        System.out.println("Book checked out using old system.");
    }
}

class LibraryAdapter implements NewLibrarySystem {
    private OldeLibrarySystem oldSystem;

    public LibraryAdapter(OldLibrarySystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    public void borrowBook() {
        oldSystem.checkoutBook();
    }
}

public class Adapter {
    public statis void main(String[] args) {
        OldLibrarySystem oldSystem = new OldLibrarySystem();
        NewLibrarySystem adapter = new LibraryAdapter(oldSystem);
        adapter.borrowBook();
    }
}