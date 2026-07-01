package com.example.pruebas_de_fx;

/**
 * Clase de personaje jugable "Guerrero". Se caracteriza por un alto
 * valor de ataque y una habilidad pasiva que incrementa temporalmente
 * su poder de ataque.
 */
public class GUERRERO extends Jugador {

    /**
     * Crea un personaje Guerrero con sus estadísticas fijas por defecto.
     *
     * @param nombre nombre elegido para el personaje.
     */
    public GUERRERO(String nombre) {
        super(nombre, 100, 40, 10, 9, 2);
    }

    /**
     * Activa la habilidad especial del Guerrero ("Furia de Guerrero"),
     * que aumenta el ataque en 15 puntos mientras queden usos disponibles.
     */
    @Override
    public String activarSkill() {
        if (usosPasiva > 0) {
            System.out.println("¡Furia de Guerrero! El ataque aumenta temporalmente.");
            this.ataque += 15;
            this.usosPasiva--;
        } else {
            System.out.println("¡No te queda energía para más furia!");
        }
        return null;
    }
}
