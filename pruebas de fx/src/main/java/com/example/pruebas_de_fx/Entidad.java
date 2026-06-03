package com.example.pruebas_de_fx;

public abstract class Entidad {
    protected String nombre;
    protected int vida;
    protected int vidaMax;
    protected int ataque;
    protected int defensa;
    protected int usosPasiva;

    public Entidad(String nombre, int vidaMax, int ataque, int defensa, int usosPasiva) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.ataque = ataque;
        this.defensa = defensa;
        this.usosPasiva = usosPasiva;
    }

    public String recibirDanio(int danioRecibido) {
        int danioFinal = danioRecibido - this.defensa;
        if (danioFinal < 0) danioFinal = 0;

        this.vida -= danioFinal;
        if (this.vida <= 0) {
            this.vida = 0;
            return nombre + " recibió " + danioFinal + " de daño y ha caído.";
        }
        return nombre + " recibió " + danioFinal + " de daño.";
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    // Getters para JavaFX
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getVidaMax() { return vidaMax; }
    public int getAtaque() { return ataque; }
}