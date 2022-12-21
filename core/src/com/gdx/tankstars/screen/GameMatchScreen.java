package com.gdx.tankstars.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gdx.tankstars.TankStarsGame;
import com.gdx.tankstars.game.*;

public class GameMatchScreen extends State implements Screen {
    private TankStarsGame game;
    private ShapeRenderer shapeRenderer;
    private Texture backgroundTexture;
    private final Rectangle pauseButton = new Rectangle(1212, 25, 45, 45);
    GameMatch gameMatch;
    Tank tank1;
    Tank tank2;

    private int powerMeterY = 20;
    private int powerMeterDirection = 1;
    private boolean powerMeterStatic = false;
    private int delay = 0;

    public GameMatchScreen(TankStarsGame game) {
        super(game);
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        gameMatch = new GameMatch(game);
        this.tank1 = gameMatch.getTank1();
        this.tank2 = gameMatch.getTank2();
        game.setGameMatchData(gameMatch);
        game.setStateData(super.getState());
    };

    public GameMatchScreen(TankStarsGame game, int calledByPause) {
        super(game, 1);
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        gameMatch = game.getGameMatchData();
        this.tank1 = gameMatch.getTank1();
        this.tank2 = gameMatch.getTank2();

    }


    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("background-2.png"));
        //gameMatch.start();
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            //System.out.println(x + " | " + y);

            if (pauseButton.contains(x, y)) {
                System.out.println("Select Pause button");
                game.setScreen(new GamePauseScreen(game));
            }

        };



        float speed = gameMatch.getTank1().getSpeed();
        Vector2 tank1Pos = tank1.getPosition();
        Vector2 tank2Pos = tank2.getPosition();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            System.out.println("Press left");
            if (super.getTurn() == 1) {
                tank1.setPosition(new Vector2(tank1Pos.x - speed, tank1Pos.y));
            }
            else if (super.getTurn() == 2) {
                tank2.setPosition(new Vector2(tank2Pos.x - speed, tank2Pos.y));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            System.out.println("Press Right");
            if (super.getTurn() == 1) {
                tank1.setPosition(new Vector2(tank1Pos.x + speed, tank1Pos.y));
            }
            else if (super.getTurn() == 2) {
                tank2.setPosition(new Vector2(tank2Pos.x + speed, tank2Pos.y));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            System.out.println("Press Up");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            System.out.println("Press Down");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            System.out.println("Press Enter");
            powerMeterStatic = true;

        }



        game.getBatch().begin();
        game.getBatch().draw(new Texture(Gdx.files.internal("background-3.png")), 0, 0);
        if (super.getTurn() == 1) {
            game.getBatch().draw(new Texture(Gdx.files.internal("turn-1.png")), 550, 600, 215, 130);
        }
        else if (super.getTurn() == 2) {
            game.getBatch().draw(new Texture(Gdx.files.internal("turn-2.png")), 550, 600, 215, 130);
        }

        gameMatch.drawAssets();
        game.getBatch().end();


        // Health Bar Player 1 (100% width)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(210 / 255.0f, 210 / 255.0f, 210 / 255.0f, 1);
        shapeRenderer.rect( 120, Gdx.graphics.getHeight() - 73, 200, 30);
        shapeRenderer.end();


        // Health Bar Player 2 (100% width)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(210 / 255.0f, 210 / 255.0f, 210 / 255.0f, 1);
        shapeRenderer.rect( Gdx.graphics.getWidth() - (120 + 200), Gdx.graphics.getHeight() - 73, 200, 30);
        shapeRenderer.end();


        float tank1Width = 200 * (float) tank1.getHealth() / 100;
        float tank2Width = 200 * (float) tank2.getHealth() / 100;

        // Health Meter Player 1 (variable width)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(247 / 255.0f, 64 / 255.0f, 64 / 255.0f, 1);
        shapeRenderer.rect( 120, Gdx.graphics.getHeight() - 73, tank1Width, 30);
        shapeRenderer.end();


        // Health Meter Player 2 (variable width)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(247 / 255.0f, 64 / 255.0f, 64 / 255.0f, 1);
        shapeRenderer.rect( Gdx.graphics.getWidth() - (120 + 200), Gdx.graphics.getHeight() - 73, tank2Width, 30);
        shapeRenderer.end();


        // Fuel Meter (100% width)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(210 / 255.0f, 210 / 255.0f, 210 / 255.0f, 1);
        shapeRenderer.rect( 100, 50, 150, 30);
        shapeRenderer.end();


        int fuel = 100;
        if (super.getTurn() == 1) {
            fuel = tank1.getFuel();
        }
        else if (super.getTurn() == 2) {
            fuel = tank2.getFuel();
        }

        float fuelMeterWidth = 150 * (float) fuel / 100;
        // remove the if statement below when not in development
        if (fuelMeterWidth == 0) {
        }

        // Fuel Meter (variable width)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(248 / 255.0f, 193 / 255.0f, 47 / 255.0f, 1);
        shapeRenderer.rect( 100, 50, fuelMeterWidth, 30);
        shapeRenderer.end();



        // Power Meter

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(144 / 255.0f, 248 / 255.0f, 47 / 255.0f, 1);
        shapeRenderer.rect( 1150, 20, 25, 130);
        shapeRenderer.end();


        // Power Meter Cursor
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(247 / 255.0f, 64 / 255.0f, 64 / 255.0f, 1);
        shapeRenderer.rect( 1150, powerMeterY, 25, 5);
        shapeRenderer.end();

        if (tank1.getHealth() <= 0) {
            game.setScreen(new VictoryScreen(game, new Texture(Gdx.files.internal("victory2.png"))));
        }
        if (tank2.getHealth() <= 0) {
            game.setScreen(new VictoryScreen(game, new Texture(Gdx.files.internal("victory1.png"))));
        }


        if (!powerMeterStatic) {
            if (powerMeterY <= (20)) {
                powerMeterDirection = 1;
            }
            if (powerMeterY >= (20 + 130 - 5)) {
                powerMeterDirection = 0;
            }
            if (powerMeterDirection == 1) {
                powerMeterY += 1;
            }
            if (powerMeterDirection == 0) {
                powerMeterY -= 1;
            }
        }
        if (powerMeterStatic) {
            delay++;
            if (delay >= 100) {
                powerMeterY = 20;
                powerMeterStatic = false;
                powerMeterDirection = 1;
                tank1.refillFuel();
                tank2.refillFuel();
                changeTurn();
                delay = 0;

            }
        }




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
