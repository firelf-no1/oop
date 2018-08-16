/**
 * FILE : SpecialShip.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Special ship, shoots 6 cannons in different directions and teleport away from combat.
 * I increased the shot delay in order to reduce game difficulty.
 */
import oop.ex2.SpaceShipPhysics;

public class SpecialShip extends ComputerShip {

	private static final int SHOTS_DELAY = 24;

	/**
	 * Holds the cannons , represented as an ExtendablePhysics objects.
	 */
	private AddonPhysics[] cannonsPhysics = { new AddonPhysics(),
			new AddonPhysics(),
			new AddonPhysics(),
			new AddonPhysics(),
			new AddonPhysics() };

	/**
	 * Override the normal fire to shoot a special 6 cannons star shaped shots.
	 */
	public void fire(SpaceWars game) {
		if (shotsTurnCounter <= 0) {
			game.addShot(pos);
			// Shoot all cannons
			for (SpaceShipPhysics direction : cannonsPhysics) {
				game.addShot(direction);
			}
			shotsTurnCounter = SHOTS_DELAY;
		}
	}

	/**
	 * If a ship gets to close it fires a special shot and teleports.
	 */
	@Override
	public void doSpecificAction(SpaceWars game) {
		SpaceShip otherShip = game.getClosestShipTo(this);
        moveInteract(otherShip, Interaction.escape);
		if (otherShip.getPhysics().distanceFrom(pos) <= SMALL_DISTANCE_SPECIAL) {
			double angleDif = Math.PI / 3 ;
			for (int i = 0; i < cannonsPhysics.length; i++) {
				cannonsPhysics[i].setAngle(pos.getAngle() + angleDif * (i + 1));
				cannonsPhysics[i].setLocation(pos.getX(), pos.getY());
			}
			fire(game);
			teleport();
		}
	}
}
