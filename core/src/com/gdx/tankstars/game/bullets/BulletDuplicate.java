package com.gdx.tankstars.game.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

public class BulletDuplicate {

    Texture bulletTexture;

    private Vector2 position;

    public BulletDuplicate(Texture bulletTexture, Vector2 position) {
        this.bulletTexture = bulletTexture;
        this.position = position;
    }

    public int moveRight(int force, int angle, Vector2 f){

            if (position.x >= f.x){
                return 1;

            }
            this.position.x += 1;
            this.position.y = (float) ((float) (this.position.x*Math.tan(angle)) - ((10*this.position.x*this.position.x)/(2*force*force*Math.cos(angle))));
            return 0;

    }

    public void draw(SpriteBatch batch){
        System.out.println("YYA");
        batch.draw(bulletTexture, position.x, position.y);



    }
}
