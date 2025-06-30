import java.sql.*;
import java.util.Scanner;

public class FindEmployeeByEmpno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement pstmt = null;

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/employee"; // Replace 'employee' with your DB name
        String user = "root";
        String password = ""; // Change if your MySQL has a password

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get employee number from user
            System.out.print("Enter Employee Number (empno): ");
            int empno = scanner.nextInt();

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);

            // Prepare SQL statement
            String sql = "SELECT * FROM emp WHERE empno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empno);

            // Execute query
            ResultSet rs = pstmt.executeQuery();

            // Display result
            if (rs.next()) {
                System.out.println("\nEmployee Details:");
                System.out.println("Emp No      : " + rs.getInt("empno"));
                System.out.println("Name        : " + rs.getString("empnm"));
                System.out.println("Designation : " + rs.getString("designation"));
                System.out.println("City        : " + rs.getString("city"));
                System.out.println("Salary      : " + rs.getDouble("salary"));
                System.out.println("Department  : " + rs.getString("department"));
            } else {
                System.out.println("No employee found with empno: " + empno);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
