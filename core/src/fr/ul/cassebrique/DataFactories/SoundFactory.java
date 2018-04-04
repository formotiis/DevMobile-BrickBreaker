package fr.ul.cassebrique.DataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFactory {

    private static final Sound collide = Gdx.audio.newSound(Gdx.files.internal("sounds/collision.wav"));
    private static final Sound impact = Gdx.audio.newSound(Gdx.files.internal("sounds/impact.mp3"));
    private static final Sound loss = Gdx.audio.newSound(Gdx.files.internal("sounds/perte.mp3"));
    private static final Sound ballloss = Gdx.audio.newSound(Gdx.files.internal("sounds/perteBalle.wav"));
    private static final Sound victory = Gdx.audio.newSound(Gdx.files.internal("sounds/victoire.mp3"));

     public static void playCollide(float v){
        collide.play(v);
     }
    public static void playImpact(float v){
        impact.play(v);
    }
    public static void playLoss(float v){
        loss.play(v);
    }
    public static void playBallLoss(float v){
        ballloss.play(v);
    }
    public static void playVictory(float v){
        victory.play(v);
    }
}
