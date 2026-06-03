package com.example.pruebas_de_fx;

public interface Hechizos { // Debe ser 'interface'
    String getNombreHechizo();
    int getCosteMana();
    void aplicarEfecto(Jugador lanzador, Entidad objetivo);
}
