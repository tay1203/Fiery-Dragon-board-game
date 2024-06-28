package animals;

/**
 * {@code Animal} class is an abstract class that represents entities classified as animals,
 * and it is composed of game components of Fiery Dragons game.
 * Animals including Spider, Bat, Salamander, Pirate Dragon and Baby Dragon.
 *
 * Modified by:
 * @author Koe Rui En
 */
public abstract class Animal {

    /**
     * name for an animal
     */
    private String animalName;

    /**
     * maximum movement of an animal
     */
    private int maxMovement;

    /**
     * a flag to state whether an animal can cause the dragon token to move backwards
     */
    private boolean isBackwardable;

    /**
     * a flag to state whether an animal has special ability to be performed on the dragon token
     */
    private boolean hasSpeciality;

    /**
     * a flag to state whether an animal has special action effect to be performed on the dragon token
     */
    private boolean hasAction;

    /**
     * Constructor of the Animal class.
     *
     * @param animalName Animal's name
     * @param maxMovement maximum movement of Animal
     */
    public Animal(String animalName, int maxMovement){
        this.animalName = animalName;
        this.maxMovement = maxMovement;
        this.isBackwardable = false;
        this.hasSpeciality = false;
        this.hasAction = false;
    }

    /**
     * A getter to get the name of an animal.
     *
     * @return the name of animal
     */
    public String getAnimalName() {
        return animalName;
    }

    /**
     A getter to retrieve the maximum movement, which is represented by the number of Animals on the card.
     *
     * @return maximum movement as integer
     */
    public int getMaxMovement(){return maxMovement;}

    /**
     * A toString() method to get the description of Animal.
     *
     * @return a String description of the Animal
     */
    @Override
    public String toString() {
        return getAnimalName();
    }

    /**
     * Return the condition (true/false) when Animal is able to cause the dragon token to move backwards.
     *
     * @return true/false to indicate Animal is able to cause the dragon token to move backwards
     */
    public boolean isBackwardable() {
        return this.isBackwardable;
    }

    /**
     * A setter to set the state (true/false) when Animal is able to cause the dragon token to move backwards.
     *
     * @param isBackwardable a flag to indicate the state (true/false) of Animal is able to cause the dragon token to move backwards.
     */
    public void setBackwardable(boolean isBackwardable){
        this.isBackwardable = isBackwardable;
    }

    /**
     * Return the condition (true/false) whether Animal has special ability or not.
     *
     * @return true/false to indicate Animal has special ability or not.
     */
    public boolean isHasSpeciality() {
        return hasSpeciality;
    }

    /**
     * A setter to set the state (true/false) of Animal has special ability or not.
     *
     * @param hasSpeciality a flag to indicate the state (true/false) of Animal has special ability or not.
     */
    public void setHasSpeciality(boolean hasSpeciality) {
        this.hasSpeciality = hasSpeciality;
    }

    /**
     * Return the condition (true/false) whether Animal has action performed on the token or not.
     *
     * @return true/false to indicate Animal has action performed on the token or not.
     */
    public boolean isHasAction() {
        return hasAction;
    }

    /**
     * A setter to set the state (true/false) whether Animal has action performed on the token or not.
     *
     * @param hasAction a flag to indicate the state (true/false) of Animal has action performed on the token or not.
     */
    public void setHasAction(boolean hasAction) {
        this.hasAction = hasAction;
    }

    /**
     * A method to execute chance effects.
     */
    public int performAction(){
        return 0;
    };
}
