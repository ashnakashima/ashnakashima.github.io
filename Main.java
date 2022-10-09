//Name: Ashlyn Nakashima
//Project: Nakashima_HW3
//Date: February 27, 2022
//Description: Play game, Greedy 100, first player to roll over 100 wins

package com.company;

import java.util.*;

class Dice {
    private int die1;
    private int die2;

    public Dice(){
        die1 = 0;
        die2 = 0;
    }

    //generates a random number between 1-6
    //representing rolling the dice
    public void roll(){
        Random rand = new Random();
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
    }

    public int getDie1(){
        return die1;
    }

    public int getDie2(){
        return die2;
    }
}

class Player{
    private String _name;
    private int _score;

    public Player(){
        _name = " ";
        _score = 0;
    }

    public Player(String name, int score){
        _name = name;
        _score = score;
    }

    public void setName(String name){ _name = name; }
    public String getName(){ return _name;}
    public void setScore(int x){ _score = x; }
    public int getScore(){return _score;}
    //adds new score to old score value to get total score
    public void addScore(int x){ _score = _score + x; }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Greedy 100!");
        //prompts user to enter number of players and creates array of player objects
        System.out.println("Enter the # of Players: ");
        Scanner scan = new Scanner(System.in);
        int numPlayers = scan.nextInt();
        Player[] players = new Player[numPlayers];
        //prompts user to enter player name and sets that name in player object as name variable
        for(int i=0; i<numPlayers; i++){
            players[i] = new Player();
            System.out.println("Enter player " + (i+1) + " name: ");
            String playerName = scan.next();
            players[i].setName(playerName);
            players[i].setScore(0);
        }
        boolean winner = false;
        int ctr = 0;
        while(winner == false && ctr < players.length){
            System.out.println(players[ctr].getName() + "'s turn");
            //prompts player to roll
            System.out.println("Enter 'r' to roll");
            String rolling = scan.next();
            if(rolling.equals("r")){
                Dice d = new Dice();
                d.roll();
                System.out.println("Die 1: " + d.getDie1() + "    Die 2: " + d.getDie2());
                if(d.getDie1() == 1 || d.getDie2() == 1 ){
                    System.out.println("YOU ROLLED A 1");
                    System.out.println("YOU LOSE YOUR TURN AND SCORE IS NOT ADDED");
                    System.out.println(players[ctr].getName() + ": " + players[ctr].getScore());
                    System.out.println(" ");
                }
                else if(d.getDie1() == 1 && d.getDie2() == 1){
                    System.out.println("BOTH DIE CONTAIN A 1");
                    System.out.println("YOU LOSE YOUR TURN AND SCORE RESTARTS TO 0");
                    players[ctr].setScore(0);
                    System.out.println(players[ctr].getName() + ": " + players[ctr].getScore());
                    System.out.println(" ");
                }
                else {
                    System.out.println("BOTH DIE WILL BE ADDED TO SCORE");
                    players[ctr].addScore(d.getDie1());
                    players[ctr].addScore(d.getDie2());
                    System.out.println(players[ctr].getName() + ": " + players[ctr].getScore());
                    System.out.println(" ");
                    //falls out of while loop if there is a winner
                    if(players[ctr].getScore() >= 100){
                        winner = true;
                    }
                }
            }
            //changes player's turn
            ctr++;
            //when ctr reaches the last player, resets to 0
            if(ctr == players.length){
                ctr = 0;
            }
        }
        System.out.println("CONGRATULATIONS " + players[ctr-1].getName());
        System.out.println("YOU ARE THE WINNER!");
        System.out.println(" ");
        System.out.println("FINALS SCORES");
        //sopls out final scores for all players
        for(int i = 0; i<players.length; i++){
            System.out.println(players[i].getName() + ": " + players[i].getScore());
        }
    }
}


