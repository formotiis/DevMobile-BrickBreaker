package fr.ul.cassebrique.Models.brick;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.DataFactories.TextureFactory;
import fr.ul.cassebrique.Models.GameWorld;

import static fr.ul.cassebrique.DataFactories.TextureFactory.getTexBlueBrick;

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
            hitsLeft -= 1;
    }

    public int getHitsLeft() {
        return hitsLeft;
    }

    protected int x() {
        if (bodied) {
            return (int) (this.body.getPosition().x * mToP);
        }
        return x;
    }

    protected int y() {
        if (bodied) {
            return (int) (this.body.getPosition().y * mToP);
        }
        return y;
    }

    public abstract void draw(SpriteBatch sb);

    public void addBody(GameWorld gw) {
        this.mToP = gw.getMetersToPixels();
        BodyDef bd = new BodyDef();
        bd.position.set(x * gw.getPixelsToMeters(), y * gw.getPixelsToMeters());
        bd.type = BodyDef.BodyType.StaticBody;
        int lig = TextureFactory.getTexBlueBrick().getWidth(), col = TextureFactory.getTexBlueBrick().getHeight();
        FixtureDef fd = new FixtureDef();
        fd.density = 1f;
        fd.restitution = 1f;
        fd.friction = 0f;
        ChainShape cs = new ChainShape();
        float pToM = gw.getPixelsToMeters();
        /*float[] tab = {
                x*gw.getPixelsToMeters(), y*gw.getPixelsToMeters(),
                (x+lig)*gw.getPixelsToMeters(), y*gw.getPixelsToMeters(),
                (x+lig)*gw.getPixelsToMeters(), (y+col)*gw.getPixelsToMeters(),
                x*gw.getPixelsToMeters(), (y+col)*gw.getPixelsToMeters(),
                x*gw.getPixelsToMeters(), y*gw.getPixelsToMeters()
        };*/
        float[] tab = {0, 0,
                lig*pToM, 0,
                lig*pToM, col*pToM,
                0, col*pToM,
                0, 0
        };

        cs.createChain(tab);
        fd.shape = cs;
        body = gw.getWorld().createBody(bd);
        body.createFixture(fd);
        body.setUserData(this);
        //body.setTransform(x*gw.getPixelsToMeters(), y*gw.getPixelsToMeters(),0f);
        bodied = true;
    }

    public void remBody(World wd){
        if (bodied == true) {
            wd.destroyBody(body);
            body.setUserData(null);
            body = null;
            bodied = false;
        }
    }

    public void wasHit(){
        this.hit();
    }
}