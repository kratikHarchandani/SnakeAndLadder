package com.example.snakeandladder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Game {
    private Board board;
    private Dice dice;
    Deque<Player> playerList=new ArrayDeque<>();

    private Player winner;

    public Game(){
        initializeGame();
    }

    public void initializeGame(){
        board=new Board(10,10,2);
        winner=null;
        dice=new Dice(1);
        addPlayers();
    }

    public void addPlayers(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Tell me the name of player1: ");
        String name1=sc.nextLine();
        Player p1=new Player(name1,0);
        System.out.println("Tell me the name of Player2: ");
        String name2=sc.nextLine();
        Player p2=new Player(name2,0);
        playerList.add(p1);
        playerList.add(p2);

    }

    public void startGame(){
        while(winner==null){
            Player playerTurn=findPlayerTurn();
            System.out.println("player turn is : "+playerTurn.getName()
                    +" current position is : "+playerTurn.getCurrentposition());

            int diceNumbers= dice.rollDice();

            int playerNewPosition=playerTurn.getCurrentposition()+diceNumbers;
            playerNewPosition=jumpCheck(playerNewPosition);
            playerTurn.setCurrentposition(playerNewPosition);
            System.out.println("player turn is : "+playerTurn.getName()
                                +" current position is : "+playerNewPosition);
            if(playerNewPosition>board.getCells().length*board.getCells().length-1){
                winner=playerTurn;
            }
        }
        System.out.println("Winner is : "+winner.getName());
    }
    private Player findPlayerTurn(){
        Player playerTurn=playerList.removeFirst();
        playerList.addLast(playerTurn);
        return playerTurn;
    }

    private int jumpCheck(int playerNewPosition){
        if(playerNewPosition>board.getCells().length*board.getCells().length-1){
            return playerNewPosition;
        }
        Cell cell=board.getCell(playerNewPosition);
        if(cell.getJump()!=null&&cell.getJump().getStart()==playerNewPosition){
            String jumpBy=(cell.getJump().getStart()<cell.getJump().getEnd())?"Ladder":"Snake";
            System.out.println("jump done by: "+jumpBy);
            return cell.getJump().getEnd();
        }
        return playerNewPosition;
    }


}
