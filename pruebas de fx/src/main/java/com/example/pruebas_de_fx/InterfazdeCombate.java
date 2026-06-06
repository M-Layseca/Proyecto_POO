package com.example.pruebas_de_fx;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import java.io.InputStream;

public class InterfazdeCombate {
    private StackPane rootContainer;
    private VBox layout;
    private ProgressBar barraVidaJugador;
    private ProgressBar barraXpJugador;
    private Label labelVidaTexto;
    private Label labelXpTexto;
    private Jugador jugador;
    private Batalla batalla;
    private HBox contenedorEnemigos;
    private ComboBox<Enemigo> selectorEnemigos;
    private ComboBox<Hechizos> selectorHechizos;
    private HBox zonaMagia;
    private HBox botonesPrincipales;
    private MenuPrincipal2 juegoPrincipal;

    private ImageView imgJugador;
    private AnchorPane zonaVisualCombate;
    public InterfazdeCombate(Jugador jugador, Batalla batalla, MenuPrincipal2 juegoPrincipal) {
        this.jugador = jugador;
        this.batalla = batalla;
        this.juegoPrincipal = juegoPrincipal;
        crearInterfaz();
    }

    private void crearInterfaz() {
        rootContainer = new StackPane();
        rootContainer.setPrefSize(800, 600);

        String rutaFondo = "/img/fondo_batalla.jpg"; // Fondo por defecto

        for (Enemigo e : batalla.getEnemigos()) {
            if (e instanceof Herobrine) {
                rutaFondo = "/img/fondo_herobrine.png";
                break;
            }
        }

        try {
            InputStream fondoStream = getClass().getResourceAsStream(rutaFondo);
            if (fondoStream != null) {
                Image imagenFormateada = new Image(fondoStream, 800, 600, false, true);
                BackgroundImage fondoImg = new BackgroundImage(
                        imagenFormateada,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT
                );
                rootContainer.setBackground(new Background(fondoImg));
            } else {
                System.out.println("ADVERTENCIA: No se encontró la imagen en " + rutaFondo);
                rootContainer.setStyle("-fx-background-color: #121212;");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el fondo: " + e.getMessage());
            rootContainer.setStyle("-fx-background-color: #121212;");
        }

        // --- 3. Resto de la interfaz ---
        layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.65); -fx-padding: 20;");

        VBox panelEstadoJugador = new VBox(5);
        panelEstadoJugador.setAlignment(Pos.CENTER_LEFT);
        panelEstadoJugador.setStyle("-fx-background-color: rgba(44, 62, 80, 0.6); -fx-padding: 10; -fx-background-radius: 5;");

        Label lblNombre = new Label(jugador.getNombre() + " (Nivel " + jugador.getNivel() + ")");
        lblNombre.setFont(Font.font("Verdana", 16));
        lblNombre.setTextFill(Color.WHITE);
        lblNombre.setStyle("-fx-font-weight: bold;");

        labelVidaTexto = new Label();
        labelVidaTexto.setTextFill(Color.WHITE);
        barraVidaJugador = new ProgressBar();
        barraVidaJugador.setPrefWidth(250);
        barraVidaJugador.setStyle("-fx-accent: #e74c3c;");

        labelXpTexto = new Label();
        labelXpTexto.setTextFill(Color.GOLD);
        barraXpJugador = new ProgressBar();
        barraXpJugador.setPrefWidth(250);
        barraXpJugador.setStyle("-fx-accent: #f1c40f;");

        actualizarBarrasEstado();
        panelEstadoJugador.getChildren().addAll(lblNombre, labelVidaTexto, barraVidaJugador, labelXpTexto, barraXpJugador);

        zonaVisualCombate = new AnchorPane();
        zonaVisualCombate.setPrefHeight(220);
        zonaVisualCombate.setStyle("-fx-padding: 10;");

        imgJugador = new ImageView();
        imgJugador.setFitHeight(150);
        imgJugador.setFitWidth(150);
        imgJugador.setPreserveRatio(true);
        cargarImagenEntidad(imgJugador, jugador.getClass().getSimpleName().toLowerCase());

        AnchorPane.setBottomAnchor(imgJugador, 10.0);
        AnchorPane.setLeftAnchor(imgJugador, 40.0);

        contenedorEnemigos = new HBox(25);
        contenedorEnemigos.setAlignment(Pos.CENTER);

        AnchorPane.setTopAnchor(contenedorEnemigos, 10.0);
        AnchorPane.setRightAnchor(contenedorEnemigos, 40.0);

        actualizarContenedorEnemigos();

        zonaVisualCombate.getChildren().addAll(imgJugador, contenedorEnemigos);

        selectorEnemigos = new ComboBox<>();
        selectorEnemigos.setStyle("-fx-font-size: 13;");
        actualizarSelectorEnemigos();

        selectorHechizos = new ComboBox<>();
        selectorHechizos.setStyle("-fx-font-size: 13;");
        selectorHechizos.setConverter(new StringConverter<Hechizos>() {
            @Override
            public String toString(Hechizos hechizo) {
                return (hechizo != null) ? hechizo.getNombreHechizo() : "";
            }
            @Override
            public Hechizos fromString(String string) {
                return null;
            }
        });

        zonaMagia = new HBox(10);
        zonaMagia.setAlignment(Pos.CENTER);

        if (jugador instanceof MAGO) {
            MAGO mago = (MAGO) jugador;
            selectorHechizos.getItems().addAll(mago.getLibroDeHechizos());
            if (!mago.getLibroDeHechizos().isEmpty()) {
                selectorHechizos.setValue(mago.getLibroDeHechizos().get(0));
            }
            Label lblMagia = new Label("Hechizos:");
            lblMagia.setTextFill(Color.WHITE);
            zonaMagia.getChildren().addAll(lblMagia, selectorHechizos);
        } else if(jugador instanceof Steve) {
            Steve steve = (Steve) jugador;
            selectorHechizos.getItems().addAll(steve.getLibroDeHabilidades());
            if (!steve.getLibroDeHabilidades().isEmpty()) {
                selectorHechizos.setValue(steve.getLibroDeHabilidades().get(0));
            }
            Label lblInventario = new Label("Inventario:");
            lblInventario.setTextFill(Color.WHITE);
            zonaMagia.getChildren().addAll(lblInventario, selectorHechizos);
        }

        botonesPrincipales = new HBox(15);
        botonesPrincipales.setAlignment(Pos.CENTER);

        Button btnAtacar = new Button("ATACAR");
        btnAtacar.setStyle("-fx-base: #c0392b; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAtacar.setOnAction(e -> ejecutarAccion("ATACAR"));

        Button btnPasiva = new Button("PASIVA");
        btnPasiva.setStyle("-fx-base: #2980b9; -fx-text-fill: white; -fx-font-weight: bold;");
        btnPasiva.setOnAction(e -> ejecutarAccion("PASIVA"));

        Button btnCurar = new Button("USAR ESTUS");
        btnCurar.setStyle("-fx-base: #d35400; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCurar.setOnAction(e -> ejecutarAccion("CURAR"));

        botonesPrincipales.getChildren().addAll(btnAtacar, btnPasiva, btnCurar);

        layout.getChildren().addAll(
                panelEstadoJugador,
                zonaVisualCombate,
                new HBox(10, new Label("Objetivo:") {{ setTextFill(Color.WHITE); }}, selectorEnemigos, zonaMagia),
                botonesPrincipales
        );

        rootContainer.getChildren().add(layout);
    }

    private void ejecutarAccion(String tipo) {
        Enemigo objetivo = selectorEnemigos.getValue();

        if ((tipo.equals("ATACAR") || (tipo.equals("PASIVA") && jugador instanceof MAGO)) && objetivo == null) {
            return;
        }

        boolean pasoTurno = false;

        switch (tipo) {
            case "ATACAR":
                if (jugador instanceof MAGO) {
                    Hechizos hechizoSeleccionado = selectorHechizos.getValue();
                    MAGO mago = (MAGO) jugador;
                    if (hechizoSeleccionado != null && mago.usosMagia >= hechizoSeleccionado.getCosteMana()) {
                        mago.usosMagia -= hechizoSeleccionado.getCosteMana();
                        hechizoSeleccionado.aplicarEfecto(jugador, objetivo);

                        if (!objetivo.estaVivo()) {
                            jugador.ganarExp(objetivo.getXpOtorgada());
                            batalla.getEnemigos().remove(objetivo);
                        }
                        pasoTurno = true;
                    } else {
                        System.out.println("¡No tienes suficiente Maná!");
                        return;
                    }
                }
                else if (jugador instanceof Steve) {
                    Hechizos habilidad = selectorHechizos.getValue();
                    Steve steve = (Steve) jugador;
                    if (habilidad != null && steve.usosMagia >= habilidad.getCosteMana()) {
                        steve.usosMagia -= habilidad.getCosteMana();
                        habilidad.aplicarEfecto(jugador, objetivo);

                        if (!objetivo.estaVivo()) {
                            jugador.ganarExp(objetivo.getXpOtorgada());
                            batalla.getEnemigos().remove(objetivo);
                        }
                        pasoTurno = true;
                    } else {
                        System.out.println("¡No tienes suficientes materiales!");
                        return;
                    }
                } else {
                    batalla.turnoJugador(jugador, objetivo);
                    pasoTurno = true;
                }
                break;

            case "PASIVA":
                jugador.activarSkill();
                pasoTurno = true;
                break;

            case "CURAR":
                String msgCurar = jugador.usarEstus();

                if (msgCurar != null && msgCurar.contains("¡No te quedan frascos")) {
                    System.out.println(msgCurar);
                    return;
                }
                pasoTurno = true;
                break;
        }

        actualizarBarrasEstado();
        actualizarSelectorEnemigos();
        actualizarContenedorEnemigos();

        if (pasoTurno && !batalla.getEnemigos().isEmpty()) {
            batalla.turnoEnemigos(jugador);

            actualizarBarrasEstado();
            actualizarSelectorEnemigos();
            actualizarContenedorEnemigos();
        }

        verificarFinCombate();
    }

    private void actualizarBarrasEstado() {

        System.out.println("DEBUG INTERFAZ - Vida actual del jugador: " + jugador.getVida() + " / " + jugador.getVidaMax());

        double porcentajeVida = (double) jugador.getVida() / jugador.getVidaMax();
        barraVidaJugador.setProgress(porcentajeVida);
        labelVidaTexto.setText("PS: " + jugador.getVida() + " / " + jugador.getVidaMax());

        double porcentajeXp = (double) jugador.getXpActual() / 100.0;
        barraXpJugador.setProgress(porcentajeXp);

        if (jugador instanceof MAGO) {
            labelXpTexto.setText("Almas: " + jugador.getXpActual() + " / 100 | MP: " + ((MAGO) jugador).usosMagia + "/" + ((MAGO) jugador).maxUsos);
        } else if (jugador instanceof Steve) {
            labelXpTexto.setText("Almas: " + jugador.getXpActual() + " / 100 | Materiales: " + ((Steve) jugador).usosMagia + "/" + ((Steve) jugador).maxUsos);
        } else {
            labelXpTexto.setText("Almas: " + jugador.getXpActual() + " / 100 | Usos Pasiva: " + jugador.usosPasiva);
        }
    }

    private void actualizarSelectorEnemigos() {
        Enemigo seleccionadoActual = selectorEnemigos.getValue();
        selectorEnemigos.getItems().clear();
        selectorEnemigos.getItems().addAll(batalla.getEnemigos());

        if (batalla.getEnemigos().contains(seleccionadoActual)) {
            selectorEnemigos.setValue(seleccionadoActual);
        } else if (!batalla.getEnemigos().isEmpty()) {
            selectorEnemigos.setValue(batalla.getEnemigos().get(0));
        }
    }

    private void actualizarContenedorEnemigos() {
        contenedorEnemigos.getChildren().clear();

        for (Enemigo e : batalla.getEnemigos()) {
            VBox tarjetaEnemigo = new VBox(5);
            tarjetaEnemigo.setAlignment(Pos.CENTER);

            ImageView imgE = new ImageView();
            imgE.setFitHeight(110);
            imgE.setFitWidth(110);
            imgE.setPreserveRatio(true);

            cargarImagenEntidad(imgE, e.getClass().getSimpleName().toLowerCase());

            Label lblName = new Label(e.getNombre());
            lblName.setTextFill(Color.WHITE);
            lblName.setFont(Font.font("Arial", 12));

            ProgressBar barraVidaE = new ProgressBar((double) e.getVida() / e.getVidaMax());
            barraVidaE.setPrefWidth(90);
            barraVidaE.setStyle("-fx-accent: #FF0000;");

            tarjetaEnemigo.getChildren().addAll(imgE, lblName, barraVidaE);
            contenedorEnemigos.getChildren().add(tarjetaEnemigo);
        }
    }

    private void cargarImagenEntidad(ImageView iv, String nombreArchivo) {
        try {
            String ruta = "/img/" + nombreArchivo + ".png";
            InputStream stream = getClass().getResourceAsStream(ruta);
            if (stream != null) {
                iv.setImage(new Image(stream));
            } else {
                System.out.println("No se localizó el asset gráfico: " + ruta);
            }
        } catch (Exception e) {
            System.out.println("Error al montar la textura: " + e.getMessage());
        }
    }

    private void verificarFinCombate() {
        if (!jugador.estaVivo()) {
            cambiarBotonAvanzar("REINTENTAR DESDE LA HOGUERA");
        } else if (batalla.getEnemigos().isEmpty()) {
            cambiarBotonAvanzar("AVANZAR A LA SIGUIENTE SALA");
        }
    }

    private void cambiarBotonAvanzar(String textoBoton) {
        if (botonesPrincipales != null) {
            botonesPrincipales.getChildren().clear();
            Button btnProgreso = new Button(textoBoton);

            if (textoBoton.contains("VICTORIA") || textoBoton.contains("AVANZAR")) {
                btnProgreso.setStyle("-fx-base: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
            } else {
                btnProgreso.setStyle("-fx-base: #7f8c8d; -fx-text-fill: white;");
            }

            btnProgreso.setOnAction(e -> juegoPrincipal.irASiguienteSala());
            botonesPrincipales.getChildren().add(btnProgreso);
        }

        selectorEnemigos.setDisable(true);
        selectorHechizos.setDisable(true);
    }

    public StackPane getLayout() {
        return rootContainer;
    }
}