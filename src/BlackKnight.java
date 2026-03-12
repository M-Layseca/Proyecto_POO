public class BlackKnight extends Enemigo {
    int critico;
    public BlackKnight() {
        super("The Black Knight", 150, 25, 12, 1);
        this.critico = 20;
    }

    @Override
    public void atacar(Jugador objetivo) {
        System.out.println("\n--- TURNO DEL ENEMIGO ---");
        if (random.nextInt(100) < critico) {
            System.out.println(nombre + " saca su arma legendaria y....");
            System.out.println(nombre + " ha atacado con su Espada Maldita!");
            objetivo.recibirDanio(this.ataque * 2);
            this.critico = 20;
        } else {
            System.out.println(nombre + " ha atacado con su Espada!");
            objetivo.recibirDanio(this.ataque);
            this.critico += 5;
            System.out.println("(El caballero se ha vuelto mas peligroso...)");
        }
    }
}
