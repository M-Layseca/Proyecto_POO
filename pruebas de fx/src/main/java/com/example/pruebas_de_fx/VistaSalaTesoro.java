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

    public VBox getLayout() {
        return layout;
    }
}