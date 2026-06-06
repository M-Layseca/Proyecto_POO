package com.example.pruebas_de_fx;

import java.util.ArrayList;

public class Steve extends Jugador {
    public int usosMagia; // En Steve esto representará sus "Materiales"
    public int maxUsos;
    private ArrayList<Hechizos> libroDeHabilidades;

    public Steve(String nombre) {
        super(nombre, 100, 30, 15, 10, 3);
        this.usosMagia = 10; // Empieza con 10 materiales
        this.maxUsos = 10;
        this.libroDeHabilidades = new ArrayList<>();

        // Agregamos el arsenal de Smash
        this.libroDeHabilidades.add(new EspadaDiamante());
        this.libroDeHabilidades.add(new ArcoYFlecha());
        this.libroDeHabilidades.add(new BloqueDeTNT());
    }

    public ArrayList<Hechizos> getLibroDeHabilidades() {
        return libroDeHabilidades;
    }

    @Override
    public String activarSkill() {
        if (usosPasiva > 0) {
            System.out.println("¡Mesa de Crafteo! Steve mejora su equipo y recupera materiales.");
            this.ataque += 5;
            this.usosMagia += 5; // La pasiva ahora le da "munición" para sus habilidades
            if (this.usosMagia > maxUsos) this.usosMagia = maxUsos;
            this.usosPasiva--;
        } else {
            System.out.println("Te quedaste sin mesa de crafteo...");
        }
        return null;
    }
}

// --- HABILIDADES DE STEVE (Implementan tu interfaz Hechizos) ---

class EspadaDiamante implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Espadazo"; }
    @Override
    public int getCosteMana() { return 0; } // Ataque básico, no cuesta materiales

    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        System.out.println("¡Steve ataca con su espada a " + objetivo.getNombre() + "!");
        objetivo.recibirDanio(lanzador.getAtaque());
    }
}

class ArcoYFlecha implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Arco y Flecha"; }
    @Override
    public int getCosteMana() { return 2; } // Cuesta 2 materiales

    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int danio = 40 + lanzador.inteligencia;
        System.out.println("¡Steve dispara un flechazo certero a " + objetivo.getNombre() + "!");
        objetivo.recibirDanio(danio);
    }
}

class BloqueDeTNT implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Bloque de TNT"; }
    @Override
    public int getCosteMana() { return 5; } // Cuesta 5 materiales

    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int danio = 80 + lanzador.inteligencia;
        System.out.println("Steve coloca TNT... ¡Pssssssss... BOOM! Explosión sobre " + objetivo.getNombre());
        objetivo.recibirDanio(danio);
    }
}