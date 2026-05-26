package com.example.pruebas_de_fx;
import java.util.ArrayList;

public class Batalla {
    private ArrayList<Enemigo> enemigos;

    public Batalla(String sala,String descripcion,ArrayList<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    public String turnoJugador(Jugador j, Enemigo objetivo) {
        String resultado = "--- Turno de " + j.getNombre() + " ---\n";
        resultado += objetivo.recibirDanio(j.getAtaque()) + "\n";

        if (!objetivo.estaVivo()) {
            j.ganarExp(objetivo.getXpOtorgada());
            enemigos.remove(objetivo);
        }
        return resultado;
    }

    public String turnoEnemigos(Jugador j) {
        StringBuilder sb = new StringBuilder("\n--- Turno Enemigo ---\n");
        for (Enemigo e : enemigos) {
            if (j.estaVivo()) {
                sb.append(e.atacar(j)).append("\n");
            }
        }
        return sb.toString();
    }
}