package main;

import Popups.PlayerInfoDialog;
import buttons.InfoButton;
import buttons.MusicButton;
import fonts.Display;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code HomePage} class is to display for players to start the Fiery Dragons game.
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Tay Ming Hui
 * @author Wong Jia Xuan
 */
public class HomePage extends JFrame {

    // get font
    /**
     * new Font imported from other resources
     */
    private Font font = new Display().getFont();
    /**
     * panel to set up the main home panel
     */
    private JPanel homePanel;
    /**
     * panel for the game title
     */
    private JPanel gameTitlePanel;
    /**
     * panel for the button
     */
    private JPanel buttonPanel;

    private int cardNo;

    private int tilesNo;

    // constructor
    /**
     * Constructor for the HomePage class.
     *
     */
    public HomePage(){

        this.cardNo = 8;
        this.tilesNo = 3;

        //setup main home panel
        setupHomePanel();

        // initialise home page
        this.setSize(1080,720);
        this.setTitle("Fiery Dragons");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    // setup home panel
    /**
     * Configure the main homePanel to be added to HomePage.
     */
    public void setupHomePanel(){

        // add panel of home page
        homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBackground(Color.ORANGE);

        // configure button panel
        buttonPanel = new JPanel();
        // set panel transparent
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(1080 / 2 - 120, 740 / 2 , 200, 800);

        // add title
        setGameTitle();
        // add start button
        setStartButton();
        // add load button
        setLoadButton();
        // add exit button
        setExitButton();
        // add config button
        setConfigButton();
        // add help and music button
        setupButtons();
        // add game background img
        setImage();

        // add main panel to frame
        add(homePanel);

    }

    // set home page title
    /**
     * Set game title to be added to gameTitlePanel.
     */
    public void setGameTitle() {

        gameTitlePanel = new JPanel();
        // set panel transparent
        gameTitlePanel.setOpaque(false);
        gameTitlePanel.setLayout(new BorderLayout());
        gameTitlePanel.setBounds(1080/2-410, 720/2-200, 800, 200);

        // set title
        JLabel gameTitle = new JLabel();
        // set title
        gameTitle.setText("Fiery Dragons");
        // set title's font
        Font newFont = font.deriveFont(Font.PLAIN, 120);
        gameTitle.setFont(newFont);
        // align the title game to the center
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        // set title size
        gameTitle.setSize(451, 100);
        // Set text color to green
        gameTitle.setForeground(new Color(0, 123, 42));

        // add to home panel
        gameTitlePanel.add(gameTitle, BorderLayout.CENTER);
        homePanel.add(gameTitlePanel);

    }

    // add start button to home page
    /**
     * Configure start button to be added to buttonPanel.
     */
    public void setStartButton() {

        JButton startButton = new JButton("START");
        // Set text color to white
        startButton.setForeground(Color.WHITE);
        // set button size
        startButton.setPreferredSize(new Dimension(160, 60));
        startButton.setFocusable(false);
        // set button font
        Font newFont = font.deriveFont(Font.PLAIN, 38);
        startButton.setFont(newFont);
        // set button background
        startButton.setBackground(new Color(0, 169, 57));
        // add message to button
        startButton.setToolTipText("Start the game");

        // set button to be transparent
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);

        // add action listener to the button
        startButton.addActionListener(e -> {

            if (e.getSource() == startButton) {
                new PlayerInfoDialog(this, cardNo, tilesNo);
                dispose();
            }
        });

        // add to frame
        buttonPanel.add(startButton);
//        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Add 10 pixels of vertical space
        homePanel.add(buttonPanel);

    }

    /**
     * Configure exit button to be added to buttonPanel.
     * When this button is pressed, the player will close the Fiery Dragons' game without closing the game frame.
     */
    public void setExitButton(){

        JButton exitButton = new JButton("EXIT");
        // Set text color to white
        exitButton.setForeground(Color.WHITE);
        // set button size
        exitButton.setPreferredSize(new Dimension(160, 60));
        exitButton.setFocusable(false);
        // set button font
        Font newFont = font.deriveFont(Font.PLAIN, 40);
        exitButton.setFont(newFont);
        // set button background
        exitButton.setBackground(new Color(0, 169, 57));
        // add message to button
        exitButton.setToolTipText("Exit the game");

        // set button to be transparent
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);

        // add action listener to the button
        exitButton.addActionListener(e -> {

            if (e.getSource() == exitButton) {
                dispose();
            }
        });

