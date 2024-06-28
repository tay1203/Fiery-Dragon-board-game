package components;

import java.util.ArrayList;

/**
 * {@code VolcanoGroup} is a class which represents volcano cards which is one of the {@code GameComponent}
 * of Fiery Dragons game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class VolcanoGroup extends GameComponent{

    /**
     * a list of volcano cards' tiles
     */
    private ArrayList<VolcanoCard> tiles;

    /**
     * a flag to indicate the volcano cards contains cave to be attached
     */
    private boolean hasCave;

    /**
     * an CaveCard instance
     */
    private CaveCard caveCard;

    /**
     * an CaveCard instance
     */
    private int indentation;

    /**
     * number of tiles per volcano card
     */
    private int numberOfTiles;

    /**
     * Constructor of VolcanoGroup.
     *
     * @param hasCave a flag to indicate the volcano card has cave to be attached or not
     * @param tiles volcano cards' tiles(VolcanoCard) as list
     */
    public VolcanoGroup(ArrayList<VolcanoCard> tiles, boolean hasCave){
        this.tiles = tiles;
        this.hasCave = hasCave;
        this.caveCard = null;
        this.numberOfTiles = tiles.size();
        this.indentation = tiles.size()/2;
    }

    /**
     * A method to get number of tiles of a volcano card.
     *
     * @return total number of tiles of a volcano card as integer
     */
    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    /**
     * A method to get position of volcano card's indentation for cave to be attached.
     *
     * @return position of volcano card's indentation for cave to be attached as integer
     */
    public int getIndentation() {
        return indentation;
    }

    /**
     * A method to get a list of volcano cards' tiles
     *
     * @return a list of volcano cards' tiles(VolcanoCard)
     */
    public ArrayList<VolcanoCard> getTiles() {
        return tiles;
    }


}
