package board;

import animals.AnimalFactory;
import board.BoardSetup;
import components.CaveCard;
import components.Token;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * {@code CaveCardManager} is a manager class to manage the CaveCard's setup.
 * Each CaveCard contains a Token.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class CaveCardManager {

    /**
     * an instance of board.BoardSetup
     */
    private BoardSetup boardSetup;

    /**
     * a list of CaveCards
     */
    private ArrayList<CaveCard> caves;

    /**
     * a list of Tokens
     */
    private ArrayList<Token> tokens;

    /**
     * a list of shuffled Tokens
     */
    private ArrayList<Token> shuffledToken = new ArrayList<>();

    private ArrayList<Point> positions;

    /**
     * Constructor of the CaveCardManager class.
     *
     * @param boardSetup an instance of BoardSetup to configure the game board
     * @param tokens a list of Tokens
     */
    public CaveCardManager(BoardSetup boardSetup, ArrayList<Token> tokens, ArrayList<Point> positions){
        this.boardSetup = boardSetup;
        this.caves = new ArrayList<>();
        this.tokens = tokens;
        this.positions = positions;
    }

    /**
     * Parse cave of a Token instance.
     *
     * @param token an instance of Token respective to each Cave
     *
     * @return position of cave where an instance of Token starts as integer
     */
    public int parseCave(Token token){
        int i = 0;
        for (CaveCard caveCard: caves){
            if (Objects.equals(caveCard.getAnimal().getAnimalName(), token.getAnimalName())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public ArrayList<CaveCard> getCaves() {
        return caves;
    }

    /**
     * Configure the caves on the game board.
     *
     */
    public void caveSetup(){
        // create cave card's animals
        for(int i = 0; i < AnimalFactory.createCaveAnimal().size(); i++){
            caves.add(new CaveCard(AnimalFactory.createCaveAnimal().get(i)));
        }

        // shuffle caves
        Collections.shuffle(caves);

        // add cave to panel
        for (int i = 0; i < caves.size(); i++){
            CaveCard cave = this.caves.get(i);
            cave.setOpaque(false);
            this.boardSetup.getCavePanel(i).setBounds((int)positions.get(i).getX(), (int)positions.get(i).getY(), 105,105);
            this.boardSetup.getCavePanel(i).add(cave, BorderLayout.CENTER);
        }

        // setup token on each cave
        for (Token token: tokens){
            for (int i = 0; i < caves.size(); i++){
                // white token
                if (Objects.equals(token.getDragonColour(), "White") && Objects.equals(caves.get(i).getAnimal().toString(), "salamander")){
                    token.setOpaque(false);
                    int x = this.boardSetup.getCavePanel(i).getX() + 10;
                    int y = this.boardSetup.getCavePanel(i).getY() + 10;
                    token.setBornCave(caves.get(i));
                    caves.get(i).setPoint(new Point(x,y));
                    this.boardSetup.getToken(i).setBounds(x,y,45,65);
                    this.boardSetup.getToken(i).setLayout(new BorderLayout());
                    this.boardSetup.getToken(i).add(token,BorderLayout.CENTER);

                    // blue token
                } else if (Objects.equals(token.getDragonColour(), "Blue") && Objects.equals(caves.get(i).getAnimal().toString(), "bat")) {
                    token.setOpaque(false);
                    int x = this.boardSetup.getCavePanel(i).getX() + 10;
                    int y = this.boardSetup.getCavePanel(i).getY() + 10;
                    token.setBornCave(caves.get(i));
                    caves.get(i).setPoint(new Point(x,y));
                    this.boardSetup.getToken(i).setBounds(x,y,45,65);
                    this.boardSetup.getToken(i).setLayout(new BorderLayout());
                    this.boardSetup.getToken(i).add(token,BorderLayout.CENTER);

                    // green token
                } else if (Objects.equals(token.getDragonColour(), "Green") && Objects.equals(caves.get(i).getAnimal().toString(), "babydragon")) {
                    token.setOpaque(false);
                    int x = this.boardSetup.getCavePanel(i).getX() + 10;
                    int y = this.boardSetup.getCavePanel(i).getY() + 10;
                    token.setBornCave(caves.get(i));
                    caves.get(i).setPoint(new Point(x,y));
                    this.boardSetup.getToken(i).setBounds(x,y,45,65);
                    this.boardSetup.getToken(i).setLayout(new BorderLayout());
                    this.boardSetup.getToken(i).add(token,BorderLayout.CENTER);

                    // yellow token
                } else if (Objects.equals(token.getDragonColour(), "Yellow") && Objects.equals(caves.get(i).getAnimal().toString(), "spider")) {
                    token.setOpaque(false);
                    int x = this.boardSetup.getCavePanel(i).getX() + 10;
                    int y = this.boardSetup.getCavePanel(i).getY() + 10;
                    token.setBornCave(caves.get(i));
                    caves.get(i).setPoint(new Point(x,y));
                    this.boardSetup.getToken(i).setBounds(x,y,45,65);
                    this.boardSetup.getToken(i).setLayout(new BorderLayout());
                    this.boardSetup.getToken(i).add(token,BorderLayout.CENTER);
                }
            }
        }

        // add the shuffled token
        for (CaveCard caveCard: caves){
            for (Token token: tokens){
                if (Objects.equals(caveCard.getAnimal().getAnimalName(), token.getAnimalName())){
                    shuffledToken.add(token);
                }
            }
        }
    }

    /**
     * A getter to get the shuffled Tokens' list.
     *
     * @return a list of shuffled Tokens
     */
    public ArrayList<Token> getShuffledToken() {
        return shuffledToken;
    }

    /**
     * A getter to get the instance of BoardSetup.
     *
     * @return the instance of BoardSetup
     */
    public BoardSetup getBoardSetup() {
        return this.boardSetup;
    }
}
