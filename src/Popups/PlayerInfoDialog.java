package Popups;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import main.*;
/**
 * The {@code PlayerInfoDialog} class is to display for players to start the Fiery Dragons game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class PlayerInfoDialog extends JDialog {

    /**
     * text box to fill player1's name
     */
    private JTextField name1;
    /**
     * text box to fill player2's name
     */
    private JTextField name2;
    /**
     * text box to fill player3's name
     */
    private JTextField name3;
    /**
     * text box to fill player4's name
     */
    private JTextField name4;

    /**
     * text box to fill player1's age
     */
    private JTextField age1;
    /**
     * text box to fill player2's age
     */
    private JTextField age2;
    /**
     * text box to fill player3's age
     */
    private JTextField age3;
    /**
     * text box to fill player4's age
     */
    private JTextField age4;

    /**
     * confirmation button
     */
    private JButton okButton;

    /**
     * list of player names.
     *
     */
    public ArrayList<String> names = new ArrayList<>();

    /**
     * list of player names.
     *
     */
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Constructs a new {@code Popups.PlayerInfoDialog} instance.
     *
     * @param parent the parent frame
     */
    public PlayerInfoDialog(JFrame parent, int cardNo, int tilesNo) {
        super(parent, "Player Input", true);
        setSize(300,400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,5,10);

        JLabel message = new JLabel("      Player name             Age (2-99)  ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        panel.add(message, constraints);

        name1 = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(name1, constraints);
        name2 = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(name2, constraints);
        name3 = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(name3, constraints);
        name4 = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(name4, constraints);

        age1 = new JTextField(3);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(age1, constraints);
        age2 = new JTextField(3);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(age2, constraints);
        age3 = new JTextField(3);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(age3, constraints);
        age4 = new JTextField(3);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(age4, constraints);

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createPlayer()) {
                    setNames(extractPlayerName());
                    dispose();
                    new Game(getNames(), cardNo, tilesNo);
                } else {
                    JOptionPane.showMessageDialog(PlayerInfoDialog.this,
                            "Please enter valid names and ages for 2 to 4 players.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 0;
        panel.add(okButton, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    /**
     * Returns the list of player names.
     *
     * @return the list of player names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * Sets the list of player names.
     *
     * @param names the list of player names
     */
    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    /**
     * Returns the text from the specified JTextField.
     *
     * @param field the JTextField to get text from
     * @return the text from the specified JTextField
     */
    public String getName(JTextField field){
        return field.getText();
    }

    /**
     * Returns the text from the specified JTextField.
     *
     * @param field the JTextField to get text from
     * @return the text from the specified JTextField
     */
    public String getAge(JTextField field){
        return field.getText();
    }

    /**
     * Validates if the age input is within the range of 2 to 99.
     *
     * @param input the age input as a String
     * @return true if the age is valid, false otherwise
     */
    public boolean validateAge (String input){
        if (input == null) {
            return false;
        }
        try {
            int age = Integer.parseInt(input);
            return age >= 2 && age <= 99;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if the name input is not empty.
     *
     * @param input the name input as a String
     * @return true if the name is valid, false otherwise
     */
    public boolean validateName (String input){
        return !input.isEmpty();
    }

    /**
     * Creates a player if the name and age inputs are valid.
     *
     * @return true if there are at least 2 valid players, false otherwise
     */
    public boolean createPlayer(){
        players.clear();
        if (validateName(getName(name1)) && validateAge(getAge(age1))){
            players.add(new Player(getName(name1), Integer.parseInt(getAge(age1))));
        }
        if (validateName(getName(name2)) && validateAge(getAge(age2))){
            players.add(new Player(getName(name2), Integer.parseInt(getAge(age2))));
        }
        if (validateName(getName(name3)) && validateAge(getAge(age3))){
            players.add(new Player(getName(name3), Integer.parseInt(getAge(age3))));
        }
        if (validateName(getName(name4)) && validateAge(getAge(age4))){
            players.add(new Player(getName(name4), Integer.parseInt(getAge(age4))));
        }
        return players.size() > 1;
    }

    /**
     * Sorts the players by age.
     */
    public void sortPlayer(){
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

    }

    /**
     * Extracts the player names from the sorted list of players.
     *
     * @return the list of player names
     */
    public ArrayList<String> extractPlayerName(){
        sortPlayer();
        ArrayList<String> names = new ArrayList<>();
        for (Player player: players){
            names.add(player.getName());
        }
        return names;
    }

}