import java.sql.*;

public class JdbcInsertDemoSQL {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        String url = "jdbc:mysql://localhost:3306/employee"; // Use correct DB and port

        try {
            // Load MySQL Connector/J 9.0.0 driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            conn = DriverManager.getConnection(url, "root", ""); // Add password if needed

            // Create SQL insert
            String sql = "INSERT INTO emp VALUES (1, 'aman', 'Developer', 'Goa', 12000, 'mng')";

            stmt = conn.createStatement();
            int val = stmt.executeUpdate(sql);

            System.out.println(val + " record inserted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
