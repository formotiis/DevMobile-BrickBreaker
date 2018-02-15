package fr.ul.cassebrique.Models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall {

    private int nbL; //nombre de lignes
    private int nbC; //nombre de colones

    private Brick wall[][];

    private static final Brick wallInit[][]= {
            {Brick.Bleue, Brick.Bleue, Brick.Vide,  Brick.VerteAbimee, Brick.Vide, Brick.Bleue, Brick.Vide, Brick.Verte,  Brick.Verte, Brick.Bleue},
            {Brick.Bleue, Brick.Bleue, Brick.VerteAbimee,  Brick.VerteAbimee, Brick.Vide, Brick.Bleue, Brick.Bleue, Brick.Verte,  Brick.Verte, Brick.Bleue},
            {Brick.Bleue, Brick.Bleue, Brick.VerteAbimee,  Brick.VerteAbimee, Brick.Vide, Brick.Bleue, Brick.Bleue, Brick.Verte,  Brick.Verte, Brick.Bleue},
            {Brick.Bleue, Brick.Bleue, Brick.VerteAbimee,  Brick.VerteAbimee, Brick.Vide, Brick.Bleue, Brick.Bleue, Brick.Verte,  Brick.Verte, Brick.Bleue},
            {Brick.Bleue, Brick.Bleue, Brick.VerteAbimee,  Brick.VerteAbimee, Brick.Vide, Brick.Bleue, Brick.Bleue, Brick.Verte,  Brick.Verte, Brick.Bleue}
    };

    private void setBricks(Boolean random){
        if (!random){
            wall = wallInit;
        } else {

        }

    }

    public Wall(){
        this.nbL = wallInit.length;
        this.nbC = wallInit[0].length;
        this.wall = new Brick[this.nbL][this.nbC];
        setBricks(false);
    }

    public void draw(SpriteBatch sb){
        int colDif =50, ligDif=324,col=100, lig=46;
        for (int i=0; i< nbL;i++)
            for (int j=0; j<nbC;j++) {
                if (wall[i][j].getTex()!=null)
                sb.draw(wall[i][j].getTex(),  (colDif+(col*j)), (ligDif+lig*(nbL-i)));
            }
    }



}
