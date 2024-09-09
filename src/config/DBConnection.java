package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
import utils.ConsoleUI;

public class DBConnection {

    private final String dbname;
    private final String host;
    private final String port;
    private final String username;
    private final String password;
    private Connection connection;
    private static DBConnection instance;

    private DBConnection() {
        Dotenv dotenv = Dotenv.load();

        this.dbname = dotenv.get("DB_NAME");
        this.host = dotenv.get("DB_HOST");
        this.port = dotenv.get("DB_PORT");
        this.username = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASSWORD");
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + dbname, username, password);
            }
            return connection;
        } catch (SQLException e) {
            ConsoleUI.printError("Failed to establish a database connection: " + e.getMessage());
            return null;
        }
    }
}
