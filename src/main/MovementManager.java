package main;

import board.BoardSetup;
import board.CaveCardManager;
import components.GameComponent;
import components.Token;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * {@code MovementManager} is a class to manage the movement of {@code Token} in the game,
 * including handling special movements and conditions like new chit cards and chance effects.
 */
public class MovementManager {

    /**
     * The index of the nearest player.
     */
    private int nearestPLayerIndex;

    /**
     * The token of the nearest player.
     */
    private Token nearestPLayer;

    /**
     * Indicates if the movement is backward.
     */
    private boolean isBackward;

    /**
     * The minimum distance to the nearest player.
     */
    private int minDistance;

    /**
     * Indicates if there is a winner.
     */
    private boolean hasWinner;

    /**
     * Indicates if the bear is involved in the movement.
     */
    private boolean isBear;

    /**
     * Constructs a MovementManager with default values.
     */
    public MovementManager(){
        this.nearestPLayer = null;
        this.isBackward = false;
        this.minDistance = -1;
        this.hasWinner = false;
        this.isBear = false;
    }

    /**
     * Checks if there is a winner.
     *
     * @return true if there is a winner, false otherwise
     */
    public boolean isHasWinner() {
        return hasWinner;
    }

    /**
     * Handles the forward movement of a token.
     *
     * @param token the token to move
     * @param movement the number of steps to move
     * @param completeMap the complete map of game components
     * @return the new position of the token, or 999 if the movement is invalid
     */
    public int handleForwardMovement(Token token, int movement, Map<Integer, ArrayList<GameComponent>> completeMap){
        if (token.getCurrentSteps() + movement > token.getPath() + 2) {
            return 999;
        }

        if (token.getCurrentSteps() + movement == token.getPath() + 2) {
            token.setCurrentSteps(movement);
            this.hasWinner = true;
            return token.getPosition() + movement - 1;
        }

        if (token.isInCave()) {
            movement--;
            token.setInCave(false);
            token.setCurrentSteps(1);
        }

        if (!completeMap.get(token.getPosition() + movement).get(0).isOccupied() || this.isBear) {
            if (token.getCurrentSteps() < 0) {
                if (token.getCurrentSteps() + movement < 0) {
                    token.setCurrentSteps(movement);
                } else {
                    token.setCurrentSteps(movement + 1);
                }
            } else {
                token.setCurrentSteps(movement);
            }

            return token.getPosition() + movement;
        }
        return 999;
    }

    /**
     * Handles the pirate movement of a token.
     *
     * @param token the token to move
     * @param movement the number of steps to move backward
     * @param completeMap the complete map of game components
     * @return the new position of the token, or 999 if the movement is invalid
     */
    public int handlePirateMovement(Token token, int movement, Map<Integer, ArrayList<GameComponent>> completeMap){
        if (token.isInCave()) {
            return 999;
        }

        if (!completeMap.get(token.getPosition() - movement).get(0).isOccupied() || isBear) {
            if (token.getCurrentSteps() - movement <= 0 && token.getCurrentSteps() > 0) {
                token.setCurrentSteps(-(movement + 1));
            } else {
                token.setCurrentSteps(-movement);
            }
            return token.getPosition() - movement;
        }

        return 999;
    }

    /**
     * Handles the bear movement involving swapping the bear token with the nearest player's token.
     *
     * @param token the bear token
     * @param players the list of player tokens
     * @param completeMap the complete map of game components
     * @param caveCardManager the cave card manager
     * @param boardSetup the board setup
     * @return the previous position of the nearest player, or 999 if no player is found
     */
    public int handleBearMovement(Token token, ArrayList<Token> players, Map<Integer, ArrayList<GameComponent>> completeMap, CaveCardManager caveCardManager, BoardSetup boardSetup){
        this.isBackward = false;
        nearestPlayer(token, players, token.getPath());
        if (this.nearestPLayer == null) {
            return 999;
        }
        this.nearestPLayer = getNearestPLayer();
        int previousPosition = nearestPLayer.getPosition();

        int index = 0;
        this.isBear = true;
        if (token.isInCave()) {
            nearestPLayer.setCurrentSteps(-1);
            token.setCurrentSteps(1);
            index = 1;
            nearestPLayer.setInCave(true);
            token.setInCave(false);
        }
        moveToken(nearestPLayer, caveCardManager.parseCave(nearestPLayer), token.getPosition(), index, completeMap, boardSetup);

        if (isBackward) {
            if ((nearestPLayer.getCurrentSteps() + minDistance) >= nearestPLayer.getPath()) {
                handlePirateMovement(token, nearestPLayer.getPath() - minDistance, completeMap);
            } else {
                handleForwardMovement(nearestPLayer, minDistance, completeMap);
            }
            handlePirateMovement(token, minDistance, completeMap);
        } else {
            handlePirateMovement(nearestPLayer, minDistance, completeMap);
            handleForwardMovement(token, minDistance, completeMap);
        }

        if (token.isInCave()) {
            nearestPLayer.setInCave(true);
            token.setInCave(false);
        }

        return previousPosition;
    }

