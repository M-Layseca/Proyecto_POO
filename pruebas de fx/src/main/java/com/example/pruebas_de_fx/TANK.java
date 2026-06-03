package com.example.pruebas_de_fx;

public class TANK extends Jugador {

    public TANK(String nombre) {
        super(nombre, 120, 30, 20, 7, 3);
    }

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
