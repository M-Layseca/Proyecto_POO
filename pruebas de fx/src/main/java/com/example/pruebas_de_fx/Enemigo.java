package com.example.pruebas_de_fx;
import java.util.Random;

/**
 * Superclase abstracta de todos los enemigos del juego.
 * Extiende Entidad agregando la experiencia que otorga al ser
 * derrotado y un generador de números aleatorios utilizado por las
 * subclases para decidir el comportamiento de sus ataques.
 */
public abstract class Enemigo extends Entidad {

    /** Cantidad de experiencia que el enemigo otorga al jugador al ser derrotado. */
    protected int xpOtorgada;

    /** Generador de números aleatorios usado para la lógica de ataque de las subclases. */
    protected Random random = new Random();

    /**
     * Crea un enemigo con sus atributos de combate.
     *
     * @param nombre nombre del enemigo.
     * @param vidaMax vida máxima del enemigo.
     * @param ataque valor de ataque base.
     * @param defensa valor de defensa base.
     * @param usosPasiva usos disponibles de la habilidad pasiva (si aplica).
     * @param xpOtorgada experiencia otorgada al ser derrotado.
     */
    public Enemigo(String nombre, int vidaMax, int ataque, int defensa, int usosPasiva, int xpOtorgada) {
        super(nombre, vidaMax, ataque, defensa, usosPasiva);
        this.xpOtorgada = xpOtorgada;
    }

    /**
     * Obtiene la experiencia que otorga este enemigo al ser derrotado.
     *
     * @return cantidad de experiencia otorgada.
     */
    public int getXpOtorgada() { return xpOtorgada; }

    /**
     * Ejecuta el ataque del enemigo sobre un jugador objetivo.
     * Cada subclase concreta define su propia lógica de ataque.
     *
     * @param objetivo jugador que recibe el ataque.
     * @return mensaje descriptivo del resultado del ataque.
     */
    public abstract String atacar(Jugador objetivo);

    /**
     * Representación en texto del enemigo (su nombre).
     *
     * @return nombre del enemigo.
     */
    @Override
    public String toString() {
        return this.getNombre();
    }
}
