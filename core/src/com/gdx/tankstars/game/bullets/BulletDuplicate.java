package com.gdx.tankstars.game.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
        this.velocity =90;
        this.angle = 45;
        this.setParameters(velocity, angle);
    }

    public boolean moveRight(float delta){
        velocity_x = velocity_x;
        velocity_y = (float) (velocity_y - 9.81*delta);
        position.x += velocity_x*delta;
        position.y = position.y + velocity_y*delta;

        if (position.y <= 160){
            return false;

        }
        return true;
    }

    public void draw(SpriteBatch batch){
        batch.draw(bulletTexture, position.x, position.y);



    }
}
