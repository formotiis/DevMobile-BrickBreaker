package fr.ul.cassebrique.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.cassebrique.Controls.CollisionController;
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
    private Box2DDebugRenderer debug;
    private OrthographicCamera cam;

    public GameWorld(GameScreen g){
        this.gs = g;
        cam = new OrthographicCamera(1150*PIXELS_TO_METERS, 700*PIXELS_TO_METERS);
        cam.position.set(1150*PIXELS_TO_METERS/2, 700*PIXELS_TO_METERS/2, 0);
        cam.update();
        debug = new Box2DDebugRenderer();
        this.world = new World(new Vector2(0f,0f), false);
        this.bg = new Background(this);
        this.racket = new Racket(this);
        this.gameState = new GameState(this);

        this.wall = new Wall(this);
        this.balls = new ArrayList<Ball>();
        this.createBalls();
        world.setContactListener(new CollisionController());
        world.setVelocityThreshold(0.1f);
    }

    public void draw(SpriteBatch s){
        //debug.render(world, cam.combined);
        world.step(Gdx.graphics.getDeltaTime(),6,2);
        bg.draw(s);
        racket.draw(s);
        wall.updateBricks(wall.checkBricks());
        wall.draw(s);
        for (Ball b:this.balls ) {
            b.draw(s);
        }
    }

    private void createBalls(){
        this.balls.add(new Ball(this, (TextureFactory.getTexBack().getWidth()-2*50)/2-12,120));
        for (int i=0; i<gameState.getNbBalls()-1;i++){
            this.balls.add(new Ball (this,
                    Gdx.graphics.getBackBufferWidth()-50+(TextureFactory.getTexBall().getWidth())/2+12,
                    i*(TextureFactory.getTexBall().getHeight()+10)+22));
        }
        Random r = new Random();
        this.balls.get(0).setSpeed(-200+r.nextFloat()* 400, 200);
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