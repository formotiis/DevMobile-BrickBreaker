package fr.ul.cassebrique.DataFactories;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    private static final Texture texBlueBrick = new Texture("images/Brique1C.png");
    private static final Texture texGreenBrickA = new Texture("images/Brique2Ca.png");
    private static final Texture texGreenBrickB = new Texture("images/Brique2Cb.png");
    private static final Texture texBack = new Texture("images/Fond2.png");
    private static final Texture texBall = new Texture("images/Bille.png");
    private static final Texture texBorder = new Texture("images/Contour.png");
    private static final Texture texRacket = new Texture("images/Barre.png");
    private static final Texture texWin = new Texture("images/Bravo.bmp");
    private static final Texture texBallLoss = new Texture("images/PerteBalle.bmp");
    private static final Texture texGameOver = new Texture("images/Perte.bmp");

    public static Texture getTexBack() {
        return texBack;
    }

    public static Texture getTexBall() {
        return texBall;
    }

    public static Texture getTexBorder() {
        return texBorder;
    }

    public static Texture getTexRacket() {
        return texRacket;
    }
    public static Texture getTexBlueBrick() {
        return texBlueBrick;
    }

    public static Texture getTexGreenBrickA() {
        return texGreenBrickA;
    }

    public static Texture getTexGreenBrickB() {
        return texGreenBrickB;
    }

    public static Texture getTexWin() {
        return texWin;
    }

    public static Texture getTexBallLoss() {
        return texBallLoss;
    }

    public static Texture getTexGameOver() {
        return texGameOver;
    }
}
