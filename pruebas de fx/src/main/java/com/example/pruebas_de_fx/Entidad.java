package com.example.pruebas_de_fx;

/**
 * Superclase abstracta entre participante en el combate
 * (jugadores y enemigos). Encapsula los atributos comunes de vida,
 * ataque, defensa y la lógica básica de recibir daño.
 */
public abstract class Entidad {

    /** Nombre de la entidad. */
    protected String nombre;

    /** Puntos de vida actuales. */
    protected int vida;

    /** Puntos de vida máximos. */
    protected int vidaMax;

    /** Valor de ataque base. */
    protected int ataque;

    /** Valor de defensa base, usado para mitigar el daño recibido. */
    protected int defensa;

    /** Cantidad de usos disponibles de la habilidad pasiva/especial. */
    protected int usosPasiva;

    /**
     * Crea una entidad con sus atributos base. La vida actual se
     * inicializa igual a la vida máxima.
     *
     * @param nombre nombre de la entidad.
     * @param vidaMax vida máxima de la entidad.
     * @param ataque valor de ataque base.
     * @param defensa valor de defensa base.
     * @param usosPasiva usos disponibles de la habilidad pasiva.
     */
    public Entidad(String nombre, int vidaMax, int ataque, int defensa, int usosPasiva) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.ataque = ataque;
        this.defensa = defensa;
        this.usosPasiva = usosPasiva;
    }

    /**
     * Aplica daño a la entidad, mitigado por su defensa
     * (daño final = daño recibido − defensa, con un mínimo de 0).
     * Si la vida llega a 0, la entidad queda derrotada.
     *
     * @param danioRecibido daño bruto antes de aplicar la defensa.
     * @return mensaje describiendo el daño final recibido y, si corresponde,
     *         que la entidad ha caído.
     */
    public String recibirDanio(int danioRecibido) {
        int danioFinal = danioRecibido - this.defensa;
        if (danioFinal < 0) danioFinal = 0;

        this.vida -= danioFinal;
        if (this.vida <= 0) {
            this.vida = 0;
            return nombre + " recibió " + danioFinal + " de daño y ha caído.";
        }
        return nombre + " recibió " + danioFinal + " de daño.";
    }

    /**
     * Indica si la entidad sigue con vida.
     *
     * @return {@code true} si la vida actual es mayor que 0.
     */
    public boolean estaVivo() {
        return this.vida > 0;
    }

    /**
     * Obtiene el nombre de la entidad.
     *
     * @return nombre de la entidad.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene la vida actual de la entidad.
     *
     * @return puntos de vida actuales.
     */
    public int getVida() { return vida; }

    /**
     * Obtiene la vida máxima de la entidad.
     *
     * @return puntos de vida máximos.
     */
    public int getVidaMax() { return vidaMax; }

    /**
     * Obtiene el valor de ataque base de la entidad.
     *
     * @return valor de ataque.
     */
    public int getAtaque() { return ataque; }
}
