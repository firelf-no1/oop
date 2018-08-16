/**
 * FILE : ComputerShip.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Abstract class for all the computer ships.
 */
import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.Image;

public abstract class ComputerShip extends SpaceShip {
	
	/**
	 * Types of movement interactions can be escape, or pursue.
	 */
	protected enum Interaction {
		escape, pursue
	};

	protected final double SMALL_ANGLE_AGGRESSIVE = 0.2;
	protected final double SMALL_DISTANCE_RUNNER = 0.24;
	protected final double SMALL_ANGLE_RUNNER = 0.22;
	protected final double SMALL_DISTANCE_BASHER = 0.18;
	protected final double SMALL_ANGLE_DRUNKARD = 0.25;
	protected final double SMALL_DISTANCE_SPECIAL = 0.15;

	/**
	 * Returns the ship image
	 */
	public Image getImage() {
		if (shieldsUp) {
			return AddonGameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		}
		return AddonGameGUI.ENEMY_SPACESHIP_IMAGE;
	}

	/**
	 * Moves the ship with an interaction relative to another ship.
	 * @param otherShip The ship to interact with.
	 * @param action The type of interaction : pursue or escape.
	 */
	protected void moveInteract(SpaceShip otherShip, Interaction action) {
		SpaceShipPhysics otherPhysics = otherShip.getPhysics();
		int direction = FORWARD;
		// If the Ship is to our right
		if (pos.angleTo(otherPhysics) < 0) {
			if (action == Interaction.pursue) {
				direction = RIGHT;
			}
			else {
				direction = LEFT;
			}
		}
		// If the Ship is to our left
		else if (pos.angleTo(otherPhysics) > 0) {
			if (action == Interaction.pursue) {
				direction = LEFT;
			} else {
				direction = RIGHT;
			}
		}
		// If the ship is directly in front of us - keep forward
		pos.move(true, direction);
	}
}
