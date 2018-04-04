package fr.ul.cassebrique.Models.brick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public class GreenBrick extends Brick{

    private Texture tex;
    private Texture hitTex;
    private Animation anim;
    private boolean hit;
    private float tempsAnim;

    public GreenBrick(int x, int y) {
        super(x, y, 2);
        this.tex = TextureFactory.getTexGreenBrickA();
        this.hitTex = TextureFactory.getTexGreenBrickB();
        TextureAtlas ta = new TextureAtlas("images/Anim2Ca.pack");
        Array<TextureAtlas.AtlasRegion> listIms = ta.findRegions("Anim2Ca");
        tempsAnim = 0;
        this.anim = new Animation<TextureAtlas.AtlasRegion>(2/20f, listIms, Animation.PlayMode.LOOP);
    }

    public GreenBrick(int x, int y, boolean b) {
        super(x, y, 2);
        this.hit = b;
        if (b){
            this.hit();
        }
        tempsAnim = 0;
        this.tex = TextureFactory.getTexGreenBrickA();
        this.hitTex = TextureFactory.getTexGreenBrickB();
    }

    @Override
    public void draw(SpriteBatch sb) {
        if(getHitsLeft()==1)
            sb.draw(hitTex, x(), y());
        else if (getHitsLeft()==2) {
            tempsAnim += Gdx.graphics.getDeltaTime();
            TextureRegion im = (TextureRegion) anim.getKeyFrame(tempsAnim);
            sb.draw(im, x(), y());
        }
    }

    @Override
    protected int maxHits() {
        if (hit){
            return 1;
        } else {
            return 2;
        }
    }

}
