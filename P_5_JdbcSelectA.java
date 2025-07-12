import java.sql.*;

public class P_5_JdbcSelectA {
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp where empnm like 'a%'");

            // Display results
            while (rs.next()) {
                int empno = rs.getInt("empno");
                String empnm = rs.getString("empnm");
                String designation = rs.getString("designation");
                String city = rs.getString("city");
                double salary = rs.getDouble("salary");
                String department = rs.getString("department");

                System.out.println(empno + " | " + empnm + " | " + designation + " | " +
                                   city + " | " + salary + " | " + department);
                                    
                            
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
