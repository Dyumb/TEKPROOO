import java.util.ArrayList;
import java.util.List;

// Kelas untuk logging aktivitas
class Logger {
    private List<String> logs = new ArrayList<>();

    public void log(String message) {
        logs.add(message);
        System.out.println("Log: " + message);
    }

    public List<String> getLogs() {
        return logs;
    }
}

// Kelas untuk konfigurasi database
class DatabaseConfig {
    private String url;
    private String username;
    private String password;

    public DatabaseConfig(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getConnectionString() {
        return "URL: " + url + ", User: " + username;
    }
}

public class Singleton {
    private static volatile Singleton instance;
    private DatabaseConfig config;
    private Logger logger;
    private boolean isConnected;
    private List<String> activeQueries;

    private Singleton() {
        this.config = new DatabaseConfig("jdbc:mysql://localhost:3306/library", "admin", "password");
        this.logger = new Logger();
        this.activeQueries = new ArrayList<>();
        this.isConnected = false;
        logger.log("Singleton instance created.");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void connect() {
        if (!isConnected) {
            isConnected = true;
            logger.log("Connected to database: " + config.getConnectionString());
        } else {
            logger.log("Already connected to database.");
        }
    }

    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            logger.log("Disconnected from database.");
        }
    }

    public void executeQuery(String query) {
        if (isConnected) {
            activeQueries.add(query);
            logger.log("Executing query: " + query);
        } else {
            logger.log("Cannot execute query: Database not connected.");
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public List<String> getActiveQueries() {
        return activeQueries;
    }
}