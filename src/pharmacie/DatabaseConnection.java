package pharmacie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pharmacie";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        Connection connection = null;
        try {
            // Chargez le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Établissez la connexion
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
