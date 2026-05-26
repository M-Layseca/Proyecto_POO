package com.example.pruebas_de_fx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import java.io.InputStream;




public class MenuPrincipal2 extends Application {
    private StackPane root; // Contenedor principal que intercambia las pantallas
    private Jugador protagonista;
    private Orquestador orquestador = new Orquestador();
    private int salaActual = 1;
    private final int MAX_SALAS = 5; // Cantidad de salas antes del Jefe Final

    @Override
    public void start(Stage primaryStage) {
        root = new StackPane();
        mostrarMenuEntrada(); // Empezamos mostrando el menú de selección de personaje

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Dark Souls: POO Edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // --- 1. MENÚ DE ENTRADA (BIENVENIDA Y SELECCIÓN) ---
    private void mostrarMenuEntrada() {
        VBox menuVBox = new VBox(20);
        menuVBox.setAlignment(Pos.CENTER);
        menuVBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); -fx-padding: 30;");

        Label titulo = new Label("DARK SOULS: POO EDITION");
        titulo.setStyle("-fx-font-size: 36; -fx-text-fill: #e74c3c; -fx-font-weight: bold;");

        Label subtitulo = new Label("Selecciona tu Clase e ingresa tu Nombre");
        subtitulo.setStyle("-fx-font-size: 16; -fx-text-fill: white;");

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del héroe...");
        txtNombre.setMaxWidth(250);

        ComboBox<String> cbClase = new ComboBox<>();
        cbClase.getItems().addAll("Guerrero", "Mago", "Tanque");
        cbClase.setValue("Guerrero");

        Button btnIniciar = new Button("INICIAR AVENTURA");
        btnIniciar.setStyle("-fx-base: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");

        btnIniciar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                nombre = "Sin Nombre";
            }

            // Instanciamos el polimorfismo según la selección
            switch (cbClase.getValue()) {
                case "Mago":
                    protagonista = new MAGO(nombre);
                    break;
                case "Tanque":
                    protagonista = new TANK(nombre);
                    break;
                default:
                    protagonista = new GUERRERO(nombre);
                    break;
            }

