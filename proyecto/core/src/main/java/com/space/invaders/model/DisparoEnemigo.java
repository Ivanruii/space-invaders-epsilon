package com.space.invaders.model;

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

    public void shoot(float posX) {
        setPosX(posX);
        if (this.posY > 0) {
            posY -= 5;
        } else {
            setEnCurso(false);
            posY = 0;
        }
    }

    public boolean colisionaNaveAmiga(NaveAmiga naveAmiga) {
        if (!this.isEnCurso() || !naveAmiga.isVivo()) {
            return false;
        }

        float disparoX = this.getPosX();
        float disparoY = this.getPosY();
        float naveX = naveAmiga.getPosX();
        float naveY = naveAmiga.getPosY();

        return (disparoX < naveX + naveAmiga.getAncho() &&
            disparoX + this.getAncho() > naveX &&
            disparoY < naveY + naveAmiga.getAlto() &&
            disparoY + this.getAlto() > naveY);
    }

    // Dibuja el disparo enemigo
    public void draw(SpriteBatch sp) {
        sp.draw(this.getDisparoEnemigo(), this.getPosX(), this.getPosY(), this.getAlto(), this.getAncho());
    }
}

