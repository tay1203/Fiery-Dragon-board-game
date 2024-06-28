package animals;

/**
 * A class representing {@code Bear}, which is one of {@code Animal},
 * is composed of game components of Fiery Dragons game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class Bear extends Animal {

    /**
     * Constructor for the Bear class.
     *
     */
    public Bear() {

        super("bear",1);
        // bear has special ability to swap the position of the token, with the token that is closest to its position
        this.setHasSpeciality(true);

    }
}
