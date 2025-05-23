public class Main {
    public static void main(String[] args) {
        // Pola Adapter
        System.out.println("=== Pola Adapter ===");
        OldLibrarySystem oldSystem = new OldLibrarySystem();
        NewLibrarySystem adapter = new Adapter(oldSystem);
        adapter.borrowBook();

        // Pola Facade
        System.out.println("\n=== Pola Facade ===");
        Facade facade = new Facade();
        facade.borrow("Design Patterns in Java");
        facade.returnBook("Design Patterns in Java");

        // Pola Factory Method
        System.out.println("\n=== Pola Factory Method ===");
        BookFactory fictionFactory = FactoryMethod.getFictionFactory();
        Book book = fictionFactory.produceBook("1984 by George Orwell");
        System.out.println("Buku yang dibuat: " + book.getDetails());

        // Pola Singleton
        System.out.println("\n=== Pola Singleton ===");
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        singleton1.connect();
        singleton1.executeQuery("SELECT * FROM books");
        singleton1.getLogger().log("User meminjam buku");
        System.out.println("Singleton sama: " + (singleton1 == singleton2));

        // Pola Observer
        System.out.println("\n=== Pola Observer ===");
        Observer library = new Observer();
        Member alice = new Member("Alice");
        Member bob = new Member("Bob");
        library.addObserver(alice);
        library.addObserver(bob);
        library.addBook("Effective Java");

        // Pola Strategy
        System.out.println("\n=== Pola Strategy ===");
        Strategy searchContext = new Strategy();
        searchContext.addData("Clean Code");
        searchContext.addData("The Pragmatic Programmer");
        searchContext.setSearchStrategy(new SearchByTitle());
        searchContext.performSearch("Clean");
    }
}