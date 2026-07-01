package com.example.pruebas_de_fx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Vista gráfica (JavaFX) de la sala de tesoro. Ejecuta la lógica de
 * {@link SalaTesoro}, captura su salida por consola para mostrarla en
 * pantalla, y presenta un botón para continuar a la siguiente sala.
 */
public class VistaSalaTesoro {

    /** Contenedor raíz de la vista. */
    private VBox layout;

    /** Referencia al controlador principal, usada para avanzar de sala. */
    private MenuPrincipal2 orquestador;

    /**
     * Construye la vista de sala de tesoro: ejecuta la lógica de
     * {@link SalaTesoro#entrar(Jugador)} capturando su salida por
     * consola, y arma la interfaz gráfica con el resultado obtenido.
     *
     * @param jugador jugador que recibe la recompensa de la sala.
     * @param orquestador controlador principal usado para navegar a la siguiente sala.
     */
    public VistaSalaTesoro(Jugador jugador, MenuPrincipal2 orquestador) {
        this.orquestador = orquestador;

        SalaTesoro salaLogica = new SalaTesoro();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream viejoOut = System.out;
        System.setOut(ps);
        salaLogica.entrar(jugador);
        System.setOut(viejoOut);
        String resultadoConsola = baos.toString();

        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1a1a24; -fx-padding: 40;");

        Label lblTitulo = new Label("¡SALA DEL TESORO!");
        lblTitulo.setStyle("-fx-font-size: 26; -fx-font-weight: bold;");
        lblTitulo.setTextFill(Color.GOLD);

        Label lblPremio = new Label(resultadoConsola);
        lblPremio.setStyle("-fx-font-size: 16; -fx-alignment: center;");
        lblPremio.setTextFill(Color.WHITE);

        Button btnContinuar = new Button("CONTINUAR AVENTURA");
        btnContinuar.setStyle("-fx-base: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        btnContinuar.setOnAction(e -> orquestador.irASiguienteSala());

        layout.getChildren().addAll(lblTitulo, lblPremio, btnContinuar);
    }

    /**
     * Obtiene el contenedor raíz de la vista, listo para insertarse en
     * la escena de JavaFX.
     *
     * @return layout raíz de la vista.
     */
    public VBox getLayout() {
        return layout;
    }
}
