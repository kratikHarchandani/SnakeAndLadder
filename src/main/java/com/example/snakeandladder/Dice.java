package com.example.snakeandladder;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Dice {
    private int diceCount;
    int min=1;
    int max=6;
    public Dice(int diceCount){
        this.diceCount=diceCount;
    }

    public int rollDice(){
        int totalSum=0;
        int diceUsed=0;
        while(diceUsed<diceCount){
            totalSum= ThreadLocalRandom.current().nextInt(min,max+1);
            diceUsed+=1;
        }
        return totalSum;
    }
}
