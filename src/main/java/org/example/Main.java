package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        String URL ="jdbc:mysql://localhost:3306/student";
        String USER = "root";
        String PASSWORD = "akankshya2005";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner sc = new Scanner(System.in);
            int choice ;
            do {
                System.out.println("Select Operation to Perform-- ");
                System.out.println("1. Register"+" "+"2. Get Name by ID"+"  "+"3. Update "+"  "+"4. Delete "
                   +"  "+"5. Get All "+"  "+"6. Exit");

                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        int id = sc.nextInt();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Domain: ");
                        String domain = sc.nextLine();

                        PreparedStatement insert = con.prepareStatement("INSERT INTO student_data(std_id, std_name, domain) VALUES (?,?,?)");

                        insert.setInt(1,id);
                        insert.setString(2, name);
                        insert.setString(3, domain);

                        int rows = insert.executeUpdate();
                        System.out.println(rows > 0 ? "Student Registered Successfully" : "Can not insert");
                        break;

                    case 2:
                        System.out.print("Enter Student ID: ");
                        int searchId = sc.nextInt();

                        PreparedStatement getName = con.prepareStatement("SELECT std_name FROM student_data WHERE std_id=?");
                        getName.setInt(1, searchId);
                        ResultSet rsnm = getName.executeQuery();

                        if (rsnm.next()) {
                            System.out.println("Name: " + rsnm.getString("std_name"));
                        } else {
                            System.out.println("Invalid id ");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Student ID to update: ");
                        int srchID = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new Domain: ");
                        String newDomain = sc.nextLine();

                        PreparedStatement update = con.prepareStatement("UPDATE student_data SET domain=?,std_name=? WHERE std_id=?");
                        update.setString(1, newDomain);
                        update.setString(2,newName);
                        update.setInt(3, srchID);

                        int updtrow = update.executeUpdate();
                        System.out.println(updtrow > 0 ? "Data Updated!" : "Update Failed.");
                        break;

                    case 4:
                        System.out.print("Enter Student ID to delete: ");
                        int deleteId = sc.nextInt();

                        PreparedStatement delete = con.prepareStatement("DELETE FROM student_data WHERE std_id=?");
                        delete.setInt(1, deleteId);

                        int deleted = delete.executeUpdate();
                        System.out.println(deleted > 0 ? "Student Deleted!" : "Delete Failed.");
                        break;

                    case 5:
                        PreparedStatement getAll = con.prepareStatement("SELECT * FROM student_data");
                        ResultSet rsAll = getAll.executeQuery();


                        while (rsAll.next()) {
                            System.out.println("ID: " + rsAll.getInt("std_id") +
                                    ",Student Name: " + rsAll.getString("std_name") +
                                    ", Student Domain: " + rsAll.getString("domain"));
                        }
                        break;

                    case 6:
                        System.out.println("Exiting program...");
                        con.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }while(choice != 6);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
