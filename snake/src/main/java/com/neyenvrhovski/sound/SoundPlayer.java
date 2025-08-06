package com.neyenvrhovski.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    public static void playSound(String soundFilePath) {
        try (
            InputStream audioSrc = SoundPlayer.class.getClassLoader().getResourceAsStream(soundFilePath);
            AudioInputStream audioStream = audioSrc != null ? AudioSystem.getAudioInputStream(audioSrc) : null
        ) {
            if (audioStream == null) {
                System.err.println("Archivo de sonido no encontrado: " + soundFilePath);
                return;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Liberar recursos cuando termine de sonar
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al reproducir el sonido: " + soundFilePath);
            e.printStackTrace();
        }
    }
}