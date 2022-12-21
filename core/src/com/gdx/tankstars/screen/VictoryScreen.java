package com.gdx.tankstars.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.tankstars.TankStarsGame;

public class VictoryScreen implements Screen {
    private TankStarsGame game;
    private Texture texture;
    private int delay;


    public VictoryScreen(TankStarsGame game, Texture texture) {
        this.game = game;
        this.texture = texture;

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        delay++;
        if (delay >= 200) {
            game.setScreen(new StartScreen(game));
            this.dispose();
        }
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
