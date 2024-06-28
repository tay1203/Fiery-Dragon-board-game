package components;

import animals.Animal;
import javax.swing.*;
import java.awt.*;

/**
 * {@code ChitCard} class is one of the {@code GameComponent} of Fiery Dragons game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class ChitCard extends GameComponent {

    /**
     * a flag to record the ChitCard is flipped or not
     */
    private boolean isFlipped;

    /**
     * number of movement of the ChitCard
     */
    private int movement;

    /**
     * image of ChitCard
     */
    private Image chitCardImage;

    /**
     * Constructor of the ChitCard class.
     *
     * @param animal an instance of an Animal
     * @param movement number of movement of a ChitCard as integer
     */
    public ChitCard(Animal animal, int movement) {
        this.setAnimal(animal);
        this.movement = movement;

    }

    /**
     * A getter to get the movement's number of ChitCard.
     *
     * @return return the movement's number of ChitCard as integer
     */
    public int getMovement() {
        return this.movement;
    }

    /**
     * A method to revert the state of ChitCard.
     *
     * @param flip a flag to revert the state of ChitCard
     */
    public void flipCard(boolean flip){
        this.isFlipped = flip;
    }

    /**
     * Return the condition (true/false) to show whether the ChitCard is flipped or not.
     *
     * @return true/false to indicate the state of ChitCard
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * A method to get an image of a ChitCard.
     *
     * @return the image icon of a ChitCard
     */
    @Override
    public ImageIcon getImage(){
        // card is flipped
        if (isFlipped) {
            String name = "./images/" + getAnimal().toString() + "_" + this.getMovement() + ".png";
            chitCardImage = new ImageIcon(getClass().getResource("/images/" + this.getAnimal().toString() + "_" + this.getMovement() + ".png")).getImage();
            flipCard(false);
        } else {
            chitCardImage = new ImageIcon(getClass().getResource("/images/chitcardback.png")).getImage();
            flipCard(true);
        }
        chitCardImage = chitCardImage.getScaledInstance(52, 52, Image.SCALE_SMOOTH);
        return new ImageIcon(chitCardImage);
    }
}
