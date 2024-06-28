package buttons;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * The {@code Music} class is to get music file as background music of Fiery Dragon Game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class Music {

    /**
     * a Clip instance
     **/
    private Clip clip;

    /**
     * a flag to indicate the music is played or not
     **/
    private boolean playing = false;

    /**
     * Constructor of Music class.
     *
     * @param filepath music file path
     **/

    public Music(String filepath) {
        try {
            URL url = getClass().getResource(filepath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to play the music.
     **/
    public void play() {
        if (clip != null && !playing) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            playing = true;
        }
    }

    /**
     * A method to stop the music.
     **/
    public void stop() {
        if (clip != null && playing) {
            clip.stop();
            playing = false;
        }
    }

}
