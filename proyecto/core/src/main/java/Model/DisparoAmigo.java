package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DisparoAmigo extends Disparo{
    private Texture disparoAmigo;

    private boolean vivo;

    private int vidas;


    public DisparoAmigo(int ancho, int alto, float posX, float posY, boolean enCurso, Texture disparoAmigo, boolean vivo, int vidas) {
        super(ancho, alto, posX, posY, enCurso);
        this.disparoAmigo = disparoAmigo;
        this.vivo = vivo;
        this.vidas = vidas;
    }

    public Texture getDisparoAmigo() {
        return disparoAmigo;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getVidas() {
        return vidas;
    }

    public void shoot(float posX){
        setPosX(posX);
        if (this.posY< Gdx.graphics.getHeight()){
            posY= posY+5;
        }else{
            posY= 60;
            setEnCurso(false);
        }
    }


    public void draw (SpriteBatch sp) {
        sp.draw(this.getDisparoAmigo(),this.getPosX(),this.getPosY(),this.getAlto(),this.getAncho());
    }
}
