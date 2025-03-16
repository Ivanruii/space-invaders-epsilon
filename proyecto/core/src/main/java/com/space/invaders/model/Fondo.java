package com.space.invaders.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {
    private float ancho;
    private float largo;
    private float x;
    private float y;
    private float velocidadFondo;
    private Texture tableroInteractivo;

    public Fondo(float velocidadFondo,Texture tableroInteractivo) {
        this.ancho = Gdx.graphics.getWidth();
        this.largo = Gdx.graphics.getHeight();
        this.x = 0;
        this.y = 0;
        this.velocidadFondo = velocidadFondo;
        this.tableroInteractivo = tableroInteractivo;
    }

    public float getVelocidadFondo() {
        return velocidadFondo;
    }
    public void setVelocidadFondo (float velocidadFondo) {
        this.velocidadFondo = velocidadFondo;
    }

    public void dibujarFondo (SpriteBatch batch) {
        batch.draw(tableroInteractivo,x,y,ancho,largo);
        batch.draw(tableroInteractivo,x,y + Gdx.graphics.getHeight(),ancho,largo);
    }
    public void moverFondo () {
        y = y - (velocidadFondo * Gdx.graphics.getDeltaTime());

        if (y <= -Gdx.graphics.getHeight()) {
            y = 0;
        }
    }
}
