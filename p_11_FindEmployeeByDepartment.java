import java.sql.*;
import java.util.Scanner;

public class p_11_FindEmployeeByDepartment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/employee"; // Change DB name if needed
        String user = "root";
        String password = ""; // Change if your MySQL has a password

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get department name from user
            System.out.print("Enter Department Name: ");
            String deptName = scanner.nextLine();

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);

            // Prepare SQL statement
            String sql = "SELECT empnm, designation FROM emp WHERE department = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deptName);

            // Execute query
            rs = pstmt.executeQuery();

            // Display results
            boolean found = false;
            System.out.println("\nEmployees in Department: " + deptName);
            while (rs.next()) {
                String name = rs.getString("empnm");
                String designation = rs.getString("designation");
                System.out.println("Name: " + name + ", Designation: " + designation);
                found = true;
            }

            if (!found) {
                System.out.println("No employees found in this department.");
            }
        rs.close();
        pstmt.close();
        conn.close();
        scanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
         
        } 
        
    }
}
