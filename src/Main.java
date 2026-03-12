import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Jugador jugador = null;

        System.out.println("========================================");
        System.out.println("   BIENVENIDO A DARK SOULS: POO EDITION");
        System.out.println("========================================");

        // 1. SELECCIÓN DE PERSONAJE
        System.out.print("\nIntroduce tu nombre, valiente: ");
        String nombre = sc.nextLine();

        System.out.println("\nElige tu clase:");
        System.out.println("1. GUERRERO (Más ataque)");
        System.out.println("2. MAGO (Recupera usos mágicos)");
        System.out.println("3. TANK (Se cura con su pasiva)");
        System.out.print("Elección: ");
        int clase = sc.nextInt();

        switch (clase) {
            case 1: jugador = new GUERRERO(nombre); break;
            case 2: jugador = new MAGO(nombre); break;
            case 3: jugador = new TANK(nombre); break;
            default:
                System.out.println("Opción no válida. Serás un Guerrero por defecto.");
                jugador = new GUERRERO(nombre);
        }

        // 2. PREPARACIÓN DE LA BATALLA (Horda de enemigos)
        // Creamos una lista y metemos 2 Hollows para probar
        ArrayList<Enemigo> hordaDeHollows = new ArrayList<>();
        hordaDeHollows.add(new Hollow());
        hordaDeHollows.add(new Hollow());

        // 3. CREACIÓN DE LA SALA DE BATALLA
        Batalla sala1 = new Batalla(
                "Cementerio de Ceniza",
                "Un lugar frío lleno de restos de antiguos caballeros...",
                hordaDeHollows
        );

        // 4. ¡INICIO DEL JUEGO!
        System.out.println("\n--- COMIENZA TU VIAJE ---");
        sala1.entrar(jugador);

        // Mensaje final
        if (jugador.estaVivo()) {
            System.out.println("\nHas sobrevivido a tu primer encuentro. La hoguera te espera.");
        } else {
            System.out.println("\nTu rastro se pierde en la niebla...");
        }

        System.out.println("\nPresiona ENTER para cerrar el juego...");
        sc.nextLine(); // Limpiar buffer
        sc.nextLine(); // Esperar entrada
    }
}