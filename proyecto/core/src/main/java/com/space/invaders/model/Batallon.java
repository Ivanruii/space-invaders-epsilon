package com.space.invaders.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Batallon {
    private static final int NUM_SQUADS_BATALLON = 4;
    private static final int SEPARACION_VERTICAL_SQUADS = 35;
    private static final int INTERVALO_DISPARO = 180;

    private List<Squadron> listSquadron;
    private Texture[] texturasSquadron;
    private Texture tDisparoEnemigo;

    // Control de movimiento del batallon
    private float velX = 2;
    private float velY = 2;
    private boolean cambiarDireccion = false;

    // Control de disparos
    private int tiempoDisparoEnemigo = 0;

    public Batallon(Texture[] texturasSquadron, Texture tDisparoEnemigo) {
        this.listSquadron = new ArrayList<>();
        this.texturasSquadron = texturasSquadron;
        this.tDisparoEnemigo = tDisparoEnemigo;
    }

    public void inicializarBatallon() {
        for (int i = 0; i < NUM_SQUADS_BATALLON; i++) {
            agregarSquadron(i);
        }
    }

    /* Agrega un nuevo squadron al batallon */
    private void agregarSquadron(int index) {
        // Se usa el módulo por si en algún momento hay más squadrones que texturas
        Texture texturaSquadron = texturasSquadron[index % texturasSquadron.length];

        Squadron nuevoSquadron = new Squadron(texturaSquadron, tDisparoEnemigo);
        // Se indica la posición en altura del squadron, ya que no todos están en la misma altura
        nuevoSquadron.inicializarSquadron(Gdx.graphics.getHeight() - index * SEPARACION_VERTICAL_SQUADS);
        listSquadron.add(nuevoSquadron);
    }

    /* Actualiza el estado del batallón en cada frame */
    public void actualizar() {
        moverBatallon();
        gestionarDisparos();
    }

    /* Mueve todo el batallón según los parámetros de velocidad */
    public void moverBatallon() {
        comprobarLimites();

        float deltaX = cambiarDireccion ? 0 : velX;
        float deltaY = cambiarDireccion ? -velY : 0;

        for (Squadron squadron : listSquadron) {
            squadron.moverSquadron(deltaX, deltaY);
        }

        if (cambiarDireccion) {
            velX = -velX;
            cambiarDireccion = false;
        }
    }

    /*
    Comprueba si algún escuadrón ha alcanzado los límites de la pantalla,
    para cambiar la dirección del movimiento
    */
    private void comprobarLimites() {
        boolean alcanzoBorde = false;

        for (Squadron squadron : listSquadron) {
            float posXExtremoIzquierdo = squadron.getPosicionExtremoIzquierdo();
            float posXExtremoDerecho = squadron.getPosicionExtremoDerecho();

            if ((velX > 0 && posXExtremoDerecho >= Gdx.graphics.getWidth()) ||
                (velX < 0 && posXExtremoIzquierdo <= 0)) {
                alcanzoBorde = true;
                break;
            }
        }

        if (alcanzoBorde) {
            cambiarDireccion = true;
        }
    }

    /* Gestiona el control de disparos enemigos */
    private void gestionarDisparos() {
        if (tiempoDisparoEnemigo >= INTERVALO_DISPARO) {
            disparoEnemigoAleatorio();
            tiempoDisparoEnemigo = 0;
        } else {
            tiempoDisparoEnemigo++;
        }
    }

    /* Selecciona un escuadrón al azar para disparar */
    private void disparoEnemigoAleatorio() {
        if (!listSquadron.isEmpty()) {
            int squadIndex = new Random().nextInt(listSquadron.size());
            listSquadron.get(squadIndex).dispararAleatorio();
        }
    }

    /* Comprueba si estan todas las naves del batallon eliminadas */
    public boolean estanTodasNavesEliminadas() {
        for (Squadron squadron : listSquadron) {
            for (NaveEnemiga nave : squadron.getNaves()) {
                if (nave.isVivo()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Squadron> getSquadrons() {
        return listSquadron;
    }

    public void draw(SpriteBatch sp) {
        for (Squadron squadron : listSquadron) {
            squadron.draw(sp);
        }
    }

}
