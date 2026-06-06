package com.example.pruebas_de_fx;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.io.InputStream;

public class VistaCreacionPersonaje {
    private VBox layout;
    private MenuPrincipal2 orquestador;
    private ImageView imgPersonaje;
    public VistaCreacionPersonaje(MenuPrincipal2 orquestador) {
        this.orquestador = orquestador;
        crearComponentes();
    }

    private void crearComponentes() {
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1a1a1a; -fx-padding: 30;");

        Label lblTitulo = new Label("Crea tu Héroe");
        lblTitulo.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        lblTitulo.setTextFill(Color.WHITE);


        HBox contenedorNombre = new HBox(10);
        contenedorNombre.setAlignment(Pos.CENTER);
        Label lblNombre = new Label("Nombre:");
        lblNombre.setTextFill(Color.WHITE);
        lblNombre.setStyle("-fx-font-size: 14;");
        TextField txtNombre = new TextField("Héroe");
        contenedorNombre.getChildren().addAll(lblNombre, txtNombre);


        Label lblClase = new Label("Selecciona tu Arquetipo:");
        lblClase.setTextFill(Color.WHITE);
        lblClase.setStyle("-fx-font-size: 14;");

        ComboBox<String> selectorClase = new ComboBox<>();
        selectorClase.getItems().addAll("GUERRERO", "MAGO", "TANK", "STEVE");
        selectorClase.setValue("GUERRERO");
        selectorClase.setStyle("-fx-font-size: 14;");


        imgPersonaje = new ImageView();
        imgPersonaje.setFitHeight(200);
        imgPersonaje.setFitWidth(200);
        imgPersonaje.setPreserveRatio(true);

        actualizarImagenPersonaje("GUERRERO");

        selectorClase.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                actualizarImagenPersonaje(newValue);
            }
        });

        Button btnConfirmar = new Button("COMENZAR AVENTURA");
        btnConfirmar.setPrefWidth(220);
        btnConfirmar.setStyle("-fx-base: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14;");

        btnConfirmar.setOnAction(e -> {
            String nombreInput = txtNombre.getText().trim();
            if (nombreInput.isEmpty()) {
                nombreInput = "Desconocido";
            }

            Jugador nuevoJugador;
            switch (selectorClase.getValue()) {
                case "MAGO":
                    nuevoJugador = new MAGO(nombreInput);
                    break;
                case "TANK":
                    nuevoJugador = new TANK(nombreInput);
                    break;
                case "STEVE":
                    nuevoJugador = new Steve(nombreInput);
                    break;
                case "GUERRERO":
                default:
                    nuevoJugador = new GUERRERO(nombreInput);
                    break;
            }

            orquestador.iniciarSimulacionDeJuego(nuevoJugador);
        });

        Button btnVolver = new Button("VOLVER AL MENÚ");
        btnVolver.setPrefWidth(220);
        btnVolver.setStyle("-fx-base: #7f8c8d; -fx-text-fill: white;");
        btnVolver.setOnAction(e -> orquestador.mostrarMenuInicio());

        layout.getChildren().addAll(
                lblTitulo,
                contenedorNombre,
                lblClase,
                selectorClase,
                imgPersonaje,
                btnConfirmar,
                btnVolver
        );
    }

    private void actualizarImagenPersonaje(String clase) {
        String rutaImagen = "";

        switch (clase) {
            case "MAGO":
                rutaImagen = "/img/mago.png";
                break;
            case "TANK":
                rutaImagen = "/img/tank.png";
                break;
            case "STEVE":
                rutaImagen = "/img/steve.png";
                break;
            case "GUERRERO":
            default:
                rutaImagen = "/img/guerrero.png";
                break;
        }

        try {
            InputStream stream = getClass().getResourceAsStream(rutaImagen);
            if (stream != null) {
                Image nuevaImagen = new Image(stream);
                imgPersonaje.setImage(nuevaImagen);
            } else {
                System.out.println("No se encontró la imagen en la ruta: " + rutaImagen);
                imgPersonaje.setImage(null);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen del personaje: " + e.getMessage());
        }
    }

    public VBox getLayout() {
        return layout;
    }
}