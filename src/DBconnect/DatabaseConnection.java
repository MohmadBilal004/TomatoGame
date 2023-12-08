package DBconnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {
    
            // Create a single instance of the DatabaseConnection
            private static DatabaseConnection instance;

            private static final String URL = "jdbc:mysql://localhost:3306/tomatogame";
            private static final String USER = "root";
            private static final String PASSWORD = "";

            
            // Private constructor to prevent instantiation from outside
            private DatabaseConnection() {
                try {
                    // Load the JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Public method to get the single instance of the DatabaseConnection
            public static DatabaseConnection getInstance() {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
                return instance;
            }

            // Public method to get the database connection
            public Connection connect() {
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return conn;
            }
        }