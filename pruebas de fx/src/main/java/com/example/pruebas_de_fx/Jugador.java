package com.example.pruebas_de_fx;

public abstract class Jugador extends Entidad{
    int nivel;
    int estus;
    int xpActual;
    int inteligencia;

    public Jugador(String nombre, int vida, int ataque, int defensa, int inteligencia, int usosPasiva) {
        super(nombre, vida, ataque, defensa, usosPasiva);
        this.nivel = 1;
        this.estus = 3;
        this.xpActual = 0;
        this.inteligencia = inteligencia;
    }

    public abstract String activarSkill();

    public int getXpActual() {
        return this.xpActual;
    }
    public int getNivel() {
        return this.nivel;
    }
    // Dentro de Jugador.java
    public String ganarExp(int xpGanada) {
        this.xpActual += xpGanada;
        String msg = "¡Has ganado " + xpGanada + " Almas!\n";
        if (this.xpActual >= 100) {
            msg += subirNivel();
        }
        return msg;
    }

    private String subirNivel() {
        this.nivel++;
        this.xpActual -= 100;
        this.vidaMax += 20;
        this.ataque += 5;
        this.vida = this.vidaMax;
        return "¡SUBIDA DE NIVEL! Ahora eres nivel " + nivel + ". Salud restaurada.";
    }

    public String usarEstus() {
        if (estus <= 0) {
            System.out.println("¡No quedan frascos de Estus!");
        } else {
            this.estus--;
            this.vida += 30;

            if (this.vida > this.vidaMax) {
                this.vida = this.vidaMax;
            }

            System.out.println("Bebes un Estus... Salud actual: " + vida + "/" + vidaMax);
            System.out.println("Frascos restantes: " + estus);
        }
        return null;
    }

}
