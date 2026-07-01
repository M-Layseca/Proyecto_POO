package com.example.pruebas_de_fx;
import java.util.ArrayList;

/**
 * Controla la lógica de un combate entre un Jugador y un grupo
 * de Enemigos, resolviendo los turnos de ataque de ambos bandos.
 */
public class Batalla {

    /** Enemigos activos que participan en la batalla. */
    private ArrayList<Enemigo> enemigos;

    /**
     * Crea una batalla con el grupo de enemigos indicado.
     *
     *sala nombre de la sala donde ocurre la batalla (no almacenado).
     *descripcion descripción de la sala (no almacenada).
     *enemigos lista de enemigos que participarán en la batalla.
     */
    public Batalla(String sala,String descripcion,ArrayList<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

    /**
     * Obtiene la lista de enemigos actualmente activos en la batalla.
     *
     * @return lista de enemigos vivos.
     */
    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    /**
     * Procesa el turno de ataque del jugador contra un enemigo objetivo.
     * Si el enemigo es derrotado, el jugador gana su experiencia y el
     * enemigo es eliminado de la lista de enemigos activos.
     */
    public String turnoJugador(Jugador j, Enemigo objetivo) {
        String resultado = "--- Turno de " + j.getNombre() + " ---\n";
        resultado += objetivo.recibirDanio(j.getAtaque()) + "\n";

        if (!objetivo.estaVivo()) {
            j.ganarExp(objetivo.getXpOtorgada());
            enemigos.remove(objetivo);
        }
        return resultado;
    }

    /**
     * Procesa el turno de ataque de todos los enemigos activos contra
     * el jugador, deteniéndose si el jugador es derrotado a mitad de turno.
     */
    public String turnoEnemigos(Jugador j) {
        StringBuilder sb = new StringBuilder("\n--- Turno Enemigo ---\n");
        for (Enemigo e : enemigos) {
            if (j.estaVivo()) {
                sb.append(e.atacar(j)).append("\n");
            }
        }
        return sb.toString();
    }
}
