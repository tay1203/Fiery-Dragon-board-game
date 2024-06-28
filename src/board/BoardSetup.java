package board;

import javax.swing.*;
import java.awt.*;
/**
 * {@code BoardSetup} class extends JPanel to set up the board layout for the game.
 * It initializes and arranges various panels such as chit cards, volcano cards, caves, and tokens.

 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class BoardSetup extends JPanel{

    // chit card's panel
    /**
     * panel displaying the chit card
     */
    private JPanel chitCardPanel;

    // volcano cards' panel
    /**
     * panel displaying the volcano card
     */
    private JPanel volcanoCardPanel = new JPanel();

    // caves' panel
    /**
     * panel displaying cave1
     */
    private JPanel cave1Panel = new JPanel();
    /**
     * panel displaying cave2
     */
    private JPanel cave2Panel = new JPanel();
    /**
     * panel displaying cave3
     */
    private JPanel cave3Panel = new JPanel();
    /**
     * panel displaying cave4
     */
    private JPanel cave4Panel = new JPanel();

    // token's panel
    /**
     * panel displaying token1
     */
    private JPanel token1 = new JPanel();
    /**
     * panel displaying token2
     */
    private JPanel token2 = new JPanel();
    /**
     * panel displaying token3
     */
    private JPanel token3 = new JPanel();
    /**
     * panel displaying token4
     */
    private JPanel token4 = new JPanel();

    /**
     * Constructor for BoardSetup.
     * Initializes the panel and sets up various components.
     */
    public BoardSetup(){
        this.setBackground(Color.ORANGE);
        this.setLayout(null);

        setupToken();
        setupChitCardPanel();
        setupCavePanel();
        setupVolcanoCardPanel();
    }

    /**
     * Gets the chit card panel.
     *
     * @return the chit card panel
     */
    public JPanel getChitCardPanel() {
        return chitCardPanel;
    }

    /**
     * Sets up the chit card panel with a grid layout and specific bounds.
     */
    public void setupChitCardPanel(){
        chitCardPanel = new JPanel();
        chitCardPanel.setLayout(new GridLayout(5, 4,1,1));
        chitCardPanel.setBounds(405,223,255,270);
        chitCardPanel.setOpaque(false);
        this.add(chitCardPanel);
    }

    /**
     * Gets the volcano card panel.
     *
     * @return the volcano card panel
     */
    public JPanel getVolcanoCardPanel() {
        return volcanoCardPanel;
    }

    /**
     * Sets up the volcano card panel with null layout and specific bounds.
     */
    public void setupVolcanoCardPanel(){
        volcanoCardPanel.setLayout(null);
        volcanoCardPanel.setBounds(0,0,1080,720);
        volcanoCardPanel.setOpaque(false);
        this.add(volcanoCardPanel);
    }

    /**
     * Gets the cave panel based on the given number.
     *
     * @param number the number representing the cave panel (0 to 3)
     *
     * @return the specified cave panel
     */
    public JPanel getCavePanel(int number) {
        if (number == 0){
            return cave1Panel;
        } else if (number == 1) {
            return cave2Panel;
        } else if (number == 2) {
            return cave3Panel;
        } else {
            return cave4Panel;
        }
    }

    /**
     * Sets up the cave panels with specific bounds, layouts, and opacities.
     */
    public void setupCavePanel(){

//        cave1Panel.setBounds(638,35,101,105);
        cave1Panel.setOpaque(false);
        cave1Panel.setLayout(new BorderLayout());
        this.add(cave1Panel);

//        cave2Panel.setBounds(740,456,105,105);
        cave2Panel.setOpaque(false);
        cave2Panel.setLayout(new BorderLayout());
        this.add(cave2Panel);

//        cave3Panel.setBounds(312,561,105,105);
        cave3Panel.setOpaque(false);
        cave3Panel.setLayout(new BorderLayout());
        this.add(cave3Panel);

//        cave4Panel.setBounds(202,147,105,105);
        cave4Panel.setOpaque(false);
        cave4Panel.setLayout(new BorderLayout());
        this.add(cave4Panel);

    }

    /**
     * Gets the token panel based on the given number.
     *
     * @param number the number representing the token panel (0 to 3)
     *
     * @return the specified token panel
     */
    public JPanel getToken(int number) {
        if (number == 0){
            return token1;
        } else if (number == 1) {
            return token2;
        } else if (number == 2) {
            return token3;
        } else {
            return token4;
        }
    }

    /**
     * Sets up the token panels and adds them to the main panel.
     */
    public void setupToken(){
        //token1.setBounds(610,60,820,820);
        token1.setOpaque(false);
        this.add(token1);
        //token2.setBounds(890,370,45,65);
        token2.setOpaque(false);
        this.add(token2);
        //token3.setBounds(680,640,45,65);
        token3.setOpaque(false);
        this.add(token3);
        //token4.setBounds(325,0,820,820);
        token4.setOpaque(false);
        this.add(token4);
    }

    /**
     * Overrides the paintComponent method to draw custom components on the panel.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension arcs = new Dimension(600, 600);
        int width = 600;
        int height = 600;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(92, 64, 51));
        g2d.fillRoundRect(227, 50, width - 5, height - 5, arcs.width, arcs.height);

        Dimension arcs2 = new Dimension(380, 380);
        int width2 = 380;
        int height2 = 380;
        Graphics2D g2d2 = (Graphics2D) g;
        g2d2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d2.setColor(Color.orange);
        g2d2.fillRoundRect(338, 165, width2 - 5, height2 - 5, arcs2.width, arcs2.height);

    }

}
