package com.tictactoe.main;

import com.tictactoe.game.Empty;
import com.tictactoe.game.GameField;
import com.tictactoe.robot.Robot;




public class Main {


    public static void main(String[] args){


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            System.out.println("Driver not found");
        }


        Robot robot = new Robot();
        robot.addRobot();
        robot.addInsultToRobot();
        robot.fetchRobot();
        robot.fetchUvrede();

        Empty[][] gameArray = new Empty[3][3];
        GameField game = new GameField(gameArray,robot);
        game.begin();


    }
}
