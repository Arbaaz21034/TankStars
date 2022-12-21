package com.gdx.tankstars.game;
import com.gdx.tankstars.utils.Noise;

public class TerrainGeneratorAlt {
    // Constants for the terrain generation
    public static final int TERRAIN_WIDTH = 900;
    public static final int TERRAIN_HEIGHT = 150;
    private static final float NOISE_SCALE = 0.01f;
    private static final float CURVE_FACTOR = 0.5f;

    private Noise noiseGenerator;

    public TerrainGeneratorAlt() {
        // Initialize the noise generator
        noiseGenerator = new Noise();
    }

    public double[][] generateTerrain() {
        // Create a 2D array to store the terrain heights
        double[][] terrain = new double[TERRAIN_WIDTH][TERRAIN_HEIGHT];

        // Generate the noise values
        for (int x = 0; x < TERRAIN_WIDTH; x++) {
            for (int y = 0; y < TERRAIN_HEIGHT; y++) {
                double noiseValue = Noise.noise(x * NOISE_SCALE, y * NOISE_SCALE);

                // Apply a curve to the noise values to create valleys
                noiseValue = CURVE_FACTOR * noiseValue * noiseValue + (1 - CURVE_FACTOR) * noiseValue;

                // Scale the noise value and store it in the terrain array
                terrain[x][y] = noiseValue * 100;
            }
        }

        return terrain;
    }
}
