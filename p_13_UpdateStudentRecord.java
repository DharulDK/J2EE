import java.sql.*;
import java.util.Scanner;

public class p_13_UpdateStudentRecord {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/employee"; // Replace 'college' with your DB name
        String user = "root";
        String password = ""; // Update if your MySQL has a password

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Get student roll number to update
            System.out.print("Enter Roll Number of student to update: ");
            int rollno = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Get new details
            System.out.print("Enter New First Name: ");
            String firstname = scanner.nextLine();

            System.out.print("Enter New Last Name: ");
            String lastname = scanner.nextLine();

            System.out.print("Enter New Course: ");
            String course = scanner.nextLine();

            System.out.print("Enter New Semester: ");
            int semester = scanner.nextInt();

            // Prepare UPDATE statement
            String sql = "UPDATE stud SET firstname = ?, lastname = ?, course = ?, semester = ? WHERE rollno = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, course);
            pstmt.setInt(4, semester);
            pstmt.setInt(5, rollno);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student record updated successfully!");
            } else {
                System.out.println("No student found with roll number: " + rollno);
            }
            pstmt.close();
            conn.close();
             scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
