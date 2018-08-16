package oop.ex6.ex6Exception;

/**
 * This class is an extension to Exception, and will be used to be thrown whenever a s-java code format
 * violation were found.
 */
public class Ex6Exception extends Exception {

    // Constants

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public Ex6Exception(){}

    /**
     * Constructor - with message
     * @param message - message describes the cause for exception
     */
    public Ex6Exception(String message) {super(message);}

    /**
     * Method uses to print the message, describing the Ex6Exception cause.
     */
    public void exceptionPrint() {System.err.println(this.toString());}
}