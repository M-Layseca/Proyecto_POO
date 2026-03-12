import java.util.ArrayList;
import java.util.Scanner;

public class Batalla extends Salas {
    private ArrayList<Enemigo> enemigos;

    public Batalla(String nombre, String desc, ArrayList<Enemigo> enemigos) {
        super(nombre, desc);
        this.enemigos = enemigos;
    }

    @Override
    public void entrar(Jugador jugador) {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("   ZONA: " + nombreSala.toUpperCase());
        System.out.println("   " + descripcion);
        System.out.println("=".repeat(45));
        iniciarCombate(jugador);
    }

    private void iniciarCombate(Jugador jugador) {
        Scanner sc = new Scanner(System.in);

        while (jugador.estaVivo() && !enemigos.isEmpty()) {
            // --- MOSTRAR ESTADO DE TODOS ---
            mostrarEstadoGeneral(jugador);

            // --- TURNO DEL JUGADOR ---
            System.out.println("\n[1] Atacar | [2] Pasiva | [3] Estus");
            System.out.print("Acción: ");
            int accion = sc.nextInt();

            switch (accion) {
                case 1:
                    // SELECCIÓN DE OBJETIVO
                    System.out.println("\n¿A quién quieres atacar?");
                    for (int i = 0; i < enemigos.size(); i++) {
                        System.out.println((i + 1) + ". " + enemigos.get(i).nombre + " " + (i + 1));
                    }
                    System.out.print("Elige un número: ");
                    int targetIndex = sc.nextInt() - 1;

                    if (targetIndex >= 0 && targetIndex < enemigos.size()) {
                        Enemigo objetivo = enemigos.get(targetIndex);
                        System.out.println("\n¡Lanzas un golpe contra " + objetivo.nombre + " " + (targetIndex + 1) + "!");
                        objetivo.recibirDanio(jugador.ataque);

                        if (!objetivo.estaVivo()) {
                            enemigos.remove(targetIndex);
                        }
                    } else {
                        System.out.println("Te confundes y golpeas al aire...");
                    }
                    break;
                case 2:
                    jugador.activarPasiva();
                    break;
                case 3:
                    jugador.usarEstus();
                    break;
            }

            // --- TURNO DE LOS ENEMIGOS ---
            if (!enemigos.isEmpty() && jugador.estaVivo()) {
                System.out.println("\n--- LOS ENEMIGOS CONTRATACAN ---");
                for (int i = 0; i < enemigos.size(); i++) {
                    if (jugador.estaVivo()) {
                        System.out.print("[" + enemigos.get(i).nombre + " " + (i + 1) + "]: ");
                        enemigos.get(i).atacar(jugador);
                    }
                }
            }
        }
    }

    // MÉTODO PARA MOSTRAR LAS BARRAS DE VIDA
    private void mostrarEstadoGeneral(Jugador jugador) {
        System.out.println("\n" + "-".repeat(15) + " ESTADO " + "-".repeat(15));

        // Vida del Jugador
        System.out.println(String.format("%-15s", jugador.nombre) + dibujarBarra(jugador.vida, jugador.vidaMax) + " " + jugador.vida + " HP");

        System.out.println("\nENEMIGOS:");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            String nombreConNumero = e.nombre + " " + (i + 1);
            System.out.println(String.format("%-15s", nombreConNumero) + dibujarBarra(e.vida, e.vidaMax) + " " + e.vida + " HP");
        }
        System.out.println("-".repeat(38));
    }

    // LÓGICA PARA DIBUJAR LA BARRA [#####-----]
    private String dibujarBarra(int actual, int max) {
        int totalBloques = 10;
        // Calculamos cuántos bloques llenar (proporción)
        int llenos = (int) ((double) actual / max * totalBloques);
        if (llenos < 0) llenos = 0;

        String barra = "[";
        for (int i = 0; i < totalBloques; i++) {
            if (i < llenos) barra += "#"; // Parte llena
            else barra += "-";           // Parte vacía
        }
        return barra + "]";
    }
}