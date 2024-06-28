package Popups;

import main.Game;
import main.HomePage;
import components.Token;
import javax.swing.*;

/**
 * {@code WinningPopupPanel} class is a panel to render the winning condition when
 * the player's token successfully reaches its cave.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class WinningPopupPanel {

    /**
     * result to be returned to Main.Game
     */
    private int result;

    /**
     * game frame
     */
    private Game parent;

    /**
     * Constructor of the Popups.WinningPopupPanel class.
     *
     * @param parent game frame
     * @param token an instance of Token
     */
    public WinningPopupPanel(Token token, Game parent){
        this.parent = parent;
        Object[] options = {"Start a new game", "Stay Here"};
        String winner = token.getDragonColour();
        String message = winner + " Dragon has reached its cave!";
        // show option pane for player to choose
        this.result = JOptionPane.showOptionDialog(null, message, "You win!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
    }

    /**
     * Decision made by the current player.
     *
     */
    public void takingDecision(){
        if (result == 0){
            parent.dispose();
            new HomePage();
        } else{
            parent.disableChitPanel();
        }
    }
}
