package com.tictactoe.robot;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Robot {

    private String ime;
    private ArrayList<String> uvrede = new ArrayList<String>();

    public Robot() {}

    public String getIme() {
        return ime;
    }

    public ArrayList<String> getUvrede() {
        return uvrede;
    }

    public void fetchRobot(){
        System.out.println("Popis Robota");
        System.out.println("//");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/robot?" +
                    "user=username&password=pass");
            Statement stmt = conn.createStatement();
            try {
                ResultSet resultSet;
                resultSet = stmt.executeQuery("SELECT ime FROM roboti");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("ime"));
                }

            }catch(SQLException ignored){

            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.println("//");

    }

    public void fetchUvrede() {
        System.out.print("Enter your opponents name: ");
        Scanner scan = new Scanner(System.in);
        this.ime = scan.nextLine();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/robot?" +
                    "user=username&password=pass");
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT uvreda, roboti.id, roboti.ime FROM uvrede " +
                            "LEFT JOIN roboti ON roboti.id = uvrede.uvredaID " +
                            "WHERE roboti.ime = ?");

            stmt.setString(1, this.getIme());
            try {
                ResultSet resultSet;
                resultSet = stmt.executeQuery();
                if (!resultSet.isBeforeFirst() ) {
                    fetchUvrede();
                }
                while (resultSet.next()) {
                        this.uvrede.add(resultSet.getString("uvreda"));
                    }

            }catch(SQLException ignored){

            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public boolean confirmation(){
        String confirmation = null;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Confirm action (y/n): ");
            confirmation = scan.nextLine();
            if (confirmation.equals("n")){
                return false;
            }else if(confirmation.equals("y")){
                return true;
            }
        }while(true);
    }

    public void addRobot(){
        System.out.print("Add a Robot? ");
        if(!confirmation())
        {
            return;
        }
        Scanner scan = new Scanner(System.in);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/robot?" +
                    "user=username&password=pass");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO roboti(ime)" +
                    "VALUES (?)");
            System.out.print("Enter the robot name: ");
            stmt.setString(1, scan.nextLine());
            try {
                stmt.executeUpdate();

            }catch(SQLException ignored){

            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void addInsultToRobot() {
        fetchRobot();
        System.out.print("Add insults to a Robot? ");
        if(!confirmation())
        {
            return;
        }
        System.out.print("Enter robot name: ");
        Scanner scan = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/robot?" +
                    "user=username&password=pass");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO uvrede(uvredaID, uvreda)" +
                    "VALUES (?,?)");
            PreparedStatement stmtRobot = conn.prepareStatement(
                    "SELECT roboti.ime, roboti.id FROM roboti WHERE ime = ?");

            stmtRobot.setString(1, scan.nextLine());


            try {
                boolean flag = true;
                ResultSet resultSet = stmtRobot.executeQuery();
                do {
                    if (!resultSet.isBeforeFirst()) {
                        System.out.println("The robot does not exist");
                        System.out.print("Enter a robots name which is on the list shown above: ");
                        stmtRobot.setString(1, scan.nextLine());
                        resultSet = stmtRobot.executeQuery();
                    }   else{
                        resultSet.next();
                        flag = false;
                    }
                }while(flag);

                flag = true;

                int brojUvreda = 0;
                System.out.print("Enter the amount of insults: ");
                do {
                    try{
                        brojUvreda = scan.nextInt();
                        scan.nextLine();
                        flag = false;
                    }catch(InputMismatchException ex){
                        scan.nextLine();
                        System.out.print("Enter the amount using numbers please: ");
                    }
                }while(flag);
                for(int i = 0;i<brojUvreda;i++) {
                    System.out.print("Type in the insult ");
                    String uvreda = scan.nextLine();
                    stmt.setInt(1, resultSet.getInt("id"));
                    stmt.setString(2, uvreda);
                    stmt.executeUpdate();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                System.out.println("Greska u sql");
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

}
