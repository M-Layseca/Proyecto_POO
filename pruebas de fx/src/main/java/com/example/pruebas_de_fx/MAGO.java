package com.example.pruebas_de_fx;

import java.util.ArrayList;

public class MAGO extends Jugador {
    public int usosMagia; // Ya los tenías definidos
    public int maxUsos;
    private ArrayList<Hechizos> libroDeHechizos;

    public MAGO(String nombre) {
        super(nombre, 70, 20, 2, 15, 2);
        this.usosMagia = 10;
        this.maxUsos = 10;
        this.libroDeHechizos = new ArrayList<>();
        this.libroDeHechizos.add(new BoladeFuego());
        this.libroDeHechizos.add(new FlechadeAlma());
        this.libroDeHechizos.add(new LanzaRelampago());// El mago empieza con este
    }

    public ArrayList<Hechizos> getLibroDeHechizos() {
        return libroDeHechizos;
    }

    @Override
    public void activarPasiva() {
        if (usosPasiva > 0) {
            this.usosMagia += 5;
            if (this.usosMagia > maxUsos) this.usosMagia = maxUsos;
            this.usosPasiva--;
            System.out.println("¡Meditación! Magia actual: " + usosMagia + "/" + maxUsos);
        } else {
            System.out.println("Tu mente está agotada para meditar...");
        }
    }
}