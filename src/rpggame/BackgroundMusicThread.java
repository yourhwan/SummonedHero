package rpggame;

import sun.applet.Main;

import javax.sound.sampled.*;
import java.io.*;

public class BackgroundMusicThread extends Thread {
    private String filePath;

    public BackgroundMusicThread(String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        try {
            // Get the absolute path of the WAV file
            String audioFilePath = Main.class.getResource("/" + filePath).getPath();

            // Load the audio file
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
