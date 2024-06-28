package buttons;

import fonts.Display;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A {@code InfoButton} class represents a button for player to click and show the game rules of Fiery Dragons game.
 * It extends {@code JPanel} to render the button
 * and implements {@code MouseListener} to enable mouse interaction with {@code InfoButton},
 * allowing for the execution of actions.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class InfoButton extends JPanel implements MouseListener {

    /**
     * game frame
     */
    JFrame parent;

    /**
     * Constructor of the InfoButton class.
     *
     * @param parent game frame
     */
    public InfoButton(JFrame parent) {
        this.parent = parent;
        JLabel b = new JLabel();
        b.setForeground(Color.WHITE);
        b.setText("?");
        Font font = new Display().getFont().deriveFont(Font.PLAIN, 60);
        b.setFont(font);
        b.setOpaque(false);
        this.setOpaque(false);
        this.setBounds(950, 40, 70,70);
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
        String message = """
                
                On Your Turn:
                    - Uncover a dragon card
                    - If it shows the same animal as your current square:
                       Move your dragon the number of animals shown clockwise.
                       You can choose to continue turn or end turn.
                    - If it shows a different animal:
                       Your turn has been ended.
                    - If it shows pirate dragon(s):
                       Move backwards that number of spaces
                Rules:
                    1. Only one dragon per square.
                       You are not allowed to move if the target square is occupied.
                    2. Land exactly in cave to win.
                       If your moves pass your cave, you are not allowed to move.
                    3. Game Ends when a dragon reaches its cave.
                
                """;
        JOptionPane.showConfirmDialog(parent, message, "Information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
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
     * Paint the buttons.InfoButton component.
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
