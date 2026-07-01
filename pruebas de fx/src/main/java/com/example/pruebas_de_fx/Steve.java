package com.example.pruebas_de_fx;

import java.util.ArrayList;

/**
 * Clase de personaje jugable "Steve" (temática Minecraft). En vez de
 * maná utiliza "materiales" como recurso para sus habilidades, y cuenta
 * con un arsenal propio de ataques especiales.
 */
public class Steve extends Jugador {

    /** Cantidad actual de materiales disponibles (equivalente al maná en otras clases). */
    public int usosMagia;

    /** Cantidad máxima de materiales que Steve puede almacenar. */
    public int maxUsos;

    /** Lista de habilidades especiales disponibles para Steve. */
    private ArrayList<Hechizos> libroDeHabilidades;

    /**
     * Crea al personaje Steve con sus estadísticas fijas por defecto y
     * su inventario inicial de habilidades (Espada de Diamante, Arco y
     * Flecha, Bloque de TNT).
     *
     * @param nombre nombre elegido para el personaje.
     */
    public Steve(String nombre) {
        super(nombre, 100, 30, 15, 10, 3);
        this.usosMagia = 10; // Empieza con 10 materiales
        this.maxUsos = 10;
        this.libroDeHabilidades = new ArrayList<>();

        // Agregamos el arsenal de Smash
        this.libroDeHabilidades.add(new EspadaDiamante());
        this.libroDeHabilidades.add(new ArcoYFlecha());
        this.libroDeHabilidades.add(new BloqueDeTNT());
    }

    /**
     * Obtiene la lista de habilidades especiales de Steve.
     *
     * @return lista de habilidades disponibles.
     */
    public ArrayList<Hechizos> getLibroDeHabilidades() {
        return libroDeHabilidades;
    }

    /**
     * Activa la habilidad pasiva "Mesa de Crafteo": aumenta el ataque
     * en 5 puntos y recupera 5 materiales (sin superar el máximo),
     * mientras queden usos de la pasiva disponibles.
     */
    @Override
    public String activarSkill() {
        if (usosPasiva > 0) {
            System.out.println("¡Mesa de Crafteo! Steve mejora su equipo y recupera materiales.");
            this.ataque += 5;
            this.usosMagia += 5; // La pasiva ahora le da "munición" para sus habilidades
            if (this.usosMagia > maxUsos) this.usosMagia = maxUsos;
            this.usosPasiva--;
        } else {
            System.out.println("Te quedaste sin mesa de crafteo...");
        }
        return null;
    }
}

// --- HABILIDADES DE STEVE (Implementan tu interfaz Hechizos) ---

/**
 * Habilidad básica de Steve: ataque cuerpo a cuerpo con espada de
 * diamante. No consume materiales.
 */
class EspadaDiamante implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Espadazo"; }
    @Override
    public int getCosteMana() { return 0; } // Ataque básico, no cuesta materiales

    /**
     * Inflige daño igual al ataque base del lanzador.
     *
     * @param lanzador jugador que ejecuta el ataque.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        System.out.println("¡Steve ataca con su espada a " + objetivo.getNombre() + "!");
        objetivo.recibirDanio(lanzador.getAtaque());
    }
}

/**
 * Habilidad a distancia de Steve: disparo de arco y flecha. Consume 2
 * materiales.
 */
class ArcoYFlecha implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Arco y Flecha"; }
    @Override
    public int getCosteMana() { return 2; } // Cuesta 2 materiales

    /**
     * Inflige daño base de 40 más la inteligencia del lanzador.
     *
     * @param lanzador jugador que ejecuta el ataque.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int danio = 40 + lanzador.inteligencia;
        System.out.println("¡Steve dispara un flechazo certero a " + objetivo.getNombre() + "!");
        objetivo.recibirDanio(danio);
    }
}

/**
 * Habilidad de área de Steve: coloca un bloque de TNT que explota.
 * Consume 5 materiales.
 */
class BloqueDeTNT implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Bloque de TNT"; }
    @Override
    public int getCosteMana() { return 5; } // Cuesta 5 materiales

    /**
     * Inflige daño base de 80 más la inteligencia del lanzador.
     *
     * @param lanzador jugador que ejecuta el ataque.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int danio = 80 + lanzador.inteligencia;
        System.out.println("Steve coloca TNT... ¡Pssssssss... BOOM! Explosión sobre " + objetivo.getNombre());
        objetivo.recibirDanio(danio);
    }
}
