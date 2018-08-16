/** 
 * FILE : HumanShip.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * The human player ship, opperiting the spaceship by the input of the user.
 */

import oop.ex2.GameGUI;
import java.awt.Image;


public class HumanShip extends SpaceShip {

	/**
	 * Handles user input
	 */
	@Override
	public void doSpecificAction(SpaceWars game) {
		GameGUI gui = game.getGUI();
		int direction = FORWARD ;
		boolean accelerate = false;
		// Handle the input from the user
		if (gui.isTeleportPressed()) {	teleport(); }
		if (gui.isLeftPressed()) { direction = LEFT; }
		else if (gui.isRightPressed()) { direction = RIGHT; }
		if (gui.isUpPressed()) { accelerate = true; }
		// Move with the given input
		pos.move(accelerate, direction);		
		if (gui.isShieldsPressed()) { shieldOn(); }
		if (gui.isShotPressed()) { fire(game);	}
	}

	/**
	 * Sets the ships image.
	 */
	@Override
	public Image getImage() {
		if (shieldsUp) {
			return AddonGameGUI.SPACESHIP_IMAGE_SHIELD;
		}
		return AddonGameGUI.SPACESHIP_IMAGE;
	}

}
 