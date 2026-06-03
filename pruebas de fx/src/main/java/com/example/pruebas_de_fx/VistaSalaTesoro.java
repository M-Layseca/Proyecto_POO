package com.example.pruebas_de_fx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VistaSalaTesoro {
    private VBox layout;
    private MenuPrincipal2 orquestador;

    public VistaSalaTesoro(Jugador jugador, MenuPrincipal2 orquestador) {
        this.orquestador = orquestador;

        // Instanciamos tu lógica original de SalaTesoro
        SalaTesoro salaLogica = new SalaTesoro();

        // TRUCO: Capturar lo que SalaTesoro imprime en consola para mostrarlo en la GUI
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream viejoOut = System.out;
        System.setOut(ps); // Redireccionamos temporalmente la consola

        salaLogica.entrar(jugador); // Ejecuta los cambios de atributos en el jugador

        System.setOut(viejoOut); // Restauramos la consola original
        String resultadoConsola = baos.toString();

        // Construir la interfaz visual
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1a1a24; -fx-padding: 40;");

        Label lblTitulo = new Label("¡SALA DEL TESORO!");
        lblTitulo.setStyle("-fx-font-size: 26; -fx-font-weight: bold;");
        lblTitulo.setTextFill(Color.GOLD);

        // Texto con el premio obtenido
        Label lblPremio = new Label(resultadoConsola);
        lblPremio.setStyle("-fx-font-size: 16; -fx-alignment: center;");
        lblPremio.setTextFill(Color.WHITE);

        Button btnContinuar = new Button("CONTINUAR AVENTURA");
        btnContinuar.setStyle("-fx-base: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        // Al presionar, avanza a la siguiente sala del orquestador
        btnContinuar.setOnAction(e -> orquestador.irASiguienteSala());

        layout.getChildren().addAll(lblTitulo, lblPremio, btnContinuar);
    }

    public VBox getLayout() {
        return layout;
    }
}