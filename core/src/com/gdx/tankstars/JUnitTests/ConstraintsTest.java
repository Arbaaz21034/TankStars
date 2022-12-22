package com.gdx.tankstars.JUnitTests;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.tankstars.game.Tank;
import com.gdx.tankstars.game.Tank1;
import org.junit.Test;
import static org.junit.Assert.*;
public class ConstraintsTest {

    @Test
    public void testConstraintsPositive() {

        Tank t = new Tank1(2000, 1000);

        float p_x = t.getPosition().x;
        float p_y = t.getPosition().y;
        if (p_x > 1280) {
            fail("Constraints failed for tank x position in positive");
        }
        if (p_y > 720) {
            fail("Constraints failed for tank y position in positive");
        }
    }

    @Test
    public void testConstraintsNegative() {
        Tank t = new Tank1(-100, -2000);
        float p_x = t.getPosition().x;
        float p_y = t.getPosition().y;
        if (p_x < 0) {
            fail("Constraints failed for tank x position in negative");
        }
        if (p_y < 0) {
            fail("Constraints failed for tank y position in negative");
        }
    }

}

