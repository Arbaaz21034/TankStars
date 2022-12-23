package com.gdx.tankstars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Tank2 extends Tank implements Serializable {
    private transient Texture tankTexture;

    public Tank2(Vector2 position) {
        super();
        this.setPosition(position);

    }


    @Override
    public void draw(SpriteBatch batch) {
        if (super.getTankTexture() == null) {
            super.setType(this.getType());
        }
        Vector2 position = this.getPosition();
        batch.draw(super.getTankTexture(), position.x, position.y);
    }



}
