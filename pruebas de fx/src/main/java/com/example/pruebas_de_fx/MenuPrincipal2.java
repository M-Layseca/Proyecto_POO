package com.example.pruebas_de_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class MenuPrincipal2 extends Application {
    private Stage primaryStage;
    private StackPane rootLayout;
    private Jugador jugador;
    private int salaActual = 1;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.rootLayout = new StackPane();

        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setTitle("Dark Souls: POO Edition");
        primaryStage.setScene(scene);
        primaryStage.show();

        mostrarMenuInicio();
    }

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

        Random rand = new Random();

        if (salaActual == 10) {
            System.out.println("¡Avanzando a la Sala Final: El Altar de la Ceniza!");
            ArrayList<Enemigo> enemigosBoss = new ArrayList<>();
            enemigosBoss.add(new Boss());

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

            VistaSalaTesoro pantallaTesoro = new VistaSalaTesoro(jugador, this);
            rootLayout.getChildren().setAll(pantallaTesoro.getLayout());
        } else {

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

    private javafx.scene.Parent pantSelf(javafx.scene.Parent p) {
        return p;
    }

    public static void main(String[] args) {
        launch(args);
    }
}