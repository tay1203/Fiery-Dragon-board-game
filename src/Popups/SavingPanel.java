package Popups;

import main.HomePage;
import javax.swing.*;
import java.awt.*;

/**
 * {@code SavingPanel} class is a panel to render the saving option for the current player to choose
 * whether they want to save the current progress or not.
 *
 * Created by:
 * @author  Koe Rui En
 *
 * Modified by:
 * @author Tay Ming Hui
 * @author Wong Jia Xuan
 */
public class SavingPanel {

    /**
     * result to be returned
     */
    private int result;

    /**
     * game frame
     */
    private JFrame gameFrame;

    /**
     * Constructor of the SavePanel class.
     *
     * @param parent game frame
     */
    public SavingPanel(JFrame parent){
        this.gameFrame = parent;
        Object[] options = {"Save", "Exit"};
        String message = "Do you want to save the current progress?\n(Your can save the progress if click save option)";
        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/images/dragon.png")).getImage().getScaledInstance(45,35, Image.SCALE_SMOOTH));
        // show option pane for player to choose
        this.result = JOptionPane.showOptionDialog(null, message, "Saving Game Progress", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, null);

        takingDecision();
    }

    /**
     * Decision made by the current player.
     *
     */
    public void takingDecision(){
        if (result == 0){
            // save game

        } else{
            // directly exit the game if exit  is chosen
            gameFrame.dispose();
            new HomePage();
        }
    }
}


