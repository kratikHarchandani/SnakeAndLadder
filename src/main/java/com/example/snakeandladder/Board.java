package com.example.snakeandladder;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Board {
    private Cell[][] cells;

    public Board(int boardSize,int numberOfSnakes,int numberOfLadders){
        initializeCells(boardSize);
        addSnakesLadders(cells,numberOfSnakes,numberOfLadders);
    }

    public void initializeCells(int boardSize){
        cells=new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                Cell cellObj=new Cell();
                cells[i][j]=cellObj;
            }
        }
    }

    public void addSnakesLadders(Cell [][]cells,int numberOfSnakes,int numberOfLadders){
        while(numberOfSnakes>0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            if(snakeTail>=snakeHead){
                continue;
            }
            Jump snakeObj=new Jump();
            snakeObj.setStart(snakeHead);
            snakeObj.setEnd(snakeTail);

            Cell cell=getCell(snakeHead);
            cell.setJump(snakeObj);
            numberOfSnakes-=1;
        }

        while(numberOfLadders>0){
            int ladderStart=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int ladderEnd=ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);
            if(ladderStart>=ladderEnd){
                continue;
            }
            Jump tailObj=new Jump();
            tailObj.setStart(ladderStart);
            tailObj.setEnd(ladderEnd);

            Cell cell=getCell(ladderStart);
            cell.setJump(tailObj);
            numberOfLadders-=1;
        }
    }
    public Cell getCell(int snakeHead){
        int boardRow=snakeHead/ cells.length;
        int boardCol=boardRow% cells.length;
        return cells[boardRow][boardCol];
    }
}
