package com.example.pruebas_de_fx;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class InterfazdeCombate {
    private VBox layout;
    private TextArea log;
    private ProgressBar barraVidaJugador;
    private ProgressBar barraXpJugador;
    private Label labelVidaTexto;
    private Label labelXpTexto;
    private Jugador jugador;
    private Batalla batalla;
    private VBox contenedorEnemigos;

    private ComboBox<Enemigo> selectorEnemigos;
    private ComboBox<Hechizos> selectorHechizos;
    private HBox zonaMagia;
    private HBox botonesPrincipales; // Cambiado a variable global para poder alterarla al ganar/perder
    private MenuPrincipal2 juegoPrincipal; // Referencia para controlar el cambio de sala

    // Constructor unificado con el orquestador del menú principal
    public InterfazdeCombate(Jugador jugador, Batalla batalla, MenuPrincipal2 juegoPrincipal) {
        this.jugador = jugador;
        this.batalla = batalla;
        this.juegoPrincipal = juegoPrincipal;
        crearInterfaz();
    }

    private void crearInterfaz() {
        layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #121212; -fx-padding: 20;");

        // 1. Zona Superior: Visualización de enemigos
        contenedorEnemigos = new VBox(10);
        contenedorEnemigos.setAlignment(Pos.CENTER);

        // 2. Zona de Selección de Objetivo
        HBox zonaObjetivo = new HBox(10);
        zonaObjetivo.setAlignment(Pos.CENTER);
        Label lblObjetivo = new Label("Seleccionar Objetivo:");
        lblObjetivo.setTextFill(Color.WHITE);

        selectorEnemigos = new ComboBox<>();
        selectorEnemigos.setPromptText("Elige un enemigo");
        zonaObjetivo.getChildren().addAll(lblObjetivo, selectorEnemigos);

        // 3. Log de Texto
        log = new TextArea("¡La batalla comienza!\n");
        log.setEditable(false);
        log.setPrefHeight(150);
        log.setStyle("-fx-control-inner-background: black; -fx-text-fill: #00ff00;");

        // 4. Estadísticas del Jugador (Vida y Experiencia)
        Label nombreJugador = new Label(jugador.getNombre() + " (" + jugador.getClass().getSimpleName() + ")");
        nombreJugador.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        nombreJugador.setTextFill(Color.WHITE);

        barraVidaJugador = new ProgressBar(1.0);
        barraVidaJugador.setPrefWidth(300);
        barraVidaJugador.setStyle("-fx-accent: #e74c3c;");

        labelVidaTexto = new Label();
        labelVidaTexto.setTextFill(Color.WHITE);

        barraXpJugador = new ProgressBar(0.0);
        barraXpJugador.setPrefWidth(300);
        barraXpJugador.setStyle("-fx-accent: #f1c40f;");

        labelXpTexto = new Label();
        labelXpTexto.setTextFill(Color.LIGHTGOLDENRODYELLOW);

        // 5. Botones de Acción Estándar (Inicializados en la variable global)
        botonesPrincipales = new HBox(10);
        botonesPrincipales.setAlignment(Pos.CENTER);

        Button btnAtacar = new Button("ATACAR");
        btnAtacar.setOnAction(e -> procesarAccion("atacar"));

        Button btnPasiva = new Button("PASIVA");
        btnPasiva.setOnAction(e -> procesarAccion("pasiva"));

        Button btnItem = new Button("USAR ESTUS");
        btnItem.setOnAction(e -> procesarAccion("item"));

        botonesPrincipales.getChildren().addAll(btnAtacar, btnPasiva, btnItem);

        // 6. Zona de Magia (Exclusiva para el Mago)
        zonaMagia = new HBox(10);
        zonaMagia.setAlignment(Pos.CENTER);

        if (jugador instanceof MAGO) {
            MAGO mago = (MAGO) jugador;
            Label lblMagia = new Label("Hechizos:");
            lblMagia.setTextFill(Color.WHITE);

            selectorHechizos = new ComboBox<>();
            selectorHechizos.getItems().addAll(mago.getLibroDeHechizos());
            selectorHechizos.setPromptText("Elige un hechizo");

            selectorHechizos.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(Hechizos item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? "" : item.getNombreHechizo() + " (Coste: " + item.getCosteMana() + ")");
                }
            });
            selectorHechizos.setButtonCell(selectorHechizos.getCellFactory().call(null));

            Button btnLanzarHechizo = new Button("LANZAR MAGIA");
            btnLanzarHechizo.setOnAction(e -> procesarAccion("magia"));

            zonaMagia.getChildren().addAll(lblMagia, selectorHechizos, btnLanzarHechizo);
        }

        // Carga e inicialización visual de estadísticas
        actualizarUI();

        layout.getChildren().addAll(
                contenedorEnemigos,
                zonaObjetivo,
                log,
                nombreJugador,
                barraVidaJugador,
                labelVidaTexto,
                barraXpJugador,
                labelXpTexto,
                botonesPrincipales,
                zonaMagia
        );
    }

    private void procesarAccion(String tipoAccion) {
        if (!jugador.estaVivo() || batalla.getEnemigos().isEmpty()) return;

        Enemigo objetivo = selectorEnemigos.getValue();

        if ((tipoAccion.equals("atacar") || tipoAccion.equals("magia")) && objetivo == null) {
            log.appendText("\n[!] Debes seleccionar un enemigo de la lista primero.");
            return;
        }

        switch (tipoAccion) {
            case "atacar":
                String resultadoAtaque = batalla.turnoJugador(jugador, objetivo);
                log.appendText("\n" + resultadoAtaque);
                break;

            case "pasiva":
                log.appendText("\n--- Usando Habilidad Pasiva ---");
                jugador.activarPasiva();
                if (jugador instanceof MAGO) {
                    log.appendText("\n¡Meditación completada! Has recuperado usos de magia.");
                } else if (jugador instanceof TANK) {
                    log.appendText("\n¡Segundo aire activado! Recuperaste salud.");
                } else if (jugador instanceof GUERRERO) {
                    log.appendText("\n¡Furia de Guerrero activada! Tu poder de ataque aumentó.");
                }
                break;

            case "item":
                log.appendText("\n--- Bebiendo Frasco de Estus ---");
                jugador.usarEstus();
                if (jugador.estus >= 0) {
                    log.appendText("\nTe has curado. Frascos restantes: " + jugador.estus);
                } else {
                    log.appendText("\n¡No te quedan frascos de Estus!");
                }
                break;

            case "magia":
                Hechizos hechizoSeleccionado = selectorHechizos.getValue();
                if (hechizoSeleccionado == null) {
                    log.appendText("\n[!] Por favor, selecciona un hechizo de la lista desplegable.");
                    return;
                }

                MAGO mago = (MAGO) jugador;
                int coste = hechizoSeleccionado.getCosteMana();

                if (mago.usosMagia >= coste) {
                    mago.usosMagia -= coste;
                    log.appendText("\n--- Lanzando Magia ---");
                    log.appendText("\n" + mago.getNombre() + " utiliza " + hechizoSeleccionado.getNombreHechizo() + ".");
                    hechizoSeleccionado.aplicarEfecto(mago, objetivo);

                    if (!objetivo.estaVivo()) {
                        log.appendText("\n" + jugador.ganarExp(objetivo.getXpOtorgada()));
                    }
                } else {
                    log.appendText("\n[!] No tienes suficiente magia (" + mago.usosMagia + "/" + coste + " necesarios) para lanzar " + hechizoSeleccionado.getNombreHechizo() + ".");
                }
                break;
        }

        // Limpieza interna inmediata en el modelo antes del contraataque
        batalla.getEnemigos().removeIf(e -> !e.estaVivo());

        // Forzar actualización visual para capturar los cambios de XP inmediatamente
        actualizarUI();

        // Turno de respuesta de los enemigos sobrevivientes
        if (!batalla.getEnemigos().isEmpty() && jugador.estaVivo()) {
            String resultadoEnemigos = batalla.turnoEnemigos(jugador);
            log.appendText("\n" + resultadoEnemigos);

            // Volver a actualizar por si el jugador recibió daño
            actualizarUI();
        }
    }

    private void actualizarUI() {
        if (batalla == null || jugador == null) return;

        // Limpiar caídos del modelo
        batalla.getEnemigos().removeIf(e -> !e.estaVivo());

        // 1. Sincronizar barra y textos de Experiencia (Almas)
        int xp = jugador.getXpActual();
        int lvl = jugador.getNivel();
        double porcentajeXp = (double) xp / 100.0;

        if (barraXpJugador != null) {
            barraXpJugador.setProgress(porcentajeXp);
        }
        if (labelXpTexto != null) {
            labelXpTexto.setText("Nivel: " + lvl + " | Almas (XP): " + xp + "/100");
        }

        // 2. Sincronizar barra y textos de Vida/Maná
        if (barraVidaJugador != null) {
            double porcentajeVida = (double) jugador.getVida() / jugador.getVidaMax();
            barraVidaJugador.setProgress(porcentajeVida);
        }

        if (labelVidaTexto != null) {
            if (jugador instanceof MAGO) {
                MAGO m = (MAGO) jugador;
                labelVidaTexto.setText("HP: " + jugador.getVida() + "/" + jugador.getVidaMax() +
                        " | MP: " + m.usosMagia + "/" + m.maxUsos);
            } else {
                labelVidaTexto.setText("HP: " + jugador.getVida() + "/" + jugador.getVidaMax());
            }
        }

        // 3. Re-renderizar lista e indicadores de enemigos activos
        if (contenedorEnemigos != null && selectorEnemigos != null) {
            Enemigo objetivoSeleccionado = selectorEnemigos.getValue();
            contenedorEnemigos.getChildren().clear();
            selectorEnemigos.getItems().clear();

            for (Enemigo e : batalla.getEnemigos()) {
                selectorEnemigos.getItems().add(e);

                VBox box = new VBox(5);
                box.setAlignment(Pos.CENTER);
                Label labelEnemigo = new Label(e.getNombre() + " (" + e.getVida() + "/" + e.getVidaMax() + " HP)");
                labelEnemigo.setTextFill(Color.WHITE);

                ProgressBar barraEnemigo = new ProgressBar((double) e.getVida() / e.getVidaMax());
                barraEnemigo.setStyle("-fx-accent: #c0392b;");

                box.getChildren().addAll(labelEnemigo, barraEnemigo);
                contenedorEnemigos.getChildren().add(box);
            }

            if (batalla.getEnemigos().contains(objetivoSeleccionado)) {
                selectorEnemigos.setValue(objetivoSeleccionado);
            } else if (!batalla.getEnemigos().isEmpty()) {
                selectorEnemigos.setValue(batalla.getEnemigos().get(0));
            }
        }

        // 4. CONTROL FLUIDO DE FIN DE COMBATE (EVITA EL NULLPOINTER)
        if (!jugador.estaVivo()) {
            log.appendText("\n--- HAS SIDO DERROTADO ---");
            cambiarBotonAvanzar("REINTENTAR DESDE LA HOGUERA");
        } else if (batalla.getEnemigos().isEmpty()) {
            log.appendText("\n--- ¡VICTORIA EN COMBATE! ---");
            cambiarBotonAvanzar("AVANZAR A LA SIGUIENTE SALA");
        }
    }

    // Método seguro que reemplaza los botones de combate por el botón de navegación al orquestador principal
    private void cambiarBotonAvanzar(String textoBoton) {
        if (botonesPrincipales != null) {
            botonesPrincipales.getChildren().clear();
            Button btnProgreso = new Button(textoBoton);

            if (textoBoton.contains("VICTORIA") || textoBoton.contains("AVANZAR")) {
                btnProgreso.setStyle("-fx-base: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
            } else {
                btnProgreso.setStyle("-fx-base: #7f8c8d; -fx-text-fill: white;");
            }

            // Al pulsar, invoca de forma segura la transición de salas en MenuPrincipal2
            btnProgreso.setOnAction(e -> juegoPrincipal.irASiguienteSala());
            botonesPrincipales.getChildren().add(btnProgreso);
        }

        // Bloquear selectores para que no se puedan lanzar acciones fuera de combate
        if (selectorEnemigos != null) selectorEnemigos.setDisable(true);
        if (selectorHechizos != null) selectorHechizos.setDisable(true);
    }

    public VBox getLayout() { return layout; }
}