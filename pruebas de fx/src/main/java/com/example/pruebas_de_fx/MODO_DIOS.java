package com.example.pruebas_de_fx;

public class MODO_DIOS extends Jugador {

    public MODO_DIOS(String nombre) {
        super(nombre + " El Administrador", 9999, 9999, 0, 9999, 10);
    }

    @Override
    public void activarPasiva(){
        System.out.println("Expansion de dominio... Nah I'd win");
        this.vida = this.vidaMax * 2;
        this.ataque = this.ataque * 5;
    }
}
