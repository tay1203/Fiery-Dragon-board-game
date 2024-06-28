package board;

import animals.Animal;
import animals.AnimalFactory;
import components.ChitCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * {@code ChitBoard} is a class that handle the initialisation of {@code Animal} for ChitCard's instance.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class ChitBoard {

    /**
     * a list of ChitCard's instances
     */
    private ArrayList<ChitCard> chits;

    /**
     * Constructor of the board.ChitBoard class.
     *
     **/
    public ChitBoard(){
        this.chits = new ArrayList<>();
    }

    /**
     * Return a list of instances of ChitCard.
     *
     * @return a list of instances of ChitCard
     */
    public ArrayList<ChitCard> getChits() {
        return this.chits;
    }

    /**
     * Initialise ChitCards with different types of Animals.
     *
     */
    public void createBoard(){
        // create chit cards' animals
        for (Animal animal: AnimalFactory.createChitCardAnimal()){
            // set the movement displayed on a chit card
            for(int i = 1; i <= animal.getMaxMovement(); i++ ){
                this.chits.add(new ChitCard(animal, i));
            }
        }
    }

    /**
     * Shuffle a list of ChitCard.
     *
     */
    public void shuffleChits(){
        Collections.shuffle(chits);
    }

    /**
     * Configure ChitCard components.
     *
     */
    public void configureChitBoard(){
        createBoard();
        shuffleChits();
    }

}
