package com.example.pruebas_de_fx;

public class Hollow extends Enemigo {
    public Hollow() {
        super("Hollow", 30, 15, 2, 0, 20);
    }

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
