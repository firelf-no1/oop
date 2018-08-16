package filesprocessing.orders;

import filesprocessing.exceptions.TypeOneErrors;

/**
 * Order factory according to the factory design.
 */
public class OrderFactory {
    private static final String HASH = "#",	REVERSE = "REVERSE";

    /**
     * @param orderString string we need to convert to Order.
     * @return the Order the string represent.
     * @throws TypeOneErrors
     */
    public static OrderInterface createOrder(String orderString) throws TypeOneErrors {
        boolean isReverse = false;
        OrderInterface order;
        //split the string with #
        String[] array = orderString.split(HASH);
        //check if REVERSE in the end of the string
        try { if (array[array.length - 1].equals(REVERSE)) { isReverse = true; } }
        catch (ArrayIndexOutOfBoundsException e){ throw new TypeOneErrors(); }
        //default order
        order = new AbsOrder();
        //check all cases given and send an object according to what needed.
        switch(array[0]) {
            case "abs":
                order = new AbsOrder();
                break;
            case "type":
                order = new TypeOrder();
                break;
            case "size":
                order =  new SizeOrder();
                break;
            default:
                throw new TypeOneErrors();
        }
        if(isReverse) { order = new ReverseOrder(order); }
        return order;
    }
}