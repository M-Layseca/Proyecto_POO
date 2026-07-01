package com.example.pruebas_de_fx;

/**
 * Enemigo básico de tipo "Hollow". Es el enemigo más débil del juego,
 * con una probabilidad de fallar su propio ataque.
 */
public class Hollow extends Enemigo {

    /**
     * Crea un Hollow con sus estadísticas fijas por defecto.
     */
    public Hollow() {
        super("Hollow", 30, 15, 2, 0, 20);
    }

    /**
     * Ataca al jugador objetivo. Existe un 20% de probabilidad de que
     * el Hollow se tropiece y falle el ataque.
     *
     * @param objetivo jugador que recibe el ataque.
     * @return mensaje describiendo el resultado del ataque (fallo o daño infligido).
     */
    @Override
    public String atacar(Jugador objetivo) {
        int probAtaque = random.nextInt(100);
        if (probAtaque < 20) {
            return "¡El " + nombre + " se ha tropezado y falló el ataque!";
        } else {
            return objetivo.recibirDanio(this.ataque);
        }
    }
}
