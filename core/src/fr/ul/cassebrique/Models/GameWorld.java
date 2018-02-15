package fr.ul.cassebrique.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.cassebrique.DataFactories.TextureFactory;
import fr.ul.cassebrique.Views.GameScreen;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {

    private GameScreen gs;
    private GameState gameState;
    private Wall wall;
    private Background bg;
    private Racket racket;
    private ArrayList<Ball> balls;
    private final static int timeIter = 1000/60;
    private World world;
    private final static float METERS_TO_PIXELS = 250f;
    private final static float PIXELS_TO_METERS = (1/METERS_TO_PIXELS);

    public GameWorld(GameScreen g){
        this.gs = g;
        this.world = new World(new Vector2(0f,0f), false);
        this.bg = new Background(this);
        this.racket = new Racket(this);
        this.gameState = new GameState(this);
        this.wall = new Wall();

        this.balls = new ArrayList<Ball>();
        this.createBalls();
    }

    public void draw(SpriteBatch s){
        world.step(Gdx.graphics.getDeltaTime(),6,2);
        bg.draw(s);
        racket.draw(s);
        wall.draw(s);
        for (Ball b:this.balls ) {
            b.draw(s);
        }
    }

    private void createBalls(){
        this.balls.add(new Ball(this, (TextureFactory.getTexBack().getWidth()-2*50)/2-12,120));
        for (int i=0; i<gameState.getNbBalls()-1;i++){
            this.balls.add(new Ball (this,
                    Gdx.graphics.getBackBufferWidth()-50+(TextureFactory.getTexBall().getWidth())/2,
                    i*(TextureFactory.getTexBall().getHeight()+10)+10));
        }
        Random r = new Random();
        this.balls.get(0).setSpeed(-200, 200);
    }

    public Racket getRacket() {
        return racket;
    }

    public static long getTimeIter(long time) {
        if (time>timeIter)
            return 0;
        return timeIter-time;
    }

    public static float getMetersToPixels() {
        return METERS_TO_PIXELS;
    }

    public static float getPixelsToMeters() {
        return PIXELS_TO_METERS;
    }

    public World getWorld() {
        return world;
    }
}
