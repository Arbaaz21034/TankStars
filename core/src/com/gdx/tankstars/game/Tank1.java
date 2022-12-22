package com.gdx.tankstars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tank1 extends Tank {
    Texture tankTexture;



    public Tank1(Vector2 position) {
        super();
        this.setPosition(position);

    }

    public Tank1(float x, float y) {
        super();
        this.setPositionInitially(new Vector2(x, y));

    }

    @Override
    public void draw(SpriteBatch batch) {
        Vector2 position = this.getPosition();
        batch.draw(super.getTankTexture(), position.x, position.y);
    }






}
