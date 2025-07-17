import java.sql.*;

public class p_20_DatabaseMetaDataExample {
    public static void main(String[] args) {
        Connection conn = null;

        String url = "jdbc:mysql://localhost:3306/employee"; // Replace with your DB name
        String user = "root";
        String password = ""; // Add your MySQL password if needed

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Get DatabaseMetaData object
            DatabaseMetaData dbMeta = conn.getMetaData();

            // Display database info
            System.out.println("\n--- Database Metadata ---");
            System.out.println("Database Product Name : " + dbMeta.getDatabaseProductName());
            System.out.println("Database Version      : " + dbMeta.getDatabaseProductVersion());
            System.out.println("Driver Name           : " + dbMeta.getDriverName());
            System.out.println("Driver Version        : " + dbMeta.getDriverVersion());
            System.out.println("User Name             : " + dbMeta.getUserName());
            System.out.println("URL                   : " + dbMeta.getURL());

            // Get tables
            System.out.println("\n--- Tables in Database ---");
            ResultSet tables = dbMeta.getTables(null, null, "%", new String[] {"TABLE"});
            while (tables.next()) {
                System.out.println("Table: " + tables.getString("TABLE_NAME"));
            }
            tables.close();

            // Example: Get columns of a table
            System.out.println("\n--- Columns of 'emp' Table ---");
            ResultSet columns = dbMeta.getColumns(null, null, "emp", null);
            while (columns.next()) {
                System.out.println("Column: " + columns.getString("COLUMN_NAME") +
                                   ", Type: " + columns.getString("TYPE_NAME") +
                                   ", Size: " + columns.getInt("COLUMN_SIZE"));
            }
            columns.close();

            // Example: ResultSetMetaData
            System.out.println("\n--- Example: ResultSet Metadata for 'emp' ---");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
            ResultSetMetaData rsMeta = rs.getMetaData();

            int columnCount = rsMeta.getColumnCount();
            System.out.println("Number of Columns: " + columnCount);

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ": " + rsMeta.getColumnName(i) +
                                   " (" + rsMeta.getColumnTypeName(i) + ")");
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
