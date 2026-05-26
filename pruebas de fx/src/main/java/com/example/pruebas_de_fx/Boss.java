package com.example.pruebas_de_fx;

public class Boss extends Enemigo {
    public Boss() {
        super("El Gran Molinillo El terrible", 500, 35, 5, 0, 250);
    }

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