            // Reiniciamos contadores por si es un reintento
            salaActual = 1;
            irASiguienteSala();
        });

        menuVBox.getChildren().addAll(titulo, subtitulo, txtNombre, cbClase, btnIniciar);

        // Intento de carga de fondo (si el archivo existe en tus assets, si no, se verá negro elegante)
        try {
            Image fondoImg = new Image(getClass().getResourceAsStream("/com/example/pruebas_de_fx/fondo.gif"));
            BackgroundImage bImg = new BackgroundImage(fondoImg,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
            root.setBackground(new Background(bImg));
        } catch (Exception ex) {
            root.setStyle("-fx-background-color: #1a1a1a;");
        }

        root.getChildren().setAll(menuVBox);
    }

    // --- 2. LÓGICA CENTRAL DE ROTACIÓN Y FLUJO DE SALAS ---
    public void irASiguienteSala() {
        // Validación de Game Over instantáneo
        if (protagonista == null || !protagonista.estaVivo()) {
            mostrarPantallaMuerte();
            return;
        }

        // Caso A: Salas intermedias (Aleatoriedad entre Combate y Tesoro)
        if (salaActual <= MAX_SALAS) {
            double dadoSala = Math.random(); // Número aleatorio entre 0.0 y 1.0

            if (dadoSala < 0.70) {
                // 70% Probabilidad: Sala de Combate
                Batalla batallaAleatoria = orquestador.generarBatallaAleatoria();

                // NOTA: Pasamos 'this' (MenuPrincipal2) como tercer parámetro para que la interfaz sepa a quién avisar al ganar
                InterfazdeCombate guiCombate = new InterfazdeCombate(protagonista, batallaAleatoria, this);
                root.getChildren().setAll(guiCombate.getLayout());
            } else {
                // 30% Probabilidad: Sala de Tesoro Despejada
                SalaTesoro salaRegalo = new SalaTesoro();
                mostrarPantallaTesoro(salaRegalo);
            }
            salaActual++;
        }
        // Caso B: El umbral del Jefe Final
        else if (salaActual == MAX_SALAS + 1) {
            Batalla bossFinal = orquestador.generarJefeFinal();
            InterfazdeCombate guiCombate = new InterfazdeCombate(protagonista, bossFinal, this);
            root.getChildren().setAll(guiCombate.getLayout());
            salaActual++;
        }
        // Caso C: Victoria Absoluta
        else {
            mostrarPantallaVictoria();
        }
    }

    // --- 3. SCENE VISUAL: SALA DE TESOROS (INTERFAZ GRAFICA) ---
    private void mostrarPantallaTesoro(SalaTesoro sala) {
        VBox visualTesoro = new VBox(20);
        visualTesoro.setAlignment(Pos.CENTER);
        visualTesoro.setStyle("-fx-background-color: #2c3e50; -fx-padding: 40; -fx-border-color: #f1c40f; -fx-border-width: 3;");

        Label title = new Label("SALA " + salaActual + ": " + sala.nombreSala.toUpperCase());
        title.setStyle("-fx-font-size: 26; -fx-text-fill: #f1c40f; -fx-font-weight: bold;");

        Label desc = new Label(sala.descripcion);
        desc.setStyle("-fx-font-size: 16; -fx-text-fill: white; -fx-font-style: italic;");

        TextArea recompensaLog = new TextArea("Encuentras un viejo cofre en el centro del altar...\n");
        recompensaLog.setEditable(false);
        recompensaLog.setPrefHeight(120);
        recompensaLog.setMaxWidth(450);
        recompensaLog.setStyle("-fx-control-inner-background: #1a252f; -fx-text-fill: #e67e22; -fx-font-family: 'Courier New';");

        Button btnAbrir = new Button("ABRIR COFRE");
        btnAbrir.setStyle("-fx-base: #f39c12; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 14;");

        Button btnAvanzar = new Button("CONTINUAR VIAJE");
        btnAvanzar.setDisable(true); // Bloqueado hasta que el cofre sea abierto y procesado
        btnAvanzar.setOnAction(e -> irASiguienteSala());

        btnAbrir.setOnAction(e -> {
            // Guardamos valores previos para mostrar la diferencia matemática en pantalla
            int hpMaxPrevia = protagonista.getVidaMax();
            int atkPrevio = protagonista.getAtaque();
            int estusPrevios = protagonista.estus;
            int pasivaPrevia = protagonista.usosPasiva;

            // Se ejecuta la lógica nativa de tu clase SalaTesoro
            sala.entrar(protagonista);

            recompensaLog.appendText("\n¡El cofre se abre mágicamente!");

            // Comparamos qué stat cambió para informárselo al usuario de forma gráfica
            if (protagonista.getVidaMax() > hpMaxPrevia) {
                recompensaLog.appendText("\n>> [Fragmento de Estus]: ¡Tu vida máxima aumentó de " + hpMaxPrevia + " a " + protagonista.getVidaMax() + " HP! (Salud restaurada)");
            } else if (protagonista.getAtaque() > atkPrevio) {
                recompensaLog.appendText("\n>> [Titanita]: ¡Tu daño de ataque aumentó de " + atkPrevio + " a " + protagonista.getAtaque() + "!");
            } else if (protagonista.estus > estusPrevios) {
                recompensaLog.appendText("\n>> [Esencia de Fuego]: ¡Conseguiste frascos extra! Estus: " + estusPrevios + " -> " + protagonista.estus);
            } else if (protagonista.usosPasiva > pasivaPrevia) {
                recompensaLog.appendText("\n>> [Voluntad Inquebrantable]: ¡Has recuperado +1 uso de tu habilidad pasiva!");
            } else {
                recompensaLog.appendText("\n>> El cofre contenía Almas perdidas. Te sientes más fuerte.");
            }

            btnAbrir.setDisable(true);
            btnAvanzar.setDisable(false); // Desbloqueamos el botón para cambiar de escena
        });

        visualTesoro.getChildren().addAll(title, desc, btnAbrir, recompensaLog, btnAvanzar);
        root.getChildren().setAll(visualTesoro);
    }

    private void mostrarPantallaMuerte() {
        // 1. Limpiamos el root de estilos previos (como el fondo negro del menú)
        root.setStyle(null);
        root.setBackground(null);

        VBox muerte = new VBox(25);
        muerte.setAlignment(Pos.CENTER);
        muerte.setStyle("-fx-background-color: transparent;"); // Transparente para ver el fondo del root

        try {
            String rutaImagen = "/img/death_scene.jpg";
            InputStream stream = getClass().getResourceAsStream(rutaImagen);

            if (stream == null) {
                System.err.println("ERROR: No se encontró la imagen en " + rutaImagen);
                root.setStyle("-fx-background-color: #1a0000;"); // Rojo muy oscuro de respaldo
            } else {
                Image imagenFondo = new Image(stream);
                
                // BackgroundSize: width=100%, height=100%, asPercentage=true, contain=false, cover=true
                BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
                BackgroundImage backgroundImage = new BackgroundImage(
                        imagenFondo,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        backgroundSize
                );

                root.setBackground(new Background(backgroundImage));
                System.out.println("Imagen de muerte cargada correctamente: " + rutaImagen);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar el fondo: " + e.getMessage());
            root.setStyle("-fx-background-color: black;");
        }

        // 2. CONFIGURACIÓN DEL TEXTO "HAS MUERTO"
        Label lbl = new Label("HAS MUERTO");
        lbl.setStyle("-fx-text-fill: #b30000; -fx-font-size: 68; -fx-font-weight: bold; -fx-letter-spacing: 8;");

        // 3. ANIMACIÓN DE PARPADEO (TIMELINE)
        Timeline parpadeo = new Timeline(
                new KeyFrame(Duration.ZERO, event -> lbl.setOpacity(1.0)),
                new KeyFrame(Duration.seconds(1.2), event -> lbl.setOpacity(0.2)),
                new KeyFrame(Duration.seconds(2.4), event -> lbl.setOpacity(1.0))
        );
        parpadeo.setCycleCount(Animation.INDEFINITE); // Infinito
        parpadeo.setAutoReverse(true);                // Transición de opacidad suave de ida y vuelta
        parpadeo.play();                              // Iniciar ciclo de animación

        // 4. BOTÓN DE REINTENTO
        Button btn = new Button("REINTENTAR DESDE LA HOGUERA");
        btn.setStyle("-fx-base: #222222; -fx-text-fill: #aaaaaa; -fx-font-weight: bold; -fx-padding: 10 20;");

        btn.setOnAction(e -> {
            parpadeo.stop(); // Detener el hilo de animación para evitar fugas de memoria
            mostrarMenuEntrada();
        });

        muerte.getChildren().addAll(lbl, btn);
        root.getChildren().setAll(muerte);
    }

    // --- 5. PANTALLA: VICTORIA ABSOLUTA ---
    private void mostrarPantallaVictoria() {
        VBox victoria = new VBox(20);
        victoria.setAlignment(Pos.CENTER);
        victoria.setStyle("-fx-background-color: #2c3e50;");

        Label lbl = new Label("HOGUERA ENCENDIDA");
        lbl.setStyle("-fx-text-fill: #f1c40f; -fx-font-size: 46; -fx-font-weight: bold;");

        Label lblSub = new Label("Has enlazado la Primera Llama. El reino está a salvo... por ahora.");
        lblSub.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        Button btnMenu = new Button("VOLVER AL MENÚ");
        btnMenu.setOnAction(e -> mostrarMenuEntrada());

        victoria.getChildren().addAll(lbl, lblSub, btnMenu);
        root.getChildren().setAll(victoria);
    }

    public static void main(String[] args) {
        launch(args);
    }
}