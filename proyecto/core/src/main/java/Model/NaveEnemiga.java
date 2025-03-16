package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NaveEnemiga extends Nave {
    private DisparoEnemigo disparoE; //
    private Texture naveEnemiga;
    private boolean vivo;

    public NaveEnemiga(int ancho, int alto, float posX, float posY, Texture naveEnemiga, boolean vivo, DisparoEnemigo disparoE) {
        super(ancho, alto, posX, posY);
        this.naveEnemiga = naveEnemiga;
        this.vivo = vivo;
        this.disparoE = disparoE;
    }

    public Texture getNaveEnemiga() {
        return naveEnemiga;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    // Movimiento hacia abajo
    public void move() {
        if (this.posY > 0) {
            posY--;
        } else {
            vivo = false;
        }
    }

    // Disparos
    public void shoot() {
        disparoE.setPosX(this.posX + (this.ancho / 2));
        if (disparoE.getPosY() > 0) {
            disparoE.setPosY(disparoE.getPosY() - 5);
        } else {
            disparoE.setPosY(this.posY);
            disparoE.setEnCurso(false);
        }
    }

    public void draw(SpriteBatch sp) {
        sp.draw(this.getNaveEnemiga(), this.getPosX(), this.getPosY(), this.getAncho(), this.getAlto());
    }
}
