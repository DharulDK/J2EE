import java.sql.*;
import java.util.Scanner;

public class p_14_DeleteStudentRecord {
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

            // Get roll number to delete
            System.out.print("Enter Roll Number of student to delete: ");
            int rollno = scanner.nextInt();

            // Prepare DELETE statement
            String sql = "DELETE FROM stud WHERE rollno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rollno);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student record deleted successfully!");
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
