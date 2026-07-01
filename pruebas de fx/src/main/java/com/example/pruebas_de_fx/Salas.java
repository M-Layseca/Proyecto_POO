package com.example.pruebas_de_fx;

/**
 * Representa una sala genérica dentro de la mazmorra que el jugador
 * recorre durante la partida. Es la superclase abstracta de las
 * distintas variantes de sala (por ejemplo, salas de combate o de tesoro).
 */
public abstract class Salas {

    /** Nombre identificador de la sala. */
    protected String nombreSala;

    /** Descripción textual de la sala, mostrada al jugador al ingresar. */
    protected String descripcion;

    /**
     * Crea una nueva sala con su nombre y descripción.
     *
     * @param nombre nombre de la sala.
     * @param descripcion descripción de la sala.
     */
    public Salas(String nombre, String descripcion) {
        this.nombreSala = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Ejecuta la lógica correspondiente al ingreso del jugador a la sala.
     * Cada subclase concreta define su propio comportamiento (combate,
     * recompensa, etc.).
     *
     * @param jugador jugador que ingresa a la sala.
     */
    public abstract void entrar(Jugador jugador);
}
