package fr.ul.cassebrique.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public class Racket {

    private GameWorld gw;
    private int[] pos;

    private int oHeight;
    private int oWidth;
    private Texture tex;
    private int minWidth;
    private int maxWidth;
    private Body body;
    private Body lBall;
    private Body rBall;


    public Racket(GameWorld gw) {
        this.gw = gw;
        this.pos = new int[2];
        this.pos[1] = 100;
        this.minWidth = 50;//tobeupdated
        this.maxWidth = TextureFactory.getTexBack().getWidth()-2*50;
        this.oWidth = 125;
        this.pos[0] = maxWidth/2-oWidth/2;
        this.tex = TextureFactory.getTexRacket();
        int dimb = 20;
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        FixtureDef fd = new FixtureDef();
        fd.density = 1f;
        fd.restitution = 1f;
        fd.friction = 0f;

        fd.shape = new CircleShape();
        fd.shape.setRadius(10*gw.getPixelsToMeters());
        lBall = gw.getWorld().createBody(bd);
        lBall.createFixture(fd);
        lBall.setTransform(pos[0]*gw.getPixelsToMeters(), pos[1]*gw.getPixelsToMeters(), 0f);

        ChainShape cs = new ChainShape();
        float[] tab = {
                (pos[0]+dimb)*gw.getPixelsToMeters(), (pos[1]+dimb)*gw.getPixelsToMeters(),
                (pos[0]+dimb+tex.getHeight())*gw.getPixelsToMeters(), (pos[1]+dimb)*gw.getPixelsToMeters(),
                (pos[0]+dimb+tex.getHeight())*gw.getPixelsToMeters(), (pos[1]+tex.getWidth()-2*dimb)*gw.getPixelsToMeters(),
                (pos[0]+dimb)*gw.getPixelsToMeters(), (pos[1]+tex.getWidth()-2*dimb)*gw.getPixelsToMeters()
        };
        cs.createChain(tab);
        fd.shape = cs;
        body = gw.getWorld().createBody(bd);
        body.createFixture(fd);
        body.setTransform(pos[0]*gw.getPixelsToMeters(), pos[1]*gw.getPixelsToMeters(),0f);

        fd.shape = new CircleShape();
        fd.shape.setRadius(10*gw.getPixelsToMeters());
        rBall = gw.getWorld().createBody(bd);
        rBall.createFixture(fd);
        rBall.setTransform((pos[0]+tex.getWidth()-2*dimb)*gw.getPixelsToMeters(), (pos[1]+tex.getWidth()-2*dimb)*gw.getPixelsToMeters(), 0f);

    }

    public void draw(SpriteBatch sb){
        sb.draw(tex,
                lBall.getPosition().x*gw.getMetersToPixels(),
                lBall.getPosition().y*gw.getMetersToPixels());
    }

    public void move(int y){
        lBall.setTransform(lBall.getPosition().x+(y*gw.getPixelsToMeters()), lBall.getPosition().y, 0f);
        body.setTransform(body.getPosition().x+(y*gw.getPixelsToMeters()), body.getPosition().y, 0f);
        rBall.setTransform(rBall.getPosition().x+(y*gw.getPixelsToMeters()), rBall.getPosition().y, 0f);
    }

    public boolean isMovePossible(int y){
        return (((rBall.getPosition().x)*gw.getMetersToPixels()+y<maxWidth+oWidth) &&
                ((rBall.getPosition().x)*gw.getMetersToPixels()+y>minWidth));
    }

    public void moveRelative(int y){
        if ((((rBall.getPosition().x)*gw.getMetersToPixels()+oWidth/2)-5<y)&&(y<((rBall.getPosition().x)*gw.getMetersToPixels()+oWidth/2)+5)){

        } else if (y>(rBall.getPosition().x)*gw.getMetersToPixels()+oWidth/2){
            if (isMovePossible(10))
                move(10);
        }else {
            if (isMovePossible(-10))
                move(-10);
        }

    }
}
