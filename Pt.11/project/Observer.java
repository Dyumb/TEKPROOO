import java.util.ArrayList;
import java.util.List;

interface ObserverInterface {
    void update(String message);
}

class Member implements ObserverInterface {
    private String name;
    private List<String> notifications = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

    public void update(String message) {
        notifications.add(message);
        System.out.println(name + " received notification: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
    }
}

public class Observer {
    private List<ObserverInterface> observers = new ArrayList<>();
    private List<String> availableBooks = new ArrayList<>();
    private String status;

    public void addObserver(ObserverInterface observer) {
        observers.add(observer);
    }

    public void removeObserver(ObserverInterface observer) {
        observers.remove(observer);
    }

    public void addBook(String bookTitle) {
        availableBooks.add(bookTitle);
        status = "Book added: " + bookTitle;
        notifyObservers();
    }

    private void notifyObservers() {
        for (ObserverInterface observer : observers) {
            observer.update(status);
        }
    }

    public List<String> getAvailableBooks() {
        return availableBooks;
    }
}