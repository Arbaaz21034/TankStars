package com.gdx.tankstars.game;

import com.gdx.tankstars.TankStarsGame;

import java.io.Serializable;

public class SaveData implements Serializable {
    private TankStarsGame game;

    public SaveData(TankStarsGame game) {
        this.game = game;
    }

}
