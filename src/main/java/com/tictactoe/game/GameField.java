package com.tictactoe.game;


import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

public class GameField {

    Empty[][] empty = new Empty[3][3];
    Robot robot;

    public GameField(Empty[][] empty, Robot robot) {
        this.empty = empty;
        this.robot = robot;
    }

    public void setEmpty(){
        for(int i = 0;i < 3; i++){
            for(int j=0;j<3;j++){
                empty[i][j] = new Empty();
            }
        }
    }

    public Empty[][] getEmpty(){
        return this.empty;
    }

    public void playerMove(){
        Scanner scan = new Scanner(System.in);
        boolean flag=true;
        do {
            try {
                int x = scan.nextInt();
                scan.nextLine();
                int y = scan.nextInt();
                scan.nextLine();
                if (!(this.empty[x][y] instanceof Circle) && !(this.empty[x][y] instanceof Cross)) {
                    this.empty[x][y] = new Circle();
                    drawCurrentBoard();
                    flag = false;
                }


            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("Unijeli ste nevaljanjo mjesto, bez pokusaja varanja molim");
            }
        }while(flag);
        checkWin();
        aiThink();
    }

    public void drawCurrentBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.empty[i][j].draw();
            }
            System.out.println();
        }
    }


    public void checkWin(){
        int brojacP = 0;
        int brojacB = 0;

        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if (this.empty[i][j] instanceof Circle){
                    brojacP++;
                }
                if (this.empty[i][j] instanceof Cross){
                    brojacB++;
                }
            }
            if(brojacP==3){
                pobjedaIgraca();
            }
            if(brojacB==3){
                pobjedaAI();
            }
            brojacP=0;
            brojacB=0;
        }

        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if (this.empty[j][i] instanceof Circle){
                    brojacP++;
                }
                if (this.empty[j][i] instanceof Cross){
                    brojacB++;
                }
            }
            if(brojacP==3){
                pobjedaIgraca();
            }
            if(brojacB==3){
                pobjedaAI();
            }
            brojacP=0;
        }
        if(this.empty[0][0] instanceof Circle && this.empty[1][1] instanceof Circle && this.empty[2][2] instanceof Circle)
        {
            pobjedaIgraca();
        }else
            if(this.empty[0][0] instanceof Cross && this.empty[1][1] instanceof Cross && this.empty[2][2] instanceof Cross){
                pobjedaAI();
            }

        if(this.empty[2][0] instanceof Circle && this.empty[1][1] instanceof Circle && this.empty[0][2] instanceof Circle){
            pobjedaIgraca();
        }else
            if(this.empty[2][0] instanceof Circle && this.empty[1][1] instanceof Circle && this.empty[0][2] instanceof Circle){
            pobjedaAI();
    }
    }

    private void pobjedaAI(){
        System.out.println("Robotska nadmoc");
        System.exit(0);
    }
    private void  pobjedaIgraca(){
        System.out.println("Pobijedio je igrac");
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


