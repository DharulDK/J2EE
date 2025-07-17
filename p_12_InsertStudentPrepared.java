// Source code is decompiled from a .class file using FernFlower decompiler.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class p_12_InsertStudentPrepared {
   public InsertStudentPrepared() {
   }

   public static void main(String[] var0) {
      Connection var1 = null;
      PreparedStatement var2 = null;
      Scanner var3 = new Scanner(System.in);
      String var4 = "jdbc:mysql://localhost:3306/employee";
      String var5 = "root";
      String var6 = "";

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         var1 = DriverManager.getConnection(var4, var5, var6);
         System.out.println("Connected to database!");
         System.out.print("Enter Roll Number: ");
         int var7 = var3.nextInt();
         var3.nextLine();
         System.out.print("Enter First Name: ");
         String var8 = var3.nextLine();
         System.out.print("Enter Last Name: ");
         String var9 = var3.nextLine();
         System.out.print("Enter Course: ");
         String var10 = var3.nextLine();
         System.out.print("Enter Semester: ");
         int var11 = var3.nextInt();
         String var12 = "INSERT INTO stud (rollno, firstname, lastname, course, semester) VALUES (?, ?, ?, ?, ?)";
         var2 = var1.prepareStatement(var12);
         var2.setInt(1, var7);
         var2.setString(2, var8);
         var2.setString(3, var9);
         var2.setString(4, var10);
         var2.setInt(5, var11);
         int var13 = var2.executeUpdate();
         if (var13 > 0) {
            System.out.println("Student record inserted successfully!");
         } else {
            System.out.println("Failed to insert student record.");
         }
      } catch (Exception var22) {
         var22.printStackTrace();
      } finally {
         try {
            if (var2 != null) {
               var2.close();
            }

            if (var1 != null) {
               var1.close();
            }

            var3.close();
         } catch (SQLException var21) {
            var21.printStackTrace();
         }

      }

   }
}
