package buttons;

import fonts.Display;
import main.Game;
import main.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * A {@code ExitButton} class represents a button for player to exit the Fiery Dragon Game.
 * It extends {@code JPanel} to render the button
 * and implements {@code MouseListener} to enable mouse interaction with {@code ExitButton},
 * allowing for the execution of actions.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class ExitButton extends JPanel implements MouseListener {

    /**
     * game frame
     */
    JFrame parent;

    /**
     * Constructor of the ExitButton class.
     *
     */
    public ExitButton(JFrame parent) {
        this.parent = parent;
        JLabel b = new JLabel();
        b.setForeground(Color.RED);
        b.setText("X");
        Font font = new Display().getFont().deriveFont(Font.PLAIN, 60);
        b.setFont(font);
        b.setOpaque(false);
        this.setOpaque(false);
        this.setBounds(950, 200, 70,70);
        this.setLayout(new GridBagLayout());
        this.add(b, new GridBagConstraints());
        addMouseListener(this);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int result = JOptionPane.showConfirmDialog(parent, "Do you want to exit?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
        if (result == JOptionPane.YES_OPTION){
            parent.dispose();
            new HomePage();
        } 
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
     * Paint the ExitButton component.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // set new arc for info button
        Dimension arcs = new Dimension(68, 68);
        int width = 68;
        int height = 68;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 169, 57));
        g2d.fillRoundRect(0,0,width,height, arcs.width, arcs.height);

    }
}

