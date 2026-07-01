package com.example.pruebas_de_fx;

/**
 * Personaje especial de administrador ("Modo Dios"), pensado como
 * herramienta de depuración/pruebas. Posee estadísticas extremadamente
 * altas y una habilidad que duplica su vida y multiplica su ataque.
 */
public class MODO_DIOS extends Jugador {

    /**
     * Crea un personaje en Modo Dios con estadísticas de prueba extremas.
     *
     * @param nombre nombre base del personaje (se le añade el sufijo "El Administrador").
     */
    public MODO_DIOS(String nombre) {
        super(nombre + " El Administrador", 9999, 9999, 0, 9999, 10);
    }

    /**
     * Activa la habilidad especial: duplica la vida máxima actual y
     * multiplica el ataque por 5.
     *
     */
    @Override
    public String activarSkill(){
        System.out.println("Expansion de dominio... Nah I'd win");
        this.vida = this.vidaMax * 2;
        this.ataque = this.ataque * 5;
        return null;
    }
}
