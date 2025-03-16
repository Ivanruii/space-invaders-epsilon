package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DisparoEnemigo extends Disparo {
    private Texture disparoEnemigo; // Textura del disparo enemigo, no estoy 100% seguro de que vaya a tener su propia textura, hacermelo saber.

    public DisparoEnemigo(int ancho, int alto, float posX, float posY, boolean enCurso, Texture disparoEnemigo) {
        super(ancho, alto, posX, posY, enCurso);
        this.disparoEnemigo = disparoEnemigo;
    }

    public Texture getDisparoEnemigo() {
        return disparoEnemigo;
    }

    // Lógica del disparo enemigo
    public void shoot(float posX) {
        setPosX(posX);
        if (this.posY > 0) {
            posY = posY - 5;
        } else {
            posY = Gdx.graphics.getHeight() - 60;
            setEnCurso(false);
        }
    }

    // Dibuja el disparo enemigo
    public void draw(SpriteBatch sp) {
        sp.draw(this.getDisparoEnemigo(), this.getPosX(), this.getPosY(), this.getAlto(), this.getAncho());
    }
}

