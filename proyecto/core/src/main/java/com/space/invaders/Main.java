package com.space.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import Model.Batallon;
import Model.DisparoAmigo;
import Model.NaveAmiga;
import Model.NaveEnemiga;
import Model.Squadron;

public class Main extends ApplicationAdapter {
    // Estado del juego
    private enum EstadoJuego {
        MENU, JUGANDO, FIN_JUEGO, VICTORIA
    }

    // Componentes básicos del juego
    private SpriteBatch batch;
    private EstadoJuego estadoActual;
    private int contadorMovimientoBatallon;
    private float posicionObjetivoJugador;

    // Recursos gráficos
    private Texture naveAmiga;
    private Texture[] texturasSquadron;
    private Texture disparoAmigo;
    private Texture disparoEnemigo;
    private Texture portada;
    private Texture gameOver;
    private Texture pantallaVictoria;

    // Recursos de audio
    private Music musicaMenu;
    private Music musicaJuego;

    // Entidades del juego
    private NaveAmiga jugador;
    private Batallon batallon;

    @Override
    public void create() {
        batch = new SpriteBatch();
        estadoActual = EstadoJuego.MENU;
        posicionObjetivoJugador = Gdx.graphics.getWidth() / 2;
        contadorMovimientoBatallon = 0;

        cargarTexturas();
        cargarMusica();
        inicializarEntidadesJuego();
    }

    /* Cargar todas las texturas necesarias para el juego */
    private void cargarTexturas() {
        naveAmiga = new Texture("naveAmiga.png");

        texturasSquadron = new Texture[4];
        texturasSquadron[0] = new Texture("enemigo1.png");
        texturasSquadron[1] = new Texture("enemigo2.png");
        texturasSquadron[2] = new Texture("enemigo3.png");
        texturasSquadron[3] = new Texture("enemigo4.png");

        disparoAmigo = new Texture("disparoAmigo.png");
        disparoEnemigo = new Texture("disparoAmigo.png");

        portada = new Texture("inicio.png");
        gameOver = new Texture("gameover.jpg");
        pantallaVictoria = new Texture("winner.jpg");
    }

    /* Carga los archivos de música */
    private void cargarMusica() {
        musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("Music/principio.mp3"));
        musicaJuego = Gdx.audio.newMusic(Gdx.files.internal("Music/play.mp3"));

        musicaMenu.setLooping(true);
        musicaMenu.play();

        musicaJuego.setLooping(true);
    }

    /* Inicializa las entidades principales del juego */
    private void inicializarEntidadesJuego() {
        DisparoAmigo disparo = new DisparoAmigo(20, 20, 60, 60, false, disparoAmigo);

        jugador = new NaveAmiga(
            60, 80,
            (Gdx.graphics.getWidth() / 2) - 60,
            10,
            naveAmiga,
            true, 3, disparo
        );

        batallon = new Batallon(texturasSquadron, disparoEnemigo);
        batallon.inicializarBatallon();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        procesarEntrada();
        actualizar();
        dibujar();
    }

    /**
     * Procesa la entrada del usuario según el estado actual
     */
    private void procesarEntrada() {
        switch (estadoActual) {
            case MENU:
                if (Gdx.input.justTouched()) {
                    iniciarJuego();
                }
                break;

            case JUGANDO:
                if (Gdx.input.justTouched()) {
                    posicionObjetivoJugador = Gdx.input.getX();
                }

                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    jugador.disparar();
                }
                break;

            case FIN_JUEGO:
            case VICTORIA:
                if (Gdx.input.justTouched()) {
                    reiniciarJuego();
                }
                break;
        }
    }

    /* Actualiza la lógica del juego */
    private void actualizar() {
        if (estadoActual != EstadoJuego.JUGANDO) return;

        jugador.move(posicionObjetivoJugador);
        jugador.actualizarDisparos();

        actualizarMovimientoEnemigos();
        batallon.actualizar();

        comprobarColisiones();

        if (batallon.estanTodasNavesEliminadas()) {
            juegoGanado();
        }
    }

    /* Actualiza el movimiento de los enemigos */
    private void actualizarMovimientoEnemigos() {
        if (contadorMovimientoBatallon >= 120) {
            batallon.moverBatallon();
            contadorMovimientoBatallon = 0;
        }
        contadorMovimientoBatallon++;
    }

    /* Dibujado de los elementos en pantalla según el estado del juego */
    private void dibujar() {
        batch.begin();

        switch (estadoActual) {
            case MENU:
                batch.draw(portada, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                break;

            case JUGANDO:
                jugador.draw(batch);
                batallon.draw(batch);
                break;

            case FIN_JUEGO:
                batch.draw(gameOver, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                break;

            case VICTORIA:
                batch.draw(pantallaVictoria, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                break;
        }

        batch.end();
    }

    /* Comprobación de todas las colisiones */
    private void comprobarColisiones() {
        comprobarColisionesDisparosJugador();
        comprobarColisionesDisparosEnemigos();
    }

    /* Comprueba las colisiones de los disparos */
    private void comprobarColisionesDisparosJugador() {
        for (DisparoAmigo disparo : jugador.getDisparos()) {
            if (disparo.isEnCurso()) {
                for (Squadron squadron : batallon.getSquadrons()) {
                    for (NaveEnemiga nave : squadron.getNaves()) {
                        if (nave.isVivo() && disparo.colisionaNaveEnemiga(nave)) {
                            nave.setVivo(false);
                            disparo.setEnCurso(false);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void comprobarColisionesDisparosEnemigos() {
        for (Squadron squadron : batallon.getSquadrons()) {
            for (NaveEnemiga nave : squadron.getNaves()) {
                if (nave.isVivo() && nave.getDisparoEnemigo().isEnCurso()) {
                    if (nave.getDisparoEnemigo().colisionaNaveAmiga(jugador)) {
                        jugador.setVivo(false);
                        finJuego();
                        break;
                    }
                }
            }
        }
    }

    /* Iniciar el juego desde el menú principal */
    private void iniciarJuego() {
        estadoActual = EstadoJuego.JUGANDO;
        musicaMenu.stop();
        musicaJuego.play();
    }

    /* Finalizar el juego por derrota */
    private void finJuego() {
        estadoActual = EstadoJuego.FIN_JUEGO;
        musicaJuego.stop();
    }

    /* Finaliza el juego por victoria */
    private void juegoGanado() {
        estadoActual = EstadoJuego.VICTORIA;
        musicaJuego.stop();
    }

    /* Reinicia el juego al estado inicial */
    private void reiniciarJuego() {
        estadoActual = EstadoJuego.MENU;

        DisparoAmigo disparo = new DisparoAmigo(20, 20, 60, 60, false, disparoAmigo);

        jugador = new NaveAmiga(
            60,
            80,
            (Gdx.graphics.getWidth() / 2) - 60,
            10,
            naveAmiga,
            true, 3, disparo
        );

        batallon = new Batallon(texturasSquadron, disparoEnemigo);
        batallon.inicializarBatallon();

        contadorMovimientoBatallon = 0;

        musicaMenu.play();
    }

    @Override
    public void dispose() {
        batch.dispose();
        naveAmiga.dispose();
        for (Texture textura : texturasSquadron) {
            textura.dispose();
        }
        disparoAmigo.dispose();
        disparoEnemigo.dispose();
        portada.dispose();
        gameOver.dispose();
        pantallaVictoria.dispose();

        musicaMenu.dispose();
        musicaJuego.dispose();
    }
}
