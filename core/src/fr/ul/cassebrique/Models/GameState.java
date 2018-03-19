package fr.ul.cassebrique.Models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.cassebrique.DataFactories.TextureFactory;

enum State{
    Running,
    BallLoss,
    GameOver,
    Won,
    Pause,
    Quit
}

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

    public void ballLoss(){
        this.state = State.BallLoss;
        nbBalls--;
    }

}

