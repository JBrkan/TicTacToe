package com.tictactoe.game;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Robot {
    private String ime;
    private ArrayList<String> uvrede = new ArrayList<String>();

    public Robot(String ime, ArrayList<String> uvrede) {
        this.ime = ime;
        this.uvrede = uvrede;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public ArrayList<String> getUvrede() {
        return uvrede;
    }

    public void setUvrede(ArrayList<String> uvrede) {
        this.uvrede = uvrede;
    }

    public void fetchUvrede() {
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
                ResultSet resultSet = stmt.executeQuery();
                if (!resultSet.isBeforeFirst() ) {
                    System.out.println("Unesite ime robota: ");
                    fetchUvrede();
                }
                int i =0;
                while (resultSet.next()) {
                        this.getUvrede().add(resultSet.getString("uvreda"));
                    }

            }catch(SQLException e){

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
