package main;

import Popups.DecisionPanel;
import Popups.WinningPopupPanel;
import animals.Animal;
import board.BoardSetup;
import board.CaveCardManager;
import board.ChitCardManager;
import board.VolcanoCardManager;
import buttons.ExitButton;
import buttons.InfoButton;
import buttons.MusicButton;
import components.GameComponent;
import components.Token;
import fonts.Display;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * The {@code Game} class represents the main logic and user interface for the Fiery Dragons game.
 * It extends {@link JFrame} to provide a graphical user interface and manages game components,
 * player tokens, and the game flow.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class Game extends JFrame implements Runnable{

    /**
     * the number of players in the game.
     **/
    private int number_of_players;

    /**
     * the list of player tokens.
     **/
    private ArrayList<Token> players;

    /** the list of tokens in the game.
     **/
    private ArrayList<Token> tokens;

    /**
     * the setup for the game board
     **/
    private BoardSetup boardSetup;

    /**
     * manages chit cards for the game
     **/
    private ChitCardManager chitCardManager;

    /**
     * manages volcano cards for the game
     **/
    private VolcanoCardManager volcanoCardManager;

    /**
     * manages cave cards for the game
     **/
    private CaveCardManager caveCardManager;

    /**
     *  computes chits movement
     */
    private MovementManager movementManager;

    /** winner of the game
     * */
    private Token winner;

    /**
     * list of player's names
     **/
    private ArrayList<String> names;

    /**
     * label to indicate next player
     **/
    private JLabel currentPlayerLabel = new JLabel();

    /**
     * label to indicate player chooses to continue its turn or not
     **/
    private JLabel turnLabel = new JLabel();

    /**
     * list of dragon tokens' colours
     **/
    private ArrayList<String> colour = new ArrayList<>();

    /**
     * a flag to indicate special effects of a flipped chit card is applied to the token
     **/
    private boolean executeSpecial;

    /**
     * a complete map path for tokens to move on it
     **/
    private Map<Integer, ArrayList<GameComponent> > completeMap;
    /**
     * store current player's token
     */
    private Token currentToken;

    private int cardNo;

    private int tilesNo;

    /**
     * Constructor
     */
    public Game() {
        // Default constructor for loading game state
    }
    /**
     * Constructs a new {@code Game} instance, initializing the game state and user interface.
     */
    public Game(ArrayList<String> names, int cardNo, int tilesNo){

        this.players = new ArrayList<>();
        this.names = names;
        this.cardNo = cardNo;
        this.tilesNo = tilesNo;
        startGame();
        setupBoard();

        this.setBackground(Color.ORANGE);
        this.setSize(1080,720);
        this.setTitle("Fiery Dragons");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // run game in a separate worker
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                Game.this.run();
                return null;
            }
        };

        this.setVisible(true);

        this.number_of_players = 0;
        this.tokens = caveCardManager.getShuffledToken();
        this.movementManager = new MovementManager();
        this.executeSpecial = false;

        // setup game
        setupGame();

        // run game
        worker.execute();
    }

    /**
     * A getter to get the ChitCard manager.
     *
     * @return the ChitCard manager.
     */
    public ChitCardManager getChitCardManager() {
        return this.chitCardManager;
    }

    /**
     * Starts the game by initializing player tokens and setting up the game.
     */
    public void startGame(){
        colour.add("Blue");
        colour.add("White");
        colour.add("Yellow");
        colour.add("Green");
        Collections.shuffle(colour);
        number_of_players = this.names.size();
        for (int i = 0; i < number_of_players; i++) {
            players.add(new Token(colour.get(i)));
        }
    }

    /**
     * Checks if any player has won the game.
     *
     * @return {@code true} if a player has won, {@code false} otherwise.
     */
    public boolean checkWin(){
        for (Token token: tokens){
            if (token.getCurrentSteps() == token.getPath()+2) {
                this.winner = token;
                return true;
            }
        }
        return false;
    }

    /**
     * Validates the flipped chit card for the given token and calculates its movement.
     *
     * @param token the token to validate the chit card for.
     * @return the new position of the token or a special value indicating no movement.
     */

    public int validateFlippedChit(Token token){
        if(getChitCardManager().getFlippedAnimal() == null || getChitCardManager().getFlippedChit() == null) {
            return 99;
        }
        try {
            Thread.sleep(500); // Sleep for 500 milliseconds (0.5 second)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Animal animal = getChitCardManager().getFlippedAnimal();
        String animalName = animal.getAnimalName();
        String currentPositionAnimal;
        if (token.isInCave()){
            currentPositionAnimal = this.completeMap.get(token.getPosition()).get(1).getAnimal().getAnimalName();
        } else {
            currentPositionAnimal = this.completeMap.get(token.getPosition()).get(0).getAnimal().getAnimalName();
        }
        int movement = getChitCardManager().getChitMovement();
        int newPosition = 999;
        System.out.println(currentPositionAnimal);
        if (Objects.equals(currentPositionAnimal, animalName)) {
            newPosition = movementManager.handleForwardMovement(token, movement, completeMap);
        } else if (animal.isBackwardable()) {
            newPosition = movementManager.handlePirateMovement(token, movement, completeMap);
            getChitCardManager().clearAnimal();
        }
        else if (animal.isHasSpeciality()) {
            newPosition = movementManager.handleBearMovement(token, tokens, completeMap, caveCardManager, boardSetup);
            if (newPosition != 999){
                executeSpecial = true;
            }
        } else if (animal.isHasAction()) {
            int chosen = animal.performAction();
            if (chosen == 3){
                executeSpecial = true;
            }
            newPosition = movementManager.handleChance(chosen, token, players, completeMap, boardSetup, caveCardManager);

        }
        if (newPosition == 0){
            getChitCardManager().clearAnimal();
        }

        return newPosition;
    }

    /**
     * Sets up the initial game state
     */
    public void setupGame(){
        volcanoCardManager.setCompleteMap();

        for (Token currentToken : this.tokens) {
            int num = caveCardManager.parseCave(currentToken);
            currentToken.setPosition(volcanoCardManager.getCavePosition(num));
            // this is the complete path for all token including all caves
            volcanoCardManager.setCave(currentToken.getPosition(), currentToken.getBornCave());
            currentToken.setCavePosition(currentToken.getPosition());
            currentToken.setPath(volcanoCardManager.getNumberOfTiles());
        }
        this.completeMap = volcanoCardManager.getCompleteMap();

    }

    /**
     * Executes the main game loop, handling player turns and movements.
     */
    public void execute(){
        while (!checkWin()) {
            int currentPlayer = 0;
            while (currentPlayer < this.tokens.size()) {
                this.currentToken = this.tokens.get(currentPlayer);
                currentPlayerLabel.setText(this.names.get(currentPlayer)+"'s "+currentToken.getDragonColour() +" Dragon turn");
                int num = caveCardManager.parseCave(currentToken);
                // get movement for token to move
                enableChitPanel();
                int movement = validateFlippedChit(currentToken);
                // if the player can move either forward/backward
                if (movement != 99 && movement != 999) {
                    disableChitPanel();
                    GameComponent nextPosition;
                    if (movement == currentToken.getCavePosition()+volcanoCardManager.getNumberOfTiles() && Objects.equals(currentToken.getAnimalName(), completeMap.get(movement).get(1).getAnimal().getAnimalName())){
                        nextPosition = completeMap.get(movement).get(1);
                    } else {
                        nextPosition = completeMap.get(movement).get(0);
                    }
                    int previousPosition = currentToken.getPosition();
                    if (executeSpecial){
                        turnLabel.setText(this.names.get(currentPlayer) + " has switched its position.");
                        executeSpecial = false;
                        chitCardManager.clearAnimal();
                    } else{
                        completeMap.get(previousPosition).get(0).setOccupied(false);
                        nextPosition.setOccupied(true);
                    }
                    int index = 0;
                    if (movementManager.isHasWinner()){
                        index = 1;
                    }
                    movementManager.moveToken(currentToken, num, movement, index, completeMap, boardSetup);
                    movementManager.reset();
                    if (currentToken.getCurrentSteps() == currentToken.getPath()+2) {
                        break;
                    }
                    // if all chits are uncovered
                    if (chitCardManager.getChitList().size() == chitCardManager.getNumberOfChits()) {
                        JOptionPane.showOptionDialog(this, "You have uncovered all chits !", "Full", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                        turnLabel.setText("");
                        chitCardManager.setFlipBack(true);
                        try {
                            Thread.sleep(300); // Sleep for 1000 milliseconds (1 second)
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        currentPlayer++;
                    } else {
                        if (!new DecisionPanel().takingDecision()) {
                            turnLabel.setText("");
                            chitCardManager.setFlipBack(true);
                            try {
                                Thread.sleep(500); // Sleep for 500 milliseconds (0.5 second)
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            currentPlayer++;
                        } else {
                            turnLabel.setText("");
                            turnLabel.setText(this.names.get(currentPlayer) + " has continued its turn.");
                            chitCardManager.setFlipBack(false);
                            chitCardManager.clearAnimal();
                        }
                    }

                } else if (movement == 999) {
                    disableChitPanel();
                    turnLabel.setText("");
                    chitCardManager.setFlipBack(true);
                    try {
                        Thread.sleep(300); // Sleep for 1000 milliseconds (1 second)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentPlayer++;
                }

            }
        }
        new WinningPopupPanel(winner, this).takingDecision();
    }

    /**
     * Sets up the game by initializing the managers for chit cards, volcano cards, and cave cards,
     * and adds the game components to the game board.
     */
    public void setupBoard(){
//        // set number of tiles not more than 5 (predefined)
//        int minTileNumber = 3;
//        int maxTileNumber = 4;
//        int tilesNumbers = new Random().nextInt(minTileNumber, maxTileNumber);
//
//        // predefined card number
//        int minCardNumber = 8;
//        // default volcano card number
//        int maxCardNumber = 9;
//        int cardNumber = new Random().nextInt(minCardNumber, maxCardNumber);

        this.chitCardManager = new ChitCardManager();
        this.chitCardManager.chitCardSetup();
        volcanoCardManager = new VolcanoCardManager(chitCardManager.getBoardSetup(), tilesNo, cardNo);
        volcanoCardManager.volcanoCardSetup();
        volcanoCardManager.setUpCavePosition();
        ArrayList<Point> pts = volcanoCardManager.getCavePoints();
        System.out.println(pts.size());
        caveCardManager = new CaveCardManager(volcanoCardManager.getBoardSetup(), this.players, volcanoCardManager.getCavePoints());
        caveCardManager.caveSetup();

        currentPlayerLabel.setFont(new Display().getFont().deriveFont(Font.BOLD, 24f));
        currentPlayerLabel.setBackground(null);
        currentPlayerLabel.setBounds(15,15,500,35);
        currentPlayerLabel.setForeground(Color.black);

        turnLabel.setFont(new Display().getFont().deriveFont(Font.BOLD, 16f));
        turnLabel.setBackground(null);
        turnLabel.setBounds(15,50,500,25);
        turnLabel.setForeground(Color.black);

        InfoButton infoButton = new InfoButton(this);
        MusicButton musicButton = new MusicButton();
        ExitButton exitButton = new ExitButton(this);

        this.add(infoButton);
        this.add(musicButton);
        this.add(exitButton);

        this.add(currentPlayerLabel);
        this.add(turnLabel);
        this.add(chitCardManager.getBoardSetup());
        this.add(volcanoCardManager.getBoardSetup());
        this.add(caveCardManager.getBoardSetup());

        boardSetup = caveCardManager.getBoardSetup();
    }

    /**
     * Disable ChitCard to be flipped.
     *
     **/
    public void disableChitPanel(){
        chitCardManager.disableChitPanel(true);
    }

    /**
     * Enable ChitCard to be flipped.
     *
     **/
    public void enableChitPanel(){
        chitCardManager.disableChitPanel(false);
    }



    /**
     * Apply the game state which is readed from json.
     *
     * @param gameState an instance of GameState
     */
    public void applyGameState(GameState gameState) {
        this.players = gameState.getPlayers();
        this.completeMap = gameState.getCompleteMap();
        this.currentToken = gameState.getCurrentToken();
        this.names = gameState.getNames();
        System.out.println("Game loaded from save state");
        setupBoard();
        setupGame();
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        this.execute();
    }
}
