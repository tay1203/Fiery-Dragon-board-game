package main;

/**
 * The {@code Player} class represents a player in the game.
 * It contains the player's name and age, and provides methods to access and modify these attributes.
 *
 * Created by:
 * @author Wong Jia Xuan
 *
 * Modified by:
 * @author Koe Rui En
 * @author Tay Ming Hui
 */
public class Player {
    /**
     * The name of the player.
     */
    private String name;

    /**
     * The age of the player.
     */
    private int age;

    /**
     * Constructs a new {@code Main.Main.Player} instance with the specified name and age.
     *
     * @param name the name of the player
     * @param age the age of the player
     */
    public Player(String name, int age) {
        setName(name);
        setAge(age);
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name the new name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of the player.
     *
     * @return the age of the player
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the player.
     *
     * @param age the new age of the player
     */
    public void setAge(int age) {
        this.age = age;
    }
}
