package com.space.invaders.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NaveEnemiga extends Nave {
    private DisparoEnemigo disparoE;
    private Texture naveEnemiga;
    private boolean vivo;

    public NaveEnemiga(int ancho, int alto, float posX, float posY, Texture naveEnemiga, boolean vivo, DisparoEnemigo disparoE) {
        super(ancho, alto, posX, posY);
        this.naveEnemiga = naveEnemiga;
        this.vivo = vivo;
        this.disparoE = disparoE;
    }

    public void actualizarDisparo() {
        if (!vivo) return;
        moverDisparo();
    }

    public void moverNave(float deltaX, float deltaY) {
        if (!vivo) return;
        posX += deltaX;
        posY += deltaY;
    }

    public void disparar() {
        if (!disparoE.isEnCurso()) {
            iniciarDisparo();
        }
    }

    private void iniciarDisparo() {
        disparoE.setPosX(posX + ancho / 2);
        disparoE.setPosY(posY);
        disparoE.setEnCurso(true);
    }

    private void moverDisparo() {
        if (disparoE.isEnCurso()) {
            disparoE.shoot(disparoE.getPosX());
        } else {
            disparoE.setPosX(posX + ancho / 2);
            disparoE.setPosY(posY);
        }
    }


    public boolean isVivo() {
        return vivo;
    }

    public DisparoEnemigo getDisparoEnemigo() {
        return disparoE;
    }

    public void draw(SpriteBatch sp) {
        if (vivo) {
            sp.draw(naveEnemiga, posX, posY, ancho, alto);
        }
        if (disparoE.isEnCurso()) {
            disparoE.draw(sp);
        }
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
}
