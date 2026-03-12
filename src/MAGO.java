public class MAGO extends Jugador {
    int usosMagia;
    int maxUsos;

    public MAGO(String nombre) {
        super(nombre, 70, 20, 2,2);
        this.usosMagia = 10;
        this.maxUsos = 10;
    }

    @Override
    public void activarPasiva() {
        if (usosPasiva > 0) {
            this.usosMagia += 5;
            if (this.usosMagia > maxUsos) {
                this.usosMagia = maxUsos;
            }
            this.usosPasiva--;
            System.out.println("¡Meditación! Magia actual: " + usosMagia);
        } else {
            System.out.println("Tu mente está agotada para meditar...");
        }
    }
}
