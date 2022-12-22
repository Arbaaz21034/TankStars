package com.gdx.tankstars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.tankstars.game.GameMatch;
import com.gdx.tankstars.game.State;
import com.gdx.tankstars.game.TerrainGenerator;
import com.gdx.tankstars.game.bullets.GameMatchScreenD;
import com.gdx.tankstars.screen.GameMatchScreen;
import com.gdx.tankstars.screen.StartScreen;

import java.io.Serializable;

public class TankStarsGame extends Game implements Serializable {

  private SpriteBatch batch;

  public TankStarsGame() {
  }

  public SpriteBatch getBatch() {
    return batch;
  }

  /* Game Data */

  private int player1;
  private int player2;
  private GameMatch gameMatchData;
  private State stateData;

  public GameMatch getGameMatchData() {
    return gameMatchData;
  }

  public void setGameMatchData(GameMatch gameMatchData) {
    this.gameMatchData = gameMatchData;
  }

  @Override
  public void create() {
    batch = new SpriteBatch();

    //this.setScreen(new TerrainGenerator_1());
    this.setScreen(new StartScreen(this));
    //this.setScreen(new TempScreen());
//    this.setScreen(new GameMatchScreen((this)));
    //this.setScreen(new GameMatchScreenD((this)));

  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  public int getPlayer1() {
    return player1;
  }

  public void setPlayer1(int player1) {
    this.player1 = player1;
  }

  public int getPlayer2() {
    return player2;
  }

  public void setPlayer2(int player2) {
    this.player2 = player2;
  }


  public State getStateData() {
    return stateData;
  }

  public void setStateData(State stateData) {
    this.stateData = stateData;
  }
}
