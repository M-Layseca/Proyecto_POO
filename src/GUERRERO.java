public class GUERRERO extends Jugador {
    public GUERRERO(String nombre) {
        super(nombre, 100, 15, 5, 2);
    }

    @Override
    public void activarPasiva() {
        if (usosPasiva > 0) {
            System.out.println("¡Furia de Guerrero! El ataque aumenta temporalmente.");
            this.ataque += 5;
            this.usosPasiva--;
        } else {
            System.out.println("¡No te queda energía para más furia!");
        }
    }
}
