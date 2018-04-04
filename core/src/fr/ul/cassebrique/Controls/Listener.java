package fr.ul.cassebrique.Controls;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import fr.ul.cassebrique.Models.GameState;
import fr.ul.cassebrique.Views.GameScreen;
import javafx.scene.input.KeyCode;

public class Listener implements InputProcessor {

    private GameScreen gs;

    public Listener(GameScreen g) {
        this.gs = g;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.ESCAPE){
            gs.quit();
            return true;
        } else if (keycode == Input.Keys.SPACE){
            gs.pause();
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
