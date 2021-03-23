package com.tictactoe.game;


import com.tictactoe.robot.Robot;

import java.util.Random;
import java.util.Scanner;

public class GameField {

    Empty[][] empty;
    Robot robot;

    public GameField(Empty[][] empty, Robot robot) {
        this.empty = empty;
        this.robot = robot;
    }

    public void begin(){
        for(int i = 0;i < 3; i++){
            for(int j=0;j<3;j++){
                empty[i][j] = new Empty();
                empty[i][j].draw();
            }
            System.out.println();
        }
        playerMove();
    }

    private void playerMove(){
        Scanner scan = new Scanner(System.in);
        boolean flag=true;
        do {
            try {
                System.out.print("Enter coordinate x: ");
                int x = scan.nextInt() -1;
                System.out.print("Enter coordinate y: ");
                scan.nextLine();
                int y = scan.nextInt() -1;
                scan.nextLine();
                if (!(this.empty[x][y] instanceof Circle) && !(this.empty[x][y] instanceof Cross)) {
                    this.empty[x][y] = new Circle();
                    drawCurrentBoard();
                    flag = false;
                }


            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("Enter a valid field");
            }
        }while(flag);
        checkWin();
        aiThink();
    }

    private void drawCurrentBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.empty[i][j].draw();
            }
            System.out.println();
        }
    }


    private void checkWin(){
        int counterP = 0;
        int counterB = 0;

        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if (this.empty[i][j] instanceof Circle){
                    counterP++;
                }
                if (this.empty[i][j] instanceof Cross){
                    counterB++;
                }
            }
            if(counterP==3){
                victoryPlayer();
            }
            if(counterB==3){
                victoryAI();
            }
            counterP=0;
            counterB=0;
        }

        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if (this.empty[j][i] instanceof Circle){
                    counterP++;
                }
                if (this.empty[j][i] instanceof Cross){
                    counterB++;
                }
            }
            if(counterP==3){
                victoryPlayer();
            }
            if(counterB==3){
                victoryAI();
            }
            counterP=0;
        }
        if(this.empty[0][0] instanceof Circle && this.empty[1][1] instanceof Circle && this.empty[2][2] instanceof Circle)
        {
            victoryPlayer();
        }else
            if(this.empty[0][0] instanceof Cross && this.empty[1][1] instanceof Cross && this.empty[2][2] instanceof Cross){
                victoryAI();
            }

        if(this.empty[2][0] instanceof Circle && this.empty[1][1] instanceof Circle && this.empty[0][2] instanceof Circle){
            victoryPlayer();
        }else
            if(this.empty[2][0] instanceof Circle && this.empty[1][1] instanceof Circle && this.empty[0][2] instanceof Circle){
            victoryAI();
    }
    }

    private void victoryAI(){
        System.out.println("The AI has somehow outsmarted you?");
        System.exit(0);
    }
    private void  victoryPlayer(){
        System.out.println("You WON!");
        System.out.println("You WON!");
        System.exit(0);
    }

    private void aiThink(){
        System.out.println("The ai brainstorms ideas to win");
        Random random = new Random();
        System.out.println(robot.getUvrede().get(random.nextInt(this.robot.getUvrede().size())));
        aiMove();

    }
    private void aiMove() {

        if(!(this.empty[1][1] instanceof Circle) && !(this.empty[1][1] instanceof Cross)){
            this.empty[1][1] = new Cross();
        }else
        if(!(this.empty[0][0] instanceof Circle) && !(this.empty[0][0] instanceof Cross)){
            this.empty[0][0] = new Cross();
        } else
        if(!(this.empty[2][2] instanceof Circle) && !(this.empty[2][2] instanceof Cross)){
            this.empty[2][2] = new Cross();
        }
        drawCurrentBoard();
        checkWin();
        playerMove();
    }
}


