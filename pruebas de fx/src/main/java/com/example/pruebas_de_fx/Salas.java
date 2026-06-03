package com.example.pruebas_de_fx;

public abstract class Salas {
    protected String nombreSala;
    protected String descripcion;

    public Salas(String nombre, String descripcion) {
        this.nombreSala = nombre;
        this.descripcion = descripcion;
    }

    public abstract void entrar(Jugador jugador);
}