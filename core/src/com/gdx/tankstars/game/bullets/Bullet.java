package com.gdx.tankstars.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bullet {
    private float speed; // placeholder value for now
    private float attack;
    private float angle;
    private String name;
    private Texture texture;

    public Bullet(String name) {
        this.name = name;
    }

    public void setBullet(float speed, float attack, float angle) {
        this.angle = angle;
        this.attack = attack;
        this.speed = speed;
    }

    public void setTexture(String texturePath) {
        this.texture = new Texture(Gdx.files.internal(texturePath));
    }





}
