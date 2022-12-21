package com.gdx.tankstars.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.gdx.tankstars.TankStarsGame;

public class GameMatchScreenStatic implements Screen {
    private TankStarsGame game;
    private Texture gameMatchScreenTexture;
    private Texture pauseButtonTexture;
    private Circle pauseButton = new Circle(1248, 32, 18);

    public GameMatchScreenStatic(TankStarsGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        gameMatchScreenTexture = new Texture(Gdx.files.internal("game-match-screen.png"));
        pauseButtonTexture = new Texture(Gdx.files.internal("button-pause.png"));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (pauseButton.contains(x, y)) {
                System.out.println("Press pause button");
                game.setScreen(new GamePauseScreen(game));
                this.dispose();
            }

            else {
                System.out.printf("GameMatchScreen: x: %d  y:%d\n", x, y);
            }
        }


        game.getBatch().begin();
        game.getBatch().draw(gameMatchScreenTexture, 0, 0);
        game.getBatch().draw(pauseButtonTexture, 1230 , 670);
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
        gameMatchScreenTexture.dispose();
        pauseButtonTexture.dispose();
    }

}
