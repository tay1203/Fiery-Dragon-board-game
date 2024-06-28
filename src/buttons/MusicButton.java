package buttons;

import fonts.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A {@code MusicButton} class represents a button for player to mute or on the sound.
 * It extends {@code JPanel} to render the button
 * and implements {@code MouseListener} to enable mouse interaction with {@code MusicButton},
 * allowing for the execution of actions.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class MusicButton extends JPanel implements MouseListener {

    /**
     * disable music label
     */
    JLabel disableLabel;

    /**
     * flag to indicate music button is clicked
     */
    boolean clicked;

    /**
     * Music player instance
     */
    private Music music;

    /**
     * Constructor of the MusicButton class.
     *
     */
    public MusicButton() {
        // set panel to be invisible
        this.setOpaque(false);
        this.setBounds(950, 120, 70, 70);

        this.disableLabel = new JLabel();
        this.disableLabel.setOpaque(false);
        this.disableLabel.setForeground(Color.RED);
        this.disableLabel.setFont(new Display().getFont().deriveFont(55f));
        this.disableLabel.setBounds(0, 0, 68, 68);
        disableLabel.setBackground(null);
        this.add(disableLabel);
        clicked = false;
        addMouseListener(this);

        // Initialize music player
        music = new Music("music.wav"); // Ensure this path is correct
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!clicked) {
            disableLabel.setText("/");
            clicked = true;
            music.stop();
        } else {
            disableLabel.setText("");
            clicked = false;
            music.play();
        }
        repaint();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Invoked when a mouse button has been released on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Invoked when the mouse enters a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Paint the buttons.MusicButton component.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // set new arc for music button
        Dimension arcs = new Dimension(68, 68);
        int width = 68;
        int height = 68;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!clicked) {
            g2d.setColor(new Color(0, 169, 57));
        } else {
            g2d.setColor(Color.gray);
        }
        g2d.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);

        String filename = "/images/music.png";
        Image musicImage = new ImageIcon(getClass().getResource(filename)).getImage();
        g.drawImage(musicImage, 3, 5, 60, 60, null);
    }
}
