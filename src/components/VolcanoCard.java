package components;

import animals.Animal;
import javax.swing.*;
import java.awt.*;

/**
 * {@code VolcanoCard} class represents volcano cards' tiles
 * which is one of the {@code GameComponent} of Fiery Dragons game.
 *
 * Created by:
 * @author Tay Ming Hui
 *
 * Modified by:
 * @author Koe Rui En
 */
public class VolcanoCard extends GameComponent {

    /**
     * Constructor of the VolcanoCard class.
     *
     * @param animal an instance of an Animal
     */
    public VolcanoCard(Animal animal){
        this.setAnimal(animal);
        this.setOccupied(false);
    }

    /**
     * A method to get the new point of VolcanoCard's instance.
     *
     * @return return a new point of VolcanoCard's instance
     */
    public Point getPoint(){
        int x = this.getX()+15;
        int y = this.getY()-30;
        return new Point(x,y);
    }

    /**
     * Paint the VolcanoCard component.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image volcanoCardImage = new ImageIcon(getClass().getResource("/images/"+this.getAnimal().toString()+"_1.png")).getImage();
        g.drawImage(volcanoCardImage,0,0,50,50,null);
    }
}
