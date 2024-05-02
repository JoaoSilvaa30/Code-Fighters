package game.players;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Sound {

    public Clip menu1;
    public Clip menu2;
    public Clip round1;

    public Sound() {
        try {
            // Print current working directory for debugging
            // Load and open audio files
            loadAudioFiles();
            // Open the clips
            openClips();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // Handle the exception as needed (e.g., log, display a message, exit the program)
        }
    }

    private void loadAudioFiles() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Load audio files using ClassLoader
        URL menu1Stream = Sound.class.getResource("/music/menu1.wav");
        URL menu2Stream = Sound.class.getResource("/music/menu2.wav");
        URL round1Stream = Sound.class.getResource("/music/round1.wav");

        menu1 = AudioSystem.getClip();
        menu2 = AudioSystem.getClip();
        round1 = AudioSystem.getClip();

        loadAudioFile(menu1Stream, menu1);
        loadAudioFile(menu2Stream, menu2);
        loadAudioFile(round1Stream, round1);

    }

    private void loadAudioFile(URL inputStream, Clip clip) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Don't close the InputStream here
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
        clip.open(audioStream);
    }

    private void openClips() throws LineUnavailableException {
        if (menu1 != null && menu2 != null && round1 != null) {
            menu1.open();
            menu2.open();
            round1.open();
        }
    }

    public void PlayMenu1() {
        playClip(menu1);
    }

    public void PlayMenu2() {
        playClip(menu2);
    }

    public void PlayRound1() {
        playClip(round1);
    }

    private void playClip(Clip clip) {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void decreaseVolume(Clip clip, float decreaseAmount) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float currentVolume = gainControl.getValue();
            float decreasedVolume = currentVolume - decreaseAmount;
            gainControl.setValue(decreasedVolume);
        }
    }
}


