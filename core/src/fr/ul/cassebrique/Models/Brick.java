package fr.ul.cassebrique.Models;

import com.badlogic.gdx.graphics.Texture;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public enum Brick {

    Vide  (null),
    Bleue (TextureFactory.getTexBlueBrick()),
    Verte (TextureFactory.getTexGreenBrickA()),
    VerteAbimee (TextureFactory.getTexGreenBrickB());

    private Texture tex = null;

    Brick(Texture t){
        tex = t;
    }

    public Texture getTex() {
        return tex;
    }
}