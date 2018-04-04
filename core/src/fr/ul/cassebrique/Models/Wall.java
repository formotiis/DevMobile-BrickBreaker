package fr.ul.cassebrique.Models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.ul.cassebrique.Models.brick.BlueBrick;
import fr.ul.cassebrique.Models.brick.Brick;
import fr.ul.cassebrique.Models.brick.EmptyBrick;
import fr.ul.cassebrique.Models.brick.GreenBrick;

import java.util.ArrayList;
import java.util.Random;

public class Wall {

    private int nbL; //nombre de lignes
    private int nbC; //nombre de colones
    private GameWorld gw;
    private Random r;

    private static int colDif =50, ligDif=324,col=100, lig=46;
    private int amBricks;

    private fr.ul.cassebrique.Models.brick.Brick wall[][];

    private static final Brick wallInit[][]= {
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-0))), new GreenBrick((colDif+(col*1)), (ligDif+lig*(5-0))), new BlueBrick((colDif+(col*2)), (ligDif+lig*(5-0))),  new GreenBrick((colDif+(col*3)), (ligDif+lig*(5-0)),true), new BlueBrick((colDif+(col*4)), (ligDif+lig*(5-0))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-0))), new GreenBrick((colDif+(col*6)), (ligDif+lig*(5-0)),true), new BlueBrick((colDif+(col*7)), (ligDif+lig*(5-0))),  new GreenBrick((colDif+(col*8)), (ligDif+lig*(5-0))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-0)))},
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-1))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-1))), new GreenBrick((colDif+(col*2)), (ligDif+lig*(5-1))),  new BlueBrick((colDif+(col*3)), (ligDif+lig*(5-1))), new GreenBrick((colDif+(col*4)), (ligDif+lig*(5-1)),true), new GreenBrick((colDif+(col*5)), (ligDif+lig*(5-1)),true), new BlueBrick((colDif+(col*6)), (ligDif+lig*(5-1))), new GreenBrick((colDif+(col*7)), (ligDif+lig*(5-1))),  new BlueBrick((colDif+(col*8)), (ligDif+lig*(5-1))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-1)))},
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*2)), (ligDif+lig*(5-2))),  new GreenBrick((colDif+(col*3)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*4)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-2))), new GreenBrick((colDif+(col*6)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*7)), (ligDif+lig*(5-2))),  new BlueBrick((colDif+(col*8)), (ligDif+lig*(5-2))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-2)))},
            {new BlueBrick((colDif+(col*0)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*2)), (ligDif+lig*(5-3))),  new BlueBrick((colDif+(col*3)), (ligDif+lig*(5-3))), new GreenBrick((colDif+(col*4)), (ligDif+lig*(5-3))), new GreenBrick((colDif+(col*5)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*6)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*7)), (ligDif+lig*(5-3))),  new BlueBrick((colDif+(col*8)), (ligDif+lig*(5-3))), new BlueBrick((colDif+(col*9)), (ligDif+lig*(5-3)))},
            {new EmptyBrick((colDif+(col*0)), (ligDif+lig*(5-4))), new BlueBrick((colDif+(col*1)), (ligDif+lig*(5-4))), new EmptyBrick((colDif+(col*2)), (ligDif+lig*(5-4))),  new EmptyBrick((colDif+(col*3)), (ligDif+lig*(5-4))), new BlueBrick((colDif+(col*4)), (ligDif+lig*(5-4))), new BlueBrick((colDif+(col*5)), (ligDif+lig*(5-4))), new EmptyBrick((colDif+(col*6)), (ligDif+lig*(5-4))), new EmptyBrick((colDif+(col*7)), (ligDif+lig*(5-4))),  new BlueBrick((colDif+(col*8)), (ligDif+lig*(5-4))), new EmptyBrick((colDif+(col*9)), (ligDif+lig*(5-4)))}
    };

    private void setBricks(Boolean random){
        if (!random){
            wall = wallInit;
            for (int i=0; i< nbL;i++)
                for (int j=0; j<nbC;j++) {
                wall[i][j].addBody(gw);
                }
        } else {
            for (int i=0; i< nbL;i++)
                for (int j=0; j<nbC;j++) {
                    wall[i][j] = randomBrick((colDif+(col*j)),(ligDif+lig*(5-i)));
                }
            for (int i=0; i< nbL;i++)
                for (int j=0; j<nbC;j++) {
                    wall[i][j].addBody(gw);
                }
        }

    }

    public Brick randomBrick(int x, int y){
        int t = r.nextInt(1000);
        if (t<100){
            return new EmptyBrick(x, y);
        } else if(t<500){
            return new BlueBrick(x,y);
        } else if (t<900){
            return new GreenBrick(x,y);
        } else return new GreenBrick(x,y,true);
    }

    public Wall(GameWorld gaw){
        this.gw = gaw;
        this.r = new Random();
        this.nbL = wallInit.length;
        this.nbC = wallInit[0].length;
        this.wall = new Brick[this.nbL][this.nbC];
        setBricks(false);
        amBricks = countBricks();
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
            a.get(i).remBody(gw.getWorld(), this);
        }
    }

    public void draw(SpriteBatch sb){
        for (int i=0; i< nbL;i++)
            for (int j=0; j<nbC;j++) {
                wall[i][j].draw(sb);
            }
    }

    private int countBricks(){
        int res=0;
        for (int i=0; i< nbL;i++)
            for (int j=0; j<nbC;j++) {
                if (!(wall[i][j].getClass().equals(EmptyBrick.class))){
                    if (wall[i][j].getHitsLeft()>0) {
                        res+=1;
                    }
                }
            }
        return res;
    }

    public boolean isWallEmpty(){
        return this.amBricks==0;
    }

    public void brickRemoved(){
        this.amBricks-=1;
    }

    public void reset(boolean b){
        for (int i=0; i< nbL;i++)
            for (int j=0; j<nbC;j++) {
                wall[i][j].remBody(gw.getWorld(), this);
                wall[i][j].reset();
            }
        setBricks(b);
        amBricks = countBricks();
    }
}
