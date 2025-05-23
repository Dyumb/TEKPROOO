import java.util.ArrayList;
import java.util.List;

interface SearchStrategy {
    void search(String keyword, Strategy context);
}

class SearchByTitle implements SearchStrategy {
    public void search(String keyword, Strategy context) {
        List<String> results = context.getBooks().stream()
                .filter(book -> book.toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        context.logSearchResult("Title search for: " + keyword, results);
    }
}

class SearchByAuthor implements SearchStrategy {
    public void search(String keyword, Strategy context) {
        List<String> results = context.getBooks().stream()
                .filter(book -> {
                    String[] parts = book.split(" by ");
                    return parts.length > 1 && parts[1].toLowerCase().contains(keyword.toLowerCase());
                })
                .toList();
        context.logSearchResult("Author search for: " + keyword, results);
    }
}

public class Strategy {
    private SearchStrategy strategy;
    private List<String> books = new ArrayList<>();
    private List<String> searchHistory = new ArrayList<>();

    public Strategy() {
        books.add("The Hobbit by J.R.R. Tolkien");
        books.add("Dune by Frank Herbert");
        books.add("1984 by George Orwell");
    }

    public void setSearchStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void addData(String book) {
        books.add(book);
    }

    public void performSearch(String keyword) {
        if (strategy != null) {
            strategy.search(keyword, this);
        } else {
            System.out.println("No search strategy set.");
        }
    }

    public List<String> getBooks() {
        return books;
    }

    public void logSearchResult(String query, List<String> results) {
        String resultLog = query + " - Results: " + (results.isEmpty() ? "No matches" : results);
        searchHistory.add(resultLog);
        System.out.println(resultLog);
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }
}