package fr.ul.cassebrique.Models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public class GameState {

    private State state;
    private int nbBalls;
    private Texture tex;
    private GameWorld gw;

    public GameState(GameWorld gw) {
        this.gw = gw;
        this.state = State.Running;
        this.nbBalls = 3;
        this.tex = TextureFactory.getTexBall();
    }

    public State getState() {
        return state;
    }

    public int getNbBalls() {
        return nbBalls;
    }

    public boolean isRunning(){
        return state == State.Running;
    }

    public void ballLoss(){
        nbBalls--;
    }

    public void ballWon(){
        nbBalls++;
    }

    public void run(){
        this.state = State.Running;
    }

    public void reset(){
        this.state = State.Running;
        this.nbBalls = 3;
    }

    public void pause(){
        this.state = State.Pause;
    }

    public boolean isPaused(){
        return this.state==State.Pause;
    }

    public void resume(){
        this.state = State.Running;
    }

    public void updateState(State s){
        this.state =s;
    }

}

