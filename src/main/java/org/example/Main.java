package org.example;

import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String URL ="jdbc:mysql://localhost:3306/student";
        String USER = "root";
        String PASSWORD = "akankshya2005";
        try {
          Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
/*
          PreparedStatement pr=con.prepareStatement("insert into student_data values (?,?,?)");
            System.out.println("Enter id  : \n name :\n Domain :");
            Scanner sc = new Scanner(System.in);


          pr.setInt(1,sc.nextInt());
          sc.nextLine();
          pr.setString(2,sc.nextLine());
          pr.setString(3,sc.nextLine());

          //4th Step(Execute Query)
           int rowsaffect = pr.executeUpdate();
            System.out.println(rowsaffect);
            if(rowsaffect>0){
                System.out.println("Data inserted");
            }*/
            PreparedStatement prs = con.prepareStatement("SELECT * FROM student_data");
            ResultSet rs = prs.executeQuery();

            System.out.println("\n--- Student Data ---");
            while (rs.next()) {
                int id = rs.getInt(1);       // column 1
                String name = rs.getString(2); // column 2
                String domain = rs.getString(3); // column 3

                System.out.println("ID: " + id + ", Name: " + name + ", Domain: " + domain);
            }


            con.close();
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }


    }
}