package board;

import animals.Animal;
import board.BoardSetup;
import board.ChitBoard;
import components.ChitCard;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code ChitCardManager} is a manager class to manage the ChitCard's setup.
 * It implements {@link MouseListener} to enable mouse interaction with {@code ChitCard},
 * allowing for the execution of actions.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class ChitCardManager implements MouseListener {

    /**
     * an instance of ChitBoard
     */
    private ChitBoard gameBoard;

    /**
     * an instance of BoardSetup
     */
    private BoardSetup boardSetup;

    /**
     * a map to store ChitCard images to be displayed along with their positions
     */
    private Map<JLabel, Integer> labelIndexMap;

    /**
     * an instance of Animal
     */
    private Animal animal;

    /**
     * number of movement shown on the ChitCard
     */
    private int movement;

    /**
     * a flipped ChitCard
     */
    private ChitCard flippedChit;

    /**
     * a list of ChitCards
     */
    private ArrayList<ChitCard> chitList;

    /**
     * label of a clicked ChitCard
     */
    private JLabel clickedChit;

    /**
     * a list of clicked ChitCards' label
     */
    private ArrayList<JLabel> clickedChitList;

    /**
     * flag to indicate the flipped card is disabled
     */
    private boolean disable;

    /**
     * flag to indicate the card is flipped
     */
    private boolean flipped;

    int numberOfChits;

    /**
     * Constructor of the ChitCardManager class.
     *
     */
    public ChitCardManager() {
        this.boardSetup = new BoardSetup();
        this.labelIndexMap = new HashMap<>();
        this.chitList = new ArrayList<>();
        this.clickedChitList = new ArrayList<>();
        this.disable = false;
        this.flipped = false;
        this.numberOfChits = 0;
    }

    /**
     * Configure the ChitCard on the game board.
     *
     */
    public void chitCardSetup(){
        gameBoard = new ChitBoard();
        gameBoard.configureChitBoard();
        this.numberOfChits =  gameBoard.getChits().size();
        for (int i = 0; i < gameBoard.getChits().size(); i++){
            JLabel chitcard = new JLabel();
            // add mouse listener to the chit card
            chitcard.addMouseListener(this);
            gameBoard.getChits().get(i).flipCard(false);
            chitcard.setIcon(gameBoard.getChits().get(i).getImage());
            boardSetup.getChitCardPanel().add(chitcard);
            labelIndexMap.put(chitcard, i);
        }

    }

    public int getNumberOfChits() {
        return numberOfChits;
    }

    /**
     * A getter to get the instance of Animal of a flipped ChitCard.
     *
     * @return an instance Animal of a flipped ChitCard
     */
    public Animal getFlippedAnimal() {
        return this.animal;
    }

    /**
     * A getter to get the movement of a ChitCard.
     *
     * @return movement of a ChitCard as integer
     */
    public int getChitMovement() {
        return this.movement;
    }

    /**
     * A getter to get a flipped ChitCard's instance.
     *
     * @return an instance of ChitCard of a flipped ChitCard
     */
    public ChitCard getFlippedChit() {
        return flippedChit;
    }

    /**
     * Set the instance of Animal of a flipped ChitCard.
     *
     * @param animal an instance of Animal on the ChitCard
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * Remove the instance of Animal of a flipped ChitCard.
     *
     */
    public void clearAnimal(){
        this.animal = null;
    }

    /**
     * Set the movement of a flipped ChitCard.
     *
     * @param movement the movement of flipped ChitCard as integer
     */
    public void setMovement(int movement) {
        this.movement = movement;
    }

    /**
     * Cover back all the uncovered ChitCards.
     *
     */
    public void flipBackChit() {
        // delay the flip back movement's time
        try {
            Thread.sleep(1000); // Sleep for 1000 milliseconds (1 second)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // cover up the chit card after the current turn of a player
        for (int i = 0; i < chitList.size(); i++) {
            chitList.get(i).flipCard(false);
            clickedChitList.get(i).setIcon(chitList.get(i).getImage());
            this.flippedChit = null;
            clearAnimal();
            setMovement(0);
        }
        this.chitList.clear();
        this.clickedChitList.clear();
        flipped = false;
    }

    /**
     * Set the flipped ChitCard to be covered.
     *
     */
    public void setFlipBack(boolean flip){
        if (!flip){
            flipped = false;
        }
        // check the chit card should be flipped back
        if (flip){
            flipBackChit();
        }
    }

    /**
     * Disable ChitCard to be flipped.
     *
     * @param able flag(true/false) to toggle the chit cards' status
     */
    public void disableChitPanel(boolean able){
        this.disable = able;
    }

    /**
     * A getter to get a list of ChitCard.
     *
     * @return a list of ChitCard
     */
    public ArrayList<ChitCard> getChitList() {
        return chitList;
    }

    /**
     * A getter to get the instance of board.BoardSetup.
     *
     * @return the instance of board.BoardSetup
     */
    public BoardSetup getBoardSetup(){
        return this.boardSetup;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!disable && !flipped) {
            clickedChit = (JLabel) e.getSource();
            int index = labelIndexMap.get(clickedChit);
            // card is uncovered
            if (gameBoard.getChits().get(index).isFlipped()) {
                clickedChit.setIcon(gameBoard.getChits().get(index).getImage());
                this.clickedChitList.add(clickedChit);
                this.flippedChit = gameBoard.getChits().get(index);
                this.chitList.add(flippedChit);
                this.setAnimal(gameBoard.getChits().get(index).getAnimal());
                this.setMovement(gameBoard.getChits().get(index).getMovement());
                flipped = true;
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
