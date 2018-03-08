package fr.ul.cassebrique.Models.brick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public class GreenBrick extends Brick{

    private Texture tex;
    private Texture hitTex;
    private boolean hit;

    public GreenBrick(int x, int y) {
        super(x, y, 2);
        this.tex = TextureFactory.getTexGreenBrickA();
        this.hitTex = TextureFactory.getTexGreenBrickB();
    }

    public GreenBrick(int x, int y, boolean b) {
        super(x, y, 2);
        this.hit = b;
        if (b){
            this.hit();
        }
        this.tex = TextureFactory.getTexGreenBrickA();
        this.hitTex = TextureFactory.getTexGreenBrickB();
    }

    @Override
    public void draw(SpriteBatch sb) {
        if(getHitsLeft()==1)
            sb.draw(hitTex, x(), y());
        else if (getHitsLeft()==2)
            sb.draw(tex, x(),y());
    }

}
