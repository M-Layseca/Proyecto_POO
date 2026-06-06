package com.example.pruebas_de_fx;

public class Herobrine extends Enemigo {

    public Herobrine() {
        super("Herobrine", 666, 40, 20, 0, 500);
    }

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