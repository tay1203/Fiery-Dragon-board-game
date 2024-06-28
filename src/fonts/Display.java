package fonts;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * A Display class is to set new font's display for the UI of Fiery Dragons.
 *
 * Created by:
 * @author Tay Ming Hui
 */
public class Display {

    /**
     * new font's resources
     */
    InputStream is = getClass().getResourceAsStream("IrishGrover-Regular.ttf");

    /**
     * an instance of Font to render text in visible way
     */
    Font font;

    /**
     * Constructor of the Display class.
     *
     * @throws RuntimeException if FontFormat or I/O exception is occurred during runtime
     */
    public Display(){
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method to get a new font to be displayed.
     *
     * @return a new font to be displayed on Fiery Dragons' UI
     */
    public Font getFont() {
        return font;
    }
}
