package com.gdx.tankstars.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.tankstars.TankStarsGame;
import com.gdx.tankstars.game.GameMatch;
import com.gdx.tankstars.game.SaveData;
import com.gdx.tankstars.game.Serialize;

import java.io.IOException;

public class LoadGameScreen implements Screen  {
    private TankStarsGame game;
    private Texture loadScreenTexture;
    private final Rectangle buttonLoadGame = new Rectangle(513, 417, 248, 70);
    private final Rectangle buttonBack = new Rectangle(513, 532, 248, 70);


    public LoadGameScreen(TankStarsGame game) {
        this.game = game;
    }



    @Override
    public void show() {
        loadScreenTexture = new Texture(Gdx.files.internal("load-game-screen.png"));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (buttonLoadGame.contains(x, y)) {
                System.out.println("Press load game button");
                try {
                    GameMatch gameMatch = Serialize.load();
                    System.out.println(gameMatch.getState().getTurn());
                    game.setScreen(new GameMatchScreen(game, gameMatch));
                    this.dispose();

                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (buttonBack.contains(x, y)) {
                System.out.println("Press back button");
                game.setScreen(new StartScreen(game));
                this.dispose();
            }
            else {
                System.out.printf("LoadGameScreen: x: %d  y:%d\n", x, y);
            }

        }

        game.getBatch().begin();
        game.getBatch().draw(loadScreenTexture, 0, 0);
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
