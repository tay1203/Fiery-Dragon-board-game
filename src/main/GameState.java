package main;

import components.GameComponent;
import components.Token;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * {@code GameState} is a manager class to manage the saving and loading of the current game state.
 *
 * Created by:
 * @author Wong Jia Xuan
 *
 * Modified by:
 * @author Koe Rui En
 *
 */
public class GameState implements Serializable {

    /**
     * default value for serial version
     */
    private static final long serialVersionUID = 1L;

    /**
     * a list of player tokens
     */
    private ArrayList<Token> players;

    /**
     * a list of player names.
     */
    private ArrayList<String> names;

    /**
     * a complete map of game components indexed by an integer.
     */

    private Map<Integer, ArrayList<GameComponent>> completeMap;

    /**
     * the current token in play.
     */
    private Token currentToken;

    // Constructor
    /**
     * Constructor of the GameState class.
     *
     * @param players      the list of player Tokens
     * @param names        the list of player names as string
     * @param completeMap  the complete map of GameComponent
     * @param currentToken the current Token instance
     */
    public GameState(ArrayList<Token> players, ArrayList<String> names, Map<Integer, ArrayList<GameComponent>> completeMap, Token currentToken) {
        this.players = players;
        this.names = names;
        this.completeMap = completeMap;
        this.currentToken = currentToken;
    }

    /**
     * A getter to get  the list of player Token.
     *
     * @return the list of player Token
     */
    public ArrayList<Token> getPlayers() {
        return players;
    }

    /**
     * A setter to set the list of player Token.
     *
     * @param players the list of player Token to set
     */
    public void setPlayers(ArrayList<Token> players) {
        this.players = players;
    }

    /**
     * A getter to get the complete map of GameComponent.
     *
     * @return the complete map of game components
     */
    public Map<Integer, ArrayList<GameComponent>> getCompleteMap() {
        return completeMap;
    }

    /**
     * A setter to set the complete map of game components.
     *
     * @param completeMap the complete map of game components to set
     */
    public void setCompleteMap(Map<Integer, ArrayList<GameComponent>> completeMap) {
        this.completeMap = completeMap;
    }

    /**
     * A getter to get the current token.
     *
     * @return the current token
     */
    public Token getCurrentToken() {
        return currentToken;
    }

    /**
     * A setter to set  the current token.
     *
     * @param currentToken the current token to set
     */
    public void setCurrentToken(Token currentToken) {
        this.currentToken = currentToken;
    }

    /**
     * A getter to get the list of player names.
     *
     * @return the list of player names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * A setter to set  the list of player names.
     *
     * @param names the list of player names to set
     */
    public void setNames(ArrayList<String> names) {
        this.names = names;
    }


}