package com.example.pruebas_de_fx;

/**
 * Superclase abstracta de todos los personajes controlables por el
 * usuario. Extiende {@link Entidad} agregando el sistema de nivel,
 * experiencia, inteligencia y frascos de curación (estus).
 */
public abstract class Jugador extends Entidad{

    /** Nivel actual del jugador. */
    int nivel;

    /** Cantidad de frascos de Estus (curación) disponibles. */
    int estus;

    /** Experiencia acumulada en el nivel actual. */
    int xpActual;

    /** Puntos de inteligencia, usados como bonificación de daño en hechizos. */
    int inteligencia;

    /**
     * Crea un jugador con sus atributos base. El jugador inicia en
     * nivel 1, con 0 de experiencia y 3 frascos de Estus.
     *
     * @param nombre nombre del personaje.
     * @param vida vida máxima inicial.
     * @param ataque valor de ataque base.
     * @param defensa valor de defensa base.
     * @param inteligencia puntos de inteligencia del personaje.
     * @param usosPasiva usos disponibles de la habilidad pasiva/especial.
     */
    public Jugador(String nombre, int vida, int ataque, int defensa, int inteligencia, int usosPasiva) {
        super(nombre, vida, ataque, defensa, usosPasiva);
        this.nivel = 1;
        this.estus = 3;
        this.xpActual = 0;
        this.inteligencia = inteligencia;
    }

    /**
     * Activa la habilidad especial/pasiva propia de la clase de
     * personaje. Cada subclase concreta define su propio efecto.
     *
     * @return mensaje descriptivo del efecto (puede ser {@code null} si
     *         el resultado se informa únicamente por consola).
     */
    public abstract String activarSkill();

    /**
     * Obtiene la experiencia actual acumulada en el nivel presente.
     *
     * @return experiencia actual.
     */
    public int getXpActual() {
        return this.xpActual;
    }

    /**
     * Obtiene el nivel actual del jugador.
     *
     * @return nivel actual.
     */
    public int getNivel() {
        return this.nivel;
    }
    // Dentro de Jugador.java

    /**
     * Otorga experiencia al jugador. Si la experiencia acumulada alcanza
     * o supera 100, el jugador sube de nivel automáticamente.
     *
     * @param xpGanada cantidad de experiencia obtenida.
     * @return mensaje describiendo la experiencia ganada y, si corresponde,
     *         el aviso de subida de nivel.
     */
    public String ganarExp(int xpGanada) {
        this.xpActual += xpGanada;
        String msg = "¡Has ganado " + xpGanada + " Almas!\n";
        if (this.xpActual >= 100) {
            msg += subirNivel();
        }
        return msg;
    }

    /**
     * Procesa la subida de nivel: incrementa el nivel, descuenta 100
     * puntos de experiencia, aumenta la vida máxima en 20 y el ataque
     * en 5, y restaura la vida al máximo.
     *
     * @return mensaje describiendo la subida de nivel.
     */
    private String subirNivel() {
        this.nivel++;
        this.xpActual -= 100;
        this.vidaMax += 20;
        this.ataque += 5;
        this.vida = this.vidaMax;
        return "¡SUBIDA DE NIVEL! Ahora eres nivel " + nivel + ". Salud restaurada.";
    }

    /**
     * Consume un frasco de Estus para restaurar 30 puntos de vida
     * (sin superar la vida máxima), si quedan frascos disponibles.
     *
     * @return {@code null}, ya que el resultado se informa por consola.
     */
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
