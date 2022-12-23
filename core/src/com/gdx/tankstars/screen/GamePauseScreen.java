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

public class GamePauseScreen implements Screen {
    private transient TankStarsGame game;
    private GameMatch gameMatch;
    private transient Texture pauseScreenTexture;
    private final Rectangle buttonResume = new Rectangle(513, 302, 248, 70);
    private final Rectangle buttonSave = new Rectangle(513, 417, 248, 70);
    private final Rectangle buttonStartMenu = new Rectangle(513, 532, 248, 70);

    public GamePauseScreen(TankStarsGame game, GameMatch gameMatch) {
        this.game = game;
        this.gameMatch = gameMatch;
    }


    @Override
    public void show() {
        pauseScreenTexture = new Texture(Gdx.files.internal("pause-screen.png"));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (buttonResume.contains(x, y)) {
                System.out.println("Press resume button");
                game.setScreen(new GameMatchScreen(game, 1));
                this.dispose();
            }
            else if (buttonSave.contains(x, y)) {
                System.out.println("Press save button");
                game.setScreen(new StartScreen(game));
                System.out.println("eee -> " + gameMatch.getState().getTurn());
                SaveData saveData = new SaveData(gameMatch);
                Serialize serialize = new Serialize(saveData);
                try {
                    serialize.save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            else if (buttonStartMenu.contains(x, y)) {
                System.out.println("Press main menu button");
                game.setScreen(new StartScreen(game));
                this.dispose();
            }
            else {
                System.out.printf("GamePauseScreen: x: %d  y:%d\n", x, y);
            }

        }

        game.getBatch().begin();
        game.getBatch().draw(pauseScreenTexture, 0, 0);
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
        pauseScreenTexture.dispose();
    }
}
