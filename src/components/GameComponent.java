package components;

import animals.Animal;
import javax.swing.*;
import java.awt.*;

/**
 * {@code GameComponent} class is an abstract class that represents all the game components of Fiery Dragons game.
 * GameComponents including CaveCard, ChitCard, Token, and VolcanoCard.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public abstract class GameComponent extends JPanel{

    /**
     * an instance of Animal
     */
    private Animal animal;

    /**
     * point/position of a GameComponent
     */
    private Point point;

    /**
     * a flag to record the GameComponent is occupied or not
     */
    private boolean isOccupied;

    /**
     * A setter to set an Animal's instance on the GameComponent.
     *
     * @param animal an instance of Animal
     */
    public void setAnimal(Animal animal){
        this.animal = animal;
    }

    /**
     * A getter to get the instance of Animal.
     *
     * @return return the instance of Animal
     */
    public Animal getAnimal() {
        return this.animal;
    }

    /**
     * A method to get the image of GameComponent.
     *
     * @return return null
     */
    public ImageIcon getImage(){return null;}

    /**
     * A setter to set the point of GameComponents.
     *
     * @param point the point of CaveCard
     */
    public void setPoint(Point point) {
        this.point = point;
    }
    /**
     * A method to get the point/position of GameComponent.
     *
     * @return return the point of GameComponent
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Return the condition (true/false) to show whether the GameComponent is occupied or not.
     *
     * @return true/false to indicate the current state of GameComponent
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Set the condition (true/false) to show whether the GameComponent is occupied or not.
     *
     * @param occupied true/false to indicate the current state of GameComponent
     */
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
