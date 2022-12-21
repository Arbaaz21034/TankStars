package com.gdx.tankstars.screen;

import com.gdx.tankstars.TankStarsGame;

public class BlazeTankScreen extends TankScreen {
    private TankStarsGame game;

    public BlazeTankScreen(TankStarsGame game, int player) {
        super(game, "tank-1-screen.png",1);
        this.game = game;
    }

}
