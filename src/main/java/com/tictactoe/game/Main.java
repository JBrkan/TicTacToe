package com.tictactoe.game;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        }
        Scanner scan = new Scanner(System.in);
        String ime = null;
        ArrayList<String> uvrede = new ArrayList<String>();

        Robot robot = new Robot(ime, uvrede);
        robot.fetchUvrede();

        Empty[][] gameArray = new Empty[3][3];
        GameField game = new GameField(gameArray,robot);
        game.setEmpty();
        game.playerMove();

    }
}
