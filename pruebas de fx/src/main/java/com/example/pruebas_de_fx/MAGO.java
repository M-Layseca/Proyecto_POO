package com.example.pruebas_de_fx;

import java.util.ArrayList;

/**
 * Clase de personaje jugable "Mago". Se caracteriza por una alta
 * inteligencia y un libro de hechizos ofensivos que consumen puntos
 * de maná.
 */
public class MAGO extends Jugador {

    /** Cantidad actual de puntos de maná disponibles. */
    public int usosMagia; // Ya los tenías definidos

    /** Cantidad máxima de puntos de maná que el Mago puede almacenar. */
    public int maxUsos;

    /** Lista de hechizos disponibles para el Mago. */
    private ArrayList<Hechizos> libroDeHechizos;

    /**
     * Crea un personaje Mago con sus estadísticas fijas por defecto y su
     * libro de hechizos inicial (Bola de Fuego, Flecha de Alma, Lanza
     * Relámpago y Resplandor Final).
     *
     * @param nombre nombre elegido para el personaje.
     */
    public MAGO(String nombre) {
        super(nombre, 70, 20, 2, 20, 2);
        this.usosMagia = 10;
        this.maxUsos = 10;
        this.libroDeHechizos = new ArrayList<>();
        this.libroDeHechizos.add(new BoladeFuego());
        this.libroDeHechizos.add(new FlechadeAlma());
        this.libroDeHechizos.add(new LanzaRelampago());
        this.libroDeHechizos.add(new Resplandor_Final());
    }

    /**
     * Obtiene la lista de hechizos disponibles para el Mago.
     *
     * @return lista de hechizos del libro de hechizos.
     */
    public ArrayList<Hechizos> getLibroDeHechizos() {
        return libroDeHechizos;
    }

    /**
     * Activa la habilidad pasiva "Meditación": recupera 6 puntos de
     * maná (sin superar el máximo), mientras queden usos de la pasiva
     * disponibles.
     *
     * @return {@code null}, ya que el resultado se informa por consola.
     */
    @Override
    public String activarSkill() {
        if (usosPasiva > 0) {
            this.usosMagia += 6;
            if (this.usosMagia > maxUsos) this.usosMagia = maxUsos;
            this.usosPasiva--;
            System.out.println("¡Meditación! Magia actual: " + usosMagia + "/" + maxUsos);
        } else {
            System.out.println("Tu mente está agotada para meditar...");
        }
        return null;
    }
}
