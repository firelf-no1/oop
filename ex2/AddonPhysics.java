/** 
 * FILE : AddonPhysics.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Extension to the physics , allows interaction with the location of the 
 * physics object and with the angle.
 */
import oop.ex2.SpaceShipPhysics;

public class AddonPhysics extends SpaceShipPhysics {
	
	/**
	 * Sets the angle to a given angle.
	 * @param angle to set to.
	 */
	protected void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Changes the location of the physics object
	 * @param x The x value to put.
	 * @param y The y value to put.
	 */
	protected void setLocation(double x,double y){
		this.x = x;
		this.y = y;
	}
}
