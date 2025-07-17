import java.sql.*;
import java.util.Scanner;

public class p_18_GetEmployeesByDesignation {
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/employee"; // Replace with your DB name
        String user = "root";
        String password = ""; // Add your MySQL password if needed

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");

            // Connect to database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Get designation from user
            System.out.print("Enter Designation to search: ");
            String designation = scanner.nextLine();

            // Prepare CallableStatement for procedure with IN parameter
            String sql = "{CALL get_employees_by_designation(?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, designation);

            // Execute procedure
            rs = cstmt.executeQuery();

            // Process result set
            boolean found = false;
            System.out.println("\nEmployees with Designation: " + designation);
            System.out.println("-----------------------------------------------------");

            while (rs.next()) {
                int empno = rs.getInt("empno");
                String empnm = rs.getString("empnm");
                String city = rs.getString("city");
                double salary = rs.getDouble("salary");
                String department = rs.getString("department");

                System.out.println("Emp No: " + empno +
                                   ", Name: " + empnm +
                                   ", City: " + city +
                                   ", Salary: " + salary +
                                   ", Department: " + department);

                found = true;
            }

            if (!found) {
                System.out.println("No employees found with designation: " + designation);
            }
            rs.close();
            cstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
