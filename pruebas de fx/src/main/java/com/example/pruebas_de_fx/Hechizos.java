package com.example.pruebas_de_fx;

/**
 * Contrato que deben cumplir todas las habilidades mágicas (hechizos)
 * que un {@link Jugador} puede lanzar durante el combate.
 * <p>
 * Cada implementación define su propio nombre, coste de maná y el
 * efecto concreto que produce sobre un objetivo.
 */
public interface Hechizos {

    /**
     * Obtiene el nombre visible del hechizo.
     *
     * @return nombre del hechizo.
     */
    String getNombreHechizo();

    /**
     * Obtiene el coste en puntos de maná necesario para lanzar el hechizo.
     *
     * @return coste de maná del hechizo.
     */
    int getCosteMana();

    /**
     * Aplica el efecto del hechizo sobre un objetivo (daño, curación, buff, etc.).
     *
     * @param lanzador jugador que lanza el hechizo.
     * @param objetivo entidad que recibe el efecto del hechizo.
     */
    void aplicarEfecto(Jugador lanzador, Entidad objetivo);
}
