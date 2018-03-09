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
    private float ptm;
    private float mtp;


    public Racket(GameWorld gw) {
        this.gw = gw;
        this.ptm =gw.getPixelsToMeters();
        this.mtp = gw.getMetersToPixels();
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
        fd.shape.setRadius(10*ptm);
        lBall = gw.getWorld().createBody(bd);
        lBall.createFixture(fd);
        lBall.setTransform((pos[0]- dimb/2)*ptm, (pos[1]+dimb/2)*ptm, 0f);
        lBall.setUserData(this);
        ChainShape cs = new ChainShape();
        float[] tab = {

                0, 0,

                0, (tex.getHeight())*ptm,

                (tex.getWidth()-2*dimb)*ptm, tex.getHeight()*ptm,

                (tex.getWidth()-2*dimb)*ptm, 0,

                0,0

        };
        cs.createChain(tab);

        fd.shape = cs;
        body = gw.getWorld().createBody(bd);
        body.setTransform(pos[0]*ptm, pos[1]*ptm,0f);
        body.createFixture(fd);
        body.setUserData(this);
        //body.setTransform(pos[0]*ptm, pos[1]*ptm,0f);

        fd.shape = new CircleShape();
        fd.shape.setRadius(10*ptm);
        rBall = gw.getWorld().createBody(bd);
        rBall.createFixture(fd);
        rBall.setTransform(((pos[0]+tex.getWidth()-(2*dimb))+dimb/2)*ptm, (pos[1]+dimb/2)*ptm, 0f);
        rBall.setUserData(this);
    }

    public void draw(SpriteBatch sb){
        sb.draw(tex,
                lBall.getPosition().x*mtp-8,
                lBall.getPosition().y*mtp-12);
    }

    public void move(int y){
        lBall.setTransform(lBall.getPosition().x+(y*ptm), lBall.getPosition().y, 0f);
        body.setTransform(body.getPosition().x+(y*ptm), body.getPosition().y, 0f);
        rBall.setTransform(rBall.getPosition().x+(y*ptm), rBall.getPosition().y, 0f);
    }

    public boolean isMovePossible(int y){
        //System.out.println("Rball position "+ (rBall.getPosition().x*mtp) + " < "+ (maxWidth));
        //System.out.println("Lball position "+ (lBall.getPosition().x*mtp) + " > "+ (minWidth));
        return (((rBall.getPosition().x)*mtp+10/2 +y < maxWidth) &&
                ((lBall.getPosition().x)*mtp-10/2+y>minWidth));
    }

    public void moveRelative(int y){
        if ((((rBall.getPosition().x)*mtp-oWidth/2)-5<y)&&(y<((rBall.getPosition().x)*mtp-oWidth/2)+5)){

        } else if (y>(rBall.getPosition().x)*mtp - oWidth/2){
            if (isMovePossible(10))
                move(10);
        }else {
            if (isMovePossible(-10))
                move(-10);
        }

    }
}
