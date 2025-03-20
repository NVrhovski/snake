package com.neyenvrhovski.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    public static void playSound(String soundFilePath) {
        try {
            // Cargar el archivo de audio como un InputStream
            InputStream audioSrc = SoundPlayer.class.getClassLoader().getResourceAsStream(soundFilePath);
            if (audioSrc == null) {
                throw new IllegalArgumentException("Archivo de sonido no encontrado: " + soundFilePath);
            }

            // Convertir el InputStream a un AudioInputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);

            // Obtener un Clip y cargar el audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Reproducir el sonido
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
