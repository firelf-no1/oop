/** 
 * FILE : RunnerShip.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Runner ship, tries to run away from combat and teleport if being aimed at.
 */
import oop.ex2.SpaceShipPhysics;

public class RunnerShip extends ComputerShip {

	/**
	 * Tries to escape other ships, using spying ability.
	 */
	@Override
	public void doSpecificAction(SpaceWars game) {
		SpaceShip otherShip = game.getClosestShipTo(this);
		moveInteract(otherShip, Interaction.escape);		
		spy(otherShip);	
	}

	/**
	 * The ship spies on another ship , the ship will try to teleport if a ship is close and aiming the cannon
	 * st her.
	 */
	private void spy(SpaceShip otherShip) {
		SpaceShipPhysics otherPhysics = otherShip.getPhysics();
		if (pos.distanceFrom(otherPhysics) <= SMALL_DISTANCE_RUNNER) {
			if (Math.abs(otherPhysics.angleTo(pos)) < SMALL_ANGLE_RUNNER ) {
				teleport();
			}
		}
	}

}
