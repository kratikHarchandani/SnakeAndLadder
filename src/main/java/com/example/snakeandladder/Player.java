package com.example.snakeandladder;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private int currentposition;

    public Player(String name,int currentposition){
        this.name=name;
        this.currentposition=currentposition;
    }
}
