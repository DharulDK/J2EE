import java.sql.*;

public class JdbCountDemoSQL {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        String url = "jdbc:mysql://localhost:3306/employee"; // DB name: employee

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL
            conn = DriverManager.getConnection(url, "root", "");

            // Create Statement
            stmt = conn.createStatement();

            // Execute SELECT query
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM emp");

            // Display results
          if(rs.next()){
            int count=rs.getInt("total");
            System.out.println("Total number of employee:"+count);
          }

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
