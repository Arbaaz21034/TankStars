package com.gdx.tankstars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Camera extends com.badlogic.gdx.graphics.Camera {
    private float screenWidth = Gdx.graphics.getWidth();
    private float screenHeight = Gdx.graphics.getHeight();
    OrthographicCamera camera = new OrthographicCamera(screenWidth, screenHeight);

    public Camera() {
        camera.position.set(screenWidth / 2, screenHeight / 2, 0);
        camera.update();
        //camera.zoom = 0.25f;
        //camera.update();


        SpriteBatch batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
    }

    @Override
    public void update() {

    }

    @Override
    public void update(boolean updateFrustum) {

    }

}
