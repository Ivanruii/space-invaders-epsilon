package Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NaveAmiga extends Nave{
    private Texture naveAmiga;

    private boolean vivo;

    private int vidas;


    public NaveAmiga(int ancho, int alto, float posX, float posY,  Texture naveAmiga, boolean vivo, int vidas) {
        super(ancho, alto, posX, posY);
        this.naveAmiga = naveAmiga;
        this.vivo = vivo;
        this.vidas = vidas;
    }

    public Texture getNaveAmiga() {
        return naveAmiga;
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

    public void move(float click){
         if (click < (this.posX+(this.ancho/2))) {
            posX--;
        }else{
            posX++;
        }
    }

    public void draw (SpriteBatch sp) {
        sp.draw(this.getNaveAmiga(),this.getPosX(),this.getPosY(),this.getAlto(),this.getAncho());
    }
}
