import java.sql.*;
import java.util.Scanner;

public class p_16_InsertEmployeeWithParams {
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

            // Get employee details from user
            System.out.print("Enter Employee Number: ");
            int empno = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Employee Name: ");
            String empnm = scanner.nextLine();

            System.out.print("Enter Designation: ");
            String designation = scanner.nextLine();

            System.out.print("Enter City: ");
            String city = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            // Prepare CallableStatement for procedure with parameters
            String sql = "{CALL insert_employee_with_params(?, ?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);

            // Set parameters
            cstmt.setInt(1, empno);
            cstmt.setString(2, empnm);
            cstmt.setString(3, designation);
            cstmt.setString(4, city);
            cstmt.setDouble(5, salary);
            cstmt.setString(6, department);

            // Execute procedure
            cstmt.execute();
            cstmt.close();
            conn.close();
             scanner.close();
            System.out.println("Employee record inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
