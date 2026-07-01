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

/**
 * Vista gráfica (JavaFX) del menú de inicio del juego. Muestra el
 * título animado (efecto de parpadeo), un fondo animado en GIF y los
 * botones para iniciar una nueva partida o salir de la aplicación.
 */
public class VistaMenuInicio {

    /** Contenedor raíz de la vista (incluye el fondo animado). */
    private StackPane rootContainer;

    /** Contenedor del contenido visible (título y botones). */
    private VBox layoutContenido;

    /** Referencia al controlador principal, usado para navegar entre pantallas. */
    private MenuPrincipal2 orquestador;

    /** Animación de parpadeo aplicada al título del menú. */
    private Timeline animacionParpadeo;

    /**
     * Construye la vista del menú de inicio y arma sus componentes gráficos.
     *
     * @param orquestador controlador principal usado para navegar a otras pantallas.
     */
    public VistaMenuInicio(MenuPrincipal2 orquestador) {
        this.orquestador = orquestador;
        crearComponentes();
    }

    /**
     * Crea y ensambla todos los componentes gráficos del menú: fondo
     * animado, título con efecto de parpadeo, y botones de "Nueva
     * Partida" y "Salir".
     */
    private void crearComponentes() {
        rootContainer = new StackPane();
        rootContainer.setStyle("-fx-background-color: #000000;");

        try {
            InputStream stream = getClass().getResourceAsStream("/img/dark-souls.gif");
            if (stream != null) {
                Image gifImagen = new Image(stream);
                ImageView gifFondo = new ImageView(gifImagen);

                gifFondo.setFitWidth(800);
                gifFondo.setFitHeight(600);
                gifFondo.setPreserveRatio(true);

                rootContainer.getChildren().add(gifFondo);
            } else {
                System.out.println("No se pudo encontrar el archivo GIF. Verifica la ruta en resources.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el GIF de fondo: " + e.getMessage());
        }

        layoutContenido = new VBox(25);
        layoutContenido.setAlignment(Pos.CENTER);
        layoutContenido.setStyle("-fx-background-color: transparent; -fx-padding: 40;");

        Label lblTitulo = new Label("Dark Souls: POO Edition");
        lblTitulo.setFont(Font.font("Verdana", 36));
        lblTitulo.setTextFill(Color.web("#FFFFFF"));
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        animacionParpadeo = new Timeline(
                new KeyFrame(Duration.seconds(0.0), e -> lblTitulo.setOpacity(1.0)),
                new KeyFrame(Duration.seconds(0.6), e -> lblTitulo.setOpacity(0.15)),
                new KeyFrame(Duration.seconds(1.2), e -> lblTitulo.setOpacity(1.0))
        );
        animacionParpadeo.setCycleCount(Animation.INDEFINITE);
        animacionParpadeo.play(); // Iniciar animación automáticamente

        Button btnNuevaPartida = new Button("NUEVA PARTIDA");
        btnNuevaPartida.setPrefWidth(200);
        btnNuevaPartida.setStyle("-fx-base: #2c3e50; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");

        btnNuevaPartida.setOnAction(e -> {
            detenerAnimacion();
            orquestador.mostrarCreacionPersonaje();
        });

        Button btnSalir = new Button("SALIR");
        btnSalir.setPrefWidth(200);
        btnSalir.setStyle("-fx-base: #7f8c8d; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");
        btnSalir.setOnAction(e -> {
            detenerAnimacion();
            System.exit(0);
        });

        layoutContenido.getChildren().addAll(lblTitulo, btnNuevaPartida, btnSalir);

        rootContainer.getChildren().add(layoutContenido);
    }

    /**
     * Detiene la animación de parpadeo del título, si está activa.
     * Debe llamarse antes de abandonar esta pantalla para evitar que
     * la animación siga ejecutándose en segundo plano.
     */
    public void detenerAnimacion() {
        if (animacionParpadeo != null) {
            animacionParpadeo.stop();
        }
    }

    /**
     * Obtiene el contenedor raíz de la vista, listo para insertarse en
     * la escena de JavaFX.
     *
     * @return layout raíz de la vista.
     */
    public StackPane getLayout() {
        return rootContainer;
    }
}