import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbConn{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ali";
    private static final String USERNAME = "postgres";
    private static final String USER_PASS = "z";

    private static Connection connection;

    private DbConn() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USERNAME, USER_PASS);
            } catch (SQLException e) {
                throw new RuntimeException("Something went wrong during connection", e);
            }
        }
        return connection;
    }
}
