package Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Squadron {
    private static final int MAX_NAVES_SQUADRON = 8;
    private static final int SEPARACION_HORIZONTAL_NAVES = 50;
    private static final int SEPARACION_VERTICAL_NAVES = 50;

    private static final int ANCHO_NAVE = 30;
    private static final int ALTO_NAVE = 30;

    private List<NaveEnemiga> navesSquadron;
    private Texture naveEnemiga;
    private Texture tDisparoEnemigo;

    public Squadron(Texture naveEnemiga, Texture tDisparoEnemigo) {
        this.navesSquadron = new ArrayList<>();
        this.naveEnemiga = naveEnemiga;
        this.tDisparoEnemigo = tDisparoEnemigo;
    }

    public void inicializarSquadron(int alturaYSquadron) {
        for (int i = 0; i < MAX_NAVES_SQUADRON; i++) {
            agregarNave(i, alturaYSquadron);
        }
    }

    /* Agrega una nueva nave al squadron */
    private void agregarNave(int index, int alturaYSquadron) {
        float posX = index * SEPARACION_HORIZONTAL_NAVES;
        float posY = alturaYSquadron - SEPARACION_VERTICAL_NAVES;

        DisparoEnemigo disparo = new DisparoEnemigo(15, 15, posX, posY, false, tDisparoEnemigo);
        navesSquadron.add(new NaveEnemiga(ANCHO_NAVE, ALTO_NAVE, posX, posY, naveEnemiga, true, disparo));
    }

    /* Mueve el squadron según las indicaciones del batallon */
    public void moverSquadron(float deltaX, float deltaY) {
        for (NaveEnemiga nave : navesSquadron) {
            if (nave.isVivo()) {
                nave.moverNave(deltaX, deltaY);
                nave.actualizarDisparo();
            }
        }
    }

    /* Hace que alguna de las naves del squadron dispare de forma aletoria */
    public void dispararAleatorio() {
        if (!navesSquadron.isEmpty()) {
            int naveIndex = new Random().nextInt(navesSquadron.size());
            if (navesSquadron.get(naveIndex).isVivo()) navesSquadron.get(naveIndex).disparar();
        }
    }

    /* Obtiene la posición de la nave más cercana al borde izquierdo */
    public float getPosicionExtremoIzquierdo() {
        float minX = Float.MAX_VALUE;
        for (NaveEnemiga nave : navesSquadron) {
            if (nave.isVivo() && nave.getPosX() < minX) {
                minX = nave.getPosX();
            }
        }
        return minX;
    }

    /* Obtiene la posición de la nave más cercana al borde derecho */
    public float getPosicionExtremoDerecho() {
        float maxX = 0;
        for (NaveEnemiga nave : navesSquadron) {
            if (nave.isVivo()) {
                float extremoDerecho = nave.getPosX() + nave.getAncho();
                if (extremoDerecho > maxX) {
                    maxX = extremoDerecho;
                }
            }
        }
        return maxX;
    }

    public List<NaveEnemiga> getNaves() {
        return navesSquadron;
    }

    public void draw(SpriteBatch sp) {
        for (NaveEnemiga nave : navesSquadron) {
            nave.draw(sp);
        }
    }
}
