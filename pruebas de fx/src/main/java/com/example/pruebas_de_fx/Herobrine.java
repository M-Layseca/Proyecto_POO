package com.example.pruebas_de_fx;

/**
 * Enemigo tipo boss "Herobrine". Cuenta con tres posibles ataques distintos,
 * elegidos aleatoriamente en cada turno, cada uno con un daño base
 * ligeramente diferente.
 */
public class Herobrine extends Enemigo {

    /**
     * Crea a Herobrine con sus estadísticas fijas por defecto.
     */
    public Herobrine() {
        super("Herobrine", 666, 40, 20, 0, 500);
    }

    /**
     * Ataca al jugador objetivo eligiendo aleatoriamente entre tres
     * variantes de ataque (rayo, TNT o pico de diamante), cada una con
     * un daño distinto.
     *
     * @param objetivo jugador que recibe el ataque.
     * @return mensaje describiendo el resultado del ataque.
     */
    @Override
    public String atacar(Jugador objetivo) {
        int suerte = random.nextInt(100);

        if (suerte < 30) {
            return "¡" + nombre + " te mira fijamente con sus ojos blancos!\n" +
                    "Un rayo cae directamente sobre ti.\n" +
                    objetivo.recibirDanio(this.ataque + 15);
        } else if (suerte < 60) {
            return nombre + " invoca un bloque de TNT a tus pies...\n" +
                    "¡BOOM!\n" +
                    objetivo.recibirDanio(this.ataque + 10);
        } else {
            return nombre + " te ataca brutalmente con un pico de diamante.\n" +
                    objetivo.recibirDanio(this.ataque);
        }
    }
}
