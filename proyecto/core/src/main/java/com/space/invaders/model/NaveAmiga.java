package com.space.invaders.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class NaveAmiga extends Nave {
    private List<DisparoAmigo> disparos;
    private Texture disparoTexture;
    private Texture naveAmiga;
    private boolean vivo;
    private int vidas;
    private static final int MAX_DISPAROS = 3; // Número máximo de disparos simultáneos

    public NaveAmiga(int ancho, int alto, float posX, float posY, Texture naveAmiga, boolean vivo, int vidas, DisparoAmigo disparoModelo) {
        super(ancho, alto, posX, posY);
        this.naveAmiga = naveAmiga;
        this.vivo = vivo;
        this.vidas = vidas;
        this.disparos = new ArrayList<>();
        this.disparoTexture = disparoModelo.getDisparoAmigo();
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

    public void move(float click) {
        if (click < (this.posX + (this.ancho / 2))) {
            if (this.posX > 0) {
                posX--;
            }
        } else {
            if (click - this.posX > this.ancho) {
                if (this.posX + this.ancho < Gdx.graphics.getWidth() - 18) {
                    posX++;
                }
            }
        }
    }

    public void disparar() {
        // Limitar el número de disparos simultáneos
        if (disparos.size() < MAX_DISPAROS) {
            float posicionDisparo = this.getPosX() + (this.ancho / 2);
            DisparoAmigo nuevoDisparo = new DisparoAmigo(20, 20, posicionDisparo, this.getPosY() + this.alto, true, disparoTexture);
            disparos.add(nuevoDisparo);
        }
    }

    public void actualizarDisparos() {
        Iterator<DisparoAmigo> it = disparos.iterator();
        while (it.hasNext()) {
            DisparoAmigo disparo = it.next();
            if (disparo.isEnCurso()) {
                disparo.shoot(disparo.getPosX());
            } else {
                it.remove();
            }
        }
    }

    public List<DisparoAmigo> getDisparos() {
        return disparos;
    }

    public void draw(SpriteBatch sp) {
        sp.draw(this.getNaveAmiga(), this.getPosX(), this.getPosY(), this.getAlto(), this.getAncho());

        // Dibujar todos los disparos activos
        for (DisparoAmigo disparo : disparos) {
            if (disparo.isEnCurso()) {
                disparo.draw(sp);
            }
        }
    }
}
