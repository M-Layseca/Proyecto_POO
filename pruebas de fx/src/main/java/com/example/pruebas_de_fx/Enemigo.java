package com.example.pruebas_de_fx;
import java.util.Random;

public abstract class Enemigo extends Entidad {
    protected int xpOtorgada;
    protected Random random = new Random();

    public Enemigo(String nombre, int vidaMax, int ataque, int defensa, int usosPasiva, int xpOtorgada) {
        super(nombre, vidaMax, ataque, defensa, usosPasiva);
        this.xpOtorgada = xpOtorgada;
    }

    public int getXpOtorgada() { return xpOtorgada; }

    public abstract String atacar(Jugador objetivo);
    @Override
    public String toString() {
        return this.getNombre();
    }
}