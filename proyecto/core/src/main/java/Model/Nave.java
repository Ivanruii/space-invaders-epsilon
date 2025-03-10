package Model;

public class Nave {
    protected int ancho;

    protected int alto;

    protected float posX;

    protected float posY;

    public Nave(int ancho, int alto, float posX, float posY) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
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
    public float getPosY() {
        return posY;
    }
}
