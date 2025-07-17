import java.sql.*;
import java.util.Scanner;

public class p_19_ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/employee"; // Replace with your DB
    static final String USER = "root";
    static final String PASSWORD = ""; // Add your MySQL password if needed

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database!");

            int choice;
            do {
                System.out.println("\n--- Product CRUD Operations ---");
                System.out.println("1. Insert Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        insertProduct(conn, scanner);
                        break;
                    case 2:
                        viewProducts(conn);
                        break;
                    case 3:
                        updateProduct(conn, scanner);
                        break;
                    case 4:
                        deleteProduct(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void insertProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product ID: ");
        int pid = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Product Name: ");
        String pname = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = scanner.nextInt();

        String sql = "INSERT INTO product (pid, productname, price, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pid);
            pstmt.setString(2, pname);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, qty);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product inserted successfully!");
            } else {
                System.out.println("Insert failed.");
            }
        }
    }

    static void viewProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Product List ---");
            while (rs.next()) {
                System.out.println("PID: " + rs.getInt("pid") +
                        ", Name: " + rs.getString("productname") +
                        ", Price: " + rs.getDouble("price") +
                        ", Quantity: " + rs.getInt("quantity"));
            }
        }
    }

    static void updateProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product ID to update: ");
        int pid = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter New Product Name: ");
        String pname = scanner.nextLine();

        System.out.print("Enter New Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter New Quantity: ");
        int qty = scanner.nextInt();

        String sql = "UPDATE product SET productname = ?, price = ?, quantity = ? WHERE pid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pname);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, qty);
            pstmt.setInt(4, pid);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("No product found with ID: " + pid);
            }
        }
    }

    static void deleteProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product ID to delete: ");
        int pid = scanner.nextInt();

        String sql = "DELETE FROM product WHERE pid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pid);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("No product found with ID: " + pid);
            }
        }
    }
}
