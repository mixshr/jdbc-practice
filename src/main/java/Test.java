import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;

public class Test {
    private static final String DATABASE_URL = "jdbc:postgresql://192.168.0.114:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1000";

    private static Connection CONNECTION;
    private static Statement STATEMENT;
    private static ResultSet RS;

    public static void main(String[] args) {
        String query = "SELECT * FROM developers";
        /*String table = "CREATE TABLE developers (id SERIAL NOT NULL PRIMARY KEY, name VARCHAR(20) NOT NULL, specialty VARCHAR(20) NOT NULL, salary NUMERIC(8,2) NOT NULL)";
        String data = "INSERT INTO developers (name, specialty, salary) VALUES ('Alexander', 'Java', 3000);\n" +
                      "INSERT INTO developers (name, specialty, salary) VALUES ('Igor', 'Php', 2000);\n" +
                      "INSERT INTO developers (name, specialty, salary) VALUES ('Fedor', 'Frontend', 4000);";*/
        try {
            CONNECTION = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            STATEMENT = CONNECTION.createStatement();
           /* STATEMENT.executeUpdate(table);
            STATEMENT.executeUpdate(data);*/
            RS = STATEMENT.executeQuery(query);

            while (RS.next()) {
                int id = RS.getInt("id");
                String name = RS.getString("name");
                String specialty = RS.getString("specialty");
                BigDecimal salary = RS.getBigDecimal("salary");

                System.out.println("");
                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: $" + salary);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            CONNECTION.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            STATEMENT.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            RS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}