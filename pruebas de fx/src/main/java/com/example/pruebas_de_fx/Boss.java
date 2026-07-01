package com.example.pruebas_de_fx;

/**
 * Enemigo de tipo jefe. Posee estadísticas muy superiores a los
 * enemigos comunes y una probabilidad muy baja de infligir un ataque
 * devastador de un solo golpe.
 */
public class Boss extends Enemigo {

    /**
     * Crea el Boss con sus estadísticas fijas por defecto.
     */
    public Boss() {
        super("El Gran Molinillo El terrible", 500, 35, 5, 0, 250);
    }

    /**
     * Ataca al jugador objetivo. Existe una probabilidad de 1/1000 de
     * desatar un ataque especial que inflige daño extremo; en caso
     * contrario realiza un ataque normal con bolas de fuego.
     *
     * @param objetivo jugador que recibe el ataque.
     * @return mensaje describiendo el resultado del ataque.
     */
    @Override
    public String atacar(Jugador objetivo) {
        int suerte = random.nextInt(1000);

        if (suerte == 777) {
            return "¡HA OCURRIDO UNA CALAMIDAD!\n" +
                    nombre + " usa magia del Dios de la Muerte.\n" +
                    objetivo.recibirDanio(999999);
        } else {
            return nombre + " ataca con sus bolas de fuego.\n" +
                    objetivo.recibirDanio(this.ataque);
        }
    }
}
