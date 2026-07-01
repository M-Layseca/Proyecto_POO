public class Boss extends Enemigo {
    public Boss() {
        super("El Gran Molinillo El terrible", 500, 35, 5, 0, 250 );
    }

    @Override
    public void atacar(Jugador objetivo){
        System.out.println("\n--- TURNO DEL ENEMIGO ---");

        int suerte = random.nextInt(1000);

        if (suerte == 666){
            System.out.println("Ha ocurrido una calamidad...");
            System.out.println(nombre + " ha usado la margia arcana del Dios de la Muerte");
            objetivo.recibirDanio(9999);
        } else {
            System.out.println(nombre + " ataca con sus bola do fogo");
            objetivo.recibirDanio(this.ataque);
        }
    }
}
