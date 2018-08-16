/**
 * FILE : SpaceShip.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Abstract class for all of the space ships.
 */
import oop.ex2.*;
import java.awt.Image;


/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *
 * @author oop
 */
public abstract class SpaceShip{

    private static final int SHIELD_ENERGY = 3;
    private static final int SHOT_ENERGY = 19;
    private static final int TEL_ENERGY = 140;
    protected int shotsTurnCounter = 0;
    private static final int SHOTS_DELAY = 8;
    private static final int STARTING_HEALTH = 22;
    private static final int STARTING_ENERGY = 190;
    private static final int FINAL_ENERGY = 210;
    private static final int ENERGY_INCRESE = 18;
    protected static final int LEFT = 1;
    protected static final int RIGHT = -1;
    protected static final int FORWARD = 0;

    /**
     * The position and physics of the ship.
     */
    protected SpaceShipPhysics pos;
    protected boolean shieldsUp;
    protected int health;
    protected int energy;
    protected int finalenergy;


    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shotsTurnCounter--;
        shieldsUp = false;
        doSpecificAction(game);
        if (energy + 1 <= finalenergy) {
            energy++;
        }
        else {
            energy = finalenergy;
        }
    }

    /**
     * Does the specific action of the ship (each spaceship as a diffrent implementesin of it)
     * all specific behavior of the ship (such as movement , shooting etc.) should be implemented here.
     * @param game The game controller.
     */
    public abstract void doSpecificAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip(){
        if (!shieldsUp) {
            health--;
        }
        else {
            finalenergy = finalenergy + ENERGY_INCRESE;
            if (energy + ENERGY_INCRESE <= finalenergy) {
                energy = energy + ENERGY_INCRESE;
            }
            else {
                energy = finalenergy;
            }

        }

    }

    /**
     * calls reset , sets random position and maximum health/energy.
     */
    public SpaceShip() {
        reset();
    }


    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        pos = new SpaceShipPhysics();
        shieldsUp = false;
        health = STARTING_HEALTH;
        energy = STARTING_ENERGY;
        finalenergy = FINAL_ENERGY;

    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (health <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return pos;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldsUp) {
            health--;
        }

    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        return null;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (energy >= SHOT_ENERGY && shotsTurnCounter <= 0) {
            game.addShot(pos);
            energy -= SHOT_ENERGY;
            shotsTurnCounter = SHOTS_DELAY;
        }
       
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (energy >= SHIELD_ENERGY) {
            shieldsUp = true;
            energy -= SHIELD_ENERGY;
        }
        
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (energy >= TEL_ENERGY) {
            energy -= TEL_ENERGY;
            pos = new SpaceShipPhysics();
        }
       
    }
    
}
