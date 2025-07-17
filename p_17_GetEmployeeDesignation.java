import java.sql.*;
import java.util.Scanner;

public class p_17_GetEmployeeDesignation {
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cstmt = null;
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

            // Get empno from user
            System.out.print("Enter Employee Number: ");
            int empno = scanner.nextInt();

            // Prepare CallableStatement with IN & OUT parameter
            String sql = "{CALL get_employee_designation(?, ?)}";
            cstmt = conn.prepareCall(sql);

            // Set IN parameter
            cstmt.setInt(1, empno);

            // Register OUT parameter
            cstmt.registerOutParameter(2, Types.VARCHAR);

            // Execute procedure
            cstmt.execute();

            // Get OUT parameter value
            String designation = cstmt.getString(2);

            if (designation != null) {
                System.out.println("Employee Designation: " + designation);
            } else {
                System.out.println("No employee found with empno: " + empno);
            }
            cstmt.close();
            conn.close();
             scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
