package board;

import components.GameComponent;
import components.VolcanoCard;
import components.VolcanoGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code VolcanoCardManager} is a manager class to manage the volcano cards' {@code VolcanoGroup} setup.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class VolcanoCardManager {

    /**
     * an instance of BoardSetup
     */
    private BoardSetup boardSetup;

    /**
     * an instance of VolcanoCardBoard
     */
    private VolcanoCardBoard volcano;

    /**
     * a mapping of Integer to the VolcanoCard
     */
    private Map<Integer, VolcanoCard> volcanoIndexMap;

    /**
     * a mapping of Integer to the GameComponent
     */
    private Map<Integer, ArrayList<GameComponent> > completeMap;

    /**
     * a mapping of Integer to the GameComponent
     */
    private ArrayList<VolcanoGroup> volcanoGroups;

    /**
     * a list of caves' positions
     */
    private ArrayList<Integer> caves;

    /**
     * total number of volcano cards' tiles
     */
    private int numberOfTiles;

    /**
     * a list of cave positions
     */
    private ArrayList<Point> cavePositions;

    /**
     * Constructor of the VolcanoCardManager class.
     *
     * @param boardSetup an instance of BoardSetup
     * @param cardNumber volcano cards number required
     * @param tilesNum number of tiles per volcano cards
     *
     */
    public VolcanoCardManager(BoardSetup boardSetup, int tilesNum, int cardNumber){
        this.boardSetup = boardSetup;
        volcanoIndexMap = new HashMap<>();
        completeMap = new HashMap<>();
        this.volcanoGroups = new ArrayList<>();
        this.caves = new ArrayList<Integer>();
        this.numberOfTiles = 0;
        this.cavePositions = new ArrayList<>();
        volcano = new VolcanoCardBoard(tilesNum, cardNumber);
    }

    /**
     * Configure the VolcanoCard on the game board.
     *
     */
    public void volcanoCardSetup() {
        ArrayList <VolcanoCard> tiles = getVolcanoCard();
        int size = 53;
        int centerX = 1050 / 2;
        int centerY = 700 / 2;
        int radius = 230;
        int j = tiles.size()-1;
        this.numberOfTiles = tiles.size();
        // configure the volcano card's squares on the board
        for (int i = 0; i < tiles.size(); i++) {
            double angle = 2 * Math.PI * i / numberOfTiles;
            int x = centerX + (int) (radius * Math.sin(angle)) - size / 2;
            int y = centerY + (int) (radius * Math.cos(angle)) - size / 2;
            VolcanoCard vol = tiles.get(i);
            // revert the index of volcano card to be stored
            volcanoIndexMap.put(j, vol);
            vol.setBounds(x, y, 53, 53);
            vol.setOpaque(false);

            this.getBoardSetup().getVolcanoCardPanel().add(vol);
            j--;
        }

    }

    private void addCavePoint() {
        int size = 30;
        int centerX = 1090 / 2;
        int centerY = 730 / 2;
        int radius = 315;
        for (int j = 0; j < caves.size(); j++){
            for (int i = 0; i < numberOfTiles; i++) {
                if ( i == caves.get(j)){
                    System.out.println(caves.get(j));
                    double angle = 2 * Math.PI * (numberOfTiles-1-i) / numberOfTiles;
                    int x = centerX + (int) (radius * Math.sin(angle)) - size / 2;
                    int y = centerY + (int) (radius * Math.cos(angle)) - size / 2;
                    cavePositions.add(new Point(x-53, y-50));
                }
            }
        }
    }

    /**
     * A method to get a list of tiles of all volcano cards(VolcanoCard)
     *
     * @return a list of volcano cards' tile(VolcanoCard) of all volcano cards。
     */
    public ArrayList<VolcanoCard> getVolcanoCard(){
        ArrayList<VolcanoCard> tiles = new ArrayList<>();
        volcanoGroups = volcano.getVolcanos();

        for (VolcanoGroup group: volcanoGroups){
            tiles.addAll(group.getTiles());
        }
        return tiles;
    }

    /**
     * A method to get total number of all volcano cards' tiles
     *
     * @return total number of all volcano cards' tiles
     */
    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    /**
     * A method to get complete map paths for all dragon tokens to move on it.
     *
     * @return a complete map paths for all dragon tokens to move on it
     */
    public Map<Integer, ArrayList<GameComponent>> getCompleteMap() {
        return this.completeMap;
    }

    /**
     * A method to configure complete map paths for all dragon tokens to move on it.
     *
     */
    public void setCompleteMap(){
        for (int i = 0; i < numberOfTiles; i++) {
            ArrayList<GameComponent> components = new ArrayList<>();
            components.add(getVolcanoIndexMap().get(i));
            this.completeMap.put(i, components);
        }

        for (int j = numberOfTiles; j < numberOfTiles*2; j++){
            ArrayList<GameComponent> components = new ArrayList<>();
            components.add(getVolcanoIndexMap().get(j-numberOfTiles));
            this.completeMap.put(j, components);
        }

        for (int k = -1; k > -(numberOfTiles+1); k--){
            ArrayList<GameComponent> components = new ArrayList<>();
            components.add(getVolcanoIndexMap().get(numberOfTiles+k));
            this.completeMap.put(k, components);
        }

    }

    /**
     * A method to configure cave's position on the map paths.
     *
     * @param j position for cave to be set on the map paths as integer
     * @param gameComponent a cave instance
     *
     */
    public void setCave(int j, GameComponent gameComponent){
        completeMap.get(j).add(gameComponent);
        completeMap.get(j+numberOfTiles).add(gameComponent);
        completeMap.get(j-numberOfTiles).add(gameComponent);
    }

    /**
     * A getter to get a map of VolcanoCard with its index.
     *
     * @return return a map of VolcanoCard with its index
     */
    public Map<Integer, VolcanoCard> getVolcanoIndexMap() {
        return volcanoIndexMap;
    }

    /**
     * A method to configure caves to be attached to volcano cards(VolcanoGroup) that have indentation
     *
     */
    public void setUpCavePosition(){
        int cards = this.volcanoGroups.size();
        int j = cards/4;
        // cave position on dynamic number of tiles
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < cards; i+=j){
            int position = volcanoGroups.get(i).getNumberOfTiles() * i + volcanoGroups.get(i).getIndentation();
            temp.add(position);
        }

        // add cave position
        caves.add(temp.get(2));
        caves.add(temp.get(3));
        caves.add(temp.get(0));
        caves.add(temp.get(1));

        addCavePoint();

    }

    /**
     * A method to get position of caves to be attached to volcano cards(VolcanoGroup) that have indentation
     *
     * @param i position of cave attached as integer
     *
     * @return position of caves to be attached to volcano cards(VolcanoGroup) that have indentation
     */
    public int getCavePosition(int i) {
        return caves.get(i);
    }

    /**
     * A method to get position of caves to be attached to volcano cards(VolcanoGroup) that have indentation
     *
     * @return a list of positions of the cave to be attached to
     */
    public ArrayList<Point> getCavePoints() {
        return cavePositions;
    }

    /**
     * A getter to get the instance of BoardSetup.
     *
     * @return return the instance of BoardSetup
     */
    public BoardSetup getBoardSetup() {
        return this.boardSetup;
    }
}
