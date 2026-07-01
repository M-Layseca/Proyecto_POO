public abstract class Jugador extends Entidad{
    int nivel;
    int estus;
    int xpActual;

    public Jugador(String nombre, int vida, int ataque, int defensa, int usosPasiva) {
        super(nombre, vida, ataque, defensa, usosPasiva);
        this.nivel = 1;
        this.estus = 3;
        this.xpActual = 0;
    }

    public abstract void activarPasiva();

    public void ganarExp(int xpGanada) {
        this.xpActual += xpGanada;
        System.out.println("¡Has ganando " + xpGanada +" Almas! (Total: " + xpActual + "/100)" );

        while(this.xpActual >= 100){
            subirNivel();
        }
    }

    private void subirNivel() {
        this.nivel++;
        this.xpActual -= 100;

        this.vidaMax += 20;
        this.ataque += 5;
        this.defensa += 2;

        this.vida = this.vidaMax;

        System.out.println("\n=========================================");
        System.out.println("¡ENHORABUENA! Has subido al Nivel " + this.nivel);
        System.out.println("Tus atributos han aumentado y tu salud se ha restaurado.");
        System.out.println("Ataque: " + this.ataque + " | Defensa: " + this.defensa + " | Vida: " + this.vidaMax);
        System.out.println("=========================================\n");
    }

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
