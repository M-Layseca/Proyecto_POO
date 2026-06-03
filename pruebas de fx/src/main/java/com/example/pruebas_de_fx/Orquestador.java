package com.example.pruebas_de_fx;

import java.util.ArrayList;
import java.util.Random;

public class Orquestador {
    private Random rand;

    public Orquestador() {
        this.rand = new Random();
    }

    // Genera una batalla aleatoria para ser usada por la Interfaz
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

    public Batalla generarJefeFinal() {
        ArrayList<Enemigo> bossList = new ArrayList<>();
        bossList.add(new Boss());
        return new Batalla("Altar de la Ceniza", "El destino del mundo se decide aquí.", bossList);
    }
}