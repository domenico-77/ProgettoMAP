/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

/**
 *
 * @author mtubi
 */
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author domen
 */
public class ThreadMusica implements LineListener {

    private boolean playCompleted;
    private static Clip audioClip;

    public void play(String audioFilePath) {

        File audioFile = new File("./risorse/fileAudio.wav");

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);

            audioClip.start();

            audioClip.loop(1000);

            while (!playCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            audioClip.close();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }

    }

    public static void Music() {
        Thread m = new Thread(() -> {
            String audioFilePath = "./risorse/fileAudio.wav";
            ThreadMusica player = new ThreadMusica();
            player.play(audioFilePath);
        });
        m.setDaemon(true);
        m.start();
    }

  

    public static void setVolumeOn() {
        BooleanControl muteControl = (BooleanControl) audioClip.getControl(BooleanControl.Type.MUTE);
        muteControl.setValue(false);

    }

    public static void setVolumeOff() {
        BooleanControl muteControl = (BooleanControl) audioClip.getControl(BooleanControl.Type.MUTE);
        muteControl.setValue(true);
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.STOP) {
            playCompleted = true;
        }
    }

}
