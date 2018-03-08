package fr.ul.cassebrique.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public class Background {

    private Texture bg;
    private static int bSize = 50;
    private Body body;
    private GameWorld gw;

    public Background(GameWorld gaw) {
        this.gw = gaw;
        bg = TextureFactory.getTexBack();
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        FixtureDef fd = new FixtureDef();
        fd.density = 1f;
        fd.restitution = 1f;
        fd.friction = 0f;
        ChainShape cs = new ChainShape();
        float[] tab = { bSize*gw.getPixelsToMeters(), 0,
                bSize*gw.getPixelsToMeters(), (bg.getHeight()-bSize)*gw.getPixelsToMeters(),
                (bg.getWidth()-(bSize*2))*gw.getPixelsToMeters(), (bg.getHeight()-bSize)*gw.getPixelsToMeters(),
                (bg.getWidth()-(bSize*2))*gw.getPixelsToMeters(), 0};
        cs.createChain(tab);
        fd.shape = cs;
        body = gw.getWorld().createBody(bd);
        body.createFixture(fd);
    }

    public void draw(SpriteBatch sb){
        sb.draw(bg ,0,0);
    }
}
