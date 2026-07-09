package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String URL ="jdbc:mysql://localhost:3306/student";
        String USER = "root";
        String PASSWORD = "Rintu@1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Now we can Talk to Database");

    }
}