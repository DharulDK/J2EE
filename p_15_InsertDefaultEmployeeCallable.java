import java.sql.*;

public class p_15_InsertDefaultEmployeeCallable {
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cstmt = null;

        String url = "jdbc:mysql://localhost:3306/employee"; // Replace with your DB
        String user = "root";
        String password = ""; // Add your password if needed

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Prepare CallableStatement for procedure without parameters
            String sql = "{CALL insert_default_employee()}";
            cstmt = conn.prepareCall(sql);

            // Execute procedure
            cstmt.execute();
            System.out.println("Default employee record inserted successfully!");

       cstmt.close();
       conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
