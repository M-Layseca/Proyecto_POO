package com.example.pruebas_de_fx;

/**
 * Clase de personaje jugable "Tank". Se caracteriza por una alta
 * defensa y vida, junto con una habilidad de curación.
 */
public class TANK extends Jugador {

    /**
     * Crea un personaje Tank con sus estadísticas fijas por defecto.
     *
     * @param nombre nombre elegido para el personaje.
     */
    public TANK(String nombre) {
        super(nombre, 120, 30, 20, 7, 3);
    }

    /**
     * Activa la habilidad especial "Segundo Aire": recupera la mitad de
     * la vida faltante respecto del doble de la vida máxima, sin superar
     * la vida máxima, mientras queden usos de la pasiva disponibles.
     *
     * @return {@code null}, ya que el resultado se informa por consola.
     */
    @Override
    public String activarSkill() {
        if (usosPasiva > 0) {
            int cura = (vidaMax*2 - vida) / 2;
            this.vida += cura;

            if (this.vida > vidaMax) {
                this.vida = vidaMax;
            }

            this.usosPasiva--; // Gastamos el uso
            System.out.println("¡Segundo aire! Has recuperado " + cura + " HP.");
            System.out.println("Vida actual: " + vida + "/" + vidaMax);
        } else {
            System.out.println("Estás demasiado cansado para usar Segundo Aire...");
        }
        return null;
    }
}
