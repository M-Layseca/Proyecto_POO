package com.example.pruebas_de_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;

import static com.example.pruebas_de_fx.SoundManager.playBackgroundMusic;

/**
 * Clase principal de la aplicación JavaFX y controlador central del
 * flujo del juego. Se encarga de inicializar la ventana, alternar
 * entre las distintas vistas (menú, creación de personaje, salas de
 * tesoro y combates) y de generar la progresión de salas hasta llegar
 * al jefe final.
 */
public class MenuPrincipal2 extends Application {

    /** Ventana principal de la aplicación. */
    private Stage primaryStage;

    /** Contenedor raíz donde se intercambian las distintas vistas. */
    private StackPane rootLayout;

    /** Personaje jugador de la partida en curso. */
    private Jugador jugador;

    /** Número de la sala actual dentro de la progresión (1 a 10, 10 es el jefe final). */
    private int salaActual = 1;

    /** Campo declarado pero no utilizado (referencia residual de refactorización). */
    private SoundManager playBackgroundMusic;

    /**
     * Punto de entrada de JavaFX. Inicializa la ventana principal,
     * reproduce la música del menú y muestra la pantalla de inicio.
     *
     * @param primaryStage ventana principal provista por JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.rootLayout = new StackPane();

        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setTitle("Dark Souls: POO Edition");
        primaryStage.setScene(scene);
        primaryStage.show();
        playBackgroundMusic("menu_theme.wav");
        mostrarMenuInicio();
    }

    /**
     * Muestra la pantalla del menú de inicio, reemplazando el
     * contenido actual del layout raíz.
     */
    public void mostrarMenuInicio() {
        VistaMenuInicio menuInicio = new VistaMenuInicio(this);
        rootLayout.getChildren().setAll(menuInicio.getLayout());
    }

    /**
     * Muestra la pantalla de creación de personaje, reemplazando el
     * contenido actual del layout raíz.
     */
    public void mostrarCreacionPersonaje() {
        VistaCreacionPersonaje creacionPersonaje = new VistaCreacionPersonaje(this);
        rootLayout.getChildren().setAll(creacionPersonaje.getLayout());
    }

    /**
     * Inicia una nueva partida con el jugador recién creado, reiniciando
     * la progresión de salas desde la sala 1.
     *
     * @param jugadorCreado personaje jugador con el que se iniciará la partida.
     */
    public void iniciarSimulacionDeJuego(Jugador jugadorCreado) {
        this.jugador = jugadorCreado;
        this.salaActual = 1;
        irASiguienteSala();
    }

    /**
     * Avanza a la siguiente sala de la progresión del juego. Si el
     * jugador ha muerto, vuelve al menú de inicio. Al llegar a la sala
     * 10 genera el combate contra el jefe final (Herobrine si el
     * jugador es {@link Steve}, o el {@link Boss} genérico en caso
     * contrario). Para las salas intermedias, decide aleatoriamente
     * entre una sala de tesoro (40% de probabilidad) o una sala de
     * combate contra una horda generada aleatoriamente.
     */
    public void irASiguienteSala() {
        if (!jugador.estaVivo()) {
            System.out.println("El jugador ha muerto. Volviendo al menú...");
            mostrarMenuInicio();
            return;
        }

        Random rand = new Random();

        if (salaActual == 10) {
            System.out.println("¡Avanzando a la Sala Final: El Altar de la Ceniza!");
            ArrayList<Enemigo> enemigosBoss = new ArrayList<>();

            if (jugador instanceof Steve) {
                System.out.println("La niebla se espesa... Una presencia siniestra observa a Steve.");
                enemigosBoss.add(new Herobrine()); // Herobrine
            } else {
                enemigosBoss.add(new Boss()); // El Gran Molinillo El terrible
            }
            Batalla batallaBoss = new Batalla("Altar de la Ceniza", "El fin de tu viaje se decide aquí.", enemigosBoss);
            InterfazdeCombate pantallaCombate = new InterfazdeCombate(jugador, batallaBoss, this);
            rootLayout.getChildren().setAll(pantSelf(pantallaCombate.getLayout()));

            salaActual++;
            return;
        }

        if (salaActual > 10) {
            mostrarMenuInicio();
            return;
        }

        System.out.println("Avanzando a la Sala: " + salaActual);

        int probabilidadSala = rand.nextInt(100);

        if (probabilidadSala < 40) {
            playBackgroundMusic("treasure_theme.wav");
            VistaSalaTesoro pantallaTesoro = new VistaSalaTesoro(jugador, this);
            rootLayout.getChildren().setAll(pantallaTesoro.getLayout());

        } else {

            playBackgroundMusic("battle_theme.wav");

            ArrayList<Enemigo> enemigosAleatorios = new ArrayList<>();

            int cantidadEnemigos = rand.nextInt(3) + 1;

            for (int i = 0; i < cantidadEnemigos; i++) {
                int tipoEnemigo = rand.nextInt(100);
                if (tipoEnemigo < 70) {
                    enemigosAleatorios.add(new Hollow());
                } else {
                    enemigosAleatorios.add(new BlackKnight());
                }
            }

            Batalla nuevaBatalla = new Batalla("Sala " + salaActual, "Una húmeda mazmorra plagada de peligros.", enemigosAleatorios);
            InterfazdeCombate pantallaCombate = new InterfazdeCombate(jugador, nuevaBatalla, this);

            javafx.scene.layout.StackPane layoutCombate = pantallaCombate.getLayout();

            layoutCombate.setPrefSize(800, 600);

            rootLayout.getChildren().clear();
            rootLayout.getChildren().add(layoutCombate);
        }

        salaActual++;
    }

    /**
     * Función identidad utilizada como envoltorio al insertar un nodo
     * en el layout raíz (no transforma el nodo recibido).
     *
     * @param p nodo a devolver sin modificaciones.
     * @return el mismo nodo recibido como parámetro.
     */
    private javafx.scene.Parent pantSelf(javafx.scene.Parent p) {
        return p;
    }

    /**
     * Punto de entrada de la aplicación. Lanza la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        launch(args);
    }
}