import java.sql.*;
import java.util.Scanner;

public class SearchEmployeeByEmpno {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/employee"; // Database name: employee
        String user = "root";
        String password = "";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get employee number from user
            System.out.print("Enter employee number (empno): ");
            int empno = scanner.nextInt();

            // Connect to database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare SQL query with parameter
            String query = "SELECT * FROM emp WHERE empno = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, empno);

            // Execute query
            ResultSet rs = pstmt.executeQuery();

            // Display result
            if (rs.next()) {
                String empnm = rs.getString("empnm");
                String designation = rs.getString("designation");
                String city = rs.getString("city");
                double salary = rs.getDouble("salary");
                String department = rs.getString("department");

                System.out.println("Employee Details:");
                System.out.println("Emp No: " + empno);
                System.out.println("Name: " + empnm);
                System.out.println("Designation: " + designation);
                System.out.println("City: " + city);
                System.out.println("Salary: " + salary);
                System.out.println("Department: " + department);
            } else {
                System.out.println("No employee found with empno: " + empno);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close connections
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                scanner.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
