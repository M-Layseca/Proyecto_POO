package com.example.pruebas_de_fx;

import java.util.ArrayList;
import java.util.Random;

/**
 * Componente generativo encargado de crear el contenido aleatorio de la
 * progresión del juego: hordas de enemigos comunes y el combate final
 * contra el jefe.
 */
public class Orquestador {

    /** Generador de números aleatorios usado para decidir la composición de las hordas. */
    private Random rand;

    /**
     * Crea un nuevo orquestador con su generador aleatorio.
     */
    public Orquestador() {
        this.rand = new Random();
    }

    /**
     * Genera una batalla aleatoria de 1 a 3 enemigos, donde cada uno
     * tiene un 70% de probabilidad de ser un {@link Hollow} y un 30% de
     * ser un {@link BlackKnight}.
     *
     * @return una nueva {@link Batalla} con la horda generada.
     */
    public Batalla generarBatallaAleatoria() {
        ArrayList<Enemigo> horda = new ArrayList<>();
        int cantidadEnemigos = rand.nextInt(3) + 1;

        for (int i = 0; i < cantidadEnemigos; i++) {
            int dado = rand.nextInt(100);
            if (dado < 70) {
                horda.add(new Hollow());
            } else {
                horda.add(new BlackKnight());
            }
        }
        return new Batalla("Catacumbas", "Huele a humedad y acero oxidado.", horda);
    }

    /**
     * Genera la batalla final contra el {@link Boss} del juego.
     *
     * @return una nueva {@link Batalla} que contiene únicamente al jefe final.
     */
    public Batalla generarJefeFinal() {
        ArrayList<Enemigo> bossList = new ArrayList<>();
        bossList.add(new Boss());
        return new Batalla("Altar de la Ceniza", "El destino del mundo se decide aquí.", bossList);
    }
}
