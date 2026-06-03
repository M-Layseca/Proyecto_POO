package com.example.pruebas_de_fx;

import java.util.Random;

public class SalaTesoro extends Salas {
    public SalaTesoro() {
        super("Cofre Antiguo", "Un resplandor dorado ilumina esta habitación...");
    }

    @Override
    public void entrar(Jugador jugador) {
        System.out.println("\n--- " + nombreSala + " ---");
        System.out.println(descripcion);

        Random r = new Random();
        int premio = r.nextInt(4); // 0 a 3

        switch (premio) {
            case 0:
                jugador.vidaMax += 200;
                jugador.vida = jugador.vidaMax; // Curación completa al mejorar
                System.out.println("¡Has encontrado un Fragmento de Estus! Tu vida máxima aumenta.");
                break;
            case 1:
                jugador.ataque += 5;
                System.out.println("¡Has encontrado Titanita! Tu ataque ha mejorado.");
                break;
            case 2:
                jugador.estus += 2;
                System.out.println("¡Has encontrado Esencia de Fuego! Tienes 2 Estus más.");
                break;
            case 3:
                jugador.usosPasiva += 5;
                System.out.println("¡Has recuperado tu voluntad! +1 uso de pasiva.");
                break;
        }
    }
}