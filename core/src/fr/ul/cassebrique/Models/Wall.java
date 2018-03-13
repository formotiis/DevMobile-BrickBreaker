package fr.ul.cassebrique.Models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.ul.cassebrique.Models.brick.BlueBrick;
import fr.ul.cassebrique.Models.brick.Brick;
import fr.ul.cassebrique.Models.brick.EmptyBrick;
import fr.ul.cassebrique.Models.brick.GreenBrick;

import java.util.ArrayList;

public class Wall {

    private int nbL; //nombre de lignes
    private int nbC; //nombre de colones
    private GameWorld gw;

    private static int colDif =50, ligDif=324,col=100, lig=46;

    private fr.ul.cassebrique.Models.brick.Brick wall[][];

    private static final Brick wallInit[][]= {
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-0))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-0))), new EmptyBrick((colDif+(col*2)), (ligDif+lig*(5-0))),  new GreenBrick((colDif+(col*3)), (ligDif+lig*(5-0)), true), new EmptyBrick((colDif+(col*4)), (ligDif+lig*(5-0))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-0))), new EmptyBrick((colDif+(col*6)), (ligDif+lig*(5-0))), new GreenBrick((colDif+(col*7)), (ligDif+lig*(5-0))),  new GreenBrick((colDif+(col*8)), (ligDif+lig*(5-0))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-0)))},
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-1))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-1))), new GreenBrick((colDif+(col*2)), (ligDif+lig*(5-1)), true),  new GreenBrick((colDif+(col*3)), (ligDif+lig*(5-1)), true), new EmptyBrick((colDif+(col*4)), (ligDif+lig*(5-1))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-1))), new BlueBrick((colDif+(col*6)), (ligDif+lig*(5-1))), new GreenBrick((colDif+(col*7)), (ligDif+lig*(5-1))),  new GreenBrick((colDif+(col*8)), (ligDif+lig*(5-1))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-1)))},
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-2))), new GreenBrick((colDif+(col*2)), (ligDif+lig*(5-2)), true),  new GreenBrick((colDif+(col*3)), (ligDif+lig*(5-2)), true), new BlueBrick((colDif+(col*4)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*6)), (ligDif+lig*(5-2))), new GreenBrick((colDif+(col*7)), (ligDif+lig*(5-2))),  new GreenBrick((colDif+(col*8)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-2)))},
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-3))), new GreenBrick((colDif+(col*2)), (ligDif+lig*(5-3)), true),  new GreenBrick((colDif+(col*3)), (ligDif+lig*(5-3)), true), new EmptyBrick((colDif+(col*4)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*6)), (ligDif+lig*(5-3))), new GreenBrick((colDif+(col*7)), (ligDif+lig*(5-3))),  new GreenBrick((colDif+(col*8)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-3)))},
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-4))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-4))), new GreenBrick((colDif+(col*2)), (ligDif+lig*(5-4)), true),  new GreenBrick((colDif+(col*3)), (ligDif+lig*(5-4)), true), new EmptyBrick((colDif+(col*4)), (ligDif+lig*(5-4))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-4))), new BlueBrick((colDif+(col*6)), (ligDif+lig*(5-4))), new GreenBrick((colDif+(col*7)), (ligDif+lig*(5-4))),  new GreenBrick((colDif+(col*8)), (ligDif+lig*(5-4))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-4)))}
    };

    private void setBricks(Boolean random){
        if (!random){
            wall = wallInit;
            for (int i=0; i< nbL;i++)
                for (int j=0; j<nbC;j++) {
                wall[i][j].addBody(gw);
                }
        } else {

        }

    }

    public Wall(GameWorld gaw){
        this.gw = gaw;
        this.nbL = wallInit.length;
        this.nbC = wallInit[0].length;
        this.wall = new Brick[this.nbL][this.nbC];
        setBricks(false);
    }

    public ArrayList<Brick> checkBricks(){
        ArrayList<Brick> al = new ArrayList<Brick>();
        for (int i=0; i< nbL;i++)
            for (int j=0; j<nbC;j++) {
                if (!(wall[i][j].getClass().equals(EmptyBrick.class))){
                    if (wall[i][j].getHitsLeft()==0) {
                        al.add(wall[i][j]);
                    }
                }
            }
        return al;
    }

    public void updateBricks(ArrayList<Brick> a){
        for (int i =0; i<a.size();i++){
            a.get(i).remBody(gw.getWorld());
        }
    }

    public void draw(SpriteBatch sb){
        for (int i=0; i< nbL;i++)
            for (int j=0; j<nbC;j++) {
                wall[i][j].draw(sb);
            }
    }



}
