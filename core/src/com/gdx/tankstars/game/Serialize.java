package com.gdx.tankstars.game;

import java.io.*;

public class Serialize {
    SaveData saveData;

    public Serialize(SaveData saveData) {
        this.saveData = saveData;
    }


    public void save() throws IOException {
        FileOutputStream fs = new FileOutputStream("save");
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(this.saveData);
        os.close();
        fs.close();
    }
}
