package components;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *  This is a {@code Token} class representing the player in the game.
 *
 * Created by:
 * @author Tay Ming HUi
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class Token extends GameComponent {


    /**
     * the colour of the Token
     */
    private String dragonColour;

    /**
     * the position of the Token
     */
    private int position;

    /**
     * the Token's image icon
     */
    private ImageIcon tokenImage;

    /**
     * the point of the Token's image
     */

    private Point imageCorner;

    /**
     * an instance of CaveCard
     */
    private CaveCard cave;

    /**
     * a list to store the Token's paths
     */

    private int path;

    /**
     * a flag to record the ChitCard is flipped or not
     */
    private boolean hasFlipped;

    /**
     * current steps of Token
     */
    private int currentSteps;

    boolean inCave;

    int cavePosition;

    /**
     * A constructor of a dragon token.
     *
     * @param dragonColour the colour of the dragon token.
     */
    public Token(String dragonColour) {
        setDragonColour(dragonColour);
        setPosition(0);
        this.imageCorner = new Point(0, 0);
        setHasFlipped(false);
        this.currentSteps = 0;
        this.inCave = true;
        this.cavePosition = 0;
    }

    /**
     * A toString method to show the dragon token's colour.
     *
     * @return a dragon colour as String
     */
    public String toString() {
    return dragonColour ;
    }

    /**
     * A getter to get the dragon colour.
     *
     * @return a dragon colour as String
     */
    public String getDragonColour() {
        return dragonColour;
    }

    /**
     * A getter to get the animal name.
     *
     * @return an animal name as String
     */
    public String getAnimalName() {
        if (Objects.equals(dragonColour, "White")){
            return "salamander";
        } else if (Objects.equals(dragonColour, "Blue")) {
            return "bat";
        } else if (Objects.equals(dragonColour, "Green")){
            return "babydragon";
        } else if (Objects.equals(dragonColour, "Yellow")){
            return "spider";
        }
        return "";
    }

    /**
     * A setter to set the initial cave where the dragon token born.
     *
     * @param cave the initial cave where the dragon token born
     */
    public void setBornCave(CaveCard cave){
        this.cave = cave;
    }

    /**
     * A getter to get the initial cave where the dragon token born.
     *
     * @return the initial cave where the dragon token born
     */
    public CaveCard getBornCave() {
        return cave;
    }

    public boolean isInCave() {
        return inCave;
    }

    public void setInCave(boolean inCave) {
        this.inCave = inCave;
    }

    public int getCavePosition() {
        return cavePosition;
    }

    public void setCavePosition(int cavePosition) {
        this.cavePosition = cavePosition;
    }

    /**
     * A setter to record the player flipped the dragon card or not.
     *
     * @param flip the boolean flag to record the player flipped the dragon card or not
     */
    public void setHasFlipped(boolean flip){
        this.hasFlipped = flip;
    }

    /**
     * A setter to record the path of the dragon token.
     *
     * @param path a list that records the GameComponent(volcano card and cave) as a dragon token's travel path
     */
    public void setPath(int path){
        this.path = path;
    }

    /**
     * A getter to return the dragon token's travel path as list.
     *
     * @return the list of paths for dragon token to travel
     */
    public int getPath() {
        return this.path;
    }

    /**
     * A setter to set the dragon token's colour.
     *
     * @param colour the dragon token's colour as String
     */
    public void setDragonColour(String colour){
        this.dragonColour = colour;
    }

    /**
     * A getter to get the current position of the dragon token (accumulated steps).
     *
     * @return an int of current position of Token
     */
    public int getPosition() {
        return position;
    }

    /**
     * A setter to set the current position of the dragon token after flipping a valid dragon card.
     *
     * @param position an int of the maximum step shown on the dragon card
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * A getter to set the current steps of the Token after flipping a valid ChitCard.
     *
     * @return the current steps of the Token taken as integer
     */
    public int getCurrentSteps() {
        return currentSteps;
    }

    /**
     * A setter to set the current steps of the Token taken.
     *
     * @param steps steps taken by Token as integer
     */
    public void setCurrentSteps(int steps) {
        this.currentSteps = this.getCurrentSteps() + steps;
    }

    public void setImageCorner(Point imageCorner) {
        this.imageCorner = imageCorner;
    }

    /**
     * Paint the Token component.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String filename = "/images/"+this.getDragonColour().toLowerCase()+"_dragon.png";
        tokenImage = new ImageIcon(new ImageIcon(getClass().getResource(filename)).getImage().getScaledInstance(45,65,Image.SCALE_SMOOTH));
        tokenImage.paintIcon(null,g,(int)(this.imageCorner.getX()), (int)(this.imageCorner.getY()));


    }




}



