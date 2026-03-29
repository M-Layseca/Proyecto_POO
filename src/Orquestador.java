import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Orquestador {
    private Random rand;

    public Orquestador() {
        this.rand = new Random();
    }

    // 1. EL INICIO (Lo que llama el Main)
    public void iniciarJuego() {
        Jugador protagonista = menuCreacionPersonaje();

        // Si el jugador no canceló o cerró, empezamos el recorrido
        if (protagonista != null) {
            iniciarAventura(protagonista);
        }
    }

    // 2. EL MENÚ DE CREACIÓN
    private Jugador menuCreacionPersonaje() {
        Scanner sc = new Scanner(System.in);
        Jugador nuevoJugador = null;

        System.out.println("========================================");
        System.out.println("   BIENVENIDO A DARK SOULS: POO EDITION");
        System.out.println("========================================");

        System.out.print("\nIntroduce tu nombre, Latente: ");
        String nombre = sc.nextLine();

        System.out.println("\nElige tu clase:");
        System.out.println("1. GUERRERO (Fuerza bruta y más vida)");
        System.out.println("2. MAGO (Conocimiento arcano y hechizos)");
        System.out.println("3. TANK (Defensa impenetrable)");
        System.out.print("Elección: ");

        int clase = sc.nextInt();
        sc.nextLine(); // Limpieza de buffer

        switch (clase) {
            case 1 -> nuevoJugador = new GUERRERO(nombre);
            case 2 -> nuevoJugador = new MAGO(nombre);
            case 3 -> nuevoJugador = new TANK(nombre);
            case 88224646 -> nuevoJugador = new MODO_DIOS(nombre);
            default -> {
                System.out.println("Opción inválida. Los dioses te han hecho Guerrero por defecto.");
                nuevoJugador = new GUERRERO(nombre);
            }
        }

        System.out.println("\n¡Te damos la bienvenida, " + nuevoJugador.nombre + "!");
        return nuevoJugador;
    }

    // 3. EL BUCLE DEL RECORRIDO (Las 5 salas)
    private void iniciarAventura(Jugador jugador) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nAtraviesas la niebla y entras a la mazmorra...");

        for (int x = 1; x <= 5; x++) {
            if (!jugador.estaVivo()) {
                break;
            }

            if (x == 5) {
                System.out.println("\n[SALA " + x + "/5] Sientes una presencia abrumadora...");
                Batalla salaBoss = generarBossFight();
                salaBoss.entrar(jugador);
            } else {
                System.out.println("\n[SALA " + x + "/5] Avanzas por un pasillo oscuro...");
                Batalla salaNormal = generarSalaAleatoria();
                salaNormal.entrar(jugador);
            }

            if (jugador.estaVivo() && x < 5) {
                System.out.println("\nTomas un respiro. (Presiona ENTER para avanzar a la siguiente sala)");
                sc.nextLine();
            }
        }

        if (jugador.estaVivo()) {
            System.out.println("\n¡VICTORIA! Has conquistado la mazmorra y encendido la hoguera primordial.");
        }
    }

    // 4. GENERADORES DE SALAS
    private Batalla generarSalaAleatoria() {
        ArrayList<Enemigo> horda = new ArrayList<>();
        int cantidadEnemigos = rand.nextInt(3) + 1;

        for (int i = 0; i < cantidadEnemigos; i++) {
            int dado = rand.nextInt(100);
            if (dado < 70) {
                horda.add(new Hollow());
            } else {
                horda.add(new BlackKnight());
            }
        }
        return new Batalla("Catacumbas Aleatorias", "Huele a humedad y acero oxidado.", horda);
    }

    private Batalla generarBossFight() {
        ArrayList<Enemigo> bossList = new ArrayList<>();
        bossList.add(new Boss()); // Ojo aquí: asegúrate de tener una clase Boss creada.
        return new Batalla("Horno de la Primera Llama", "No hay escapatoria.", bossList);
    }
} // <--- Este es el ÚNICO corchete final que cierra toda la clase Orquestador