package com.gdx.tankstars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.tankstars.TankStarsGame;
import com.gdx.tankstars.game.bullets.BulletDuplicate;

import java.io.Serializable;
import java.util.Random;

public class GameMatch extends State implements Serializable {
    private transient TankStarsGame game;
    private Tank tank1;
    private Tank tank2;

    private BulletDuplicate bullet;


    public Tank getTank2() {
        return tank2;
    }

    public Tank getTank1() {
        return tank1;
    }

    public GameMatch(TankStarsGame game) {
        super(game);
        this.game = game;
        this.tank1 = new Tank1(new Vector2(100, 200));
        this.tank2 = new Tank2(new Vector2(1100, 200));
        tank1.setPlayer(1);
        tank2.setPlayer(2);

        int t1 = game.getPlayer1();
        int t2 = game.getPlayer2();
        Random r = new Random();
        if (t1 == 0) {
            t1 = r.nextInt(3 - 1 + 1) + 1;
        }
        if (t2 == 0) {
            t2 = r.nextInt(3 - 1 + 1) + 1;
        }
        tank1.setType(t1);
        tank2.setType(t2);
    }

    public GameMatch() {
        super();
        System.out.println("-----------");
        System.out.println(tank1.getType());
        System.out.println(tank2.getType());
        tank1.setType(tank1.getType());
        tank2.setType(tank2.getType());
    }

    public void provideGame(TankStarsGame game) {
        this.game = game;
    }

    public void start() {
        System.out.println("Game has been started.");
        this.drawAssets();
    }

    public void drawAssets() {
        tank1.draw(game.getBatch());
        tank2.draw(game.getBatch());
    }

}
