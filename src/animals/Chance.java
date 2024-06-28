package animals;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A {@code Chance} class is a class that applied any chance effects to the player's dragon tokens
 * when the player flip on it.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class Chance extends Animal{

    /**
     * Constructor of Chance class.
     */
    public Chance() {
        super("chance", 1);
        this.setHasAction(true);
    }

    /**
     * A method to execute chance effects.
     */
    @Override
    public int performAction() {
        ArrayList<String> chances = new ArrayList<>();
        chances.add("DO NOTHING!!\nUh Oh! \nNothing here.");
        chances.add("ADVANCE TO MOVE FORWARD><\nSurprise! \nMove 2 steps forward.");
        chances.add("PIRATE DRAGON's HAUL T_T\nPirate Dragon looted your cave.\nMove 2 steps backward.");
        chances.add("HIDDEN FORTUNE^_^\nYou discovered a buried treasure.\nMove three steps forward.");
        chances.add("DRAGONQUAKE!!!\nEarthquake!!!\nAll dragons except you move backward 1 step.");

        int chosen = new Random().nextInt(chances.size());
        String display = chances.get(chosen);

        JOptionPane.showMessageDialog(null, display, "You are chosen!", JOptionPane.PLAIN_MESSAGE);

        return chosen;
    }
}
