/**
 * FILE : Drunkard.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * A ship with a drunken pilot, This ship will escape from other ships and tries to fire at the
 * other direction.
 */
public class DrunkardShip extends ComputerShip {

    /**
     * It will always accelerate,
     * and turn away from the nearest ship. When its angle to the nearest ship is more than 0.25
     * radians, it will try to fire
     */

    @Override
    public void doSpecificAction(SpaceWars game) {
        SpaceShip otherShip = game.getClosestShipTo(this);
        // Escape
        moveInteract(otherShip, Interaction.escape);
        // Get the angle and if its good trys to shot
        if (pos.angleTo(otherShip.getPhysics()) > Math.abs(SMALL_ANGLE_DRUNKARD)) {
            fire(game);
        }
    }
}
