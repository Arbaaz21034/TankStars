package com.gdx.tankstars.game.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.tankstars.game.Tank;

import java.util.*;

public class BulletDuplicate {

    Texture bulletTexture;

    private Vector2 position;

    private float velocity_x;
    private float velocity_y;
    private float angle;
    private float velocity;

    private boolean motion;

    public void setParameters(float velocity, float angle) {

        this.velocity_x = (float) (velocity*Math.cos(Math.toRadians(angle)));
        this.velocity_y = (float) (velocity*Math.sin(Math.toRadians(angle)));




    }

    public BulletDuplicate(Texture bulletTexture, Vector2 position) {
        this.bulletTexture = bulletTexture;
        this.position = position;
//        this.velocity =90;
//        this.angle = 45;
//        this.setParameters(velocity, angle);
    }

    public boolean moveRight(float delta, Tank tank){
//        delta += 50;
        velocity_y = (float) (velocity_y - 9.81*delta);
        position.x += velocity_x*delta;
        position.y = position.y + velocity_y*delta;



        if (position.y <= 160){
            tank.setAttackPoint(new Vector2(position.x, position.y));
            tank.setInitialPosition(tank.getPosition());
            return false;

        }

        if (position.x > 1280){
            tank.setAttackPoint(new Vector2(position.x, position.y));

            return false;
        }
        if (position.y > 720){
            tank.setAttackPoint(new Vector2(position.x, position.y));

            return false;
        }
        if (position.x < 0){
            tank.setAttackPoint(new Vector2(position.x, position.y));

            return false;
        }
        if (position.y < 0){
            tank.setAttackPoint(new Vector2(position.x, position.y));

            return false;
        }
        return true;
    }

    public boolean moveLeft(float delta, Tank tank){
//        delta += 50;
        velocity_y = (float) (velocity_y - 9.81*delta);
        position.x -= velocity_x*delta;
        position.y = position.y + velocity_y*delta;

        if (position.y <= 160){
            tank.setAttackPoint(new Vector2(position.x, position.y));
            tank.setInitialPosition(tank.getPosition());
            return false;

        }
        if (position.x > 1280){
            tank.setAttackPoint(new Vector2(position.x, position.y));
            return false;
        }
        if (position.y > 720){
            tank.setAttackPoint(new Vector2(position.x, position.y));
            return false;
        }
        if (position.x < 0){
            tank.setAttackPoint(new Vector2(position.x, position.y));

            return false;
        }
        if (position.y < 0){
            tank.setAttackPoint(new Vector2(position.x, position.y));

            return false;
        }
        return true;
    }

    public void draw(SpriteBatch batch){
        batch.draw(bulletTexture, position.x, position.y);



    }
}
