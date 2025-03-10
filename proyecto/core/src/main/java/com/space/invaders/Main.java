package com.space.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import Model.DisparoAmigo;
import Model.NaveAmiga;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    private NaveAmiga amiga;

    private Texture naveAmiga;

    private Texture dispAmigo;

    private Texture portada;

    private DisparoAmigo disparoAmigo;

    private float click;

    private float sitio;

    private boolean inicio;

    private Music music_intro;

    private Music music_playing;

    @Override
    public void create() {
        sitio = (Gdx.graphics.getWidth()/2)- 60;
        click = (Gdx.graphics.getWidth()/2)- 60;
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        naveAmiga = new Texture("naveAmiga.png");
        dispAmigo = new Texture("disparoAmigo.png");
        portada = new Texture("inicio.png");
        music_intro = Gdx.audio.newMusic(Gdx.files.internal("Music/principio.mp3"));
        music_playing = Gdx.audio.newMusic(Gdx.files.internal("Music/play.mp3"));
        inicio = true;
        music_intro.setLooping(true);
        music_intro.play();
        music_playing.setLooping(true);
        music_playing.stop();

        disparoAmigo = new DisparoAmigo(20, 20, 60, 60, false, dispAmigo, false, 4);
        amiga = new NaveAmiga(60, 80, (Gdx.graphics.getWidth()/2)- 60, 10, naveAmiga, true, 3);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        if(inicio){
            batch.draw(portada, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            if (Gdx.input.justTouched()){
                inicio = false;
                music_intro.stop();
            }
        }else {
            music_playing.play();
            amiga.draw(batch);
            if (Gdx.input.justTouched()) {
                click = Gdx.input.getX();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !disparoAmigo.isEnCurso()) {
                sitio = amiga.getPosX() + (amiga.getAncho() / 2);
                disparoAmigo.setEnCurso(true);
            }
            if (disparoAmigo.isEnCurso()) {
                disparoAmigo.shoot(sitio);
                if (disparoAmigo.isEnCurso()) {
                    disparoAmigo.draw(batch);
                }
            }
            amiga.move(click);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        naveAmiga.dispose();
        dispAmigo.dispose();
        image.dispose();
        portada.dispose();
    }
}

