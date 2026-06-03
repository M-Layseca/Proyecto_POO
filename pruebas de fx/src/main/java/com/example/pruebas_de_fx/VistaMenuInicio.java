package com.example.pruebas_de_fx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.InputStream;

public class VistaMenuInicio {
    private StackPane rootContainer; // Contenedor principal de capas (Fondo Negro -> GIF -> Contenido)
    private VBox layoutContenido;    // Contenedor vertical para título y botones
    private MenuPrincipal2 orquestador;
    private Timeline animacionParpadeo;

    public VistaMenuInicio(MenuPrincipal2 orquestador) {
        this.orquestador = orquestador;
        crearComponentes();
    }

    private void crearComponentes() {
        // 1. Inicializar el contenedor raíz de capas
        rootContainer = new StackPane();
        // Color de fondo negro absoluto de base
        rootContainer.setStyle("-fx-background-color: #000000;");

        // 2. CAPA INTERMEDIA: El GIF de fondo con transparencia
        try {
            // Reemplaza "fondo.gif" por la ruta exacta de tu archivo dentro de src/main/resources
            InputStream stream = getClass().getResourceAsStream("/img/dark-souls.gif");
            if (stream != null) {
                Image gifImagen = new Image(stream);
                ImageView gifFondo = new ImageView(gifImagen);

                gifFondo.setFitWidth(800);
                gifFondo.setFitHeight(600);
                gifFondo.setPreserveRatio(true); // Mantiene la proporción si lo deseas

                // Añadir el GIF al StackPane (se dibuja primero, sobre el fondo negro)
                rootContainer.getChildren().add(gifFondo);
            } else {
                System.out.println("No se pudo encontrar el archivo GIF. Verifica la ruta en resources.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el GIF de fondo: " + e.getMessage());
        }

        // 3. CAPA SUPERIOR: Textos y Botones
        layoutContenido = new VBox(25);
        layoutContenido.setAlignment(Pos.CENTER);
        // Dejamos el fondo de este VBox transparente para que se vea el GIF de atrás
        layoutContenido.setStyle("-fx-background-color: transparent; -fx-padding: 40;");

        // Título del Juego
        Label lblTitulo = new Label("Dark Souls: POO Edition");
        lblTitulo.setFont(Font.font("Verdana", 36));
        lblTitulo.setTextFill(Color.web("#FFFFFF"));
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        // --- Lógica del Parpadeo (Timeline) ---
        animacionParpadeo = new Timeline(
                new KeyFrame(Duration.seconds(0.0), e -> lblTitulo.setOpacity(1.0)),
                new KeyFrame(Duration.seconds(0.6), e -> lblTitulo.setOpacity(0.15)),
                new KeyFrame(Duration.seconds(1.2), e -> lblTitulo.setOpacity(1.0))
        );
        animacionParpadeo.setCycleCount(Animation.INDEFINITE);
        animacionParpadeo.play(); // Iniciar animación automáticamente

        // Botones existentes
        Button btnNuevaPartida = new Button("NUEVA PARTIDA");
        btnNuevaPartida.setPrefWidth(200);
        btnNuevaPartida.setStyle("-fx-base: #2c3e50; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");

        btnNuevaPartida.setOnAction(e -> {
            detenerAnimacion(); // Limpieza para evitar fugas de memoria al cambiar de escena
            orquestador.mostrarCreacionPersonaje();
        });

        Button btnSalir = new Button("SALIR");
        btnSalir.setPrefWidth(200);
        btnSalir.setStyle("-fx-base: #7f8c8d; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");
        btnSalir.setOnAction(e -> {
            detenerAnimacion();
            System.exit(0);
        });

        // Agregamos los elementos al VBox
        layoutContenido.getChildren().addAll(lblTitulo, btnNuevaPartida, btnSalir);

        // Agregamos el VBox al StackPane central (quedará encima de todo)
        rootContainer.getChildren().add(layoutContenido);
    }

    public void detenerAnimacion() {
        if (animacionParpadeo != null) {
            animacionParpadeo.stop();
        }
    }

    public StackPane getLayout() {
        return rootContainer;
    }
}