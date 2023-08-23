package aplicacion;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Aplicacion {

    public static void main(String[] args) {
        Thread songThread = new Thread(() -> playSong("cancion.wav")); // Cambia "cancion.wav" por la ruta de tu archivo de audio
        Thread gifThread = new Thread(Aplicacion::showGif);

        songThread.start();
        gifThread.start();
    }

    public static void playSong(String fileName) {
        try {
            File audioFile = new File(fileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // Espera hasta que la canción termine de reproducirse
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showGif() {
        JFrame frame = new JFrame("GIF Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        ImageIcon gifIcon = new ImageIcon("animacion.gif"); // Cambia "animacion.gif" por la ruta de tu archivo GIF
        JLabel gifLabel = new JLabel(gifIcon);

        frame.getContentPane().add(gifLabel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

}
