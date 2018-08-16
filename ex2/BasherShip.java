/** 
 * FILE : BasherShip.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Basher ship, pursue enemy and attempts to ram him.
 */
public class BasherShip extends ComputerShip {

	/**
	 * Pursue other ships , If it gets within a distance of 0.19 units (inclusive)
	 * from another ship, it will attempt to turn on its shields and trys to ram her.
	 */
	public void doSpecificAction(SpaceWars game) {
		SpaceShip otherShip = game.getClosestShipTo(this);
		moveInteract(otherShip, Interaction.pursue);
		// Trys to ram with the shields up
		if (otherShip.getPhysics().distanceFrom(pos) <= SMALL_DISTANCE_BASHER) {
			shieldOn();
		}
	}
}
