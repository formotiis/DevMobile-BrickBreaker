package fr.ul.cassebrique.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.ul.cassebrique.DataFactories.TextureFactory;
import fr.ul.cassebrique.Models.GameWorld;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends ScreenAdapter {

    private SpriteBatch sb;
    private GameWorld gw;

    public GameScreen(){
        sb = new SpriteBatch();
        gw = new GameWorld(this);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        long time = System.nanoTime();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();
        sb.begin();
        gw.draw(sb);
        sb.end();
        time = System.nanoTime() -time;
        time = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
        //System.out.println("time:"+time);
        try {
            if (!(time<0)) {
                Thread.sleep(GameWorld.getTimeIter(time));
            }
            //System.out.println("time:"+time);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void dispose () {
        sb.dispose();
    }

    public void update(){
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
        }

    }
}