    /**
     * Gets the nearest player token.
     *
     * @return the nearest player token
     */
    public Token getNearestPLayer() {
        return nearestPLayer;
    }

    /**
     * Finds the nearest player to a given token.
     *
     * @param token the reference token
     * @param players the list of player tokens
     * @param boardSize the size of the board
     */
    public void nearestPlayer(Token token, ArrayList<Token> players, int boardSize){
        int minimumDistance = Integer.MAX_VALUE;
        for (Token player : players) {
            if (!Objects.equals(token.getDragonColour(), player.getDragonColour()) && !player.isInCave()) {
                int distanceClockwise = (player.getPosition() - token.getPosition() + boardSize) % boardSize;
                int distanceCounterClockwise = (token.getPosition() - player.getPosition() + boardSize) % boardSize;
                int distance = Math.min(distanceClockwise, distanceCounterClockwise);
                if (distance < minimumDistance) {
                    minimumDistance = distance;
                    this.minDistance = minimumDistance;
                    this.nearestPLayer = player;
                    isBackward = distance == distanceCounterClockwise;
                }
            }
        }
    }

    /**
     * Handles the earthquake movement, affecting all players except the given token.
     *
     * @param token the reference token
     * @param players the list of player tokens
     * @param completeMap the complete map of game components
     * @param boardSetup the board setup
     * @param caveCardManager the cave card manager
     * @return 999 indicating the earthquake action
     */
    public int handleEarthquake(Token token, ArrayList<Token> players, Map<Integer, ArrayList<GameComponent>> completeMap, BoardSetup boardSetup, CaveCardManager caveCardManager){
        for (Token player : players) {
            if (!Objects.equals(token.getDragonColour(), player.getDragonColour())) {
                int position = handlePirateMovement(player, 1, completeMap);
                if (position != 999) {
                    moveToken(player, caveCardManager.parseCave(player), position, 0, completeMap, boardSetup);
                }
            }
        }
        return 999;
    }

    /**
     * Handles various chance actions based on the chosen action index.
     *
     * @param chosen the index of the chosen action
     * @param token the reference token
     * @param players the list of player tokens
     * @param completeMap the complete map of game components
     * @param boardSetup the board setup
     * @param caveCardManager the cave card manager
     * @return the result of the chosen action
     */
    public int handleChance(int chosen, Token token, ArrayList<Token> players, Map<Integer, ArrayList<GameComponent>> completeMap, BoardSetup boardSetup, CaveCardManager caveCardManager){
        return switch (chosen) {
            case 0 -> 999;
            case 1 -> handleForwardMovement(token, 2, completeMap);
            case 2 -> handlePirateMovement(token, 2, completeMap);
            case 3 -> handleForwardMovement(token, 3, completeMap);
            case 4 -> handleEarthquake(token, players, completeMap, boardSetup, caveCardManager);
            default -> 999;
        };
    }

    /**
     * Resets the movement manager to its initial state.
     */
    public void reset(){
        this.nearestPLayerIndex = -1;
        this.nearestPLayer = null;
        this.isBackward = false;
        this.isBear = false;
    }

    /**
     * Moves a token to a specified position on the board.
     *
     * @param token the token to move
     * @param tokenIndex the index of the token
     * @param position the new position of the token
     * @param index the index in the complete map
     * @param completeMap the complete map of game components
     * @param boardSetup the board setup
     */
    public void moveToken(Token token, int tokenIndex, int position, int index, Map<Integer, ArrayList<GameComponent>> completeMap, BoardSetup boardSetup){
        Point point = completeMap.get(position).get(index).getPoint();
        JPanel panel = boardSetup.getToken(tokenIndex);
        token.setPosition(position);
        panel.setLocation(point);
        try {
            Thread.sleep(500); // Sleep for 500 milliseconds (0.5 second)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        panel.revalidate();
        panel.repaint();
    }
}
