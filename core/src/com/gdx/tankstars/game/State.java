package com.gdx.tankstars.game;


import com.gdx.tankstars.TankStarsGame;

import java.io.Serializable;


public class State implements Serializable {
    private transient TankStarsGame game;
    private int turn; // 1 for player 1, 2 for player 2
    private Tank1 tank1;
    private Tank2 tank2;

    public State(TankStarsGame game) {
        this.game = game;
        this.turn = 1;
    }

    public State(TankStarsGame game, int calledByPause) {
        this.game = game;
        this.turn = game.getStateData().getTurn();
    };

    public State(TankStarsGame game, GameMatch gameMatch) {
        this.game = game;
        this.turn = gameMatch.getState().getTurn();
        System.out.println(this.turn);
    }

    public State() {
        
    }

    public State getState() {
        return this;
    }

    public int getTurn() {
        return this.turn;
    }

    public void changeTurn() {
        if (turn == 1) {
            turn = 2;
        }
        else if (turn == 2){
            turn = 1;
        }
    }



}
