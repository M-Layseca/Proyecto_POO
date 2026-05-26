package com.example.pruebas_de_fx;

public class TANK extends Jugador {

    public TANK(String nombre) {
        super(nombre, 120, 30, 10, 7, 2);
    }

    @Override
    public void activarPasiva() {
        if (usosPasiva > 0) {
            int cura = (vidaMax - vida) / 2;
            this.vida += cura;

            // Aseguramos que no pase del máximo
            if (this.vida > vidaMax) {
                this.vida = vidaMax;
            }

            this.usosPasiva--; // Gastamos el uso
            System.out.println("¡Segundo aire! Has recuperado " + cura + " HP.");
            System.out.println("Vida actual: " + vida + "/" + vidaMax);
        } else {
            System.out.println("Estás demasiado cansado para usar Segundo Aire...");
        }
    }
}
