/**
 * FILE : SpaceShipFactory.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * A Factory for spaceships , It is used by the supplied driver to create all the spaceship objects
 * according to the command line arguments.
 */
import oop.ex2.*;
import java.util.HashMap;
import java.util.Map;

public class SpaceShipFactory {
    public static SpaceShip[] createSpaceShips(String[] args){
        // Maps ship classes from the string
        Map<String, Class<? extends SpaceShip>> shipTypes = new HashMap<String, Class<? extends SpaceShip>>();
        shipTypes.put("h", HumanShip.class);
        shipTypes.put("d", DrunkardShip.class);
        shipTypes.put("r", RunnerShip.class);
        shipTypes.put("a", AggressiveShip.class);
        shipTypes.put("b", BasherShip.class);
        shipTypes.put("s", SpecialShip.class);

        SpaceShip[] ships = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                // Instantiate a ship in the array
                ships[i] = (shipTypes.get(args[i]).newInstance());
            } catch (Exception e) {
                System.out.println("Illegal ship types");
            }
        }
        return ships;
    }
}
