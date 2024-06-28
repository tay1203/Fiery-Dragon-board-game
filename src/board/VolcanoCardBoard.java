package board;

import animals.Animal;
import animals.AnimalFactory;
import components.VolcanoCard;
import components.VolcanoGroup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * {@code VolcanoCardBoard} is a class that handle the initialisation of {@code Animal} for VolcanoGroup's instances.
 * This is to configure the VolcanoCards' tiles and VolcanoCard.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class VolcanoCardBoard{

    /**
     * a list of VolcanoGroup's instances
     */
    public ArrayList<VolcanoGroup> volcanos;

    /**
     * Constructor of the VolcanoCardBoard class.
     *
     * @param cardNumber volcano cards number required
     * @param numTiles number of tiles per volcano cards
     **/
    public VolcanoCardBoard(int numTiles, int cardNumber){
        this.volcanos = new ArrayList<>();

        setupVolcanoAnimals(numTiles, cardNumber);
    }

    /**
     * Return a list of instances of VolcanoGroup.
     *
     * @return a list of instances of VolcanoGroup
     */
    public ArrayList<VolcanoGroup> getVolcanos() {
        return volcanos;
    }

    /**
     * Initialise volcano cards(VolcanoGroup) with different types of Animals on tiles(VolcanoCard).
     *
     * @param cardNumber  number of volcano cards
     * @param numberOfTiles number of tiles per card
     *
     */
    public void setupVolcanoAnimals(int numberOfTiles, int cardNumber){
        // create volcano cards' animals
        ArrayList<ArrayList<Animal>> noCaveGroup= AnimalFactory.createVolcanoAnimalNoCave(numberOfTiles, cardNumber/2);
        ArrayList<ArrayList<Animal>> withCaveGroup= AnimalFactory.createVolcanoAnimalWithCave(numberOfTiles, cardNumber/2);
        for (int i = 0; i < cardNumber/2; i++){
            ArrayList <VolcanoCard> group = new ArrayList<>();
            ArrayList <VolcanoCard> group2 = new ArrayList<>();
            for (int j = 0; j < numberOfTiles; j++){
                group.add(new VolcanoCard(noCaveGroup.get(i).get(j)));
                group2.add(new VolcanoCard(withCaveGroup.get(i).get(j)));
            }
            // add volcano cards to the list
            this.volcanos.add(new VolcanoGroup(group, false));
            this.volcanos.add(new VolcanoGroup(group2, true));
        }
    }

}
