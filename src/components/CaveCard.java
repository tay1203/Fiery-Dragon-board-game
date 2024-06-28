package components;

import animals.Animal;
import javax.swing.*;
import java.awt.*;

/**
 * {@code CaveCard} class is one of the {@code GameComponent} of Fiery Dragons game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 * @author Wong Jia Xuan
 */
public class CaveCard extends GameComponent {

    /**
     * Constructor of the CaveCard class.
     *
     * @param animal an instance of an Animal
     */
    public CaveCard(Animal animal){
        this.setAnimal(animal);
        this.setPoint(new Point(0,0));
    }

    /**
     * Paint the CaveCard component.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String filename = "/images/cave_" + this.getAnimal().toString() + ".png";
        Image caveCardImage = new ImageIcon(getClass().getResource(filename)).getImage();
        g.drawImage(caveCardImage,0,0,100,100, null);
    }

}
