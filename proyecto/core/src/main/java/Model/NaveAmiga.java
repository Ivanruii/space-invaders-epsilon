package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NaveAmiga extends Nave{
    private DisparoAmigo disparoA;

    private Texture naveAmiga;

    private boolean vivo;

    private int vidas;


    public NaveAmiga(int ancho, int alto, float posX, float posY,  Texture naveAmiga, boolean vivo, int vidas, DisparoAmigo disparoA) {
        super(ancho, alto, posX, posY);
        this.naveAmiga = naveAmiga;
        this.vivo = vivo;
        this.vidas = vidas;
        this.disparoA = disparoA;
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
            if(this.posX>0){
                posX--;
            }
        }else{
            if(click-this.posX>this.ancho){
                if(this.posX+this.ancho<Gdx.graphics.getWidth()-18){
                    posX++;
                }
            }
        }
    }

    public void shoot(float posX){
        disparoA.setPosX(posX);
        if (disparoA.getPosY()< Gdx.graphics.getHeight()){
            disparoA.setPosY(disparoA.getPosY()+5);
        }else{
            disparoA.setPosY(60);
            disparoA.setEnCurso(false);
        }
    }

    public void draw (SpriteBatch sp) {
        sp.draw(this.getNaveAmiga(),this.getPosX(),this.getPosY(),this.getAlto(),this.getAncho());
    }
}
