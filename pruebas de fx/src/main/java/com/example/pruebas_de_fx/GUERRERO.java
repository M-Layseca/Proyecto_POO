package com.example.pruebas_de_fx;

public class GUERRERO extends Jugador {
    public GUERRERO(String nombre) {
        super(nombre, 100, 40, 10, 9, 2);
    }

    @Override
    public String activarSkill() {
        if (usosPasiva > 0) {
            System.out.println("¡Furia de Guerrero! El ataque aumenta temporalmente.");
            this.ataque += 15;
            this.usosPasiva--;
        } else {
            System.out.println("¡No te queda energía para más furia!");
        }
        return null;
    }
}
