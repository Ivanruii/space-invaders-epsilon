package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DisparoAmigo extends Disparo{
    private Texture disparoAmigo;

    public DisparoAmigo(int ancho, int alto, float posX, float posY, boolean enCurso, Texture disparoAmigo, ) {
        super(ancho, alto, posX, posY, enCurso);
        this.disparoAmigo = disparoAmigo;
    }

    public Texture getDisparoAmigo() {
        return disparoAmigo;
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
