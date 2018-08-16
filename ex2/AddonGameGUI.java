/**
 * FILE : AddonGameGUI.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Extension to the GameGUI , allows me to change the ships image.
 */
import javax.swing.*;
import java.awt.*;


public class AddonGameGUI {

    /**
     * The image of the human player's spaceship
     */
    public static final Image SPACESHIP_IMAGE = createImageIcon("humanship.gif", "");

    /**
     * The image of the human player's spaceship with an active shield
     */
    public static final Image SPACESHIP_IMAGE_SHIELD = createImageIcon("humanshipshild.gif",
            "");

    /**
     * The image of a spaceship controlled by the computer.
     */
    public static final Image ENEMY_SPACESHIP_IMAGE = createImageIcon("computership.gif", "");

    /**
     * The image of a spaceship controlled by the computer, with an active shield.
     */
    public static final Image ENEMY_SPACESHIP_IMAGE_SHIELD = createImageIcon("computershipshild.gif", "");

    private static Image createImageIcon(String path, String description) {
        java.net.URL imgURL = AddonGameGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}