package fr.ul.cassebrique.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.cassebrique.DataFactories.TextureFactory;

public class Racket {

    private GameWorld gw;
    private int[] pos;

    private int oHeight;
    private int oWidth;
    private Texture tex;
    private int minWidth;
    private int maxWidth;


    public Racket(GameWorld gw) {
        this.gw = gw;
        this.pos = new int[2];
        this.pos[1] = 100;
        this.minWidth = 50;//tobeupdated
        this.maxWidth = TextureFactory.getTexBack().getWidth()-2*50;
        // this.oHeight = 20 ;
        this.oWidth = 125;
        this.pos[0] = maxWidth/2-oWidth/2;
        this.tex = TextureFactory.getTexRacket();

    }

    public void draw(SpriteBatch sb){
        sb.draw(tex, pos[0], pos[1]);
    }

    public void move(int y){
        this.pos[0] = pos[0]+y;
    }

    public boolean isMovePossible(int y){
        return ((pos[0]+y<maxWidth-oWidth)&&(pos[0]+y>minWidth));
    }

    public void moveRelative(int y){
        if (((pos[0]+oWidth/2)-5<y)&&(y<(pos[0]+oWidth/2)+5)){

        } else if (y>pos[0]+oWidth/2){
            if (isMovePossible(10))
                move(10);
        }else {
            if (isMovePossible(-10))
                move(-10);
        }

    }
}
