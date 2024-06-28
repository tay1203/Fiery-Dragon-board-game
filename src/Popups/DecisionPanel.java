package Popups;

import javax.swing.*;
import java.awt.*;

/**
 * {@code DecisionPanel} class is a panel that extends {@link JOptionPane} to render the options for the current player
 * to decide whether they want to continue the current turn.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class DecisionPanel extends JOptionPane{

    /**
     * result to be returned to the Game
     */
    private final int result;

    /**
     * Constructor of the DecisionPanel class.
     *
     */
    public DecisionPanel() {
        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/images/dragon.png")).getImage().getScaledInstance(45,35, Image.SCALE_SMOOTH));
        // display option for current player to decide
        this.result = showOptionDialog(null,"Do you want to continue?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, null, null);

    }

    /**
     * Return the decision made by the current player after they select the options displayed on the DecisionPanel.
     *
     * @return the decision made by the current player as a boolean.
     */
    public boolean takingDecision() {
        return result == JOptionPane.YES_OPTION;
    }
}