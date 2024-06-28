package animals;

/**
 * A class representing {@code PirateDragon}, which is one of {@code Animal},
 * is composed of game components of Fiery Dragons game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class PirateDragon extends Animal {

    /**
     * Constructor for the PirateDragon class.
     *
     */
    public PirateDragon(){

        super("piratedragon",2);
        // can cause token to move backwards
        setBackwardable(true);

    }

}
