public abstract class Jugador extends Entidad{
    int nivel;
    int estus;

    public Jugador(String nombre, int vida, int ataque, int defensa, int usosPasiva) {
        super(nombre, vida, ataque, defensa, usosPasiva);
        this.nivel = 1;
    }

    public abstract void activarPasiva();

    public void usarEstus() {
        if (estus <= 0) {
            System.out.println("¡No quedan frascos de Estus!");
        } else {
            this.estus--;
            this.vida += 30;

            // Comprobación
            if (this.vida > this.vidaMax) {
                this.vida = this.vidaMax;
            }

            System.out.println("Bebes un Estus... Salud actual: " + vida + "/" + vidaMax);
            System.out.println("Frascos restantes: " + estus);
        }
    }

}
