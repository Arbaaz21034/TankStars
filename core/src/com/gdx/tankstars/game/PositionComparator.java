package com.gdx.tankstars.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class PositionComparator implements Comparator<Vector2> {
    private static PositionComparator comp = null;
    public static PositionComparator getInstance()
    {
        if (comp == null) {
            comp = new PositionComparator();
        }
        return comp;
    }
    private PositionComparator() {}

    @Override
    public int compare(Vector2 v1, Vector2 v2) {
        return (int) (v1.x - v2.x);
    }


}