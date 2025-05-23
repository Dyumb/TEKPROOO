
interface Observer {
    void update(String message);
}

class Member implements Observer {
    private String name;
    private List<String> notifications = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

    public void update(String message) {
        notifications.add(message);
        System.out.println(name + "received notification : " + message);
    }

    public void showNotifications() {
        System.out.println(name + "`s notification :" + notifications);
    }
}

class Library {
    private List<Observer> observers = new ArrayList<>();
    private List<String> availableBooks = new ArrayList<>();
    private String status;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    public List<String> getAvailableBooks() {
        return availableBooks;
    }
}

public class Observer {
    public static void main(String[] args) {
        Library library = new Library();
        Member member1 = new Member("Alice");
        Member member2 = new Member("Bob");

        library.addObserver(member1);
        library.addObserver(member2);

        library.addBook("The Hobit");
        library.addBook("Dune");

        member1.showNotifications();
        member2.showNotifications();
    }
}