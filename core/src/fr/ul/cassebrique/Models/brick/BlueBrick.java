package fr.ul.cassebrique.Models.brick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public class BlueBrick extends Brick {

    private Texture tex;

    public BlueBrick(int x, int y) {
        super(x, y, 1);
        this.tex = TextureFactory.getTexBlueBrick();
    }

    @Override
    public void draw(SpriteBatch sb) {
        if (getHitsLeft() == 1)
            sb.draw(tex, x(), y());
    }

}