package com.example.pruebas_de_fx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

/**
 * Gestor estático de música de fondo. Se encarga de cargar los
 * archivos de audio desde el classpath, reproducirlos en bucle y
 * detenerlos cuando corresponde.
 */
public class SoundManager {

    /** Reproductor de música actualmente activo (compartido por toda la app). */
    private static MediaPlayer mediaPlayer;

    /**
     * Reproduce en bucle el archivo de música de fondo indicado,
     * ubicado en el directorio de recursos {@code /music/}. Si ya hay
     * una pista sonando, se detiene antes de iniciar la nueva.
     *
     * @param fileName nombre del archivo de música (dentro de {@code /music/}).
     */
    public static void playBackgroundMusic(String fileName) {
        try {
            // Detener la música actual si hay una sonando
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            // CORRECCIÓN: Buscamos el archivo como un recurso del "classpath"
            String path = "/music/" + fileName;

            // Usamos el ClassLoader de la clase para obtener la URL interna
            URL resourceUrl = SoundManager.class.getResource(path);

            if (resourceUrl == null) {
                System.out.println("No se encontró el archivo de música en los recursos: " + path);
                return;
            }

            // Convertimos la URL interna a un formato de cadena que la clase Media entienda
            Media media = new Media(resourceUrl.toExternalForm());
            mediaPlayer = new MediaPlayer(media);

            // Configurar para que se repita indefinidamente (Loop)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // Ajustar volumen (0.0 a 1.0)
            mediaPlayer.setVolume(0.25);

            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error al reproducir el audio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Detiene la reproducción de música actual, si existe.
     */
    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
