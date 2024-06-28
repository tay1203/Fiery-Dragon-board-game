package animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * A factory class to manufacture all the game components comprising animals including volcano cards, chit cards and caves.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class AnimalFactory {

    /**
     * Create different types of Animal on the ChitCard, add them to the animalFactory list and return the list.
     *
     * @return return a list of Animal on the ChitCard
     */
    public static ArrayList<Animal> createChitCardAnimal(){
        ArrayList<Animal> animalFactory = new ArrayList<>();

        // add all animals to the list
        animalFactory.add(new Bat());
        animalFactory.add(new Spider());
        animalFactory.add(new Salamander());
        animalFactory.add(new BabyDragon());
        // add twice pirate dragon because 2 same chits
        animalFactory.add(new PirateDragon());
        animalFactory.add(new PirateDragon());
        animalFactory.add(new Bear());
        animalFactory.add(new Bear());
        animalFactory.add(new Chance());
        animalFactory.add(new Chance());


        return animalFactory;
    }

    /**
     * Create different types of Animal on the CaveCard, add them to the animalFactory list and return the list.
     *
     * @return return a list of Animal on the CaveCard
     */
    public static ArrayList<Animal> createCaveAnimal(){
        ArrayList<Animal> animalFactory = new ArrayList<>();

        // add all animals to the list
        animalFactory.add(new Bat());
        animalFactory.add(new Spider());
        animalFactory.add(new Salamander());
        animalFactory.add(new BabyDragon());

        return animalFactory;
    }


    /**
     * Private list of animals for each volcano card's tile.
     *
     */
    private static Animal[] getVolcanoCardTilesAnimalList(){
        Animal[] animalList = {new Spider(), new Bat(), new Salamander(), new BabyDragon()};

        return animalList;

    }

    /**
     * Create different types of Animal on the volcano cards' tiles(VolcanoCard),
     * add them to the noCaveGroup list and return the list.
     *
     * @param cardNumber number of volcano cards
     * @param numOfTiles number of tiles per card
     *
     * @return return a list of Animal on the volcano cards' tiles(VolcanoCard) without cave
     */
    public static ArrayList<ArrayList<Animal>> createVolcanoAnimalNoCave(int numOfTiles, int cardNumber){
        // volcano card with no cuts/caves
        ArrayList<ArrayList<Animal>> noCaveGroup = new ArrayList<>();

        // vary on volcano card number
        for (int j = 1; j<= cardNumber; j++) {
            ArrayList<Animal> noCave = new ArrayList<>();
            // number of tiles per card
            for (int i = 0; i < numOfTiles; i++) {
                int randomAnimalIndex = new Random().nextInt(getVolcanoCardTilesAnimalList().length);
                if (randomAnimalIndex >= 0 && randomAnimalIndex < getVolcanoCardTilesAnimalList().length) {
                    noCave.add(getVolcanoCardTilesAnimalList()[randomAnimalIndex]);
                }
            }
            noCaveGroup.add(noCave);
        }

        System.out.println(noCaveGroup);


        return noCaveGroup;
    }

    /**
     * Create different types of Animal on the volcano cards' tiles(VolcanoCard),
     * add them to the withCaveGroup list and return the list.
     *
     * @param cardNumber number of volcano cards
     * @param numOfTiles number of tiles per card
     *
     * @return return a list of Animal on the volcano cards' tiles(VolcanoCard) with caves
     */
    public static ArrayList<ArrayList<Animal>> createVolcanoAnimalWithCave(int numOfTiles, int cardNumber) {
        // volcano card with cuts/caves
        ArrayList<ArrayList<Animal>> withCaveGroup = new ArrayList<>();

        // vary on volcano card number
        for (int j = 1; j<= cardNumber; j++) {
            ArrayList<Animal> noCave = new ArrayList<>();
            // number of tiles per card
            for (int i = 0; i < numOfTiles; i++) {
                int randomAnimalIndex = new Random().nextInt(getVolcanoCardTilesAnimalList().length);
                // double-checking the index generated within the list of animals
                if (randomAnimalIndex >= 0 && randomAnimalIndex < getVolcanoCardTilesAnimalList().length) {
                    noCave.add(getVolcanoCardTilesAnimalList()[randomAnimalIndex]);
                }
            }
            withCaveGroup.add(noCave);
        }

        System.out.println(withCaveGroup);

        return withCaveGroup;

    }

}
