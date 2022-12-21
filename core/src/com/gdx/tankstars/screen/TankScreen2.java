package com.gdx.tankstars.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.tankstars.TankStarsGame;

public class TankScreen2 implements Screen  {

    private final TankStarsGame game;
    private Texture tankScreen;
    private Texture buttonStart;
    private String screenPath;
    private int player;

    private final Rectangle tank1_rect = new Rectangle(835, 302, 118, 118);
    private final Rectangle tank2_rect = new Rectangle(978,302,118, 118);
    private final Rectangle tank3_rect = new Rectangle(1126, 302, 118, 118);
    private final Rectangle buttonStart_rect = new Rectangle(914, 541, 1163 - 914, 620 - 541);

    public TankScreen2(TankStarsGame game, String screenPath, int player) {
        this.game = game;
        this.screenPath = screenPath;
        this.player = player;
    }

    public void setScreenPath(String path) {
        this.screenPath = path;
    }

    public String getScreenPath() {
        return this.screenPath;
    }


    @Override
    public void show() {
        tankScreen = new Texture(Gdx.files.internal(screenPath));
        buttonStart = new Texture(Gdx.files.internal("button-start.png"));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (tank1_rect.contains(x, y)) {
                System.out.println("Select tank 1");
                game.setScreen(new BlazeTankScreenP2(game, 1));
                this.dispose();
            }
            else if (tank2_rect.contains(x, y)) {
                System.out.println("Select tank 2");
                game.setScreen(new FrostbiteTankScreenP2(game, 2));
                this.dispose();
            }
            else if (tank3_rect.contains(x, y)) {
                System.out.println("Select tank 3");
                game.setScreen(new BeastTankScreenP2(game, 3));
                this.dispose();
            }
            else if (buttonStart_rect.contains(x, y)) {
                System.out.println("Start button");
                game.setPlayer2(this.player);
                game.setScreen(new GameMatchScreen(game));
                this.dispose();
            }
            else {
                System.out.printf("TankScreen: x: %d  y:%d\n", x, y);
            }
        }

        game.getBatch().begin();
        game.getBatch().draw(tankScreen, 0, 0);
        game.getBatch().draw(buttonStart, 813, 0); // had to use weird unrealistic coords here due to libgdx being weird.
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        this.tankScreen.dispose();
    }
}
