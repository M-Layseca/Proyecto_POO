public class Hollow extends Enemigo {
    public Hollow() {
        super("Hollow", 30, 8, 2, 1);
    }

    @Override
    public void atacar(Jugador objetivo) {
        System.out.println("\n--- TURNO DEL ENEMIGO ---");
        System.out.println(this.nombre + " intenta lanzarte un tajo...");

        int probAtaque = random.nextInt(100); // 0 a 99

        if (probAtaque < 20) { // Un 20% real
            System.out.println("¡El Hollow se ha tropezado y ha fallado el ataque!");
        } else {
            objetivo.recibirDanio(this.ataque);
        }
    }
}
