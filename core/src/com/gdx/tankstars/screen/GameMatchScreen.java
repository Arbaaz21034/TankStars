package com.gdx.tankstars.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.gdx.tankstars.TankStarsGame;
import com.gdx.tankstars.game.*;
import com.gdx.tankstars.game.bullets.BulletDuplicate;

public class GameMatchScreen extends State implements Screen {
    private TankStarsGame game;
    private ShapeRenderer shapeRenderer;
    private Texture backgroundTexture;
    private final Rectangle pauseButton = new Rectangle(1212, 25, 45, 45);
    private GameMatch gameMatch;
    private Tank tank1;
    private Tank tank2;
    private BulletDuplicate bullet;
    Texture tex = new Texture(Gdx.files.internal("bullet-2.png"));


    private int powerMeterY = 20;
    private int powerMeterDirection = 1;
    private boolean powerMeterStatic = true;

    private int angleMeterY = 20;
    private int angleMeterDirection = 1;
    private boolean angleMeterStatic = false;

    private boolean onDelay = false;
    private int delay = 0;
    private int paramCount = 0;
    private boolean showBullet = false;

    private boolean showDamage1 = false;
    private boolean showDamage2 = false;

    private float damage;



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

        // --------------------------------------------------
        // This is for Handling Pause button

        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            System.out.println(x + " | " + y);

            if (pauseButton.contains(x, y)) {
                System.out.println("Select Pause button");
                game.setScreen(new GamePauseScreen(game));
            }

        };
        // --------------------------------------------------

        //<<<<-------------------------------------------------
        // Handling Tank Movement
        float speed = gameMatch.getTank1().getSpeed();
        Vector2 tank1Pos = tank1.getPosition();
        Vector2 tank2Pos = tank2.getPosition();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            System.out.println("Press left");
            if (super.getTurn() == 1) {
                tank1.setPosition(new Vector2(tank1Pos.x - speed, tank1Pos.y));
            }
            else if (super.getTurn() == 2) {
                tank2.setPosition(new Vector2(tank2Pos.x - speed, tank2Pos.y));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            System.out.println("Press Right");
            if (super.getTurn() == 1) {
                tank1.setPosition(new Vector2(tank1Pos.x + speed, tank1Pos.y));
            }
            else if (super.getTurn() == 2) {
                tank2.setPosition(new Vector2(tank2Pos.x + speed, tank2Pos.y));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            System.out.println("Press Up");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            System.out.println("Press Down");
        }
        // ----------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//            System.out.println("Press Enter");
            if (!powerMeterStatic) {
                powerMeterStatic = true;
//                onDelay = true;
                showBullet = true;

            }
            if (!angleMeterStatic) {
                angleMeterStatic = true;
                powerMeterStatic = false;
            }
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

        // Angle Meter

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(47 / 255.0f, 169 / 255.0f, 248 / 255.0f, 1);
        shapeRenderer.rect( 1000, 20, 25, 130);
        shapeRenderer.end();


        // Power Meter Cursor
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(247 / 255.0f, 64 / 255.0f, 64 / 255.0f, 1);
        shapeRenderer.rect( 1150, powerMeterY, 25, 5);
        shapeRenderer.end();


        // Angle Meter Cursor
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(247 / 255.0f, 64 / 255.0f, 64 / 255.0f, 1);
        shapeRenderer.rect( 1000, angleMeterY, 25, 5);
        shapeRenderer.end();


        if (tank1.getHealth() <= 0) {
            game.setScreen(new VictoryScreen(game, new Texture(Gdx.files.internal("victory2.png"))));
        }
        if (tank2.getHealth() <= 0) {
            game.setScreen(new VictoryScreen(game, new Texture(Gdx.files.internal("victory1.png"))));
        }

        if (!angleMeterStatic) {
            if (angleMeterY <= (20)) {
                angleMeterDirection = 1;
            }
            if (angleMeterY >= (20 + 130 - 5)) {
                angleMeterDirection = 0;
            }
            if (angleMeterDirection == 1) {
                angleMeterY += 1;
            }
            if (angleMeterDirection == 0) {
                angleMeterY -= 1;
            }
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

        if (onDelay) {
            float x = getAngle();
            delay++;
            if (delay >= 50) {
                powerMeterY = 20;
                angleMeterY = 20;
                angleMeterStatic = false;
                powerMeterStatic = true;
                powerMeterDirection = 1;
                angleMeterDirection = 1;
                tank1.refillFuel();
                tank2.refillFuel();
                changeTurn();
                paramCount = 0;
                delay = 0;
                onDelay = false;
            }
        };


        if (showBullet) {
            if (super.getTurn() == 1) {


                if (paramCount == 0) {
                    bullet = new BulletDuplicate(tex, new Vector2(tank1.getPosition().x, tank1.getPosition().y));
                    bullet.setParameters(getPower(), getAngle());
                    paramCount = 1;
                }
                if (bullet.moveRight(delta, tank2)) {
                    game.getBatch().begin();
                    bullet.draw(game.getBatch());
                    game.getBatch().end();
                }
                else {
                    if (tank2.getAttackPoint().y > 160){
                        onDelay = true;
                    }
                    else{
                        damage = tank2.calculateDamage();
                        System.out.println("The Damage is: "+ damage);
                        if (damage > 0){
                            showDamage2 = true;
                            tank2.doDamage((int)damage);
                        }
                        else{
                            onDelay = true;
                        }
                    }
                    showBullet = false;
                }
            }
            else if (super.getTurn() == 2) {

                if (paramCount == 0) {
                    bullet = new BulletDuplicate(tex, new Vector2(tank2.getPosition().x,tank2.getPosition().y));
                    bullet.setParameters(getPower(), getAngle());
                    paramCount = 1;
                }
                if (bullet.moveLeft(delta, tank1)) {
                    game.getBatch().begin();
                    bullet.draw(game.getBatch());
                    game.getBatch().end();
                }

                else {
                    if (tank1.getAttackPoint().y > 160){
                        onDelay = true;
                    }
                    else{
                        damage = tank1.calculateDamage();
                        System.out.println("The Damage is: "+ damage);
                        if (damage > 0){
                            showDamage1 = true;
                            tank1.doDamage((int)damage);
                        }
                        else{
                            onDelay = true;
                        }
                    }
                    showBullet = false;
                }

            }



        }
        if (showDamage1){
//            System.out.println("YESsss");
//            System.out.println(tank1.getPosition().x);
            if(!tank1.damageControl(damage)){
//                System.out.println("YES");
                showDamage1 = false;
                onDelay = true;
            }

        }
        if (showDamage2){
//            System.out.println("YESsss");
//            System.out.println(tank1.getPosition().x);
            if(!tank2.damageControl(damage)){
//                System.out.println("YES");
                showDamage2 = false;
                onDelay = true;
            }

        }



    }

    public float getAngle() {
        float w = (float) angleMeterY / 145;
        float res = (float) w * 100;
        float res2 = res * 1.8f;
//        System.out.println("This is Angle: "+ res);
        return res;

    };

    public float getPower() {
        float w = (float) powerMeterY / 145;
        float res = (float) w * 100;
//        System.out.println("This is Power: "+ res);
        return res;
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
