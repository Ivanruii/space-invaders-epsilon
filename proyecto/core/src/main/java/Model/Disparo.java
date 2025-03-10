package Model;

public class Disparo {
    protected int ancho;

    protected int alto;

    protected float posX;

    protected float posY;

    protected boolean enCurso;

    public Disparo(int ancho, int alto, float posX, float posY, boolean enCurso) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.enCurso = enCurso;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }
}
