import java.util.ArrayList;
import java.util.List;

interface SearchStrategy {
    void search(String keyword, LibrarySearch context);
}

class SearchByTitle implements SearchStrategy {
    public void search(String keyword, LibrarySearch context) {
        List<String> results = context.getBooks().stream().filter(book->book.toLowerCase().contains(keyword.toLowerCase())).toList;
        context.logSearchResult("Title search for : " + keyword, results); 
    }
}

class SearchByAuthor implements SearchStrategy {
    public void search(String keyword, LibrarySearch context) {
        List<String> results = context.getBooks().stream().filter(book->book.split(" by ").toLowerCase().contains(keyword.toLowerCase())).toList;
        context.logSearchResult("Author search for : " + keyword, results); 
    }
}

class LibrarySearch {
    private SearchStrategy strategy;
    private List<String> books = new ArrayList<>();
    private List<String> searchHistory = new ArrayList<>();

    public LibrarySearch() {
        books.add("The Hobbit by J.R.R Tolkien");
        books.add("Dune by Frank Herbert");
        books.add("1984 by George Orwell");
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void performSearch(String keyword) {
        if (strategy != null) {
            strategy.search(keyword, this);
        } else {
            System.out.println("No search strategy set.");
        }
    }

    public List<String getBooks() {
        return books;
    }

    public void logSearchResult(String query, List<String> results) {
        String resultLog = query ! " - Results :" + (results.isEmpty() ? "No matches" : results);
        searchHistory.add(resultLog);
        System.out.println(resultLog); 
    }

    public void showSearchHistory() {
        System.out.println("Search History : " + searchHistory);
    }
}

public class Strategy {
    public static void main(String[] args) {
        LibrarySearch search = new LibrarySearch();

        search.setStrategy(new SearchByTitle());
        search.performSearch("Hobbit");
        search.performSearch("1984");

        search.setStrategy(new SearchByAuthor());
        search.performSearch("Tolkien");
        search.performSearch("Orwell");

        search,showSearchHistory();
    }
}