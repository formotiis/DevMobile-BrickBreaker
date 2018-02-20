package fr.ul.cassebrique.Models.brick;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import fr.ul.cassebrique.DataFactories.TextureFactory;
import fr.ul.cassebrique.Models.GameWorld;

public abstract class Brick {

    private int x;
    private int y;
    boolean bodied;
    private int hitsLeft;
    protected Body body;
    private float mToP;

    protected Brick(int x, int y, int maxHits) {
        this.x = x;
        this.y = y ;
        this.hitsLeft = maxHits;
        this.bodied = false;
    }

    protected void hit() {
        if (hitsLeft > 0)
            hitsLeft = hitsLeft - 1;
    }

    protected int getHitsLeft() {
        return hitsLeft;
    }

    protected int x() {
        if (bodied)
            return (int) (this.body.getPosition().x * mToP);
        return x;
    }

    protected int y() {
        if (bodied)
            return (int) (this.body.getPosition().y * mToP);
        return y;
    }

    public abstract void draw(SpriteBatch sb);

    public void addBody(GameWorld gw){
        this.mToP = gw.getMetersToPixels();
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        int lig = 46, col=50;
        FixtureDef fd = new FixtureDef();
        ChainShape cs = new ChainShape();
        fd.density = 1f;
        fd.restitution = 1f;
        fd.friction = 0f;
        float[] tab = {
                x*gw.getPixelsToMeters(), y*gw.getPixelsToMeters(),
                (x+lig)*gw.getPixelsToMeters(), y*gw.getPixelsToMeters(),
                (x+lig)*gw.getPixelsToMeters(), (y+col)*gw.getPixelsToMeters(),
                x*gw.getPixelsToMeters(), (y+col)*gw.getPixelsToMeters(),
                x*gw.getPixelsToMeters(), y*gw.getPixelsToMeters()
        };
        cs.createChain(tab);
        fd.shape = cs;
        body = gw.getWorld().createBody(bd);
        body.createFixture(fd);
        body.setTransform(x*gw.getPixelsToMeters(), y*gw.getPixelsToMeters(),0f);
        bodied = true;
    }
}