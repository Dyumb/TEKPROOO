import java.util.ArrayList;
import java.util.List;

class Logger {
    private List<String> logs = new ArrayList<>();

    public void log(String message) {
        logs.add(message);
        System.out.println("Log : " + message);
    }

    public List<String> getLogs() {
        return logs;
    }
}

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
        return "URL : " + url + ", User : " + username;
    }
}

class DatabaseConnection {
    private static volatile DatabseConnection instance;
    private DatabaseConfig config;
    private Logger logger;
    private boolean isConnected;
    private List<String> activeQueries;

    private DatabaseConfig() {
        this.config = new DatabaseConfig("jdbc:mysql://localhost:3306/library", "admin", "password");
        this.logger = new Logger();
        this.activeQueries = new ArrayList<>();
        this.isConnected = false;
        logger.log("DatabaseConnection instance created.");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void connect() {
        if (!isConnected) {
            isConnected = true;
            logger.log("Connected to database : " + config.getConnectionString());
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
            logger.log("Executing query : " + query);
        } else {
            logger.log("Cannot execute query : Database not connected.");
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public List<String> getActiveQueries() {
        return activeQueries;
    }
}

public class Singleton {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        System.out.println("Same instance : " + (db1 == db2));

        db1.connect();
        db1.executeQuery("SELECT * FROM books");
        db1.executeQuery("INSERT INTO members VALUES ('Alice')");

        db2.connect();
        db2.executeQuery("SELECT * FROM loans");

        System.out.println("Active Queries : " + db1.getActiveQueries());
        System.out.println("Logs : " + db1.getLogger().getLogs());

        db1.disconnect();
    }
}