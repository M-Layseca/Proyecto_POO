public abstract class Entidad {
    protected String nombre;
    protected int vida;
    protected int vidaMax;
    protected int ataque;
    protected int defensa;
    protected int usosPasiva;

    public Entidad(String nombre, int vidaMax, int ataque, int defensa, int usosPasiva) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.ataque = ataque;
        this.defensa = defensa;
        this.usosPasiva = usosPasiva;
    }

    public void recibirDanio(int danioRecibido) {
        int danioFinal = danioRecibido - this.defensa;
        if (danioFinal < 0) danioFinal = 0; // Para que no se cure al recibir golpes débiles

        this.vida -= danioFinal;
        System.out.println(nombre + " recibió " + danioFinal + " de daño.");

        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(nombre + " ha caído en combate...");
        }
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

}