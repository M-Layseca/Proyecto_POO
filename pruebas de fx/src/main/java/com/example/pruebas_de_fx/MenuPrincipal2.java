package com.example.pruebas_de_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class MenuPrincipal2 extends Application {
    private Stage primaryStage;
    private StackPane rootLayout; // Contenedor raíz para intercambiar pantallas con fluidez

    // Estado global del juego
    private Jugador jugador;
    private int salaActual = 1;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.rootLayout = new StackPane();

        // Establecer la ventana base
        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setTitle("Dark Souls: POO Edition");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Mostrar la primera pantalla: Menú de Inicio
        mostrarMenuInicio();
    }

    // --- Métodos de Navegación de Vistas ---

    public void mostrarMenuInicio() {
        VistaMenuInicio menuInicio = new VistaMenuInicio(this);
        rootLayout.getChildren().setAll(menuInicio.getLayout());
    }

    public void mostrarCreacionPersonaje() {
        VistaCreacionPersonaje creacionPersonaje = new VistaCreacionPersonaje(this);
        rootLayout.getChildren().setAll(creacionPersonaje.getLayout());
    }

    public void iniciarSimulacionDeJuego(Jugador jugadorCreado) {
        this.jugador = jugadorCreado;
        this.salaActual = 1;
        irASiguienteSala();
    }

    public void irASiguienteSala() {
        if (!jugador.estaVivo()) {
            System.out.println("El jugador ha muerto. Volviendo al menú...");
            mostrarMenuInicio();
            return;
        }

        // Instancia de Random para las probabilidades
        Random rand = new Random();

        // 1. CONDICIÓN DE JEFE: Si llega a la sala 5 (o la que definas como MAX_SALAS), combate fijo contra el Boss
        if (salaActual == 10) {
            System.out.println("¡Avanzando a la Sala Final: El Altar de la Ceniza!");
            ArrayList<Enemigo> enemigosBoss = new ArrayList<>();
            enemigosBoss.add(new Boss()); // Tu jefe final fixed

            Batalla batallaBoss = new Batalla("Altar de la Ceniza", "El fin de tu viaje se decide aquí.", enemigosBoss);
            InterfazdeCombate pantallaCombate = new InterfazdeCombate(jugador, batallaBoss, this);
            rootLayout.getChildren().setAll(pantSelf(pantallaCombate.getLayout()));

            salaActual++; // Avanza para que al ganar termine el juego
            return;
        }

        // Si ya pasó al jefe final y ganó
        if (salaActual > 10) {
            mostrarMenuInicio();
            return;
        }

        System.out.println("Avanzando a la Sala: " + salaActual);

        // 2. DETERMINAR TIPO DE SALA (40% Tesoro, 60% Combate)
        int probabilidadSala = rand.nextInt(100); // Genera número entre 0 y 99

        if (probabilidadSala < 40) {
            // --- 40% PROBABILIDAD: SALA DE TESORO ---
            VistaSalaTesoro pantallaTesoro = new VistaSalaTesoro(jugador, this);
            rootLayout.getChildren().setAll(pantallaTesoro.getLayout());
        } else {
            // --- 60% PROBABILIDAD: SALA DE COMBATE ALEATORIO ---
            ArrayList<Enemigo> enemigosAleatorios = new ArrayList<>();

            // Determinar cuántos enemigos habrá (Mínimo 1, Máximo 3)
            int cantidadEnemigos = rand.nextInt(3) + 1;

            for (int i = 0; i < cantidadEnemigos; i++) {
                // Elige qué enemigo poner (ej: 70% Hollow, 30% BlackKnight)
                int tipoEnemigo = rand.nextInt(100);
                if (tipoEnemigo < 70) {
                    enemigosAleatorios.add(new Hollow());
                } else {
                    enemigosAleatorios.add(new BlackKnight());
                }
            }

            // Crear la batalla con la horda generada dinámicamente
            Batalla nuevaBatalla = new Batalla("Sala " + salaActual, "Una húmeda mazmorra plagada de peligros.", enemigosAleatorios);
            InterfazdeCombate pantallaCombate = new InterfazdeCombate(jugador, nuevaBatalla, this);

            // 2. Obtenemos su contenedor raíz (el StackPane con el fondo)
            javafx.scene.layout.StackPane layoutCombate = pantallaCombate.getLayout();

            // 3. Forzamos a que el layout de combate llene la ventana de 800x600
            layoutCombate.setPrefSize(800, 600);

            // 4. Limpiamos el rootLayout principal e inyectamos la nueva escena de combate
            rootLayout.getChildren().clear();
            rootLayout.getChildren().add(layoutCombate);
        }

        // Incrementamos el contador para la próxima habitación
        salaActual++;
    }

    // Método auxiliar por si necesitas mapear layouts de forma limpia
    private javafx.scene.Parent pantSelf(javafx.scene.Parent p) {
        return p;
    }

    public static void main(String[] args) {
        launch(args);
    }
}