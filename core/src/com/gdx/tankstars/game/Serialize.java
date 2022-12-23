package com.gdx.tankstars.game;

import com.gdx.tankstars.TankStarsGame;

import java.io.*;

public class Serialize {
    SaveData saveData;

    public Serialize(SaveData saveData) {
        this.saveData = saveData;
    }

    public void setSaveData(SaveData saveData) {
        this.saveData = saveData;
    }

    public void save() throws IOException {
        FileOutputStream fs = new FileOutputStream("save");
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(this.saveData);
        System.out.println("Game saved successfully");
        os.close();
        fs.close();
    };

    public static GameMatch load() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream("save");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();
            objectInputStream.close();
            SaveData mySaveData = (SaveData) obj;
            System.out.println("Game loaded successfully");
            GameMatch gameMatch = mySaveData.getGameMatch();
            return gameMatch;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
