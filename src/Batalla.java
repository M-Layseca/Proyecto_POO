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
            mostrarEstadoGeneral(jugador);

            // 1. TURNO DEL JUGADOR
            ejecutarTurnoJugador(jugador, sc);

            // 2. TURNO DE LOS ENEMIGOS (Solo si el jugador sigue vivo y quedan enemigos)
            if (jugador.estaVivo() && !enemigos.isEmpty()) {
                ejecutarTurnoEnemigos(jugador);
            }
        }

        anunciarResultado(jugador);
    }

    private void ejecutarTurnoJugador(Jugador jugador, Scanner sc) {
        System.out.println("\n[1] Atacar | [2] Pasiva | [3] Estus | [4] Magia");
        System.out.print("Acción: ");
        int accion = sc.nextInt();
        sc.nextLine(); // Limpieza de buffer para Windows 11

        switch (accion) {
            case 1 -> realizarAtaqueFisico(jugador, sc);
            case 2 -> jugador.activarPasiva();
            case 3 -> jugador.usarEstus();
            case 4 -> procesarMagia(jugador, sc);
            default -> System.out.println("Te bloqueas por el miedo y pierdes el turno...");
        }
    }

    private void realizarAtaqueFisico(Jugador jugador, Scanner sc) {
        int t = elegirObjetivo(sc);
        if (validarIndice(t)) {
            Enemigo objetivo = enemigos.get(t);
            System.out.println("\n¡Atacas a " + objetivo.nombre + "!");
            objetivo.recibirDanio(jugador.ataque);
            verificarMuerteEnemigo(t, jugador);
        }
    }

    private void procesarMagia(Jugador jugador, Scanner sc) {
        if (!(jugador instanceof MAGO mago)) {
            System.out.println("No tienes conocimientos arcano para usar magia.");
            return;
        }

        System.out.println("\n--- HECHIZOS (Maná: " + mago.usosMagia + ") ---");
        for (int i = 0; i < mago.getLibroDeHechizos().size(); i++) {
            Hechizos h = mago.getLibroDeHechizos().get(i);
            System.out.println((i + 1) + ". " + h.getNombreHechizo() + " (Coste: " + h.getCosteMana() + ")");
        }

        System.out.print("Elige (0 para cancelar): ");
        int sel = sc.nextInt() - 1;

        if (validarIndiceHechizo(sel, mago)) {
            Hechizos hechizo = mago.getLibroDeHechizos().get(sel);
            if (mago.usosMagia >= hechizo.getCosteMana()) {
                int t = elegirObjetivo(sc);
                if (validarIndice(t)) {
                    hechizo.aplicarEfecto(enemigos.get(t));
                    mago.usosMagia -= hechizo.getCosteMana();
                    verificarMuerteEnemigo(t, jugador);
                }
            } else {
                System.out.println("¡Sin maná!");
            }
        }
    }

    private void ejecutarTurnoEnemigos(Jugador jugador) {
        System.out.println("\n--- LOS ENEMIGOS CONTRATACAN ---");
        for (Enemigo e : enemigos) {
            if (jugador.estaVivo()) {
                e.atacar(jugador);
            }
        }
    }

    // --- MÉTODOS AUXILIARES (UTILERÍA) ---

    private int elegirObjetivo(Scanner sc) {
        System.out.println("\n¿A quién apuntas?");
        for (int i = 0; i < enemigos.size(); i++) {
            System.out.println((i + 1) + ". " + enemigos.get(i).nombre);
        }
        System.out.print("Selección: ");
        int sel = sc.nextInt() - 1;
        sc.nextLine();
        return sel;
    }

    private boolean validarIndice(int i) {
        return i >= 0 && i < enemigos.size();
    }

    private boolean validarIndiceHechizo(int i, MAGO m) {
        return i >= 0 && i < m.getLibroDeHechizos().size();
    }

    private void verificarMuerteEnemigo(int indice, Jugador jugador) {
        Enemigo caido = enemigos.get(indice);

        if (!caido.estaVivo()) {
            System.out.println("¡" + caido.nombre + " ha caído!");

            jugador.ganarExp(caido.getXpOtorgada());

            enemigos.remove(indice);

        }
    }

    private void anunciarResultado(Jugador j) {
        if (j.estaVivo()) System.out.println("\nVictoria. Has despejado la sala.");
        else System.out.println("\nHAS MUERTO. Tu viaje termina aquí.");
    }

    private void mostrarEstadoGeneral(Jugador jugador) {
        System.out.println("\n" + "-".repeat(15) + " ESTADO " + "-".repeat(15));
        System.out.println(String.format("%-15s", jugador.nombre) + dibujarBarra(jugador.vida, jugador.vidaMax) + " " + jugador.vida + " HP");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            System.out.println(String.format("%-15s", e.nombre + " " + (i+1)) + dibujarBarra(e.vida, e.vidaMax) + " " + e.vida + " HP");
        }
    }

    private String dibujarBarra(int actual, int max) {
        int bloques = 10;

        // Evitamos que el juego explote si algún enemigo tiene 0 de vida máxima por error
        if (max <= 0) max = 1;

        // Calculamos la proporción matemática
        int llenos = (int) ((double) actual / max * bloques);

        // EL ESCUDO DEFINITIVO: Obligamos a 'llenos' a quedarse siempre entre 0 y 10.
        llenos = Math.max(0, Math.min(bloques, llenos));

        return "[" + "#".repeat(llenos) + "-".repeat(bloques - llenos) + "]";
    }


}