        // add to frame
        buttonPanel.add(exitButton);
        homePanel.add(buttonPanel);

    }

    // add exit button to home page
    /**
     * Configure load button to be added to buttonPanel.
     * When this button is pressed, the player can resume the last game from the file they saved.
     */
    public void setLoadButton(){

        JButton loadButton = new JButton("LOAD");
        // Set text color to white
        loadButton.setForeground(Color.WHITE);
        // set button size
        loadButton.setPreferredSize(new Dimension(160, 60));
        loadButton.setFocusable(false);
        // set button font
        Font newFont = font.deriveFont(Font.PLAIN, 40);
        loadButton.setFont(newFont);
        // set button background
        loadButton.setBackground(new Color(0, 169, 57));
        // add message to button
        loadButton.setToolTipText("Load game's history");

        // set button to be transparent
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);

        JPanel savePanel = new JPanel();

        // // add action listener to the button
        // loadButton.addActionListener(e -> {

        //     // get save file
        //     if (e.getSource() == loadButton) {

        //         JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir") + "/Project/sprint4/src/saves"));
        //         int option = fileChooser.showOpenDialog(this);
        //         if (option == JFileChooser.APPROVE_OPTION) {
        //             String filePath = fileChooser.getSelectedFile().getPath();
        //             GameState gameState = new Game().loadGame(filePath);
        //             if (gameState != null) {
        //                 new Game().applyGameState(gameState);
        //             } else {
        //                 JOptionPane.showMessageDialog(this, "Failed to load the game!", "Error", JOptionPane.ERROR_MESSAGE);
        //             }
        //         }
        //     }
        // });


        // add to frame
        buttonPanel.add(loadButton);
        homePanel.add(buttonPanel);

    }

    public void setConfigButton(){

        JButton configButton = new JButton("CONFIG");
        // Set text color to white
        configButton.setForeground(Color.WHITE);
        // set button size
        configButton.setPreferredSize(new Dimension(160, 60));
        configButton.setFocusable(false);
        // set button font
        Font newFont = font.deriveFont(Font.PLAIN, 36);
        configButton.setFont(newFont);
        // set button background
        configButton.setBackground(new Color(0, 169, 57));
        // add message to button
        configButton.setToolTipText("Config Board");

        // set button to be transparent
        configButton.setOpaque(true);
        configButton.setBorderPainted(false);

        // add action listener to the button
        configButton.addActionListener(e -> {

            if (e.getSource() == configButton) {
                Integer[] numbers = {4, 5, 6, 7, 8};
                Integer[] numbers2 = {3, 4, 5, 6};

                // Create a JComboBox with the numbers array
                JComboBox<Integer> comboBox = new JComboBox<>(numbers);
                comboBox.setSelectedItem(8);
                JComboBox<Integer> comboBoxTiles = new JComboBox<>(numbers2);
                comboBoxTiles.setSelectedItem(3);
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 2, 10, 10));
                panel.add(new JLabel("Select Number of Volcano Cards"));
                panel.add(comboBox);
                panel.add(new JLabel("Select Number of tiles in each card:"));
                panel.add(comboBoxTiles);

                // Show a JOptionPane with the panel inside
                int result = JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Configuration of Board",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE
                );

                // Check if the user clicked OK
                if (result == JOptionPane.OK_OPTION) {
                    // Retrieve the selected number
                    this.cardNo = (Integer) comboBox.getSelectedItem();
                    this.tilesNo = (Integer) comboBoxTiles.getSelectedItem();

                }
            }
        });

        // add to frame
        buttonPanel.add(configButton);
        homePanel.add(buttonPanel);

    }

    /**
     * Set background image to be added to the homePanel.
     */
    public void setImage(){

        JPanel imagePanel = new JPanel();
        // set panel transparent
        imagePanel.setOpaque(false);
        imagePanel.setBackground(null);
        imagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imagePanel.setBounds(1080/2+85, 720/2-40, 300, 160);

        String filename = "/images/dragon.png";
        ImageIcon dragonImage = new ImageIcon(new ImageIcon(getClass().getResource(filename)).getImage().getScaledInstance(280,160,Image.SCALE_SMOOTH));

        JLabel labelImage = new JLabel(dragonImage);

        imagePanel.add(labelImage);
        homePanel.add(imagePanel);

    }

    /**
     * Configure help and info button to be added to homePanel.
     */
    public void setupButtons(){
        InfoButton infoButton = new InfoButton(this);
        MusicButton musicButton = new MusicButton();
        homePanel.add(infoButton);
        homePanel.add(musicButton);
    }

}