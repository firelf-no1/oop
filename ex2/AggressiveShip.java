/** 
 * FILE : AggressiveShip.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Aggressive ship, This ship pursues other ships and tries to fire at them.
 */
public class AggressiveShip extends ComputerShip {

	/**
	 * The ship will always accelerate,
	 * and turn towards the nearest ship. When its angle to the nearest ship is less than 0.21
	 * radians, it will try to fire
	 */
	@Override
	public void doSpecificAction(SpaceWars game) {
		SpaceShip otherShip = game.getClosestShipTo(this);
		// Pursue
		moveInteract(otherShip, Interaction.pursue);
		// Get angle and shoot if good
		if (pos.angleTo(otherShip.getPhysics()) <= Math.abs(SMALL_ANGLE_AGGRESSIVE)) {
			fire(game);
		}
	}
}
