package com.gdx.tankstars.screen;

import com.gdx.tankstars.TankStarsGame;

public class BeastTankScreenP2 extends TankScreen2 {
    private TankStarsGame game;

    public BeastTankScreenP2(TankStarsGame game, int player) {
        super(game, "tank-3-screen-2.png", 3);
        this.game = game;
    }
}
