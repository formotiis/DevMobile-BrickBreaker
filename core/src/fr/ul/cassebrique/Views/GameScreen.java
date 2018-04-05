package fr.ul.cassebrique.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.ul.cassebrique.Controls.Listener;
import fr.ul.cassebrique.DataFactories.SoundFactory;
import fr.ul.cassebrique.DataFactories.TextureFactory;
import fr.ul.cassebrique.Models.GameWorld;
import fr.ul.cassebrique.Models.State;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends ScreenAdapter {

    private SpriteBatch sb;
    private GameWorld gw;
    private static int lx = TextureFactory.getTexBallLoss().getHeight()/2+50;
    private static int ly = TextureFactory.getTexBallLoss().getWidth()/2-100;

    private static int wx = TextureFactory.getTexWin().getHeight()/2+100;
    private static int wy = TextureFactory.getTexWin().getWidth()/2-100;

    private static int gx = TextureFactory.getTexGameOver().getHeight()/2+100;
    private static int gy = TextureFactory.getTexGameOver().getWidth()/2-100;

    private int worldWidth = TextureFactory.getTexBack().getWidth();
    private int worldHeight = TextureFactory.getTexBack().getHeight();

    private OrthographicCamera camera;


    private Timer.Task tt;
    private boolean scheduled;

    public GameScreen(){
        camera = new OrthographicCamera(worldWidth,worldHeight);

        //FitViewport vp = new FitViewport(worldWidth,worldHeight,camera);
        //vp.apply();
        camera.position.set(worldWidth/2f, worldHeight /2f,0);
        camera.update();
        Gdx.input.setInputProcessor(new Listener(this));
        sb = new SpriteBatch();
        gw = new GameWorld(this);
        tt = new Timer.Task() {
            @Override
            public void run() {
                gw.restart(gw.whatState());
            }
        };
        scheduled = false;

    }

    public void scheduleDone(){
        scheduled = false;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        sb.setProjectionMatrix(camera.combined);
        long time = System.nanoTime();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();
        sb.begin();
        gw.draw(sb);
        if (gw.whatState().equals(State.BallLoss)){
            sb.draw(TextureFactory.getTexBallLoss(),lx,ly);
            if (!scheduled) {
                SoundFactory.playBallLoss(1);
                Timer.schedule(tt, 3);
                scheduled = true;
            }

        } else if(gw.whatState().equals(State.Won)){
            sb.draw(TextureFactory.getTexWin(),wx,wy);
            if (!scheduled) {
                SoundFactory.playVictory(1);
                Timer.schedule(tt, 3);
                scheduled = true;
            }

        } else if(gw.whatState().equals(State.GameOver)){
            sb.draw(TextureFactory.getTexGameOver(),gx,gy);

            if (!scheduled) {
                SoundFactory.playLoss(1);
                Timer.schedule(tt, 3);
                scheduled = true;
            }

        }
        sb.end();
        time = System.nanoTime() -time;
        time = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
        try {
            if (!(time<0)) {
                Thread.sleep(GameWorld.getTimeIter(time));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void dispose () {
        sb.dispose();
    }

    public void update(){
        if (gw.isRunning()){
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                if (gw.getRacket().isMovePossible(-10))
                    gw.getRacket().move(-10);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                if(gw.getRacket().isMovePossible(10))
                    gw.getRacket().move(10);
            }

            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                gw.getRacket().moveRelative(Gdx.input.getX());
            }
            if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
                gw.getRacket().moveRelative(Gdx.input.getX());
            }
            if (Gdx.input.isTouched()){
                gw.getRacket().moveRelative(Gdx.input.getX());
            } //else {
                //gw.getRacket().moveRelative((int)Gdx.input.getAccelerometerY());
            //}
        }

        if (gw.whatState() == State.BallLoss){
            gw.updateState(State.BallLoss);
        } else if (gw.whatState() == State.Won){
            gw.updateState(State.Won);
        }else if (gw.whatState() == State.GameOver) {
            gw.updateState(State.GameOver);
        } else if (gw.whatState() == State.Quit) {
            Gdx.app.exit();
            //System.exit(0);
        }
    }

    public void pause(){
        gw.pauseOrResume();
    }

    public void quit(){
        gw.updateState(State.Quit);
    }

}
