package com.gdx.tankstars.game;

import com.gdx.tankstars.TankStarsGame;

import java.io.Serializable;

public class SaveData implements Serializable {
    private GameMatch gameMatch;

    public SaveData(GameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    public GameMatch getGameMatch() {
        return this.gameMatch;
    }

}
