package com.gdx.tankstars.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.tankstars.TankStarsGame;

public class StartScreen implements Screen {

    private TankStarsGame game;
    private Texture startScreenTexture;
    private final Rectangle buttonPlay_rect = new Rectangle(522, 266, 236, 56);
    private final Rectangle buttonLoad_rect = new Rectangle(522, 353, 236,  56);
    private final Rectangle buttonExit_rect = new Rectangle(522, 439, 236,  56);
    public StartScreen(TankStarsGame game) {
        this.game = game;
    }


    @Override
    public void show() {
        startScreenTexture = new Texture(Gdx.files.internal("start-screen.png"));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (buttonPlay_rect.contains(x, y)) {
                System.out.println("Select play button");
                game.setScreen(new MenuScreen(game));
                this.dispose();
            }
            else if (buttonLoad_rect.contains(x, y)) {
                System.out.println("Select load button");
                game.setScreen(new LoadGameScreen(game));
                this.dispose();
            }
            else if (buttonExit_rect.contains(x, y)) {
                System.out.println("Select exit button");
                this.dispose();
                System.exit(0);
            }
            else {
                System.out.printf("StartScreen: x: %d  y:%d\n", x, y);
            }


        }

        game.getBatch().begin();
        game.getBatch().draw(startScreenTexture, 0, 0);
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
        startScreenTexture.dispose();
    }
}
