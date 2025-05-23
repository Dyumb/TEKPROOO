interface NewLibrarySystem {
    void borrowBook();
}

class OldLibrarySystem {
    public void checkoutBook() {
        System.out.println("Book checked out using old system.");
    }
}

public class Adapter implements NewLibrarySystem {
    private OldLibrarySystem oldSystem;

    public Adapter(OldLibrarySystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    public void borrowBook() {
        oldSystem.checkoutBook();
    }
}