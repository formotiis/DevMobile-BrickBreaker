package fr.ul.cassebrique.Controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.DataFactories.SoundFactory;
import fr.ul.cassebrique.Models.Background;
import fr.ul.cassebrique.Models.Ball;
import fr.ul.cassebrique.Models.Racket;
import fr.ul.cassebrique.Models.brick.Brick;

public class CollisionController implements ContactListener{

    public CollisionController() {
    }

    @Override
    public void beginContact(Contact contact) {
        Vector2 normal= contact.getWorldManifold().getNormal();
        Body A = contact.getFixtureA().getBody();
        Body B = contact.getFixtureB().getBody();

        if (A.getUserData().getClass().equals(Ball.class)) {
            Ball ball = (Ball) A.getUserData();

            if (B.getUserData().getClass().equals(Background.class)) {
                ball.setSpeedFloat(normalisep(ball.getSpeedX(), normal.x),
                        normalise(ball.getSpeedY(), normal.y));
                SoundFactory.playCollide(1);
            }

            if (B.getUserData().getClass().equals(Racket.class)) {
                Racket r = (Racket)B.getUserData();
                if (!r.offMiddleCollision(B)) {
                    ball.setSpeedFloat(normalise(ball.getSpeedX(), normal.x),
                            normalise(ball.getSpeedY(), normal.y));
                }else {
                    float y = contact.getWorldManifold().getPoints()[0].y;
                    ball.setSpeedFloat((normalise(ball.getSpeedX(), normal.x)*r.ratio(y)),
                            (normalise(ball.getSpeedY(), normal.y)*r.ratio(y)));
                }
                SoundFactory.playImpact(1);
            }

            if (Brick.class.isAssignableFrom(B.getUserData().getClass())) {
                Brick b = (Brick) B.getUserData();
                b.wasHit();
                ball.setSpeedFloat(normalisep(ball.getSpeedX(), normal.x),
                        normalise(ball.getSpeedY(), normal.y));
                SoundFactory.playCollide(1);
            }
        }

        if (B.getUserData().getClass().equals(Ball.class)) {

            Ball ball = (Ball) B.getUserData();

            if (A.getUserData().getClass().equals(Background.class)) {
                ball.setSpeedFloat(normalisep(ball.getSpeedX(), normal.x),
                        normalise(ball.getSpeedY(), normal.y));
                SoundFactory.playCollide(1);
            }

            if (A.getUserData().getClass().equals(Racket.class)) {
                Racket r = (Racket)A.getUserData();
                if (!r.offMiddleCollision(A)) {
                    ball.setSpeedFloat(normalise(ball.getSpeedX(), normal.x),
                            normalise(ball.getSpeedY(), normal.y));
                }else {
                    float y = contact.getWorldManifold().getPoints()[0].y;
                    ball.setSpeedFloat((normalise(ball.getSpeedX(), normal.x)*r.ratio(y)),
                            (normalise(ball.getSpeedY(), normal.y)*r.ratio(y)));
                }
                SoundFactory.playImpact(1);
            }
            if (Brick.class.isAssignableFrom(A.getUserData().getClass())) {
                Brick b = (Brick) A.getUserData();
                b.wasHit();
                ball.setSpeedFloat(normalisep(ball.getSpeedX(), normal.x),
                        normalise(ball.getSpeedY(), normal.y));
                SoundFactory.playCollide(1);
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private float normalise(float x, float normal){
        float r = x;
        if (normal == -1f  || normal == 1f){
            r = -r;
        }
        return r;
    }

    private float normalisep(float x, float normal){
        float r = x;
        if (normal == -1f  || normal == 1f){
            r = -r-(0.01f*r);
        }
        return r;
    }
}
